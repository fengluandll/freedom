package mx.com.televisa.divfilmica.model.connection;

/**
* Project: CONS-0626
*
* File: ConnectionWrapper.java
*
* Created on: Febrero 08, 2012 at 12:00
*
* Copyright (c) - Kaz Consulting 
*/

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;

import javax.sql.DataSource;

import mx.com.televisa.divfilmica.automatic.HypDivExecuteProcess;
import mx.com.televisa.divfilmica.model.packages.Procedure;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


/**
 * Clase que maneja las conexiones hacia la Base de Datos
 *
 * @author Kaz Consulting / Iván Castañeda Loeza
 *
 * @version 1.0.0
 *
 * @date Julio 11, 2011, 12:00 pm
 */
public class ConnectionWrapper {
    
    static Logger logger = Logger.getLogger(ConnectionWrapper.class);

    /** La conexion hacia la base de datos. */
    private Connection connection;


    /**
     * Clase que establece la coneccion y maneja los reitentos
     */
    public ConnectionWrapper() {
        
        PropertyConfigurator.configure("src/log4j.properties");
        ResourceBundle bundle = ResourceBundle.getBundle("DataConnection");
        String user = bundle.getString("com.mx.televisa.divfilmica.usuario");
        String pass = bundle.getString("com.mx.televisa.divfilmica.password");
        String url = bundle.getString("com.mx.televisa.divfilmica.diverManager");
        String driver = bundle.getString("com.mx.televisa.divfilmica.class.forname");
        int numIntentos = Integer.parseInt(bundle.getString("com.mx.televisa.divfilmica.numIntentos"));
        long tiempoIntentos = Long.parseLong(bundle.getString("com.mx.televisa.divfilmica.tiempoIntentos.segundos"));
        
        int count=0;
        int errorCode;
        do{
                errorCode=0;
                logger.info("Intento de conexion a ERP...."+(count+1));
            
            try{
                Class.forName(driver).newInstance();
                connection = DriverManager.getConnection(url,user,pass);
            }catch(SQLException e){
                errorCode = e.getErrorCode();
                logger.info("CODIGO DE ERROR DE CONECCION "+errorCode);
                if(errorCode == 17002){
                   count++;  
                   System.out.println(errorCode);
                   logger.error(e.getMessage(),e);
                }
            } catch (ClassNotFoundException e) {
                logger.error(e.getMessage(),e);
            } catch (InstantiationException e) {
                logger.error(e.getMessage(),e);
            } catch (IllegalAccessException e) {
                logger.error(e.getMessage(),e);
            }
            if(count == numIntentos){
                logger.error("CONEXION FALLIDA, NO SE PUDO ESTABLECER LA CONEXION!!");
                logger.error("NO DE INTENTOS... "+numIntentos);
                System.exit(0);
            }
            if(errorCode==17002){
                try {
                    logger.info("Esperando siguiente intento...");
                    Thread.sleep(tiempoIntentos*1000);
                } catch (InterruptedException e) {
                    logger.error(e.getMessage(),e);
                }
            }
        }while(count < numIntentos && errorCode==17002);
                 
        
    }


    /**
     * Encapsula el metodo homonimo de la clase Statement.
     * Revisar como se comportan los métodos que usan este,
     * para identificar el cierre de las conexiones.
     *
     * @param Una cadena SQL SELECT lista para ser ejecutada en la base de datos.
     * @return Un ResultSet con el resultado de la instruccion.
     */
    public ResultSet executeQuery(String tsSql) throws Exception {

        return connection.createStatement().executeQuery(tsSql);
    }

    /**
     * Encapsula el metodo homonimo de la clase Statement.
     * Revisar como se comportan los métodos que usan este,
     * para identificar el cierre de las conexiones.
     *
     * @param Una cadena SQL INSERT, UPDATE o DELETE lista para ser ejecutada en la base de datos.
     * @return El número de registros modificados.
     */
    public int executeUpdate(String tsSql) throws Exception {

        return connection.createStatement().executeUpdate(tsSql);
    }

    /**
     *Encapsula el método hominimo de la clase Statement
     */
    public PreparedStatement prepareStatement(String tsSql) throws SQLException {

        return connection.prepareStatement(tsSql);
    }

    /**
     *Encapsula el método hominimo de la clase Statement
     */
    public Statement createStatement() throws SQLException {

        return connection.createStatement();
    }

    /*public CallableStatement prepareCall(Procedure procedure) throws SQLException {

      return prepareCall(procedure.getName(), procedure.getParamCount());
    }*/


    /**
     *Encapsula el método hominimo de la clase Statement para el llamado a SP's
     * sin parámetros
     */
    
    public CallableStatement prepareCall(Procedure toProcedure) throws SQLException {

        return prepareCall(toProcedure.getName(), toProcedure.getParamCount());
    }


    /**
     *Encapsula el método hominimo de la clase Statement para el llamado SP's 
     * con parámtros
     */
    public CallableStatement prepareCall(String tsStoredProcedure,
                                         int tiParamNumber) throws SQLException {

        if(tiParamNumber == 0) {
          return connection.prepareCall("{CALL " + tsStoredProcedure + "}");
        }

        StringBuilder lsSb = new StringBuilder();
        for (int i = 1; i <= tiParamNumber; i++) {
            lsSb.append("?,");
        }

        // Quita la ultima coma
        String lsParams = lsSb.substring(0, (lsSb.length() - 1));
        lsParams = "(" + lsParams + ")";

        return connection.prepareCall("{CALL " + tsStoredProcedure + lsParams +
                                      "}");
    }


    /**
     *Encapsula el método hominimo de la clase Statement para el llamado de SP's
     * con parámetros
     */
    public CallableStatement prepareCallProcedure(String tsStoredProcedure,
                                                  Object... toParams) throws SQLException {

        StringBuilder lsSb = new StringBuilder();
        for (Object param : toParams) {
            if (param.equals("?")) {
                lsSb.append("?,");
            } else {
                lsSb.append("'" + param + "',");
            }
        }
        // Quita la ultima coma
        String lsParams = lsSb.substring(0, (lsSb.length() - 1));
        lsParams = "(" + lsParams + ")";

        // No funciona correctamente con la siguiente linea
        // strParams = strParams.replaceAll("'?'", "?");

        return connection.prepareCall("{CALL " + tsStoredProcedure + lsParams +
                                      "}");
    }

    /**
     *Encapsula el método hominimo de la clase Statement para el llamado de
     *Funciones
     */
    public CallableStatement prepareCallFunction(String tsStoredProcedure,
                                                 Object... toParams) throws SQLException {

        StringBuilder lsSb = new StringBuilder();
        for (Object param : toParams) {
            lsSb.append("'" + param + "',");
        }
        // Quita la ultima coma
        String lsParams = lsSb.substring(0, (lsSb.length() - 1));
        lsParams = "(" + lsParams + ")";

        // No funciona correctamente con la siguiente linea
        // strParams = strParams.replaceAll("'?'", "?");


        //Oracle
        String lsCallableQuery =
            "{ call ? := " + tsStoredProcedure + lsParams + "}";

        //MySQL
        //String callableQuery = "{? = call " + storedProcedure + strParams + "}";


        return connection.prepareCall(lsCallableQuery);
    }


    /**
     *Encapsula el método hominimo de la clase Statement para la obtención de 
     * secuencias
     */
    public int getSecuenceNextValue(String tsSecuencia) throws Exception {

        // Esta implementada en base a secuencias de Oracle,
        // en caso de ser necesario, implementar usando los AUTO_INCREMENT

        String lsQuery = "SELECT " + tsSecuencia + ".nextval AS ID FROM DUAL";
        ResultSet loSet = executeQuery(lsQuery);
        loSet.next();
        return loSet.getInt("ID") + 1;
    }

    /**
     * Método para obtener una conexión
     */
    public Connection getConnection() {

        return connection;
    }


    /**
     * Método para activar desactivar autocommit
     *
     */
    public void begin() throws SQLException {

        connection.setAutoCommit(false);
    }

    /**
     * Método para solicitar commit
     *
     */
    public void commit() throws SQLException {

        connection.commit();
    }

    /**
     * Método para solicitar rollback
     *
     */
    public void rollback() throws SQLException {

        connection.rollback();
    }

    /**
     * Método para cerrar conexión
     */
    public void close() {

        try {
            connection.close();
        } catch (Exception e) {
            connection = null;
        }
    }

    /**
     * Método para cerrar conexiones inactivas
     */
    public static void closeAll(Object... toCloseableObjects) {

        for (Object loCloseable : toCloseableObjects) {

            if (loCloseable != null) {

                if (loCloseable instanceof ResultSet) {
                    try {
                        ((ResultSet)loCloseable).close();
                    } catch (Exception e) {
                    }
                }

                if (loCloseable instanceof Statement) {
                    try {
                        ((Statement)loCloseable).close();
                    } catch (Exception e) {
                    }
                }

                if (loCloseable instanceof Connection) {
                    try {
                        ((Connection)loCloseable).close();
                    } catch (Exception e) {
                    }
                }

                if (loCloseable instanceof ConnectionWrapper) {
                    try {
                        ((ConnectionWrapper)loCloseable).close();
                    } catch (Exception e) {
                    }
                }
            }
        }
    }

}


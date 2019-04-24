package mx.com.televisa.derechocorporativo.data;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import mx.com.televisa.derechocorporativo.config.HardCodeConstants;


public class ConnectionWrapper {
	
	/** La conexion hacia la base de datos. */
    private Connection connection;

    
    /**
     * 
     */
    public ConnectionWrapper() throws Exception {	
        if(HardCodeConstants.USE_DATA_SOURCE) {
        	Context initContext = new InitialContext(); 
        	DataSource ds = (DataSource) initContext.lookup(HardCodeConstants.DATA_SOURCE);
        	connection = ds.getConnection();
        } else {
            Class.forName(HardCodeConstants.JDBC_DRIVER).newInstance();
            connection = DriverManager.getConnection(HardCodeConstants.JDBC_URL, 
            					HardCodeConstants.JDBC_USER, HardCodeConstants.JDBC_PASSWORD);
        }
	}


    /**
     * Encapsula el metodo homonimo de la clase Statement.
     * Revisar como se comportan los m�todos que usan este, 
     * para identificar el cierre de las conexiones.
     * 
     * @param Una cadena SQL SELECT lista para ser ejecutada en la base de datos.
     * @return Un ResultSet con el resultado de la instruccion.
     */
    public ResultSet executeQuery(String sql) throws Exception {
        
    	return connection.createStatement().executeQuery(sql);
    }
    
    /**
     * Encapsula el metodo homonimo de la clase Statement.
     * Revisar como se comportan los m�todos que usan este, 
     * para identificar el cierre de las conexiones.
     * 
     * @param Una cadena SQL INSERT, UPDATE o DELETE lista para ser ejecutada en la base de datos.
     * @return El n�mero de registros modificados.
     */ 
    public int executeUpdate(String sql) throws Exception {
    	
    	return connection.createStatement().executeUpdate(sql);
    }
    
    /**
     *
     */
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        
        return connection.prepareStatement(sql);
    }
    
    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        
        return connection.prepareStatement(sql, resultSetType, resultSetConcurrency);
    }

    /**
     *
     */
    public Statement createStatement() throws SQLException {
        
        return connection.createStatement();
    }    
    
    
    public CallableStatement prepareCall(Procedure storedProcedure) throws SQLException {
    
    	return prepareCall(storedProcedure.getName(), storedProcedure.getParamNum());
    }
    
    public CallableStatement prepareCall(String storedProcedure, int paramCount) throws SQLException {
        
    	StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= paramCount; i++) {
				sb.append("?,");
		}
		
		// Quita la ultima coma
		String strParams = sb.substring(0, (sb.length() - 1));
		strParams = "(" + strParams + ")";

		// No funciona correctamente con la siguiente linea
		// strParams = strParams.replaceAll("'?'", "?");

		return connection.prepareCall("{CALL " + storedProcedure + strParams + "}");
    }

    
    
	/**
    *
    */
	public CallableStatement prepareCallProcedure(String storedProcedure, Object... params) throws SQLException {

		StringBuilder sb = new StringBuilder();
		for (Object param : params) {
			if (param.equals("?")) {
				sb.append("?,");
			} else {
				sb.append("'" + param + "',");
			}
		}
		// Quita la ultima coma
		String strParams = sb.substring(0, (sb.length() - 1));
		strParams = "(" + strParams + ")";

		// No funciona correctamente con la siguiente linea
		// strParams = strParams.replaceAll("'?'", "?");

		return connection.prepareCall("{CALL " + storedProcedure + strParams + "}");
	}

	/**
	   *
	   */
	public CallableStatement prepareCallFunction(String storedProcedure, Object... params) throws SQLException {

		StringBuilder sb = new StringBuilder();
		for (Object param : params) {
			sb.append("'" + param + "',");
		}
		// Quita la ultima coma
		String strParams = sb.substring(0, (sb.length() - 1));
		strParams = "(" + strParams + ")";

		// No funciona correctamente con la siguiente linea
		// strParams = strParams.replaceAll("'?'", "?");

		
		
		//Oracle
		//String callableQuery = "{ call ? := " + storedProcedure + strParams + "}";

		//MySQL
		String callableQuery = "{? = call " + storedProcedure + strParams + "}";

		
		return connection.prepareCall(callableQuery);
	}
    
    /**
     *
     */
    public int getSecuenceNextValue(String secuencia) throws Exception {

    	// Esta implementada en base a secuencias de Oracle, 
    	// en caso de ser necesario, implementar usando los AUTO_INCREMENT
    	
        String query="SELECT " + secuencia + ".nextval AS ID FROM DUAL";
        ResultSet set = executeQuery(query);
        set.next();
        return set.getInt("ID") + 1;
    }
    
    /**
     *
     */
    public Connection getConnection() {
        
        return connection;
    }
    
	
    /**
     * @throws SQLException 
     * 
     */
    public void begin() throws SQLException {
    
    	connection.setAutoCommit(false);
    }
    
    /**
     * @throws SQLException 
     * 
     */
    public void commit() throws SQLException {
    
    	connection.commit();
    }
    
    /**
     * @throws SQLException 
     * 
     */
    public void rollback() throws SQLException {
    
    	connection.rollback();
    }
    
    /**
     * 
     */
    public void close() {
        
        try {
            connection.close();
        } catch(Exception e) {
            connection = null; 
        }
    }
	
    /**
     * 
     */
    public static void closeAll(Object ... closeableObjects) {

		for(Object closeable : closeableObjects) {
		
			if(closeable != null) {
			
				if(closeable instanceof ResultSet) {
					try {
						((ResultSet) closeable).close();
					} catch(Exception e){}
				}

				if(closeable instanceof Statement) {
					try {
						((Statement) closeable).close();
					} catch(Exception e){}
				}
				
				if(closeable instanceof Connection) {
					try {
						((Connection) closeable).close();
					} catch(Exception e){}
				}
				
				if(closeable instanceof ConnectionWrapper) {
					try {
						((ConnectionWrapper) closeable).close();
					} catch(Exception e){}
				}
			}
		}  
    }

}

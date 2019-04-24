package mx.com.televisa.derechocorporativo.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

//import config.ConfigProperties;

public class ServiceLocator {
/*
	 //unica instancia de la clase
    private static ServiceLocator instance = new ServiceLocator();

 //Inicializador de instancia
    {
        // 1. Carga la clase Driver de conexion a la BD
        try {
            String driver = null;
            //driver = ConfigProperties.getProperty("televisa.db.driver");
            driver = "oracle.jdbc.driver.OracleDriver";
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.err.println("Error loading driver: " + e);
        }
    }


    public static ServiceLocator getInstance() {
        return instance;
    }


    public Connection getConnection() throws SQLException  {
        //String url = ConfigProperties.getProperty("televisa.db.url");
        //String user = ConfigProperties.getProperty("televisa.db.user");
        //String pass = ConfigProperties.getProperty("televisa.db.pass");

    	String url = "jdbc:oracle:thin:@10.7.39.28:1549:TVDBDRCOD";
    	String user = "USRDRC";
    	String pass = "u5rDrC_g40";

        Connection connection = null;

            connection = DriverManager.getConnection(url, user, pass);

            //Imprime la informacion de la conexion
            DatabaseMetaData dbMetaData = connection.getMetaData();
            String productName = dbMetaData.getDatabaseProductName();
            String productVersion = dbMetaData.getDatabaseProductVersion();
            System.out.println("Connection: " + connection);
            System.out.println("Database: " + productName);
            System.out.println("Version: " + productVersion);

        return connection;
    }
*/
}

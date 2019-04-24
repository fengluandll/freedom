package com.movemini.config;

public interface HardCodeConstants {

	String CONTEXT_PATH = "SAO";

    boolean USE_DATA_SOURCE = false;
	String DATA_SOURCE = "java:/comp/env/jdbc/SAODataSource";	//Tomcat
	//String DATA_SOURCE = "jdbc/SAODataSource";				//Weblogic

	boolean debug = false;

   String JDBC_DRIVER = "com.mysql.jdbc.Driver";
   
//   String JDBC_URL = "";
//   String JDBC_USER = "";
//   String JDBC_PASSWORD = "";
   
   
   String TEMP_DIR = "/opt/tmp/";			//Mac
	//String TEMP_DIR = "C:/Temp/";			//Win   
	
	String SMTP_MAIL ="notificaciones.sao@gmail.com";
	String SMTP_PWD = "Password46";
	//String SMTP_MAIL = "";
	//String SMTP_MAIL = "";

	String SMTP_AUTH = "true";
	String SMTP_TLS = "true";
	String SMTP_HOST = "smtp.gmail.com";
	String SMTP_PORT = "587";
	String MAIN_FOLDER = "/opt/ProsanteRepository";

    String ID_OMNILINGUA_MXN = "3";
    String ID_OMNILINGUA_USA = "4";
    String ID_A_FUTURO = "5";

    String ID_COTIZACION_INGLES = "2";

    String ID_ROL_TECNICOS = "6";
	
   
   
	//CROSS_TO_PROD
	//String JDBC_URL = "jdbc:mysql://192.168.0.100:3306/sao_db?useSSL=false";
	//String JDBC_USER = "root";
	//String JDBC_PASSWORD = "120315";
	
	//DESARROLLO - OLD
	//	String JDBC_URL = "jdbc:mysql://localhost:3306/sao_db?useSSL=false";
	//	String JDBC_USER = "root";
	//	String JDBC_PASSWORD = "root"; // NAVA
	//String JDBC_PASSWORD = "120315"; //OTROS
	
	//DESARROLLO,  QA y PRODUCCION
	String JDBC_URL = "jdbc:mysql://localhost:3306/sao_db?useSSL=false";
	String JDBC_USER = "root";
	String JDBC_PASSWORD = "root";
    
    
}

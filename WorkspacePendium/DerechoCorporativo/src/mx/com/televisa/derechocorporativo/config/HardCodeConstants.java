/**
* Project: Derecho Corporativo
*
* File: HardCodeConstants.java
*
* Created on: Agosto 31, 2015 at 12:00
*
* Copyright (c) - Televisa - 2015
*/

package mx.com.televisa.derechocorporativo.config;

/**
 * Constantes
 *
 * @author KAZ - CONSULTING/
 *         Iv�n Casta�eda Loeza
 *         Jes�s Argumedo
 *         Eduardo Nava
 *         Ernesto Corona Mendoza
 *
 *
 *
 * @version 1.0.0
 *
 * @date Agosto 31, 2015 at 12:00 pm
 *
 */
public interface HardCodeConstants {

	boolean USE_DATA_SOURCE = true;
	
	//Tomcat
	//String DATA_SOURCE = "java:/comp/env/jdbc/DerechoCorpOracle";

	//Weblogic Televisa
	String DATA_SOURCE = "jdbc/DerechoCorp";
	//Weblogic local
	//String DATA_SOURCE = "jdbc/DerechoCorpLocal";
	//Weblogic prod
	//String DATA_SOURCE = "jdbc/DerechoCorpProd";
	
	
	
	
	String JDBC_DRIVER = "";
	String JDBC_URL = "";
	String JDBC_USER = "";
	String JDBC_PASSWORD = "";
	boolean DEBUG_ENABLED = false;

}
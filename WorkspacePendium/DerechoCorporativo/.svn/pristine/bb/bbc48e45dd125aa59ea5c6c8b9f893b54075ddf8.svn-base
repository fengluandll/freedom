/**
* Project: Derecho Corporativo
*
* File: AppConfig.java
*
* Created on: Agosto 31, 2015 at 12:00
*
* Copyright (c) - Televisa - 2015
*/

package mx.com.televisa.derechocorporativo.config;

import java.sql.CallableStatement;
import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;
import mx.com.televisa.derechocorporativo.data.packages.APP_CONFIG_PKG;

/**
 * Clase configuradora
 *
 * @author KAZ - CONSULTING/
 *         Iván Castañeda Loeza
 *         Jesús Argumedo
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
public class AppConfig {

	public static String getConfigValue(String lsCode) {
	    ConnectionWrapper luConnectionWrapper = null;
	    CallableStatement luCallableStatement = null;

		try {			
			luConnectionWrapper = new ConnectionWrapper();			
			luCallableStatement = luConnectionWrapper.prepareCall(APP_CONFIG_PKG.GET_CONFIG_VAR);
			luCallableStatement.setString(1, lsCode);
			luCallableStatement.registerOutParameter(2, java.sql.Types.VARCHAR);
			luCallableStatement.execute();
			
			return luCallableStatement.getString(2);

		} catch(Exception ex) {			
			ex.printStackTrace();
			
			return ex.toString();
			
		} finally {
			ConnectionWrapper.closeAll(luConnectionWrapper);
		}
	}
}


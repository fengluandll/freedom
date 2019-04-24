/*
* Project: Derecho Corporativo
*
* File: RequestHandlerAjaxCheckBox.java
*
* Created on: Agosto 31, 2015 at 12:00
*
* Copyright (c) - Televisa - 2015
*/

package mx.com.televisa.derechocorporativo.modules.captura;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import javax.servlet.ServletRequest;
import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;
import mx.com.televisa.derechocorporativo.util.FacesUtils;

/**
 * 
 * 
 */
public class RequestHandlerAjaxCheckBox {
	
	/**
	 * 
	 * 
	 */
	public static String handleRequest(ServletRequest tuServletRequest) {
			int liIdEmpresa                       = Integer.parseInt(FacesUtils.getSessionBean().getIdCurrentEmpresa());
			ConnectionWrapper luConnectionWrapper = null;
			ResultSet         luResultSet         = null;
			CallableStatement luCallableStatement = null;
			int               linUserId           = Integer.parseInt(FacesUtils.getSessionBean().getIdUser());
			
			try {
                luConnectionWrapper = new ConnectionWrapper();			
                luCallableStatement = luConnectionWrapper.prepareCall("DERCORP_CAPTURA_PKG.DELETE_ONE_CHECKBOX_INFO_PR", 2);
                luCallableStatement.setInt(1,liIdEmpresa);
                luCallableStatement.setString(2,tuServletRequest.getParameter("CODE"));
                luCallableStatement.execute();
                luCallableStatement.close();

				if(tuServletRequest.getParameter("VALUE") != null && !tuServletRequest.getParameter("VALUE").equals("")){
				    luCallableStatement = luConnectionWrapper.prepareCall("DERCORP_CAPTURA_PKG.SAVE_INFO_PR", 4);
				    luCallableStatement.setInt(1,liIdEmpresa);
				    luCallableStatement.setString(2, tuServletRequest.getParameter("CODE"));
				    luCallableStatement.setString(3, tuServletRequest.getParameter("CODE"));
				    luCallableStatement.setInt(4,linUserId);
				    luCallableStatement.execute();
				}
			} catch(Exception ex) {
				ex.printStackTrace();
				return ex.toString();

			} finally {
				ConnectionWrapper.closeAll(luResultSet, luCallableStatement, luConnectionWrapper);
			}
			
			return "OK";

	}
}

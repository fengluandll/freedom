package mx.com.televisa.derechocorporativo.modules.login;


import java.sql.CallableStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.servlet.ServletRequest;

import org.apache.log4j.Logger;

import mx.com.televisa.derechocorporativo.bean.SessionBean;
import mx.com.televisa.derechocorporativo.config.NavigationRules;
import mx.com.televisa.derechocorporativo.daos.FusionDAO;
import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;
//import mx.com.televisa.derechocorporativo.data.HQLBeanList;
//import mx.com.televisa.derechocorporativo.model.VUserLogin;
import mx.com.televisa.derechocorporativo.util.FacesUtils;

public class LoginBean {
	
	private final static Logger log = Logger.getLogger(LoginBean.class);

	private String txtUsername;
	private String txtPassword;
	private String lblMessage;

	

	public LoginBean() {
	
		ServletRequest request = FacesUtils.getRequest();
		
		boolean doLogout = (request.getParameter("logout") != null) ? 
							request.getParameter("logout").equals("true") : false;
		
		if(doLogout) {
			lblMessage = "La salida del sistema ha sido existosa";
			this.doLogout();
			//FacesUtils.getSession().invalidate();
			FacesUtils.getSession().setAttribute("sessionBean",null);
			
		}
		
		//
		
	/*	SessionBean sessionBean = FacesUtils.getSessionBean();
		
		sessionBean.setIdCurrentEmpresa(null);
		sessionBean.setNomUser(null);
		sessionBean.setIdUser(null);
		sessionBean.setIdRol(null);
		sessionBean.setNomUser(null);*/
		
		
		
	}




	public String doLogin() {
		
		try {
			
			String prop1 = System.getProperty("java.endorsed.dirs");
			String prop2 = System.getProperty("catalina.home");
			String prop3 = System.getProperty("java.class.path");
			
			
			System.out.println("java.endorsed.dirs: " + prop1);
			System.out.println("catalina.home: " + prop2);
			System.out.println("java.class.path: " + prop3);
		
		}
		catch(Exception ex) {
			
			System.out.println(ex.toString());
			ex.printStackTrace();
		}

		
		
		ConnectionWrapper conn = null;
		
		String lstResult = "";
		String lstMessage = "";
		int	   linUserId = 0;
		String lstUserLongName = "";
		int    linRolId = 0;
		String lstRolName = "";
		int    linUserAccesId = 0;
		
		try {
			
			conn = new ConnectionWrapper();
		
			System.out.println("Usuario:" + this.txtUsername);	
			//System.out.println("Password:" + this.txtPassword);
			
			CallableStatement callStmt = conn.prepareCall(
						"SS_LOGIN_PKG.DO_LOGIN_PR", 9);
			
			callStmt.setString(1, this.txtUsername);
			callStmt.setString(2, this.txtPassword);
			callStmt.registerOutParameter(3, java.sql.Types.VARCHAR);
			callStmt.registerOutParameter(4, java.sql.Types.VARCHAR);
			callStmt.registerOutParameter(5, java.sql.Types.NUMERIC);
			callStmt.registerOutParameter(6, java.sql.Types.VARCHAR);
			callStmt.registerOutParameter(7, java.sql.Types.VARCHAR);
			callStmt.registerOutParameter(8, java.sql.Types.VARCHAR);
			callStmt.registerOutParameter(9, java.sql.Types.NUMERIC);
			callStmt.execute();			
			
			lstResult = callStmt.getString(3);
			lstMessage = callStmt.getString(4);
			linUserId = callStmt.getInt(5);
			lstUserLongName = callStmt.getString(6);
			linRolId = callStmt.getInt(7);
			lstRolName = callStmt.getString(8);
			linUserAccesId = callStmt.getInt(9);
			log.info("ESTATUS::::::"+lstResult);
			log.error("ERROR");
			log.debug("debug");
			log.fatal("fatal");
			
			if (lstResult.equals("OK")){
				FacesUtils.getSessionBean().setIdUser(String.valueOf(linUserId));
				FacesUtils.getSessionBean().setIdRol(String.valueOf(linRolId));
				FacesUtils.getSessionBean().setNomUser(String.valueOf(lstUserLongName));
				FacesUtils.getSessionBean().setIdUserAcces(Integer.valueOf(linUserAccesId));
				return NavigationRules.AUTHORIZED_ACCESS;
			} else if(lstResult.equals("PASSWORD_SET_REQ")||lstResult.equals("PASSWORD_CHANGE_REQ") || lstResult.equals("CHANGE_PWD_REQUIRED_REQ")){
				this.lblMessage = lstMessage;
				FacesUtils.getSessionBean().setIdUser(String.valueOf(linUserId));
				FacesUtils.getSessionBean().setIdRol(String.valueOf(linRolId));
				FacesUtils.getSessionBean().setNomUser(String.valueOf(lstUserLongName));
				FacesUtils.getSessionBean().setIdUserAcces(Integer.valueOf(linUserAccesId));
				return NavigationRules.CHANGE_PASSWORD;
			} else {
				this.lblMessage = lstMessage;
				return null;
			}
			
		} catch(Exception ex) {
			
			ex.printStackTrace();
			
			return ex.getMessage();
			
		} finally {
			
			ConnectionWrapper.closeAll(conn);
		}
		
		//return NavigationRules.AUTHORIZED_ACCESS;
	}
	
	
	public void doLogout(){

		ConnectionWrapper conn = null;
		int linUserId  = 0;
		int linAccesLogId = 0;
		try{
			
			linUserId 	  = Integer.valueOf(FacesUtils.getSessionBean().getIdUser());
			linAccesLogId = Integer.valueOf(FacesUtils.getSessionBean().getIdUserAcces());
		
		}  catch(Exception ex) {
			linUserId = 0;
			linAccesLogId = 0;
		}
			
		
		try {
			
			conn = new ConnectionWrapper();
			System.out.println("Logging out...");
			
			CallableStatement callStmt = conn.prepareCall(
						"SS_LOGIN_PKG.DO_LOGOUT_PR", 2);
			
			callStmt.setInt(1, linUserId);
			callStmt.setInt(2, linAccesLogId);
			callStmt.execute();			
			

		} catch(Exception ex) {
			
			ex.printStackTrace();
			
		} finally {
			
			ConnectionWrapper.closeAll(conn);
		}
		
		//return NavigationRules.AUTHORIZED_ACCESS; */
	}
		

	
	
	
	public String getTxtUsername() {
		return txtUsername;
	}
	public void setTxtUsername(String txtUsername) {
		this.txtUsername = txtUsername;
	}
	public String getTxtPassword() {
		return txtPassword;
	}
	public void setTxtPassword(String txtPassword) {
		this.txtPassword = txtPassword;
	}
	public String getLblMessage() {
		return lblMessage;
	}
	public void setLblMessage(String lblMessage) {
		this.lblMessage = lblMessage;
	}


}

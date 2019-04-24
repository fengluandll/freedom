package mx.com.televisa.derechocorporativo.modules.login;


import java.sql.CallableStatement;
import java.sql.Statement;
import java.util.StringTokenizer;

import javax.servlet.ServletRequest;

import mx.com.televisa.derechocorporativo.bean.SessionBean;
import mx.com.televisa.derechocorporativo.config.NavigationRules;
import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;
//import mx.com.televisa.derechocorporativo.data.HQLBeanList;
//import mx.com.televisa.derechocorporativo.model.VUserLogin;
import mx.com.televisa.derechocorporativo.util.FacesUtils;

public class ChangePasswordBean {

	private String txtUserName;
	private String txtOdlPassword;
	private String txtPassword1;
	private String txtPassword2;
	private String lblMessage;

	

	public ChangePasswordBean() {
		this.setTxtUserName(FacesUtils.getSessionBean().getNomUser().toString());
	
	}




	public String doChange() {
		
		ConnectionWrapper conn = null;
		
		String lstResult = "";
		String lstMessage = "";
		int	   linUserId = Integer.valueOf(FacesUtils.getSessionBean().getIdUser());
		String lstUserLongName = "";
		int    linRolId = Integer.valueOf(FacesUtils.getSessionBean().getIdRol());
		String lstRolName = "";
		this.setTxtUserName(FacesUtils.getSessionBean().getNomUser().toString());
		try {
			
			conn = new ConnectionWrapper();
			
			CallableStatement callStmt = conn.prepareCall(
						"SS_CHANGE_PASSWORD_PKG.DO_CHANGE_PR", 6);
			
			callStmt.setInt(1, linUserId);
			callStmt.setInt(2, linRolId);
			callStmt.setString(3,this.getTxtOdlPassword());
			callStmt.setString(4,this.getTxtPassword1());
			callStmt.setString(5,this.getTxtPassword2());
			callStmt.registerOutParameter(6, java.sql.Types.VARCHAR);
			callStmt.execute();			
			
			lstResult = callStmt.getString(6);
			
			if (lstResult.equals("OK")){
				return NavigationRules.AUTHORIZED_ACCESS;
			} else {
				this.setLblMessage(lstResult);
				return null;
			}
			
		} catch(Exception ex) {
			
			ex.printStackTrace();
			
			return ex.toString();
			
		} finally {
			
			ConnectionWrapper.closeAll(conn);
		}
	}
	
	

	public String getLblMessage() {
		return lblMessage;
	}
	public void setLblMessage(String lblMessage) {
		this.lblMessage = lblMessage;
	}
	public String getTxtOdlPassword() {
		return txtOdlPassword;
	}
	public void setTxtOdlPassword(String txtOdlPassword) {
		this.txtOdlPassword = txtOdlPassword;
	}
	public String getTxtPassword1() {
		return txtPassword1;
	}
	public void setTxtPassword1(String txtPassword1) {
		this.txtPassword1 = txtPassword1;
	}
	public java.lang.String getTxtPassword2() {
		return txtPassword2;
	}
	public void setTxtPassword2(String txtPassword2) {
		this.txtPassword2 = txtPassword2;
	}
	public String getTxtUserName() {
		return txtUserName;
	}
	public void setTxtUserName(String txtUserName) {
		this.txtUserName = txtUserName;
	}


}

/*
* Project: AISA
*
* File: LoginBean.java
*
* Created on: Abril 5, 2019 at 11:00
*
* Copyright (c) - Kaz Consulting - 2019
*/

package mx.com.televisa.playcity.beans;

import java.io.Serializable;
import java.util.HashMap;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import mx.com.televisa.playcity.view.BillingExtractionSchedulerView;
import mx.com.televisa.playcity.util.Functions;
import mx.com.televisa.playcity.util.LDAP;
import mx.com.televisa.playcity.util.Mensajes;

/*
* Bean que almacena información de usuario y hace operaciones de login   
*
* @author Kaz Consulting / Jonathan Mariche Catana
*
* @version 1.0.0
*
* @date Abril 5, 2019 at 11:00 am
*/

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable{	
	
	private static final long serialVersionUID = 1L;
	private static Logger poLogger = Logger.getLogger(LoginBean.class);	
	private int           piIdUsuario;
	private String        psUsername;
	private String        psPassword;
	private String        psNumEmployed;
	private String        psFirstName;
	private String        psLastName;
	private String        psName;
	private String        psFullName;
	private String        psArea;
	private String        psNameRol;
	private int           piIdRol;
	
	//Valida credenciales de usuario y hace el login
	public String login(){		
		DirContext loContext = null;		
		String lsResult = "";
		try {
			loContext = LDAP.connect(this.psUsername, this.psPassword);
			if(loContext != null){				
				this.setPsUsername(this.psUsername);
				this.setPsName(this.psUsername);
				LoginBean loLoginBean = new LoginBean();
				loLoginBean.setPsUsername(this.psUsername);				
				Functions.getSession().setAttribute("loginBean", loLoginBean);
				lsResult = "exito";
				String[] laReturningAtts = {"CN"}; 
				HashMap<String, String> loUserMap = LDAP.search(
				"(&(objectCategory=person)(objectClass=user)(sAMAccountName=" +
						this.psUsername +"))", loContext, laReturningAtts);
				loLoginBean.setPsName(loUserMap.get("CN"));
				BillingExtractionSchedulerView loTest = new BillingExtractionSchedulerView();
				loTest.createJob();
				LDAP.close(loContext);				
			}
			else{
				lsResult = "fallo";
				Functions.showMessage("Usuario o contraseña incorrecto",
						"Error", Mensajes.ERROR);
			}
		} catch (NamingException toException) {				
		}					
		return "exito";//lsResult;							
	}
	
	//Cierra sesión
	public String logout() {
		LoginBean loLoginBean = (LoginBean) 
				Functions.getSession().getAttribute("loginBean");		
		poLogger.debug("Session de " + loLoginBean.getPsName()
				+ " Cerrada!!");
		Functions.getSession().removeAttribute("loginBean");		
		FacesContext.getCurrentInstance().getExternalContext()
				.invalidateSession();
		return "salir";
	}
	
	public LoginBean(String tsUsername, String tsPassword) {
		super();
		this.psUsername = tsUsername;
		this.psPassword = tsPassword;
	}

	public LoginBean() {
		super();
	}

	public int getPiIdUsuario() {
		return piIdUsuario;
	}

	public void setPiIdUsuario(int piIdUsuario) {
		this.piIdUsuario = piIdUsuario;
	}

	public String getPsUsername() {
		return psUsername;
	}

	public void setPsUsername(String psUsername) {
		this.psUsername = psUsername;
	}

	public String getPsPassword() {
		return psPassword;
	}

	public void setPsPassword(String psPassword) {
		this.psPassword = psPassword;
	}

	public String getPsNumEmployed() {
		return psNumEmployed;
	}

	public void setPsNumEmployed(String psNumEmployed) {
		this.psNumEmployed = psNumEmployed;
	}

	public String getPsFirstName() {
		return psFirstName;
	}

	public void setPsFirstName(String psFirstName) {
		this.psFirstName = psFirstName;
	}

	public String getPsLastName() {
		return psLastName;
	}

	public void setPsLastName(String psLastName) {
		this.psLastName = psLastName;
	}

	public String getPsName() {
		return psName;
	}

	public void setPsName(String psName) {
		this.psName = psName;
	}

	public String getPsFullName() {
		return psFullName;
	}

	public void setPsFullName(String psFullName) {
		this.psFullName = psFullName;
	}

	public String getPsArea() {
		return psArea;
	}

	public void setPsArea(String psArea) {
		this.psArea = psArea;
	}

	public String getPsNameRol() {
		return psNameRol;
	}

	public void setPsNameRol(String psNameRol) {
		this.psNameRol = psNameRol;
	}

	public int getPiIdRol() {
		return piIdRol;
	}

	public void setPiIdRol(int piIdRol) {
		this.piIdRol = piIdRol;
	}	

}

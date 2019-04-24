package mx.javaonline.admin.beans;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import mx.javaonline.daos.CambiaPassDAO;

import org.apache.log4j.Logger;

public class CambiaPasswordBean {
	
	FacesContext facesContext = FacesContext.getCurrentInstance();
	private static org.apache.log4j.Logger logger = Logger.getLogger(CambiaPasswordBean.class);

	private String pass;
	private String confirmPass;
	CambiaPassDAO cambiaPassDAO;
	
	public CambiaPasswordBean() {
		cambiaPassDAO = new CambiaPassDAO();
	}
	
	public void guardarPass(){
	
		if(pass.equals(confirmPass)){
			boolean bandera = cambiaPassDAO.cambiaPass(confirmPass);
			if(bandera){
		        FacesContext.getCurrentInstance().addMessage(null,
						   new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Tu contraseña cambio exitosamente","Exito"));
				
			}else{
				FacesContext.getCurrentInstance().addMessage(null,
						   new FacesMessage(FacesMessage.SEVERITY_FATAL,
							"Error al actualizar tu contraseña, consulte al administrador","Ups"));
			}
			
		}else{
			FacesContext.getCurrentInstance().addMessage(null,
					   new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Las contraseñas deben coincidir","Ups"));
		}
	}
	
	

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getConfirmPass() {
		return confirmPass;
	}

	public void setConfirmPass(String confirmPass) {
		this.confirmPass = confirmPass;
	}

	
}

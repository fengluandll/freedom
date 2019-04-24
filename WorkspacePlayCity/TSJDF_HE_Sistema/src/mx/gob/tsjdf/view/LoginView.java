package mx.gob.tsjdf.view;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import mx.gob.tsjdf.bean.LoginBean;
import mx.gob.tsjdf.model.LoginDAO;
import mx.gob.tsjdf.util.Funciones;

@ManagedBean
@RequestScoped
public class LoginView implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	private static org.apache.log4j.Logger logger = Logger.getLogger(LoginView.class);
	
	String username;
	String password;
	LoginDAO loginDAO;
	
	

	public String passUser(){
		loginDAO = new LoginDAO();
		String paso = "";
		logger.info("Entro en el Bean******************+");
		
		boolean ok = loginDAO.obtenUsuario(this.username,this.password);
		if(ok){
			LoginBean loginBean = loginDAO.datosEmpleado(this.username);
			Funciones.getSession().setAttribute("loginBean", loginBean);
			paso = "home";
			
		}else{
			Funciones.mostrarMensaje("Usuario o contraseña incorrecto", "Error", "ERROR");
		}
		return paso;
	}
	
	public String salir() {

		LoginBean personalBean = (LoginBean) Funciones.getSession().getAttribute("loginBean");
		
		logger.debug("Session de " + personalBean.getNombreCompleto()
				+ " Cerrada!!");
		FacesContext.getCurrentInstance().getExternalContext()
				.invalidateSession();

		return "salir";
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



}

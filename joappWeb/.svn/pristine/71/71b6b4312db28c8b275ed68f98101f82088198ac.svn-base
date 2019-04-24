/**
 * @Jose de Jesus Argumedo Quiroz
 */
package mx.javaonline.admin.beans;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import mx.javaonline.beans.StudentPersonalBean;
import mx.javaonline.daos.BuzonSugerenciaDAO;

public class BuzonSugerenciasBean {

	private String txtAreaValue;
	FacesContext facesContext;
	HttpSession session;
	BuzonSugerenciaDAO buzonSugerenciaDAO;
	
	public BuzonSugerenciasBean() {
		facesContext = FacesContext.getCurrentInstance();
		session = (HttpSession) facesContext.getExternalContext().getSession(true);
	}
	
	public void enviar(){
		buzonSugerenciaDAO = new BuzonSugerenciaDAO();
		StudentPersonalBean studentPersonalBean = (StudentPersonalBean)session.getAttribute("studentPersonalBean");
		boolean bandera = buzonSugerenciaDAO.enviaComentarios(studentPersonalBean.getStudentPersonalId(), studentPersonalBean.getSegment1(), txtAreaValue);
		if(bandera){
			txtAreaValue = "";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Gracias",
					"Tomaremos en cuenta tus comentarios" ); 
			FacesContext.getCurrentInstance().addMessage(null, message);
		}else{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ups",
					"Algo ocurrio que no se enviaron tus comentarios, consulta con tu asesor" ); 
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public String getTxtAreaValue() {
		return txtAreaValue;
	}

	public void setTxtAreaValue(String txtAreaValue) {
		this.txtAreaValue = txtAreaValue;
	}


	
	

}

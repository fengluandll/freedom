package mx.javaonline.evaluacion;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import mx.javaonline.beans.StudentPersonalBean;
import mx.javaonline.daos.StatusBarTopicDAO;

import org.primefaces.context.RequestContext;

public class EvaluacionBean implements Serializable{
	

	private static final long serialVersionUID = 1L;
	private String preg1;
	private String preg2;
	private String preg3;
	private String preg4;
	private String preg5;
	FacesContext facesContext = FacesContext.getCurrentInstance();
	HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);

	public EvaluacionBean() {
	
	}

	public void califica(){
		StudentPersonalBean studentPersonalBean = (StudentPersonalBean)session.getAttribute("studentPersonalBean");
		if(preg1.equals("") || preg1 == null){
			preg1 = "/images/eliminar.png";
		}
		if(preg3.equals("") || preg2 == null){
			preg2 = "/images/eliminar.png";
		}
		if(preg3.equals("") || preg3 == null){
			preg3 = "/images/eliminar.png";
		}
		if(preg4.equals("") || preg4 == null){
			preg4 = "/images/eliminar.png";
		}
		if(preg5.equals("") || preg5 == null){
			preg5 = "/images/eliminar.png";
		}
		
		if(preg1.equals("/images/Accept-32.png") && preg2.equals("/images/Accept-32.png") 
			                        && preg3.equals("/images/Accept-32.png") 
			                        && preg4.equals("/images/Accept-32.png")
			                        && preg5.equals("/images/Accept-32.png")){
			StatusBarTopicDAO barTopicDAO = new StatusBarTopicDAO();
			String topicId =(String) session.getAttribute("topicId2");
	    	barTopicDAO.setStatusBar("evaluacion", 100,topicId,studentPersonalBean,0,"evaluacion_tab");
			RequestContext.getCurrentInstance().execute("dlgExito.show()");
		}else{
			RequestContext.getCurrentInstance().update("frmEval:display");
			RequestContext.getCurrentInstance().execute("dlg.show()");
		}
	}
	public String getPreg1() {
		
		
		return preg1;
	}

	public void setPreg1(String preg1) {
		this.preg1 = preg1;
	}

	public String getPreg2() {
		
		return preg2;
	}

	public void setPreg2(String preg2) {
		this.preg2 = preg2;
	}

	public String getPreg3() {
		
		return preg3;
	}

	public void setPreg3(String preg3) {
		this.preg3 = preg3;
	}

	public String getPreg4() {
	
		return preg4;
	}

	public void setPreg4(String preg4) {
		this.preg4 = preg4;
	}

	public String getPreg5() {
	
		return preg5;
	}

	public void setPreg5(String preg5) {
		this.preg5 = preg5;
	}


	
	

}

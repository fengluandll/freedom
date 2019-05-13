/**
 * @Jose de Jesus Argumedo Quiroz
 */
package mx.javaonline.beans;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import mx.javaonline.util.FuncionesGrales;

@ManagedBean
@RequestScoped
public class RedirectPageBean {
	
	FacesContext facesContext = FacesContext.getCurrentInstance();
	HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
	Map<String,String> params = facesContext.getExternalContext().getRequestParameterMap();
	
	private int topicId;
	private String courseName;
	private String topicName;
	
	
	public RedirectPageBean(){
		
	}
	
	public void sessionCourseId(String cusroId){
		session.removeAttribute("courseId");
		session.setAttribute("courseId", cusroId);
		System.out.println("Entro al redirect!!!");
		FuncionesGrales.manageDialog("dlgConCurso", 1);
	}
	
	public String loadPage() {
		
		session.setAttribute("topicId", params.get("topicId"));
		session.setAttribute("topicName", params.get("topicName"));
		session.setAttribute("courseName", params.get("courseName"));
		session.setAttribute("unitName", params.get("unitName"));
		session.setAttribute("courseId", params.get("courseId"));
		session.setAttribute("unitId", params.get("unitId"));
		session.setAttribute("idOrder", params.get("idOrder"));
		
		return "dashTopics";
	}

	public int getTopicId() {
		return topicId;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	
	

}

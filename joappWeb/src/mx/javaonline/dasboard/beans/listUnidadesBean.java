/**
 * @Jose de Jesus Argumedo Quiroz
 */
package mx.javaonline.dasboard.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import mx.javaonline.beans.CoursesBean;
import mx.javaonline.beans.StudentPersonalBean;
import mx.javaonline.beans.TopicsBean;
import mx.javaonline.carousel.beans.ObjetivoCursoBean;
import mx.javaonline.daos.CursosDAO;
import mx.javaonline.daos.TopicsDAO;
import mx.javaonline.daos.UnidadesDAO;

@ManagedBean
@ViewScoped
public class listUnidadesBean {
	
	private static org.apache.log4j.Logger logger = Logger.getLogger(listUnidadesBean.class);
	FacesContext facesContext = FacesContext.getCurrentInstance();
	HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
	StudentPersonalBean studentPersonalBean = (StudentPersonalBean)session.getAttribute("studentPersonalBean");
	List<CursoUnidadBean> 	listCurUni;
	List<TopicsBean> 		listTopics;
	UnidadesDAO 			unidadesDAO;
	TopicsDAO 				topicsDAO;
	private int 			courseId;
	private String 			pageLink;
	private String 			nomCurso;
	private String			nomUnidad;
	private int				unitId;
	CursosDAO				cursosDAO;
	List<CoursesBean>		listCursos;
	
	
	
	public listUnidadesBean() {
		unidadesDAO = new UnidadesDAO();
		topicsDAO 	= new TopicsDAO();
		cursosDAO	= new CursosDAO();
		listCursos = dameCursos();
	}
	
	
	
	public void dameUnidades(int courseId,String nomCurso) {
		this.listCurUni = unidadesDAO.unidadesFromCurso(courseId);
		this.nomCurso = nomCurso;
		this.courseId = courseId;
	}
	
	public List<CursoUnidadBean> dameUnidades(int courseId) {
		this.listCurUni = unidadesDAO.unidadesFromCurso(courseId);
		return this.listCurUni;
	}
	
	public void dameTemas(int unitId,String nomUnidad) {
		this.listTopics = topicsDAO.dameTemas(unitId);
		this.nomUnidad  = nomUnidad;
		this.unitId		= unitId;
	}
	
	public List<TopicsBean> dameTemas(int unitId) {
		this.listTopics = topicsDAO.dameTemas(unitId);
		return this.listTopics;
	}
	
	public List<CoursesBean> dameCursos() {
		
		List<CoursesBean> listCursos = cursosDAO.getCursosContratados(studentPersonalBean.getStudentPersonalId());
		return listCursos;
		//this.listCursos = listCursos;
	}
	
	public List<ObjetivoCursoBean> dameObjetivosCurso(int idCurso){
		cursosDAO	= new CursosDAO();
		List<ObjetivoCursoBean> listObjetivos = cursosDAO.dameObjetivos(idCurso);
		return listObjetivos;
	}
	

	public List<CursoUnidadBean> getListCurUni() {
		return listCurUni;
	}

	public void setListCurUni(List<CursoUnidadBean> listCurUni) {
		this.listCurUni = listCurUni;
	}



	public int getCourseId() {
		return courseId;
	}



	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}



	public String getPageLink() {
		if(pageLink == null){
			//this.pageLink = "/joappWeb/home/antesIniciar.jsp";
			this.pageLink = "/joappWeb/dashboards/listaUnidades.xhtml";
			
		}
		return pageLink;
	}



	public void setPageLink(String pageLink) {
		this.pageLink = pageLink;
	}



	public List<TopicsBean> getListTopics() {
		return listTopics;
	}



	public void setListTopics(List<TopicsBean> listTopics) {
		this.listTopics = listTopics;
	}



	public String getNomCurso() {
		return nomCurso;
	}



	public void setNomCurso(String nomCurso) {
		this.nomCurso = nomCurso;
	}



	public String getNomUnidad() {
		return nomUnidad;
	}



	public void setNomUnidad(String nomUnidad) {
		this.nomUnidad = nomUnidad;
	}



	public int getUnitId() {
		return unitId;
	}



	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}



	public List<CoursesBean> getListCursos() {
		return listCursos;
	}



	public void setListCursos(List<CoursesBean> listCursos) {
		this.listCursos = listCursos;
	}
	
	
	
}


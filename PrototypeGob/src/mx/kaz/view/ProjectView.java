/**
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 */
package mx.kaz.view;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import mx.kaz.beans.PersonalBean;
import mx.kaz.beans.ProyectBean;
import mx.kaz.beans.RegLicitaciones;
import mx.kaz.beans.RegProyectoBean;
import mx.kaz.daos.ProyectDAO;

import org.apache.log4j.Logger;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

/**
 * @author jesus argumedo
 *
 */
@ManagedBean
@ViewScoped
public class ProjectView implements Serializable{

	private static final long serialVersionUID = 1L;
	private org.apache.log4j.Logger logger = Logger.getLogger(ProjectView.class);
	ProyectDAO proyectDAO;
	FacesContext facesContext;
	HttpSession session;
	List<RegProyectoBean> listProj;
	private List<RegProyectoBean> filteredProy;
	String tblObras;
	private RegProyectoBean selectedProject;
	private List<RegLicitaciones> listLici;
	
	
	public ProjectView(){
		
		proyectDAO = new ProyectDAO();
		facesContext = FacesContext.getCurrentInstance();
		session = (HttpSession) facesContext.getExternalContext().getSession(true);
		obtenProyectos();
		
	}
	
	public void obtenProyectos(){
	
		String nomPto = (String)session.getAttribute("nomPto");
		//Es nulo si el rol es de api
		if(nomPto != null){
			int idPort = proyectDAO.obtenIdPto(nomPto);
			listProj = proyectDAO.obtenContenido(idPort);
		}else{
			PersonalBean personalBean = (PersonalBean)session.getAttribute("personalBean");
			listProj = proyectDAO.obtenContenido(personalBean.getSegment6());
		}
	}
	

	

	
	public List<RegProyectoBean> getFilteredProy() {
		return filteredProy;
	}

	public void setFilteredProy(List<RegProyectoBean> filteredProy) {
		this.filteredProy = filteredProy;
	}

	public void showTblObras(){
		this.tblObras = "true";
	}

	public List<RegProyectoBean> getListProj() {
		return listProj;
	}

	public void setListProj(List<RegProyectoBean> listProj) {
		this.listProj = listProj;
	}

	public String getTblObras() {
		return tblObras;
	}

	public void setTblObras(String tblObras) {
		this.tblObras = tblObras;
	}

	public RegProyectoBean getSelectedProject() {
		return selectedProject;
	}

	public void setSelectedProject(RegProyectoBean selectedProject) {
		this.selectedProject = selectedProject;
	}

	public List<RegLicitaciones> getListLici() {
		return listLici;
	}

	public void setListLici(List<RegLicitaciones> listLici) {
		this.listLici = listLici;
	}

	

}

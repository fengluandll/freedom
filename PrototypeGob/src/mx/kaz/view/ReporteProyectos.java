/**
 * Copyright Argumedo
 */
package mx.kaz.view;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;

import mx.kaz.beans.ProyectBean;
import mx.kaz.beans.reportes.ReporteLicitaBean;
import mx.kaz.daos.ReportesDAO;


@ManagedBean(name="dtReporteProyView")
@RequestScoped
public class ReporteProyectos implements Serializable{

	private static final long serialVersionUID = 1L;

	ReportesDAO reportesDAO;
	private List<ProyectBean> listProyectos;//lista de proyectos
	private List<ReporteLicitaBean> filteredProy;
	
	public ReporteProyectos(){
		reportesDAO = new ReportesDAO();
		obtenProyectos();
	}
	
	
	private void obtenProyectos(){
		listProyectos = reportesDAO.proyectosAgregados();
		
	}
	
    public void preProcessPDF(Object document) {
        Document pdf = (Document) document;
        pdf.setPageSize(PageSize.LETTER.rotate());
        pdf.open();
        
    }


	public List<ProyectBean> getListProyectos() {
		return listProyectos;
	}


	public void setListProyectos(List<ProyectBean> listProyectos) {
		this.listProyectos = listProyectos;
	}


	public List<ReporteLicitaBean> getFilteredProy() {
		return filteredProy;
	}


	public void setFilteredProy(List<ReporteLicitaBean> filteredProy) {
		this.filteredProy = filteredProy;
	}
	
	
	
}

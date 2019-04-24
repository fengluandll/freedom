
package mx.kaz.view;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.view.ViewScoped;

import mx.kaz.beans.RegProyectoBean;
import mx.kaz.beans.reportes.AgendaFallosBean;
import mx.kaz.daos.ReportesDAO;

import org.apache.log4j.Logger;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;

/**
 * @author Jesus Argumedo
 *
 */
@ManagedBean
@RequestScoped
public class AgendaFallosView implements Serializable{


	private static final long serialVersionUID = 1L;
	private org.apache.log4j.Logger logger = Logger.getLogger(AgendaFallosView.class);
	List<AgendaFallosBean> listFallos;
	ReportesDAO reportesDAO;
	private List<AgendaFallosBean> filteredProy;
	
	
	public AgendaFallosView(){
		reportesDAO = new ReportesDAO();
		getAgendaFallos();
	}
	
    public void preProcessPDF(Object document) {
        Document pdf = (Document) document;
        pdf.open();
        pdf.setPageSize(PageSize.LETTER.rotate());
        
      
    }
    
	private void getAgendaFallos(){
		listFallos = reportesDAO.getAgendaFallos();
	}

	public List<AgendaFallosBean> getListFallos() {
		return listFallos;
	}

	public void setListFallos(List<AgendaFallosBean> listFallos) {
		this.listFallos = listFallos;
	}

	public List<AgendaFallosBean> getFilteredProy() {
		return filteredProy;
	}

	public void setFilteredProy(List<AgendaFallosBean> filteredProy) {
		this.filteredProy = filteredProy;
	}
	
	
	
}

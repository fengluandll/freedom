package mx.kaz.view;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import mx.kaz.beans.reportes.AgendaAperturasBean;
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
public class AgendaAperturaView implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private org.apache.log4j.Logger logger = Logger.getLogger(AgendaAperturaView.class);
	List<AgendaAperturasBean> listApertura;
	ReportesDAO reportesDAO;
	private List<AgendaAperturasBean> filteredProy;
	
	public AgendaAperturaView(){
		reportesDAO = new ReportesDAO();
		getAperturas();
	}
	
	
    public void preProcessPDF(Object document) {
        Document pdf = (Document) document;
        pdf.open();
        pdf.setPageSize(PageSize.LETTER.rotate());

    }
    
	private void getAperturas(){
		listApertura = reportesDAO.getAgendaApertura();
	}

	public List<AgendaAperturasBean> getListApertura() {
		return listApertura;
	}

	public void setListApertura(List<AgendaAperturasBean> listApertura) {
		this.listApertura = listApertura;
	}

	public List<AgendaAperturasBean> getFilteredProy() {
		return filteredProy;
	}

	public void setFilteredProy(List<AgendaAperturasBean> filteredProy) {
		this.filteredProy = filteredProy;
	}
	
	
	
}

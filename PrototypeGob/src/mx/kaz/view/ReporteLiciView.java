
package mx.kaz.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;

import mx.kaz.beans.reportes.ReporteLicitaBean;
import mx.kaz.daos.ProyectDAO;
import mx.kaz.daos.ReportesDAO;

/**
 * @authorJesus Argumedo
 *
 */
@ManagedBean(name="dtReporteLiciView")
@RequestScoped
public class ReporteLiciView implements Serializable{
	
	private static final long serialVersionUID = 1L;
	FacesContext facesContext;
	List<ReporteLicitaBean> listRep;
	List<ReporteLicitaBean> listLiciProceso;
	HttpSession session;
	ReportesDAO reportesDAO;
	ProyectDAO proyectDAO;
	private List<ReporteLicitaBean> filteredProy;

	public ReporteLiciView(){
		listRep = new ArrayList<>();
		listLiciProceso = new ArrayList<>();
		facesContext = FacesContext.getCurrentInstance();
		session = (HttpSession) facesContext.getExternalContext().getSession(true);
		reportesDAO = new ReportesDAO();
		proyectDAO = new ProyectDAO();
		generaReporte();
		generaLiciProcesadas();
	}
	
	private void generaReporte(){
		String nomPto = (String)session.getAttribute("nomPto");

			if(nomPto.equals("todos")){
				listRep = reportesDAO.liciRealizadas(0);
			}else{
				int idPort = proyectDAO.obtenIdPto(nomPto);
				listRep = reportesDAO.liciRealizadas(idPort);
			}
	}
	
    public void preProcessPDF(Object document) {
        Document pdf = (Document) document;
        pdf.setPageSize(PageSize.LETTER.rotate());
        pdf.open();
        
    }
    
	private void generaLiciProcesadas(){
		String nomPto = (String)session.getAttribute("nomPto");
		
			if(nomPto.equals("todos")){
				listLiciProceso = reportesDAO.liciProcesadas(0);
			}else{
				int idPort = proyectDAO.obtenIdPto(nomPto);
				listLiciProceso = reportesDAO.liciProcesadas(idPort);
			}
		
	}

	public List<ReporteLicitaBean> getListRep() {
		return listRep;
	}

	public void setListRep(List<ReporteLicitaBean> listRep) {
		this.listRep = listRep;
	}

	public List<ReporteLicitaBean> getListLiciProceso() {
		return listLiciProceso;
	}

	public void setListLiciProceso(List<ReporteLicitaBean> listLiciProceso) {
		this.listLiciProceso = listLiciProceso;
	}

	public List<ReporteLicitaBean> getFilteredProy() {
		return filteredProy;
	}

	public void setFilteredProy(List<ReporteLicitaBean> filteredProy) {
		this.filteredProy = filteredProy;
	}
	
	
	
	
}

package mx.kaz.view;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import mx.kaz.beans.reportes.ReporteLicitaBean;
import mx.kaz.daos.ProyectDAO;
import mx.kaz.daos.ReportesDAO;
import mx.kaz.obras.beans.ObrasBean;

/**
 * @author Argumedo
 *
 */
@ManagedBean(name="dtReporteLaasspView")
@RequestScoped
public class LaasspView implements Serializable{

	private static final long serialVersionUID = 1L;
	ReportesDAO reportesDAO; 
	FacesContext facesContext;
	HttpSession session;
	List<ObrasBean> listlaassp;
	private List<ObrasBean> filteredProy;
	ProyectDAO proyectDAO;
	
	public LaasspView() {
		reportesDAO = new ReportesDAO();
		proyectDAO = new ProyectDAO();
		facesContext = FacesContext.getCurrentInstance();
		session = (HttpSession) facesContext.getExternalContext().getSession(true);
		dameReporte();
	}
	
	public void dameReporte(){
		
		String nomPto = (String)session.getAttribute("nomPto");

		if(nomPto.equals("todos")){
			listlaassp = reportesDAO.laasspReport(0);
		}else{
			int idPort = proyectDAO.obtenIdPto(nomPto);
			listlaassp = reportesDAO.laasspReport(idPort);
		}
	}

	public List<ObrasBean> getListlaassp() {
		return listlaassp;
	}

	public void setListlaassp(List<ObrasBean> listlaassp) {
		this.listlaassp = listlaassp;
	}

	public List<ObrasBean> getFilteredProy() {
		return filteredProy;
	}

	public void setFilteredProy(List<ObrasBean> filteredProy) {
		this.filteredProy = filteredProy;
	}

	
}

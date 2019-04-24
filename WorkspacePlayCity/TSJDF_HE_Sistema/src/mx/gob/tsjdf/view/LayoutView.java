package mx.gob.tsjdf.view;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;

import mx.gob.tsjdf.bean.LayoutBean;
import mx.gob.tsjdf.bean.LoginBean;
import mx.gob.tsjdf.model.LayoutDAO;
import mx.gob.tsjdf.model.LoginDAO;
import mx.gob.tsjdf.util.Funciones;

@ManagedBean
@ViewScoped
public class LayoutView {
	private static org.apache.log4j.Logger logger = Logger.getLogger(LoginDAO.class);
	private String quincena;
	private String tipoReporte;
	private Date fechaAplicacionPago;
	private Date fechaComputacion;
	private LayoutDAO layoutDao;
	private List<LayoutBean> listLayout;
	private List<LayoutBean> filteredLayout;
	private boolean showDialog;
	private String reportName;
	public String getQuincena() {
		return quincena;
	}
	public void setQuincena(String quincena) {
		this.quincena = quincena;
	}
	public String getTipoReporte() {
		return tipoReporte;
	}
	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}
	public Date getFechaAplicacionPago() {
		return fechaAplicacionPago;
	}
	public void setFechaAplicacionPago(Date fechaAplicacionPago) {
		this.fechaAplicacionPago = fechaAplicacionPago;
	}
	public Date getFechaComputacion() {
		return fechaComputacion;
	}
	public void setFechaComputacion(Date fechaComputacion) {
		this.fechaComputacion = fechaComputacion;
	}
	public List<LayoutBean> getListLayout() {
		return listLayout;
	}
	public void setListLayout(List<LayoutBean> listLayout) {
		this.listLayout = listLayout;
	}
	public List<LayoutBean> getFilteredLayout() {
		return filteredLayout;
	}
	public void setFilteredLayout(List<LayoutBean> filteredLayout) {
		this.filteredLayout = filteredLayout;
	}
	public boolean isShowDialog() {
		return showDialog;
	}
	public void setShowDialog(boolean showDialog) {
		this.showDialog = showDialog;
	}
	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	public void generarReporte(){
		logger.info("--> Variables");
		//logger.info("                Quincena: "+this.quincena);
		//logger.info("            Tipo Reporte: "+this.tipoReporte);
		//logger.info("     Fecha de Aplicacion: "+Funciones.dateToString(this.fechaAplicacionPago));
		//logger.info("    Fecha de Computacion: "+Funciones.dateToString(this.fechaComputacion));
		layoutDao = new LayoutDAO();
		setListLayout(layoutDao.generarReporte(this.quincena, this.tipoReporte, 
				Funciones.dateToString(this.fechaAplicacionPago), 
				Funciones.dateToString(this.fechaComputacion)));
		setShowDialog(true);
		logger.info(" ");
		logger.info("           Mostrar datos: "+this.showDialog);
		setReportName("Layout_"+this.quincena+"_rpt");
		Funciones.updateComponent("frmExporter:pgExporter");
		Funciones.updateComponent("frmTable:layoutTable");
		actualizaDatos();
	}
	public void actualizaDatos(){
		LoginBean loginBean = (LoginBean) Funciones.getSession().getAttribute("loginBean");
		layoutDao = new LayoutDAO();
		for (LayoutBean u : listLayout){
			layoutDao.cambiaStatusSolicitudGenerada(u.getIdSolicitud(), 6,loginBean.getNumEmpleado());
		}
		layoutDao.cambiaStatusQuincenaActiva(this.quincena);
	}
}

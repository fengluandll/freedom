package mx.gob.tsjdf.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import mx.gob.tsjdf.bean.LoginBean;
import mx.gob.tsjdf.bean.QuincenaActualBean;
import mx.gob.tsjdf.model.QuincenasActualDAO;
import mx.gob.tsjdf.util.Funciones;

@ManagedBean
@ViewScoped
public class QuincenaActualView {
	
	
	QuincenasActualDAO quincenaDao;
	List<QuincenaActualBean> listQuincena;
	List<QuincenaActualBean> filteredList;
	List<QuincenaActualBean> listData;
	
	String fechaPaga, fechaPagaNombre, activo, activoNombre;
	Integer idQuincenaActual;
	
	public List<QuincenaActualBean> getFilteredList() {
		return filteredList;
	}


	public void setFilteredList(List<QuincenaActualBean> filteredList) {
		this.filteredList = filteredList;
	}


	public String getFechaPaga() {
		return fechaPaga;
	}


	public void setFechaPaga(String fechaPaga) {
		this.fechaPaga = fechaPaga;
	}


	public String getFechaPagaNombre() {
		return fechaPagaNombre;
	}


	public void setFechaPagaNombre(String fechaPagaNombre) {
		this.fechaPagaNombre = fechaPagaNombre;
	}


	public String getActivo() {
		return activo;
	}


	public void setActivo(String activo) {
		this.activo = activo;
	}


	public String getActivoNombre() {
		return activoNombre;
	}


	public void setActivoNombre(String activoNombre) {
		this.activoNombre = activoNombre;
	}


	public Integer getIdQuincenaActual() {
		return idQuincenaActual;
	}


	public void setIdQuincenaActual(Integer idQuincenaActual) {
		this.idQuincenaActual = idQuincenaActual;
	}


	public List<QuincenaActualBean> getListQuincena() {
		return listQuincena;
	}


	public void setListQuincena(List<QuincenaActualBean> listQuincena) {
		this.listQuincena = listQuincena;
	}


	LoginBean login = (LoginBean) Funciones.getSession().getAttribute("loginBean");
	
	
	@PostConstruct
    public void init() {
		
		cargaDatos();
    }
	
	private void cargaDatos(){
		quincenaDao    = new QuincenasActualDAO();
		listQuincena   =  quincenaDao.getDatosQuincenaActual();
	}
	
	public void guardarDatos(){
		LoginBean login = (LoginBean) Funciones.getSession().getAttribute("loginBean");
		String flagActivo = "N";
		if(this.activoNombre.equals("Activo"))
			flagActivo = "Y";
		else 
			flagActivo = "N";
		Integer existe = quincenaDao.getExisteQuincena(this.fechaPaga);
		if(existe>0){
			quincenaDao.cambiaStatusQuincenaExiste(this.fechaPaga,login.getIdUsuario());
		}else{
			quincenaDao.guardaRegistro(this.fechaPaga, flagActivo, login.getIdUsuario());
		}
		cargaDatos();
		Funciones.updateComponent("frmTable:dataTable");
	}
	
	public void cambiaStatus(QuincenaActualBean quincena){
		LoginBean login = (LoginBean) Funciones.getSession().getAttribute("loginBean");
		quincenaDao.cambiaStatusQuincenaExiste(quincena.getFechaPaga(),login.getIdUsuario());
		cargaDatos();
		Funciones.updateComponent("frmTable:dataTable");
	}
}

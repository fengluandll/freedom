package mx.gob.tsjdf.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import mx.gob.tsjdf.bean.HorasExtrasBean;
import mx.gob.tsjdf.bean.LoginBean;
import mx.gob.tsjdf.bean.SolicitudesBean;
import mx.gob.tsjdf.model.LovDAO;
import mx.gob.tsjdf.util.Funciones;

@ManagedBean
@ViewScoped
public class ModificaSolicitudView implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private static org.apache.log4j.Logger logger = Logger.getLogger(QuincenasLovView.class);
	private String numEmpleado;
    private String nombreEmpleado;
    private String organoJudicial;
    private String rfc;
    private String fechaInicial;
    private String fechaFinal;
    private String fechaPaga;
    private String descripcionNomina;
    private String jornadaOrdinaria;
    //Horas extras
    private java.util.Date horaInicio;
    private java.util.Date horaSalida;
    private java.util.Date fechaHe;
    private String hrsLaboradas;
    private String justificacion;
    private int idSolicitudHE;
    private int idSolicitud;
    private String mg;
    private LovDAO lovDAO;
    private boolean check;
    private String aprobarSolicitud;
    
    private String motivoRechazo;
    
    public String getMotivoRechazo() {
		return motivoRechazo;
	}


	public void setMotivoRechazo(String motivoRechazo) {
		this.motivoRechazo = motivoRechazo;
	}


	List<HorasExtrasBean> listHoras;
    
    
    @PostConstruct
    public void init(){
    	lovDAO = new LovDAO();
    	HttpSession session = Funciones.getSession();
    	SolicitudesBean solicitudesBean = (SolicitudesBean)session.getAttribute("solicitudesBean");
    	listHoras = (List<HorasExtrasBean>)session.getAttribute("listHoras");
    	
    	this.numEmpleado          = solicitudesBean.getNumEmpleado();
		this.nombreEmpleado       = solicitudesBean.getNombreCompleto();
		this.organoJudicial    	  = solicitudesBean.getIdArea();
		this.rfc                  = solicitudesBean.getRfc();
		this.fechaInicial         = solicitudesBean.getFechaInicial()==null?null:solicitudesBean.getFechaInicial().toString();
		this.fechaFinal           = solicitudesBean.getFechaFinal()==null?null:solicitudesBean.getFechaFinal().toString();
		this.fechaPaga		      = solicitudesBean.getFechaPaga()==null?null:solicitudesBean.getFechaPaga().toString();
		this.descripcionNomina    = solicitudesBean.getDescripcionNomina();
		this.jornadaOrdinaria     = solicitudesBean.getJornadaOrdinaria();
    }
    
    
    public void guardaTodo(){
    	int idSolicitud = lovDAO.obtendIdSolicitud(this.numEmpleado,this.fechaPaga);
    	boolean ok = false;
    	if(check){
    		ok = lovDAO.guardaTodaSolicitud(this.jornadaOrdinaria, idSolicitud, 2);
    	}else{
    		ok = lovDAO.guardaTodaSolicitud(this.jornadaOrdinaria, idSolicitud, 1);
    	}
    	if(ok){
    		Funciones.mostrarMensaje("Solicitud guardada correctamente", "", "INFO");
    	}else{
    		Funciones.mostrarMensaje("Ocurrio un error en  la ase de datos, consulte al administrador de sistema", "", "ERROR");
    	}
    }
    
    public void aprobarSolicitudes(){
		LoginBean loginBean = (LoginBean) Funciones.getSession().getAttribute("loginBean");
    	int idSolicitud = lovDAO.obtendIdSolicitud(this.numEmpleado,this.fechaPaga);
    	boolean ok = false;
    	if(this.aprobarSolicitud.equals("Seleccione uno...")){
    		Funciones.mostrarMensaje("No se realizo ninguna accion", "", "INFO");
    		return;
    	}
    	if(this.aprobarSolicitud.equals("Autorizada")){
    		ok = lovDAO.aprobarSolicitud(idSolicitud, 3, loginBean.getNumEmpleado(),"Se autorizo correctamente");
        	if(ok){
        		Funciones.mostrarMensaje("Solicitud fue autorizada correctamente", "", "INFO");
        	}else{
        		Funciones.mostrarMensaje("Ocurrio un error en  la ase de datos, consulte al administrador de sistema", "", "ERROR");
        	}
    		return;
    	}
    	if(this.aprobarSolicitud.equals("Rechazada")){
    		ok = lovDAO.aprobarSolicitud(idSolicitud, 4, loginBean.getNumEmpleado(),this.motivoRechazo);
        	if(ok){
        		Funciones.mostrarMensaje("Solicitud fue recazada correctamente", "", "INFO");
        	}else{
        		Funciones.mostrarMensaje("Ocurrio un error en  la ase de datos, consulte al administrador de sistema", "", "ERROR");
        	}
        	return;
    	}
    }
    
    public void nuevaHora(){
    	this.mg = "N";
    	this.horaInicio    = null;
    	this.horaSalida    = null;
    	this.fechaHe       = null;
    	this.hrsLaboradas  = "";
    	this.justificacion = "";
    	Funciones.updateComponent("idHoras:gridHoras");
    	Funciones.manageDialog("dlg2", 2);
    }
    
    public void modificaHoras(HorasExtrasBean horasExtrasBean){
     	this.mg = "M";
    	this.horaInicio    = Funciones.deStringToDateHrs(horasExtrasBean.getHoraEntrada());
    	this.horaSalida    = Funciones.deStringToDateHrs(horasExtrasBean.getHoraSalida());
    	this.fechaHe       = Funciones.deStringToDate(horasExtrasBean.getFecha());
    	this.hrsLaboradas  = horasExtrasBean.getNumHorasLaboradas();
    	this.justificacion = horasExtrasBean.getJustificacion();
    	this.idSolicitudHE = horasExtrasBean.getIdSolicitudHe();
    	this.idSolicitud   = horasExtrasBean.getIdSolicitud();
    	Funciones.manageDialog("dlg2", 2);
    	
    }
    
    public void eliminaHoras(HorasExtrasBean horasExtrasBean){
    	boolean ok = lovDAO.EliminaHoraExtra(horasExtrasBean.getIdSolicitudHe());
    	if(ok){
    		listHoras = lovDAO.dameHorasExtras(idSolicitud);
    		Funciones.updateComponent("frmSolicitud:idHorasExt");
    		Funciones.mostrarMensaje("Registro eliminado correctamente", "", "INFO");
    	}else{
    		Funciones.mostrarMensaje("Ocurrio un error en  la ase de datos, consulte al administrador de sistema", "", "ERROR");
    	}
    }
    
    public void guardaHorasExtras(){
		LoginBean loginBean = (LoginBean) Funciones.getSession().getAttribute("loginBean");
		Integer tipoSession = loginBean.getIdRol();
    	String diaFeriado = "";
    	@SuppressWarnings("deprecation")
		Integer hrIniFin = this.horaSalida.getHours()-this.horaInicio.getHours();
    	int idSolicitud = lovDAO.obtendIdSolicitud(this.numEmpleado,this.fechaPaga);

    	if(tipoSession == 3)
    		diaFeriado = "OK";
    	else
    		diaFeriado = Funciones.diaFeriado(Funciones.dateToString(this.fechaHe), this.numEmpleado,this.fechaPaga, idSolicitud, this.hrsLaboradas,hrIniFin.toString());
    	if(diaFeriado.equals("OK")){
	    	if(mg.equals("N")){
	    		boolean existe = lovDAO.existeMismafecha(Funciones.dateToString(this.fechaHe),idSolicitud);
	    		if(existe){
	        		Funciones.mostrarMensaje("Error: ya existe un registro con la misma fecha", "", "WARN");
	        	}else{
		        	if(idSolicitud > 0){
		        		boolean paso = lovDAO.guardaHorasextras(idSolicitud, Funciones.dateToString(this.fechaHe), Funciones.dateToHoras(this.horaInicio), Funciones.dateToHoras(this.horaSalida), this.hrsLaboradas, this.justificacion);
		        		if(paso){
		        			Funciones.mostrarMensaje("Hora extraordinaria agregada correctamente", "", "INFO");
		        			Funciones.manageDialog("dlg2", 1);
		        			listHoras = lovDAO.dameHorasExtras(idSolicitud);
		        			Funciones.updateComponent("frmSolicitud:idHorasExt");
		        		}else{
		        			Funciones.mostrarMensaje("Ocurrio un error al guardar las horas extras, consulte al administrador de sistema", "", "ERROR");
		        		}
		        	}else{
		        		Funciones.mostrarMensaje("Error: No se ecnontro el id solicitud, consulte al administrador de sistema", "", "ERROR");
		        	}
	        	}
	    	}else{
	    		boolean existe = lovDAO.existeMismafechaModificable(Funciones.dateToString(this.fechaHe),idSolicitud,this.idSolicitudHE);
	    		if(existe){
	    			Funciones.mostrarMensaje("Error: ya existe un registro con la misma fecha", "", "WARN");
	    		}else{
	    			boolean ok = lovDAO.editaHorasExtras(this.idSolicitudHE, Funciones.dateToString(this.fechaHe),
							Funciones.dateToHoras(this.horaInicio), Funciones.dateToHoras(this.horaSalida),
							this.hrsLaboradas, this.justificacion);
					if(ok){
						Funciones.manageDialog("dlg2", 1);
						listHoras = lovDAO.dameHorasExtras(this.idSolicitud);
						Funciones.updateComponent("frmSolicitud:idHorasExt");
						Funciones.mostrarMensaje("Registro actualizado correctamente", "", "INFO");
					}else{
						Funciones.mostrarMensaje("Ocurrio un error en  la ase de datos, consulte al administrador de sistema", "", "ERROR");
					}
	    		}
	    	}
	    }else{
    		Funciones.mostrarMensaje(diaFeriado, "", "ERROR");
    	}
    }
    
	public java.util.Date getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(java.util.Date horaInicio) {
		this.horaInicio = horaInicio;
	}

	public java.util.Date getHoraSalida() {
		return horaSalida;
	}

	public void setHoraSalida(java.util.Date horaSalida) {
		this.horaSalida = horaSalida;
	}

	public java.util.Date getFechaHe() {
		return fechaHe;
	}

	public void setFechaHe(java.util.Date fechaHe) {
		this.fechaHe = fechaHe;
	}

	public String getHrsLaboradas() {
		return hrsLaboradas;
	}

	public void setHrsLaboradas(String hrsLaboradas) {
		this.hrsLaboradas = hrsLaboradas;
	}

	public String getJustificacion() {
		return justificacion;
	}

	public void setJustificacion(String justificacion) {
		this.justificacion = justificacion;
	}

	public int getIdSolicitudHE() {
		return idSolicitudHE;
	}

	public void setIdSolicitudHE(int idSolicitudHE) {
		this.idSolicitudHE = idSolicitudHE;
	}

	public String getMg() {
		return mg;
	}

	public void setMg(String mg) {
		this.mg = mg;
	}

	public void setNumEmpleado(String numEmpleado) {
		this.numEmpleado = numEmpleado;
	}

	public String getNumEmpleado() {
		return numEmpleado;
	}
	public void setnumEmpleado(String numEmpleado) {
		this.numEmpleado = numEmpleado;
	}
	public String getNombreEmpleado() {
		return nombreEmpleado;
	}
	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}
	public String getOrganoJudicial() {
		return organoJudicial;
	}
	public void setOrganoJudicial(String organoJudicial) {
		this.organoJudicial = organoJudicial;
	}
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	public String getFechaInicial() {
		return fechaInicial;
	}
	public void setFechaInicial(String fechaInicial) {
		this.fechaInicial = fechaInicial;
	}
	public String getFechaFinal() {
		return fechaFinal;
	}
	public void setFechaFinal(String fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	public String getFechaPaga() {
		return fechaPaga;
	}
	public void setFechaPaga(String fechaPaga) {
		this.fechaPaga = fechaPaga;
	}
	public String getDescripcionNomina() {
		return descripcionNomina;
	}
	public void setDescripcionNomina(String descripcionNomina) {
		this.descripcionNomina = descripcionNomina;
	}
	public String getJornadaOrdinaria() {
		return jornadaOrdinaria;
	}
	public void setJornadaOrdinaria(String jornadaOrdinaria) {
		this.jornadaOrdinaria = jornadaOrdinaria;
	}



	public List<HorasExtrasBean> getListHoras() {
		return listHoras;
	}



	public void setListHoras(List<HorasExtrasBean> listHoras) {
		this.listHoras = listHoras;
	}

	public int getIdSolicitud() {
		return idSolicitud;
	}

	public void setIdSolicitud(int idSolicitud) {
		this.idSolicitud = idSolicitud;
	}


	public boolean isCheck() {
		return check;
	}


	public void setCheck(boolean check) {
		this.check = check;
	}


	public String getAprobarSolicitud() {
		return aprobarSolicitud;
	}


	public void setAprobarSolicitud(String aprobarSolicitud) {
		this.aprobarSolicitud = aprobarSolicitud;
	}
    
    
    
    

}

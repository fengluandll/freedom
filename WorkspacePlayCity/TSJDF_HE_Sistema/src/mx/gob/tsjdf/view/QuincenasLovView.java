package mx.gob.tsjdf.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import mx.gob.tsjdf.bean.HorasExtrasBean;
import mx.gob.tsjdf.bean.LoginBean;
import mx.gob.tsjdf.bean.LovBean;
import mx.gob.tsjdf.bean.SolicitudesBean;
import mx.gob.tsjdf.model.CapturarSolicitudesDAO;
import mx.gob.tsjdf.model.LovDAO;
import mx.gob.tsjdf.model.UsersDAO;
import mx.gob.tsjdf.util.Funciones;

@ManagedBean
@ViewScoped
public class QuincenasLovView implements Serializable {
	private static final long serialVersionUID = -8766619882656268375L;
	
	private static org.apache.log4j.Logger logger = Logger.getLogger(QuincenasLovView.class);
	private Map<String,Map<String,String>> data = new HashMap<String, Map<String,String>>();
	FacesContext facesContext = FacesContext.getCurrentInstance();
	Map<String,String> params = facesContext.getExternalContext().getRequestParameterMap();
	
    private String code;
    private String descripcion;
    private String numEmpleado;
    private String numEmpleadoSolicitud;
    private String nombreEmpleado;
    private String organoJudicial;
    private String rfc;
    private String fechaInicial;
    private String fechaFinal;
    private String fechaPaga;
    private String descripcionNomina;
    private String jornadaOrdinaria;
    private java.util.Date horaInicio;
    private java.util.Date horaSalida;
    private java.util.Date fechaHe;
    private String hrsLaboradas;
    private String justificacion;
    List<HorasExtrasBean> listHoras;
    List<SolicitudesBean> listSolicitudes;
    List<SolicitudesBean> filteredSolicitudes;
    private String mg;
    private int idSolicitudHE;
    private boolean check;
    CapturarSolicitudesDAO capturaSolDao;
    
    
    private Map<String,String> lovData;
    LovDAO lovDAO; 
    List<LovBean> listLov;
    UsersDAO usersDAO;
     
    @PostConstruct
    public void init() {

		LoginBean login = (LoginBean) Funciones.getSession().getAttribute("loginBean");
		
    	lovDAO    = new LovDAO();
    	usersDAO  = new UsersDAO();
    	listLov   =  lovDAO.getQuincenas(login.getIdRol());
    	lovData   = new TreeMap<String, String>();
    	listHoras = new ArrayList<>();
    	
    	for(LovBean l : listLov){
    		lovData.put(l.getCode()+" - "+l.getDescripcion(),l.getCode());
    	}
    }
    
    public String editaSolicitud(String numEmpleado){
    	
	    
    	SolicitudesBean solicitudesBean = lovDAO.dameSolicitud(numEmpleado, code);
    	listHoras = lovDAO.dameHorasExtras(solicitudesBean.getIdSolicitud());
    	HttpSession session = Funciones.getSession();
    	session.setAttribute("solicitudesBean", solicitudesBean);
    	session.setAttribute("listHoras"      , listHoras);
		return "modificarSolicitud";
    }
    
    public String apruebaSolicitud(String numEmpleado){
    	
	    logger.info("");
    	SolicitudesBean solicitudesBean = lovDAO.dameSolicitud(numEmpleado, code);
    	listHoras = lovDAO.dameHorasExtras(solicitudesBean.getIdSolicitud());
    	HttpSession session = Funciones.getSession();
    	session.setAttribute("solicitudesBean", solicitudesBean);
    	session.setAttribute("listHoras"      , listHoras);
		return "aprobarSolicitud";
    }
    
    public void eliminaSolicitud(int idSolicitud){
    	boolean ok = lovDAO.eliminaSolicitud(idSolicitud);
    	if(ok){
    		Funciones.mostrarMensaje("Solicitud eliminada correctamente", "", "INFO");
    		listSolicitudes = capturaSolDao.dameSolicitudesPorProcesar(getCode());
    	}else{
    		Funciones.mostrarMensaje("Ocurrio un error en  la ase de datos, consulte al administrador de sistema", "", "ERROR");
    	}
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
    
    public void listaHorasExtras(int idSolicitud){
    	listHoras = lovDAO.dameHorasExtras(idSolicitud);
    }
    
    public void modificaHoras(HorasExtrasBean horasExtrasBean){
    	this.mg = "M";
    	this.horaInicio    = Funciones.deStringToDateHrs(horasExtrasBean.getHoraEntrada());
    	this.horaSalida    = Funciones.deStringToDateHrs(horasExtrasBean.getHoraSalida());
    	this.fechaHe       = Funciones.deStringToDate(horasExtrasBean.getFecha());
    	this.hrsLaboradas  = horasExtrasBean.getNumHorasLaboradas();
    	this.justificacion = horasExtrasBean.getJustificacion();
    	this.idSolicitudHE = horasExtrasBean.getIdSolicitud();
    	Funciones.manageDialog("dlg2", 2);
    	
    }
    
    public void eliminaHoras(HorasExtrasBean horasExtrasBean){
    	boolean ok = lovDAO.EliminaHoraExtra(horasExtrasBean.getIdSolicitudHe());
    	if(ok){
    		listaHorasExtras(horasExtrasBean.getIdSolicitud());
    		Funciones.updateComponent("frmSolicitud:idHorasExt");
    		Funciones.mostrarMensaje("Registro eliminado correctamente", "", "INFO");
    	}else{
    		Funciones.mostrarMensaje("Ocurrio un error en  la ase de datos, consulte al administrador de sistema", "", "ERROR");
    	}
    }
    
    public void guardaHorasExtras(){
		LoginBean loginBean = (LoginBean) Funciones.getSession().getAttribute("loginBean");
		Integer tipoSession = loginBean.getIdRol();
    	int idSolicitud = lovDAO.obtendIdSolicitud(this.numEmpleado,this.fechaPaga);
    	String diaFeriado = "";
    	@SuppressWarnings("deprecation")
		Integer hrIniFin = this.horaSalida.getHours()-this.horaInicio.getHours();
    	if(tipoSession == 3)
    		diaFeriado = "OK";
    	else
    		diaFeriado = Funciones.diaFeriado(Funciones.dateToString(this.fechaHe), this.numEmpleado,this.fechaPaga, idSolicitud, this.hrsLaboradas,hrIniFin.toString());
    	if (diaFeriado.equals("OK")) {
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
		        			listaHorasExtras(idSolicitud);
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
		    			listaHorasExtras(this.idSolicitudHE);
		    			Funciones.updateComponent("frmSolicitud:idHorasExt");
		    			Funciones.mostrarMensaje("Registro actualizado correctamente", "", "INFO");
		    		}else{
		    			Funciones.mostrarMensaje("Ocurrio un error en  la base de datos, consulte al administrador de sistema", "", "ERROR");
		    		}
	    		}
	    	}
    	}else{
    		Funciones.mostrarMensaje(diaFeriado, "", "ERROR");
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
    
    public void generarQuincena(){
    	LoginBean personalBean = (LoginBean) Funciones.getSession().getAttribute("loginBean");
    	boolean existe = usersDAO.existeUsusario(numEmpleado);
    	if(existe){
    		SolicitudesBean solicitudesBean = lovDAO.dameSolicitud(numEmpleado, code);
    		if(solicitudesBean.getNumEmpleado()!=null){
    			String validaComisarios = lovDAO.validaComisarios(numEmpleado, code);
    			if(validaComisarios.equals("OK")){
	    			boolean existeSolicitud = lovDAO.existeEmpleadoSolicitud(numEmpleado, code);
	    			
	    			if(!existeSolicitud){
	    				boolean paso = lovDAO.guardarSolicitud(solicitudesBean,personalBean);
	        			if(paso){
	        				this.numEmpleadoSolicitud = solicitudesBean.getNumEmpleado();
	                		this.nombreEmpleado       = solicitudesBean.getNombreCompleto();
	                		this.organoJudicial    	  = solicitudesBean.getIdArea();
	                		this.rfc                  = solicitudesBean.getRfc();
	                		this.fechaInicial         = solicitudesBean.getFechaInicial()==null?null:solicitudesBean.getFechaInicial().toString();
	                		this.fechaFinal           = solicitudesBean.getFechaFinal()==null?null:solicitudesBean.getFechaFinal().toString();
	                		this.fechaPaga		      = solicitudesBean.getFechaPaga()==null?null:solicitudesBean.getFechaPaga().toString();
	                		this.descripcionNomina    = solicitudesBean.getDescripcionNomina();
	                		this.jornadaOrdinaria     = solicitudesBean.getJornadaOrdinaria();
	        			}else{
	        				Funciones.mostrarMensaje("Error al insertar la solicitud, consulte al Administrador de Sistema", "", "FATAL");
	        			}
	    			}else{
	    				Funciones.mostrarMensaje("Este empleado ya tiene una solicitud registrada", "", "WARN");
	    			}
    			}else{
    				Funciones.mostrarMensaje(validaComisarios, "", "WARN");
    			}
    			
    			
    		}else{
    			Funciones.mostrarMensaje("Este empleado no existe en la quincena seleccionada", "", "WARN");
    		}
    		
    	}else{
    		Funciones.mostrarMensaje("El numero de empleado no existe", "", "ERROR");
    	}
    	
    }
 
    public void consultaSolicitudes(){
        capturaSolDao = new CapturarSolicitudesDAO();
        listSolicitudes = new ArrayList<>();
        setListSolicitudes(capturaSolDao.dameSolicitudesPorProcesar(getCode()));
    }
 
    public void consultaSolicitudesReporte(){
        capturaSolDao = new CapturarSolicitudesDAO();
        listSolicitudes = new ArrayList<>();
        setListSolicitudes(capturaSolDao.dameSolicitudesCreadasQuincena(getCode()));
    }
 
    public void consultaSolicitudesPorAprobar(){
        capturaSolDao = new CapturarSolicitudesDAO();
        listSolicitudes = new ArrayList<>();
        listSolicitudes = capturaSolDao.dameSolicitudesPorAprobar(getCode());
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

	public String getNumEmpleadoSolicitud() {
		return numEmpleadoSolicitud;
	}

	public void setNumEmpleadoSolicitud(String numEmpleadoSolicitud) {
		this.numEmpleadoSolicitud = numEmpleadoSolicitud;
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

	public String getNumEmpleado() {
		return numEmpleado;
	}

	public void setNumEmpleado(String numEmpleado) {
		this.numEmpleado = numEmpleado;
	}

	public Map<String, Map<String, String>> getData() {
        return data;
    }
 
    public String getCode() {
        return code;
    }
 
    public void setCode(String code) {
        this.code = code;
    }

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Map<String, String> getLovData() {
		return lovData;
	}

	public void setLovData(Map<String, String> lovData) {
		this.lovData = lovData;
	}
	public void onQuincenaChange() {
    }

	public List<HorasExtrasBean> getListHoras() {
		return listHoras;
	}

	public void setListHoras(List<HorasExtrasBean> listHoras) {
		this.listHoras = listHoras;
	}

	public String getMg() {
		return mg;
	}

	public void setMg(String mg) {
		this.mg = mg;
	}

	public int getIdSolicitudHE() {
		return idSolicitudHE;
	}

	public void setIdSolicitudHE(int idSolicitudHE) {
		this.idSolicitudHE = idSolicitudHE;
	}

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	public List<SolicitudesBean> getListSolicitudes() {
		return listSolicitudes;
	}

	public void setListSolicitudes(List<SolicitudesBean> listSolicitudes) {
		this.listSolicitudes = listSolicitudes;
	}

	public List<SolicitudesBean> getFilteredSolicitudes() {
		return filteredSolicitudes;
	}

	public void setFilteredSolicitudes(List<SolicitudesBean> filteredSolicitudes) {
		this.filteredSolicitudes = filteredSolicitudes;
	}
	
	
}

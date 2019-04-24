/**
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 */
package mx.kaz.view;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpSession;

import mx.kaz.beans.PersonalBean;
import mx.kaz.beans.ProyectBean;
import mx.kaz.beans.RegLicitaciones;
import mx.kaz.beans.RegProyectoBean;
import mx.kaz.daos.ProyectDAO;
import mx.kaz.mail.SendMails;
import mx.kaz.util.ManejoFechas;

import org.apache.log4j.Logger;
import org.primefaces.event.SelectEvent;

/**
 * @author Jesus Argumedo All rights reserved.
 *
 */
@ViewScoped
public class RegProyectoView implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private static org.apache.log4j.Logger logger = Logger.getLogger(RegProyectoView.class);
	private int idProyect = 0;
	private String api;
	private String nomProyecto;
	private String desPro;
	private String claveCartera;
	private BigDecimal montoAsignado;
	private String tipoRecurso;
	private String periodoEjecu;
	private BigDecimal inversionTotal;
	private BigDecimal montoEjercido;
	private int idPort;
	FacesContext facesContext;
	HttpSession session;
	ProyectBean proyectBean;
	RegLicitaciones regLicitaciones;
	private int idLicitacion;
	private String descObra;
	private BigDecimal presuBase;
	private Date pubConvocatoria;
	private Date aperProposi;
	private String nomParticipante;
	private String montoParticipante;
	private Date fecFallo;
	private String nomContratista;
	private BigDecimal montoTotal;
	private Date perEjecucionIni;
	private Date perEjecucionFin;
	private String observaciones;
	private List<RegLicitaciones> listLici;
	ProyectDAO proyectDAO;
	boolean agregaNoLicitacion;
	private Map<String,Integer> mapProcedures = new HashMap<String,Integer>();
	private Map<String,Integer> mapTipoRecurso = new HashMap<String,Integer>();
	int idProcedure;
	SendMails sendMails;
	private boolean enabled = true;
	
	
	public RegProyectoView(){
		sendMails = new SendMails();
		agregaNoLicitacion = false;
		facesContext = FacesContext.getCurrentInstance();
		session = (HttpSession) facesContext.getExternalContext().getSession(true);
		proyectDAO = new ProyectDAO();
		Map<String,String> params = facesContext.getExternalContext().getRequestParameterMap();
		String idProyect = params.get("idProject");
		if(idProyect != null){
			listLici = proyectDAO.obtenLicitacion(Integer.parseInt(idProyect));
		}
		mapProcedures  = proyectDAO.loadProcedure(); 
		mapTipoRecurso = proyectDAO.loadTipoRecurso();
	}
	
	public void editarProject(RegProyectoBean regProyectoBean){
		
		this.idProyect = regProyectoBean.getIdProyect();
		this.setNomProyecto(regProyectoBean.getNomProyecto());
		this.desPro = regProyectoBean.getDesPro();
		this.claveCartera = regProyectoBean.getClaveCartera();
		this.periodoEjecu = regProyectoBean.getPeriodoEjecu();
		this.inversionTotal = regProyectoBean.getInversionTotal();
		this.montoEjercido = regProyectoBean.getMontoEjercido();
		this.montoAsignado = regProyectoBean.getMontoAsignado();
		this.tipoRecurso = regProyectoBean.getTipoRecursoNum();
		
	}
	
	public void guardaActualizacion() throws IOException{
		logger.info("Actualiza "+ this.idProyect);
		RegProyectoBean regProyectoBean = new RegProyectoBean();
		regProyectoBean.setIdProyect(this.idProyect);
		regProyectoBean.setIdPort(this.idPort);
		regProyectoBean.setNomProyecto(this.nomProyecto);
		regProyectoBean.setDesPro(this.desPro);
		regProyectoBean.setClaveCartera(this.claveCartera);
		regProyectoBean.setPeriodoEjecu(this.periodoEjecu);
		regProyectoBean.setInversionTotal(this.inversionTotal);
		regProyectoBean.setMontoEjercido(this.montoEjercido);
		regProyectoBean.setMontoAsignado(this.montoAsignado);
		regProyectoBean.setTipoRecurso(this.tipoRecurso);
		boolean bandera = proyectDAO.actualizaProject(regProyectoBean);
		if(bandera){
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Proyecto actualizado","Exito"));
			sendMails.editaProyecto(regProyectoBean);
//			projectView .obtenProyectos();
//			RequestContext.getCurrentInstance().update("frmShowPr:tblProy");
			FacesContext.getCurrentInstance().getExternalContext().redirect(
			FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/proyectos/registroProyectos.xhtml");
//			RequestContext context = RequestContext.getCurrentInstance();
//			RequestContext.getCurrentInstance().execute("dlgProy.hide()");
		}
	}
	
	public void editarLicitacion(RegLicitaciones regLicitaciones){
		this.idLicitacion = regLicitaciones.getIdLicitacion();
		this.descObra = regLicitaciones.getDescObra();
		this.presuBase = regLicitaciones.getPresuBase();
		this.pubConvocatoria = regLicitaciones.getPublConvocatoria();
		this.aperProposi	= regLicitaciones.getAperProposiciones();
		this.nomParticipante = regLicitaciones.getParticipantes();
		this.fecFallo	= regLicitaciones.getFecFallo();
		this.nomContratista = regLicitaciones.getNomContratista();
		this.montoTotal = regLicitaciones.getTotalMonto();
		this.perEjecucionIni = regLicitaciones.getIniObra();
		this.perEjecucionFin = regLicitaciones.getFinObra();
		this.observaciones	= regLicitaciones.getObservaciones();
		this.idProcedure    = regLicitaciones.getTipoProcedimiento();
		
		
	}
	
	public void actualizaLicitacion() throws IOException{
		logger.info("Actualiza "+ this.idProyect);
		Integer projectId = (Integer) session.getAttribute("idProject");
		RegLicitaciones regLicitaciones = new RegLicitaciones();
		regLicitaciones.setIdLicitacion(this.idLicitacion);
		regLicitaciones.setDescObra(descObra);
		regLicitaciones.setPresuBase(presuBase);
		regLicitaciones.setPublConvocatoria(ManejoFechas.utilDateToslDate(this.pubConvocatoria));
		regLicitaciones.setAperProposiciones(ManejoFechas.utilDateToslDate(aperProposi));
		regLicitaciones.setParticipantes(nomParticipante);
		regLicitaciones.setFecFallo(ManejoFechas.utilDateToslDate(fecFallo));
		regLicitaciones.setNomContratista(nomContratista);
		regLicitaciones.setTotalMonto(montoTotal);
		regLicitaciones.setIniObra(ManejoFechas.utilDateToslDate(perEjecucionIni));
		regLicitaciones.setFinObra(ManejoFechas.utilDateToslDate(perEjecucionFin));
		regLicitaciones.setObservaciones(observaciones);
		String nomProcedure = proyectDAO.dameTipoProcedimiento(this.idProcedure);
		regLicitaciones.setNomProcedure(nomProcedure);
		regLicitaciones.setTipoProcedimiento(idProcedure);
		boolean bandera = proyectDAO.actualizaLicitacion(regLicitaciones);
		if(bandera){
//			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,
//					"Proyecto actualizado","Exito"));
//			projectView .obtenProyectos();
//			RequestContext.getCurrentInstance().update("frmShowPr:tblProy");
			sendMails.nuevaLicitacion(regLicitaciones,"modifica");
			FacesContext.getCurrentInstance().getExternalContext().redirect(
			FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/proyectos/registroProyectos.xhtml?idProject="+projectId);
//			RequestContext context = RequestContext.getCurrentInstance();
//			RequestContext.getCurrentInstance().execute("dlgProy.hide()");
		}
	}
	
	
	public int getIdPort() {
		return idPort;
	}



	public void setIdPort(int idPort) {
		this.idPort = idPort;
	}

	 public void onRowSelect(SelectEvent event) {
		 
		 RegProyectoBean regProyectoBean = (RegProyectoBean) event.getObject();
		 idProyect = regProyectoBean.getIdProyect();
		 session.setAttribute("idProject", idProyect);
		 agregaNoLicitacion = true;
		 listLici = proyectDAO.obtenLicitacion(regProyectoBean.getIdProyect());
	
	    }
	 
	 public void disabledButton(){
		 System.out.println("Desabilitado");
		 enabled = false;
	 }

	public void guardaProyecto() throws IOException{

		
		proyectBean = new ProyectBean();
		String nomPto = (String)session.getAttribute("nomPto");
		if(nomPto != null){
			proyectBean.setIdPort(proyectDAO.obtenIdPto(nomPto));
		}else{
			PersonalBean personalBean = (PersonalBean)session.getAttribute("personalBean");
			proyectBean.setIdPort(personalBean.getSegment6());
		}
		
		proyectBean.setName(this.nomProyecto);
		proyectBean.setDescription(this.desPro);
		proyectBean.setCode(this.claveCartera);
		proyectBean.setPeriod(this.periodoEjecu);
		proyectBean.setTotalInvestment(this.inversionTotal);
		proyectBean.setExercisedAmount(this.montoEjercido);
		proyectBean.setAsignedAmount(this.montoAsignado);
		proyectBean.setResourceType(this.tipoRecurso);
		enabled = false;
		boolean bandera = proyectDAO.insertProyect(proyectBean);
		if(bandera){
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Proyecto agregado","Exito"));
			sendMails.nuevoProyecto(proyectBean);
//			projectView .obtenProyectos();
//			RequestContext.getCurrentInstance().update("frmShowPr:tblProy");
			FacesContext.getCurrentInstance().getExternalContext().redirect(
			FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/proyectos/registroProyectos.xhtml");
//			RequestContext context = RequestContext.getCurrentInstance();
//			RequestContext.getCurrentInstance().execute("dlgProy.hide()");
		}
	}
	
	public void guardaLicitacion() throws IOException{
		
		regLicitaciones = new RegLicitaciones();
		regLicitaciones.setIdProject(this.idProyect);
		regLicitaciones.setDescObra(this.descObra);
		regLicitaciones.setPresuBase(presuBase);
		regLicitaciones.setPublConvocatoria(ManejoFechas.utilDateToslDate(this.pubConvocatoria));
		regLicitaciones.setAperProposiciones(ManejoFechas.utilDateToslDate(this.aperProposi));
		regLicitaciones.setFecFallo(ManejoFechas.utilDateToslDate(this.fecFallo));
		regLicitaciones.setNomContratista(this.nomContratista);
		regLicitaciones.setTotalMonto(this.montoTotal);
		regLicitaciones.setIniObra(ManejoFechas.utilDateToslDate(this.perEjecucionIni));
		regLicitaciones.setFinObra(ManejoFechas.utilDateToslDate(this.perEjecucionFin));
		regLicitaciones.setObservaciones(this.observaciones);
		regLicitaciones.setParticipantes(this.nomParticipante);
		String nomProcedure = proyectDAO.dameTipoProcedimiento(this.idProcedure);
		regLicitaciones.setNomProcedure(nomProcedure);
		regLicitaciones.setTipoProcedimiento(this.idProcedure);
		boolean bandera = proyectDAO.insertLicitacion(regLicitaciones);
		if(bandera){
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Licitacion agregada","Exito"));
			descObra = "";
			presuBase = new BigDecimal(0);
			pubConvocatoria = new Date();
			aperProposi = new Date();
			fecFallo = new Date();
			nomContratista ="";
			montoTotal = new BigDecimal(0);
			perEjecucionIni = new Date();
			perEjecucionFin = new Date();
			observaciones = "";
			nomParticipante = "";
			sendMails.nuevaLicitacion(regLicitaciones,"nueva");
			FacesContext.getCurrentInstance().getExternalContext().redirect(
					FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/proyectos/registroProyectos.xhtml?idProject="+this.idProyect);
		}else{
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Ocurrio un error","ups!"));
		}
	}
	
	
	
	public BigDecimal getMontoEjercido() {
		return montoEjercido;
	}


	public void setMontoEjercido(BigDecimal montoEjercido) {
		this.montoEjercido = montoEjercido;
	}


	public BigDecimal getInversionTotal() {
		return inversionTotal;
	}


	public void setInversionTotal(BigDecimal inversionTotal) {
		this.inversionTotal = inversionTotal;
	}


	public String getPeriodoEjecu() {
		return periodoEjecu;
	}


	public void setPeriodoEjecu(String periodoEjecu) {
		this.periodoEjecu = periodoEjecu;
	}


	public int getIdProyect() {
		return idProyect;
	}

	public void setIdProyect(int idProyect) {
		this.idProyect = idProyect;
	}

	public String getApi() {
		return api;
	}

	public void setApi(String api) {
		this.api = api;
	}

	public String getNomProyecto() {
		return nomProyecto;
	}

	public void setNomProyecto(String nomProyecto) {
		this.nomProyecto = nomProyecto;
	}

	public String getDesPro() {
		return desPro;
	}

	public void setDesPro(String desPro) {
		this.desPro = desPro;
	}

	public String getClaveCartera() {
		return claveCartera;
	}

	public void setClaveCartera(String claveCartera) {
		this.claveCartera = claveCartera;
	}

	public BigDecimal getMontoAsignado() {
		return montoAsignado;
	}

	public void setMontoAsignado(BigDecimal montoAsignado) {
		this.montoAsignado = montoAsignado;
	}

	public String getTipoRecurso() {
		return tipoRecurso;
	}

	public void setTipoRecurso(String tipoRecurso) {
		this.tipoRecurso = tipoRecurso;
	}



	public String getDescObra() {
		return descObra;
	}



	public void setDescObra(String descObra) {
		this.descObra = descObra;
	}



	public BigDecimal getPresuBase() {
		return presuBase;
	}



	public void setPresuBase(BigDecimal presuBase) {
		this.presuBase = presuBase;
	}










	public String getNomParticipante() {
		return nomParticipante;
	}



	public void setNomParticipante(String nomParticipante) {
		this.nomParticipante = nomParticipante;
	}



	public String getMontoParticipante() {
		return montoParticipante;
	}



	public void setMontoParticipante(String montoParticipante) {
		this.montoParticipante = montoParticipante;
	}



	


	public String getNomContratista() {
		return nomContratista;
	}



	public void setNomContratista(String nomContratista) {
		this.nomContratista = nomContratista;
	}


	public BigDecimal getMontoTotal() {
		return montoTotal;
	}



	public void setMontoTotal(BigDecimal montoTotal) {
		this.montoTotal = montoTotal;
	}



	public Date getPubConvocatoria() {
		return pubConvocatoria;
	}



	public void setPubConvocatoria(Date pubConvocatoria) {
		this.pubConvocatoria = pubConvocatoria;
	}



	public Date getAperProposi() {
		return aperProposi;
	}



	public void setAperProposi(Date aperProposi) {
		this.aperProposi = aperProposi;
	}



	public Date getFecFallo() {
		return fecFallo;
	}



	public void setFecFallo(Date fecFallo) {
		this.fecFallo = fecFallo;
	}



	public Date getPerEjecucionIni() {
		return perEjecucionIni;
	}



	public void setPerEjecucionIni(Date perEjecucionIni) {
		this.perEjecucionIni = perEjecucionIni;
	}



	public Date getPerEjecucionFin() {
		return perEjecucionFin;
	}



	public void setPerEjecucionFin(Date perEjecucionFin) {
		this.perEjecucionFin = perEjecucionFin;
	}



	public List<RegLicitaciones> getListLici() {
		return listLici;
	}



	public void setListLici(List<RegLicitaciones> listLici) {
		this.listLici = listLici;
	}



	public String getObservaciones() {
		return observaciones;
	}



	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public int getIdLicitacion() {
		return idLicitacion;
	}

	public void setIdLicitacion(int idLicitacion) {
		this.idLicitacion = idLicitacion;
	}

	public boolean isAgregaNoLicitacion() {
		return agregaNoLicitacion;
	}

	public void setAgregaNoLicitacion(boolean agregaNoLicitacion) {
		this.agregaNoLicitacion = agregaNoLicitacion;
	}


	public Map<String, Integer> getMapProcedures() {
		return mapProcedures;
	}

	public void setMapProcedures(Map<String, Integer> mapProcedures) {
		this.mapProcedures = mapProcedures;
	}

	public int getIdProcedure() {
		return idProcedure;
	}

	public void setIdProcedure(int idProcedure) {
		this.idProcedure = idProcedure;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Map<String, Integer> getMapTipoRecurso() {
		return mapTipoRecurso;
	}

	public void setMapTipoRecurso(Map<String, Integer> mapTipoRecurso) {
		this.mapTipoRecurso = mapTipoRecurso;
	}

	

	
		
	
}

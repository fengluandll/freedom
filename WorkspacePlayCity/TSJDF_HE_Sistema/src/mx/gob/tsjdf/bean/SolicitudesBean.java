package mx.gob.tsjdf.bean;

import java.io.Serializable;

public class SolicitudesBean implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private int idSolicitud;
	private String numEmpleado;
	private String nombreCompleto;
	private String rfc;
	private String aPaterno;
	private String aMaterno;
	private String nombre;
	private String jornadaOrdinaria;
	private String fechaInicial;
	private String fechaFinal;
	private String fechaPaga;
	private String idArea;
	private String idNivel;
	private String numPlaza;
	private int totalHe;
	private int totalDiasHe;
	private int idstatusSolicitud;
	private String numEmpleadoModifica;
	private java.sql.Date fechaModifica;
	private String descripcionNomina;
	private String statusSolicitud;
	private String numEmpeadoCrea;
	private String fechaCrea;
	private String numEmpeadoAutoriza;
	private String fechaAutoriza;
	private String numEmpleadoGenera;
	private String fechaGenera;
	
	public String getStatusSolicitud() {
		return statusSolicitud;
	}
	public void setStatusSolicitud(String statusSolicitud) {
		this.statusSolicitud = statusSolicitud;
	}
	public String getDescripcionNomina() {
		return descripcionNomina;
	}
	public void setDescripcionNomina(String descripcionNomina) {
		this.descripcionNomina = descripcionNomina;
	}
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public int getIdSolicitud() {
		return idSolicitud;
	}
	public void setIdSolicitud(int idSolicitud) {
		this.idSolicitud = idSolicitud;
	}
	public String getNumEmpleado() {
		return numEmpleado;
	}
	public void setNumEmpleado(String numEmpleado) {
		this.numEmpleado = numEmpleado;
	}
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	public String getaPaterno() {
		return aPaterno;
	}
	public void setaPaterno(String aPaterno) {
		this.aPaterno = aPaterno;
	}
	public String getaMaterno() {
		return aMaterno;
	}
	public void setaMaterno(String aMaterno) {
		this.aMaterno = aMaterno;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getJornadaOrdinaria() {
		return jornadaOrdinaria;
	}
	public void setJornadaOrdinaria(String jornadaOrdinaria) {
		this.jornadaOrdinaria = jornadaOrdinaria;
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
	public String getIdArea() {
		return idArea;
	}
	public void setIdArea(String idArea) {
		this.idArea = idArea;
	}
	public String getIdNivel() {
		return idNivel;
	}
	public void setIdNivel(String idNivel) {
		this.idNivel = idNivel;
	}
	public String getNumPlaza() {
		return numPlaza;
	}
	public void setNumPlaza(String numPlaza) {
		this.numPlaza = numPlaza;
	}
	public int getTotalHe() {
		return totalHe;
	}
	public void setTotalHe(int totalHe) {
		this.totalHe = totalHe;
	}
	public int getTotalDiasHe() {
		return totalDiasHe;
	}
	public void setTotalDiasHe(int totalDiasHe) {
		this.totalDiasHe = totalDiasHe;
	}
	public int getIdstatusSolicitud() {
		return idstatusSolicitud;
	}
	public void setIdstatusSolicitud(int idstatusSolicitud) {
		this.idstatusSolicitud = idstatusSolicitud;
	}
	public String getNumEmpleadoModifica() {
		return numEmpleadoModifica;
	}
	public void setNumEmpleadoModifica(String numEmpleadoModifica) {
		this.numEmpleadoModifica = numEmpleadoModifica;
	}
	public java.sql.Date getFechaModifica() {
		return fechaModifica;
	}
	public void setFechaModifica(java.sql.Date fechaModifica) {
		this.fechaModifica = fechaModifica;
	}
	public String getNumEmpeadoCrea() {
		return numEmpeadoCrea;
	}
	public void setNumEmpeadoCrea(String numEmpeadoCrea) {
		this.numEmpeadoCrea = numEmpeadoCrea;
	}
	public String getFechaCrea() {
		return fechaCrea;
	}
	public void setFechaCrea(String fechaCrea) {
		this.fechaCrea = fechaCrea;
	}
	public String getNumEmpeadoAutoriza() {
		return numEmpeadoAutoriza;
	}
	public void setNumEmpeadoAutoriza(String numEmpeadoAutoriza) {
		this.numEmpeadoAutoriza = numEmpeadoAutoriza;
	}
	public String getFechaAutoriza() {
		return fechaAutoriza;
	}
	public void setFechaAutoriza(String fechaAutoriza) {
		this.fechaAutoriza = fechaAutoriza;
	}
	public String getNumEmpleadoGenera() {
		return numEmpleadoGenera;
	}
	public void setNumEmpleadoGenera(String numEmpleadoGenera) {
		this.numEmpleadoGenera = numEmpleadoGenera;
	}
	public String getFechaGenera() {
		return fechaGenera;
	}
	public void setFechaGenera(String fechaGenera) {
		this.fechaGenera = fechaGenera;
	}
}

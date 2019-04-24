package mx.gob.tsjdf.bean;

import java.io.Serializable;

public class HorasExtrasBean implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private int idSolicitud;
	private String fecha;
	private String horaEntrada;
	private String horaSalida;
	private String numHorasLaboradas;
	private String justificacion;
	private int idSolicitudHe;
	
	
	
	
	public int getIdSolicitudHe() {
		return idSolicitudHe;
	}
	public void setIdSolicitudHe(int idSolicitudHe) {
		this.idSolicitudHe = idSolicitudHe;
	}
	public int getIdSolicitud() {
		return idSolicitud;
	}
	public void setIdSolicitud(int idSolicitud) {
		this.idSolicitud = idSolicitud;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getHoraEntrada() {
		return horaEntrada;
	}
	public void setHoraEntrada(String horaEntrada) {
		this.horaEntrada = horaEntrada;
	}
	public String getHoraSalida() {
		return horaSalida;
	}
	public void setHoraSalida(String horaSalida) {
		this.horaSalida = horaSalida;
	}
	public String getNumHorasLaboradas() {
		return numHorasLaboradas;
	}
	public void setNumHorasLaboradas(String numHorasLaboradas) {
		this.numHorasLaboradas = numHorasLaboradas;
	}
	public String getJustificacion() {
		return justificacion;
	}
	public void setJustificacion(String justificacion) {
		this.justificacion = justificacion;
	}
	
	

}

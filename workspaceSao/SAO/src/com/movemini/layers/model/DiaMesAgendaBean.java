package com.movemini.layers.model;

import java.io.Serializable;

public class DiaMesAgendaBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String 	dia;
	private String 	mes;
	private String 	diaLetra;
	private int	   	idEventoFecha;
	private int	   	idEventoVersion;	
	private String 	fecha;
	private int		idEventoStatus;
	private String		clienteNom;
	
	
	public String getDia() {
		return dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public String getDiaLetra() {
		return diaLetra;
	}
	public void setDiaLetra(String diaLetra) {
		this.diaLetra = diaLetra;
	}
	public int getIdEventoFecha() {
		return idEventoFecha;
	}
	public void setIdEventoFecha(int idEventoFecha) {
		this.idEventoFecha = idEventoFecha;
	}
	public int getIdEventoVersion() {
		return idEventoVersion;
	}
	public void setIdEventoVersion(int idEventoVersion) {
		this.idEventoVersion = idEventoVersion;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public int getIdEventoStatus() {
		return idEventoStatus;
	}
	public void setIdEventoStatus(int idEventoStatus) {
		this.idEventoStatus = idEventoStatus;
	}
	public String getClienteNom() {
		return clienteNom;
	}
	public void setClienteNom(String clienteNom) {
		this.clienteNom = clienteNom;
	}
	
	
	
}

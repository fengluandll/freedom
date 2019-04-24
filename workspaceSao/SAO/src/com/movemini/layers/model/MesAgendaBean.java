package com.movemini.layers.model;

import java.io.Serializable;

public class MesAgendaBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String mesLetra;
	private String mesNumero;
	
	
	public String getMesLetra() {
		return mesLetra;
	}
	public void setMesLetra(String mesLetra) {
		this.mesLetra = mesLetra;
	}
	public String getMesNumero() {
		return mesNumero;
	}
	public void setMesNumero(String mesNumero) {
		this.mesNumero = mesNumero;
	}
	
	

}

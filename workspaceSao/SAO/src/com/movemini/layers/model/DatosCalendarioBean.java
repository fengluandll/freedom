package com.movemini.layers.model;

import java.io.Serializable;

public class DatosCalendarioBean implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private String 	llevaEvento;
	private int 	idCliente;
	private String 	nomCliente;
	private int 	idEvento;
	private String 	nomEvento;
	private String 	claveCotizacion;
	private String 	sede;
	private String 	numFactura;
	private int 	idEventoVersion;
	private String 	fecha;
	private String abreviaResponsable;
	
	public String getLlevaEvento() {
		return llevaEvento;
	}
	public void setLlevaEvento(String llevaEvento) {
		this.llevaEvento = llevaEvento;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public String getNomCliente() {
		return nomCliente;
	}
	public void setNomCliente(String nomCliente) {
		this.nomCliente = nomCliente;
	}
	public int getIdEvento() {
		return idEvento;
	}
	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
	}
	public String getNomEvento() {
		return nomEvento;
	}
	public void setNomEvento(String nomEvento) {
		this.nomEvento = nomEvento;
	}
	public String getClaveCotizacion() {
		return claveCotizacion;
	}
	public void setClaveCotizacion(String claveCotizacion) {
		this.claveCotizacion = claveCotizacion;
	}
	public String getSede() {
		return sede;
	}
	public void setSede(String sede) {
		this.sede = sede;
	}
	public String getNumFactura() {
		return numFactura;
	}
	public void setNumFactura(String numFactura) {
		this.numFactura = numFactura;
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
	public String getAbreviaResponsable() {
		return abreviaResponsable;
	}
	public void setAbreviaResponsable(String abreviaResponsable) {
		this.abreviaResponsable = abreviaResponsable;
	}


	
}

/**
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 */
package mx.kaz.beans.reportes;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Argumedo
 *
 */
public class ReporteObraBean implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private BigDecimal    montoAsignadoIni;
	private BigDecimal    montoAsignadoFin;
	private String     	  tipoRecurso;
	private int        	  idPuerto;
	private String     	  nom_contratista;
	private java.sql.Date periodEjecIni;
	private java.sql.Date periodEjecFin;
	private java.sql.Date periodEjecIni2;
	private java.sql.Date periodEjecFin2;
	private java.sql.Date fechaFalloIni;
	private java.sql.Date fechaFalloFin;
	
	public ReporteObraBean() {
		
	}

	public BigDecimal getMontoAsignadoIni() {
		return montoAsignadoIni;
	}

	public void setMontoAsignadoIni(BigDecimal montoAsignadoIni) {
		this.montoAsignadoIni = montoAsignadoIni;
	}

	public BigDecimal getMontoAsignadoFin() {
		return montoAsignadoFin;
	}

	public void setMontoAsignadoFin(BigDecimal montoAsignadoFin) {
		this.montoAsignadoFin = montoAsignadoFin;
	}

	public String getTipoRecurso() {
		return tipoRecurso;
	}

	public void setTipoRecurso(String tipoRecurso) {
		this.tipoRecurso = tipoRecurso;
	}

	public int getIdPuerto() {
		return idPuerto;
	}

	public void setIdPuerto(int idPuerto) {
		this.idPuerto = idPuerto;
	}

	public String getNom_contratista() {
		return nom_contratista;
	}

	public void setNom_contratista(String nom_contratista) {
		this.nom_contratista = nom_contratista;
	}

	public java.sql.Date getPeriodEjecIni() {
		return periodEjecIni;
	}

	public void setPeriodEjecIni(java.sql.Date periodEjecIni) {
		this.periodEjecIni = periodEjecIni;
	}

	public java.sql.Date getPeriodEjecFin() {
		return periodEjecFin;
	}

	public void setPeriodEjecFin(java.sql.Date periodEjecFin) {
		this.periodEjecFin = periodEjecFin;
	}

	public java.sql.Date getPeriodEjecIni2() {
		return periodEjecIni2;
	}

	public void setPeriodEjecIni2(java.sql.Date periodEjecIni2) {
		this.periodEjecIni2 = periodEjecIni2;
	}

	public java.sql.Date getPeriodEjecFin2() {
		return periodEjecFin2;
	}

	public void setPeriodEjecFin2(java.sql.Date periodEjecFin2) {
		this.periodEjecFin2 = periodEjecFin2;
	}

	public java.sql.Date getFechaFalloIni() {
		return fechaFalloIni;
	}

	public void setFechaFalloIni(java.sql.Date fechaFalloIni) {
		this.fechaFalloIni = fechaFalloIni;
	}

	public java.sql.Date getFechaFalloFin() {
		return fechaFalloFin;
	}

	public void setFechaFalloFin(java.sql.Date fechaFalloFin) {
		this.fechaFalloFin = fechaFalloFin;
	}
	
	

}

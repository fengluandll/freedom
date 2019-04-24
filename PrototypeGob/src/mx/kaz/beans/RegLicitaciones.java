/**
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 */
package mx.kaz.beans;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Jesus Argumedo. All rights reserved.
 *
 */
public class RegLicitaciones implements Serializable{
	

	private static final long serialVersionUID = 1L;
	private int tipoProcedimiento;
	private String nomProcedure;
	private int idProject;
	private int idLicitacion;
	private String descObra;
	private BigDecimal presuBase;
	private java.sql.Date publConvocatoria;
	private java.sql.Date aperProposiciones;
	private java.sql.Date fecFallo;
	private java.sql.Date iniObra;
	private java.sql.Date finObra;
	private String observaciones;
	private BigDecimal totalMonto;
	private String nomContratista;
	private String participantes;
	
	
	public RegLicitaciones(){
		
	}


	
	public int getIdProject() {
		return idProject;
	}


		

	public int getTipoProcedimiento() {
		return tipoProcedimiento;
	}



	public void setTipoProcedimiento(int tipoProcedimiento) {
		this.tipoProcedimiento = tipoProcedimiento;
	}



	public void setIdProject(int idProject) {
		this.idProject = idProject;
	}



	public int getIdLicitacion() {
		return idLicitacion;
	}


	public void setIdLicitacion(int idLicitacion) {
		this.idLicitacion = idLicitacion;
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


	public java.sql.Date getPublConvocatoria() {
		return publConvocatoria;
	}


	public void setPublConvocatoria(java.sql.Date publConvocatoria) {
		this.publConvocatoria = publConvocatoria;
	}


	public java.sql.Date getAperProposiciones() {
		return aperProposiciones;
	}


	public void setAperProposiciones(java.sql.Date aperProposiciones) {
		this.aperProposiciones = aperProposiciones;
	}


	public java.sql.Date getFecFallo() {
		return fecFallo;
	}


	public void setFecFallo(java.sql.Date fecFallo) {
		this.fecFallo = fecFallo;
	}


	public java.sql.Date getIniObra() {
		return iniObra;
	}


	public void setIniObra(java.sql.Date iniObra) {
		this.iniObra = iniObra;
	}


	public java.sql.Date getFinObra() {
		return finObra;
	}


	public void setFinObra(java.sql.Date finObra) {
		this.finObra = finObra;
	}


	public String getObservaciones() {
		return observaciones;
	}


	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}


	public BigDecimal getTotalMonto() {
		return totalMonto;
	}


	public void setTotalMonto(BigDecimal totalMonto) {
		this.totalMonto = totalMonto;
	}


	public String getNomContratista() {
		return nomContratista;
	}


	public void setNomContratista(String nomContratista) {
		this.nomContratista = nomContratista;
	}


	public String getParticipantes() {
		return participantes;
	}


	public void setParticipantes(String participantes) {
		this.participantes = participantes;
	}



	public String getNomProcedure() {
		return nomProcedure;
	}



	public void setNomProcedure(String nomProcedure) {
		this.nomProcedure = nomProcedure;
	}
	
	

}

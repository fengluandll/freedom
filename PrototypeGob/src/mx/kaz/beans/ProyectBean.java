
package mx.kaz.beans;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author jesus argumedo Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 */
public class ProyectBean implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private int idProyect;
	private int idProyectType;
	private String name;
	private int idPort;
	private String acronym;
	private String description;
	private String code;
	private String period;
	private BigDecimal totalInvestment;
	private BigDecimal exercisedAmount;
	private BigDecimal asignedAmount;
	private String resourceType;
	private BigDecimal resourcePercent;
	private String buildDescription;
	private String nomPto;
	
	
	
	
	public String getNomPto() {
		return nomPto;
	}
	public void setNomPto(String nomPto) {
		this.nomPto = nomPto;
	}
	public int getIdProyect() {
		return idProyect;
	}
	public void setIdProyect(int idProyect) {
		this.idProyect = idProyect;
	}
	public int getIdProyectType() {
		return idProyectType;
	}
	public void setIdProyectType(int idProyectType) {
		this.idProyectType = idProyectType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIdPort() {
		return idPort;
	}
	public void setIdPort(int idPort) {
		this.idPort = idPort;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public BigDecimal getTotalInvestment() {
		return totalInvestment;
	}
	public void setTotalInvestment(BigDecimal totalInvestment) {
		this.totalInvestment = totalInvestment;
	}
	public BigDecimal getExercisedAmount() {
		return exercisedAmount;
	}
	public void setExercisedAmount(BigDecimal exercisedAmount) {
		this.exercisedAmount = exercisedAmount;
	}
	public BigDecimal getAsignedAmount() {
		return asignedAmount;
	}
	public void setAsignedAmount(BigDecimal asignedAmount) {
		this.asignedAmount = asignedAmount;
	}
	public String getResourceType() {
		return resourceType;
	}
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}
	public BigDecimal getResourcePercent() {
		return resourcePercent;
	}
	public void setResourcePercent(BigDecimal resourcePercent) {
		this.resourcePercent = resourcePercent;
	}
	public String getBuildDescription() {
		return buildDescription;
	}
	public void setBuildDescription(String buildDescription) {
		this.buildDescription = buildDescription;
	}
	public String getAcronym() {
		return acronym;
	}
	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}
	
	
	
	
	
	
}

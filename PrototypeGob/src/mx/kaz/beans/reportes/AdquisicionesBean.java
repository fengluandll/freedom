/**
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 */
package mx.kaz.beans.reportes;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 */
public class AdquisicionesBean implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private int idBuilds;
    private int idPort;
    private String build_description;
    private int idTypeProcedure;
    private java.sql.Date fecContractIni;
    private java.sql.Date fecContract_fin;
    private BigDecimal amountHire;
    private BigDecimal contractedAmount;
    private String providerName;
    private String resourceType;
    private int idArticulo;
    private int idFraccion;
    private java.sql.Date fecCompranet;
    private java.sql.Date fecClarificationMeeting;
    private java.sql.Date fecPresentationsTender;
    private java.sql.Date feFail;
    private String razonSocial;
    private String segment1;
    
	public int getIdBuilds() {
		return idBuilds;
	}
	public void setIdBuilds(int idBuilds) {
		this.idBuilds = idBuilds;
	}
	public int getIdPort() {
		return idPort;
	}
	public void setIdPort(int idPort) {
		this.idPort = idPort;
	}
	public String getBuild_description() {
		return build_description;
	}
	public void setBuild_description(String build_description) {
		this.build_description = build_description;
	}
	public int getIdTypeProcedure() {
		return idTypeProcedure;
	}
	public void setIdTypeProcedure(int idTypeProcedure) {
		this.idTypeProcedure = idTypeProcedure;
	}
	public java.sql.Date getFecContractIni() {
		return fecContractIni;
	}
	public void setFecContractIni(java.sql.Date fecContractIni) {
		this.fecContractIni = fecContractIni;
	}
	public java.sql.Date getFecContract_fin() {
		return fecContract_fin;
	}
	public void setFecContract_fin(java.sql.Date fecContract_fin) {
		this.fecContract_fin = fecContract_fin;
	}
	public BigDecimal getAmountHire() {
		return amountHire;
	}
	public void setAmountHire(BigDecimal amountHire) {
		this.amountHire = amountHire;
	}
	public BigDecimal getContractedAmount() {
		return contractedAmount;
	}
	public void setContractedAmount(BigDecimal contractedAmount) {
		this.contractedAmount = contractedAmount;
	}
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	public String getResourceType() {
		return resourceType;
	}
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}
	public int getIdArticulo() {
		return idArticulo;
	}
	public void setIdArticulo(int idArticulo) {
		this.idArticulo = idArticulo;
	}
	public int getIdFraccion() {
		return idFraccion;
	}
	public void setIdFraccion(int idFraccion) {
		this.idFraccion = idFraccion;
	}
	public java.sql.Date getFecCompranet() {
		return fecCompranet;
	}
	public void setFecCompranet(java.sql.Date fecCompranet) {
		this.fecCompranet = fecCompranet;
	}
	public java.sql.Date getFecClarificationMeeting() {
		return fecClarificationMeeting;
	}
	public void setFecClarificationMeeting(java.sql.Date fecClarificationMeeting) {
		this.fecClarificationMeeting = fecClarificationMeeting;
	}
	public java.sql.Date getFecPresentationsTender() {
		return fecPresentationsTender;
	}
	public void setFecPresentationsTender(java.sql.Date fecPresentationsTender) {
		this.fecPresentationsTender = fecPresentationsTender;
	}
	public java.sql.Date getFeFail() {
		return feFail;
	}
	public void setFeFail(java.sql.Date feFail) {
		this.feFail = feFail;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public String getSegment1() {
		return segment1;
	}
	public void setSegment1(String segment1) {
		this.segment1 = segment1;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    

}

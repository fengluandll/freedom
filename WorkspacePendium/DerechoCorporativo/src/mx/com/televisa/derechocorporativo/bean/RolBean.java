/**
* Project: Derecho Corporativo
*
* File: RolBean.java
*
* Created on: Agosto 31, 2015 at 12:00
*
* Copyright (c) - Televisa - 2015
*/

package mx.com.televisa.derechocorporativo.bean;

import java.io.Serializable;

/**
 * Mapeo de Datos
 *
 * @author KAZ - CONSULTING/
 *         Iván Castañeda Loeza
 *         Jesús Argumedo
 *         Eduardo Nava
 *         Ernesto Corona Mendoza
 *
 *
 *
 * @version 1.0.0
 *
 * @date Agosto 31, 2015 at 12:00 pm
 *
 */
public class RolBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private int idRol;
    private String nomName;
    private String desDescription;
    private int numPasswordExpirationDays;
    private String  revokeEnterprises;
	private String  reportPre;
    private String  reportPer;
    private int 	userModifico;
    
    public String getReportPre() {
		return reportPre;
	}
	public void setReportPre(String reportPre) {
		this.reportPre = reportPre;
	}
	public String getReportPer() {
		return reportPer;
	}
	public void setReportPer(String reportPer) {
		this.reportPer = reportPer;
	}


	public String getRevokeEnterprises() {
		return revokeEnterprises;
	}
	public void setRevokeEnterprises(String revokeEnterprises) {
		this.revokeEnterprises = revokeEnterprises;
	}
	public int getIdRol() {
		return idRol;
	}
	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}
	public String getNomName() {
		return nomName;
	}
	public void setNomName(String nomName) {
		this.nomName = nomName;
	}
	public String getDesDescription() {
		return desDescription;
	}
	public void setDesDescription(String desDescription) {
		this.desDescription = desDescription;
	}
	public int getNumPasswordExpirationDays() {
		return numPasswordExpirationDays;
	}
	public void setNumPasswordExpirationDays(int numPasswordExpirationDays) {
		this.numPasswordExpirationDays = numPasswordExpirationDays;
	}
	/**
	 * @return the userModifico
	 */
	public int getUserModifico() {
		return userModifico;
	}
	/**
	 * @param userModifico the userModifico to set
	 */
	public void setUserModifico(int userModifico) {
		this.userModifico = userModifico;
	}
    
	

}

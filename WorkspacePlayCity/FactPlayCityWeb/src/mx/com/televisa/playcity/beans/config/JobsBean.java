/**
 * Project: Facturación Automatica Aisa
 * 
 * File: ServiceLocatorException.java
 * 
 * Created on: Abril 05, 2008 at 13:29
 * 
 * Copyright (c) - Televisa- 2019
 */
package mx.com.televisa.playcity.beans.config;

import java.io.Serializable;

/*
* Facturación Automatica Aisa
*
* @author Kaz-Consulting / Argumedo
*
* Bean de mapeo para la tabla de aisa_jobs_tab
*
* @version 1.0.0
*
* @date Abril 05, 2019, 13:29 pm
*/

public class JobsBean implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private int 		piIdJob;
	private String 		psNomJob;
	private int         piIdCasino;
	private String      psNomTrigger;
	private String      psHora;
	private String      psMinuto;
	private String      psCodSemana;
	private String      psNomCasino;
	private String      psTiempo;
	
	
	public int getPiIdJob() {
		return piIdJob;
	}
	public void setPiIdJob(int piIdJob) {
		this.piIdJob = piIdJob;
	}
	public String getPsNomJob() {
		return psNomJob;
	}
	public void setPsNomJob(String psNomJob) {
		this.psNomJob = psNomJob;
	}
	public int getPiIdCasino() {
		return piIdCasino;
	}
	public void setPiIdCasino(int piIdCasino) {
		this.piIdCasino = piIdCasino;
	}
	public String getPsNomTrigger() {
		return psNomTrigger;
	}
	public void setPsNomTrigger(String psNomTrigger) {
		this.psNomTrigger = psNomTrigger;
	}
	public String getPsHora() {
		return psHora;
	}
	public void setPsHora(String psHora) {
		this.psHora = psHora;
	}
	public String getPsMinuto() {
		return psMinuto;
	}
	public void setPsMinuto(String psMinuto) {
		this.psMinuto = psMinuto;
	}
	public String getPsCodSemana() {
		return psCodSemana;
	}
	public void setPsCodSemana(String psCodSemana) {
		this.psCodSemana = psCodSemana;
	}
	public String getPsNomCasino() {
		return psNomCasino;
	}
	public void setPsNomCasino(String psNomCasino) {
		this.psNomCasino = psNomCasino;
	}
	public String getPsTiempo() {
		return psTiempo;
	}
	public void setPsTiempo(String psTiempo) {
		this.psTiempo = psTiempo;
	}

	
	

}

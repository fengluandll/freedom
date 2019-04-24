/**
 * Project: Derecho Corporativo
 *
 * File: ConstanciaActasBean.java
 *
 * Created on: Agosto 31, 2016 at 12:00pm
 *
 * Copyright (c) - Televisa - 2015
 */
package mx.com.televisa.derechocorporativo.bean;


/**
 * JavaBean para el reporte de Constancia de Actas
 *
 * @author KAZ - CONSULTING/
 *         Iv�n Casta�eda Loeza
 *         Jes�s Argumedo
 *         Eduardo Nava
 *         Ernesto Corona Mendoza
 *
 *
 *
 * @version 1.0.0
 *
 * @date Agosto 31, 2016 at 12:00 pm
 *
 */
public class ConstanciaActasBean {

	private String psFecha;
	private String psAsamblea;
	private String psTipoEscritura;

	/**
	 * Dame Fecha
	 * @return
	 */
	public String getPsFecha() {
		return psFecha;
	}
	/**
	 * asigna Fecha
	 * @param psFecha
	 */
	public void setPsFecha(String psFecha) {
		this.psFecha = psFecha;
	}
	
	/**
	 * Obtiene asamblea
	 * @return
	 */
	public String getPsAsamblea() {
		return psAsamblea;
	}
	/**
	 * Asigna Asamblea
	 * @param psAsamblea
	 */
	public void setPsAsamblea(String psAsamblea) {
		this.psAsamblea = psAsamblea;
	}
	
	/**
	 * Obtiene tipo de escritura de poder
	 * @return
	 */
	public String getPsTipoEscritura() {
		return psTipoEscritura;
	}
	
	/**
	 * Asigna tipo de escritura de poder
	 * @param psTipoEscritura
	 */
	public void setPsTipoEscritura(String psTipoEscritura) {
		this.psTipoEscritura = psTipoEscritura;
	}
		

}
/**
* Project: Derecho Corporativo
*
* File: ReferenciaDocumentumBean.java
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
public class ReferenciaDocumentumBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String Memo;
	private String DocEnt;
	private String Otros;
	
	private String ActaAsamblea;
	private String Convocatoria;
	
	private String Acta;
	
	private String InformeComisario;
	private String DictamenEdosFin;
	private String DictamenFiscal;
	private String Constancia;

	private String Solicitud;
	private String ActaResoluciones;
	private String Publicaciones;

	private String ContEnt;

	/**
	 * @return the memo
	 */
	public String getMemo() {
		return Memo;
	}

	/**
	 * @param memo the memo to set
	 */
	public void setMemo(String memo) {
		Memo = memo;
	}

	/**
	 * @return the docEnt
	 */
	public String getDocEnt() {
		return DocEnt;
	}

	/**
	 * @param docEnt the docEnt to set
	 */
	public void setDocEnt(String docEnt) {
		DocEnt = docEnt;
	}

	/**
	 * @return the otros
	 */
	public String getOtros() {
		return Otros;
	}

	/**
	 * @param otros the otros to set
	 */
	public void setOtros(String otros) {
		Otros = otros;
	}

	/**
	 * @return the actaAsamblea
	 */
	public String getActaAsamblea() {
		return ActaAsamblea;
	}

	/**
	 * @param actaAsamblea the actaAsamblea to set
	 */
	public void setActaAsamblea(String actaAsamblea) {
		ActaAsamblea = actaAsamblea;
	}

	/**
	 * @return the convocatoria
	 */
	public String getConvocatoria() {
		return Convocatoria;
	}

	/**
	 * @param convocatoria the convocatoria to set
	 */
	public void setConvocatoria(String convocatoria) {
		Convocatoria = convocatoria;
	}

	/**
	 * @return the acta
	 */
	public String getActa() {
		return Acta;
	}

	/**
	 * @param acta the acta to set
	 */
	public void setActa(String acta) {
		Acta = acta;
	}

	/**
	 * @return the informeComisario
	 */
	public String getInformeComisario() {
		return InformeComisario;
	}

	/**
	 * @param informeComisario the informeComisario to set
	 */
	public void setInformeComisario(String informeComisario) {
		InformeComisario = informeComisario;
	}

	/**
	 * @return the dictamenEdosFin
	 */
	public String getDictamenEdosFin() {
		return DictamenEdosFin;
	}

	/**
	 * @param dictamenEdosFin the dictamenEdosFin to set
	 */
	public void setDictamenEdosFin(String dictamenEdosFin) {
		DictamenEdosFin = dictamenEdosFin;
	}

	/**
	 * @return the dictamenFiscal
	 */
	public String getDictamenFiscal() {
		return DictamenFiscal;
	}

	/**
	 * @param dictamenFiscal the dictamenFiscal to set
	 */
	public void setDictamenFiscal(String dictamenFiscal) {
		DictamenFiscal = dictamenFiscal;
	}

	/**
	 * @return the constancia
	 */
	public String getConstancia() {
		return Constancia;
	}

	/**
	 * @param constancia the constancia to set
	 */
	public void setConstancia(String constancia) {
		Constancia = constancia;
	}

	/**
	 * @return the solicitud
	 */
	public String getSolicitud() {
		return Solicitud;
	}

	/**
	 * @param solicitud the solicitud to set
	 */
	public void setSolicitud(String solicitud) {
		Solicitud = solicitud;
	}

	/**
	 * @return the actaResoluciones
	 */
	public String getActaResoluciones() {
		return ActaResoluciones;
	}

	/**
	 * @param actaResoluciones the actaResoluciones to set
	 */
	public void setActaResoluciones(String actaResoluciones) {
		ActaResoluciones = actaResoluciones;
	}

	/**
	 * @return the publicaciones
	 */
	public String getPublicaciones() {
		return Publicaciones;
	}

	/**
	 * @param publicaciones the publicaciones to set
	 */
	public void setPublicaciones(String publicaciones) {
		Publicaciones = publicaciones;
	}

	/**
	 * @return the contEnt
	 */
	public String getContEnt() {
		return ContEnt;
	}

	/**
	 * @param contEnt the contEnt to set
	 */
	public void setContEnt(String contEnt) {
		ContEnt = contEnt;
	}

	
}
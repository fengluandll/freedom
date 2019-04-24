/**
* Project: Derecho Corporativo
*
* File: PaisBean.java
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
public class PestaniaBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private int idSeccion;
	private  String nomSeccion;
	private int idRol;
	
	/**
	 * Obtiene IdSeccion
	 * @return idSeccion
	 */
	public int getIdSeccion() {
		return idSeccion;
	}
	
	/**
	 * Asigna IdSeccion
	 * @param idSeccion
	 */
	public void setIdSeccion(int idSeccion) {
		this.idSeccion = idSeccion;
	}
	
	/**
	 * Obtiene Nombre de Seccion
	 * @return
	 */
	public String getNomSeccion() {
		return nomSeccion;
	}
	
	/**
	 * Asigna Nombre seccion
	 * @param nomSeccion
	 */
	public void setNomSeccion(String nomSeccion) {
		this.nomSeccion = nomSeccion;
	}
	
	/**
	 * Obtiene Idrol
	 * @return
	 */
	public int getIdRol() {
		return idRol;
	}
	
	/**
	 * Asigna IdRol
	 * @param idRol
	 */
	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}
	

	

}

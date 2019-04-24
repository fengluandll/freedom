/**
* Project: Derecho Corporativo
*
* File: GlosarioBean.java
*
* Created on: Junio 16, 2016 at 12:00
*
* Copyright (c) - Televisa - 2015
*/
package mx.com.televisa.derechocorporativo.bean;

import java.io.InputStream;
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
 * @date Junio 16, 2016 at 12:00 pm
 *
 */
public class GlosarioBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String nomArchivo;
	private InputStream archivo;

	
	/**
	 * 
	 * @param nomArchivo nombre del archivo
	 * @param archivo   InutStream
	 */
	public GlosarioBean(String nomArchivo, InputStream archivo) {
		super();
		this.nomArchivo = nomArchivo;
		this.archivo = archivo;
	}

	/**
	 * Obtine Nombre de archivo
	 * @return
	 */
	public String getNomArchivo() {
		return nomArchivo;
	}
	
	/**
	 * Obtiene el archivo
	 * @return
	 */
	public InputStream getArchivo() {
		return archivo;
	}
		

}

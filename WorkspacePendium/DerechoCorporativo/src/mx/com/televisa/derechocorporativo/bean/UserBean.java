/**
* Project: Derecho Corporativo
*
* File: UserBean.java
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
public class UserBean implements Serializable{

	private int    idUser;
	private String nomUserLongName;
	private String numEmpleado;
	private String nomUsername;
	private String cvePassword;
	private int    idStatus;
	private String status;
	private int idRol;
	private int usuarioModifico;
	
	
	
	public int getIdRol() {
		return idRol;
	}
	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public String getNomUserLongName() {
		return nomUserLongName;
	}
	public void setNomUserLongName(String nomUserLongName) {
		this.nomUserLongName = nomUserLongName;
	}
	public String getNomUsername() {
		return nomUsername;
	}
	public void setNomUsername(String nomUsername) {
		this.nomUsername = nomUsername;
	}
	public String getCvePassword() {
		return cvePassword;
	}
	public void setCvePassword(String cvePassword) {
		this.cvePassword = cvePassword;
	}
	public int getIdStatus() {
		return idStatus;
	}
	public void setIdStatus(int idStatus) {
		this.idStatus = idStatus;
	}
	/**
	 * @return the usuarioModifico
	 */
	public int getUsuarioModifico() {
		return usuarioModifico;
	}
	/**
	 * @param usuarioModifico the usuarioModifico to set
	 */
	public void setUsuarioModifico(int usuarioModifico) {
		this.usuarioModifico = usuarioModifico;
	}
	public String getNumEmpleado() {
		return numEmpleado;
	}
	public void setNumEmpleado(String numEmpleado) {
		this.numEmpleado = numEmpleado;
	}
	
	
}

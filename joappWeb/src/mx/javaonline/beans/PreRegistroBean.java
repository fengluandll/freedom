/**
 * @Jose de Jesus Argumedo Quiroz
 */
package mx.javaonline.beans;

import java.io.Serializable;

public class PreRegistroBean implements Serializable{


	private static final long serialVersionUID = 3155408243474431754L;
	private int idPreegistro;
	private String nombres;
	private String apellidos;
	private java.sql.Date fechaNacimiento;
	private String correoPrincipal;
	private String status;
	private java.sql.Date creationDate;
	
	public PreRegistroBean() {
		
	}

	public int getIdPreegistro() {
		return idPreegistro;
	}

	public void setIdPreegistro(int idPreegistro) {
		this.idPreegistro = idPreegistro;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public java.sql.Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(java.sql.Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getCorreoPrincipal() {
		return correoPrincipal;
	}

	public void setCorreoPrincipal(String correoPrincipal) {
		this.correoPrincipal = correoPrincipal;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public java.sql.Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(java.sql.Date creationDate) {
		this.creationDate = creationDate;
	}
	
	

}

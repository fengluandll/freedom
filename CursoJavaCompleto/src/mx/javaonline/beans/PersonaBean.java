/**
 * @autor startOnline
 */
package mx.javaonline.beans;

import java.io.Serializable;

public class PersonaBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private String nombre;
	private String apellidos;
	private int edad;
	private String edoCivil;
	
	public PersonaBean() {
	
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getEdoCivil() {
		return edoCivil;
	}

	public void setEdoCivil(String edoCivil) {
		this.edoCivil = edoCivil;
	}
	
	

}

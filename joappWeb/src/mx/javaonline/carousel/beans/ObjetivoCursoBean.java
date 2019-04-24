/**
 * @Jose de Jesus Argumedo Quiroz
 */
package mx.javaonline.carousel.beans;

import java.io.Serializable;

public class ObjetivoCursoBean implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private int idObjetivo;
	private int courseId;
	private String descripcion;
	
	public ObjetivoCursoBean() {
		
	}

	public int getIdObjetivo() {
		return idObjetivo;
	}

	public void setIdObjetivo(int idObjetivo) {
		this.idObjetivo = idObjetivo;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	

}

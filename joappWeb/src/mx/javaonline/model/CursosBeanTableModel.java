package mx.javaonline.model;

import java.io.Serializable;

public class CursosBeanTableModel implements Serializable{


	private static final long serialVersionUID = 1L;
	private int id_curso;
	private String nombre_curso;
	private java.sql.Date fecha_creacion;

	public CursosBeanTableModel() {
		
	}

	public int getId_curso() {
		return id_curso;
	}

	public void setId_curso(int id_curso) {
		this.id_curso = id_curso;
	}

	public String getNombre_curso() {
		return nombre_curso;
	}

	public void setNombre_curso(String nombre_curso) {
		this.nombre_curso = nombre_curso;
	}

	public java.sql.Date getFecha_creacion() {
		return fecha_creacion;
	}

	public void setFecha_creacion(java.sql.Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}


	
}

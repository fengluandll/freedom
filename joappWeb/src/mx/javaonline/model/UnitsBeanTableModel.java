package mx.javaonline.model;

import java.io.Serializable;

public class UnitsBeanTableModel implements Serializable{


	private static final long serialVersionUID = 1L;
	private int unidad_id;
	private String nombre_unidad;
	
	public UnitsBeanTableModel() {
	}

	public int getUnidad_id() {
		return unidad_id;
	}

	public void setUnidad_id(int unidad_id) {
		this.unidad_id = unidad_id;
	}

	public String getNombre_unidad() {
		return nombre_unidad;
	}

	public void setNombre_unidad(String nombre_unidad) {
		this.nombre_unidad = nombre_unidad;
	}

	
	
}

package mx.javaonline.model;

import java.io.Serializable;

public class TopicBeanTableModel implements Serializable{


	private static final long serialVersionUID = 1L;
	private int id_topic;
	private String nombre_topic;
	
	public TopicBeanTableModel() {

	}

	public int getId_topic() {
		return id_topic;
	}

	public void setId_topic(int id_topic) {
		this.id_topic = id_topic;
	}

	public String getNombre_topic() {
		return nombre_topic;
	}

	public void setNombre_topic(String nombre_topic) {
		this.nombre_topic = nombre_topic;
	}

	

}

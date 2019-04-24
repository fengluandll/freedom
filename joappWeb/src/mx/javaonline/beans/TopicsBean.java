package mx.javaonline.beans;

import java.io.Serializable;
import java.sql.Blob;

public class TopicsBean implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private int topicId;
	private String topicName;
	private String link;
	private int unitId;
	private String segment1;
	private String segment2;
	private String segment3;
	private String segment4;
	private String segment5;
	private int segment6;
	private java.sql.Date segment7;
	private Blob segment8;
	private int contentTopicId;
	private String linkVideo;
	private Blob lamina;
	private Blob practica;
	private Blob solucion;
	private String segment1_1;
	private int evaluacion_tab_id;
	private String linkEvaluacion;
	private String duracion;
	
	
	public TopicsBean() {
	}

	
	public int getUnitId() {
		return unitId;
	}


	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}


	public int getTopicId() {
		return topicId;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}


	public String getSegment1() {
		return segment1;
	}


	public void setSegment1(String segment1) {
		this.segment1 = segment1;
	}


	public String getSegment2() {
		return segment2;
	}


	public void setSegment2(String segment2) {
		this.segment2 = segment2;
	}


	public String getSegment3() {
		return segment3;
	}


	public void setSegment3(String segment3) {
		this.segment3 = segment3;
	}


	public String getSegment4() {
		return segment4;
	}


	public void setSegment4(String segment4) {
		this.segment4 = segment4;
	}


	public String getSegment5() {
		return segment5;
	}


	public void setSegment5(String segment5) {
		this.segment5 = segment5;
	}


	public int getSegment6() {
		return segment6;
	}


	public void setSegment6(int segment6) {
		this.segment6 = segment6;
	}


	public java.sql.Date getSegment7() {
		return segment7;
	}


	public void setSegment7(java.sql.Date segment7) {
		this.segment7 = segment7;
	}


	public Blob getSegment8() {
		return segment8;
	}


	public void setSegment8(Blob segment8) {
		this.segment8 = segment8;
	}


	public int getContentTopicId() {
		return contentTopicId;
	}


	public void setContentTopicId(int contentTopicId) {
		this.contentTopicId = contentTopicId;
	}


	public String getLinkVideo() {
		return linkVideo;
	}


	public void setLinkVideo(String linkVideo) {
		this.linkVideo = linkVideo;
	}


	public Blob getLamina() {
		return lamina;
	}


	public void setLamina(Blob lamina) {
		this.lamina = lamina;
	}


	public Blob getPractica() {
		return practica;
	}


	public void setPractica(Blob practica) {
		this.practica = practica;
	}


	public Blob getSolucion() {
		return solucion;
	}


	public void setSolucion(Blob solucion) {
		this.solucion = solucion;
	}


	public String getSegment1_1() {
		return segment1_1;
	}


	public void setSegment1_1(String segment1_1) {
		this.segment1_1 = segment1_1;
	}


	public int getEvaluacion_tab_id() {
		return evaluacion_tab_id;
	}


	public void setEvaluacion_tab_id(int evaluacion_tab_id) {
		this.evaluacion_tab_id = evaluacion_tab_id;
	}


	public String getLinkEvaluacion() {
		return linkEvaluacion;
	}


	public void setLinkEvaluacion(String linkEvaluacion) {
		this.linkEvaluacion = linkEvaluacion;
	}


	public String getDuracion() {
		return duracion;
	}


	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}
	
	

}

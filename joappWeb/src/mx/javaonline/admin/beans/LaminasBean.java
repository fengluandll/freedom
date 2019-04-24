/**
 * @Jose de Jesus Argumedo Quiroz
 */
package mx.javaonline.admin.beans;

import java.io.Serializable;
import java.sql.Blob;

public class LaminasBean implements Serializable{

	

	private static final long serialVersionUID = 1L;
	private int id_laminas;
    private int topic_id;
    private String nom_lamina;
    private Blob lamina;
    private String segment1;
    private String segment2;
    private String segment3;
    private String segment4;
    private String segment5;
    private int segment6;
    private java.sql.Date segment7;
    private Blob segment8;
    private java.sql.Date creation_date;
    private java.sql.Date last_update_date;

	public LaminasBean() {
	
	}

	public int getId_laminas() {
		return id_laminas;
	}

	public void setId_laminas(int id_laminas) {
		this.id_laminas = id_laminas;
	}

	public int getTopic_id() {
		return topic_id;
	}

	public void setTopic_id(int topic_id) {
		this.topic_id = topic_id;
	}

	public String getNom_lamina() {
		return nom_lamina;
	}

	public void setNom_lamina(String nom_lamina) {
		this.nom_lamina = nom_lamina;
	}

	public Blob getLamina() {
		return lamina;
	}

	public void setLamina(Blob lamina) {
		this.lamina = lamina;
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

	public java.sql.Date getCreation_date() {
		return creation_date;
	}

	public void setCreation_date(java.sql.Date creation_date) {
		this.creation_date = creation_date;
	}

	public java.sql.Date getLast_update_date() {
		return last_update_date;
	}

	public void setLast_update_date(java.sql.Date last_update_date) {
		this.last_update_date = last_update_date;
	}
	
	

}

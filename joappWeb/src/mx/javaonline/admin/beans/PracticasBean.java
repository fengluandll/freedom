/**
 * @Jose de Jesus Argumedo Quiroz
 */
package mx.javaonline.admin.beans;

import java.io.Serializable;
import java.sql.Blob;

public class PracticasBean implements Serializable{


	private static final long serialVersionUID = 1L;
	private int idPracticas;
	private int id_topic;
    private String nomPractica;
    private String nomArchivo;
    private String nomVideo;
    private String link_video;
    private Blob lamina;
    private Blob practicaPdf;
    private String practicaVideo;
    private int idContentType;
    private String tipoContendio;
    private String segment1;
    private String segment2;
    private String segment3;
    private String segment4;
    private String segment5;
    private int segment6;
    private java.sql.Date segment7;
    private Blob segment8;
    private java.sql.Date creationDate;
    private java.sql.Date lastUpdateDate;
    
	public PracticasBean() {

	}

	public int getIdPracticas() {
		return idPracticas;
	}

	public void setIdPracticas(int idPracticas) {
		this.idPracticas = idPracticas;
	}

	public int getId_topic() {
		return id_topic;
	}

	public void setId_topic(int id_topic) {
		this.id_topic = id_topic;
	}

	public String getNomPractica() {
		return nomPractica;
	}

	public void setNomPractica(String nomPractica) {
		this.nomPractica = nomPractica;
	}

	public String getNomArchivo() {
		return nomArchivo;
	}

	public void setNomArchivo(String nomArchivo) {
		this.nomArchivo = nomArchivo;
	}

	public String getNomVideo() {
		return nomVideo;
	}

	public void setNomVideo(String nomVideo) {
		this.nomVideo = nomVideo;
	}

	public String getLink_video() {
		return link_video;
	}

	public void setLink_video(String link_video) {
		this.link_video = link_video;
	}

	public Blob getLamina() {
		return lamina;
	}

	public void setLamina(Blob lamina) {
		this.lamina = lamina;
	}

	public Blob getPracticaPdf() {
		return practicaPdf;
	}

	public void setPracticaPdf(Blob practicaPdf) {
		this.practicaPdf = practicaPdf;
	}

	public String getPracticaVideo() {
		return practicaVideo;
	}

	public void setPracticaVideo(String practicaVideo) {
		this.practicaVideo = practicaVideo;
	}

	public int getIdContentType() {
		return idContentType;
	}

	public void setIdContentType(int idContentType) {
		this.idContentType = idContentType;
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

	public java.sql.Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(java.sql.Date creationDate) {
		this.creationDate = creationDate;
	}

	public java.sql.Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(java.sql.Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getTipoContendio() {
		return tipoContendio;
	}

	public void setTipoContendio(String tipoContendio) {
		this.tipoContendio = tipoContendio;
	}

	
}

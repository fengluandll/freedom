package mx.javaonline.dasboard.beans;

import java.io.Serializable;

public class CursoUnidadBean implements Serializable{

	
private static final long serialVersionUID = 1L;

private int studentPersonalId;
private int courseId;
private String courseName;
private String ligaCurso;
private int unitId;
private String unitName;
private String segment1;
private String segment2;
private String segment3;
private String ligaUnidad;
private int    idOrder;

	public CursoUnidadBean(){
		
	}

	public int getStudentPersonalId() {
		return studentPersonalId;
	}

	public void setStudentPersonalId(int studentPersonalId) {
		this.studentPersonalId = studentPersonalId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getLigaCurso() {
		return ligaCurso;
	}

	public void setLigaCurso(String ligaCurso) {
		this.ligaCurso = ligaCurso;
	}

	public int getUnitId() {
		return unitId;
	}

	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
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

	public String getLigaUnidad() {
		return ligaUnidad;
	}

	public void setLigaUnidad(String ligaUnidad) {
		this.ligaUnidad = ligaUnidad;
	}

	/**
	 * @return the idOrder
	 */
	public int getIdOrder() {
		return idOrder;
	}

	/**
	 * @param idOrder the idOrder to set
	 */
	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	
	
}

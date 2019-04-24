package mx.javaonline.beans;

import java.io.Serializable;

public class CoursesBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int studentlCoursesId;
	private int studentId;
	private int courseId;
	private String courseName;
	private String link;
	private java.sql.Date creationDate;
	private String descripcion;
	private String duracion;
	private String inversionTotal;
	private String profesor;
	private String img;
	private String segment2;
	private String segment3;
	private String segment4;
	private String segment5;
	private String segment6;
	private String segment7;
	private String segment8;
	private int 	rownum;
	

	public CoursesBean() {
	
	}

	
	public java.sql.Date getCreationDate() {
		return creationDate;
	}


	public void setCreationDate(java.sql.Date creationDate) {
		this.creationDate = creationDate;
	}


	public int getStudentlCoursesId() {
		return studentlCoursesId;
	}

	public void setStudentlCoursesId(int studentlCoursesId) {
		this.studentlCoursesId = studentlCoursesId;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
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

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	/**
	 * @return the segment2
	 */
	public String getSegment2() {
		return segment2;
	}


	/**
	 * @param segment2 the segment2 to set
	 */
	public void setSegment2(String segment2) {
		this.segment2 = segment2;
	}


	/**
	 * @return the segment3
	 */
	public String getSegment3() {
		return segment3;
	}


	/**
	 * @param segment3 the segment3 to set
	 */
	public void setSegment3(String segment3) {
		this.segment3 = segment3;
	}


	/**
	 * @return the segment4
	 */
	public String getSegment4() {
		return segment4;
	}


	/**
	 * @param segment4 the segment4 to set
	 */
	public void setSegment4(String segment4) {
		this.segment4 = segment4;
	}


	/**
	 * @return the segment5
	 */
	public String getSegment5() {
		return segment5;
	}


	/**
	 * @param segment5 the segment5 to set
	 */
	public void setSegment5(String segment5) {
		this.segment5 = segment5;
	}


	/**
	 * @return the segment6
	 */
	public String getSegment6() {
		return segment6;
	}


	/**
	 * @param segment6 the segment6 to set
	 */
	public void setSegment6(String segment6) {
		this.segment6 = segment6;
	}


	/**
	 * @return the segment7
	 */
	public String getSegment7() {
		return segment7;
	}


	/**
	 * @param segment7 the segment7 to set
	 */
	public void setSegment7(String segment7) {
		this.segment7 = segment7;
	}


	/**
	 * @return the segment8
	 */
	public String getSegment8() {
		return segment8;
	}


	/**
	 * @param segment8 the segment8 to set
	 */
	public void setSegment8(String segment8) {
		this.segment8 = segment8;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public String getDuracion() {
		return duracion;
	}


	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}


	public String getInversionTotal() {
		return inversionTotal;
	}


	public void setInversionTotal(String inversionTotal) {
		this.inversionTotal = inversionTotal;
	}


	public String getProfesor() {
		return profesor;
	}


	public void setProfesor(String profesor) {
		this.profesor = profesor;
	}


	public String getImg() {
		return img;
	}


	public void setImg(String img) {
		this.img = img;
	}


	public int getRownum() {
		return rownum;
	}


	public void setRownum(int rownum) {
		this.rownum = rownum;
	}
	
	

}

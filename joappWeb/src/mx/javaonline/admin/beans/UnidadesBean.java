package mx.javaonline.admin.beans;

import java.io.Serializable;
import java.sql.Blob;

public class UnidadesBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int unitId;
    private String unitName;
    private String link;
    private int courseId;
    private String segment1;
    private String segment2;
    private String segment3;
    private String segment4;
    private String segment5;
    private int segment6;
    private java.sql.Date segment7;
    private Blob segment8;
    private java.sql.Date creation_date;
    private java.sql.Date end_date;
    private String courseName;
	
    public UnidadesBean() {

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

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
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

	public java.sql.Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(java.sql.Date end_date) {
		this.end_date = end_date;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
    
    

}

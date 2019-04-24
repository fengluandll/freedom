package mx.javaonline.admin.beans;

import java.io.Serializable;
import java.sql.Blob;

public class TopicsBean implements Serializable{

	/**
	 * 
	 */
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
	private java.sql.Date creationDate;
	private java.sql.Date endDate;
    private String unitName;
    
	public TopicsBean() {

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

	public int getUnitId() {
		return unitId;
	}

	public void setUnitId(int unitId) {
		this.unitId = unitId;
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

	public java.sql.Date getEndDate() {
		return endDate;
	}

	public void setEndDate(java.sql.Date endDate) {
		this.endDate = endDate;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	
	

}

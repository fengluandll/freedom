/**
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 */
package mx.kaz.beans;

import java.io.Serializable;
import java.sql.Blob;

/**
 * @author Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 */
public class PersonalBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3670582760481850669L;
	private int personalId;
	private String givenNames;
	private String surNames;
	private int fromLoginId;
	private int rolId;
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
	
	public int getPersonalId() {
		return personalId;
	}
	public void setPersonalId(int personalId) {
		this.personalId = personalId;
	}
	public String getGivenNames() {
		return givenNames;
	}
	public void setGivenNames(String givenNames) {
		this.givenNames = givenNames;
	}
	public String getSurNames() {
		return surNames;
	}
	public void setSurNames(String surNames) {
		this.surNames = surNames;
	}
	public int getFromLoginId() {
		return fromLoginId;
	}
	public void setFromLoginId(int fromLoginId) {
		this.fromLoginId = fromLoginId;
	}
	public int getRolId() {
		return rolId;
	}
	public void setRolId(int rolId) {
		this.rolId = rolId;
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
	
	
	

}

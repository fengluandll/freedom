package sysadmin.app.model;

import java.sql.Date;

public class FlexvaluesBean {
	private int flex_value_id; 
	private String  flex_code; 
	private String  flex_type; 
	private String  name; 
	private String  description; 
	private String  enabled_flag; 
	private Date  start_date; 
	private Date  end_date; 
	private String  segment1; 
	private String  segment2; 
	private String  segment3; 
	private String  segment4; 
	private String  segment5; 
	private String  segment6; 
	private String  segment7; 
	private String  segment8; 
	private String  segment9; ;
	public FlexvaluesBean(int flex_value_id, String flex_code, String flex_type,
			String name, String description, String enabled_flag,
			Date start_date, Date end_date, String segment1, String segment2,
			String segment3, String segment4, String segment5, String segment6,
			String segment7, String segment8, String segment9) {
		// TODO Auto-generated constructor stub
		super();
		this.flex_value_id = flex_value_id;
		this.flex_code = flex_code;
		this.flex_type = flex_type;
		this.name = name;
		this.description = description;
		this.enabled_flag = enabled_flag;
		this.start_date = start_date;
		this.end_date = end_date;
		this.segment1 = segment1;
		this.segment2 = segment2;
		this.segment3 = segment3;
		this.segment4 = segment4;
		this.segment5 = segment5;
		this.segment6 = segment6;
		this.segment7 = segment7;
		this.segment8 = segment8;
		this.segment9 = segment9;;
	}
	public int getFlex_value_id() {
		return flex_value_id;
	}
	public void setFlex_value_id(int flex_value_id) {
		this.flex_value_id = flex_value_id;
	}
	public String getFlex_code() {
		return flex_code;
	}
	public void setFlex_code(String flex_code) {
		this.flex_code = flex_code;
	}
	public String getFlex_type() {
		return flex_type;
	}
	public void setFlex_type(String flex_type) {
		this.flex_type = flex_type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEnabled_flag() {
		return enabled_flag;
	}
	public void setEnabled_flag(String enabled_flag) {
		this.enabled_flag = enabled_flag;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
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
	public String getSegment6() {
		return segment6;
	}
	public void setSegment6(String segment6) {
		this.segment6 = segment6;
	}
	public String getSegment7() {
		return segment7;
	}
	public void setSegment7(String segment7) {
		this.segment7 = segment7;
	}
	public String getSegment8() {
		return segment8;
	}
	public void setSegment8(String segment8) {
		this.segment8 = segment8;
	}
	public String getSegment9() {
		return segment9;
	}
	public void setSegment9(String segment9) {
		this.segment9 = segment9;
	}
	
}

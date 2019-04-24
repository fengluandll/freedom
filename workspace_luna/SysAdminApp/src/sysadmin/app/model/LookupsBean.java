package sysadmin.app.model;

import java.sql.Date;

public class LookupsBean {
	private int lookup_id; 
	private String  lookup_code; 
	private String  lookup_type; 
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
	private String  segment9;;
	public LookupsBean(int lookup_id, String lookup_code, String lookup_type,
			String name, String description, String enabled_flag,
			Date start_date, Date end_date, String segment1, String segment2,
			String segment3, String segment4, String segment5, String segment6,
			String segment7, String segment8, String segment9) {
		super();
		this.lookup_id = lookup_id;
		this.lookup_code = lookup_code;
		this.lookup_type = lookup_type;
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
		this.segment9 = segment9;
	}
	public int getLookup_id() {
		return lookup_id;
	}
	public void setLookup_id(int lookup_id) {
		this.lookup_id = lookup_id;
	}
	public String getLookup_code() {
		return lookup_code;
	}
	public void setLookup_code(String lookup_code) {
		this.lookup_code = lookup_code;
	}
	public String getLookup_type() {
		return lookup_type;
	}
	public void setLookup_type(String lookup_type) {
		this.lookup_type = lookup_type;
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

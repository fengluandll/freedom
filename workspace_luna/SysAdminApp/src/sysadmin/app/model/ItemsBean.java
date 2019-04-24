package sysadmin.app.model;

import java.sql.Date;

public class ItemsBean {
	
	private int item_id; 
	private int organization_id;
	private String name; 
	private String description; 
	private String 	type; 
	private String  barcode; 
	private String  enabled_flag; 
	private Date start_date; 
	private Date  end_date; 
	private String segment_context; 
	private String segment1; 
	private String  segment2; 
	private String  segment3; 
	private String  segment4; 
	private String  segment5; 
	private String  segment6; 
	private String  segment7; 
	private String  segment8; 
	private String  segment9;;
	public ItemsBean(int item_id, int organization_id, String name,
			String description, String type, String barcode,
			String enabled_flag, Date start_date, Date end_date,
			String segment_context, String segment1, String segment2,
			String segment3, String segment4, String segment5, String segment6,
			String segment7, String segment8, String segment9) {
		// TODO Auto-generated constructor stub
		super();
		this.item_id = item_id;
		this.organization_id = organization_id;
		this.name = name;
		this.description = description;
		this.type = type;
		this.barcode = barcode;
		this.enabled_flag = enabled_flag;
		this.start_date = start_date;
		this.end_date = end_date;
		this.segment_context = segment_context;
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
	public int getItem_id() {
		return item_id;
	}
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	public int getOrganization_id() {
		return organization_id;
	}
	public void setOrganization_id(int organization_id) {
		this.organization_id = organization_id;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
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
	public String getSegment_context() {
		return segment_context;
	}
	public void setSegment_context(String segment_context) {
		this.segment_context = segment_context;
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

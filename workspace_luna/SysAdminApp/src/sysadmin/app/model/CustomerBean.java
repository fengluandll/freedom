package sysadmin.app.model;

import java.sql.Date;

public class CustomerBean {
	/*
	 * Tabla: Customers
	 * 
	 */
	// Declaracion de campos
    private int customer_id; 
    private String first_name;
    private String last_name;
    private String business_name;
    private String rfc;
    private String address_line1;
    private String address_line2;
    private String address_line3;
    private String city;
    private String state;
    private String country;
    private String postal_code;
    private String segment1;
    private String segment2;
    private String segment3;
    private String segment4;
    private String segment5;
    private String segment6;
    private String segment7;
    private String segment8;
    private String segment9;
    private String email;
    private String area_code_phone;
    private String phone;
    private Date start_date;
    private Date end_date;
    private Date born_date;;
    
    
	public CustomerBean(int customer_id, String first_name, String last_name, String business_name, String rfc,
            String address_line1, String address_line2, String address_line3, String city, String state,
            String country, String postal_code, String segment1, String segment2, String segment3, String segment4,
            String segment5, String segment6, String segment7, String segment8, String segment9, String email,
            String area_code_phone, String phone, Date start_date, Date end_date, Date born_date) {
		// TODO Auto-generated constructor stub
		this.customer_id = customer_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.business_name = business_name;
        this.rfc = rfc;
        this.address_line1 = address_line1;
        this.address_line2 = address_line2;
        this.address_line3 = address_line3;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postal_code = postal_code;
        this.segment1 = segment1;
        this.segment2 = segment2;
        this.segment3 = segment3;
        this.segment4 = segment4;
        this.segment5 = segment5;
        this.segment6 = segment6;
        this.segment7 = segment7;
        this.segment8 = segment8;
        this.segment9 = segment9;
        this.email = email;
        this.area_code_phone = area_code_phone;
        this.phone = phone;
        this.start_date = start_date;
        this.end_date = end_date;
        this.born_date = born_date;;
	}
	
	public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getBusiness_name() {
        return business_name;
    }

    public void setBusiness_name(String business_name) {
        this.business_name = business_name;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getAddress_line1() {
        return address_line1;
    }

    public void setAddress_line1(String address_line1) {
        this.address_line1 = address_line1;
    }

    public String getAddress_line2() {
        return address_line2;
    }

    public void setAddress_line2(String address_line2) {
        this.address_line2 = address_line2;
    }

    public String getAddress_line3() {
        return address_line3;
    }

    public void setAddress_line3(String address_line3) {
        this.address_line3 = address_line3;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getArea_code_phone() {
        return area_code_phone;
    }

    public void setArea_code_phone(String area_code_phone) {
        this.area_code_phone = area_code_phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public Date getBorn_date() {
        return born_date;
    }

    public void setBorn_date(Date born_date) {
        this.born_date = born_date;
    }

}

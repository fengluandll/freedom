package sysadmin.app.model;

import java.sql.Date;

public class UsersBean {
	/*
	 * Tabla: Users
	 * 
	 */
	// Declaracion de campos
    public int     user_id;
    public String  username;
    public String  first_name;
    public String  last_name;
    public int     profile_id;
    public String  password;
    public String  photo;
    public Date    start_date;
    public Date    end_date;
    public String  segmnent1;
    public String  segmnent2;
    public String  segmnent3;
    public String  segmnent4;
    public String  segmnent5;
    public String  segmnent6;
    public String  segmnent7;
    public String  segmnent8;
    public String  segmnent9;
    
    public UsersBean(int user_id, String username, String first_name,
			String last_name, int profile_id, String password, String photo,
			Date start_date, Date end_date, String segmnent1, String segmnent2,
			String segmnent3, String segmnent4, String segmnent5,
			String segmnent6, String segmnent7, String segmnent8,
			String segmnent9) {
    	// TODO Auto-generated constructor stub
		//super();
		this.user_id = user_id;
		this.username = username;
		this.first_name = first_name;
		this.last_name = last_name;
		this.profile_id = profile_id;
		this.password = password;
		this.photo = photo;
		this.start_date = start_date;
		this.end_date = end_date;
		this.segmnent1 = segmnent1;
		this.segmnent2 = segmnent2;
		this.segmnent3 = segmnent3;
		this.segmnent4 = segmnent4;
		this.segmnent5 = segmnent5;
		this.segmnent6 = segmnent6;
		this.segmnent7 = segmnent7;
		this.segmnent8 = segmnent8;
		this.segmnent9 = segmnent9;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public int getProfile_id() {
		return profile_id;
	}

	public void setProfile_id(int profile_id) {
		this.profile_id = profile_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
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

	public String getSegmnent1() {
		return segmnent1;
	}

	public void setSegmnent1(String segmnent1) {
		this.segmnent1 = segmnent1;
	}

	public String getSegmnent2() {
		return segmnent2;
	}

	public void setSegmnent2(String segmnent2) {
		this.segmnent2 = segmnent2;
	}

	public String getSegmnent3() {
		return segmnent3;
	}

	public void setSegmnent3(String segmnent3) {
		this.segmnent3 = segmnent3;
	}

	public String getSegmnent4() {
		return segmnent4;
	}

	public void setSegmnent4(String segmnent4) {
		this.segmnent4 = segmnent4;
	}

	public String getSegmnent5() {
		return segmnent5;
	}

	public void setSegmnent5(String segmnent5) {
		this.segmnent5 = segmnent5;
	}

	public String getSegmnent6() {
		return segmnent6;
	}

	public void setSegmnent6(String segmnent6) {
		this.segmnent6 = segmnent6;
	}

	public String getSegmnent7() {
		return segmnent7;
	}

	public void setSegmnent7(String segmnent7) {
		this.segmnent7 = segmnent7;
	}

	public String getSegmnent8() {
		return segmnent8;
	}

	public void setSegmnent8(String segmnent8) {
		this.segmnent8 = segmnent8;
	}

	public String getSegmnent9() {
		return segmnent9;
	}

	public void setSegmnent9(String segmnent9) {
		this.segmnent9 = segmnent9;
	}
}

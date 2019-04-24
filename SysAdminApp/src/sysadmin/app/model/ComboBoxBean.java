package sysadmin.app.model;

public class ComboBoxBean {
	public int cmb_id;
	public String cmb_name;
	public String cmb_description;
	public ComboBoxBean(int cmb_id, String cmb_name, String cmb_description) {
		super();
		this.cmb_id = cmb_id;
		this.cmb_name = cmb_name;
		this.cmb_description = cmb_description;
	}
	public int getCmb_id() {
		return cmb_id;
	}
	public void setCmb_id(int cmb_id) {
		this.cmb_id = cmb_id;
	}
	public String getCmb_name() {
		return cmb_name;
	}
	public void setCmb_name(String cmb_name) {
		this.cmb_name = cmb_name;
	}
	public String getCmb_description() {
		return cmb_description;
	}
	public void setCmb_description(String cmb_description) {
		this.cmb_description = cmb_description;
	}
}

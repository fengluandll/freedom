package mx.gob.tsjdf.bean;

public class SessionMenuBean {
	Integer id_menu;
	Integer id_rol;
	Integer id_submenu;
	Integer id_rol_menu;
	String  menu, menu_url, submenu, submenu_url;
	public Integer getId_menu() {
		return id_menu;
	}
	public void setId_menu(Integer id_menu) {
		this.id_menu = id_menu;
	}
	public Integer getId_rol() {
		return id_rol;
	}
	public void setId_rol(Integer id_rol) {
		this.id_rol = id_rol;
	}
	public Integer getId_submenu() {
		return id_submenu;
	}
	public void setId_submenu(Integer id_submenu) {
		this.id_submenu = id_submenu;
	}
	public Integer getId_rol_menu() {
		return id_rol_menu;
	}
	public void setId_rol_menu(Integer id_rol_menu) {
		this.id_rol_menu = id_rol_menu;
	}
	public String getMenu() {
		return menu;
	}
	public void setMenu(String menu) {
		this.menu = menu;
	}
	public String getMenu_url() {
		return menu_url;
	}
	public void setMenu_url(String menu_url) {
		this.menu_url = menu_url;
	}
	public String getSubmenu() {
		return submenu;
	}
	public void setSubmenu(String submenu) {
		this.submenu = submenu;
	}
	public String getSubmenu_url() {
		return submenu_url;
	}
	public void setSubmenu_url(String submenu_url) {
		this.submenu_url = submenu_url;
	}
	public SessionMenuBean() {
		super();
	}
	public SessionMenuBean(Integer id_menu, Integer id_rol, String menu, String menu_url) {
		super();
		this.id_menu = id_menu;
		this.id_rol = id_rol;
		this.menu = menu;
		this.menu_url = menu_url;
	}
	public SessionMenuBean(Integer id_menu, Integer id_rol, Integer id_submenu, String submenu, String submenu_url) {
		super();
		this.id_menu = id_menu;
		this.id_rol = id_rol;
		this.id_submenu = id_submenu;
		this.submenu = submenu;
		this.submenu_url = submenu_url;
	}
}

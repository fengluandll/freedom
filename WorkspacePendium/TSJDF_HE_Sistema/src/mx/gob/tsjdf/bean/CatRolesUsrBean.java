package mx.gob.tsjdf.bean;

public class CatRolesUsrBean {
	Integer id_rol;
	String Descripcion;
	public Integer getId_rol() {
		return id_rol;
	}
	public void setId_rol(Integer id_rol) {
		this.id_rol = id_rol;
	}
	public String getDescripcion() {
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	public CatRolesUsrBean(Integer id_rol, String descripcion) {
		super();
		this.id_rol = id_rol;
		Descripcion = descripcion;
	}
	public CatRolesUsrBean() {
		super();
	}
}

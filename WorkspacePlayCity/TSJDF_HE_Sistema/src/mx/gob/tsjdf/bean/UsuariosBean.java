package mx.gob.tsjdf.bean;

public class UsuariosBean {
	private Integer id_usuario, id_rol;
	private String num_empleado, status, nombre_completo, rol_name,password;
	public Integer getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(Integer id_usuario) {
		this.id_usuario = id_usuario;
	}
	public Integer getId_rol() {
		return id_rol;
	}
	public void setId_rol(Integer id_rol) {
		this.id_rol = id_rol;
	}
	public String getNum_empleado() {
		return num_empleado;
	}
	public void setNum_empleado(String num_empleado) {
		this.num_empleado = num_empleado;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNombre_completo() {
		return nombre_completo;
	}
	public void setNombre_completo(String nombre_completo) {
		this.nombre_completo = nombre_completo;
	}
	public String getRol_name() {
		return rol_name;
	}
	public void setRol_name(String rol_name) {
		this.rol_name = rol_name;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UsuariosBean(Integer id_usuario, Integer id_rol, String num_empleado, String status, String nombre_completo,
			String rol_name,String password) {
		super();
		this.id_usuario = id_usuario;
		this.id_rol = id_rol;
		this.num_empleado = num_empleado;
		this.status = status;
		this.nombre_completo = nombre_completo;
		this.rol_name = rol_name;
		this.password = password;
	}
	public UsuariosBean() {
		super();
	}
}

package mx.gob.tsjdf.bean;

public class AreasUsuarioBean {
	Integer idUsuario;
	String idArea;
	String NombreArea;
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getIdArea() {
		return idArea;
	}
	public void setIdArea(String idArea) {
		this.idArea = idArea;
	}
	public String getNombreArea() {
		return NombreArea;
	}
	public void setNombreArea(String nombreArea) {
		NombreArea = nombreArea;
	}
	public AreasUsuarioBean() {
		super();
	}
}

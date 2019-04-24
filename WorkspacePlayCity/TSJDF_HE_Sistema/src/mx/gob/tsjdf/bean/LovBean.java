package mx.gob.tsjdf.bean;

public class LovBean {
	String Code;
	String Nmbre;
	String Descripcion;
	public String getCode() {
		return Code;
	}
	public void setCode(String code) {
		Code = code;
	}
	public String getNmbre() {
		return Nmbre;
	}
	public void setNmbre(String nmbre) {
		Nmbre = nmbre;
	}
	public String getDescripcion() {
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	public LovBean() {
		super();
	}
	public LovBean(String code, String nmbre, String descripcion) {
		super();
		Code = code;
		Nmbre = nmbre;
		Descripcion = descripcion;
	}
}

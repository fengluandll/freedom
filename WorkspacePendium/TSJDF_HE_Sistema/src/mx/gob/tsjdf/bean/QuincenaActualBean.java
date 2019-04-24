package mx.gob.tsjdf.bean;

public class QuincenaActualBean {
	Integer idQuincenaActual;
	String  fechaPaga;
	String  fechaPagaNombre;
	String  activo;
	String  activoNombre;
	public Integer getIdQuincenaActual() {
		return idQuincenaActual;
	}
	public void setIdQuincenaActual(Integer idQuincenaActual) {
		this.idQuincenaActual = idQuincenaActual;
	}
	public String getFechaPaga() {
		return fechaPaga;
	}
	public void setFechaPaga(String fechaPaga) {
		this.fechaPaga = fechaPaga;
	}
	public String getFechaPagaNombre() {
		return fechaPagaNombre;
	}
	public void setFechaPagaNombre(String fechaPagaNombre) {
		this.fechaPagaNombre = fechaPagaNombre;
	}
	public String getActivo() {
		return activo;
	}
	public void setActivo(String activo) {
		this.activo = activo;
	}
	public String getActivoNombre() {
		return activoNombre;
	}
	public void setActivoNombre(String activoNombre) {
		this.activoNombre = activoNombre;
	}
	public QuincenaActualBean() {
		super();
	}
	
}

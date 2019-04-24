package mx.kaz.beans.reportes;

import java.io.Serializable;

/**
 * @author Jesus Argumedo
 *
 */
public class AgendaAperturasBean implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private String dia;
	private String api;
	private String obra;
	private String observaciones;
	public String getDia() {
		return dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}
	public String getApi() {
		return api;
	}
	public void setApi(String api) {
		this.api = api;
	}
	public String getObra() {
		return obra;
	}
	public void setObra(String obra) {
		this.obra = obra;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	
}

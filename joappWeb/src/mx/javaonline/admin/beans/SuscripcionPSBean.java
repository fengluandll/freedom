/**
 * @Jose de Jesus Argumedo Quiroz
 */
package mx.javaonline.admin.beans;

import java.io.Serializable;

public class SuscripcionPSBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private String fecInscripcion;
	private String formaPAgo;
	
	public SuscripcionPSBean() {

	}

	public String getFecInscripcion() {
		return fecInscripcion;
	}

	public void setFecInscripcion(String fecInscripcion) {
		this.fecInscripcion = fecInscripcion;
	}

	public String getFormaPAgo() {
		return formaPAgo;
	}

	public void setFormaPAgo(String formaPAgo) {
		this.formaPAgo = formaPAgo;
	}

	
}

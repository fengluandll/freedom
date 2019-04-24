/**
 * @Jose de Jesus Argumedo Quiroz
 */
package mx.javaonline.admin.beans;

import org.apache.commons.lang3.StringUtils;

import mx.javaonline.daos.SuscripcionDAO;

public class SuscripcionBean {

	SuscripcionDAO suscripcionDAO;
	private String leyendaSuscr;
	public SuscripcionBean() {
		getFecSusc();
	}
	
	public void getFecSusc(){
		suscripcionDAO = new SuscripcionDAO();
		SuscripcionPSBean suscripcionPSBean = suscripcionDAO.fechaSuscripcion();
		String dia = StringUtils.substringBefore(suscripcionPSBean.getFecInscripcion(), "-");
		leyendaSuscr = dia;
	}

	public String getLeyendaSuscr() {
		return leyendaSuscr;
	}

	public void setLeyendaSuscr(String leyendaSuscr) {
		this.leyendaSuscr = leyendaSuscr;
	}
	
	

}

/**
 * @Jose de Jesus Argumedo Quiroz
 */
package mx.javaonline.admin.beans;

import java.io.Serializable;

public class CursoPorcenBean implements Serializable{


	private static final long serialVersionUID = 1L;
	private String courseName;
	private int porcentaje;
	private int totUnits;

	public CursoPorcenBean() {

	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(int porcentaje) {
		this.porcentaje = porcentaje;
	}

	public int getTotUnits() {
		return totUnits;
	}

	public void setTotUnits(int totUnits) {
		this.totUnits = totUnits;
	}
	
	

}

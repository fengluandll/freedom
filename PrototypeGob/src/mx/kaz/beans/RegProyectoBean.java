
package mx.kaz.beans;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * @author Jesus Argumedo. All rights reserved.
 *
 */
@ManagedBean
@ViewScoped
public class RegProyectoBean implements Serializable{
	

	private static final long serialVersionUID = 1L;
	private int idProyect;
	private String api;
	private String nomProyecto;
	private String desPro;
	private String claveCartera;
	private BigDecimal montoAsignado;
	private String tipoRecurso;
	private String periodoEjecu;
	private BigDecimal inversionTotal;
	private BigDecimal montoEjercido;
	private int idPort;
	private String tipoRecursoNum;
	
	public RegProyectoBean(){
		
	}
	
	
	public BigDecimal getInversionTotal() {
		return inversionTotal;
	}


	public void setInversionTotal(BigDecimal inversionTotal) {
		this.inversionTotal = inversionTotal;
	}


	public BigDecimal getMontoEjercido() {
		return montoEjercido;
	}


	public void setMontoEjercido(BigDecimal montoEjercido) {
		this.montoEjercido = montoEjercido;
	}


	public int getIdPort() {
		return idPort;
	}


	public void setIdPort(int idPort) {
		this.idPort = idPort;
	}


	public String getPeriodoEjecu() {
		return periodoEjecu;
	}


	public void setPeriodoEjecu(String periodoEjecu) {
		this.periodoEjecu = periodoEjecu;
	}


	public int getIdProyect() {
		return idProyect;
	}

	public void setIdProyect(int idProyect) {
		this.idProyect = idProyect;
	}

	public String getApi() {
		return api;
	}

	public void setApi(String api) {
		this.api = api;
	}

	

	public String getClaveCartera() {
		return claveCartera;
	}

	public void setClaveCartera(String claveCartera) {
		this.claveCartera = claveCartera;
	}

	public BigDecimal getMontoAsignado() {
		return montoAsignado;
	}

	public void setMontoAsignado(BigDecimal montoAsignado) {
		this.montoAsignado = montoAsignado;
	}

	public String getTipoRecurso() {
		return tipoRecurso;
	}

	public void setTipoRecurso(String tipoRecurso) {
		this.tipoRecurso = tipoRecurso;
	}

	public String getNomProyecto() {
		return nomProyecto;
	}

	public void setNomProyecto(String nomProyecto) {
		this.nomProyecto = nomProyecto;
	}

	public String getDesPro() {
		return desPro;
	}

	public void setDesPro(String desPro) {
		this.desPro = desPro;
	}


	public String getTipoRecursoNum() {
		return tipoRecursoNum;
	}


	public void setTipoRecursoNum(String tipoRecursoNum) {
		this.tipoRecursoNum = tipoRecursoNum;
	}


	
	
	

}

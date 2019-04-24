
package mx.kaz.beans.reportes;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @authorJesus Argumedo
 */

public class ReporteLicitaBean implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private String encabezado;
	private String proyecto;
	private String desObra;
	private BigDecimal montoContratado;
	private String obras;
	private BigDecimal presuBase;
	private java.sql.Date publiPrebases;
	private java.sql.Date publiConvocatoria;
	private java.sql.Date aperturaProposiciones;
	private java.sql.Date fallo;
	private java.sql.Date inicioObra;
	private java.sql.Date finObra;
	private String incioFinObra;
	private String observaciones;
	private String participantes;
	private String contratista;
	private String tipoProcedimiento; 
	private String nomPto;
	
	
	public String getProyecto() {
		return proyecto;
	}
	public void setProyecto(String proyecto) {
		this.proyecto = proyecto;
	}
	public String getDesObra() {
		return desObra;
	}
	public void setDesObra(String desObra) {
		this.desObra = desObra;
	}
	public BigDecimal getMontoContratado() {
		return montoContratado;
	}
	public void setMontoContratado(BigDecimal montoContratado) {
		this.montoContratado = montoContratado;
	}
	public String getObras() {
		return obras;
	}
	public void setObras(String obras) {
		this.obras = obras;
	}
	public BigDecimal getPresuBase() {
		return presuBase;
	}
	public void setPresuBase(BigDecimal presuBase) {
		this.presuBase = presuBase;
	}
	public java.sql.Date getPubliPrebases() {
		return publiPrebases;
	}
	public void setPubliPrebases(java.sql.Date publiPrebases) {
		this.publiPrebases = publiPrebases;
	}
	public java.sql.Date getPubliConvocatoria() {
		return publiConvocatoria;
	}
	public void setPubliConvocatoria(java.sql.Date publiConvocatoria) {
		this.publiConvocatoria = publiConvocatoria;
	}
	public java.sql.Date getAperturaProposiciones() {
		return aperturaProposiciones;
	}
	public void setAperturaProposiciones(java.sql.Date aperturaProposiciones) {
		this.aperturaProposiciones = aperturaProposiciones;
	}
	public java.sql.Date getFallo() {
		return fallo;
	}
	public void setFallo(java.sql.Date fallo) {
		this.fallo = fallo;
	}
	public java.sql.Date getInicioObra() {
		return inicioObra;
	}
	public void setInicioObra(java.sql.Date inicioObra) {
		this.inicioObra = inicioObra;
	}
	public java.sql.Date getFinObra() {
		return finObra;
	}
	public void setFinObra(java.sql.Date finObra) {
		this.finObra = finObra;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public String getIncioFinObra() {
		return incioFinObra;
	}
	public void setIncioFinObra(String incioFinObra) {
		this.incioFinObra = incioFinObra;
	}
	public String getParticipantes() {
		return participantes;
	}
	public void setParticipantes(String participantes) {
		this.participantes = participantes;
	}
	public String getContratista() {
		return contratista;
	}
	public void setContratista(String contratista) {
		this.contratista = contratista;
	}
	public String getEncabezado() {
		return encabezado;
	}
	public void setEncabezado(String encabezado) {
		this.encabezado = encabezado;
	}
	public String getTipoProcedimiento() {
		return tipoProcedimiento;
	}
	public void setTipoProcedimiento(String tipoProcedimiento) {
		this.tipoProcedimiento = tipoProcedimiento;
	}
	public String getNomPto() {
		return nomPto;
	}
	public void setNomPto(String nomPto) {
		this.nomPto = nomPto;
	}
	
	
	
}

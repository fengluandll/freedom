/**
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 */
package mx.kaz.obras.view;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import mx.kaz.beans.jasper.reports.JReportsBean;
import mx.kaz.beans.reportes.ReporteObraBean;
import mx.kaz.daos.ReportesDAO;
import mx.kaz.util.ManejoFechas;
import net.sf.jasperreports.engine.JRException;

import org.apache.log4j.Logger;
import org.jfree.util.Log;

/**
 * @author Jesus Argumedo
 *
 */
@ManagedBean(name="dtReporteObrasView")
@RequestScoped
public class ReporteObrasView implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private static org.apache.log4j.Logger logger = Logger.getLogger(ReporteObrasView.class);
	private String tipoRecurso;
	private String contratista;
	private BigDecimal montoAsigInicial;
	private BigDecimal montoAsigFinal;
	private Date pEjecInicialIni;
	private Date pEjecInicialFin;
	private Date pEjecFinalIni;
	private Date pEjecFinalFin;
	private Date fechaFalloIni;
	private Date fechaFalloFin;
	private int 		  idPuerto;
	ReportesDAO 		  reportesDAO;
	ReporteObraBean       reporteObraBean;
	private Map<String,Integer> mapApis = new HashMap<String,Integer>();
	JReportsBean jReportsBean;
	private String tipoFormato;
	
	public ReporteObrasView() {
		reportesDAO = new ReportesDAO();
		mapApis = reportesDAO.damePuertos();
	}
	
	public void ejecutaReporte(){
		reporteObraBean = new ReporteObraBean();
		reporteObraBean.setTipoRecurso(this.tipoRecurso);
		reporteObraBean.setNom_contratista(this.contratista);
		reporteObraBean.setMontoAsignadoIni(this.montoAsigInicial);
		reporteObraBean.setMontoAsignadoFin(this.montoAsigFinal);
		reporteObraBean.setPeriodEjecIni(this.pEjecInicialIni != null ? ManejoFechas.utilDateToslDate(this.pEjecInicialIni) :null);
		reporteObraBean.setPeriodEjecFin(this.pEjecInicialFin != null ? ManejoFechas.utilDateToslDate(this.pEjecInicialFin) : null);
		reporteObraBean.setPeriodEjecIni2(this.pEjecFinalIni != null?ManejoFechas.utilDateToslDate(this.pEjecFinalIni):null);
		reporteObraBean.setPeriodEjecFin2(this.pEjecFinalFin!=null?ManejoFechas.utilDateToslDate(this.pEjecFinalFin):null);
		reporteObraBean.setIdPuerto(this.idPuerto);
		reporteObraBean.setFechaFalloIni(this.fechaFalloIni!=null?ManejoFechas.utilDateToslDate(this.fechaFalloIni):null);
		reporteObraBean.setFechaFalloFin(this.fechaFalloFin!=null?ManejoFechas.utilDateToslDate(this.fechaFalloFin):null);
		
		
		boolean ok = reportesDAO.obrasReporte(reporteObraBean);
		if(ok){
			try {
				jReportsBean = new JReportsBean();
				if(this.tipoFormato.equals("PDF")){
					jReportsBean.viewPDF("reportObras");
				}else if(this.tipoFormato.equals("EXCEL")){
					jReportsBean.viewExcel("reportObras");
				}
				
			} catch (IOException | JRException e) {
				Log.error(e);
			}
		}
	}
	
	
	
	public Map<String, Integer> getMapApis() {
		return mapApis;
	}

	public void setMapApis(Map<String, Integer> mapApis) {
		this.mapApis = mapApis;
	}

	public int getIdPuerto() {
		return idPuerto;
	}

	public void setIdPuerto(int idPuerto) {
		this.idPuerto = idPuerto;
	}

	public String getContratista() {
		return contratista;
	}


	public void setContratista(String contratista) {
		this.contratista = contratista;
	}


	public BigDecimal getMontoAsigInicial() {
		return montoAsigInicial;
	}


	public void setMontoAsigInicial(BigDecimal montoAsigInicial) {
		this.montoAsigInicial = montoAsigInicial;
	}


	public BigDecimal getMontoAsigFinal() {
		return montoAsigFinal;
	}


	public void setMontoAsigFinal(BigDecimal montoAsigFinal) {
		this.montoAsigFinal = montoAsigFinal;
	}


	







	public Date getpEjecInicialIni() {
		return pEjecInicialIni;
	}

	public void setpEjecInicialIni(Date pEjecInicialIni) {
		this.pEjecInicialIni = pEjecInicialIni;
	}

	public Date getpEjecInicialFin() {
		return pEjecInicialFin;
	}

	public Date getpEjecFinalIni() {
		return pEjecFinalIni;
	}

	public Date getpEjecFinalFin() {
		return pEjecFinalFin;
	}


	public String getTipoRecurso() {
		return tipoRecurso;
	}

	public void setTipoRecurso(String tipoRecurso) {
		this.tipoRecurso = tipoRecurso;
	}

	public void setpEjecInicialFin(Date pEjecInicialFin) {
		this.pEjecInicialFin = pEjecInicialFin;
	}

	public void setpEjecFinalIni(Date pEjecFinalIni) {
		this.pEjecFinalIni = pEjecFinalIni;
	}

	public void setpEjecFinalFin(Date pEjecFinalFin) {
		this.pEjecFinalFin = pEjecFinalFin;
	}

	public String getTipoFormato() {
		return tipoFormato;
	}

	public void setTipoFormato(String tipoFormato) {
		this.tipoFormato = tipoFormato;
	}

	public Date getFechaFalloIni() {
		return fechaFalloIni;
	}

	public void setFechaFalloIni(Date fechaFalloIni) {
		this.fechaFalloIni = fechaFalloIni;
	}

	public Date getFechaFalloFin() {
		return fechaFalloFin;
	}

	public void setFechaFalloFin(Date fechaFalloFin) {
		this.fechaFalloFin = fechaFalloFin;
	}
	
	
	

}

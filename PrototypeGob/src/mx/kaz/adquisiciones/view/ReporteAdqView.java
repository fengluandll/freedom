/**
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 */
package mx.kaz.adquisiciones.view;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.sun.faces.application.annotation.FacesComponentUsage;

import net.sf.jasperreports.engine.JRException;
import mx.kaz.beans.jasper.reports.JReportsBean;
import mx.kaz.util.FuncionesGenerales;
import mx.kaz.util.Mensajes;


/**
 * @author Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 */
@ManagedBean(name="dtReporteAdqView")
@RequestScoped
public class ReporteAdqView implements Serializable{


	private static final long serialVersionUID = 1L;
	
	private int idBuilds;
    private int idPort;
    private String build_description;
    private int idTypeProcedure;
    private java.util.Date fecContractIni;
    private java.util.Date fecContract_fin;
    private BigDecimal amountHire;
    private BigDecimal contractedAmount;
    private String providerName;
    private String resourceType;
    private int idArticulo;
    private int idFraccion;
    private java.util.Date fecCompranet;
    private java.util.Date fecClarificationMeeting;
    private java.util.Date fecPresentationsTender;
    private java.util.Date feFailDesde;
    private java.util.Date feFailHasta;
    private String razonSocial;
    private String segment1;
    private String tipoFormato;
    private JReportsBean jReportsBean;
    private FacesContext facesContext = FacesContext.getCurrentInstance();
    private HttpSession session;
    
	public ReporteAdqView() {
		jReportsBean = new JReportsBean();
	}
	
	public void ejecutaReporte(){
		StringBuilder stb = new StringBuilder();
		if((this.feFailDesde != null && this.feFailHasta == null)){
			FuncionesGenerales.mostrarMensaje("Fecha de fallo Hasta", "Requiere ser capturada", Mensajes.WARN);
		}else if(this.feFailHasta !=null && this.feFailDesde == null){
			FuncionesGenerales.mostrarMensaje("Fecha de fallo Desde", "Requiere ser capturada", Mensajes.WARN);
		}else{
			stb.append("WHERE 1 = 1 \n");
			if(this.amountHire.compareTo(BigDecimal.ZERO) > 0 ){
				stb.append("AND   monto_a_contratar       = " + this.amountHire+"\n");
			}
			if(this.idPort != 0){
				stb.append("AND   id_port = " + this.idPort + "\n");
			}
			if(!this.build_description.equals("")){
				stb.append("AND   UPPER(descripcion) like UPPER('%" + this.build_description + "%')\n");
			}
			if(this.idTypeProcedure != 0){
				stb.append("AND   id_type_procedure = " + this.idTypeProcedure + "\n");
			}
			if(this.fecContractIni != null){
				stb.append("AND   cont_inicio = '" + FuncionesGenerales.dateToString(this.fecContractIni) + "'\n");
			}
			if(this.fecContract_fin != null){
				stb.append("AND   cont_fin = '" + FuncionesGenerales.dateToString(this.fecContract_fin) + "'\n");
			}
			if(this.contractedAmount.compareTo(BigDecimal.ZERO) > 0){
				stb.append("AND  monto_contratado  = " + this.contractedAmount + "\n");
			}
			if(!this.providerName.equals("")){
				stb.append("AND   UPPER(nom_proveedor) LIKE UPPER('%" + this.providerName + "%')\n");
			}
			if(!this.resourceType.equals("")){
				stb.append("AND   tipo_recurso = '" + this.resourceType + "'\n");
			}
			if(this.idArticulo != 0){
				stb.append("AND   id_articulo = " + this.idArticulo + "\n");
			}
			if(this.idFraccion != 0){
				stb.append("AND   id_fraccion = " + this.idFraccion + "\n");
			}
			if(this.fecCompranet != null){
				stb.append("AND   fec_compranet = '" + FuncionesGenerales.dateToString(this.fecCompranet) + "'\n");
			}
			if(this.fecClarificationMeeting != null){
				stb.append("AND   fec_junt_aclar = '" + FuncionesGenerales.dateToString(this.fecClarificationMeeting) + "'\n");
			}
			if(this.fecPresentationsTender != null){
				stb.append("AND   fec_prent_prop = '" + FuncionesGenerales.dateToString(this.fecClarificationMeeting) + "'\n");
			}		
			if(this.feFailDesde != null && this.feFailHasta != null){
				stb.append("AND   fec_fallo BETWEEN '" + FuncionesGenerales.dateToString(this.feFailDesde) + "' AND '" + FuncionesGenerales.dateToString(this.feFailHasta) + "'\n");
			}
			if(!this.razonSocial.equals("")){
				stb.append("AND   UPPER(razon_social) LIKE UPPER('%" + this.razonSocial + "%')\n");
			}
			if(!this.segment1.equals("")){
				stb.append("AND   UPPER(num_acuerdo)  like UPPER('%" + this.segment1 + "%')\n");
			}
			session = (HttpSession) facesContext.getExternalContext().getSession(true);
			session.setAttribute("paramLassp", stb.toString());
			try {
				if(this.tipoFormato.equals("PDF")){
					jReportsBean.viewPDF("Laassp");
				}else if(this.tipoFormato.equals("EXCEL")){
					jReportsBean.viewExcel("Laassp");
				}
				
			} catch (IOException | JRException e) {
				e.printStackTrace();
			}
		}
	}

	public int getIdBuilds() {
		return idBuilds;
	}

	public void setIdBuilds(int idBuilds) {
		this.idBuilds = idBuilds;
	}

	public int getIdPort() {
		return idPort;
	}

	public void setIdPort(int idPort) {
		this.idPort = idPort;
	}

	public String getBuild_description() {
		return build_description;
	}

	public void setBuild_description(String build_description) {
		this.build_description = build_description;
	}

	public int getIdTypeProcedure() {
		return idTypeProcedure;
	}

	public void setIdTypeProcedure(int idTypeProcedure) {
		this.idTypeProcedure = idTypeProcedure;
	}

	public java.util.Date getFecContractIni() {
		return fecContractIni;
	}

	public void setFecContractIni(java.util.Date fecContractIni) {
		this.fecContractIni = fecContractIni;
	}

	public java.util.Date getFecContract_fin() {
		return fecContract_fin;
	}

	public void setFecContract_fin(java.util.Date fecContract_fin) {
		this.fecContract_fin = fecContract_fin;
	}

	public BigDecimal getAmountHire() {
		return amountHire;
	}

	public void setAmountHire(BigDecimal amountHire) {
		this.amountHire = amountHire;
	}

	public BigDecimal getContractedAmount() {
		return contractedAmount;
	}

	public void setContractedAmount(BigDecimal contractedAmount) {
		this.contractedAmount = contractedAmount;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public int getIdArticulo() {
		return idArticulo;
	}

	public void setIdArticulo(int idArticulo) {
		this.idArticulo = idArticulo;
	}

	public int getIdFraccion() {
		return idFraccion;
	}

	public void setIdFraccion(int idFraccion) {
		this.idFraccion = idFraccion;
	}

	public java.util.Date getFecCompranet() {
		return fecCompranet;
	}

	public void setFecCompranet(java.util.Date fecCompranet) {
		this.fecCompranet = fecCompranet;
	}

	public java.util.Date getFecClarificationMeeting() {
		return fecClarificationMeeting;
	}

	public void setFecClarificationMeeting(java.util.Date fecClarificationMeeting) {
		this.fecClarificationMeeting = fecClarificationMeeting;
	}

	public java.util.Date getFecPresentationsTender() {
		return fecPresentationsTender;
	}

	public void setFecPresentationsTender(java.util.Date fecPresentationsTender) {
		this.fecPresentationsTender = fecPresentationsTender;
	}

	

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getSegment1() {
		return segment1;
	}

	public void setSegment1(String segment1) {
		this.segment1 = segment1;
	}

	public String getTipoFormato() {
		return tipoFormato;
	}

	public void setTipoFormato(String tipoFormato) {
		this.tipoFormato = tipoFormato;
	}

	public java.util.Date getFeFailDesde() {
		return feFailDesde;
	}

	public void setFeFailDesde(java.util.Date feFailDesde) {
		this.feFailDesde = feFailDesde;
	}

	public java.util.Date getFeFailHasta() {
		return feFailHasta;
	}

	public void setFeFailHasta(java.util.Date feFailHasta) {
		this.feFailHasta = feFailHasta;
	}

	
	
}


package mx.kaz.obras.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Blob;

/**
 * @author Argumel
 *
 */
public class ObrasBean implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private int idBuilds;
	private int idPort; 
	private String nomPuerto;
	private String buildDescription; 
	private int idTypeProcedure; 
	private String tipoProce;
	private int idArticulo;
	private String articulo;
	private int idFraccion;
	private String fraccion;
	private java.sql.Date fecContractIni; 
	private java.sql.Date fecContractFin; 
	private BigDecimal amountHire; 
	private BigDecimal contractedAmount; 
	private String providerName; 
	private String resourceType; 
	private java.sql.Date fecCompranet; 
	private java.sql.Date fecClarification_meeting; 
	private java.sql.Date fecPresentationsTender; 
	private java.sql.Date fecFail;
	private java.sql.Date fecFirmaContratacion; 
	private String razonSocial;
	private String tp;
	private String segment1; 
	private String segment2; 
	private String segment3; 
	private String segment4; 
	private String segment5; 
	private int segment6; 
	private java.sql.Date segment7; 
	private Blob segment8; 
	private java.sql.Date creationDate; 
	private java.sql.Date lastUpdateDate;
	private String resourceTypeNum;
	
	public ObrasBean() {
		
	}

	
	public String getTipoProce() {
		return tipoProce;
	}


	public void setTipoProce(String tipoProce) {
		this.tipoProce = tipoProce;
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

	public String getBuildDescription() {
		return buildDescription;
	}

	public void setBuildDescription(String buildDescription) {
		this.buildDescription = buildDescription;
	}

	public int getIdTypeProcedure() {
		return idTypeProcedure;
	}

	public void setIdTypeProcedure(int idTypeProcedure) {
		this.idTypeProcedure = idTypeProcedure;
	}

	public java.sql.Date getFecContractIni() {
		return fecContractIni;
	}

	public void setFecContractIni(java.sql.Date fecContractIni) {
		this.fecContractIni = fecContractIni;
	}

	public java.sql.Date getFecContractFin() {
		return fecContractFin;
	}

	public void setFecContractFin(java.sql.Date fecContractFin) {
		this.fecContractFin = fecContractFin;
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

	public java.sql.Date getFecCompranet() {
		return fecCompranet;
	}

	public void setFecCompranet(java.sql.Date fecCompranet) {
		this.fecCompranet = fecCompranet;
	}

	public java.sql.Date getFecClarification_meeting() {
		return fecClarification_meeting;
	}

	public void setFecClarification_meeting(java.sql.Date fecClarification_meeting) {
		this.fecClarification_meeting = fecClarification_meeting;
	}

	public java.sql.Date getFecPresentationsTender() {
		return fecPresentationsTender;
	}

	public void setFecPresentationsTender(java.sql.Date fecPresentationsTender) {
		this.fecPresentationsTender = fecPresentationsTender;
	}

	public java.sql.Date getFecFail() {
		return fecFail;
	}

	public void setFecFail(java.sql.Date fecFail) {
		this.fecFail = fecFail;
	}

	public String getSegment1() {
		return segment1;
	}

	public void setSegment1(String segment1) {
		this.segment1 = segment1;
	}

	public String getSegment2() {
		return segment2;
	}

	public void setSegment2(String segment2) {
		this.segment2 = segment2;
	}

	public String getSegment3() {
		return segment3;
	}

	public void setSegment3(String segment3) {
		this.segment3 = segment3;
	}

	public String getSegment4() {
		return segment4;
	}

	public void setSegment4(String segment4) {
		this.segment4 = segment4;
	}

	public String getSegment5() {
		return segment5;
	}

	public void setSegment5(String segment5) {
		this.segment5 = segment5;
	}

	public int getSegment6() {
		return segment6;
	}

	public void setSegment6(int segment6) {
		this.segment6 = segment6;
	}

	public java.sql.Date getSegment7() {
		return segment7;
	}

	public void setSegment7(java.sql.Date segment7) {
		this.segment7 = segment7;
	}

	public Blob getSegment8() {
		return segment8;
	}

	public void setSegment8(Blob segment8) {
		this.segment8 = segment8;
	}

	public java.sql.Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(java.sql.Date creationDate) {
		this.creationDate = creationDate;
	}

	public java.sql.Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(java.sql.Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
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


	public String getTp() {
		return tp;
	}


	public void setTp(String tp) {
		this.tp = tp;
	}


	public String getNomPuerto() {
		return nomPuerto;
	}


	public void setNomPuerto(String nomPuerto) {
		this.nomPuerto = nomPuerto;
	}


	public String getArticulo() {
		return articulo;
	}


	public void setArticulo(String articulo) {
		this.articulo = articulo;
	}


	public String getFraccion() {
		return fraccion;
	}


	public void setFraccion(String fraccion) {
		this.fraccion = fraccion;
	}


	public String getRazonSocial() {
		return razonSocial;
	}


	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}


	public String getResourceTypeNum() {
		return resourceTypeNum;
	}


	public void setResourceTypeNum(String resourceTypeNum) {
		this.resourceTypeNum = resourceTypeNum;
	}


	public java.sql.Date getFecFirmaContratacion() {
		return fecFirmaContratacion;
	}


	public void setFecFirmaContratacion(java.sql.Date fecFirmaContratacion) {
		this.fecFirmaContratacion = fecFirmaContratacion;
	}

	
	

}

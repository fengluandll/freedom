package mx.gob.tsjdf.cfdi.dto;

import java.io.Serializable;

public class HonorariosPercepDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cTipopd;
	private String cCve;
	private String cNomConcepto;
	private String nImpGra;
	private String nImpexeImporte;
	private String nImpExeTotal;
	private String cTipo;
	private String cNomDesc;
	private String cTipopdedu;
	private String numEmpleado;
	
	
	
	
	public String getNumEmpleado() {
		return numEmpleado;
	}
	public void setNumEmpleado(String numEmpleado) {
		this.numEmpleado = numEmpleado;
	}
	public String getcTipopdedu() {
		return cTipopdedu;
	}
	public void setcTipopdedu(String cTipopdedu) {
		this.cTipopdedu = cTipopdedu;
	}
	public String getcTipopd() {
		return cTipopd;
	}
	public void setcTipopd(String cTipopd) {
		this.cTipopd = cTipopd;
	}
	public String getcCve() {
		return cCve;
	}
	public void setcCve(String cCve) {
		this.cCve = cCve;
	}
	public String getcNomConcepto() {
		return cNomConcepto;
	}
	public void setcNomConcepto(String cNomConcepto) {
		this.cNomConcepto = cNomConcepto;
	}
	public String getnImpGra() {
		return nImpGra;
	}
	public void setnImpGra(String nImpGra) {
		this.nImpGra = nImpGra;
	}
	public String getnImpexeImporte() {
		return nImpexeImporte;
	}
	public void setnImpexeImporte(String nImpexeImporte) {
		this.nImpexeImporte = nImpexeImporte;
	}
	public String getnImpExeTotal() {
		return nImpExeTotal;
	}
	public void setnImpExeTotal(String nImpExeTotal) {
		this.nImpExeTotal = nImpExeTotal;
	}
	public String getcTipo() {
		return cTipo;
	}
	public void setcTipo(String cTipo) {
		this.cTipo = cTipo;
	}
	public String getcNomDesc() {
		return cNomDesc;
	}
	public void setcNomDesc(String cNomDesc) {
		this.cNomDesc = cNomDesc;
	}
	
	
	


}

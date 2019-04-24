package mx.com.televisa.derechocorporativo.bean.reformas;

import java.io.Serializable;

public class EscriturasOtrosBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private String solicitud;

	private String solPor;
	
	private String fechaDocumento;

	private String fechaRecibido;

	private String folioNum;

	private String numDocSol;

	private String documentoEntregado;

	private String numDocDocEnt;
	
	private String fechaDocumentoE;

	private String fechaRecibidoE;
	
	private int agregDoc;


	public EscriturasOtrosBean(String solicitud
							  ,String solPor
                              ,String fechaDocumento
                              ,String fechaRecibido
                              ,String folioNum
                              ,String numDocSol
                              ,String documentoEntregado
                              ,String numDocDocEnt
                              ,String fechaDocumentoE
                              ,String fechaRecibidoE
                              ,int agregDoc
    ){
        this.solicitud = solicitud;
        this.solPor=solPor;
        this.fechaDocumento = fechaDocumento;
        this.fechaRecibido = fechaRecibido;
        this.folioNum = folioNum;
        this.numDocSol = numDocSol;
        this.documentoEntregado = documentoEntregado;
        this.numDocDocEnt = numDocDocEnt;
        this.fechaDocumentoE = fechaDocumentoE;
        this.fechaRecibidoE = fechaRecibidoE;
        this.agregDoc = agregDoc;
	}

	public int getAgregDoc() {
		return agregDoc;
	}
	
	public void setAgregDoc(int agregDoc) {
		this.agregDoc = agregDoc;
	}
	
	public String getSolicitud() {
		return solicitud;
	}

	public String getFechaDocumento() {
		return fechaDocumento;
	}

	public String getFechaRecibido() {
		return fechaRecibido;
	}

	public String getFolioNum() {
		return folioNum;
	}

	public String getNumDocSol() {
		return numDocSol;
	}

	public String getSolPor() {
		return solPor;
	}

	public void setSolPor(String solPor) {
		this.solPor = solPor;
	}

	public String getFechaDocumentoE() {
		return fechaDocumentoE;
	}

	public void setFechaDocumentoE(String fechaDocumentoE) {
		this.fechaDocumentoE = fechaDocumentoE;
	}

	public String getFechaRecibidoE() {
		return fechaRecibidoE;
	}

	public void setFechaRecibidoE(String fechaRecibidoE) {
		this.fechaRecibidoE = fechaRecibidoE;
	}

	public String getDocumentoEntregado() {
		return documentoEntregado;
	}

	public String getNumDocDocEnt() {
		return numDocDocEnt;
	}

	

}
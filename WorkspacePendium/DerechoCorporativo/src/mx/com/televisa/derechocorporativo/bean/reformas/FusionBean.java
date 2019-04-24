package mx.com.televisa.derechocorporativo.bean.reformas;

import java.io.Serializable;

public class FusionBean implements Serializable{
	private static final long serialVersionUID = 1L;

	private String solicitud;
	
	private String solPor;

	private String fechaDocumento;

	private String fechaRecibido;

	private String folioNum;

	private String numDocSol;

	private String actaResoluciones;

	private String numDocActaResol;

	private String convocatoria;

	private String numDocConvocatoria;

	private String publicaciones;

	private String numDocPublicaciones;

	private String documentoEntregado;

	private String numDocDocEntregado;

	private String convenioFusion;

	private String numDocConvenFusion;
	
	private String fechaDocumentoE;

	private String fechaRecibidoE;
	
	private int agregDoc;

    private String capitalFijoAum;
    private String deCapFijAum;
    private	String	conCapFijAum;
    private	String	quedarCapFijAum;
    private	String	capitalVariableAum;
    private	String	deCapVarAum;
    private	String	conCapVarAum;
    private	String	quedarCapVarAum;
    private	String	capitalSocialAum;
    private	String	capitalFijoDis;
    private	String	deCapFijDis;
    private	String	conCapFijDis;
    private	String	quedarCapFijDis;
    private	String	capitalVariableDis;
    private	String	deCapVarDis;
    private	String	conCapVarDis;
    private	String	quedarCapVarDis;
    private	String	capitalSocialDis;
    private	String	capitalTotal;

    private String asunto;
    private String tipoReunion;
    private String sociedadFusionante;
    private String fecha;
    private String hora;
    private String asambleaSociedadesFusionantes;
    private String fechaEfectosPartes;
    private String fechaEfectosTerceros;
    private String artClaEstatusReformadas;
    private String otrosAcuerdosObservaciones;
    private String otrosRegistros;

    public FusionBean(){}

    public FusionBean(String asunto
                     ,String tipoReunion
                     ,String sociedadFusionante
                     ,String fecha
                     ,String hora
                     ,String asambleaSociedadesFusionantes
                     ,String fechaEfectosPartes
                     ,String fechaEfectosTerceros
                     ,String artClaEstatusReformadas
                     ,String otrosAcuerdosObservaciones
                     ,String otrosRegistros
    ){
    	this.asunto = asunto;
    	this.tipoReunion = tipoReunion;
    	this.sociedadFusionante = sociedadFusionante;
    	this.fecha = fecha;
    	this.hora = hora;
    	this.asambleaSociedadesFusionantes = asambleaSociedadesFusionantes;
    	this.fechaEfectosPartes = fechaEfectosPartes;
    	this.fechaEfectosTerceros = fechaEfectosTerceros;
    	this.artClaEstatusReformadas = artClaEstatusReformadas;
    	this.otrosAcuerdosObservaciones = otrosAcuerdosObservaciones;
    	this.otrosRegistros = otrosRegistros;
    }

    public FusionBean(  String capitalFijoAum,
			    		String deCapFijAum,
			    		String	conCapFijAum,
			    		String	quedarCapFijAum,
			    		String	capitalVariableAum,
			    		String	deCapVarAum,
			    		String	conCapVarAum,
			    		String	quedarCapVarAum,
			    		String	capitalSocialAum,
			    		String	capitalFijoDis,
			    		String	deCapFijDis,
			    		String	conCapFijDis,
			    		String	quedarCapFijDis,
			    		String	capitalVariableDis,
			    		String	deCapVarDis,
			    		String	conCapVarDis,
			    		String	quedarCapVarDis,
			    		String	capitalSocialDis,
			    		String	capitalTotal
){
						this.capitalFijoAum = capitalFijoAum;
						this.deCapFijAum = deCapFijAum;
						this.conCapFijAum = conCapFijAum;
						this.quedarCapFijAum = quedarCapFijAum;
						this.capitalVariableAum = capitalVariableAum;
						this.deCapVarAum = deCapVarAum;
						this.conCapVarAum = conCapVarAum;
						this.quedarCapVarAum = quedarCapVarAum;
						this.capitalSocialAum = capitalSocialAum;
						this.capitalFijoDis = capitalFijoDis;
						this.deCapFijDis = deCapFijDis;
						this.conCapFijDis = conCapFijDis;
						this.quedarCapFijDis = quedarCapFijDis;
						this.capitalVariableDis = capitalVariableDis;
						this.deCapVarDis = deCapVarDis;
						this.conCapVarDis = conCapVarDis;
						this.quedarCapVarDis = quedarCapVarDis;
						this.capitalSocialDis = capitalSocialDis;
						this.capitalTotal = capitalTotal;
}

	public FusionBean(String solicitud
					 ,String solPor
                     ,String fechaDocumento
                     ,String fechaRecibido
                     ,String folioNum
                     ,String numDocSol
                     ,String actaResoluciones
                     ,String numDocActaResol
                     ,String convocatoria
                     ,String numDocConvocatoria
                     ,String publicaciones
                     ,String numDocPublicaciones
                     ,String documentoEntregado
                     ,String numDocDocEntregado
                     ,String convenioFusion
                     ,String numDocConvenFusion
                     ,String fechaDocumentoE
                     ,String fechaRecibidoE
                     ,int agregDoc
    ){
		this.solicitud = solicitud;
		this.solPor = solPor;
		this.fechaDocumento = fechaDocumento;
		this.fechaRecibido = fechaRecibido;
		this.folioNum = folioNum;
		this.numDocSol = numDocSol;
		this.actaResoluciones = actaResoluciones;
		this.numDocActaResol = numDocActaResol;
		this.convocatoria = convocatoria;
		this.numDocConvocatoria = numDocConvocatoria;
		this.publicaciones = publicaciones;
		this.numDocPublicaciones = numDocPublicaciones;
		this.documentoEntregado = documentoEntregado;
		this.numDocDocEntregado = numDocDocEntregado;
		this.convenioFusion = convenioFusion;
		this.numDocConvenFusion = numDocConvenFusion;
		this.fechaDocumentoE = fechaDocumentoE;
        this.fechaRecibidoE = fechaRecibidoE;
        this.agregDoc = agregDoc;

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

	public String getActaResoluciones() {
		return actaResoluciones;
	}

	public String getNumDocActaResol() {
		return numDocActaResol;
	}

	public String getConvocatoria() {
		return convocatoria;
	}

	public String getNumDocConvocatoria() {
		return numDocConvocatoria;
	}

	public String getPublicaciones() {
		return publicaciones;
	}

	public String getNumDocPublicaciones() {
		return numDocPublicaciones;
	}

	public String getDocumentoEntregado() {
		return documentoEntregado;
	}

	public String getNumDocDocEntregado() {
		return numDocDocEntregado;
	}

	public String getCapitalFijoAum() {
		return capitalFijoAum;
	}

	public String getDeCapFijAum() {
		return deCapFijAum;
	}

	public String getConCapFijAum() {
		return conCapFijAum;
	}

	public String getQuedarCapFijAum() {
		return quedarCapFijAum;
	}

	public String getCapitalVariableAum() {
		return capitalVariableAum;
	}

	public String getDeCapVarAum() {
		return deCapVarAum;
	}

	public String getConCapVarAum() {
		return conCapVarAum;
	}

	public String getQuedarCapVarAum() {
		return quedarCapVarAum;
	}

	public String getCapitalSocialAum() {
		return capitalSocialAum;
	}

	public String getCapitalFijoDis() {
		return capitalFijoDis;
	}

	public String getDeCapFijDis() {
		return deCapFijDis;
	}

	public String getConCapFijDis() {
		return conCapFijDis;
	}

	public String getQuedarCapFijDis() {
		return quedarCapFijDis;
	}

	public String getCapitalVariableDis() {
		return capitalVariableDis;
	}

	public String getDeCapVarDis() {
		return deCapVarDis;
	}

	public String getConCapVarDis() {
		return conCapVarDis;
	}

	public String getQuedarCapVarDis() {
		return quedarCapVarDis;
	}

	public String getCapitalSocialDis() {
		return capitalSocialDis;
	}

	public String getCapitalTotal() {
		return capitalTotal;
	}

	public String getAsunto() {
		return asunto;
	}

	public String getTipoReunion() {
		return tipoReunion;
	}

	public String getSociedadFusionante() {
		return sociedadFusionante;
	}

	public String getFecha() {
		return fecha;
	}

	public String getHora() {
		return hora;
	}

	public String getAsambleaSociedadesFusionantes() {
		return asambleaSociedadesFusionantes;
	}

	public String getFechaEfectosPartes() {
		return fechaEfectosPartes;
	}

	public String getFechaEfectosTerceros() {
		return fechaEfectosTerceros;
	}

	public String getArtClaEstatusReformadas() {
		return artClaEstatusReformadas;
	}

	public String getOtrosAcuerdosObservaciones() {
		return otrosAcuerdosObservaciones;
	}

	public String getOtrosRegistros() {
		return otrosRegistros;
	}

	public String getConvenioFusion() {
		return convenioFusion;
	}

	public String getNumDocConvenFusion() {
		return numDocConvenFusion;
	}

}
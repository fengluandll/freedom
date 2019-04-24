package mx.com.televisa.derechocorporativo.bean.reformas;

import java.io.Serializable;

public class AprobEjerSocBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private String utilidad;
    private String perdida;

    private String aprobDicFiscal;
    private String decreDividendos;
    private String ratifiConsejeros;
    private String ratifiFuncionarios;
    private String ratifiComisarios;
    private String desigConsejeros;
    private String desigFuncionarios;
    private String desigComisarios;
    private String otorgaPoderes;
    private String revocaPoderes;

    public AprobEjerSocBean(String utilidad
                           ,String perdida
    ){
    	this.utilidad = utilidad;
    	this.perdida = perdida;
    }

    public AprobEjerSocBean(String aprobDicFiscal
                           ,String decreDividendos
                           ,String ratifiConsejeros
                           ,String ratifiFuncionarios
                           ,String ratifiComisarios
                           ,String desigConsejeros
                           ,String desigFuncionarios
                           ,String desigComisarios
                           ,String otorgaPoderes
                           ,String revocaPoderes
    ){
    	this.aprobDicFiscal = aprobDicFiscal;
    	this.decreDividendos = decreDividendos;
    	this.ratifiConsejeros = ratifiConsejeros;
    	this.ratifiFuncionarios = ratifiFuncionarios;
    	this.ratifiComisarios = ratifiComisarios;
    	this.desigConsejeros = desigConsejeros;
    	this.desigFuncionarios = desigFuncionarios;
    	this.desigComisarios = desigComisarios;
    	this.otorgaPoderes = otorgaPoderes;
    	this.revocaPoderes = revocaPoderes;
    }

	public String getUtilidad() {
		return utilidad;
	}

	public String getPerdida() {
		return perdida;
	}

	public String getAprobDicFiscal() {
		return aprobDicFiscal;
	}

	public String getDecreDividendos() {
		return decreDividendos;
	}

	public String getRatifiConsejeros() {
		return ratifiConsejeros;
	}

	public String getRatifiFuncionarios() {
		return ratifiFuncionarios;
	}

	public String getRatifiComisarios() {
		return ratifiComisarios;
	}

	public String getDesigConsejeros() {
		return desigConsejeros;
	}

	public String getDesigFuncionarios() {
		return desigFuncionarios;
	}

	public String getDesigComisarios() {
		return desigComisarios;
	}

	public String getOtorgaPoderes() {
		return otorgaPoderes;
	}

	public String getRevocaPoderes() {
		return revocaPoderes;
	}


}
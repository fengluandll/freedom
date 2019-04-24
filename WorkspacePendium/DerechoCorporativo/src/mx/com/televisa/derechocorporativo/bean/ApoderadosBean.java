/**
* Project: Derecho Corporativo
*
* File: ApoderadosBean.java
*
* Created on: Agosto 31, 2015 at 12:00
*
* Copyright (c) - Televisa - 2015
*/

package mx.com.televisa.derechocorporativo.bean;

import java.io.Serializable;
/**
 * Mapeo de Datos
 *
 * @author KAZ - CONSULTING/
 *         Iván Castañeda Loeza
 *         Jesús Argumedo
 *         Eduardo Nava
 *         Ernesto Corona Mendoza
 *
 *
 *
 * @version 1.0.0
 *
 * @date Agosto 31, 2015 at 12:00 pm
 *
 */
public class ApoderadosBean implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private int idEmpresa;
	private int idCatalogo;
	private int idCatalogoValor;
	private String desTipoElemento;
	private int numTipoPoder;
	private String desGrupo;
	private String desEscritura;
	private java.sql.Date fecha;
	private String tipoBaja;
	private String documento;
	private String RefDocumentum;
	private String checkRev;
	private String protoMedianteEsc;
	private String protoMedianteEscFecha;
	private String revocadoMediante;
	private String revocadoMedianteFecha;
	private String    idRevocacion;
	private String atributo3;
	private String atributo2;
	private String elijerevocacion;
	private String atributo15;
	
	public String getRefDocumentum() {
		return RefDocumentum;
	}
	public void setRefDocumentum(String refDocumentum) {
		RefDocumentum = refDocumentum;
	}
	public java.sql.Date getFecha() {
		return fecha;
	}
	public void setFecha(java.sql.Date fecha) {
		this.fecha = fecha;
	}
	public String getTipoBaja() {
		return tipoBaja;
	}
	public void setTipoBaja(String tipoBaja) {
		this.tipoBaja = tipoBaja;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public int getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public int getIdCatalogo() {
		return idCatalogo;
	}
	public void setIdCatalogo(int idCatalogo) {
		this.idCatalogo = idCatalogo;
	}
	public int getIdCatalogoValor() {
		return idCatalogoValor;
	}
	public void setIdCatalogoValor(int idCatalogoValor) {
		this.idCatalogoValor = idCatalogoValor;
	}
	public String getDesTipoElemento() {
		return desTipoElemento;
	}
	public void setDesTipoElemento(String desTipoElemento) {
		this.desTipoElemento = desTipoElemento;
	}
	public int getNumTipoPoder() {
		return numTipoPoder;
	}
	public void setNumTipoPoder(int numTipoPoder) {
		this.numTipoPoder = numTipoPoder;
	}
	public String getDesGrupo() {
		return desGrupo;
	}
	public void setDesGrupo(String desGrupo) {
		this.desGrupo = desGrupo;
	}
	public String getDesEscritura() {
		return desEscritura;
	}
	public void setDesEscritura(String desEscritura) {
		this.desEscritura = desEscritura;
	}
	public String getCheckRev() {
		return checkRev;
	}
	public void setCheckRev(String checkRev) {
		this.checkRev = checkRev;
	}
	public String getProtoMedianteEsc() {
		return protoMedianteEsc;
	}
	public void setProtoMedianteEsc(String protoMedianteEsc) {
		this.protoMedianteEsc = protoMedianteEsc;
	}
	public String getProtoMedianteEscFecha() {
		return protoMedianteEscFecha;
	}
	public void setProtoMedianteEscFecha(String protoMedianteEscFecha) {
		this.protoMedianteEscFecha = protoMedianteEscFecha;
	}
	public String getRevocadoMediante() {
		return revocadoMediante;
	}
	public void setRevocadoMediante(String revocadoMediante) {
		this.revocadoMediante = revocadoMediante;
	}
	public String getRevocadoMedianteFecha() {
		return revocadoMedianteFecha;
	}
	public void setRevocadoMedianteFecha(String revocadoMedianteFecha) {
		this.revocadoMedianteFecha = revocadoMedianteFecha;
	}
	public String getIdRevocacion() {
		return idRevocacion;
	}
	public void setIdRevocacion(String idRevocacion) {
		this.idRevocacion = idRevocacion;
	}
	public String getAtributo3() {
		return atributo3;
	}
	public void setAtributo3(String atributo3) {
		this.atributo3 = atributo3;
	}
	public String getAtributo2() {
		return atributo2;
	}
	public void setAtributo2(String atributo2) {
		this.atributo2 = atributo2;
	}
	public String getElijerevocacion() {
		return elijerevocacion;
	}
	public void setElijerevocacion(String elijerevocacion) {
		this.elijerevocacion = elijerevocacion;
	}
	public String getAtributo15() {
		return atributo15;
	}
	public void setAtributo15(String atributo15) {
		this.atributo15 = atributo15;
	}
	
	

}

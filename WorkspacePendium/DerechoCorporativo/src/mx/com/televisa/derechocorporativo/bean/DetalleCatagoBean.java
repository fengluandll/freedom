/**
* Project: Derecho Corporativo
*
* File: DetalleCatagoBean.java
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
public class DetalleCatagoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int idCatalogo;
	private int idCatalogoVal;
	private String identificador;
	private String Nombre;
	private String descripcion;
	private String valor;
	private String attr1;
	private String attr2;
	private String attr3;
	private String statusFlex;
	
	public int getIdCatinCat() {
		return idCatinCat;
	}
	public void setIdCatinCat(int idCatinCat) {
		this.idCatinCat = idCatinCat;
	}
	public String getCampCatinCat() {
		return campCatinCat;
	}
	public void setCampCatinCat(String campCatinCat) {
		this.campCatinCat = campCatinCat;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	private int    idCatinCat;
	private String campCatinCat;
	
	
	public int getIdCatalogoVal() {
		return idCatalogoVal;
	}
	public void setIdCatalogoVal(int idCatalogoVal) {
		this.idCatalogoVal = idCatalogoVal;
	}
	public int getIdCatalogo() {
		return idCatalogo;
	}
	public void setIdCatalogo(int idCatalogo) {
		this.idCatalogo = idCatalogo;
	}
	public String getIdentificador() {
		return identificador;
	}
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setdescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getAttr1() {
		return attr1;
	}
	public void setAttr1(String attr1) {
		this.attr1 = attr1;
	}
	public String getAttr2() {
		return attr2;
	}
	public void setAttr2(String attr2) {
		this.attr2 = attr2;
	}
	public String getAttr3() {
		return attr3;
	}
	public void setAttr3(String attr3) {
		this.attr3 = attr3;
	}
	public String getStatusFlex() {
		return statusFlex;
	}
	public void setStatusFlex(String statusFlex) {
		this.statusFlex = statusFlex;
	}

}

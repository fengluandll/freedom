/**
* Project: Derecho Corporativo
*
* File: ClasificacionBean.java
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
public class ClasificacionBean implements Serializable{

private static final long serialVersionUID = 1L;

private int    idCatalogoValor;
private String valCatVal;


public int getIdCatalogoValor() {
	return idCatalogoValor;
}
public void setIdCatalogoValor(int idCatalogoValor) {
	this.idCatalogoValor = idCatalogoValor;
}
public String getValCatVal() {
	return valCatVal;
}
public void setValCatVal(String valCatVal) {
	this.valCatVal = valCatVal;
}



}

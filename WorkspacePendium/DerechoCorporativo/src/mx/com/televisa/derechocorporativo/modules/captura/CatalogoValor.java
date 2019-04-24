/**
* Project: Derecho Corporativo
*
* File: CatalogoValor.java
*
* Created on: Agosto 31, 2015 at 12:00
*
* Copyright (c) - Televisa - 2015
*/

package mx.com.televisa.derechocorporativo.modules.captura;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import mx.com.televisa.derechocorporativo.reflexion.ReflexionUtil;

/**
 * LLamar al método fillObject en la clase ReflexionUtil
 * para obtener los metadatos de la tabla.
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
public class CatalogoValor {

	public int idCatalogoValor;
	public int idCatalogo;
	public String codCatVal;
	public String nomCatVal;
	public String valCatVal;
	public String desCatVal;
	public String atributo1;
	public String atributo2;

	/**
	 * Manda llamar al método fillObject que se encuentra almacenado en la clase ReflexionUtil
     * para obtener los metadata.
     * 
	 * @param luResultSet
	 * @param luResultSetMetaData
	 * @throws Exception
	 */
	public CatalogoValor(ResultSet luResultSet, ResultSetMetaData luResultSetMetaData) throws Exception {
		
		ReflexionUtil.fillObject(luResultSet, luResultSetMetaData, this, CatalogoValor.class);
	}

	public CatalogoValor(int idCatalogoValor, String valCatVal) {
		super();
		this.idCatalogoValor = idCatalogoValor;
		this.valCatVal = valCatVal;
	}
	
	

}

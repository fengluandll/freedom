/**
* Project: Derecho Corporativo
*
* File: Agrupaciones.java
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
 * Clase agrupadora de componentes web html.
 *
 * @author KAZ - CONSULTING/
 *         Iv�n Casta�eda Loeza
 *         Jes�s Argumedo
 *         Eduardo Nava
 *         Ernesto Corona Mendoza
 *
 * Manda llamar una funci�n para agrupar cat�logos.
 *
 * @version 1.0.0
 *
 * @date Agosto 31, 2015 at 12:00 pm
 *
 */
public class Agrupaciones {
	
	public int idAgrupacion;
	public int countCampos;
	public String isFlex;
	public String isPair;

   /**
    * Manda llamar al m�todo fillObject que se encuentra almacenado en la clase ReflexionUtil
    * para obtener los metadata.
    * 
    * @param tuResultSet
    * @param tuMetaData
    * @throws Exception
    */
	public Agrupaciones(ResultSet tuResultSet, ResultSetMetaData tuMetaData)
			throws Exception {
			ReflexionUtil.fillObject(tuResultSet, tuMetaData, this, Agrupaciones.class);
	}
}
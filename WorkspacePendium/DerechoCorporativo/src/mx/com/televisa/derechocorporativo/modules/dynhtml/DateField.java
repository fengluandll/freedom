/**
* Project: Derecho Corporativo
*
* File: Agrupaciones.java
*
* Created on: Agosto 31, 2015 at 12:00
*
* Copyright (c) - Televisa - 2015
*/

package mx.com.televisa.derechocorporativo.modules.dynhtml;

import mx.com.televisa.derechocorporativo.components.JSCal;
import mx.com.televisa.derechocorporativo.modules.flextabs.FlexColumn;

/**
 * Clase para crear componente web html date.
 *
 * @author KAZ - CONSULTING/
 *         Iván Castañeda Loeza
 *         Jesús Argumedo
 *         Eduardo Nava
 *         Ernesto Corona Mendoza
 *
 * Almacena un método que devuelve una cadena contenedora de un componente web html checkbox.
 *
 * @version 1.0.0
 *
 * @date Agosto 31, 2015 at 12:00 pm
 *
 */
public class DateField {

	public static String getDate(FlexColumn tuFlexColumn, String tsValue, int tiIdFlexTable){
		StringBuilder sb = new StringBuilder();
		String newFullId = tiIdFlexTable+"__"+tuFlexColumn.COD_FLEX_COLUM + "_FLEX_" + tuFlexColumn.ID_FLEX_COLUM; 
		//ULR 15/05/2017 se añadio onblur para evaluar fecha una vez que se termine de escribir
		sb.append("<input type='text' name='" + newFullId +"' id='" + newFullId  + "' size='" + tuFlexColumn.CAN_TAMANN_COLUM + "' value='" + tsValue + "' onkeyup='javascript:getMascaraFecha(this);' onblur='validarFecha(this)'>"+
				JSCal.getCalendarImageOnly(newFullId, "")
				);

		return sb.toString();
	}
	
	public static String getDateScriptOnly(FlexColumn flexColumn, int tiIdFlexTable){
		
		
		//return JSCal.getCalendarScriptOnly(flexColumn.COD_FLEX_COLUM, "");
		
		
		String newFullId = tiIdFlexTable + "__" + flexColumn.COD_FLEX_COLUM + "_FLEX_" + flexColumn.ID_FLEX_COLUM;
		
		//String fieldCodeId = flexColumn.COD_FLEX_COLUM;
		
		//String addjavaScript = "copyDateValue('" + newFullId + "','" + fieldCodeId + "');";
		
		
		return JSCal.getCalendarScriptOnly(newFullId, "");


		
	}
	
	
}

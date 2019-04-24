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

import mx.com.televisa.derechocorporativo.modules.flextabs.FlexColumn;

/**
 * Clase para crear componente web html checkbox.
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
public class CheckBox {

	/**
	 * 
	 * @param tuFlexColumn
	 * @param tsValue
	 * @param tsEditable
	 * @return String
	 * 
	 */
	public static String getCheckbox(FlexColumn tuFlexColumn, String tsValue, String tsEditable, int tiIdFlexTable){		
		String lstChecked;
		String lstValue;
		String lstArmaCheckbox;

		if(tsValue.equals("Si") || tsValue.equals("YES")){			
			lstChecked = "checked='checked'";
			lstValue = "value='Si'";
		}
		else {
			lstChecked = "";
			lstValue = "value='No'";
		}

		lstArmaCheckbox = "<input type='checkbox' name='"+ tuFlexColumn.COD_FLEX_COLUM +"' id='" + tiIdFlexTable + "__" + tuFlexColumn.COD_FLEX_COLUM + "' " + lstValue + " onclick='isChecked(this);' "+tuFlexColumn.DES_FORMULA+" " + lstChecked+ " "+tsEditable+">"+""+ "<br>";
		//lstArmaCheckbox = "<input type='checkbox' name='"+ tuFlexColumn.COD_FLEX_COLUM +"' id='" + tiIdFlexTable + "__" + tuFlexColumn.COD_FLEX_COLUM + "' " + lstValue + " onclick='isChecked(this);"+tuFlexColumn.DES_FORMULA+"'  " + lstChecked+ " "+tsEditable+">"+""+ "<br>";


		return lstArmaCheckbox;
	}

}
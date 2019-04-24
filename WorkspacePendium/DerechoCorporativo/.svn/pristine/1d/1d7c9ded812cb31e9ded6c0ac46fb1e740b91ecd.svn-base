/**
* Project: Derecho Corporativo
*
* File: JSCal.java
*
* Created on: Agosto 31, 2015 at 12:00
*
* Copyright (c) - Televisa - 2015
*/

package mx.com.televisa.derechocorporativo.components;

import mx.com.televisa.derechocorporativo.util.FacesUtils;

/**
 * Crear componente web html Calendario 
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
public class JSCal {

	private static final String DATE_FORMAT = "%d/%m/%Y";

    /**
     * Crear componente web html Calendario
     * 
     * @param tsField
     * @param tsAddJavascript
     * @return String
     */
	public static String getCalendar(String tsField, String tsAddJavascript) {
		StringBuilder lsStringBuilder = new StringBuilder();
		String        lsContextPath   = FacesUtils.getContextPath();

		lsStringBuilder.append("<img src=\"" + lsContextPath + "/img/calendar.gif\" id=\"" + tsField.replace(":", "_")  +"_trigger\"  title=\"Mostrar Calendario\" /> ");
		lsStringBuilder.append("<script type=\"text/javascript\">    	");
		lsStringBuilder.append("  new Calendar({");
		lsStringBuilder.append("          inputField: \"" + tsField  +"\",");
		lsStringBuilder.append("          dateFormat: \"" + DATE_FORMAT + "\",");
		lsStringBuilder.append("          trigger: \"" + tsField.replace(":", "_")  +"_trigger\",");
		lsStringBuilder.append("          bottomBar: true,										  ");
		lsStringBuilder.append("          onSelect: function() {");
		lsStringBuilder.append("                 var date = Calendar.intToDate(this.selection.get());");
		lsStringBuilder.append("                 this.hide();");
		lsStringBuilder.append("                 " + tsAddJavascript);
		lsStringBuilder.append("				  return false;");
		lsStringBuilder.append("         }");
		lsStringBuilder.append(" });");
		lsStringBuilder.append(" function clear_" + tsField.replace(":", "_")  +"() {");
		lsStringBuilder.append("	document.getElementById('" + tsField  +"').value = \"\";");
		lsStringBuilder.append("	}");
		lsStringBuilder.append("</script>");

		return lsStringBuilder.toString();
	}

	/**
	 * Crear componente web html Calendario
	 * 
	 * @param tsField
	 * @param tsAddJavascript
	 * @return String
	 */
	public static String getCalendarImageOnly(String tsField, String tsAddJavascript) {
		StringBuilder lsStringBuilder = new StringBuilder();
		String        lsContextPath   = FacesUtils.getContextPath();

		lsStringBuilder.append("<img src=\"" + lsContextPath + "/img/calendar.gif\" id=\"" + tsField.replace(":", "_")  +"_trigger\"  title=\"Mostrar Calendario\" /> ");

		return lsStringBuilder.toString();
	}

/**
 * Crear componente web html Calendario
 * 
 * @param tsField
 * @param tsAddJavascript
 * @return String
 */
	public static String getCalendarScriptOnly(String tsField, String tsAddJavascript) {
		StringBuilder lsStringBuilder = new StringBuilder();

		lsStringBuilder.append("  new Calendar({");
		lsStringBuilder.append("          inputField: \"" + tsField  +"\",");
		lsStringBuilder.append("          dateFormat: \"" + DATE_FORMAT + "\",");
		lsStringBuilder.append("          trigger: \"" + tsField.replace(":", "_")  +"_trigger\",");
		lsStringBuilder.append("          bottomBar: true,										  ");
		lsStringBuilder.append("          onSelect: function() {");
		lsStringBuilder.append("                 var date = Calendar.intToDate(this.selection.get());");
		lsStringBuilder.append("                 this.hide();");
		lsStringBuilder.append("                 " + tsAddJavascript);
		lsStringBuilder.append("				  return false;");
		lsStringBuilder.append("         }");
		lsStringBuilder.append(" });");
		lsStringBuilder.append(" function clear_" + tsField.replace(":", "_")  +"() {");
		lsStringBuilder.append("	document.getElementById('" + tsField  +"').value = \"\";");
		lsStringBuilder.append("	}");

		return lsStringBuilder.toString();
	}
	
	
	
}

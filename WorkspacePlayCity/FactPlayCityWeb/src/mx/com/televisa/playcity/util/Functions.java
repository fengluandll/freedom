/*
* Project: AISA
*
* File: Functions.java
*
* Created on: Abril 5, 2019 at 11:00
*
* Copyright (c) - Kaz Consulting - 2019
*/

package mx.com.televisa.playcity.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/*
* Funciones auxiliares   
* 
* @author Kaz Consulting / Jesus Argumedo
*
* @version 1.0.0
*
* @date Abril 5, 2019 at 11:00 am
*/

public class Functions {

	public static StringBuilder poVariableLog;
	public static String        psMsjValidacion;
	public static int           piErroresGrales;
	public static int           piTotalEmpleados;
	public static int           piRecibosValidos;
	public static int           piRecibosInvalidos;
		
	public Functions(){
		poVariableLog = new StringBuilder();
	}

	//Muestra mensaje en la página
	public static void showMessage(String tsTitle, 
								   String tsMessage, 
								   Mensajes tsMessageType) {
		switch (tsMessageType) {
		case INFO:
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,tsTitle,tsMessage));	
			break;
		case FATAL:
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_FATAL,tsTitle,tsMessage));
			break;
		case ERROR:
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,tsTitle,tsMessage));
			break;
		case WARN:
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN,tsTitle,tsMessage));
			break;
		case NULL:
			FacesContext.getCurrentInstance().addMessage(null,null);
			break;
		}
	}

	/**
	 * 
	 * @param component
	 *            id del componente por ejemplo form:display
	 */
	public static void updateComponent(String tsComponent) {
		RequestContext toRequestContext = RequestContext.getCurrentInstance();
		toRequestContext.update(tsComponent);
	}

	/**
	 * 
	 * @param component
	 *            id del componente por ejemplo form:display
	 */
	public static void renderedComponent(String tsComponent) {
		RequestContext toRequestContext = RequestContext.getCurrentInstance();
		toRequestContext.update(tsComponent);
	}

	/**
	 * 
	 * @param nomdialog
	 *            nombre del dialogo
	 * @param action
	 *            1 ocultar, 2 mostrar
	 */
	public static void manageDialog(String tsNomDialog, int tiAction) {
		RequestContext poContext = RequestContext.getCurrentInstance();

		if (tiAction == 1) {
			poContext.execute("PF('" + tsNomDialog + "').hide();");
		} else if (tiAction == 2) {
			poContext.execute("PF('" + tsNomDialog + "').show();");
		}

	}

	/**
	 * Metodo que obtiene la session actual de la aplicacion
	 * 
	 * @return session
	 */
	public static HttpSession getSession() {

		FacesContext loFacesContext = FacesContext.getCurrentInstance();
		HttpSession loSession = (HttpSession) loFacesContext.getExternalContext().getSession(true);
		return loSession;
	}

	/**
	 * Metodo que convierte una fecha de tipo java.util.Date a String
	 * 
	 * @param fecha
	 *            en java.util.Date
	 * @return regresa la fecha en String
	 */
	public static String dateToString(Date toFecha) {
		SimpleDateFormat loSimpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return loSimpleDateFormat.format(toFecha);
	}

	/**
	 * Metodo que convierte una fecha en horas y minutos
	 * 
	 * @param fecha
	 *            en java.util.Date
	 * @return regresa la fecha en String
	 */
	public static String dateToHoras(Date toFecha) {
		SimpleDateFormat loSimpleDateFormat = new SimpleDateFormat("HH:mm");
		return loSimpleDateFormat.format(toFecha);
	}

	// Devuele un java.util.Date desde un String en formato HH:mm
	// @param La fecha a convertir a formato date
	// @return Retorna la fecha en formato Date
	public static synchronized Date fromStringToDateHrs(String toFecha) {
		SimpleDateFormat loSimpleDateFormat = new SimpleDateFormat("HH:mm");
		Date loFechaEnviar = null;
		try {
			loFechaEnviar = loSimpleDateFormat.parse(toFecha);
			return loFechaEnviar;
		} catch (ParseException toException) {
			toException.printStackTrace();
			return null;
		}
	}

	// Devuele un java.util.Date desde un String en formato HH:mm
	// @param La fecha a convertir a formato date
	// @return Retorna la fecha en formato Date
	public static synchronized java.util.Date deStringToDate(String toFecha) {
		SimpleDateFormat loSimpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date loFechaEnviar = null;
		try {
			loFechaEnviar = loSimpleDateFormat.parse(toFecha);
			return loFechaEnviar;
		} catch (ParseException toException) {
			toException.printStackTrace();
			return null;
		}
	}
	
}
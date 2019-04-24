package mx.gob.tsjdf.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import mx.gob.tsjdf.cfdi.bo.ValidacionBo;
import mx.gob.tsjdf.model.LovDAO;

public class Funciones {

	static LovDAO lovDao;
	public static StringBuilder variableLog;
	public static String msjValidacion;
	public static int erroresGrales;
	public static int totalEmpleados;
	public static int recibosValidos;
	public static int recibosInvalidos;
	

	
	public Funciones(){
		variableLog = new StringBuilder();
	}

	/**
	 * 
	 * @param titulo
	 * @param mensaje
	 * @param tipoMensaje
	 *            INFO,ERROR,FATAL,WARN
	 */
	public static void mostrarMensaje(String titulo, String mensaje, String tipoMensaje) {

		if (tipoMensaje.equals("INFO")) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, mensaje));
		} else if (tipoMensaje.equals("ERROR")) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, titulo, mensaje));
		} else if (tipoMensaje.equals("FATAL")) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, titulo, mensaje));
		} else if (tipoMensaje.equals("WARN")) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, titulo, mensaje));
		}
	}

	/**
	 * 
	 * @param component
	 *            id del componente por ejemplo form:display
	 */
	public static void updateComponent(String component) {
		RequestContext requestContext = RequestContext.getCurrentInstance();
		requestContext.update(component);
	}

	/**
	 * 
	 * @param component
	 *            id del componente por ejemplo form:display
	 */
	public static void renderedComponent(String component) {
		RequestContext requestContext = RequestContext.getCurrentInstance();
		requestContext.update(component);
	}

	/**
	 * 
	 * @param nomdialog
	 *            nombre del dialogo
	 * @param action
	 *            1 ocultar, 2 mostrar
	 */
	public static void manageDialog(String nomdialog, int action) {
		RequestContext context = RequestContext.getCurrentInstance();

		if (action == 1) {
			context.execute("PF('" + nomdialog + "').hide();");
		} else if (action == 2) {
			context.execute("PF('" + nomdialog + "').show();");
		}

	}

	/**
	 * Metodo que obtiene la session actual de la aplicacion
	 * 
	 * @return session
	 */
	public static HttpSession getSession() {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		return session;
	}

	/**
	 * Metodo que convierte una fecha de tipo java.util.Date a String
	 * 
	 * @param fecha
	 *            en java.util.Date
	 * @return regresa la fecha en String
	 */
	public static String dateToString(java.util.Date fecha) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(fecha);
	}

	/**
	 * Metodo que convierte una fecha en horas y minutos
	 * 
	 * @param fecha
	 *            en java.util.Date
	 * @return regresa la fecha en String
	 */
	public static String dateToHoras(java.util.Date fecha) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		return sdf.format(fecha);
	}

	// Devuele un java.util.Date desde un String en formato HH:mm
	// @param La fecha a convertir a formato date
	// @return Retorna la fecha en formato Date
	public static synchronized java.util.Date deStringToDateHrs(String fecha) {
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("HH:mm");
		java.util.Date fechaEnviar = null;
		try {

			fechaEnviar = formatoDelTexto.parse(fecha);
			return fechaEnviar;
		} catch (ParseException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	// Devuele un java.util.Date desde un String en formato HH:mm
	// @param La fecha a convertir a formato date
	// @return Retorna la fecha en formato Date
	public static synchronized java.util.Date deStringToDate(String fecha) {
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date fechaEnviar = null;
		try {

			fechaEnviar = formatoDelTexto.parse(fecha);
			return fechaEnviar;
		} catch (ParseException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static String diaFeriado(String fecha, String numEmpleado, String fechaPaga, int idSolicitud, String numHEaIngresar, String horaIniFin) {
		lovDao = new LovDAO();
		String regreso = "OK";
		Integer diaFeriado = 0;
		String areaEmpleado = lovDao.obtenAreaEmpleado(numEmpleado);
		if (areaEmpleado.equals("1") || areaEmpleado.equals("2") || areaEmpleado.equals("3")
				|| areaEmpleado.equals("4")) {
			return regreso;
		} else {
			Integer validaMes = lovDao.obtenValidaMesFechaPagaFechaHE(fecha, fechaPaga);
			if(validaMes==0){
				regreso = "No se puede agregar esta fecha, ya que no corresponde a la quincena seleccionada";
				return regreso;
			}
			String nombreDia = lovDao.obtenNombreDia(fecha);
			if (nombreDia.equals("DOMINGO") || nombreDia.equals("SÁBADO")) {
				regreso = "No se puede agregar esta fecha, ya que es fin de semana";
				return regreso;
			}
			diaFeriado = lovDao.obtenDiaFeriado(fecha);
			if (diaFeriado > 0) {
				regreso = "No se puede agregar esta fecha, ya que es un dia feriado";
				return regreso;
			}
			Integer fechaRepetida = lovDao.obtenFechaRepetida(Integer.valueOf(idSolicitud), fechaPaga);
			if (fechaRepetida>0){
				regreso = "No se puede agregar esta fecha, porque ya fue ingresada con anterioridad";
				return regreso;
			}
			if(Integer.valueOf(horaIniFin)!=Integer.valueOf(numHEaIngresar)){
				regreso = "No se puede agregar esta fecha, porque las hora final menos la hora inicial no coincide con las horas ingresadas";
				return regreso;
			}
			Integer horasRegistradas = lovDao.obtenNumeroDeHERegistradas(Integer.valueOf(idSolicitud));
			if((horasRegistradas+Integer.valueOf(numHEaIngresar))>18){
				regreso = "No se puede agregar, ya que exece el numero de horas permitidas (18) por quincena: "+(horasRegistradas+Integer.valueOf(numHEaIngresar));
				return regreso;
			}
		}
		return regreso;
	}
}

package mx.kaz.mail;

import java.text.DecimalFormat;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import mx.kaz.beans.ProyectBean;
import mx.kaz.beans.RegLicitaciones;
import mx.kaz.beans.RegProyectoBean;
import mx.kaz.daos.MailDAO;

import org.apache.log4j.Logger;

public class SendMails {
	

	private static org.apache.log4j.Logger logger = Logger.getLogger(SendMails.class);
	FacesContext facesContext = FacesContext.getCurrentInstance();
	HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
	ResourceBundle bundle;
	//String destinatario;
	String asunto;
	 DecimalFormat myFormatter;
	 MailDAO mailDAO;
	 String destinatario;

	public SendMails() {
		bundle       = ResourceBundle.getBundle("DatosGenerales");
//        destinatario = bundle.getString("notificador.simplemail.sentTo");
        asunto       = bundle.getString("notificador.simplemail.asunto");
        myFormatter = new DecimalFormat("###,###,###.00");
        mailDAO = new MailDAO();
        destinatario = mailDAO.traeCorreos();
	}
	
	public boolean nuevoProyecto(ProyectBean proyectBean){
		String nomPto = (String)session.getAttribute("nomPto");
		boolean bandera = false;
		String contenido = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" + 
					"<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
					"<head>\n" +
					"</head>\n" +
					"<body>\n" +
					"<table width=\"400\" border=\"0\" align=\"center\" >\n" +
					"  <tr align=\"center\">\n" +
					"    <td colspan=\"2\" class=\"tituloTabla\"> <strong>"+ nomPto +"</strong> Agrego un nuevo proyecto</td>\n" +
					"  </tr>\n" +
					"  <tr>\n" +
					"    <td width=\"240\"><strong>Nombre del proyecto:</strong></td>\n" +
					"    <td width=\"144\"> "+ proyectBean.getName() +"</td>\n" +
					"  </tr>\n" +
					"  <tr>\n" +
					"    <td class=\"negritaSubtitulo\"><strong>Descripci&oacute;n del proyecto:</strong></td>\n" +
					"    <td> "+proyectBean.getDescription()+"</td>\n" +
					"  </tr>\n" +
					"  <tr>\n" +
					"    <td class=\"negritaSubtitulo\"><strong>Clave cartera:</strong></td>\n" +
					"    <td> " +proyectBean.getCode()+ "</td>\n" +
					"  </tr>\n" +
					"  <tr>\n" +
					"    <td class=\"negritaSubtitulo\"><strong>Periodo de ejecuci&oacute;n;</strong></td>\n" +
					"    <td>"+proyectBean.getPeriod()+"</td>\n" +
					"  </tr>\n" +
					"  <tr>\n" +
					"    <td class=\"negritaSubtitulo\"><strong>Inversi&oacute;n total del proyecto:</strong></td>\n" +
					"    <td>"+myFormatter.format(proyectBean.getTotalInvestment())+"</td>\n" +
					"  </tr>\n" +
					"  <tr>\n" +
					"    <td class=\"negritaSubtitulo\"><strong>Monto del ejercido:</strong></td>\n" +
					"    <td>"+myFormatter.format(proyectBean.getExercisedAmount())+"</td>\n" +
					"  </tr>\n" +
					"  <tr>\n" +
					"    <td class=\"negritaSubtitulo\"><strong>Monto asignado:</strong></td>\n" +
					"    <td>"+myFormatter.format(proyectBean.getAsignedAmount())+"</td>\n" +
					"  </tr>\n" +
					"  <tr>\n" +
					"    <td class=\"negritaSubtitulo\"><strong>Tipo de recurso:</strong></td>\n" +
					"    <td>"+proyectBean.getResourceType()+"</td>\n" +
					"  </tr>\n" +
					"</table>\n" +
					"</body>\n" +
					"</html>";
		try {
			 new SimpleMailSender().send(destinatario, asunto, contenido, false, new String[0]);
			 bandera = true;
			} catch (Exception e) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrio un error","Vuelve a intentarlo"); 
			    FacesContext.getCurrentInstance().addMessage(null, message);
				logger.error(e);
			}
		return bandera;
	}
	
	public boolean editaProyecto(RegProyectoBean regProyectoBean){
		String nomPto = (String)session.getAttribute("nomPto");
		boolean bandera = false;
		String contenido = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" + 
					"<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
					"<head>\n" +
					"</head>\n" +
					"<body>\n" +
					"<table width=\"400\" border=\"0\" align=\"center\">\n" +
					"  <tr align=\"center\">\n" +
					"    <td colspan=\"2\" class=\"tituloTabla\"> <strong>"+ nomPto +"</strong> Hizo una modificacion en el siguiente proyecto</td>\n" +
					"  </tr>\n" +
					"  <tr>\n" +
					"    <td width=\"240\"><strong>Nombre del proyecto:</strong></td>\n" +
					"    <td width=\"144\"> "+ regProyectoBean.getNomProyecto() +"</td>\n" +
					"  </tr>\n" +
					"  <tr>\n" +
					"    <td class=\"negritaSubtitulo\"><strong>Descripci&oacute;n del proyecto:</strong></td>\n" +
					"    <td> "+regProyectoBean.getDesPro()+"</td>\n" +
					"  </tr>\n" +
					"  <tr>\n" +
					"    <td class=\"negritaSubtitulo\"><strong>Clave cartera:</strong></td>\n" +
					"    <td> " +regProyectoBean.getClaveCartera()+ "</td>\n" +
					"  </tr>\n" +
					"  <tr>\n" +
					"    <td class=\"negritaSubtitulo\"><strong>Periodo de ejecuci&oacute;n:</strong></td>\n" +
					"    <td>"+regProyectoBean.getPeriodoEjecu()+";</td>\n" +
					"  </tr>\n" +
					"  <tr>\n" +
					"    <td class=\"negritaSubtitulo\"><strong>Inversi&oacute;n total del proyecto:</strong></td>\n" +
					"    <td>"+myFormatter.format(regProyectoBean.getInversionTotal())+"</td>\n" +
					"  </tr>\n" +
					"  <tr>\n" +
					"    <td class=\"negritaSubtitulo\"><strong>Monto del ejercido:</strong></td>\n" +
					"    <td>"+myFormatter.format(regProyectoBean.getMontoEjercido())+"</td>\n" +
					"  </tr>\n" +
					"  <tr>\n" +
					"    <td class=\"negritaSubtitulo\"><strong>Monto asignado:</strong></td>\n" +
					"    <td>"+myFormatter.format(regProyectoBean.getMontoAsignado())+"</td>\n" +
					"  </tr>\n" +
					"  <tr>\n" +
					"    <td class=\"negritaSubtitulo\"><strong>Tipo de recurso:</strong></td>\n" +
					"    <td>"+regProyectoBean.getTipoRecurso()+"</td>\n" +
					"  </tr>\n" +
					"</table>\n" +
					"</body>\n" +
					"</html>";
		try {
			 new SimpleMailSender().send(destinatario, asunto, contenido, false, new String[0]);
			 bandera = true;
			} catch (Exception e) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrio un error","Vuelve a intentarlo"); 
			    FacesContext.getCurrentInstance().addMessage(null, message);
				logger.error(e);
			}
		return bandera;
	}

	public boolean nuevaLicitacion(RegLicitaciones regLicitaciones,String accion){
		String nomPto = (String)session.getAttribute("nomPto");
		boolean bandera = false;
		String leyenda = "";
		if(accion.equals("nueva")){
			leyenda = "</strong> Agrego una nueva licitaci&oacute;n</td>\n";
		}else if(accion.equals("modifica")){
			leyenda = "</strong> modifico la siguiente licitaci&oacute;n</td>\n";
		}
		String contenido = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" + 
					"<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
					"<head>\n" +
					"</head>\n" +
					"<body>\n" +
					"<table width=\"400\" border=\"0\" align=\"center\" >\n" +
					"  <tr align=\"center\">\n" +
					"    <td colspan=\"2\" class=\"tituloTabla\"> <strong>"+ nomPto +"</strong> " + leyenda +"</td>\n" +
					"  </tr>\n" +
					"  <tr>\n" +
					"    <td width=\"240\"><strong>Tipo procedimiento:</strong></td>\n" +
					"    <td width=\"144\"> "+ regLicitaciones.getNomProcedure() +"</td>\n" +
					"  </tr>\n" +
					"  <tr>\n" +
					"    <td class=\"negritaSubtitulo\"><strong>Descripci&oacute;n de la obra:</strong></td>\n" +
					"    <td> "+regLicitaciones.getDescObra()+"</td>\n" +
					"  </tr>\n" +
					"  <tr>\n" +
					"    <td class=\"negritaSubtitulo\"><strong>Presupuesto base:</strong></td>\n" +
					"    <td> " +myFormatter.format(regLicitaciones.getPresuBase())+ "</td>\n" +
					"  </tr>\n" +
					"  <tr>\n" +
					"    <td class=\"negritaSubtitulo\"><strong>Publicaci&oacute;n de laobra</strong></td>\n" +
					"    <td>"+regLicitaciones.getPublConvocatoria()+"</td>\n" +
					"  </tr>\n" +
					"  <tr>\n" +
					"    <td class=\"negritaSubtitulo\"><strong>Apertura proposiciones:</strong></td>\n" +
					"    <td>"+regLicitaciones.getAperProposiciones()+"</td>\n" +
					"  </tr>\n" +
					"  <tr>\n" +
					"    <td class=\"negritaSubtitulo\"><strong>Participantes:</strong></td>\n" +
					"    <td>"+regLicitaciones.getParticipantes()+"</td>\n" +
					"  </tr>\n" +
					"  <tr>\n" +
					"    <td class=\"negritaSubtitulo\"><strong>Fecha de fallo:</strong></td>\n" +
					"    <td>"+regLicitaciones.getFecFallo()+"</td>\n" +
					"  </tr>\n" +
					"  <tr>\n" +
					"    <td class=\"negritaSubtitulo\"><strong>Contratista:</strong></td>\n" +
					"    <td>"+regLicitaciones.getNomContratista()+"</td>\n" +
					"  </tr>\n" +
					"  <tr>\n" +
					"    <td class=\"negritaSubtitulo\"><strong>Monto contratado:</strong></td>\n" +
					"    <td>"+regLicitaciones.getTotalMonto()+"</td>\n" +
					"  </tr>\n" +
					"  <tr>\n" +
					"    <td class=\"negritaSubtitulo\"><strong>Periodo ejecuci&oacute;n inicio:</strong></td>\n" +
					"    <td>"+regLicitaciones.getIniObra()+"</td>\n" +
					"  </tr>\n" +
					"  <tr>\n" +
					"    <td class=\"negritaSubtitulo\"><strong>Periodo ejecuci&oacute;n fin:</strong></td>\n" +
					"    <td>"+regLicitaciones.getFinObra()+"</td>\n" +
					"  </tr>\n" +
					"  <tr>\n" +
					"    <td class=\"negritaSubtitulo\"><strong>Observaciones:</strong></td>\n" +
					"    <td>"+regLicitaciones.getObservaciones()+"</td>\n" +
					"  </tr>\n" +
					"</table>\n" +
					"</body>\n" +
					"</html>";
		try {
			 new SimpleMailSender().send(destinatario, asunto, contenido, false, new String[0]);
			 bandera = true;
			} catch (Exception e) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrio un error","Vuelve a intentarlo"); 
			    FacesContext.getCurrentInstance().addMessage(null, message);
				logger.error(e);
			}
		return bandera;
	}
	
	

}

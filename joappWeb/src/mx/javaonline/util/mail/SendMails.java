package mx.javaonline.util.mail;

import java.io.File;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mx.javaonline.admin.beans.AsignaCursosUsuario;
import mx.javaonline.admin.beans.CutBean;
import mx.javaonline.beans.StudentPersonalBean;

import org.apache.log4j.Logger;

public class SendMails {
	

	private static org.apache.log4j.Logger logger = Logger.getLogger(SendMails.class);
	FacesContext   facesContext = FacesContext.getCurrentInstance();
	ResourceBundle bundle;
	ResourceBundle bundleMail;
	//String destinatario;
	String asunto;

	public SendMails() {
		bundle       = ResourceBundle.getBundle("DatosGenerales");
		bundleMail   = ResourceBundle.getBundle("ParametrosMail");
//        destinatario = bundle.getString("notificador.simplemail.sentTo");
        asunto       = bundle.getString("notificador.simplemail.asunto");
	}
	
	public boolean recuperaPassword(String pass,String correo){
		String destinatario = correo;
		boolean bandera = false;
		asunto = bundle.getString("contrasena.recuperada");
		String imagen = bundle.getString("mail.imagenes.encabezado.startonline");
		String contenido = "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
						"<head>\n" +
						"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
						"<title>Documento sin t�tulo</title>\n" +
						"</head>\n" +
						"\n" +
						"<body>\n" +
						"<table width=\"200\" border=\"0\" align=\"center\">\n" +
						"  <tr>\n" +
						"    <td colspan=\"2\"><img src=\"cid:image\"></td>\n" +
						"  </tr>\n" +
						"  <tr>\n" +
						"    <td colspan=\"2\"><p>Este es un correo de recuperaci�n de contrase�a, recuerda  cambiarla en tu perfil ya que est�s dentro del sistema.</p></td>\n" +
						"  </tr>\n" +
						"  <tr>\n" +
						"    <td align=\"right\">&nbsp;</td>\n" +
						"    <td>&nbsp;</td>\n" +
						"  </tr>\n" +
						"  <tr>\n" +
						"    <td align=\"right\">Password :</td>\n" +
						"    <td><strong> " + pass +"</strong></td>\n" +
						"  </tr>\n" +
						"  <tr>\n" +
						"    <td colspan=\"2\"><p>Saludos, </p></td>\n" +
						"  </tr>\n" +
						"  <tr>\n" +
						"    <td colspan=\"2\"><p>Equipo de startOnline</p></td>\n" +
						"  </tr>\n" +
						"  <tr>\n" +
						"    <td colspan=\"2\"><p><a href=\"http://startonline.com.mx:2425/joappWeb/login.xhtml\">Entra aqui</a></p></td>\n" +
						"  </tr>\n" +
						"  <tr>\n" +
						"    <td colspan=\"2\"><p><a href=\"www.startonline.com.mx\">www.startonline.com.mx</a></p></td>\n" +
						"  </tr>\n" +
						"  <tr>\n" +
						"    <td colspan=\"2\"><p>Llevando el conocimiento a tus manos</p></td>\n" +
						"  </tr>\n" +
						"</table>\n" +
						"</body>\n" +
						"</html>";
		try {
			 //new SimpleMailSender().send(destinatario, asunto, contenido, false, new String[0]);
			 new SendEmail(destinatario,asunto).enviaConImagen(bundleMail.getString("notificador.simplemail.ruta.imagen2"),contenido);
			 bandera = true;
			} catch (Exception e) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrio un error","Vuelve a intentarlo"); 
			    FacesContext.getCurrentInstance().addMessage(null, message);
				logger.error(e);
			}
		return bandera;
	}

	public void enviaMailBienvenida(){
//		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
//		logger.debug(request.getContextPath());
//		String url = request.getContextPath() + "/images/bienvenido_startOnline_mail.jpg";
		
//		File temp = new File("C:\\Users\\Argumedo\\Documents\\test_cuerpo_correo\\bienvenido_startOnline_mail.jpg");
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		String destinatario = (String) session.getAttribute("user");//+","+ bundle.getString("notificador.simplemail.remitente");
		
		
		String imagen = bundleMail.getString("notificador.simplemail.ruta.imagen1");
		asunto       = bundleMail.getString("notificador.simplemail.mail.bienvenida");
//		File temp = new File(imagen);
		
//		logger.debug("temp.getAbsolutePath() "+temp.getAbsolutePath());
//		String[] file = {temp.getAbsolutePath()};
		String contenido = "<html>" +
                "<body>" +
                "<img src=\"cid:image\">"+
			   // "<img src=\" "+imagen +"\" " +
             "</body></html>";

			try {
				//new SimpleMailSender().send(destinatario, asunto, contenido, false, new String[0]);
				new SendEmail(destinatario,asunto).enviaConImagen(imagen, contenido);
				
			} catch (Exception e) {
				logger.error(e);
			}
			enviaDatosCuenta();
			
			
			
//		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correo enviado","Exito" ); 
//	    FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public void enviaDatosCuenta(){
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
//		logger.debug(session.getAttribute("surnames"));
//		logger.debug(session.getAttribute("user"));
//		logger.debug(session.getAttribute("numCta"));
//		logger.debug(session.getAttribute("nomRol"));
//		logger.debug(session.getAttribute("pass"));
		String asunto = bundle.getString("detalle.cuenta");
		String imagen = bundleMail.getString("notificador.simplemail.ruta.imagen2");
		String destinatario =  (String) session.getAttribute("user");//+","+ bundle.getString("notificador.simplemail.remitente");
		String contenido = "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
				"<head>\n" +
				"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
				"<title>datos de la cuenta</title>\n" +
				"</head>\n" +
				"\n" +
				"<body>\n" +
				"<p>&nbsp;</p>\n" +
				"<table width=\"784\" border=\"0\" align=\"center\">\n" +
				"  <tr>\n" +
				"    <td colspan=\"2\"><img src=\"cid:image\"></td>\n" +
				"  </tr>\n" +
				"  <tr>\n" +
				"    <td>&nbsp;</td>\n" +
				"    <td>&nbsp;</td>\n" +
				"  </tr>\n" +
				"  <tr>\n" +
				"    <td colspan=\"2\"><p><strong>Bienvenido</strong> " + session.getAttribute("surnames") + "</p></td>\n" +
				"  </tr>\n" +
				"  <tr>\n" +
				"    <td colspan=\"2\"><p>Te damos la cordial bienvenida a <strong>startOnline </strong>sus datos de pago han sido recibidos</p></td>\n" +
				"  </tr>\n" +
				"  <tr>\n" +
				"    <td colspan=\"2\"><p><strong>Los datos de tu  cuenta</strong></p></td>\n" +
				"  </tr>\n" +
				"  <tr>\n" +
				"    <td align=\"center\"><strong>Usuario</strong></td>\n" +
				"    <td> " + session.getAttribute("user") +"</td>\n" +
				"  </tr>\n" +
				"  <tr>\n" +
				"    <td align=\"center\"><strong>Contrase�a</strong></td>\n" +
				"    <td> " + session.getAttribute("pass") + "</td>\n" +
				"  </tr>\n" +
				"  <tr>\n" +
				"    <td align=\"center\"><strong>No.  Cuenta</strong></td>\n" +
				"    <td> " + session.getAttribute("numCta") + "</td>\n" +
				"  </tr>\n" +
				"  <tr>\n" +
				"    <td align=\"center\"><strong>Rol</strong></td>\n" +
				"    <td> " + session.getAttribute("nomRol") +"</td>\n" +
				"  </tr>\n" +
				"  <tr>\n" +
				"    <td colspan=\"2\" align=\"center\">&nbsp;</td>\n" +
				"  </tr>\n" +
				"  <tr>\n" +
				"    <td colspan=\"2\" align=\"center\"><strong>Cursos  contratados</strong></td>\n" +
				"  </tr>\n" +
				"  <tr>\n" +
				"    <td colspan=\"2\" align=\"center\"><em>" +session.getAttribute("allCourses") +"</em></td>\n" +
				"  </tr>\n" +
				"  <tr>\n" +
				"    <td colspan=\"2\" align=\"center\">&nbsp;</td>\n" +
				"  </tr>\n" +
				"  <tr>\n" +
				"    <td colspan=\"2\" align=\"left\"><p><strong style='color:blue;'>Para una mejor experiencia en la aplicacion te recomiendo que uses el navegador Chrome</strong></p></td>\n" +
				"  </tr>\n" +
				"  <tr>\n" +
				"    <td colspan=\"2\" align=\"left\"><p><a href='http://www.google.com/intl/es-419/chrome/'><strong style='color:blue;'>Dercarga Chrome Aqui</strong></a></p></td>\n" +
				"  </tr>\n" +
				"  <tr>\n" +
				"    <td colspan=\"2\" align=\"left\"><p><strong>A partir de este momento ya puede ingresar a sus cursos. Que  lo disfrutes.</strong></p></td>\n" +
				"  </tr>\n" +
				"  <tr>\n" +
				"    <td colspan=\"2\" align=\"left\"><p>Saludos, <br />\n" +
				"    Equipo de startOnline</p></td>\n" +
				"  </tr>\n" +
				"  <tr>\n" +
				"    <td colspan=\"2\"><p><a href=\"http://startonline.com.mx:2425/joappWeb/login.xhtml\">Entra aqui</a></p></td>\n" +
				"  </tr>\n" +
				"  <tr>\n" +
				"    <td colspan=\"2\" align=\"left\"><p><strong>www.startonline.com.mx</strong></p></td>\n" +
				"  </tr>\n" +
				"</table>\n" +
				"<p>&nbsp;</p>\n" +
				"</body>\n" +
				"</html>";
		
		try {
			 //new SimpleMailSender().send(destinatario, asunto, contenido, false, new String[0]);
			new SendEmail(destinatario,asunto).enviaConImagen(imagen, contenido); 
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correo enviado","Exito" ); 
			 FacesContext.getCurrentInstance().addMessage(null, message);
			} catch (Exception e) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Correo NO enviado","Error: "+e.getMessage() ); 
			    FacesContext.getCurrentInstance().addMessage(null, message);
				logger.error(e);
			}
		
	}
	
	
	
	
	
	public boolean enviaPregunta(CutBean cutBean,String pregunta,String[] files){
		String destinatario = bundle.getString("envia.pregunta.to");
		boolean bandera = false;
		asunto = bundle.getString("respuesta.pregunta");
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		StudentPersonalBean studentPersonalBean = (StudentPersonalBean)session.getAttribute("studentPersonalBean");
		
		String contenido = "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
						"<head>\n" +
						"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
						"<title>Pregunta</title>\n" +
						"</head>\n" +
						"\n" +
						"<body>\n" +
						"<table width=\"400\" border=\"0\" align=\"center\">\n" +
						"  <tr>\n" +
						"    <td><em><strong>NUM CTA.</strong></em></td>\n" +
						"    <td>"+studentPersonalBean.getNumCta()+"</td>\n" +
						"  </tr>\n" +
						"  <tr>\n" +
						"    <td><em><strong>NOMBRES</strong></em></td>\n" +
						"    <td>"+studentPersonalBean.getGivenNames()+"</td>\n" +
						"  </tr>\n" +
						"  <tr>\n" +
						"    <td><em><strong>APELLIDOS</strong></em></td>\n" +
						"    <td>"+studentPersonalBean.getSurNames()+"</td>\n" +
						"  </tr>\n" +
						"  <tr>\n" +
						"    <td><em><strong>TELEFONO</strong></em></td>\n" +
						"    <td>"+studentPersonalBean.getPhone()+"</td>\n" +
						"  </tr>\n" +
						"  <tr>\n" +
						"    <td><em><strong>MAIL</strong></em></td>\n" +
						"    <td>"+studentPersonalBean.getMail()+"</td>\n" +
						"  </tr>\n" +
						
						"  <tr>\n" +
						"    <td><em><strong>CURSO</strong></em></td>\n" +
						"    <td> "+cutBean.getCourseName()+"</td>\n" +
						"  </tr>\n" +
						"  <tr>\n" +
						"    <td><em><strong>UNIDAD</strong></em></td>\n" +
						"    <td>"+cutBean.getUnitName()+"</td>\n" +
						"  </tr>\n" +
						"  <tr>\n" +
						"    <td><em><strong>TOPIC</strong></em></td>\n" +
						"    <td>"+cutBean.getTopicName()+"</td>\n" +
						"  </tr>\n" +
						"  <tr bgcolor='#666666'>\n" +
						"    <td colspan=\"2\" align=\"center\"><em><strong>PREGUNTA</strong></em></td>\n" +
						"  </tr>\n" +
						"  <tr>\n" +
						"    <td colspan=\"2\">"+pregunta+"</td>\n" +
						"  </tr>\n" +
						"</table>" +
						"</body>\n" +
						"</html>";
		try {
			 //new SimpleMailSender().send(destinatario, asunto, contenido, false, files);
			new SendEmail(destinatario,asunto).enviaSinImagen(contenido);
			 bandera = true;
			} catch (Exception e) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ups algo paso al enviar tu pregunta","Por favor vuelve a intentarlo" ); 
			    FacesContext.getCurrentInstance().addMessage(null, message);
				logger.error(e);
			}
		return bandera;
	}
	
	
	
	public static void main(String[] args) {
		new SendMails().enviaMailBienvenida();
	}
}
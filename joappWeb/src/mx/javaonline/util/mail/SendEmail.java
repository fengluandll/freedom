/**
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 */
package mx.javaonline.util.mail;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * @author Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 */
public class SendEmail {
	
	ResourceBundle bundle;
	Message 	   message;

	public SendEmail(String para,String asunto) {
		  bundle 	    = ResourceBundle.getBundle("ParametrosMail");
		  
		  // Recipient's email ID needs to be mentioned.
	      String to = para;
	      
	      // Sender's email ID needs to be mentioned
	      String from = bundle.getString("notificador.simplemail.from");
	      final String username = bundle.getString("notificador.simplemail.from");
	      final String password =bundle.getString("notificador.simplemail.contrasenia");
	      String host = bundle.getString("notificador.simplemail.servidorSMTP");
	      Properties props = new Properties();
	      props.put("mail.smtp.auth", bundle.getString("notificador.simplemail.auth"));
	      props.put("mail.smtp.starttls.enable", bundle.getString("notificador.simplemail.starttls.enable"));
	      props.put("mail.smtp.host", host);
	      props.put("mail.smtp.port", bundle.getString("notificador.simplemail.puertoSMTP"));

	      // Get the Session object.
	      Session session = Session.getInstance(props,
	         new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	               return new PasswordAuthentication(username, password);
		   }
	         });

	      try {
		   // Create a default MimeMessage object.
		   message = new MimeMessage(session);
		
		   // Set From: header field of the header.
		   message.setFrom(new InternetAddress(from,bundle.getString("notificador.simplemail.nombre.aparecer.correo")));
		
		   // Set To: header field of the header.
		   message.setRecipients(Message.RecipientType.TO,
	               InternetAddress.parse(to));
		   
		   // Set Subject: header field
		   message.setSubject(asunto);
		
		   // Now set the actual message
		   //message.setText(mailParamBean.getContenidoHtml());

	      } catch (MessagingException e) {
	         throw new RuntimeException(e);
	      } catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Enviar todo el contenido html incluyendo la imagen
	 * @param rutaImagen Imagen a adjuntar
	 */
	public void enviaConImagen(String rutaImagen,String contenido){
		try{
		    /***Adjunta imagen dentro del body***/
		   // This mail has 2 part, the BODY and the embedded image
	         MimeMultipart multipart = new MimeMultipart("related");

	         // first part (the html)
	         BodyPart messageBodyPart = new MimeBodyPart();
	        // String htmlText = "<img src=\"cid:image\">";
	         messageBodyPart.setContent(contenido, "text/html");
	         // add it
	         multipart.addBodyPart(messageBodyPart);

	         // second part (the image)
	         messageBodyPart = new MimeBodyPart();
	         DataSource fds = new FileDataSource(rutaImagen);

	         messageBodyPart.setDataHandler(new DataHandler(fds));
	         messageBodyPart.setHeader("Content-ID", "<image>");

	         // add image to the multipart
	         multipart.addBodyPart(messageBodyPart);

	         // put everything together
	         message.setContent(multipart);
	         Transport.send(message);
	         System.out.println("Sent message successfully....");
		} catch (MessagingException e) {
	         throw new RuntimeException(e);
		}
	}
	
	/**
	 * Envi contenido HTML sin incluir imagenes
	 * @param contenidoHtml
	 */
	public void enviaSinImagen(String contenidoHtml){
		  
		try {
			  message.setContent(
					  contenidoHtml,
					  "text/html");
			  Transport.send(message);
			  System.out.println("Sent message successfully....");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		  
	}
	
	public static void main(String[] args) {
//		MailParamBean mailParamBean = new MailParamBean();
//		mailParamBean.setContenidoHtml("Este es una prueba del contenido HTML");
//		mailParamBean.setFrom("no-reply@startonline.com.mx");
//		mailParamBean.setNomCorreoAparecer("Bienvido a startOnline");
//		mailParamBean.setPassword("noReply");
//		mailParamBean.setSubJect("Bienvenido a startOnline");
//		mailParamBean.setTo("argumedo40@hotmail.com");
//		mailParamBean.setUserName("no-reply@startonline.com.mx");
		new SendEmail("argumedo40@hotmail.com","Bienvenido a startOnline").enviaSinImagen("<html><h1>hola</h1></html>");;
	}
}

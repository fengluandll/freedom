package com.movemini.notificacion;


import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.movemini.config.HardCodeConstants;
import com.movemini.data.SSDB;

public class EnviarCorreo {

    public static void EnviarTo(String to, String cc, String cco, String from, MailFormat mail, List<File> adjuntos) {
        System.out.println("Empezando con envio de correo");
        String host = HardCodeConstants.SMTP_HOST;//SSDB.getConfiById(1);
        String port = HardCodeConstants.SMTP_PORT;//SSDB.getConfiById(2);
        String _auth = HardCodeConstants.SMTP_AUTH ;
        String tls = HardCodeConstants.SMTP_TLS;
        final String user = HardCodeConstants.SMTP_MAIL; //SSDB.getConfiById(3);
        final String pass = HardCodeConstants.SMTP_PWD;//SSDB.getConfiById(4);
        
        boolean val = !"".equals(host) && !"".equals(port) && !"".equals(user) && !"".equals(pass)
                && host != null && port != null && user != null && pass != null;
        if (val) {
            Properties prs = new Properties();
            prs.setProperty("mail.smtp.host", host);
            prs.setProperty("mail.smtp.auth", _auth);
            prs.setProperty("mail.smtp.port", port);
            prs.put("mail.smtp.starttls.enable", tls);
            
            
            javax.mail.Authenticator auth = new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(user, pass);
                }
            };
            
            Session session = Session.getInstance(prs, auth);
            
            
            try {
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(from));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

                if(cc != null && !cc.equals("")) {
                	message.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));
                }
                
                if(cco != null && !cco.equals("")) {
                	message.addRecipient(Message.RecipientType.BCC, new InternetAddress(cco));
                }
                
                
                
                message.setSubject(mail.getSubject());
                message.setContent(mail.getContent(), "text/html; charset=UTF-8");
                
                MimeBodyPart messageBodyPart = new MimeBodyPart();
                messageBodyPart.setContent(mail.getContent(), "text/html; charset=UTF-8");

                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(messageBodyPart);

                if (adjuntos != null && adjuntos.size() > 0) {
                    for (File rutaAdjunto : adjuntos) {
                        
                    	messageBodyPart = new MimeBodyPart();
                       
                        if (rutaAdjunto.exists()) {
                            DataSource source = new FileDataSource(rutaAdjunto);
                            messageBodyPart.setDataHandler(new DataHandler(source));
                            messageBodyPart.addHeader("Content-Type", "application/pdf");
                            messageBodyPart.setFileName(rutaAdjunto.getName());
                            multipart.addBodyPart(messageBodyPart);
                        	
                        }
                    }
                }
                
                message.setContent(multipart);
                message.saveChanges();
                Transport.send(message);
                System.out.println("correo enviado a: " + to);
            } catch (MessagingException mex) {
                mex.printStackTrace();
            }
        } else {
            System.out.println("Faltan configuraciones");
        }
    }

    public static void EnviarTo(String to, String from, MailFormat mail) {
        System.out.println("Empezando con envio de correo");
        String host = HardCodeConstants.SMTP_HOST;//SSDB.getConfiById(1);
        String port = HardCodeConstants.SMTP_PORT;//SSDB.getConfiById(2);
          String _auth = HardCodeConstants.SMTP_AUTH ;
        String tls = HardCodeConstants.SMTP_TLS;
        final String user = HardCodeConstants.SMTP_MAIL; //SSDB.getConfiById(3);
        final String pass = HardCodeConstants.SMTP_PWD;//SSDB.getConfiById(4);
        
        boolean val = !"".equals(host) && !"".equals(port) && !"".equals(user) && !"".equals(pass)
                && host != null && port != null && user != null && pass != null;
        
        if (val) {
            Properties prs = System.getProperties();
            prs.setProperty("mail.smtp.host", host);
            prs.setProperty("mail.smtp.auth", _auth);
            prs.setProperty("mail.smtp.port", port);
            prs.put("mail.smtp.starttls.enable", tls);
            
            Session session = Session.getInstance(prs,
                    new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(user, pass);
                }
            });
            try {
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(from));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(
                        to));
                message.setSubject(mail.getSubject());
                message.setContent(mail.getContent(),"text/html; charset=utf-8");
                message.saveChanges();
                Transport.send(message);
                System.out.println("Enviado Correctamente!");
            } catch (MessagingException mex) {
                mex.printStackTrace();
            }
        }else{
           System.out.println("Faltan propiedades");
        }
        	
        	
    }

}

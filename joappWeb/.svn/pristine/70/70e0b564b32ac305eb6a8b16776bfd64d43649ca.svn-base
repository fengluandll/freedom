package mx.javaonline.util.mail;

import java.util.ResourceBundle;


public class EjemploMailSender {
	
	 ResourceBundle bundle;
	 String destinatario;
	 String asunto;
	 
	public EjemploMailSender() {
		bundle       = ResourceBundle.getBundle("DatosGenerales");
        destinatario = bundle.getString("notificador.simplemail.sentTo");
        asunto       = bundle.getString("notificador.simplemail.asunto");

	}
	
    public void sendMail(){

        String contenido = "<html>" +
                           "<body>" +
                            "<img src=\"http://localhost:8080/joappWeb/images/bienvenido_startOnline_mail.jpg\">" +
                           
                        "</body></html>";
    
        try {
            new SimpleMailSender().send(destinatario, asunto, contenido, false, new String[0]);
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }

	public static void main(String[] args) {
		new EjemploMailSender().sendMail();

	}

}

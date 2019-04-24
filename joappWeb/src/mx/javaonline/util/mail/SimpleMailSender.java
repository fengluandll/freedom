package mx.javaonline.util.mail;

import java.io.File;
import java.util.ResourceBundle;

public class SimpleMailSender {
    
    private JavaMailWrapper mailer;
            
            /**
             * 
             */
            public SimpleMailSender()  {
                    
            ResourceBundle bundle = ResourceBundle.getBundle("DatosGenerales");
            
            String servidorSMTP = bundle.getString("notificador.simplemail.servidorSMTP");
            String puertoSMTP = bundle.getString("notificador.simplemail.puertoSMTP");
            String remitente = bundle.getString("notificador.simplemail.remitente");

            String usuario = remitente;
            String contrasenia =bundle.getString("notificador.simplemail.contrasenia");

            boolean auth = bundle.getString("notificador.simplemail.auth").equals("true");
            boolean ssl = bundle.getString("notificador.simplemail.ssl").equals("true");
            boolean useAuthenticator =bundle.getString("notificador.simplemail.useAuthenticator").equals("true");
            boolean useHTML = bundle.getString("notificador.simplemail.useHTML").trim().equals("true");

            mailer = new JavaMailWrapper();

            mailer.setServerParameters(servidorSMTP, puertoSMTP, remitente, usuario, contrasenia);
            mailer.setAdicionalConfiguration(useHTML, auth, ssl, useAuthenticator);
            }
            
            public void setBCC(String bcc) {
                    //copia oculta
                    mailer.setBCC(bcc);
            }
            
            
        public void send(String email, String asunto, String contenido, boolean useThread, String[] fileName) throws Exception{
            mailer.setMailParameters(email,asunto,contenido,fileName);
            send(useThread);
        }    
        

        //Eliminar
        //Es preferible mantener la version que sa adjuntos como String, ya que esta es compatible con mail 1.3
        public void send(String email, String asunto, String contenido, boolean useThread, File fileName) throws Exception{
            mailer.setMailParameters(email,asunto,contenido,fileName);
            send(useThread);
        }    
        
        
        private void send(boolean useThread) throws Exception {
            
            if(useThread) {
                    mailer.start();        
            } else {
                    mailer.sendMail();
            }
        }
        
        public static void main(String[] args) {

             try {
                String destinatario = "argumedo_21@yahoo.com.mx";

                String asunto = "Mail Test.";
                String contenido = "<html>" +
                                    "<body>" +
                                    "<h1>Prueba de Correo </h1>" +
                                    "Mail OK " +
                                    "<br>" +
                                    "</body></html>";
       
                boolean useThread = false;

                 new SimpleMailSender().send(destinatario, asunto, contenido, useThread, new String[0]);
                
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
}

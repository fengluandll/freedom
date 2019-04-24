package mx.com.televisa.divfilmica.mail;

import java.io.File;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import mx.com.televisa.divfilmica.beans.LogBean;

import mx.com.televisa.divfilmica.beans.TNoCosteadasBean;
import mx.com.televisa.divfilmica.readFiles.ReadFile;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class SendMails {
    
    static Logger logger = Logger.getLogger(SendMails.class);
    ResourceBundle bundle;
    String email;
    String asunto;
    String destinatario;
    String fileName;
    
    public SendMails() {
        PropertyConfigurator.configure("src/log4j.properties");
        bundle = ResourceBundle.getBundle("notificador");
        destinatario = bundle.getString("notificador.simplemail.sentTo");
        asunto = bundle.getString("notificador.simplemail.asunto");
    }
    
    public void sendMail(LogBean logBean,String[] fileAttach){

        String contenido = "<html>" +
                           "<body>" +
                        "<p align=\"center\">\n" + 
                        "  <b><font color=\"#ff0000\" size=\"6\">La carga autom&#225;tica present&#243; un error \n" + 
                        "  cr&#237;tico y fu&#233; detenida.</font></b>\n" + 
                        "</p>\n" + 
                        "<p align=\"center\">\n" + 
                        "  <b><font size=\"5\">Cod. Error:  </font></b><font size=\"5\">"+logBean.getCodError()+"\n" + 
                        "</font>    </p>\n" + 
                        "<p align=\"center\">\n" + 
                        "  <b><font size=\"5\">Error:</font></b><font size=\"5\">"+logBean.getDescError() +"\n" + 
                        "</font>    </p>\n" + 
                        "<p align=\"center\">\n" + 
                        "  <b><font size=\"5\">Regla essbase:</font></b><font size=\"5\"> "+logBean.getReglaEssbase()+"\n" + 
                        "</font>    </p>\n" + 
                        "<p align=\"center\">\n" + 
                        "  <b><font size=\"5\">Proceso:</font></b><font size=\"5\">" +logBean.getNomProceso()+"\n" +
                        "</p>"+
                        "</body></html>";
    
        try {
            new SimpleMailSender().send(destinatario, asunto, contenido, false, fileAttach);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
    }
    
    public void sendMailInit(){

        try{  

                    String asunto = bundle.getString("com.mx.televisa.divfilmica.nom.desarrollo.produccion")+":Inicio de Proceso de Carga Automática" ;
                    String contenido = "<html>" +
                                                       "<body>" +
                                                       "<h1>El proceso de Carga Automática ha iniciado</h1>" + 
                                                       "<br>" +
                                                       new Date() +
                                                       "<br>" +
                                                       "</body></html>";
                                                   
                    boolean useThread = false;
                    new SimpleMailSender().send(destinatario, asunto, contenido, useThread, new String[0]);
               }  
               catch(Exception e){  
                       logger.error("ERROR EN EL ENVIO DE CORREO:",e);
               }  
    }
    
    public void sendMailEachStep(String fileName){
                  
                    try{  

                        String asunto = bundle.getString("com.mx.televisa.divfilmica.nom.desarrollo.produccion")+":Ha finalizado el proceso:" + fileName ;
                        String contenido = "<html>" +
                                                           "<body>" +
                                                           "<h1>Ha finalizado el proceso:" +fileName+ "</h1>" + 
                                                           "<br>" +
                                                           new Date() +
                                                           "<br>" +
                                                           "</body></html>";
                                                       
                        boolean useThread = false;
                        new SimpleMailSender().send(destinatario, asunto, contenido, useThread, new String[0]);
                       }  
                       catch(Exception e)  
                       {  
                               logger.error("ERROR EN EL ENVIO DE CORREO:",e);
                       }  
                        
        } 
    
    public void sendMailEndFinalStep(String[] fileAttach, String finalMessage){
     
        String desc = "";
        String foundErr;
                        
        SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");
        String fecha = format.format(new Date());
        File temp = null;
        
                
                        ReadFile description = new ReadFile();
                        foundErr = description.listenDirUri(bundle.getString("com.mx.televisa.divfilmica.ruta.uri"), bundle.getString("com.mx.televisa.divfilmica.filter.uri"));
                        logger.info("Archivos de error:" + foundErr);
                        if(foundErr.equals("")){
                                desc = "<br>El Proceso concluyó sin archivos de logs de error en la carga";
                        }else{
                                desc = "<br>El proceso de carga automática generó los siguientes archivos de log de error: " + foundErr;
                        }
                        
                
        try {

                               String asunto = bundle.getString("com.mx.televisa.divfilmica.nom.desarrollo.produccion")+":Fin del Proceso de Carga Automática";
                               String contenido = "<html>" +
                                                   "<body>" +
                                                   "<h1>El proceso de Carga Automática ha finalizado.</h1>" + 
                                                   "<br><h1>RESUMEN:</h1>" +
                                                   "<br>" +
                                                   finalMessage +
                                                   "<br>" +
                                                   "<br>" +
                                                   desc +
                                                   "<br>" +
                                                   "</body></html>";
            boolean useThread = false;
            new SimpleMailSender().send(destinatario, asunto, contenido, false, fileAttach);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
    }
    
    public void sendMailTransCosteadas(List<TNoCosteadasBean> listCosteadas){
     
        String desc = null;
                        
        SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");
        
                        
                                desc = "El proceso de carga automatica detecto las siguientes transacciones no costeadas";
                        
                
        try {
            StringBuffer str = new StringBuffer();
            str.append("<table>");
            str.append("<tr>");
            str.append("<td align='center'>");  
            str.append("Id Transacción");
            str.append("</td>");  
            str.append("<td align='center'>");  
            str.append("Código");
            str.append("</td>");
            str.append("<td align='center'>");  
            str.append("Descripción");
            str.append("</td>");
            str.append("</tr>");
            for(TNoCosteadasBean cot:listCosteadas){
                          
                          str.append("<tr>");
                          str.append("<td>");
                          str.append(cot.getIdtransaction());
                          str.append("</td>");
                          str.append("<td>");
                          str.append(cot.getDesCodigo());
                          str.append("</td>");
                          str.append("<td>");
                          str.append(cot.getDestituloOri());
                          str.append("</td>");
                          str.append("</tr>");              
                          
                          
                      }
            str.append("</table>");
                               String asunto = bundle.getString("com.mx.televisa.divfilmica.nom.desarrollo.produccion")+":OBI Transacciones no Costeadas en ERP";
                               String contenido = "<html>" +
                                                   "<body>" +
                                                   "<br><h1>RESUMEN:</h1>" +
                                                   "<br>" +
                                                   desc +
                                                   "<br>" +
                                                  "<h3>"+
                                                    str.toString() + "</h3>"+
                                                                
                                                   "</body></html>";
            
            boolean useThread = false;
            new SimpleMailSender().send(destinatario, asunto, contenido, false, new String[0]);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
    }
   
}

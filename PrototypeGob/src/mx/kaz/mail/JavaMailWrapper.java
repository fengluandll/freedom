package mx.kaz.mail;

import java.io.File;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.sun.mail.smtp.SMTPTransport;






import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


//se usaban con mail 1.4
	//import com.sun.mail.smtp.SMTPAddressFailedException;
	//import com.sun.mail.smtp.SMTPAddressSucceededException;
	//import com.sun.mail.smtp.SMTPSendFailedException;

	/**
	 * Abstraccion de las 
	 * funcionalidades de javax.mail.
	 */
public class JavaMailWrapper extends Thread{
        
	private static org.apache.log4j.Logger logger = Logger.getLogger(SendMails.class);

    private String  to, subject, from, cc, bcc, url, text;
                private String mailhost;
                private String mailPort;
                private String mailer = "smtpsend";
                
                private File file;
                private String[] strFile;
                
                private String protocol, host, user, password;
                private String record;      // name of folder in which to record mail
                
                private boolean debug = false;
                private boolean verbose = false;
                private boolean auth = false;
                private boolean ssl = false;
                private boolean useAuthenticator = false;
                private boolean useHTML = true;
                
                public JavaMailWrapper(){
                    
                }

                public void setServerParameters(String host, String port, String mailer,
                                        String user, String password){

                    this.mailhost = host;
                    this.mailPort = port;
                    this.user = user;
                    this.password = password;
                    this.mailer = mailer;
                    this.from = mailer;
                }

                 public void setAdicionalConfiguration(boolean useHTML, boolean auth, boolean ssl, boolean useAuthenticator){

                    this.ssl = ssl;
                    this.auth = auth;      
                    this.useAuthenticator = useAuthenticator;
                    this.useHTML = useHTML;
                }

                public void setMailParameters(String to, String subject, String text,File file){

                    this.to = to;
                    this.subject = subject;
                    this.text = text;
                    this.file = file;
                }
                
                public void setMailParameters(String to, String subject, String text, String[] strFile){

                    this.to = to;
                    this.subject = subject;
                    this.text = text;
                    this.strFile = strFile;
                }

                public void setMailParameters(String to,String cc,String bcc,String subject,String text){

                    this.cc = cc;
                    this.bcc = bcc;
                    this.to = to;
                    this.subject = subject;
                    this.text = text;
                }

                public void setBCC(String bcc){

                    this.bcc = bcc;
                }
                
                public void run(){

                    try {
                                    sendMail();
                            } catch (Exception e) {
                                    e.printStackTrace();
                            }
                }
                    
                
public void sendMail() throws Exception {        
                    
                    try {
                        
                        Properties props = System.getProperties();
                        if (mailhost != null) {
                            props.put("mail.smtp.host", mailhost);
                        }
                        
                        if (auth) {
                            props.put("mail.smtp.auth", "true");
                        }            
                               
                        props.put("mail.smtp.port", mailPort);
                        
                        //props.put("mail.smtp.auth.ntlm.domain", "CORPKIODC02.corp.televisa.com.mx");
                        

                        // Get a Session object
                        Session session = null;
                        if(useAuthenticator) {
                            session = Session.getInstance(props, new MyAuthenticator(user, password));
                        }else {
                            session = Session.getInstance(props, null);
                        }
                        
                        if (debug)session.setDebug(true);
                        
                        // construct the message
                        Message msg = new MimeMessage(session);
                        if (from != null)
                            msg.setFrom(new InternetAddress(from));
                        else
                            msg.setFrom();
                        
                        msg.setRecipients(Message.RecipientType.TO,
                                InternetAddress.parse(to, false));
                        if (cc != null)
                            msg.setRecipients(Message.RecipientType.CC,
                                    InternetAddress.parse(cc, false));
                        if (bcc != null)
                            msg.setRecipients(Message.RecipientType.BCC,
                                    InternetAddress.parse(bcc, false));
                        
                        msg.setSubject(subject);
                                    
                        MimeMultipart multipart = null;
                        if (strFile.length > 0 && strFile != null) {  
//                            logger.debug("LLegue al 2");
                            BodyPart mbp1 = new MimeBodyPart();
                          //mbp1.setText(text);
                          mbp1.setContent(text,"text/html");
                          MimeBodyPart mbp2 = new MimeBodyPart();
                          DataSource source = new FileDataSource(new File(strFile[0]));
                          mbp2.setDataHandler(new DataHandler(source));
                          mbp2.setFileName(strFile[0]);
                          mbp2.setHeader("Content-ID", "<image_cid>"); 
                          multipart = new MimeMultipart();
                          multipart.addBodyPart(mbp1);
                          multipart.addBodyPart(mbp2);
                          msg.setContent(multipart);
                          
                          
                          //mail 1.4
                          /*Este if solo esta en harcode solo listo para adjuntar 5 imagenes dependiendo de la longitud
                           de nombres de archivos entra en el if correspodiente y adjunta las imagenes, no vi la manera
                           dinamica para adjuntar los archivos*/
//                          logger.debug("TAMAÑO ATTACH "+strFile.length);
                          if(strFile.length == 2 ){
                              MimeBodyPart mbp3 = new MimeBodyPart();
                              mbp3.attachFile(new File(strFile[0]));
                              MimeBodyPart mbp4 = new MimeBodyPart();
                              mbp4.attachFile(new File(strFile[1]));
                              MimeMultipart mp = new MimeMultipart();
                              mp.addBodyPart(mbp1);
                              mp.addBodyPart(mbp3);
                              mp.addBodyPart(mbp4);
                              msg.setContent(mp);
                          }else if(strFile.length == 3){
                        	  MimeBodyPart mbp3 = new MimeBodyPart();
                              mbp3.attachFile(new File(strFile[0]));
                              MimeBodyPart mbp4 = new MimeBodyPart();
                              mbp4.attachFile(new File(strFile[1]));
                              MimeBodyPart mbp5 = new MimeBodyPart();
                              mbp5.attachFile(new File(strFile[2]));
                              
                              MimeMultipart mp = new MimeMultipart();
                              mp.addBodyPart(mbp1);
                              mp.addBodyPart(mbp3);
                              mp.addBodyPart(mbp4);
                              mp.addBodyPart(mbp5);
                              msg.setContent(mp);
                          }else if(strFile.length == 4){
                        	  MimeBodyPart mbp3 = new MimeBodyPart();
                              mbp3.attachFile(new File(strFile[0]));
                              MimeBodyPart mbp4 = new MimeBodyPart();
                              mbp4.attachFile(new File(strFile[1]));
                              MimeBodyPart mbp5 = new MimeBodyPart();
                              mbp5.attachFile(new File(strFile[2]));
                              MimeBodyPart mbp6 = new MimeBodyPart();
                              mbp6.attachFile(new File(strFile[3]));
                              
                              MimeMultipart mp = new MimeMultipart();
                              mp.addBodyPart(mbp1);
                              mp.addBodyPart(mbp3);
                              mp.addBodyPart(mbp4);
                              mp.addBodyPart(mbp5);
                              mp.addBodyPart(mbp6);
                              msg.setContent(mp);
                          }else if(strFile.length == 5){
                        	  MimeBodyPart mbp3 = new MimeBodyPart();
                              mbp3.attachFile(new File(strFile[0]));
                              MimeBodyPart mbp4 = new MimeBodyPart();
                              mbp4.attachFile(new File(strFile[1]));
                              MimeBodyPart mbp5 = new MimeBodyPart();
                              mbp5.attachFile(new File(strFile[2]));
                              MimeBodyPart mbp6 = new MimeBodyPart();
                              mbp6.attachFile(new File(strFile[3]));
                              MimeBodyPart mbp7 = new MimeBodyPart();
                              mbp7.attachFile(new File(strFile[4]));
                              
                              MimeMultipart mp = new MimeMultipart();
                              mp.addBodyPart(mbp1);
                              mp.addBodyPart(mbp3);
                              mp.addBodyPart(mbp4);
                              mp.addBodyPart(mbp5);
                              mp.addBodyPart(mbp6);
                              mp.addBodyPart(mbp7);
                              msg.setContent(mp);
                          }
                          
                          
                          
                          
                         
                        } else {
                            // If the desired charset is known, you can use
                            // setText(text, charset)
                            
                            if(useHTML) {
                                msg.setContent(text,"text/html");
                            	
                            } else {
                                msg.setText(text);
                            }
                                
                            
                        }
                        
                       // msg.setHeader("X-Mailer", mailer);
                        msg.setSentDate(new Date());
                        
                        //SENDING
                        SMTPTransport t =
                                (SMTPTransport)session.getTransport(ssl ? "smtps" : "smtp");
                        try {
                            if (auth) {
                                t.connect(mailhost, user, password);                    
                            } else {
                                t.connect();
                            }
                                
                            t.sendMessage(msg, msg.getAllRecipients());
                        } finally {
                            
                            //mail 1.4
                            //if (verbose)
                            //    System.out.println("Response: " + t.getLastServerResponse());
                            t.close();
                        }
                        logger.info("Mail was sent successfully.");
                        
                        
                        
                        
                        // Keep a copy, if requested.
                        
                        if (record != null) {
                            // Get a Store object
                            Store store = null;
                            if (url != null) {
                                URLName urln = new URLName(url);
                                store = session.getStore(urln);
                                store.connect();
                            } else {
                                if (protocol != null)
                                    store = session.getStore(protocol);
                                else
                                    store = session.getStore();
                                
                                // Connect
                                if (host != null || user != null || password != null)
                                    store.connect(host, user, password);
                                else
                                    store.connect();
                            }
                            
                            // Get record Folder.  Create if it does not exist.
                            Folder folder = store.getFolder(record);
                            if (folder == null) {
                                System.err.println("Can't get record folder.");
                                System.exit(1);
                            }
                            if (!folder.exists())
                                folder.create(Folder.HOLDS_MESSAGES);
                            
                            Message[] msgs = new Message[1];
                            msgs[0] = msg;
                            folder.appendMessages(msgs);
                            
                        }
                        
                    } catch (Exception e) {
                        
                        System.out.println(new Date());
                        e.printStackTrace();
                        throw e;
                        
                        /*
                        if (e instanceof SendFailedException) {
                            MessagingException sfe = (MessagingException)e;
                            if (sfe instanceof SMTPSendFailedException) {
                                SMTPSendFailedException ssfe =
                                        (SMTPSendFailedException)sfe;
                                System.out.println("SMTP SEND FAILED:");
                                if (verbose)
                                    System.out.println(ssfe.toString());
                                System.out.println("  Command: " + ssfe.getCommand());
                                System.out.println("  RetCode: " + ssfe.getReturnCode());
                                System.out.println("  Response: " + ssfe.getMessage());
                            } else {
                                if (verbose)
                                    System.out.println("Send failed: " + sfe.toString());
                            }
                            Exception ne;
                            while ((ne = sfe.getNextException()) != null &&
                                    ne instanceof MessagingException) {
                                sfe = (MessagingException)ne;
                                if (sfe instanceof SMTPAddressFailedException) {
                                    SMTPAddressFailedException ssfe =
                                            (SMTPAddressFailedException)sfe;
                                    System.out.println("ADDRESS FAILED:");
                                    if (verbose)
                                        System.out.println(ssfe.toString());
                                    System.out.println("  Address: " + ssfe.getAddress());
                                    System.out.println("  Command: " + ssfe.getCommand());
                                    System.out.println("  RetCode: " + ssfe.getReturnCode());
                                    System.out.println("  Response: " + ssfe.getMessage());
                                } else if (sfe instanceof SMTPAddressSucceededException) {
                                    System.out.println("ADDRESS SUCCEEDED:");
                                    SMTPAddressSucceededException ssfe =
                                            (SMTPAddressSucceededException)sfe;
                                    if (verbose)
                                        System.out.println(ssfe.toString());
                                    System.out.println("  Address: " + ssfe.getAddress());
                                    System.out.println("  Command: " + ssfe.getCommand());
                                    System.out.println("  RetCode: " + ssfe.getReturnCode());
                                    System.out.println("  Response: " + ssfe.getMessage());
                                }
                            }
                        } else {
                            System.out.println("Got Exception: " + e);
                            if (verbose)
                                e.printStackTrace();
                        }*/
                        
                    }
                    
                   
                }

              
               

            }

            class MyAuthenticator extends javax.mail.Authenticator {

                private String username;
                private String password;
                
                public MyAuthenticator(String username, String password) {
                
                    this.username = username;
                    this.password = password;        
                }
                
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
}

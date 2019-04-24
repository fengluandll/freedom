package mx.com.televisa.divfilmica.readFiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipOutputStream;

import mx.com.televisa.divfilmica.beans.LogBean;
import mx.com.televisa.divfilmica.mail.SendMails;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class ZipFileDirectory {
    
    static Logger logger = Logger.getLogger(ZipFileDirectory.class);
    ResourceBundle bundle;
    
    public ZipFileDirectory(){
        PropertyConfigurator.configure("src/log4j.properties");
        bundle = ResourceBundle.getBundle("DataConnection");
    }
  
    public void createZip (String zipFile, String sourceDirectory){
            
            try
                   {
                           //create byte buffer
                           byte[] buffer = new byte[1024];
                           /*
                            * To create a zip file, use
                            * 
                            * ZipOutputStream(OutputStream out)
                            * constructor of ZipOutputStream class.
                            *  
                            */
     
                            //create object of FileOutputStream
                            FileOutputStream fout = new FileOutputStream(zipFile);
     
                            //create object of ZipOutputStream from FileOutputStream
                            ZipOutputStream zout = new ZipOutputStream(fout);
     
                            //create File object from directory name
                            File dir = new File(sourceDirectory);
     
                            //check to see if this directory exists
                            if(!dir.isDirectory())
                            {
                                   logger.info(sourceDirectory + " is not a directory");
                            }
                            else
                            {
                                   File[] files = dir.listFiles();
     
                                   for(int i=0; i < files.length ; i++)
                                   {
                                           
                                        logger.info("Adding " + files[i].getName());
     
                                           //create object of FileInputStream for source file
                                           FileInputStream fin = new FileInputStream(files[i]);
     
                                           /*
                                            * To begin writing ZipEntry in the zip file, use
                                            * 
                                            * void putNextEntry(ZipEntry entry)
                                            * method of ZipOutputStream class.
                                            * 
                                            * This method begins writing a new Zip entry to 
                                            * the zip file and positions the stream to the start 
                                            * of the entry data.
                                            */
     
                                           zout.putNextEntry(new ZipEntry(files[i].getName()));
     
                                           /*
                                            * After creating entry in the zip file, actually 
                                            * write the file.
                                            */
                                           int length;
     
                                           while((length = fin.read(buffer)) > 0)
                                           {
                                              zout.write(buffer, 0, length);
                                           }
     
                                           /*
                                            * After writing the file to ZipOutputStream, use
                                            * 
                                            * void closeEntry() method of ZipOutputStream class to 
                                            * close the current entry and position the stream to 
                                            * write the next entry.
                                            */
     
                                            zout.closeEntry();
     
                                            //close the InputStream
                                            fin.close();
                                   }
                            }
     
                             //close the ZipOutputStream
                             zout.close();
     
                             
                        logger.info("Zip file has been created!");
     
                   }
                   catch(IOException ioe)
                   {
                           System.out.println("IOException :" + ioe);
                           logger.error(ioe.getMessage(),ioe);
                   }        
        }
    
    /**
     * Este metodo solo funciona si se le van a mandar uno o dos archivos a adjuntar
     * se creo este metodo solo para adjunta la carpeta logs y la carpeta de
     * los uris
     * @param files arreglo de archivos a adjuntar, solo pueden ser 2 archivos adjuntos
     */
    public void zipSendMail(String[] files, String finalMessage){
        
        ZipFileDirectory test = new ZipFileDirectory();
        SimpleDateFormat format = new SimpleDateFormat("dd_MM_yyyy");
        String fecha = format.format(new Date());
        SendMails sendMails = new SendMails();
        //test.createZip(temp.getAbsolutePath() ,"C:\\Users\\Argumedo\\Documents\\Televisa3\\CARGA_COMPLETA_ERP");
        
            
        
        
        File temp = null;
        File temp2 = null;
        if(files.length == 2){
            temp = new File(bundle.getString("com.mx.televisa.divfilmica.ruta.zip")+"\\logs_"+fecha+".zip");
            temp2 = new File(bundle.getString("com.mx.televisa.divfilmica.ruta.zip")+"\\uri_"+fecha+".zip");
            test.createZip(temp.getAbsolutePath(),files[0]);
            test.createZip(temp2.getAbsolutePath(),files[1]);
            String[] file = {temp.getAbsolutePath(),temp2.getAbsolutePath()};
            for(String f:file){
                logger.debug("ARCHIVOS****** "+f);
            }
            sendMails.sendMailEndFinalStep(file,finalMessage);
            
            temp.delete();
            temp2.delete();
        }else{
            temp = new File(bundle.getString("com.mx.televisa.divfilmica.ruta.zip")+"\\logs_"+fecha+".zip");
            test.createZip(temp.getAbsolutePath(),files[0]);
            String[] file = {temp.getAbsolutePath()};
            sendMails.sendMailEndFinalStep(file,finalMessage);
            temp.delete();
        }
        
    }
        
        public static void main (String []args){
            ZipFileDirectory test = new ZipFileDirectory();
            SimpleDateFormat format = new SimpleDateFormat("dd_MM_yyyy");
            String fecha = format.format(new Date());
            SendMails sendMails = new SendMails();
       
            File temp = new File("C:\\Users\\Argumedo\\Documents\\Televisa3\\logs_"+fecha+".zip");
            
            //test.createZip(temp.getAbsolutePath() ,"C:\\Users\\Argumedo\\Documents\\Televisa3\\CARGA_COMPLETA_ERP");
            test.createZip(temp.getAbsolutePath(),"C:\\Users\\Argumedo\\Documents\\Televisa3\\CARGA_COMPLETA_ERP");
            String[] file = {temp.getAbsolutePath()};
              sendMails.sendMail(new LogBean(),file);
            //temp.delete();
        
        }
}

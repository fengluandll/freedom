package mx.com.televisa.divfilmica.readFiles;

import java.io.File;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import mx.com.televisa.divfilmica.beans.FileUriBean;
import mx.com.televisa.divfilmica.model.LogDAO;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class ReadFile {
    
    ResourceBundle bundle;
    static Logger logger = Logger.getLogger(ReadFile.class);
    public ReadFile() {
        PropertyConfigurator.configure("src/log4j.properties");
        bundle = ResourceBundle.getBundle("notificador");
    }
    
    /*** 
     * @param errDirectory Ruta absoluta donde se encuentran los archivos .err
     * @param filter tipo de archivo para filtrar la busqueda
     * @return String con los nombrer de los archivos.err encontrados,"" si no hay alguno
     */
public String listenDirUri(String errDirectory,String filter){
                    
                    File dir = new File(errDirectory);
                    File [] ficheros = dir.listFiles();
                    String filesName = "";
                    if (ficheros == null){
                      logger.error("No hay ficheros en el directorio especificado");
                    }else {
                      for (int i=0;i<ficheros.length;i++){
                                      if(ficheros[i].getName().endsWith(filter)){
                                              filesName = filesName + "\n <br> *" + ficheros[i].getName()+" - Last Modified:" + new Date(ficheros[i].lastModified());
                                      }
                      } return filesName;
                    }
                    
                    
                    return "";
            }
    
   
    public List<FileUriBean> listenDirUri(){
                    LogDAO logDAO = new LogDAO();
                    FileUriBean fileUriBean = new FileUriBean();
                    List<FileUriBean> listFileUriBean = new ArrayList<FileUriBean>();
                    File dir = new File(bundle.getString("com.mx.televisa.divfilmica.ruta.uri"));
                    File [] ficheros = dir.listFiles();
                    String[] filesName = new String[ficheros.length];
                    if (ficheros == null){
                      logger.error("No hay ficheros en el directorio especificado");
                    }else {
                      for (int i = 0 ; i < ficheros.length ; i++){
                          
                          if(ficheros[i].getName().endsWith(bundle.getString("com.mx.televisa.divfilmica.filter.uri"))){
                              fileUriBean = new FileUriBean();
                              fileUriBean = logDAO.getDataFileUri(ficheros[i].getName());
                              fileUriBean.setNomArchivoUri(ficheros[i].getName());
                          }
                          
                          listFileUriBean.add(fileUriBean);
                      } 
                       
                    }
                    
                    
                    return listFileUriBean;
            }
    
    public static void main(String[] args){
       
        
    }
}

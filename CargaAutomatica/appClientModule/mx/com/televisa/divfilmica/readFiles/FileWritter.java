package mx.com.televisa.divfilmica.readFiles;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import java.util.TreeMap;

import mx.com.televisa.divfilmica.beans.FileUriBean;
import mx.com.televisa.divfilmica.beans.LogBean;
import mx.com.televisa.divfilmica.model.LogDAO;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class FileWritter {
    static Logger logger = Logger.getLogger(FileWritter.class);
    File fileLog;
    BufferedReader in;
    FileWriter out;
    ResourceBundle bundleLog;
    ResourceBundle bundle;
    ResourceBundle bundleNoti;
    LogDAO logDAO;
    private static String rutaLog0;
    private static String rutaLog1;
    private static String rutaLog2;
    private static String rutaLog3;
    private static String rutaLog4;
    private static String rutaLog5;
    
    public FileWritter() {
        PropertyConfigurator.configure("src/log4j.properties");
        bundleLog = ResourceBundle.getBundle("log4j");
        bundle = ResourceBundle.getBundle("DataConnection");
        bundleNoti = ResourceBundle.getBundle("notificador");
        fileLog = new File(bundleLog.getString("log4j.appender.archivo.file"));
        rutaLog0 = bundleNoti.getString("com.mx.televisa.divfilmica.read.log0");
        rutaLog1 = bundleNoti.getString("com.mx.televisa.divfilmica.read.log1");
        rutaLog2 = bundleNoti.getString("com.mx.televisa.divfilmica.read.log2");
        rutaLog3 = bundleNoti.getString("com.mx.televisa.divfilmica.read.log3");
        rutaLog4 = bundleNoti.getString("com.mx.televisa.divfilmica.read.log4");
        rutaLog5 = bundleNoti.getString("com.mx.televisa.divfilmica.read.log5");
    }
    
    public void limpiaLog(){
                try {
                    out = new FileWriter (fileLog);
                    out.close();
                } catch (IOException ex) {
                    logger.error(ex.getMessage(),ex);
                }
        }
    /**
     *Metodo estatico que valida que un archivo esté disponible
     * @param tsRoute
     * 
     */
    public static boolean isValidFile(String tsRoute){
            Boolean isValid = false;
            File f = new File(tsRoute);
            if(f.exists()) isValid = true;
            return isValid;
        }
    
    /**
     * metodo que extrae losdatos necesarios de la cadena con error critico
     * y se conecta a la base de datos para traerse el nombre del proceso
     * @param cadena
     */
    public LogBean extraeCadena(String cadena){
        logDAO = new LogDAO();
        LogBean logBean = new LogBean();
        logger.info(cadena);
        String numError = StringUtils.substring(cadena,6,13);
        int ultimoIgual = 0;
        String tipoError = null;
        String reglaessb = null;
        int[] encontro = new int[3];
            encontro[0] = StringUtils.indexOf(cadena, "Rules File");
            encontro[1] = StringUtils.indexOf(cadena, "CalcObject");
            encontro[2] = StringUtils.indexOf(cadena, "ReptObject");
            for(int i = 0; i < encontro.length; i ++){
                if(encontro[i] > -1){
                        ultimoIgual = StringUtils.lastIndexOf(cadena, "=");
                        tipoError = StringUtils.substring(cadena, encontro[i],ultimoIgual);
                        reglaessb = StringUtils.substring(cadena,ultimoIgual+2,cadena.length());
                        logger.debug("numError "+numError);
                        logger.debug("tipoError "+tipoError);
                        logger.debug("reglaessb "+reglaessb);
                        
                    }
                logBean.setCodError(numError);
                logBean.setTipoError(tipoError);
                logBean.setReglaEssbase(reglaessb);
                logBean.setNomProceso(logDAO.getDataLog(reglaessb,tipoError));
            }
        return logBean;
    }
    
    /**
     * Metodo generico que lee todos los logs dependiendo del bat que se
     * ejecuto
     * @param nomLog accion que decide que archivo log leera
     * @return un bean que contiene todos los datos para mandar el mail
     */
    public LogBean leeLogg1(int nomLog){
        
        LogBean logBean;
        StringBuffer buf=new StringBuffer();
        Map<Integer,String> mapArchivo = new TreeMap<Integer,String>();
                try {
                    if(nomLog == 0){ 
                        logger.debug("Leyendo "+bundleNoti.getString("com.mx.televisa.divfilmica.name.log0"));   
                        in = new BufferedReader(new FileReader(rutaLog0));
                    }else if(nomLog == 1){     
                        logger.debug("Leyendo "+bundleNoti.getString("com.mx.televisa.divfilmica.name.log1"));   
                        in = new BufferedReader(new FileReader(rutaLog1));
                    }else if(nomLog == 2){ 
                        logger.debug("Leyendo "+bundleNoti.getString("com.mx.televisa.divfilmica.name.log2"));   
                        in = new BufferedReader(new FileReader(rutaLog2));
                    }else if(nomLog == 3){
                        logger.debug("Leyendo "+bundleNoti.getString("com.mx.televisa.divfilmica.name.log3"));   
                        in = new BufferedReader(new FileReader(rutaLog3));
                    }else if(nomLog == 4){
                         logger.debug("Leyendo "+bundleNoti.getString("com.mx.televisa.divfilmica.name.log4"));   
                         in = new BufferedReader(new FileReader(rutaLog4));    
                     }else if(nomLog == 5){
                         logger.debug("Leyendo "+bundleNoti.getString("com.mx.televisa.divfilmica.name.log5"));   
                         in = new BufferedReader(new FileReader(rutaLog5));    
                     }  
                        String txt;
                        String sNum;
                        String sNumCompleto;
                        
                      
                        int iNum;
                        int count = 0;
                        
                        while ((txt=in.readLine()) != null) {
                            mapArchivo.put(count, txt);
                            count++;
                            if(StringUtils.indexOf(txt,"sts =") > -1){
                               sNum = StringUtils.substring(txt,6,7);
                               sNumCompleto = StringUtils.substring(txt,6,13);
                               
                               if(StringUtils.isNumeric(sNum)){
                                       iNum =  Integer.parseInt(sNum);
                                        
                                       if(iNum > 0 && Integer.parseInt(sNumCompleto) != 1007083 && Integer.parseInt(sNumCompleto) != 1090010){
                                           
                                               logger.info("Error: "+iNum);
                                               logBean = extraeCadena(txt);
                                               logBean.setHuboError(true);
                                               logger.debug("MAPA "+mapArchivo.get(mapArchivo.size()-3));
                                               logBean.setDescError(mapArchivo.get(mapArchivo.size()-3));
                                               in.close();
                                               return logBean;
                                           
                                       }
                                   }
                               
                            }
    
                        }
                    
                
            } catch (FileNotFoundException ex) {
                logger.error(ex.getMessage(),ex);
            } catch (IOException ex) {
                    logger.error(ex.getMessage(),ex);
                }
                    logBean = new LogBean();
                    logBean.setHuboError(false);
                    return logBean;
    }
    
    public void escribeUri() throws FileNotFoundException, IOException {
        StringBuffer buf;
        ReadFile readFile = new ReadFile();
        List<FileUriBean>  listFileUriBean = readFile.listenDirUri();
        BufferedReader in = null;  
        BufferedWriter out = null;
        String txt = null;
        for(FileUriBean archivoUri:listFileUriBean){
            buf=new StringBuffer();
            in = new BufferedReader(new FileReader(bundleNoti.getString("com.mx.televisa.divfilmica.ruta.uri")+"/"+archivoUri.getNomArchivoUri()));
            
             while ((txt=in.readLine()) != null) {
                    buf.append(txt);
                    buf.append("\n");
                }
                in.close();
                out = new BufferedWriter(new FileWriter(bundleNoti.getString("com.mx.televisa.divfilmica.ruta.uri")+"/"+archivoUri.getNomArchivoUri()));
                out.write("Regla de Carga: "+archivoUri.getReglaCarga());
                out.write("\n");
                out.write("Subproceso: "+archivoUri.getSubproceso());
                out.write("\n");
                out.write("\n");
                out.write(buf.toString());
                out.close();
            }
        }
       
    public static void main(String args[]){
        try {
            new FileWritter().escribeUri();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }
    
}

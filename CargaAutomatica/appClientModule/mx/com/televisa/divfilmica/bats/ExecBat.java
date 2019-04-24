/**
* Project: CONS-0626
*
* File: ExecBat.java
*
* Created on: Febrero 24, 2012 at 13:16
*
* Copyright (c) - Kaz Consulting / Argumel
*/
package mx.com.televisa.divfilmica.bats;

/**
 * Calse que al ser llamada se ejecuta el bat
 *
 * @author Kaz Consulting / Argumel
 *
 * @version 1.0.0
 *
 * @date Febrero 24, 2012 at 113:16
 */
import java.io.IOException;

import java.util.ResourceBundle;


import mx.com.televisa.divfilmica.readFiles.FileWritter;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class ExecBat {
    
    ResourceBundle bundle;
    long totalTiempo;
    static Logger logger = Logger.getLogger(ExecBat.class);
        private final String BAT_CERO;
        private final String BAT_UNO;
        private final String BAT_DOS;
        private final String BAT_TRES;
        private final String BAT_CUATRO;
        private final String RUTA_BAT_CERO;
        private final String RUTA_BAT_UNO;
        private final String RUTA_BAT_DOS;
        private final String RUTA_BAT_TRES;
        private final String RUTA_BAT_CUATRO;
    
    public ExecBat() {
        PropertyConfigurator.configure("src/log4j.properties");
        bundle = ResourceBundle.getBundle("DataConnection");
        BAT_CERO =  bundle.getString("com.mx.televisa.divfilmica.execute.bat.cero");
        BAT_UNO =  bundle.getString("com.mx.televisa.divfilmica.execute.bat.uno");
        BAT_DOS =  bundle.getString("com.mx.televisa.divfilmica.execute.bat.dos");
        BAT_TRES =  bundle.getString("com.mx.televisa.divfilmica.execute.bat.tres");
        BAT_CUATRO =  bundle.getString("com.mx.televisa.divfilmica.execute.bat.cuatro");
        RUTA_BAT_CERO =  StringUtils.substringAfterLast(bundle.getString("com.mx.televisa.divfilmica.execute.bat.cero"),"/wait ");
        RUTA_BAT_UNO =  StringUtils.substringAfterLast(bundle.getString("com.mx.televisa.divfilmica.execute.bat.uno"),"/wait ");
        RUTA_BAT_DOS =  StringUtils.substringAfterLast(bundle.getString("com.mx.televisa.divfilmica.execute.bat.dos"),"/wait ");
        RUTA_BAT_TRES =  StringUtils.substringAfterLast(bundle.getString("com.mx.televisa.divfilmica.execute.bat.tres"),"/wait ");
        RUTA_BAT_CUATRO =  StringUtils.substringAfterLast(bundle.getString("com.mx.televisa.divfilmica.execute.bat.cuatro"),"/wait ");
    }
    
    public void batCero() throws IOException, InterruptedException {
            
            if(FileWritter.isValidFile(RUTA_BAT_CERO)){
                Runtime runtime = Runtime.getRuntime();
                Process p = runtime.exec(BAT_CERO);
                p.waitFor();
            }else{
                logger.error("ERROR: NO SE ENCONTRO EL BAT EN LA RUTA ESPECIFICADA "+StringUtils.substringAfterLast(RUTA_BAT_CERO,"/c"));
            }
            
        }
    
    public void batUno() throws IOException, InterruptedException {
            
            if(FileWritter.isValidFile(RUTA_BAT_UNO)){
                Runtime runtime = Runtime.getRuntime();
                Process p = runtime.exec(BAT_UNO);
                p.waitFor();
            }else{
                logger.error("ERROR: NO SE ENCONTRO EL BAT EN LA RUTA ESPECIFICADA "+StringUtils.substringAfterLast(RUTA_BAT_UNO,"/c"));
            }
            
        }
        
    public void batDos() throws IOException, InterruptedException {
            
            if(FileWritter.isValidFile(RUTA_BAT_DOS)){
                Runtime runtime = Runtime.getRuntime();
                Process p = runtime.exec(BAT_DOS);
                p.waitFor();
            }else{
                logger.error("ERROR: NO SE ENCONTRO EL BAT EN LA RUTA ESPECIFICADA "+RUTA_BAT_DOS);
            }
            
        }
    public void batTres() throws IOException, InterruptedException {
            
            if(FileWritter.isValidFile(RUTA_BAT_TRES)){
                Runtime runtime = Runtime.getRuntime();
                Process p = runtime.exec(BAT_TRES);
                p.waitFor();
            }else{
                logger.error("ERROR: NO SE ENCONTRO EL BAT EN LA RUTA ESPECIFICADA "+RUTA_BAT_TRES);
            }
            
        }
    public void batCuatro() throws IOException, InterruptedException {
            
            if(FileWritter.isValidFile(RUTA_BAT_CUATRO)){
                Runtime runtime = Runtime.getRuntime();
                Process p = runtime.exec(BAT_CUATRO);
                p.waitFor();
            }else{
                logger.error("ERROR: NO SE ENCONTRO EL BAT EN LA RUTA ESPECIFICADA "+RUTA_BAT_CUATRO);
            }
            
        }
}

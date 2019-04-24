/**
* Project: CONS-0626
*
* File: SimpleThread.java
*
* Created on: Febrero 24, 2012 at 13:30
*
* Copyright (c) - Kaz Consulting / Argumel
*/
package mx.com.televisa.divfilmica.threads;

import java.io.IOException;

import java.util.Date;

import java.util.ResourceBundle;

import mx.com.televisa.divfilmica.bats.ExecBat;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Hilo que ejecuta un bat para un proceso en paralelo
 *
 * @author Kaz Consulting / Argumel
 *
 * @version 1.0.0
 *
 * @date Febrero 24, 2012 at 13:30
 */
public class SimpleThread extends Thread{
    
    ResourceBundle bundle;
    long totalTiempo;
    static Logger logger = Logger.getLogger(SimpleThread.class);
    
    public SimpleThread() {
        PropertyConfigurator.configure("src/log4j.properties");
        bundle = ResourceBundle.getBundle("DataConnection");
        
    }
    
    /**
     * Ejecuccion del bat 1
     */
    public void run() {
          ExecBat exe1 = new ExecBat();
          logger.info("Incio Ejecución de Bat 2: "+ new Date());
          long tiempoInicio = System.currentTimeMillis();
          try {
              
              exe1.batDos();
          } catch (IOException e) {
              logger.error(e.getMessage(),e);
          } catch (InterruptedException e) {
              logger.error(e.getMessage(),e);
          }
              totalTiempo = System.currentTimeMillis() - tiempoInicio;
              logger.info("Tiempo Total de Bat 2 " + totalTiempo / 1000 + " Segundos");  
              logger.info("Fin Ejecución de Bat 2: "+ new Date());
              
          }
}

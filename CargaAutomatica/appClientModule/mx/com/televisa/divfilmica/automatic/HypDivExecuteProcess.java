/**
* Project: CONS-0626
*
* File: HypDivExecuteProcess.java
*
* Created on: Febrero 08, 2012 at 16:21
*
* Copyright (c) - Kaz Consulting / Argumel
*/
package mx.com.televisa.divfilmica.automatic;

import java.io.IOException;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import mx.com.televisa.divfilmica.bats.ExecBat;
import mx.com.televisa.divfilmica.beans.LogBean;
import mx.com.televisa.divfilmica.beans.TNoCosteadasBean;
import mx.com.televisa.divfilmica.interfaces.ExecuteProcess;
import mx.com.televisa.divfilmica.mail.SendMails;
import mx.com.televisa.divfilmica.model.connection.ConnectionWrapper;
import mx.com.televisa.divfilmica.model.packages.Procedure;

import mx.com.televisa.divfilmica.readFiles.FileWritter;

import mx.com.televisa.divfilmica.readFiles.ZipFileDirectory;
import mx.com.televisa.divfilmica.threads.SimpleThread;

import mx.com.televisa.divfilmica.threads.SimpleThreadDos;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Calse principal donde ejecuta todos los procesos
 *
 * @author Kaz Consulting / Argumel
 *
 * @version 1.0.0
 *
 * @date Febrero 08, 2012 at 16:21
 */
public class HypDivExecuteProcess implements ExecuteProcess{
    
    
    ResourceBundle bundle;
    ResourceBundle bundleNoti;
    long totalTiempo;
    static Logger logger = Logger.getLogger(HypDivExecuteProcess.class);
    SendMails sendMails;
    ZipFileDirectory zipFileDirectory;
    boolean ejecutaPerpetuidad = false;
    String finalMessage = "";
    
    public HypDivExecuteProcess() {
        sendMails = new SendMails();
        //System.out.println("paso 1.2");
        //JOptionPane.showMessageDialog(null,"Paso1.2","ANTES DE LEER EL LOG4J",JOptionPane.INFORMATION_MESSAGE);
        PropertyConfigurator.configure("src/log4j.properties");
        bundle = ResourceBundle.getBundle("DataConnection");
        bundleNoti = ResourceBundle.getBundle("notificador");
        //JOptionPane.showMessageDialog(null,"Paso2","antes de limpiar log",JOptionPane.INFORMATION_MESSAGE);
        FileWritter fileWritter = new FileWritter();
        fileWritter.limpiaLog();
        //JOptionPane.showMessageDialog(null,"Paso3","despues de limpiar log",JOptionPane.INFORMATION_MESSAGE);
        logger.info("INICIANDO CARGA AUTOMATICA...");
        
        
        
        executeERP();
        
        executeFechasProgramacion();
        executeBajas();
        //provBajaRealProyectado(); descomentar para la siguiente liberacion
        ejecutaPerpetuidad();
        boolean flag = executeVencimientos(); //Se pone una bandera para saber si corren o no los bats*/
       
        
        //boolean flag = true;
        try {
            if(flag){
                
                
                executeBats();
                FileWritter fileWriterTv = new FileWritter();
                
                fileWriterTv.escribeUri();
//Proceso donde envia el ultimo mail y adjunta la carpeta de logs y la carpeta uri
               String[] files ={bundleNoti.getString("com.mx.televisa.divfilmica.ruta.logs"),bundleNoti.getString("com.mx.televisa.divfilmica.ruta.uri")};
                //sendMails.sendMail(new LogBean(), files);
                zipFileDirectory = new ZipFileDirectory();
                zipFileDirectory.zipSendMail(files, finalMessage);
                logger.debug("PASO ultimo mail");
            }else{
                logger.info("NO SE EJECUTARON LOS BATS POR FALLO EN LA CONEXION...");
            }
            
        } 
          catch (Exception e) {
            logger.error(e.getMessage(),e);
        } //TODO quitar commentario
          /*catch (InterruptedException e) {
            logger.error(e.getMessage(),e);
        }*/
    }
    
    public void ejecutaPerpetuidad(){
        ConnectionWrapper connectionWrapper = new ConnectionWrapper();
        PreparedStatement psmt;
        String trueFalse=null;
        try {
            psmt = connectionWrapper.prepareStatement(bundle.getString("com.mx.televisa.divfilmica.select.tabla.XXHYPFED_OBI_VARS_TAB"));
            ResultSet rs = psmt.executeQuery();
            while(rs.next()){
               trueFalse = rs.getString(1);
            }
                logger.info("Ejecuta perpetuidad? "+ trueFalse);
                if(trueFalse.equals("TRUE")){
                       Procedure procedure = new Procedure(bundle.getString("com.mx.televisa.divfilmica.paquete.perpetuidad"),1);
                       CallableStatement cb = connectionWrapper.prepareCall(procedure);
                       cb.registerOutParameter(1, Types.VARCHAR);
                       long tiempoInicio = System.currentTimeMillis();
                       cb.execute();
                       String err = cb.getString(1);
                       logger.info("Extrayendo Informacon de la BD: "+"("+bundle.getString("com.mx.televisa.divfilmica.paquete.perpetuidad")+"): "+err);
                       logger.info("Carga de perpetuidad finalizado...");
                       totalTiempo = System.currentTimeMillis() - tiempoInicio;
                       logger.info("TIEMPO de Carga de perpetuidad " + totalTiempo / 1000 + "Segundos");
                       ejecutaPerpetuidad = true;
                      
                   }
            
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error","Error "+e.getMessage(),JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            logger.error(e.getMessage(),e);
            
        }finally{
                connectionWrapper.close();     
            }
      
    }
   
    /**
     * Metodo que ejecuta el proceso de hypCosteo
     */
    public void executeERP() {
        logger.info("Extrayendo Amortizaciones de series y peliculas...");
        long tiempoInicio = System.currentTimeMillis();
        hypCosteo();
        //fininiAmort();Ya no se usa
        //comprasAmortizacion();Ya no se usa
        logger.info("Amortizaciones de series y peliculas finalizado...");
        totalTiempo = System.currentTimeMillis() - tiempoInicio;
        logger.info("TIEMPO Carga de datos Amortizacion " + totalTiempo / 1000 + "Segundos");
    }
    
    /**
     * Metodo que manda a llamar los calculos de pl/sql para las provisiones de baja real y proyectado
     */
    private void provBajaRealProyectado(){
        ConnectionWrapper connectionWrapper=null;
        try {
            connectionWrapper = new ConnectionWrapper();
            Procedure procedure = new Procedure(bundle.getString("com.mx.televisa.divfilmica.paquete.prov.bajareal.proyectado"),2);
            CallableStatement cb = connectionWrapper.prepareCall(procedure);
            cb.setString(1, "AU");
            cb.registerOutParameter(2, Types.VARCHAR);
            long tiempoInicio = System.currentTimeMillis();
            cb.execute();
            String err = cb.getString(2);
            logger.info("Extrayendo Informacon de la BD: "+"("+bundle.getString("com.mx.televisa.divfilmica.paquete.prov.bajareal.proyectado")+"): "+err);
            logger.info("Carga de provisiones de baja real y proyectada finalizado...");
            totalTiempo = System.currentTimeMillis() - tiempoInicio;
            logger.info("TIEMPO de Carga de provisiones de baja real y proyectada " + totalTiempo / 1000 + "Segundos");
           
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error","Error "+e.getMessage(),JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            logger.error(e.getMessage(),e);
        }finally{
                connectionWrapper.close();     
            }
    }
    
    /**
     * 
     */
    private void comprasAmortizacion(){
        ConnectionWrapper connectionWrapper=null;
        try {
            connectionWrapper = new ConnectionWrapper();
            Procedure procedure = new Procedure(bundle.getString("com.mx.televisa.divfilmica.paquete.compras.amort"),1);
            CallableStatement cb = connectionWrapper.prepareCall(procedure);
            cb.registerOutParameter(1, Types.VARCHAR);
            cb.execute();
            String err = cb.getString(1);
            logger.info("Extrayendo Informacon de la BD: "+"("+bundle.getString("com.mx.televisa.divfilmica.paquete.compras.amort")+"): "+err);
           
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error","Error "+e.getMessage(),JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            logger.error(e.getMessage(),e);
        }finally{
                connectionWrapper.close();     
            }
    }
    
    private void fininiAmort(){
        ConnectionWrapper connectionWrapper=null;
        try {
            connectionWrapper = new ConnectionWrapper();
            Procedure procedure = new Procedure(bundle.getString("com.mx.televisa.divfilmica.paquete.finini.amort"),1);
            CallableStatement cb = connectionWrapper.prepareCall(procedure);
            cb.registerOutParameter(1, Types.VARCHAR);
            cb.execute();
            String err = cb.getString(1);
            logger.info("Extrayendo Informacon de la BD: "+"("+ bundle.getString("com.mx.televisa.divfilmica.paquete.finini.amort")+"): "+err);
           
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error","Error "+e.getMessage(),JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            logger.error(e.getMessage(),e);
        }finally{
                connectionWrapper.close();     
            }
    }
    
    /**
     * Metodo que manda a llamar el procedure hyp_fed_hyp_costeo_pkg.hf_extract_pr
     * e inserta en la tabla hyp_fed.hypdiv_datosamortizacion_erp
     */
    private void hypCosteo(){
        ConnectionWrapper connectionWrapper=null;
        
        try {
            connectionWrapper = new ConnectionWrapper();
            //Verifica si existen transacciones no costeadas
            Procedure procedureCosteo = new Procedure(bundle.getString("com.mx.televisa.divfilmica.paquete.existe.costeo"),1);
            CallableStatement cbCosteo = connectionWrapper.prepareCall(procedureCosteo);
            cbCosteo.registerOutParameter(1, Types.VARCHAR);
            cbCosteo.execute();
            String salida = cbCosteo.getString(1);
            logger.debug("COSTEO "+salida);
            if(salida.equals("SI")){
                TNoCosteadasBean tNoCosteadasBean;
                List<TNoCosteadasBean> listCosteadas = new ArrayList<TNoCosteadasBean>();
                PreparedStatement psmt = connectionWrapper.prepareStatement(bundle.getString("com.mx.televisa.divfilmica.select.tabla.costeada"));
                ResultSet rs = psmt.executeQuery();
                String desCodigo;
                String idtransaction;
                String destituloOri;
                
                while(rs.next()){
                    tNoCosteadasBean = new TNoCosteadasBean();
                    tNoCosteadasBean.setDesCodigo(rs.getString("des_codigo"));
                    tNoCosteadasBean.setIdtransaction(rs.getInt("id_transaction"));
                    tNoCosteadasBean.setDestituloOri(rs.getString("des_titulo_original"));
                    listCosteadas.add(tNoCosteadasBean);
                }
                sendMails.sendMailTransCosteadas(listCosteadas);
            }
            
            
            //Se manda el primer mail cuando se ha conectado a la BD
            sendMails.sendMailInit();
            Procedure procedure = new Procedure(bundle.getString("com.mx.televisa.divfilmica.paquete.hyp.costeo"),3);
            CallableStatement cb = connectionWrapper.prepareCall(procedure);
            cb.setString(1,getYearBD());
            cb.setString(2, getMonthBD());
            cb.registerOutParameter(3, Types.VARCHAR);
            cb.execute();
            String err = cb.getString(3);
            logger.info("Extrayendo Informacon de la BD:: "+"("+ bundle.getString("com.mx.televisa.divfilmica.paquete.hyp.costeo")+"): "+err);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error","Error "+e.getMessage(),JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            logger.error(e.getMessage(),e);
            
        }finally{
                connectionWrapper.close();     
            }
        
    }
  
    private String getMonthBD(){
        
        ConnectionWrapper connectionWrapper=null;
        String lmes=null;
        try {
            connectionWrapper = new ConnectionWrapper();
            ResultSet  rs = connectionWrapper.executeQuery(bundle.getString("com.mx.televisa.divfilmica.select.var.mes"));
            
            while(rs.next()){
                lmes = rs.getString(1);
            }
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error","Error "+e.getMessage(),JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            logger.error(e.getMessage(),e);
        }finally{
                connectionWrapper.close();     
            }
        return lmes;
    }
    
    private String getYearBD(){
        
        ConnectionWrapper connectionWrapper=null;
        String lanio=null;
        try {
            connectionWrapper = new ConnectionWrapper();
            ResultSet  rs = connectionWrapper.executeQuery(bundle.getString("com.mx.televisa.divfilmica.select.var.anio"));
            
            while(rs.next()){
                lanio = rs.getString(1);
            }
            return lanio;
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error","Error "+e.getMessage(),JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            logger.error(e.getMessage(),e);
        }finally{
                connectionWrapper.close();     
            }
        return lanio;
    }    
    
    
    

    public void executeFechasProgramacion() {
        logger.info("Extrayendo Programacion futura...");
        long tiempoInicio = System.currentTimeMillis();
        
        ConnectionWrapper connectionWrapper=null;
        try {
            connectionWrapper = new ConnectionWrapper();
            Procedure procedure = new Procedure(bundle.getString("com.mx.televisa.divfilmica.paquete.fechas.prog"),1);
            CallableStatement cb = connectionWrapper.prepareCall(procedure);
            cb.registerOutParameter(1, Types.VARCHAR);
            cb.execute();
            String err = cb.getString(1);
            logger.info("Extrayendo Informacon de la BD:: "+"("+ bundle.getString("com.mx.televisa.divfilmica.paquete.fechas.prog")+"): "+err);
           
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error","Error "+e.getMessage(),JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            logger.error(e.getMessage(),e);
        }finally{
                connectionWrapper.close();     
            }
        
        
        totalTiempo = System.currentTimeMillis() - tiempoInicio;
        logger.info("TIEMPO de carga Programacion futura " + totalTiempo / 1000 + "Segundos");
    }

    public void executeBajas() {
        logger.info("Extrayendo Bajas...");
        long tiempoInicio = System.currentTimeMillis();
        
        ConnectionWrapper connectionWrapper=null;
        try {
            connectionWrapper = new ConnectionWrapper();
            Procedure procedure = new Procedure(bundle.getString("com.mx.televisa.divfilmica.paquete.bajas"),1);
            CallableStatement cb = connectionWrapper.prepareCall(procedure);
            cb.registerOutParameter(1, Types.VARCHAR);
            cb.execute();
            String err = cb.getString(1);
            logger.info("Extrayendo Informacon de la BD:: "+"("+ bundle.getString("com.mx.televisa.divfilmica.paquete.bajas")+"): "+err);
           
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error","Error "+e.getMessage(),JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            logger.error(e.getMessage(),e);
        }finally{
                connectionWrapper.close();     
            }
        
        totalTiempo = System.currentTimeMillis() - tiempoInicio;
        logger.info("TIEMPO Bajas " + totalTiempo / 1000 + "Segundos");
    }

    public boolean executeVencimientos() {
        logger.info("Extrayendo Vencimientos...");
        long tiempoInicio = System.currentTimeMillis();
        ConnectionWrapper connectionWrapper=null;
        boolean flag = true;
        
        try {
            connectionWrapper = new ConnectionWrapper();
            logger.debug("CONECTION VIEE**** "+connectionWrapper.getConnection());
            Procedure procedure = new Procedure(bundle.getString("com.mx.televisa.divfilmica.paquete.vencimientos"),1);
            CallableStatement cb = connectionWrapper.prepareCall(procedure);
            cb.registerOutParameter(1, Types.VARCHAR);
            cb.execute();
            String err = cb.getString(1);
            logger.info("Extrayendo Informacon de la BD:: "+"("+ bundle.getString("com.mx.televisa.divfilmica.paquete.vencimientos")+"): "+err);
           
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error","Error "+e.getMessage(),JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            logger.error(e.getMessage(),e);
            flag = false;
        }finally{
                connectionWrapper.close();     
            }
        
        totalTiempo = System.currentTimeMillis() - tiempoInicio;
        logger.info("TIEMPO carga de vencimientos " + totalTiempo / 1000 + "Segundos");
        return flag;
    }

    /**
     * Se ejecutan los 4 bats y manda correos si alguno hubo error.
     * @throws IOException
     * @throws InterruptedException
     */
    public void executeBats() throws IOException, InterruptedException {
                
                sendMails = new SendMails();
                FileWritter fileWritter = new FileWritter();
                long tiempoInicio = System.currentTimeMillis();
                ExecBat exe1 = new ExecBat();
                LogBean logBean = new LogBean();
                //Solo entrara si se activo la perpetuidad
                if(this.ejecutaPerpetuidad){
                    logger.info("Ejecutando Perpetuidad...");
                    logger.info("Incio Ejecución de Bat 0 "+new Date());
                    finalMessage = finalMessage + "<br> *Inicio de Proceso: Perpetuidad ->"+ new Date();
                    exe1.batCero();
                    logger.info("Fin de Ejecución de Bat 0 "+new Date());
                    finalMessage = finalMessage + "<br> *Fin de Proceso: Perpetuidad ->"+ new Date();
                    
                    //Lee log cero
                    logBean = fileWritter.leeLogg1(0);
                    //logger.info("hubo error en bat 1? "+logBean.isHuboError());
                    if(logBean.isHuboError()){
                        String[] file = {bundleNoti.getString("com.mx.televisa.divfilmica.read.log0")};
                        sendMails.sendMail(logBean,file);
                        //  logger.info("Hubo un error en la carga de Consolidacion ");
                        System.exit(1);
                    }else{
                        //Manda correo si termino bien el bat anterior
                        sendMails.sendMailEachStep(bundleNoti.getString("com.mx.televisa.divfilmica.nom.perpetuidad"));         
                    }
                }
               
                logger.info("Ejecutando Consolidacion...");
                logger.info("Incio Ejecución de Bat 1 "+new Date());
                finalMessage = finalMessage 
                               + "<br> *Inicio de Proceso: Carga de Amortización Real, Carga de Programación Futura ->"
                               + new Date();
                exe1.batUno();
                logger.info("Fin de Ejecución de Bat 1 "+new Date());
                finalMessage = finalMessage 
                       + "<br> *Fin de Proceso: Carga de Amortización Real, Carga de Programación Futura ->"
                       + new Date()
                       + "<br> *Inicio de Proceso: Consolidación Cubo Operativo,Carga de Bajas y Vencimientos," 
                       + "Consolidación Cubo Bajas y Vencimientos ->"
                       + new Date();
                //Lee primer log
                logBean = fileWritter.leeLogg1(1);
                //logger.info("hubo error en bat 1? "+logBean.isHuboError());
                if(logBean.isHuboError()){
                    String[] file = {bundleNoti.getString("com.mx.televisa.divfilmica.read.log1")};
                    sendMails.sendMail(logBean,file);
                  //  logger.info("Hubo un error en la carga de Consolidacion ");
                    System.exit(1);
                }else{
                    //Manda correo si termino bien el bat anterior
                    sendMails.sendMailEachStep(bundleNoti.getString("com.mx.televisa.divfilmica.nom.amort.real")+","+bundleNoti.getString("com.mx.televisa.divfilmica.nom.prog.futura"));
                    SimpleThread thread1 = new SimpleThread();
                    SimpleThreadDos thread2 = new SimpleThreadDos();
                    //Se ejecuta los ods bats al mismo tiempo
                    thread1.start();
                    thread2.start();
                    //Espera que los dos terminen
                    thread1.join(0);
                    thread2.join(0);
                    //Lee segundo log
                    logBean = fileWritter.leeLogg1(2);
                    //logger.info("hubo error en bat 2? "+logBean.isHuboError());
                    if(logBean.isHuboError()){
                        String[] file2 = {bundleNoti.getString("com.mx.televisa.divfilmica.read.log2")};
                        sendMails.sendMail(logBean,file2);
                        //logger.info("Hubo un error en la carga bat 2 ");
                        System.exit(1);
                    }else{
                       
                            //Lee tercer log
                            logBean = fileWritter.leeLogg1(3);
                          //  logger.info("hubo error en bat 2? "+logBean.isHuboError());
                            if(logBean.isHuboError()){
                                String[] file3 = {bundleNoti.getString("com.mx.televisa.divfilmica.read.log3")};
                                sendMails.sendMail(logBean,file3);
                            //    logger.info("Hubo un error en la carga bat 2 ");
                                System.exit(1);
                            }else{
                                
                                    finalMessage = finalMessage 
                                        + "<br> *Fin de Proceso: Consolidación Cubo Operativo,Carga de Bajas y Vencimientos," 
                                        + "Consolidación Cubo Bajas y Vencimientos ->"
                                        + new Date()           
                                        + "<br> *Inicio de Proceso: Copiado de Real a Cubo Ejecutivo,Copiado de Proyección " 
                                        + "a Cubo Ejecutivo ->"
                                        + new Date();
                                    //Manda correo si termino bien elos bats anteriores
                                    sendMails.sendMailEachStep(bundleNoti.getString("com.mx.televisa.divfilmica.nom.cons.cubo.oper")+","+
                                                               bundleNoti.getString("com.mx.televisa.divfilmica.nom.bajas.venci")+","+
                                                               bundleNoti.getString("com.mx.televisa.divfilmica.nom.con.cub.baja.venci"));
                                    //Lee cuarto log
                                    logBean = fileWritter.leeLogg1(4);
                              //      logger.info("hubo error en bat 3? "+logBean.isHuboError());
                                    if(logBean.isHuboError()){
                                        String[] file4 = {bundleNoti.getString("com.mx.televisa.divfilmica.read.log4")};
                                        sendMails.sendMail(logBean,file4);
                                //        logger.info("Hubo un error en la carga bat 3 ");
                                        System.exit(1);
                                        
                                    }else{
                                       
                                        logger.info("Incio Ejecución de Bat 4 "+new Date());
                                        exe1.batCuatro();
                                        logger.info("Fin de Ejecución de Bat 4 "+new Date());
                                        totalTiempo = System.currentTimeMillis() - tiempoInicio;
                                        logger.info("TIEMPO de ejecutar consolidacion " + totalTiempo / 1000 + "Segundos");
                                        //Lee quinto log
                                        logBean = fileWritter.leeLogg1(5);
                                  //      logger.info("hubo error en bat 4? "+logBean.isHuboError());
                                        if(logBean.isHuboError()){
                                            String[] file5 = {bundleNoti.getString("com.mx.televisa.divfilmica.read.log5")};
                                            sendMails.sendMail(logBean,file5);
                                    //        logger.info("Hubo un error en la carga bat 4 ");
                                        }else{
                                            finalMessage = finalMessage 
                                                + "<br> *Fin de Proceso:Copiado de Real a Cubo Ejecutivo,Copiado de Proyección " 
                                                + "a Cubo Ejecutivo ->"
                                                + new Date();
                                            //Manda correo si termino bien elos bats anteriores
                                            sendMails.sendMailEachStep(bundleNoti.getString("com.mx.televisa.divfilmica.nom.cop.real.cub.eje")+","+
                                                                       bundleNoti.getString("com.mx.televisa.divfilmica.nom.cop.proy.cub.eje"));
                                        }
                                    }
                        }
                
                    }
            }
    
    }
        
    
    /*
    public static void main(String args[]){
        new HypDivExecuteProcess();
    }*/

 
}

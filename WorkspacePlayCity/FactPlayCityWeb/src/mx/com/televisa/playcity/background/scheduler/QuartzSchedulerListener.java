/*
* Project: AISA
*
* File: QuartzSchedulerListener.java
*
* Created on: Abril 5, 2019 at 11:00
*
* Copyright (c) - Kaz Consulting - 2019
*/

package mx.com.televisa.playcity.background.scheduler;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.quartz.impl.StdSchedulerFactory;

/*
* Clase listener que se inicia al arranque de la app  
* y contiene el Quartz Scheduler
*
* @author Kaz Consulting / Jonathan Mariche Catana
*
* @version 1.0.0
*
* @date Abril 5, 2019 at 11:00 am
*/

public class QuartzSchedulerListener implements ServletContextListener {

    private static Scheduler poScheduler;
	private static Logger    poLogger = Logger.getLogger(QuartzSchedulerListener.class);
	private static final String psTRIGGER_GROUP_NAME = "mainTriggerGroup";
	private static final String psJOB_GROUP_NAME = "mainJobGroup";

	//Se ejecuta cuando se detiene la aplicación
    @Override
    public void contextDestroyed(ServletContextEvent toServletContext) {
	    try{	
    		if(poScheduler != null) {	    				       
    			poScheduler.shutdown(false);
	    	}    
    	} catch (Exception toException) {
    		poLogger.error("Error al apagar Quartz scheduler: " 
    				+ toException.getMessage(), toException);    		
    	}
    }

	//Se ejecuta cuando se inicia la aplicación
    @Override
    public void contextInitialized(ServletContextEvent toServletContext) {        
        try {
        	poScheduler = ((StdSchedulerFactory) toServletContext.getServletContext()
                    .getAttribute(QuartzInitializerListener.QUARTZ_FACTORY_KEY))
                    .getScheduler();            
        } catch (SchedulerException toException) {
        	poLogger.error("Error al obtener Quartz scheduler: " 
    				+ toException.getMessage(), toException);
        }
    }
    
    //Crea un job que se ejecuta de acuerdo al plan que se le indica    
    public static boolean createJob(Class<? extends Job> toJobClass,
    								String tsJobName,
    								String tsTriggerName,
    								CronScheduleBuilder toSchedule,
    								String tsDataProperty){    	
    	try {
	    	JobDetail loJob = JobBuilder.newJob(toJobClass)
	                .withIdentity(tsJobName, psJOB_GROUP_NAME).build();
	
	        Trigger loTrigger = TriggerBuilder
	                .newTrigger()
	                .withIdentity(tsTriggerName, psTRIGGER_GROUP_NAME)
	                .usingJobData("dataProperty", tsDataProperty)
	                .startNow()
	                .withSchedule(toSchedule)
	                .build();
	        // Tell quartz to schedule the job using our trigger        
			poScheduler.scheduleJob(loJob, loTrigger);
			poLogger.info("Se creó el job: " + loJob.getKey().getName() +
					" con el trigger: " + loTrigger.getKey().getName());
			return true;
		} catch (SchedulerException toException) {
			poLogger.error(toException.getStackTrace(),toException);			
		}
    	return false;
    }
    
    //Elimina un job existente    
    public static boolean deleteJob(String tsJobName, String tsTriggerName){
    	try {	
    		TriggerKey loTriggerKey = TriggerKey.triggerKey(tsTriggerName, psTRIGGER_GROUP_NAME);  
    	    JobKey loJobKey = JobKey.jobKey(tsJobName, psJOB_GROUP_NAME);      	        	   
    	    JobDetail loJob = (JobDetail) poScheduler.getJobDetail(loJobKey);
    	    Trigger loTrigger = (Trigger) poScheduler.getTrigger(loTriggerKey);        	    
    	    if (loJob != null) {   
    	    	if(loTrigger != null){
    	    		poScheduler.pauseTrigger(loTriggerKey);
    	    	    poLogger.info("Se pausó el trigger: " + loTriggerKey.getName());
    	    	    poScheduler.unscheduleJob(loTriggerKey);
    	    	    poLogger.info("Se eliminó el trigger: " + loTriggerKey.getName());
    	    	}	    	    
    	    	else{
    	    		poLogger.error("Error al eliminar trigger: " + loTriggerKey.getName() + " no existe");
    	    		return false;
    	    	}
	    	    poScheduler.pauseJob(loJobKey);
	    	    poLogger.info("Se pausó el job: " + loJobKey.getName());	    	    
	    	    poScheduler.deleteJob(loJobKey);    
	    	    poLogger.info("Se eliminó el job: " + loJobKey.getName());
	    	    return true;
    	    }
    	    else{
    	    	poLogger.error("Error al eliminar job: " + loJobKey.getName() + " no existe");
    	    	return false;
    	    }
		} catch (SchedulerException toException) {
			poLogger.error(toException.getStackTrace(),toException);
		}
    	return false;
    }
    
    //Actualiza un job existente    
    public static void updateJob(Class<? extends Job> toJobClass,
    								String tsJobName,
    								String tsTriggerName,
    								CronScheduleBuilder toSchedule,
    								String tsDataProperty){
    	if(deleteJob(tsJobName, tsTriggerName))
    		createJob(toJobClass, tsJobName, tsTriggerName, toSchedule, tsDataProperty);
    }
 
  //	Ejecuta un job inmediatamente    
    public static boolean triggerJob(String tsJobName, String tsDataProperty){
    	try {	
    		JobKey loJobKey = new JobKey(tsJobName, psJOB_GROUP_NAME);
    		JobDetail loJob = (JobDetail) poScheduler.getJobDetail(loJobKey); 		    		
    	    if (loJob != null) {                    
    	    	JobDataMap data = new JobDataMap();
    	    	data.put("dataProperty", tsDataProperty);
    	    	poScheduler.triggerJob(loJobKey, data);
	    	    poLogger.info("Se ejecutó el job: " + loJobKey.getName());
	    	    return true;
    	    }
    	    else{
    	    	poLogger.error("Error al ejecutar job: " + loJobKey.getName() + " no existe");
    	    	return false;
    	    }
		} catch (SchedulerException toException) {
			poLogger.error(toException.getStackTrace(),toException);
		}
    	return false;
    }
    
}
/*
* Project: AISA
*
* File: JobsExecuter.java
*
* Created on: Abril 5, 2019 at 11:00
*
* Copyright (c) - Kaz Consulting - 2019
*/

package mx.com.televisa.playcity.background.jobs;

import mx.com.televisa.playcity.view.BillingExtractionSchedulerView;
import org.apache.log4j.Logger;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Job;

/*
* Clase que ejecuta la extracción de información de los casinos en un job
*
* @author Kaz Consulting / Jonathan Mariche Catana
*
* @version 1.0.0
*
* @date Abril 5, 2019 at 11:00 am
*/

public class JobsExecuter implements Job {	
	
	private static Logger poLogger = Logger.getLogger(BillingExtractionSchedulerView.class);
		
	//Este método se llama desde el Job para ejecutar operaciones de extracción 
	@Override
	public void execute(JobExecutionContext toContext) 
			throws JobExecutionException {
		try {						
			JobDataMap loIdCasino = toContext.getMergedJobDataMap();			
			System.out.println("Proceso de extracción del casino " + loIdCasino.getString("dataProperty"));
	    } catch (Exception toException) {
	    	poLogger.error(toException.getStackTrace());
	    	toException.printStackTrace();
	    }
	}	
	
}

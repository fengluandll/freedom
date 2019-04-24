/*
* Project: AISA
*
* File: BillingExtractionSchedulerView.java
*
* Created on: Abril 5, 2019 at 11:00
*
* Copyright (c) - Kaz Consulting - 2019
*/

package mx.com.televisa.playcity.view;

import javax.annotation.PostConstruct;
import mx.com.televisa.playcity.background.jobs.JobsExecuter;
import mx.com.televisa.playcity.background.scheduler.QuartzSchedulerListener;
import org.quartz.CronScheduleBuilder;

/*
* Controlador de la vista del administrador de los procesos de extracción de
* facturas de los casinos.   
*
* @author Kaz Consulting / Jonathan Mariche Catana
*
* @version 1.0.0
*
* @date Abril 5, 2019 at 11:00 am
*/

public class BillingExtractionSchedulerView {
		
	@PostConstruct
	public void init() {			        
    }
	
	public void createJob(){				
		CronScheduleBuilder loSchedule = CronScheduleBuilder
				.cronSchedule("0 08 14 * * ?")
				.withMisfireHandlingInstructionDoNothing();
		QuartzSchedulerListener.createJob(JobsExecuter.class,"job1","trigger1",
				loSchedule, "Casino1");
					
		QuartzSchedulerListener.triggerJob("job1","Casino1");
	}
	
}

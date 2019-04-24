package mx.com.televisa.derechocorporativo.test;

import mx.com.televisa.derechocorporativo.modules.login.LoginBean;

import org.apache.log4j.Logger;

public class Testlog {
	private final static Logger logger = Logger.getLogger(Testlog.class);
	public static void main(String rgs[]){
		logger.debug("My Debug Log");
        logger.info("My Info Log");
        logger.warn("My Warn Log");
        logger.error("My error log");
        logger.fatal("My fatal log");
                 
	}
}

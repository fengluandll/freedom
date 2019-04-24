package mx.javaonline.beans;

import java.io.Serializable;

import org.apache.log4j.Logger;

public class CronometroTiempo implements Serializable{
	

	private static boolean issuspended = false;
	private String sCronometro;
	int hora = 0, min = 0, seg = 0;
	int ds = 0;
	private org.apache.log4j.Logger logger = Logger.getLogger(CronometroTiempo.class);
	
	
	public void correCronometro(){
		logger.debug("ENTRO correCronometro ");
		logger.debug("ds "+ds);
		logger.debug("hora "+hora);
		logger.debug("min "+min);
		logger.debug("seg "+seg);
		while (true) {
            if (ds == 99) {
                ds = 0;
                seg++;
            }
            if (seg == 59) {
                seg = 0;
                min++;
            }
            if (min == 59) {
                min = 0;
                hora++;
            }
            ds++;

           // this.setsCronometro(hora + ":" + min + ":" + seg + ":" + ds);
	}
	}
/*	
    Thread hilo = new Thread() {

        @Override
        public void run() {
        	
            try {
                while (true) {
                    if (ds == 99) {
                        ds = 0;
                        seg++;
                    }
                    if (seg == 59) {
                        seg = 0;
                        min++;
                    }
                    if (min == 59) {
                        min = 0;
                        hora++;
                    }
                    ds++;

                    sCronometro = hora + ":" + min + ":" + seg + ":" + ds;

                    hilo.sleep(10);
                }
            } catch (java.lang.InterruptedException ie) {
                logger.error(ie);
            }
        }
    };
    
    public void inciaCronometro(){
    	if (!issuspended) {
    		logger.debug("INICIO CRONOMETRO");
            hilo.start();

        } else {
            hilo.resume();
            issuspended = false;

        }
    }
    
    public void pausaCronometro(){
    	logger.debug("PAUSO CRONOMETRO");
    	 hilo.suspend();
         issuspended = true;
    }
    
    public void detieneCronometro(){
    	logger.debug("DETUVO CRONOMETRO");
    	hilo.suspend();//se suspende el hilo.. (NO utilizamos hilo.stop() porque si lo usamos, el hilo se "muere")
        ds = seg = min = hora = 0;
        sCronometro = "0:0:0:0";
        issuspended = true;
    }
*/
	public String getsCronometro() {
		return sCronometro;
	}

	public void setsCronometro(String sCronometro) {
		this.sCronometro = sCronometro;
	}
    
    

}

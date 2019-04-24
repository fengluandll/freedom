package mx.com.televisa;

import mx.com.televisa.divfilmica.automatic.HypDivExecuteProcess;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Example {
    
    //static Logger logger = Logger.getLogger(Example.class);
    
    public Example() {
     //   PropertyConfigurator.configure("src/log4j.properties");
       // logger.debug("Hola");
    }
    
    public static void main(String[] args) {
        HypDivExecuteProcess h = new HypDivExecuteProcess();
    }
}

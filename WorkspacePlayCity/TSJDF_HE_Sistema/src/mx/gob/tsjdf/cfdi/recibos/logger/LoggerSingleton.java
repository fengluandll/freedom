package mx.gob.tsjdf.cfdi.recibos.logger;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerSingleton
{
  private static String logName = null;
  private static String path = null;
  private Logger log = null;
  private static Boolean success;
  
  private LoggerSingleton()
  {
    this.log = Logger.getLogger(logName);
    


    FileHandler fh = null;
    try
    {
      System.out.println("Inicializando bitacora... ");
      
      fh = new FileHandler(path + logName);
      fh.setFormatter(new TxtFormatter());
      this.log.addHandler(fh);
      this.log.setLevel(Level.ALL);
      
      success = Boolean.valueOf(true);
    }
    catch (SecurityException e)
    {
      System.out.println("Ocurrio una excepcion de seguridad al tratar de inicializar la bitacora.");
      
      System.out.println("La bitacora no se podra escribir en archivo. " + path);
      e.printStackTrace();
    }
    catch (IOException e)
    {
      System.out.println("Ocurreo una excepcion de E/S al tratar de inicializar la bitacora.");
      
      System.out.println("La bitacora no se podra escribir en archivo. " + path);
      e.printStackTrace();
    }
    catch (IllegalArgumentException e)
    {
      System.out.println("Ocurrio una excepcion de argumento no valido al tratar de inicializar la bitacora.");
      
      System.out.println("La bitacora no se podra escribir en archivo. " + path);
      e.printStackTrace();
    }
  }
  
  private static class LoggerSingletonHolder
  {
    private static final LoggerSingleton INSTANCE = new LoggerSingleton();
    
  }
  
  public static Logger getInstance(String name, String filePath)
  {
    if (logName == null) {
      logName = name;
    }
    if (path == null) {
      path = filePath;
    }
    //LoggerSingleton logger = LoggerSingletonHolder.INSTANCE;
    LoggerSingleton logger = new LoggerSingleton();
    
    return logger.log;
  }
  
  public static Logger getInstance()
  {
    if ((logName != null) && (path != null))
    {
      //LoggerSingleton logger = LoggerSingletonHolder.INSTANCE;
    	LoggerSingleton logger = new LoggerSingleton();
      
      return logger.log;
    }
    return null;
  }
  
  public Boolean getSuccess()
  {
    return success;
  }
}

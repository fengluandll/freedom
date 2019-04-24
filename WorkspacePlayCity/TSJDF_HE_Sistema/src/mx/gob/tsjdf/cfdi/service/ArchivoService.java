package mx.gob.tsjdf.cfdi.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import mx.gob.tsjdf.cfdi.dto.EmpleadoTokenDto;
import mx.gob.tsjdf.util.Funciones;




public class ArchivoService {
	
	//private Logger logger;
	private static org.apache.log4j.Logger logger = Logger.getLogger(ArchivoService.class);
	public Funciones funciones;
	
	public ArchivoService(){
		//this.logger = LoggerSingleton.getInstance();
		funciones = new Funciones();
	}

	
	public String crearDirectorios(String dirRaiz, String dirEjecucion,String descripcion)
	  {
	    String resp = null;
	    File raiz = new File(dirRaiz);
	    File ejecucion = new File(raiz.getAbsolutePath() + "/" + descripcion + "_" + dirEjecucion);
	    File dirLog = new File(ejecucion.getAbsolutePath() + "/" + "LOG");
	    File dirTxtSinToken = new File(ejecucion.getAbsolutePath() + "/" + "TXTSINTOKEN");
	    File dirTxts = new File(ejecucion.getAbsolutePath() + "/" + "TXTS");
	    if ((raiz.exists()) && 
	      (ejecucion.mkdir()) && 
	      (dirTxtSinToken.mkdir()) && (dirTxts.mkdir()) && (dirLog.mkdir())) {
	      resp = ejecucion.getAbsolutePath();
	    }
	    return resp;
	  }
	  
	  public boolean creaArchivo(String pathAbs, StringBuffer contenido)
	  {
	    try
	    {
	      FileOutputStream fos = new FileOutputStream(pathAbs);
	      Writer out = new OutputStreamWriter(fos, "UTF8");
	      out.write(contenido.toString());
	      out.flush();
	      out.close();
	      return true;
	    }
	    catch (IOException e)
	    {
	      e.printStackTrace();
	      //logger.severe("No es posible crear el archivo TXT " +pathAbs);
	      //funciones.mostrarMensaje("No es posible crear el archivo TXT " + pathAbs, "Error", "ERROR");
	    }
	    return false;
	  }
	  
	  public int verificarCreadosConToken(String pathSinToken, String pathConToken)
	  {
	    File dirSinToken = new File(pathSinToken);
	    File dirConToken = new File(pathConToken);
	    if ((dirSinToken.exists()) && (dirConToken.exists()))
	    {
	      int archivosConToken = dirConToken.list().length;
	      if (dirSinToken.list().length == archivosConToken) {
	        return archivosConToken;
	      }
	      return -1;
	    }
	    return -1;
	  }
	  
	  public void eliminarDirectorio(String pathDir)
	  {
	    
	    int contFiles = 0;
	    File dir = new File(pathDir);
	    if ((dir.exists()) && (dir.isDirectory()))
	    {
	      String[] archivos = dir.list();
	      if (archivos.length > 0)
	      {
	    	  funciones.variableLog.append("Eliminando archivos del directorio  " + dir.getAbsolutePath()+"\n");
	    	  logger.info("se eliminaran archivos temporales... por favor, espere");
	        System.out.println("se eliminaran archivos temporales... por favor, espere");
	        for (int i = 0; i < archivos.length; i++) {
	          if (borraArchivo(pathDir + archivos[i])) {
	            contFiles++;
	          }
	        }
	      }
	      dir.delete();
	      logger.info("Se elimino el directorio " + dir.getAbsolutePath() + " con " + contFiles + " archivos contenidos");
	      funciones.variableLog.append("Se elimino el directorio " + dir.getAbsolutePath() + " con " + contFiles + " archivos contenidos"+"\n");
	    }
	  }
	  
	  public boolean borraArchivo(String pathArchivo)
	  {
	    File archivo = new File(pathArchivo);
	    if (archivo.exists())
	    {
	      if (archivo.delete()) {
	        return true;
	      }
	      return false;
	    }
	    return false;
	  }
	  
	  public StringBuffer leerArchivoTxt(String path)
	  {
	    StringBuffer contenido = new StringBuffer("");
	    try
	    {
	      BufferedReader in = new BufferedReader(new InputStreamReader(
	        new FileInputStream(path), "UTF8"));
	      String str;
	      while ((str = in.readLine()) != null)
	      {
	        //String str;
	        contenido.append(str + "\r\n");
	      }
	      in.close();
	    }
	    catch (IOException e)
	    {
	      e.printStackTrace();
	      Funciones.variableLog.append("No puede leerse el contenido de un archivo"+"\n");
	    }
	    return contenido;
	  }
	  
	  public ArrayList<String> leerArchivoEmpleados(String path)
	  {
	    
	    ArrayList<String> contenido = new ArrayList<>();
	    try
	    {
	      BufferedReader in = new BufferedReader(new InputStreamReader(
	        new FileInputStream(path), "UTF8"));
	      String str;
	      while ((str = in.readLine()) != null)
	      {
	        //String str;
	        contenido.add(str);
	      }
	      in.close();
	    }
	    catch (IOException e)
	    {
	    	funciones.variableLog.append("No puede leerse el contenido de un archivo que contiene los numeros de empleados"+"\n");
	    	funciones.variableLog.append(e.getMessage()+"\n");
	      e.printStackTrace();
	    }
	    return contenido;
	  }
	  
	
	  
	  public EmpleadoTokenDto recrearConToken(String nombreTxt, String token, String fechaEjecucionTsjdf, String dirTxt, String dirToken)
	  {
	    EmpleadoTokenDto dto = new EmpleadoTokenDto();
	    
	    String pathArchivoTxt = dirTxt + nombreTxt;
	    
	    StringBuffer contenido = leerArchivoTxt(pathArchivoTxt);
	    
	    StringBuffer contenidoModif = new StringBuffer(contenido.toString().replaceFirst("token", token));
	    
	    String pathArchivoToken = dirToken + fechaEjecucionTsjdf + "_" + token + ".txt";
	    
	    boolean creado = creaArchivo(pathArchivoToken, contenidoModif);
	    if (creado)
	    {
	      dto.setNumeroEmpleado(nombreTxt.substring(0, nombreTxt.lastIndexOf("-")));
	      dto.setToken(token);
	      dto.setArchivo(pathArchivoToken.substring(pathArchivoToken.lastIndexOf("/") + 1, pathArchivoToken.length()));
	      Funciones.variableLog.append("empleado : " + dto.getNumeroEmpleado() + " token : " + dto.getToken() + " archivo : " + dto.getArchivo()+"\n");
	      
	    }
	    return dto;
	  }
	  
	  public StringBuffer leerArchivoError(String path)
	  {
	    StringBuffer contenido = new StringBuffer("");
	    try
	    {
	      BufferedReader in = new BufferedReader(new InputStreamReader(
	        new FileInputStream(path), "UTF8"));
	      String str;
	      while ((str = in.readLine()) != null)
	      {
	        //String str;
	        contenido.append(str + "\r\n");
	      }
	      in.close();
	    }
	    catch (IOException e)
	    {
	    	Funciones.variableLog.append("No es posible leer el contenido del archivo " + path);
	      e.printStackTrace();
	    }
	    return contenido;
	  }
	  
	  public void copiaArchivo(String rutaOrigen, String rutaDestino)
	  {
	    File origen = new File(rutaOrigen);
	    File destino = new File(rutaDestino);
	    try
	    {
	      InputStream in = new FileInputStream(origen);
	      OutputStream out = new FileOutputStream(destino);
	      byte[] buffer = new byte[1024];
	      int len;
	      while ((len = in.read(buffer)) > 0)
	      {
	        //int len;
	        out.write(buffer, 0, len);
	      }
	    }
	    catch (FileNotFoundException e)
	    {
	      e.printStackTrace();
	      funciones.variableLog.append("No puede encontrarse alguno de las rutas del archivo origen o destino\n");
	    }
	    catch (IOException e)
	    {
	      e.printStackTrace();
	      funciones.variableLog.append("No puede escribirse en el archivo destino \n");
	    }
	  }
	  
	  public void limpiaDirectorio(String directorio)
	  {
	    int contFiles = 0;
	    File dir = new File(directorio);
	    if ((dir.exists()) && (dir.isDirectory()))
	    {
	      String[] archivos = dir.list();
	      if (archivos.length > 0)
	      {
	    	  funciones.variableLog.append("Eliminando archivos del directorio  " + dir.getAbsolutePath() + "\n");
	    	  logger.info("se eliminaran archivos temporales... por favor, espere");
		       System.out.println("se eliminaran archivos temporales... por favor, espere");
		       funciones.variableLog.append("se eliminaran archivos temporales... por favor, espere" + "\n");
	        for (int i = 0; i < archivos.length; i++) {
	          if (borraArchivo(directorio + archivos[i])) {
	            contFiles++;
	          }
	        }
	      }
	      funciones.variableLog.append("Se elimino el directorio " + dir.getAbsolutePath() + " con " + contFiles + " archivos contenidos" + "\n");
	      logger.info("Se elimino el directorio " + dir.getAbsolutePath() + " con " + contFiles + " archivos contenidos");
	    }
	  }
	  
	  private boolean contieneNull(StringBuffer contenido)
	  {
	    if (contenido.toString().toUpperCase().contains("NULL")) {
	      return true;
	    }
	    return false;
	  }
	  
	  public boolean verificaNullTxts(String directorio)
	  {
	    
	    boolean resp = true;
	    int cont = 0;int contValidos = 0;int contInvalidos = 0;
	    File dir = new File(directorio);
	    if ((dir.exists()) && (dir.isDirectory()))
	    {
	      File[] archivos = dir.listFiles();
	      for (File archivo : archivos)
	      {
	        StringBuffer contenido = leerArchivoTxt(archivo.getAbsolutePath());
	        if (contieneNull(contenido))
	        {
	        	funciones.variableLog.append("El archivo: " + archivo.getName() + " contiene la palabra null" + "\n");
	        	logger.info("El archivo: " + archivo.getName() + " contiene la palabra null");
	          this.borraArchivo(archivo.getAbsolutePath());//Argumel
	          //resp = false;
	          resp = true;
	          contInvalidos++;
	        }
	        else
	        {
	          contValidos++;
	          funciones.variableLog.append("Archivo " + archivo.getName() + " valido" + "\n");
	          this.logger.info("Archivo " + archivo.getName() + " valido");
	        }
	      }
	      funciones.variableLog.append("Archivos validos = " 		   + contValidos + "\n");
	      funciones.variableLog.append("Archivos invalidos = " 		   + contInvalidos + "\n");
	      funciones.variableLog.append("Total archivos verificados = " + cont + "\n");
	      
	      logger.info("Archivos validos = " 		   + contValidos);
	      logger.info("Archivos invalidos = " 		   + contInvalidos);
	      logger.info("Total archivos verificados = " + cont);
	      
	    }
	    return resp;
	  }
	  
	  public File[] getListaTxtsPorTransferir(String pathLog, String nombreLogActual, String pathTXTS)
	  {
	    File[] archivosQueFaltaEnviar = null;
	    ArrayList<String> listaTxtEnviados = new ArrayList<>(9500);
	    ArrayList<File> listaTxtNoEnviados = new ArrayList<>(9500);
	    ArrayList<File> logs = getLogsAnteriores(pathLog, nombreLogActual);
	    if ((logs != null) && (logs.size() > 0))
	    {
	    	funciones.variableLog.append("total de logs encontrados: " + logs.size() + "\n");
	      for (File log : logs) {
	        procesaLog(log.getAbsolutePath(), listaTxtEnviados);
	      }
	      File[] arrayTxts = getListaTxts(pathTXTS);
	      if ((arrayTxts != null) && (listaTxtEnviados.size() > 0))
	      {
	        for (File txt : arrayTxts)
	        {
	          boolean enviado = false;
	          for (String nombreTxt : listaTxtEnviados) {
	            if (txt.getName().equals(nombreTxt))
	            {
	              enviado = true;
	              break;
	            }
	          }
	          if (!enviado) {
	            listaTxtNoEnviados.add(txt);
	          }
	        }
	        archivosQueFaltaEnviar = new File[listaTxtNoEnviados.size()];
	        listaTxtNoEnviados.toArray(archivosQueFaltaEnviar);
	      }
	      else if (listaTxtEnviados.size() == 0)
	      {
	        archivosQueFaltaEnviar = arrayTxts;
	      }
	    }
	    else
	    {
	      archivosQueFaltaEnviar = getListaTxts(pathTXTS);
	    }
	    return archivosQueFaltaEnviar;
	  }
	  
	  public ArrayList<File> getLogsAnteriores(String directorioLog, String nombreLogActual)
	  {
	    ArrayList<File> logs = new ArrayList<>();
	    File directorio = new File(directorioLog);
	    if ((directorio.exists()) && (directorio.isDirectory()))
	    {
	      File[] files = directorio.listFiles();
	      for (File file : files) {
	        if ((file.getName().endsWith(".log")) && (!file.getName().equals(nombreLogActual))) {
	          logs.add(file);
	        }
	      }
	    }
	    else
	    {
	    	funciones.variableLog.append("No existe el directorio log" + "\n");
	    	logger.info("No existe el directorio log ");
	    }
	    return logs;
	  }
	  
	  public File[] getListaTxts(String directorio)
	  {
	    File carpeta = new File(directorio);
	    if ((carpeta.exists()) && (carpeta.isDirectory())) {
	      return carpeta.listFiles();
	    }
	    return null;
	  }
	  
	  public void procesaLog(String pathLog, ArrayList<String> listaTxtEnviados)
	  {
	    try
	    {
	      BufferedReader in = new BufferedReader(new InputStreamReader(
	        new FileInputStream(pathLog), "UTF8"));
	      String str;
	      while ((str = in.readLine()) != null)
	      {
	        //String str;
	        if (str.contains("transferido : ")) {
	          listaTxtEnviados.add(str.substring(
	            str.indexOf("transferido : ") + 14, str.length()));
	        }
	      }
	      in.close();
	    }
	    catch (IOException e)
	    {
	      e.printStackTrace();
	      funciones.variableLog.append("No puede leerse el contenido de un archivo" + "\n");
	      logger.info("No puede leerse el contenido de un archivo ");
	    }
	  }

}

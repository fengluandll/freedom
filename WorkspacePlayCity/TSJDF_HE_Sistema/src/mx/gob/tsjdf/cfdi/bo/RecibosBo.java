package mx.gob.tsjdf.cfdi.bo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RecibosBo {
	
	 public static String getClave(String cadena){
		 
	    return cadena.substring(0, cadena.indexOf(" "));
	  }
	  
	  public static String getDescripcion(String cadena)
	  {
	    return cadena.substring(cadena.indexOf(" ") + 1, cadena.length()).toUpperCase();
	  }
	  
	  public static double Redondear(double numero)
	  {
	    return Math.rint(numero * 100.0D) / 100.0D;
	  }
	  
	  public static String getCodigo(String cadena)
	  {
	    if (cadena != null)
	    {
	      if (cadena.length() > 4) {
	        return cadena.substring(0, cadena.length() - 4).trim();
	      }
	      return cadena;
	    }
	    return "";
	  }
	  
	  public static String getUniverso(String cadena)
	  {
	    if (cadena != null)
	    {
	      if (cadena.length() > 1) {
	        return cadena.substring(cadena.length() - 1, cadena.length());
	      }
	      return cadena;
	    }
	    return "";
	  }
	  
	  public static String truncCuenta(String cuentaBancaria)
	  {
	    String cuenta = "";
	    if (cuentaBancaria != null) {
	      if (cuentaBancaria.length() > 4) {
	        cuenta = cuentaBancaria.substring(cuentaBancaria.length() - 4, cuentaBancaria.length());
	      }
	    }
	    return cuenta;
	  }
	  
	  public static String getFecha(Date tiempo)
	  {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	    return sdf.format(tiempo);
	  }
	  
	  public static String intToStr(int num)
	  {
	    String cadena = "";
	    if (num < 100)
	    {
	      for (int i = 0; i < 3 - Integer.toString(num).length(); i++) {
	        cadena = "0" + cadena;
	      }
	      return cadena + Integer.toString(num);
	    }
	    return Integer.toString(num);
	  }
	  
	  public static String getLogName(String fechaPaga, Date tiempo)
	  {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
	    return "log_" + fechaPaga.replace("/", "") + "_" + sdf.format(tiempo) + ".log";
	  }
	  
	  public static String getLogNameCopia(String fechaPaga, Date tiempo)
	  {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
	    return "log_" + fechaPaga.replace("/", "") + "_" + sdf.format(tiempo) + ".pdf";
	  }
	  
	  public static String getLogNameError(Date tiempo)
	  {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
	    return "log_obtencion_errores_" + sdf.format(tiempo) + ".log";
	  }
	  
	  public static String getNombreReporteHtml(String fechaPaga, Date tiempo)
	  {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
	    return "reporte_" + fechaPaga.replace("/", "") + "_" + sdf.format(tiempo) + ".html";
	  }
	  
	  public static String getNombreReporteErrores(Date tiempo)
	  {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
	    return "reporte_errores_" + sdf.format(tiempo) + ".pdf";
	  }
	  
	  public static String getNombreReportePdfRelacionETA(Date tiempo)
	  {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
	    return "relacion_" + sdf.format(tiempo) + ".pdf";
	  }
	  
	  public static String getNombreReporteExcelRelacionETA(Date tiempo)
	  {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
	    return "relacion_" + sdf.format(tiempo) + ".xls";
	  }
	  
	  public static String getDirInicial(Date timepoEjecucion, String fechaPaga)
	  {
	    if (fechaPaga.length() == 10)
	    {
	      SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
	      return fechaPaga.substring(6, fechaPaga.length()) + fechaPaga.substring(3, 5) + fechaPaga.substring(0, 2) + "_" + sdf.format(timepoEjecucion);
	    }
	    return null;
	  }
	  
	  public static String getDirTxtSinToken(String dirEjecucion)
	  {
	    return dirEjecucion + "/TXTSINTOKEN/";
	  }
	  
	  public static String getDirTxts(String dirEjecucion)
	  {
	    return dirEjecucion + "/TXTS/";
	  }
	  
	  public static String getDirLog(String dirEjecucion)
	  {
	    return dirEjecucion + "/LOG/";
	  }
	  
	  public static String validaNullEspacios(String cadena)
	  {
	    return cadena.replaceAll("null", " ").replaceAll("NULL", " ").replace("Null", "").replace("   ", " ").replace("  ", " ");
	  }

}

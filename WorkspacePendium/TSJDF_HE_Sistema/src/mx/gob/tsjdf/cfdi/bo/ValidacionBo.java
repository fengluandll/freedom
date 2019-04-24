package mx.gob.tsjdf.cfdi.bo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mx.gob.tsjdf.cfdi.dto.ErrorDatoEmpleadoDto;

public class ValidacionBo
{
  public static int recibosValidos = 0;
  public static int recibosInvalidos = 0;
  private static ErrorDatoEmpleadoDto errorDatoEmpDto;
  
  public static String getNombreErroresValidacion(Date tiempo, String fechaPaga,String descripcion)
  {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
    //return "reporte_validacion_" + fechaPaga.replaceAll("/", "") + "_" + sdf.format(tiempo) + ".xls";
    return descripcion + "_" + fechaPaga.replaceAll("/", "") + "_" + sdf.format(tiempo) + ".xls";
  }
  
  public static ErrorDatoEmpleadoDto getErrorDatoEmpDto()
  {
    return errorDatoEmpDto;
  }
  
  private static String validaCadena(String dato)
  {
    if (dato == null) {
      return "Dato nulo";
    }
    if (dato.isEmpty()) {
      return "Dato vacio";
    }
    if (dato.toUpperCase().contains("NULL")) {
      return "El dato contiene la palabra null";
    }
    if (dato.contains("  ")) {
      return "El dato contiene doble espacio";
    }
    return "valido";
  }
  
  public static boolean rfcInvalido(String rfc)
  {
    errorDatoEmpDto = new ErrorDatoEmpleadoDto();
    errorDatoEmpDto.setCampo("RFC");
    errorDatoEmpDto.setDato(rfc);
    
    String msj = validaCadena(rfc);
    if (msj.equals("valido"))
    {
      Pattern pat = Pattern.compile("[A-Z,Ñ,&]{3,4}[0-9]{2}[0-1][0-9][0-3][0-9][A-Z,0-9]?[A-Z,0-9]?[0-9,A-Z]?");
      Matcher mat = pat.matcher(rfc);
      if (rfc.length() == 13)
      {
        if (mat.matches()) {
          return false;
        }
        errorDatoEmpDto.setError("El rfc no cumple con la expresion regular [A-Z,Ñ,&]{3,4}[0-9]{2}[0-1][0-9][0-3][0-9][A-Z,0-9]?[A-Z,0-9]?[0-9,A-Z]?");
        return true;
      }
      errorDatoEmpDto.setError("El rfc no es de 13 caracteres");
      return true;
    }
    errorDatoEmpDto.setError(msj);
    return true;
  }
  
  public static boolean curpInvalido(String curp)
  {
    errorDatoEmpDto = new ErrorDatoEmpleadoDto();
    errorDatoEmpDto.setCampo("CURP");
    errorDatoEmpDto.setDato(curp);
    
    String msj = validaCadena(curp);
    if (msj.equals("valido"))
    {
      Pattern pat = Pattern.compile("[A-Z][A,E,I,O,U,X][A-Z]{2}[0-9]{2}[0-1][0-9][0-3][0-9][M,H][A-Z]{2}[B,C,D,F,G,H,J,K,L,M,N,Ñ,P,Q,R,S,T,V,W,X,Y,Z]{3}[0-9,A-Z][0-9]");
      Matcher mat = pat.matcher(curp);
      if (mat.matches()) {
        return false;
      }
      errorDatoEmpDto.setError("EL CURP no cumple con la expresion regular [A-Z][A,E,I,O,U,X][A-Z]{2}[0-9]{2}[0-1][0-9][0-3][0-9][M,H][A-Z]{2}[B,C,D,F,G,H,J,K,L,M,N,Ñ,P,Q,R,S,T,V,W,X,Y,Z]{3}[0-9,A-Z][0-9]");
      return true;
    }
    errorDatoEmpDto.setError(msj);
    return true;
  }
  
  public static boolean cadenaInvalida(String dato, int longitud, String campo)
  {
    errorDatoEmpDto = new ErrorDatoEmpleadoDto();
    errorDatoEmpDto.setCampo(campo);
    errorDatoEmpDto.setDato(dato);
    boolean resp = true;
    
    String error = validaCadena(dato);
    if (error.equals("valido"))
    {
      Pattern pat = Pattern.compile("([A-Za-z0-9Ññ.,()áéíóúÁÉÍÓÚ\\-\"/°üÜ ]){1,1000}");
      Matcher mat = pat.matcher(dato);
      if (dato.length() > longitud) {
        errorDatoEmpDto.setError("El dato contiene mas de " + longitud + " caracteres");
      } else if (mat.matches()) {
        resp = false;
      } else {
        errorDatoEmpDto.setError("El dato contiene caracteres especiales");
      }
    }
    else
    {
      errorDatoEmpDto.setError(error);
    }
    return resp;
  }
  
  public static boolean zpInvalida(String zp)
  {
    errorDatoEmpDto = new ErrorDatoEmpleadoDto();
    errorDatoEmpDto.setCampo("ZONAPAGADORA");
    errorDatoEmpDto.setDato(zp);
    boolean resp = true;
    String msj = validaCadena(zp);
    if (msj.equals("valido"))
    {
      Pattern pat = Pattern.compile("([A-Za-z0-9\\-]){8}");
      Matcher mat = pat.matcher(zp);
      if (mat.matches()) {
        resp = false;
      } else {
        errorDatoEmpDto.setError("La zona pagadora contiene caracteres especiales, o bien no es igual a 8 caracteres");
      }
    }
    else
    {
      errorDatoEmpDto.setError(msj);
    }
    return resp;
  }
  
  public static boolean areaInvalida(String area,String tipoNomina)
  {
    errorDatoEmpDto = new ErrorDatoEmpleadoDto();
    errorDatoEmpDto.setCampo("AREA");
    errorDatoEmpDto.setDato(area);
    boolean resp = true;
    String msj = validaCadena(area);
    if (msj.equals("valido")){
    	
    	if(tipoNomina.equals("consejo")){
    		resp = false;
    	}else{
		      Pattern pat = Pattern.compile("([0-9]){8}");
		      Matcher mat = pat.matcher(area);
		      if (mat.matches()) {
		        resp = false;
		      } else {
		        errorDatoEmpDto.setError("El area solo puede estar formada por 8 digitos");
		      }
    	}
    }
    else
    {
      errorDatoEmpDto.setError(msj);
    }
    return resp;
  }
  
  public static boolean codigoInvalido(String codigo,String tipoNomina)
  {
    errorDatoEmpDto = new ErrorDatoEmpleadoDto();
    errorDatoEmpDto.setCampo("CODIGO");
    errorDatoEmpDto.setDato(codigo);
    boolean resp = true;
    String msj = validaCadena(codigo);
    Pattern pat = null;
    if (msj.equals("valido"))
    {
      
      if(tipoNomina.equals("consejo")){
    	  pat = Pattern.compile("([A-Za-z0-9]){1,4}");  
      }else{
    	  pat = Pattern.compile("([A-Za-z0-9]){6,7}");
      }
      Matcher mat = pat.matcher(codigo);
      if (mat.matches()) {
        resp = false;
      } else {
        errorDatoEmpDto.setError("El codigo solo debe estar formado por caracteres alfanumericos, longitud minima 6 y maxima 7");
      }
    }
    else
    {
      errorDatoEmpDto.setError(msj);
    }
    return resp;
  }
  
  public static boolean nivelInvalido(String nivel,String tipoNomina)
  {
    errorDatoEmpDto = new ErrorDatoEmpleadoDto();
    errorDatoEmpDto.setCampo("NIVEL");
    errorDatoEmpDto.setDato(nivel);
    boolean resp = true;
    String msj = validaCadena(nivel);
    Pattern pat = null;
    if (msj.equals("valido"))
    {
    	if(tipoNomina.equals("consejo")){
    		pat = Pattern.compile("([A-Za-z0-9]){2}");
    	}else{
    		pat = Pattern.compile("([A-Za-z0-9]){3}");
    	}
      
      Matcher mat = pat.matcher(nivel);
      if (mat.matches()) {
        resp = false;
      } else {
    	  if(tipoNomina.equals("consejo")){
    		  errorDatoEmpDto.setError("El nivel salarial debe estar formado por 2 caracteres alfanumericos");
    	  }else{
    		  errorDatoEmpDto.setError("El nivel salarial debe estar formado por 3 caracteres alfanumericos");
    	  }
        
      }
    }
    else
    {
      errorDatoEmpDto.setError(msj);
    }
    return resp;
  }
  
  public static boolean universoInvalido(String universo,String tipoNomina)
  {
	  if(tipoNomina.equals("consejo")){
    	  return false;
      }
    errorDatoEmpDto = new ErrorDatoEmpleadoDto();
    errorDatoEmpDto.setCampo("UNIVERSO");
    errorDatoEmpDto.setDato(universo);
    boolean resp = true;
    String msj = validaCadena(universo);
    if (msj.equals("valido"))
    {
      Pattern pat = Pattern.compile("([A-Za-z]){1}");
      Matcher mat = pat.matcher(universo);
      if (mat.matches()) {
        resp = false;
      } else {
        errorDatoEmpDto.setError("El universo debe ser un solo caracter alfabetico");
      }
    }
    else
    {
      errorDatoEmpDto.setError(msj);
    }
    return resp;
  }
  
  public static boolean plazaInvalida(String plaza,String tipoNomina)
  {
    errorDatoEmpDto = new ErrorDatoEmpleadoDto();
    errorDatoEmpDto.setCampo("NOPLAZA");
    errorDatoEmpDto.setDato(plaza);
    boolean resp = true;
    String msj = validaCadena(plaza);
    Pattern pat = null;
    if (msj.equals("valido"))
    {
    	if(tipoNomina.equals("consejo")){
    		pat = Pattern.compile("([0-9]){6}");
    	}else{
    		pat = Pattern.compile("([0-9]){5}");
    	}
      
      Matcher mat = pat.matcher(plaza);
      if (mat.matches()) {
        resp = false;
      } else {
    	  if(tipoNomina.equals("consejo")){
    		  errorDatoEmpDto.setError("El numero de plaza solo puede estar formada por 6 digitos");
    	  }else{
    		  errorDatoEmpDto.setError("El numero de plaza solo puede estar formada por 5 digitos");
    	  }
        
      }
    }
    else
    {
      errorDatoEmpDto.setError(msj);
    }
    return resp;
  }
  
  public static boolean seccSindicalInvalida(String seccSindical)
  {
    errorDatoEmpDto = new ErrorDatoEmpleadoDto();
    errorDatoEmpDto.setCampo("SECCIONSINDICAL");
    errorDatoEmpDto.setDato(seccSindical);
    boolean resp = true;
    String msj = validaCadena(seccSindical);
    if (msj.equals("valido"))
    {
      Pattern pat = Pattern.compile("([0-9]){2}");
      Matcher mat = pat.matcher(seccSindical);
      if (mat.matches()) {
        resp = false;
      } else {
        errorDatoEmpDto.setError("La seccion sindical solo puede estar formada por 2 digitos");
      }
    }
    else
    {
      errorDatoEmpDto.setError(msj);
    }
    return resp;
  }
  
  public static boolean nombreInvalido(String nombre, int longitud, String campo)
  {
    errorDatoEmpDto = new ErrorDatoEmpleadoDto();
    errorDatoEmpDto.setCampo(campo);
    errorDatoEmpDto.setDato(nombre);
    boolean resp = true;
    String error = validaCadena(nombre);
    if (error.equals("valido"))
    {
      Pattern pat = Pattern.compile("([A-Za-z0-9Ññ.áéíóúÁÉÍÓÚ'Üü ]){1,1000}");
      Matcher mat = pat.matcher(nombre);
      if (nombre.length() > longitud) {
        errorDatoEmpDto.setError("El nombre del empleado contiene mas de " + longitud + " caracteres");
      } else if (mat.matches()) {
        resp = false;
      } else {
        errorDatoEmpDto.setError("El nombre del empleado contiene caracteres especiales");
      }
    }
    else
    {
      errorDatoEmpDto.setError(error);
    }
    return resp;
  }
  
  public static boolean numeroEmpleadoInvalido(String numeroEmpleado)
  {
    errorDatoEmpDto = new ErrorDatoEmpleadoDto();
    errorDatoEmpDto.setCampo("NUMEMPLEADO");
    errorDatoEmpDto.setDato(numeroEmpleado);
    String msj = validaCadena(numeroEmpleado);
    if (msj.equals("valido"))
    {
      Pattern pat = Pattern.compile("[0-9]{7}");
      Matcher mat = pat.matcher(numeroEmpleado);
      if (mat.matches()) {
        return false;
      }
      errorDatoEmpDto.setError("El numero de empleado tiene que estar formado por 7 digitos");
      return true;
    }
    errorDatoEmpDto.setError(msj);
    return true;
  }
  
  public static boolean fechaInvalida(String fecha, String campo)
  {
    errorDatoEmpDto = new ErrorDatoEmpleadoDto();
    errorDatoEmpDto.setCampo(campo);
    errorDatoEmpDto.setDato(fecha);
    String msj = validaCadena(fecha);
    if (msj.equals("valido"))
    {
      Pattern pat = Pattern.compile("(19|20)\\d\\d-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])");
      Matcher mat = pat.matcher(fecha);
      if (mat.matches()) {
        return false;
      }
      errorDatoEmpDto.setError("La fecha no cumple con el formato YYYY-MM-DD");
      return true;
    }
    errorDatoEmpDto.setError(msj);
    return true;
  }
  
  public static boolean tImporteInvalido(String decimal, String campo)
  {
    errorDatoEmpDto = new ErrorDatoEmpleadoDto();
    errorDatoEmpDto.setCampo(campo);
    errorDatoEmpDto.setDato(decimal);
    if (decimal != null)
    {
      Pattern pat = Pattern.compile("[0-9]{1,10}+(\\.[0-9]{1,6})?");
      Matcher mat = pat.matcher(decimal);
      if (mat.matches()) {
        return false;
      }
      errorDatoEmpDto.setError("El valor no cumple con el formato T_IMPORTE del layout");
      return true;
    }
    errorDatoEmpDto.setError("El numero es nulo");
    return true;
  }
  
  public static boolean tDecimalInvalido(String decimal, String campo)
  {
    errorDatoEmpDto = new ErrorDatoEmpleadoDto();
    errorDatoEmpDto.setCampo(campo);
    errorDatoEmpDto.setDato(decimal);
    if (decimal != null)
    {
      Pattern pat = Pattern.compile("[0-9]{1,10}+(\\.[0-9]{1,6})?");
      Matcher mat = pat.matcher(decimal);
      if (mat.matches()) {
        return false;
      }
      errorDatoEmpDto.setError("El valor no cumple con el formato T_DECIMAL del layout");
      return true;
    }
    errorDatoEmpDto.setError("El numero es nulo");
    return true;
  }
  
  public static boolean tDecimal2Invalido(String decimal, String campo)
  {
    errorDatoEmpDto = new ErrorDatoEmpleadoDto();
    errorDatoEmpDto.setCampo(campo);
    errorDatoEmpDto.setDato(decimal);
    if (decimal != null)
    {
      Pattern pat = Pattern.compile("[0-9]{1,10}+(\\.[0-9]{1,2})?");
      Matcher mat = pat.matcher(decimal);
      if (mat.matches()) {
        return false;
      }
      errorDatoEmpDto.setError("El valor no cumple con el formato T_DECIMAL2 del layout");
      return true;
    }
    errorDatoEmpDto.setError("El numero es nulo");
    return true;
  }
  
  public static boolean tDecimal3Invalido(String decimal, String campo)
  {
    errorDatoEmpDto = new ErrorDatoEmpleadoDto();
    errorDatoEmpDto.setCampo(campo);
    errorDatoEmpDto.setDato(decimal);
    if (decimal != null)
    {
      Pattern pat = Pattern.compile("[0-9]{1,20}+(\\.[0-9]{1,6})?");
      Matcher mat = pat.matcher(decimal);
      if (mat.matches()) {
        return false;
      }
      errorDatoEmpDto.setError("El valor no cumple con el formato T_DECIMAL3 del layout");
      return true;
    }
    errorDatoEmpDto.setError("El numero es nulo");
    return true;
  }
  
  public static boolean claveSatInvalida(String clave, String campo)
  {
    errorDatoEmpDto = new ErrorDatoEmpleadoDto();
    errorDatoEmpDto.setCampo(campo);
    errorDatoEmpDto.setDato(clave);
    String msj = validaCadena(clave);
    if (msj.equals("valido"))
    {
      Pattern pat = Pattern.compile("[0-9]{1,3}");
      Matcher mat = pat.matcher(clave);
      if (mat.matches()) {
        return false;
      }
      errorDatoEmpDto.setError("La clave del sat debe estar formada por digitos, minimo 1, maximo 3");
      return true;
    }
    errorDatoEmpDto.setError(msj);
    return true;
  }
  
  public static boolean claveTsjdfInvalida(String clave, String campo)
  {
    errorDatoEmpDto = new ErrorDatoEmpleadoDto();
    errorDatoEmpDto.setCampo(campo);
    errorDatoEmpDto.setDato(clave);
    String msj = validaCadena(clave);
    if (msj.equals("valido"))
    {
      Pattern pat = Pattern.compile("[0-9]{1,15}");
      Matcher mat = pat.matcher(clave);
      if (mat.matches()) {
        return false;
      }
      errorDatoEmpDto.setError("La clave del tribunal debe estar formada por digitos, minimo 1, maximo 15");
      return true;
    }
    errorDatoEmpDto.setError(msj);
    return true;
  }
  
  public static boolean sumaIncorrectaPercepciones(double perTotalGravado, double perTotalExento, double totalPercepciones)
  {
    errorDatoEmpDto = new ErrorDatoEmpleadoDto();
    if (RecibosBo.Redondear(perTotalGravado + perTotalExento) == totalPercepciones) {
      return false;
    }
    errorDatoEmpDto.setCampo("TOTALGRAVADO,TOTALEXENTO,SUBTOTAL");
    errorDatoEmpDto.setDato(Double.toString(perTotalGravado) + "," + Double.toString(perTotalExento) + "," + Double.toString(totalPercepciones));
    errorDatoEmpDto.setError("TOTALGRAVADO + TOTALEXENTO no es igual a SUBTOTAL");
    return true;
  }
  
  public static boolean parteExentaParteGravadaCeros(double cantidadExenta, double cantidadGravada, String campo)
  {
    errorDatoEmpDto = new ErrorDatoEmpleadoDto();
    if ((cantidadExenta == 0.0D) && (cantidadGravada == 0.0D))
    {
      errorDatoEmpDto.setCampo("CLAVE:" + campo);
      errorDatoEmpDto.setDato(Double.toString(cantidadExenta) + "," + Double.toString(cantidadGravada));
      errorDatoEmpDto.setError("IMPORTEGRAVADO e IMPORTEEXENTO, ambas cantidades no pueden ser cero");
      return true;
    }
    return false;
  }
}

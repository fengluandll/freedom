package mx.com.televisa.derechocorporativo.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;


public class TextFormatter {

	public static String removeAccents(String strInput) {
		
		//Implementar otros caracteres
		String strOutput = "";
		if(strInput != null){
		
			strOutput = strInput.replaceAll("�", "a");
			strOutput = strOutput.replaceAll("�", "e");
			strOutput = strOutput.replaceAll("�", "i");
			strOutput = strOutput.replaceAll("�", "o");
			strOutput = strOutput.replaceAll("�", "u");
			
			strOutput = strOutput.replaceAll("�", "A");
			strOutput = strOutput.replaceAll("�", "E");
			strOutput = strOutput.replaceAll("�", "I");
			strOutput = strOutput.replaceAll("�", "O");
			strOutput = strOutput.replaceAll("�", "U");
			
			//Acentos Invertidos
			strOutput = strOutput.replaceAll("�", "a");
			strOutput = strOutput.replaceAll("�", "e");
			strOutput = strOutput.replaceAll("�", "i");
			strOutput = strOutput.replaceAll("�", "o");
			strOutput = strOutput.replaceAll("�", "u");
			
			strOutput = strOutput.replaceAll("�", "A");
			strOutput = strOutput.replaceAll("�", "E");
			strOutput = strOutput.replaceAll("�", "I");
			strOutput = strOutput.replaceAll("�", "O");
			strOutput = strOutput.replaceAll("�", "U");
		}	
		
		
		return strOutput;
	}

	/**
	 * Metodo que reemplaza acentos cuando viaja por la URL
	 * @param strInput
	 * @return
	 */
public static String replaceAccentsURL(String strInput) {
		
		//Implementar otros caracteres
		
		String strOutput = strInput.replaceAll("�", "%E1");
		strOutput = strOutput.replaceAll("�", "%E9");
		strOutput = strOutput.replaceAll("�", "%ED");
		strOutput = strOutput.replaceAll("�", "%F3");
		strOutput = strOutput.replaceAll("�", "%FA");
		
		strOutput = strOutput.replaceAll("�", "%C1");
		strOutput = strOutput.replaceAll("�", "%C9");
		strOutput = strOutput.replaceAll("�", "%CD");
		strOutput = strOutput.replaceAll("�", "%D3");
		strOutput = strOutput.replaceAll("�", "%DA");
		
		//Acentos Invertidos
		strOutput = strOutput.replaceAll("�", "%E0");
		strOutput = strOutput.replaceAll("�", "%E8");
		strOutput = strOutput.replaceAll("�", "%EC");
		strOutput = strOutput.replaceAll("�", "%F2");
		strOutput = strOutput.replaceAll("�", "%F9");
		
		strOutput = strOutput.replaceAll("�", "%C0");
		strOutput = strOutput.replaceAll("�", "%C8");
		strOutput = strOutput.replaceAll("�", "%CC");
		strOutput = strOutput.replaceAll("�", "%D2");
		strOutput = strOutput.replaceAll("�", "%D9");
		
		strOutput = strOutput.replaceAll("�", "%D1");
		strOutput = strOutput.replaceAll("�", "%F1");
		
		
		
		
		return strOutput;
	}

/**
 * Metodoq ue reemplaza acentos cuando los parametros viene de una
 * peticion Ajax o de javaScript
 * @param strInput
 * @return
 */
public static String replaceAccentsURLFromAjax(String strInput) {
	
	//Implementar otros caracteres
	
	String strOutput = strInput.replaceAll("á", "�");
	strOutput = strOutput.replaceAll("é", "�");
	strOutput = strOutput.replaceAll("í", "�");
	strOutput = strOutput.replaceAll("ó", "�");
	strOutput = strOutput.replaceAll("ú", "�");

	
	
	return strOutput;
}
	

	/**
	 * 
	 */
	public static final int PAD_DERECHO = 0;

	/**
	 * 
	 */
	public static final int PAD_IZQUIERDO = 1;


	/**
	 *
	 */
	public static String tipoTitulo(String texto) {
	
		String textoformateado = "";
		
		StringTokenizer tokens = new StringTokenizer(texto);


		
		while(tokens.hasMoreElements()) {

			String palabra = tokens.nextToken();

			if(palabra.length() > 3) {
				textoformateado += palabra.toUpperCase().charAt(0) + 
						   palabra.substring(1).toLowerCase();
			} else {
				textoformateado += palabra.toLowerCase();
			}
			
			if(tokens.hasMoreElements()) {
				textoformateado += " ";
			}

		}

		return textoformateado;
	}

	/**
	 *
	 */
	public static String iniciales(String texto) {
		
		String iniciales = "";		
		StringTokenizer tokens = new StringTokenizer(texto);

		while(tokens.hasMoreElements()) {

			iniciales += tokens.nextToken().charAt(0);
		}
		
		return iniciales;
	}
	
	/**
	 * Complementa una cadena <code>String</code> usando el caracter <code>complementario</code>,
	 * hasta que la se complete una cadena con la <code>longitudDeseada</code>.
	 *
	 * El complemento se realiza por el lado izquierdo o derecho segun el parametro 
	 * <code>posicion</code>, al que se le pueden mandar los valores 
	 * constantes de FormateadorDeTexto.PAD_DERECHO y
	 * FormateadorDeTexto.PAD_IZQUIERDO.
	 *
	 * @return La cadena completa con la longitud deseada.
	 * @param cadenaOriginal 
	 * @param complementario 
	 * @param longitudDeseada
	 * @param posicion
	 */
	public static String pad(String cadenaOriginal, char complementario, int longitudDeseada, int posicion) {
	
		String complemento = new String();

		int longitudFaltante = Math.abs(longitudDeseada) - cadenaOriginal.length();

		if (longitudFaltante <= 0) {
			return cadenaOriginal;
		}
	
		for (int i = 0 ; i < longitudFaltante ; ++i) {
			complemento += complementario;
		}

		return (posicion == PAD_DERECHO ? cadenaOriginal + complemento : complemento + cadenaOriginal);
	}

	public static String padOrCrunch(String cadenaOriginal, 
							char complementario, int longitudDeseada, int posicion) {
		
		if(cadenaOriginal.length() > longitudDeseada) {
			return cadenaOriginal.substring(0, longitudDeseada); 
		}
		
		return  pad(cadenaOriginal, complementario, longitudDeseada, posicion);
	}

	public static String getNewLine()  {
		
		return System.getProperty("line.separator");		
	}

	public static String getNewLine(int n)  {
		
		String lines = "";
		
		for(int i = 1; i <= n; i++) {
			
			lines += getNewLine();
		}
		
		return lines;
	}

	public static String ToFormatHtml(String tCadenaReemplazar){
		//tCadenaReemplazar = tCadenaReemplazar.replace("\"", "%22");//Comillas dobles(")
		if(tCadenaReemplazar != null){
			if(tCadenaReemplazar.contains("&#39;")){
				tCadenaReemplazar = tCadenaReemplazar.replace("&#39;", "'");//
			}
			tCadenaReemplazar = tCadenaReemplazar.replace("&", "&amp;");//1� Ampersand para no sustituir 
			tCadenaReemplazar = tCadenaReemplazar.replace("\"", "&quot;");//Comillas dobles(")
			tCadenaReemplazar = tCadenaReemplazar.replace("\'", "&apos;");//Comilla simple(')
			tCadenaReemplazar = tCadenaReemplazar.replace("<", "&lt;");//Menor que
			tCadenaReemplazar = tCadenaReemplazar.replace(">", "&gt;");//Mayor que
			
		}else{
			tCadenaReemplazar = "";
		}
		
		
		return tCadenaReemplazar.trim();
	}
	
	//Devuele un java.util.Date desde un String en formato dd-MM-yyyy
    //@param La fecha a convertir a formato date
    //@return Retorna la fecha en formato Date
    public static synchronized java.util.Date deStringToDate(String fecha) {
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date fechaEnviar = null;
        try {
         
            fechaEnviar = formatoDelTexto.parse(fecha);
            return fechaEnviar;
        } catch (ParseException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public static synchronized java.sql.Date deStringToDate2(String fecha) {
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
        java.sql.Date fechaEnviar = null;
        try {
         
        	java.util.Date date = formatoDelTexto.parse(fecha);
        	fechaEnviar = new java.sql.Date(date.getTime());
            return fechaEnviar;
        } catch (ParseException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    /**
     * Metodoq ue convierte una java.util.Date a java.sql.Date
     * @param fecha de la libreria java.util
     * @return valor en java.sql.Date
     */
    public static java.sql.Date utilDateToslDate(java.util.Date fecha){
     java.sql.Date fecSql = new  java.sql.Date(fecha.getTime());
     return fecSql;
    }
    
    /**
     * Fecha en formato dd/MM/YYYY
     * String regrea el String en formato MM/dd/YYYY
     * @param fecha
     */
    public static String dayToMonthString(String fecha){
    	String[] part = fecha.split("/");
    	String newFecha = "";
    	//for(int i = 0;i <part.length; i++){
    		
    	//}
    	System.out.println(part[0]);
		System.out.println(part[1]);
		System.out.println(part[2]);
		newFecha = part[1]+"/"+part[0]+"/"+part[2];
		return newFecha;
    }
    
  /*  public static void main(String[] args) {
		String fec = new TextFormatter().dayToMonthString("01/04/2006");
		System.out.println(fec);

	}*/

}
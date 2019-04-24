package com.movemini.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

import org.eclipse.jdt.internal.compiler.ast.ArrayAllocationExpression;

public class StringUtils {
	
	public static String NEW_LINE = System.getProperty("line.separator");

	

	
public static ArrayList<String> split(String str, String delim) {
		
		ArrayList<String>  ret = new ArrayList<String>();
		
		try {
			
			StringTokenizer tok = new StringTokenizer(str, delim);
			
			while (tok.hasMoreTokens()) {
				ret.add(tok.nextToken());
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return ret;
	}


	public static String toUpper(String str) {
		
		try {
			
			return str.toUpperCase();
			
		} catch (Exception e) {
			
			return "";
		}
		
	}
	

	public static boolean contains(String str1, String str2) {
		
		try {
			
			return str1.contains(str2);
			
		} catch (Exception e) {
			
			return false;
		}
		
	}
	

	public static boolean in(String testString, String ... options) {
		
		try {
			ArrayList<String> list = new ArrayList<String>(Arrays.asList(options));
			
			return list.contains(testString);
			
		} catch (Exception e) {
			
			return false;
		}
		
	}
	
	public static String latin1ToUTF8(String stringISO) throws Exception {
	
		
		return new String(stringISO.getBytes("ISO-8859-1"),"UTF-8");
	}
	
	

	public static String utf8ToLatin1(String stringISO) throws Exception {
	
		
		return new String(stringISO.getBytes("UTF-8"),"ISO-8859-1");
	}
	

	public static String removeAccents(String strInput) {
		
		//Implementar otros caracteres
		
		String strOutput = strInput.replaceAll("�", "a");
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
	
	
	/*
	 * :)
	 *  DE FACIL IMPLEMENTACI�N
	 * */
	public static String padRight(String cadenaOriginal, String complementario, int longitudDeseada) {
		
		return pad(cadenaOriginal, complementario.toCharArray()[0], longitudDeseada, PAD_DERECHO);
	}
	
	/*
	 * :)
	 *  DE FACIL IMPLEMENTACI�N
	 * */
	public static String padLeft(String cadenaOriginal, String complementario, int longitudDeseada) {
		
		return pad(cadenaOriginal, complementario.toCharArray()[0], longitudDeseada, PAD_IZQUIERDO);
	}
	
	
	/*
	 * :)
	 *  DE FACIL IMPLEMENTACI�N
	 * */
	public static String padRight(int cadenaOriginal, String complementario, int longitudDeseada) {
		
		return pad(Integer.toString(cadenaOriginal), complementario.toCharArray()[0], longitudDeseada, PAD_DERECHO);
	}
	
	/*
	 * :)
	 *  DE FACIL IMPLEMENTACI�N
	 * */
	public static String padLeft(int cadenaOriginal, String complementario, int longitudDeseada) {
		
		return pad(Integer.toString(cadenaOriginal), complementario.toCharArray()[0], longitudDeseada, PAD_IZQUIERDO);
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
	
	
	
	
	
	public static String removeLastChar(String string1) {
		
		
		return (string1 != null && !string1.equals(""))? string1.substring(0, string1.length()-1) : "";

	}
	
	
}

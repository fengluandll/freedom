package mx.com.televisa.derechocorporativo.util;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.StringTokenizer;

public class NumberFormatter {

	/**
	 *
	 */
	public static String ordinal(int numero) {
	
		String numeroTextual = new Integer(numero).toString();

		char terminacion = numeroTextual.charAt(numeroTextual.length() - 1); 

	
		if(terminacion == '1' || terminacion == '3') {
			return numeroTextual + "ra.";
		} else if(terminacion == '2'){
			return numeroTextual + "da.";
		} else if(terminacion == '4' || terminacion == '5' || terminacion == '6'){
			return numeroTextual + "ta.";
		} else if(terminacion == '7' || terminacion == '0'){
			return numeroTextual + "ma.";
		} else if(terminacion == '8'){
			return numeroTextual + "va.";
		} else if(terminacion == '9'){
			return numeroTextual + "na.";
		} else {
			return numeroTextual;
		}
	}

	/**
	 *
	 */
	public static String ordinalMasculino(int numero) {
	
		String numeroTextual = new Integer(numero).toString();

		char terminacion = numeroTextual.charAt(numeroTextual.length() - 1); 

	
		if(terminacion == '1' || terminacion == '3') {
			return numeroTextual + "r.";
		} else if(terminacion == '2'){
			return numeroTextual + "do.";
		} else if(terminacion == '4' || terminacion == '5' || terminacion == '6'){
			return numeroTextual + "to.";
		} else if(terminacion == '7' || terminacion == '0'){
			return numeroTextual + "mo.";
		} else if(terminacion == '8'){
			return numeroTextual + "vo.";
		} else if(terminacion == '9'){
			return numeroTextual + "no.";
		} else {
			return numeroTextual;
		}
	}
	

	
	
	public static String moneda(double numero) {
		
		return "$" + conComas(numero);				
	}

	

	public static String conComas(String numero) {
		

		try {
		
			return conComas(Double.parseDouble(numero),2);	
		
		} catch(Exception ex) {
			
			return "0";
		}
	}
	
	public static String conComas(double numero) {
		
		return conComas(numero, 2);				
	}
	

	public static String conComas(String numero, int decimalPlaces) {
		
		try {
			
			return conComas(Double.parseDouble(numero),decimalPlaces);	
		
		} catch(Exception ex) {
			
			return "0";
		}
	}

	/**
	 * 
	 */
	public static String conComas(double numero, int decimalPlaces) {

		try {
				
			NumberFormat formateadorNumerico = NumberFormat.getInstance(Locale.US);
			String numeroFormateado = formateadorNumerico.format(numero);
			
			String PUNTO_DECIMAL = ".";
			StringTokenizer tok = new StringTokenizer(numeroFormateado, PUNTO_DECIMAL);			
			String entero = tok.nextToken();
			String decimal; 
			
			if(decimalPlaces == 0) {
				
				return entero;
				
			} else {
				if(tok.hasMoreElements()) {
					decimal = TextFormatter.padOrCrunch(tok.nextToken(), '0', decimalPlaces, 
							TextFormatter.PAD_DERECHO);
				} else {
					decimal = TextFormatter.padOrCrunch("0", '0', decimalPlaces, 
							TextFormatter.PAD_DERECHO);
				}			
		
				return entero + "." + decimal;
			}
		
		} catch(Exception ex) {
			
			return "0";
		}
	}
	

	
	
	
	/**
	 * 
	 */
	public static String conComasHTML(double numero, boolean usarDecimalesEnSuperindice) {
		
		String numeroFormateado = conComas(numero);
		
		
		if(usarDecimalesEnSuperindice) {
		
			String PUNTO_DECIMAL = ".";
			StringTokenizer tok = new StringTokenizer(numeroFormateado, PUNTO_DECIMAL);			
			String entero = tok.nextToken();
			String decimal; 
			
			if(tok.hasMoreElements()) {
				decimal = tok.nextToken();
			} else {
				decimal = "0";
			}			
			
			return entero + ".<sup>" + decimal + "</sup>"; 		
			
		} else {
			return numeroFormateado; 	
		}	
	}
	
	/**
	 * 
	 */
	public static String conLetra(double numero) {

		if(numero > 9999999.99) {
			return "";
		} else {
	
			String numeroString = conComas(numero).replace(",", "");
			
			String PUNTO_DECIMAL = ".";
			
			java.util.StringTokenizer porPartes = 
				new java.util.StringTokenizer(
						numeroString, 	PUNTO_DECIMAL);

			String entero = porPartes.nextToken();
			String decimal = TextFormatter.pad(porPartes.nextToken(), '0', 2,
					TextFormatter.PAD_IZQUIERDO); 
			
			//String moneda = "U.S.D.";
			String moneda = "M.N.";
			
			return enteroConLetra(new Integer(entero).intValue()) + " " + decimal + "/100 " + moneda;
		}	
	}

	/**
	 * 
	 */
	private static String enteroConLetra(int entero) {

		String conLetra = "";

		int millones 	   = ((entero - (entero % 1000000)) / 1000000);
		int centenasMillar = ((entero - (entero % 100000)) / 100000); 
		int decenasMillar  = ((entero - (entero % 10000)) / 10000); 
		int unidadesMillar = ((entero - (entero % 1000)) / 1000); 
		int centenas 	   = ((entero - (entero % 100)) / 100);
		int decenas        = ((entero - (entero % 10)) / 10);
		int unidades       = ((entero - (entero % 1)) / 1);
		
		unidades 	= unidades - (decenas * 10); 
		decenas 	= decenas - (centenas * 10); 
		centenas 	= centenas - (unidadesMillar * 10); 
		unidadesMillar 	= unidadesMillar - (decenasMillar * 10); 
		decenasMillar 	= decenasMillar - (centenasMillar * 10); 
		centenasMillar 	= centenasMillar - (millones * 10); 
		//millones 	= millones; // (sentencia burda) solo para terminar la secuencia o, en su caso, continuarla


		switch(millones) {

			case 0: conLetra += ""; break;
			case 1: conLetra += "UN MILLON "; break;
			case 2: conLetra += "DOS MILLONES "; break;
			case 3: conLetra += "TRES MILLONES "; break;
			case 4: conLetra += "CUATRO MILLONES "; break;
			case 5: conLetra += "CINCO MILLONES "; break;
			case 6: conLetra += "SEIS MILLONES "; break;
			case 7: conLetra += "SIETE MILLONES "; break;
			case 8: conLetra += "OCHO MILLONES "; break;
			case 9: conLetra += "NUEVE MILLONES "; break;

			/* programables tambien con una nueva variable "decenas de millon" con el mismo metodo */
			
			/*
				En esta implementacion no aplican
				debido a la funcionalidad del metodo 
				Double.toString(), ya que mas de 1 x 10 E 7 , 
				no se formatean de manera correcta
			*/
			case 10: conLetra += "DIEZ MILLONES "; break;
			case 11: conLetra += "ONCE MILLONES "; break;
			case 12: conLetra += "DOCE MILLONES "; break;
			case 13: conLetra += "TRECE MILLONES "; break;
			case 14: conLetra += "CATORCE MILLONES "; break;
			case 15: conLetra += "QUINCE MILLONES "; break;
			case 16: conLetra += "DIECISEIS MILLONES "; break;
			case 17: conLetra += "DIECISIETE MILLONES "; break;
			case 18: conLetra += "DIECIOCHO MILLONES "; break;
			case 19: conLetra += "DIECINUEVE MILLONES "; break;
			case 20: conLetra += "VEINTE MILLONES "; break;
			case 21: conLetra += "VEINTIUN MILLONES "; break;
			case 22: conLetra += "VEINTIDOS MILLONES "; break;
			case 23: conLetra += "VEINTITRES MILLONES "; break;
			case 24: conLetra += "VEINTICUATRO MILLONES "; break;
			case 25: conLetra += "VEINTICINCO MILLONES "; break;
			case 26: conLetra += "VEINTISEIS MILLONES "; break;
			case 27: conLetra += "VEINTISIETE MILLONES "; break;
			case 28: conLetra += "VEINTIOCHO MILLONES "; break;
			case 29: conLetra += "VEINTINUEVE MILLONES "; break;
			case 30: conLetra += "TREINTA MILLONES "; break;			
			case 31: conLetra += "TREINTA Y UN MILLONES "; break;
			case 32: conLetra += "TREINTA Y DOS MILLONES "; break;
			case 33: conLetra += "TREINTA Y TRES MILLONES "; break;
			case 34: conLetra += "TREINTA Y CUATRO MILLONES "; break;
			case 35: conLetra += "TREINTA Y CINCO MILLONES "; break;
			case 36: conLetra += "TREINTA Y SEIS MILLONES "; break;
			case 37: conLetra += "TREINTA Y SIETE MILLONES "; break;			
			case 38: conLetra += "TREINTA Y OCHO MILLONES "; break;			
			case 39: conLetra += "TREINTA Y NUEVE MILLONES "; break;
			case 40: conLetra += "CUARENTA MILLONES "; break;
			case 41: conLetra += "CUARENTA Y UN MILLONES "; break;
			case 42: conLetra += "CUARENTA Y DOS MILLONES "; break;
			case 43: conLetra += "CUARENTA Y TRES MILLONES "; break;
			case 44: conLetra += "CUARENTA Y CUATRO MILLONES "; break;
			case 45: conLetra += "CUARENTA Y CINCO MILLONES "; break;
			case 46: conLetra += "CUARENTA Y SEIS MILLONES "; break;
			case 47: conLetra += "CUARENTA Y SIETE MILLONES "; break;
			case 48: conLetra += "CUARENTA Y OCHO MILLONES "; break;
			case 49: conLetra += "CUARENTA Y NUEVE MILLONES "; break;
			case 50: conLetra += "CINCUENTA MILLONES "; break;
		}
				
		if(decenasMillar == 0 && unidadesMillar == 0) {
		
			switch(centenasMillar) {

				case 0: conLetra += ""; break;
				case 1: conLetra += "CIEN MIL "; 	break;
				case 2: conLetra += "DOSCIENTOS MIL "; break;
				case 3: conLetra += "TRESCIENTOS MIL "; break;
				case 4: conLetra += "CUATROCIENTOS MIL "; break;
				case 5: conLetra += "QUINIENTOS MIL "; break;
				case 6: conLetra += "SEISCIENTOS MIL "; break;
				case 7: conLetra += "SETECIENTOS MIL "; break;
				case 8: conLetra += "OCHOCIENTOS MIL "; break;
				case 9: conLetra += "NOVECIENTOS MIL "; break;							
			}
				
		} else {
		
			switch(centenasMillar) {

				case 0: conLetra += ""; break;
				case 1: conLetra += "CIENTO "; 	break;
				case 2: conLetra += "DOSCIENTOS "; break;
				case 3: conLetra += "TRESCIENTOS "; break;
				case 4: conLetra += "CUATROCIENTOS "; break;
				case 5: conLetra += "QUINIENTOS "; break;
				case 6: conLetra += "SEISCIENTOS "; break;
				case 7: conLetra += "SETECIENTOS "; break;
				case 8: conLetra += "OCHOCIENTOS "; break;
				case 9: conLetra += "NOVECIENTOS "; break;							
			}	
		}

		if(decenasMillar == 1 && unidadesMillar == 1) {

			conLetra += "ONCE MIL "; 
		} else if(decenasMillar == 1 && unidadesMillar == 2) {

			conLetra += "DOCE MIL ";
		} else if(decenasMillar == 1 && unidadesMillar == 3) {

			conLetra += "TRECE MIL "; 
		} else if(decenasMillar == 1 && unidadesMillar == 4) {

			conLetra += "CATORCE MIL "; 
		} else if(decenasMillar == 1 && unidadesMillar == 5) {

			conLetra += "QUINCE MIL "; 
		} else {

			if(unidadesMillar == 0) {
		
				switch(decenasMillar) {

					case 0: conLetra += ""; break;
					case 1: conLetra += "DIEZ MIL "; break;
					case 2: conLetra += "VEINTE MIL "; break;
					case 3: conLetra += "TREINTA MIL "; break;
					case 4: conLetra += "CUARENTA MIL "; break;
					case 5: conLetra += "CINCUENTA MIL "; break;
					case 6: conLetra += "SESENTA MIL "; break;
					case 7: conLetra += "SETENTA MIL "; break;
					case 8: conLetra += "OCHENTA MIL "; break;
					case 9: conLetra += "NOVENTA MIL "; break;						
				}
			} else {

				switch(decenasMillar) {

					case 0: conLetra += ""; break;
					case 1: conLetra += "DIECI"; break;
					case 2: conLetra += "VEINTI"; break;
					case 3: conLetra += "TREINTA Y "; break;
					case 4: conLetra += "CUARENTA Y "; break;
					case 5: conLetra += "CINCUENTA Y "; break;
					case 6: conLetra += "SESENTA Y "; break;
					case 7: conLetra += "SETENTA Y "; break;
					case 8: conLetra += "OCHENTA Y "; break;
					case 9: conLetra += "NOVENTA Y "; break;			
			
				}

				switch(unidadesMillar) {

					case 1:
							if(decenasMillar == 0 && centenasMillar == 0){

								conLetra += "MIL ";
							} else {
			
								conLetra += "UN MIL ";						
							}
					  break;
					case 2: conLetra += "DOS MIL "; break;
					case 3: conLetra += "TRES MIL "; break;
					case 4: conLetra += "CUATRO MIL "; break;
					case 5: conLetra += "CINCO MIL "; break;
					case 6: conLetra += "SEIS  MIL "; break;
					case 7: conLetra += "SIETE MIL "; break;
					case 8: conLetra += "OCHO  MIL "; break;
					case 9: conLetra += "NUEVE  MIL "; break;			
			
				}

			}

		}



		switch(centenas) {

			case 0: conLetra += ""; break;
			case 1: 
					if(decenas == 0 && unidades == 0) {
					
						conLetra += "CIEN ";
					} else {
					
						conLetra += "CIENTO ";
					}
			 	break;
			case 2: conLetra += "DOSCIENTOS "; break;
			case 3: conLetra += "TRESCIENTOS "; break;
			case 4: conLetra += "CUATROCIENTOS "; break;
			case 5: conLetra += "QUINIENTOS "; break;
			case 6: conLetra += "SEISCIENTOS "; break;
			case 7: conLetra += "SETECIENTOS "; break;
			case 8: conLetra += "OCHOCIENTOS "; break;
			case 9: conLetra += "NOVECIENTOS "; break;			
			
		}


		if(decenas == 1 && unidades == 1) {

			conLetra += "ONCE "; 
		} else if(decenas == 1 && unidades == 2) {

			conLetra += "DOCE ";
		} else if(decenas == 1 && unidades == 3) {

			conLetra += "TRECE "; 
		} else if(decenas == 1 && unidades == 4) {

			conLetra += "CATORCE "; 
		} else if(decenas == 1 && unidades == 5) {

			conLetra += "QUINCE "; 
		} else {


			if(unidades == 0) {
		
				switch(decenas) {

					case 0: conLetra += ""; break;
					case 1: conLetra += "DIEZ "; break;
					case 2: conLetra += "VEINTE "; break;
					case 3: conLetra += "TREINTA "; break;
					case 4: conLetra += "CUARENTA "; break;
					case 5: conLetra += "CINCUENTA "; break;
					case 6: conLetra += "SESENTA "; break;
					case 7: conLetra += "SETENTA "; break;
					case 8: conLetra += "OCHENTA "; break;
					case 9: conLetra += "NOVENTA "; break;			
			
				}
	
			} else {

				switch(decenas) {

					case 0: conLetra += ""; break;
					case 1: conLetra += "DIECI"; break;
					case 2: conLetra += "VEINTI"; break;
					case 3: conLetra += "TREINTA Y "; break;
					case 4: conLetra += "CUARENTA Y "; break;
					case 5: conLetra += "CINCUENTA Y "; break;
					case 6: conLetra += "SESENTA Y "; break;
					case 7: conLetra += "SETENTA Y "; break;
					case 8: conLetra += "OCHENTA Y "; break;
					case 9: conLetra += "NOVENTA Y "; break;			
			
				}

				switch(unidades) {

					case 1: conLetra += "UN "; break;
					case 2: conLetra += "DOS "; break;
					case 3: conLetra += "TRES "; break;
					case 4: conLetra += "CUATRO "; break;
					case 5: conLetra += "CINCO "; break;
					case 6: conLetra += "SEIS "; break;
					case 7: conLetra += "SIETE "; break;
					case 8: conLetra += "OCHO "; break;
					case 9: conLetra += "NUEVE "; break;			
			
				}

				
			}
		
		}

/*
		
		if(millones != 0 && ( centenasMillar == 0 &&
								decenasMillar == 0 &&
								unidadesMillar == 0 &&
								centenas == 0 &&
								decenas == 0 &&
								unidades == 0)) {
								
			conLetra += "DE PESOS";
		} else {
			conLetra += "PESOS";
		} 
*/

		return conLetra;
	}



}

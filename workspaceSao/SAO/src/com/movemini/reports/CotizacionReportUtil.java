package com.movemini.reports;

import com.movemini.config.HardCodeConstants;
import com.movemini.data.Record;
import com.movemini.util.NumberFormatter;

public class CotizacionReportUtil {


	public static double getDoubleValue(String fieldNameBase, Record linea, String id_tipo_cotizacion, double dbl_tipo_cambio,boolean enIngles) {
		//System.out.println("mejoras" +  fieldNameBase);
		try {

			String sufix = "_mxn";

			if(HardCodeConstants.ID_OMNILINGUA_USA.equals(id_tipo_cotizacion) || enIngles) {

				sufix = "_usd";
			}

			String value = linea.getString(fieldNameBase + sufix);


			if(HardCodeConstants.ID_A_FUTURO.equals(id_tipo_cotizacion)) {

				value = Double.toString(Double.parseDouble(value) / dbl_tipo_cambio);
			}

			return Double.parseDouble(value);

		} catch(Exception ex) {

			return 0;

		}

	}
	
	public static double getDoubleValue3(String fieldNameBase, Record linea, String id_tipo_cotizacion, double dbl_tipo_cambio,boolean enIngles) {
		//System.out.println("mejoras" +  fieldNameBase);
		try {

			String sufix = "";

			if(HardCodeConstants.ID_OMNILINGUA_USA.equals(id_tipo_cotizacion) || enIngles) {

				sufix = "_usd";
			}

			String value = linea.getString(fieldNameBase + sufix);


			if(HardCodeConstants.ID_A_FUTURO.equals(id_tipo_cotizacion)) {

				value = Double.toString(Double.parseDouble(value) / dbl_tipo_cambio);
			}

			return Double.parseDouble(value);

		} catch(Exception ex) {

			return 0;

		}

	}


	public static double getDoubleValue2(String fieldNameBase, Record linea, String id_tipo_cotizacion, double dbl_tipo_cambio,boolean enIngles) {
		//ystem.out.println("mejora" + fieldNameBase);
		try {


			String value = linea.getString(fieldNameBase );


			if(HardCodeConstants.ID_OMNILINGUA_USA.equals(id_tipo_cotizacion) || enIngles ) {

				value = Double.toString(Double.parseDouble(value) / dbl_tipo_cambio);
			}

			return Double.parseDouble(value);

		} catch(Exception ex) {

			return 0;

		}

	}

	
	public static double getDoubleValue(String value) {

		try {

			return Double.parseDouble(value);

		} catch(Exception ex) {

			return 0;

		}

	}

public static String getValue2(String fieldNameBase, Record linea, String id_tipo_cotizacion, double dbl_tipo_cambio, boolean english) {

		try {

			String sufix = "_mxn";
			String moneda = " MXN";

			if(HardCodeConstants.ID_OMNILINGUA_USA.equals(id_tipo_cotizacion) || english) {

				sufix = "_usd";
				moneda =  " USD";
			}

			String value = linea.getString(fieldNameBase + sufix);


			if(HardCodeConstants.ID_A_FUTURO.equals(id_tipo_cotizacion)) {
				value = linea.getString(fieldNameBase+ "_mxn");
				value = Double.toString(Double.parseDouble(value)/dbl_tipo_cambio);
				moneda =  " USD";
			}

			if(value != null && !"".equals(value.trim()) && !"0.00".equals(value) && !"0.0".equals(value)) {

				return  "$ "  + NumberFormatter.conComas(value) + moneda;
			}else{
				return "";
			}


		} catch(Exception ex) {

			return "";

		}

	}

	public static String formatoConMoneda(Object valor , String id_tipo_cotizacion) 		{
		String moneda = " MXN";
		if(HardCodeConstants.ID_OMNILINGUA_USA.equals(id_tipo_cotizacion) ) {
			moneda =" USD";


		}

		if(valor != null && !valor.toString().trim().equals("")) {
			return "$ " + NumberFormatter.conComas(valor.toString()) + moneda;
		}else {
			return "";
		}

	}

	public static String formatoConMoneda2(Object valor , String id_tipo_cotizacion, boolean ingles) 		{
		String moneda = " MXN";
		if(HardCodeConstants.ID_OMNILINGUA_USA.equals(id_tipo_cotizacion) || ingles ||HardCodeConstants.ID_A_FUTURO.equals(id_tipo_cotizacion)) {
			moneda =" USD";


		}

		if(valor != null && !valor.toString().trim().equals("")) {
			return "$ " + NumberFormatter.conComas(valor.toString()) + moneda;
		}else {
			return "";
		}

	}
	
	


	public static String formatoConMoneda2ConConversion(String valor , String id_tipo_cotizacion,double dbl_tipo_cambio, boolean ingles) 		{
		String moneda = " MXN";
		String value = valor;
		if(HardCodeConstants.ID_OMNILINGUA_USA.equals(id_tipo_cotizacion) || ingles ) {

			value = Double.toString(Double.parseDouble(valor) / dbl_tipo_cambio);
			moneda =" USD";
		}
		

		if(valor != null && !valor.toString().trim().equals("")) {
			return "$ " + NumberFormatter.conComas(value) + moneda;
		}else {
			return "";
		}

	}

	public static String getValue(String fieldNameBase, Record linea, String id_tipo_cotizacion, double dbl_tipo_cambio) {

		try {

			String sufix = "_mxn";

			if(HardCodeConstants.ID_OMNILINGUA_USA.equals(id_tipo_cotizacion)) {

				sufix = "_usd";
			}

			String value = linea.getString(fieldNameBase + sufix);


			if(HardCodeConstants.ID_A_FUTURO.equals(id_tipo_cotizacion)) {

				value = Double.toString(Double.parseDouble(value) / dbl_tipo_cambio);
			}

			return NumberFormatter.conComas(value);

		} catch(Exception ex) {

			return "";

		}

	}
}

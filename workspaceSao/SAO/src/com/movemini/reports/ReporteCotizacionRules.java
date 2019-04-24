package com.movemini.reports;

public class ReporteCotizacionRules {

	public static boolean mostrarPrecioCliente(String id_mostrar_precios) {
		
		return id_mostrar_precios.equals("1") || id_mostrar_precios.equals("2"); 
	}
	
	
	public static boolean mostrarPrecioEspecial(String id_mostrar_precios) {
		
		return id_mostrar_precios.equals("1") || id_mostrar_precios.equals("3"); 
	}
	
	

	public static boolean isCortesiaCliente(String cortesia) {
		
		return cortesia.equals("1") || cortesia.equals("2"); 
	}
	
	
	public static boolean isCortesiaEspecial(String cortesia) {
		
		return cortesia.equals("1") || cortesia.equals("3"); 
	}
}

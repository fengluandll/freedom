package mx.com.televisa.derechocorporativo.util;

public class GetMonth {
	/**
     * Metodo que convierte el mes con numero a mes en palabra
     * @param numero de mes por ejemplo 01 02 03 etc
     * @return regresa el mes en palabra
     */
    public static String numeroaMes(String numero){

	   String mes = "";

	   if(numero.equals("01")){
	    mes  = "Enero";
	   }else if(numero.equals("02")){
	    mes  = "Febrero";
	   }else if(numero.equals("03")){
	    mes  = "Marzo";
	   }else if(numero.equals("04")){
	    mes  = "Abril";
	   }else if(numero.equals("05")){
	   mes  = "Mayo";
	   }else if(numero.equals("06")){
	    mes  = "Junio";
	   }else if(numero.equals("07")){
	    mes  = "Julio";
	   }else if(numero.equals("08")){
	    mes  = "Agosto";
	   }else if(numero.equals("09")){
	    mes  = "Septiembre";
	   }else if(numero.equals("10")){
	    mes  = "Octubre";
	   }else if(numero.equals("11")){
	    mes  = "Noviembre";
	   }else if(numero.equals("12")){
	    mes  = "Diciembre";
	   }

	   return mes;
    }
}
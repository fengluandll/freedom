package com.movemini.util;

import java.text.DateFormatSymbols;
import java.time.DayOfWeek;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeMap;


public class DateUtils {
	
	

	public static String meses[] = {
		"Enero", 
		"Febrero", 
		"Marzo", 
		"Abril", 
		"Mayo", 
		"Junio", 
		"Julio", 
		"Agosto", 
		"Septiembre", 
		"Octubre", 
		"Noviembre", 
		"Diciembre"
	};
	
	

	public static String months[] = {
		"January", 
		"February", 
		"March", 
		"April", 
		"May", 
		"June", 
		"July", 
		"August", 
		"September", 
		"October", 
		"November", 
		"December"
	};


	public static String diasSemana[] = {
		"Lunes", 
		"Martes", 
		"Miï¿½rcoles", 
		"Jueves", 
		"Viernes", 
		"Sï¿½bado",
		"Domingo"
	};

	
	  public static String getToday() {
          
              
          GregorianCalendar cal = new GregorianCalendar();
          cal.setTime(new Date());
          
          int dia = cal.get(cal.DAY_OF_MONTH);
          int mes = cal.get(cal.MONTH);
          int anio = cal.get(cal.YEAR);

          return  dia + "-" + (mes+1) + "-de-" + anio;
      
      }
	  
	  public static String getFechaLayoutBancomer() {
          
          GregorianCalendar cal = new GregorianCalendar();
          cal.setTime(new Date());
          
          int dia = cal.get(cal.DAY_OF_MONTH);
          int mes = cal.get(cal.MONTH);
          int anio = cal.get(cal.YEAR);

          return StringUtils.padLeft(dia, "0", 2) + "-" + StringUtils.padLeft((mes+1), "0", 2) + "-" + anio;
      
      }
	  
	  
	  public static String getTodayHumanReadable(String campo) {
          
          
		  StringTokenizer dateMySQLFormatTok = new StringTokenizer(campo, "-"); 
		  
          
          //GregorianCalendar cal = new GregorianCalendar();
          //cal.setTime(new Date());
          
          int anio = Integer.parseInt(dateMySQLFormatTok.nextToken());
          int mes =  Integer.parseInt(dateMySQLFormatTok.nextToken()) -1 ;
          int dia =  Integer.parseInt(dateMySQLFormatTok.nextToken());
          


          return dia + " de " + meses[mes].toUpperCase() + " de " + anio;
      
      }
	  
	  
	  
	  
	  
	  public static String getTodayHumanReadableEng(String campo) {
          
		  StringTokenizer dateMySQLFormatTok = new StringTokenizer(campo, "-"); 
		  
          
          //GregorianCalendar cal = new GregorianCalendar();
          //cal.setTime(new Date());
          
          int anio = Integer.parseInt(dateMySQLFormatTok.nextToken());
          int mes =  Integer.parseInt(dateMySQLFormatTok.nextToken()) -1 ;
          int dia =  Integer.parseInt(dateMySQLFormatTok.nextToken());
          


          return " " + months[mes].toUpperCase() + " " + dia + "," + anio;
      
      }
	  
	  
	  
  public static String getTodayHumanReadable() {
          
          
          GregorianCalendar cal = new GregorianCalendar();
          cal.setTime(new Date());
          
          int dia = cal.get(cal.DAY_OF_MONTH);
          int mes = cal.get(cal.MONTH);
          int anio = cal.get(cal.YEAR);

          return dia + " de " + meses[mes].toUpperCase() + " de " + anio;
      
      }
	  
	  
	  
	  
	  
	  public static String getTodayHumanReadableEng() {
          
          
          GregorianCalendar cal = new GregorianCalendar();
          cal.setTime(new Date());
          
          int dia = cal.get(cal.DAY_OF_MONTH);
          int mes = cal.get(cal.MONTH);
          int anio = cal.get(cal.YEAR);

          return dia + " " + months[mes].toUpperCase() + " " + dia + "," + anio;
      
      }
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  public static String getDateHumanReadable(String dateMySQLFormat) {
          
		  StringTokenizer dateMySQLFormatTok = new StringTokenizer(dateMySQLFormat, "-"); 
		  
          
          //GregorianCalendar cal = new GregorianCalendar();
          //cal.setTime(new Date());
          
          int anio = Integer.parseInt(dateMySQLFormatTok.nextToken());
          int mes =  Integer.parseInt(dateMySQLFormatTok.nextToken());
          int dia =  Integer.parseInt(dateMySQLFormatTok.nextToken());
          

          return dia + " de " + meses[mes-1].toUpperCase() + " de " + anio;
      
      }
	  
	  
	  
	  public static String getMySQLFormat(Calendar cal) {
          
          int dia = cal.get(cal.DAY_OF_MONTH);
          int mes = cal.get(cal.MONTH) + 1;
          int anio = cal.get(cal.YEAR);

          String strMes = StringUtils.pad(""+mes, '0', 2, StringUtils.PAD_IZQUIERDO);
          String strDia = StringUtils.pad(""+dia, '0', 2, StringUtils.PAD_IZQUIERDO);
          
          return anio+ "-" + strMes + "-" + strDia;
      
      }
	  
	  
	  
	  
	  
	  /**
	   * ERP 
	   */
	  public static String getRangoFechas(Date date1, Date date2) {
          
          
          GregorianCalendar cal1 = new GregorianCalendar();
          cal1.setTime(date1);
          
          int dia1 = cal1.get(Calendar.DAY_OF_MONTH);
          int mes1 = cal1.get(Calendar.MONTH);
          int anio1 = cal1.get(Calendar.YEAR);

          GregorianCalendar cal2 = new GregorianCalendar();
          cal2.setTime(date2);
          
          int dia2 = cal2.get(Calendar.DAY_OF_MONTH);
          int mes2 = cal2.get(Calendar.MONTH);
          int anio2 = cal2.get(Calendar.YEAR);

          if(
        		  //anio1 != anio2 
        		  mes1 != mes2) {
        	  
        	  return "del " + dia1 + " de " + meses[mes1] + 
        			  //+ " de " + anio1;
        			  " al " + dia2 + " de " + meses[mes2];
          } else {
    	  
        	  return "del " + dia1 + " al " + dia2 + " de " + meses[mes1];
          }
          
      }
	  
	  public static String getSysdate() {


        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(new Date());

        int dia = cal.get(GregorianCalendar.DAY_OF_MONTH);
        int mes = cal.get(GregorianCalendar.MONTH);
        int anio = cal.get(GregorianCalendar.YEAR);
        
        int hora = cal.get(GregorianCalendar.HOUR);
        int min = cal.get(GregorianCalendar.MINUTE);
        int seg = cal.get(GregorianCalendar.SECOND);
        
        return anio + "-" + (mes + 1) + "-" + dia + "-" + hora + "-" + min + "-" + seg;

    }
	  
	  /**
	   * 
	   * @param fechaInicio yyyy/MM/dd
	   * @param fechaFin
	   * @return
	   */
	  public static TreeMap<Month,String> getMonthInt(String fechaInicio,
			  										  String fechaFin){
		  
		  TreeMap<Month,String> mapFechas = new TreeMap<Month, String>();
		  
		  if(!fechaInicio.equals("") && !fechaFin.equals("")){
			  
		  
		  	int inMesInicio = 0;
		  	int inMesFin 	= 0;
		 	String[] part1 = fechaInicio.split("-");
	    	String anioInicio =  part1[0];
	    	String mesInicio  =  part1[1];
	    	String diaInicio  =  part1[2];
	    	
		  	String[] part2 = fechaFin.split("-");
	    	String anioFin =  part2[0];
	    	String mesFin  =  part2[1];
	    	String diaFin  =  part2[2];
	    	switch(mesInicio){
	    	case "01":
	    		inMesInicio = 1;
	    		break;
	    	case "02":
	    		inMesInicio = 2;
	    		break;
	    	case "03":
	    		inMesInicio = 3;
	    		break;
	    	case "04":
	    		inMesInicio = 4;
	    		break;
	    	case "05":
	    		inMesInicio = 5;
	    		break;
	    	case "06":
	    		inMesInicio = 6;
	    		break;
	    	case "07":
	    		inMesInicio = 7;
	    		break;
	    	case "08":
	    		inMesInicio = 8;
	    		break;
	    	case "09":
	    		inMesInicio = 9;
	    		break;
	    	default:
	    		inMesInicio = Integer.parseInt(mesInicio);
	    		break;
	    	}
	    	
	    	switch(mesFin){
	    	case "01":
	    		inMesFin = 1;
	    		break;
	    	case "02":
	    		inMesFin = 2;
	    		break;
	    	case "03":
	    		inMesFin = 3;
	    		break;
	    	case "04":
	    		inMesFin = 4;
	    		break;
	    	case "05":
	    		inMesFin = 5;
	    		break;
	    	case "06":
	    		inMesFin = 6;
	    		break;
	    	case "07":
	    		inMesFin = 7;
	    		break;
	    	case "08":
	    		inMesFin = 8;
	    		break;
	    	case "09":
	    		inMesFin = 9;
	    		break;
	    	default:
	    		inMesFin = Integer.parseInt(mesFin);
	    		break;
	    	}
	    	
		  
		  List<String>  rangoMeses = getMonthForInt(inMesInicio,inMesFin);
	        for(String m : rangoMeses){
	        	if(m.equals("enero")){
	        		mapFechas.put(Month.JANUARY, getMonthsLetra("01"));
	        	}else if(m.equals("febrero")){
	        		mapFechas.put(Month.FEBRUARY, getMonthsLetra("02"));
			    	
	        	}else if(m.equals("marzo")){
	        		mapFechas.put(Month.MARCH, getMonthsLetra("03"));
			    	
	        	}else if(m.equals("abril")){
	        		mapFechas.put(Month.APRIL, getMonthsLetra("04"));
			    	
	        	}else if(m.equals("mayo")){
	        		mapFechas.put(Month.MAY, getMonthsLetra("05"));
			    	
	        	}else if(m.equals("junio")){
	        		mapFechas.put(Month.JUNE, getMonthsLetra("06"));
			    	
	        	}else if(m.equals("julio")){
	        		mapFechas.put(Month.JULY, getMonthsLetra("07"));
			    	
	        	}else if(m.equals("agosto")){
	        		mapFechas.put(Month.AUGUST, getMonthsLetra("08"));
			    	
	        	}else if(m.equals("septiembre")){
	        		mapFechas.put(Month.SEPTEMBER, getMonthsLetra("09"));
			    	
	        	}else if(m.equals("octubre")){
	        		mapFechas.put(Month.OCTOBER, getMonthsLetra("10"));
			    	
	        	}else if(m.equals("noviembre")){
	        		mapFechas.put(Month.NOVEMBER, getMonthsLetra("11"));
			    	
	        	}else if(m.equals("diciembre")){
	        		mapFechas.put(Month.DECEMBER, getMonthsLetra("12"));
	        	}
	        	
	        }
	        
		 }else{
	        
        		mapFechas.put(Month.JANUARY, getMonthsLetra("01"));
        		mapFechas.put(Month.FEBRUARY, getMonthsLetra("02"));
        		mapFechas.put(Month.MARCH, getMonthsLetra("03"));
        		mapFechas.put(Month.APRIL, getMonthsLetra("04"));
        		mapFechas.put(Month.MAY, getMonthsLetra("05"));
        		mapFechas.put(Month.JUNE, getMonthsLetra("06"));
        		mapFechas.put(Month.JULY, getMonthsLetra("07"));
        		mapFechas.put(Month.AUGUST, getMonthsLetra("08"));
        		mapFechas.put(Month.SEPTEMBER, getMonthsLetra("09"));
        		mapFechas.put(Month.OCTOBER, getMonthsLetra("10"));
        		mapFechas.put(Month.NOVEMBER, getMonthsLetra("11"));
        		mapFechas.put(Month.DECEMBER, getMonthsLetra("12"));
		 }
	    	
		  return mapFechas;
	  }
	  
	  private static String getMonthsLetra(String numeroMes){
		  String mesLetra = "";
		  
		  if(numeroMes.equals("01")){
			  mesLetra = "Enero";
		  }else if(numeroMes.equals("02")){
			  mesLetra = "Febrero";
		  }else if(numeroMes.equals("03")){
			  mesLetra = "Marzo";
		  }else if(numeroMes.equals("04")){
			  mesLetra = "Abril";
		  }else if(numeroMes.equals("05")){
			  mesLetra = "Mayo";
		  }else if(numeroMes.equals("06")){
			  mesLetra = "Junio";
		  }else if(numeroMes.equals("07")){
			  mesLetra = "Julio";
		  }else if(numeroMes.equals("08")){
			  mesLetra = "Agosto";
		  }else if(numeroMes.equals("09")){
			  mesLetra = "Septiembre";
		  }else if(numeroMes.equals("10")){
			  mesLetra = "Octubre";
		  }else if(numeroMes.equals("11")){
			  mesLetra = "Noviembre";
		  }else if(numeroMes.equals("12")){
			  mesLetra = "Diciembre";
		  }
		  return mesLetra;
	  }
	  
	  private static Month getMonthsNumero(String numeroMes){
		  Month mesNumero = null;
		  
		  if(numeroMes.equals("01")){
			  mesNumero = Month.JANUARY;
		  }else if(numeroMes.equals("02")){
			  mesNumero = Month.FEBRUARY;
		  }else if(numeroMes.equals("03")){
			  mesNumero = Month.MARCH;
		  }else if(numeroMes.equals("04")){
			  mesNumero = Month.APRIL;
		  }else if(numeroMes.equals("05")){
			  mesNumero = Month.MAY;
		  }else if(numeroMes.equals("06")){
			  mesNumero = Month.JUNE;
		  }else if(numeroMes.equals("07")){
			  mesNumero = Month.JULY;
		  }else if(numeroMes.equals("08")){
			  mesNumero = Month.AUGUST;
		  }else if(numeroMes.equals("09")){
			  mesNumero = Month.SEPTEMBER;
		  }else if(numeroMes.equals("10")){
			  mesNumero = Month.OCTOBER;
		  }else if(numeroMes.equals("11")){
			  mesNumero = Month.NOVEMBER;
		  }else if(numeroMes.equals("12")){
			  mesNumero = Month.DECEMBER;
		  }
		  return mesNumero;
	  }
	  
	  public static String DiaInglesAEspanol(DayOfWeek dayOfWeek){
		  
		  	String diaEspa = "Problemas al traerse ingles a español";
			if(dayOfWeek == DayOfWeek.MONDAY){
				diaEspa = "Lunes";
			}else if(dayOfWeek == DayOfWeek.TUESDAY){
				diaEspa = "Martes";
			}else if(dayOfWeek == DayOfWeek.WEDNESDAY){
				diaEspa = "Miercoles";
			}else if(dayOfWeek == DayOfWeek.THURSDAY){
				diaEspa = "Jueves";
			}else if(dayOfWeek == DayOfWeek.FRIDAY){
				diaEspa = "Viernes";
			}else if(dayOfWeek == DayOfWeek.SATURDAY){
				diaEspa = "Sábado";
			}else if(dayOfWeek == DayOfWeek.SUNDAY){
				diaEspa = "Domingo";
			}
			return diaEspa;
		}
	  
	  public static String MesInglesAEspanol(Month month){
		  
		  	String mesEspa = "Problemas al traerse ingles a español";
			if(month == Month.JANUARY){
				mesEspa = "Enero";
			}else if(month == Month.FEBRUARY){
				mesEspa = "Febrero";
			}else if(month == Month.MARCH){
				mesEspa = "Marzo";
			}else if(month == Month.APRIL){
				mesEspa = "Abril";
			}else if(month == Month.MAY){
				mesEspa = "Mayo";
			}else if(month == Month.JUNE){
				mesEspa = "Junio";
			}else if(month == Month.JULY){
				mesEspa = "Julio";
			}else if(month == Month.AUGUST){
				mesEspa = "Agosto";
			}else if(month == Month.SEPTEMBER){
				mesEspa = "Septiembre";
			}else if(month == Month.OCTOBER){
				mesEspa = "Octubre";
			}else if(month == Month.NOVEMBER){
				mesEspa = "Noviembre";
			}else if(month == Month.DECEMBER){
				mesEspa = "Diciembre";
			}
			return mesEspa;
		}
	  
	  public static List<String> getMonthForInt(int m1,int m2) {
	        
	        DateFormatSymbols dfs = new DateFormatSymbols();
	        List<String> listRangoMeses = new ArrayList<String>();
	        for(int i = m1-1; i < m2;i ++){
	            listRangoMeses.add(dfs.getMonths()[i]);
	        }
	        return listRangoMeses;
	        }
}

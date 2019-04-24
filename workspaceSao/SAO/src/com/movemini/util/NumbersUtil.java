package com.movemini.util;

public class NumbersUtil {

	

	public static boolean isInteger(String num) {
		
		try {
			Integer.parseInt(num);
			return true;
			
		}catch(Exception ex){
			
			return false;
		}
	}
	
	public static boolean isNumeric(String num) {
		
		try {
			Double.parseDouble(num);
			return true;
			
		}catch(Exception ex){
			
			return false;
		}
	}
	

	public static boolean isNullOrZero(String value) {
		
		try {
			
			if(value == null) {
				
				return true;
			}
			
			if(value.equals("")) {
				
				return true;
			}

			if(value.equals("0")) {
				
				return true;
			}
			
			return false;
			
		}catch(Exception ex){
			
			return false;
		}
	}
	
	
	
	public static String roman(int num) {
		
		switch (num)
		{
			case 1 : return "I";
			case 2 : return "II";
			case 3 : return "III";
			case 4 : return "IV";
			case 5 : return "V";
			case 6 : return "VI";
			case 7 : return "VII";
		}
			
		return "";
	}
	
}

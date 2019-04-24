package com.movemini.util;

public class Convert {

	public static double toDouble(String a) {
		
		try {
			
			return Double.parseDouble(a);
			
		} catch(Exception ex) {
			return 0;
		}
	}
}

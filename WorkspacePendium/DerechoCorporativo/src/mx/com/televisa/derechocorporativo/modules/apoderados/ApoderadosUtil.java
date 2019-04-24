package mx.com.televisa.derechocorporativo.modules.apoderados;

import java.util.ArrayList;

import mx.com.televisa.derechocorporativo.util.TextFormatter;

public class ApoderadosUtil {

	public static String poderesMatch(String poderes, ArrayList<String> poderesNamesParam) {
		
		try {
			if(poderesNamesParam.size() == 0) {
				
				return poderes;
			}
			
			ArrayList<String> results = new ArrayList<String>(); 
			
			String result = "";
			
			for(String param : poderesNamesParam) {
				
				if(TextFormatter.removeAccents(poderes.trim()).contains(TextFormatter.removeAccents(param.trim()))) {
					
					//result += "<li>" + param + "</li>";
					
					if(!results.contains(param.trim())) {
						
						results.add(param.trim());
					}
					
				}
				
			}
			
			
			for (String string : results) {
			
				result += "<li>" + string + "</li>";
			}
			
			
			return result;
			
		} catch(Exception ex) {
			
			return "";
		}
	}
}

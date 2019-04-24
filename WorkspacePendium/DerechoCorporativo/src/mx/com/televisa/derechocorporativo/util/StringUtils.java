package mx.com.televisa.derechocorporativo.util;

public class StringUtils {

	public static String latin1ToUTF8(String stringISO) throws Exception {
	
		
		return new String(stringISO.getBytes("ISO-8859-1"),"UTF-8");
	}
	
	public static String[] split(String str, String character) {
		
		if(character.equals("|")) {
			
			return str.split("\\|");
		
		} else {
			
			return str.split(character);
		}
	} 
}

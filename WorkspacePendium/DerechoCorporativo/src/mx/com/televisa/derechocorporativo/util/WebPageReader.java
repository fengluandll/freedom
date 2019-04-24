package mx.com.televisa.derechocorporativo.util;

import java.net.*;
import java.nio.charset.StandardCharsets;
import java.io.*;

public class WebPageReader {

	public static String read(String url) throws Exception {
		
		StringBuilder sb = new StringBuilder();
		
	    URL theURL = new URL(url);
	    
        BufferedReader in = new BufferedReader(new InputStreamReader(theURL.openStream(),StandardCharsets.ISO_8859_1.newDecoder()));

        
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            //System.out.println(inputLine);
        	
        	sb.append(inputLine);
        	
        }
        in.close();
        
        return sb.toString();
        
	}
}

package com.movemini.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import javax.servlet.http.HttpServletRequest;


public class FileReader {

	
	 public static String getServerContextPath(HttpServletRequest request) {

            String scheme = request.getScheme(); // http
            String serverName = request.getServerName(); // hostname.com
            
            int serverPort = request.getServerPort(); // 80
            String contextPath = request.getContextPath(); // /mywebapp

            String path = scheme + "://" + serverName + ":" + serverPort + contextPath;
        
            return path;
	 }
	 
	
	 /**
	  * @deprecated
	  * */
	 private static String getPath(HttpServletRequest request) {

	        /*String definedPath = HardCodeConstants.WEB_FILES_PATH;
	        
	        if(definedPath.equals("INTERNAL")) {
	        */
	            String scheme = request.getScheme(); // http
	            String serverName = request.getServerName(); // hostname.com
	            
	            //Cuando la maquina no ve iavsa.dyndns-home.com desde adentro.
	            serverName = "localhost";
	            
	            
	            int serverPort = request.getServerPort(); // 80
	            String contextPath = request.getContextPath(); // /mywebapp

	            //String servletPath = request.getServletPath();   // /servlet/MyServlet
	            //String pathInfo = request.getPathInfo();         // /a/b;c=123
	            //String queryString = request.getQueryString();          // d=789
	            // Reconstruct original requesting URL

	            String path = scheme + "://" + serverName + ":" + serverPort + contextPath;
	        
	            return path;
	            
	        /*} else {
	        
	            return definedPath;
	        }*/
	        
	        
	    }
	    
	    
	 /**
	  * @deprecated
	  * */
	   private  static URL getURL(HttpServletRequest request, String webFolder, String fileName) throws MalformedURLException {

	        String path = getPath(request);
	        URL url = new URL(path + "/" + webFolder + "/" + fileName);
	        
	        return url;
	    }


	    
	    
}

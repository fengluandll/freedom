package com.movemini.util;

import javax.servlet.ServletRequest;

public class RequestUtil {

	public static String getContextPath(ServletRequest request) {
		
		
		return request.getServletContext().getContextPath();
	}
	

	public static String getFullContextPath(ServletRequest request) {
		
		String protocol = request.getScheme(); 			// http
	    String serverName = request.getServerName(); 	// hostname.com
	    
	    int serverPort = request.getServerPort(); 		// 8081
	    String contextPath = request.getServletContext().getContextPath(); 	// mywebapp

	    //String servletPath = request.getServletPath();   // /servlet/MyServlet
	    //String pathInfo = request.getPathInfo();         // /a/b;c=123
	    //String queryString = request.getQueryString();          // d=789
	    // Reconstruct original requesting URL

	    String fullContextPath = protocol + "://" + serverName + ":" + serverPort + contextPath;
	    
	    return fullContextPath;
	}
	
	
}

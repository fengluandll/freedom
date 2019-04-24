/*
* Project: AISA
*
* File: LDAP.java
*
* Created on: Abril 5, 2019 at 11:00
*
* Copyright (c) - Kaz Consulting - 2019
*/

package mx.com.televisa.playcity.util;

import java.util.HashMap;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import mx.com.televisa.playcity.view.BillingExtractionSchedulerView;

import org.apache.log4j.Logger;

/*
* Clase de conexión y acceso a datos del servidor LDAP   
*
* @author Kaz Consulting / Jonathan Mariche Catana
*
* @version 1.0.0
*
* @date Abril 5, 2019 at 11:00 am
*/

public class LDAP {
	
	private final static String psURL = "ldap://corpsfedc02.corp.televisa.com.mx:389"
			+ "/dc=corp,dc=televisa,dc=com,dc=mx";
	private static Logger poLogger = Logger.getLogger(BillingExtractionSchedulerView.class);

	//Obtiene conexión con servidor LDAP
	public static DirContext connect(String tsUserID,String tsPassword) 
			throws NamingException{
 		Hashtable<String, String> loEnvironment = new Hashtable<String, String>();
 		loEnvironment.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
 		loEnvironment.put(Context.PROVIDER_URL, psURL);
 		loEnvironment.put(Context.SECURITY_AUTHENTICATION,"simple");
 		loEnvironment.put(Context.SECURITY_PRINCIPAL, tsUserID + "@corp");
 		loEnvironment.put(Context.SECURITY_CREDENTIALS,tsPassword);  	
 		loEnvironment.put(Context.REFERRAL, "follow");
  		DirContext loContext = null;
  		try{
  			loContext = new InitialDirContext(loEnvironment);  		
		} catch (NamingException toException) {
			poLogger.error(toException.getStackTrace());
			toException.printStackTrace();			
		} 
  		return loContext;
	}

	//Cierra conexión con servidor LDAP
	public static void close(DirContext toContext) {
	    try {
	    	toContext.close();
	    } catch (NamingException toException) {  
	    	poLogger.error(toException.getStackTrace());
	    	toException.printStackTrace();
	    }
	}

	//Realiza una búsqueda en el servidor LDAP
	public static HashMap<String, String> search (String tsFilter,
												  DirContext toContext, 
												  String[] taReturningAtts) 
														throws NamingException{ 	    
	    SearchControls loControls = new SearchControls();
	    loControls.setReturningObjFlag(true); // Para que devuelva los elementos que buscamos
	    
	    //Asignamos los atributos a devolver
	    loControls.setReturningAttributes(taReturningAtts);
	    loControls.setSearchScope(SearchControls.SUBTREE_SCOPE);

	    NamingEnumeration loAnswer = null;
	    HashMap<String, String> loMapResult = new HashMap<String, String>();
	    try{
	    	loAnswer = toContext.search(psURL,tsFilter,loControls);	    	
		    while (loAnswer.hasMore()){
		        SearchResult loResult = (SearchResult) loAnswer.next();		        
		        for (String returning : taReturningAtts){		        	
		        	loMapResult.put(returning, 
		        			loResult.getAttributes().get(returning).get().toString());
		        }	        
		    }   
	    }catch (NamingException toException) { 
	    	poLogger.error(toException.getStackTrace());
	    	toException.printStackTrace();
	    }
	    
	    return loMapResult;	    		  
	}

}
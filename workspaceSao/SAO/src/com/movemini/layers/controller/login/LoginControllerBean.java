package com.movemini.layers.controller.login;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import com.movemini.data.OneRecordFactory;
import com.movemini.data.Record;
import com.movemini.layers.session.SessionBean;
   
public class LoginControllerBean {

	private HttpServletRequest request;
	
	
	public LoginControllerBean(HttpServletRequest request) {

		this.request = request;
	}
	
	
	public String doLogin(String usr, String passwd) {
		String txt = "SELECT * FROM ss_usuario WHERE usuario = '"+usr+"' AND contrasenia = '"+passwd+"'";
		//System.out.println(txt);
    	Record user = OneRecordFactory.getFirstRecord(txt);
    	
    	if(user != null){
    		
    		SessionBean sessionBean = SessionBean.getInstance(request.getSession());
    		
    		
    		System.out.println(sessionBean);
    		
    		
    		sessionBean = SessionBean.getInstance(request.getSession());
    		
    		
    		System.out.println(sessionBean);
    		
    		
    		
    		sessionBean.setUser(user);
    		
    		
    		

   			return "<script>window.location.replace(\"main.jsp\");</script>";    		
    	} else { 
    	
    		throw new IllegalArgumentException("Usuario o contrase&ntilde;a incorrectos. Por favor verif&iacute;ca tus datos.");
    	}
    	
    	
    	
	}
	
}

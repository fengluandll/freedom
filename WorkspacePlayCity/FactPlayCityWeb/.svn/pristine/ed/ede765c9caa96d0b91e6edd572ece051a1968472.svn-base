/*
* Project: AISA
*
* File: LoginFilter.java
*
* Created on: Abril 5, 2019 at 11:00
*
* Copyright (c) - Kaz Consulting - 2019
*/

package mx.com.televisa.playcity.filter;

import java.io.IOException;
import javax.faces.context.FacesContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mx.com.televisa.playcity.beans.LoginBean;
import org.apache.log4j.Logger;

/*
* Filtro de seguridad de la aplicación 
* 
* @author Kaz Consulting / Jesus Argumedo
*
* @version 1.0.0
*
* @date Abril 5, 2019 at 11:00 am
*/

public class LoginFilter implements Filter{
	
	private static Logger poLogger = Logger.getLogger(LoginFilter.class);
	FacesContext          poFacesContext = FacesContext.getCurrentInstance();	
	
	@Override
	public void destroy() {
		poLogger.debug("session destroyed :");
	}

	//Filtra las peticiones de acuerdo a las reglas de seguridad
	public void doFilter(ServletRequest toRequest, ServletResponse toResponse,
			FilterChain toChain) throws IOException, ServletException {
			  HttpServletRequest  loRequest = (HttpServletRequest) toRequest;
			  HttpServletResponse loResponse = (HttpServletResponse) toResponse;			  
			  HttpSession         loSession = loRequest.getSession();			  
			  //Proceso la URL que está requiriendo el cliente
			  String              lsUrlStr = loRequest.getRequestURL().toString();
			  poLogger.info("urlStr " + lsUrlStr);
			  boolean lbisProtected = noProtectURL(lsUrlStr);
			  
			  //Si no requiere protección continúo normalmente.
			  if (lbisProtected) {
				  toChain.doFilter(loRequest, loResponse);
			    return;
			  }
			  
			  //El usuario no está logueado
			  LoginBean loLoginBean = (LoginBean)loSession.getAttribute("loginBean");
			  if (loLoginBean != null && (loLoginBean.getPsUsername() == null 
					  || loLoginBean.getPsUsername().equals(""))) {
				  loResponse.sendRedirect(loRequest.getContextPath() + 
						  "/faces/login.xhtml");
			    return;
			  }
			 
			  //El recurso requiere protección, pero el usuario ya está logueado.
			  toChain.doFilter(loRequest, loResponse);
	}
			
	//Determina si una url debe ser protegida
	private boolean noProtectURL(String tsUrlStr) {		
		  if ( tsUrlStr.endsWith("login.xhtml") 
				  || tsUrlStr.endsWith("login.xhtml;"))
		    return true;
		  if (tsUrlStr.indexOf("/javax.faces.resource/") != -1)
		    return true;
		  return false;
		}

	@Override
	public void init(FilterConfig toConfig) throws ServletException {
	}

}
package mx.com.televisa.derechocorporativo.filter;


import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LoginFilter implements Filter {

	FacesContext facesContext = FacesContext.getCurrentInstance();
	
	public LoginFilter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void destroy() {
		System.out.println("session destroyed :");

	}

	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
			  HttpServletRequest req = (HttpServletRequest) request;
			  HttpServletResponse res = (HttpServletResponse) response;
			  
			  HttpSession session = req.getSession();
			  
			  //Proceso la URL que está requiriendo el cliente
			  String urlStr = req.getRequestURL().toString().toLowerCase();
			  boolean noProteger = noProteger(urlStr);
//			  System.out.println(urlStr + " - desprotegido=[" + noProteger + "]");
			  
			  //Si no requiere protección continúo normalmente.
			  if (noProteger) {
			    chain.doFilter(request, response);
			    return;
			  }
			  
			  //El usuario no está logueado
//			  logger.debug("studenPersonal "+session.getAttribute("studentPersonalBean"));
			  if (session.getAttribute("sessionBean") == null) {
			    res.sendRedirect(req.getContextPath());
			    return;
			  }
			 
			  //El recurso requiere protección, pero el usuario ya está logueado.
			  chain.doFilter(request, response);
	}
	
	private boolean noProteger(String urlStr) {

		/*
		 * Lugar para colocar y programar todos los patrones que
		 * creamos convenientes para determinar cuales de los recursos no
		 * requieren protección. 
		 */
		  if (urlStr.endsWith("logout=true") || urlStr.endsWith("recuperapass.xhtml") || 
			  urlStr.endsWith("login.jsp")   || urlStr.endsWith("printTab.jsp") ||
			  urlStr.endsWith("apoderadosprint.jsp") || urlStr.endsWith("printtab.jsp")||
			  urlStr.endsWith("reformasprint.jsp") || urlStr.endsWith("consultapoderesprint.jsp") || 
			   urlStr.endsWith("detalleprint.jsp")) 
		    return true;
		  if (urlStr.indexOf("/javax.faces.resource/") != -1)
		    return true;
		  return false;
		}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("Init de Login Filter");

	}

}



package mx.kaz.filter;

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


import org.apache.log4j.Logger;

/**
 * @author Jesus Argumedo
 *
 */
public class LoginFilter implements Filter{
	
	private static org.apache.log4j.Logger logger = Logger.getLogger(LoginFilter.class);
	FacesContext facesContext = FacesContext.getCurrentInstance();
	
	public LoginFilter() {

	}

	@Override
	public void destroy() {
		logger.debug("session destroyed :");

	}

	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
			  HttpServletRequest req = (HttpServletRequest) request;
			  HttpServletResponse res = (HttpServletResponse) response;
			  
			  HttpSession session = req.getSession();
			  
			  //Proceso la URL que est� requiriendo el cliente
			  String urlStr = req.getRequestURL().toString();
			  logger.info("urlStr "+urlStr);
			  boolean noProteger = noProteger(urlStr);
			  
			  //Si no requiere protecci�n contin�o normalmente.
			  if (noProteger) {
			    chain.doFilter(request, response);
			    return;
			  }
			  
			  //El usuario no est� logueado
//			  logger.debug("studenPersonal "+session.getAttribute("studentPersonalBean"));
			  if (session.getAttribute("personalBean") == null) {
			    res.sendRedirect(req.getContextPath() + "/login.xhtml");
			    return;
			  }
			 
			  //El recurso requiere protecci�n, pero el usuario ya est� logueado.
			  chain.doFilter(request, response);
	}
	
	private boolean noProteger(String urlStr) {

		/*
		 * Este es un buen lugar para colocar y programar todos los patrones que
		 * creamos convenientes para determinar cuales de los recursos no
		 * requieren protecci�n. Sin duda que habr�a que crear un mecanizmo tal
		 * que se obtengan de un archivo de configuraci�n o algo que no requiera
		 * compilaci�n.
		 */
		  if ( urlStr.endsWith("login.xhtml") || urlStr.endsWith("login.xhtml;") || urlStr.toUpperCase().endsWith("changePassword.xhtml".toUpperCase()))
		    return true;
		  if (urlStr.indexOf("/javax.faces.resource/") != -1)
		    return true;
		  return false;
		}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}

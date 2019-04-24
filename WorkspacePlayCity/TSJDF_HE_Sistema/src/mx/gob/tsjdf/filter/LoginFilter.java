package mx.gob.tsjdf.filter;

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

public class LoginFilter implements Filter{

	private static org.apache.log4j.Logger logger = Logger.getLogger(LoginFilter.class);
	FacesContext facesContext = FacesContext.getCurrentInstance();
	
	public LoginFilter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void destroy() {
		logger.debug("session destroyed :");

	}

	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
			  HttpServletRequest req = (HttpServletRequest) request;
			  HttpServletResponse res = (HttpServletResponse) response;
			  
			  HttpSession session = req.getSession();
			  
			  //Proceso la URL que esta requiriendo el cliente
			  String urlStr = req.getRequestURL().toString().toLowerCase();
			  boolean noProteger = noProteger(urlStr);
//			  System.out.println(urlStr + " - desprotegido=[" + noProteger + "]");
			  
			  //Si no requiere proteccion continuo normalmente.
			  if (noProteger) {
			    chain.doFilter(request, response);
			    return;
			  }
			  
			  //El usuario no esta logueado
//			  logger.debug("studenPersonal "+session.getAttribute("studentPersonalBean"));
			  if (session.getAttribute("loginBean") == null) {
			    res.sendRedirect(req.getContextPath() + "/index.xhtml");
			    return;
			  }
			 
			  //El recurso requiere proteccion, pero el usuario ya esta logueado.
			  chain.doFilter(request, response);
	}
	
	private boolean noProteger(String urlStr) {

		/*
		 * Este es un buen lugar para colocar y programar todos los patrones que
		 * creamos convenientes para determinar cuales de los recursos no
		 * requieren proteccion. Sin duda que habria que crear un mecanizmo tal
		 * que se obtengan de un archivo de configuracion o algo que no requiera
		 * compilacion.
		 */
		  if (urlStr.endsWith("index.xhtml") || urlStr.indexOf("index.xhtml;") > -1)
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

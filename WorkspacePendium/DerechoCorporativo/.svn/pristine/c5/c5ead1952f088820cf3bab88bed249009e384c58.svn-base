package mx.com.televisa.derechocorporativo.modules.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SupportedBrowserCheck
 */
public class SupportedBrowserCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html;charset=UTF-8");
	    PrintWriter out = response.getWriter();
        
        try {
        	
        	String clientInfo = request.getParameter("clientInfo");
        	out.println("");
			
        	
        	
        	
		} catch (Exception e) {
			out.println(e.getMessage());
			e.printStackTrace();
		}finally{
			out.close();
		}
		
		
	}

}

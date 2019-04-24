package mx.com.televisa.derechocorporativo.modules.reports;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RepEstructuraCapSocialServlet
 */
@WebServlet("/RepEstructuraCapSocialServlet")
public class RepEstructuraCapSocialServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public RepEstructuraCapSocialServlet() {
        super();
    }
    
    public void processRequest(HttpServletRequest request,HttpServletResponse response) throws IOException {

    	response.setContentType("text/html");
         PrintWriter out = response.getWriter();
         out.println("<h1> HOLA MUNDO</h1>");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

}

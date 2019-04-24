package mx.com.televisa.derechocorporativo.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.com.televisa.derechocorporativo.modules.reports.apoderados.RepApoderados;

/**
 * Servlet implementation class ConsultaIdPoderesServlet
 */
@WebServlet("/ConsultaIdPoderesServletBkpAntesDeArgu")
public class ConsultaIdPoderesServletBkpAntesDeArgu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void processRequest(HttpServletRequest tuRequest
                              ,HttpServletResponse tuResponse)throws IOException{
		PrintWriter out = tuResponse.getWriter();
		try{
			tuResponse.setContentType("text/html;charset=UTF-8"); 
			  
			  HttpSession session = tuRequest.getSession();
			  String idCatalogo = tuRequest.getParameter("idCatalogo");
			  String idCatValor = RepApoderados.getIdValorCatalogo(idCatalogo);
			  System.out.println("Valores "+idCatValor);
			  session.removeAttribute("PODERES");
			  out.println(idCatValor);
		}finally{
			out.close();
		}
		  
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
	}


}

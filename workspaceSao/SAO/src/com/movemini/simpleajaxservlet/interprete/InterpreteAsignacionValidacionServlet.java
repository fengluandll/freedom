package com.movemini.simpleajaxservlet.interprete;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movemini.data.OneValueFactory;

/**
 * Servlet implementation class InterpreteAsignacionValidacionServlet
 */
@WebServlet("/InterpreteAsignacionValidacionServlet")
public class InterpreteAsignacionValidacionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InterpreteAsignacionValidacionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
		
		
		String id_interprete = request.getParameter("id_interprete");
		
		String id_evento_interprete = request.getParameter("id_evento_interprete");
		
		String matches = OneValueFactory.get("evento_interprete_validacion_pr", id_evento_interprete, id_interprete);
		
		String result = "OK";
		 
		if(!matches.equals("0")) {
			
			result = "El int&eacute;rprete ya esta asignado a un evento en esa fecha. Desea continuar con la asignaci&oacute;n?";
		}
		
		
		
		response.getWriter().append(result);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

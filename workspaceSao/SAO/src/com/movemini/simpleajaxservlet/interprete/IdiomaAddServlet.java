package com.movemini.simpleajaxservlet.interprete;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movemini.data.ConnectionWrapper;

/**
 * Servlet implementation class EspecialidadAddServlet
 */
@WebServlet("/IdiomaAddServlet")
public class IdiomaAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IdiomaAddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		
		String id_interprete = request.getParameter("id_interprete");
		String id_idioma = request.getParameter("id_idioma");
		
		
		ConnectionWrapper.staticExecuteUpdate("INSERT INTO cat_interprete_idioma (id_interprete, id_idioma) VALUES ('" + id_interprete + "','" + id_idioma + "')");
		
		
		String result = "OK";
		
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

package com.movemini.simpleajaxservlet.evento_sala;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movemini.data.ConnectionWrapper;
import com.movemini.data.ProcedureCall;

/**
 * Servlet implementation class SalaUpdateCantidadServlet
 */
@WebServlet("/SalaUpdateCantidadServlet")
public class SalaUpdateCantidadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SalaUpdateCantidadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setCharacterEncoding("UTF-8");
		
		
		String key = request.getParameter("key");
		String nom = request.getParameter("nom");
		
		String sql = "UPDATE evento_sala SET cantidad_dias = '" + nom + "' WHERE id_evento_sala = '" + key + "'";
		
		ConnectionWrapper.staticExecuteUpdate(sql);
		
		
		ProcedureCall.call("recalculate_evento_sala_pr", key);
		
		
		
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

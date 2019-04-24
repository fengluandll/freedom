package com.movemini.simpleajaxservlet.paquete;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movemini.data.ConnectionWrapper;
import com.movemini.data.OneValueFactory;
import com.movemini.data.ProcedureCall;

/**
 * Servlet implementation class EventoAddPaqueteServlet
 */
@WebServlet("/EventoAddPaqueteServlet")
public class EventoAddPaqueteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventoAddPaqueteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
		String id_evento_sala = request.getParameter("id_evento_sala");
		String id_paquete = request.getParameter("id_paquete");
		
		ProcedureCall.call("evento_producto_insert_paquete_pr", id_evento_sala, id_paquete);
		
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

package com.movemini.simpleajaxservlet.evento_fecha;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movemini.data.ConnectionWrapper;
import com.movemini.data.ProcedureCall;

/**
 * Servlet implementation class EventoFechaUpdateServlet
 */
@WebServlet("/EventoFechaUpdateServlet")
public class EventoFechaUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventoFechaUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
		String key = request.getParameter("key");
		String fecha = request.getParameter("fecha");
		
		String sql = "UPDATE evento_fecha SET fecha =  STR_TO_DATE('" + fecha+ "', '%d/%c/%Y') WHERE id_evento_fecha = '" + key + "'";
		System.out.println(sql);
		ConnectionWrapper.staticExecuteUpdate(sql);
		
		
		
		ProcedureCall.call("evento_fechas_pr", key);
		
		
		
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

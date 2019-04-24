package com.movemini.simpleajaxservlet.evento;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movemini.data.ConnectionWrapper;

/**
 * Servlet implementation class EventoUpdateServlet
 */
@WebServlet("/EventoUpdateServlet")
public class EventoUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventoUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setCharacterEncoding("UTF-8");



		String id_evento = request.getParameter("id_evento");



		String field_id = request.getParameter("field_id");
		String field_value = request.getParameter("field_value");


		
		

		String sql = "UPDATE evento SET " + field_id + " = '" + field_value + "' WHERE id_evento = (select id_evento from evento_version where id_evento_version = '" + id_evento + "' )";

		ConnectionWrapper.staticExecuteUpdate(sql);


		String result = "OK";

		response.getWriter().append(result);





//		String id_evento = request.getParameter("id_evento");
//
//		String nombre = request.getParameter("nombre");
//		String status = request.getParameter("status");
//		String id_empresa = request.getParameter("id_empresa");
//
//
//		String sql = "UPDATE evento SET nombre = '" + nombre + "', status = '" + status + "', id_empresa = '" + id_empresa + "' WHERE id = '" + id_evento + "'";
//
//		ConnectionWrapper.staticExecuteUpdate(sql);
//
//
//		String result = "OK";
//
//		response.getWriter().append(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

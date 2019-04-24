package com.movemini.simpleajaxservlet.interprete;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movemini.data.ConnectionWrapper;

/**
 * Servlet implementation class AsignacionInterpreteUpdateFechaServlet
 */
@WebServlet("/AsignacionInterpreteUpdateFechaServlet")
public class AsignacionInterpreteUpdateFechaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AsignacionInterpreteUpdateFechaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String key = request.getParameter("key");
		String fecha = request.getParameter("fecha");
		
		String sql = "UPDATE evento_interprete SET fecha = str_to_date('" + fecha + "', '%d/%m/%Y') WHERE id_evento_interprete = '" + key + "'";
		
		ConnectionWrapper.staticExecuteUpdate(sql);
		
		
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

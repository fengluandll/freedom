package com.movemini.simpleajaxservlet.evento_sala;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movemini.data.ConnectionWrapper;

/**
 * Servlet implementation class SedeDeleteServlet
 */
@WebServlet("/SalaDeleteServlet")
public class SalaDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SalaDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		
		
		String id_sala = request.getParameter("id_sala");
		
		
		//ConnectionWrapper.staticExecuteUpdate("DELETE FROM evento_sede_agenda WHERE id_sede = " + id_sede + "");
		
		ConnectionWrapper.staticExecuteUpdate("DELETE FROM evento_sala WHERE id_evento_sala = " + id_sala + "");
		
		
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

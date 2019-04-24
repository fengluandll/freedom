package com.movemini.simpleajaxservlet.evento_fecha;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movemini.simpleajaxservlet.evento_sala_fecha.SedeFechasTable;

/**
 * Servlet implementation class EventoFechasSelectServlet
 */
@WebServlet("/EventoFechasSelectServlet")
public class EventoFechasSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventoFechasSelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		
		response.setCharacterEncoding("UTF-8");
		
		
		
		EventoFechasTable bean = new EventoFechasTable(request);
		
		String result = bean.getHtml();
		
		
		
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

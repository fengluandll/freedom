package com.movemini.simpleajaxservlet.tecnico;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movemini.data.ConnectionWrapper;
import com.movemini.tecnico.ManejadorTecnico;

/**
 * Servlet implementation class TecnicoAsignacionServlet
 */
@WebServlet("/TecnicoDesAsignacionServlet")
public class TecnicoDesAsignacionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TecnicoDesAsignacionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stubString id_interprete = request.getParameter("id_interprete");
		
		String idTecnico = request.getParameter("id_tecnico");
		String idEventoTecnico = request.getParameter("id_evento_tecnico");

		ManejadorTecnico mnjTecnico = new ManejadorTecnico();
		mnjTecnico.quitaAsignacionTecnico(idEventoTecnico);
		
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

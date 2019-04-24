package com.movemini.simpleajaxservlet.tecnico;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movemini.data.OneValueFactory;
import com.movemini.tecnico.ManejadorTecnico;

/**
 * Servlet implementation class TecnicoAsignacionValidacionServlet
 */
@WebServlet("/TecnicoAsignacionValidacionServlet")
public class TecnicoAsignacionValidacionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TecnicoAsignacionValidacionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String idTecnico = request.getParameter("id_tecnico");
		
		String idEventotecnico = request.getParameter("id_evento_tecnico");
		
		ManejadorTecnico mnjTecnico = new ManejadorTecnico();
		
		String result = mnjTecnico.validarTecnicoAsignadoEvento(idEventotecnico, idTecnico);
		
		if(result.length() <= 0){
			result = "OK";
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

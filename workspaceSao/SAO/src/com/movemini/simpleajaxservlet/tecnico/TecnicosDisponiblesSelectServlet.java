package com.movemini.simpleajaxservlet.tecnico;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movemini.simpleajaxservlet.interprete.InterpretesAsignadosTable;
import com.movemini.tecnico.ManejadorTecnico;

/**
 * Servlet implementation class TecnicosDisponiblesSelectServlet
 */
@WebServlet("/TecnicosDisponiblesSelectServlet")
public class TecnicosDisponiblesSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TecnicosDisponiblesSelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");

		
		ManejadorTecnico mnjTecnico = new ManejadorTecnico();
		
		String result = mnjTecnico.crearTablaTecnicosDisponibles();
		
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

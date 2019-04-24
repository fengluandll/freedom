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
 * Servlet implementation class TecnicoUpdateServlet
 */
@WebServlet("/TecnicoUpdateServlet")
public class TecnicoUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TecnicoUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String idTecnico =  request.getParameter("id_tecnico") != null || request.getParameter("id_tecnico").length() >= 0 ? request.getParameter("id_tecnico") : "0";
		String fieldId = request.getParameter("field_id");
		String fieldValue = request.getParameter("field_value");
		
		ManejadorTecnico mnjTecnico = new ManejadorTecnico();
		mnjTecnico.actualiarTecnico(fieldId, fieldValue, Integer.parseInt(idTecnico));
		
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

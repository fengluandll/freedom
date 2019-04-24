package com.movemini.simpleajaxservlet.traductores;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movemini.catalogos.ManejadorCatalogos;
import com.movemini.data.ConnectionWrapper;

/**
 * Servlet implementation class TraductorUpdateServlet
 */
@WebServlet("/TraductorUpdateServlet")
public class TraductorUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TraductorUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String idTraductor = request.getParameter("id_traductor");
		String campo = request.getParameter("field_id");
		String valor = request.getParameter("field_value");
		
		ManejadorCatalogos mnjCatalogos = new ManejadorCatalogos();
		mnjCatalogos.actualizarCampoTraductor(campo, valor, idTraductor);
		
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
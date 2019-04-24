package com.movemini.simpleajaxservlet.subtotales;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movemini.subtotales.CotizacionSubtotales;

/**
 * Servlet implementation class SubtotalesInsertNuevoServlet
 */
@WebServlet("/SubtotalesInsertNuevoServlet")
public class SubtotalesInsertNuevoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubtotalesInsertNuevoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String idEvento = request.getParameter("id_evento");
		String nombre = request.getParameter("nombre_subgrupo");
		String descripcion = request.getParameter("descripcion_subgrupo");

		CotizacionSubtotales subtotal = new CotizacionSubtotales();
		subtotal.crearSubtotal(idEvento, nombre,descripcion);
		
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

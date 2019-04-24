package com.movemini.simpleajaxservlet.subtotales;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movemini.data.ConnectionWrapper;
import com.movemini.data.OneValueFactory;
import com.movemini.subtotales.CotizacionSubtotales;

/**
 * Servlet implementation class CotizacionUpdateEventoSala
 */
@WebServlet("/CotizacionUpdateEventoSala")
public class CotizacionUpdateEventoSala extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CotizacionUpdateEventoSala() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String fieldId = request.getParameter("field_id");
		String fieldValue = request.getParameter("field_value");
		String idSala = request.getParameter("id_sala");
		
		CotizacionSubtotales subtotal = new CotizacionSubtotales();
		subtotal.actualizarSubtotalSala(fieldId, fieldValue, idSala);
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

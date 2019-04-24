package com.movemini.simpleajaxservlet.factura_detalle;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movemini.data.ConnectionWrapper;

/**
 * Servlet implementation class AddEventoDetalleFacturaServlet
 */
@WebServlet("/AddEventoDetalleFacturaServlet")
public class AddEventoDetalleFacturaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddEventoDetalleFacturaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {




		String id_evento_factura= request.getParameter("ID_FACTURA");



		ConnectionWrapper.staticExecuteUpdate("INSERT INTO factura_detalle (id_evento_factura) VALUES ("+id_evento_factura+")");


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

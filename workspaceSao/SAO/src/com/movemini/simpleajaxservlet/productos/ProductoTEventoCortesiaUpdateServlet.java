package com.movemini.simpleajaxservlet.productos;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movemini.data.ConnectionWrapper;
import com.movemini.data.ProcedureCall;

/**
 * Servlet implementation class ProductoTEventoCortesiaUpdateServlet
 */
@WebServlet("/ProductoTEventoCortesiaUpdateServlet")
public class ProductoTEventoCortesiaUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductoTEventoCortesiaUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());






		String id_evento_producto = request.getParameter("id_evento_producto");
		String cortesia = request.getParameter("cortesia");
		String sql = "UPDATE evento_producto_te SET cortesia = '" + cortesia + "' WHERE id_evento_producto = '" + id_evento_producto + "'";
		

		System.out.println(sql);

		ConnectionWrapper.staticExecuteUpdate(sql);


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

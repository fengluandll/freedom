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
 * Servlet implementation class ProductoTEEventoUpdateServlet
 */
@WebServlet("/ProductoTEEventoUpdateServlet")
public class ProductoTEEventoUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductoTEEventoUpdateServlet() {
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
		String field_id = request.getParameter("field_id");
		String field_value = request.getParameter("field_value");

		boolean updateMonto = true;

		if(field_id.equals("monto")) {

			updateMonto = false;
		}


		if(field_value == null || field_value.trim().equals("")){
			field_value = "NULL";
		}else {
			 field_value ="'" + field_value + "'";
		}

		String sql = "UPDATE evento_producto_te SET " + field_id + " = " + field_value + " WHERE id_evento_producto = " + id_evento_producto;

		ConnectionWrapper.staticExecuteUpdate(sql);


		if(updateMonto) {

			ProcedureCall.call("evento_producto_te_update_cant", id_evento_producto);
		}



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

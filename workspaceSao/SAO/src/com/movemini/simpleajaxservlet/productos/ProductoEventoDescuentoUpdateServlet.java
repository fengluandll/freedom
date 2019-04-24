package com.movemini.simpleajaxservlet.productos;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movemini.data.ConnectionWrapper;
import com.movemini.data.OneValueFactory;
import com.movemini.data.ProcedureCall;

/**
 * Servlet implementation class ProductoEventoDescuentoUpdateServlet
 */
@WebServlet("/ProductoEventoDescuentoUpdateServlet")
public class ProductoEventoDescuentoUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductoEventoDescuentoUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		

		String result = "";
		

		String id_evento_producto = request.getParameter("id_evento_producto");
		String descuento = request.getParameter("descuento");

		
		
		String descuento_max = OneValueFactory.get(
						"SELECT descuento_max FROM cat_producto "
						+ "WHERE id_producto = (select id_producto FROM evento_producto "
												+ "WHERE id_evento_producto = " + id_evento_producto + ")");


		if(descuento == null || descuento.equals("")) {
			
			descuento = "0";
		}
		

		if(descuento_max == null || descuento_max.equals("")) {
			
			descuento_max = "0";
		}

		
		double dblDescuento = Double.parseDouble(descuento);
		double dblDescuentoMax = Double.parseDouble(descuento_max);		
				
		
		if(dblDescuento > dblDescuentoMax) {
			
			result = "El descuento solicitado, excede el descuento m�ximo de este producto (" + descuento_max  + " %)";
			
			
		} else {
			
		

			String sql = "UPDATE evento_producto SET descuento = '" + descuento + "' WHERE id_evento_producto = '" + id_evento_producto + "'";
			
			ConnectionWrapper.staticExecuteUpdate(sql);
			
			
			ProcedureCall.call("evento_producto_update_values_pr", id_evento_producto, 1);
			
			
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

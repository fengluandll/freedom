package com.movemini.simpleajaxservlet.productos;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movemini.data.ConnectionWrapper;
import com.movemini.data.OneRecordFactory;
import com.movemini.data.ProcedureCall;
import com.movemini.data.Record;

/**
 * Servlet implementation class ValidateCantidadProductoEventoServlet
 */
@WebServlet("/ValidateCantidadProductoEventoServlet")
public class ValidateCantidadProductoEventoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidateCantidadProductoEventoServlet() {
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
		
		try {
		
			


			String id_evento_producto = request.getParameter("id_evento_producto");
			String cantidad = request.getParameter("cantidad");
			
			String sql = "SELECT * FROM evento_producto WHERE id_evento_producto = '" + id_evento_producto + "'";
			
			Record row_evento_producto =  OneRecordFactory.getFirstRecord(sql);
			
			
			
			
			String paq_cantidad_min = row_evento_producto.get("paq_cantidad_min");
			String paq_cantidad_max = row_evento_producto.get("paq_cantidad_max");
			//String paq_precio_fijo = row_evento_producto.get("paq_precio_fijo");
		
			
			if(
					
					paq_cantidad_min == null
					||
					paq_cantidad_max == null
				
					||
					
					paq_cantidad_min.equals("")
					|| 
					paq_cantidad_max.equals("")
				
					//&& paq_precio_fijo == null
					) {
				
				result = "OK";
				
				
			} else {
				
				

				if(Integer.parseInt(cantidad) < Integer.parseInt(paq_cantidad_min) 
					|| Integer.parseInt(cantidad) > Integer.parseInt(paq_cantidad_max)
						) {
					
					result = "El dato esta fuera de los rangos permitidos en este paquete.";
					
					
				} else {
					
					result = "OK";
					
					
				}
			
			}
			
			
			
			
		} catch (Exception e) {
			
			
			
			result = "El dato esta fuera de los rangos permitidos en este paquete.";
			
			
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

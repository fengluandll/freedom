package com.movemini.simpleajaxservlet.productos;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movemini.config.HardCodeConstants;
import com.movemini.data.ConnectionWrapper;
import com.movemini.data.OneValueFactory;
import com.movemini.data.ProcedureCall;
import com.movemini.layers.session.SessionBean;

/**
 * Servlet implementation class ProductoEventoPrecioUpdateServlet
 */
@WebServlet("/ProductoEventoPrecioUpdateServlet")
public class ProductoEventoPrecioUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductoEventoPrecioUpdateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());

		SessionBean sessionBean = SessionBean.getInstance(request);
		String id_evento = sessionBean.getIdEvento();

//		String idMoneda = OneValueFactory.get("SELECT id_moneda FROM evento WHERE id_evento = " + id_evento);
//
//		String precio_sufix = "";
//
//		if (idMoneda.equals("1")) {
//
//			precio_sufix = "_mxn";
//
//		} else {
//
//			precio_sufix = "_usd";
//		}

		//String id_tipo_eveto = OneValueFactory.get("SELECT id_tipo_eveto FROM evento WHERE id_evento = " + id_evento);

		

		String idTipoCotizacion = OneValueFactory.get("SELECT id_tipo_cotizacion FROM evento WHERE id_evento = " + id_evento);

		String precio_sufix = "";

		if (HardCodeConstants.ID_OMNILINGUA_USA.equals(idTipoCotizacion)) {

			precio_sufix = "_usd";
			
		} else {

			precio_sufix = "_mxn";
		}
		

		
		
		String id_evento_producto = request.getParameter("id_evento_producto");
		String cantidad = request.getParameter("cantidad");

		String sql = "UPDATE evento_producto SET precio_especial_descuento" + precio_sufix + " = '" + cantidad
				+ "' WHERE id_evento_producto = '" + id_evento_producto + "'";

		ConnectionWrapper.staticExecuteUpdate(sql);

		ProcedureCall.call("evento_producto_update_price_pr", id_evento_producto);  //, id_tipo_eveto

		String result = "OK";

		response.getWriter().append(result);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

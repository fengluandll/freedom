package com.movemini.simpleajaxservlet.productos;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movemini.catalogos.ManejadorCatalogos;
import com.movemini.data.ConnectionWrapper;
import com.movemini.data.OneRecordFactory;
import com.movemini.data.OneValueFactory;
import com.movemini.data.ProcedureCall;
import com.movemini.data.Record;

/**
 * Servlet implementation class PoliticaAddServlet
 */
@WebServlet("/ProductoAddServlet")
public class ProductoAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductoAddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		

		
		//String ID_EVENTO = request.getParameter("ID_EVENTO");
		
		String id_dia = request.getParameter("id_dia");
		String id_producto = request.getParameter("id_producto");
		String idTipoEvento = request.getParameter("id_tipo_evento");
		
		
		
		//ConnectionWrapper.staticExecuteUpdate("INSERT INTO evento_producto_temp (id_evento, id_producto) VALUES ('" + ID_EVENTO + "','" + id_producto + "')");
		//ConnectionWrapper.staticExecuteUpdate("INSERT INTO evento_producto (id_evento_sala_dia, id_producto, cantidad) VALUES ('" + id_dia + "','" + id_producto + "',1)");
		if(idTipoEvento.equals("1")){
			ConnectionWrapper.staticExecuteUpdate("INSERT INTO evento_producto (id_evento_sala, id_producto, cantidad) VALUES ('" + id_dia + "','" + id_producto + "',1)");
			

			String id_evento_producto = OneValueFactory.get("SELECT max(id_evento_producto) FROM evento_producto");
					
			
			ProcedureCall.call("evento_producto_update_values_pr", id_evento_producto, idTipoEvento);
			
		}else{
			
			ManejadorCatalogos mnjCatalogos = new ManejadorCatalogos();
			Record producto = mnjCatalogos.obtenerProductoTraduccionEscrita(id_producto);
			
			/*
			 * CC.entrega_normal,
		        CC.entrega_urgente,
		        CC.palabra_normal,
		        CC.palabra_urgente,
		        cc.id_cobro,
		        cc.normal_urgente
			 */
			
			

			
			
			String qry = "INSERT INTO evento_producto_te (id_evento_sala,id_producto, cuartilla_normal_especial, cuartilla_urgente_especial, palabra_normal_especial, palabra_urgente_especial, cuartilla_normal_especial_usd, cuartilla_urgente_especial_usd, palabra_normal_especial_usd, palabra_urgente_especial_usd, cuartilla_normal, cuartilla_urgente, palabra_normal, palabra_urgente, cuartilla_normal_usd, cuartilla_urgente_usd, palabra_normal_usd, palabra_urgente_usd) "
					+ "VALUES ('" + id_dia + "','" + id_producto+ "'"
							+ ",'" + producto.get("cuartilla_normal_especial") + "'"
							+ ",'" + producto.get("cuartilla_urgente_especial") + "'"
							+ ",'" + producto.get("palabra_normal_especial") + "'"
							+ ",'" + producto.get("palabra_urgente_especial") + "'"
							+ ",'" + producto.get("cuartilla_normal_especial_usd") + "'"
							+ ",'" + producto.get("cuartilla_urgente_especial_usd") + "'"
							+ ",'" + producto.get("palabra_normal_especial_usd") + "'"
							+ ",'" + producto.get("palabra_urgente_especial_usd") + "'"
							+ ",'" + producto.get("cuartilla_normal") + "'"
							+ ",'" + producto.get("cuartilla_urgente") + "'"
							+ ",'" + producto.get("palabra_normal") + "'"
							+ ",'" + producto.get("palabra_urgente") + "'"
							+ ",'" + producto.get("cuartilla_normal_usd") + "'"
							+ ",'" + producto.get("cuartilla_urgente_usd") + "'"
							+ ",'" + producto.get("palabra_normal_usd") + "'"
							+ ",'" + producto.get("palabra_urgente_usd") + "'"+
							")";
			System.out.println(qry);
			
			ConnectionWrapper.staticExecuteUpdate(qry);
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

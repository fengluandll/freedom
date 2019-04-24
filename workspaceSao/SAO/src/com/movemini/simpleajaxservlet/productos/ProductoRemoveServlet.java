package com.movemini.simpleajaxservlet.productos;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movemini.data.ConnectionWrapper;
import com.movemini.data.OneValueFactory;

/**
 * Servlet implementation class PoliticaRemoveServlet
 */
@WebServlet("/ProductoRemoveServlet")
public class ProductoRemoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductoRemoveServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
//		String id_evento_condicion = request.getParameter("ID_EVENTO");
		String id_evento_producto = request.getParameter("id_evento_producto");
		
		String isTraduccionEscrita = request.getParameter("isTraduccionEscrita");
		
		
		if(isTraduccionEscrita == null || isTraduccionEscrita.equals("") || isTraduccionEscrita.equals("NO")) {
		
			//ConnectionWrapper.staticExecuteUpdate("DELETE FROM evento_producto_temp WHERE id_evento_producto = '" + id_evento_producto + "'");
			ConnectionWrapper.staticExecuteUpdate("DELETE FROM evento_producto WHERE id_evento_producto = '" + id_evento_producto + "'");
		} else {
			
			ConnectionWrapper.staticExecuteUpdate("DELETE FROM evento_producto_te WHERE id_evento_producto = '" + id_evento_producto + "'");
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

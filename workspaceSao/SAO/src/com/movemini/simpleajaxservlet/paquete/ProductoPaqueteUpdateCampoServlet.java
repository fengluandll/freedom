package com.movemini.simpleajaxservlet.paquete;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movemini.data.ConnectionWrapper;

/**
 * Servlet implementation class ProductoPaqueteUpdateCampoServlet
 */
@WebServlet("/ProductoPaqueteUpdateCampoServlet")
public class ProductoPaqueteUpdateCampoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductoPaqueteUpdateCampoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		


		
		String key = request.getParameter("key");
		String valor = request.getParameter("valor");
		String campo = request.getParameter("campo");
		
		String sql = "UPDATE cat_paquete_producto SET " + campo +" = '" + valor + "' WHERE id_paquete_producto = '" + key + "'";
		
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
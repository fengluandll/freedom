package com.movemini.simpleajaxservlet.productos;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

import com.movemini.data.ConnectionWrapper;
import com.movemini.data.OneValueFactory;
import com.movemini.layers.session.SessionBean;

import com.movemini.debug.DebugTools;

/**
 * Servlet implementation class PoliticasEventoSelectServlet
 */
@WebServlet("/ProductosEventoSelectServlet")
public class ProductosEventoSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductosEventoSelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());

		response.setCharacterEncoding("UTF-8");

		String id_dia = request.getParameter("id_dia");

		ProductosEventoTable bean = new ProductosEventoTable(request);

		String result = bean.getHtml();



		String query_NombreSala = "Select IFNULL(nombre_sala,'') from evento_sala where id_evento_sala = '" + id_dia + "'";

		String nombre = OneValueFactory.get(query_NombreSala);
		DebugTools.println(query_NombreSala,getClass());
		JSONObject obj = new JSONObject();
		obj.put("data", result);
		obj.put("nombre"," - " + nombre + " - ");

		response.getWriter().append(obj.toJSONString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

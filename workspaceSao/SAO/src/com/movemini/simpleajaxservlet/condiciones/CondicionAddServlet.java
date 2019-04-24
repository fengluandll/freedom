package com.movemini.simpleajaxservlet.condiciones;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movemini.data.ConnectionWrapper;

/**
 * Servlet implementation class PoliticaAddServlet
 */
@WebServlet("/CondicionAddServlet")
public class CondicionAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CondicionAddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setCharacterEncoding("UTF-8");


		String id_evento_version = request.getParameter("ID_EVENTO");
		String id_condicion = request.getParameter("id_condicion");


		ConnectionWrapper.staticExecuteUpdate("INSERT INTO evento_condicion (id_evento_version, id_condicion) VALUES ("+id_evento_version+",'" + id_condicion + "')");


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
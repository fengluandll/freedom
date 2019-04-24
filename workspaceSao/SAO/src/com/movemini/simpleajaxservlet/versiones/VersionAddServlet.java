package com.movemini.simpleajaxservlet.versiones;

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
@WebServlet("/VersionAddServlet")
public class VersionAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public VersionAddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id_evento_version = request.getParameter("ID_EVENTO");
		System.out.println(" ----------------------------  ----------------------------------------");
		System.out.println(id_evento_version);

		String idNuevaVersion = OneValueFactory.get("version_insert_pr", id_evento_version);

		String result = "-1";

		if( idNuevaVersion != null ){
				result = idNuevaVersion;
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

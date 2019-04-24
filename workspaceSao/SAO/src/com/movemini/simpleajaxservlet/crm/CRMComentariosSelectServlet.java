package com.movemini.simpleajaxservlet.crm;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movemini.simpleajaxservlet.evento_sala.SalasTable;

/**
 * Servlet implementation class CRMComentariosSelectServlet
 */
@WebServlet("/CRMComentariosSelectServlet")
public class CRMComentariosSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CRMComentariosSelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		response.setCharacterEncoding("UTF-8");
		String idCliente = request.getParameter("id");



		CRMComentariosTable bean = new CRMComentariosTable(request);
		bean.setIdCliente(idCliente);
		bean.setIdEvento(request.getParameter("idEventoComentario"));

		response.getWriter().append(bean.getHtml());



	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

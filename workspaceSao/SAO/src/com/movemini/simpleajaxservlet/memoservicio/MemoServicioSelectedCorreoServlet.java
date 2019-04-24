package com.movemini.simpleajaxservlet.memoservicio;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.movemini.data.OneValueFactory;


import com.movemini.memoservicio.MemoServicio;

/**
 * Servlet implementation class MemoServicioSelectedCorreoServlet
 */
@WebServlet("/MemoServicioSelectedCorreoServlet")
public class MemoServicioSelectedCorreoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemoServicioSelectedCorreoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		String id_evento_version  = request.getParameter("ID_EVENTO");
		String tipoCorreo = request.getParameter("tipoCorreo");
		String idEvento = OneValueFactory.get("select id_evento from evento_version where id_evento_version = "+ id_evento_version);



		MemoServicio memo = new MemoServicio();


		String result = tipoCorreo.equals("1") ? memo.obtenerCorreoContacto(idEvento) : tipoCorreo.equals("2")?memo.obternerCorreosOmnilingua(): memo.obternerCorreosInterpretes(id_evento_version);

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

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
 * Servlet implementation class MemoServicioSelectedNombreEventoServlet
 */
@WebServlet("/MemoServicioSelectedNombreEventoServlet")
public class MemoServicioSelectedNombreEventoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemoServicioSelectedNombreEventoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		String id_evento_version = request.getParameter("ID_EVENTO");
		String idEvento = OneValueFactory.get("select id_evento from evento_version where id_evento_version = "+ id_evento_version);

		MemoServicio memo = new MemoServicio();

		String result = memo.obtenerNombreEvento(idEvento);

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

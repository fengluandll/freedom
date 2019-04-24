package com.movemini.simpleajaxservlet.evento;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movemini.data.ConnectionWrapper;
import com.movemini.data.OneValueFactory;
import com.movemini.layers.session.SessionBean;

/**
 * Servlet implementation class SalaTraduccionEscritaServlet
 */
@WebServlet("/SalaTraduccionEscritaServlet")
public class SalaTraduccionEscritaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SalaTraduccionEscritaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());


		SessionBean sessionBean = SessionBean.getInstance(request);
		String id_evento_version = sessionBean.getIdEventoVersion();


		String id_evento_sala = OneValueFactory.get("SELECT id_evento_sala FROM evento_sala WHERE id_evento_version = " + id_evento_version );


		response.getWriter().append(id_evento_sala);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

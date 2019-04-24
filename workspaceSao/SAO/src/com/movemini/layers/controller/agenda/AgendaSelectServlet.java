package com.movemini.layers.controller.agenda;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movemini.simpleajaxservlet.evento.EventoForm;

/**
 * Servlet implementation class AgendaSelectServlet
 */
@WebServlet("/AgendaSelectServlet")
public class AgendaSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgendaSelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8"); //

		String idCliente = request.getParameter("id_cliente");
		String idEvento = request.getParameter("id_evento");
		String idEstatus = request.getParameter("id_estatus");
		String fecha = request.getParameter("fecha");

		response.getWriter().append(Agenda.getEventsSplit2(idEstatus, idCliente, idEvento, fecha));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package com.movemini.simpleajaxservlet.evento;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movemini.data.ConnectionWrapper;




/**
 * Servlet implementation class EventoCancelacionServlet
 */
@WebServlet("/EventoCancelacionServlet")
public class EventoCancelacionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventoCancelacionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String idEvento = request.getParameter("id_evento");
		String motivo = request.getParameter("motivo");
		String porcentajeInterpretes = request.getParameter("porcentaje_interpretes");
		String porcentajeEquipo = request.getParameter("porcentaje_equipo");
		String porcentajePagoInterpretes = request.getParameter("porcentaje_pago_interpretes");
		
		String sql = "INSERT INTO evento_cancelacion (id_evento_version, motivo, porcentaje_interpretes, porcentaje_equipo, porcentaje_pago_interpretes) VALUES ('" + 
				idEvento + "','" + motivo + "','" + porcentajeInterpretes  + "', '" + porcentajeEquipo + "', '" + porcentajePagoInterpretes + "')";
		
		ConnectionWrapper.staticExecuteUpdate(sql);
		
		
		String respuesta = "OK";
		
		response.getWriter().append(respuesta);
				
				
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

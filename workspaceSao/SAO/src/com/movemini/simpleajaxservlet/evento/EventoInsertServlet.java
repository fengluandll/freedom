package com.movemini.simpleajaxservlet.evento;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movemini.data.ConnectionWrapper;
import com.movemini.data.OneValueFactory;
import com.movemini.data.ProcedureCall;
import com.movemini.data.Record;
import com.movemini.layers.session.SessionBean;

/**
 * Servlet implementation class EventoInsertServlet
 */
@WebServlet("/EventoInsertServlet")
public class EventoInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventoInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setCharacterEncoding("UTF-8");


		//String id_evento = request.getParameter("id_evento");

		String nombre = request.getParameter("nombre");
		//String status = request.getParameter("status");
		String id_cliente = request.getParameter("id_cliente");

		//String id_moneda = request.getParameter("id_moneda");


		//Record myUser = (Record) request.getSession().getAttribute("user");
		Record myUser = SessionBean.getInstance(request).getUser();

        String id_usr = myUser.get("id_usuario");

        String id_tipo_evento = request.getParameter("id_tipo_evento");
        String id_tipo_cotizacion = request.getParameter("id_tipo_cotizacion");






//        String sql = "INSERT INTO evento (nombre, id_cliente, id_evento_status, id_usuario, id_moneda) VALUES ('" +
//				nombre + "','" + id_cliente + "',  1, '" + id_usr + "', '" + id_moneda + "')";


        //
        // TODO: Corregir nombre de campo id_tipo_eveto, en todas las referencias que haya del mismo
        //


        String sql = "INSERT INTO evento (nombre, id_cliente, id_evento_status, id_usuario, id_tipo_eveto, id_tipo_cotizacion) VALUES ('" +
				nombre + "','" + id_cliente + "',  1, '" + id_usr + "', '" + id_tipo_evento + "', '" + id_tipo_cotizacion + "')";




		ConnectionWrapper.staticExecuteUpdate(sql);



		String maxNewId = OneValueFactory.get("SELECT max(id_evento) FROM evento WHERE nombre = '" + nombre + "'");

		String id_evento_version = "";
		if(id_tipo_evento.equals("2")) {

			String id_evento = maxNewId;

			ProcedureCall.call("evento_version_create_if_not_db", id_evento);


			id_evento_version = OneValueFactory.get("SELECT id_evento_version FROM evento_version WHERE id_evento = " + id_evento);

			String sql2 = "INSERT INTO evento_sala (id_evento_version, nombre_sala) VALUES (" + id_evento_version + ",'Traducción Escrita')";

			ConnectionWrapper.staticExecuteUpdate(sql2);

		} else {



			String id_evento = maxNewId;

			ProcedureCall.call("evento_version_create_if_not_db", id_evento);


			id_evento_version = OneValueFactory.get("SELECT id_evento_version FROM evento_version WHERE id_evento = " + id_evento);

			String sql2 = "INSERT INTO evento_sala (id_evento_version, nombre_sala) VALUES (" + id_evento_version + ",'')";

			ConnectionWrapper.staticExecuteUpdate(sql2);




		}

		String sql2 = "update evento set id_ultima_version =" + id_evento_version +" where id_evento = "+maxNewId;
		ConnectionWrapper.staticExecuteUpdate(sql2);
		

		ProcedureCall.call("crearClaveCotizacion", maxNewId);



		//String result = "OK";
		String result = id_evento_version;

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

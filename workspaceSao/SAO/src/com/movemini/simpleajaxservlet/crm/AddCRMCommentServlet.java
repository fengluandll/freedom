package com.movemini.simpleajaxservlet.crm;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movemini.data.ProcedureCall;
import com.movemini.layers.session.SessionBean;

/**
 * Servlet implementation class AddCRMCommentServlet
 */
@WebServlet("/AddCRMCommentServlet")
public class AddCRMCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCRMCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {




		String cliente_id = request.getParameter("id_cliente");
		if(cliente_id == null){
			cliente_id = (String) request.getSession().getAttribute("cliente_id");
		}

		System.out.println(cliente_id);

		String id_user = SessionBean.getInstance(request).getUser().getId();


		String id_semaforo = request.getParameter("id_semaforo");
		String comentario = request.getParameter("comentario");
		String idEvento = request.getParameter("idEventoComentario");

		ProcedureCall.call("crm_cliente_comentarios_insert_pr", cliente_id, id_user, id_semaforo, comentario,idEvento);


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

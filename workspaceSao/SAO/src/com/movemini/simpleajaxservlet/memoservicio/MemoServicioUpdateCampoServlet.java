package com.movemini.simpleajaxservlet.memoservicio;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movemini.memoservicio.MemoServicio;
import com.movemini.tecnico.ManejadorTecnico;

/**
 * Servlet implementation class MemoServicioUpdateCampoServlet
 */
@WebServlet("/MemoServicioUpdateCampoServlet")
public class MemoServicioUpdateCampoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemoServicioUpdateCampoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String key = request.getParameter("key");
		String valor = request.getParameter("valor");
		String campo = request.getParameter("campo");
		
		MemoServicio memo = new MemoServicio();
		memo.actualizarCampoMemoServicio(campo, valor, key);
		
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

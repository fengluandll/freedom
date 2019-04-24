package com.movemini.simpleajaxservlet.evento_sala;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movemini.data.ConnectionWrapper;
import com.movemini.data.OneValueFactory;

/**
 * Servlet implementation class SedeNombreServlet
 */
@WebServlet("/SalaNombreServlet")
public class SalaNombreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SalaNombreServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		
		
		
		String id_sala = request.getParameter("id_sala");
		
		String sql = "SELECT nombre_sala FROM evento_sala WHERE id_evento_sala = '" + id_sala + "'";
		
        String result = OneValueFactory.get(sql);
        
        result = (result != null) ? result : "";
		
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

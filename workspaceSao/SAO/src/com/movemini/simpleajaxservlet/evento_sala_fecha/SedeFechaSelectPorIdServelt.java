package com.movemini.simpleajaxservlet.evento_sala_fecha;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movemini.data.ConnectionWrapper;

/**
 * Servlet implementation class SedeFechaSelectPorIdServelt
 */
@WebServlet("/SedeFechaSelectPorIdServelt")
public class SedeFechaSelectPorIdServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SedeFechaSelectPorIdServelt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		
		
		
//		
//		String id_sede = request.getParameter("id_sede");
//		String respuesta = "OK";
//		
//		String sql = "select * from evento_sede_agenda where id_sede = " + id_sede + " and STR_TO_DATE(fecha, '%Y-%m-%d') = STR_TO_DATE(now(), '%Y-%m-%d')";
//		
//        ConnectionWrapper connect = null;
//        
//        Statement stmt = null;
//        ResultSet resultSet = null;
//        
//        try {
//            connect = new ConnectionWrapper();
//            stmt = connect.createStatement();
//            
//            resultSet = stmt.executeQuery(sql);
//            
//            if (!resultSet.first()) {
//            	respuesta = "SIN AGENDA";
//            }
//        
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        } finally {
//            ConnectionWrapper.closeAll(/*resul, */stmt, connect);
//        }
//        
//        response.getWriter().append(respuesta);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

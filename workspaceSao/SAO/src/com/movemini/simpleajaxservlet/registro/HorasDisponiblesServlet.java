package com.movemini.simpleajaxservlet.registro;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movemini.data.ConnectionWrapper;
import com.movemini.data.DataArray;
import com.movemini.data.Record;
import com.movemini.layers.session.SessionBean;
import com.movemini.simpleajaxservlet.evento_area.EventoAreaTable;
import com.movemini.simpleflexgrid.components.SelectList;

/**
 * Servlet implementation class HorasDisponiblesServlet
 */
@WebServlet("/HorasDisponiblesServlet")
public class HorasDisponiblesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HorasDisponiblesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
//		
//		String dia = request.getParameter("dia");
//		//String sede = (String) request.getSession().getAttribute("sede");
//		String sede = SessionBean.getInstance(request).getSede();
//		
//		
//		ConnectionWrapper conn = null;
//		CallableStatement stmt = null;
//		
//		ResultSet set = null;
//		
//		try {
//			
//			conn = new ConnectionWrapper();
//			
//			stmt = conn.prepareCallProcedure("select_horas_disponibles_pr", dia, sede);
//
//			set = stmt.executeQuery();
//			
//			
//			
//			String selectHTML = SelectList.getListByResultSet("hora", set, "Seleccionar", "", "");
//			
//			
//			
//			/*while(set.next()) {
//				
//				list.add(new Record(set));
//			}*/
//			
//			
//			//return list;
//			
//			
//
//			response.getWriter().append(selectHTML);
//			
//			
//		}catch(Exception ex){
//			
//			//LOG EXCEPTION
//			ex.printStackTrace();
//			
//			
//			
//		} finally {
//			
//			ConnectionWrapper.closeAll(set, stmt, conn);
//			
//		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package com.movemini.simpleforms;

import java.io.IOException;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

import com.movemini.data.ConnectionWrapper;
import com.movemini.data.OneRecordFactory;
import com.movemini.data.Record;

/**
 * Servlet implementation class SimpleFormUpdateFieldServlet
 */
@WebServlet("/SimpleFormUpdateFieldServlet")
public class SimpleFormUpdateFieldServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SimpleFormUpdateFieldServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		

		String field_id = request.getParameter("field_id");
		
		String field_value = request.getParameter("field_value");

		
		
		String id = (String) request.getSession().getAttribute("row_id");
		

		String entityCode = (String) request.getSession().getAttribute("entityCode");
		
	 	Record screenConfig =  OneRecordFactory.getFirstRecord("SELECT * FROM flex_entity WHERE id = " + entityCode);
	 	
	 	
	 	try{
	 	ConnectionWrapper con = new ConnectionWrapper();
	 	
	 	PreparedStatement stmt = con.prepareStatement("UPDATE "+screenConfig.get("table")+" SET "+field_id+" = ? WHERE "+screenConfig.get("column_id") +" = ?");
		
	 	if (field_value != null) {
		 	field_value	= field_value.replaceAll("'","\\'");
	 	}
	 	
		stmt.setString(1, field_value);
		stmt.setString(2, id);
		
		stmt.executeUpdate();
		con.close();
		
		
	 	}catch(Exception ex) {
	 		ex.printStackTrace();
	 	}
		
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

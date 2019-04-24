package com.movemini.reports;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movemini.util.CSVUtil;
import com.movemini.util.DateUtils;
import com.movemini.util.StringUtils;

/**
 * Servlet implementation class ClientesCSVReportServlet
 */
@WebServlet("/ClientesCSVReportServlet")
public class ClientesCSVReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientesCSVReportServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
		response.setCharacterEncoding("UTF-8");
		
		String d = DateUtils.getToday();
		
		//String idPayroll = request.getParameter("idPayroll");
		String dummyParam = "";
		
		response.setContentType("text/csv");
	    response.setHeader("Content-Disposition", "attachment; filename=\"Clientes_SAO_" + d + ".csv\"");
	    try
	    {
	        OutputStream outputStream = response.getOutputStream();
	        
	        String outputResult = CSVUtil.getLinesForCSV("crm_cliente_select_pr", true, ", ", dummyParam);
	        
	        
	        //outputResult = StringUtils.utf8ToLatin1(outputResult);
	        
	        outputStream.write(outputResult.getBytes());
	        outputStream.flush();
	        outputStream.close();
	    }
	    catch(Exception e)
	    {
	        System.out.println(e.toString());
	    }
	    
	    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

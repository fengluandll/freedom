package com.movemini.flexreports;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movemini.simpleajaxservlet.reports.Report2Table;

/**
 * Servlet implementation class ReportGenericTableServlet
 */
@WebServlet("/ReportGenericTableServlet")
public class ReportGenericTableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportGenericTableServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//Argumel 30/05/2018
		
		/*String id_report = request.getParameter("id_report");
	 	if(id_report.equals("11")){
			response.sendRedirect(request.getContextPath() + "/ReportAgendaExcel");
		}*/
	 	
		response.setCharacterEncoding("UTF-8");
		
		ReportGenericTable bean = new ReportGenericTable(request);
		
		String result = bean.getHtml();
		
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

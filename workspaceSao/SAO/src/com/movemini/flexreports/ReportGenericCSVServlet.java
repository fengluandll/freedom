package com.movemini.flexreports;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movemini.data.DataArray;
import com.movemini.data.OneRecordFactory;
import com.movemini.data.Record;
import com.movemini.util.CSVUtil;
import com.movemini.util.DateUtils;

/**
 * Servlet implementation class CSVReportGeneric
 */
@WebServlet("/ReportGenericCSVServlet")
public class ReportGenericCSVServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportGenericCSVServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//response.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("ISO-8859-1");
		
		
		String id_report = request.getParameter("id_report");
		
		Record report = OneRecordFactory.getFirstRecord("select * from flex_report where id_report = " + id_report);
		

		ArrayList<String> paramValues = new ArrayList<String>();
				
				
		ArrayList<Record> paramDefs = DataArray.getArrayList("select * from flex_report_param where id_report = " + id_report);
		
		for (Record paramDef : paramDefs) {
			
			String code =  paramDef.get("code");
			
			paramValues.add(request.getParameter(code));
			
		}
		
		
		
		String[] paramValuesArray = new String[paramValues.size()];
		paramValuesArray = paramValues.toArray(paramValuesArray);
		
		
		
		
		
		String d = DateUtils.getToday();
		
		//String idPayroll = request.getParameter("idPayroll");
		//String dummyParam = "";
		
		response.setContentType("text/csv");
	    response.setHeader("Content-Disposition", "attachment; filename=\"" + report.get("file_name") + "_" + d + ".csv\"");
	    try
	    {
	        OutputStream outputStream = response.getOutputStream();
	        
	        String outputResult = CSVUtil.getLinesForCSV(report.get("stored_procedure"), true, ", ", paramValuesArray);
	        
	        
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

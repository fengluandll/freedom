package com.movemini.simpleajaxservlet.reports;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.movemini.layers.controller.agenda.AgendaExcel;
import com.movemini.layers.controller.agenda.ParameterAgendaBean;

/**
 * Servlet implementation class ReportAgendaExcel
 */
@WebServlet("/ReportAgendaExcel")
public class ReportAgendaExcel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException{
	
		String filePath = System.getProperty("user.dir")+"/"+"AGENDA2018.xlsx";
		ParameterAgendaBean parameterAgendaBean = new ParameterAgendaBean();
		String anio = request.getParameter("param_anio");
		String fecInicio = request.getParameter("param_fecha_ini");
		String fecFin	 = request.getParameter("param_fecha_fin");
		String idCliente	 = request.getParameter("param_id_cliente");
		parameterAgendaBean.setYear(anio);
		parameterAgendaBean.setFechaInicio(fecInicio);
		parameterAgendaBean.setFechaFin(fecFin);
		parameterAgendaBean.setIdCliente(!idCliente.equals("") ? Integer.parseInt(idCliente) : 0);
		
		System.out.println(anio);
        try {
        	AgendaExcel agendaExcel = new AgendaExcel();
			XSSFWorkbook wb = agendaExcel.getReportAsExcel(parameterAgendaBean);

			FileOutputStream fileOut = new FileOutputStream(filePath);
			wb.write(fileOut);
			fileOut.close();

			FileInputStream fin = new FileInputStream(new File(filePath));

			response.setHeader("Pragma", "public");
			response.setHeader("Expires", "0");
			response.setHeader("Cache-Control","must-revalidate, post-check=0, pre-check=0");
			response.setHeader("Cache-Control", "false");
			response.setHeader("Content-Disposition", "attachment;filename=\""+ "AGENDA2018.xlsx" + "\"");
			response.setHeader("Content-Transfer-Encoding", "binary");
			response.setContentType("application/vnd.ms-excel");
			// response.setContentLength(fileInBytes.length);

			BufferedInputStream bis = new BufferedInputStream(fin);

			ServletOutputStream stream = response.getOutputStream();
			int readBytes = 0;

			while ((readBytes = bis.read()) != -1) {		
				stream.write(readBytes);
			}
			
		
		}catch(Exception e){
        	System.out.println("No se pudo encontrar el archivo");
        	response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Reporte de Agenda </title>");
            out.println("</head>");
            out.println("<body bgcolor=\"white\">");
            out.println("<body>");
            out.println("<P>No se pudo generar Reporte en Excel</P>");
            out.println("</body>");
            out.println("</html>");
            e.printStackTrace();
        }
	}
	
	
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);// TODO Auto-generated method stub
	}
	
	

}

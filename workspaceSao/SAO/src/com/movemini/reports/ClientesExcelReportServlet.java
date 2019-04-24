package com.movemini.reports;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ClientesExcelReportServlet
 */
@WebServlet("/ClientesExcelReportServlet")
public class ClientesExcelReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientesExcelReportServlet() {
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
//		ConnectionWrapper conn = null;
//		Statement recordsStmt  = null;
//		//ResultSet recordsSet = null;
//		ResultSet recordsSet2 = null;
//		
		
		try {
//			
//			conn = new ConnectionWrapper();
//			//recordsStmt = conn.createStatement();
//			//recordsSet = recordsStmt.executeQuery("SELECT * FROM reporte WHERE reporte_id = '" + reportId + "'");	
//				
//			recordsSet.next();
//
//			//Nombre de Archivo a Descargar
//			String fileName = recordsSet.getString("file_name");
//
//			String layoutFileName = recordsSet.getString("layout");
//			
//			String query = recordsSet.getString("query");
//			
//			//Parametros
//			recordsSet2 = recordsStmt.executeQuery("SELECT * FROM reporte_params WHERE reporte_id = '" + reportId + "'");
//			while(recordsSet2.next()){
//				String param_name = recordsSet2.getString("nombre_interno");
//				query = query.replace("#" + param_name + "#", request.getParameter(param_name));
//			}
//			
//			int START_ROW = 1;
//			
//			
//			
//			
//			
			
			GenericReportHandler.createReport(request, response, "crm_cliente_select_pr", "NO_APLICA", "Clientes.xlsx", 0);
		    
		} catch (Exception ex) {
			
			
			ex.printStackTrace();
			
			response.setContentType("text/html;charset=UTF-8");
	        PrintWriter out = response.getWriter();
	        
	        out.println(ex.toString());
		} finally {
			
			//Conexion.closeAll(recordsSet2, recordsSet, recordsStmt, conn);
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

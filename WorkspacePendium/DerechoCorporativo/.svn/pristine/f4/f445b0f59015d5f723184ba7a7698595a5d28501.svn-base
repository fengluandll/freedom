package mx.com.televisa.derechocorporativo.modules.reports;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.com.televisa.derechocorporativo.daos.ReportesDAO;
import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * Servlet implementation class ResAprobEjerFisServlet
 */
@WebServlet("/ResAprobEjerFisServlet")
public class ResAprobEjerFisServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ConnectionWrapper conectionWrapper;
	JasperReport jasperReport;
	JasperPrint jasperPrint;
	FacesContext facesContext = FacesContext.getCurrentInstance();
	Connection con;
	private File file;
	HttpSession session;
	Map<String, Object> parametros;

    public void processRequest(HttpServletRequest  tRequest,
            HttpServletResponse tResponse) throws IOException {
        ReportesDAO reportesDAO = new ReportesDAO();

    	System.out.println("Entro ResAprobEjerFisServlet: ");

    	String lstPeriodo = tRequest.getParameter("txtPeriodo");
        String lstPdfExcel = tRequest.getParameter("sPdfExcel");

        boolean lbExecProcessSucc = reportesDAO.execProcess_Rep_RM_AES(1, lstPeriodo);

        //if(lbExecProcessSucc){
			try {
				if(lstPdfExcel.equals("PDF")){
					viewPDF("ResAprobEjerFis", tRequest, tResponse, lstPeriodo);
				}else if(lstPdfExcel.equals("EXCEL")){
					viewExcel("ResAprobEjerFis", tRequest, tResponse, lstPeriodo);
				}
	
			} catch (JRException e) {
			    e.printStackTrace();
			}
        //}

    }
    
	public void viewPDF(String nombreReport,HttpServletRequest  request,
            HttpServletResponse response, String tPeriodo)
            throws IOException, JRException{

			preCargaReporte(nombreReport+".jasper",request,response, tPeriodo);
			response.addHeader("Content-disposition", "attachment;filename="+nombreReport+".pdf");
			OutputStream servletOutputStream = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
  
	}

	private void preCargaReporte(String nomReporte,HttpServletRequest  request,
        HttpServletResponse response, String tPeriodo){
        try {
            conectionWrapper = new ConnectionWrapper();
			parametros = new HashMap<String, Object>();
			con = conectionWrapper.getConnection();
			String reportPath = request.getServletContext().getRealPath("/jasperReports/"+nomReporte);

			System.out.println("************;: "+reportPath);

			file = new File(reportPath);
			jasperReport = (JasperReport)JRLoader.loadObject(file);

            //WEBLOGIG
			String lsRutaJasper = request.getServletContext().getRealPath("jasperReports/");
            parametros.put("val_stfecha", tPeriodo);
			parametros.put("SUBREPORT_DIR", lsRutaJasper);
			System.out.println("lsRutaJasper: "+lsRutaJasper);
			

            //TOMCAT
			/*
			String lsRutaRepDet = "C:\\Users\\ernesto1\\Documents\\ecm\\Eclipse_Luna\\DerechoCorporativo\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\DerechoCorporativo\\jasperReports\\";
			parametros.put("val_stfecha", tPeriodo);
			parametros.put("SUBREPORT_DIR",lsRutaRepDet);
			System.out.println(lsRutaRepDet);
			*/

			

			jasperPrint = JasperFillManager.fillReport(jasperReport, parametros,con);
			} catch (Exception e) {
			    e.printStackTrace();
			} finally{
				try {
	                con.close();
				} catch (SQLException e) {
	                e.printStackTrace();
				}
			}
	}

	public void viewExcel(String nombreReport,HttpServletRequest  request,
                HttpServletResponse response, String tPeriodo)
                throws IOException, JRException{
				preCargaReporte(nombreReport+".jasper",request,response, tPeriodo); 
				response.addHeader("Content-disposition", "attachment;filename="+nombreReport+".xlsx"); 
				OutputStream servletOutputStream = response.getOutputStream();  
				JRXlsxExporter docxExporter=new JRXlsxExporter();  
				docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);  
				docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);  
				docxExporter.exportReport(); 
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
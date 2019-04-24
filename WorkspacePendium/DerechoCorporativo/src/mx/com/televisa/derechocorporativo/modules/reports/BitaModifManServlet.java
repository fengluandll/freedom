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
 * Servlet implementation class BitaModifManServlet
 */
@WebServlet("/BitaModifManServlet")
public class BitaModifManServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ConnectionWrapper conectionWrapper;
	JasperReport jasperReport;
	JasperPrint jasperPrint;
	FacesContext facesContext = FacesContext.getCurrentInstance();
	Connection con;
	private File file;
	HttpSession session;
	Map<String, Object> parametros;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public void processRequest(HttpServletRequest  tRequest,
	                           HttpServletResponse tResponse) throws IOException {

        String lstPdfExcel = tRequest.getParameter("sPdfExcel");
        String lstDesde    = tRequest.getParameter("txDesde");
        String lstHasta    = tRequest.getParameter("txHasta");

		try {
			if(lstPdfExcel.equals("PDF")){
				viewPDF("BitacoraModifMan", tRequest, tResponse, lstDesde, lstHasta);
			}else if(lstPdfExcel.equals("EXCEL")){
				viewExcel("BitacoraModifMan", tRequest, tResponse, lstDesde, lstHasta);
			}

		} catch (JRException e) {
		    e.printStackTrace();
		}

    }

	public void viewPDF(String nombreReport,HttpServletRequest  request,
            HttpServletResponse response,
            String tsFecDesde
            ,String tsHasta)
            throws IOException, JRException{

			preCargaReporte(nombreReport+".jasper",request,response, tsFecDesde, tsHasta);
			response.addHeader("Content-disposition", "attachment;filename="+nombreReport+".pdf");
			OutputStream servletOutputStream = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
  
	}

	private void preCargaReporte(String nomReporte,HttpServletRequest  request,
        HttpServletResponse response,
        String tsFecDesde
        ,String tsHasta){
        try {
            conectionWrapper = new ConnectionWrapper();
			parametros = new HashMap<String, Object>();
			con = conectionWrapper.getConnection();
			String reportPath = request.getServletContext().getRealPath("/jasperReports/"+nomReporte);

			System.out.println("************;: "+reportPath);

			file = new File(reportPath);
			jasperReport = (JasperReport)JRLoader.loadObject(file);
			parametros.put("valStFecDesde", tsFecDesde);
			parametros.put("valStFecHasta", tsHasta);

            //WEBLOGIG
			/*
			String lsRutaJasper = request.getServletContext().getRealPath("jasperReports/");
			parametros.put("SUBREPORT_DIR", lsRutaJasper);
			System.out.println("lsRutaJasper: "+lsRutaJasper);
			*/

			//TOMCAT
			String lsRutaRepDet = "C:\\Users\\ernesto1\\Documents\\ecm\\Eclipse_Luna\\DerechoCorporativo\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\DerechoCorporativo\\jasperReports\\";
			parametros.put("SUBREPORT_DIR",lsRutaRepDet);
			System.out.println(lsRutaRepDet);
			

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
                HttpServletResponse response,
                String tsFecDesde
                ,String tsHasta)
                throws IOException, JRException{

				preCargaReporte(nombreReport+".jasper",request,response, tsFecDesde, tsHasta); 
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
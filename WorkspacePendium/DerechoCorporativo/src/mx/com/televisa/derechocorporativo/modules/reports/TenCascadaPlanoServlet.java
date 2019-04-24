package mx.com.televisa.derechocorporativo.modules.reports;

import java.io.ByteArrayOutputStream;
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

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
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
 * Servlet implementation class TenCascadaPlanoServlet
 */
@WebServlet("/TenCascadaPlanoServlet")
public class TenCascadaPlanoServlet extends HttpServlet {
	
	final static Logger log = Logger.getLogger(TenCascadaPlanoServlet.class);
	
	
	private static final long serialVersionUID = 1L;
	ConnectionWrapper conectionWrapper;
	JasperReport jasperReport;
	JasperPrint jasperPrint;
	FacesContext facesContext = FacesContext.getCurrentInstance();
	Connection con;
	private File file;
	HttpSession session;
	Map<String, Object> parametros;
	String nomEmpresa = "";
	
	public void processRequest(HttpServletRequest request,
	        HttpServletResponse response)throws IOException{
		
		String txtSalida = request.getParameter("psHiFuOut");
		nomEmpresa = request.getParameter("hiddenNomEmpresa");
		
		log.info("Tipo de reporte: "+txtSalida);
		try {
			if(txtSalida.equals("PDF")){
					viewPDF("TenCascPlano", request, response);			
			}else if(txtSalida.equals("EXCEL")){
				viewExcel("TenCascPlano", request, response);	
			//	viewEXCEL("TenCascPlano", request, response);
			}

		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void viewExcel(String nombreReport,HttpServletRequest  request,
            HttpServletResponse response)
            throws IOException, JRException{
			preCargaReporte(nombreReport+".jasper",request,response); 
			response.addHeader("Content-disposition", "attachment;filename="+nombreReport+".xlsx"); 
			OutputStream servletOutputStream = response.getOutputStream();  
			JRXlsxExporter docxExporter=new JRXlsxExporter();  
			docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);  
			docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);  
			docxExporter.exportReport(); 
}
	
	public void viewPDF(String nombreReport,HttpServletRequest  request,
            HttpServletResponse response) 
            throws IOException, JRException{

			preCargaReporte(nombreReport+".jasper",request,response);
			response.addHeader("Content-disposition", "attachment;filename="+nombreReport+".pdf");
			OutputStream servletOutputStream = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
			// FacesContext.getCurrentInstance().responseComplete();  
	}
	
	private void preCargaReporte(String nomReporte,HttpServletRequest  request,
	        HttpServletResponse response){
			
	        try {
	            conectionWrapper = new ConnectionWrapper();
				parametros = new HashMap<String, Object>();
				con = conectionWrapper.getConnection();
				String reportPath = request.getServletContext().getRealPath("/jasperReports/"+nomReporte);
				System.out.println("************;: "+reportPath);
				//System.out.println(request.getServletContext().getRealPath("jasperReports/"));
				file = new File(reportPath);
				jasperReport 			 = (JasperReport)JRLoader.loadObject(file);				
				
				parametros.put("p_nomEmpresa",nomEmpresa);
				//WEBLOGIC
				String lsRutaJasper = request.getServletContext().getRealPath("jasperReports/");
				//parametros.put("SUBREPORT_DIR", lsRutaJasper);
				System.out.println("lsRutaJasper: "+lsRutaJasper);

				//TOMCAT
				/*
				String lsRutaRepDet = "C:\\Users\\ernesto1\\Documents\\ecm\\Eclipse_Luna\\DerechoCorporativo\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\DerechoCorporativo\\jasperReports\\";
				parametros.put("SUBREPORT_DIR",lsRutaRepDet);
				*/

				//System.out.println(lsRutaRepDet);
				//System.out.println(request.getServletContext().getRealPath("jasperReports\\"));
				


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
	
	
	
	
	

	
	
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TenCascadaPlanoServlet() {
        super();
        // TODO Auto-generated constructor stub
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

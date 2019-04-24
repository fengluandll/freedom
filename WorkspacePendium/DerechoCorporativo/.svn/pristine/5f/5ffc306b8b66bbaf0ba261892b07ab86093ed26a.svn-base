package mx.com.televisa.derechocorporativo.servlets;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.CallableStatement;
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

import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;
import mx.com.televisa.derechocorporativo.util.ErrorsUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * Servlet implementation class RepRefMovServlet
 */
@WebServlet("/RepRefMovServlet")
public class RepRefMovServlet extends HttpServlet {
	final static Logger log = Logger.getLogger(RepRefMovServlet.class);
	private static final long serialVersionUID = 1L;
	private ConnectionWrapper conectionWrapper;
	private JasperReport jasperReport;
	private JasperPrint jasperPrint;
	private FacesContext facesContext = FacesContext.getCurrentInstance();
	private Connection con;
	private File file;
	private HttpSession session;
	private Map<String, Object> parametros;   
	private String lstIdCatalogos;
       
    
	public void processRequest(HttpServletRequest tRequest, HttpServletResponse tResponse) throws IOException{
		//String hiddenEmpresa = null;
		log.info("Entro al Servlet**** ");
		String hiddenEmpresa = 	(tRequest.getParameter("hiddenEmpresa") != null || (!tRequest.getParameter("hiddenEmpresa").equals(""))) ? tRequest.getParameter("hiddenEmpresa") : null;
		String paramString    =  "hiddenEmpresa=" + hiddenEmpresa;
		String txtFecSolDesde = tRequest.getParameter("txtFecSolDesde");
		String txtFecSolHasta = tRequest.getParameter("txtFecSolHasta");
		String sPdfExcel      = tRequest.getParameter("sPdfExcel");
		/*
		log.info("txtFecSolDesde**** "+txtFecSolDesde);
		log.info("txtFecSolHasta**** "+txtFecSolHasta);
		log.info("sPdfExcel**********" +sPdfExcel);
		log.info("paramString********" +paramString);*/
		CallableStatement cstm = null;
		ConnectionWrapper conection = null;
		String sql = "";
		if(hiddenEmpresa != null && !hiddenEmpresa.equals("")){
			sql = "where id_empresa in("+hiddenEmpresa+")";
		}else{
			sql = "where 1 = 1";
		}
		try {
			log.info("hiddenEmpresa**** "+hiddenEmpresa);
			log.info("Iniciando conecction wrapper");
			conection = new ConnectionWrapper();
			con = conection.getConnection();
			log.info("Antes del procedure");
			cstm = con.prepareCall("{CALL USRDRC.DERCORP_REPORT_REFORMA_pkg.creaReporteReforma_pr}");
			cstm.execute();
			log.info("Ya se ejecuto el procedure");
		} catch (Exception e1) {
			log.error(ErrorsUtils.getStackTraceAsString(e1));
			e1.printStackTrace();
		}finally{
			try {
				cstm.close();
				con.close();
			} catch (SQLException e) {
				log.error(ErrorsUtils.getStackTraceAsString(e));
				e.printStackTrace();
				
			}
			
		}
		try {
				
				log.info("hiddenEmpresa "+hiddenEmpresa);
				if(sPdfExcel.equals("PDF")){
					viewPDF("reporteReformas", tRequest, tResponse, txtFecSolDesde, txtFecSolHasta,sql);
				}else if(sPdfExcel.equals("EXCEL")){
				viewExcel("reporteReformas", tRequest, tResponse, txtFecSolDesde, txtFecSolHasta,sql);
				}
			
			} catch (JRException e) {
				log.error(ErrorsUtils.getStackTraceAsString(e));
				e.printStackTrace();
			}
		
	}
	
		public void viewPDF(String nombreReport,HttpServletRequest  request,
	            HttpServletResponse response,
	            String tsFecDesde
	            ,String tsHasta
	            ,String empresas)
	            throws IOException, JRException{
			log.info("dentro del viewPDF**** ");
			log.info("tsFecDesde: "+tsFecDesde);
			log.info("tsHasta: "+tsHasta);
			log.info("empresas"+empresas);
	
			preCargaReporte(nombreReport+".jasper",request,response, tsFecDesde, tsHasta,empresas);
			response.addHeader("Content-disposition", "attachment;filename="+nombreReport+".pdf");
			OutputStream servletOutputStream = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
	
	}
	
	private void preCargaReporte(String nomReporte,HttpServletRequest  request,
									HttpServletResponse response,
									String tsFecDesde
									,String tsHasta
									,String empresas){
	
			try {
				conectionWrapper = new ConnectionWrapper();
				parametros = new HashMap<String, Object>();
				con = conectionWrapper.getConnection();
				String reportPath = request.getServletContext().getRealPath("/jasperReports/"+nomReporte);
				
				log.info("************;: "+reportPath);
				
				file = new File(reportPath);
				log.info("file************;: "+file);
				jasperReport = (JasperReport)JRLoader.loadObject(file);
				parametros.put("valStFecSolDesde", tsFecDesde);
				parametros.put("valStFecSolHasta", tsHasta);
				parametros.put("val_idEmpresa", empresas);
				log.info("tsFecDesde************;: "+tsFecDesde);
				log.info("tsHasta************;: "+tsFecDesde);
				log.info("empresas************;: "+tsFecDesde);
			//WEBLOGIG
			/*
			String lsRutaJasper = request.getServletContext().getRealPath("jasperReports/");
			parametros.put("SUBREPORT_DIR", lsRutaJasper);
			log.info("lsRutaJasper: "+lsRutaJasper);
			*/
			
			//TOMCAT
			/*String lsRutaRepDet = "C:\\Users\\ernesto1\\Documents\\ecm\\Eclipse_Luna\\DerechoCorporativo\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\DerechoCorporativo\\jasperReports\\";
			parametros.put("SUBREPORT_DIR",lsRutaRepDet);
			log.info(lsRutaRepDet);*/
			
			log.info("****** jasperPrint");
			jasperPrint = JasperFillManager.fillReport(jasperReport, parametros,con);
			log.info("****** DESPUES jasperPrint");
			} catch (Exception e) {
				log.error(ErrorsUtils.getStackTraceAsString(e));
			    e.printStackTrace();
			} finally{
				try {
			        con.close();
				} catch (SQLException e) {
					log.error(ErrorsUtils.getStackTraceAsString(e));
			        e.printStackTrace();
				}
			}
	}
	
	public void viewExcel(String nombreReport,HttpServletRequest  request,
	    HttpServletResponse response,
	    String tsFecDesde
	    ,String tsHasta
	    ,String empresas)
	    throws IOException, JRException{
	
		preCargaReporte(nombreReport+".jasper",request,response, tsFecDesde, tsHasta,empresas); 
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
		processRequest(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

}

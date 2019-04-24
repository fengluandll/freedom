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
 * Servlet implementation class ReporteControlServlet
 */
@WebServlet("/ReporteControlServlet")
public class ReporteControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ConnectionWrapper conectionWrapper;
	JasperReport jasperReport;
	JasperPrint jasperPrint;
	FacesContext facesContext = FacesContext.getCurrentInstance();
	Connection con;
	private File file;
	HttpSession session;
	Map<String, Object> parametros;
	
	public void processRequest(HttpServletRequest request,
	        HttpServletResponse response)throws IOException{
		
		String lsEmpresas       = request.getParameter("hiddenEmpresa");
		String lsResponsable       = request.getParameter("hiddenResponsable");
		String txtSalida = request.getParameter("psHiFuOut");
		String lstxSolicitadoPor = request.getParameter("hiddenSolicitadoPor");
		String lstxDe 			 = request.getParameter("txDe").trim();				
		String lstxPara 		 = request.getParameter("txPara");
		
	
		//System.out.println(lsEmpresas);
		try {
			if(txtSalida.equals("PDF")){
				if(lsEmpresas.equals("") && lsResponsable.equals("") && lstxSolicitadoPor.equals("") && 
						lstxDe.equals("") && lstxPara.equals("")){
					viewPDF("ReporteControl", request, response,lsEmpresas,lsResponsable);
				}else if(lsEmpresas.indexOf(",") > -1 || lsResponsable.indexOf(",") > -1 || 
						lstxSolicitadoPor.indexOf(",") > -1){
					viewPDF("ReporteControl", request, response,lsEmpresas,lsResponsable);
				}else if((lstxDe.length() > 0 && lstxPara.length() > 0) && lsEmpresas.equals("") && 
						  lsResponsable.equals("") && lstxSolicitadoPor.equals("")){
					viewPDF("ReporteControl", request, response,lsEmpresas,lsResponsable);
				}else//OTRO
				 if((lsResponsable.indexOf(",") == -1) && (lsEmpresas.equals("") && 
						  lstxSolicitadoPor.equals("") && 
						  lstxDe.equals("") && lstxPara.equals(""))){
					viewPDF("ReporteControl", request, response,lsEmpresas,lsResponsable);
				}else if((lstxSolicitadoPor.indexOf(",") == -1) && (lsEmpresas.equals("") && 
						  lsResponsable.equals("") && 
						  lstxDe.equals("") && lstxPara.equals(""))){
					viewPDF("ReporteControl", request, response,lsEmpresas,lsResponsable);
					
				}else if( ( (lsResponsable.indexOf(",") == -1 && !lsResponsable.equals("")) || (lstxSolicitadoPor.indexOf(",") == -1 && !lstxSolicitadoPor.equals("") ) ) && 
						(!lstxDe.equals("") && !lstxPara.equals("")) ){
					viewPDF("ReporteControl", request, response,lsEmpresas,lsResponsable);
				}else{
					viewPDF("ReporteControl2", request, response,lsEmpresas,lsResponsable);
				}
				
			}else if(txtSalida.equals("EXCEL")){
				if(lsEmpresas.equals("") && lsResponsable.equals("") && lstxSolicitadoPor.equals("") && 
						lstxDe.equals("") && lstxPara.equals("")){
					viewPDF("ReporteControl", request, response,lsEmpresas,lsResponsable);
				}else if(lsEmpresas.indexOf(",") > -1 || lsResponsable.indexOf(",") > -1 || 
						lstxSolicitadoPor.indexOf(",") > -1 || (lstxDe.length() > 0 && lstxPara.length() > 0)){
					viewExcel("ReporteControl", request, response,lsEmpresas,lsResponsable);
				}else if((lstxDe.length() > 0 && lstxPara.length() > 0) && !lstxSolicitadoPor.equals("")){
					viewPDF("ReporteControl", request, response,lsEmpresas,lsResponsable);
				
				}else if(!lsResponsable.equals("") && lsEmpresas.equals("") && lstxSolicitadoPor.equals("") && 
						lstxDe.equals("") && lstxPara.equals("")){
					viewPDF("ReporteControl", request, response,lsEmpresas,lsResponsable);
					
				}else{
					viewExcel("ReporteControl2", request, response,lsEmpresas,lsResponsable);
				}
				
			}

		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void viewExcel(String nombreReport,HttpServletRequest  request,
            HttpServletResponse response,String lsEmpresas,String lsResponsable)
            throws IOException, JRException{
			preCargaReporte(nombreReport+".jasper",request,response,lsEmpresas,lsResponsable); 
			response.addHeader("Content-disposition", "attachment;filename="+nombreReport+".xlsx"); 
			OutputStream servletOutputStream = response.getOutputStream();  
			JRXlsxExporter docxExporter=new JRXlsxExporter();  
			docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);  
			docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);  
			docxExporter.exportReport(); 
	}
	
	public void viewPDF(String nombreReport,HttpServletRequest  request,
            HttpServletResponse response,
            String lsEmpresas,String lsResponsable) 
            throws IOException, JRException{

			preCargaReporte(nombreReport+".jasper",request,response,lsEmpresas,lsResponsable);
			response.addHeader("Content-disposition", "attachment;filename="+nombreReport+".pdf");
			OutputStream servletOutputStream = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
			// FacesContext.getCurrentInstance().responseComplete();  
	}
	
	private void preCargaReporte(String nomReporte,HttpServletRequest  request,
	        HttpServletResponse response,
	        String lsEmpresas,String lsResponsable){
			
	        try {
	            conectionWrapper = new ConnectionWrapper();
				parametros = new HashMap<String, Object>();
				con = conectionWrapper.getConnection();
				String reportPath = request.getServletContext().getRealPath("/jasperReports/"+nomReporte);
				String fecCond  = "";
				String solicPor = "";
				System.out.println("************;: "+reportPath);
				//System.out.println(request.getServletContext().getRealPath("jasperReports/"));
				file = new File(reportPath);
				jasperReport 			 = (JasperReport)JRLoader.loadObject(file);
				String lstxDe 			 = request.getParameter("txDe").trim();				
				String lstxPara 		 = request.getParameter("txPara");
				String condicion		 = "";
				String responsable 		 = "";
				String lstxSolicitadoPor = request.getParameter("hiddenSolicitadoPor");
				
				if(lstxDe != null && !lstxDe.equals("") && lstxPara != null && !lstxPara.equals("")){
					fecCond = " AND (vw.VAL_C1 != 'N/A' or vw.VAL_C1 is null)";
					fecCond += "  AND TO_DATE(NVL(VAL_C1,'01/01/1900'),'DD/MM/YYYY') >= to_date('"+lstxDe+"','DD/MM/YYYY') AND TO_DATE(NVL(VAL_C1,'01/01/1900'),'DD/MM/YYYY') <= to_date('"+lstxPara+"','DD/MM/YYYY')";
					System.out.println("fecCond***** "+fecCond);
				}
				if(lstxSolicitadoPor != null && !lstxSolicitadoPor.equals("")){
					solicPor = " and vw.solicitado_por in("+ lstxSolicitadoPor +")";
				}
			
				if(lsEmpresas.length() > 0 && lsResponsable.length() > 0){
					condicion = " AND ID_EMPRESA IN("+lsEmpresas+") AND responsable IN("+lsResponsable+")";
				}else if (lsEmpresas.length() > 0){
					condicion = " AND ID_EMPRESA IN("+lsEmpresas+")";
					
				}else if(lsResponsable.length() > 0){
					responsable = " AND responsable IN("+lsResponsable+")";
				}
				parametros.put("p_responsable",responsable);
				parametros.put("p_condicion", condicion);
				parametros.put("pFecCondition", fecCond);
				parametros.put("pValFecDe", lstxDe);
				parametros.put("pValFecPara", lstxPara);
				parametros.put("psolicitudPor",solicPor);
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
    public ReporteControlServlet() {
        super();
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

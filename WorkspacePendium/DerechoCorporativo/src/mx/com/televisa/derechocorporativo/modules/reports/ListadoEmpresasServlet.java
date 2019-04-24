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
 * Servlet implementation class ListadoEmpresasServlet
 */
@WebServlet("/ListadoEmpresasServlet")
public class ListadoEmpresasServlet extends HttpServlet {
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
		//String que contiene el id de las empresas seleccionadas	
		String lsEmpresas       = request.getParameter("hiddenEmpresa");
		String lsResponsable       = request.getParameter("hiddenResponsable");
		String txtSalida = request.getParameter("psHiFuOut");
	
		//System.out.println(lsEmpresas);
		try {
			if(txtSalida.equals("PDF")){
				viewPDF("ListadoEmpresas", request, response,lsEmpresas,lsResponsable);
			}else if(txtSalida.equals("EXCEL")){
				viewExcel("ListadoEmpresas", request, response,lsEmpresas,lsResponsable);
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
				//String  imagePath=  FacesContext.getCurrentInstance().getExternalContext().getRealPath("/images/logoSCT_hoz3.png");
				System.out.println("************;: "+reportPath);
				//System.out.println(request.getServletContext().getRealPath("jasperReports/"));
				file = new File(reportPath);
				jasperReport = (JasperReport)JRLoader.loadObject(file);
				/*
				parametros.put("p_Nombre",tsName);
				parametros.put("p_Suplente",tsName);
				parametros.put("p_Flex", tsCheck);
				*/
			
				if(lsEmpresas.length() > 0 && lsResponsable.length() > 0){
					parametros.put("ID_EMPRESA", " WHERE ID_EMPRESA IN("+lsEmpresas+") AND atributo3 IN("+lsResponsable+")");
				}else if (lsEmpresas.length() > 0){
					parametros.put("ID_EMPRESA", " WHERE ID_EMPRESA IN("+lsEmpresas+")");
					
				}else if(lsResponsable.length() > 0){
					parametros.put("ID_EMPRESA", " WHERE atributo3 IN("+lsResponsable+")");
				}else{
					parametros.put("ID_EMPRESA", "");
				}

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
    public ListadoEmpresasServlet() {
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

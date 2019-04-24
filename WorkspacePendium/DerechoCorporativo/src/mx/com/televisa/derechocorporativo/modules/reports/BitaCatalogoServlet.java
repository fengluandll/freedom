package mx.com.televisa.derechocorporativo.modules.reports;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
 * Servlet implementation class BitaCatalogo
 */
@WebServlet("/BitaCatalogo")
public class BitaCatalogoServlet extends HttpServlet {
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
    
    public BitaCatalogoServlet() {
        super();
    }

    public void processRequest(HttpServletRequest  tRequest,
            				   HttpServletResponse tResponse) throws IOException {

			String lstPdfExcel 	= tRequest.getParameter("sPdfExcel");
			String lstDesde  	= tRequest.getParameter("txDesde");
			String lstHasta  	= tRequest.getParameter("txHasta");
			String ejecutar  	= tRequest.getParameter("reporte")==null?"":tRequest.getParameter("reporte");
			lstIdCatalogos   	= tRequest.getParameter("idRepHiFun")==null?"":tRequest.getParameter("idRepHiFun");
			String    nomReporte = "";
			
			
			if(ejecutar.equals("bitaEmp")){
				nomReporte = "BitacoraEmpresas";				
			}else{
				String tipoReporte  = tRequest.getParameter("repCatalogo");
				String[] tipos = tipoReporte.split(",");
				List<String> listTipos = Arrays.asList(tipos);
				if(listTipos.contains("1")){
					nomReporte = "BitacoraCatalogo";
				}else if(listTipos.contains("2")){
					nomReporte = "BitacoraCatalogoDelete";
				}else if(listTipos.contains("3")){
					nomReporte = "BitacoraCatagoDetalle";
				}else if(listTipos.contains("4")){
					nomReporte = "BitacoraDetalleDelete";
				}
			}
			
			try {
			if(lstPdfExcel.equals("PDF")){
			//viewPDF(ejecutar.equals("bitaEmp")?"BitacoraEmpresas":"BitacoraCatalogo", tRequest, tResponse, lstDesde, lstHasta);
				viewPDF(nomReporte, tRequest, tResponse, lstDesde, lstHasta);
			}else if(lstPdfExcel.equals("EXCEL")){
			//viewExcel(ejecutar.equals("bitaEmp")?"BitacoraEmpresas":"BitacoraCatalogo", tRequest, tResponse, lstDesde, lstHasta);
				viewExcel(nomReporte, tRequest, tResponse, lstDesde, lstHasta);
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
		String idCatalogos = "";
        try {
            conectionWrapper = new ConnectionWrapper();
			parametros = new HashMap<String, Object>();
			con = conectionWrapper.getConnection();
			String reportPath = request.getServletContext().getRealPath("/jasperReports/"+nomReporte);

			System.out.println("************;: "+reportPath);
			if(!this.getLstIdCatalogos().equals("")){
				idCatalogos = "AND ID_CATALOGO IN ("+this.getLstIdCatalogos()+")";
			}else{
				idCatalogos = "AND ID_CATALOGO = ID_CATALOGO";
			}
			
			file = new File(reportPath);
			jasperReport = (JasperReport)JRLoader.loadObject(file);
			parametros.put("valStFecDesde", tsFecDesde);
			parametros.put("valStFecHasta", tsHasta);
			parametros.put("p_idCatalogos", idCatalogos);
            //WEBLOGIG
			/*
			String lsRutaJasper = request.getServletContext().getRealPath("jasperReports/");
			parametros.put("SUBREPORT_DIR", lsRutaJasper);
			System.out.println("lsRutaJasper: "+lsRutaJasper);
			*/

			//TOMCAT
			/*String lsRutaRepDet = "C:\\Users\\ernesto1\\Documents\\ecm\\Eclipse_Luna\\DerechoCorporativo\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\DerechoCorporativo\\jasperReports\\";
			parametros.put("SUBREPORT_DIR",lsRutaRepDet);
			System.out.println(lsRutaRepDet);*/
			

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
		processRequest(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

	public String getLstIdCatalogos() {
		return lstIdCatalogos;
	}
	
	

}

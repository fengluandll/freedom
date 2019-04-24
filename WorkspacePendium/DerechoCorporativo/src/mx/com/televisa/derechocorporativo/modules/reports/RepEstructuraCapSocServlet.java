package mx.com.televisa.derechocorporativo.modules.reports;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.com.televisa.derechocorporativo.bean.CriterioBusquedaBean;
import mx.com.televisa.derechocorporativo.bean.JReportsBean;
import mx.com.televisa.derechocorporativo.daos.CriterioBusquedaDAO;
import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;
import mx.com.televisa.derechocorporativo.util.FacesUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.apache.commons.lang3.StringUtils;

/**
 * Servlet implementation class RepEstructuraCapSocServlet
 */
@WebServlet("/RepEstructuraCapSocServlet")
public class RepEstructuraCapSocServlet extends HttpServlet {

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
			HttpServletResponse response) throws IOException {

		//response.setContentType("text/html;charset=UTF-8");
		//PrintWriter out = response.getWriter();

		HttpSession session = request.getSession();
		String txtSalida = request.getParameter("txtSalida");

		try {			
			CriterioBusquedaBean busquedaBean = new CriterioBusquedaBean();
			StringBuilder stb = new StringBuilder();
			String lstDenominacion = request.getParameter("denominacion");
			String lstPais = request.getParameter("pai");
			String[] check = request.getParameterValues("chk");
			String lstTextoBuscar = request.getParameter("textBuscar");
			
			busquedaBean.setDenominacion(lstDenominacion);
			
			if (check != null) {
				for (String chk : check) {
					stb.append(chk);
					stb.append(",");
				}
				String clasif = StringUtils.substring(stb.toString(), 0, stb
						.toString().length() - 1);
				busquedaBean.setIdClasificacion(clasif);
				
			} else {
				busquedaBean.setIdClasificacion(null);
			}			
			busquedaBean.setIdPais(Integer.parseInt(lstPais));			
			busquedaBean.setEmpresa(lstTextoBuscar.equals("Empresa") ? null: lstTextoBuscar);			
			String empresasIds = getEmpresasIds(busquedaBean);
			if(txtSalida.equals("PDF")){
				viewPDF("CapitalSocialReport",request,response,empresasIds);
			}else if(txtSalida.equals("EXCEL")){
				viewExcel("CapitalSocialReport",request,response,empresasIds);
			}
			
			
//			out.println("Este Servlet solo muestra los ID de las empresas que cumplen los criterios.");
//
//			out.println(empresasIds);
//			
//			out.println("Hay que agregar aqui un redirect al servlet de Jasper, mandando los Id como parametro");			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public void viewPDF(String nombreReport,HttpServletRequest  request,
			                                HttpServletResponse response,
			                                String empresas) 
			                                throws IOException, JRException{
		preCargaReporte(nombreReport+".jasper",request,response,empresas);
		response.addHeader("Content-disposition", "attachment;filename="+nombreReport+".pdf");
		OutputStream servletOutputStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
		// FacesContext.getCurrentInstance().responseComplete();  
	}
	
	@SuppressWarnings("deprecation")
	public void viewExcel(String nombreReport,HttpServletRequest  request,
            								  HttpServletResponse response,
            								  String empresas) throws IOException, JRException{
		preCargaReporte(nombreReport+".jasper",request,response,empresas); 
		response.addHeader("Content-disposition", "attachment;filename="+nombreReport+".xlsx"); 
		OutputStream servletOutputStream = response.getOutputStream();  
	    JRXlsxExporter docxExporter=new JRXlsxExporter();  
	    docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);  
	    docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);  
	    docxExporter.exportReport(); 
	}
	
	private void preCargaReporte(String nomReporte,HttpServletRequest  request,
            					                   HttpServletResponse response,
            					                   String empresas){
	try {	
		 conectionWrapper = new ConnectionWrapper();
		 parametros = new HashMap<String, Object>();
		 con = conectionWrapper.getConnection();
		 String reportPath = request.getServletContext().getRealPath("/jasperReports/"+nomReporte);
		 //String  imagePath=  FacesContext.getCurrentInstance().getExternalContext().getRealPath("/images/logoSCT_hoz3.png");
		 System.out.println("************ "+reportPath);
		 file = new File(reportPath);
		 jasperReport = (JasperReport)JRLoader.loadObject(file);
		 parametros.put("pinEmpresa"," WHERE id_empresa IN ("+empresas+")");
		 parametros.put("SUBREPORT_DIR",request.getServletContext().getRealPath("jasperReports/"));
		 	
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
	
	public String getEmpresasIds(CriterioBusquedaBean criterioBusquedaBean) {

		try {
			StringBuilder sb = new StringBuilder();
	
			CriterioBusquedaDAO busquedaDAO = new CriterioBusquedaDAO();
			List<CriterioBusquedaBean> listBusqueda = busquedaDAO.dameBusqueda(criterioBusquedaBean);
	
			for (CriterioBusquedaBean cb : listBusqueda) {
				
				sb.append(cb.getIdEmpresa() + ",");
	
	
			}
			
			String ids = sb.toString();
			
			// Quita la ultima coma
			return ids.substring(0, (ids.length() - 1));
		
		}catch(Exception ex) {
		
			ex.printStackTrace();
			
			return "";
			
		}
	}
	

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}

/**
* Project: Derecho Corporativo
*
* File: JReportsBean.java
*
* Created on: Agosto 31, 2015 at 12:00
*
* Copyright (c) - Televisa - 2015
*/

package mx.com.televisa.derechocorporativo.bean;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
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
 * Mapeo de Datos
 *
 * @author KAZ - CONSULTING/
 *         Iván Castañeda Loeza
 *         Jesús Argumedo
 *         Eduardo Nava
 *         Ernesto Corona Mendoza
 *
 *
 *
 * @version 1.0.0
 *
 * @date Agosto 31, 2015 at 12:00 pm
 *
 */
public class JReportsBean {
	ConnectionWrapper conectionWrapper;
	JasperReport jasperReport;
	JasperPrint jasperPrint;
	FacesContext facesContext = FacesContext.getCurrentInstance();
	Connection con;
	private File file;
	HttpSession session;
	Map<String, Object> parametros;

	
	private void init(String nomReporte){
		try {	
		 conectionWrapper = new ConnectionWrapper();
		 parametros = new HashMap<String, Object>();
		 con = conectionWrapper.getConnection();
		 String  reportPath =  FacesContext.getCurrentInstance().getExternalContext().getRealPath("/jasperReports/"+nomReporte); 
		 //String  imagePath=  FacesContext.getCurrentInstance().getExternalContext().getRealPath("/images/logoSCT_hoz3.png");
		 //parametros.put("p_image", imagePath);
		 System.out.println("************ "+reportPath);			 
		 file = new File(reportPath);
		 jasperReport = (JasperReport)JRLoader.loadObject(file);
		 parametros.put("pinEmpresa"," WHERE id_empresa IN (153,66)");
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
	
	public void viewPDF(String nombreReport) throws IOException, JRException{
		init(nombreReport+".jasper");
		HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
		//HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
		//PrintWriter out = response.getWriter();
		
		response.addHeader("Content-disposition", "attachment;filename="+nombreReport+".pdf");
		ServletOutputStream servletOutputStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
		 FacesContext.getCurrentInstance().responseComplete();  
	}
	
	@SuppressWarnings("deprecation")
	public void viewExcel(String nombreReport) throws IOException, JRException{
		init(nombreReport+".jasper");
		HttpServletResponse response=(HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();  
		response.addHeader("Content-disposition", "attachment;filename="+nombreReport+".xlsx"); 
	    ServletOutputStream servletOutputStream=response.getOutputStream();  
	    JRXlsxExporter docxExporter=new JRXlsxExporter();  
	    docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);  
	    docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);  
	    docxExporter.exportReport();  
	    FacesContext.getCurrentInstance().responseComplete();  
	}

}
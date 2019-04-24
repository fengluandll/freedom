/**
 * Copyright Argumedo
 */
package mx.kaz.beans.jasper.reports;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.kaz.daos.ProyectDAO;
import mx.kaz.model.ConectionWrapper;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.http.HttpRequest;
import org.apache.log4j.Logger;

/**
 * @author Argumedo
 *
 */
@ManagedBean(name="dtJReportsBean")
@RequestScoped
public class JReportsBean {
	ConectionWrapper conectionWrapper;
	private org.apache.log4j.Logger logger = Logger.getLogger(JReportsBean.class);
	JasperReport jasperReport;
	JasperPrint jasperPrint;
	FacesContext facesContext = FacesContext.getCurrentInstance();
	Connection con;
	private File file;
	HttpSession session;
	Map<String, Object> parametros;
	ProyectDAO proyectDAO;
	
	private void init(String nomReporte){
		conectionWrapper = new ConectionWrapper();
		proyectDAO = new ProyectDAO();
		session = (HttpSession) facesContext.getExternalContext().getSession(true);
		String nomPto    = (String)session.getAttribute("nomPto");
		String condLassp = (String)session.getAttribute("paramLassp");
		parametros = new HashMap<String, Object>();
		
		try {
			 con = conectionWrapper.getConexion();
			 String  reportPath=  FacesContext.getCurrentInstance().getExternalContext().getRealPath("/jasperReports/"+nomReporte); 
			 String  imagePath=  FacesContext.getCurrentInstance().getExternalContext().getRealPath("/images/logoSCT_hoz3.png");
			 parametros.put("p_image", imagePath);
			 parametros.put("SUBREPORT_DIR",FacesContext.getCurrentInstance().getExternalContext().getRealPath("jasperReports/"));
			 logger.info("************ "+reportPath);			 
			 file = new File(reportPath);
			 jasperReport = (JasperReport)JRLoader.loadObject(file); 
			 if(nomPto != null && nomPto.equals("todos")){
				 parametros.put("p_idPort"," WHERE 1 = 1");
				 jasperPrint = JasperFillManager.fillReport(jasperReport, parametros,con);
//				 session.removeAttribute("nomPto");
				 
			 }else if(nomPto != null && !nomPto.equals("todos")){
				 int idPort = proyectDAO.obtenIdPto(nomPto);
				 parametros.put("p_idPort"," WHERE id_port = "+idPort);
				 jasperPrint = JasperFillManager.fillReport(jasperReport, parametros,con);
//				 session.removeAttribute("nomPto");
				 
			 }else if(condLassp!= null){
				 parametros.put("p_idPort",condLassp);
				 session.removeAttribute("paramLassp");
				 jasperPrint = JasperFillManager.fillReport(jasperReport, parametros,con);
				 
			 }else{
				 jasperPrint = JasperFillManager.fillReport(jasperReport, parametros,con);
			 }
			 
			
		} catch (JRException | NamingException | SQLException e) {
			logger.error(e);
		} finally{
			try {
				con.close();
			} catch (SQLException e) {
				logger.error(e);
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

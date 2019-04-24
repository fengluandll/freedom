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
import mx.com.televisa.derechocorporativo.daos.HistoricoCargosDAO;
import mx.com.televisa.derechocorporativo.util.TextFormatter;

/**
 * Servlet implementation class HistoricoFuncionariosServlet
 *
 * @author KAZ - CONSULTING/ Ivan Castañeda Loeza Jesus Argumedo Eduardo Nava
 *         Ernesto Corona Mendoza
 *
 *
 *
 * @version 1.0.0
 *
 * @date Agosto 31, 2015 at 12:00 pm
 *
 */
@WebServlet("/HistoricoCargosServlet")
public class HistoricoCargosServlet extends HttpServlet {
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

		String lsChecks[] = request.getParameterValues("chNomFun");
		String lsHiFunMultilist = request.getParameter("idRepHiFun");
		String lsIdioma = request.getParameter("sIdioma");
		String lsEmpresas = request.getParameter("hiddenEmpresa");
		String lsEstatus = request.getParameter("estatus");

		System.out.println(lsHiFunMultilist);

		String txtSalida = request.getParameter("psSalida");

		String lsCheck = "";
		String lsCargos = "";

		int liCheck = 0;

		HistoricoCargosDAO luHistoricoCargosDAO = new HistoricoCargosDAO();

		for (String lsChk : lsChecks) {
			if (lsChk.equals("")) {
			} else {
				lsCheck = lsCheck + lsChk + ", ";

			}
		}

		liCheck = lsCheck.lastIndexOf(",");
		lsCheck = lsCheck.substring(0, liCheck);

		System.out.println(lsCheck);

		lsCargos = luHistoricoCargosDAO.getNomFlex(lsCheck);
		System.out.println(lsCargos);

		try {
			if (txtSalida.equals("PDF")) {
				if (lsIdioma.equals("INGLES"))
					viewPDF("AnexoCuestionarioFRepIn", request, response,
							lsHiFunMultilist, lsCheck, lsCargos, lsIdioma,
							lsEmpresas, lsEstatus);
				else if (lsIdioma.equals("ESPAÑOL"))
					viewPDF("AnexoCuestionarioFRepEs", request, response,
							lsHiFunMultilist, lsCheck, lsCargos, lsIdioma,
							lsEmpresas, lsEstatus);

			} else if (txtSalida.equals("EXCEL")) {
				if (lsIdioma.equals("INGLES"))
					viewExcel("AnexoCuestionarioFRepIn", request, response,
							lsHiFunMultilist, lsCheck, lsCargos, lsIdioma,
							lsEmpresas, lsEstatus);
				else if (lsIdioma.equals("ESPAÑOL"))
					viewExcel("AnexoCuestionarioFRepEs", request, response,
							lsHiFunMultilist, lsCheck, lsCargos, lsIdioma,
							lsEmpresas, lsEstatus);
			}

		} catch (JRException e) {
			e.printStackTrace();
		}

	}

	public void viewPDF(String nombreReport, HttpServletRequest request,
			HttpServletResponse response, String empresas, String psCheks,
			String tsCargos, String tsIdioma, String lsEmpresas,
			String lsEstatus) throws IOException, JRException {

		preCargaReporte(nombreReport + ".jasper", request, response, empresas,
				psCheks, tsCargos, tsIdioma, lsEmpresas, lsEstatus);
		response.addHeader("Content-disposition", "attachment;filename="
				+ nombreReport + ".pdf");
		OutputStream servletOutputStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint,
				servletOutputStream);

	}

	private void preCargaReporte(String nomReporte, HttpServletRequest request,
			HttpServletResponse response, String tsName, String tsCheck,
			String tsCargos, String tsIdioma, String lsEmpresas,
			String lsEstatus) {

		String lstxDe = request.getParameter("txDe").trim();
		String nomRep = request.getParameter("txtNomReporte").trim();
		String lstxPara = request.getParameter("txPara");
		
		String fecCond       = "";
		String lsRegCinco    = "";
		String lsMostrarDe   = "";
		String lsMostrarPara = "";
		
		int liRegla5 = request.getParameter("chReg5") == null ? 50000 : 1826;
		System.out.println("Regla5: " + liRegla5);

		//lstxDe = lstxDe.equals("") ? null : lstxDe;
		//lstxPara = lstxPara.equals("") ? null : lstxPara;
		if(lstxDe != null && !lstxDe.equals("") && lstxPara != null && !lstxPara.equals("")){
			lsMostrarDe   = TextFormatter.dayToMonthString(lstxDe);
			lsMostrarPara = TextFormatter.dayToMonthString(lstxPara);
			if(lsEstatus.equals("activo")){
				fecCond = "  AND TO_DATE(NVL(VW.FECHA_DESIGNACION,'01/01/1900'),'DD/MM/YYYY') >= to_date('"+lstxDe+"','DD/MM/YYYY') AND TO_DATE(NVL(VW.FECHA_DESIGNACION,'01/01/1900'),'DD/MM/YYYY') <= to_date('"+lstxPara+"','DD/MM/YYYY')";
			}else if(lsEstatus.equals("inactivo")){
				fecCond = "  AND TO_DATE(NVL(VW.FECHA_BAJA,'01/01/1900'),'DD/MM/YYYY') >= to_date('"+lstxDe+"','DD/MM/YYYY') AND TO_DATE(NVL(VW.FECHA_BAJA,'01/01/1900'),'DD/MM/YYYY') <= to_date('"+lstxPara+"','DD/MM/YYYY')";
			}else if (lsEstatus.equals("Todo")){
				fecCond = " AND ((TO_DATE(NVL(VW.FECHA_DESIGNACION,'01/01/1900'),'DD/MM/YYYY') >= to_date('"+lstxDe+"','DD/MM/YYYY') and TO_DATE(NVL(VW.FECHA_DESIGNACION,'01/01/1900'),'DD/MM/YYYY') <= to_date('"+lstxPara+"','DD/MM/YYYY') OR TO_DATE(NVL(VW.FECHA_BAJA,'01/01/1900'),'DD/MM/YYYY') >= to_date('"+lstxDe+"','DD/MM/YYYY') and TO_DATE(NVL(VW.FECHA_BAJA,'01/01/1900'),'DD/MM/YYYY') <= to_date('"+lstxPara+"','DD/MM/YYYY')) )";	
			}
			
			System.out.println("fecCond***** "+fecCond);
		}
		if(liRegla5 == 1826 && (lstxDe != null && !lstxDe.equals("") ) ){
			//lsRegCinco = " AND ((TO_DATE(NVL(VW.FECHA_DESIGNACION,'01/01/1900'),'DD/MM/YYYY') >= to_date('"+lstxDe+"','DD/MM/YYYY') - 1826 and TO_DATE(NVL(VW.FECHA_DESIGNACION,'01/01/1900'),'DD/MM/YYYY') <= to_date('"+lstxDe+"','DD/MM/YYYY') OR TO_DATE(NVL(VW.FECHA_BAJA,'01/01/1900'),'DD/MM/YYYY') >= to_date('"+lstxDe+"','DD/MM/YYYY') - 1826 and TO_DATE(NVL(VW.FECHA_BAJA,'01/01/1900'),'DD/MM/YYYY') <= to_date('"+lstxDe+"','DD/MM/YYYY')) )";
			//lsRegCinco = " AND (TO_DATE(NVL(VW.FECHA_BAJA,'01/01/1900'),'DD/MM/YYYY') >= to_date('"+lstxDe+"','DD/MM/YYYY') - 1826 and TO_DATE(NVL(VW.FECHA_BAJA,'01/01/1900'),'DD/MM/YYYY') <= to_date('"+lstxDe+"','DD/MM/YYYY')) ";
			lsRegCinco = " AND ( (TO_DATE(NVL(VW.FECHA_BAJA,'01/01/1900'),'DD/MM/YYYY') >= to_date('"+lstxDe+"','DD/MM/YYYY') - 1826 and TO_DATE(NVL(VW.FECHA_BAJA,'01/01/1900'),'DD/MM/YYYY') <= to_date('"+lstxDe+"','DD/MM/YYYY')) or ( VW.FECHA_BAJA is null AND VW.FECHA_DESIGNACION is not null) )";
		}
		System.out.println("Desde:  " + lstxDe);
		System.out.println("Hasta:  " + lstxPara);

		try {
			conectionWrapper = new ConnectionWrapper();
			parametros = new HashMap<String, Object>();
			con = conectionWrapper.getConnection();
			String reportPath = request.getServletContext().getRealPath(
					"/jasperReports/" + nomReporte);

			System.out.println("************;: " + reportPath);

			file = new File(reportPath);
			jasperReport = (JasperReport) JRLoader.loadObject(file);

			if (lsEmpresas.length() > 0) {
				parametros.put("pIdEMpresas", " AND VW.ID_EMPRESA IN("+ lsEmpresas + ")");
			} else {
				parametros.put("pIdEMpresas", "");
			}

			if (lsEstatus.equals("Todo")) {
				parametros.put("pValEstatus", "");

			} else if (lsEstatus.equals("activo")) {
				parametros.put("pValEstatus", " AND VW.FECHA_BAJA is null");
			} else if (lsEstatus.equals("inactivo")) {
				parametros.put("pValEstatus", " AND VW.FECHA_BAJA is not null");
			}

			parametros.put("pValIdFlex", tsCheck);

			if (tsName.length() > 0) {
				parametros.put("pValNombre", " AND PER.PERSON_ID  IN(" + tsName+ ")");
			} else {
				parametros.put("pValNombre", "");
			}
			parametros.put("pFecCondition", fecCond);
			parametros.put("pRegCincoCond", lsRegCinco);
			//parametros.put("pFecCondition", "");
			//parametros.put("pRegCincoCond", "");
			
			parametros.put("pValFecDe", lstxDe);
			parametros.put("pValFecPara", lstxPara);
			parametros.put("pMostrarDe", lsMostrarDe);
			parametros.put("pMostrarPara", lsMostrarPara);
			

			parametros.put("pValReg5", liRegla5);
			parametros.put("pValCargos", tsCargos);
			parametros.put("pValIdioma", tsIdioma);
			parametros.put("pNomRep", nomRep);

			String lsRutaJasper = request.getServletContext().getRealPath(
					"jasperReports/");
			parametros.put("SUBREPORT_DIR", lsRutaJasper);
			System.out.println("WEBLOGIC: " + lsRutaJasper);

			// INI TOMCAT
			/*
			 * String lsRutaRepDet =
			 * "C:\\Users\\ernesto1\\Documents\\ecm\\Eclipse_Luna\\DerechoCorporativo\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\DerechoCorporativo\\jasperReports\\"
			 * ; parametros.put("SUBREPORT_DIR",lsRutaRepDet);
			 * System.out.println("TOMCAT: "+lsRutaRepDet);
			 */
			// FIN

			jasperPrint = JasperFillManager.fillReport(jasperReport,
					parametros, con);
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void viewExcel(String nombreReport, HttpServletRequest request,
			HttpServletResponse response, String tsName, String tsCheck,
			String tsCargos, String tsIdioma, String lsEmpresas,
			String lsEstatus) throws IOException, JRException {

		preCargaReporte(nombreReport + ".jasper", request, response, tsName,
				tsCheck, tsCargos, tsIdioma, lsEmpresas, lsEstatus);
		response.addHeader("Content-disposition", "attachment;filename="
				+ nombreReport + ".xlsx");
		OutputStream servletOutputStream = response.getOutputStream();
		JRXlsxExporter docxExporter = new JRXlsxExporter();
		docxExporter
				.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
				servletOutputStream);
		docxExporter.exportReport();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}

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
import mx.com.televisa.derechocorporativo.util.TextFormatter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * Servlet implementation class HistoricoFuncionariosServlet
 */
@WebServlet("/HistoricoFuncionariosServlet")
public class HistoricoFuncionariosServlet extends HttpServlet {
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
		
		//String lsName = request.getParameter("idHiFunHidd");
		/*
		String lsHiFunMultiList = new String(request.getParameter("idHiFunMultiList").getBytes("ISO-8859-1"),"UTF-8");
		lsHiFunMultiList = TextFormatter.removeAccents(lsHiFunMultiList);
		lsHiFunMultiList = "'"+lsHiFunMultiList+"'";
		*/
		
		//NAVA
		String lsHiFunMultiList = request.getParameter("idHiFunHidd");
		String lsEmpresas       = request.getParameter("hiddenEmpresa");
		String lsEstatus        = request.getParameter("estatus");
		
		
		
		
		
		System.out.println("lsEmpresas: "+lsEmpresas);
		System.out.println("HiFunMultiList: "+lsHiFunMultiList);
		
		String lsChecks[] = request.getParameterValues("chHiFun");
		
		//if(lsName == null || lsChecks == null || lsName.equals("") ||lsChecks.equals("")){

			//response.sendRedirect(request.getContextPath()+"/faces/jsp/reportes/fijos/HistorialFuncionarios.jsp");
		//}else{
			String txtSalida = request.getParameter("psHiFuOut");
			//System.out.println("Salida:  "+txtSalida);
			
			String lsCheck = "";
			
			int liCheck = 0;
/*
			System.out.println("Name:  "+lsName);
			System.out.print("Checks.length:  ");
			System.out.println(lsChecks == null ? 0:lsChecks.length);
*/
			for(String lsChk:lsChecks){
				//System.out.println("Chk:  "+lsChk);
				if(lsCheck != null)
	                lsCheck = lsCheck + lsChk+", ";
				//System.out.println("Check:  "+lsCheck);
			}

			liCheck = lsCheck.lastIndexOf(",");
			//System.out.println("lastIndexOf: "+ liCheck);
			lsCheck = lsCheck.substring(0,liCheck);
			//System.out.println("CadFin:  "+lsCheck);

			//System.out.println("Checks: ");
			//System.out.println(lsCheck);
			//System.out.println(" END Checks");

			try {
				if(txtSalida.equals("PDF")){
					if(!lsEmpresas.contains(",")&&!lsEmpresas.equals("")){
						viewPDF("HFuncionariosEmpresaSingle", request, response, lsHiFunMultiList, lsCheck,lsEmpresas,lsEstatus);
					}else if(!lsHiFunMultiList.contains(",")&&!lsHiFunMultiList.equals("")){
						viewPDF("HFuncionariosPersonaSingle", request, response, lsHiFunMultiList, lsCheck,lsEmpresas,lsEstatus);
					}else{
						viewPDF("HFuncionarios", request, response, lsHiFunMultiList, lsCheck,lsEmpresas,lsEstatus);						
					}
				}else if(txtSalida.equals("EXCEL")){
					if(!lsEmpresas.contains(",")&&!lsEmpresas.equals("")){
						viewExcel("HFuncionariosEmpresaSingle", request, response, lsHiFunMultiList, lsCheck,lsEmpresas,lsEstatus);
					}else if(!lsHiFunMultiList.contains(",")&&!lsHiFunMultiList.equals("")){
						viewExcel("HFuncionariosPersonaSingle", request, response, lsHiFunMultiList, lsCheck,lsEmpresas,lsEstatus);
					}else{
						viewExcel("HFuncionarios", request, response, lsHiFunMultiList, lsCheck,lsEmpresas,lsEstatus);						
					}
					//viewExcel("HFuncionarios", request, response, lsHiFunMultiList, lsCheck,lsEmpresas,lsEstatus);
				}

			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//}
	}

	public void viewPDF(String nombreReport,HttpServletRequest  request,
            HttpServletResponse response,
            String empresas, String psCheks,
            String lsEmpresas, String lsEstatus) 
            throws IOException, JRException{

			preCargaReporte(nombreReport+".jasper",request,response,empresas, psCheks,lsEmpresas,lsEstatus);
			response.addHeader("Content-disposition", "attachment;filename="+nombreReport+".pdf");
			OutputStream servletOutputStream = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
			// FacesContext.getCurrentInstance().responseComplete();  
	}

	private void preCargaReporte(String nomReporte,HttpServletRequest  request,
        HttpServletResponse response,
        String tsName, String tsCheck,
        String lsEmpresas, String lsEstatus){
		
		String lstxDe   = request.getParameter("txFecDeHFun").trim();
		String lstxPara = request.getParameter("txFecParaHFun").trim();

		lstxDe = lstxDe.equals("")?null:lstxDe;
		lstxPara = lstxPara.equals("")?null:lstxPara;

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
			if(lsEstatus.equals("Todo")){
				parametros.put("pValEstatus", "");
				
			}else if(lsEstatus.equals("activo")){
				parametros.put("pValEstatus", " AND VW.FECHA_BAJA is null");
			}else if(lsEstatus.equals("inactivo")){
				parametros.put("pValEstatus", " AND VW.FECHA_BAJA is not null");
			}
			
			parametros.put("pValIdFlex", tsCheck);
			if(tsName.length() > 0){
				parametros.put("pValNombre", " AND PER.PERSON_ID  IN("+ tsName+")");
			}else{
				parametros.put("pValNombre", "");
			}
			
			
			//ECM 29 Marzo 2016 Agregar rango de Fechas
			parametros.put("pFecDesde", lstxDe);
			parametros.put("pFecHasta", lstxPara);
			if(lsEmpresas.length() > 0){
				parametros.put("pIdEMpresas", " AND VW.ID_EMPRESA IN("+lsEmpresas+")");
			}else{
				parametros.put("pIdEMpresas", "");
			}
			
			
			
			System.out.println("HF Desde:  "+lstxDe);
			System.out.println("HF Hasta:  "+lstxPara);
			System.out.println("HF IDFLEX:  "+lstxDe);
			System.out.println("HF IDNOM:  "+lstxPara);



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
	public void viewExcel(String nombreReport,HttpServletRequest  request,
                HttpServletResponse response, String tsName, String tsCheck,String lsEmpresas,String lsEstatus)
                throws IOException, JRException{
				preCargaReporte(nombreReport+".jasper",request,response,tsName, tsCheck,lsEmpresas,lsEstatus); 
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

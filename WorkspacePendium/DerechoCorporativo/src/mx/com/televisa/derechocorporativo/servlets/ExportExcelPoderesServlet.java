package mx.com.televisa.derechocorporativo.servlets;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.com.televisa.derechocorporativo.bean.GenericBean;
import mx.com.televisa.derechocorporativo.bean.GenericDataBean;
import mx.com.televisa.derechocorporativo.daos.MngDataPoderes;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

@WebServlet(description = "Exportar a Excel Rportes de Apoderados", urlPatterns = { "/ExportExcelPoderesServlet" })
public class ExportExcelPoderesServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(ExportExcelPoderesServlet.class);
	
	public ExportExcelPoderesServlet() {
	        super();
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
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	
	public void processRequest(HttpServletRequest request,HttpServletResponse response) throws IOException{
		this.request = request;
		this.response = response;		        
        String action = "";
        
         try
         {
            action = request.getParameter("action");
            String hiddenEmpresa = 	(request.getParameter("hiddenEmpresa") != null) ? request.getParameter("hiddenEmpresa") : "";
    		String txtTipoPoder = 	(request.getParameter("txtTipoPoder") != null) ? request.getParameter("txtTipoPoder") : "";    		    		    		
    		String hiddenApoderados = (request.getParameter("hiddenApoderados") != null) ? request.getParameter("hiddenApoderados") : "";    		
    		String hiddenPoder = (request.getParameter("hiddenPoder") != null) ? request.getParameter("hiddenPoder") : "";
    		String txtEscritura = (request.getParameter("txtEscritura") != null) ? request.getParameter("txtEscritura") : "";    	
    		
    		String txtApoderados = (request.getParameter("txtApoderados") != null) ? request.getParameter("txtApoderados") : "";
    		
    		String apoderados = txtApoderados.replace("</li><li>","%' OR PENDIUM_OTORGAPODER_EP_TAB.DESC_APODERADOS LIKE '%");
    		apoderados = apoderados.replace("<ul><li>","PENDIUM_OTORGAPODER_EP_TAB.DESC_APODERADOS LIKE '%");
    		apoderados = apoderados.replace("</li></ul>","%'");
    		apoderados = apoderados.equals("<ul></ul>") ? null : apoderados;	
    		
            switch(action){                          	
            	case "generarExcelPoderesPorApoderado": 
            		generarExcelPoderesPorApoderado(hiddenEmpresa, txtTipoPoder, hiddenApoderados, hiddenPoder, txtEscritura);
            	break;   
            	case "generarExcelPoderesPorEmpresa":
            		generarExcelPoderesPorEmpresa(hiddenEmpresa, txtTipoPoder, apoderados, hiddenPoder, txtEscritura);
            	break;
            	case "generarExcelPoderesPorTipoPoder":
            		generarExcelPoderesPorTipoPoder(hiddenEmpresa, txtTipoPoder, apoderados, hiddenPoder, txtEscritura);
            		break;
            	case "generarExcelPoderesPorEscritura":
            		generarExcelPoderesPorEscritura(hiddenEmpresa, txtTipoPoder, apoderados, hiddenPoder, txtEscritura);
            	break;
            	case "generarExcelPoderesPorAsunto":
            		String hiddenGrupoApoderados = (request.getParameter("hiddenGrupoApoderados") != null) ? request.getParameter("hiddenGrupoApoderados") : "";
            		generarExcelPoderesPorAsunto(hiddenEmpresa, txtTipoPoder, hiddenApoderados, hiddenGrupoApoderados, hiddenPoder, txtEscritura);
            		break;
            	case "generarExcelPoderesPorFacultades":  
            		String txtFacultades = (request.getParameter("txtFacultades") != null) ? request.getParameter("txtFacultades") : "";
            		String filterAD = null;
            		String filterAA = null;
            		String filterTC = null;
            		String filterPC = null;
            		if(txtFacultades.contains("Actos de Dominio"))
            			filterAD = "AD";			            		            		
            		if(txtFacultades.contains("Actos de Administracion"))
            			filterAA = "AA";			            		            	
            		if(txtFacultades.contains("Titulos de Credito"))
            			filterTC = "TC";			            		            		
            		if(txtFacultades.contains("Pleitos y Cobranzas"))
            			filterPC = "PC";		            		
            		generarExcelPoderesPorFacultades(hiddenEmpresa, txtTipoPoder, apoderados, hiddenPoder, txtEscritura, filterAD, filterAA, filterTC, filterPC);
            		break;
            	case "generarExcelPoderesPorVigencia":  
            		String txtFechaDesde = 	(request.getParameter("txtFechaDesde") != null) ? request.getParameter("txtFechaDesde") : "";
            		String txtFechaHasta = 	(request.getParameter("txtFechaHasta") != null) ? request.getParameter("txtFechaHasta") : "";            		            		
            		generarExcelPoderesPorVigencia(hiddenEmpresa, txtFechaDesde, txtFechaHasta, txtTipoPoder, apoderados, hiddenPoder, txtEscritura);
            		break;
            }
         }catch(Exception ex){ 	         	
        	 logger.error(ex.getMessage());        
         }             
    }
	
	public void generarExcelPoderesPorApoderado(String hiddenEmpresa, String txtTipoPoder, String hiddenApoderados, 
			String hiddenPoder, String txtEscritura){
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet();
		Map<String, CellStyle> styles = createStyles(wb);
		String ApoderadoCurrent = "";
		String EmpresaCurrent = "";
		String TipoPoderCurrent = "";
		GenericDataBean gdb = new GenericDataBean();
		
		String tipoPoder = txtTipoPoder.replace("<ul><li>", "'");
		tipoPoder = tipoPoder.replace("</li><li>","','");
		tipoPoder = tipoPoder.replace("</li></ul>", "'");
		tipoPoder = tipoPoder.equals("<ul></ul>") ? null : tipoPoder;
		tipoPoder = tipoPoder.replace("Carta Poder","CP").replace("Poder Especial","PE").replace("Poder General","PG");
		String escritura = txtEscritura.equals("") ? "" : "'%"+txtEscritura+"%'";		
		int numeroFila = 0;
		
		try {
			gdb = MngDataPoderes.query_PODERES_POR_APODERADOS(hiddenEmpresa, tipoPoder, hiddenApoderados, hiddenPoder, escritura);
		} catch (Exception ex) {
			 logger.error(ex.getMessage());        
		}
		
		for(int i=0; i < gdb.beans.size(); i++){
			GenericBean bean = gdb.get(i);
			
			String Apoderado = bean.getProperty("DESC_NOM_EMPL").toString();												
			String Empresa = bean.getProperty("NOM_EMPRESA").toString();
			String TipoPoder = bean.getProperty("IND_TIPO_ESCRITURA").toString();
			
			if(!ApoderadoCurrent.equals(Apoderado)){
				if(i>0){
					sheet.createRow((short) i+numeroFila).createCell(0).setCellValue("");
					numeroFila++;
					sheet.createRow((short) i+numeroFila).createCell(0).setCellValue("");
					numeroFila++;
				}
				 
				HSSFRow nombreApoderadoRow = sheet.createRow((short) i+numeroFila);
				Cell cell = nombreApoderadoRow.createCell(0);
				cell.setCellValue(Apoderado);
				cell.setCellStyle(styles.get("fondoAzul"));
				sheet.addMergedRegion(new CellRangeAddress(i+numeroFila, i+numeroFila,0,8));
				numeroFila++;
				ApoderadoCurrent = Apoderado;	
				EmpresaCurrent = "";							
			}
			
			if(!EmpresaCurrent.equals(Empresa)){				
				HSSFRow nombreEmpresaRow = sheet.createRow((short) i+numeroFila);
				Cell cell = nombreEmpresaRow.createCell(0);
				cell.setCellValue(Empresa);		
				cell.setCellStyle(styles.get("fondoAzul"));
				sheet.addMergedRegion(new CellRangeAddress(i+numeroFila, i+numeroFila,0,8));
				numeroFila++;
				EmpresaCurrent = Empresa;	
				TipoPoderCurrent = "";							
				
			}
			
			if(!TipoPoderCurrent.equals(TipoPoder)){	
				numeroFila++;
				HSSFRow tipoPoderRow = sheet.createRow((short) i+numeroFila);
				Cell tipoPoderCell = tipoPoderRow.createCell(0);
				tipoPoderCell.setCellValue((TipoPoder.equals("PG") ? "Poderes Generales" : TipoPoder.equals("PE") ? "Poderes Especiales" : TipoPoder.equals("CP") ? "Cartas Poder" : "Escrituras de Revocaci�n"));
				tipoPoderCell.setCellStyle(styles.get("negrita"));		
				TipoPoderCurrent = TipoPoder;	
			}				
			
			numeroFila++;
			HSSFRow tableHeaderRow = sheet.createRow((short) i+numeroFila);
			switch(TipoPoder){
			case "PG": 					
				tableHeaderRow.createCell(0).setCellValue("Escritura No.");											
				tableHeaderRow.createCell(1).setCellValue("Fecha");										
				tableHeaderRow.createCell(2).setCellValue("Poder");									
				tableHeaderRow.createCell(3).setCellValue("Actos de Dominio");										
				tableHeaderRow.createCell(4).setCellValue("Actos de Administraci�n");									
				tableHeaderRow.createCell(5).setCellValue("T�tulos de Cr�dito");										
				tableHeaderRow.createCell(6).setCellValue("Pleitos y Cobranzas");	
				tableHeaderRow.createCell(7).setCellValue("Especial");
				tableHeaderRow.createCell(8).setCellValue("Vigencia");							
				break;
			case "CP": 																			
				tableHeaderRow.createCell(0).setCellValue("Fecha");										
				tableHeaderRow.createCell(1).setCellValue("Descripci�n");										
				tableHeaderRow.createCell(2).setCellValue("Vigencia");					
				break;
			case "PE": 					
				tableHeaderRow.createCell(0).setCellValue("Escritura No.");										
				tableHeaderRow.createCell(1).setCellValue("Fecha");										
				tableHeaderRow.createCell(2).setCellValue("Descripci�n");										
				tableHeaderRow.createCell(3).setCellValue("Vigencia");					
				break;
			}
			numeroFila++;
			Iterator<Cell> tableHeaderIterator = tableHeaderRow.cellIterator();
			while(tableHeaderIterator.hasNext()){
				tableHeaderIterator.next().setCellStyle(styles.get("fondoGris"));
			}
			
				String esc = bean.getProperty("DES_ESCRITURA") == null ? "" : bean.getProperty("DES_ESCRITURA") == null ? "" : bean.getProperty("DES_ESCRITURA").toString();
				String fec = bean.getProperty("FEC_OTORGAMIENTO_INSTR") == null ? "" : bean.getProperty("FEC_OTORGAMIENTO_INSTR").toString();
				String fecCP = bean.getProperty("FEC_FECHA") == null ? "" : bean.getProperty("FEC_FECHA").toString();
				String pod = bean.getProperty("DES_PODERTIPO") == null ? "" : bean.getProperty("DES_PODERTIPO").toString();				
				String ad = bean.getProperty("DESC_ACTOSDOMINIO") == null ? "" : bean.getProperty("DESC_ACTOSDOMINIO").toString();
				ad = removeHTMLTags(ad);
				String aa = bean.getProperty("DESC_ACTOSADMON") == null ? "" : bean.getProperty("DESC_ACTOSADMON").toString();
				aa = removeHTMLTags(aa);
				String tc = bean.getProperty("DESC_TITULOSCREDITO") == null ? "" : bean.getProperty("DESC_TITULOSCREDITO").toString();
				tc = removeHTMLTags(tc);
				String pc = bean.getProperty("DESC_PLEITOSCOBRANZA") == null ? "" : bean.getProperty("DESC_PLEITOSCOBRANZA").toString();
				pc = removeHTMLTags(pc);
				String vig = bean.getProperty("FEC_VIGENCIAFIN") == null ? "" : bean.getProperty("FEC_VIGENCIAFIN").toString();
				String desc = bean.getProperty("DES_PODER") == null ? "" : bean.getProperty("DES_PODER").toString();
				desc = removeHTMLTags(desc);
				String descEspecial = bean.getProperty("DESC_PODER_ESPECIAL") == null ? "" : bean.getProperty("DESC_PODER_ESPECIAL").toString();
				descEspecial = removeHTMLTags(descEspecial);
				
				HSSFRow dataRow = sheet.createRow((short) i+numeroFila); 								
				switch(TipoPoder){
				case "PG": 					
					dataRow.createCell(0).setCellValue(esc);					
					dataRow.createCell(1).setCellValue(fec);					
					dataRow.createCell(2).setCellValue(pod);					
					dataRow.createCell(3).setCellValue(ad);					
					dataRow.createCell(4).setCellValue(aa);					
					dataRow.createCell(5).setCellValue(tc);					
					dataRow.createCell(6).setCellValue(pc);
					dataRow.createCell(7).setCellValue(descEspecial);		
					dataRow.createCell(8).setCellValue(vig);							
					break;
				case "CP": 														
					dataRow.createCell(0).setCellValue(fecCP);					
					dataRow.createCell(1).setCellValue(desc);					
					dataRow.createCell(2).setCellValue(vig);						
					break;
				case "PE": 				
					dataRow.createCell(0).setCellValue(esc);					
					dataRow.createCell(1).setCellValue(fec);					
					dataRow.createCell(2).setCellValue(desc);					
					dataRow.createCell(3).setCellValue(vig);					
					break;
				}					
				
				Iterator<Cell> dataRowIterator = dataRow.cellIterator();
				while(dataRowIterator.hasNext()){
					dataRowIterator.next().setCellStyle(styles.get("bordeDelgado"));
				}
				
				String iRevoca = bean.getProperty("IND_APREVOCA") == null ? null : bean.getProperty("IND_APREVOCA").toString();
				if(iRevoca != null){
					numeroFila++;
					dataRow = sheet.createRow((short) i+numeroFila); 
					dataRow.createCell(0).setCellValue(removeHTMLTags(bean.getProperty("DESC_REVOCA").toString()));
				}
				
				
				sheet.autoSizeColumn(0);
				sheet.autoSizeColumn(1);
				sheet.autoSizeColumn(2);
				sheet.autoSizeColumn(3);
				sheet.autoSizeColumn(4);
				sheet.autoSizeColumn(5);
				sheet.autoSizeColumn(6);
				sheet.autoSizeColumn(7);
				sheet.autoSizeColumn(8);
			}			
		
		downloadExcel(wb, "ReportePoderesPorApoderado");
	}
		
	public void generarExcelPoderesPorEmpresa(String hiddenEmpresa, String txtTipoPoder, String apoderados, 
			String hiddenPoder, String txtEscritura){
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet();
		Map<String, CellStyle> styles = createStyles(wb);		
		String EmpresaCurrent = "";
		String TipoPoderCurrent = "";
		GenericDataBean gdb = new GenericDataBean();
		try {					
			String tipoPoder = txtTipoPoder.replace("<ul><li>", "'");
			tipoPoder = tipoPoder.replace("</li><li>","','");
			tipoPoder = tipoPoder.replace("</li></ul>", "'");
			tipoPoder = tipoPoder.equals("<ul></ul>") ? null : tipoPoder;
			tipoPoder = tipoPoder.replace("Carta Poder","CP").replace("Poder Especial","PE").replace("Poder General","PG");
			String escritura = txtEscritura.equals("") ? "" : "'%"+txtEscritura+"%'";
			
			gdb = MngDataPoderes.query_PODERES_POR_EMPRESA(hiddenEmpresa, tipoPoder, apoderados, hiddenPoder, escritura);
		} catch (Exception ex) {
			 logger.error(ex.getMessage());        
		}
		
		int numeroFila = 0;
		for(int i=0; i < gdb.beans.size(); i++){
			GenericBean bean = gdb.get(i);
																	
			String Empresa = bean.getProperty("NOM_EMPRESA").toString();
			String TipoPoder = bean.getProperty("IND_TIPO_ESCRITURA").toString();
									
			if(!EmpresaCurrent.equals(Empresa)){				
				if(i>0){
					numeroFila++;
					sheet.createRow((short) i+numeroFila).createCell(0).setCellValue("");
					numeroFila++;
					sheet.createRow((short) i+numeroFila).createCell(0).setCellValue("");					
				}
				
				HSSFRow nombreEmpresaRow = sheet.createRow((short) i+numeroFila);
				Cell cell = nombreEmpresaRow.createCell(0);
				cell.setCellValue(Empresa);		
				cell.setCellStyle(styles.get("fondoAzul"));
				sheet.addMergedRegion(new CellRangeAddress(i+numeroFila, i+numeroFila,0,9));
				numeroFila++;
				EmpresaCurrent = Empresa;	
				TipoPoderCurrent = "";							
				
			}
			
			if(!TipoPoderCurrent.equals(TipoPoder)){	
				numeroFila++;
				HSSFRow tipoPoderRow = sheet.createRow((short) i+numeroFila);
				Cell tipoPoderCell = tipoPoderRow.createCell(0);
				tipoPoderCell.setCellValue((TipoPoder.equals("PG") ? "Poderes Generales" : TipoPoder.equals("PE") ? "Poderes Especiales" : TipoPoder.equals("CP") ? "Cartas Poder" : "Escrituras de Revocaci�n"));
				tipoPoderCell.setCellStyle(styles.get("negrita"));				
				TipoPoderCurrent = TipoPoder;	
			}				
			
			numeroFila++;
			HSSFRow tableHeaderRow = sheet.createRow((short) i+numeroFila);
			switch(TipoPoder){
			case "PG": 					
				tableHeaderRow.createCell(0).setCellValue("Escritura No.");											
				tableHeaderRow.createCell(1).setCellValue("Fecha");										
				tableHeaderRow.createCell(2).setCellValue("Apoderados");
				tableHeaderRow.createCell(3).setCellValue("Tipo Poder");
				tableHeaderRow.createCell(4).setCellValue("Actos de Dominio");										
				tableHeaderRow.createCell(5).setCellValue("Actos de Administraci�n");									
				tableHeaderRow.createCell(6).setCellValue("T�tulos de Cr�dito");										
				tableHeaderRow.createCell(7).setCellValue("Pleitos y Cobranzas");
				tableHeaderRow.createCell(8).setCellValue("Especial");
				tableHeaderRow.createCell(9).setCellValue("Vigencia");							
				break;
			case "CP": 																	
				tableHeaderRow.createCell(0).setCellValue("Fecha");		
				tableHeaderRow.createCell(1).setCellValue("Tipo Poder");
				tableHeaderRow.createCell(2).setCellValue("Apoderados");					
				tableHeaderRow.createCell(3).setCellValue("Descripci�n");										
				tableHeaderRow.createCell(4).setCellValue("Vigencia");					
				break;
			case "PE": 					
				tableHeaderRow.createCell(0).setCellValue("Escritura No.");										
				tableHeaderRow.createCell(1).setCellValue("Fecha");		
				tableHeaderRow.createCell(2).setCellValue("Tipo Poder");
				tableHeaderRow.createCell(3).setCellValue("Apoderados");		
				tableHeaderRow.createCell(4).setCellValue("Descripci�n");										
				tableHeaderRow.createCell(5).setCellValue("Vigencia");				
				break;
			}
			
			Iterator<Cell> tableHeaderIterator = tableHeaderRow.cellIterator();
			while(tableHeaderIterator.hasNext()){
				tableHeaderIterator.next().setCellStyle(styles.get("fondoGris"));
			}
			
			numeroFila++;
			
				String esc = bean.getProperty("DES_ESCRITURA") == null ? "" : bean.getProperty("DES_ESCRITURA") == null ? "" : bean.getProperty("DES_ESCRITURA").toString();
				String fec = bean.getProperty("FEC_OTORGAMIENTO_INSTR") == null ? "" : bean.getProperty("FEC_OTORGAMIENTO_INSTR").toString();
				String fecCP = bean.getProperty("FEC_FECHA") == null ? "" : bean.getProperty("FEC_FECHA").toString();
				String apod = bean.getProperty("DESC_APODERADOS") == null ? "" : bean.getProperty("DESC_APODERADOS").toString();			
				apod = removeHTMLTags(apod);
				String ad = bean.getProperty("DESC_ACTOSDOMINIO") == null ? "" : bean.getProperty("DESC_ACTOSDOMINIO").toString();
				ad = removeHTMLTags(ad);
				String aa = bean.getProperty("DESC_ACTOSADMON") == null ? "" : bean.getProperty("DESC_ACTOSADMON").toString();
				aa = removeHTMLTags(aa);
				String tc = bean.getProperty("DESC_TITULOSCREDITO") == null ? "" : bean.getProperty("DESC_TITULOSCREDITO").toString();
				tc = removeHTMLTags(tc);
				String pc = bean.getProperty("DESC_PLEITOSCOBRANZA") == null ? "" : bean.getProperty("DESC_PLEITOSCOBRANZA").toString();
				pc = removeHTMLTags(pc);
				String vig = bean.getProperty("FEC_VIGENCIAFIN") == null ? "" : bean.getProperty("FEC_VIGENCIAFIN").toString();
				String desc = bean.getProperty("DES_PODER") == null ? "" : bean.getProperty("DES_PODER").toString();
				String pod = bean.getProperty("DES_PODERTIPO") == null ? "" : bean.getProperty("DES_PODERTIPO").toString();
				desc = removeHTMLTags(desc);
				String descEspecial = bean.getProperty("DESC_PODER_ESPECIAL") == null ? "" : bean.getProperty("DESC_PODER_ESPECIAL").toString();
				descEspecial = removeHTMLTags(descEspecial);
				
				int idOtorgaPoder = Integer.valueOf(bean.getProperty("ID_OPODER_EP_PK").toString());
				String apendiceRevocados = "";				
				GenericDataBean gdbRevocaciones = MngDataPoderes.query_REVOCACIONES(idOtorgaPoder);
				for(int j=0; j < gdbRevocaciones.beans.size(); j++){
					GenericBean beanRevocacion = gdbRevocaciones.get(j);	
					String apendiceRevocadoCurrent = "";								
					  apendiceRevocadoCurrent = beanRevocacion.getProperty("DESC_REVOCA") == null ? "" : beanRevocacion.getProperty("DESC_REVOCA").toString(); 
					  if(!apendiceRevocados.contains(apendiceRevocadoCurrent)){						
						  apendiceRevocados += apendiceRevocadoCurrent.trim().length()>0 ? apendiceRevocadoCurrent + "\n" : "";	
					  }									  
				}
								
				HSSFRow dataRow = sheet.createRow((short) i+numeroFila); 								
				switch(TipoPoder){
				case "PG": 					
					dataRow.createCell(0).setCellValue(esc);					
					dataRow.createCell(1).setCellValue(fec);					
					dataRow.createCell(2).setCellValue(apod);
					dataRow.createCell(3).setCellValue(pod);									
					dataRow.createCell(4).setCellValue(ad);					
					dataRow.createCell(5).setCellValue(aa);					
					dataRow.createCell(6).setCellValue(tc);					
					dataRow.createCell(7).setCellValue(pc);					
					dataRow.createCell(8).setCellValue(descEspecial);	
					dataRow.createCell(9).setCellValue(vig);
					break;
				case "CP": 															
					dataRow.createCell(0).setCellValue(fecCP);
					dataRow.createCell(1).setCellValue(pod);
					dataRow.createCell(2).setCellValue(apod);
					dataRow.createCell(3).setCellValue(desc);					
					dataRow.createCell(4).setCellValue(vig);						
					break;
				case "PE": 				
					dataRow.createCell(0).setCellValue(esc);					
					dataRow.createCell(1).setCellValue(fec);
					dataRow.createCell(2).setCellValue(pod);
					dataRow.createCell(3).setCellValue(apod);
					dataRow.createCell(4).setCellValue(desc);					
					dataRow.createCell(5).setCellValue(vig);						
					break;
				}					
				
				Iterator<Cell> dataRowIterator = dataRow.cellIterator();
				while(dataRowIterator.hasNext()){
					dataRowIterator.next().setCellStyle(styles.get("bordeDelgado"));
				}
				
				if(apendiceRevocados.length()>0){
					numeroFila++;
					dataRow = sheet.createRow((short) i+numeroFila); 
					dataRow.createCell(0).setCellValue(removeHTMLTags(apendiceRevocados));
				}
								
				sheet.autoSizeColumn(0);
				sheet.autoSizeColumn(1);
				sheet.autoSizeColumn(2);
				sheet.autoSizeColumn(3);
				sheet.autoSizeColumn(4);
				sheet.autoSizeColumn(5);
				sheet.autoSizeColumn(6);
				sheet.autoSizeColumn(7);
				sheet.autoSizeColumn(8);
				sheet.autoSizeColumn(9);
												
			}			
		
		downloadExcel(wb, "ReportePoderesPorEmpresa");
	}
	
	public void generarExcelPoderesPorEscritura(String hiddenEmpresa, String txtTipoPoder, String apoderados, 
			String hiddenPoder, String txtEscritura){
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet();
		Map<String, CellStyle> styles = createStyles(wb);						
		String escritura = "";
		String fechaOtorgamiento = "";
		String delegadoPor = "";
		String fecha = "";
		String hora = "";
		String escrituraDigitalizada = "";
		String nombreLicenciado = "";
		String notarioPublico = "";
		String deCiudad = "";
		String suplenciaAsociado = "";
		String registroPublico = "";
		String fechaRegistro = "";
		String folioMercantil = "";
		String otrosDatosRegistro = "";
		String solicitud = "";
		String solicitadoPor = "";
		String fechaSolicitud = "";
		String fechaRecibido = "";
		String folio = "";
		String documentoEntrega = "";
		String fechaDocumento = "";
		String fechaEntregado = "";
		String tipoPoder = txtTipoPoder.replace("<ul><li>", "'");
		tipoPoder = tipoPoder.replace("</li><li>","','");
		tipoPoder = tipoPoder.replace("</li></ul>", "'");
		tipoPoder = tipoPoder.equals("<ul></ul>") ? null : tipoPoder;
		tipoPoder = tipoPoder.replace("Carta Poder","CP").replace("Poder Especial","PE").replace("Poder General","PG");
		String escrituraFilter = txtEscritura.equals("") ? "" : "'"+txtEscritura+"'";	
		int numeroFila = 0;		
		
		GenericDataBean gdb = new GenericDataBean();
		
		if(!escrituraFilter.equals("")){
			try {																
				gdb = MngDataPoderes.query_PODERES_POR_ESCRITURA(hiddenEmpresa, tipoPoder, apoderados, hiddenPoder, escrituraFilter);
			} catch (Exception ex) {
				logger.error(ex.getMessage());        
			}
			
			GenericBean bean = null;
			int escrituraAnterior = 0;
			int empresaAnterior = 0;
			String tipoEscrituraAnterior = "";
			if(gdb.beans.size() > 0){	
				for(int i=0;i<gdb.beans.size();i++){
					bean = gdb.get(i);
					int escrituraActual = bean.getProperty("ID_EP_PK") == null ? 0 : Integer.valueOf(bean.getProperty("ID_EP_PK").toString());
					String TipoPoder = bean.getProperty("IND_TIPO_ESCRITURA").toString();
					String tipoEscrituraActual = bean.getProperty("IND_TIPO_ESCRITURA").toString();
					int empresaActual = bean.getProperty("ID_EMPRESA") == null ? 0 : Integer.valueOf(bean.getProperty("ID_EMPRESA").toString());						
					String descTipoPoder = (tipoEscrituraActual.equals("PG") ? "Poder General" : tipoEscrituraActual.equals("PE") ? "Poder Especial" : tipoEscrituraActual.equals("CP") ? "Carta Poder" : "Escritura de Revocaci�n");
					
					String descTipoEscritura = Integer.valueOf(bean.getProperty("NUM_TIPO_ESC_DIFERENTES").toString())>1? "Poderes" : descTipoPoder;
					
					if(escrituraActual != escrituraAnterior && empresaActual != empresaAnterior){	
						escritura = bean.getProperty("DES_ESCRITURA") == null ? "" : bean.getProperty("DES_ESCRITURA") == null ? "" : bean.getProperty("DES_ESCRITURA").toString();
						fechaOtorgamiento = bean.getProperty("FEC_OTORGAMIENTO_INSTR") == null ? "" : bean.getProperty("FEC_OTORGAMIENTO_INSTR").toString();
						delegadoPor = bean.getProperty("DELEGADOPOR") == null ? "" : bean.getProperty("DELEGADOPOR").toString();
						fecha = bean.getProperty("FEC_FECHA") == null ? "" : bean.getProperty("FEC_FECHA").toString();
						hora = bean.getProperty("FEC_HORA") == null ? "" : bean.getProperty("FEC_HORA").toString();
						escrituraDigitalizada = bean.getProperty("NUM_DOCUMENTUM_INSTR") == null ? "" : bean.getProperty("NUM_DOCUMENTUM_INSTR").toString();			
						nombreLicenciado = bean.getProperty("NOM_LICENCIADO") == null ? "" : bean.getProperty("NOM_LICENCIADO").toString();
						notarioPublico = bean.getProperty("NUM_NOTARIO") == null ? "" : bean.getProperty("NUM_NOTARIO").toString();
						deCiudad = bean.getProperty("DE_NOTARIO") == null ? "" : bean.getProperty("DE_NOTARIO").toString();
						suplenciaAsociado = bean.getProperty("DES_SUPLENCIA_ASOCIADO") == null ? "" : bean.getProperty("DES_SUPLENCIA_ASOCIADO").toString();
						registroPublico = bean.getProperty("DES_INSC_REGPUB") == null ? "" : bean.getProperty("DES_INSC_REGPUB").toString();
						fechaRegistro = bean.getProperty("FEC_REGISTRO") == null ? "" : bean.getProperty("FEC_REGISTRO").toString();
						folioMercantil = bean.getProperty("NUM_FOLIO_MERC") == null ? "" : bean.getProperty("NUM_FOLIO_MERC").toString();
						otrosDatosRegistro = bean.getProperty("DES_OTROS_DATOS_REGISTRO") == null ? "" : bean.getProperty("DES_OTROS_DATOS_REGISTRO").toString();
						solicitud = bean.getProperty("ID_SOL_DOC") == null ? "" : bean.getProperty("ID_SOL_DOC").toString();
						solicitadoPor = bean.getProperty("DES_SOL_RESP") == null ? "" : bean.getProperty("DES_SOL_RESP").toString();
						fechaSolicitud = bean.getProperty("FEC_SOL") == null ? "" : bean.getProperty("FEC_SOL").toString();
						fechaRecibido = bean.getProperty("FEC_SOL_REC") == null ? "" : bean.getProperty("FEC_SOL_REC").toString();
						folio = bean.getProperty("DES_SOL_FOLIO") == null ? "" : bean.getProperty("DES_SOL_FOLIO").toString();
						documentoEntrega = bean.getProperty("ID_ENT_DOC") == null ? "" : bean.getProperty("ID_ENT_DOC").toString();
						fechaDocumento = bean.getProperty("FEC_ENT_DOC") == null ? "" : bean.getProperty("FEC_ENT_DOC").toString();
						fechaEntregado = bean.getProperty("FEC_ENT_REC") == null ? "" : bean.getProperty("FEC_ENT_REC").toString();
						
						String empresa = bean.getProperty("NOM_EMPRESA").toString();
						
						if(i>0){
							numeroFila++;
							sheet.createRow((short) i+numeroFila).createCell(0).setCellValue("");
							numeroFila++;
							sheet.createRow((short) i+numeroFila).createCell(0).setCellValue("");							
						}						
												
						HSSFRow tipoPoderRow = sheet.createRow((short) i+numeroFila);
						Cell tipoPoderCell = tipoPoderRow.createCell(0);
						tipoPoderCell.setCellValue(descTipoEscritura);
						tipoPoderCell.setCellStyle(styles.get("negrita"));
						numeroFila++;
						
						HSSFRow nombreEmpresaRow = sheet.createRow((short) i+numeroFila);
						Cell cell = nombreEmpresaRow.createCell(0);
						cell.setCellValue(empresa);		
						cell.setCellStyle(styles.get("fondoAzul"));
						sheet.addMergedRegion(new CellRangeAddress(i+numeroFila, i+numeroFila,0,7));
						numeroFila++;												
										
						HSSFRow delegadoPorRow = sheet.createRow((short)i+numeroFila);
						Cell delegadoPorTitleCell = delegadoPorRow.createCell(0);
						delegadoPorTitleCell.setCellValue("Delegado por");	
						Cell delegadoPorCell = delegadoPorRow.createCell(1);
						delegadoPorCell.setCellValue(delegadoPor);		
						numeroFila++;
						
						HSSFRow fechaRow = sheet.createRow((short) i+numeroFila);
						Cell fechaTitleCell = fechaRow.createCell(0);
						fechaTitleCell.setCellValue("Fecha");	
						Cell fechaCell = fechaRow.createCell(1);
						fechaCell.setCellValue(fecha);		
						numeroFila++;
						
						HSSFRow horaRow = sheet.createRow((short) i+numeroFila);
						Cell horaTitleCell = horaRow.createCell(0);
						horaTitleCell.setCellValue("Hora");	
						Cell horaCell = horaRow.createCell(1);
						horaCell.setCellValue(hora);		
						numeroFila++;
						
						HSSFRow instrumentoRow = sheet.createRow((short) i+numeroFila);
						Cell instrumentoTitleCell = instrumentoRow.createCell(0);
						instrumentoTitleCell.setCellValue("Instrumento");
						CellStyle instrumentoStyle = styles.get("fondoGris");
						instrumentoStyle.setAlignment(CellStyle.ALIGN_LEFT);
						instrumentoTitleCell.setCellStyle(instrumentoStyle);
						numeroFila++;
						
						HSSFRow escrituraRow = sheet.createRow((short) i+numeroFila);
						Cell escrituraTitleCell = escrituraRow.createCell(0);
						escrituraTitleCell.setCellValue("Escritura No.");	
						Cell escrituraCell = escrituraRow.createCell(1);
						escrituraCell.setCellValue(escritura);		
						numeroFila++;
						
						HSSFRow escrituraDigitalRow = sheet.createRow((short) i+numeroFila);
						Cell escrituraDigitalTitleCell = escrituraDigitalRow.createCell(0);
						escrituraDigitalTitleCell.setCellValue("Escritura Digitalizada:");	
						Cell escrituraDigitalCell = escrituraDigitalRow.createCell(1);
						escrituraDigitalCell.setCellValue(escrituraDigitalizada);		
						numeroFila++;
						
						HSSFRow fechaOtorgamientoRow = sheet.createRow((short) i+numeroFila);
						Cell fechaOtorgamientoTitleCell = fechaOtorgamientoRow.createCell(0);
						fechaOtorgamientoTitleCell.setCellValue("Fecha Otorgamiento:");	
						Cell fechaOtorgamientoCell = fechaOtorgamientoRow.createCell(1);
						fechaOtorgamientoCell.setCellValue(fechaOtorgamiento);		
						numeroFila++;
						
						HSSFRow nombreLicenciadoRow = sheet.createRow((short) i+numeroFila);
						Cell nombreLicenciadoTitleCell = nombreLicenciadoRow.createCell(0);
						nombreLicenciadoTitleCell.setCellValue("Licenciado:");	
						Cell nombreLicenciadoCell = nombreLicenciadoRow.createCell(1);
						nombreLicenciadoCell.setCellValue(nombreLicenciado);		
						numeroFila++;
						
						HSSFRow notarioPublicoRow = sheet.createRow((short) i+numeroFila);
						Cell notarioPublicoTitleCell = notarioPublicoRow.createCell(0);
						notarioPublicoTitleCell.setCellValue("Notario p�blico:");	
						Cell notarioPublicoCell = notarioPublicoRow.createCell(1);
						notarioPublicoCell.setCellValue(notarioPublico);		
						numeroFila++;		
						
						HSSFRow deCiudadRow = sheet.createRow((short) i+numeroFila);
						Cell deCiudadTitleCell = deCiudadRow.createCell(0);
						deCiudadTitleCell.setCellValue("De:");	
						Cell deCiudadCell = deCiudadRow.createCell(1);
						deCiudadCell.setCellValue(deCiudad);		
						numeroFila++;
						
						HSSFRow suplenciaAsociadoRow = sheet.createRow((short) i+numeroFila);
						Cell suplenciaAsociadoTitleCell = suplenciaAsociadoRow.createCell(0);
						suplenciaAsociadoTitleCell.setCellValue("Suplencia / Asociado:");	
						Cell suplenciaAsociadoCell = suplenciaAsociadoRow.createCell(1);
						suplenciaAsociadoCell.setCellValue(suplenciaAsociado);		
						numeroFila++;
						
						HSSFRow registroPublicoRow = sheet.createRow((short) i+numeroFila);
						Cell registroPublicoTitleCell = registroPublicoRow.createCell(0);
						registroPublicoTitleCell.setCellValue("Inscrita en el Registro P�blico de Comercio de:");	
						Cell registroPublicoCell = registroPublicoRow.createCell(1);
						registroPublicoCell.setCellValue(registroPublico);		
						numeroFila++;
						
						HSSFRow fechaRegistroRow = sheet.createRow((short) i+numeroFila);
						Cell fechaRegistroTitleCell = fechaRegistroRow.createCell(0);
						fechaRegistroTitleCell.setCellValue("Fecha de Registro:");	
						Cell fechaRegistroCell = fechaRegistroRow.createCell(1);
						fechaRegistroCell.setCellValue(fechaRegistro);		
						numeroFila++;
						
						HSSFRow folioMercantilRow = sheet.createRow((short) i+numeroFila);
						Cell folioMercantilTitleCell = folioMercantilRow.createCell(0);
						folioMercantilTitleCell.setCellValue("Folio Mercantil / Folio Mercantil Electr�nico");	
						Cell folioMercantilCell = folioMercantilRow.createCell(1);
						folioMercantilCell.setCellValue(folioMercantil);		
						numeroFila++;
						
						HSSFRow otrosDatosRegistroRow = sheet.createRow((short)i+numeroFila);
						Cell otrosDatosRegistroTitleCell = otrosDatosRegistroRow.createCell(0);
						otrosDatosRegistroTitleCell.setCellValue("Otros Datos de Registro");	
						Cell otrosDatosRegistroCell = otrosDatosRegistroRow.createCell(1);
						otrosDatosRegistroCell.setCellValue(otrosDatosRegistro);		
						numeroFila++;
						
						HSSFRow referenciaDocumentumRow = sheet.createRow((short) i+numeroFila);
						Cell referenciaDocumentumTitleCell = referenciaDocumentumRow.createCell(0);
						referenciaDocumentumTitleCell.setCellValue("Referencia Documentum");
						CellStyle referenciaDocumentumStyle = styles.get("fondoGris");
						referenciaDocumentumStyle.setAlignment(CellStyle.ALIGN_LEFT);
						referenciaDocumentumTitleCell.setCellStyle(referenciaDocumentumStyle);
						numeroFila++;
						
						if(solicitud.equals("") && solicitadoPor.equals("")
								&& fechaSolicitud.equals("") && fechaRecibido.equals("")
								&& folio.equals("") && documentoEntrega.equals("")
								&& fechaDocumento.equals("") && fechaEntregado.equals("")){
							
						}else{
										
							HSSFRow solicitudRow = sheet.createRow((short) i+numeroFila);
							Cell solicitudTitleCell = solicitudRow.createCell(0);
							solicitudTitleCell.setCellValue("Solicitud");	
							Cell solicitudCell = solicitudRow.createCell(1);
							solicitudCell.setCellValue(solicitud);		
							numeroFila++;
							
							HSSFRow solicitadoPorRow = sheet.createRow((short) i+numeroFila);
							Cell solicitadoPorTitleCell = solicitadoPorRow.createCell(0);
							solicitadoPorTitleCell.setCellValue("Solicitado por:");	
							Cell solicitadoPorCell = solicitadoPorRow.createCell(1);
							solicitadoPorCell.setCellValue(solicitadoPor);		
							numeroFila++;				
							
							HSSFRow fechaSolicitudRow = sheet.createRow((short) i+numeroFila);
							Cell fechaSolicitudTitleCell = fechaSolicitudRow.createCell(0);
							fechaSolicitudTitleCell.setCellValue("Fecha de Solicitud:");	
							Cell fechaSolicitudCell = fechaSolicitudRow.createCell(1);
							fechaSolicitudCell.setCellValue(fechaSolicitud);		
							numeroFila++;
							
							HSSFRow fechaRecibidoRow = sheet.createRow((short) i+numeroFila);
							Cell fechaRecibidoTitleCell = fechaRecibidoRow.createCell(0);
							fechaRecibidoTitleCell.setCellValue("Fecha de Recibido:");	
							Cell fechaRecibidoCell = fechaRecibidoRow.createCell(1);
							fechaRecibidoCell.setCellValue(fechaRecibido);		
							numeroFila++;
							
							HSSFRow folioRow = sheet.createRow((short) i+numeroFila);
							Cell folioTitleCell = folioRow.createCell(0);
							folioTitleCell.setCellValue("Folio:");	
							Cell folioCell = folioRow.createCell(1);
							folioCell.setCellValue(folio);		
							numeroFila++;
							
							HSSFRow documentoEntregaRow = sheet.createRow((short) i+numeroFila);
							Cell documentoEntregaTitleCell = documentoEntregaRow.createCell(0);
							documentoEntregaTitleCell.setCellValue("Documento de Entrega:");	
							Cell documentoEntregaCell = documentoEntregaRow.createCell(1);
							documentoEntregaCell.setCellValue(documentoEntrega);		
							numeroFila++;
							
							HSSFRow fechaDocumentoRow = sheet.createRow((short) i+numeroFila);
							Cell fechaDocumentoTitleCell = fechaDocumentoRow.createCell(0);
							fechaDocumentoTitleCell.setCellValue("Fecha de Documento:");	
							Cell fechaDocumentoCell = fechaDocumentoRow.createCell(1);
							fechaDocumentoCell.setCellValue(fechaDocumento);		
							numeroFila++;
							
							HSSFRow fechaEntregadoRow = sheet.createRow((short) i+numeroFila);
							Cell fechaEntregadoTitleCell = fechaEntregadoRow.createCell(0);
							fechaEntregadoTitleCell.setCellValue("Fecha de Recibido:");	
							Cell fechaEntregadoCell = fechaEntregadoRow.createCell(1);
							fechaEntregadoCell.setCellValue(fechaEntregado);		
							numeroFila++;	
						}
						
						escrituraAnterior = escrituraActual;						
					}
																																					
						if((i==0 || !bean.getProperty("ID_OPODER_EP_PK").toString().equals(gdb.get(i-1).getProperty("ID_OPODER_EP_PK").toString())) && 
								bean.getProperty("NOM_EMPRESA").toString().equals(gdb.get(i).getProperty("NOM_EMPRESA").toString())){														
							
							numeroFila++;
							
							if(i==0 || !tipoEscrituraActual.equals(tipoEscrituraAnterior) || empresaAnterior != empresaActual){																										
								HSSFRow tipoPoderRow = sheet.createRow((short) i+numeroFila);
								Cell tipoPoderCell = tipoPoderRow.createCell(0);
								tipoPoderCell.setCellValue(descTipoPoder);
								tipoPoderCell.setCellStyle(styles.get("negrita"));
								numeroFila++;
								tipoEscrituraAnterior = tipoEscrituraActual; 
							}
							
							HSSFRow tableHeaderRow = sheet.createRow((short) i+numeroFila);
							switch(TipoPoder){
							case "PG": 																	
								tableHeaderRow.createCell(0).setCellValue("Apoderados");									
								tableHeaderRow.createCell(1).setCellValue("Poder");
								tableHeaderRow.createCell(2).setCellValue("Actos de Dominio");										
								tableHeaderRow.createCell(3).setCellValue("Actos de Administraci�n");									
								tableHeaderRow.createCell(4).setCellValue("T�tulos de Cr�dito");										
								tableHeaderRow.createCell(5).setCellValue("Pleitos y Cobranzas");										
								tableHeaderRow.createCell(6).setCellValue("Especial");
								tableHeaderRow.createCell(7).setCellValue("Vigencia");	
								break;
							case "CP": 									
								tableHeaderRow.createCell(0).setCellValue("Poder");
								tableHeaderRow.createCell(1).setCellValue("Apoderados");		
								tableHeaderRow.createCell(2).setCellValue("Descripci�n");										
								tableHeaderRow.createCell(3).setCellValue("Vigencia");					
								break;
							case "PE": 					
								tableHeaderRow.createCell(0).setCellValue("Poder");
								tableHeaderRow.createCell(1).setCellValue("Apoderados");		
								tableHeaderRow.createCell(2).setCellValue("Descripci�n");										
								tableHeaderRow.createCell(3).setCellValue("Vigencia");		
								break;
							}							
							Iterator<Cell> tableHeaderIterator = tableHeaderRow.cellIterator();
							while(tableHeaderIterator.hasNext()){
								tableHeaderIterator.next().setCellStyle(styles.get("fondoGris"));
							}
						}														
					
					String pod = bean.getProperty("DES_PODERTIPO") == null ? "" : bean.getProperty("DES_PODERTIPO").toString();
					String apod = bean.getProperty("DESC_APODERADOS") == null ? "" : bean.getProperty("DESC_APODERADOS").toString();
					apod = removeHTMLTags(apod);
					String ad = bean.getProperty("DESC_ACTOSDOMINIO") == null ? "" : bean.getProperty("DESC_ACTOSDOMINIO").toString();
					ad = removeHTMLTags(ad);
					String aa = bean.getProperty("DESC_ACTOSADMON") == null ? "" : bean.getProperty("DESC_ACTOSADMON").toString();
					aa = removeHTMLTags(aa);
					String tc = bean.getProperty("DESC_TITULOSCREDITO") == null ? "" : bean.getProperty("DESC_TITULOSCREDITO").toString();
					tc = removeHTMLTags(tc);
					String pc = bean.getProperty("DESC_PLEITOSCOBRANZA") == null ? "" : bean.getProperty("DESC_PLEITOSCOBRANZA").toString();
					pc = removeHTMLTags(pc);
					String vig = bean.getProperty("FEC_VIGENCIAFIN") == null ? "" : bean.getProperty("FEC_VIGENCIAFIN").toString();				
					String descEsp = bean.getProperty("DES_PODER") == null ? "" : bean.getProperty("DES_PODER").toString();
					descEsp = removeHTMLTags(descEsp);
					String descEspecial = bean.getProperty("DESC_PODER_ESPECIAL") == null ? "" : bean.getProperty("DESC_PODER_ESPECIAL").toString();
					descEspecial = removeHTMLTags(descEspecial);									
						
					int idOtorgaPoder = Integer.valueOf(bean.getProperty("ID_OPODER_EP_PK").toString());
					String apendiceRevocados = "";				
					GenericDataBean gdbRevocaciones = MngDataPoderes.query_REVOCACIONES(idOtorgaPoder);
					for(int j=0; j < gdbRevocaciones.beans.size(); j++){
						GenericBean beanRevocacion = gdbRevocaciones.get(j);	
						String apendiceRevocadoCurrent = "";								
						  apendiceRevocadoCurrent = beanRevocacion.getProperty("DESC_REVOCA") == null ? "" : beanRevocacion.getProperty("DESC_REVOCA").toString(); 
						  if(!apendiceRevocados.contains(apendiceRevocadoCurrent)){						
							  apendiceRevocados += apendiceRevocadoCurrent.trim().length()>0 ? apendiceRevocadoCurrent + "\n" : "";	
						  }									  
					}
					
					numeroFila++;
					HSSFRow dataRow = sheet.createRow((short) i+numeroFila); 
					
					if((i==0 || !bean.getProperty("ID_OPODER_EP_PK").toString().equals(gdb.get(i-1).getProperty("ID_OPODER_EP_PK").toString())) && 
								bean.getProperty("NOM_EMPRESA").toString().equals(gdb.get(i).getProperty("NOM_EMPRESA").toString())){
						switch(TipoPoder){
							case "PG": 															
								dataRow.createCell(0).setCellValue(apod);
								dataRow.createCell(1).setCellValue(pod);
								dataRow.createCell(2).setCellValue(ad);					
								dataRow.createCell(3).setCellValue(aa);					
								dataRow.createCell(4).setCellValue(tc);					
								dataRow.createCell(5).setCellValue(pc);					
								dataRow.createCell(6).setCellValue(descEspecial);
								dataRow.createCell(7).setCellValue(vig);
								break;
							case "CP": 										
								dataRow.createCell(0).setCellValue(pod);
								dataRow.createCell(1).setCellValue(apod);
								dataRow.createCell(2).setCellValue(descEsp);					
								dataRow.createCell(3).setCellValue(vig);						
								break;
							case "PE": 		
								dataRow.createCell(0).setCellValue(pod);
								dataRow.createCell(1).setCellValue(apod);
								dataRow.createCell(2).setCellValue(descEsp);					
								dataRow.createCell(3).setCellValue(vig);						
								break;
							}					
						
						Iterator<Cell> dataRowIterator = dataRow.cellIterator();
						while(dataRowIterator.hasNext()){
							dataRowIterator.next().setCellStyle(styles.get("bordeDelgado"));
						}
						
						empresaAnterior = empresaActual;
						
						if(apendiceRevocados.length()>0){
							numeroFila++;
							dataRow = sheet.createRow((short) i+numeroFila); 
							dataRow.createCell(0).setCellValue(removeHTMLTags(apendiceRevocados));
						}
						
					}					
				}
			}								
		}
		else{
			try{
				gdb = MngDataPoderes.query_ESCRITURAS(hiddenEmpresa, tipoPoder, apoderados, hiddenPoder, escritura);
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
			GenericBean beanApoderados = null;
		
			if(gdb.beans.size() == 0) {
				HSSFRow tableHeaderRow = sheet.createRow((short) 0);										 																	
				tableHeaderRow.createCell(0).setCellValue("No se encontraron registros");	 										 
			}
			else{													
				for(int i=0; i < gdb.beans.size(); i++){
					HSSFRow tableHeaderRow = sheet.createRow((short) numeroFila);										 																	
					tableHeaderRow.createCell(0).setCellValue("Escritura");									
					tableHeaderRow.createCell(1).setCellValue("Empresa");
					tableHeaderRow.createCell(2).setCellValue("Tipo");										
					tableHeaderRow.createCell(3).setCellValue("Poder");									
					tableHeaderRow.createCell(4).setCellValue("Apoderados                ");
					Iterator<Cell> tableHeaderIterator = tableHeaderRow.cellIterator();
					while(tableHeaderIterator.hasNext()){
						tableHeaderIterator.next().setCellStyle(styles.get("fondoGris"));
					}
					numeroFila ++;
					
					beanApoderados = gdb.get(i);					
					String esc = beanApoderados.getProperty("DES_ESCRITURA") == null ? "" : beanApoderados.getProperty("DES_ESCRITURA") == null ? "" : beanApoderados.getProperty("DES_ESCRITURA").toString();										
					String apo = beanApoderados.getProperty("DESC_APODERADOS") == null ? "" : beanApoderados.getProperty("DESC_APODERADOS").toString();
					apo = removeHTMLTags(apo);					
					String empresa = beanApoderados.getProperty("NOM_EMPRESA") == null ? "" : beanApoderados.getProperty("NOM_EMPRESA").toString();
					empresa = removeHTMLTags(empresa);
					String descEsp = beanApoderados.getProperty("DES_PODER") == null ? "" : beanApoderados.getProperty("DES_PODER").toString();
					descEsp = removeHTMLTags(descEsp);
					tipoPoder = beanApoderados.getProperty("IND_TIPO_ESCRITURA").toString();
					String descTipoPoder = (tipoPoder.equals("PG") ? "Poder General" : tipoPoder.equals("PE") ? "Poder Especial" : tipoPoder.equals("CP") ? "Carta Poder" : "Escritura de Revocaci�n");															
					apo = apo.replace("null", "");					
					descEsp = descEsp.replace("null", "");
					
					int idOtorgaPoder = Integer.valueOf(beanApoderados.getProperty("ID_OPODER_EP_PK").toString());
					String apendiceRevocados = "";				
					GenericDataBean gdbRevocaciones = MngDataPoderes.query_REVOCACIONES(idOtorgaPoder);
					for(int j=0; j < gdbRevocaciones.beans.size(); j++){
						GenericBean beanRevocacion = gdbRevocaciones.get(j);	
						String apendiceRevocadoCurrent = "";								
						  apendiceRevocadoCurrent = beanRevocacion.getProperty("DESC_REVOCA") == null ? "" : beanRevocacion.getProperty("DESC_REVOCA").toString(); 
						  if(!apendiceRevocados.contains(apendiceRevocadoCurrent)){						
							  apendiceRevocados += apendiceRevocadoCurrent.trim().length()>0 ? apendiceRevocadoCurrent + "\n" : "";	
						  }									  
					}
					
					HSSFRow dataRow = sheet.createRow((short) numeroFila);										 																	
					dataRow.createCell(0).setCellValue(esc);									
					dataRow.createCell(1).setCellValue(empresa);
					dataRow.createCell(2).setCellValue(descTipoPoder);										
					dataRow.createCell(3).setCellValue(descEsp);									
					dataRow.createCell(4).setCellValue(apo);
					Iterator<Cell> dataRowIterator = dataRow.cellIterator();
					while(dataRowIterator.hasNext()){
						dataRowIterator.next().setCellStyle(styles.get("bordeDelgado"));
					}										
					
					if(apendiceRevocados.length()>0){
						numeroFila++;
						dataRow = sheet.createRow((short) numeroFila); 
						dataRow.createCell(0).setCellValue(removeHTMLTags(apendiceRevocados));
						numeroFila ++;
					}
					
					numeroFila ++;
				}							
			}
		}
		sheet.autoSizeColumn(0);
		sheet.autoSizeColumn(1);
		sheet.autoSizeColumn(2);
		sheet.autoSizeColumn(3);
		sheet.autoSizeColumn(4);
		sheet.autoSizeColumn(5);
		sheet.autoSizeColumn(6);
		sheet.autoSizeColumn(7);
									
		downloadExcel(wb, "ReportePoderesPorEscritura");				
	}
	
	String empresaCurrent = "";
	String tipoPoderCurrent = "";
	String facultadCurrent = "";	
	int numeroFila = 0;
	public void generarExcelPoderesPorFacultades(String hiddenEmpresa, String txtTipoPoder, String hiddenApoderados, 
			 String hiddenPoder, String txtEscritura, String filterAD, String filterAA, String filterTC, String filterPC){
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet();
		Map<String, CellStyle> styles = createStyles(wb);
		empresaCurrent = "";
		tipoPoderCurrent = "";
		facultadCurrent = "";	
		numeroFila = 0;
		String tipoPoderFilter = txtTipoPoder.replace("<ul><li>", "'");
		tipoPoderFilter = tipoPoderFilter.replace("</li><li>","','");
		tipoPoderFilter = tipoPoderFilter.replace("</li></ul>", "'");
		tipoPoderFilter = tipoPoderFilter.equals("<ul></ul>") ? null : tipoPoderFilter;
		tipoPoderFilter = tipoPoderFilter.replace("Carta Poder","CP").replace("Poder Especial","PE").replace("Poder General","PG");
		String escritura = txtEscritura.equals("") ? "" : "'%"+txtEscritura+"%'";
		
		GenericDataBean gdbPoderesGenerales = new GenericDataBean();		
		
		try {												
			gdbPoderesGenerales = MngDataPoderes.query_PODERES_POR_FACULTAD(hiddenEmpresa, tipoPoderFilter, hiddenApoderados, hiddenPoder, escritura, filterAD, filterAA, filterTC, filterPC);
		} catch (Exception ex) {
			logger.error(ex.getMessage());        
		}
		
		 if(gdbPoderesGenerales.beans.size() > 0){							
				
			 	GenericDataBean gdbPoderesEspeciales = new GenericDataBean();						
				
				for(int i=0; i < gdbPoderesGenerales.beans.size(); i++){				
					GenericBean beanPG = gdbPoderesGenerales.get(i);
					String idEmpresa = beanPG.getProperty("ID_EMPRESA").toString();
					String idEmpresaAnterior = i>0 ? gdbPoderesGenerales.get(i-1).getProperty("ID_EMPRESA").toString() : idEmpresa;				
					if(idEmpresa.equals(idEmpresaAnterior)){
						sheet = generarTablaPorFacultades(sheet, beanPG, i, filterAD, filterAA, filterTC, filterPC, styles);					
					}		
				}
			}	
		
		sheet.autoSizeColumn(0);
		sheet.autoSizeColumn(1);
		sheet.autoSizeColumn(2);
		sheet.autoSizeColumn(3);
		sheet.autoSizeColumn(4);
		sheet.autoSizeColumn(5);
		sheet.autoSizeColumn(6);
		sheet.autoSizeColumn(7);
		
		downloadExcel(wb, "ReportePoderesPorFacultades");
		
	}
	
	public HSSFSheet generarTablaPorFacultades (HSSFSheet sheet, GenericBean bean, int i, String filterAD, String filterAA, String filterTC, 
			String filterPC, Map<String, CellStyle> styles){		
		String empresa = bean.getProperty("NOM_EMPRESA").toString();
		String tipoPoder = bean.getProperty("IND_TIPO_ESCRITURA").toString();
		String facultadEspecial = bean.getProperty("DES_PODERTIPO") != null ? bean.getProperty("DES_PODERTIPO").toString() : ""; 
										
		if(!empresaCurrent.equals(empresa)){
			if(i>0){
				sheet.createRow((short) i+numeroFila).createCell(0).setCellValue("");
				numeroFila++;				
			}
						
			HSSFRow nombreEmpresaRow = sheet.createRow((short) i+numeroFila);
			Cell cell = nombreEmpresaRow.createCell(0);
			cell.setCellValue(empresa);		
			cell.setCellStyle(styles.get("fondoAzul"));
			sheet.addMergedRegion(new CellRangeAddress(i+numeroFila, i+numeroFila,0,7));
			numeroFila++;
			empresaCurrent = empresa;	
			tipoPoderCurrent = "";								
		}
		
		if(!facultadCurrent.equals(facultadEspecial) && (tipoPoder.equals("PE") || tipoPoder.equals("CP"))){															
			numeroFila++;
			HSSFRow facultadEspecialRow = sheet.createRow((short) i+numeroFila);
			Cell cell = facultadEspecialRow.createCell(0);
			cell.setCellValue(facultadEspecial);		
			cell.setCellStyle(styles.get("fondoAzul"));			
			sheet.addMergedRegion(new CellRangeAddress(i+numeroFila, i+numeroFila,0,7));
			numeroFila++;
			facultadCurrent = facultadEspecial;	
			tipoPoderCurrent = "";											
		}
		
		if(!tipoPoderCurrent.equals(tipoPoder)){							
			HSSFRow tipoPoderRow = sheet.createRow((short) i+numeroFila);		
			Cell tipoPoderCell = tipoPoderRow.createCell(0);
			tipoPoderCell.setCellValue((tipoPoder.equals("PG") ? "Poderes Generales" : tipoPoder.equals("PE") ? "Poderes Especiales" : tipoPoder.equals("CP") ? "Cartas Poder" : "Escrituras de Revocaci�n"));
			tipoPoderCell.setCellStyle(styles.get("negrita"));
		
			tipoPoderCurrent = tipoPoder;	
		}			
		
		numeroFila++;
		HSSFRow tableHeaderRow = sheet.createRow((short) i+numeroFila);
		switch(tipoPoder){
		case "PG": 					
			tableHeaderRow.createCell(0).setCellValue("Escritura No.");											
			tableHeaderRow.createCell((int)tableHeaderRow.getLastCellNum()).setCellValue("Fecha");										
			tableHeaderRow.createCell((int)tableHeaderRow.getLastCellNum()).setCellValue("Apoderados");	
			tableHeaderRow.createCell((int)tableHeaderRow.getLastCellNum()).setCellValue("Poder");
			if(filterAD != null || filterAA != null || filterTC != null || filterPC != null){
				if(filterAD != null)
					tableHeaderRow.createCell((int)tableHeaderRow.getLastCellNum()).setCellValue("Actos de Dominio");				            		            		
				if(filterAA != null)
        			tableHeaderRow.createCell((int)tableHeaderRow.getLastCellNum()).setCellValue("Actos de Administraci�n");				            		            	
				if(filterTC != null)
        			tableHeaderRow.createCell((int)tableHeaderRow.getLastCellNum()).setCellValue("T�tulos de Cr�dito");			            		            		
				if(filterPC != null)
        			tableHeaderRow.createCell((int)tableHeaderRow.getLastCellNum()).setCellValue("Pleitos y Cobranzas");
			}
			else{						
				tableHeaderRow.createCell((int)tableHeaderRow.getLastCellNum()).setCellValue("Actos de Dominio");				            		            		
				tableHeaderRow.createCell((int)tableHeaderRow.getLastCellNum()).setCellValue("Actos de Administraci�n");				            		            	
				tableHeaderRow.createCell((int)tableHeaderRow.getLastCellNum()).setCellValue("T�tulos de Cr�dito");			            		            								
        		tableHeaderRow.createCell((int)tableHeaderRow.getLastCellNum()).setCellValue("Pleitos y Cobranzas");
			}
																											
			tableHeaderRow.createCell((int)tableHeaderRow.getLastCellNum()).setCellValue("Vigencia");							
			break;
		case "CP": 																		
			tableHeaderRow.createCell(0).setCellValue("Fecha");		
			tableHeaderRow.createCell(1).setCellValue("Poder");
			tableHeaderRow.createCell(2).setCellValue("Apoderados");		
			tableHeaderRow.createCell(3).setCellValue("Descripci�n");										
			tableHeaderRow.createCell(4).setCellValue("Vigencia");					
			break;
		case "PE": 					
			tableHeaderRow.createCell(0).setCellValue("Escritura No.");										
			tableHeaderRow.createCell(1).setCellValue("Fecha");	
			tableHeaderRow.createCell(2).setCellValue("Poder");
			tableHeaderRow.createCell(3).setCellValue("Apoderados");		
			tableHeaderRow.createCell(4).setCellValue("Descripci�n");										
			tableHeaderRow.createCell(5).setCellValue("Vigencia");				
			break;
		}
		numeroFila++;
		Iterator<Cell> tableHeaderIterator = tableHeaderRow.cellIterator();
		while(tableHeaderIterator.hasNext()){
			tableHeaderIterator.next().setCellStyle(styles.get("fondoGris"));
		}
		
			String esc = bean.getProperty("DES_ESCRITURA") == null ? "" : bean.getProperty("DES_ESCRITURA") == null ? "" : bean.getProperty("DES_ESCRITURA").toString();
			String fec = bean.getProperty("FEC_OTORGAMIENTO_INSTR") == null ? "" : bean.getProperty("FEC_OTORGAMIENTO_INSTR").toString();
			String fecCP = bean.getProperty("FEC_FECHA") == null ? "" : bean.getProperty("FEC_FECHA").toString();
			String apod = bean.getProperty("DESC_APODERADOS") == null ? "" : bean.getProperty("DESC_APODERADOS").toString();			
			apod = removeHTMLTags(apod);
			String pod = bean.getProperty("DES_PODERTIPO") == null ? "" : bean.getProperty("DES_PODERTIPO").toString();
			String ad = bean.getProperty("DESC_ACTOSDOMINIO") == null ? "" : bean.getProperty("DESC_ACTOSDOMINIO").toString();
			ad = removeHTMLTags(ad);
			String aa = bean.getProperty("DESC_ACTOSADMON") == null ? "" : bean.getProperty("DESC_ACTOSADMON").toString();
			aa = removeHTMLTags(aa);
			String tc = bean.getProperty("DESC_TITULOSCREDITO") == null ? "" : bean.getProperty("DESC_TITULOSCREDITO").toString();
			tc = removeHTMLTags(tc);
			String pc = bean.getProperty("DESC_PLEITOSCOBRANZA") == null ? "" : bean.getProperty("DESC_PLEITOSCOBRANZA").toString();
			pc = removeHTMLTags(pc);
			String vig = bean.getProperty("FEC_VIGENCIAFIN") == null ? "" : bean.getProperty("FEC_VIGENCIAFIN").toString();
			String desc = bean.getProperty("DES_PODER") == null ? "" : bean.getProperty("DES_PODER").toString();
			desc = removeHTMLTags(desc);
			
			int idOtorgaPoder = Integer.valueOf(bean.getProperty("ID_OPODER_EP_PK").toString());
			String apendiceRevocados = "";				
			GenericDataBean gdbRevocaciones = MngDataPoderes.query_REVOCACIONES(idOtorgaPoder);
			for(int j=0; j < gdbRevocaciones.beans.size(); j++){
				GenericBean beanRevocacion = gdbRevocaciones.get(j);	
				String apendiceRevocadoCurrent = "";								
				  apendiceRevocadoCurrent = beanRevocacion.getProperty("DESC_REVOCA") == null ? "" : beanRevocacion.getProperty("DESC_REVOCA").toString(); 
				  if(!apendiceRevocados.contains(apendiceRevocadoCurrent)){						
					  apendiceRevocados += apendiceRevocadoCurrent.trim().length()>0 ? apendiceRevocadoCurrent + "\n" : "";	
				  }									  
			}
			
			if(tipoPoderCurrent.equals(tipoPoder) && empresaCurrent.equals(empresa)){												
				HSSFRow dataRow = sheet.createRow((short) i+numeroFila); 								
				switch(tipoPoder){
				case "PG": 					
					dataRow.createCell(0).setCellValue(esc);		
					dataRow.createCell((int)(int)dataRow.getLastCellNum()).setCellValue(fec);			
					dataRow.createCell((int)dataRow.getLastCellNum()).setCellValue(apod);					
					dataRow.createCell((int)dataRow.getLastCellNum()).setCellValue(pod);	
					if(filterAD != null || filterAA != null || filterTC != null || filterPC != null){
						if(filterAD != null)
							dataRow.createCell((int)dataRow.getLastCellNum()).setCellValue(ad);						            		            		
						if(filterAA != null)
							dataRow.createCell((int)dataRow.getLastCellNum()).setCellValue(aa);					            		            	
						if(filterTC != null)
							dataRow.createCell((int)dataRow.getLastCellNum()).setCellValue(tc);			            		            		
						if(filterPC != null)
							dataRow.createCell((int)dataRow.getLastCellNum()).setCellValue(pc);
					}
					else{						
						dataRow.createCell((int)dataRow.getLastCellNum()).setCellValue(ad);					
						dataRow.createCell((int)dataRow.getLastCellNum()).setCellValue(aa);					
						dataRow.createCell((int)dataRow.getLastCellNum()).setCellValue(tc);					
						dataRow.createCell((int)dataRow.getLastCellNum()).setCellValue(pc);												
					}
																		
					dataRow.createCell((int)dataRow.getLastCellNum()).setCellValue(vig);					
					break;
				case "CP": 													
					dataRow.createCell(0).setCellValue(fecCP);
					dataRow.createCell(1).setCellValue(pod);	
					dataRow.createCell(2).setCellValue(apod);
					dataRow.createCell(3).setCellValue(desc);					
					dataRow.createCell(4).setCellValue(vig);	
					numeroFila++;
					break;
				case "PE": 				
					dataRow.createCell(0).setCellValue(esc);					
					dataRow.createCell(1).setCellValue(fec);
					dataRow.createCell(2).setCellValue(pod);	
					dataRow.createCell(3).setCellValue(apod);
					dataRow.createCell(4).setCellValue(desc);					
					dataRow.createCell(5).setCellValue(vig);
					numeroFila++;
					break;
				}					
				
				Iterator<Cell> dataRowIterator = dataRow.cellIterator();
				while(dataRowIterator.hasNext()){
					dataRowIterator.next().setCellStyle(styles.get("bordeDelgado"));
				}
				
				if(apendiceRevocados.length()>0){
					numeroFila++;
					dataRow = sheet.createRow((short) i+numeroFila); 
					dataRow.createCell(0).setCellValue(removeHTMLTags(apendiceRevocados));
				}
				
				
			}											
			else{
				numeroFila--;	
			}	
		return sheet;
	}
	
	public void generarExcelPoderesPorAsunto(String hiddenEmpresa, String txtTipoPoder, String hiddenApoderados, 
			String hiddenGrupoApoderados, String hiddenPoder, String txtEscritura){
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet();
		Map<String, CellStyle> styles = createStyles(wb);		
		String empresaCurrent = "";
		String tipoPoderCurrent = "";
		String escrituraCurrent = " ";
		String grupoDeApoderadoCurrent = "";
		GenericDataBean gdb = new GenericDataBean();
		try {						
			String tipoPoder = txtTipoPoder.replace("<ul><li>", "'");
			tipoPoder = tipoPoder.replace("</li><li>","','");
			tipoPoder = tipoPoder.replace("</li></ul>", "'");
			tipoPoder = tipoPoder.equals("<ul></ul>") ? null : tipoPoder;
			tipoPoder = tipoPoder.replace("Carta Poder","CP").replace("Poder Especial","PE").replace("Poder General","PG");
			String escritura = txtEscritura.equals("") ? "" : "'%"+txtEscritura+"%'";
			
			gdb = MngDataPoderes.query_PODERES_POR_ASUNTO(hiddenEmpresa, tipoPoder, hiddenApoderados, hiddenGrupoApoderados, hiddenPoder, escritura);
		} catch (Exception ex) {
			logger.error(ex.getMessage());        
		}
		
		int numeroFila = 0;
		for(int i=0; i < gdb.beans.size(); i++){
			GenericBean bean = gdb.get(i);
			String grupoDeApoderado = bean.getProperty("NOM_GRUPO").toString();			
			String empresa = bean.getProperty("NOM_EMPRESA").toString();
			String tipoPoder = bean.getProperty("IND_TIPO_ESCRITURA").toString();
									
			if(!grupoDeApoderadoCurrent.equals(grupoDeApoderado)){
				if(i>0){
					sheet.createRow((short) i+numeroFila).createCell(0).setCellValue("");
					numeroFila++;
					sheet.createRow((short) i+numeroFila).createCell(0).setCellValue("");
					numeroFila++;
				}
				 
				HSSFRow grupoApoderadoRow = sheet.createRow((short) i+numeroFila);
				Cell cell = grupoApoderadoRow.createCell(0);
				cell.setCellValue(grupoDeApoderado);
				cell.setCellStyle(styles.get("fondoAzul"));
				sheet.addMergedRegion(new CellRangeAddress(i+numeroFila, i+numeroFila,0,8));
				numeroFila++;
				grupoDeApoderadoCurrent = grupoDeApoderado;	
				empresaCurrent = "";							
			}
			
			if(!empresaCurrent.equals(empresa)){												
				HSSFRow nombreEmpresaRow = sheet.createRow((short) i+numeroFila);
				Cell cell = nombreEmpresaRow.createCell(0);
				cell.setCellValue(empresa);		
				cell.setCellStyle(styles.get("fondoAzul"));
				sheet.addMergedRegion(new CellRangeAddress(i+numeroFila, i+numeroFila,0,8));
				numeroFila++;
				empresaCurrent = empresa;	
				tipoPoderCurrent = "";											
			}
			
			if(!tipoPoderCurrent.equals(tipoPoder)){	
				numeroFila++;
				HSSFRow tipoPoderRow = sheet.createRow((short) i+numeroFila);
				Cell tipoPoderCell = tipoPoderRow.createCell(0);
				tipoPoderCell.setCellValue((tipoPoder.equals("PG") ? "Poderes Generales" : tipoPoder.equals("PE") ? "Poderes Especiales" : tipoPoder.equals("CP") ? "Cartas Poder" : "Escrituras de Revocaci�n"));
				tipoPoderCell.setCellStyle(styles.get("negrita"));				
				tipoPoderCurrent = tipoPoder;	
			}									
			
				String esc = bean.getProperty("DES_ESCRITURA") == null ? "" : bean.getProperty("DES_ESCRITURA") == null ? "" : bean.getProperty("DES_ESCRITURA").toString();
				String fec = bean.getProperty("FEC_OTORGAMIENTO_INSTR") == null ? "" : bean.getProperty("FEC_OTORGAMIENTO_INSTR").toString();
				String fecCP = bean.getProperty("FEC_FECHA") == null ? "" : bean.getProperty("FEC_FECHA").toString();
				String apod = bean.getProperty("DESC_APODERADOS") == null ? "" : bean.getProperty("DESC_APODERADOS").toString();			
				apod = removeHTMLTags(apod);
				String pod = bean.getProperty("DES_PODERTIPO") == null ? "" : bean.getProperty("DES_PODERTIPO").toString();
				String ad = bean.getProperty("DESC_ACTOSDOMINIO") == null ? "" : bean.getProperty("DESC_ACTOSDOMINIO").toString();
				ad = removeHTMLTags(ad);
				String aa = bean.getProperty("DESC_ACTOSADMON") == null ? "" : bean.getProperty("DESC_ACTOSADMON").toString();
				aa = removeHTMLTags(aa);
				String tc = bean.getProperty("DESC_TITULOSCREDITO") == null ? "" : bean.getProperty("DESC_TITULOSCREDITO").toString();
				tc = removeHTMLTags(tc);
				String pc = bean.getProperty("DESC_PLEITOSCOBRANZA") == null ? "" : bean.getProperty("DESC_PLEITOSCOBRANZA").toString();
				pc = removeHTMLTags(pc);
				String vig = bean.getProperty("FEC_VIGENCIAFIN") == null ? "" : bean.getProperty("FEC_VIGENCIAFIN").toString();
				String desc = bean.getProperty("DES_PODER") == null ? "" : bean.getProperty("DES_PODER").toString();
				desc = removeHTMLTags(desc);
				String descEspecial = bean.getProperty("DESC_PODER_ESPECIAL") == null ? "" : bean.getProperty("DESC_PODER_ESPECIAL").toString();
				descEspecial = removeHTMLTags(descEspecial);
				
				int idOtorgaPoder = Integer.valueOf(bean.getProperty("ID_OPODER_EP_PK").toString());
				String apendiceRevocados = "";				
				GenericDataBean gdbRevocaciones = MngDataPoderes.query_REVOCACIONES(idOtorgaPoder);
				for(int j=0; j < gdbRevocaciones.beans.size(); j++){
					GenericBean beanRevocacion = gdbRevocaciones.get(j);	
					String apendiceRevocadoCurrent = "";								
					  apendiceRevocadoCurrent = beanRevocacion.getProperty("DESC_REVOCA") == null ? "" : beanRevocacion.getProperty("DESC_REVOCA").toString(); 
					  if(!apendiceRevocados.contains(apendiceRevocadoCurrent)){						
						  apendiceRevocados += apendiceRevocadoCurrent.trim().length()>0 ? apendiceRevocadoCurrent + "\n" : "";	
					  }									  
				}
				
				if(!esc.equals(escrituraCurrent) || (esc.equals(escrituraCurrent) && !gdb.get(i-1).getProperty("NOM_GRUPO").toString().equals(grupoDeApoderado)) 
						||(esc.equals(escrituraCurrent) && gdb.get(i-1).getProperty("NOM_GRUPO").toString().equals(grupoDeApoderado) && !gdb.get(i-1).getProperty("NOM_EMPRESA").toString().equals(empresa))
						||(esc.equals(escrituraCurrent) && gdb.get(i-1).getProperty("NOM_GRUPO").toString().equals(grupoDeApoderado) && !gdb.get(i-1).getProperty("IND_TIPO_ESCRITURA").toString().equals(tipoPoder))){								
				
					numeroFila++;
					HSSFRow tableHeaderRow = sheet.createRow((short) i+numeroFila);
					switch(tipoPoder){
						case "PG": 					
							tableHeaderRow.createCell(0).setCellValue("Escritura No.");											
							tableHeaderRow.createCell(1).setCellValue("Fecha");										
							tableHeaderRow.createCell(2).setCellValue("Apoderados");
							tableHeaderRow.createCell(3).setCellValue("Poder");
							tableHeaderRow.createCell(4).setCellValue("Actos de Dominio");										
							tableHeaderRow.createCell(5).setCellValue("Actos de Administraci�n");									
							tableHeaderRow.createCell(6).setCellValue("T�tulos de Cr�dito");										
							tableHeaderRow.createCell(7).setCellValue("Pleitos y Cobranzas");										
							tableHeaderRow.createCell(8).setCellValue("Especial");
							tableHeaderRow.createCell(9).setCellValue("Vigencia");
							break;
						case "CP": 																			
							tableHeaderRow.createCell(0).setCellValue("Fecha");		
							tableHeaderRow.createCell(1).setCellValue("Poder");
							tableHeaderRow.createCell(2).setCellValue("Apoderados");		
							tableHeaderRow.createCell(3).setCellValue("Descripci�n");										
							tableHeaderRow.createCell(4).setCellValue("Vigencia");					
							break;
						case "PE": 					
							tableHeaderRow.createCell(0).setCellValue("Escritura No.");										
							tableHeaderRow.createCell(1).setCellValue("Fecha");		
							tableHeaderRow.createCell(2).setCellValue("Poder");
							tableHeaderRow.createCell(3).setCellValue("Apoderados");		
							tableHeaderRow.createCell(4).setCellValue("Descripci�n");										
							tableHeaderRow.createCell(5).setCellValue("Vigencia");				
							break;
					}
					numeroFila++;
					Iterator<Cell> tableHeaderIterator = tableHeaderRow.cellIterator();
					while(tableHeaderIterator.hasNext()){
						tableHeaderIterator.next().setCellStyle(styles.get("fondoGris"));
					}
					
					HSSFRow dataRow = sheet.createRow((short) i+numeroFila); 								
					switch(tipoPoder){
					case "PG": 		
						String escToShow = Integer.valueOf(bean.getProperty("IND_REQUIERE_PROTO").toString())==1 ? esc:"N/A";
						dataRow.createCell(0).setCellValue(escToShow);					
						dataRow.createCell(1).setCellValue(fec);					
						dataRow.createCell(2).setCellValue(apod);	
						dataRow.createCell(3).setCellValue(pod);
						dataRow.createCell(4).setCellValue(ad);					
						dataRow.createCell(5).setCellValue(aa);					
						dataRow.createCell(6).setCellValue(tc);					
						dataRow.createCell(7).setCellValue(pc);					
						dataRow.createCell(8).setCellValue(descEspecial);							
						dataRow.createCell(9).setCellValue(vig);
						break;
					case "CP": 																
						dataRow.createCell(0).setCellValue(fecCP);
						dataRow.createCell(1).setCellValue(pod);
						dataRow.createCell(2).setCellValue(apod);
						dataRow.createCell(3).setCellValue(desc);					
						dataRow.createCell(4).setCellValue(vig);						
						break;
					case "PE": 				
						String escToShow1 = Integer.valueOf(bean.getProperty("IND_REQUIERE_PROTO").toString())==1 ? esc:"N/A";
						dataRow.createCell(0).setCellValue(escToShow1);					
						dataRow.createCell(1).setCellValue(fec);
						dataRow.createCell(2).setCellValue(pod);
						dataRow.createCell(3).setCellValue(apod);
						dataRow.createCell(4).setCellValue(desc);					
						dataRow.createCell(5).setCellValue(vig);						
						break;
					}					
					
					Iterator<Cell> dataRowIterator = dataRow.cellIterator();
					while(dataRowIterator.hasNext()){
						dataRowIterator.next().setCellStyle(styles.get("bordeDelgado"));
					}
					escrituraCurrent = esc;
				
					if(apendiceRevocados.length()>0){
						numeroFila++;
						dataRow = sheet.createRow((short) i+numeroFila); 
						dataRow.createCell(0).setCellValue(removeHTMLTags(apendiceRevocados));
					}
					
				}											
				else{
					numeroFila--;	
				}
			}	
			
			sheet.autoSizeColumn(0);
			sheet.autoSizeColumn(1);
			sheet.autoSizeColumn(2);
			sheet.autoSizeColumn(3);
			sheet.autoSizeColumn(4);
			sheet.autoSizeColumn(5);
			sheet.autoSizeColumn(6);
			sheet.autoSizeColumn(7);
			sheet.autoSizeColumn(8);
		
			downloadExcel(wb, "ReportePoderesPorAsunto");
		}	
	
	private static Map<String, CellStyle> createStyles(Workbook wb){
        HashMap<String, CellStyle> styles = new HashMap<String, CellStyle>();
                
	        CellStyle blueBackgroundStyle = wb.createCellStyle();
	
	        Font font = wb.createFont();
	        font.setColor(HSSFColor.WHITE.index);
	        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
	        blueBackgroundStyle.setFont(font);
	        
	        blueBackgroundStyle.setFillForegroundColor(new HSSFColor.LIGHT_BLUE().getIndex());
	        blueBackgroundStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);	        
        
	        styles.put("fondoAzul", blueBackgroundStyle);
               
        	CellStyle grayBackgroundStyle = wb.createCellStyle();	         
        	Font font2 = wb.createFont();
	        font2.setColor(HSSFColor.BLACK.index);
	        font2.setBoldweight(Font.BOLDWEIGHT_BOLD);
	        grayBackgroundStyle.setFont(font2);
	        
	        grayBackgroundStyle.setFillForegroundColor(new HSSFColor.GREY_25_PERCENT().getIndex());
	        grayBackgroundStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
	        grayBackgroundStyle.setBorderTop(CellStyle.BORDER_THIN);
	        grayBackgroundStyle.setBorderRight(CellStyle.BORDER_THIN);
	        grayBackgroundStyle.setBorderBottom(CellStyle.BORDER_THIN);
	        grayBackgroundStyle.setBorderLeft(CellStyle.BORDER_THIN);
	        
	        grayBackgroundStyle.setAlignment(CellStyle.ALIGN_CENTER);
	        
	        styles.put("fondoGris", grayBackgroundStyle);  
	        
	        CellStyle borderStyle = wb.createCellStyle();	         
        	Font font3 = wb.createFont();
	        font3.setColor(HSSFColor.BLACK.index);
	        borderStyle.setFont(font3);
	        	        	        
	        borderStyle.setBorderTop(CellStyle.BORDER_THIN);
	        borderStyle.setBorderRight(CellStyle.BORDER_THIN);
	        borderStyle.setBorderBottom(CellStyle.BORDER_THIN);
	        borderStyle.setBorderLeft(CellStyle.BORDER_THIN);
	        
	        styles.put("bordeDelgado", borderStyle);
	        
	        CellStyle boldStyle = wb.createCellStyle();	    	
	        Font font4 = wb.createFont();
	        font4.setColor(HSSFColor.BLACK.index);
	        font4.setBoldweight(Font.BOLDWEIGHT_BOLD);
	        font4.setItalic(true);
	        boldStyle.setFont(font4);
	        	      
	        styles.put("negrita", boldStyle);
	        
        return styles;
    }
	
	public void downloadExcel(HSSFWorkbook wb, String fileName){
		try{
		    ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
		    wb.write(outByteStream);
		    byte [] outArray = outByteStream.toByteArray();
		    response.setContentType("application/ms-excel");
		    response.setContentLength(outArray.length);
		    response.setHeader("Expires:", "0"); // eliminates browser caching
		    response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xls");
		    OutputStream outStream = response.getOutputStream();
		    outStream.write(outArray);
		    outStream.flush();
		 }
		 catch(IOException ex){
			 ex.printStackTrace();
		 }	
	}
	
	public String removeHTMLTags(String textoHTML){
		String textoPlano = "";
		String tagsRegex = "\\<[^\\>]*\\>";		
		textoHTML = textoHTML.replaceAll("</div>", "\n");
		textoHTML = textoHTML.replaceAll("<br/>", "\n");
		textoHTML = textoHTML.replaceAll("<br />", "\n");		
		textoHTML = textoHTML.replaceAll(tagsRegex, " ");
		textoHTML = textoHTML.replaceAll("&#9679;", " ");
		textoHTML = textoHTML.replaceAll("&amp;", "&");
		textoHTML = textoHTML.replaceAll("&quot;", "");
		textoHTML = textoHTML.replaceAll("&nbsp;", " ");
		textoHTML = textoHTML.replaceAll("null", "");
		textoHTML = textoHTML.replaceAll("&acute;", "�");
		textoHTML = textoHTML.replaceAll("&aacute;", "�");
		textoHTML = textoHTML.replaceAll("&eacute;", "�");
		textoHTML = textoHTML.replaceAll("&iacute;", "�");
		textoHTML = textoHTML.replaceAll("&oacute;", "�");
		textoHTML = textoHTML.replaceAll("&uacute;", "�");
		textoHTML = textoHTML.replaceAll("&Aacute;", "�");
		textoHTML = textoHTML.replaceAll("&Eacute;", "�");
		textoHTML = textoHTML.replaceAll("&Iacute;", "�");
		textoHTML = textoHTML.replaceAll("&Oacute;", "�");
		textoPlano = textoHTML.replaceAll("&Uacute;", "�");
		textoPlano = textoHTML.replaceAll("&ntilde;", "�");
		textoPlano = textoHTML.replaceAll("&Ntilde;", "�");
		textoPlano = textoHTML.replaceAll("&#39;", "'");
		return textoPlano;
	}

	public void generarExcelPoderesPorTipoPoder(String hiddenEmpresa, String txtTipoPoder, String apoderados, 
			String hiddenPoder, String txtEscritura){
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet();
		Map<String, CellStyle> styles = createStyles(wb);		
		String empresaCurrent = "";
		String escrituraCurrent = " ";
		String tipoPoderCurrent="";		
		GenericDataBean gdb = new GenericDataBean();
		try {					
			String tipoPoder = txtTipoPoder.replace("<ul><li>", "'");
			tipoPoder = tipoPoder.replace("</li><li>","','");
			tipoPoder = tipoPoder.replace("</li></ul>", "'");
			tipoPoder = tipoPoder.equals("<ul></ul>") ? null : tipoPoder;
			tipoPoder = tipoPoder.replace("Carta Poder","CP").replace("Poder Especial","PE").replace("Poder General","PG");
			String escritura = txtEscritura.equals("") ? "" : "'%"+txtEscritura+"%'";
			
			gdb = MngDataPoderes.query_PODERES_POR_TIPO_DE_PODER(hiddenEmpresa, tipoPoder, apoderados, hiddenPoder, escritura);
		} catch (Exception ex) {
			logger.error(ex.getMessage());        
		}
		
		int numeroFila = 0;
		for(int i=0; i < gdb.beans.size(); i++){
			GenericBean bean = gdb.get(i);
																	
			String empresa = bean.getProperty("NOM_EMPRESA").toString();
			String tipoEscritura = bean.getProperty("IND_TIPO_ESCRITURA").toString();
			String tipoPoder = bean.getProperty("DES_PODERTIPO").toString();
			if(!empresaCurrent.equals(empresa)){				
				if(i>0){
					sheet.createRow((short) i+numeroFila).createCell(0).setCellValue("");
					numeroFila++;
					
				}
				
				HSSFRow nombreEmpresaRow = sheet.createRow((short) i+numeroFila);
				Cell cell = nombreEmpresaRow.createCell(0);
				
				cell.setCellValue(empresa);		
				cell.setCellStyle(styles.get("fondoAzul"));
				sheet.addMergedRegion(new CellRangeAddress(i+numeroFila, i+numeroFila,0,8));
				
				//sheet.addMergedRegion(new CellRangeAddress(i+numeroFila, i+numeroFila,0,7));
				numeroFila++;
				empresaCurrent = empresa;	
				tipoPoderCurrent = "";		
				escrituraCurrent = "";
				
			}
			//numeroFila++;
			
			if(!tipoPoderCurrent.equals(tipoPoder)){	
				HSSFRow tipoPoderRow = sheet.createRow((short) i+numeroFila);
				Cell tipoPoderCell = tipoPoderRow.createCell(0);
				//tipoPoderCell.setCellValue((tipoEscritura.equals("PG") ? "Poderes Generales" : tipoEscritura.equals("PE") ? "Poderes Especiales" : tipoEscritura.equals("CP") ? "Cartas Poder" : "Escrituras de Revocaci�n"));
				tipoPoderCell.setCellValue(tipoPoder);
				tipoPoderCell.setCellStyle(styles.get("fondoAzul"));
				sheet.addMergedRegion(new CellRangeAddress(i+numeroFila, i+numeroFila,0,8));
				numeroFila++;
				escrituraCurrent = "";
			}
			if(!escrituraCurrent.equals(tipoEscritura)){	
				
				HSSFRow tipoEsrituraRow = sheet.createRow((short) i+numeroFila);
				Cell tipoEscrituraCell = tipoEsrituraRow.createCell(0);
				tipoEscrituraCell.setCellValue((tipoEscritura.equals("PG") ? "Poderes Generales" : tipoEscritura.equals("PE") ? "Poderes Especiales" : tipoEscritura.equals("CP") ? "Cartas Poder" : "Escrituras de Revocaci�n"));
				tipoEscrituraCell.setCellStyle(styles.get("negrita"));
				numeroFila++;
									
			}			
							
				if(tipoEscritura.equals("PG")){
					
					HSSFRow tableHeaderRow = sheet.createRow((short) i+numeroFila);
					tableHeaderRow.createCell(0).setCellValue("Escritura No.");											
					tableHeaderRow.createCell(1).setCellValue("Fecha");										
					tableHeaderRow.createCell(2).setCellValue("Apoderados");									
					tableHeaderRow.createCell(3).setCellValue("Actos de Dominio");										
					tableHeaderRow.createCell(4).setCellValue("Actos de Administraci�n");									
					tableHeaderRow.createCell(5).setCellValue("T�tulos de Cr�dito");										
					tableHeaderRow.createCell(6).setCellValue("Pleitos y Cobranzas");
					tableHeaderRow.createCell(7).setCellValue("Especial");	
					tableHeaderRow.createCell(8).setCellValue("Vigencia");						
					Iterator<Cell> tableHeaderIterator = tableHeaderRow.cellIterator();
					while(tableHeaderIterator.hasNext()){
						tableHeaderIterator.next().setCellStyle(styles.get("fondoGris"));
					}
					numeroFila++;
				}

				if(tipoEscritura.equals("CP")){
				
					HSSFRow tableHeaderRow = sheet.createRow((short) i+numeroFila);														
					tableHeaderRow.createCell(0).setCellValue("Fecha");		
					tableHeaderRow.createCell(1).setCellValue("Apoderados");		
					tableHeaderRow.createCell(2).setCellValue("Descripci�n");										
					tableHeaderRow.createCell(3).setCellValue("Vigencia");				
					Iterator<Cell> tableHeaderIterator = tableHeaderRow.cellIterator();
					while(tableHeaderIterator.hasNext()){
						tableHeaderIterator.next().setCellStyle(styles.get("fondoGris"));
					}
					numeroFila++;
				}
		
				if(tipoEscritura.equals("PE")){
				
					HSSFRow tableHeaderRow = sheet.createRow((short) i+numeroFila);
					tableHeaderRow.createCell(0).setCellValue("Escritura No.");										
					tableHeaderRow.createCell(1).setCellValue("Fecha");		
					tableHeaderRow.createCell(2).setCellValue("Apoderados");		
					tableHeaderRow.createCell(3).setCellValue("Descripci�n");										
					tableHeaderRow.createCell(4).setCellValue("Vigencia");						
					Iterator<Cell> tableHeaderIterator = tableHeaderRow.cellIterator();
					while(tableHeaderIterator.hasNext()){
						tableHeaderIterator.next().setCellStyle(styles.get("fondoGris"));
					}
					numeroFila++;
				}
			
				String esc = bean.getProperty("DES_ESCRITURA") == null ? "" : bean.getProperty("DES_ESCRITURA") == null ? "" : bean.getProperty("DES_ESCRITURA").toString();
				String fec = bean.getProperty("FEC_OTORGAMIENTO_INSTR") == null ? "" : bean.getProperty("FEC_OTORGAMIENTO_INSTR").toString();				
				String fecCP = bean.getProperty("FEC_FECHA") == null ? "" : bean.getProperty("FEC_FECHA").toString();
				String apod = bean.getProperty("DESC_APODERADOS") == null ? "" : bean.getProperty("DESC_APODERADOS").toString();			
				apod = removeHTMLTags(apod);
				String ad = bean.getProperty("DESC_ACTOSDOMINIO") == null ? "" : bean.getProperty("DESC_ACTOSDOMINIO").toString();
				ad = removeHTMLTags(ad);
				String aa = bean.getProperty("DESC_ACTOSADMON") == null ? "" : bean.getProperty("DESC_ACTOSADMON").toString();
				aa = removeHTMLTags(aa);
				String tc = bean.getProperty("DESC_TITULOSCREDITO") == null ? "" : bean.getProperty("DESC_TITULOSCREDITO").toString();
				tc = removeHTMLTags(tc);
				String pc = bean.getProperty("DESC_PLEITOSCOBRANZA") == null ? "" : bean.getProperty("DESC_PLEITOSCOBRANZA").toString();
				pc = removeHTMLTags(pc);
				String vig = bean.getProperty("FEC_VIGENCIAFIN") == null ? "" : bean.getProperty("FEC_VIGENCIAFIN").toString();
				String desc = bean.getProperty("DES_PODER") == null ? "" : bean.getProperty("DES_PODER").toString();
				desc = removeHTMLTags(desc);
				String esp = bean.getProperty("DESC_PODER_ESPECIAL") == null ? "" : bean.getProperty("DESC_PODER_ESPECIAL").toString();
				esp = removeHTMLTags(esp);
								
				int idOtorgaPoder = Integer.valueOf(bean.getProperty("ID_OPODER_EP_PK").toString());
				String apendiceRevocados = "";				
				GenericDataBean gdbRevocaciones = MngDataPoderes.query_REVOCACIONES(idOtorgaPoder);
				for(int j=0; j < gdbRevocaciones.beans.size(); j++){
					GenericBean beanRevocacion = gdbRevocaciones.get(j);	
					String apendiceRevocadoCurrent = "";								
					  apendiceRevocadoCurrent = beanRevocacion.getProperty("DESC_REVOCA") == null ? "" : beanRevocacion.getProperty("DESC_REVOCA").toString(); 
					  if(!apendiceRevocados.contains(apendiceRevocadoCurrent)){						
						  apendiceRevocados += apendiceRevocadoCurrent.trim().length()>0 ? apendiceRevocadoCurrent + "\n" : "";	
					  }									  
				}
				
				HSSFRow dataRow = sheet.createRow((short) i+numeroFila); 								
			
				//case "PG":
				if(tipoEscritura.equals("PG")){
					dataRow.createCell(0).setCellValue(esc);					
					dataRow.createCell(1).setCellValue(fec);					
					dataRow.createCell(2).setCellValue(apod);					
					dataRow.createCell(3).setCellValue(ad);					
					dataRow.createCell(4).setCellValue(aa);					
					dataRow.createCell(5).setCellValue(tc);					
					dataRow.createCell(6).setCellValue(pc);		
					dataRow.createCell(7).setCellValue(esp);
					dataRow.createCell(8).setCellValue(vig);							
				}
				//case "CP":
				if(tipoEscritura.equals("CP")){									
					dataRow.createCell(0).setCellValue(fecCP);
					dataRow.createCell(1).setCellValue(apod);
					dataRow.createCell(2).setCellValue(desc);					
					dataRow.createCell(3).setCellValue(vig);						
				//	break;
				}
				//case "PE": 	
				if(tipoEscritura.equals("PE")){
					dataRow.createCell(0).setCellValue(esc);					
					dataRow.createCell(1).setCellValue(fec);
					dataRow.createCell(2).setCellValue(apod);
					dataRow.createCell(3).setCellValue(desc);					
					dataRow.createCell(4).setCellValue(vig);						
					//break;
				}					
				
				Iterator<Cell> dataRowIterator = dataRow.cellIterator();
				while(dataRowIterator.hasNext()){
					dataRowIterator.next().setCellStyle(styles.get("bordeDelgado"));
				}
				
				if(apendiceRevocados.length()>0){
					numeroFila++;
					dataRow = sheet.createRow((short) i+numeroFila); 
					dataRow.createCell(0).setCellValue(removeHTMLTags(apendiceRevocados));
				}
						
				numeroFila++;
				
				sheet.autoSizeColumn(0);
				sheet.autoSizeColumn(1);
				sheet.autoSizeColumn(2);
				sheet.autoSizeColumn(3);
				sheet.autoSizeColumn(4);
				sheet.autoSizeColumn(5);
				sheet.autoSizeColumn(6);
				sheet.autoSizeColumn(7);
				sheet.autoSizeColumn(8);
				
				escrituraCurrent = tipoEscritura;	
				tipoPoderCurrent = tipoPoder;
				
			}			
		
		downloadExcel(wb, "ReportePoderesPorTipoPoder");
	}
	
	public void generarExcelPoderesPorVigencia(String hiddenEmpresa, String txtFechaDesde, String txtFechaHasta,String txtTipoPoder, String apoderados, 
			String hiddenPoder, String txtEscritura){
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet();
		Map<String, CellStyle> styles = createStyles(wb);		
		String empresaCurrent = "";
		String escrituraCurrent = " ";		
		GenericDataBean gdb = new GenericDataBean();
		try {					
			String tipoPoder = txtTipoPoder.replace("<ul><li>", "'");
			tipoPoder = tipoPoder.replace("</li><li>","','");
			tipoPoder = tipoPoder.replace("</li></ul>", "'");
			tipoPoder = tipoPoder.equals("<ul></ul>") ? null : tipoPoder;
			tipoPoder = tipoPoder.replace("Carta Poder","CP").replace("Poder Especial","PE").replace("Poder General","PG");
			String escritura = txtEscritura.equals("") ? "" : "'%"+txtEscritura+"%'";
			
			gdb = MngDataPoderes.query_PODERES_POR_VIGENCIA(hiddenEmpresa, txtFechaDesde, txtFechaHasta,tipoPoder, apoderados, hiddenPoder, escritura);
		} catch (Exception ex) {
			logger.error(ex.getMessage());        
		}
		
		int numeroFila = 0;
		for(int i=0; i < gdb.beans.size(); i++){
			GenericBean bean = gdb.get(i);
																	
			String empresa = bean.getProperty("NOM_EMPRESA").toString();
			String tipoEscritura = bean.getProperty("IND_TIPO_ESCRITURA").toString();
			String tipoPoder = bean.getProperty("DES_PODERTIPO").toString();
			if(!empresaCurrent.equals(empresa)){				
				if(i>0){
					sheet.createRow((short) i+numeroFila).createCell(0).setCellValue("");
					numeroFila++;					
				}
				
				HSSFRow nombreEmpresaRow = sheet.createRow((short) i+numeroFila);
				Cell cell = nombreEmpresaRow.createCell(0);
				
				cell.setCellValue(empresa);		
				cell.setCellStyle(styles.get("fondoAzul"));
				sheet.addMergedRegion(new CellRangeAddress(i+numeroFila, i+numeroFila,0,8));
				
				numeroFila++;
				empresaCurrent = empresa;					
				escrituraCurrent = "";				
			}		
					
			if(!escrituraCurrent.equals(tipoEscritura)){					
				HSSFRow tipoEsrituraRow = sheet.createRow((short) i+numeroFila);
				Cell tipoEscrituraCell = tipoEsrituraRow.createCell(0);
				tipoEscrituraCell.setCellValue((tipoEscritura.equals("PG") ? "Poderes Generales" : tipoEscritura.equals("PE") ? "Poderes Especiales" : tipoEscritura.equals("CP") ? "Cartas Poder" : "Escrituras de Revocaci�n"));
				tipoEscrituraCell.setCellStyle(styles.get("negrita"));
				numeroFila++;								
			}			
							
				if(tipoEscritura.equals("PG")){
					
					HSSFRow tableHeaderRow = sheet.createRow((short) i+numeroFila);
					tableHeaderRow.createCell(0).setCellValue("Escritura No.");
					tableHeaderRow.createCell(1).setCellValue("Fecha vencimiento");
					tableHeaderRow.createCell(2).setCellValue("Poder");
					tableHeaderRow.createCell(3).setCellValue("Apoderados");									
					tableHeaderRow.createCell(4).setCellValue("Actos de Dominio");										
					tableHeaderRow.createCell(5).setCellValue("Actos de Administraci�n");									
					tableHeaderRow.createCell(6).setCellValue("T�tulos de Cr�dito");										
					tableHeaderRow.createCell(7).setCellValue("Pleitos y Cobranzas");
					tableHeaderRow.createCell(8).setCellValue("Especial");											
					Iterator<Cell> tableHeaderIterator = tableHeaderRow.cellIterator();
					while(tableHeaderIterator.hasNext()){
						tableHeaderIterator.next().setCellStyle(styles.get("fondoGris"));
					}
					numeroFila++;
				}

				if(tipoEscritura.equals("CP")){
				
					HSSFRow tableHeaderRow = sheet.createRow((short) i+numeroFila);														
					tableHeaderRow.createCell(0).setCellValue("Fecha vencimiento");		
					tableHeaderRow.createCell(1).setCellValue("Poder");	
					tableHeaderRow.createCell(2).setCellValue("Apoderados");		
					tableHeaderRow.createCell(3).setCellValue("Descripci�n");																		
					Iterator<Cell> tableHeaderIterator = tableHeaderRow.cellIterator();
					while(tableHeaderIterator.hasNext()){
						tableHeaderIterator.next().setCellStyle(styles.get("fondoGris"));
					}
					numeroFila++;
				}
		
				if(tipoEscritura.equals("PE")){
				
					HSSFRow tableHeaderRow = sheet.createRow((short) i+numeroFila);
					tableHeaderRow.createCell(0).setCellValue("Escritura No.");										
					tableHeaderRow.createCell(1).setCellValue("Fecha vencimiento");		
					tableHeaderRow.createCell(2).setCellValue("Poder");	
					tableHeaderRow.createCell(3).setCellValue("Apoderados");		
					tableHeaderRow.createCell(4).setCellValue("Descripci�n");																			
					Iterator<Cell> tableHeaderIterator = tableHeaderRow.cellIterator();
					while(tableHeaderIterator.hasNext()){
						tableHeaderIterator.next().setCellStyle(styles.get("fondoGris"));
					}
					numeroFila++;
				}
			
				String esc = bean.getProperty("DES_ESCRITURA") == null ? "" : bean.getProperty("DES_ESCRITURA") == null ? "" : bean.getProperty("DES_ESCRITURA").toString();				
				String apod = bean.getProperty("DESC_APODERADOS") == null ? "" : bean.getProperty("DESC_APODERADOS").toString();			
				apod = removeHTMLTags(apod);
				String ad = bean.getProperty("DESC_ACTOSDOMINIO") == null ? "" : bean.getProperty("DESC_ACTOSDOMINIO").toString();
				ad = removeHTMLTags(ad);
				String aa = bean.getProperty("DESC_ACTOSADMON") == null ? "" : bean.getProperty("DESC_ACTOSADMON").toString();
				aa = removeHTMLTags(aa);
				String tc = bean.getProperty("DESC_TITULOSCREDITO") == null ? "" : bean.getProperty("DESC_TITULOSCREDITO").toString();
				tc = removeHTMLTags(tc);
				String pc = bean.getProperty("DESC_PLEITOSCOBRANZA") == null ? "" : bean.getProperty("DESC_PLEITOSCOBRANZA").toString();
				pc = removeHTMLTags(pc);
				String vig = bean.getProperty("FEC_VIGENCIAFIN") == null ? "" : bean.getProperty("FEC_VIGENCIAFIN").toString();
				String desc = bean.getProperty("DES_PODER") == null ? "" : bean.getProperty("DES_PODER").toString();
				desc = removeHTMLTags(desc);
				String esp = bean.getProperty("DESC_PODER_ESPECIAL") == null ? "" : bean.getProperty("DESC_PODER_ESPECIAL").toString();
				esp = removeHTMLTags(esp);
								
				int idOtorgaPoder = Integer.valueOf(bean.getProperty("ID_OPODER_EP_PK").toString());
				String apendiceRevocados = "";				
				GenericDataBean gdbRevocaciones = MngDataPoderes.query_REVOCACIONES(idOtorgaPoder);
				for(int j=0; j < gdbRevocaciones.beans.size(); j++){
					GenericBean beanRevocacion = gdbRevocaciones.get(j);	
					String apendiceRevocadoCurrent = "";								
					  apendiceRevocadoCurrent = beanRevocacion.getProperty("DESC_REVOCA") == null ? "" : beanRevocacion.getProperty("DESC_REVOCA").toString(); 
					  if(!apendiceRevocados.contains(apendiceRevocadoCurrent)){						
						  apendiceRevocados += apendiceRevocadoCurrent.trim().length()>0 ? apendiceRevocadoCurrent + "\n" : "";	
					  }									  
				}
				
				HSSFRow dataRow = sheet.createRow((short) i+numeroFila); 								
			
				//case "PG":
				if(tipoEscritura.equals("PG")){
					dataRow.createCell(0).setCellValue(esc);					
					dataRow.createCell(1).setCellValue(vig);
					dataRow.createCell(2).setCellValue(tipoPoder);
					dataRow.createCell(3).setCellValue(apod);					
					dataRow.createCell(4).setCellValue(ad);					
					dataRow.createCell(5).setCellValue(aa);					
					dataRow.createCell(6).setCellValue(tc);					
					dataRow.createCell(7).setCellValue(pc);		
					dataRow.createCell(8).setCellValue(esp);												
				}
				//case "CP":
				if(tipoEscritura.equals("CP")){									
					dataRow.createCell(0).setCellValue(vig);
					dataRow.createCell(1).setCellValue(tipoPoder);
					dataRow.createCell(2).setCellValue(apod);
					dataRow.createCell(3).setCellValue(desc);															
				}
				//case "PE": 	
				if(tipoEscritura.equals("PE")){
					dataRow.createCell(0).setCellValue(esc);					
					dataRow.createCell(1).setCellValue(vig);
					dataRow.createCell(2).setCellValue(tipoPoder);
					dataRow.createCell(3).setCellValue(apod);
					dataRow.createCell(4).setCellValue(desc);															
				}					
				
				Iterator<Cell> dataRowIterator = dataRow.cellIterator();
				while(dataRowIterator.hasNext()){
					dataRowIterator.next().setCellStyle(styles.get("bordeDelgado"));
				}
				
				if(apendiceRevocados.length()>0){
					numeroFila++;
					dataRow = sheet.createRow((short) i+numeroFila); 
					dataRow.createCell(0).setCellValue(removeHTMLTags(apendiceRevocados));
				}
						
				numeroFila++;
				
				sheet.autoSizeColumn(0);
				sheet.autoSizeColumn(1);
				sheet.autoSizeColumn(2);
				sheet.autoSizeColumn(3);
				sheet.autoSizeColumn(4);
				sheet.autoSizeColumn(5);
				sheet.autoSizeColumn(6);
				sheet.autoSizeColumn(7);
				sheet.autoSizeColumn(8);
				
				escrituraCurrent = tipoEscritura;					
				
			}			
		
		downloadExcel(wb, "ReportePoderesPorVigencia");
	}
	
}

package mx.com.televisa.derechocorporativo.servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.com.televisa.derechocorporativo.data.Record;
import mx.com.televisa.derechocorporativo.model.Catalog;
import mx.com.televisa.derechocorporativo.model.CatalogElement;
import mx.com.televisa.derechocorporativo.modules.reports.TenCascadaPlanoServlet;
import mx.com.televisa.derechocorporativo.modules.reports.tenecascada.TenenciaCascada;
import mx.com.televisa.derechocorporativo.util.ErrorsUtils;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFColor;

@WebServlet("/ExcelArbolCascadaServlet")
public class ExcelArbolCascadaServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3169424113022973717L;
	final static Logger log = Logger.getLogger(ExcelArbolCascadaServlet.class);

	public void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.info("Reporte de tenencia en cascada");
		String empresaId = request.getParameter("hiddenEmpresa");
		String fecha = "";

		String paramConsolida = (request.getParameter("hiddenConsolida") != null) ? request
				.getParameter("hiddenConsolida") : "";
		String paramSegmento = (request.getParameter("hiddenSegmento") != null) ? request
				.getParameter("hiddenSegmento") : "";
		String paramClasificacion = (request
				.getParameter("hiddenClasificacion") != null) ? request
				.getParameter("hiddenClasificacion") : "";
		String paramPais = (request.getParameter("hiddenPais") != null) ? request
				.getParameter("hiddenPais") : "";
		String paramNoEmpOracle = (request.getParameter("hiddenNoEmpOracle") != null) ? request
				.getParameter("hiddenNoEmpOracle") : "";
		String paramGiro = (request.getParameter("hiddenGiro") != null) ? request
				.getParameter("hiddenGiro") : "";
		String paramPorcentaje = (request.getParameter("txtPorcentaje") != null) ? request
				.getParameter("txtPorcentaje") : "";
		String selectPorcentaje = (request.getParameter("selectPorcentaje") != null) ? request
				.getParameter("selectPorcentaje") : "";
		String selectPorcentajeCual = (request
				.getParameter("selectPorcentajeCual") != null) ? request
				.getParameter("selectPorcentajeCual") : "";

		ArrayList<Record> treeTableRecords = null;
		treeTableRecords = TenenciaCascada.getTreeDataRows(empresaId, fecha,
				paramConsolida, paramSegmento, paramClasificacion, paramPais,
				paramNoEmpOracle, paramGiro, paramPorcentaje, selectPorcentaje,
				selectPorcentajeCual);

		if (treeTableRecords != null) {
			log.info("generando el reporte....");
			List<CatalogElement> catalogElements = new Catalog(
					"DERCORP_BUSQUEDA_VIEW").getList(
					"DISTINCT ID_EMPRESA, DENOM_ACTUAL", "WHERE ID_EMPRESA = "
							+ empresaId);
			CatalogElement elem = catalogElements.get(0);
			String nomEmpresa = elem.getName();
			HSSFWorkbook wb = new HSSFWorkbook();
			wb.setSheetName(0, "Reporte en Cascada");
			HSSFSheet sheet = wb.createSheet();
			// titulo del reporte
			HSSFRow rowTitulo = sheet.createRow((short) 0);
			sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 9));// merge de
																	// celdas
																	// para
																	// titulo
			sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 9));// merge de
																	// celdas
																	// para
																	// filtros
			sheet.addMergedRegion(new CellRangeAddress(3, 3, 0, 9));// merge de
																	// celdas
																	// para
																	// fecha
			Cell cellTitulo = rowTitulo.createCell(0);
			cellTitulo.setCellValue("Reporte de Tenencia en Cascada");
			cellTitulo.setCellStyle(createStyles(wb).get("titleStyle"));

			HSSFRow rowFiltros = sheet.createRow((short) 2);
			Cell cellFiltros = rowFiltros.createCell(0);
			String txtFiltros = "";
			if (paramConsolida.length() > 0)
				txtFiltros += "Consolida: " + paramConsolida + "\n";
			if (paramSegmento.length() > 0)
				txtFiltros += "Segmento: " + paramSegmento + "\n";
			if (paramClasificacion.length() > 0)
				txtFiltros += "Clasificación: " + paramClasificacion + "\n";
			if (paramPais.length() > 0)
				txtFiltros += "País: " + paramPais + "\n";
			if (paramNoEmpOracle.length() > 0)
				txtFiltros += "Núm de Empresa: " + paramNoEmpOracle + "\n";
			if (paramGiro.length() > 0)
				txtFiltros += "Giro: " + paramGiro + "\n";
			if (paramPorcentaje.length() > 0)
				txtFiltros += "Porcentaje: " + paramPorcentaje + "\n";
			if (selectPorcentajeCual.length() > 0)
				txtFiltros += "Porcentaje Seleccionado: "
						+ selectPorcentajeCual + "\n";
			CellStyle style = wb.createCellStyle();
			style.setWrapText(true);
			cellFiltros.setCellStyle(style);
			log.info("Filtros seleccionados: " + txtFiltros);
			cellFiltros.setCellValue(txtFiltros);
			cellFiltros.getRow().setHeight((short) 1500);
			cellFiltros.getCellStyle().setAlignment(HSSFCellStyle.VERTICAL_TOP);

			// fecha
			Calendar fechaEmision = Calendar.getInstance();

			String[] meses = new String[] { "Enero", "Febebro", "Marzo",
					"Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre",
					"Octubre", "Noviembre", "Diciembre" };
			int anio = fechaEmision.get(Calendar.YEAR);
			int mes = fechaEmision.get(Calendar.MONTH);
			HSSFRow rowFecha = sheet.createRow((short) 3);
			Cell cellFecha = rowFecha.createCell(0);
			cellFecha.setCellValue("Fecha de emisión: " + meses[mes] + "/"
					+ anio);

			List<String> listTitulosHeader = new ArrayList<String>();
			listTitulosHeader.add("# Empresa");
			listTitulosHeader.add(nomEmpresa);
			listTitulosHeader.add("% Directo");
			listTitulosHeader.add("% Indirecto");
			listTitulosHeader.add("Consolida");
			listTitulosHeader.add("Segmento");
			listTitulosHeader.add("Clasificación");
			listTitulosHeader.add("País");
			listTitulosHeader.add("Giro");
			listTitulosHeader.add("División");

			HSSFRow row = sheet.createRow((short) 4);
			for (int numCell = 0; numCell < listTitulosHeader.size(); numCell++) {
				Cell cell = row.createCell(numCell);
				cell.setCellValue(listTitulosHeader.get(numCell));
				cell.setCellStyle(createStyles(wb).get("headerTable"));
			}

			int incremento = 5;
			String numEmpresa = "";
			String nomEmpresaRecord = "";
			String porcDirecto = "";
			String porcIndirecto = "";
			String consolida = "";
			String segmento = "";
			String clasificacion = "";
			String pais = "";
			String giro = "";
			String division = "";

			for (Record record : treeTableRecords) {

				if (record.get("NO_EMP_ORACLE") != null
						&& !record.get("NO_EMP_ORACLE").isEmpty()) {
					numEmpresa = record.get("NO_EMP_ORACLE");
				} else {
					numEmpresa = "Vacío";
				}

				if (record.get("NOM_EMPRESA") != null
						&& !record.get("NOM_EMPRESA").isEmpty()) {
					nomEmpresaRecord = record.get("NOM_EMPRESA");
				} else {
					nomEmpresaRecord = "Vacío";
				}
				if (record.get("DIRECTO") != null
						&& !record.get("DIRECTO").isEmpty()) {
					porcDirecto = record.get("DIRECTO");
				} else {
					porcDirecto = "Vacío";
				}
				if (record.get("INDIRECTO") != null
						&& !record.get("INDIRECTO").isEmpty()) {
					porcIndirecto = record.get("INDIRECTO");
				} else {
					porcIndirecto = "Vacío";
				}
				if (record.get("CONSOLIDA") != null
						&& !record.get("CONSOLIDA").isEmpty()) {
					consolida = record.get("CONSOLIDA");
				} else {
					consolida = "Vacío";
				}

				if (record.get("SEGMENTO") != null
						&& !record.get("SEGMENTO").isEmpty()) {
					segmento = record.get("SEGMENTO");
				} else {
					segmento = "Vacío";
				}
				if (record.get("CLASIFICACION") != null
						&& !record.get("CLASIFICACION").isEmpty()) {
					clasificacion = record.get("CLASIFICACION");
				} else {
					clasificacion = "Vacío";
				}
				if (record.get("PAIS") != null && !record.get("PAIS").isEmpty()) {
					pais = record.get("PAIS");
				} else {
					pais = "Vacío";
				}
				if (record.get("GIRO") != null && !record.get("GIRO").isEmpty()) {
					giro = record.get("GIRO");
				} else {
					giro = "Vacío";
				}
				if (record.get("DIVISION") != null
						&& !record.get("DIVISION").isEmpty()) {
					division = record.get("DIVISION");
				} else {
					division = "Vacío";
				}

				HSSFRow row1 = sheet.createRow((short) incremento);
				row1.createCell((short) 0).setCellValue(numEmpresa);
				row1.createCell((short) 1).setCellValue(nomEmpresaRecord);
				row1.createCell((short) 2).setCellValue(porcDirecto);
				row1.createCell((short) 3).setCellValue(porcIndirecto);
				row1.createCell((short) 4).setCellValue(consolida);
				row1.createCell((short) 5).setCellValue(segmento);
				row1.createCell((short) 6).setCellValue(clasificacion);
				row1.createCell((short) 7).setCellValue(pais);
				row1.createCell((short) 8).setCellValue(giro);
				row1.createCell((short) 9).setCellValue(division);
				// se ajusta la celda al contenido
				for (int numCell = 0; numCell < listTitulosHeader.size(); numCell++) {
					sheet.autoSizeColumn(numCell);
				}
				incremento++;
			}
			log.info("Exportando el reporte");
			ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
			wb.write(outByteStream);
			byte[] outArray = outByteStream.toByteArray();
			response.setContentType("application/ms-excel");
			response.setContentLength(outArray.length);
			response.setHeader("Expires:", "0"); // eliminates browser caching
			response.setHeader("Content-Disposition",
					"attachment; filename=ReporteCascada.xls");
			OutputStream outStream = response.getOutputStream();
			outStream.write(outArray);
			outStream.flush();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(ErrorsUtils.getStackTraceAsString(e));
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(ErrorsUtils.getStackTraceAsString(e));
			e.printStackTrace();

		}
	}

	public static Map<String, CellStyle> createStyles(HSSFWorkbook wb) {
		HashMap<String, CellStyle> styles = new HashMap<String, CellStyle>();

		CellStyle headerTable = wb.createCellStyle();

		Font font = wb.createFont();
		font.setColor(HSSFColor.WHITE.index);
		headerTable.setFont(font);
		setColorAzulCustom(wb);
		headerTable.setFillForegroundColor(HSSFColor.BLUE.index);
		headerTable.setFillPattern(CellStyle.SOLID_FOREGROUND);
		headerTable.setAlignment(CellStyle.ALIGN_CENTER);
		font.setBoldweight(font.BOLDWEIGHT_BOLD);
		headerTable.setFont(font);
		styles.put("headerTable", headerTable);

		font = wb.createFont();
		CellStyle titleStyle = wb.createCellStyle();
		titleStyle.setAlignment(CellStyle.ALIGN_CENTER);
		font.setBoldweight(font.BOLDWEIGHT_BOLD);
		font.setFontHeightInPoints((short) 15);
		titleStyle.setFont(font);
		styles.put("titleStyle", titleStyle);
		
		CellStyle headerTableLeft = wb.createCellStyle();

		font = wb.createFont();
		font.setColor(HSSFColor.WHITE.index);
		headerTableLeft.setFont(font);
		setColorAzulCustom(wb);
		headerTableLeft.setFillForegroundColor(HSSFColor.BLUE.index);
		headerTableLeft.setFillPattern(CellStyle.SOLID_FOREGROUND);
		headerTableLeft.setAlignment(CellStyle.ALIGN_LEFT);
		font.setBoldweight(font.BOLDWEIGHT_BOLD);
		headerTableLeft.setFont(font);
		styles.put("headerTableLeft", headerTableLeft);

		return styles;
	}

	public static void setColorAzulCustom(HSSFWorkbook wb) {
		
		HSSFPalette palette = wb.getCustomPalette();
		// HSSFColor colorCustom = palette.addColor((byte) r, (byte) g, (byte)
		// b);
		// se sobrescribe el color azul a color azul de Pendium
		palette.setColorAtIndex(HSSFColor.BLUE.index, 
				(byte) 43, // RGB red // (0-255)43,73,99
				(byte) 73, // RGB green
				(byte) 99 // RGB blue
		);
	}
}

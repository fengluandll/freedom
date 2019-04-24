package mx.com.televisa.derechocorporativo.modules.reportsFlex;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;
import mx.com.televisa.derechocorporativo.data.packages.DERCORP_REPORTFLEX_PKG;
import mx.com.televisa.derechocorporativo.reflexion.ReflexionUtil;
import mx.com.televisa.derechocorporativo.util.ExcelUtil;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

public class Section {

	int idSeccion;
	int idReportFlex;
	String nomSeccion; 
	String desSeccion;
	
	public Section(ResultSet set, ResultSetMetaData metaData) throws Exception {

		ReflexionUtil.fillObject(set, metaData, this, Section.class);
	}

	public static ArrayList<Section> getSecciones(int idReportFlex, ConnectionWrapper connect) {

		// String idEmpresa = FacesUtils.getSessionBean().getIdCurrentEmpresa();

		ArrayList<Section> rows = new ArrayList<Section>();

		
		CallableStatement stmt = null;
		ResultSet set = null;

		try {

			stmt = connect.prepareCall(DERCORP_REPORTFLEX_PKG.GET_SECCIONES_PR);

			stmt.registerOutParameter(1, OracleTypes.CURSOR);
			
			stmt.setInt(2, idReportFlex);

			stmt.execute();

			set = ((OracleCallableStatement) stmt).getCursor(1);

			while (set.next()) {

				rows.add(new Section(set, set.getMetaData()));
			}

		} catch (Exception ex) {

			// TODO - Pendiente - Gestion de Excepciones
			ex.printStackTrace();

		} finally {
			
			ConnectionWrapper.closeAll(set, stmt);
		}
		return rows;
	}
	
	
	public void toHTML(StringBuilder sb, ConnectionWrapper connect, HttpServletRequest request, boolean edit, Map<String, String> empresaValuesMap, ReportFlex reportFlex) {
		
		//<a onclick=\"alert('" + this.idSeccion + "')\">(Editar)</a>
		
		String input = "";
		if(edit) {
			input = "<input value='" + this.nomSeccion + "' onKeyUp=\"updateSection('" + this.idSeccion + "',this.value)\">";
		} else {
			input = this.nomSeccion;
		}
		
		
		
		//sb.append("<tr><td><fieldset><legend>" + this.nomSeccion + "</legend>");
		sb.append("<tr><td><fieldset><legend>" + input + "</legend>");
		sb.append("<table width='100%'>");
		
		if(edit) {
			sb.append("<tr><td width='2%'>&nbsp;</td><td width='15%'></td><td width='25%'></td><td width='15%'></td><td width='25%'></td><td width='18%'></td></tr>");
		} else {
			
			sb.append("<tr><td width='10%'>&nbsp;</td><td width='20%'></td><td width='25%'></td><td width='20%'></td><td width='25%'></td></tr>");
		}
		
		if(edit) {
			sb.append("<tr><td colspan='6' align='left'><a href='#' onclick='newRow(" + this.idSeccion + ",1)'><img src='../../img/icons/new.png' width='14' height='14'> Nueva Fila (1 campo)</a></td></tr>");
			sb.append("<tr><td colspan='6' align='left'><a href='#' onclick='newRow(" + this.idSeccion + ",2)'><img src='../../img/icons/new.png' width='14' height='14'> Nueva Fila (2 campos)</a></td></tr>");
			sb.append("<tr><td colspan='6' align='right'>&nbsp;</td></tr>");
		}
		
		ArrayList<SectionRow> secRows = SectionRow.getSeccionRows(this.idSeccion, connect);
		
		  int c = 0;

		
		for (SectionRow row : secRows) {
			
			String cssClass = "";
			
	  		if(c%2==0) {
	  			cssClass = "tableRow2";
	  		}
	  		c++;
			row.toHTML(sb, connect, request, edit, empresaValuesMap, cssClass, reportFlex);
		}
		
		
		if(edit) {
			sb.append("<tr><td colspan='6' align='right'>&nbsp;</td></tr>");
			sb.append("<tr><td colspan='6' align='right'><a href='#' onclick='deleteSection(" + this.idSeccion + ")'><img src='../../img/icons/delete.png' width='14' height='14'> Borrar Esta Secci&oacute;n</a></td></tr>");
		}
		
		sb.append("</table>");
		
		sb.append("</fieldset></td></tr>");
		sb.append("<tr><td></td></tr>");
		
	}
	
	
	
	

	public int toExcel(XSSFSheet sheet, ConnectionWrapper connect, HttpServletRequest request, Map<String, String> empresaValuesMap, int rowIndex) {
		
		int colIndex = 1;
		
		ExcelUtil.setCellValue(sheet, rowIndex, colIndex, this.nomSeccion);
		ExcelUtil.setCellStyleBold(sheet, rowIndex, colIndex);
		
		
		rowIndex += 2;
		
		ArrayList<SectionRow> secRows = SectionRow.getSeccionRows(this.idSeccion, connect);
		
		
		//int c = 0;

		
		for (SectionRow row : secRows) {
			
			rowIndex = row.toExcel(sheet, connect, request, empresaValuesMap, rowIndex);
			
			rowIndex += 1;
		}
		
		return rowIndex;
	}
	
	
}

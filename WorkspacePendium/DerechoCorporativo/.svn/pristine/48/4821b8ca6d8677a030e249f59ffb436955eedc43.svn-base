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

import mx.com.televisa.derechocorporativo.bean.CapitalFVBen;
import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;
import mx.com.televisa.derechocorporativo.data.packages.DERCORP_REPORTFLEX_PKG;
import mx.com.televisa.derechocorporativo.modules.flextabs.FlexTable;
import mx.com.televisa.derechocorporativo.modules.flextabs.FlexTableSaveHandler;
import mx.com.televisa.derechocorporativo.reflexion.ReflexionUtil;
import mx.com.televisa.derechocorporativo.util.ExcelUtil;
import mx.com.televisa.derechocorporativo.util.FacesUtils;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

public class SectionRow {

	private int idSeccionRow;
	private int idSeccion;
	private int idOrder;
	private String atributo1; // Numero de Campos
	
	private FlexTableSaveHandler flexTabSavHand;
	private String idEmpresa;

	
	public SectionRow(ResultSet set, ResultSetMetaData metaData) throws Exception {

		ReflexionUtil.fillObject(set, metaData, this, SectionRow.class);
	}

	public static ArrayList<SectionRow> getSeccionRows(int idSeccion, ConnectionWrapper connect) {

		ArrayList<SectionRow> rows = new ArrayList<SectionRow>();

		
		CallableStatement stmt = null;
		ResultSet set = null;

		try {

			stmt = connect.prepareCall(DERCORP_REPORTFLEX_PKG.GET_ROWS_PR);

			stmt.registerOutParameter(1, OracleTypes.CURSOR);
			
			stmt.setInt(2, idSeccion);

			stmt.execute();

			set = ((OracleCallableStatement) stmt).getCursor(1);

			while (set.next()) {

				rows.add(new SectionRow(set, set.getMetaData()));
			}

		} catch (Exception ex) {

			// TODO - Pendiente - Gestion de Excepciones
			ex.printStackTrace();

		} finally {
			
			ConnectionWrapper.closeAll(set, stmt);
		}
		return rows;
	}
	

	public void toHTML(StringBuilder sb, ConnectionWrapper connect, HttpServletRequest request, boolean edit, Map<String, String> empresaValuesMap, String cssClass, ReportFlex reportFlex) {
		flexTabSavHand =new FlexTableSaveHandler();
		idEmpresa=FacesUtils.getSessionBean().getIdCurrentEmpresa();
		int entra=0;
		
		//sb.append("<tr><td></td>");
		sb.append("<tr>");
		
		ArrayList<Field> fields = Field.getFields(this.idSeccionRow, connect);
		
		String colspan1 = "";
			//Numero de Campos
		if(this.atributo1.equals("1")) {
			
			colspan1 = "colspan='3'";
		}
		
		for (Field field : fields) {
			
			StringBuilder sbTemp = new StringBuilder();
			FlexTable ft = null;
			

			if(field.getIdFlexTbl() != 0) {
				
				ft = new FlexTable(field.getIdFlexTbl());
			}
			
			String campoShow = (field.getIdAddCampo() != 0) ? field.getNomCampo() : "<small><i>[DEFINIR]</i></small>";
			
			if(!edit && field.getIdFlexTbl() != 0 && this.atributo1.equals("1")) {
			
				
				
				//sb.append("<td>" + campoShow + "</td>");
				sbTemp.append("<td colspan='5' class='" + cssClass + "'>");
				
			} else if(!edit && field.getIdFlexTbl() == 0 && this.atributo1.equals("2")){
				
				//sbTemp.append("<td class='" + cssClass + "'>" + campoShow + "</td>");
				sbTemp.append("<td " + colspan1 + " class='" + cssClass + "'>");

			}else{
				if(field.getIdAddCampo()==1028 || field.getIdAddCampo()==1029){
					flexTabSavHand = new FlexTableSaveHandler();
					CapitalFVBen capitalFVBen = flexTabSavHand.getCheckValuesFlex(Integer.parseInt(idEmpresa));
					if(capitalFVBen.getCapFijo() == 0 && capitalFVBen.getCapVariable() == 0){
						sbTemp.append("");
						sbTemp.append("<td " + colspan1 + ">");
					}else{
						sbTemp.append("<td class='" + cssClass + "'>" + campoShow + "</td>");
				
						sbTemp.append("<td " + colspan1 + " class='" + cssClass + "'>");										
					}
				}else{
					sbTemp.append("<td class='" + cssClass + "'>" + campoShow + "</td>");
				
					sbTemp.append("<td " + colspan1 + " class='" + cssClass + "'>");
				}
				//sbTemp.append("<td class='" + cssClass + "'>" + campoShow + "</td>");
				
				//sbTemp.append("<td " + colspan1 + " class='" + cssClass + "'>");
			}
			
			
			if(edit) {
				
				sbTemp.append("<a href='#' onclick='changeField(" + field.getIdCampo() + ")'><img src='../../img/icons/edit.png' width='14' height='14'> Editar</a>");
				
				if(field.getIdFlexTbl() != 0) {
						
					sbTemp.append("<br><a href='#' onclick='openFlexColsPupUp(" + field.getIdCampo() + "," + field.getIdFlexTbl() + ")'><img src='../../img/icons/edit.png' width='14' height='14'> Seleccionar Columnas</a>");
				}
				
				
				
			} else {
				
				
				if(field.getIdFlexTbl() == 0) {
					String value="";
					if(field.getIdAddCampo()==1028 || field.getIdAddCampo()==1029){
						flexTabSavHand = new FlexTableSaveHandler();
						CapitalFVBen capitalFVBen = flexTabSavHand.getCheckValuesFlex(Integer.parseInt(idEmpresa));
						if(capitalFVBen.getCapFijo() == 0 && capitalFVBen.getCapVariable() == 0){
							sbTemp.append("");
						}else{
							value = empresaValuesMap.get(Integer.toString(field.getIdAddCampo()));										
						}
					}else{
						value = empresaValuesMap.get(Integer.toString(field.getIdAddCampo()));
					}
					//String value = empresaValuesMap.get(Integer.toString(field.getIdAddCampo()));
					
					if(value == null || value.equals("null")) {
						
						value = "";
					}
					
					sbTemp.append(value);
					
				} else {
					
					
					
					
					ft.atributo8 = "0"; // No mostrar búsqueda 
					
					//ULR se agrego esta condicion para mostar los titulos de las flex de admon y vig en los reportes
					if(ft.idflexTbl==8 || ft.idflexTbl==39 || ft.idflexTbl==11 || ft.idflexTbl==13 ||
	                		  ft.idflexTbl==40 || ft.idflexTbl==12 || ft.idflexTbl==14 || ft.idflexTbl==15 ||
	                		  ft.idflexTbl==16 || ft.idflexTbl==43 ||  ft.idflexTbl==44 || ft.idflexTbl==46 || ft.idflexTbl==26 || ft.idflexTbl==25 || ft.idflexTbl==9){
						//se agrega Flex 44
						ft.showTitle = true;
					}else{
						ft.showTitle = false;
					}
					//ft.showTitle = false;
					
					ft.showEditableCombosECS = false;
					
					
					ft.filterRowsForReport = true; // Issue 35 - NAVA - Marzo2016
					
					
					
					ft.reportFlexReference = reportFlex;
					
					ft.specificFlexColumnsList = field.atributo1;					
					
					
					if(field.atributo1 != null && !field.atributo1.equals("")) {
						
						ft.specificFlexColumnsCond = " AND ID_FLEX_COLUM IN (" + field.atributo1 + ") ";	
					} else {
						
						ft.specificFlexColumnsCond = " AND LENGTH(TRIM(DES_FLEX_COLUM)) > 0 ";
						
					}
					
					
					if(ft.atributo11 != null && ft.atributo11.equals("DETALLE")) {
						
						ft.atributo2 = "VERTICAL";
					}
					
					sbTemp.append(ft.toHTML(request));
					if(ft.cantReg==0){
						entra=1;
						String tempStringSb;
						tempStringSb=sbTemp.toString().replace("<td colspan='4' class='tableRow2'>", "<td colspan='4' class=''>");
						sb.append(tempStringSb);
					}

				}
				
				
				
			}
			if(entra!=1){
				sb.append(sbTemp);
			}
			
			sb.append("</td>");
		}
		
		if(edit) {
			sb.append("<td class='" + cssClass + "'>");
			sb.append("<a href='#' onclick='deleteRow(" + this.getIdSeccionRow() + ")'><img src='../../img/icons/delete.png' width='14' height='14'> Borrar Fila</a>");
			sb.append("</td>");
		}
		
		
		
		sb.append("</tr>");
	}
	


	public int toExcel(XSSFSheet sheet, ConnectionWrapper connect, HttpServletRequest request, Map<String, String> empresaValuesMap, int rowIndex) {
		
		ArrayList<Field> fields = Field.getFields(this.idSeccionRow, connect);
		
		int colIndex 		= 2;
		String campoLabel 	= "";
		String value 		= "";
		FlexTable ft 		= null;
		
		for (Field field : fields) {
			
			campoLabel = (field.getIdAddCampo() != 0) ? field.getNomCampo() : "";
			
			ExcelUtil.setCellValue(sheet, rowIndex, colIndex, campoLabel);
			ExcelUtil.setCellStyleBold(sheet, rowIndex, colIndex);
			
			colIndex += 1;
				
			
			if(field.getIdFlexTbl() == 0) {
				value = empresaValuesMap.get(Integer.toString(field.getIdAddCampo()));
				
				if(value == null || value.equals("null")) {
					
					value = "";
				}
				
				
				if(value.contains("green")) {
					
					value = "VERDE";
					
				} else if(value.contains("yellow")) {
					
					value = "AMARILLO";
					
				} else if(value.contains("red")){
					
					value = "ROJO";
				}
				
				
				ExcelUtil.setCellValue(sheet, rowIndex, colIndex, value);
				
			} else {
				
				
				ft = new FlexTable(field.getIdFlexTbl());
				
				ft.atributo8 = "0"; // No mostrar búsqueda 
				ft.showTitle = false;
				ft.showEditableCombosECS = false;
				ft.filterRowsForReport = true;
				

				if(field.atributo1 != null && !field.atributo1.equals("")) {
					
					ft.specificFlexColumnsCond = "AND ID_FLEX_COLUM IN (" + field.atributo1 + ")";	
				}else {
					
					ft.specificFlexColumnsCond = " AND LENGTH(TRIM(DES_FLEX_COLUM)) > 0 ";
					
				}
				
				rowIndex = ft.toExcel(sheet, connect, request, rowIndex);				
			}
			
				
			colIndex += 2;
			
		}
		
		return rowIndex;
	}
	
	
	public int getIdSeccionRow() {
		return idSeccionRow;
	}

	public void setIdSeccionRow(int idSeccionRow) {
		this.idSeccionRow = idSeccionRow;
	}

	public int getIdSeccion() {
		return idSeccion;
	}

	public void setIdSeccion(int idSeccion) {
		this.idSeccion = idSeccion;
	}

	public int getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	public String getAtributo1() {
		return atributo1;
	}

	public void setAtributo1(String atributo1) {
		this.atributo1 = atributo1;
	}
	
	
	
	
	
}

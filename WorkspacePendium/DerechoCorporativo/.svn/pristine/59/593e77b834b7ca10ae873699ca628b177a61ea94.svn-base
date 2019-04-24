package mx.com.televisa.derechocorporativo.modules.reports.adminvig;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;
import mx.com.televisa.derechocorporativo.modules.flextabs.FlexTableSaveHandler;
import mx.com.televisa.derechocorporativo.modules.reports.ecs.ReporteECSFlexTable;
import mx.com.televisa.derechocorporativo.reflexion.ReflexionUtil;
import mx.com.televisa.derechocorporativo.util.ExcelUtil;

import org.apache.poi.xssf.usermodel.XSSFSheet;

public class ReporteAdmVigSeccionRow {
	
	private int idSeccionRow;
	private int idSeccion;
	private int idOrder;
	private String atributo1; // Numero de Campos
	
	private FlexTableSaveHandler flexTabSavHand;
	private String idEmpresa;

	
	public ReporteAdmVigSeccionRow(ResultSet set, ResultSetMetaData metaData) throws Exception {

		ReflexionUtil.fillObject(set, metaData, this, ReporteAdmVigSeccionRow.class);
	}

	public ReporteAdmVigSeccionRow(){
		
	}

	
	


	public int toExcel(XSSFSheet sheet, 
					   ConnectionWrapper 	connect, 
					   HttpServletRequest 	request, 
					   Map<String, String> 	empresaValuesMap, 
					   int 					rowIndex,
					   int 					idpEmpresa,
					   String[] 			arrayidFlex,
					   String				nomFuncionario) {
		
		ArrayList<ReporteAdmVigField> fields = ReporteAdmVigField.getFields(this.idSeccionRow, connect,idpEmpresa);
		
		int colIndex 		= 2;
		String campoLabel 	= "";
		String value 		= "";
		ReporteAdmVigFlexTable ft 		= null;
		int row 			= 0;
		
		/*
		for (ReporteAdmVigField field : fields) {
			
			campoLabel = (field.getIdAddCampo() != 0) ? field.getNomCampo() : "";
			
			if(!campoLabel.equals("Estructura Capital Social")){
				ExcelUtil.setCellValue(sheet, rowIndex , colIndex, campoLabel);
				ExcelUtil.setCellStyleBold(sheet, rowIndex , colIndex);
				row ++;
			}
			
			
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
				}else if(value.contains("C1045")){
					
					value = "Si";
				}
				
				
				ExcelUtil.setCellValue(sheet, rowIndex, colIndex, value);
				
			} else {
				
				
		}
			
				
			colIndex += 2;
			
		}*/
		
		for(String idFlex : arrayidFlex){
			ft = new ReporteAdmVigFlexTable(idFlex,String.valueOf(idpEmpresa));
			
			ft.atributo8 = "0"; // No mostrar búsqueda 
			ft.showTitle = true;
			ft.showEditableCombosECS = false;
			ft.filterRowsForReport = true;
			
			/*
			if(field.atributo1 != null && !field.atributo1.equals("")) {
				
				ft.specificFlexColumnsCond = "AND ID_FLEX_COLUM IN (" + field.atributo1 + ")";	
			}else {
				
				ft.specificFlexColumnsCond = " AND LENGTH(TRIM(DES_FLEX_COLUM)) > 0 ";
				
			}*/
			
			rowIndex = ft.toExcel(sheet, connect, request, rowIndex,String.valueOf(idpEmpresa),Integer.parseInt(idFlex),nomFuncionario);
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

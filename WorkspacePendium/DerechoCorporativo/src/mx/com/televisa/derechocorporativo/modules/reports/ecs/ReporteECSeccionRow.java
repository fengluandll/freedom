package mx.com.televisa.derechocorporativo.modules.reports.ecs;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import mx.com.televisa.derechocorporativo.bean.CapitalFVBen;
import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;
import mx.com.televisa.derechocorporativo.data.packages.DERCORP_REPORTFLEX_PKG;
import mx.com.televisa.derechocorporativo.modules.flextabs.EstructuraCapitalSocial;
import mx.com.televisa.derechocorporativo.modules.flextabs.FlexTable;
import mx.com.televisa.derechocorporativo.modules.flextabs.FlexTableSaveHandler;
import mx.com.televisa.derechocorporativo.reflexion.ReflexionUtil;
import mx.com.televisa.derechocorporativo.util.ExcelUtil;
import mx.com.televisa.derechocorporativo.util.FacesUtils;
import mx.com.televisa.derechocorporativo.util.NumberFormatter;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

import org.apache.poi.xssf.usermodel.XSSFSheet;

public class ReporteECSeccionRow {
	
	private int idSeccionRow;
	private int idSeccion;
	private int idOrder;
	private String atributo1; // Numero de Campos
	
	private FlexTableSaveHandler flexTabSavHand;
	private String idEmpresa;

	
	public ReporteECSeccionRow(ResultSet set, ResultSetMetaData metaData) throws Exception {

		ReflexionUtil.fillObject(set, metaData, this, ReporteECSeccionRow.class);
	}

	public ReporteECSeccionRow(){
		
	}

	
	


	public int toExcel(XSSFSheet sheet, ConnectionWrapper connect, HttpServletRequest request, Map<String, String> empresaValuesMap, int rowIndex,int idpEmpresa,String accionistas) {
		
		ArrayList<ReporteECSField> fields = ReporteECSField.getFields(this.idSeccionRow, connect,idpEmpresa);
		
		int colIndex 		= 2;
		String campoLabel 	= "";
		String value 		= "";
		ReporteECSFlexTable ft 		= null;
		int isVerde			= 0;
		int palabraPor		= 0;
		String tipoSoc = EstructuraCapitalSocial.getTipoSoc(idpEmpresa);//ULR obtener tipo de sociedad
		for (ReporteECSField field : fields) {
			
			campoLabel = (field.getIdAddCampo() != 0) ? field.getNomCampo() : "";
			
			if(!campoLabel.equals("Estructura Capital Social")){
				colIndex 		= 2;
				if(isVerde == 1){//JJAQ 21/03/2019 PARA QUE NO IMPRIMA LAS OPCIONES DE SEMAFORO ROJO O AMARILLO
					if(!field.desTipoCampo.equals("CHECKBOX_D")){
						if(field.getIdAddCampo() == 1028 || field.getIdAddCampo() == 1029){
							
							if(tipoSoc.equals("isSdeRL") || tipoSoc.equals("isSA")|| tipoSoc.equals("isSAB") || 
		    						   tipoSoc.equals("isLLC")   || tipoSoc.equals("isSLU") || tipoSoc.equals("isSAC") ||
		    						   tipoSoc.equals("isSAU") || tipoSoc.equals("isLTD") || tipoSoc.equals("isSL")){
		    							
		    					}else if((tipoSoc.equals("isSA")|| tipoSoc.equals("isSC") || tipoSoc.equals("isLLC") ||
		    								  tipoSoc.equals("isSLU") || tipoSoc.equals("isSAC") || tipoSoc.equals("isSAU") ||
		    								  tipoSoc.equals("isLTD") || tipoSoc.equals("isSL"))){
		    							
		    					}else{
		    							ExcelUtil.setCellValue(sheet, rowIndex , colIndex, campoLabel);
		    							ExcelUtil.setCellStyleBold(sheet, rowIndex , colIndex);	
		    						}
						}else{
	    							ExcelUtil.setCellValue(sheet, rowIndex , colIndex, campoLabel);
	    							ExcelUtil.setCellStyleBold(sheet, rowIndex , colIndex);									    							
	    						}
						
					}
				}else{
					if(field.getIdAddCampo() == 1028 || field.getIdAddCampo() == 1029){
						if(tipoSoc.equals("isSdeRL") || tipoSoc.equals("isSA")|| tipoSoc.equals("isSAB") || 
		 						   tipoSoc.equals("isLLC")   || tipoSoc.equals("isSLU") || tipoSoc.equals("isSAC") ||
		 						   tipoSoc.equals("isSAU") || tipoSoc.equals("isLTD") || tipoSoc.equals("isSL")){
						}else if((tipoSoc.equals("isSA")|| tipoSoc.equals("isSC") || tipoSoc.equals("isLLC") ||
								  tipoSoc.equals("isSLU") || tipoSoc.equals("isSAC") || tipoSoc.equals("isSAU") ||
								  tipoSoc.equals("isLTD") || tipoSoc.equals("isSL"))){
							
						}else{
							ExcelUtil.setCellValue(sheet, rowIndex , colIndex, campoLabel);
 							ExcelUtil.setCellStyleBold(sheet, rowIndex , colIndex);	
						}
					}else{
						if(field.desTipoCampo.equals("CHECKBOX_D")){
							colIndex++;
							if(palabraPor == 0){
								ExcelUtil.setCellValue(sheet, rowIndex , 2, "Por:");
								ExcelUtil.setCellStyleBold(sheet, rowIndex , 2);
							}
							ExcelUtil.setCellValue(sheet, rowIndex , colIndex, campoLabel);
							//ExcelUtil.setCellStyleBold(sheet, rowIndex , colIndex);
							palabraPor++;
							colIndex--;
						}else{
							ExcelUtil.setCellValue(sheet, rowIndex , colIndex, campoLabel);
							ExcelUtil.setCellStyleBold(sheet, rowIndex , colIndex);
						}
							
 					}
					
				}
				
				
				
			}
			
			
			colIndex += 1;
				
			
			if(field.getIdFlexTbl() == 0) {
				value = empresaValuesMap.get(Integer.toString(field.getIdAddCampo()));
				if(field.getDesTipoCampo().equals("NUMERIC")){
					value = NumberFormatter.conComas(value);
				}
				
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
				
				if(field.getIdAddCampo() == 546 && value.contains("VERDE")){
					isVerde = 1;
				}
				if(isVerde == 1){//JJAQ 21/03/2019 PARA QUE NO IMPRIMA LAS OPCIONES DE SEMAFORO ROJO O AMARILLO
					if(!field.desTipoCampo.equals("CHECKBOX_D")){
						if(field.getIdAddCampo() == 1028 || field.getIdAddCampo() == 1029){
							if(tipoSoc.equals("isSdeRL") || tipoSoc.equals("isSA")|| tipoSoc.equals("isSAB") || 
			 						   tipoSoc.equals("isLLC")   || tipoSoc.equals("isSLU") || tipoSoc.equals("isSAC") ||
			 						   tipoSoc.equals("isSAU") || tipoSoc.equals("isLTD") || tipoSoc.equals("isSL")){
			 							
			 				}else if((tipoSoc.equals("isSA")|| tipoSoc.equals("isSC") || tipoSoc.equals("isLLC") ||
									  tipoSoc.equals("isSLU") || tipoSoc.equals("isSAC") || tipoSoc.equals("isSAU") ||
									  tipoSoc.equals("isLTD") || tipoSoc.equals("isSL"))){
			 					
			 				}else{
			 					ExcelUtil.setCellValue(sheet, rowIndex, colIndex, value);
			 				}
						}else{
		 					    ExcelUtil.setCellValue(sheet, rowIndex, colIndex, value);									    							
		 					 }
						
					}
				}else{
					if(!field.desTipoCampo.equals("CHECKBOX_D")){
						if(field.getIdAddCampo() == 1028 || field.getIdAddCampo() == 1029){
							if(tipoSoc.equals("isSdeRL") || tipoSoc.equals("isSA")|| tipoSoc.equals("isSAB") || 
			 						   tipoSoc.equals("isLLC")   || tipoSoc.equals("isSLU") || tipoSoc.equals("isSAC") ||
			 						   tipoSoc.equals("isSAU") || tipoSoc.equals("isLTD") || tipoSoc.equals("isSL")){
			 							
			 				}else if((tipoSoc.equals("isSA")|| tipoSoc.equals("isSC") || tipoSoc.equals("isLLC") ||
									  tipoSoc.equals("isSLU") || tipoSoc.equals("isSAC") || tipoSoc.equals("isSAU") ||
									  tipoSoc.equals("isLTD") || tipoSoc.equals("isSL"))){
			 					
			 				}else{
			 					ExcelUtil.setCellValue(sheet, rowIndex, colIndex, value);
			 				}
						}else{
		 							ExcelUtil.setCellValue(sheet, rowIndex, colIndex, value);							    							
		 						}
						
					}
					
				}
				
				
			} else {
				
				
				ft = new ReporteECSFlexTable("7",String.valueOf(idpEmpresa));
				
				ft.atributo8 = "0"; // No mostrar búsqueda 
				ft.showTitle = false;
				ft.showEditableCombosECS = false;
				ft.filterRowsForReport = true;
				

				if(field.atributo1 != null && !field.atributo1.equals("")) {
					
					ft.specificFlexColumnsCond = "AND ID_FLEX_COLUM IN (" + field.atributo1 + ")";	
				}else {
					
					ft.specificFlexColumnsCond = " AND LENGTH(TRIM(DES_FLEX_COLUM)) > 0 ";
					
				}
				
				rowIndex = ft.toExcel(sheet, connect, request, rowIndex,String.valueOf(idpEmpresa),accionistas);				
			}
			
			if(campoLabel.equals("Estructura Capital Social")){
				colIndex += 2;
			}else{
				if(isVerde == 1){//JJAQ 21/03/2019 PARA QUE NO IMPRIMA LAS OPCIONES DE SEMAFORO ROJO O AMARILLO
					if(!field.desTipoCampo.equals("CHECKBOX_D")){
						if(field.getIdAddCampo() == 1028 || field.getIdAddCampo() == 1029){
							if(tipoSoc.equals("isSdeRL") || tipoSoc.equals("isSA")|| tipoSoc.equals("isSAB") || 
			 						   tipoSoc.equals("isLLC")   || tipoSoc.equals("isSLU") || tipoSoc.equals("isSAC") ||
			 						   tipoSoc.equals("isSAU") || tipoSoc.equals("isLTD") || tipoSoc.equals("isSL")){
			 							
			 				}else if((tipoSoc.equals("isSA")|| tipoSoc.equals("isSC") || tipoSoc.equals("isLLC") ||
									  tipoSoc.equals("isSLU") || tipoSoc.equals("isSAC") || tipoSoc.equals("isSAU") ||
									  tipoSoc.equals("isLTD") || tipoSoc.equals("isSL"))){
			 					
			 				}else{
			 					rowIndex +=1;
			 				}
						}else{
							rowIndex +=1;							    							
		 						}
						
					}
				}else{
					if(field.getIdAddCampo() == 1028 || field.getIdAddCampo() == 1029){
						if(tipoSoc.equals("isSdeRL") || tipoSoc.equals("isSA")|| tipoSoc.equals("isSAB") || 
		 						   tipoSoc.equals("isLLC")   || tipoSoc.equals("isSLU") || tipoSoc.equals("isSAC") ||
		 						   tipoSoc.equals("isSAU") || tipoSoc.equals("isLTD") || tipoSoc.equals("isSL")){
		 							
		 				}else if((tipoSoc.equals("isSA")|| tipoSoc.equals("isSC") || tipoSoc.equals("isLLC") ||
								  tipoSoc.equals("isSLU") || tipoSoc.equals("isSAC") || tipoSoc.equals("isSAU") ||
								  tipoSoc.equals("isLTD") || tipoSoc.equals("isSL"))){
		 					
		 				}else{
		 					rowIndex +=1;
		 				}
					}else{
	 					rowIndex +=1;
	 				}
				}
				
			}
			
			
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

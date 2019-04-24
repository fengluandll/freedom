package com.movemini.layers.controller.agenda;

import java.time.LocalDate;
import java.time.Month;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.HSSFRegionUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.movemini.data.AgendaExcelDAO;
import com.movemini.layers.model.ClienteColorBean;
import com.movemini.layers.model.DatosCalendarioBean;
import com.movemini.layers.model.DiaMesAgendaBean;
import com.movemini.layers.model.MesAgendaBean;
import com.movemini.util.ColorUtil;
import com.movemini.util.DateUtils;

public class AgendaExcel {
	
	
	XSSFWorkbook    workbook;
	Sheet 	 		sheet;
	CellStyle 		style;
	Font 			font;
	int 			firstColumn			=	0;
	int 			lastColumn			=	0;
	AgendaExcelDAO	agendaExcelDAO;
	int 			countDiaSemana		=	0;
	int 			countDiasMes		=	0;
	int 			countCeldaEspacio 	= 	0;
	int 			totalceldaEspacio	=	0;
	int 			countCeldaMemoAzul 	= 	0;
	int 			totalceldaMemoAzul	=	0;
	int 			contIsLunes			=	0;
	int 			fila2Azul			=	0;
	int 			countfila2Azul   	= 	0;
	int 			diaAnterior 		= 	0;
	int 			diaActual 			= 	0;
	int				countDiaRepetido	=	0;
	int				countRows			=	0;
	int				countceldaCEITH		=	0;
	Row 			row					;
	int 			countCuadroAzul		=	0;//Contador para repetir una vez por mes el cuadro azul de memo
	Row 			rowMemo				;
	Row 			rowsMemo			;
	Row 			rowsCancelado		;
	Row 			rowsPenalizacion	;
	Row 			rowsBarraNegra1		;
	Row 			rowsBarraAbajoHorarioNegra		;
	Row 			rowsLetraGde		;
	Row				rowsLetraGdeSigRow  ;
	Row 			rowsCliente			;
	Row 			rowsCantEquipo		;
	Row 			rowsInterprete		;
	Row 			rowsTecnicos		;
	Row 			rowsHorarios		;
	Cell 			cellCancelado		;
	Cell 			cellCanceladoAll	;
    Cell 			cellMemo 			;
    Cell 			cellsMemo			;
    Cell 			cellMemoAll			;
    Cell 			cellsMemoAll		;
    Cell 			cellPenalizacion	;
    Cell 			cellPenalizacionAll	;
    Cell 			cell1				;
    Cell 			cell2				;
    Cell 			cell3				;
    Cell 			cell4				;
    Cell 			cell5				;
    Cell 			cell6				;
    Cell 			cell7				;
    Cell 			cell8				;
    Cell 			cell9				;
    Cell 			cell10				;
    Cell 			cell11				;
    Cell 			cell12				;
    Cell 			cell13				;
    Cell 			cell14				;
    Cell 			cell15				;
    Cell 			cell16				;
    Cell 			cell17				;
    Cell 			cell18				;
    Cell 			cell19				;
    Cell 			cell20				;
    Cell 			cell21				;
	
	public XSSFWorkbook getReportAsExcel(ParameterAgendaBean parameterAgendaBean){
		String anio 							= parameterAgendaBean.getYear();
		String fecInicio						= parameterAgendaBean.getFechaInicio();
		String fecFin							= parameterAgendaBean.getFechaFin();
		int    idCliente						= parameterAgendaBean.getIdCliente();
		agendaExcelDAO 							= new AgendaExcelDAO();
		workbook 								= new XSSFWorkbook();
		List<MesAgendaBean>    listMes 			=  agendaExcelDAO.getMonths(anio);
		List<DiaMesAgendaBean> listDiaSemana 	=  agendaExcelDAO.getDayOfWeek(anio,fecInicio,fecFin,idCliente);
		ColorUtil              colorUtil 		= new ColorUtil();
		//Itera los meses que existe de acuerdo al año introducido
		String[] meses 							= DateUtils.meses;
		String mesActual 						= "";
		String mesAnterior 						= "";
		TreeMap<Month,String> mapMonths 		= DateUtils.getMonthInt(fecInicio,fecFin);
		Map<Integer,Integer>  mapRows			= new TreeMap<Integer, Integer>();
		Map<String,ClienteColorBean>  mapColors = new TreeMap<String, ClienteColorBean>();
		ClienteColorBean	clienteColorBean;
		
		//for(int i = 0; i < meses.length ; i++){
		Iterator claves = mapMonths.keySet().iterator();
		
		//ITERA PARA AGREGAR AL MAPA QUE EVENTOS SE REPITEN PARA PONERLES EL MISMO COLOR
		for(DiaMesAgendaBean lisDia : listDiaSemana){
			if(!mapColors.containsKey(lisDia.getClienteNom())){
				clienteColorBean = new ClienteColorBean();
				clienteColorBean.setnVeces(1);
				clienteColorBean.setColor(colorUtil.anyColorExcel());
				mapColors.put(lisDia.getClienteNom(),clienteColorBean);
			}else{
				ClienteColorBean cliente = mapColors.get(lisDia.getClienteNom());
				int count = cliente.getnVeces();
				cliente.setnVeces(++count);
				mapColors.put(lisDia.getClienteNom(),cliente);
			}
		}
		
		while(claves.hasNext()){
				
			Month 	clave 	= (Month)claves.next();
			String 	nomMes 	= mapMonths.get(clave);
			mesActual 		= nomMes;
			//countDiasMes++;
			if( !mesAnterior.equals("") &&  (!mesActual.equals(mesAnterior)) && countDiaSemana == 0){
				//If para resolver cuando los meses terminen en domingo 30 ó 31
		    	mesAnterior 		= "";
		    	countCeldaEspacio  	= 0;
		    	countCeldaMemoAzul 	= 0;
		    	mapRows				= new TreeMap<Integer, Integer>();
	        }
        	
			
			LocalDate dias = LocalDate.of(Integer.parseInt(anio), clave, 1);	
		
		
			
			if(countDiaSemana == 0){//SOLO ENTRARA UNA SOLA VEZ. LA PRIMERA ITERACION
				contIsLunes = 0;
				firstColumn	=	1;
		    	lastColumn	=	5;
		    	sheet 		= workbook.createSheet(nomMes);
		    	row = sheet.createRow(0);
		    	rowsBarraNegra1 = sheet.createRow(5);
		    	rowsBarraNegra1.setHeight((short) 240);
		    	//rowsLetraGde = sheet.createRow(6);
		    	//rowsLetraGde.setHeight((short) 460);
		    	
			}
		//ITERA DIA POR DIA DEL MES
    	for(int j = 1; j < dias.lengthOfMonth() + 1 ; j++){
    		
    		
    	
    		LocalDate dateGral = LocalDate.of(Integer.parseInt(anio), clave, j);
//			System.out.println(DateUtils.DiaInglesAEspanol(dateGral.getDayOfWeek()) + " " +dateGral.getDayOfMonth() + " de "+ DateUtils.MesInglesAEspanol(dateGral.getMonth()));
			
			if(contIsLunes == 0 && DateUtils.DiaInglesAEspanol(dateGral.getDayOfWeek()).equals("Lunes")){
				contIsLunes++; //Este if es para poner el primer dia lunes de cada mes, si empieza en martes lo omite hasta encontrar el lunes
				countCuadroAzul++;
		        
			}			        
		    if(contIsLunes > 0){
		    	countDiaSemana++;
		    //PINTA DIAS SEMANAS
				sheet.addMergedRegion(new CellRangeAddress(0,0,firstColumn,lastColumn));//first row,last row(hacia abajo),first column,last column
		        
		        Cell cell = row.createCell(firstColumn);
		        cell.setCellValue(DateUtils.DiaInglesAEspanol(dateGral.getDayOfWeek()) + " " + dateGral.getDayOfMonth() + " de "+ DateUtils.MesInglesAEspanol(dateGral.getMonth()));
		        cell.setCellStyle(StyleCustom.diaSemanaBlack(workbook));
		        
		       //Seccion 5a fila color negro toda la fila
		        sheet.addMergedRegion(new CellRangeAddress(5,5,firstColumn,lastColumn));
		        Cell cell2 = rowsBarraNegra1.createCell(firstColumn);
		        cell2.setCellStyle(StyleCustom.colorCelda(workbook, "NEGRO"));
		      //Seccion 6a fila G EN ROJO
		        for(DiaMesAgendaBean lisDia : listDiaSemana){
 		        	if(Integer.parseInt(lisDia.getDia()) == dateGral.getDayOfMonth() && 
		        	   Integer.parseInt(lisDia.getMes()) ==  dateGral.getMonthValue()){
		        		
		        		diaActual 	= dateGral.getDayOfMonth();
		        		if(diaActual == diaAnterior){//Si es igual es porque hay dos eventos en el mismo dia
		        			countRows++;
		        			if(!mapRows.containsKey(countRows) ){
		        				countDiaRepetido += 7;
		        				mapRows.put(countRows, countDiaRepetido);
		        				rowsLetraGde = sheet.createRow(countDiaRepetido);
			        			rowsLetraGde.setHeight((short) 460);
			        			rowsCliente = sheet.createRow(countDiaRepetido + 1);
			        			rowsCantEquipo = sheet.createRow(countDiaRepetido + 2);
			        			rowsInterprete = sheet.createRow(countDiaRepetido + 3);
			        			rowsTecnicos   = sheet.createRow(countDiaRepetido + 4);	
			        			rowsHorarios   = sheet.createRow(countDiaRepetido + 5);
			        			//Linea negra
//			        			sheet.addMergedRegion(new CellRangeAddress(countDiaRepetido + 6,countDiaRepetido + 6,firstColumn,lastColumn));
			        			rowsBarraAbajoHorarioNegra = sheet.createRow(countDiaRepetido + 6);
			        			rowsBarraAbajoHorarioNegra.setHeight((short) 240);
//			        			Cell cellBarraNegra = rowsBarraAbajoHorarioNegra.createCell(firstColumn);
//								cellBarraNegra.setCellStyle(StyleCustom.colorCelda(workbook, "NEGRO"));
		        			}		        			
		        			
					    	
		        		}else{
		        			countRows++;	
		        			if(!mapRows.containsKey(countRows) ){
		        				countDiaRepetido += 6;
		        				mapRows.put(countRows, countDiaRepetido);
		        				rowsLetraGde = sheet.createRow(countDiaRepetido);
		        				rowsLetraGde.setHeight((short) 460);
		        				rowsCliente = sheet.createRow(countDiaRepetido + 1);
		        				rowsCantEquipo = sheet.createRow(countDiaRepetido + 2);
		        				rowsInterprete = sheet.createRow(countDiaRepetido + 3);
		        				rowsTecnicos   = sheet.createRow(countDiaRepetido + 4);
		        				rowsHorarios   = sheet.createRow(countDiaRepetido + 5);
//		        				sheet.addMergedRegion(new CellRangeAddress(countDiaRepetido + 6,countDiaRepetido + 6,firstColumn,lastColumn));
		        				rowsBarraAbajoHorarioNegra = sheet.createRow(countDiaRepetido + 6);
		        				rowsBarraAbajoHorarioNegra.setHeight((short) 240);
//		        				Cell cellBarraNegra = rowsBarraAbajoHorarioNegra.createCell(firstColumn);
//								cellBarraNegra.setCellStyle(StyleCustom.colorCelda(workbook, "NEGRO"));
		        			}
		        			
		        			
		        		}
    			DatosCalendarioBean CalVal 			= agendaExcelDAO.getCotCliente(anio, lisDia.getFecha(), lisDia.getIdEventoVersion());
    			String 				sInterpretes 	= agendaExcelDAO.getInterprete(lisDia.getIdEventoVersion());
    			String 				sTecnicos 		= agendaExcelDAO.getTecnico(lisDia.getIdEventoVersion());
    			String 				fHorario  		= agendaExcelDAO.getHorario(lisDia.getIdEventoVersion());
    			ClienteColorBean    cliente 		= mapColors.get(CalVal.getNomCliente());//Obtiene de acuerdo al nombre del cliente cuantas veces aparece y el color que le asigno el colorUtil
    			
			    			
			    			
							
				
		        			rowsLetraGde = sheet.getRow(mapRows.get(countRows));
			        		Cell cellLetraResp = rowsLetraGde.createCell(firstColumn);
					        sheet.setColumnWidth(firstColumn, 1000);
					        cellLetraResp.setCellValue(CalVal.getAbreviaResponsable());
					        cellLetraResp.setCellStyle(StyleCustom.letraGde(workbook,"ROJO"));
					        Cell cellCotiz = rowsLetraGde.createCell(firstColumn + 1);
					        sheet.setColumnWidth(firstColumn + 1, 2820);
					        cellCotiz.setCellValue("Cotización:");
					        if(lisDia.getIdEventoStatus() != 5){
					        	cellCotiz.setCellStyle(StyleCustom.letritasNegras(workbook,"DERECHA",(short)8,HSSFColor.GREY_50_PERCENT.index));
					        }else{
					        	if(cliente.getnVeces() > 1){
					        		cellCotiz.setCellStyle(StyleCustom.letritasNegras(workbook,"DERECHA",(short)8,cliente.getColor()));
					        		//cellCotiz.setCellStyle(StyleCustom.colorCelda(workbook, "AZUL"));
    							}else{
    								cellCotiz.setCellStyle(StyleCustom.letritasNegras(workbook,"DERECHA",(short)8,HSSFColor.BLACK.index));
    							}
					        	
					        }
					        
					        Cell cellCotizVal = rowsLetraGde.createCell(firstColumn + 2);
					        cellCotizVal.setCellValue(CalVal.getClaveCotizacion());
					        if(lisDia.getIdEventoStatus() != 5){
					        	cellCotizVal.setCellStyle(StyleCustom.letritasNegras(workbook,"IZQUIERDA",(short)10,HSSFColor.GREY_50_PERCENT.index));
					        }else{
					        	if(cliente.getnVeces() > 1){
					        		cellCotizVal.setCellStyle(StyleCustom.letritasNegras(workbook,"IZQUIERDA",(short)10,cliente.getColor()));
					        	}else{
					        		cellCotizVal.setCellStyle(StyleCustom.letritasNegras(workbook,"IZQUIERDA",(short)10,HSSFColor.BLACK.index));
					        	}
					        	
					        }
					        
					        Cell cellFac = rowsLetraGde.createCell(firstColumn + 3);
					        sheet.setColumnWidth(firstColumn + 2, 6000);
					        cellFac.setCellValue("Factura(s):");
					        if(lisDia.getIdEventoStatus() != 5){
					        	cellFac.setCellStyle(StyleCustom.letritasNegras(workbook,"DERECHA",(short)8,HSSFColor.GREY_50_PERCENT.index));
					        }else{
					        	if(cliente.getnVeces() > 1){
					        		cellFac.setCellStyle(StyleCustom.letritasNegras(workbook,"DERECHA",(short)8,cliente.getColor()));
					        	}else{
					        	cellFac.setCellStyle(StyleCustom.letritasNegras(workbook,"DERECHA",(short)8,HSSFColor.BLACK.index));
					        	}
					        }
					        
					        Cell cellFacVal = rowsLetraGde.createCell(firstColumn + 4);
					        cellFacVal.setCellValue(CalVal.getNumFactura());
					        if(lisDia.getIdEventoStatus() != 5){
					        	cellFacVal.setCellStyle(StyleCustom.letritasNegras(workbook,"IZQUIERDA",(short)10,HSSFColor.GREY_50_PERCENT.index));
					        }else{
					        	if(cliente.getnVeces() > 1){
					        	cellFacVal.setCellStyle(StyleCustom.letritasNegras(workbook,"IZQUIERDA",(short)10,cliente.getColor()));
					        	}else{
					        		cellFacVal.setCellStyle(StyleCustom.letritasNegras(workbook,"IZQUIERDA",(short)10,HSSFColor.BLACK.index));
					        	}
					        }
					        
					      //Seccion cliente
					        countceldaCEITH += 7;
					        rowsCliente = sheet.getRow(mapRows.get(countRows) + 1);
					        Cell cellCliente = rowsCliente.createCell(firstColumn);
					        Cell cellCliente2 = rowsCliente.createCell(firstColumn+1);
					        cellCliente.setCellValue("Cliente:");
					        sheet.addMergedRegion(new CellRangeAddress(countceldaCEITH,countceldaCEITH,firstColumn,lastColumn - 3));
					        if(lisDia.getIdEventoStatus() != 5){
					        	cellCliente.setCellStyle(StyleCustom.letritasNegras(workbook,"DERECHA",(short)8,HSSFColor.GREY_50_PERCENT.index));
						        cellCliente2.setCellStyle(StyleCustom.letritasNegras(workbook,"DERECHA",(short)8,HSSFColor.GREY_50_PERCENT.index));
					        }else{
					        	if(cliente.getnVeces() > 1){
					        	cellCliente.setCellStyle(StyleCustom.letritasNegras(workbook,"DERECHA",(short)8,cliente.getColor()));
						        cellCliente2.setCellStyle(StyleCustom.letritasNegras(workbook,"DERECHA",(short)8,cliente.getColor()));
					        	}else{
					        		cellCliente.setCellStyle(StyleCustom.letritasNegras(workbook,"DERECHA",(short)8,HSSFColor.BLACK.index));
							        cellCliente2.setCellStyle(StyleCustom.letritasNegras(workbook,"DERECHA",(short)8,HSSFColor.BLACK.index));
					        	}
					        }
					        
					        rowsCliente.setHeight((short) 510);//25.5
					        Cell cellClienteVal = rowsCliente.createCell(firstColumn + 2);
					        cellClienteVal.setCellValue(CalVal.getNomCliente());
					        if(lisDia.getIdEventoStatus() != 5){
					        	cellClienteVal.setCellStyle(StyleCustom.letritasNegras(workbook,"IZQUIERDA",(short)10,HSSFColor.GREY_50_PERCENT.index));
					        }else{
					        	if(cliente.getnVeces() > 1){
					        		cellClienteVal.setCellStyle(StyleCustom.letritasNegras(workbook,"IZQUIERDA",(short)10,cliente.getColor()));
					        	}else{
					        		cellClienteVal.setCellStyle(StyleCustom.letritasNegras(workbook,"IZQUIERDA",(short)10,HSSFColor.BLACK.index));
					        	}
					        }
					        
					        Cell cellSede = rowsCliente.createCell(firstColumn + 3);
					        cellSede.setCellValue("Sede:");
					        if(lisDia.getIdEventoStatus() != 5){
					        	cellSede.setCellStyle(StyleCustom.letritasNegras(workbook,"DERECHA",(short)8,HSSFColor.GREY_50_PERCENT.index));
					        }else{
					        	if(cliente.getnVeces() > 1){
					        		cellSede.setCellStyle(StyleCustom.letritasNegras(workbook,"DERECHA",(short)8,cliente.getColor()));
					        	}else{
					        		cellSede.setCellStyle(StyleCustom.letritasNegras(workbook,"DERECHA",(short)8,HSSFColor.BLACK.index));
					        	}
					        }
					        
					        Cell cellSedeVal = rowsCliente.createCell(firstColumn + 4);
					        cellSedeVal.setCellValue(CalVal.getSede());
					        if(lisDia.getIdEventoStatus() != 5){
					        	cellSedeVal.setCellStyle(StyleCustom.letritasNegras(workbook,"JUSTIFICADO",(short)10,HSSFColor.GREY_50_PERCENT.index));
					        }else{
					        	if(cliente.getnVeces() > 1){
					        		cellSedeVal.setCellStyle(StyleCustom.letritasNegras(workbook,"JUSTIFICADO",(short)10,cliente.getColor()));
					        	}else{
					        		cellSedeVal.setCellStyle(StyleCustom.letritasNegras(workbook,"JUSTIFICADO",(short)10,HSSFColor.BLACK.index));
					        	}
					        }
					        
					       
					      //Seccion 8a fila Cantidad equipo
					        rowsCantEquipo = sheet.getRow(mapRows.get(countRows) + 2);
					        Cell cellCantEquipo  = rowsCantEquipo.createCell(firstColumn);
					        Cell cellCantEquipo2 = rowsCantEquipo.createCell(firstColumn+1);
					        cellCantEquipo.setCellValue("Cantidad Equipo:");
					        sheet.addMergedRegion(new CellRangeAddress(countceldaCEITH + 1,countceldaCEITH + 1,firstColumn,lastColumn - 3));
					        if(lisDia.getIdEventoStatus() != 5){
					        	cellCantEquipo.setCellStyle(StyleCustom.letritasNegras(workbook,"DERECHA",(short)8,HSSFColor.GREY_50_PERCENT.index));
						        cellCantEquipo2.setCellStyle(StyleCustom.letritasNegras(workbook,"DERECHA",(short)8,HSSFColor.GREY_50_PERCENT.index));
					        }else{
					        	if(cliente.getnVeces() > 1){
						        	cellCantEquipo.setCellStyle(StyleCustom.letritasNegras(workbook,"DERECHA",(short)8,cliente.getColor()));
							        cellCantEquipo2.setCellStyle(StyleCustom.letritasNegras(workbook,"DERECHA",(short)8,cliente.getColor()));
					        	}else{
					        		cellCantEquipo.setCellStyle(StyleCustom.letritasNegras(workbook,"DERECHA",(short)8,HSSFColor.BLACK.index));
							        cellCantEquipo2.setCellStyle(StyleCustom.letritasNegras(workbook,"DERECHA",(short)8,HSSFColor.BLACK.index));
					        	}
					        }
					        
					        
					        Cell cellCantEquipoVal = rowsCantEquipo.createCell(firstColumn + 2);
					        Cell cellCantEquipoVal2 = rowsCantEquipo.createCell(firstColumn + 3);
					        Cell cellCantEquipoVal3 = rowsCantEquipo.createCell(firstColumn + 4);
					        sheet.addMergedRegion(new CellRangeAddress(countceldaCEITH + 1,countceldaCEITH + 1,firstColumn + 2,lastColumn));
					        cellCantEquipoVal.setCellValue("");
					        if(lisDia.getIdEventoStatus() != 5){
					        	cellCantEquipoVal.setCellStyle(StyleCustom.letritasNegras(workbook,"IZQUIERDA",(short)10,HSSFColor.GREY_50_PERCENT.index));
						        cellCantEquipoVal2.setCellStyle(StyleCustom.letritasNegras(workbook,"IZQUIERDA",(short)10,HSSFColor.GREY_50_PERCENT.index));
						        cellCantEquipoVal3.setCellStyle(StyleCustom.letritasNegras(workbook,"IZQUIERDA",(short)10,HSSFColor.GREY_50_PERCENT.index));
					        }else{
					        	if(cliente.getnVeces() > 1){
						        	cellCantEquipoVal.setCellStyle(StyleCustom.letritasNegras(workbook,"IZQUIERDA",(short)10,cliente.getColor()));
							        cellCantEquipoVal2.setCellStyle(StyleCustom.letritasNegras(workbook,"IZQUIERDA",(short)10,cliente.getColor()));
							        cellCantEquipoVal3.setCellStyle(StyleCustom.letritasNegras(workbook,"IZQUIERDA",(short)10,cliente.getColor()));
					        	}else{
					        		cellCantEquipoVal.setCellStyle(StyleCustom.letritasNegras(workbook,"IZQUIERDA",(short)10,HSSFColor.BLACK.index));
							        cellCantEquipoVal2.setCellStyle(StyleCustom.letritasNegras(workbook,"IZQUIERDA",(short)10,HSSFColor.BLACK.index));
							        cellCantEquipoVal3.setCellStyle(StyleCustom.letritasNegras(workbook,"IZQUIERDA",(short)10,HSSFColor.BLACK.index));
					        	}
					        }
					        
					       //Seccion 9a fila interprete
					        rowsInterprete = sheet.getRow(mapRows.get(countRows) + 3);
					        Cell cellInterprete = rowsInterprete.createCell(firstColumn);
					        Cell cellInterprete2 = rowsInterprete.createCell(firstColumn+1);
					        cellInterprete.setCellValue("Intérprete(s):");
					        sheet.addMergedRegion(new CellRangeAddress(countceldaCEITH + 2,countceldaCEITH + 2,firstColumn,lastColumn - 3));
					        if(lisDia.getIdEventoStatus() != 5){
					        	cellInterprete.setCellStyle(StyleCustom.letritasNegras(workbook,"DERECHA",(short)8,HSSFColor.GREY_50_PERCENT.index));
						        cellInterprete2.setCellStyle(StyleCustom.letritasNegras(workbook,"DERECHA",(short)8,HSSFColor.GREY_50_PERCENT.index));
					        }else{
					        	if(cliente.getnVeces() > 1){
						        	cellInterprete.setCellStyle(StyleCustom.letritasNegras(workbook,"DERECHA",(short)8,cliente.getColor()));
							        cellInterprete2.setCellStyle(StyleCustom.letritasNegras(workbook,"DERECHA",(short)8,cliente.getColor()));
					        	}else{
					        		cellInterprete.setCellStyle(StyleCustom.letritasNegras(workbook,"DERECHA",(short)8,HSSFColor.BLACK.index));
							        cellInterprete2.setCellStyle(StyleCustom.letritasNegras(workbook,"DERECHA",(short)8,HSSFColor.BLACK.index));
					        	}
					        }
					        
					        Cell cellInterpreteVal = rowsInterprete.createCell(firstColumn + 2);
					        Cell cellInterpreteVal2 = rowsInterprete.createCell(firstColumn + 3);
					        Cell cellInterpreteVal3 = rowsInterprete.createCell(firstColumn + 4);
					        sheet.addMergedRegion(new CellRangeAddress(countceldaCEITH + 2,countceldaCEITH + 2,firstColumn + 2,lastColumn));
					        cellInterpreteVal.setCellValue(sInterpretes);
					        if(lisDia.getIdEventoStatus() != 5){
					        	cellInterpreteVal.setCellStyle(StyleCustom.letritasNegras(workbook,"IZQUIERDA",(short)10,HSSFColor.GREY_50_PERCENT.index));
						        cellInterpreteVal2.setCellStyle(StyleCustom.letritasNegras(workbook,"IZQUIERDA",(short)10,HSSFColor.GREY_50_PERCENT.index));
						        cellInterpreteVal3.setCellStyle(StyleCustom.letritasNegras(workbook,"IZQUIERDA",(short)10,HSSFColor.GREY_50_PERCENT.index));
					        }else{
					        	if(cliente.getnVeces() > 1){
						        	cellInterpreteVal.setCellStyle(StyleCustom.letritasNegras(workbook,"IZQUIERDA",(short)10,cliente.getColor()));
							        cellInterpreteVal2.setCellStyle(StyleCustom.letritasNegras(workbook,"IZQUIERDA",(short)10,cliente.getColor()));
							        cellInterpreteVal3.setCellStyle(StyleCustom.letritasNegras(workbook,"IZQUIERDA",(short)10,cliente.getColor()));
					        	}else{
					        		cellInterpreteVal.setCellStyle(StyleCustom.letritasNegras(workbook,"IZQUIERDA",(short)10,HSSFColor.BLACK.index));
							        cellInterpreteVal2.setCellStyle(StyleCustom.letritasNegras(workbook,"IZQUIERDA",(short)10,HSSFColor.BLACK.index));
							        cellInterpreteVal3.setCellStyle(StyleCustom.letritasNegras(workbook,"IZQUIERDA",(short)10,HSSFColor.BLACK.index));
					        	}
					        }
					        
					      //Seccion 10a fila 
					        rowsTecnicos = sheet.getRow(mapRows.get(countRows) + 4);
					        Cell celltecnico = rowsTecnicos.createCell(firstColumn);
					        Cell celltecnico2 = rowsTecnicos.createCell(firstColumn+1);
					        celltecnico.setCellValue("Técnico(s):");
					        sheet.addMergedRegion(new CellRangeAddress(countceldaCEITH + 3,countceldaCEITH + 3,firstColumn,lastColumn - 3));
					        if(lisDia.getIdEventoStatus() != 5){
					        	celltecnico.setCellStyle(StyleCustom.letritasNegras(workbook,"DERECHA",(short)8,HSSFColor.GREY_50_PERCENT.index));
						        celltecnico2.setCellStyle(StyleCustom.letritasNegras(workbook,"DERECHA",(short)8,HSSFColor.GREY_50_PERCENT.index));
					        }else{
					        	if(cliente.getnVeces() > 1){
						        	celltecnico.setCellStyle(StyleCustom.letritasNegras(workbook,"DERECHA",(short)8,cliente.getColor()));
							        celltecnico2.setCellStyle(StyleCustom.letritasNegras(workbook,"DERECHA",(short)8,cliente.getColor()));
					        	}else{
					        		celltecnico.setCellStyle(StyleCustom.letritasNegras(workbook,"DERECHA",(short)8,HSSFColor.BLACK.index));
							        celltecnico2.setCellStyle(StyleCustom.letritasNegras(workbook,"DERECHA",(short)8,HSSFColor.BLACK.index));
					        	}
					        }
					        
					        
					        Cell cellTecnicoVal = rowsTecnicos.createCell(firstColumn + 2);
					        Cell cellTecnicoVal2 = rowsTecnicos.createCell(firstColumn + 3);
					        Cell cellTecnicoVal3= rowsTecnicos.createCell(firstColumn + 4);
					        sheet.addMergedRegion(new CellRangeAddress(countceldaCEITH + 3,countceldaCEITH + 3,firstColumn + 2,lastColumn));
					        cellTecnicoVal.setCellValue(sTecnicos);
					        if(lisDia.getIdEventoStatus() != 5){
					        	cellTecnicoVal.setCellStyle(StyleCustom.letritasNegras(workbook,"IZQUIERDA",(short)10,HSSFColor.GREY_50_PERCENT.index));
						        cellTecnicoVal2.setCellStyle(StyleCustom.letritasNegras(workbook,"IZQUIERDA",(short)10,HSSFColor.GREY_50_PERCENT.index));
						        cellTecnicoVal3.setCellStyle(StyleCustom.letritasNegras(workbook,"IZQUIERDA",(short)10,HSSFColor.GREY_50_PERCENT.index));
					        }else{
					        	if(cliente.getnVeces() > 1){
						        	cellTecnicoVal.setCellStyle(StyleCustom.letritasNegras(workbook,"IZQUIERDA",(short)10,cliente.getColor()));
							        cellTecnicoVal2.setCellStyle(StyleCustom.letritasNegras(workbook,"IZQUIERDA",(short)10,cliente.getColor()));
							        cellTecnicoVal3.setCellStyle(StyleCustom.letritasNegras(workbook,"IZQUIERDA",(short)10,cliente.getColor()));
					        	}else{
					        		cellTecnicoVal.setCellStyle(StyleCustom.letritasNegras(workbook,"IZQUIERDA",(short)10,HSSFColor.BLACK.index));
							        cellTecnicoVal2.setCellStyle(StyleCustom.letritasNegras(workbook,"IZQUIERDA",(short)10,HSSFColor.BLACK.index));
							        cellTecnicoVal3.setCellStyle(StyleCustom.letritasNegras(workbook,"IZQUIERDA",(short)10,HSSFColor.BLACK.index));
					        	}
					        }
					        
					        
					       //Seccion 11a fila 
					        rowsHorarios = sheet.getRow(mapRows.get(countRows) + 5);
					        Cell cellHorario = rowsHorarios.createCell(firstColumn);
					        Cell cellHorario2 = rowsHorarios.createCell(firstColumn+1);
					        cellHorario.setCellValue("Horario:");
					        sheet.addMergedRegion(new CellRangeAddress(countceldaCEITH + 4,countceldaCEITH + 4,firstColumn,lastColumn - 3));
					        if(lisDia.getIdEventoStatus() != 5){
					        	cellHorario.setCellStyle(StyleCustom.letritasNegras(workbook,"DERECHA",(short)8,HSSFColor.GREY_50_PERCENT.index));
						        cellHorario2.setCellStyle(StyleCustom.letritasNegras(workbook,"DERECHA",(short)8,HSSFColor.GREY_50_PERCENT.index));
					        }else{
					        	if(cliente.getnVeces() > 1){
						        	cellHorario.setCellStyle(StyleCustom.letritasNegras(workbook,"DERECHA",(short)8,cliente.getColor()));
							        cellHorario2.setCellStyle(StyleCustom.letritasNegras(workbook,"DERECHA",(short)8,cliente.getColor()));
					        	}else{
					        		cellHorario.setCellStyle(StyleCustom.letritasNegras(workbook,"DERECHA",(short)8,HSSFColor.BLACK.index));
							        cellHorario2.setCellStyle(StyleCustom.letritasNegras(workbook,"DERECHA",(short)8,HSSFColor.BLACK.index));
					        	}
					        }
					        
					        
					        Cell cellHorarioVal = rowsHorarios.createCell(firstColumn + 2);
					        //sheet.addMergedRegion(new CellRangeAddress(11,11,firstColumn + 2,lastColumn - 1));
					        cellHorarioVal.setCellValue(fHorario);
					        if(lisDia.getIdEventoStatus() != 5){
					        	cellHorarioVal.setCellStyle(StyleCustom.letritasNegras(workbook,"IZQUIERDA",(short)8,HSSFColor.GREY_50_PERCENT.index));
					        }else{
					        	if(cliente.getnVeces() > 1){
					        		cellHorarioVal.setCellStyle(StyleCustom.letritasNegras(workbook,"IZQUIERDA",(short)8,cliente.getColor()));
					        	}else{
					        		cellHorarioVal.setCellStyle(StyleCustom.letritasNegras(workbook,"IZQUIERDA",(short)8,HSSFColor.BLACK.index));
					        	}
					        }
					        
					        
					        Cell cellTema = rowsHorarios.createCell(firstColumn + 3);
					        //sheet.addMergedRegion(new CellRangeAddress(11,11,firstColumn + 2,lastColumn - 1));
					        cellTema.setCellValue("Tema:");
					        if(lisDia.getIdEventoStatus() != 5){
					        	cellTema.setCellStyle(StyleCustom.letritasNegras(workbook,"DERECHA",(short)8,HSSFColor.GREY_50_PERCENT.index));
					        }else{
					        	if(cliente.getnVeces() > 1){
					        		cellTema.setCellStyle(StyleCustom.letritasNegras(workbook,"DERECHA",(short)8,cliente.getColor()));
					        	}else{
					        		cellTema.setCellStyle(StyleCustom.letritasNegras(workbook,"DERECHA",(short)8,HSSFColor.BLACK.index));
					        	}
					        }
					        
					        rowsHorarios.setHeight((short) 1000);
					        
					        Cell cellTemaVal = rowsHorarios.createCell(firstColumn + 4);
					        //sheet.addMergedRegion(new CellRangeAddress(11,11,firstColumn + 2,lastColumn - 1));
					        cellTemaVal.setCellValue("");
					        if(lisDia.getIdEventoStatus() != 5){
					        	cellTemaVal.setCellStyle(StyleCustom.letritasNegras(workbook,"JUSTIFICADO",(short)10,HSSFColor.GREY_50_PERCENT.index));
					        }else{
					        	if(cliente.getnVeces() > 1){
					        		cellTemaVal.setCellStyle(StyleCustom.letritasNegras(workbook,"JUSTIFICADO",(short)10,cliente.getColor()));
					        	}else{
					        		cellTemaVal.setCellStyle(StyleCustom.letritasNegras(workbook,"JUSTIFICADO",(short)10,HSSFColor.BLACK.index));
					        	}
					        }
					        
					        
					      
				      
				        diaAnterior = dateGral.getDayOfMonth();
				        
				        
		        	}
		        	
		        }
		        
		        
		       
		        if(countDiaSemana == 7 && (dias.lengthOfMonth() != dateGral.getDayOfMonth())){
		        	//Seccion 2da fila color azul memo
			        //Row rowMemo2 = sheet.createRow(1);
		        	if(!mesAnterior.equals("") &&  (!mesActual.equals(mesAnterior)) ){
			        	
		        	}else{
		        		//Seccion 2da fila color azul memo
		        		countCeldaMemoAzul++;
			        	totalceldaMemoAzul = (countDiaSemana * countCeldaMemoAzul) * 5 + countCeldaMemoAzul;
				        sheet.setColumnWidth(totalceldaMemoAzul + 1, 1000);
				        cell = rowMemo.createCell(totalceldaMemoAzul + 1);
				        cell.setCellStyle(StyleCustom.colorCelda(workbook, "AZUL"));
				        cellMemo = rowMemo.createCell(totalceldaMemoAzul + 2);
				        
				        sheet.setColumnWidth(totalceldaMemoAzul + 2, 2820);
				        rowMemo.setHeight((short) 360);
				        cellMemo.setCellValue("Memo");
				        
				        cellMemo.setCellStyle(StyleCustom.letritasNegras(workbook,"JUSTIFICADO",(short)8,HSSFColor.BLACK.index));
				        
				        //Para rellenar los bordes de la semana
				        int countCeld 	= 0;
				        int cellMargin 	= 6;
				        for(int i = 3; i < 36; i++){
				        	
				        	
					        cell1 = rowMemo.createCell(totalceldaMemoAzul + i); //PAra las demas celdas rellenarlas del borde hasta terminar el dia que son 5 celdas
					        cell2 = rowMemo.createCell(totalceldaMemoAzul + i);
					        cell3 = rowMemo.createCell(totalceldaMemoAzul + i);
					        cell1.setCellStyle(StyleCustom.letritasNegras(workbook,"JUSTIFICADO",(short)8,HSSFColor.BLACK.index));
					        cell2.setCellStyle(StyleCustom.letritasNegras(workbook,"JUSTIFICADO",(short)8,HSSFColor.BLACK.index));
					        cell3.setCellStyle(StyleCustom.letritasNegras(workbook,"JUSTIFICADO",(short)8,HSSFColor.BLACK.index));
					        
					        cell4 = rowsMemo.createCell(totalceldaMemoAzul + i); //PAra las demas celdas rellenarlas del borde hasta terminar el dia que son 5 celdas
					        cell5 = rowsMemo.createCell(totalceldaMemoAzul + i);
					        cell6 = rowsMemo.createCell(totalceldaMemoAzul + i);
					        cell4.setCellStyle(StyleCustom.letritasNegras(workbook,"JUSTIFICADO",(short)8,HSSFColor.BLACK.index));
					        cell5.setCellStyle(StyleCustom.letritasNegras(workbook,"JUSTIFICADO",(short)8,HSSFColor.BLACK.index));
					        cell6.setCellStyle(StyleCustom.letritasNegras(workbook,"JUSTIFICADO",(short)8,HSSFColor.BLACK.index));
					        
					        cell7 = rowsCancelado.createCell(totalceldaMemoAzul + i); //PAra las demas celdas rellenarlas del borde hasta terminar el dia que son 5 celdas
					        cell8 = rowsCancelado.createCell(totalceldaMemoAzul + i);
					        cell9 = rowsCancelado.createCell(totalceldaMemoAzul + i);
					        cell7.setCellStyle(StyleCustom.letritasNegras(workbook,"JUSTIFICADO",(short)8,HSSFColor.BLACK.index));
					        cell8.setCellStyle(StyleCustom.letritasNegras(workbook,"JUSTIFICADO",(short)8,HSSFColor.BLACK.index));
					        cell9.setCellStyle(StyleCustom.letritasNegras(workbook,"JUSTIFICADO",(short)8,HSSFColor.BLACK.index));
					        
					        cell10 = rowsCancelado.createCell(totalceldaMemoAzul + i); //PAra las demas celdas rellenarlas del borde hasta terminar el dia que son 5 celdas
					        cell11 = rowsPenalizacion.createCell(totalceldaMemoAzul + i);
					        cell12 = rowsPenalizacion.createCell(totalceldaMemoAzul + i);
					        cell10.setCellStyle(StyleCustom.letritasNegras(workbook,"JUSTIFICADO",(short)8,HSSFColor.BLACK.index));
					        cell11.setCellStyle(StyleCustom.letritasNegras(workbook,"JUSTIFICADO",(short)8,HSSFColor.BLACK.index));
					        cell12.setCellStyle(StyleCustom.letritasNegras(workbook,"JUSTIFICADO",(short)8,HSSFColor.BLACK.index));
					        
					        cell13 = rowsCancelado.createCell(totalceldaMemoAzul + i); //PAra las demas celdas rellenarlas del borde hasta terminar el dia que son 5 celdas
					        cell14 = rowsPenalizacion.createCell(totalceldaMemoAzul + i);
					        cell15 = rowsPenalizacion.createCell(totalceldaMemoAzul + i);
					        cell13.setCellStyle(StyleCustom.letritasNegras(workbook,"JUSTIFICADO",(short)8,HSSFColor.BLACK.index));
					        cell14.setCellStyle(StyleCustom.letritasNegras(workbook,"JUSTIFICADO",(short)8,HSSFColor.BLACK.index));
					        cell15.setCellStyle(StyleCustom.letritasNegras(workbook,"JUSTIFICADO",(short)8,HSSFColor.BLACK.index));
					        
					        cell16 = rowsCancelado.createCell(totalceldaMemoAzul + i); //PAra las demas celdas rellenarlas del borde hasta terminar el dia que son 5 celdas
					        cell17 = rowsPenalizacion.createCell(totalceldaMemoAzul + i);
					        cell18 = rowsPenalizacion.createCell(totalceldaMemoAzul + i);
					        cell16.setCellStyle(StyleCustom.letritasNegras(workbook,"JUSTIFICADO",(short)8,HSSFColor.BLACK.index));
					        cell17.setCellStyle(StyleCustom.letritasNegras(workbook,"JUSTIFICADO",(short)8,HSSFColor.BLACK.index));
					        cell18.setCellStyle(StyleCustom.letritasNegras(workbook,"JUSTIFICADO",(short)8,HSSFColor.BLACK.index));
					        
					        cell19 = rowsCancelado.createCell(totalceldaMemoAzul + i); //PAra las demas celdas rellenarlas del borde hasta terminar el dia que son 5 celdas
					        cell20 = rowsPenalizacion.createCell(totalceldaMemoAzul + i);
					        cell21 = rowsPenalizacion.createCell(totalceldaMemoAzul + i);
					        cell19.setCellStyle(StyleCustom.letritasNegras(workbook,"JUSTIFICADO",(short)8,HSSFColor.BLACK.index));
					        cell20.setCellStyle(StyleCustom.letritasNegras(workbook,"JUSTIFICADO",(short)8,HSSFColor.BLACK.index));
					        cell21.setCellStyle(StyleCustom.letritasNegras(workbook,"JUSTIFICADO",(short)8,HSSFColor.BLACK.index));
					        					        
				        }
				        for(int i = 0; i < 7; i++){
				        	countCeld++;
				        	if(countCeld == 1){
					        	sheet.addMergedRegion(new CellRangeAddress(1,1,totalceldaMemoAzul + 3,totalceldaMemoAzul + 5));
					        	sheet.addMergedRegion(new CellRangeAddress(2,2,totalceldaMemoAzul + 3,totalceldaMemoAzul + 5));
					        	sheet.addMergedRegion(new CellRangeAddress(3,3,totalceldaMemoAzul + 3,totalceldaMemoAzul + 5));
					        	sheet.addMergedRegion(new CellRangeAddress(4,4,totalceldaMemoAzul + 3,totalceldaMemoAzul + 5));
					        }else{
					        	sheet.addMergedRegion(new CellRangeAddress(1,1,totalceldaMemoAzul + cellMargin,totalceldaMemoAzul + cellMargin + 4));
					        	sheet.addMergedRegion(new CellRangeAddress(2,2,totalceldaMemoAzul + cellMargin,totalceldaMemoAzul + cellMargin + 4));
					        	sheet.addMergedRegion(new CellRangeAddress(3,3,totalceldaMemoAzul + cellMargin,totalceldaMemoAzul + cellMargin + 4));
					        	sheet.addMergedRegion(new CellRangeAddress(4,4,totalceldaMemoAzul + cellMargin,totalceldaMemoAzul + cellMargin + 4));
					        	cellMargin = cellMargin + 5;
					        }
				        }
				        //***TERMINA LOS BORDES FALTANTE***//
				        
				      //Seccion 2da fila color blanco s/memo
				        sheet.setColumnWidth(totalceldaMemoAzul + 1, 1000);
				        cell = rowsMemo.createCell(totalceldaMemoAzul + 1);
				        cell.setCellStyle(StyleCustom.colorCelda(workbook, "BLANCO"));
				        Cell cellsMemo = rowsMemo.createCell(totalceldaMemoAzul + 2);
				        sheet.setColumnWidth(totalceldaMemoAzul + 2, 2820);
				        rowsMemo.setHeight((short) 360);
				        cellsMemo.setCellValue("S/Memo");
				        cellsMemo.setCellStyle(StyleCustom.letritasNegras(workbook,"JUSTIFICADO",(short)8,HSSFColor.BLACK.index));
				      //Seccion 3da fila color rojo cancelado
				        sheet.setColumnWidth(totalceldaMemoAzul + 1, 1000);
				        cell = rowsCancelado.createCell(totalceldaMemoAzul + 1);
				        cell.setCellStyle(StyleCustom.colorCelda(workbook, "ROJO"));
				        cellCancelado = rowsCancelado.createCell(totalceldaMemoAzul + 2);
				        sheet.setColumnWidth(totalceldaMemoAzul + 2, 2820);
				        rowsCancelado.setHeight((short) 360);
				        cellCancelado.setCellValue("Cancelado");
				        cellCancelado.setCellStyle(StyleCustom.letritasNegras(workbook,"JUSTIFICADO",(short)8,HSSFColor.BLACK.index));
				      //Seccion 4da fila color rojo cancelado
				        sheet.setColumnWidth(totalceldaMemoAzul + 1, 1000);
				        cell = rowsPenalizacion.createCell(totalceldaMemoAzul + 1);
				        cell.setCellStyle(StyleCustom.colorCelda(workbook, "AMARILLO"));
				        cellPenalizacion = rowsPenalizacion.createCell(totalceldaMemoAzul + 2);
				        sheet.setColumnWidth(totalceldaMemoAzul + 2, 2820);
				        rowsPenalizacion.setHeight((short) 360);
				        cellPenalizacion.setCellValue("Penalización");
				        cellPenalizacion.setCellStyle(StyleCustom.letritasNegras(workbook,"JUSTIFICADO",(short)8,HSSFColor.BLACK.index));
				        //Seccion 6a fila G EN ROJO
				     /*   cell = rowsLetraGde.createCell(totalceldaMemoAzul + 1);
				        rowsLetraGde.setHeight((short) 460);
				        cell.setCellValue("G");
				        cell.setCellStyle(StyleCustom.letraGde(workbook,"ROJO"));
				        Cell cellCotiz = rowsLetraGde.createCell(totalceldaMemoAzul + 2);
				        cellCotiz.setCellValue("Cotización:");
				        cellCotiz.setCellStyle(StyleCustom.letritasNegras(workbook,"DERECHA",(short)8));
				        Cell cellCotizVal = rowsLetraGde.createCell(totalceldaMemoAzul + 3);
				        cellCotizVal.setCellValue("0486-18");
				        cellCotizVal.setCellStyle(StyleCustom.letritasNegras(workbook,"IZQUIERDA",(short)10));
				        Cell cellFac = rowsLetraGde.createCell(totalceldaMemoAzul + 4);
				        sheet.setColumnWidth(totalceldaMemoAzul + 3, 5050);
				        cellFac.setCellValue("Factura(s):");
				        cellFac.setCellStyle(StyleCustom.letritasNegras(workbook,"DERECHA",(short)8));
				        Cell cellFacVal = rowsLetraGde.createCell(totalceldaMemoAzul + 5);
				        cellFacVal.setCellValue("5794");
				        cellFacVal.setCellStyle(StyleCustom.letritasNegras(workbook,"IZQUIERDA",(short)10));
				      //Seccion 7a cliente
				        Cell cellCliente = rowsCliente.createCell(totalceldaMemoAzul + 1);
				        cellCliente.setCellValue("Cliente:");
				        cellCliente.setCellStyle(StyleCustom.letritasNegras(workbook,"DERECHA",(short)8));
				        sheet.addMergedRegion(new CellRangeAddress(7,7,totalceldaMemoAzul + 1,totalceldaMemoAzul + 2));
				        rowsCliente.setHeight((short) 510);//25.5
				        Cell cellClienteVal = rowsCliente.createCell(totalceldaMemoAzul + 3);
				        cellClienteVal.setCellValue("GRUPO DESTINOS");
				        cellClienteVal.setCellStyle(StyleCustom.letritasNegras(workbook,"IZQUIERDA",(short)10));
				        Cell cellSede = rowsCliente.createCell(totalceldaMemoAzul + 4);
				        cellSede.setCellValue("Sede:");
				        cellSede.setCellStyle(StyleCustom.letritasNegras(workbook,"DERECHA",(short)8));
				        Cell cellSedeVal = rowsCliente.createCell(totalceldaMemoAzul + 5);
				        cellSedeVal.setCellValue("CDMX");
				        cellSedeVal.setCellStyle(StyleCustom.letritasNegras(workbook,"IZQUIERDA",(short)10));
				      //Seccion 8a fila cantidad equipo
				        Cell cellCantEquipo = rowsCantEquipo.createCell(totalceldaMemoAzul + 1);
				        cellCantEquipo.setCellValue("Cantidad Equipo:");
				        sheet.addMergedRegion(new CellRangeAddress(8,8,totalceldaMemoAzul + 1,totalceldaMemoAzul + 2));
				        cellCantEquipo.setCellStyle(StyleCustom.letritasNegras(workbook,"DERECHA",(short)8));
				        
				        Cell cellCantEquipoVal = rowsCantEquipo.createCell(totalceldaMemoAzul + 3);
				        sheet.addMergedRegion(new CellRangeAddress(8,8,totalceldaMemoAzul + 3,totalceldaMemoAzul + 5));
				        cellCantEquipoVal.setCellValue("40W, 1CAB, 1TEC");
				        cellCantEquipoVal.setCellStyle(StyleCustom.letritasNegras(workbook,"IZQUIERDA",(short)10));
				       
				      //Seccion 9a fila color negro toda la fila
				        Cell cellInterprete = rowsInterprete.createCell(totalceldaMemoAzul + 1);
				        cellInterprete.setCellValue("Intérprete(s):");
				        sheet.addMergedRegion(new CellRangeAddress(9,9,totalceldaMemoAzul + 1,totalceldaMemoAzul + 2));
				        cellInterprete.setCellStyle(StyleCustom.letritasNegras(workbook,"DERECHA",(short)8));
				        Cell cellInterpreteVal = rowsInterprete.createCell(totalceldaMemoAzul + 3);
				        sheet.addMergedRegion(new CellRangeAddress(9,9,totalceldaMemoAzul + 3,totalceldaMemoAzul + 5));
				        cellInterpreteVal.setCellValue("GABRIELA DURAZO");
				        cellInterpreteVal.setCellStyle(StyleCustom.letritasNegras(workbook,"IZQUIERDA",(short)10));
				        
				      //Seccion 10a fila 
				        Cell celltecnico = rowsTecnicos.createCell(totalceldaMemoAzul + 1);
				        celltecnico.setCellValue("Técnico(s):");
				        sheet.addMergedRegion(new CellRangeAddress(10,10,totalceldaMemoAzul + 1,totalceldaMemoAzul + 2));
				        celltecnico.setCellStyle(StyleCustom.letritasNegras(workbook,"DERECHA",(short)8));
				        
				        Cell cellTecnicoVal = rowsTecnicos.createCell(totalceldaMemoAzul + 3);
				        sheet.addMergedRegion(new CellRangeAddress(10,10,totalceldaMemoAzul + 3,totalceldaMemoAzul + 5));
				        cellTecnicoVal.setCellValue("");
				        cellTecnicoVal.setCellStyle(StyleCustom.letritasNegras(workbook,"IZQUIERDA",(short)10));
				        
				      //Seccion 11a fila 
				        Cell cellHorario = rowsHorarios.createCell(totalceldaMemoAzul + 1);
				        cellHorario.setCellValue("Horario:");
				        sheet.addMergedRegion(new CellRangeAddress(11,11,totalceldaMemoAzul + 1,totalceldaMemoAzul + 2));
				        cellHorario.setCellStyle(StyleCustom.letritasNegras(workbook,"DERECHA",(short)8));
				        
				        Cell cellHorarioVal = rowsHorarios.createCell(totalceldaMemoAzul + 3);
				        //sheet.addMergedRegion(new CellRangeAddress(11,11,firstColumn + 2,lastColumn - 1));
				        cellHorarioVal.setCellValue("7:30 - 9:30 HRS");
				        cellHorarioVal.setCellStyle(StyleCustom.letritasNegras(workbook,"IZQUIERDA",(short)8));
				        
				        Cell cellTema = rowsHorarios.createCell(totalceldaMemoAzul + 4);
				        //sheet.addMergedRegion(new CellRangeAddress(11,11,firstColumn + 2,lastColumn - 1));
				        cellTema.setCellValue("Tema:");
				        cellTema.setCellStyle(StyleCustom.letritasNegras(workbook,"DERECHA",(short)8));
				        rowsHorarios.setHeight((short) 1000);
				        
				        Cell cellTemaVal = rowsHorarios.createCell(totalceldaMemoAzul + 5);
				        //sheet.addMergedRegion(new CellRangeAddress(11,11,firstColumn + 2,lastColumn - 1));
				        cellTemaVal.setCellValue("");
				        cellTemaVal.setCellStyle(StyleCustom.letritasNegras(workbook,"IZQUIERDA",(short)10));
				*/        
		        	}
		        }
		        
		        if(countCuadroAzul == 1){//Solo entra una vez, al inicio del dia lunes del mes
		        	//Seccion 2da fila color azul memo
		        	
			        rowMemo = sheet.createRow(1);
			        sheet.setColumnWidth(1, 1000);
			        cell = rowMemo.createCell(1);
			        cell.setCellStyle(StyleCustom.colorCelda(workbook, "AZUL"));
			        cellMemo = rowMemo.createCell(2);
			        
			        
			        sheet.setColumnWidth(2, 2820);
			        rowMemo.setHeight((short) 360);
			        cellMemo.setCellValue("Memo");
			        cellMemo.setCellStyle(StyleCustom.letritasNegras(workbook,"JUSTIFICADO",(short)8,HSSFColor.BLACK.index));
			        countCuadroAzul = 0;
			      //Seccion 2da fila color blanco s/memo
			        rowsMemo = sheet.createRow(2);
			        sheet.setColumnWidth(1, 1000);
			        cell = rowsMemo.createCell(1);
			        cell.setCellStyle(StyleCustom.colorCelda(workbook, "BLANCO"));
			        cellsMemo = rowsMemo.createCell(2);
			        sheet.setColumnWidth(2, 2820);
			        rowsMemo.setHeight((short) 360);
			        cellsMemo.setCellValue("S/Memo");
			        cellsMemo.setCellStyle(StyleCustom.letritasNegras(workbook,"JUSTIFICADO",(short)8,HSSFColor.BLACK.index));
			      //Seccion 3da fila color rojo cancelado
			        rowsCancelado = sheet.createRow(3);
			        sheet.setColumnWidth(1, 1000);
			        cell = rowsCancelado.createCell(1);
			        cell.setCellStyle(StyleCustom.colorCelda(workbook, "ROJO"));
			        cellCancelado = rowsCancelado.createCell(2);
			        sheet.setColumnWidth(2, 2820);
			        rowsCancelado.setHeight((short) 360);
			        cellCancelado.setCellValue("Cancelado");
			        cellCancelado.setCellStyle(StyleCustom.letritasNegras(workbook,"JUSTIFICADO",(short)8,HSSFColor.BLACK.index));
			      //Seccion 4da fila color rojo cancelado
			        rowsPenalizacion = sheet.createRow(4);
			        sheet.setColumnWidth(1, 1000);
			        cell = rowsPenalizacion.createCell(1);
			        cell.setCellStyle(StyleCustom.colorCelda(workbook, "AMARILLO"));
			        cellPenalizacion = rowsPenalizacion.createCell(2);
			        sheet.setColumnWidth(2, 2820);
			        rowsPenalizacion.setHeight((short) 360);
			        cellPenalizacion.setCellValue("Penalización");
			        cellPenalizacion.setCellStyle(StyleCustom.letritasNegras(workbook,"JUSTIFICADO",(short)8,HSSFColor.BLACK.index));
			        
			        for(int i = 3; i < 36; i++){
			        	cellMemoAll = rowMemo.createCell(i);
			        	cellMemoAll.setCellStyle(StyleCustom.letritasNegras(workbook,"JUSTIFICADO",(short)8,HSSFColor.BLACK.index));
			        	cellsMemoAll	= rowsMemo.createCell(i);
			        	cellsMemoAll.setCellStyle(StyleCustom.letritasNegras(workbook,"JUSTIFICADO",(short)8,HSSFColor.BLACK.index));
			        	cellCanceladoAll	= rowsCancelado.createCell(i);
			        	cellCanceladoAll.setCellStyle(StyleCustom.letritasNegras(workbook,"JUSTIFICADO",(short)8,HSSFColor.BLACK.index));
			        	cellPenalizacionAll	= rowsPenalizacion.createCell(i);
			        	cellPenalizacionAll.setCellStyle(StyleCustom.letritasNegras(workbook,"JUSTIFICADO",(short)8,HSSFColor.BLACK.index));
			        }
			        sheet.addMergedRegion(new CellRangeAddress(1,1,3,5));
			        sheet.addMergedRegion(new CellRangeAddress(1,1,6,10));
			        sheet.addMergedRegion(new CellRangeAddress(1,1,11,15));
			        sheet.addMergedRegion(new CellRangeAddress(1,1,16,20));
			        sheet.addMergedRegion(new CellRangeAddress(1,1,21,25));
			        sheet.addMergedRegion(new CellRangeAddress(1,1,26,30));
			        sheet.addMergedRegion(new CellRangeAddress(1,1,31,35));
			        
			        sheet.addMergedRegion(new CellRangeAddress(2,2,3,5));
			        sheet.addMergedRegion(new CellRangeAddress(2,2,6,10));
			        sheet.addMergedRegion(new CellRangeAddress(2,2,11,15));
			        sheet.addMergedRegion(new CellRangeAddress(2,2,16,20));
			        sheet.addMergedRegion(new CellRangeAddress(2,2,21,25));
			        sheet.addMergedRegion(new CellRangeAddress(2,2,26,30));
			        sheet.addMergedRegion(new CellRangeAddress(2,2,31,35));
			        
			        sheet.addMergedRegion(new CellRangeAddress(3,3,3,5));
			        sheet.addMergedRegion(new CellRangeAddress(3,3,6,10));
			        sheet.addMergedRegion(new CellRangeAddress(3,3,11,15));
			        sheet.addMergedRegion(new CellRangeAddress(3,3,16,20));
			        sheet.addMergedRegion(new CellRangeAddress(3,3,21,25));
			        sheet.addMergedRegion(new CellRangeAddress(3,3,26,30));
			        sheet.addMergedRegion(new CellRangeAddress(3,3,31,35));
			        
			        sheet.addMergedRegion(new CellRangeAddress(4,4,3,5));
			        sheet.addMergedRegion(new CellRangeAddress(4,4,6,10));
			        sheet.addMergedRegion(new CellRangeAddress(4,4,11,15));
			        sheet.addMergedRegion(new CellRangeAddress(4,4,16,20));
			        sheet.addMergedRegion(new CellRangeAddress(4,4,21,25));
			        sheet.addMergedRegion(new CellRangeAddress(4,4,26,30));
			        sheet.addMergedRegion(new CellRangeAddress(4,4,31,35));
			        //La quinta fila esta mas arriba
			        //Seccion 6a fila G EN ROJO
			      /*  rowsLetraGde = sheet.createRow(6);
			        cell = rowsLetraGde.createCell(1);
			        rowsLetraGde.setHeight((short) 460);
			        cell.setCellValue("G");
			        cell.setCellStyle(StyleCustom.letraGde(workbook,"ROJO"));
			        Cell cellCotiz = rowsLetraGde.createCell(2);
			        cellCotiz.setCellValue("Cotización:");
			        cellCotiz.setCellStyle(StyleCustom.letritasNegras(workbook,"DERECHA",(short)8));
			        Cell cellCotizVal = rowsLetraGde.createCell(3);
			        cellCotizVal.setCellValue("0486-18");
			        cellCotizVal.setCellStyle(StyleCustom.letritasNegras(workbook,"IZQUIERDA",(short)10));
			        Cell cellFac = rowsLetraGde.createCell(4);
			        sheet.setColumnWidth(3, 6000);
			        cellFac.setCellValue("Factura(s):");
			        cellFac.setCellStyle(StyleCustom.letritasNegras(workbook,"DERECHA",(short)8));
			        Cell cellFacVal = rowsLetraGde.createCell(5);
			        cellFacVal.setCellValue("5794");
			        cellFacVal.setCellStyle(StyleCustom.letritasNegras(workbook,"IZQUIERDA",(short)10));
			      //Seccion cliente
			        rowsCliente = sheet.createRow(7);
			        Cell cellCliente = rowsCliente.createCell(1);
			        cellCliente.setCellValue("Cliente:");
			        cellCliente.setCellStyle(StyleCustom.letritasNegras(workbook,"DERECHA",(short)8));
			        sheet.addMergedRegion(new CellRangeAddress(7,7,firstColumn,lastColumn - 3));
			        rowsCliente.setHeight((short) 510);//25.5
			        Cell cellClienteVal = rowsCliente.createCell(3);
			        cellClienteVal.setCellValue("GRUPO DESTINOS");
			        cellClienteVal.setCellStyle(StyleCustom.letritasNegras(workbook,"IZQUIERDA",(short)10));
			        Cell cellSede = rowsCliente.createCell(4);
			        cellSede.setCellValue("Sede:");
			        cellSede.setCellStyle(StyleCustom.letritasNegras(workbook,"DERECHA",(short)8));
			        Cell cellSedeVal = rowsCliente.createCell(5);
			        cellSedeVal.setCellValue("CDMX");
			        cellSedeVal.setCellStyle(StyleCustom.letritasNegras(workbook,"IZQUIERDA",(short)10));
			      //Seccion 8a fila cantidad equipo
			        rowsCantEquipo = sheet.createRow(8);
			        Cell cellCantEquipo = rowsCantEquipo.createCell(1);
			        cellCantEquipo.setCellValue("Cantidad Equipo:");
			        sheet.addMergedRegion(new CellRangeAddress(8,8,firstColumn,lastColumn - 3));
			        cellCantEquipo.setCellStyle(StyleCustom.letritasNegras(workbook,"DERECHA",(short)8));
			        
			        Cell cellCantEquipoVal = rowsCantEquipo.createCell(3);
			        sheet.addMergedRegion(new CellRangeAddress(8,8,firstColumn + 2,lastColumn));
			        cellCantEquipoVal.setCellValue("40W, 1CAB, 1TEC");
			        cellCantEquipoVal.setCellStyle(StyleCustom.letritasNegras(workbook,"IZQUIERDA",(short)10));
			        
			      //Seccion 9a fila interprete
			        rowsInterprete = sheet.createRow(9);
			        Cell cellInterprete = rowsInterprete.createCell(1);
			        cellInterprete.setCellValue("Intérprete(s):");
			        sheet.addMergedRegion(new CellRangeAddress(9,9,firstColumn,lastColumn - 3));
			        cellInterprete.setCellStyle(StyleCustom.letritasNegras(workbook,"DERECHA",(short)8));
			        Cell cellInterpreteVal = rowsInterprete.createCell(3);
			        sheet.addMergedRegion(new CellRangeAddress(9,9,firstColumn + 2,lastColumn));
			        cellInterpreteVal.setCellValue("GABRIELA DURAZO");
			        cellInterpreteVal.setCellStyle(StyleCustom.letritasNegras(workbook,"IZQUIERDA",(short)10));
			        
			      //Seccion 10a fila 
			        rowsTecnicos = sheet.createRow(10);
			        Cell celltecnico = rowsTecnicos.createCell(1);
			        celltecnico.setCellValue("Técnico(s):");
			        sheet.addMergedRegion(new CellRangeAddress(10,10,firstColumn,lastColumn - 3));
			        celltecnico.setCellStyle(StyleCustom.letritasNegras(workbook,"DERECHA",(short)8));
			        
			        Cell cellTecnicoVal = rowsTecnicos.createCell(3);
			        sheet.addMergedRegion(new CellRangeAddress(10,10,firstColumn + 2,lastColumn));
			        cellTecnicoVal.setCellValue("");
			        cellTecnicoVal.setCellStyle(StyleCustom.letritasNegras(workbook,"IZQUIERDA",(short)10));
			        
			      //Seccion 11a fila 
			        rowsHorarios = sheet.createRow(11);
			        Cell cellHorario = rowsHorarios.createCell(1);
			        cellHorario.setCellValue("Horario:");
			        sheet.addMergedRegion(new CellRangeAddress(11,11,firstColumn,lastColumn - 3));
			        cellHorario.setCellStyle(StyleCustom.letritasNegras(workbook,"DERECHA",(short)8));
			        
			        Cell cellHorarioVal = rowsHorarios.createCell(3);
			        //sheet.addMergedRegion(new CellRangeAddress(11,11,firstColumn + 2,lastColumn - 1));
			        cellHorarioVal.setCellValue("7:30 - 9:30 HRS");
			        cellHorarioVal.setCellStyle(StyleCustom.letritasNegras(workbook,"IZQUIERDA",(short)8));
			        
			        Cell cellTema = rowsHorarios.createCell(4);
			        //sheet.addMergedRegion(new CellRangeAddress(11,11,firstColumn + 2,lastColumn - 1));
			        cellTema.setCellValue("Tema:");
			        cellTema.setCellStyle(StyleCustom.letritasNegras(workbook,"DERECHA",(short)8));
			        rowsHorarios.setHeight((short) 1000);
			        
			        Cell cellTemaVal = rowsHorarios.createCell(5);
			        //sheet.addMergedRegion(new CellRangeAddress(11,11,firstColumn + 2,lastColumn - 1));
			        cellTemaVal.setCellValue("");
			        cellTemaVal.setCellStyle(StyleCustom.letritasNegras(workbook,"IZQUIERDA",(short)10));
	*/ 			        
		        }
		       
		        if(countDiaSemana == 7 && (dias.lengthOfMonth() != dateGral.getDayOfMonth())){
		        	if(!mesAnterior.equals("") &&  (!mesActual.equals(mesAnterior))){
			        	
		        	}else{
		        		countCeldaEspacio++;
			        	totalceldaEspacio = (countDiaSemana * countCeldaEspacio) * 5 + countCeldaEspacio;
			        	Cell cellEspacio = row.createCell(totalceldaEspacio);
			        	cellEspacio.setCellValue("");
			        	sheet.setColumnWidth(totalceldaEspacio, 2820);
		        	}
		        	
			        
		        }
		     
		            
		      //Segunda iteracion. siguiente dia
		        if(countDiaSemana == 7){
		        	firstColumn	=	lastColumn  + 2;
		        	lastColumn	=	firstColumn + 4;
		        	if( !mesAnterior.equals("") &&  (!mesActual.equals(mesAnterior)) ){
			        	contIsLunes 		= 	0;
						firstColumn			=	1;
				    	lastColumn			=	5;
				    	Sheet sheetExiste   = workbook.getSheet(nomMes);//Para que no vuelva a crear el shet cuando el mes termine en domingo 30
				    	if(sheetExiste == null){
				    		sheet 				= workbook.createSheet(nomMes);
				    		countDiaRepetido = 0;//Para que inicia con row 0
				    	}
				    	
				    	row = sheet.createRow(0);
				    	rowsBarraNegra1		= sheet.createRow(5);
				    	rowsBarraNegra1.setHeight((short) 240);
				    	rowsLetraGde 		= sheet.createRow(6);
				    	rowsLetraGde.setHeight((short) 460);
				    	rowsCliente 		= sheet.createRow(7);
				    	rowsCantEquipo 		= sheet.createRow(8);
				    	rowsInterprete		= sheet.createRow(9);
				    	rowsTecnicos		= sheet.createRow(10);
				    	rowsHorarios		= sheet.createRow(11);
				    	mesAnterior 		= "";
				    	countCeldaEspacio  	= 0;
				    	countCeldaMemoAzul 	= 0;
				    	mapRows				= new TreeMap<Integer, Integer>();
			        }
		        	countDiaSemana = 0;
		        }else{
		        	firstColumn	=	lastColumn  + 1;
		        	lastColumn	=	firstColumn + 4;
		        }
		        
		    	
		        
		        
    	}        
		    countRows = 0;  
		    countceldaCEITH = 0;
	}
    	
    	mesAnterior = nomMes;
    	
    	if( !mesAnterior.equals("") &&  (!mesActual.equals(mesAnterior)) ){
    		countDiaRepetido = 0;
    	}
    	
	}
		if(listMes.isEmpty()){
			sheet 		= workbook.createSheet("Sin información");
		}
		
        return workbook;
        
	}

}

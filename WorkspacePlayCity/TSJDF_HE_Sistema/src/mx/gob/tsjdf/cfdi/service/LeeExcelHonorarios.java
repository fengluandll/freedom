package mx.gob.tsjdf.cfdi.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import mx.gob.tsjdf.cfdi.dto.HonorariosDTO;
import mx.gob.tsjdf.cfdi.dto.HonorariosPercepDto;
import mx.gob.tsjdf.cfdi.recibos.conf.CamposExcel;
import mx.gob.tsjdf.cfdi.recibos.conf.CamposPercepExcel;
import mx.gob.tsjdf.util.Funciones;


public class LeeExcelHonorarios {
	
	
	
	Map<String,HonorariosDTO>       mapHono       = new Hashtable<>();
	Map<String,HonorariosPercepDto> mapHonoPercep = new Hashtable<>();
	DecimalFormat formato = new DecimalFormat("#########.00");
	
	public Map<String,HonorariosDTO> dameHonorarios(FileInputStream excelDeduPer,FileInputStream excelHonorarios,List<String> listMsgErrores){
		//Carga Excel
				HonorariosDTO 	honorariosDTO;
				FileInputStream file;
				int count = 0;
				List<HonorariosDTO> listHeader = new ArrayList<>();
				try {
					//file = new FileInputStream(new File(excelHonorarios));
					XSSFWorkbook workbook = new XSSFWorkbook (excelHonorarios);
					XSSFSheet sheet = workbook.getSheetAt(0);
					
					//Get iterator to all the rows in current sheet
					Iterator<Row> rowIterator = sheet.iterator();
					Cell cell = null;
					String numEmp = "";
					while (rowIterator.hasNext()) {
						count ++;
						honorariosDTO = new HonorariosDTO();
						
						Row row = rowIterator.next();
						
						cell = row.getCell(CamposExcel.SUBTOTAL);					
						if(cell != null){//Para que no tome los nulos y no salga la exception nullPointeException
							if(cell.getRowIndex() > 0){ //para que no tome el encabezado
								switch(cell.getCellType()) {
				                case Cell.CELL_TYPE_NUMERIC:
				                	String valor     = String.valueOf(cell.getNumericCellValue());
									String despPunto = StringUtils.substringAfter(valor,".");
									honorariosDTO.setTotPercep(despPunto.equals("0")?String.valueOf(cell.getNumericCellValue())+"0":String.valueOf(cell.getNumericCellValue()));
				                    break;
				                case Cell.CELL_TYPE_STRING:
				                	
				                    break;
							}
								
							}
						}
						cell = row.getCell(CamposExcel.TOTAL);					
						if(cell != null){
							if(cell.getRowIndex() > 0){ 
								switch(cell.getCellType()) {
				                case Cell.CELL_TYPE_NUMERIC:
				                	String valor     = String.valueOf(cell.getNumericCellValue());
									String despPunto = StringUtils.substringAfter(valor,".");
									honorariosDTO.setTotal(despPunto.length()==1?String.valueOf(cell.getNumericCellValue())+"0":String.valueOf(cell.getNumericCellValue()));
				                    break;
				                case Cell.CELL_TYPE_STRING:
				                	
				                    break;
								}
								
							}
						}
						cell = row.getCell(CamposExcel.DESCUENTO);					
						if(cell != null){
							if(cell.getRowIndex() > 0){ 
								switch(cell.getCellType()) {
				                case Cell.CELL_TYPE_NUMERIC:
				                	
				                	String valorFormateado = formato.format(cell.getNumericCellValue());
				                	if(valorFormateado.equals(".00")){
				                		valorFormateado = "0.00";
				                		honorariosDTO.setDescuento(valorFormateado);
				                		break;
				                	}else{
					                	String valor     = String.valueOf(valorFormateado);
										String despPunto = StringUtils.substringAfter(valor,".");
										honorariosDTO.setDescuento(despPunto.length()==1?String.valueOf(valorFormateado)+"0":String.valueOf(valorFormateado));
					                    break;
				                	}
				                case Cell.CELL_TYPE_STRING:
				                	
				                    break;
								}
								
							}
						}
						cell = row.getCell(CamposExcel.METODODEPAGO);					
						if(cell != null){
							if(cell.getRowIndex() > 0){ 
								switch(cell.getCellType()) {
				                case Cell.CELL_TYPE_NUMERIC:
				                	
				                    break;
				                case Cell.CELL_TYPE_STRING:
				                	honorariosDTO.setMetodopago(cell.getStringCellValue());
				                    break;
								}
								
							}
						}
						cell = row.getCell(CamposExcel.SERIE);					
						if(cell != null){
							if(cell.getRowIndex() > 0){ 
								switch(cell.getCellType()) {
				                case Cell.CELL_TYPE_NUMERIC:
				                	honorariosDTO.setClavedep(StringUtils.substringBefore(String.valueOf(cell.getNumericCellValue()), ".") );
				                    break;
				                case Cell.CELL_TYPE_STRING:
				                	honorariosDTO.setClavedep(cell.getStringCellValue());
				                    break;
								}
								
							}
						}
						cell = row.getCell(CamposExcel.RFC);					
						if(cell != null){
							if(cell.getRowIndex() > 0){ 
								switch(cell.getCellType()) {
				                case Cell.CELL_TYPE_NUMERIC:
				                	
				                    break;
				                case Cell.CELL_TYPE_STRING:
				                	honorariosDTO.setRfc(cell.getStringCellValue());
				                    break;
								}
								
							}
						}
						cell = row.getCell(CamposExcel.NOMBRE);					
						if(cell != null){
							if(cell.getRowIndex() > 0){ 
								switch(cell.getCellType()) {
				                case Cell.CELL_TYPE_NUMERIC:
				                	
				                    break;
				                case Cell.CELL_TYPE_STRING:
				                	honorariosDTO.setNombre(cell.getStringCellValue());
				                    break;
								}
								
							}
						}
						cell = row.getCell(CamposExcel.CANTIDAD);					
						if(cell != null){
							if(cell.getRowIndex() > 0){ 
								switch(cell.getCellType()) {
					                case Cell.CELL_TYPE_NUMERIC:
					                	honorariosDTO.setCantidad((int) cell.getNumericCellValue());
					                    break;
					                case Cell.CELL_TYPE_STRING:
					                	honorariosDTO.setCantidad((Integer.parseInt(cell.getStringCellValue())));
					                    break;
								}
								
							}
						}
						cell = row.getCell(CamposExcel.UNIDAD);					
						if(cell != null){
							if(cell.getRowIndex() > 0){ 
								switch(cell.getCellType()) {
				                case Cell.CELL_TYPE_NUMERIC:
				                	
				                    break;
				                case Cell.CELL_TYPE_STRING:
				                	honorariosDTO.setUnidad(cell.getStringCellValue());
				                    break;
								}
								
							}
						}
						cell = row.getCell(CamposExcel.DESCRIPCION);					
						if(cell != null){
							if(cell.getRowIndex() > 0){ 
								switch(cell.getCellType()) {
				                case Cell.CELL_TYPE_NUMERIC:
				                	
				                    break;
				                case Cell.CELL_TYPE_STRING:
				                	honorariosDTO.setDescripcion(cell.getStringCellValue());
				                    break;
								}
								
							}
						}
						cell = row.getCell(CamposExcel.VALORUNITARIO);					
						if(cell != null){
							if(cell.getRowIndex() > 0){ 
								switch(cell.getCellType()) {
				                case Cell.CELL_TYPE_NUMERIC:
				                	String valorFormateado = formato.format(cell.getNumericCellValue());
				                	String valor     = valorFormateado;
									String despPunto = StringUtils.substringAfter(valor,".");
									honorariosDTO.setValorunita(despPunto.equals("0")?valorFormateado+"0":valorFormateado);
				                    break;
				                case Cell.CELL_TYPE_STRING:
				                	
				                    break;
								}
								
							}
						}
						cell = row.getCell(CamposExcel.IMPORTE);					
						if(cell != null){
							if(cell.getRowIndex() > 0){
								switch(cell.getCellType()) {
				                case Cell.CELL_TYPE_NUMERIC:
				                	String valor     = String.valueOf(cell.getNumericCellValue());
									String despPunto = StringUtils.substringAfter(valor,".");
									honorariosDTO.setImporte(despPunto.equals("0")?String.valueOf(cell.getNumericCellValue())+"0":String.valueOf(cell.getNumericCellValue()));
				                    break;
				                case Cell.CELL_TYPE_STRING:
				                	
				                    break;
								}
								
								
							}
						}
						cell = row.getCell(CamposExcel.NUMEMPLEADO);					
						if(cell != null){
							if(cell.getRowIndex() > 0){ 
								switch(cell.getCellType()) {
				                case Cell.CELL_TYPE_NUMERIC:
				                	String emp = StringUtils.substringBefore(String.valueOf(cell.getNumericCellValue()),".");
				                	honorariosDTO.setNumemplead(emp);
				                    break;
				                case Cell.CELL_TYPE_STRING:
				                	honorariosDTO.setNumemplead(cell.getStringCellValue());
				                    break;
								}
								
							}
						}
						cell = row.getCell(CamposExcel.CURP);					
						if(cell != null){
							if(cell.getRowIndex() > 0){ 
								switch(cell.getCellType()) {
				                case Cell.CELL_TYPE_NUMERIC:
				                	
				                    break;
				                case Cell.CELL_TYPE_STRING:
				                	honorariosDTO.setCurp(cell.getStringCellValue());
				                    break;
								}
								
							}
						}
						cell = row.getCell(CamposExcel.TIPOREGIMEN);					
						if(cell != null){
							if(cell.getRowIndex() > 0){ 
								switch(cell.getCellType()) {
				                case Cell.CELL_TYPE_NUMERIC:
				                	honorariosDTO.setTiporegime((int)cell.getNumericCellValue());
				                    break;
				                case Cell.CELL_TYPE_STRING:
				                	
				                    break;
								}
								
							}
						}
						cell = row.getCell(CamposExcel.FECHAPAGO);					
						if(cell != null){
							if(cell.getRowIndex() > 0){ 
								switch(cell.getCellType()) {
				                case Cell.CELL_TYPE_NUMERIC:
				                	listMsgErrores.add("El formato de fecha de Pago debe ser texto");
				                    break;
				                case Cell.CELL_TYPE_STRING:
				                	honorariosDTO.setFechapago(cell.getStringCellValue());
				                    break;
								}
								
							}
						}
						cell = row.getCell(CamposExcel.FECHAINICIALPAGO);					
						if(cell != null){
							if(cell.getRowIndex() > 0){ 
								
								switch(cell.getCellType()) {
				                case Cell.CELL_TYPE_NUMERIC:
				                	honorariosDTO.setFechainici("null");
				                	listMsgErrores.add("El formato de fecha Inicial de Pago debe ser texto");
				                    break;
				                case Cell.CELL_TYPE_STRING:
				                    honorariosDTO.setFechainici(cell.getStringCellValue());
				                    break;
								}
								
							}
						}
						cell = row.getCell(CamposExcel.FECHAFINALPAGO);					
						if(cell != null){
							if(cell.getRowIndex() > 0){ 
								switch(cell.getCellType()) {
				                case Cell.CELL_TYPE_NUMERIC:
				                	listMsgErrores.add("El formato de fecha Final de Pago debe ser texto");
				                    break;
				                case Cell.CELL_TYPE_STRING:
				                	honorariosDTO.setFechafinal(cell.getStringCellValue());
				                    break;
								}
								
							}
						}
						cell = row.getCell(CamposExcel.NUMDIASPAGADOS);					
						if(cell != null){
							if(cell.getRowIndex() > 0){ 
								switch(cell.getCellType()) {
				                case Cell.CELL_TYPE_NUMERIC:
				                	honorariosDTO.setNumdiaspag((int)cell.getNumericCellValue());
				                    break;
				                case Cell.CELL_TYPE_STRING:
				                	
				                    break;
								}
								
							}
						}
						cell = row.getCell(CamposExcel.DEPARTAMENTO);					
						if(cell != null){
							if(cell.getRowIndex() > 0){ 
								switch(cell.getCellType()) {
				                case Cell.CELL_TYPE_NUMERIC:
				                	
				                    break;
				                case Cell.CELL_TYPE_STRING:
				                	honorariosDTO.setDepartamen(cell.getStringCellValue());
				                    break;
								}
								
							}
						}
						cell = row.getCell(CamposExcel.PUESTO);					
						if(cell != null){
							if(cell.getRowIndex() > 0){ 
								switch(cell.getCellType()) {
				                case Cell.CELL_TYPE_NUMERIC:
				                	
				                    break;
				                case Cell.CELL_TYPE_STRING:
				                	honorariosDTO.setPuesto(cell.getStringCellValue());
				                    break;
								}
								
							}
						}
						
						cell = row.getCell(CamposExcel.TIPO_CONTRATO);					
						if(cell != null){
							if(cell.getRowIndex() > 0){ 
								switch(cell.getCellType()) {
				                case Cell.CELL_TYPE_NUMERIC:
				                	
				                    break;
				                case Cell.CELL_TYPE_STRING:
				                	honorariosDTO.setTipoContrato(cell.getStringCellValue());
				                    break;
								}
								
							}
						}
						cell = row.getCell(CamposExcel.TIPO_JORNADA);					
						if(cell != null){
							if(cell.getRowIndex() > 0){ 
								switch(cell.getCellType()) {
				                case Cell.CELL_TYPE_NUMERIC:
				                	
				                    break;
				                case Cell.CELL_TYPE_STRING:
				                	honorariosDTO.setTipoJornada(cell.getStringCellValue());
				                    break;
								}
								
							}
						}
						
						
						cell = row.getCell(CamposExcel.PERIODICIDADPAGO);					
						if(cell != null){
							if(cell.getRowIndex() > 0){ 
								switch(cell.getCellType()) {
				                case Cell.CELL_TYPE_NUMERIC:
				                	
				                    break;
				                case Cell.CELL_TYPE_STRING:
				                	honorariosDTO.setPeriodicid(cell.getStringCellValue());
				                    break;
								}
								
							}
						}
						cell = row.getCell(CamposExcel.TOTALGRAVADO);					
						if(cell != null){
							if(cell.getRowIndex() > 0){ 
								switch(cell.getCellType()) {
				                case Cell.CELL_TYPE_NUMERIC:
				                	String valor     = String.valueOf(cell.getNumericCellValue());
									String despPunto = StringUtils.substringAfter(valor,".");
									honorariosDTO.setTotPergra(despPunto.equals("0")?String.valueOf(cell.getNumericCellValue())+"0":String.valueOf(cell.getNumericCellValue()));
				                    break;
				                case Cell.CELL_TYPE_STRING:
				                	
				                    break;
								}
								
							}
						}
						cell = row.getCell(CamposExcel.TOTALEXENTO);					
						if(cell != null){
							if(cell.getRowIndex() > 0){ 
								switch(cell.getCellType()) {
				                case Cell.CELL_TYPE_NUMERIC:
				                	if(cell.getNumericCellValue() == 0.0){
										honorariosDTO.setTotPerexe("00.00");
									}else{
										String valor     = String.valueOf(cell.getNumericCellValue());
										String despPunto = StringUtils.substringAfter(valor,".");
										honorariosDTO.setTotPerexe(despPunto.equals("0")?String.valueOf(cell.getNumericCellValue())+"0":String.valueOf(cell.getNumericCellValue()));
									}
				                    break;
				                case Cell.CELL_TYPE_STRING:
				                	
				                    break;
								}
								
							}
						}
					/*	cell = row.getCell(CamposExcel.TOTALIMPUESTOSRETENIDOS);					
						if(cell != null){
							if(cell.getRowIndex() > 0){
								String valor     = String.valueOf(cell.getNumericCellValue());
								String despPunto = StringUtils.substringAfter(valor,".");
								honorariosDTO.setImporteisr(despPunto.equals("0")?String.valueOf(cell.getNumericCellValue())+"0":String.valueOf(cell.getNumericCellValue()));
							}
						}*/
						cell = row.getCell(CamposExcel.IMPORTE_SR);					
						if(cell != null){
							if(cell.getRowIndex() > 0){ 
								switch(cell.getCellType()) {
				                case Cell.CELL_TYPE_NUMERIC:
				                	String importe   = "";
									String valor     = String.valueOf(cell.getNumericCellValue());
									String despPunto = StringUtils.substringAfter(valor,".");
									if(despPunto.equals("0")){
										importe = String.valueOf(cell.getNumericCellValue())+"0";
									}else if(!despPunto.equals("0") && despPunto.length() == 1){
										importe = String.valueOf(cell.getNumericCellValue())+"0";
									}else{
										importe = String.valueOf(cell.getNumericCellValue());
									}
									honorariosDTO.setImporteisr(importe);
				                    break;
				                case Cell.CELL_TYPE_STRING:
				                	
				                    break;
								}
								
							}
						}
						cell = row.getCell(CamposExcel.ZONAPAGADORA);					
						if(cell != null){
							if(cell.getRowIndex() > 0){ 
								switch(cell.getCellType()) {
				                case Cell.CELL_TYPE_NUMERIC:
				                	
				                    break;
				                case Cell.CELL_TYPE_STRING:
				                	honorariosDTO.setZonaPagadora(cell.getStringCellValue());
				                    break;
								}
								
							}
						}
						cell = row.getCell(CamposExcel.AREA);					
						if(cell != null){
							if(cell.getRowIndex() > 0){ 
								switch(cell.getCellType()) {
				                case Cell.CELL_TYPE_NUMERIC:
				                	
				                    break;
				                case Cell.CELL_TYPE_STRING:
				                	honorariosDTO.setClavedep(cell.getStringCellValue());
				                    break;
								}
								
							}
						}
						cell = row.getCell(CamposExcel.CODIGO);					
						if(cell != null){
							if(cell.getRowIndex() > 0){ 
								switch(cell.getCellType()) {
				                case Cell.CELL_TYPE_NUMERIC:
				                	
				                    break;
				                case Cell.CELL_TYPE_STRING:
				                	honorariosDTO.setCodigo(cell.getStringCellValue());
				                    break;
								}
								
							}
						}
						cell = row.getCell(CamposExcel.NIVEL);					
						if(cell != null){
							if(cell.getRowIndex() > 0){ 
								switch(cell.getCellType()) {
				                case Cell.CELL_TYPE_NUMERIC:
				                	
				                    break;
				                case Cell.CELL_TYPE_STRING:
				                	honorariosDTO.setNivel(cell.getStringCellValue());
				                    break;
								}
								
							}
						}
						cell = row.getCell(CamposExcel.UNIVERSO);					
						if(cell != null){
							if(cell.getRowIndex() > 0){ 
								
								switch(cell.getCellType()) {
				                case Cell.CELL_TYPE_NUMERIC:
				                	honorariosDTO.setUniverso(String.valueOf(cell.getNumericCellValue()));
				                    break;
				                case Cell.CELL_TYPE_STRING:
				                	honorariosDTO.setUniverso(cell.getStringCellValue());
				                    break;
							}
								
							}
						}
						cell = row.getCell(CamposExcel.NOPLAZA);					
						if(cell != null){
							if(cell.getRowIndex() > 0){ 
								switch(cell.getCellType()) {
				                case Cell.CELL_TYPE_NUMERIC:
				                	String valor = String.valueOf(cell.getNumericCellValue());
				                	honorariosDTO.setPlaza(StringUtils.substringBefore(valor,"."));
				                    break;
				                case Cell.CELL_TYPE_STRING:
				                	honorariosDTO.setPlaza(cell.getStringCellValue());
				                    break;
								}
								
							}
						}
						cell = row.getCell(CamposExcel.SECCIONSINDICAL);					
						if(cell != null){
							if(cell.getRowIndex() > 0){ 
								switch(cell.getCellType()) {
				                case Cell.CELL_TYPE_NUMERIC:
				                	honorariosDTO.setSeccionSindical(String.valueOf(cell.getNumericCellValue()));
				                    break;
				                case Cell.CELL_TYPE_STRING:
				                	honorariosDTO.setSeccionSindical(cell.getStringCellValue());
				                    break;
								}
								
							}
						}
						cell = row.getCell(CamposExcel.LIQUIDACIONDEPAGO);					
						if(cell != null){
							if(cell.getRowIndex() > 0){ 
								switch(cell.getCellType()) {
				                case Cell.CELL_TYPE_NUMERIC:
				                	
				                    break;
				                case Cell.CELL_TYPE_STRING:
				                	honorariosDTO.setLiquidacionPago(cell.getStringCellValue());
				                    break;
								}
								
							}
						}
						cell = row.getCell(CamposExcel.IMPORTE_PERCEP);					
						if(cell != null){
							if(cell.getRowIndex() > 0){ 
								switch(cell.getCellType()) {
				                case Cell.CELL_TYPE_NUMERIC:
				                	String valor     = String.valueOf(cell.getNumericCellValue());
									String despPunto = StringUtils.substringAfter(valor,".");
									honorariosDTO.setTotPercep(despPunto.equals("0")?String.valueOf(cell.getNumericCellValue())+"0":String.valueOf(cell.getNumericCellValue()));
									listHeader.add(honorariosDTO);
									//HonorariosDTO honorariosDTOs = leeExcelPercepciones(honorariosDTO,excelDeduPer);
									//mapHono.put(honorariosDTO.getNumemplead(), honorariosDTOs);
				                    break;
				                case Cell.CELL_TYPE_STRING:
				                	
				                    break;
								}
								
							}
						}
						
					}
					
					excelHonorarios.close();
					System.out.println("Tamanio array Header "+ listHeader.size());
					List<HonorariosPercepDto> listLines = leeExcelPercepciones(excelDeduPer);
					System.out.println("Tamanio array lines "+ listLines.size());
					String numEmpl2 = null;
					List<HonorariosPercepDto> listPercepciones;
					HonorariosPercepDto honorariosPercepDto;
					for(HonorariosDTO head : listHeader){
						listPercepciones = new ArrayList<>();
						for(HonorariosPercepDto line : listLines){
							
							if(head.getNumemplead().equals(line.getNumEmpleado())){
								
								listPercepciones.add(line);
							}
							
						}
						if(listPercepciones.size() > 0){
							head.setListPercepciones(listPercepciones);
						}
						
						mapHono.put(head.getNumemplead(), head);						
					}
					System.out.println("Tamanio mapa "+mapHono.size());
					//mapHono.put(honorariosDTO.getNumemplead(), honorariosDTOs);
				} catch (IOException e) {
					e.printStackTrace();
					Funciones.mostrarMensaje(e.getMessage(),"Error" , "ERROR");
				}
				return mapHono;
	}
	
	
	public List<HonorariosPercepDto> leeExcelPercepciones(FileInputStream excelHonorarios){
		//FileInputStream file;
		//int count = 0;
		List<HonorariosPercepDto> listPercepciones = new ArrayList<>();
		HonorariosPercepDto 			honorariosPercepDto;
		//List<HonorariosPercepDto> listLines = new ArrayList<>();
		try {
			//file = new FileInputStream(new File(excelHonorarios));
			XSSFWorkbook workbook = new XSSFWorkbook (excelHonorarios);
			XSSFSheet sheet = workbook.getSheetAt(0);
			
			//Get iterator to all the rows in current sheet
			Iterator<Row> rowIterator = sheet.iterator();
			Cell cell = null;
			String numEmp = "";
			
			while (rowIterator.hasNext()) {
				
				honorariosPercepDto = new HonorariosPercepDto();
				
				Row row = rowIterator.next();
							cell = row.getCell(CamposPercepExcel.NUM_EMPLEADO);					
							if(cell != null){
								if(cell.getRowIndex() > 0){
									//Obtiene el numero de empleado para traer sus percepciones
									String numRmpl  = "";
									switch(cell.getCellType()) {
						                case Cell.CELL_TYPE_NUMERIC:
						                	 numRmpl = String.valueOf(StringUtils.substringBefore(String.valueOf(cell.getNumericCellValue()),"."));
						                    break;
						                case Cell.CELL_TYPE_STRING:
						                	numRmpl = cell.getStringCellValue();
						                    break;
						                    
									}
									honorariosPercepDto.setNumEmpleado(numRmpl);
								 }
								}
							cell = row.getCell(CamposPercepExcel.TIPOPERCEPCION);					
							if(cell != null){//Para que no tome los nulos y no salga la exception nullPointeException
								if(cell.getRowIndex() > 0){ //para que no tome el encabezado
									switch(cell.getCellType()) {
					                case Cell.CELL_TYPE_NUMERIC:
					                	
					                    break;
					                case Cell.CELL_TYPE_STRING:
					                	honorariosPercepDto.setcTipopd(cell.getStringCellValue());
					                    break;
									}
									
									
								}
							}
							cell = row.getCell(CamposPercepExcel.CLAVE);					
							if(cell != null){
								if(cell.getRowIndex() > 0){
									switch(cell.getCellType()) {
					                case Cell.CELL_TYPE_NUMERIC:
					                	honorariosPercepDto.setcCve(StringUtils.substringBefore(String.valueOf(cell.getNumericCellValue()),"."));
					                    break;
					                case Cell.CELL_TYPE_STRING:
					                	
					                    break;
									}
									
								}
							}
							cell = row.getCell(CamposPercepExcel.CONCEPTO);					
							if(cell != null){
								if(cell.getRowIndex() > 0){
									switch(cell.getCellType()) {
					                case Cell.CELL_TYPE_NUMERIC:
					                	
					                    break;
					                case Cell.CELL_TYPE_STRING:
					                	honorariosPercepDto.setcNomConcepto(cell.getStringCellValue());
					                    break;
									}
									
								}
							}
							cell = row.getCell(CamposPercepExcel.IMPORTEGRAVADO);					
							if(cell != null){
								if(cell.getRowIndex() > 0){
									switch(cell.getCellType()) {
					                case Cell.CELL_TYPE_NUMERIC:
					                	String valorFormateado = formato.format(cell.getNumericCellValue());
					                	if(valorFormateado.equals(".00")){
					                		valorFormateado = "0.00";
					                		honorariosPercepDto.setnImpGra(valorFormateado);
					                	}else{
						                	//String valor     = valorFormateado;
											String despPunto = StringUtils.substringAfter(valorFormateado,".");
											honorariosPercepDto.setnImpGra(despPunto.equals("0")?valorFormateado+"0":valorFormateado);
					                	}
					                    break;
					                case Cell.CELL_TYPE_STRING:
					                	
					                    break;
									}
									
									
								}
							}
							cell = row.getCell(CamposPercepExcel.IMPORTEEXENTO);					
							if(cell != null){
								if(cell.getRowIndex() > 0){
									switch(cell.getCellType()) {
					                case Cell.CELL_TYPE_NUMERIC:
					                	String valorFormateado = formato.format(cell.getNumericCellValue());
					                	String importe   = "";
										String valor     = valorFormateado;
										String despPunto = StringUtils.substringAfter(valor,".");
										if(valorFormateado.equals(".00")){
											honorariosPercepDto.setnImpexeImporte("0.00");
										}else{
											if(despPunto.equals("0")){
												importe = valorFormateado+"0";
											}else if(!despPunto.equals("0") && despPunto.length() == 1){
												importe = valorFormateado+"0";
											}else{
												importe = valorFormateado;
											}
											honorariosPercepDto.setnImpexeImporte(importe);
										}
					                    break;
					                case Cell.CELL_TYPE_STRING:
					                	
					                    break;
									}
									
									
								}
							}
							cell = row.getCell(CamposPercepExcel.TIPODEDUCCION);					
							if(cell != null){
								if(cell.getRowIndex() > 0){
									switch(cell.getCellType()) {
					                case Cell.CELL_TYPE_NUMERIC:
					                	
					                    break;
					                case Cell.CELL_TYPE_STRING:
					                	honorariosPercepDto.setcTipopdedu(cell.getStringCellValue());
					                    break;
									}
									
								}
							}
							cell = row.getCell(CamposPercepExcel.TIPO);					
							if(cell != null){
								if(cell.getRowIndex() > 0){
									switch(cell.getCellType()) {
					                case Cell.CELL_TYPE_NUMERIC:
					                	
					                    break;
					                case Cell.CELL_TYPE_STRING:
					                	honorariosPercepDto.setcTipo(cell.getStringCellValue());
					                    break;
									}
									
								}
							}
							cell = row.getCell(CamposPercepExcel.DESCRIPCION_PERCEP);					
							if(cell != null){
								if(cell.getRowIndex() > 0){
									switch(cell.getCellType()) {
					                case Cell.CELL_TYPE_NUMERIC:
					                	
					                    break;
					                case Cell.CELL_TYPE_STRING:
					                	honorariosPercepDto.setcNomDesc(cell.getStringCellValue());
					                	listPercepciones.add(honorariosPercepDto);
										//honorariosDTO.setListPercepciones(listPercepciones);
					                    break;
									}
									
								}
							}						
					
				
							
			}
			excelHonorarios.close();
		
		}catch (IOException e) {
			e.printStackTrace();
		}
		return listPercepciones;
	}
	
	
	/*
	public HonorariosDTO leeExcelPercepciones(String excelHonorarios,List<HonorariosDTO> listHead){
		FileInputStream file;
		int count = 0;
		listPercepciones = new ArrayList<>();
		try {
			file = new FileInputStream(new File(excelHonorarios));
			XSSFWorkbook workbook = new XSSFWorkbook (file);
			XSSFSheet sheet = workbook.getSheetAt(0);
			
			//Get iterator to all the rows in current sheet
			Iterator<Row> rowIterator = sheet.iterator();
			Cell cell = null;
			String numEmp = "";
			
			while (rowIterator.hasNext()) {
			
				honorariosPercepDto = new HonorariosPercepDto();
				
				Row row = rowIterator.next();
				cell = row.getCell(CamposPercepExcel.NUM_EMPLEADO);					
				if(cell != null){
					if(cell.getRowIndex() > 0){
						//Obtiene el numero de empleado para traer sus percepciones
						String numRmpl  = "";
						String numEmpl2 = "";
						switch(cell.getCellType()) {
			                case Cell.CELL_TYPE_NUMERIC:
			                	 numRmpl = String.valueOf(cell.getNumericCellValue());
			                	 if(honorariosDTO.getNumemplead().indexOf(".") > -1){
										numEmpl2 = honorariosDTO.getNumemplead();
									}else{
										numEmpl2 = honorariosDTO.getNumemplead();
									}
			                    break;
			                case Cell.CELL_TYPE_STRING:
			                	numRmpl = cell.getStringCellValue();
			                	if(honorariosDTO.getNumemplead().indexOf(".") > -1){
									numEmpl2 = honorariosDTO.getNumemplead();
								}else{
									numEmpl2 = honorariosDTO.getNumemplead();
								}
			                    break;
						}
						
						
						if(numRmpl.equals(numEmpl2)){
								cell = row.getCell(CamposPercepExcel.TIPOPERCEPCION);					
								if(cell != null){//Para que no tome los nulos y no salga la exception nullPointeException
									if(cell.getRowIndex() > 0){ //para que no tome el encabezado
										switch(cell.getCellType()) {
						                case Cell.CELL_TYPE_NUMERIC:
						                	
						                    break;
						                case Cell.CELL_TYPE_STRING:
						                	honorariosPercepDto.setcTipopd(cell.getStringCellValue());
						                    break;
										}
										
										
									}
								}
								cell = row.getCell(CamposPercepExcel.CLAVE);					
								if(cell != null){
									if(cell.getRowIndex() > 0){
										switch(cell.getCellType()) {
						                case Cell.CELL_TYPE_NUMERIC:
						                	honorariosPercepDto.setcCve(StringUtils.substringBefore(String.valueOf(cell.getNumericCellValue()),"."));
						                    break;
						                case Cell.CELL_TYPE_STRING:
						                	
						                    break;
										}
										
									}
								}
								cell = row.getCell(CamposPercepExcel.CONCEPTO);					
								if(cell != null){
									if(cell.getRowIndex() > 0){
										switch(cell.getCellType()) {
						                case Cell.CELL_TYPE_NUMERIC:
						                	
						                    break;
						                case Cell.CELL_TYPE_STRING:
						                	honorariosPercepDto.setcNomConcepto(cell.getStringCellValue());
						                    break;
										}
										
									}
								}
								cell = row.getCell(CamposPercepExcel.IMPORTEGRAVADO);					
								if(cell != null){
									if(cell.getRowIndex() > 0){
										switch(cell.getCellType()) {
						                case Cell.CELL_TYPE_NUMERIC:
						                	String valor     = String.valueOf(cell.getNumericCellValue());
											String despPunto = StringUtils.substringAfter(valor,".");
											honorariosPercepDto.setnImpGra(despPunto.equals("0")?String.valueOf(cell.getNumericCellValue())+"0":String.valueOf(cell.getNumericCellValue()));
						                    break;
						                case Cell.CELL_TYPE_STRING:
						                	
						                    break;
										}
										
										
									}
								}
								cell = row.getCell(CamposPercepExcel.IMPORTEEXENTO);					
								if(cell != null){
									if(cell.getRowIndex() > 0){
										switch(cell.getCellType()) {
						                case Cell.CELL_TYPE_NUMERIC:
						                	String importe   = "";
											String valor     = String.valueOf(cell.getNumericCellValue());
											String despPunto = StringUtils.substringAfter(valor,".");
											if(despPunto.equals("0")){
												importe = String.valueOf(cell.getNumericCellValue())+"0";
											}else if(!despPunto.equals("0") && despPunto.length() == 1){
												importe = String.valueOf(cell.getNumericCellValue())+"0";
											}else{
												importe = String.valueOf(cell.getNumericCellValue());
											}
											honorariosPercepDto.setnImpexeImporte(importe);
						                    break;
						                case Cell.CELL_TYPE_STRING:
						                	
						                    break;
										}
										
										
									}
								}
								cell = row.getCell(CamposPercepExcel.TIPODEDUCCION);					
								if(cell != null){
									if(cell.getRowIndex() > 0){
										switch(cell.getCellType()) {
						                case Cell.CELL_TYPE_NUMERIC:
						                	honorariosPercepDto.setcTipopdedu(cell.getStringCellValue());
						                    break;
						                case Cell.CELL_TYPE_STRING:
						                	
						                    break;
										}
										
									}
								}
								cell = row.getCell(CamposPercepExcel.TIPO);					
								if(cell != null){
									if(cell.getRowIndex() > 0){
										switch(cell.getCellType()) {
						                case Cell.CELL_TYPE_NUMERIC:
						                	
						                    break;
						                case Cell.CELL_TYPE_STRING:
						                	honorariosPercepDto.setcTipo(cell.getStringCellValue());
						                    break;
										}
										
									}
								}
								cell = row.getCell(CamposPercepExcel.DESCRIPCION_PERCEP);					
								if(cell != null){
									if(cell.getRowIndex() > 0){
										switch(cell.getCellType()) {
						                case Cell.CELL_TYPE_NUMERIC:
						                	
						                    break;
						                case Cell.CELL_TYPE_STRING:
						                	honorariosPercepDto.setcNomDesc(cell.getStringCellValue());
						                	listPercepciones.add(honorariosPercepDto);
											honorariosDTO.setListPercepciones(listPercepciones);
						                    break;
										}
										
									}
								}
							
								if(cell != null){
									if(cell.getRowIndex() > 0){
										//listPercepciones.add(honorariosPercepDto);
										//honorariosDTO.setListPercepciones(listPercepciones);
									}
								}
						}
					}
				}
							
			}
			file.close();
		
		}catch (IOException e) {
			e.printStackTrace();
		}
		return honorariosDTO;
	}
	
*/
}

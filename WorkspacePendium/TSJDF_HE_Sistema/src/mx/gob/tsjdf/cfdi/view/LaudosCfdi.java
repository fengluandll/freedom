package mx.gob.tsjdf.cfdi.view;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.event.FileUploadEvent;

import mx.gob.tsjdf.cfdi.bo.RecibosBo;
import mx.gob.tsjdf.cfdi.dao.CfdiDAO;
import mx.gob.tsjdf.cfdi.dto.EmpleadoTokenDto;
import mx.gob.tsjdf.cfdi.dto.HonorariosDTO;
import mx.gob.tsjdf.cfdi.dto.HonorariosPercepDto;
import mx.gob.tsjdf.cfdi.dto.ReporteDto;
import mx.gob.tsjdf.cfdi.service.ArchivoService;
import mx.gob.tsjdf.cfdi.service.LeeExcelHonorarios;
import mx.gob.tsjdf.cfdi.service.RecibosConTokenService;
import mx.gob.tsjdf.cfdi.service.ReporteService;
import mx.gob.tsjdf.util.Funciones;

@ManagedBean
@ViewScoped
public class LaudosCfdi  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Date    fechaEmision;
	private String  descripcion;
	private Calendar calendar = Calendar.getInstance();
	private String dirEjecucion;
	private String token;
	private FileInputStream fisLaudoHead;
	private FileInputStream fisLaudoPercep;
	private HonorariosDTO honorariosDTO;
	private Map<String,HonorariosDTO> 		mapHono = new HashMap<>();
	private LeeExcelHonorarios leeExcelHonorarios;
	private StringBuffer stb	   		  = new StringBuffer();
	private StringBuilder stbPerce 		  = new StringBuilder();
	private StringBuilder stbDedu  		  = new StringBuilder();
	private StringBuilder stbTotPercep    = new StringBuilder();
	private StringBuilder stbTotDeduc    = new StringBuilder();
	private StringBuilder stbPerceAdenda  = new StringBuilder();
	private StringBuilder stbDeducAdenda  = new StringBuilder();
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	private ReporteDto reporteDto;
	private Date    fechaPaga;
	private CfdiDAO 			 cfdiDAO  = new CfdiDAO();
	private static org.apache.log4j.Logger logger = Logger.getLogger(LaudosCfdi.class);
	private List<String> listMsgErrores;
	ReporteService reporteService;
	
	public void generaLaudos(){
		listMsgErrores = new ArrayList<>();
		reporteService = new ReporteService();
		if(fisLaudoHead != null && fisLaudoPercep != null){
				ArchivoService bo = new ArchivoService();
				leeExcelHonorarios = new LeeExcelHonorarios();
				
					    ArchivoService archivoService = new ArchivoService();			
					
			        	dirEjecucion = archivoService.crearDirectorios(
																	   cfdiDAO.dameRutaCFDI(1), 
																	   RecibosBo.getDirInicial(calendar.getTime(), sdf.format(this.fechaPaga)),
																	   this.descripcion);
			      
				
				 
						mapHono = leeExcelHonorarios.dameHonorarios(fisLaudoPercep,fisLaudoHead,listMsgErrores);
						if(!listMsgErrores.isEmpty()){
							for(String msg : listMsgErrores){
								Funciones.mostrarMensaje(msg,"Exito" , "WARN");
								
							}	
							Funciones.updateComponent("frmGenera:msgApper");
							return;
						}
						String pathLog = RecibosBo.getDirLog(dirEjecucion);
					    String nombreLog = RecibosBo.getLogName(sdf.format(this.fechaPaga), calendar.getTime());
						String fechaSistema = RecibosBo.getFecha(calendar.getTime());
						Iterator<String> claves = mapHono.keySet().iterator();
						//for(String key: mapHono.keySet()){
						 while(claves.hasNext()){
							 stb	   		  = new StringBuffer();
							 stbPerce 		  = new StringBuilder();
							 stbDedu  		  = new StringBuilder();
							 stbTotPercep    = new StringBuilder();
							 stbTotDeduc    = new StringBuilder();
							 stbPerceAdenda  = new StringBuilder();
							 stbDeducAdenda  = new StringBuilder();
							 String key = claves.next();
							 honorariosDTO = mapHono.get(key);
				//        if(honorariosDTO.getNumemplead().equals("9132014")){
				         
				        
						stb.append("H00000|1|1|"+fechaSistema+"|\n");
						stb.append("OPE001|1|token|\n");
						stb.append("C10001|3.2|"+sdf2.format(this.fechaEmision)+"|PAGO EN UNA SOLA EXHIBICIÓN|\n");
						stb.append("C30003||"+honorariosDTO.getTotPercep()+"|"+honorariosDTO.getDescuento()+"|DEDUCCIONES NÓMINA|1.0|MXN|"+honorariosDTO.getTotal()+"|"+honorariosDTO.getMetodopago()+"||||||RECIBO DE NÓMINA|"+honorariosDTO.getClavedep()+"||\n");
						stb.append("E00001|41|\n");
						stb.append("R00001|"+honorariosDTO.getRfc()+"|"+honorariosDTO.getNombre().trim()+"|||\n");
						stb.append("D00001|||||||||MÉXICO||\n");
						stb.append("I00001|"+honorariosDTO.getCantidad()+".00|"+honorariosDTO.getUnidad().toUpperCase()+"||"+honorariosDTO.getDescripcion()+"|"+honorariosDTO.getValorunita()+"|"+honorariosDTO.getImporte()+"|0|\n");
						stb.append("COM010|1.1|"+honorariosDTO.getNumemplead()+"||"+honorariosDTO.getCurp()+"|"+honorariosDTO.getTiporegime()+"||"+honorariosDTO.getFechapago()+"|"+honorariosDTO.getFechainici()+"|"+honorariosDTO.getFechafinal()+"|"+honorariosDTO.getNumdiaspag()+".00|"+honorariosDTO.getDepartamen()+"||014|||"+honorariosDTO.getPuesto()+"|"+honorariosDTO.getTipoContrato()+"|"+honorariosDTO.getTipoJornada()+"|"+honorariosDTO.getPeriodicid()+"||||\n");
				        
				        int count  = 0;
				        int countD = 0;
				        BigDecimal exentas  = new BigDecimal(honorariosDTO.getTotPerexe());
				        BigDecimal pGravadasTot = new BigDecimal("0");
				        BigDecimal pExentasTot  = new BigDecimal("0");
				        for(HonorariosPercepDto per : honorariosDTO.getListPercepciones()){
				        	
				        	if(per.getcTipo().equals("P")){
				        		count ++;
				        		stbPerce.append("COM010|PE0001|PER00"+count+"|"+per.getcTipopd()+"|"+per.getcCve()+"|"+per.getcNomConcepto()+"|"+per.getnImpGra()+"|"+per.getnImpexeImporte()+"|\n");
				        		if(!per.getcCve().equals("5100176")){
				        			stbPerceAdenda.append("A 0001|PE0001|PER001|LIQUIDACIÓN POR INDEMNIZACIÓN Y POR SUELDOS Y SALARIOS CAÍDOS|"+per.getnImpGra()+"|"+per.getcCve()+"|\n");
				        		}
				        		
				        		BigDecimal pGravadas  = new BigDecimal(per.getnImpGra());
				        		pGravadasTot = pGravadasTot.add(pGravadas);
				        		BigDecimal pExentas   = new BigDecimal(per.getnImpexeImporte());
				        		pExentasTot = pExentasTot.add(pExentas);
				        	}else if(per.getcTipo().equals("D")){
				        		countD ++;
				        		if(countD == 1){
				        			//stbDedu.append("COM010|DEDU01|0.00|"+per.getnImpexeImporte()+"|\n");
				        		}        		
				        			
				        			BigDecimal exentas2 = new BigDecimal(per.getnImpexeImporte());
				        			exentas = exentas.add(exentas2);
				        		    stbDedu.append("COM010|DE0001|DED00"+countD+"|"+per.getcTipopd()+"|"+per.getcCve()+"|"+per.getcNomConcepto()+"|"+per.getnImpGra()+"|"+per.getnImpexeImporte()+"|\n");
				        		    String concepto = null;
				        		    if (per.getcCve().equals("5200120")) {
				        		    	concepto = "CUOTAS ISSSTE LAUDO DESCUENTO";
									}else if(per.getcCve().equals("9345")){
										concepto = "Prima Vacacional";
									}else{
										concepto = per.getcNomConcepto();
									}
				        		    stbDeducAdenda.append("A 0001|DE0001|DED00"+countD+"|"+concepto+"|"+per.getnImpexeImporte()+"|"+per.getcCve()+"|\n");
				        	}
				        }
				        	/*BigDecimal tot = new BigDecimal(0);
				           if(pExentasTot == new BigDecimal(0.00)){
				        	   tot = new BigDecimal(00.00);
				           }else{
				        	   tot = pExentasTot;
				           }*/
				          stbTotPercep.append("COM010|PERC01|"+pGravadasTot+"|"+pExentasTot+"|\n");
				          stbTotDeduc.append("COM010|DEDU01|0.00|"+exentas+"|\n");
				          stb.append(stbTotPercep);
				          stb.append(stbPerce);
				          stb.append(stbTotDeduc);
				          stb.append(stbDedu);
				          stb.append("T10001|"+honorariosDTO.getImporteisr()+"|\n");
				          stb.append("T20001|ISR|"+honorariosDTO.getImporteisr()+"|\n");
				          stb.append("A 0001|1.0|\n");
				          stb.append("A 0001|FBA001|||||||||||\n");
				          stb.append("A 0001|NODOC1||\n");
				          stb.append("A 0001|NOM001|1|"+honorariosDTO.getZonaPagadora()+"|"+honorariosDTO.getClavedep()+"|"+honorariosDTO.getCodigo()+"|"+honorariosDTO.getNivel()+"|"+honorariosDTO.getUniverso()+"|"+honorariosDTO.getPlaza()+"|"+honorariosDTO.getSeccionSindical()+"|"+honorariosDTO.getLiquidacionPago().trim()+"|\n");
				          stb.append("A 0001|PERC01|"+pGravadasTot+"|"+pExentasTot+"|\n");
				          stb.append(stbPerceAdenda);
				          stb.append("A 0001|DEDU01|0.00|"+exentas+"|\n");
				          stb.append(stbDeducAdenda);
				          stb.append("A 0001|NOTGEN||\n");
				          stb.append("OPEEND|");
				//        }
				          	logger.info("Generando archivos TXT sin token");
					  		int numRecibos = 1;
					  		bo.creaArchivo(RecibosBo.getDirTxtSinToken(dirEjecucion)+honorariosDTO.getNumemplead()+"-"+numRecibos++ + ".txt", stb);
					  		
				        }
						 logger.info("VERIFICANDO SI CONTIENE NULL LOS ARCHIVOS...");
						 	boolean txtsValidos = bo.verificaNullTxts(RecibosBo.getDirTxtSinToken(dirEjecucion));
						 //boolean txtsValidos = true;
						 	reporteDto = new ReporteDto();
					  		if(txtsValidos){
					  			RecibosConTokenService rts = new RecibosConTokenService();
					  			logger.info("CREANDO TOKENS...");
								ArrayList<EmpleadoTokenDto> listaETA = rts.crearTxtConToken(
																							  fechaSistema, 
																							  RecibosBo.getDirTxtSinToken(dirEjecucion), 
																							  RecibosBo.getDirTxts(dirEjecucion),
																							  token);              
								
								
								reporteDto.setFechaFinalGenTXT(sdf.format(Long.valueOf(System.currentTimeMillis())));
								
								
								
								int generadosConToken = bo.verificarCreadosConToken(
														  RecibosBo.getDirTxtSinToken(dirEjecucion), 
														  RecibosBo.getDirTxts(dirEjecucion));
								
								logger.info("SE GENERARON "+ generadosConToken +" ARCHIVOS CON TOKEN");
								
						 if (generadosConToken > -1) {
							 logger.info("Creción de relación empleado-token-archivo en pdf: ");
			                  

			                  String relacionETA = reporteService.creaReportePdfRelacionETA(
			                    RecibosBo.getDirLog(dirEjecucion) + RecibosBo.getNombreReportePdfRelacionETA(calendar.getTime()), 
			                    listaETA, 
			                    sdf.format(this.fechaPaga));
			                  

			                  logger.info("pdf creado : " + relacionETA);
			                  
			                  logger.info("Creando relación de empleado-token-archivo en excel");
			                  

			                  String relacionExcelETA = reporteService.crearExcelRelacionETA(
			                    RecibosBo.getDirLog(dirEjecucion) + RecibosBo.getNombreReporteExcelRelacionETA(calendar.getTime()), 
			                    listaETA, 
			                    sdf.format(this.fechaPaga));
			                  

			                  logger.info("archivo excel creado: " + relacionExcelETA);		
			                  reporteDto.setRelacionEmpleadoTokenPdf(relacionETA);
			                  reporteDto.setListaSolicitudes(rts.getListaSolicitudes());
			                  

			                  reporteDto.setNumRecibosGenerados(generadosConToken);
				                bo.eliminarDirectorio(RecibosBo.getDirTxtSinToken(dirEjecucion));
				              }
			  			}
					  		logger.info("Fin creacion de TXT CON token");
							logger.info("------FIN DE PROCESO------");
							Funciones.mostrarMensaje("FIN DEL PROCESO REVISA TU CARPETA COMPARTIDA PARA MAS DETALLE","Exito" , "INFO");
							Funciones.updateComponent("frmGenera:msgApper");
			}else{
				Funciones.mostrarMensaje("Es obligatorio subir los archivos de excel para su procesamiento","Error" , "ERROR");
			}
	}
	
	
	public void uploadLaudo(FileUploadEvent event) {
		
		List<XSSFCell> Lista_celda_temporal = new ArrayList<>();
		
        if(event.getFile() != null) {
                try {
                	fisLaudoHead =(FileInputStream) event.getFile().getInputstream();
					 XSSFWorkbook Libro_trabajo = new XSSFWorkbook(fisLaudoHead);
					 XSSFSheet Hoja_hssf = Libro_trabajo.getSheetAt(0);
					 
					   /**
					 
					    * Iterar las filas y las celdas de la hoja de cálculo para obtener
					 
					    * toda la data.
					 
					    */
					 
					   Iterator Iterador_de_Fila = Hoja_hssf.rowIterator();
					 
					//   while (Iterador_de_Fila.next()) {
						   
						    XSSFRow Fila_hssf = (XSSFRow) Iterador_de_Fila.next();
						 
						    Iterator iterador = Fila_hssf.cellIterator();
						    
						    while (iterador.hasNext()) {
						 
						     XSSFCell Celda_hssf = (XSSFCell) iterador.next();
						 
						     Lista_celda_temporal.add(Celda_hssf);
						 
						    }
						    
						    //Lista_Datos_Celda.add(Lista_celda_temporal);
					 
					  // }
						    fisLaudoHead =(FileInputStream) event.getFile().getInputstream();
					  } catch (Exception e) {
						  e.printStackTrace();
					  }
		               if(Lista_celda_temporal.size() != 63){
		            	   Funciones.mostrarMensaje("Error: No es el layout Correcto","Exito" , "ERROR");
		               }else{
		            	   
		            	   Funciones.mostrarMensaje("El archivo "+event.getFile().getFileName() +" fue cargado exitosamente","Exito" , "INFO");
					      	 Funciones.updateComponent("msgFile");
		               }
					 
					 
				
        }
       
	}
	
	public void uploadPercep(FileUploadEvent event) {
		
		List<XSSFCell> Lista_celda_temporal = new ArrayList<>();
        if(event.getFile() != null) {
                try {
                	
                	 try {
                		 fisLaudoPercep =(FileInputStream) event.getFile().getInputstream();
     					 XSSFWorkbook Libro_trabajo = new XSSFWorkbook(fisLaudoPercep);
     					 XSSFSheet Hoja_hssf = Libro_trabajo.getSheetAt(0);
     					 
     					   /**
     					 
     					    * Iterar las filas y las celdas de la hoja de cálculo para obtener
     					 
     					    * toda la data.
     					 
     					    */
     					 
     					   Iterator Iterador_de_Fila = Hoja_hssf.rowIterator();
     					 //  while (Iterador_de_Fila.hasNext()) {
     						   
     						    XSSFRow Fila_hssf = (XSSFRow) Iterador_de_Fila.next();
     						 
     						    Iterator iterador = Fila_hssf.cellIterator();
     						    
     						    while (iterador.hasNext()) {
     						 
     						     XSSFCell Celda_hssf = (XSSFCell) iterador.next();
     						 
     						     Lista_celda_temporal.add(Celda_hssf);
     						 
     						    }
     						    
     						    //Lista_Datos_Celda.add(Lista_celda_temporal);
     					 
     					   //}
     						   fisLaudoPercep =(FileInputStream) event.getFile().getInputstream();
     					  } catch (Exception e) {
     						  e.printStackTrace();
     					  }
                	
                	 if(Lista_celda_temporal.size() != 8){
		            	   Funciones.mostrarMensaje("Error: No es el layout Correcto","Exito" , "ERROR");
		               }else{
		            	   fisLaudoPercep =(FileInputStream) event.getFile().getInputstream();
							Funciones.mostrarMensaje("El archivo "+event.getFile().getFileName() +" fue cargado exitosamente","Exito" , "INFO");
					      	Funciones.updateComponent("msgFile2");
		               }
                	 
					
				} catch (IOException e) {
					e.printStackTrace();
				}
        }
        return ;
	}

	public Date getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Calendar getCalendar() {
		return calendar;
	}

	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getFechaPaga() {
		return fechaPaga;
	}

	public void setFechaPaga(Date fechaPaga) {
		this.fechaPaga = fechaPaga;
	}
	
	

}

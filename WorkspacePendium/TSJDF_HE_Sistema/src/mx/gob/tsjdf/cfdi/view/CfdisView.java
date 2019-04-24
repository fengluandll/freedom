package mx.gob.tsjdf.cfdi.view;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import mx.gob.tsjdf.cfdi.bo.RecibosBo;
import mx.gob.tsjdf.cfdi.dao.CfdiDAO;
import mx.gob.tsjdf.cfdi.dao.Queries;
import mx.gob.tsjdf.cfdi.dto.CampoRubroDto;
import mx.gob.tsjdf.cfdi.dto.DescripcionPagoDto;
import mx.gob.tsjdf.cfdi.dto.EmpleadoTokenDto;
import mx.gob.tsjdf.cfdi.dto.ReporteDto;
import mx.gob.tsjdf.cfdi.service.ArchivoService;
import mx.gob.tsjdf.cfdi.service.RecibosConTokenService;
import mx.gob.tsjdf.cfdi.service.RecibosService;
import mx.gob.tsjdf.cfdi.service.ReporteService;
import mx.gob.tsjdf.util.Funciones;

@ManagedBean
@ViewScoped
public class CfdisView implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//private static Logger logger;
	private Date    fechaPaga;
	private Date    fechaEmision;
	private String  descripcion;
	private String  periocidadPago;
	private boolean valida;
	private String  token;
	private String  txtArea;
	private StringBuilder logArea;
	private String   folio = "1";
	Calendar calendar = Calendar.getInstance();
	ArchivoService archivoService;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	ReporteDto reporteDto = new ReporteDto();
	boolean txtsValidos = true;
	ReporteService reporteService;
	String nombreLog;
    String pathLog;
    CfdiDAO cfdiDAO;
    private UploadedFile file;
    ArrayList<String> listaEmpleadosArchivo;
    StringBuilder stb;
    private String rutaToken;
    String fechaSistema = RecibosBo.getFecha(calendar.getTime());
   
    private static org.apache.log4j.Logger logger = Logger.getLogger(CfdisView.class);
    
    public CfdisView(){
    	listaEmpleadosArchivo = new ArrayList<>();
    	stb = new StringBuilder();
    	archivoService = new ArchivoService();
    	reporteService = new ReporteService();
    	cfdiDAO = new CfdiDAO();
    }
    
	public void generaNomina(String tipoNomina){
		
		
		boolean existe = cfdiDAO.existeEmpleadosNomina(sdf.format(this.fechaPaga),tipoNomina);
		if(existe){
			
			
			String dirEjecucion = archivoService.crearDirectorios(
					  cfdiDAO.dameRutaCFDI(1), 
		              RecibosBo.getDirInicial(calendar.getTime(), sdf.format(this.fechaPaga)),
		              this.descripcion);
			//archivoService = new ArchivoService(RecibosBo.getLogName(sdf.format(this.fechaPaga), calendar.getTime()),RecibosBo.getDirLog(dirEjecucion));
			
			//this.txtArea = Funciones.variableLog.toString();
			//Funciones.updateComponent("frmLog:txtAreaLog");
			if (dirEjecucion != null)
	        {
			
				pathLog = RecibosBo.getDirLog(dirEjecucion);
		        nombreLog = RecibosBo.getLogName(sdf.format(this.fechaPaga), calendar.getTime());
		        //logger = LoggerSingleton.getInstance(nombreLog, pathLog);
		        
		        logger.info(sdf.format(this.fechaPaga));
				logger.info(sdf2.format(this.fechaEmision));
				logger.info(this.descripcion);
				logger.info(this.periocidadPago);
				logger.info(Boolean.toString(this.valida));
				logger.info(this.token);
				logger.info(txtArea);
				logger.info("dirEjecucion "+dirEjecucion);
				logger.info("Log*** "+ Funciones.variableLog.toString());
				logger.info("INICIO DEL PROCESO DE GENERACION DE TXTS");		
				//this.txtArea = this.txtArea + "\nINICIO DEL PROCESO DE GENERACION DE TXTS\n";
				 int generadosSinToken = 0;
		         int generadosConToken = 0;
		         
		   
		    	  
		         RecibosService recibosService = new RecibosService();
		         this.txtArea = Funciones.variableLog.toString();
		        // Funciones.updateComponent("frmLog:txtAreaLog");
		         HashMap<String, CampoRubroDto> hmapCampos = recibosService.getHmapCampos(tipoNomina);
		         
		
		         String query = Queries.getQueryAcumulado(hmapCampos);
		         
		
		         DescripcionPagoDto descripcionPago = recibosService.getFechaEjecucion(sdf.format(this.fechaPaga),tipoNomina);
		         String fechaEjecucion = descripcionPago.getFechaEjecucion();
		         
		
		         reporteDto.setFechaEjecucion(fechaEjecucion);
		         reporteDto.setDescripcionNomina(descripcionPago.getDescripcion());
		         
		         logger.info("Generacion de archivos TXT sin token");
		         //this.txtArea = this.txtArea + "Generacion de archivos TXT sin token\n";
		         
		         reporteDto.setFechaInicialGenTXT(sdf.format(Long.valueOf(System.currentTimeMillis())));
		         
		
		         logger.info("Buscando empleados de la nomina " + sdf.format(this.fechaPaga));
		         //this.txtArea = this.txtArea + "Buscando empleados de la nomina ";
		         //Funciones.updateComponent("frmLog:txtAreaLog");
		         ArrayList<String> listaEmpleados = recibosService.getListaTodosEmpleados(sdf.format(this.fechaPaga),tipoNomina);
		   	
		         reporteDto.setNumEmpleadosProcesados(listaEmpleados.size());		         
		
		         generadosSinToken = recibosService.generacionMasivaTodos(
		           listaEmpleados, 
		           hmapCampos, 
		           query, 
		           tipoNomina.equals("tribunal")?"1":"2", 
		           sdf.format(this.fechaPaga), 
		           this.folio, 
		           descripcionPago.getDescripcion(), 
		           sdf2.format(this.fechaEmision), 
		           fechaSistema, 
		           RecibosBo.getDirTxtSinToken(dirEjecucion), 
		           this.periocidadPago, 
		           fechaEjecucion, null,listaEmpleadosArchivo,tipoNomina);//Se pone null en metodo de pago para que lo tome de la base de datos
		         
		
		         
		         logger.info("Validando que los TXTs generados no contengan null");
		        // this.txtArea = this.txtArea + "Validando que los TXTs generados no contengan null\n";
		         //Funciones.updateComponent("frmLog:txtAreaLog");
		         archivoService = new ArchivoService();
		         txtsValidos = archivoService.verificaNullTxts(RecibosBo.getDirTxtSinToken(dirEjecucion));
	        
		       if (((generadosSinToken > 0) && (txtsValidos))){
		         logger.info("Generacion de archivos TXT con token");
		         //this.txtArea = this.txtArea + "Generacion de archivos TXT con token\n";
		        // Funciones.updateComponent("frmLog:txtAreaLog");
		         if ((generadosSinToken > 0) || rutaToken != null){
		           RecibosConTokenService rts = new RecibosConTokenService();
		           
		           ArrayList<EmpleadoTokenDto> listaETA = rts.crearTxtConToken(
		             fechaSistema, 
		             RecibosBo.getDirTxtSinToken(dirEjecucion), 
		             RecibosBo.getDirTxts(dirEjecucion),
		             this.token);
		           
		
		
		           reporteDto.setFechaFinalGenTXT(sdf.format(Long.valueOf(System.currentTimeMillis())));
		           
		
		
		           generadosConToken = archivoService.verificarCreadosConToken(
		             RecibosBo.getDirTxtSinToken(dirEjecucion), 
		             RecibosBo.getDirTxts(dirEjecucion));
		           /*******ARGUMELLLLL********/ 
		           
		           if (generadosConToken > 0)
		           {
		             logger.info("Crecion de relacion empleado-token-archivo en pdf: ");
		             
		
		             String relacionETA = reporteService.creaReportePdfRelacionETA(
		               RecibosBo.getDirLog(dirEjecucion) + RecibosBo.getNombreReportePdfRelacionETA(calendar.getTime()), 
		               listaETA, 
		               sdf.format(this.fechaPaga));
		             
		
		             logger.info("pdf creado : " + relacionETA);
		             
		             logger.info("Creando relacion de empleado-token-archivo en excel");
		             
		
		             String relacionExcelETA = reporteService.crearExcelRelacionETA(
		               RecibosBo.getDirLog(dirEjecucion) + RecibosBo.getNombreReporteExcelRelacionETA(calendar.getTime()), 
		               listaETA, 
		               sdf.format(this.fechaPaga));
		             
		
		             logger.info("archivo excel creado: " + relacionExcelETA);
//		             
//		
//		             reporteDto.setRelacionEmpleadoTokenPdf(relacionETA);
//		             
//		
//		             reporteDto.setListaSolicitudes(rts.getListaSolicitudes());
//		             
//		
//		             reporteDto.setNumRecibosGenerados(generadosConToken);
		           }
		         }
		         if (generadosConToken > -1) {
		             archivoService.eliminarDirectorio(RecibosBo.getDirTxtSinToken(dirEjecucion));
		  	         Funciones.mostrarMensaje("FIN DEL PROCESO REVISA TU CARPETA COMPARTIDA PARA MAS DETALLE","Exito" , "INFO");
		           }
		       }
		       
	        
	        }else{
	            System.out.println("No se ha podido crear la estructura de directorios para crear archivos");
	            Funciones.mostrarMensaje("La descripcion no puede contener ninguno de los siguientes caracteres / : * ? < > |","Error" , "WARN");
	            logger.info("No se ha podido crear la estructura de directorios para crear archivos");
	          }
		}else{
			Funciones.mostrarMensaje("La fecha de paga capturada no tiene empleados a procesar","Error" , "WARN");
		}
		
	}
	
	public void generaTokens(){
		     
	         logger.info("Generacion de archivos TXT con token");
	         if(this.rutaToken.contains("\\") || this.rutaToken.contains("/")){
	        	 Funciones.mostrarMensaje("El nombre de la carpeta no debe contener el caracter / o \\","WARN" , "WARN");
	        	 return;
	         }
	         this.rutaToken = cfdiDAO.dameRutaCFDI(1) + "\\" + this.rutaToken;
	        
	           RecibosConTokenService rts = new RecibosConTokenService();
	           
	           ArrayList<EmpleadoTokenDto> listaETA = rts.crearTxtConToken(
	             fechaSistema, 
	             RecibosBo.getDirTxtSinToken(this.rutaToken), 
	             RecibosBo.getDirTxts(this.rutaToken),
	             this.token);
	           
	
	
	           reporteDto.setFechaFinalGenTXT(sdf.format(Long.valueOf(System.currentTimeMillis())));
	           
	
	
	           int generadosConToken = archivoService.verificarCreadosConToken(
	             RecibosBo.getDirTxtSinToken(this.rutaToken), 
	             RecibosBo.getDirTxts(this.rutaToken));
	           /*******ARGUMELLLLL********/ 
	           
	           if (generadosConToken > 0)
	           {
	             logger.info("Crecion de relacion empleado-token-archivo en pdf: ");
	             
	
	             String relacionETA = reporteService.creaReportePdfRelacionETA(
	               RecibosBo.getDirLog(this.rutaToken) + RecibosBo.getNombreReportePdfRelacionETA(calendar.getTime()), 
	               listaETA, 
	               sdf.format(this.fechaPaga));
	             
	
	             logger.info("pdf creado : " + relacionETA);	             
	             logger.info("Creando relacion de empleado-token-archivo en excel");
	             
	
	             String relacionExcelETA = reporteService.crearExcelRelacionETA(
	               RecibosBo.getDirLog(this.rutaToken) + RecibosBo.getNombreReporteExcelRelacionETA(calendar.getTime()), 
	               listaETA, 
	               sdf.format(this.fechaPaga));
	
	             logger.info("archivo excel creado: " + relacionExcelETA);
	           }
	         
	         if (generadosConToken > -1) {
	             archivoService.eliminarDirectorio(RecibosBo.getDirTxtSinToken(this.rutaToken));
	  	         Funciones.mostrarMensaje("FIN DEL PROCESO REVISA TU CARPETA COMPARTIDA PARA MAS DETALLE","Exito" , "INFO");
	           }
	       
	}
	
	/**
	 * Metodo que genera txt solo de algunos empleados procesdos
	 * de un archivo de txt
	 */
	public void generaAlgunosCuantos(String tipoNomina){
		
		cfdiDAO = new CfdiDAO();
		//boolean existe = cfdiDAO.existeEmpleadosNomina(sdf.format(this.fechaPaga));
		String empleados = StringUtils.removeEnd(stb.toString(), ",");
		
		if(listaEmpleadosArchivo.size() > 0){
			int cantidad = cfdiDAO.existeAlgunosEmpleadosNomina(sdf.format(this.fechaPaga), empleados,tipoNomina);
			if(listaEmpleadosArchivo.size() == cantidad){
				
				reporteService = new ReporteService();
				archivoService = new ArchivoService();
				String dirEjecucion = archivoService.crearDirectorios(
						  cfdiDAO.dameRutaCFDI(1), 
			              RecibosBo.getDirInicial(calendar.getTime(), sdf.format(this.fechaPaga)),
			              this.descripcion);
				//archivoService = new ArchivoService(RecibosBo.getLogName(sdf.format(this.fechaPaga), calendar.getTime()),RecibosBo.getDirLog(dirEjecucion));
				
				//this.txtArea = Funciones.variableLog.toString();
				//Funciones.updateComponent("frmLog:txtAreaLog");
				if (dirEjecucion != null)
		        {
				
					pathLog = RecibosBo.getDirLog(dirEjecucion);
			        nombreLog = RecibosBo.getLogName(sdf.format(this.fechaPaga), calendar.getTime());
			        //logger = LoggerSingleton.getInstance(nombreLog, pathLog);
			        
			        logger.info(sdf.format(this.fechaPaga));
					logger.info(sdf2.format(this.fechaEmision));
					logger.info(this.descripcion);
					logger.info(this.periocidadPago);
					logger.info(Boolean.toString(this.valida));
					logger.info(this.token);
					logger.info(txtArea);
					logger.info("dirEjecucion "+dirEjecucion);
					logger.info("Log*** "+ Funciones.variableLog.toString());
					logger.info("INICIO DEL PROCESO DE GENERACION DE TXTS");		
					 int generadosSinToken = 0;
			         int generadosConToken = 0;
			         String fechaSistema = RecibosBo.getFecha(calendar.getTime());
			         RecibosService recibosService = new RecibosService();
			         //this.txtArea = Funciones.variableLog.toString();
			         //Funciones.updateComponent("frmLog:txtAreaLog");
			         HashMap<String, CampoRubroDto> hmapCampos = recibosService.getHmapCampos(tipoNomina);
			         
			
			         String query = Queries.getQueryAcumulado(hmapCampos);
			         
			
			         DescripcionPagoDto descripcionPago = recibosService.getFechaEjecucion(sdf.format(this.fechaPaga),tipoNomina);
			         String fechaEjecucion = descripcionPago.getFechaEjecucion();
			         
			
			         reporteDto.setFechaEjecucion(fechaEjecucion);
			         reporteDto.setDescripcionNomina(descripcionPago.getDescripcion());
			         
			         logger.info("Generacion de archivos TXT sin token");
			         
			         reporteDto.setFechaInicialGenTXT(sdf.format(Long.valueOf(System.currentTimeMillis())));
			         
			
			         logger.info("Buscando empleados de la nomina " + sdf.format(this.fechaPaga));
			         //ArrayList<String> listaEmpleados = recibosService.getListaTodosEmpleados(sdf.format(this.fechaPaga));
			         
			
			
			         reporteDto.setNumEmpleadosProcesados(listaEmpleadosArchivo.size());
			         
			
			         generadosSinToken = recibosService.generacionMasivaTodos(
			        		 listaEmpleadosArchivo, 
			           hmapCampos, 
			           query, 
			           tipoNomina.equals("tribunal")?"1":"2",  
			           sdf.format(this.fechaPaga), 
			           this.folio, 
			           descripcionPago.getDescripcion(), 
			           sdf2.format(this.fechaEmision), 
			           fechaSistema, 
			           RecibosBo.getDirTxtSinToken(dirEjecucion), 
			           this.periocidadPago, 
			           fechaEjecucion, null,new ArrayList<String>(),
			           tipoNomina);//Se pone null en metodo de pago para que lo tome de la base de datos
			         
			
			         
			         logger.info("Validando que los TXTs generados no contengan null");
			         this.txtArea = this.txtArea + "Validando que los TXTs generados no contengan null\n";
			        
			         archivoService = new ArchivoService();
			         txtsValidos = archivoService.verificaNullTxts(RecibosBo.getDirTxtSinToken(dirEjecucion));
			       
			       if (((generadosSinToken > 0) && (txtsValidos)))
			       {
			         logger.info("Generacion de archivos TXT con token");
			         this.txtArea = this.txtArea + "Generacion de archivos TXT con token\n";
			      
			         if ((generadosSinToken > 0)){
			           RecibosConTokenService rts = new RecibosConTokenService();
			           
			           ArrayList<EmpleadoTokenDto> listaETA = rts.crearTxtConToken(
			             fechaSistema, 
			             RecibosBo.getDirTxtSinToken(dirEjecucion), 
			             RecibosBo.getDirTxts(dirEjecucion),
			             this.token);
			           
			
			
			           reporteDto.setFechaFinalGenTXT(sdf.format(Long.valueOf(System.currentTimeMillis())));
			           
			
			
			           generadosConToken = archivoService.verificarCreadosConToken(
			             RecibosBo.getDirTxtSinToken(dirEjecucion), 
			             RecibosBo.getDirTxts(dirEjecucion));
			           /*******ARGUMELLLLL********/ 
			           
			           if (generadosConToken > 0)
			           {
			             logger.info("Crecion de relacion empleado-token-archivo en pdf: ");
			             
			
			             String relacionETA = reporteService.creaReportePdfRelacionETA(
			               RecibosBo.getDirLog(dirEjecucion) + RecibosBo.getNombreReportePdfRelacionETA(calendar.getTime()), 
			               listaETA, 
			               sdf.format(this.fechaPaga));
			             
			
			             logger.info("pdf creado : " + relacionETA);
			             
			             logger.info("Creando relacion de empleado-token-archivo en excel");
			             
			
			             String relacionExcelETA = reporteService.crearExcelRelacionETA(
			               RecibosBo.getDirLog(dirEjecucion) + RecibosBo.getNombreReporteExcelRelacionETA(calendar.getTime()), 
			               listaETA, 
			               sdf.format(this.fechaPaga));
			             
			
			             logger.info("archivo excel creado: " + relacionExcelETA);
			             
			
			             reporteDto.setRelacionEmpleadoTokenPdf(relacionETA);
			             
			
			             reporteDto.setListaSolicitudes(rts.getListaSolicitudes());
			             
			
			             reporteDto.setNumRecibosGenerados(generadosConToken);
			           }
			         }
			         if (generadosConToken > -1) {
			             archivoService.eliminarDirectorio(RecibosBo.getDirTxtSinToken(dirEjecucion));
			             this.txtArea = this.txtArea + "***FIN DEL PROCESO***\n";
			  	       Funciones.mostrarMensaje("FIN DEL PROCESO REVISA TU CARPETA COMPARTIDA PARA MAS DETALLE","Exito" , "INFO");
			  	     //  logger = LoggerSingleton.getInstance();
			           }
			       }
		       
		        
	        }else{
	            System.out.println("No se ha podido crear la estructura de directorios para crear archivos");
	            Funciones.mostrarMensaje("La descripcion no puede contener ninguno de los siguientes caracteres / : * ? < > |","Error" , "WARN");
	            logger.info("No se ha podido crear la estructura de directorios para crear archivos");
	            this.txtArea = this.txtArea + "No se ha podido crear la estructura de directorios para crear archivos\n";
//		        Funciones.updateComponent("frmLog:txtAreaLog");
		      //  logger = LoggerSingleton.getInstance();
	          }
			}else{
	        	Funciones.mostrarMensaje("Los empleados no tienen recibos en la fecha de pago especificada","Error" , "ERROR");
	        }
		}else{
			Funciones.mostrarMensaje("El archivo no contiene empleados a procesar","Error" , "WARN");
		}
		
	}
	
	public void upload(FileUploadEvent event) {
		listaEmpleadosArchivo = new ArrayList<>();
		
        if(event.getFile() != null) {
        	try{
               
                FileInputStream fis =(FileInputStream) event.getFile().getInputstream();
                BufferedReader in = new BufferedReader(new InputStreamReader(fis, "UTF8"));
                String str;
	      	      while ((str = in.readLine()) != null)
	      	      {
	      	        //String str;
	      	    	  stb.append(str);
	      	    	  stb.append(",");
	      	    	listaEmpleadosArchivo.add(str);
	      	      }
	      	      in.close();
	      	    Funciones.mostrarMensaje("El archivo "+event.getFile().getFileName() +" fue cargado exitosamente","Exito" , "INFO");
	      	    Funciones.updateComponent("msgFile");
            }catch(IOException e){
            	e.printStackTrace();
            }
        }else{
        	Funciones.mostrarMensaje("Es requerido subir el archivo txt de empleados","Error" , "ERROR");
        }
    }
	
	
	public String getTxtArea() {
		return txtArea;
	}




	public void setTxtArea(String txtArea) {
		this.txtArea = txtArea;
	}




	public StringBuilder getLogArea() {
		return logArea;
	}



	public void setLogArea(StringBuilder logArea) {
		this.logArea = logArea;
	}



	public Date getFechaPaga() {
		return fechaPaga;
	}
	public void setFechaPaga(Date fechaPaga) {
		this.fechaPaga = fechaPaga;
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
	public String getPeriocidadPago() {
		return periocidadPago;
	}
	public void setPeriocidadPago(String periocidadPago) {
		this.periocidadPago = periocidadPago;
	}
	public boolean isValida() {
		return valida;
	}
	public void setValida(boolean valida) {
		this.valida = valida;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}




	public String getFolio() {
		return folio;
	}




	public void setFolio(String folio) {
		this.folio = folio;
	}
	
	
	 public UploadedFile getFile() {
	        return file;
	    }
 
    public void setFile(UploadedFile file) {
        this.file = file;
    }

	public String getRutaToken() {
		return rutaToken;
	}

	public void setRutaToken(String rutaToken) {
		this.rutaToken = rutaToken;
	}
    
    
}

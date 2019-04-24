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

import mx.gob.tsjdf.cfdi.bo.RecibosBo;
import mx.gob.tsjdf.cfdi.bo.ValidacionBo;
import mx.gob.tsjdf.cfdi.dao.CfdiDAO;
import mx.gob.tsjdf.cfdi.dao.Queries;
import mx.gob.tsjdf.cfdi.dto.CampoRubroDto;
import mx.gob.tsjdf.cfdi.dto.DescripcionPagoDto;
import mx.gob.tsjdf.cfdi.dto.ErroresDatosEmpleadosDto;
import mx.gob.tsjdf.cfdi.service.RecibosService;
import mx.gob.tsjdf.cfdi.service.ReporteService;
import mx.gob.tsjdf.cfdi.service.ValidacionService;
import mx.gob.tsjdf.util.Funciones;

@ManagedBean
@ViewScoped
public class ValidaTodosView implements Serializable{

	private static final long serialVersionUID = 1L;
	private CfdiDAO cfdiDAO;
	private Date    fechaPaga;
	private Date    fechaEmision;
	private String  descripcion;
	private String  periocidadPago;
	private String  folio = "1";
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	ReporteService reportes;
	Calendar calendar = Calendar.getInstance();
	private int erroresGrales;
	private int totalEmpleados;
	private int recibosValidos;
	private int recibosInvalidos;
	private String renderTotal = "false";
	ArrayList<String> listaEmpleados;
	StringBuilder stb;
	private static org.apache.log4j.Logger logger = Logger.getLogger(ValidaTodosView.class);
	
	
	public ValidaTodosView(){
		listaEmpleados = new ArrayList<>();
    	stb = new StringBuilder();
	}
	
	public void validaEmpleados(String tipoNomina){
		cfdiDAO = new CfdiDAO();
		if(this.descripcion.contains("/") || this.descripcion.contains(":") || 
		  this.descripcion.contains("*")  || this.descripcion.contains("?") ||
		  this.descripcion.contains("<")  || this.descripcion.contains(">") ||
		  this.descripcion.contains("|")){
			Funciones.mostrarMensaje("La descripcion no puede contener ninguno de los siguientes caracteres / : * ? < > |","Error" , "WARN");
		}else{
			
			boolean existe = cfdiDAO.existeEmpleadosNomina(sdf.format(this.fechaPaga),tipoNomina);
			if(existe){
				logger.info("INICIO DEL PROCESO DE VALIDACION PREVIA A LA GENERACION DE TXT");
	
				reportes = new ReporteService();
		        String fechaSistema = RecibosBo.getFecha(calendar.getTime());
		          
	
		          RecibosService recibosService = new RecibosService();
		          ValidacionService validacionService = new ValidacionService();
		          HashMap<String, CampoRubroDto> hmapCampos = recibosService.getHmapCampos(tipoNomina);
		          
	
		          String query = Queries.getQueryAcumulado(hmapCampos);
		          
	
		          DescripcionPagoDto descripcionPago = recibosService.getFechaEjecucion(sdf.format(this.fechaPaga),tipoNomina);
		          String fechaEjecucion = descripcionPago.getFechaEjecucion();
		          
	
		          logger.info("Buscando empleados de la nomina " + sdf.format(this.fechaPaga) + "...");
		          ArrayList<String> listaEmpleados = recibosService.getListaTodosEmpleados(sdf.format(this.fechaPaga),tipoNomina);
		          
	
	
	
		          ArrayList<ErroresDatosEmpleadosDto> errores = validacionService.validacionMasivaTodos(listaEmpleados, hmapCampos, 
		            query, 
		            tipoNomina.equals("tribunal")?"1":"2", 
		            sdf.format(this.fechaPaga), 
		            this.folio, 
		            descripcionPago.getDescripcion(), 
		            sdf2.format(this.fechaEmision),
		            fechaSistema, 
		            this.periocidadPago, 
		            fechaEjecucion, 
		            null,
		            tipoNomina);
		          
	
	
		          reportes.creaRelacionTxtNoValidos(cfdiDAO.dameRutaCFDI(2) + 
		            ValidacionBo.getNombreErroresValidacion(calendar.getTime(), sdf.format(this.fechaPaga),this.descripcion),
		            errores,
		            sdf.format(this.fechaPaga),
		            validacionService.getListaStatus(),
		            this.descripcion);
		          
		          Funciones.mostrarMensaje("FIN DEL PROCESO REVISE SU CARPETA COMPARTIDA PARA MAYOR INFORMACION","" , "INFO");
		          Funciones.mostrarMensaje(Funciones.msjValidacion,"" , "INFO");
		          this.erroresGrales  = Funciones.erroresGrales;
		          this.totalEmpleados = Funciones.totalEmpleados;
		          this.recibosValidos = Funciones.recibosValidos;
		          this.recibosInvalidos = Funciones.recibosInvalidos;
		          renderTotal = "true";
		          Funciones.updateComponent("frmBitacora");
		          Funciones.updateComponent("frmBitacora:gridTotal");
		          logger.info("Fin de la validacion");
			}else{
				Funciones.mostrarMensaje("La fecha de paga capturada no tiene empleados a procesar","Error" , "WARN");
			}
		}
	}
	
	
	public void validaAlgunosCuantos(String tipoNomina){
		cfdiDAO = new CfdiDAO();
		//boolean existe = cfdiDAO.existeEmpleadosNomina(sdf.format(this.fechaPaga));
		String empleados = StringUtils.removeEnd(stb.toString(), ",");
		if(this.descripcion.contains("/") || this.descripcion.contains(":") || 
				  this.descripcion.contains("*")  || this.descripcion.contains("?") ||
				  this.descripcion.contains("<")  || this.descripcion.contains(">") ||
				  this.descripcion.contains("|")){
					Funciones.mostrarMensaje("La descripcion no puede contener ninguno de los siguientes caracteres / : * ? < > |","Error" , "WARN");
		}else{
			if(listaEmpleados.size() > 0){
				int cantidad = cfdiDAO.existeAlgunosEmpleadosNomina(sdf.format(this.fechaPaga), empleados,tipoNomina);
				if(listaEmpleados.size() == cantidad){
					logger.info("INICIO DEL PROCESO DE VALIDACION PREVIA A LA VALIDACION DE EMPLEADOS");
		
					reportes = new ReporteService();
			        String fechaSistema = RecibosBo.getFecha(calendar.getTime());
			          
		
			          RecibosService recibosService = new RecibosService();
			          ValidacionService validacionService = new ValidacionService();
			          HashMap<String, CampoRubroDto> hmapCampos = recibosService.getHmapCampos(tipoNomina);
			          
		
			          String query = Queries.getQueryAcumulado(hmapCampos);
			          
		
			          DescripcionPagoDto descripcionPago = recibosService.getFechaEjecucion(sdf.format(this.fechaPaga),tipoNomina);
			          String fechaEjecucion = descripcionPago.getFechaEjecucion();
			          
		
			          logger.info("Buscando empleados de la nomina " + sdf.format(this.fechaPaga) + "...");
			          //ArrayList<String> listaEmpleados = recibosService.getListaTodosEmpleados(sdf.format(this.fechaPaga));
			          
		
		
		
			          ArrayList<ErroresDatosEmpleadosDto> errores = validacionService.validacionMasivaTodos(listaEmpleados, hmapCampos, 
			            query, 
			            tipoNomina.equals("tribunal")?"1":"2", 
			            sdf.format(this.fechaPaga), 
			            this.folio, 
			            descripcionPago.getDescripcion(), 
			            sdf2.format(this.fechaEmision),
			            fechaSistema, 
			            this.periocidadPago, 
			            fechaEjecucion, 
			            null,
			            tipoNomina);
			          
		
		
			          reportes.creaRelacionTxtNoValidos(cfdiDAO.dameRutaCFDI(2) + 
			            ValidacionBo.getNombreErroresValidacion(calendar.getTime(), sdf.format(this.fechaPaga),this.descripcion),
			            errores,
			            sdf.format(this.fechaPaga),
			            validacionService.getListaStatus(),
			            this.descripcion);
			          
			          Funciones.mostrarMensaje("FIN DEL PROCESO REVISE SU CARPETA COMPARTIDA PARA MAYOR INFORMACIÓN","" , "INFO");
			          Funciones.mostrarMensaje(Funciones.msjValidacion,"" , "INFO");
			          this.erroresGrales  = Funciones.erroresGrales;
			          this.totalEmpleados = Funciones.totalEmpleados;
			          this.recibosValidos = Funciones.recibosValidos;
			          this.recibosInvalidos = Funciones.recibosInvalidos;
			          Funciones.erroresGrales    = 0;
			          Funciones.totalEmpleados   = 0;
			          Funciones.recibosValidos   = 0;
			          Funciones.recibosInvalidos = 0;
			          renderTotal = "true";
			          Funciones.updateComponent("frmBitacora");
			          Funciones.updateComponent("frmBitacora:gridTotal");
			          logger.info("Fin de la validacion");
				}else{
		        	Funciones.mostrarMensaje("Todos o algunos empleados no tienen recibos en la fecha de pago especificada","Error" , "ERROR");
		        }
			}else{
				Funciones.mostrarMensaje("El archivo no contiene empleados a procesar","Error" , "WARN");
			}
		}
	}
	
	public void upload(FileUploadEvent event) {
		
		
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
	      	    	listaEmpleados.add(str);
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

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public int getErroresGrales() {
		return erroresGrales;
	}

	public void setErroresGrales(int erroresGrales) {
		this.erroresGrales = erroresGrales;
	}

	public int getTotalEmpleados() {
		return totalEmpleados;
	}

	public void setTotalEmpleados(int totalEmpleados) {
		this.totalEmpleados = totalEmpleados;
	}

	public int getRecibosValidos() {
		return recibosValidos;
	}

	public void setRecibosValidos(int recibosValidos) {
		this.recibosValidos = recibosValidos;
	}

	public int getRecibosInvalidos() {
		return recibosInvalidos;
	}

	public void setRecibosInvalidos(int recibosInvalidos) {
		this.recibosInvalidos = recibosInvalidos;
	}

	public String getRenderTotal() {
		return renderTotal;
	}

	public void setRenderTotal(String renderTotal) {
		this.renderTotal = renderTotal;
	}

	
}

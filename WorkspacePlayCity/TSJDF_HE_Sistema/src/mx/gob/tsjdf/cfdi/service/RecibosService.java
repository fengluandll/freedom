package mx.gob.tsjdf.cfdi.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;

import mx.gob.tsjdf.cfdi.bo.RecibosBo;
import mx.gob.tsjdf.cfdi.dao.PagoDao;
import mx.gob.tsjdf.cfdi.dao.PagoEmpleadoDao;
import mx.gob.tsjdf.cfdi.dto.CampoRubroDto;
import mx.gob.tsjdf.cfdi.dto.DescripcionPagoDto;
import mx.gob.tsjdf.cfdi.dto.PagoEmpleadoDto;
import mx.gob.tsjdf.cfdi.dto.PercepcionDeduccionDto;
import mx.gob.tsjdf.cfdi.dto.ReciboDto;
import mx.gob.tsjdf.cfdi.dto.RecibosEmpleadoDto;
import mx.gob.tsjdf.cfdi.recibos.logger.LoggerSingleton;
import mx.gob.tsjdf.cfdi.view.CfdisView;
import mx.gob.tsjdf.util.Funciones;



public class RecibosService {

	 private PagoDao pagoDao;
	 Funciones funciones;
	// private static Logger logger;
	 private static org.apache.log4j.Logger logger = Logger.getLogger(RecibosService.class);
	 
	  public RecibosService(){
		funciones = new Funciones();  
	    this.pagoDao = new PagoDao();
	   // logger = LoggerSingleton.getInstance();
	  }
	  
	  public RecibosEmpleadoDto getRecibos(HashMap<String, CampoRubroDto> hmapCampos,
			  								String query,
			  								String numeroEmpleado,
			  								String fechaPaga,
			  								String metodoPago,
			  								String tipoNomina)
	  {
	    RecibosEmpleadoDto recibosEmpleadoDto = new RecibosEmpleadoDto();
	    for (Integer periodo : this.pagoDao.consultaPeriodosEmpleado(numeroEmpleado, fechaPaga,tipoNomina)) {
	      recibosEmpleadoDto.getListaRecibos().add(generaRecibo(hmapCampos, query, numeroEmpleado, fechaPaga, periodo.intValue(), metodoPago,tipoNomina));
	    }
	    return recibosEmpleadoDto;
	  }
	  
	  public ReciboDto generaRecibo(HashMap<String, CampoRubroDto> hmapCampos, String query, String numeroEmpleado, String fechaPaga, int idPeriodo, String metodoPago,String tipoNomina)
	  {
	    ReciboDto reciboDto = new ReciboDto();
	    reciboDto.setNumeroEmpleado(numeroEmpleado);
	    reciboDto.setIdPeriodo(idPeriodo);
	    

	    this.pagoDao.llenaConceptosRecibo(reciboDto, query, fechaPaga, hmapCampos,tipoNomina);
	    

	    this.pagoDao.llenaDatosGenerales(reciboDto, reciboDto.getFechaInicioPeriodo(), fechaPaga, metodoPago,tipoNomina);
	    
	    Double perTotalGravado = Double.valueOf(0.0D);
	    Double perTotalExento = Double.valueOf(0.0D);
	    HashSet<String> hsPercepciones = new HashSet();
	    HashSet<String> hsDeducciones = new HashSet();
	    for (Map.Entry<String, PercepcionDeduccionDto> p : reciboDto.getHmapPercepciones().entrySet()) {
	      if ((((PercepcionDeduccionDto)p.getValue()).getImporte().doubleValue() == 0.0D) && (!((PercepcionDeduccionDto)p.getValue()).getDespliegaConCero()))
	      {
	        hsPercepciones.add((String)p.getKey());
	      }
	      else
	      {
	        if ((((PercepcionDeduccionDto)p.getValue()).getExento() == 1) || (((PercepcionDeduccionDto)p.getValue()).getExento() == 2)) {
	          ((PercepcionDeduccionDto)p.getValue()).setImporteExento(
	            ((PercepcionDeduccionDto)p.getValue()).getImporte());
	        }
	        if (((PercepcionDeduccionDto)p.getValue()).getExento() == 0) {
	          ((PercepcionDeduccionDto)p.getValue()).setImporteGravado(((PercepcionDeduccionDto)p.getValue()).getImporte());
	        }
	        if ((((PercepcionDeduccionDto)p.getValue()).getDespliega().equals("T")) || (((PercepcionDeduccionDto)p.getValue()).getDespliega().equals("C")))
	        {
	          perTotalGravado = Double.valueOf(perTotalGravado.doubleValue() + ((PercepcionDeduccionDto)p.getValue()).getImporteGravado().doubleValue());
	          perTotalExento = Double.valueOf(perTotalExento.doubleValue() + ((PercepcionDeduccionDto)p.getValue()).getImporteExento().doubleValue());
	        }
	      }
	    }
	    for (Map.Entry<String, PercepcionDeduccionDto> d : reciboDto.getHmapDeducciones().entrySet()) {
	      if ((((PercepcionDeduccionDto)d.getValue()).getImporte().doubleValue() == 0.0D) && (!((PercepcionDeduccionDto)d.getValue()).getDespliegaConCero()))
	      {
	        hsDeducciones.add((String)d.getKey());
	      }
	      else
	      {
	        if ((((PercepcionDeduccionDto)d.getValue()).getExento() == 1) || (((PercepcionDeduccionDto)d.getValue()).getExento() == 2)) {
	          ((PercepcionDeduccionDto)d.getValue()).setImporteExento(
	            ((PercepcionDeduccionDto)d.getValue()).getImporte());
	        }
	        if (((PercepcionDeduccionDto)d.getValue()).getExento() == 0) {
	          ((PercepcionDeduccionDto)d.getValue()).setImporteGravado(
	            ((PercepcionDeduccionDto)d.getValue()).getImporte());
	        }
	      }
	    }
	    for (Iterator<String> iterator = hsPercepciones.iterator(); iterator.hasNext();)
	    {
	      String key = (String)iterator.next();
	      reciboDto.getHmapPercepciones().remove(key);
	    }
	    for (Iterator<String> iterator = hsDeducciones.iterator(); iterator.hasNext();)
	    {
	      String key = (String)iterator.next();
	      reciboDto.getHmapDeducciones().remove(key);
	    }
	    reciboDto.setTotalGravadoPercepciones(RecibosBo.Redondear(perTotalGravado.doubleValue()));
	    reciboDto.setTotalExentoPercepciones(RecibosBo.Redondear(perTotalExento.doubleValue()));
	    



	    reciboDto.setTotalGravadoDeducciones(0.0D);
	    reciboDto.setTotalExentoDeducciones(RecibosBo.Redondear(reciboDto.getTotalDescuentos()));
	    if ((reciboDto.getHorasExtraDobles() > 0.0D) || (reciboDto.getHorasExtraTriples() > 0.0D)) {
	      reciboDto.setDiasHorasExtra(10);
	    }
	    return reciboDto;
	  }
	  
	  public ArrayList<PagoEmpleadoDto> getPagosEmpleado(String numeroEmpleado, int anio)
	  {
	    PagoEmpleadoDao dao = new PagoEmpleadoDao();
	    return dao.consultarPagosEmpleado(numeroEmpleado, 
	      Integer.toString(anio));
	  }
	  
	  public ArrayList<DescripcionPagoDto> getDescripcionPagos(String anio,String tipoNomina)
	  {
	    return this.pagoDao.consultarDescripcionesPago(anio,tipoNomina);
	  }
	  
	  public int generaTxt(HashMap<String, CampoRubroDto> hmapCampos,
			  				String query,
			  				String idRfc, 
			  				String numeroEmpleado, 
			  				String fechaPaga, 
			  				String pagoDescripcion, 
			  				String fechaEmision,
			  				String token,
			  				String fechaSistema, 
			  				String dirTxt, 
			  				String periodicidadPago, 
			  				String fechaEjecucion, 
			  				String metodoPago,
			  				String tipoNomina)
	  {
	    RecibosEmpleadoDto recibosEmpleadoDto = getRecibos(hmapCampos, query, numeroEmpleado, fechaPaga, metodoPago,tipoNomina);
	    if (recibosEmpleadoDto.getListaRecibos().size() > 0)
	    {
	      int numRecibos = 1;
	      for (ReciboDto reciboDto : recibosEmpleadoDto.getListaRecibos())
	      {
	        String pathAbsoluto = dirTxt + numeroEmpleado + "-" + numRecibos++ + ".txt";
	        
	        StringBuffer contenido = new StringBuffer();
	        ArchivoService bo = new ArchivoService();
	        
	        StringBuffer strPerComplementos = new StringBuffer("");
	        StringBuffer strPerAddenda = new StringBuffer("");
	        StringBuffer strDesComplementos = new StringBuffer("");
	        StringBuffer strDesAddenda = new StringBuffer("");
	        



	        int numPerC = 1;
	        int numPerA = 1;
	        for (Map.Entry<String, PercepcionDeduccionDto> p : reciboDto.getHmapPercepciones().entrySet())
	        {
	          if ((((PercepcionDeduccionDto)p.getValue()).getDespliega().equals("T")) || (((PercepcionDeduccionDto)p.getValue()).getDespliega().equals("C")))
	          {
	            String identificadorC = RecibosBo.intToStr(numPerC++);
	            strPerComplementos.append("COM010|PE0001|PER" + 
	              identificadorC + "|" + ((PercepcionDeduccionDto)p.getValue()).getClaveSat() + 
	              "|" + ((PercepcionDeduccionDto)p.getValue()).getClaveTsjdf() + "|" + 
	              ((PercepcionDeduccionDto)p.getValue()).getDescripcionSat() + "|" + 
	              ((PercepcionDeduccionDto)p.getValue()).getImporteGravadoFormat() + "|" + 
	              ((PercepcionDeduccionDto)p.getValue()).getImporteExentoFormat() + "|\n");
	          }
	          if ((((PercepcionDeduccionDto)p.getValue()).getDespliega().equals("T")) || (((PercepcionDeduccionDto)p.getValue()).getDespliega().equals("A")))
	          {
	            String identificadorA = RecibosBo.intToStr(numPerA++);
	            strPerAddenda.append("A 0001|PE0001|PER" + identificadorA + 
	              "|" + ((PercepcionDeduccionDto)p.getValue()).getDescripcionTsjdf() + "|" + 
	              ((PercepcionDeduccionDto)p.getValue()).getImporteFormat() + "|" + 
	              ((PercepcionDeduccionDto)p.getValue()).getClaveTsjdf() + "|\n");
	          }
	        }
	        int numDesC = 1;
	        int numDesA = 1;
	        for (Map.Entry<String, PercepcionDeduccionDto> d : reciboDto.getHmapDeducciones().entrySet())
	        {
	          if ((((PercepcionDeduccionDto)d.getValue()).getDespliega().equals("T")) || (((PercepcionDeduccionDto)d.getValue()).getDespliega().equals("C")))
	          {
	            String identificadorC = RecibosBo.intToStr(numDesC++);
	          //Empleados especiales
	            String claveSAT = "null";
	            String descSAT = "null";
	            if(reciboDto.getNumeroEmpleado().equals("2174578") || reciboDto.getNumeroEmpleado().equals("2404000") ||
	               reciboDto.getNumeroEmpleado().equals("8003305") || reciboDto.getNumeroEmpleado().equals("7563425") || 
	               reciboDto.getNumeroEmpleado().equals("2049907") || reciboDto.getNumeroEmpleado().equals("1964335") ||
	               reciboDto.getNumeroEmpleado().equals("0037897")){
	            	if(tipoNomina.equals("tribunal")){
	            		if(((PercepcionDeduccionDto)d.getValue()).getClaveTsjdf().equals("8580")){
		            		claveSAT = "004";
		            		descSAT  = "Otros";
		            		 //Funciones.variableLog.append("Cambio 004 y descripcion Otros al empleado****** "+reciboDto.getNumeroEmpleado() +"\n");
		            		logger.info("Cambio 004 y descripcion Otros al empleado****** "+reciboDto.getNumeroEmpleado());
		            	}else{
		            		claveSAT = ((PercepcionDeduccionDto)d.getValue()).getClaveSat();
		            		descSAT  = ((PercepcionDeduccionDto)d.getValue()).getDescripcionSat();
		            	}
	            	}else{
	            		claveSAT = ((PercepcionDeduccionDto)d.getValue()).getClaveSat();
		        		descSAT  = ((PercepcionDeduccionDto)d.getValue()).getDescripcionSat();
	            	}
	            	
	            	
	            }else{
	            	claveSAT = ((PercepcionDeduccionDto)d.getValue()).getClaveSat();
	        		descSAT  = ((PercepcionDeduccionDto)d.getValue()).getDescripcionSat();
	            }
	            
	            strDesComplementos.append("COM010|DE0001|DED" + 
	                    identificadorC + "|" + claveSAT + 
	                    "|" + ((PercepcionDeduccionDto)d.getValue()).getClaveTsjdf() + "|" + 
	                    descSAT + "|" + 
	                    ((PercepcionDeduccionDto)d.getValue()).getImporteGravadoFormat() + "|" + 
	                    ((PercepcionDeduccionDto)d.getValue()).getImporteExentoFormat() + "|\n");
	          //Termina Empleados especiales
	            /* Se comenta para que haga los cambios sobre los empleados especiales
	             * strDesComplementos.append("COM010|DE0001|DED" + 
	              identificadorC + "|" + ((PercepcionDeduccionDto)d.getValue()).getClaveSat() + 
	              "|" + ((PercepcionDeduccionDto)d.getValue()).getClaveTsjdf() + "|" + 
	              ((PercepcionDeduccionDto)d.getValue()).getDescripcionSat() + "|" + 
	              ((PercepcionDeduccionDto)d.getValue()).getImporteGravadoFormat() + "|" + 
	              ((PercepcionDeduccionDto)d.getValue()).getImporteExentoFormat() + "|\n");*/
	          }
	          if ((((PercepcionDeduccionDto)d.getValue()).getDespliega().equals("T")) || (((PercepcionDeduccionDto)d.getValue()).getDespliega().equals("C")))
	          {
	            String identificadorA = RecibosBo.intToStr(numDesA++);
	            strDesAddenda.append("A 0001|DE0001|DED" + identificadorA + 
	              "|" + ((PercepcionDeduccionDto)d.getValue()).getDescripcionTsjdf() + "|" + 
	              ((PercepcionDeduccionDto)d.getValue()).getImporteFormat() + "|" + 
	              ((PercepcionDeduccionDto)d.getValue()).getClaveTsjdf() + "|\n");
	          }
	        }
	        contenido.append("H00000|" + idRfc + "|1|" + fechaSistema + "|\n");
	        contenido.append("OPE001|1|" + token + "|\n");
	        contenido.append("C10001|3.2|" + fechaEmision + "|PAGO EN UNA SOLA EXHIBICION|\n");
	        contenido.append("C30003||" + reciboDto.getTotalPercepcionesFormat() + "|" + reciboDto.getDescuentoLayoutFormat() + "|DEDUCCIONES NOMINA|1.0|MXN|" + 
	          reciboDto.getLiquidoCobrarFormat() + "|" + reciboDto.getMetodoPago() + "||||||RECIBO DE NÓMINA|" + reciboDto.getNumArea() + "||\n");
	        if(tipoNomina.equals("tribunal")){
	        	contenido.append("E00001|41|\n");
	        }else if(tipoNomina.equals("consejo")){
	        	contenido.append("E00001|50|\n");
	        }
	        
	        contenido.append("R00001|" + reciboDto.getRfc() + "|" + 
	          reciboDto.getNombreCompleto() + "|||\n");
	        contenido.append("D00001|||||||||MÉXICO||\n");
	        contenido
	          .append("I00001|1.00|SERVICIO||PAGO DE NÓMINA|" + 
	          reciboDto.getTotalPercepcionesFormat() + 
	          "|" + reciboDto.getTotalPercepcionesFormat() + "|0|\n");
	        


	        contenido.append("COM010|1.1|" + reciboDto.getNumeroEmpleado() + 
	          "||" + reciboDto.getCurp() + "|2||" + 
	          fechaEjecucion + "|" + 
	          reciboDto.getFechaInicioPeriodo() + "|" + 
	          reciboDto.getFechaFinPeriodo() + "|" + 
	          reciboDto.getDiasPagados() + "|" + reciboDto.getDescripcionArea() + "||014|||" + 
	          reciboDto.getPuesto() + "|||" + periodicidadPago + "||||\n");
	        contenido.append("COM010|PERC01|" + 
	          reciboDto.getTotalGravadoPercepcionesFormat() + "|" + 
	          reciboDto.getTotalExentoPercepcionesFormat() + "|\n");
	        

	        contenido.append(strPerComplementos);
	        contenido.append("COM010|DEDU01|" + reciboDto.getTotalGravadoDeduccionesFormat() + "|" + 
	          reciboDto.getTotalExentoDeduccionesFormat() + "|\n");
	        

	        contenido.append(strDesComplementos);
	        




	        contenido.append("T10001|" + reciboDto.getIsrFormat() + "|\n");
	        contenido.append("T20001|ISR|" + reciboDto.getIsrFormat() + "|\n");
	        


	        contenido.append("A 0001|1.0|\n");
	        contenido.append("A 0001|FBA001|||||||||||\n");
	        contenido.append("A 0001|NODOC1||\n");
	        contenido.append("A 0001|NOM001|" + reciboDto.getTn() + "|" + 
	          reciboDto.getZp() + "|" + reciboDto.getNumArea() + 
	          "|" + reciboDto.getCodigo() + "|" + 
	          reciboDto.getNivelSalarial() + "|" + 
	          reciboDto.getUniverso() + "|" + 
	          reciboDto.getNumeroPlaza() + "|" + 
	          reciboDto.getSeccionSindical() + "|" + 
	          pagoDescripcion + "|\n");
	        

	        contenido.append("A 0001|PERC01|" + 
	          reciboDto.getTotalGravadoPercepcionesFormat() + "|" + 
	          reciboDto.getTotalExentoPercepcionesFormat() + "|\n");
	        contenido.append(strPerAddenda);
	        

	        contenido.append("A 0001|DEDU01|" + reciboDto.getTotalGravadoDeduccionesFormat() + "|" + 
	          reciboDto.getTotalExentoDeduccionesFormat() + "|\n");
	        contenido.append(strDesAddenda);
	        contenido.append("A 0001|NOTGEN|" + reciboDto.getMensaje() + 
	          "|\n");
	        contenido.append("OPEEND|\n");
	        
	        bo.creaArchivo(pathAbsoluto, contenido);
	      }
	      return recibosEmpleadoDto.getListaRecibos().size();
	    }
	    Funciones.variableLog.append("El empleado " + numeroEmpleado + 
	  	      " no tiene recibos en la fecha de pago especificada" +"\n");
	    logger.info("El empleado " + numeroEmpleado + 
	      " no tiene recibos en la fecha de pago especificada");
	    return 0;
	  }
	  
	  public HashMap<String, CampoRubroDto> getHmapCampos(String tipoNomina)
	  {
	    return this.pagoDao.llenaListasCampos(tipoNomina);
	  }
	  
	  public int generacionMasivaTodos(ArrayList<String> listaEmpleados, HashMap<String, CampoRubroDto> hmapCampos,
			  						   String query, String idRfc, String fechaPaga, 
			  						   String strSerie, String pagoDescripcion, String fechaRecibo, 
			  						   String fechaSistema, String dirTxt, String periodicidadPago, 
			  						   String fechaEjecucion, String metodoPago,ArrayList<String> listaEmpleadosArchivo,
			  						   String tipoNomina)
	  {
	    String token = "token";
	    logger.info("Fecha de Paga = " + fechaPaga);
	    logger.info("Descripcion de la nomina = " + pagoDescripcion);
	    logger.info("Se procesaran un total de empleados = " + listaEmpleados.size());
	    System.out.println("Creando recibos, por favor espere ...");
	    
	    int contador = 0;
	    if (listaEmpleados.size() > 0) {
	      for (String numeroEmpleado : listaEmpleados)
	      {
	    	  if(listaEmpleadosArchivo.contains(numeroEmpleado)){
	    		  logger.info("El empleado "+numeroEmpleado + " Fue omitido en la nomina");
	    	  }else{
	    		   contador = contador + generaTxt(hmapCampos, query, idRfc, numeroEmpleado, fechaPaga, 
							pagoDescripcion, fechaRecibo, token, 
							fechaSistema, dirTxt, periodicidadPago, fechaEjecucion, metodoPago,tipoNomina);
					if (contador % 100 == 0) {
					System.out.println("Archivos generados = " + contador);
					}
	    	  }
	        
	      }
	    } else {
	      logger.info("No hay empleados en la  fecha de paga proporcionada : " + fechaPaga);
	      Funciones.variableLog.append("No hay empleados en la  fecha de paga proporcionada : " + fechaPaga+"\n");
	    }
	    return contador;
	  }
	  
	  public ArrayList<String> getListaTodosEmpleados(String fechaPaga,String tipoNomina)
	  {
	    return this.pagoDao.consultaEmpleadosPorPeriodo(fechaPaga,tipoNomina);
	  }
	  
	  public DescripcionPagoDto getFechaEjecucion(String fechaPaga,String tipoNomina)
	  {
	    return this.pagoDao.consultaFechaEjecucionYdescripcionPago(fechaPaga,tipoNomina);
	  }
}

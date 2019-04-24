package mx.gob.tsjdf.cfdi.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import mx.gob.tsjdf.cfdi.bo.ValidacionBo;
import mx.gob.tsjdf.cfdi.dto.CampoRubroDto;
import mx.gob.tsjdf.cfdi.dto.ErrorDatoEmpleadoDto;
import mx.gob.tsjdf.cfdi.dto.ErroresDatosEmpleadosDto;
import mx.gob.tsjdf.cfdi.dto.PercepcionDeduccionDto;
import mx.gob.tsjdf.cfdi.dto.ReciboDto;
import mx.gob.tsjdf.cfdi.dto.ReciboStatusDto;
import mx.gob.tsjdf.cfdi.dto.RecibosEmpleadoDto;
import mx.gob.tsjdf.util.Funciones;

public class ValidacionService
{
	private static org.apache.log4j.Logger logger = Logger.getLogger(ValidacionService.class);
  ArrayList<ReciboStatusDto> listaStatus;
  
  public ValidacionService()
  {
  //  logger = LoggerSingleton.getInstance();
    this.listaStatus = new ArrayList<>();
  }
  
  public ArrayList<ReciboStatusDto> getListaStatus()
  {
    return this.listaStatus;
  }
  
  public ErroresDatosEmpleadosDto validaInformacionRecibo(HashMap<String, CampoRubroDto> hmapCampos, String query,
		  												  String idRfc, String numeroEmpleado, 
		  												  String fechaPaga, String pagoDescripcion, 
		  												  String fechaEmision, String fechaSistema, 
		  												  String periodicidadPago, String fechaEjecucion, 
		  												  int erroresGenerales, String metodoPago,
		  												  String tipoNomina)
  {
    ErroresDatosEmpleadosDto dto = null;
    RecibosService rs = new RecibosService();
    RecibosEmpleadoDto recibosEmpleadoDto = rs.getRecibos(hmapCampos, query, numeroEmpleado, fechaPaga, metodoPago,tipoNomina);
    

    int numRecibos = 0;
    if (recibosEmpleadoDto.getListaRecibos().size() > 0) {
      for (ReciboDto reciboDto : recibosEmpleadoDto.getListaRecibos())
      {
        boolean valido = true;
        ArrayList<ErrorDatoEmpleadoDto> errores = new ArrayList<>();
        //logger.info("Validando empleado : " + numeroEmpleado + " recibo : " + ++numRecibos + " periodo : " + reciboDto.getIdPeriodo());
        numRecibos++;
        if(numRecibos % 100 == 0){
        	logger.info(numRecibos + " Empleados validados");
        }
        if (ValidacionBo.numeroEmpleadoInvalido(reciboDto.getNumeroEmpleado()))
        {
          valido = false;
          ErrorDatoEmpleadoDto error = ValidacionBo.getErrorDatoEmpDto();
          logger.info("Dato invalido : " + error.getDato() + " Campo : " + error.getCampo() + " Error : " + error.getError());
          errores.add(error);
        }
        if (ValidacionBo.nombreInvalido(reciboDto.getNombreCompleto(), 150, "NOMBRE"))
        {
          valido = false;
          ErrorDatoEmpleadoDto error = ValidacionBo.getErrorDatoEmpDto();
          logger.info("Dato invalido : " + error.getDato() + " Campo : " + error.getCampo() + " Error : " + error.getError());
          errores.add(error);
        }
        if (ValidacionBo.rfcInvalido(reciboDto.getRfc()))
        {
          valido = false;
          ErrorDatoEmpleadoDto error = ValidacionBo.getErrorDatoEmpDto();
          logger.info("Dato invalido : " + error.getDato() + " Campo : " + error.getCampo() + " Error : " + error.getError());
          errores.add(error);
        }
        if (ValidacionBo.curpInvalido(reciboDto.getCurp()))
        {
          valido = false;
          ErrorDatoEmpleadoDto error = ValidacionBo.getErrorDatoEmpDto();
          logger.info("Dato invalido : " + error.getDato() + " Campo : " + error.getCampo() + " Error : " + error.getError());
          errores.add(error);
        }
        if (ValidacionBo.cadenaInvalida(reciboDto.getMetodoPago(), 100, "METODODEPAGO"))
        {
          valido = false;
          ErrorDatoEmpleadoDto error = ValidacionBo.getErrorDatoEmpDto();
          logger.info("Dato invalido : " + error.getDato() + " Campo : " + error.getCampo() + " Error : " + error.getError());
          errores.add(error);
        }
        if (ValidacionBo.cadenaInvalida(reciboDto.getPuesto(), 150, "PUESTO"))
        {
          valido = false;
          ErrorDatoEmpleadoDto error = ValidacionBo.getErrorDatoEmpDto();
          logger.info("Dato invalido : " + error.getDato() + " Campo : " + error.getCampo() + " Error : " + error.getError());
          errores.add(error);
        }
        if (ValidacionBo.cadenaInvalida(reciboDto.getDescripcionArea(), 100, "DEPARTAMENTO (Descripcian del area)"))
        {
          valido = false;
          ErrorDatoEmpleadoDto error = ValidacionBo.getErrorDatoEmpDto();
          logger.info("Dato invalido : " + error.getDato() + " Campo : " + error.getCampo() + " Error : " + error.getError());
          errores.add(error);
        }
        if (ValidacionBo.zpInvalida(reciboDto.getZp()))
        {
          valido = false;
          ErrorDatoEmpleadoDto error = ValidacionBo.getErrorDatoEmpDto();
          logger.info("Dato invalido : " + error.getDato() + " Campo : " + error.getCampo() + " Error : " + error.getError());
          errores.add(error);
        }
        if (ValidacionBo.areaInvalida(reciboDto.getNumArea(),tipoNomina))
        {
          valido = false;
          ErrorDatoEmpleadoDto error = ValidacionBo.getErrorDatoEmpDto();
          logger.info("Dato invalido : " + error.getDato() + " Campo : " + error.getCampo() + " Error : " + error.getError());
          errores.add(error);
        }
        if (ValidacionBo.codigoInvalido(reciboDto.getCodigo(),tipoNomina))
        {
          valido = false;
          ErrorDatoEmpleadoDto error = ValidacionBo.getErrorDatoEmpDto();
          logger.info("Dato invalido : " + error.getDato() + " Campo : " + error.getCampo() + " Error : " + error.getError());
          errores.add(error);
        }
        if (ValidacionBo.nivelInvalido(reciboDto.getNivelSalarial(),tipoNomina))
        {
          valido = false;
          ErrorDatoEmpleadoDto error = ValidacionBo.getErrorDatoEmpDto();
          logger.info("Dato invalido : " + error.getDato() + " Campo : " + error.getCampo() + " Error : " + error.getError());
          errores.add(error);
        }
        if (ValidacionBo.universoInvalido(reciboDto.getUniverso(),tipoNomina))
        {
          valido = false;
          ErrorDatoEmpleadoDto error = ValidacionBo.getErrorDatoEmpDto();
          logger.info("Dato invalido : " + error.getDato() + " Campo : " + error.getCampo() + " Error : " + error.getError());
          errores.add(error);
        }
        if (ValidacionBo.plazaInvalida(reciboDto.getNumeroPlaza(),tipoNomina))
        {
          valido = false;
          ErrorDatoEmpleadoDto error = ValidacionBo.getErrorDatoEmpDto();
          logger.info("Dato invalido : " + error.getDato() + " Campo : " + error.getCampo() + " Error : " + error.getError());
          errores.add(error);
        }
        if (ValidacionBo.seccSindicalInvalida(reciboDto.getSeccionSindical()))
        {
          valido = false;
          ErrorDatoEmpleadoDto error = ValidacionBo.getErrorDatoEmpDto();
          logger.info("Dato invalido : " + error.getDato() + " Campo : " + error.getCampo() + " Error : " + error.getError());
          errores.add(error);
        }
        if (ValidacionBo.fechaInvalida(reciboDto.getFechaInicioPeriodo(), "FECHAINICIALPAGO"))
        {
          valido = false;
          ErrorDatoEmpleadoDto error = ValidacionBo.getErrorDatoEmpDto();
          logger.info("Dato invalido : " + error.getDato() + " Campo : " + error.getCampo() + " Error : " + error.getError());
          errores.add(error);
        }
        if (ValidacionBo.fechaInvalida(reciboDto.getFechaFinPeriodo(), "FECHAFINALPAGO"))
        {
          valido = false;
          ErrorDatoEmpleadoDto error = ValidacionBo.getErrorDatoEmpDto();
          logger.info("Dato invalido : " + error.getDato() + " Campo : " + error.getCampo() + " Error : " + error.getError());
          errores.add(error);
        }
        if (ValidacionBo.tDecimalInvalido(reciboDto.getDiasPagadosFormat(), "NUMDIASPAGADOS"))
        {
          valido = false;
          ErrorDatoEmpleadoDto error = ValidacionBo.getErrorDatoEmpDto();
          logger.info("Dato invalido : " + error.getDato() + " Campo : " + error.getCampo() + " Error : " + error.getError());
          errores.add(error);
        }
        if (ValidacionBo.tImporteInvalido(reciboDto.getTotalPercepcionesFormat(), "SUBTOTAL"))
        {
          valido = false;
          ErrorDatoEmpleadoDto error = ValidacionBo.getErrorDatoEmpDto();
          logger.info("Dato invalido : " + error.getDato() + " Campo : " + error.getCampo() + " Error : " + error.getError());
          errores.add(error);
        }
        if (ValidacionBo.tImporteInvalido(reciboDto.getLiquidoCobrarFormat(), "TOTAL"))
        {
          valido = false;
          ErrorDatoEmpleadoDto error = ValidacionBo.getErrorDatoEmpDto();
          logger.info("Dato invalido : " + error.getDato() + " Campo : " + error.getCampo() + " Error : " + error.getError());
          errores.add(error);
        }
        if (ValidacionBo.tImporteInvalido(reciboDto.getIsrFormat(), "TOTALIMPUESTOSRETENIDOS"))
        {
          valido = false;
          ErrorDatoEmpleadoDto error = ValidacionBo.getErrorDatoEmpDto();
          logger.info("Dato invalido : " + error.getDato() + " Campo : " + error.getCampo() + " Error : " + error.getError());
          errores.add(error);
        }
        if (ValidacionBo.sumaIncorrectaPercepciones(reciboDto.getTotalGravadoPercepciones(), reciboDto.getTotalExentoPercepciones(), reciboDto.getTotalPercepciones()))
        {
          valido = false;
          ErrorDatoEmpleadoDto error = ValidacionBo.getErrorDatoEmpDto();
          logger.info("Dato invalido : " + error.getDato() + " Campo : " + error.getCampo() + " Error : " + error.getError());
          errores.add(error);
        }
        for (PercepcionDeduccionDto percepcion : reciboDto.getHmapPercepciones().values())
        {
          if (ValidacionBo.claveSatInvalida(percepcion.getClaveSat(), "TIPODEDUCCION"))
          {
            valido = false;
            ErrorDatoEmpleadoDto error = ValidacionBo.getErrorDatoEmpDto();
            logger.info("Dato invalido : " + error.getDato() + " Campo : " + error.getCampo() + " Error : " + error.getError());
            errores.add(error);
          }
          if (ValidacionBo.claveTsjdfInvalida(percepcion.getClaveTsjdf(), "CLAVE"))
          {
            valido = false;
            ErrorDatoEmpleadoDto error = ValidacionBo.getErrorDatoEmpDto();
            logger.info("Dato invalido : " + error.getDato() + " Campo : " + error.getCampo() + " Error : " + error.getError());
            errores.add(error);
          }
          if (ValidacionBo.cadenaInvalida(percepcion.getDescripcionSat(), 100, "CONCEPTO"))
          {
            valido = false;
            ErrorDatoEmpleadoDto error = ValidacionBo.getErrorDatoEmpDto();
            logger.info("Dato invalido : " + error.getDato() + " Campo : " + error.getCampo() + " Error : " + error.getError());
            errores.add(error);
          }
          if ((!percepcion.getDespliegaConCero()) && 
            (ValidacionBo.parteExentaParteGravadaCeros(percepcion.getImporteExento().doubleValue(), percepcion.getImporteGravado().doubleValue(), percepcion.getClaveTsjdf())))
          {
            valido = false;
            ErrorDatoEmpleadoDto error = ValidacionBo.getErrorDatoEmpDto();
            logger.info("Dato invalido : " + error.getDato() + " Campo : " + error.getCampo() + " Error : " + error.getError());
            errores.add(error);
          }
        }
        for (PercepcionDeduccionDto descuento : reciboDto.getHmapDeducciones().values())
        {
          if (ValidacionBo.claveSatInvalida(descuento.getClaveSat(), "TIPODEDUCCION"))
          {
            valido = false;
            ErrorDatoEmpleadoDto error = ValidacionBo.getErrorDatoEmpDto();
            logger.info("Dato invalido : " + error.getDato() + " Campo : " + error.getCampo() + " Error : " + error.getError());
            errores.add(error);
          }
          if (ValidacionBo.claveTsjdfInvalida(descuento.getClaveTsjdf(), "CLAVE"))
          {
            valido = false;
            ErrorDatoEmpleadoDto error = ValidacionBo.getErrorDatoEmpDto();
            logger.info("Dato invalido : " + error.getDato() + " Campo : " + error.getCampo() + " Error : " + error.getError());
            errores.add(error);
          }
          if (ValidacionBo.cadenaInvalida(descuento.getDescripcionSat(), 100, "CONCEPTO"))
          {
            valido = false;
            ErrorDatoEmpleadoDto error = ValidacionBo.getErrorDatoEmpDto();
            logger.info("Dato invalido : " + error.getDato() + " Campo : " + error.getCampo() + " Error : " + error.getError());
            errores.add(error);
          }
          if (ValidacionBo.parteExentaParteGravadaCeros(descuento.getImporteExento().doubleValue(), descuento.getImporteGravado().doubleValue(), descuento.getClaveTsjdf()))
          {
            valido = false;
            ErrorDatoEmpleadoDto error = ValidacionBo.getErrorDatoEmpDto();
            logger.info("Dato invalido : " + error.getDato() + " Campo : " + error.getCampo() + " Error : " + error.getError());
            errores.add(error);
          }
        }
        if ((valido) && (erroresGenerales == 0))
        {
          //logger.info("estatus : valido");
          this.listaStatus.add(new ReciboStatusDto(fechaPaga, numeroEmpleado, reciboDto.getIdPeriodo(), "valido"));
          ValidacionBo.recibosValidos += 1;
        }
        else
        {
          if (!valido)
          {
            dto = new ErroresDatosEmpleadosDto();
            dto.setNumeroEmpleado(reciboDto.getNumeroEmpleado());
            dto.setPeriodo(reciboDto.getIdPeriodo());
            dto.setErrores(errores);
          }
          logger.info("Total de errores generales = " + erroresGenerales);
          logger.info("Total de errores particulares = " + errores.size());
          logger.info("estatus : invalido");
          this.listaStatus.add(new ReciboStatusDto(fechaPaga, numeroEmpleado, reciboDto.getIdPeriodo(), "invalido"));
          ValidacionBo.recibosInvalidos += 1;
        }
      }
    } else {
      logger.info("El empleado: " + numeroEmpleado + " no tiene informacian en la fecha de paga: " + fechaPaga);
    }
    return dto;
  }
  
  public ArrayList<ErroresDatosEmpleadosDto> validacionMasivaTodos(ArrayList<String> listaEmpleados, HashMap<String, CampoRubroDto> hmapCampos,
		  													       String query, String idRfc, 
		  													       String fechaPaga, String strSerie, 
		  													       String pagoDescripcion, String fechaRecibo, 
		  													       String fechaSistema, String periodicidadPago, 
		  													       String fechaEjecucion, String metodoPago,
		  													       String tipoNomina)
  {
    int erroresGrales = 0;
    logger.info("Fecha de Paga = " + fechaPaga);
    logger.info("Descripcian de la namina = " + pagoDescripcion);
    logger.info("Se procesaran un total de empleados = " + listaEmpleados.size());
    
    ArrayList<ErroresDatosEmpleadosDto> errores = new ArrayList<>();
    
    logger.info("VALIDANDO INFORMACION GENERAL");
    if (ValidacionBo.cadenaInvalida(pagoDescripcion, 40, "LIQUIDACIONDEPAGO"))
    {
      logger.error("La descripcian de la paga no es valida: " + pagoDescripcion + ", este error afecta a todos los empleados");
      errores.add(new ErroresDatosEmpleadosDto("TODOS", ValidacionBo.getErrorDatoEmpDto()));
      erroresGrales++;
    }
    if (ValidacionBo.fechaInvalida(fechaEjecucion, "FECHAPAGO"))
    {
      logger.error("Fecha de pago nula: " + fechaEjecucion + ", este error afecta a todos los empleados");
      errores.add(new ErroresDatosEmpleadosDto("TODOS", ValidacionBo.getErrorDatoEmpDto()));
      erroresGrales++;
    }
    logger.info("VALIDANDO INFORMACIaN DE CADA TXT");
    int totalEmpleados = 0;
    if (listaEmpleados.size() > 0) {
      for (String numeroEmpleado : listaEmpleados)
      {
        //logger.info("Num de empleado revisado: " + ++totalEmpleados);
    	  totalEmpleados++;
    	  if(totalEmpleados % 100 == 0){
    		  logger.info(totalEmpleados + " Empleados validados");
    	  }
        ErroresDatosEmpleadosDto dto = validaInformacionRecibo(hmapCampos, query, idRfc, numeroEmpleado, fechaPaga, 
        													   pagoDescripcion, fechaRecibo, fechaSistema,
        													   periodicidadPago, fechaEjecucion, erroresGrales, 
        													   metodoPago,tipoNomina);
        if (dto != null) {
          errores.add(dto);
        }
      }
    } else {
      logger.info("No hay empleados en la  fecha de paga proporcionada : " + fechaPaga);
    }
    logger.info("TOTAL DE ERRORES QUE AFECTAN TODA LA NaMINA = " + erroresGrales);
    logger.info("TOTAL DE EMPLEADOS REVISADOS = " + totalEmpleados);
    logger.info("TOTAL DE RECIBOS VaLIDOS = " + ValidacionBo.recibosValidos);
    logger.info("TOTAL DE RECIBOS INVaLIDOS = " + ValidacionBo.recibosInvalidos);
    Funciones.erroresGrales    = erroresGrales;
    Funciones.totalEmpleados   = totalEmpleados;
    Funciones.recibosValidos   = ValidacionBo.recibosValidos;
    Funciones.recibosInvalidos = ValidacionBo.recibosInvalidos;
    ValidacionBo.recibosValidos   = 0;
    ValidacionBo.recibosInvalidos = 0;
    
    return errores;
  }
}

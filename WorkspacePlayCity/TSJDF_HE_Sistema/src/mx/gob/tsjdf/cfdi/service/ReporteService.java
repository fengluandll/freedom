package mx.gob.tsjdf.cfdi.service;

import java.util.ArrayList;

import mx.gob.tsjdf.cfdi.dto.EmpleadoTokenDto;
import mx.gob.tsjdf.cfdi.dto.ErroresDatosEmpleadosDto;
import mx.gob.tsjdf.cfdi.dto.ReciboStatusDto;
import mx.gob.tsjdf.cfdi.recibos.reporte.ReportesExcel;
import mx.gob.tsjdf.cfdi.recibos.reporte.ReportesPdf;

public class ReporteService
{
  ReportesExcel re = new ReportesExcel();
  ReportesPdf rpdf = new ReportesPdf();
  
  public String crearReporteResumen(String pathReporte, StringBuffer contenido)
  {
    ArchivoService archivoService = new ArchivoService();
    archivoService.creaArchivo(pathReporte, contenido);
    
    return pathReporte;
  }
  
  public String crearExcelRelacionETA(String pathReporte, ArrayList<EmpleadoTokenDto> listaETA, String fechaPago)
  {
    return this.re.creaRelacionEmpleadoToken(pathReporte, listaETA, fechaPago);
  }
  
  public String creaReportePdfRelacionETA(String pathReporte, ArrayList<EmpleadoTokenDto> listaETA, String fechaPago)
  {
    return this.rpdf.creaReportePdfRelacionETA(pathReporte, listaETA, fechaPago);
  }
  
  public String creaLogPdf(String pathLog, String pathLogPdf)
  {
    return this.rpdf.creaLogPdf(pathLog, pathLogPdf);
  }
  
  public String creaRelacionTxtNoValidos(String pathReporte, ArrayList<ErroresDatosEmpleadosDto> errores, String fechaPaga, ArrayList<ReciboStatusDto> listaStatus,String descripcion)
  {
    return this.re.creaRelacionTxtNoValidos(pathReporte, errores, fechaPaga, listaStatus,descripcion);
  }
}

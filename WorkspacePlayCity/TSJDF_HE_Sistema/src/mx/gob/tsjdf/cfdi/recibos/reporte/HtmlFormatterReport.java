package mx.gob.tsjdf.cfdi.recibos.reporte;


import mx.gob.tsjdf.cfdi.dto.ReporteDto;
import mx.gob.tsjdf.cfdi.dto.SolicitudTokenDto;

public class HtmlFormatterReport
{
  public StringBuffer contenidoReporte(ReporteDto reporte)
  {
    StringBuffer contenido = new StringBuffer(1000);
    
    contenido.append(getHeader(reporte.getFechaPaga()));
    contenido.append(" <table border='1' width='1300'> ");
    contenido.append(getTituloNomina(reporte.getFechaPaga()));
    
    contenido.append("<tr>");
    contenido.append("<td style='border-style:dotted;'>Descripcion de la Nomina</td>");
    contenido.append("<td style='border-style:dotted;'>" + reporte.getDescripcionNomina() + "</td>");
    contenido.append("</tr>");
    
    contenido.append("<tr>");
    contenido.append("<td style='border-style:dotted;'>Fecha de Paga Meta4</td>");
    contenido.append("<td style='border-style:dotted;'>" + reporte.getFechaPaga() + "</td>");
    contenido.append("</tr>");
    
    contenido.append("<tr>");
    contenido.append("<td style='border-style:dotted;'>Fecha de pago efectiva</td>");
    contenido.append("<td style='border-style:dotted;'>" + reporte.getFechaEjecucion() + "</td>");
    contenido.append("</tr>");
    
    contenido.append("<tr>");
    contenido.append("<td style='border-style:dotted;'>Folio Inicial</td>");
    contenido.append("<td style='border-style:dotted;'>" + reporte.getFolioinicial() + "</td>");
    contenido.append("</tr>");
    
    contenido.append("<tr>");
    contenido.append("<td style='border-style:dotted;'>Fecha de Emision</td>");
    contenido.append("<td style='border-style:dotted;'>" + reporte.getFechaEmision() + "</td>");
    contenido.append("</tr>");
    
    contenido.append("<tr>");
    contenido.append("<td style='border-style:dotted;'>Numero de Empleados procesados</td>");
    contenido.append("<td style='border-style:dotted;'>" + reporte.getNumEmpleadosProcesados() + "</td>");
    contenido.append("</tr>");
    
    contenido.append("<tr>");
    contenido.append("<td style='border-style:dotted;'>Numero de archivos TXT generados con token</td>");
    contenido.append("<td style='border-style:dotted;'>" + reporte.getNumRecibosGenerados() + "</td>");
    contenido.append("</tr>");
    
    contenido.append("<tr>");
    contenido.append("<td style='border-style:dotted;'>Numero de archivos TXT transferidos por FTP</td>");
    contenido.append("<td style='border-style:dotted;'>" + reporte.getNumRecibosTranferidosFTP() + "</td>");
    contenido.append("</tr>");
    
    contenido.append("<tr>");
    contenido.append("<td style='border-style:dotted;'>Inicio de generacion de TXT</td>");
    contenido.append("<td style='border-style:dotted;'>" + reporte.getFechaInicialGenTXT() + "</td>");
    contenido.append("</tr>");
    
    contenido.append("<tr>");
    contenido.append("<td style='border-style:dotted;'>Fin de generacion de TXT</td>");
    contenido.append("<td style='border-style:dotted;'>" + reporte.getFechaFinalGenTXT() + "</td>");
    contenido.append("</tr>");
    
    contenido.append("<tr>");
    contenido.append("<td style='border-style:dotted;'>Inicio de Transferencia por FTP</td>");
    contenido.append("<td style='border-style:dotted;'>" + reporte.getFechaInicialTransFTP() + "</td>");
    contenido.append("</tr>");
    
    contenido.append("<tr>");
    contenido.append("<td style='border-style:dotted;'>Fin de la Transferencia por FTP</td>");
    contenido.append("<td style='border-style:dotted;'>" + reporte.getFechaFinalTransFTP() + "</td>");
    contenido.append("</tr>");
    if (reporte.getListaSolicitudes() != null)
    {
      contenido.append("</table>");
      contenido.append("<br></br>");
      contenido.append("<table border='1' width='1300'>");
      contenido.append("<tr><td colspan=3 style='border-style:dotted;'><b>Resumen de solicitudes de token</b></td></tr>");
      contenido.append("<tr><td style='border-style:dotted;'>Total de solicitudes</td>");
      contenido.append("<td colspan=2 style='border-style:dotted;'>" + reporte.getListaSolicitudes().size() + "</td></tr>");
      contenido.append("<tr><td style='border-style:dotted;'>Fecha y Hora de la solicitud</td>");
      contenido.append("<td style='border-style:dotted;'>Solicitud</td>");
      contenido.append("<td style='border-style:dotted;'>Total de tokens solicitados</td></tr>");
      for (SolicitudTokenDto solicitud : reporte.getListaSolicitudes())
      {
        contenido.append("<tr><td style='border-style:dotted;'>" + solicitud.getFechaHrSolicitud() + "</td>");
        contenido.append("<td style='border-style:dotted;'>" + solicitud.getSolicitud() + "</td>");
        contenido.append("<td style='border-style:dotted;'>" + solicitud.getNumeroTokens() + "</td></tr>");
      }
      contenido.append("</table>");
    }
    contenido.append("</table>");
    contenido.append("<br></br>");
    contenido.append("<table border='1' width='1300'>");
    contenido.append("<tr><td colspan=2 style='border-style:dotted;'><b>Soporte documental</b></td></tr>");
    
    contenido.append("<tr>");
    contenido.append("<td style='border-style:dotted;'>Logs del proceso</td>");
    contenido.append("<td style='border-style:dotted;'><a href='" + reporte.getLogPdf() + "'>" + reporte.getLogPdf() + "</a></td>");
    contenido.append("</tr>");
    
    contenido.append("<tr>");
    contenido.append("<td style='border-style:dotted;'>Relacion empleado-token</td>");
    contenido.append("<td style='border-style:dotted;'><a href='" + reporte.getRelacionEmpleadoTokenPdf() + "'>" + reporte.getRelacionEmpleadoTokenPdf() + "</a></td>");
    contenido.append("</tr>");
    
    contenido.append("</table>");
    
    contenido.append(getFooter());
    
    return contenido;
  }
  
  private String getHeader(String fechaPago)
  {
    return 
      "<META HTTP-EQUIV='Content-Type' CONTENT='text/html; charset=UTF-8'> <HTML><HEAD><title>Generacion de Series y Folios - Reporte - " + fechaPago + "</title></HEAD><BODY>";
  }
  
  private String getFooter()
  {
    return "</BODY></HTML>";
  }
  
  private String getTituloNomina(String fechaPago)
  {
    return "<tr><td colspan=2 align='center style='border-style:dotted; color:green'><h1>Reporte de generacion de TXT´s - " + fechaPago + "</h1></td></tr>";
  }
}

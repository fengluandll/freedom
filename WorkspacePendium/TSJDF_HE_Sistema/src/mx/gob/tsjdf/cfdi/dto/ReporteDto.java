package mx.gob.tsjdf.cfdi.dto;

import java.util.ArrayList;

public class ReporteDto
{
  private String descripcionNomina;
  private String fechaPaga;
  private String fechaEjecucion;
  private String folioinicial;
  private String fechaEmision;
  private int numEmpleadosProcesados;
  private int numRecibosGenerados;
  private int numRecibosTranferidosFTP;
  private String fechaInicialGenTXT;
  private String fechaFinalGenTXT;
  private String fechaInicialTransFTP;
  private String fechaFinalTransFTP;
  private String logPdf;
  private String relacionEmpleadoTokenPdf;
  private ArrayList<SolicitudTokenDto> listaSolicitudes;
  
  public String getDescripcionNomina()
  {
    return this.descripcionNomina;
  }
  
  public void setDescripcionNomina(String descripcionNomina)
  {
    this.descripcionNomina = descripcionNomina;
  }
  
  public String getFechaPaga()
  {
    return this.fechaPaga;
  }
  
  public void setFechaPaga(String fechaPaga)
  {
    this.fechaPaga = fechaPaga;
  }
  
  public String getFolioinicial()
  {
    return this.folioinicial;
  }
  
  public void setFolioinicial(String folioinicial)
  {
    this.folioinicial = folioinicial;
  }
  
  public String getFechaEmision()
  {
    return this.fechaEmision;
  }
  
  public void setFechaEmision(String fechaEmision)
  {
    this.fechaEmision = fechaEmision;
  }
  
  public int getNumEmpleadosProcesados()
  {
    return this.numEmpleadosProcesados;
  }
  
  public void setNumEmpleadosProcesados(int numEmpleadosProcesados)
  {
    this.numEmpleadosProcesados = numEmpleadosProcesados;
  }
  
  public int getNumRecibosGenerados()
  {
    return this.numRecibosGenerados;
  }
  
  public void setNumRecibosGenerados(int numRecibosGenerados)
  {
    this.numRecibosGenerados = numRecibosGenerados;
  }
  
  public int getNumRecibosTranferidosFTP()
  {
    return this.numRecibosTranferidosFTP;
  }
  
  public void setNumRecibosTranferidosFTP(int numRecibosTranferidosFTP)
  {
    this.numRecibosTranferidosFTP = numRecibosTranferidosFTP;
  }
  
  public String getLogPdf()
  {
    return this.logPdf;
  }
  
  public void setLogPdf(String logPdf)
  {
    this.logPdf = logPdf;
  }
  
  public String getRelacionEmpleadoTokenPdf()
  {
    return this.relacionEmpleadoTokenPdf;
  }
  
  public void setRelacionEmpleadoTokenPdf(String relacionEmpleadoTokenPdf)
  {
    this.relacionEmpleadoTokenPdf = relacionEmpleadoTokenPdf;
  }
  
  public String getFechaInicialGenTXT()
  {
    return this.fechaInicialGenTXT;
  }
  
  public void setFechaInicialGenTXT(String fechaInicialGenTXT)
  {
    this.fechaInicialGenTXT = fechaInicialGenTXT;
  }
  
  public String getFechaFinalGenTXT()
  {
    return this.fechaFinalGenTXT;
  }
  
  public void setFechaFinalGenTXT(String fechaFinalGenTXT)
  {
    this.fechaFinalGenTXT = fechaFinalGenTXT;
  }
  
  public String getFechaInicialTransFTP()
  {
    return this.fechaInicialTransFTP;
  }
  
  public void setFechaInicialTransFTP(String fechaInicialTransFTP)
  {
    this.fechaInicialTransFTP = fechaInicialTransFTP;
  }
  
  public String getFechaFinalTransFTP()
  {
    return this.fechaFinalTransFTP;
  }
  
  public void setFechaFinalTransFTP(String fechaFinalTransFTP)
  {
    this.fechaFinalTransFTP = fechaFinalTransFTP;
  }
  
  public String getFechaEjecucion()
  {
    return this.fechaEjecucion;
  }
  
  public void setFechaEjecucion(String fechaEjecucion)
  {
    this.fechaEjecucion = fechaEjecucion;
  }
  
  public ArrayList<SolicitudTokenDto> getListaSolicitudes()
  {
    return this.listaSolicitudes;
  }
  
  public void setListaSolicitudes(ArrayList<SolicitudTokenDto> listaSolicitudes)
  {
    this.listaSolicitudes = listaSolicitudes;
  }
}

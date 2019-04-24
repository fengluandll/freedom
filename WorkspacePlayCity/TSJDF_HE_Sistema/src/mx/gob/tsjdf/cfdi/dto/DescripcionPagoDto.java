package mx.gob.tsjdf.cfdi.dto;

public class DescripcionPagoDto
{
  private String fechaInicial;
  private String fechaFinal;
  private String fechaPaga;
  private int diasPago;
  private String descripcion;
  private String fechaEjecucion;
  
  public String getFechaInicial()
  {
    return this.fechaInicial;
  }
  
  public void setFechaInicial(String fechaInicial)
  {
    this.fechaInicial = fechaInicial;
  }
  
  public String getFechaFinal()
  {
    return this.fechaFinal;
  }
  
  public void setFechaFinal(String fechaFinal)
  {
    this.fechaFinal = fechaFinal;
  }
  
  public String getFechaPaga()
  {
    return this.fechaPaga;
  }
  
  public void setFechaPaga(String fechaPaga)
  {
    this.fechaPaga = fechaPaga;
  }
  
  public int getDiasPago()
  {
    return this.diasPago;
  }
  
  public void setDiasPago(int diasPago)
  {
    this.diasPago = diasPago;
  }
  
  public String getDescripcion()
  {
    return this.descripcion;
  }
  
  public void setDescripcion(String descripcion)
  {
    this.descripcion = descripcion;
  }
  
  public String getFechaEjecucion()
  {
    if (this.fechaEjecucion == null) {
      return "";
    }
    return this.fechaEjecucion;
  }
  
  public void setFechaEjecucion(String fechaEjecucion)
  {
    this.fechaEjecucion = fechaEjecucion;
  }
}

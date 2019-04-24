package mx.gob.tsjdf.cfdi.dto;

public class PagoEmpleadoDto
{
  private String fechaPago;
  private String fechaInicio;
  private String fechaFin;
  private String descripcion;
  
  public String getFechaPago()
  {
    return this.fechaPago;
  }
  
  public void setFechaPago(String fechaPago)
  {
    this.fechaPago = fechaPago;
  }
  
  public String getFechaInicio()
  {
    return this.fechaInicio;
  }
  
  public void setFechaInicio(String fechaInicio)
  {
    this.fechaInicio = fechaInicio;
  }
  
  public String getFechaFin()
  {
    return this.fechaFin;
  }
  
  public void setFechaFin(String fechaFin)
  {
    this.fechaFin = fechaFin;
  }
  
  public String getDescripcion()
  {
    return this.descripcion;
  }
  
  public void setDescripcion(String descripcion)
  {
    this.descripcion = descripcion;
  }
}

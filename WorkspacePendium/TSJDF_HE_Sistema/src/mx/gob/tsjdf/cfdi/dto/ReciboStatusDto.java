package mx.gob.tsjdf.cfdi.dto;

public class ReciboStatusDto
{
  private String fechaPaga;
  private String numeroEmpleado;
  private int periodo;
  private String status;
  
  public ReciboStatusDto(String fechaPaga, String numeroEmpleado, int periodo, String status)
  {
    this.fechaPaga = fechaPaga;
    this.numeroEmpleado = numeroEmpleado;
    this.periodo = periodo;
    this.status = status;
  }
  
  public String getFechaPaga()
  {
    return this.fechaPaga;
  }
  
  public void setFechaPaga(String fechaPaga)
  {
    this.fechaPaga = fechaPaga;
  }
  
  public String getNumeroEmpleado()
  {
    return this.numeroEmpleado;
  }
  
  public void setNumeroEmpleado(String numeroEmpleado)
  {
    this.numeroEmpleado = numeroEmpleado;
  }
  
  public int getPeriodo()
  {
    return this.periodo;
  }
  
  public void setPeriodo(int periodo)
  {
    this.periodo = periodo;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
}

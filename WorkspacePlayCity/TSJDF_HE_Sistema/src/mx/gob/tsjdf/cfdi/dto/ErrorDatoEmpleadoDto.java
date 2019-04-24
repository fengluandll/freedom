package mx.gob.tsjdf.cfdi.dto;

public class ErrorDatoEmpleadoDto
{
  private String dato;
  private String campo;
  private String error;
  
  public ErrorDatoEmpleadoDto() {}
  
  public ErrorDatoEmpleadoDto(String dato, String campo, String error)
  {
    this.error = error;
    this.dato = dato;
    this.campo = campo;
  }
  
  public String getDato()
  {
    return this.dato;
  }
  
  public void setDato(String dato)
  {
    this.dato = dato;
  }
  
  public String getCampo()
  {
    return this.campo;
  }
  
  public void setCampo(String campo)
  {
    this.campo = campo;
  }
  
  public String getError()
  {
    return this.error;
  }
  
  public void setError(String error)
  {
    this.error = error;
  }
}

package mx.gob.tsjdf.cfdi.dto;

public class SolicitudTokenDto
{
  private String fechaHrSolicitud;
  private String solicitud;
  private int numeroTokens;
  
  public String getFechaHrSolicitud()
  {
    return this.fechaHrSolicitud;
  }
  
  public void setFechaHrSolicitud(String fechaHrSolicitud)
  {
    this.fechaHrSolicitud = fechaHrSolicitud;
  }
  
  public String getSolicitud()
  {
    return this.solicitud;
  }
  
  public void setSolicitud(String solicitud)
  {
    this.solicitud = solicitud;
  }
  
  public int getNumeroTokens()
  {
    return this.numeroTokens;
  }
  
  public void setNumeroTokens(int numeroTokens)
  {
    this.numeroTokens = numeroTokens;
  }
}

package mx.gob.tsjdf.cfdi.dto;

import java.text.DecimalFormat;

public class PercepcionDeduccionDto
{
  DecimalFormat df = new DecimalFormat("0.00");
  private String claveTsjdf;
  private String descripcionTsjdf;
  private Double importe;
  private String claveSat;
  private String descripcionSat;
  private int exento;
  private Double importeExento;
  private Double importeGravado;
  private String despliega;
  private boolean despliegaConCero;
  
  public String getClaveTsjdf()
  {
    return this.claveTsjdf;
  }
  
  public void setClaveTsjdf(String claveTsjdf)
  {
    this.claveTsjdf = claveTsjdf;
  }
  
  public String getDescripcionTsjdf()
  {
    return this.descripcionTsjdf;
  }
  
  public void setDescripcionTsjdf(String descripcionTsjdf)
  {
    this.descripcionTsjdf = descripcionTsjdf;
  }
  
  public Double getImporte()
  {
    return this.importe;
  }
  
  public void setImporte(Double importe)
  {
    this.importe = importe;
  }
  
  public String getClaveSat()
  {
    return this.claveSat;
  }
  
  public void setClaveSat(String claveSat)
  {
    this.claveSat = claveSat;
  }
  
  public String getDescripcionSat()
  {
    if ((this.descripcionSat != null) && (this.descripcionSat.length() > 100)) {
      return this.descripcionSat.substring(0, 99);
    }
    return this.descripcionSat;
  }
  
  public void setDescripcionSat(String descripcionSat)
  {
    this.descripcionSat = descripcionSat;
  }
  
  public int getExento()
  {
    return this.exento;
  }
  
  public void setExento(int exento)
  {
    this.exento = exento;
  }
  
  public Double getImporteExento()
  {
    if (this.importeExento != null) {
      return this.importeExento;
    }
    return new Double(0.0D);
  }
  
  public void setImporteExento(Double importeExento)
  {
    this.importeExento = importeExento;
  }
  
  public Double getImporteGravado()
  {
    if (this.importeGravado != null) {
      return this.importeGravado;
    }
    return new Double(0.0D);
  }
  
  public void setImporteGravado(Double importeGravado)
  {
    this.importeGravado = importeGravado;
  }
  
  public String getDespliega()
  {
    if (this.despliega == null) {
      return "T";
    }
    return this.despliega;
  }
  
  public void setDespliega(String despliega)
  {
    this.despliega = despliega;
  }
  
  public boolean getDespliegaConCero()
  {
    return this.despliegaConCero;
  }
  
  public void setDespliegaConCero(boolean despliegaConCero)
  {
    this.despliegaConCero = despliegaConCero;
  }
  
  public String getImporteFormat()
  {
    return this.df.format(getImporte());
  }
  
  public String getImporteExentoFormat()
  {
    return this.df.format(getImporteExento());
  }
  
  public String getImporteGravadoFormat()
  {
    return this.df.format(getImporteGravado());
  }
}

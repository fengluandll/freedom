package mx.gob.tsjdf.cfdi.dto;

import java.io.Serializable;

public class CampoRubroDto implements Serializable{
	
  private String descripcionTsjdf;
  private int tipoCampo;
  private String tabla;
  private String columnName;
  private String perceptionClaveSat;
  private String perceptionDescripcionSat;
  private String deduccionClaveSat;
  private String deduccionDescripcionSat;
  private int exento;
  private String tablaExento;
  private String campoExento;
  private String tablaGravable;
  private String campoGravable;
  private String despliega;
  private String despliegaConCero;
  
  public CampoRubroDto() {}
  
  public CampoRubroDto(int tipoCampo, String tabla)
  {
    this.tipoCampo = tipoCampo;
    this.tabla = tabla;
  }
  
  public String getDescripcionTsjdf()
  {
    return this.descripcionTsjdf;
  }
  
  public void setDescripcionTsjdf(String descripcionTsjdf)
  {
    this.descripcionTsjdf = descripcionTsjdf;
  }
  
  public int getTipoCampo()
  {
    return this.tipoCampo;
  }
  
  public void setTipoCampo(int tipoCampo)
  {
    this.tipoCampo = tipoCampo;
  }
  
  public String getPerceptionClaveSat()
  {
    return this.perceptionClaveSat;
  }
  
  public void setPerceptionClaveSat(String perceptionClaveSat)
  {
    this.perceptionClaveSat = perceptionClaveSat;
  }
  
  public String getPerceptionDescripcionSat()
  {
    return this.perceptionDescripcionSat;
  }
  
  public void setPerceptionDescripcionSat(String perceptionDescripcionSat)
  {
    this.perceptionDescripcionSat = perceptionDescripcionSat;
  }
  
  public String getDeduccionClaveSat()
  {
    return this.deduccionClaveSat;
  }
  
  public void setDeduccionClaveSat(String deduccionClaveSat)
  {
    this.deduccionClaveSat = deduccionClaveSat;
  }
  
  public String getDeduccionDescripcionSat()
  {
    return this.deduccionDescripcionSat;
  }
  
  public void setDeduccionDescripcionSat(String deduccionDescripcionSat)
  {
    this.deduccionDescripcionSat = deduccionDescripcionSat;
  }
  
  public int getExento()
  {
    return this.exento;
  }
  
  public void setExento(int exento)
  {
    this.exento = exento;
  }
  
  public String getCampoExento()
  {
    return this.campoExento;
  }
  
  public void setCampoExento(String campoExento)
  {
    this.campoExento = campoExento;
  }
  
  public String getCampoGravable()
  {
    return this.campoGravable;
  }
  
  public void setCampoGravable(String campoGravable)
  {
    this.campoGravable = campoGravable;
  }
  
  public String getTabla()
  {
    return this.tabla;
  }
  
  public void setTabla(String tabla)
  {
    this.tabla = tabla;
  }
  
  public String getTablaExento()
  {
    return this.tablaExento;
  }
  
  public void setTablaExento(String tablaExento)
  {
    this.tablaExento = tablaExento;
  }
  
  public String getTablaGravable()
  {
    return this.tablaGravable;
  }
  
  public void setTablaGravable(String tablaGravable)
  {
    this.tablaGravable = tablaGravable;
  }
  
  public String getColumnName()
  {
    return this.columnName;
  }
  
  public void setColumnName(String columnName)
  {
    this.columnName = columnName;
  }
  
  public String getDespliega()
  {
    return this.despliega;
  }
  
  public void setDespliega(String despliega)
  {
    this.despliega = despliega;
  }
  
  public String getDespliegaConCero()
  {
    if (this.despliegaConCero == null) {
      return "N";
    }
    return this.despliegaConCero;
  }
  
  public void setDespliegaConCero(String despliegaConCero)
  {
    this.despliegaConCero = despliegaConCero;
  }
}

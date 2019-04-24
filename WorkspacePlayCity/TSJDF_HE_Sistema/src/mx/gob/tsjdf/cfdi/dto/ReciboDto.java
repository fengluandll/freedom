package mx.gob.tsjdf.cfdi.dto;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.HashMap;

import mx.gob.tsjdf.cfdi.bo.RecibosBo;

public class ReciboDto
  implements Serializable
{
  DecimalFormat df = new DecimalFormat("0.00");
  private int tn = 1;
  private String numeroEmpleado;
  private String nombreCompleto;
  private String curp;
  private String codigo;
  private String nivelSalarial;
  private String numeroPlaza;
  private String puesto;
  private String rfc;
  private String seccionSindical;
  private String numArea;
  private String zp;
  private String universo;
  private double horasExtraDobles;
  private double horasExtraTriples;
  private double diasPagados;
  private int idPeriodo;
  private String mensaje;
  private String calle;
  private String numeroExterior;
  private String numeroInterior;
  private String colonia;
  private String delegacionMunicipio;
  private String codigoPostal;
  private String estado;
  private String claveSatBanco;
  private String cuentaUltimosDig;
  private int diasHorasExtra;
  private double totalPercepciones;
  private double totalDescuentos;
  private String metodoPago;
  private double liquidoCobrar;
  private String fechaInicioPeriodo;
  private String fechaFinPeriodo;
  private String descripcionArea;
  private double totalExentoPercepciones;
  private double totalGravadoPercepciones;
  private double totalExentoDeducciones;
  private double totalGravadoDeducciones;
  private HashMap<String, PercepcionDeduccionDto> hmapPercepciones = new HashMap<>();
  private HashMap<String, PercepcionDeduccionDto> hmapDeducciones = new HashMap<>();
  
  public String getNumeroEmpleado()
  {
    return this.numeroEmpleado;
  }
  
  public void setNumeroEmpleado(String numeroEmpleado)
  {
    this.numeroEmpleado = numeroEmpleado;
  }
  
  public String getNombreCompleto()
  {
    if (this.nombreCompleto != null) {
      return RecibosBo.validaNullEspacios(this.nombreCompleto);
    }
    return "";
  }
  
  public void setNombreCompleto(String nombreCompleto)
  {
    this.nombreCompleto = nombreCompleto;
  }
  
  public String getCurp()
  {
    return this.curp;
  }
  
  public void setCurp(String curp)
  {
    this.curp = curp;
  }
  
  public String getCodigo()
  {
    return this.codigo;
  }
  
  public void setCodigo(String codigo)
  {
    this.codigo = codigo;
  }
  
  public String getNivelSalarial()
  {
    return this.nivelSalarial;
  }
  
  public void setNivelSalarial(String nivelSalarial)
  {
    this.nivelSalarial = nivelSalarial;
  }
  
  public String getNumeroPlaza()
  {
    return this.numeroPlaza;
  }
  
  public void setNumeroPlaza(String numeroPlaza)
  {
    this.numeroPlaza = numeroPlaza;
  }
  
  public String getPuesto()
  {
    return this.puesto;
  }
  
  public void setPuesto(String puesto)
  {
    this.puesto = puesto;
  }
  
  public String getRfc()
  {
    return this.rfc;
  }
  
  public void setRfc(String rfc)
  {
    this.rfc = rfc;
  }
  
  public String getSeccionSindical()
  {
    return this.seccionSindical;
  }
  
  public String getNumArea()
  {
    return this.numArea;
  }
  
  public void setNumArea(String numArea)
  {
    this.numArea = numArea;
  }
  
  public void setSeccionSindical(String seccionSindical)
  {
    this.seccionSindical = seccionSindical;
  }
  
  public HashMap<String, PercepcionDeduccionDto> getHmapPercepciones()
  {
    return this.hmapPercepciones;
  }
  
  public void setHmapPercepciones(HashMap<String, PercepcionDeduccionDto> hmapPercepciones)
  {
    this.hmapPercepciones = hmapPercepciones;
  }
  
  public HashMap<String, PercepcionDeduccionDto> getHmapDeducciones()
  {
    return this.hmapDeducciones;
  }
  
  public void setHmapDeducciones(HashMap<String, PercepcionDeduccionDto> hmapDeducciones)
  {
    this.hmapDeducciones = hmapDeducciones;
  }
  
  public String getZp()
  {
    return this.zp;
  }
  
  public void setZp(String zp)
  {
    this.zp = zp;
  }
  
  public String getUniverso()
  {
    return this.universo;
  }
  
  public void setUniverso(String universo)
  {
    this.universo = universo;
  }
  
  public int getTn()
  {
    return this.tn;
  }
  
  public double getHorasExtraDobles()
  {
    if (Double.isNaN(this.horasExtraDobles)) {
      return 0.0D;
    }
    return this.horasExtraDobles;
  }
  
  public void setHorasExtraDobles(double horasExtraDobles)
  {
    this.horasExtraDobles = horasExtraDobles;
  }
  
  public double getHorasExtraTriples()
  {
    if (Double.isNaN(this.horasExtraTriples)) {
      return 0.0D;
    }
    return this.horasExtraTriples;
  }
  
  public void setHorasExtraTriples(double horasExtraTriples)
  {
    this.horasExtraTriples = horasExtraTriples;
  }
  
  public double getDiasPagados()
  {
    if (Double.isNaN(this.diasPagados)) {
      return 0.0D;
    }
    return this.diasPagados;
  }
  
  public void setDiasPagados(double diasPagados)
  {
    this.diasPagados = diasPagados;
  }
  
  public int getIdPeriodo()
  {
    return this.idPeriodo;
  }
  
  public void setIdPeriodo(int idPeriodo)
  {
    this.idPeriodo = idPeriodo;
  }
  
  public String getMensaje()
  {
    if (this.mensaje == null) {
      return "";
    }
    return this.mensaje.replaceAll("null", "").replace("  ", " ").trim();
  }
  
  public void setMensaje(String mensaje)
  {
    this.mensaje = mensaje;
  }
  
  public String getCalle()
  {
    if (this.calle != null) {
      return this.calle.replaceAll("null", "").replace("  ", " ").trim();
    }
    return "";
  }
  
  public void setCalle(String calle)
  {
    this.calle = calle;
  }
  
  public String getNumeroExterior()
  {
    if (this.numeroExterior != null) {
      return this.numeroExterior.replaceAll("null", "").replace("  ", " ").trim();
    }
    return "";
  }
  
  public void setNumeroExterior(String numeroExterior)
  {
    this.numeroExterior = numeroExterior;
  }
  
  public String getNumeroInterior()
  {
    if (this.numeroInterior != null) {
      return this.numeroInterior.replaceAll("null", "").replace("  ", " ").trim();
    }
    return "";
  }
  
  public void setNumeroInterior(String numeroInterior)
  {
    this.numeroInterior = numeroInterior;
  }
  
  public String getColonia()
  {
    if (this.colonia != null) {
      return this.colonia.replaceAll("null", "").replace("  ", " ").trim();
    }
    return "";
  }
  
  public void setColonia(String colonia)
  {
    this.colonia = colonia;
  }
  
  public String getDelegacionMunicipio()
  {
    if (this.delegacionMunicipio != null) {
      return this.delegacionMunicipio.replaceAll("null", "").replace("  ", " ").trim();
    }
    return "";
  }
  
  public void setDelegacionMunicipio(String delegacionMunicipio)
  {
    this.delegacionMunicipio = delegacionMunicipio;
  }
  
  public String getCodigoPostal()
  {
    if (this.codigoPostal != null) {
      return this.codigoPostal.replaceAll("null", "").replace("  ", " ").trim();
    }
    return "";
  }
  
  public void setCodigoPostal(String codigoPostal)
  {
    this.codigoPostal = codigoPostal;
  }
  
  public String getEstado()
  {
    if (this.estado != null) {
      return this.estado.replaceAll("null", "").replace("  ", " ").trim();
    }
    return "";
  }
  
  public void setEstado(String estado)
  {
    this.estado = estado;
  }
  
  public String getClaveSatBanco()
  {
    if (this.claveSatBanco != null) {
      return this.claveSatBanco;
    }
    return "";
  }
  
  public void setClaveSatBanco(String claveSatBanco)
  {
    this.claveSatBanco = claveSatBanco;
  }
  
  public String getCuentaUltimosDig()
  {
    return this.cuentaUltimosDig;
  }
  
  public void setCuentaUltimosDig(String cuentaUltimosDig)
  {
    this.cuentaUltimosDig = cuentaUltimosDig;
  }
  
  public int getDiasHorasExtra()
  {
    return this.diasHorasExtra;
  }
  
  public void setDiasHorasExtra(int diasHorasExtra)
  {
    this.diasHorasExtra = diasHorasExtra;
  }
  
  public String getMetodoPago()
  {
    if (this.metodoPago != null)
    {
      if (this.metodoPago.contains("null")) {
        return "CHEQUE";
      }
      return this.metodoPago;
    }
    return "CHEQUE";
  }
  
  public void setMetodoPago(String metodoPago)
  {
    this.metodoPago = metodoPago;
  }
  
  public double getTotalPercepciones()
  {
    if (Double.isNaN(this.totalPercepciones)) {
      return 0.0D;
    }
    return this.totalPercepciones;
  }
  
  public void setTotalPercepciones(double totalPercepciones)
  {
    this.totalPercepciones = totalPercepciones;
  }
  
  public double getTotalDescuentos()
  {
    if (Double.isNaN(this.totalDescuentos)) {
      return 0.0D;
    }
    return this.totalDescuentos;
  }
  
  public void setTotalDescuentos(double totalDescuentos)
  {
    this.totalDescuentos = totalDescuentos;
  }
  
  public double getLiquidoCobrar()
  {
    if (Double.isNaN(this.liquidoCobrar)) {
      return 0.0D;
    }
    return this.liquidoCobrar;
  }
  
  public void setLiquidoCobrar(double liquidoCobrar)
  {
    this.liquidoCobrar = liquidoCobrar;
  }
  
  public String getFechaInicioPeriodo()
  {
    return this.fechaInicioPeriodo;
  }
  
  public void setFechaInicioPeriodo(String fechaInicioPeriodo)
  {
    this.fechaInicioPeriodo = fechaInicioPeriodo;
  }
  
  public String getFechaFinPeriodo()
  {
    return this.fechaFinPeriodo;
  }
  
  public void setFechaFinPeriodo(String fechaFinPeriodo)
  {
    this.fechaFinPeriodo = fechaFinPeriodo;
  }
  
  public String getDescripcionArea()
  {
    if (this.descripcionArea != null)
    {
      if (this.descripcionArea.length() > 100) {
        return this.descripcionArea.substring(0, 100);
      }
      return this.descripcionArea;
    }
    return "";
  }
  
  public void setDescripcionArea(String descripcionArea)
  {
    this.descripcionArea = descripcionArea;
  }
  
  public double getTotalExentoPercepciones()
  {
    return this.totalExentoPercepciones;
  }
  
  public void setTotalExentoPercepciones(double totalExentoPercepciones)
  {
    this.totalExentoPercepciones = totalExentoPercepciones;
  }
  
  public double getTotalGravadoPercepciones()
  {
    return this.totalGravadoPercepciones;
  }
  
  public void setTotalGravadoPercepciones(double totalGravadoPercepciones)
  {
    this.totalGravadoPercepciones = totalGravadoPercepciones;
  }
  
  public double getTotalGravadoDeducciones()
  {
    return this.totalGravadoDeducciones;
  }
  
  public void setTotalGravadoDeducciones(double totalGravadoDeducciones)
  {
    this.totalGravadoDeducciones = totalGravadoDeducciones;
  }
  
  public double getTotalExentoDeducciones()
  {
    return this.totalExentoDeducciones;
  }
  
  public void setTotalExentoDeducciones(double totalExentoDeducciones)
  {
    this.totalExentoDeducciones = totalExentoDeducciones;
  }
  
  public Double getIsr()
  {
    Double isr = new Double(0.0D);
    if (getHmapDeducciones().containsKey("SME_ISR")) {
      isr = ((PercepcionDeduccionDto)getHmapDeducciones().get("SME_ISR")).getImporte();
    }
    return isr;
  }
  
  public Double getDescuentoLayout()
  {
    return Double.valueOf(RecibosBo.Redondear(getTotalDescuentos() - getIsr().doubleValue()));
  }
  
  public String getHorasExtraDoblesFormat()
  {
    return this.df.format(getHorasExtraDobles());
  }
  
  public String getHorasExtraTriplesFormat()
  {
    return this.df.format(getHorasExtraTriples());
  }
  
  public String getDiasPagadosFormat()
  {
    return this.df.format(getDiasPagados());
  }
  
  public String getTotalPercepcionesFormat()
  {
    return this.df.format(getTotalPercepciones());
  }
  
  public String getTotalDescuentosFormat()
  {
    return this.df.format(getTotalDescuentos());
  }
  
  public String getLiquidoCobrarFormat()
  {
    return this.df.format(getLiquidoCobrar());
  }
  
  public String getDescuentoLayoutFormat()
  {
    return this.df.format(getDescuentoLayout());
  }
  
  public String getIsrFormat()
  {
    return this.df.format(getIsr());
  }
  
  public String getTotalExentoPercepcionesFormat()
  {
    return this.df.format(getTotalExentoPercepciones());
  }
  
  public String getTotalGravadoPercepcionesFormat()
  {
    return this.df.format(getTotalGravadoPercepciones());
  }
  
  public String getTotalGravadoDeduccionesFormat()
  {
    return this.df.format(getTotalGravadoDeducciones());
  }
  
  public String getTotalExentoDeduccionesFormat()
  {
    return this.df.format(getTotalExentoDeducciones());
  }
}

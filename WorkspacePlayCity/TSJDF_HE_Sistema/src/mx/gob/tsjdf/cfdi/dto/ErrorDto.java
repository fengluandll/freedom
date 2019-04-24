package mx.gob.tsjdf.cfdi.dto;

import java.util.ArrayList;

public class ErrorDto
{
  private int numeroError;
  private String errorGral;
  private ArrayList<String> listaArchivos;
  
  public int getNumeroError()
  {
    return this.numeroError;
  }
  
  public void setNumeroError(int numeroError)
  {
    this.numeroError = numeroError;
  }
  
  public String getErrorGral()
  {
    return this.errorGral;
  }
  
  public void setErrorGral(String errorGral)
  {
    this.errorGral = errorGral;
  }
  
  public ArrayList<String> getListaArchivos()
  {
    return this.listaArchivos;
  }
  
  public void setListaArchivos(ArrayList<String> listaArchivos)
  {
    this.listaArchivos = listaArchivos;
  }
}

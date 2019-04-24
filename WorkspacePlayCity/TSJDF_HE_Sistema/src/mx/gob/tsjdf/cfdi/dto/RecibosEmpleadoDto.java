package mx.gob.tsjdf.cfdi.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class RecibosEmpleadoDto
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private ArrayList<ReciboDto> listaRecibos = new ArrayList();
  
  public int getTotalRecibos()
  {
    return this.listaRecibos.size();
  }
  
  public ArrayList<ReciboDto> getListaRecibos()
  {
    return this.listaRecibos;
  }
  
  public void setListaRecibos(ArrayList<ReciboDto> listaRecibos)
  {
    this.listaRecibos = listaRecibos;
  }
}

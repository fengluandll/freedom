package mx.gob.tsjdf.cfdi.dto;

import java.io.Serializable;

public class EmpleadoTokenDto implements Serializable{

	 
	  private static final long serialVersionUID = 1L;
	  private String numeroEmpleado;
	  private String token;
	  private String archivo;
	  
	  public String getNumeroEmpleado()
	  {
	    return this.numeroEmpleado;
	  }
	  
	  public void setNumeroEmpleado(String numeroEmpleado)
	  {
	    this.numeroEmpleado = numeroEmpleado;
	  }
	  
	  public String getToken()
	  {
	    return this.token;
	  }
	  
	  public void setToken(String token)
	  {
	    this.token = token;
	  }
	  
	  public String getArchivo()
	  {
	    return this.archivo;
	  }
	  
	  public void setArchivo(String archivo)
	  {
	    this.archivo = archivo;
	  }
}

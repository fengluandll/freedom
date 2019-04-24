package mx.gob.tsjdf.cfdi.recibos.conf;

import java.util.Properties;

public class WebServiceProperties
{
  private String uri;
  
  public String getUri()
  {
    return this.uri;
  }
  
  public void setUri(String uri)
  {
    this.uri = uri;
  }
  
  public boolean leerProperties(String ambiente)
  {
    try
    {
      Properties props = new Properties();
      props.load(WebServiceProperties.class
        .getResourceAsStream("/mx/gob/tsjdf/properties/webservice.properties"));
      if(ambiente.equals("produccion")){
    	  setUri(props.getProperty("wsUri.produccion"));
      }else if(ambiente.equals("pruebas")){
    	  setUri(props.getProperty("wsUri.pruebas"));
      }
      
      
      return true;
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return false;
  }
}

package mx.start;

public class HolaWSProxy implements mx.start.HolaWS {
  private String _endpoint = null;
  private mx.start.HolaWS holaWS = null;
  
  public HolaWSProxy() {
    _initHolaWSProxy();
  }
  
  public HolaWSProxy(String endpoint) {
    _endpoint = endpoint;
    _initHolaWSProxy();
  }
  
  private void _initHolaWSProxy() {
    try {
      holaWS = (new mx.start.HolaWSServiceLocator()).getHolaWS();
      if (holaWS != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)holaWS)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)holaWS)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (holaWS != null)
      ((javax.xml.rpc.Stub)holaWS)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public mx.start.HolaWS getHolaWS() {
    if (holaWS == null)
      _initHolaWSProxy();
    return holaWS;
  }
  
  public java.lang.String saluda(java.lang.String nombre) throws java.rmi.RemoteException{
    if (holaWS == null)
      _initHolaWSProxy();
    return holaWS.saluda(nombre);
  }
  
  
}
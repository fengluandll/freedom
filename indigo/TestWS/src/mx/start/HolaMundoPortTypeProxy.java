package mx.start;

public class HolaMundoPortTypeProxy implements mx.start.HolaMundoPortType {
  private String _endpoint = null;
  private mx.start.HolaMundoPortType holaMundoPortType = null;
  
  public HolaMundoPortTypeProxy() {
    _initHolaMundoPortTypeProxy();
  }
  
  public HolaMundoPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initHolaMundoPortTypeProxy();
  }
  
  private void _initHolaMundoPortTypeProxy() {
    try {
      holaMundoPortType = (new mx.start.HolaMundoLocator()).getHolaMundoHttpSoap11Endpoint();
      if (holaMundoPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)holaMundoPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)holaMundoPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (holaMundoPortType != null)
      ((javax.xml.rpc.Stub)holaMundoPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public mx.start.HolaMundoPortType getHolaMundoPortType() {
    if (holaMundoPortType == null)
      _initHolaMundoPortTypeProxy();
    return holaMundoPortType;
  }
  
  public java.lang.String saluda(java.lang.String nombre) throws java.rmi.RemoteException{
    if (holaMundoPortType == null)
      _initHolaMundoPortTypeProxy();
    return holaMundoPortType.saluda(nombre);
  }
  
  
}
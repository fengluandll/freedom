package mx.gob.tsjdf.cfdi.recibos.token;

public abstract class TokenServiceCallbackHandler
{
  protected Object clientData;
  
  public TokenServiceCallbackHandler(Object clientData)
  {
    this.clientData = clientData;
  }
  
  public TokenServiceCallbackHandler()
  {
    this.clientData = null;
  }
  
  public Object getClientData()
  {
    return this.clientData;
  }
  
  public void receiveResultgetTokenList(TokenServiceStub.GetTokenListResponse result) {}
  
  public void receiveErrorgetTokenList(Exception e) {}
  
  public void receiveResultgetToken(TokenServiceStub.GetTokenResponse result) {}
  
  public void receiveErrorgetToken(Exception e) {}
}

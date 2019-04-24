package mx.gob.tsjdf.cfdi.recibos.token;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.apache.axis2.AxisFault;
import org.apache.log4j.Logger;

import mx.gob.tsjdf.cfdi.bo.RecibosBo;
import mx.gob.tsjdf.cfdi.dto.SolicitudTokenDto;
import mx.gob.tsjdf.cfdi.recibos.logger.LoggerSingleton;
import mx.gob.tsjdf.cfdi.view.CfdisView;

public class Token
{
  private String wsURI;
  private int proveedorId;
  private int rfcId;
  private int servicioId;
  private int tipoDocumentoId;
  private int wsError;
  private String wsErrorMsg;
  private String wsSolicitud;
  private String[] wsToken;
  private ArrayList<SolicitudTokenDto> listaSolicitudes;
  //private Logger logger = null;
  private static org.apache.log4j.Logger logger = Logger.getLogger(Token.class);
  
  public Token()
  {
    this.listaSolicitudes = new ArrayList<>();
   // this.logger = LoggerSingleton.getInstance();
  }
  
  public ArrayList<SolicitudTokenDto> getListaSolicitudes()
  {
    return this.listaSolicitudes;
  }
  
  public void setURI(String uri)
  {
    this.wsURI = uri;
  }
  
  public void setProveedorId(int val)
  {
    this.proveedorId = val;
  }
  
  public void setRfcId(int val)
  {
    this.rfcId = val;
  }
  
  public void setServicioId(int val)
  {
    this.servicioId = val;
  }
  
  public void setTipoDocumentoId(int val)
  {
    this.tipoDocumentoId = val;
  }
  
  public int getError()
  {
    return this.wsError;
  }
  
  public String getErrorMsg()
  {
    return this.wsErrorMsg;
  }
  
  public String getSolicitud()
  {
    return this.wsSolicitud;
  }
  
  public String[] getTokens()
  {
    return this.wsToken;
  }
  
  public int callToken()
  {
    try
    {
      SolicitudTokenDto solicitudTokenDto = new SolicitudTokenDto();
      TokenServiceStub tokensvc = new TokenServiceStub(this.wsURI);
      TokenServiceStub.GetToken wstl = new TokenServiceStub.GetToken();
      TokenServiceStub.TokenData td = new TokenServiceStub.TokenData();
      td.setProveedorId(this.proveedorId);
      td.setRfcId(this.rfcId);
      td.setServicioId(this.servicioId);
      td.setTipoDocumentoId(this.tipoDocumentoId);
      wstl.setData(td);
      TokenServiceStub.GetTokenResponse wsresp = tokensvc.getToken(wstl);
      TokenServiceStub.TokenResult token = wsresp.getGetTokenResult();
      solicitudTokenDto.setFechaHrSolicitud(RecibosBo.getFecha(new Date()));
      this.wsError = token.getError();
      this.wsErrorMsg = token.getErrorMessage();
      this.wsSolicitud = token.getSolicitud();
      this.wsToken = token.getToken().getString();
      solicitudTokenDto.setSolicitud(token.getSolicitud());
      solicitudTokenDto.setNumeroTokens(1);
      this.listaSolicitudes.add(solicitudTokenDto);
      return 0;
    }
    catch (AxisFault e)
    {
      return -100;
    }
    catch (RemoteException e) {}
    return -101;
  }
  
  private int callTokenListSvc(int numTokens)
  {
    try
    {
      SolicitudTokenDto solicitudTokenDto = new SolicitudTokenDto();
      TokenServiceStub tokensvc = new TokenServiceStub(this.wsURI);
      long timeout = 120000L;
      tokensvc._getServiceClient().getOptions().setTimeOutInMilliSeconds(timeout);
      
      solicitudTokenDto.setFechaHrSolicitud(RecibosBo.getFecha(new Date()));
      TokenServiceStub.GetTokenList wstl = new TokenServiceStub.GetTokenList();
      TokenServiceStub.TokenData td = new TokenServiceStub.TokenData();
      td.setProveedorId(this.proveedorId);
      td.setRfcId(this.rfcId);
      td.setServicioId(this.servicioId);
      td.setTipoDocumentoId(this.tipoDocumentoId);
      wstl.setData(td);
      wstl.setTotalToken(numTokens);
      TokenServiceStub.GetTokenListResponse wsresp = tokensvc.getTokenList(wstl);
      TokenServiceStub.TokenResult token = wsresp.getGetTokenListResult();
      this.wsError = token.getError();
      this.wsErrorMsg = token.getErrorMessage();
      this.wsSolicitud = token.getSolicitud();
      this.wsToken = token.getToken().getString();
      this.logger.info("Tokens solicitados= " + numTokens);
      this.logger.info("Recibo de la Solicitud = " + token.getSolicitud());
      this.logger.info("Total de tokens por solicitud = " + token.getToken().getString().length);
      solicitudTokenDto.setSolicitud(token.getSolicitud());
      solicitudTokenDto.setNumeroTokens(token.getToken().getString().length);
      this.listaSolicitudes.add(solicitudTokenDto);
      return 0;
    }
    catch (AxisFault e)
    {
      return -100;
    }
    catch (RemoteException e) {}
    return -101;
  }
  
  private String[] concat(String[] struno, String[] strdos)
  {
    ArrayList<String> temp = new ArrayList();
    temp.addAll(Arrays.asList(struno));
    temp.addAll(Arrays.asList(strdos));
    return (String[])temp.toArray(new String[struno.length + strdos.length]);
  }
  
  public int callTokenList(int numTokens)
  {
    if (numTokens < 1000) {
      return callTokenListSvc(numTokens);
    }
    int i = 0;
    String[] temp = new String[0];
    String tmpsol = "";
    int nt = numTokens;
    int st = 0;
    while (i < numTokens)
    {
      if (nt / 1000 < 1) {
        st = nt;
      } else {
        st = 1000;
      }
      int error = callTokenListSvc(st);
      if (error != 0) {
        return error;
      }
      temp = concat(this.wsToken, temp);
      nt -= st;
      i += st;
      tmpsol = tmpsol + this.wsSolicitud + ",";
    }
    this.wsToken = temp;
    this.wsSolicitud = tmpsol;
    return 0;
  }
}

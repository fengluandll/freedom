package mx.gob.tsjdf.cfdi.service;

import java.io.File;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import mx.gob.tsjdf.cfdi.dto.EmpleadoTokenDto;
import mx.gob.tsjdf.cfdi.dto.SolicitudTokenDto;
import mx.gob.tsjdf.cfdi.recibos.conf.WebServiceProperties;
import mx.gob.tsjdf.cfdi.recibos.token.Token;
import mx.gob.tsjdf.util.Funciones;

public class RecibosConTokenService
{
	//private Logger logger = null;
    private ArrayList<SolicitudTokenDto> listaSolicitudes = null;
    private static org.apache.log4j.Logger logger = Logger.getLogger(RecibosConTokenService.class);
    
  Funciones funciones;
  
  public RecibosConTokenService()
  {
	  funciones = new Funciones();
	  //this.logger = LoggerSingleton.getInstance();
  }
  
  public ArrayList<EmpleadoTokenDto> crearTxtConToken(String fechaEjecucionTsjdf, String dirTxt, String dirToken,String ambienteWebServices)
  {
    ArrayList<EmpleadoTokenDto> listaETA = new ArrayList<>();
    ArchivoService bo = new ArchivoService();
    Token tokenBo = new Token();
    try
    {
      WebServiceProperties ws = new WebServiceProperties();
      if (ws.leerProperties(ambienteWebServices))
      {
        tokenBo.setURI(ws.getUri());
        tokenBo.setProveedorId(3);
        tokenBo.setRfcId(1);
        tokenBo.setServicioId(1);
        tokenBo.setTipoDocumentoId(1);
        

        File[] archivosTxt = bo.getListaTxts(dirTxt);
        int totalArchivos = archivosTxt.length;
        
        logger.info("URI del webservice: " + ws.getUri());
        logger.info("Total de archivos leidos " + totalArchivos + " en : " + dirTxt);
        funciones.variableLog.append("URI del webservice: " + ws.getUri()+"\n");
        funciones.variableLog.append("Total de archivos leidos " + totalArchivos + " en : " + dirTxt+"\n");
        if (totalArchivos > 0) {
          if (totalArchivos > 1)
          {
            logger.info("Inicia la generacion de archivos con token asignado");
            funciones.variableLog.append("Inicia la generacion de archivos con token asignado"+"\n");
            if (tokenBo.callTokenList(totalArchivos) == 0)
            {
              logger.info("Error --> " + tokenBo.getError());
              logger.info("ErrorMsg --> " + tokenBo.getErrorMsg());
              logger.info("Recibos de la Solicitud --> " + tokenBo.getSolicitud());
             
              //Funciones.updateComponent("frmFile:gridFile1");
              String fechaNombreArchivo = fechaEjecucionTsjdf.replace("-", "").replace(":", "");
              if (tokenBo.getError() == 0)
              {
                String[] asl = tokenBo.getTokens();
                logger.info("Total de tokens solicitados --> " + totalArchivos);
                logger.info("Total de tokens entregados --> " + asl.length);
                //funciones.variableLog.append("Total de tokens solicitados --> " + totalArchivos+"\n");
                //funciones.variableLog.append("Total de tokens entregados --> " + asl.length+"\n");
                for (int i = 0; i < asl.length; i++)
                {
                  logger.info("Token " + i + " --> " + asl[i]);
                  funciones.variableLog.append("Token " + i + " --> " + asl[i]+"\n");
                  listaETA.add(bo.recrearConToken(archivosTxt[i].getName(), asl[i], fechaNombreArchivo, dirTxt, dirToken));
                }
                listaSolicitudes = tokenBo.getListaSolicitudes();
              }
            }
            else
            {
              logger.info("Error al invocar el webservice " + ws.getUri());
              logger.info("Error --> " + tokenBo.getError());
              Funciones.mostrarMensaje("Error al invocar el webservice " + ws.getUri(),"Error" , "ERROR");
              //Funciones.updateComponent("frmFile:gridFile1");
            }
          }
          else if (tokenBo.callToken() == 0)
          {
            logger.info("Error --> "    + tokenBo.getError());
            logger.info("ErrorMsg --> " + tokenBo.getErrorMsg());
            funciones.variableLog.append("Error --> "    + tokenBo.getError()+"\n");
            funciones.variableLog.append("ErrorMsg --> " + tokenBo.getErrorMsg()+"\n");
            if (tokenBo.getError() == 0)
            {
              logger.info("Solicitud --> " + tokenBo.getSolicitud());
              funciones.variableLog.append("Solicitud --> " + tokenBo.getSolicitud()+"\n");
              String[] as = tokenBo.getTokens();
              
              logger.info("Total de tokens solicitados --> " + totalArchivos);
              logger.info("Total de tokens entregados --> " + as.length);              
              logger.info("Token --> " + as[0]);
              
              funciones.variableLog.append("Total de tokens solicitados --> " + totalArchivos+"\n");
              funciones.variableLog.append("Total de tokens entregados --> " + as.length+"\n");
              funciones.variableLog.append("Token --> " + as[0]+"\n");
              String fechaNombreArchivo = fechaEjecucionTsjdf.replace("-", "").replace(":", "");
              listaETA.add(bo.recrearConToken(archivosTxt[0].getName(), as[0], fechaNombreArchivo, dirTxt, dirToken));
              this.listaSolicitudes = tokenBo.getListaSolicitudes();
            }
          }
          else
          {
            logger.info("Error al invocar el webservice");
            logger.info("Error --> " + tokenBo.getError());
            funciones.variableLog.append("Error al invocar el webservice"+"\n");
            funciones.variableLog.append("Error --> " + tokenBo.getError()+"\n");
          }
        }
      }
    }
    catch (NullPointerException e)
    {
      logger.info("Informacion para el ususario:  No existen archivos TXT");
      funciones.variableLog.append("Informacion para el ususario:  No existen archivos TXT"+"\n");
      e.printStackTrace();
    }
    return listaETA;
  }
  
  public ArrayList<SolicitudTokenDto> getListaSolicitudes()
  {
    return this.listaSolicitudes;
  }
}

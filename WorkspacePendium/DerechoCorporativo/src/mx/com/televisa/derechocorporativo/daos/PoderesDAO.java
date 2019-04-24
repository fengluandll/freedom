package mx.com.televisa.derechocorporativo.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mx.com.televisa.derechocorporativo.bean.EmpresasBean;
import mx.com.televisa.derechocorporativo.bean.PoderesBean;
import mx.com.televisa.derechocorporativo.bean.RolBean;
import mx.com.televisa.derechocorporativo.bean.UserBean;
import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;
import mx.com.televisa.derechocorporativo.util.FacesUtils;


public class PoderesDAO {	
	
	 ConnectionWrapper 	puConnectionWrapper;
	 PoderesBean        tuPoderesBean;
	 List<PoderesBean>  poderesBean;
	 Connection 		puConnection;
 
	 /**
	  * Insertar Poderes
	  * @param tuApoderadosBean
	  * @param taParametros
	  * @return boolean
	  */
      @SuppressWarnings("finally")
	public boolean insertaPoderes(PoderesBean tuPoderesBean){ 
    	  boolean           lbInserto            = false;
		  String            lsSql                = "";
	      Connection 		luConection          = null;
	      CallableStatement luCallableStatement  = null;
	      String            lsError              = null;

	        try {
	        	lsSql = "CALL DERCORP_PODERES_PKG.INSERT_PODERES_PR(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	            puConnectionWrapper = new ConnectionWrapper();
	            luConection = puConnectionWrapper.getConnection();			
	            luCallableStatement = luConection.prepareCall(lsSql);
	            luCallableStatement.setInt   (1, tuPoderesBean.getIdPoder());
	            luCallableStatement.setInt   (2, tuPoderesBean.getIdEmpresa());	            
	            luCallableStatement.setString(3, tuPoderesBean.getIntTipoPoder());
	            luCallableStatement.setInt   (4, tuPoderesBean.getIndDelegadoPor());
	            luCallableStatement.setString(5, tuPoderesBean.getFecFecha());
	            luCallableStatement.setString(6, tuPoderesBean.getFecHora());
	            luCallableStatement.setString(7, tuPoderesBean.getIndTipoDocumento());
	            luCallableStatement.setString(8, tuPoderesBean.getDescEscritura());
	            luCallableStatement.setString(9, tuPoderesBean.getFecOtorgamientoInstr());	
	            luCallableStatement.setString(10, tuPoderesBean.getNumDocumentumInstr());
	            luCallableStatement.setString(11, tuPoderesBean.getIndRequiereProto());
	            luCallableStatement.setString(12, tuPoderesBean.getIndRequiereInscrRppc());
	            luCallableStatement.setString(13, tuPoderesBean.getNomSemaforo());
	            luCallableStatement.setInt   (14, tuPoderesBean.getNumLicenciado());
	            luCallableStatement.setString(15, tuPoderesBean.getNomNotarioPublico());
	            luCallableStatement.setInt   (16, tuPoderesBean.getNumDe());
	            luCallableStatement.setString(17, tuPoderesBean.getDesSuplenciaAsociado());
	            luCallableStatement.setString(18, tuPoderesBean.getNumInscritaRegistroPublico());
	            luCallableStatement.setString(19, tuPoderesBean.getFecRegistro());
	            luCallableStatement.setString(20, tuPoderesBean.getNumFolioMec());
	            luCallableStatement.setString(21, tuPoderesBean.getDesOtrosDatosRegistro());
	            luCallableStatement.setString(22, tuPoderesBean.getIndMemo());
	            luCallableStatement.setInt   (23, tuPoderesBean.getNumSolicitadoPor());
	            luCallableStatement.setString(24, tuPoderesBean.getFecDocumentoMemo());
	            luCallableStatement.setString(25, tuPoderesBean.getFecRecibidoMemo());
	            luCallableStatement.setString(26, tuPoderesBean.getNumFolio());
	            luCallableStatement.setString(27, tuPoderesBean.getNumDocumentoMemo());
	            luCallableStatement.setString(28, tuPoderesBean.getIndDocEntrega());
	            luCallableStatement.setString(29, tuPoderesBean.getFecDocumentoEntrega());
	            luCallableStatement.setString(30, tuPoderesBean.getFecRecibidaEntrega());
	            luCallableStatement.setString(31, tuPoderesBean.getNumDocumentumEntrega());
	            luCallableStatement.setString(32, tuPoderesBean.getIndOtros());
	            luCallableStatement.setString(33, tuPoderesBean.getFecDocumentoOtros());
	            luCallableStatement.setString(34, tuPoderesBean.getFecRecibidoOtros()); 
	            luCallableStatement.setString(35, tuPoderesBean.getIndAplicaEstatus());
	            luCallableStatement.setString(36, tuPoderesBean.getNumDocumentumOtros());
	            luCallableStatement.setString(37, tuPoderesBean.getNomSemaforoEstatus());
	            luCallableStatement.setString(38, tuPoderesBean.getFecProgEntregaEstatus()); 
	            luCallableStatement.setString(39, tuPoderesBean.getIndRedactada()); 
	            luCallableStatement.setInt   (40, tuPoderesBean.getNumRespRedactada()); 
	            luCallableStatement.setString(41, tuPoderesBean.getFecCumplimientoRedactada()); 
	            luCallableStatement.setString(42, tuPoderesBean.getIndRevisionGerente()); 
	            luCallableStatement.setInt   (43, tuPoderesBean.getNumRespGerente()); 
	            luCallableStatement.setString(44, tuPoderesBean.getFecCumplimientoGerente());             
	            luCallableStatement.setString(45, tuPoderesBean.getIndCorrecciones());
	            luCallableStatement.setInt   (46, tuPoderesBean.getNumRespCorrecciones());
	            luCallableStatement.setString(47, tuPoderesBean.getFecCumplimientoCorrecciones());
	            luCallableStatement.setString(48, tuPoderesBean.getIndAutDireccion());
	            luCallableStatement.setInt   (49, tuPoderesBean.getNumRespAut());
	            luCallableStatement.setString(50, tuPoderesBean.getFecCumplimientoAut());
	            luCallableStatement.setString(51, tuPoderesBean.getIndFirmas());
	            luCallableStatement.setInt   (52, tuPoderesBean.getNumRespFirmas());
	            luCallableStatement.setString(53, tuPoderesBean.getFecCumplimientoFirmas());
	            luCallableStatement.setString(54, tuPoderesBean.getIndEntregada());
	            luCallableStatement.setInt   (55, tuPoderesBean.getNumRespEntregada());
	            luCallableStatement.setString(56, tuPoderesBean.getFecCumplimientoEntregada());
	            luCallableStatement.setString(57, tuPoderesBean.getNumEnviadaNotaria());
	            luCallableStatement.setString(58, tuPoderesBean.getFecEnvioNotaria());
	            luCallableStatement.setString(59, tuPoderesBean.getIndPoderAsunto());
	            luCallableStatement.setString(60, tuPoderesBean.getIndTipoArmado());	            
	            luCallableStatement.registerOutParameter(61, java.sql.Types.VARCHAR);

	            luCallableStatement.execute();
	            lsError = luCallableStatement.getString(61);
	            System.out.println(lsError);
	            if(lsError == null){
	            	lbInserto = true;
	            }

	        } catch (Exception e) {
	            e.printStackTrace();

	        }finally {
	        	try {
	           luConection.close();		
	           puConnectionWrapper.close();
	        	} catch (SQLException e) {
	                e.getMessage();
	            }
			return lbInserto;
	    }
	      
  
   }
	        
	        
	     /**
		  * Actualiza Poderes
		  * @param tuApoderadosBean
		  * @param taParametros
		  * @return boolean
		  */
	     
		public String actualizaPoderes(PoderesBean tuPoderesBean){ 
			  String            lsMensaje           = null;
			  String            lsSql                = "";
		      Connection 		luConection          = null;
		      CallableStatement luCallableStatement  = null;
		      String            lsError              = null;

		        try {
		        	lsSql = "CALL DERCORP_PANEL_CONTROL_PKG.UPDATE_PODERES_PR(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		        	puConnectionWrapper = new ConnectionWrapper();
		            luConection = puConnectionWrapper.getConnection();			
		            luCallableStatement = luConection.prepareCall(lsSql);
		            luCallableStatement.setInt   (1, tuPoderesBean.getIdPoder());
		            luCallableStatement.setInt   (2, tuPoderesBean.getIdEmpresa());	            
		            luCallableStatement.setString(3, tuPoderesBean.getIntTipoPoder());
		            luCallableStatement.setInt   (4, tuPoderesBean.getIndDelegadoPor());
		            luCallableStatement.setString(5, tuPoderesBean.getFecFecha());
		            luCallableStatement.setString(6, tuPoderesBean.getFecHora());
		            luCallableStatement.setString(7, tuPoderesBean.getIndTipoDocumento());
		            luCallableStatement.setString(8, tuPoderesBean.getDescEscritura());
		            luCallableStatement.setString(9, tuPoderesBean.getFecOtorgamientoInstr());	
		            luCallableStatement.setString(10, tuPoderesBean.getNumDocumentumInstr());
		            luCallableStatement.setString(11, tuPoderesBean.getIndRequiereProto());
		            luCallableStatement.setString(12, tuPoderesBean.getIndRequiereInscrRppc());
		            luCallableStatement.setString(13, tuPoderesBean.getNomSemaforo());
		            luCallableStatement.setInt   (14, tuPoderesBean.getNumLicenciado());
		            luCallableStatement.setString(15, tuPoderesBean.getNomNotarioPublico());
		            luCallableStatement.setInt   (16, tuPoderesBean.getNumDe());
		            luCallableStatement.setString(17, tuPoderesBean.getDesSuplenciaAsociado());
		            luCallableStatement.setString(18, tuPoderesBean.getNumInscritaRegistroPublico());
		            luCallableStatement.setString(19, tuPoderesBean.getFecRegistro());
		            luCallableStatement.setString(20, tuPoderesBean.getNumFolioMec());
		            luCallableStatement.setString(21, tuPoderesBean.getDesOtrosDatosRegistro());
		            luCallableStatement.setString(22, tuPoderesBean.getIndMemo());
		            luCallableStatement.setInt   (23, tuPoderesBean.getNumSolicitadoPor());
		            luCallableStatement.setString(24, tuPoderesBean.getFecDocumentoMemo());
		            luCallableStatement.setString(25, tuPoderesBean.getFecRecibidoMemo());
		            luCallableStatement.setString(26, tuPoderesBean.getNumFolio());
		            luCallableStatement.setString(27, tuPoderesBean.getNumDocumentoMemo());
		            luCallableStatement.setString(28, tuPoderesBean.getIndDocEntrega());
		            luCallableStatement.setString(29, tuPoderesBean.getFecDocumentoEntrega());
		            luCallableStatement.setString(30, tuPoderesBean.getFecRecibidaEntrega());
		            luCallableStatement.setString(31, tuPoderesBean.getNumDocumentumEntrega());
		            luCallableStatement.setString(32, tuPoderesBean.getIndOtros());
		            luCallableStatement.setString(33, tuPoderesBean.getFecDocumentoOtros());
		            luCallableStatement.setString(34, tuPoderesBean.getFecRecibidoOtros()); 
		            luCallableStatement.setString(35, tuPoderesBean.getIndAplicaEstatus());
		            luCallableStatement.setString(36, tuPoderesBean.getNumDocumentumOtros());
		            luCallableStatement.setString(37, tuPoderesBean.getNomSemaforoEstatus());
		            luCallableStatement.setString(38, tuPoderesBean.getFecProgEntregaEstatus()); 
		            luCallableStatement.setString(39, tuPoderesBean.getIndRedactada()); 
		            luCallableStatement.setInt   (40, tuPoderesBean.getNumRespRedactada()); 
		            luCallableStatement.setString(41, tuPoderesBean.getFecCumplimientoRedactada()); 
		            luCallableStatement.setString(42, tuPoderesBean.getIndRevisionGerente()); 
		            luCallableStatement.setInt   (43, tuPoderesBean.getNumRespGerente()); 
		            luCallableStatement.setString(44, tuPoderesBean.getFecCumplimientoGerente());             
		            luCallableStatement.setString(45, tuPoderesBean.getIndCorrecciones());
		            luCallableStatement.setInt   (46, tuPoderesBean.getNumRespCorrecciones());
		            luCallableStatement.setString(47, tuPoderesBean.getFecCumplimientoCorrecciones());
		            luCallableStatement.setString(48, tuPoderesBean.getIndAutDireccion());
		            luCallableStatement.setInt   (49, tuPoderesBean.getNumRespAut());
		            luCallableStatement.setString(50, tuPoderesBean.getFecCumplimientoAut());
		            luCallableStatement.setString(51, tuPoderesBean.getIndFirmas());
		            luCallableStatement.setInt   (52, tuPoderesBean.getNumRespFirmas());
		            luCallableStatement.setString(53, tuPoderesBean.getFecCumplimientoFirmas());
		            luCallableStatement.setString(54, tuPoderesBean.getIndEntregada());
		            luCallableStatement.setInt   (55, tuPoderesBean.getNumRespEntregada());
		            luCallableStatement.setString(56, tuPoderesBean.getFecCumplimientoEntregada());
		            luCallableStatement.setString(57, tuPoderesBean.getNumEnviadaNotaria());
		            luCallableStatement.setString(58, tuPoderesBean.getFecEnvioNotaria());
		            luCallableStatement.setString(59, tuPoderesBean.getIndPoderAsunto());
		            luCallableStatement.setString(60, tuPoderesBean.getIndTipoArmado());	  
		            luCallableStatement.registerOutParameter(61, java.sql.Types.VARCHAR);
		            luCallableStatement.execute();
		            lsError = luCallableStatement.getString(61);
		            System.out.println(lsError);
		           

		        } catch (Exception e) {
		            e.printStackTrace();

		        }finally {
		        	try {
		           luConection.close();		
		           puConnectionWrapper.close();
		        	} catch (SQLException e) {
		                e.getMessage();
		            }
				
		    }
		        return lsMensaje;
	  }

      
	        /**
	   	  * Elimina Poderes
	   	  * @param tuApoderadosBean
	   	  * @param taParametros
	   	  * @return boolean
	   	  */
	        
	   	public boolean DeletePoderes(int idPoder,int idEmpresa){ 
	   		 boolean            lbInserto            = false;
	   	     String             lsSql                = "";
	   	     Connection 		luConection          = null;
	         CallableStatement  luCallableStatement  = null;
	         String             lsError              = null;
			       	  
	   	        try {
	        	lsSql = "CALL DERCORP_PODERES_PKG.DELETE_PODERES_PR(?,?,?)";
	            puConnectionWrapper = new ConnectionWrapper();
	            luConection = puConnectionWrapper.getConnection();			
	            luCallableStatement = luConection.prepareCall(lsSql);
	            luCallableStatement.setInt   (1, idPoder);
	            luCallableStatement.setInt   (2, idEmpresa);	   
	            luCallableStatement.registerOutParameter(3, java.sql.Types.VARCHAR);

	            luCallableStatement.execute();
	            lsError = luCallableStatement.getString(3);
	            System.out.println(lsError);
	            if(lsError == null){
	            	lbInserto = true;
	            }

	        } catch (Exception e) {
	            e.printStackTrace();

	        }finally {
	        	try {
	           luConection.close();		
	           puConnectionWrapper.close();
	        	} catch (SQLException e) {
	                e.getMessage();
	            }
			
	    }

	   	     return lbInserto;	        
}
	   	   
		
	   	public PoderesBean dameDatosPoderes(int idPoder, int idEmpresa){
	        String            lsSql               = "";
	        PreparedStatement luPreparedStatement = null;
	        ResultSet 		  luResultSet         = null;

	        try {
	            lsSql =  "SELECT ID_PODER,\n"+ " ID_EMPRESA,\n"+ " IN_TIPO_PODER,\n"+ " IND_DELEGADO_POR,\n"+
	                    "  to_char(FEC_FECHA,'DD/MM/YYYY')as fec_fecha,\n"+ " FEC_HORA,\n"+ " IND_TIPO_DOCUMENTO,\n"+ " DES_ESCRITURA,\n"+
	            		"  FEC_OTORGAMIENTO_INSTR,\n"+ "  NUM_DOCUMENTUM_INSTR,\n"+ " IND_REQUIERE_PROTO,\n"+ " IND_REQUIERE_INSCR_RPPC,\n"+ " NOM_SEMAFORO,\n"+ " NUM_LICENCIADO,\n"+ 
	                    "  NOM_NOTARIO_PUBLICO,\n"+ " NUM_DE,\n"+ " DES_SUPLENCIA_ASOCIADO,\n"+ " NUM_INSCRITA_REGISTRO_PUBLICO,\n"+ " FEC_REGISTRO,\n"+ 
	                    "  NUM_FOLIO_MERC,\n"+ " DES_OTROS_DATOS_REGISTRO,\n"+ " IND_MEMO,\n"+ " NUM_SOLICITADO_POR,\n"+ " FEC_DOCUMENTO_MEMO,\n"+ " FEC_RECIBIDO_MEMO,\n"+ 
	                    "  NUM_FOLIO,\n"+ " NUM_DOCUMENTUM_MEMO,\n"+ " IND_DOC_ENTREGA,\n"+ " FEC_DOCUMENTO_ENTREGA,\n"+ " FEC_RECIBIDO_ENTREGA,\n"+ " NUM_DOCUMENTUM_ENTREGA,\n"+ 
	                    "  IND_OTROS,\n"+ " FEC_DOCUMENTO_OTROS,\n"+ " FEC_RECIBIDO_OTROS,\n"+ " NUM_DOCUMENTUM_OTROS,\n"+ " IND_APLICA_STATUS,\n"+ " NOM_SEMAFORO_STATUS,\n"+
	                    "  FEC_PROG_ENTREGA_STATUS,\n"+ " IND_REDACTADA,\n"+ " NUM_RESP_REDACTADA,\n"+ " FEC_CUMPLIMIENTO_REDACTADA,\n"+ " IND_REVISION_GERENTE,\n"+
	                    "  NUM_RESP_GERENTE,\n"+ " FEC_CUMPLIMIENTO_GERENTE,\n"+ " IND_CORRECCIONES,\n"+ " NUM_RESP_CORRECCIONES,\n"+ " FEC_CUMPLIMIENTO_CORRECCIONES,\n"+
	                    "  IND_AUT_DIRECCION,\n"+ " NUM_RESP_AUT,\n"+ " FEC_CUMPLIMIENTO_AUT,\n"+" IND_FIRMAS,\n"+ " NUM_RESP_FIRMAS,\n"+ " FEC_CUMPLIMIENTO_FIRMAS,\n"+
	                    "  IND_ENTREGADA,\n"+ " NUM_RESP_ENTREGADA,\n"+ " FEC_CUMPLIMIENTO_ENTREGADA,\n"+ "  NUM_ENVIADA_NOTARIA,\n"+ " FEC_ENVIO_NOTARIA,\n"+ " IND_PODER_ASUNTO,\n"+
	                    "  IND_TIPO_ARMADO \n"+ 
	                    " FROM DERCORP_PODERES_TAB\n"+
	                    " WHERE ID_PODER = ?" + 
	                    " AND ID_EMPRESA = ?";

	            puConnectionWrapper = new ConnectionWrapper();
	            luPreparedStatement = puConnectionWrapper.prepareStatement(lsSql);
	            luPreparedStatement.setInt(1, idPoder);
	            luPreparedStatement.setInt(2, idEmpresa);
	            luResultSet = luPreparedStatement.executeQuery();

	            while(luResultSet.next()){
	            	tuPoderesBean = new PoderesBean();
	                tuPoderesBean.setIdPoder(luResultSet.getInt(1));
	                tuPoderesBean.setIdEmpresa(luResultSet.getInt(2));
	                tuPoderesBean.setIntTipoPoder(luResultSet.getString(3));	            
	                tuPoderesBean.setIndDelegadoPor(luResultSet.getInt(4));
	                tuPoderesBean.setFecFecha(luResultSet.getString(5));
	                tuPoderesBean.setFecHora(luResultSet.getString(6));
	                tuPoderesBean.setIndTipoDocumento(luResultSet.getString(7));
	                tuPoderesBean.setDescEscritura(luResultSet.getString(8));
	                tuPoderesBean.setFecOtorgamientoInstr(luResultSet.getString(9));
	                tuPoderesBean.setNumDocumentumInstr(luResultSet.getString(10));	
	                tuPoderesBean.setIndRequiereProto(luResultSet.getString(11));
	                tuPoderesBean.setIndRequiereInscrRppc(luResultSet.getString(12));
	                tuPoderesBean.setNomSemaforo(luResultSet.getString(13));
	                tuPoderesBean.setNumLicenciado(luResultSet.getInt(14));
	                tuPoderesBean.setNomNotarioPublico(luResultSet.getString(15));
	                tuPoderesBean.setNumDe(luResultSet.getInt(16));
	                tuPoderesBean.setDesSuplenciaAsociado(luResultSet.getString(17));
	                tuPoderesBean.setNumInscritaRegistroPublico(luResultSet.getString(18));
	                tuPoderesBean.setFecRegistro(luResultSet.getString(19));
	                tuPoderesBean.setNumFolioMec(luResultSet.getString(20));
	                tuPoderesBean.setDesOtrosDatosRegistro(luResultSet.getString(21));
	                tuPoderesBean.setIndMemo(luResultSet.getString(22));
	                tuPoderesBean.setNumSolicitadoPor(luResultSet.getInt(23));
	                tuPoderesBean.setFecDocumentoMemo(luResultSet.getString(24));
	                tuPoderesBean.setFecRecibidoMemo(luResultSet.getString(25));
	                tuPoderesBean.setNumFolio(luResultSet.getString(26));
	                tuPoderesBean.setNumDocumentoMemo(luResultSet.getString(27));
	                tuPoderesBean.setIndDocEntrega(luResultSet.getString(28));
	                tuPoderesBean.setFecDocumentoEntrega(luResultSet.getString(29));
	                tuPoderesBean.setFecRecibidaEntrega(luResultSet.getString(30));
	                tuPoderesBean.setNumDocumentumEntrega(luResultSet.getString(31));
	                tuPoderesBean.setIndOtros(luResultSet.getString(32));
	                tuPoderesBean.setFecDocumentoOtros(luResultSet.getString(33));
	                tuPoderesBean.setFecRecibidoOtros(luResultSet.getString(34));
	                tuPoderesBean.setNumDocumentumOtros(luResultSet.getString(35)); 
	                tuPoderesBean.setIndAplicaEstatus(luResultSet.getString(36));
	                tuPoderesBean.setNomSemaforoEstatus(luResultSet.getString(37));
	                tuPoderesBean.setFecProgEntregaEstatus(luResultSet.getString(38));
	                tuPoderesBean.setIndRedactada(luResultSet.getString(39)); 
	                tuPoderesBean.setNumRespRedactada(luResultSet.getInt(40)); 
	                tuPoderesBean.setFecCumplimientoRedactada(luResultSet.getString(41)); 
	                tuPoderesBean.setIndRevisionGerente(luResultSet.getString(42)); 
	                tuPoderesBean.setNumRespGerente(luResultSet.getInt(43)); 
	                tuPoderesBean.setFecCumplimientoGerente(luResultSet.getString(44)); 
	                tuPoderesBean.setIndCorrecciones(luResultSet.getString(45));             
	                tuPoderesBean.setNumRespCorrecciones(luResultSet.getInt(46));
	                tuPoderesBean.setFecCumplimientoCorrecciones(luResultSet.getString(47));
	                tuPoderesBean.setIndAutDireccion(luResultSet.getString(48));
	                tuPoderesBean.setNumRespAut(luResultSet.getInt(49));
	                tuPoderesBean.setFecCumplimientoAut(luResultSet.getString(50));
	                tuPoderesBean.setIndFirmas(luResultSet.getString(51));
	                tuPoderesBean.setNumRespFirmas(luResultSet.getInt(52));
	                tuPoderesBean.setFecCumplimientoFirmas(luResultSet.getString(53));
	                tuPoderesBean.setIndEntregada(luResultSet.getString(54));
	                tuPoderesBean.setNumRespEntregada(luResultSet.getInt(55));
	                tuPoderesBean.setFecCumplimientoEntregada(luResultSet.getString(56));
	                tuPoderesBean.setNumEnviadaNotaria(luResultSet.getString(57));
	                tuPoderesBean.setFecEnvioNotaria(luResultSet.getString(58));
	                tuPoderesBean.setIndPoderAsunto(luResultSet.getString(59));
	                tuPoderesBean.setIndTipoArmado(luResultSet.getString(60));
	                
				}
	             luResultSet.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }finally {
	            try {
	                luPreparedStatement.close();
	                
	                puConnectionWrapper.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }


	        return tuPoderesBean;

	    }
	   	
	   	public String allowToOpenForm(int idPoder, int idEmpresa,int idUser) {
	        ConnectionWrapper conn = null;
	        Connection con			= null;
	        ResultSet set = null;
	        PreparedStatement psmt = null;
	        PreparedStatement psmt2 = null;
	        ResultSet set2 = null;
	        PreparedStatement psmt3 = null;
	        
	        try {
	              
	              conn = new ConnectionWrapper();
	              	            
	              if(idPoder > 0) {

	                    String sqlCampos = "SELECT count(*) ROW_COUNT FROM DERCORP_CONTROL_PODERES_ROW"
	                                                 + " WHERE ID_EMPRESA = " + idEmpresa + " "
	                                                 + " AND ID_USER = " + idUser;


	                    psmt = conn.prepareStatement(sqlCampos);
	                    set = psmt.executeQuery();   

	                    set.next();
	                    
	                    int rowCount = set.getInt("ROW_COUNT");
	                    
	                    if(rowCount == 0) {
	                    	con = conn.getConnection();
	                    	 CallableStatement cstmt = con.prepareCall("CALL DERCORP_PODERES_PKG.INSERT_CONTROL_PODERES_PR(?,?,?,?)");
	                    	 cstmt.setInt(1, idPoder);
	                    	 cstmt.setInt(2, idEmpresa);
	                    	 cstmt.setInt(3, idUser);
	                    	 cstmt.registerOutParameter(4, java.sql.Types.VARCHAR);
	                    	cstmt.execute();
	                    	System.err.println("Error: "+cstmt.getString(4));
	                    	return "OK";
	                    	
	                    } else {
	                    	String sqlQuery = "SELECT ID_USER FROM DERCORP_CONTROL_PODERES_ROW"
	                    						+ " WHERE ID_EMPRESA = " + idEmpresa + " "
	                                            + "AND ID_PODER = " + idPoder;
	                    	psmt3 = conn.prepareStatement(sqlQuery);
	                    	set2 = psmt3.executeQuery();
	                    	int idUserOcupaReforma=0;
	                    	while(set2.next()){
	                    		idUserOcupaReforma = set2.getInt(1);
	                    	}
	                    	
	                    	return "NOK,"+idUserOcupaReforma;
	                    }
	                    
	                    
	                    
	              } else {

	            	  return "OK";
	              }

	        } catch(Exception ex) {
	        	ex.printStackTrace();
	        	return "NOK-" + ex.toString();
	        } finally {
	        	
	        	ConnectionWrapper.closeAll(set, psmt, psmt2, conn);
	        }
		}


			         

}
      


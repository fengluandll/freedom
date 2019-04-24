/**
* Project: Derecho Corporativo
*
* File: ApoderadosDAO.java
*
* Created on: Agosto 31, 2015 at 12:00
*
* Copyright (c) - Televisa - 2015
*/

package mx.com.televisa.derechocorporativo.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.sf.jasperreports.engine.export.draw.TextDrawer;
import mx.com.televisa.derechocorporativo.bean.ApoderadosBean;
import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;
import mx.com.televisa.derechocorporativo.util.TextFormatter;

/**
 * Transacciones para la Seccion de Apoderados 
 *
 * @author KAZ - CONSULTING/
 *         Iván Castañeda Loeza
 *         Jesús Argumedo
 *         Eduardo Nava
 *         Ernesto Corona Mendoza
 *
 *
 *
 * @version 1.0.0
 *
 * @date Agosto 31, 2015 at 12:00 pm
 *
 */
public class ApoderadosDAO {

	 ConnectionWrapper 	puConnectionWrapper;
	 ApoderadosBean     puApoderadosBean;
	 Connection 		puConnection;

	 /**
	  * Copiar escritura
	  * 
	  * @param tsEscrituraDesde
	  * @param tsEscrituraHasta
	  * @param idEmpresa
	  * @return boolean
	  */
	 public boolean copiaEscritura(String tsEscrituraDesde,
                                   String tsEscrituraHasta,
                                   int idEmpresaHasta,
                                   int nomEmpresaDesde){
		 boolean           lbOK                = false;
		 String            lsSql               = "{CALL DERCORP_APODERADOS_PKG.COPY_ESTRUCTURA_PR(?,?,?,?)}";
		 Connection        luConnection        = null;
		 CallableStatement luCallableStatement = null;
		 try {
			puConnectionWrapper = new ConnectionWrapper();
			luConnection        = puConnectionWrapper.getConnection();
			luCallableStatement = luConnection.prepareCall(lsSql);

			luCallableStatement.setString(1,tsEscrituraDesde);
			luCallableStatement.setString(2,tsEscrituraHasta);
			luCallableStatement.setInt(3, idEmpresaHasta);
			luCallableStatement.setInt(4, nomEmpresaDesde);
			luCallableStatement.execute();
			lbOK = true;

		} catch (Exception e) {
			e.printStackTrace();

		}finally {
			try {
				luCallableStatement.close();
				puConnectionWrapper.close();
				luConnection.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		 return lbOK;
	 }

	 /**
	  * Devolver Apoderados
	  * @param tiIdCatalogo
	  * @return ApoderadosBean
	  */
	 public ApoderadosBean dameApoderado(int tiIdCatalogoValor,
			 							 int tiIdCatalogo,
			 							 int tsTipoPoder,
			 							 String tsGrupoApoderado,
			 							 String tsSelectEscritura,
			 							 int    tiIdEmpresa ){
		 
		 String            lsSql               = "";
		 ApoderadosBean    luApoderadosBean    = new ApoderadosBean();
		 PreparedStatement luPreparedStatement = null;
		 ResultSet         luResultSet         = null;          

		 try {
			 lsSql = "SELECT fec_fecha_baja,\n"+
                     "	       des_tipo_baja,\n"+
                     "	       des_documento,\n"+
                     "	       id_catalogo_valor,\n"+
                     "	       atributo1,\n"+
                     "	       id_catalogo,\n"+
                     "	       cod_revocado,\n"+
                     "	       DES_PROTO_MED_ESC,\n"+
                     "	       FEC_PROTO_MED_ESC,\n"+
                     "	       DES_REVOCADO_MEDIANTE,\n"+
                     "	       FEC_REVOCADO_MEDIANTE,\n"+
                     "	       ID_REVOCACION,\n"+
                     "   	   ATRIBUTO15\n"+
                     "	FROM  dercorp_apoderados_wk_tab\n"+ 
                     "	WHERE id_catalogo_valor   = ?\n"  +
                     "   AND   ID_EMPRESA         = ?\n" +
                     "   AND   ID_CATALOGO        = ?\n"+
                     "   AND   NUM_TIPO_PODER     = ?\n"+
                     "   AND   trim(atributo3)          = ?\n"+
                     "   AND   DES_ESCRITURA      = ?";

			 puConnectionWrapper = new ConnectionWrapper();
			 luPreparedStatement = puConnectionWrapper.prepareStatement(lsSql);
			 luPreparedStatement.setInt(1, tiIdCatalogoValor);
			 luPreparedStatement.setInt(2, tiIdEmpresa);
			 luPreparedStatement.setInt(3, tiIdCatalogo);
			 luPreparedStatement.setInt(4, tsTipoPoder);
			 luPreparedStatement.setString(5, tsGrupoApoderado);
			 luPreparedStatement.setString(6, tsSelectEscritura);
			 luResultSet = luPreparedStatement.executeQuery();

			 while(luResultSet.next()){
				 luApoderadosBean.setFecha(luResultSet.getDate(1));
				 luApoderadosBean.setTipoBaja(luResultSet.getString(2));
				 luApoderadosBean.setDocumento(luResultSet.getString(3));
				 luApoderadosBean.setIdCatalogoValor(luResultSet.getInt(4));
				 luApoderadosBean.setRefDocumentum(luResultSet.getString(5));
				 luApoderadosBean.setIdCatalogo(luResultSet.getInt(6));
				 luApoderadosBean.setCheckRev(luResultSet.getString(7));
				 luApoderadosBean.setProtoMedianteEsc(luResultSet.getString(8));
				 luApoderadosBean.setProtoMedianteEscFecha(luResultSet.getString(9));
				 luApoderadosBean.setRevocadoMediante(luResultSet.getString(10));
				 luApoderadosBean.setRevocadoMedianteFecha(luResultSet.getString(11));
				 luApoderadosBean.setIdRevocacion(luResultSet.getString(12));
				 luApoderadosBean.setAtributo15(luResultSet.getString(13));
			 }

		} catch (Exception e) {
			e.printStackTrace();

		}finally {
			try {
				luResultSet.close();
				luPreparedStatement.close();
				puConnectionWrapper.close();

			} catch (SQLException e) {
				e.printStackTrace();

			}
		}

		 return luApoderadosBean;
	 }
	 
	 
	 
	 /**
	  * Metodo que genera el id de revocacion haciendo
	  * un select max
	  * @deprecated
	  * @param tiIdCatalogo
	  * @param tsTipoPoder
	  * @param tsGrupoApoderado
	  * @param tsSelectEscritura
	  * @param tiIdEmpresa
	  * @return
	  */
	 private int dameIdRevocacion(int tiIdCatalogo,
								 int tsTipoPoder,
								 String tsGrupoApoderado,
								 String tsSelectEscritura,
								 int    tiIdEmpresa){
		 int idRevocacion = 0;
		 PreparedStatement luPreparedStatement = null;
		 ResultSet rs = null;
		 String lsSql = "Select  NVL(MAX(id_revocacion) + 1,1) \n"+
					  "	FROM dercorp_apoderados_wk_tab\n"+ 
					  "	WHERE id_empresa = ? \n"+
					  "	AND id_catalogo = ? \n"+
					  "	AND Num_Tipo_Poder = ?\n"+
					  "	AND TRIM(atributo3) = ?\n"+
					  "	AND des_escritura = ?";
		 try {
			puConnectionWrapper = new ConnectionWrapper();
			luPreparedStatement = puConnectionWrapper.prepareStatement(lsSql);
			luPreparedStatement.setInt(1, tiIdEmpresa);
			luPreparedStatement.setInt(2, tiIdCatalogo);
			luPreparedStatement.setInt(3, tsTipoPoder);
			luPreparedStatement.setString(4, tsGrupoApoderado);
			luPreparedStatement.setString(5, tsSelectEscritura);
			rs = luPreparedStatement.executeQuery();
			while(rs.next()){
				idRevocacion = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				luPreparedStatement.close();
				puConnectionWrapper.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		 
		 return idRevocacion;
		 
	 }

	 /**
	  * Actualizar apoderados
	  * @param tuApoderadosBean
	  * @return boolean
	  */
	 public boolean actualizaApoderado(ApoderadosBean tuApoderadosBean){
		 boolean           lbOK                = false;
		 CallableStatement luPreparedStatement = null;
		 String            luUpdate            = "";
		 Connection			con				   = null;

		 try {
          /*   luUpdate = "UPDATE dercorp_apoderados_wk_tab\n"+
                        "	SET    fec_fecha_baja = ?,\n"+
            		    "	       des_tipo_baja  = ?,\n"+
                        "	       des_documento  = ?,\n"+
                        "	       atributo1      = ?,\n"+
                        "	       COD_REVOCADO   = ?,\n"+
                        "	       DES_PROTO_MED_ESC   = ?,\n"+
                        "	       FEC_PROTO_MED_ESC   = ?,\n"+
                        "	       DES_REVOCADO_MEDIANTE   = ?,\n"+
                        "	       FEC_REVOCADO_MEDIANTE   = ?,\n"+
                        "	       ID_REVOCACION   = ?\n"+
                        "	WHERE id_catalogo_valor = ?\n"+
                        "   AND   ID_EMPRESA        = ?\n"+
                        "   AND   ID_CATALOGO        = ?\n"+
                        "   AND   NUM_TIPO_PODER     = ?\n"+
                        "   AND   DES_GRUPO          = ?\n"+
                        "   AND   DES_ESCRITURA      = ?"
            ;*/
			 luUpdate = "{CALL DERCORP_APODERADOS_PKG.GET_UPDATE_REVOCADOS_PR(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

			puConnectionWrapper = new ConnectionWrapper();
			con 				= puConnectionWrapper.getConnection();
			luPreparedStatement = con.prepareCall(luUpdate);

			luPreparedStatement.setDate(1, tuApoderadosBean.getFecha());
			luPreparedStatement.setString(2, tuApoderadosBean.getTipoBaja());
			luPreparedStatement.setString(3, tuApoderadosBean.getDocumento());
			luPreparedStatement.setString(4,tuApoderadosBean.getRefDocumentum());
			
			luPreparedStatement.setString(5, tuApoderadosBean.getCheckRev()==null?"No":"Si");
			luPreparedStatement.setString(6, tuApoderadosBean.getCheckRev()==null?"":tuApoderadosBean.getProtoMedianteEsc());
			luPreparedStatement.setString(7, tuApoderadosBean.getCheckRev()==null?"":tuApoderadosBean.getProtoMedianteEscFecha());
			luPreparedStatement.setString(8, tuApoderadosBean.getCheckRev()==null?"":tuApoderadosBean.getRevocadoMediante());
			luPreparedStatement.setString(9, tuApoderadosBean.getCheckRev()==null?"":tuApoderadosBean.getRevocadoMedianteFecha());
			/*luPreparedStatement.setInt(10, tuApoderadosBean.getCheckRev()==null?0:dameIdRevocacion(tuApoderadosBean.getIdCatalogo(),
																tuApoderadosBean.getNumTipoPoder(),
																tuApoderadosBean.getDesGrupo(),
																tuApoderadosBean.getDesEscritura(),
																tuApoderadosBean.getIdEmpresa()));
			*/
			luPreparedStatement.setInt(10, tuApoderadosBean.getIdCatalogoValor());
			luPreparedStatement.setInt(11, tuApoderadosBean.getIdEmpresa());
			luPreparedStatement.setInt(12, tuApoderadosBean.getIdCatalogo());
			luPreparedStatement.setInt(13, tuApoderadosBean.getNumTipoPoder());
			luPreparedStatement.setString(14, tuApoderadosBean.getDesGrupo());
			luPreparedStatement.setString(15, tuApoderadosBean.getDesEscritura());
			luPreparedStatement.setString(16,tuApoderadosBean.getElijerevocacion());
			luPreparedStatement.execute();
			lbOK = true;

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

		 return lbOK;
	 }
	 
	/**
	 * Metodo que busca si un apoderado esta revocado
	 * @param luApoderadosBean
	 * @param tuParametros
	 */
	 public boolean noBorrarRevocado(ApoderadosBean luApoderadosBean,String[] tuParametros){
		 
		 String lsSql = "SELECT cod_revocado\n"+
						"	FROM dercorp_apoderados_wk_tab\n"+ 
						"	WHERE id_empresa   = ? \n"+
						"	AND id_catalogo    = ? \n"+
						"	AND num_Tipo_Poder = ?\n"+
						"	AND TRIM(atributo3) 	   = ?\n"+
						"	AND des_escritura  = ?\n"+
						"	AND id_catalogo_valor = ?";
		 boolean    encontroEevocado   			= false;
		 Connection puConnection 				= null;
		 PreparedStatement luPreparedStatement 	= null;
		 ResultSet rs 							= null;
		 String    revocado 					= null;
		 try {
			puConnectionWrapper = new ConnectionWrapper();
			puConnection = puConnectionWrapper.getConnection();
			luPreparedStatement = puConnection.prepareStatement(lsSql);
			
			for(String idCatalogoValor : tuParametros){
				luPreparedStatement.setInt(1,  luApoderadosBean.getIdEmpresa());
				luPreparedStatement.setInt(2, luApoderadosBean.getIdCatalogo());
				luPreparedStatement.setInt(3,luApoderadosBean.getNumTipoPoder());
				luPreparedStatement.setString(4, luApoderadosBean.getDesGrupo());
				luPreparedStatement.setString(5, luApoderadosBean.getDesEscritura());
				luPreparedStatement.setString(6, idCatalogoValor);
				luApoderadosBean.setIdCatalogoValor(Integer.parseInt(idCatalogoValor));
				rs = luPreparedStatement.executeQuery();
				while(rs.next()){
					revocado = rs.getString(1);
					if(revocado != null && revocado.equals("Si")){
						encontroEevocado = true;
						//break PARENT_FOR;
					}else{
						quitaApoderados(luApoderadosBean);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				luPreparedStatement.close();
				rs.close();
				puConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		 
		 return encontroEevocado;
	 }

	 /**
	  * Quitar Apoderados individual
	  * 
	  * @param luApoderadosBean
	  * @param tuParametros
	  * @return boolean
	  */
	 public boolean quitaApoderados(ApoderadosBean luApoderadosBean){
		 boolean           lbPasa              = false;
		 String            lsDelete            = "";
		 PreparedStatement luPreparedStatement = null;

		 try {
			 lsDelete = "DELETE FROM dercorp_apoderados_wk_tab\n"+
                        "	WHERE id_catalogo       = ?\n"+
                        "	AND   id_catalogo_valor = ?\n"+
                        "	AND   id_empresa        = ?\n"+
                        "	AND   num_tipo_poder    = ?\n"+
                        "	AND   TRIM(atributo3)         = ?\n"+
                        "	AND   des_escritura     = ?";

			puConnectionWrapper = new ConnectionWrapper();
			puConnection = puConnectionWrapper.getConnection();
			//puConnection.setAutoCommit(false);
			luPreparedStatement = puConnection.prepareStatement(lsDelete);

			
				luPreparedStatement.setInt(1, luApoderadosBean.getIdCatalogo());
				luPreparedStatement.setInt(2, luApoderadosBean.getIdCatalogoValor());
				luPreparedStatement.setInt(3, luApoderadosBean.getIdEmpresa());
				luPreparedStatement.setInt(4, luApoderadosBean.getNumTipoPoder());
				luPreparedStatement.setString(5, luApoderadosBean.getDesGrupo());
				luPreparedStatement.setString(6, luApoderadosBean.getDesEscritura());
				luPreparedStatement.executeUpdate();
			
			//puConnection.commit();
			//puConnection.setAutoCommit(true);
			lbPasa = true;

		} catch (Exception e) {
			e.printStackTrace();

		}finally {			
			 try {
				 puConnectionWrapper.close();
				 luPreparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		 return lbPasa;
	 }
	 
	 /**
	  * Quitar Apoderados
	  * 
	  * @param luApoderadosBean
	  * @param tuParametros
	  * @return boolean
	  */
	 public boolean quitaApoderados(ApoderadosBean luApoderadosBean,String[] tuParametros){
		 boolean           lbPasa              = false;
		 String            lsDelete            = "";
		 PreparedStatement luPreparedStatement = null;

		 try {
			 lsDelete = "DELETE FROM dercorp_apoderados_wk_tab\n"+
                        "	WHERE id_catalogo       = ?\n"+
                        "	AND   id_catalogo_valor = ?\n"+
                        "	AND   id_empresa        = ?\n"+
                        "	AND   num_tipo_poder    = ?\n"+
                        "	AND   TRIM(atributo3)         = ?\n"+
                        "	AND   des_escritura     = ?";

			puConnectionWrapper = new ConnectionWrapper();
			puConnection = puConnectionWrapper.getConnection();
			//puConnection.setAutoCommit(false);
			luPreparedStatement = puConnection.prepareStatement(lsDelete);

			for(String idCatalogoValor : tuParametros){
				luPreparedStatement.setInt(1, luApoderadosBean.getIdCatalogo());
				luPreparedStatement.setInt(2, Integer.parseInt(idCatalogoValor));
				luPreparedStatement.setInt(3, luApoderadosBean.getIdEmpresa());
				luPreparedStatement.setInt(4, luApoderadosBean.getNumTipoPoder());
				luPreparedStatement.setString(5, luApoderadosBean.getDesGrupo());
				luPreparedStatement.setString(6, luApoderadosBean.getDesEscritura());
				luPreparedStatement.executeUpdate();
			}
			//puConnection.commit();
			//puConnection.setAutoCommit(true);
			lbPasa = true;

		} catch (Exception e) {
			e.printStackTrace();

		}finally {			
			 try {
				 puConnectionWrapper.close();
				 luPreparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		 return lbPasa;
	 }

	 /**
	  * Insertar Apoderados
	  * @param tuApoderadosBean
	  * @param taParametros
	  * @return boolean
	  */
	 public boolean insertApoderados(ApoderadosBean tuApoderadosBean,String[] taParametros){
		 boolean           lbPasa              = false;
		 PreparedStatement luPreparedStatement = null;
		 PreparedStatement luPreparedStatement2 = null;
		 String            lsInsert            = "";
		 String 		   lsUpdate			   = "";

		 try {
             lsInsert = "INSERT INTO dercorp_apoderados_wk_tab(id_empresa,\n"+
                        "                                    id_catalogo,\n"+
                        "	                                   id_catalogo_valor,\n"+
                        "	                                   num_tipo_poder,\n"+
                        "	                                   des_grupo,\n"+
                        "	                                   des_escritura,\n"+
                        "	                                   fec_creation_date,"+
                        "                                      atributo3,atributo2 )\n"+
                        "	VALUES(?,?,?,?,?,?,SYSDATE,?,?)";
             
             lsUpdate = " UPDATE dercorp_apoderados_wk_tab \n"+
				        "    SET des_grupo =  ? ||' '||?\n"+
				        "    WHERE 1 = 1\n"+
				        "    AND	id_empresa   = ?\n"+ 
				        "	 AND num_tipo_poder  = ?\n"+ 
				    	"	 AND des_escritura   = ?\n"+
				        "    AND TRIM(atributo3)       = ?";

			puConnectionWrapper = new ConnectionWrapper();
			puConnection = puConnectionWrapper.getConnection();
			//puConnection.setAutoCommit(false);
			luPreparedStatement = puConnection.prepareStatement(lsInsert);

			for(String idCatalogoValor : taParametros){
				luPreparedStatement.setInt(1, tuApoderadosBean.getIdEmpresa());
				luPreparedStatement.setInt(2, tuApoderadosBean.getIdCatalogo());
				luPreparedStatement.setInt(3, Integer.parseInt(idCatalogoValor));
				luPreparedStatement.setInt(4, tuApoderadosBean.getNumTipoPoder());
				luPreparedStatement.setString(5, tuApoderadosBean.getDesGrupo());
				luPreparedStatement.setString(6, TextFormatter.replaceAccentsURLFromAjax(tuApoderadosBean.getDesEscritura()));
				luPreparedStatement.setString(7, tuApoderadosBean.getDesGrupo());
				luPreparedStatement.setString(8, tuApoderadosBean.getAtributo2());
				luPreparedStatement.executeUpdate();
			}
			if(tuApoderadosBean.getAtributo2() != null || !tuApoderadosBean.getAtributo2().equals("")){
				luPreparedStatement2 = puConnection.prepareStatement(lsUpdate);
				luPreparedStatement2.setString(1,tuApoderadosBean.getAtributo2());
				luPreparedStatement2.setString(2,tuApoderadosBean.getDesGrupo());
				luPreparedStatement2.setInt(3, tuApoderadosBean.getIdEmpresa());
				luPreparedStatement2.setInt(4, tuApoderadosBean.getNumTipoPoder());
				luPreparedStatement2.setString(5, TextFormatter.replaceAccentsURLFromAjax(tuApoderadosBean.getDesEscritura()));
				luPreparedStatement2.setString(6, tuApoderadosBean.getDesGrupo());
				luPreparedStatement2.executeUpdate();
			}
			
			//puConnection.commit();
			//puConnection.setAutoCommit(true);
			lbPasa = true;

		} catch (Exception e) {
			e.printStackTrace();

		}finally {
			try {
				 puConnectionWrapper.close();
				 luPreparedStatement.close();
				 luPreparedStatement2.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return lbPasa;
	 }

}

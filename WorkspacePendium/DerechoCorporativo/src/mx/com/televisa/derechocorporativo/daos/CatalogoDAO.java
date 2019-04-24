/**
* Project: Derecho Corporativo
*
* File: CatalogoDAO.java
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
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

import org.apache.log4j.Logger;
import org.jfree.util.Log;

import mx.com.televisa.derechocorporativo.bean.CatalogoBean;
import mx.com.televisa.derechocorporativo.bean.DetalleCatagoBean;
import mx.com.televisa.derechocorporativo.bean.EmpresasBean;
import mx.com.televisa.derechocorporativo.bean.FlexColumnsCatagoBean;
import mx.com.televisa.derechocorporativo.bean.SessionBean;
import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;
import mx.com.televisa.derechocorporativo.data.packages.APP_CONFIG_PKG;
import mx.com.televisa.derechocorporativo.data.packages.DERCORP_CONSULTA_PKG;

/**
 * Obtener, almacenar, guardar y borrar Catalogos. 
 *
 * @author KAZ - CONSULTING/
 *         Iv�n Casta�eda Loeza
 *         Jes�s Argumedo
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
public class CatalogoDAO {
	
	final static Logger log = Logger.getLogger(CatalogoDAO.class);
	
    ConnectionWrapper 		puConnectionWrapper;
    CatalogoBean 			puCatalogoBean;
    List<CatalogoBean> 	    paCatalogoBean;
    DetalleCatagoBean  	    puDetalleCatagoBean;
    List<DetalleCatagoBean> paDetalleCatagoBean;
    FlexColumnsCatagoBean   puFlexColumnsCatagoBean;
    private CallableStatement   puCallableStatement;

    /**
     * Constructor de inicializaci�n de atributos de clase
     */
    public CatalogoDAO(){
        puFlexColumnsCatagoBean = new FlexColumnsCatagoBean();
        puDetalleCatagoBean     = new DetalleCatagoBean();
        puCatalogoBean          = new CatalogoBean();
        puFlexColumnsCatagoBean = new FlexColumnsCatagoBean();
        puCallableStatement = null;
	 }

    /**
     * Devuelve identificaciones de cat�logo
     * 
     * @param tiIdCatalogo
     * @return int
     */
    public int dameIdentifiConsec(int tiIdCatalogo){
        String            lsSqlMax            = "";
        PreparedStatement luPreparedStatement = null;
        int               liSecuencia         = 0;
        ResultSet         luResultSet         = null;

        try {
            lsSqlMax = "SELECT MAX(TO_NUMBER(cod_cat_val)) + 1 FROM dercorp_add_campo_cat_val_tab\n"+
                       "WHERE id_catalogo = ?";
            puConnectionWrapper = new ConnectionWrapper();
            luPreparedStatement = puConnectionWrapper.prepareStatement(lsSqlMax);
            luPreparedStatement.setInt(1, tiIdCatalogo);
            luResultSet = luPreparedStatement.executeQuery();

            while(luResultSet.next()){
                liSecuencia = luResultSet.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();

        }finally {
            try {
                luPreparedStatement.close();
                luResultSet.close();
                puConnectionWrapper.close();

            } catch (SQLException e) {
            e.printStackTrace();
	        }
        }
        return liSecuencia==0?1:liSecuencia;
    }

    /**
     * Edita el detalle de los cat�logos
     * 
     * @param tuDetalleCatagoBean
     * @return boolean
     */
    public boolean editaDetalleCatalogo(DetalleCatagoBean tuDetalleCatagoBean,SessionBean sessionBean){
        boolean           lbOK                 = false;
        String            lsSql                = "";
        String			  lsInsertHead         = "";
        String			  lsUpdatePerTotal     = "";
        String			  sqlConsulta		   = ""; 					 
        PreparedStatement luPreparedStatement  = null;
        PreparedStatement luPreparedStatement2 = null;
        CallableStatement	cstmt			   = null;
        String			nombreAEditar			="";
        ResultSet         luResultSet         = null;

        try {
        	//Obtenemos dato antes de actualizar
        	sqlConsulta = "select * from dercorp_add_campo_cat_val_tab WHERE id_catalogo_valor = ?";
        	
            lsSql = "UPDATE dercorp_add_campo_cat_val_tab\n"+  
                    "	SET     cod_cat_val = ?,\n"+
                    "	        nom_cat_val = ?,\n"+
                    "	        val_cat_val = ?,\n"+
                    "	        des_cat_val = ?,\n"+
                    "	        atributo1   = ?,\n"+
                    "	        atributo2   = ?,\n"+
                    "	        atributo3   = ?\n"+
                    "	WHERE id_catalogo_valor = ?";
            
            lsInsertHead = "UPDATE dercorp_add_campo_cat_tab\n"+
	                "      SET    FEC_LAST_UPDATE_DATE = sysdate,\n"+
	                "             NUM_LAST_UPDATED_BY  = ?\n"+
	                "      WHERE   id_catalogo = ?";
           

            puConnectionWrapper = new ConnectionWrapper();
            
            luPreparedStatement = puConnectionWrapper.prepareStatement(sqlConsulta);
            luPreparedStatement.setInt(1, tuDetalleCatagoBean.getIdCatalogoVal());
            luResultSet = luPreparedStatement.executeQuery();

			while(luResultSet.next()){
				nombreAEditar = luResultSet.getString("val_cat_val");
			}
            
			 lsUpdatePerTotal = "UPDATE dercorp_cat_personas_total_tab set nombre = ? \n"+
					   "WHERE APP_COMMON_PKG.SIN_ACENTOS_NI_NN_FN(UPPER(NOMBRE)) LIKE APP_COMMON_PKG.SIN_ACENTOS_NI_NN_FN(UPPER('%"+nombreAEditar+"%'))";
  					//"WHERE ID_CATALOGO_VALOR = ? \n"+
					   //"AND ID_CATALOGO = ?";
            
            //puConnectionWrapper.getConnection().setAutoCommit(false);
            luPreparedStatement = puConnectionWrapper.prepareStatement(lsSql);
            luPreparedStatement.setString(1, tuDetalleCatagoBean.getIdentificador());
            luPreparedStatement.setString(2, tuDetalleCatagoBean.getNombre());
            luPreparedStatement.setString(3, tuDetalleCatagoBean.getValor());
            luPreparedStatement.setString(4, tuDetalleCatagoBean.getDescripcion());
            luPreparedStatement.setString(5, tuDetalleCatagoBean.getAttr1());
            luPreparedStatement.setString(6, tuDetalleCatagoBean.getAttr2());
            luPreparedStatement.setString(7, tuDetalleCatagoBean.getAttr3());
            luPreparedStatement.setInt(8, tuDetalleCatagoBean.getIdCatalogoVal());
            luPreparedStatement.executeUpdate();
            
            luPreparedStatement2 = puConnectionWrapper.prepareStatement(lsInsertHead);
            luPreparedStatement2.setString(1,sessionBean.getIdUser());
            //luPreparedStatement2.setInt(2, tuDetalleCatagoBean.getIdCatalogo());
            luPreparedStatement2.setInt(2, -1);//Se pone -1 para que no actualize nada, este proceso ya no se requiere
			//Se hara por separado
            luPreparedStatement2.executeUpdate();
            //puConnectionWrapper.commit();
            //puConnectionWrapper.getConnection().setAutoCommit(true);
            
            if(tuDetalleCatagoBean.getIdCatalogo() == 10 || tuDetalleCatagoBean.getIdCatalogo() == 32 ||
     		   tuDetalleCatagoBean.getIdCatalogo() == 56 || tuDetalleCatagoBean.getIdCatalogo() == 57){
            	
            	luPreparedStatement2 = puConnectionWrapper.prepareStatement(lsUpdatePerTotal);
            	luPreparedStatement2.setString(1, tuDetalleCatagoBean.getValor());
            	//luPreparedStatement2.setString(1, nombreAEditar);
            //	luPreparedStatement2.setInt(3, tuDetalleCatagoBean.getIdCatalogo());
            	luPreparedStatement2.executeUpdate();
            }
            lbOK = true;
            
            //Llama al procedimeinto para insertar en la bitacora de modificaciones dercorp_bita_mod_cat_det_tab
            cstmt = puConnectionWrapper.prepareCall(APP_CONFIG_PKG.DERCORP_CATALOGS_BIT_DET_PKG);
            cstmt.setInt(1, tuDetalleCatagoBean.getIdCatalogo());
            cstmt.setString(2, sessionBean.getIdUser());
            cstmt.setString(3, "MODIFICACION");
            cstmt.setString(4, tuDetalleCatagoBean.getIdentificador());
            cstmt.execute();

		} catch (Exception e) {
			lbOK=false;
			e.printStackTrace();

		}finally {
			 puConnectionWrapper.close();

		}

		 return lbOK;
	 }

	 /**
	  * Revisa cual detalle ha sido actualizado
	  * 
	  * @param tiIdCatalogo
	  * @return boolean
	  */
    private boolean catalogoDetalleUtilizado(int tiIdCatalogo){
        String            lsSql               = "";
        boolean           lbOK                = false;
        PreparedStatement luPreparedStatement = null;
        int               liContador          = 0;
        ResultSet         luResultSet         = null;

        try {
            lsSql = 
            		"SELECT count(*)\n"+
                    " FROM dercorp_add_campo_valor_tab\n"+ 
                    "	WHERE val_valor = ?"; 
//            		"SELECT SUM(cont) cont_final FROM ( \n" +
//            		"		SELECT COUNT(*) cont \n" +
//            		"		FROM dercorp_add_campo_valor_tab \n" +
//            		"		WHERE val_valor = ? \n" +
//            		"       and id_add_campo IN(509,559) )"; //Resumen Gral y Escritura constitutiva
            		//"		UNION ALL \n" +
            		//"		SELECT COUNT(*) cont \n" + 
            		//"		FROM dercorp_apoderados_tab \n" +
            		//"		WHERE id_catalogo_valor = ?)\n" ;
            puConnectionWrapper  = new ConnectionWrapper();
            luPreparedStatement = puConnectionWrapper.prepareStatement(lsSql);
            luPreparedStatement.setString(1, String.valueOf(tiIdCatalogo));
            //luPreparedStatement.setInt(2, tiIdCatalogo);
            luResultSet = luPreparedStatement.executeQuery();

            while(luResultSet.next()){
                liContador = luResultSet.getInt(1);
            }

        }catch (Exception e) {
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

        if(liContador > 0){
            lbOK = true;
        }

        return lbOK;
	 }

    /**
	  * Revisa si un catalogo de poderes esta siendo utilizado
	  * 
	  * @param tiIdCatalogo
	  * @param idCatalogo
	  * @return boolean
	  */
   private boolean catalogoDetallePoderesUtilizado(int idCatalogoVal,int idCatalogo){
       String            lsSql               = "";
       boolean           lbOK                = false;
       PreparedStatement luPreparedStatement = null;
       int               liContador          = 0;
       ResultSet         luResultSet         = null;

       try {
    	   if(idCatalogo == 32){
    		   /*Comprueba si se est� utilizando el catalogo de Apoderados*/
	           lsSql = "SELECT COUNT(1) FROM PENDIUM_APODERADO_EP_TAB "
	        		+ "WHERE ID_EMPL_FK = ?";           		
	           puConnectionWrapper  = new ConnectionWrapper();
	           luPreparedStatement = puConnectionWrapper.prepareStatement(lsSql);
	           luPreparedStatement.setString(1, String.valueOf(idCatalogoVal));          	                  
	           luResultSet = luPreparedStatement.executeQuery();
	
	           while(luResultSet.next()){
	               liContador = luResultSet.getInt(1);
	           }	          
    	  }
    	   else if(idCatalogo == 45){
    		   /*Comprueba si se est� utilizando el catalogo de Grupo de Apoderados*/
    		   lsSql = "SELECT COUNT(1) FROM PENDIUM_APODERADO_EP_TAB "
   	        		+ "WHERE ID_GRUPO_FK = ?";           		
   	           puConnectionWrapper  = new ConnectionWrapper();
   	           luPreparedStatement = puConnectionWrapper.prepareStatement(lsSql);
   	           luPreparedStatement.setString(1, String.valueOf(idCatalogoVal));             	                    
   	           luResultSet = luPreparedStatement.executeQuery();
   	
   	           while(luResultSet.next()){
   	               liContador = luResultSet.getInt(1);
   	           }	              		   
           }
    	   else if(idCatalogo == 49){
    		   /*Comprueba si se est� utilizando el catalogo de Delegado por*/
    		   lsSql = "SELECT COUNT(1) FROM PENDIUM_ESCRITURA_PODER_TAB "
   	        		+ "WHERE IND_DELEGADO_POR = ?";           		
   	           puConnectionWrapper  = new ConnectionWrapper();
   	           luPreparedStatement = puConnectionWrapper.prepareStatement(lsSql);
   	           luPreparedStatement.setString(1, String.valueOf(idCatalogoVal));             	                    
   	           luResultSet = luPreparedStatement.executeQuery();
   	
   	           while(luResultSet.next()){
   	               liContador = luResultSet.getInt(1);
   	           }	              		   
           }    	   

       }catch (Exception e) {
            e.printStackTrace();
       }finally {
    	   if(liContador > 0){
	           try {
	               luResultSet.close();
			       luPreparedStatement.close();
			       puConnectionWrapper.close();
	           } catch (SQLException e) {
	               e.printStackTrace();
	           }
    	   }
       }

       if(liContador > 0){
           lbOK = true;
       }

       return lbOK;
	 }
    
/**
 * Elimina el detalle del cat�logo
 * @param tiIdCatalogo
 * @return boolean
 */
    public boolean eliminaDetalleCatalogo(int tiIdCatalogo,SessionBean sessionBean,int idCatalogo){
        boolean           lbOK                = false;
        String            lsSql               = "";
        String 			  lsDeltePerTotal     = "";
        PreparedStatement luPreparedStatement = null;
        PreparedStatement luPreparedStatement2 = null;
        String		      lsInsertHead        = "";
        PreparedStatement cstmt				  = null;

        int numRegistroEnFlex = getDatosFlex(idCatalogo,tiIdCatalogo);
        log.info("resultado de consultar en flex: "+numRegistroEnFlex);
        
        if(numRegistroEnFlex > 0){
        	return false;
        }
        
        if(catalogoDetalleUtilizado(tiIdCatalogo) || catalogoDetallePoderesUtilizado(tiIdCatalogo,idCatalogo) ){
            return false;
        }else{
            lsSql = "DELETE FROM dercorp_add_campo_cat_val_tab WHERE id_catalogo_valor = ?";
            lsInsertHead = "UPDATE dercorp_add_campo_cat_tab\n"+
	                "      SET    FEC_LAST_UPDATE_DATE = sysdate,\n"+
	                "             NUM_LAST_UPDATED_BY  = ?\n"+
	                "      WHERE   id_catalogo = ?";
            
            lsDeltePerTotal = "DELETE dercorp_cat_personas_total_tab WHERE ID_CATALOGO_VALOR = ? AND ID_CATALOGO = ?";
            try {
            	CatalogoBean catalogoBean = obtenCatalogoId(idCatalogo);
            	DetalleCatagoBean DetalleCatagoBean = obtenDetalleCatalogoId(tiIdCatalogo);
                puConnectionWrapper = new ConnectionWrapper();
                
                //con.setAutoCommit(false);
                luPreparedStatement = puConnectionWrapper.prepareStatement(lsSql);
                luPreparedStatement.setInt(1, tiIdCatalogo);
                luPreparedStatement.executeUpdate();
                luPreparedStatement2 = puConnectionWrapper.prepareStatement(lsInsertHead);
                luPreparedStatement2.setString(1,sessionBean.getIdUser());
                //luPreparedStatement2.setInt(2, idCatalogo);
                luPreparedStatement2.setInt(2, -1);//Se pone -1 para que no actualize nada, este proceso ya no se requiere
    			//Se hara por separado
                luPreparedStatement2.executeUpdate();
              //JJAQ 20-02-18 solo aplica para los catalogos Nombre Funcionarios, Apoderados, Contactos, Personas Externas
    			if(idCatalogo == 10 || idCatalogo == 32 ||
    			   idCatalogo == 56 || idCatalogo == 57){
    				luPreparedStatement = puConnectionWrapper.prepareStatement(lsDeltePerTotal);
    				luPreparedStatement.setInt(1, tiIdCatalogo);
    				luPreparedStatement.setInt(2, idCatalogo);
    				luPreparedStatement.executeUpdate();
    			}
                
                lbOK = true;
                //con.setAutoCommit(true);
              //Llama al procedimeinto para insertar en la bitacora de modificaciones DERCORP_BITA_MOD_CAT_TAB
                cstmt = puConnectionWrapper.prepareCall(APP_CONFIG_PKG.DERCORP_CATALOGS_DELETE_DET_PKG);
                
                cstmt.setString(1, catalogoBean.getNombre());
                cstmt.setString(2, DetalleCatagoBean.getValor());
                cstmt.setString(3, sessionBean.getIdUser());
                cstmt.execute();

			} catch (Exception e) {
				lbOK                = false;
				e.printStackTrace();

			}finally {
				 puConnectionWrapper.close();
				 try {
					cstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		 }
			 return lbOK;
	 }

    /**
     * Obtener el detalle del cat�logo
     * @param tiIdCatalogo
     * @return Objeto
     */
    public DetalleCatagoBean obtenDetalleCatalogoId(int tiIdCatalogo){
        String            lsSql               = "";
        PreparedStatement luPreparedStatement = null;
        ResultSet         luResultSet         = null;

        try{
            lsSql = "SELECT  camp.id_catalogo_valor,\n"+
                    "	     camp.id_catalogo,\n"+
                    "	     camp.cod_cat_val,\n"+
                    "	     camp.nom_cat_val,\n"+
                    "	     camp.val_cat_val,\n"+
                    "	     camp.des_cat_val,\n"+
                    "	     camp.atributo1,\n"+
                    "	     camp.atributo2,\n"+
                    "	     camp.atributo3,\n"+
                    "		 DERCORP_PANEL_CONTROL_PKG.ES_EMPRESA_FN(camp.id_catalogo,camp.val_cat_val),\n"+
                    "		 DERCORP_CATALOGS_PKG.GET_ID_CATIN_CAT_FN(camp.id_catalogo),\n" +
                    "		 DERCORP_CATALOGS_PKG.GET_CAMP_CATIN_FN(camp.id_catalogo)\n" +
                    "	FROM dercorp_add_campo_cat_val_tab camp\n"+ 
                    "	WHERE camp.id_catalogo_valor = ?";

			puConnectionWrapper = new ConnectionWrapper();
			luPreparedStatement = puConnectionWrapper.prepareStatement(lsSql);
			luPreparedStatement.setInt(1, tiIdCatalogo);
			luResultSet = luPreparedStatement.executeQuery();

			while(luResultSet.next()){
				puDetalleCatagoBean = new DetalleCatagoBean();
				puDetalleCatagoBean.setIdCatalogoVal(luResultSet.getInt(1));
				puDetalleCatagoBean.setIdCatalogo(luResultSet.getInt(2));
				puDetalleCatagoBean.setIdentificador(luResultSet.getString(3));
				puDetalleCatagoBean.setNombre(luResultSet.getString(4));
				puDetalleCatagoBean.setValor(luResultSet.getString(5));
				puDetalleCatagoBean.setdescripcion(luResultSet.getString(6));
				puDetalleCatagoBean.setAttr1(luResultSet.getString(7));
				puDetalleCatagoBean.setAttr2(luResultSet.getString(8));
				puDetalleCatagoBean.setAttr3(luResultSet.getString(9));
                puDetalleCatagoBean.setStatusFlex(luResultSet.getString(10));
                puDetalleCatagoBean.setIdCatinCat(luResultSet.getInt(11));
                puDetalleCatagoBean.setCampCatinCat(luResultSet.getString(12));
			}
		} catch (Exception e) {
			e.printStackTrace();

		}finally {
			 puConnectionWrapper.close();

		}

		 return puDetalleCatagoBean;

	 }
 
    /**
     * Insertar detalle en el cat�logo
     * @param tuDetalleCatagoBean
     * @return boolean
     */
    public boolean insertDetCatalogo(DetalleCatagoBean tuDetalleCatagoBean,SessionBean sessionBean){
        boolean           lbPaso              = false;
        String            lsSqlMax            = "";
        PreparedStatement luPreparedStatement = null;
        PreparedStatement luPreparedStatement2 = null;
        int               liSecuencia         = 0;
        ResultSet         luResultSet         = null;
        String            lsSqlInsert         = "";
        String            lsInsertPersonaTot  = "";
        String 			  lsInsertHead        = "";
        CallableStatement	cstmt			  = null;
        log.info("INSERTANDO");

        try {
            lsSqlMax = "SELECT  dercorp_cat_val_seq.nextval FROM dual";
            puConnectionWrapper = new ConnectionWrapper();
            luPreparedStatement = puConnectionWrapper.prepareStatement(lsSqlMax);
            luResultSet = luPreparedStatement.executeQuery();

            while(luResultSet.next()){
                liSecuencia = luResultSet.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                luPreparedStatement.close();
                luResultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        //liSecuencia = liSecuencia + 1;
        lsSqlInsert = "INSERT INTO dercorp_add_campo_cat_val_tab(id_catalogo_valor,\n"+
                      "                id_catalogo,\n"+
                      "                cod_cat_val,\n"+
                      "                nom_cat_val,\n"+
                      "                val_cat_val,\n"+
                      "                des_cat_val,\n"+
                      "                fec_creation_date,\n"+
                      "                atributo1,\n"+
                      "                atributo2,\n"+
                      "                atributo3)\n"+
                      " VALUES(?,?,?,?,?,?,sysdate,?,?,?)";
        lsInsertHead = "UPDATE dercorp_add_campo_cat_tab\n"+
		                "      SET    fec_last_update_date = SYSDATE,\n"+
		                "             num_last_updated_by = ?\n"+
		                "      WHERE   id_catalogo = ?";
        lsInsertPersonaTot = "INSERT INTO USRDRC.DERCORP_CAT_PERSONAS_TOTAL_TAB(PERSON_ID,NOMBRE,id_catalogo_valor,id_catalogo)\n"+ 
							 "	VALUES((SELECT DERCORP_CATALOGS_PKG.MAX_PERSONAS_TOTAL_FN(ROWNUM) from dual)\n,"+
							 "	?,?,?)";
        try {
        	//puConnectionWrapper.getConnection().setAutoCommit(false);
            luPreparedStatement = puConnectionWrapper.prepareStatement(lsSqlInsert);
			luPreparedStatement.setInt(1, liSecuencia);
			luPreparedStatement.setInt(2,    tuDetalleCatagoBean.getIdCatalogo());
			luPreparedStatement.setString(3, tuDetalleCatagoBean.getIdentificador());
			luPreparedStatement.setString(4, tuDetalleCatagoBean.getNombre());
			luPreparedStatement.setString(5, tuDetalleCatagoBean.getValor());
			luPreparedStatement.setString(6, tuDetalleCatagoBean.getDescripcion());
			luPreparedStatement.setString(7, tuDetalleCatagoBean.getAttr1());
			luPreparedStatement.setString(8, tuDetalleCatagoBean.getAttr2());
			luPreparedStatement.setString(9, tuDetalleCatagoBean.getAttr3());
			luPreparedStatement.executeUpdate();
			
			luPreparedStatement2 = puConnectionWrapper.prepareStatement(lsInsertHead);
			luPreparedStatement2.setString(1, sessionBean.getIdUser());
			//luPreparedStatement2.setInt(2, tuDetalleCatagoBean.getIdCatalogo());
			luPreparedStatement2.setInt(2, -1);//Se pone -1 para que no actualize nada, este proceso ya no se requiere
			//Se hara por separado
			luPreparedStatement2.executeUpdate();
			//puConnectionWrapper.commit();
			
			//JJAQ 20-02-18 solo aplica para los catalogos Nombre Funcionarios, Apoderados, Contactos, Personas Externas
			if(tuDetalleCatagoBean.getIdCatalogo() == 10 || tuDetalleCatagoBean.getIdCatalogo() == 32 ||
			   tuDetalleCatagoBean.getIdCatalogo() == 56 || tuDetalleCatagoBean.getIdCatalogo() == 57){
				
				luPreparedStatement2 = puConnectionWrapper.prepareStatement(lsInsertPersonaTot);
				luPreparedStatement2.setString(1, tuDetalleCatagoBean.getValor());
				luPreparedStatement2.setInt(2,    liSecuencia);
				luPreparedStatement2.setInt(3,    tuDetalleCatagoBean.getIdCatalogo());
				luPreparedStatement2.executeUpdate();
			}
			
			lbPaso = true;
			//puConnectionWrapper.getConnection().setAutoCommit(true);

            //Llama al procedimeinto para insertar en la bitacora de modificaciones dercorp_bita_mod_cat_det_tab
            cstmt = puConnectionWrapper.prepareCall(APP_CONFIG_PKG.DERCORP_CATALOGS_BIT_DET_PKG);
            cstmt.setInt(1, tuDetalleCatagoBean.getIdCatalogo());
            cstmt.setString(2, sessionBean.getIdUser());
            cstmt.setString(3, "NUEVO");
            cstmt.setString(4, tuDetalleCatagoBean.getIdentificador());
            cstmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			lbPaso = false;

		}finally {
			puConnectionWrapper.close();

		}
		 return lbPaso;

	 }

    /**
     * Devuelve el detalle del catalogo
     * @param tiIdCatalogo
     * @return Arreglo de objetos
     */
    public List<DetalleCatagoBean> dameDetalleCatgo(int tiIdCatalogo){
        PreparedStatement luPreparedStatement = null;
        String            lsSql               = "";
        ResultSet         luResultSet         = null;

        paDetalleCatagoBean = new ArrayList<>();

        try {
            lsSql = "SELECT  camp.ID_CATALOGO_VALOR,\n"+
                    "        camp.cod_cat_val,\n"+
                    "  		 camp.nom_cat_val,\n"+
                    "	     camp.val_cat_val,\n"+
                    "	     camp.des_cat_val,\n"+
                    "	     camp.atributo1,\n"+
                    "	     camp.atributo2,\n"+
                    "	     camp.atributo3,\n"+
                    "        camp.id_catalogo,\n"+
                    "		 DERCORP_PANEL_CONTROL_PKG.ES_EMPRESA_FN(camp.id_catalogo,camp.val_cat_val),\n"+
                    "		 DERCORP_CATALOGS_PKG.GET_ID_CATIN_CAT_FN(camp.id_catalogo),\n" +
                    "		 DERCORP_CATALOGS_PKG.GET_CAMP_CATIN_FN(camp.id_catalogo)\n" +
                    "	FROM dercorp_add_campo_cat_val_tab camp\n"+ 
                    "	WHERE camp.id_catalogo = ?\n"+
                    "order by APP_COMMON_PKG.SIN_ACENTOS_FN(UPPER(camp.nom_cat_val))";

            puConnectionWrapper = new ConnectionWrapper();
            luPreparedStatement = puConnectionWrapper.prepareStatement(lsSql);
            luPreparedStatement.setInt(1, tiIdCatalogo);
            luResultSet = luPreparedStatement.executeQuery();

            while(luResultSet.next()){
                puDetalleCatagoBean = new DetalleCatagoBean();
                puDetalleCatagoBean.setIdCatalogoVal(luResultSet.getInt(1));
                puDetalleCatagoBean.setIdentificador(luResultSet.getString(2));
                puDetalleCatagoBean.setNombre(luResultSet.getString(3));
                puDetalleCatagoBean.setValor(luResultSet.getString(4));
                puDetalleCatagoBean.setdescripcion(luResultSet.getString(5));
                puDetalleCatagoBean.setAttr1(luResultSet.getString(6));
                puDetalleCatagoBean.setAttr2(luResultSet.getString(7));
                puDetalleCatagoBean.setAttr3(luResultSet.getString(8));
                puDetalleCatagoBean.setIdCatalogo(luResultSet.getInt(9));
                puDetalleCatagoBean.setStatusFlex(luResultSet.getString(10));
                puDetalleCatagoBean.setIdCatinCat(luResultSet.getInt(11));
                puDetalleCatagoBean.setCampCatinCat(luResultSet.getString(12));
                paDetalleCatagoBean.add(puDetalleCatagoBean);

			}
        } catch (Exception e) {
            e.printStackTrace();

        }finally {
            puConnectionWrapper.close();

        }

        return paDetalleCatagoBean;

    }
    
    public List<DetalleCatagoBean> dameDetCatBus(int tiIdCatalogo, String tsBusCatDet){
        PreparedStatement luPreparedStatement = null;
        String            lsSql               = "";
        ResultSet         luResultSet         = null;

        paDetalleCatagoBean = new ArrayList<>();
        
        try {
            lsSql = 
            		"SELECT    CAMP.ID_CATALOGO_VALOR "+
                             ",CAMP.COD_CAT_VAL "+
              		         ",CAMP.NOM_CAT_VAL "+
            	             ",CAMP.VAL_CAT_VAL "+
		            	     ",CAMP.DES_CAT_VAL "+
		            	     ",CAMP.ATRIBUTO1 "+
            	             ",CAMP.ATRIBUTO2 "+
            	             ",CAMP.ATRIBUTO3 "+
                             ",CAMP.ID_CATALOGO "+
                             ",DERCORP_PANEL_CONTROL_PKG.ES_EMPRESA_FN(CAMP.ID_CATALOGO,CAMP.VAL_CAT_VAL) "+
                             ",DERCORP_CATALOGS_PKG.GET_ID_CATIN_CAT_FN(CAMP.ID_CATALOGO) "+
                             ",DERCORP_CATALOGS_PKG.GET_CAMP_CATIN_FN(CAMP.ID_CATALOGO) "+
		            "FROM    DERCORP_ADD_CAMPO_CAT_VAL_TAB CAMP "+ 
		            "WHERE   1=1 "+
		            "AND     CAMP.ID_CATALOGO = ? "+
		            "AND    (APP_COMMON_PKG.SIN_ACENTOS_FN(UPPER(CAMP.NOM_CAT_VAL)) LIKE APP_COMMON_PKG.SIN_ACENTOS_FN(UPPER(?)) "+
		            "OR     APP_COMMON_PKG.SIN_ACENTOS_FN(UPPER(CAMP.DES_CAT_VAL)) LIKE APP_COMMON_PKG.SIN_ACENTOS_FN(UPPER(?))) "+
		            "ORDER BY APP_COMMON_PKG.SIN_ACENTOS_FN(UPPER(CAMP.NOM_CAT_VAL))"
            ;

            puConnectionWrapper = new ConnectionWrapper();
            luPreparedStatement = puConnectionWrapper.prepareStatement(lsSql);
            luPreparedStatement.setInt(1, tiIdCatalogo);
            luPreparedStatement.setString(2, "%"+tsBusCatDet+"%");
            luPreparedStatement.setString(3, "%"+tsBusCatDet+"%");
            luResultSet = luPreparedStatement.executeQuery();

            while(luResultSet.next()){
                puDetalleCatagoBean = new DetalleCatagoBean();
                puDetalleCatagoBean.setIdCatalogoVal(luResultSet.getInt(1));
                puDetalleCatagoBean.setIdentificador(luResultSet.getString(2));
                puDetalleCatagoBean.setNombre(luResultSet.getString(3));
                puDetalleCatagoBean.setValor(luResultSet.getString(4));
                puDetalleCatagoBean.setdescripcion(luResultSet.getString(5));
                puDetalleCatagoBean.setAttr1(luResultSet.getString(6));
                puDetalleCatagoBean.setAttr2(luResultSet.getString(7));
                puDetalleCatagoBean.setAttr3(luResultSet.getString(8));
                puDetalleCatagoBean.setIdCatalogo(luResultSet.getInt(9));
                puDetalleCatagoBean.setStatusFlex(luResultSet.getString(10));
                puDetalleCatagoBean.setIdCatinCat(luResultSet.getInt(11));
                puDetalleCatagoBean.setCampCatinCat(luResultSet.getString(12));
                paDetalleCatagoBean.add(puDetalleCatagoBean);

			}
        } catch (Exception e) {
            e.printStackTrace();

        }finally {
            puConnectionWrapper.close();

        }

        return paDetalleCatagoBean;

    }

    /**
     * Si un catalogo ha sido utilizado
     * @param tiIdCatalogo
     * @return boolean
     */
    private boolean catalogoUtilizado(int tiIdCatalogo){
        String            lsSql                = "";
		String            lsSql2               = "";
		boolean           lbOK                 = false;
        PreparedStatement luPreparedStatement  = null;
        PreparedStatement luPreparedStatement2 = null;
        int               liContadorA          = 0;
        int               liContadorB          = 0;
        int               liResultado          = 0;
        ResultSet         luResultSet          = null;
        ResultSet         luResultSet2         = null;
        
        if(tiIdCatalogo == 62 ){
	        lsSql = "SELECT COUNT(NUM_PODERTIPO)\n"+
	                "  FROM PENDIUM_CATALOGO_PODERES_TAB, PENDIUM_OTORGAPODER_EP_TAB\n"+
	                "  WHERE PENDIUM_CATALOGO_PODERES_TAB.ID_PODER_PK=PENDIUM_OTORGAPODER_EP_TAB.NUM_PODERTIPO\n"+
	                "  AND PENDIUM_CATALOGO_PODERES_TAB.IND_PODERTIPO='PG'";
        }
        else if(tiIdCatalogo == 63){
        	lsSql = "SELECT COUNT(NUM_PODERTIPO)\n"+
                    "  FROM PENDIUM_CATALOGO_PODERES_TAB, PENDIUM_OTORGAPODER_EP_TAB\n"+
                    "  WHERE PENDIUM_CATALOGO_PODERES_TAB.ID_PODER_PK=PENDIUM_OTORGAPODER_EP_TAB.NUM_PODERTIPO\n"+
	                "  AND PENDIUM_CATALOGO_PODERES_TAB.IND_PODERTIPO='PE'";
        }
        try {
        	if(tiIdCatalogo == 62 || tiIdCatalogo == 63){        		        		
        		puConnectionWrapper  = new ConnectionWrapper();
	            luPreparedStatement = puConnectionWrapper.prepareStatement(lsSql);	            
	            luResultSet = luPreparedStatement.executeQuery();
	            
	            while(luResultSet.next()){
	                liContadorA = luResultSet.getInt(1);
	            }
        	}
	        else{
	            lsSql = "SELECT count(*)\n"+
	                    " FROM dercorp_add_campo_tab\n"+
	                    "	WHERE id_catalogo = ?";
	            lsSql2 = "SELECT count(*)\n"+
	                      " FROM dercorp_flex_colums_tab\n"+ 
	                      "	WHERE id_catalogo = ?";
	
	            puConnectionWrapper  = new ConnectionWrapper();
	            luPreparedStatement = puConnectionWrapper.prepareStatement(lsSql);
	            luPreparedStatement.setInt(1, tiIdCatalogo);
	            luResultSet = luPreparedStatement.executeQuery();
	
	            while(luResultSet.next()){
	                liContadorA = luResultSet.getInt(1);
	            }
	
	            luPreparedStatement2 = puConnectionWrapper.prepareStatement(lsSql2);
	            luPreparedStatement2.setInt(1, tiIdCatalogo);
	            luResultSet2 = luPreparedStatement2.executeQuery();
	
	            while(luResultSet2.next()){
	                liContadorB = luResultSet2.getInt(1);
	            }
	        }
        } catch (Exception e) {
            e.printStackTrace();

        }finally {
            try {
            	if(tiIdCatalogo == 62 || tiIdCatalogo == 63){
            	   luResultSet.close();                   
                   luPreparedStatement.close();                   
               }
               else{
            	   luResultSet.close();
                   luResultSet2.close();
                   luPreparedStatement.close();
                   luPreparedStatement2.close();
               }            	
                puConnectionWrapper.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
		}

        liResultado = liContadorA + liContadorB;

        if(liResultado > 0){
            lbOK = true;
        }

        return lbOK;
	 }

    /**
     * Elimina el cat�logo
     * @param tiIdCatalogo
     * @return boolean
     */
    public boolean eliminaCatalogo(int tiIdCatalogo,SessionBean sessionBean){
        boolean           lbOK                 = false;
        String            luSql                = "";
        String            luDelete             = "";
        String            luDelete2            = "";
        PreparedStatement luPreparedStatement  = null;
        PreparedStatement luPreparedStatement2 = null;
        PreparedStatement luPreparedStatement3 = null;
        Connection        luConnection         = null;
        CallableStatement cstmt				   = null;

        if(catalogoUtilizado(tiIdCatalogo)){
            return false;
        }
        else{
            luSql     = "DELETE FROM dercorp_add_campo_cat_tab WHERE id_catalogo = ?";
            if(tiIdCatalogo == 62){
            	luDelete = "DELETE FROM PENDIUM_CATALOGO_PODERES_TAB WHERE IND_PODERTIPO='PG'";
            }
            else if(tiIdCatalogo == 63){
            	luDelete = "DELETE FROM PENDIUM_CATALOGO_PODERES_TAB WHERE IND_PODERTIPO='PE'";
            }
            else{
	            luDelete  = "DELETE FROM dercorp_add_campo_cat_val_tab\n"+
						     "WHERE id_catalogo = ?";
	
	            luDelete2 = "DELETE FROM DERCORP_FLEX_COLUMNS_CATGO_TAB WHERE id_catalogo = ?";
            }

            try {
            	CatalogoBean catalogoBean = obtenCatalogoId(tiIdCatalogo);
                puConnectionWrapper = new ConnectionWrapper();
                luConnection = puConnectionWrapper.getConnection();
                //luConnection.setAutoCommit(false);
                
                if(tiIdCatalogo == 62 || tiIdCatalogo == 63){
                	luPreparedStatement = puConnectionWrapper.prepareStatement(luDelete);                    
                    luPreparedStatement.executeUpdate();
                    luPreparedStatement2 = puConnectionWrapper.prepareStatement(luSql);
                    luPreparedStatement2.setInt(1, tiIdCatalogo);
                    luPreparedStatement2.executeUpdate();
                }
                else{
                	luPreparedStatement = puConnectionWrapper.prepareStatement(luDelete);
                    luPreparedStatement.setInt(1, tiIdCatalogo);
                    luPreparedStatement.executeUpdate();
                    luPreparedStatement2 = puConnectionWrapper.prepareStatement(luSql);
                    luPreparedStatement2.setInt(1, tiIdCatalogo);
                    luPreparedStatement2.executeUpdate();
                    luPreparedStatement3 = puConnectionWrapper.prepareStatement(luDelete2);
                    luPreparedStatement3.setInt(1, tiIdCatalogo);
                    luPreparedStatement3.executeUpdate();
                }
                
                //luConnection.commit();
                //luConnection.setAutoCommit(true);
                lbOK = true;
                
                //Llama al procedimeinto para insertar en la bitacora de modificaciones DERCORP_BITA_MOD_CAT_TAB
                cstmt = luConnection.prepareCall("{CALL DERCORP_CATALOGS_PKG.DELETE_BITACORA_CATGO_PR(?,?)}");
                
                cstmt.setString(1, catalogoBean.getNombre());
                cstmt.setString(2, sessionBean.getIdUser());
                cstmt.execute();

			} catch (Exception e) {
				e.printStackTrace();
				lbOK = false;

			}finally {
				try {
					luPreparedStatement2.close();
					luPreparedStatement.close();
					puConnectionWrapper.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		 }
		 return lbOK;
	 }

    /**
     * Insertar columnas a las flextable
     * 
     * @param tuCatalogoBean
     * @param tuFlexColumnsCatagoBean
     * @return boolean
     */
    private boolean insetFelxColumns(CatalogoBean tuCatalogoBean, FlexColumnsCatagoBean tuFlexColumnsCatagoBean){
        String            lstInsert           = "";
        boolean           lbOK                = false;
        PreparedStatement luPreparedStatement = null;

        try {
            lstInsert = "INSERT INTO DERCORP_FLEX_COLUMNS_CATGO_TAB(\n"+
		                "			              id_catalogo,\n"+
		                "				              id_identificador,\n"+
		                "				              nom_nombre,\n"+
		                "				              nom_valor,\n"+
		                "				              des_descripcion,\n"+
		                "				              des_attr1,\n"+
		                "				              des_attr2,\n"+
		                "				              des_attr3," +
		                "							  fec_creation_date)\n"+
		                "				VALUES(?,?,?,?,?,?,?,?,sysdate)";

            puConnectionWrapper = new ConnectionWrapper();
            luPreparedStatement = puConnectionWrapper.prepareStatement(lstInsert);
            luPreparedStatement.setInt(1, tuCatalogoBean.getIdCatalogo());
            luPreparedStatement.setString(2, tuFlexColumnsCatagoBean.getIdentificador());
            luPreparedStatement.setString(3, tuFlexColumnsCatagoBean.getNombre());
            luPreparedStatement.setString(4, tuFlexColumnsCatagoBean.getValor());
            luPreparedStatement.setString(5, tuFlexColumnsCatagoBean.getDescripcion());
            luPreparedStatement.setString(6, tuFlexColumnsCatagoBean.getAttr1());
            luPreparedStatement.setString(7, tuFlexColumnsCatagoBean.getAttr2());
            luPreparedStatement.setString(8, tuFlexColumnsCatagoBean.getAttr3());
            luPreparedStatement.executeUpdate();
            lbOK = true;

        } catch (Exception e) {
            e.printStackTrace();
            lbOK = false;
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
     * Contar la columnas de las flextable
     * @param liIdCatalogo
     * @return int
     */
    private int countFlexColumns(int liIdCatalogo){
        String            lsQuery             = "";
        int               liCount             = 0;
        PreparedStatement luPreparedStatement = null;
        ResultSet         luResultSet         = null;

        try {
			lsQuery = "SELECT COUNT(*)\n"+ 
					   " FROM DERCORP_FLEX_COLUMNS_CATGO_TAB\n"+
					   " WHERE id_catalogo = ?";

            puConnectionWrapper = new ConnectionWrapper();
            luPreparedStatement = puConnectionWrapper.prepareStatement(lsQuery);
            luPreparedStatement.setInt(1, liIdCatalogo);
            luResultSet = luPreparedStatement.executeQuery();

            while(luResultSet.next()){
                liCount = luResultSet.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();

        }finally {
            try {
                luPreparedStatement.close();
                luResultSet.close();
                puConnectionWrapper.close();
            } catch (SQLException e) {
				e.printStackTrace();
			}	
		}
        return liCount;
	 }

    /**
     * Edita el catalogo
     * 
     * @param tuCatalogoBean
     * @param tuFlexColumnsCatagoBean
     * @return boolean
     */
    public boolean editaCatalogo(CatalogoBean tuCatalogoBean,	
    							 FlexColumnsCatagoBean tuFlexColumnsCatagoBean,
    							 SessionBean sessionBean){
        boolean 		   lbOK                = false;
        PreparedStatement luPreparedStatement = null;
        PreparedStatement luPreparedStatement2 = null;
        String            lsSql               = "";
        String            lsSql2              = "";
        Connection        luConnection        = null;
        int               liExisteFlex        = 0;
        CallableStatement cstmt				  = null;

        try {
            lsSql = "UPDATE  dercorp_add_campo_cat_tab SET\n"+
                    "	        cod_catalogo = ?,\n"+
                    "	        nom_catalogo = ?,\n"+
                    "	        des_catalogo = ?,\n"+
                    "	        atributo1    = ?,\n"+
                    "	        atributo2    = ?,\n"+
                    "	        atributo3    = ?,\n"+
                    "	        NUM_LAST_UPDATED_BY     = ?,\n"+ 
                    "	        FEC_LAST_UPDATE_DATE    = sysdate\n"+
                    "	WHERE id_catalogo    = ?";

            lsSql2 =  "UPDATE DERCORP_FLEX_COLUMNS_CATGO_TAB SET\n"+
                      "		          id_identificador = ?,\n"+
                      "	              nom_nombre       = ?,\n"+
                      "	              nom_valor        = ?,\n"+
                      "	              des_descripcion  = ?,\n"+
                      "	              des_attr1        = ?,\n"+
                      "	              des_attr2        = ?,\n"+
                      "	              des_attr3        = ?\n"+
                      "	WHERE id_catalogo              = ? ";

            puConnectionWrapper = new ConnectionWrapper();
            luConnection = puConnectionWrapper.getConnection();
            //luConnection.setAutoCommit(false);
            luPreparedStatement = luConnection.prepareStatement(lsSql);
            luPreparedStatement.setString(1, tuCatalogoBean.getCodigo());
            luPreparedStatement.setString(2, tuCatalogoBean.getNombre());
            luPreparedStatement.setString(3, tuCatalogoBean.getDescripcion());
            luPreparedStatement.setString(4, tuCatalogoBean.getAttr1());
            luPreparedStatement.setString(5, tuCatalogoBean.getAttr2());
            luPreparedStatement.setString(6, tuCatalogoBean.getAttr3());
            luPreparedStatement.setString(7, sessionBean.getIdUser());
            luPreparedStatement.setInt(8, tuCatalogoBean.getIdCatalogo());
            
            
            luPreparedStatement.executeUpdate();
            liExisteFlex = countFlexColumns(tuCatalogoBean.getIdCatalogo());

            if(liExisteFlex > 0){
                try{ 
            	luPreparedStatement2 = luConnection.prepareStatement(lsSql2);
                luPreparedStatement2.setString(1, tuFlexColumnsCatagoBean.getIdentificador());
                luPreparedStatement2.setString(2, tuFlexColumnsCatagoBean.getNombre());
                luPreparedStatement2.setString(3, tuFlexColumnsCatagoBean.getValor());
                luPreparedStatement2.setString(4, tuFlexColumnsCatagoBean.getDescripcion());
                luPreparedStatement2.setString(5, tuFlexColumnsCatagoBean.getAttr1());
                luPreparedStatement2.setString(6, tuFlexColumnsCatagoBean.getAttr2());
                luPreparedStatement2.setString(7, tuFlexColumnsCatagoBean.getAttr3());
                luPreparedStatement2.setInt(8, tuCatalogoBean.getIdCatalogo());
                luPreparedStatement2.executeUpdate();
                //luConnection.commit();
                //luConnection.setAutoCommit(true);
                lbOK = true;
                } catch (Exception e) {
                    e.printStackTrace();
                    lbOK = false;
                }finally {
                    try {
                        luPreparedStatement2.close();


                    } catch (SQLException e) {
                        e.printStackTrace();

                    }
                }    

			}else{
				lbOK = insetFelxColumns(tuCatalogoBean,tuFlexColumnsCatagoBean);

			}
          //Llama al procedimeinto para insertar en la bitacora de modificaciones DERCORP_BITA_MOD_CAT_TAB
            cstmt = luConnection.prepareCall("{CALL DERCORP_CATALOGS_PKG.INSERT_BITACORA_CATGO_PR(?,?,?)}");
            //cstmt = puConnectionWrapper.prepareCall(APP_CONFIG_PKG.DERCORP_CATALOGS_BIT_PKG);
            cstmt.setInt(1, tuCatalogoBean.getIdCatalogo());
            cstmt.setString(2, sessionBean.getIdUser());
            cstmt.setString(3, "MODIFICACION");
            cstmt.execute();

        } catch (Exception e) {
            e.printStackTrace();
            lbOK = false;
        }finally {
            try {
                luPreparedStatement.close();
                //luPreparedStatement2.close();
                puConnectionWrapper.close();
                luConnection.close();

            } catch (SQLException e) {
                e.printStackTrace();

            }
        }
        return lbOK;
    }

    /**
     * Obtiene el id del cat�logo
     * 
     * @param tiIdCatalogo
     * @return objeto
     */
    public CatalogoBean obtenCatalogoId(int tiIdCatalogo){
        String            lsSql               = "";
        PreparedStatement luPreparedStatement = null;
        ResultSet         luResultSet         = null;

        try {
            lsSql = "SELECT id_catalogo,\n"+
                    " cod_catalogo,\n"+
                    " nom_catalogo,\n"+
                    " des_catalogo,\n"+
                    " atributo1,\n"+
                    " atributo2,\n"+
                    " atributo3\n"+
                    " FROM dercorp_add_campo_cat_tab\n"+ 
                    "WHERE id_catalogo = ?\n"+
                    " ORDER BY id_catalogo DESC";

            puConnectionWrapper = new ConnectionWrapper();
            luPreparedStatement = puConnectionWrapper.prepareStatement(lsSql);
            luPreparedStatement.setInt(1, tiIdCatalogo);
            luResultSet = luPreparedStatement.executeQuery();

            while(luResultSet.next()){
                puCatalogoBean = new CatalogoBean();
                puCatalogoBean.setIdCatalogo(luResultSet.getInt(1));
                puCatalogoBean.setCodigo(luResultSet.getString(2));
                puCatalogoBean.setNombre(luResultSet.getString(3));
                puCatalogoBean.setDescripcion(luResultSet.getString(4));
                puCatalogoBean.setAttr1(luResultSet.getString(5));
                puCatalogoBean.setAttr2(luResultSet.getString(6));
                puCatalogoBean.setAttr3(luResultSet.getString(7));

            }
        } catch (Exception e) {
            e.printStackTrace();

        }finally {
            puConnectionWrapper.close();

        }

        return puCatalogoBean;

    }

    /**
     * Obtiene columna para la flexcolumn
     * @param tiIdCatalogo
     * @return objeto
     */
    public FlexColumnsCatagoBean obtenFlexColumn(int tiIdCatalogo){		 
        String            lsSql               = "";
        PreparedStatement luPreparedStatement = null;
        ResultSet         luResultSet         = null;

        try {
            lsSql = "SELECT id_catalogo,\n"+
                    "	      id_identificador,\n"+
                    "	      nom_nombre,\n"+
                    "	      nom_valor,\n"+
                    "	      des_descripcion,\n"+
                    "	      des_attr1,\n"+
                    "	      des_attr2,\n"+
                    "	      des_attr3 \n"+
                    "	FROM DERCORP_FLEX_COLUMNS_CATGO_TAB\n"+
                    "	WHERE id_catalogo = ?";

            puConnectionWrapper = new ConnectionWrapper();
            luPreparedStatement = puConnectionWrapper.prepareStatement(lsSql);
            luPreparedStatement.setInt(1, tiIdCatalogo);
            luResultSet = luPreparedStatement.executeQuery();

            while(luResultSet.next()){
                puFlexColumnsCatagoBean = new FlexColumnsCatagoBean();
                puFlexColumnsCatagoBean.setIdCatalogo(luResultSet.getInt(1));
                puFlexColumnsCatagoBean.setIdentificador(luResultSet.getString(2));
                puFlexColumnsCatagoBean.setNombre(luResultSet.getString(3));
                puFlexColumnsCatagoBean.setValor(luResultSet.getString(4));
                puFlexColumnsCatagoBean.setDescripcion(luResultSet.getString(5));
                puFlexColumnsCatagoBean.setAttr1(luResultSet.getString(6));
                puFlexColumnsCatagoBean.setAttr2(luResultSet.getString(7));
                puFlexColumnsCatagoBean.setAttr3(luResultSet.getString(8));

			}
        } catch (Exception e) {
            e.printStackTrace();

        }finally {
            puConnectionWrapper.close();

        }

        return puFlexColumnsCatagoBean;
	 }

    /**
     * Obtiene cat�logos
     * @return Arreglo de objetos
     */
    public List<CatalogoBean> obtenCatalogos(){
        String            lsSql               = "";
        PreparedStatement luPreparedStatement = null;
        ResultSet         luResultSet         = null;

        paCatalogoBean = new ArrayList<>();

        try {
            lsSql = "SELECT id_catalogo,\n"+
                    " cod_catalogo,\n"+
                    " nom_catalogo,\n"+
                    " des_catalogo,\n"+
                    " atributo1,\n"+
                    " atributo2,\n"+
                    " atributo3\n"+
                    " FROM dercorp_add_campo_cat_tab\n"+ 
                    " WHERE id_catalogo <> 0\n"+
                    " ORDER BY nom_catalogo";

            puConnectionWrapper = new ConnectionWrapper();
            luPreparedStatement = puConnectionWrapper.prepareStatement(lsSql);
            luResultSet = luPreparedStatement.executeQuery();

            while(luResultSet.next()){
                puCatalogoBean = new CatalogoBean();
                puCatalogoBean.setIdCatalogo(luResultSet.getInt(1));
                puCatalogoBean.setCodigo(luResultSet.getString(2));
                puCatalogoBean.setNombre(luResultSet.getString(3));
                puCatalogoBean.setDescripcion(luResultSet.getString(4));
                puCatalogoBean.setAttr1(luResultSet.getString(5));
                puCatalogoBean.setAttr2(luResultSet.getString(6));
                puCatalogoBean.setAttr3(luResultSet.getString(7));
                paCatalogoBean.add(puCatalogoBean);

            }
        }catch (Exception e) {
            e.printStackTrace();

        }finally {
            puConnectionWrapper.close();

        }

        return paCatalogoBean;

    }

    /**
     * Inserta cat�logos
     * 
     * @param tuCatalogoBean
     * @param tuFlexColumnsCatagoBean
     * @return boolean
     */
    public boolean insertCatalogo(CatalogoBean tuCatalogoBean, 
    							   FlexColumnsCatagoBean tuFlexColumnsCatagoBean,
    							   SessionBean sessionBean){
        boolean           lbPaso              = false;
        String            lsSqlMax            = "";
        PreparedStatement luPreparedStatement = null;
        CallableStatement cstmt				  = null;
        int               liSecuencia         = 0;
        ResultSet         luResultSet         = null;
        String            lsSql               = "";
        String            lsSql2              = "";
        

        try {
            lsSqlMax = "SELECT MAX(id_catalogo) FROM dercorp_add_campo_cat_tab";
            puConnectionWrapper = new ConnectionWrapper();
            luPreparedStatement = puConnectionWrapper.prepareStatement(lsSqlMax);
            luResultSet = luPreparedStatement.executeQuery();

            while(luResultSet.next()){
                liSecuencia = luResultSet.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();

        }finally {
            try {
                luPreparedStatement.close();
                luResultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        liSecuencia = liSecuencia + 1;
        lsSql = "INSERT INTO dercorp_add_campo_cat_tab(id_catalogo,\n"+
                "                cod_catalogo,\n"+
                "                nom_catalogo,\n"+
                "                des_catalogo,\n"+
                "                fec_creation_date,\n"+
                "                atributo1,\n"+
                "                atributo2,\n"+
                "                atributo3,\n"+
                "                NUM_CREATED_BY)\n"+
                "VALUES(?,?,?,?,sysdate,?,?,?,?)";

        lsSql2 = "INSERT INTO DERCORP_FLEX_COLUMNS_CATGO_TAB(\n"+
                 "			              id_catalogo,\n"+
                 "				              id_identificador,\n"+
                 "				              nom_nombre,\n"+
                 "				              nom_valor,\n"+
                 "				              des_descripcion,\n"+
                 "				              des_attr1,\n"+
                 "				              des_attr2,\n"+
                 "				              des_attr3," +
                 "							  fec_creation_date)\n"+
                 "				VALUES(?,?,?,?,?,?,?,?,sysdate)";
        
        
        
        try {
            puConnectionWrapper.begin();
            luPreparedStatement = puConnectionWrapper.prepareStatement(lsSql);
            luPreparedStatement.setInt(1, liSecuencia);
            luPreparedStatement.setString(2, tuCatalogoBean.getCodigo());
            luPreparedStatement.setString(3, tuCatalogoBean.getNombre());
            luPreparedStatement.setString(4, tuCatalogoBean.getDescripcion());
            luPreparedStatement.setString(5, tuCatalogoBean.getAttr1());
            luPreparedStatement.setString(6, tuCatalogoBean.getAttr2());
            luPreparedStatement.setString(7, tuCatalogoBean.getAttr3());
            luPreparedStatement.setString(8, sessionBean.getIdUser());
            
            luPreparedStatement.executeUpdate();
            luPreparedStatement = puConnectionWrapper.prepareStatement(lsSql2);
            luPreparedStatement.setInt(1, liSecuencia);
            luPreparedStatement.setString(2, tuFlexColumnsCatagoBean.getIdentificador());
            luPreparedStatement.setString(3, tuFlexColumnsCatagoBean.getNombre());
            luPreparedStatement.setString(4, tuFlexColumnsCatagoBean.getValor());
            luPreparedStatement.setString(5, tuFlexColumnsCatagoBean.getDescripcion());
            luPreparedStatement.setString(6, tuFlexColumnsCatagoBean.getAttr1());
            luPreparedStatement.setString(7, tuFlexColumnsCatagoBean.getAttr2());
            luPreparedStatement.setString(8, tuFlexColumnsCatagoBean.getAttr3());
            luPreparedStatement.executeUpdate();
            
            //Llama al procedimeinto para insertar en la bitacora de modificaciones DERCORP_BITA_MOD_CAT_TAB
            cstmt = puConnectionWrapper.prepareCall(APP_CONFIG_PKG.DERCORP_CATALOGS_BIT_PKG);
            cstmt.setInt(1, liSecuencia);
            cstmt.setString(2, sessionBean.getIdUser());
            cstmt.setString(3, "NUEVO");
            cstmt.execute();
            puConnectionWrapper.commit();
            lbPaso = true;

        } catch (SQLException e) {
            e.printStackTrace();
            lbPaso = false;
        }finally {
            try {
                luPreparedStatement.close();
                puConnectionWrapper.close();
                if(cstmt != null){
                	cstmt.close();
                }
                
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return lbPaso;

    }
    
    public List<CatalogoBean> obtenerCatalogoBusqueda(String tsInteligente){
    	paCatalogoBean = new ArrayList<>();
        PreparedStatement luPreparedStatement = null;
        ResultSet 		  luResultSet         = null;
        String            lsQuery;
        
        try{
	        	lsQuery = "SELECT   ID_CATALOGO,"+
				                    " COD_CATALOGO,"+
				                    " NOM_CATALOGO,"+
				                    " DES_CATALOGO"+
						  " FROM     DERCORP_ADD_CAMPO_CAT_TAB"+
						  " WHERE    1=1"+
						  " AND      (APP_COMMON_PKG.SIN_ACENTOS_FN(UPPER(COD_CATALOGO)) LIKE APP_COMMON_PKG.SIN_ACENTOS_FN(UPPER(?))"+
						  " OR       APP_COMMON_PKG.SIN_ACENTOS_FN(UPPER(NOM_CATALOGO)) LIKE APP_COMMON_PKG.SIN_ACENTOS_FN(UPPER(?))"+
						  " OR       APP_COMMON_PKG.SIN_ACENTOS_FN(UPPER(DES_CATALOGO)) LIKE APP_COMMON_PKG.SIN_ACENTOS_FN(UPPER(?)))"+
						  " AND     ID_CATALOGO <> 0 order by NOM_CATALOGO"
				  ;
	        	
	        	System.out.println("Query: "+lsQuery);

	        	puConnectionWrapper  = new ConnectionWrapper();
	            luPreparedStatement = puConnectionWrapper.prepareStatement(lsQuery);
	            luPreparedStatement.setString(1, "%"+tsInteligente+"%");
	            luPreparedStatement.setString(2, "%"+tsInteligente+"%");
	            luPreparedStatement.setString(3, "%"+tsInteligente+"%");
	            
	            luResultSet = luPreparedStatement.executeQuery();
	            
	            while(luResultSet.next()){
	            	puCatalogoBean = new CatalogoBean();
	            	//System.out.println("1: "+luResultSet.getInt(1));
	            	//System.out.println("2: "+luResultSet.getString(2));
	            	//System.out.println("3: "+luResultSet.getString(3));
	            	//System.out.println("4: "+luResultSet.getString(4));
	            	puCatalogoBean.setIdCatalogo(luResultSet.getInt(1));
	            	puCatalogoBean.setCodigo(luResultSet.getString(2));
	            	puCatalogoBean.setNombre(luResultSet.getString(3));
	            	puCatalogoBean.setDescripcion(luResultSet.getString(4));
	            	paCatalogoBean.add(puCatalogoBean);
	            }

        }catch(Exception e){
        	e.printStackTrace();
        }finally {
                try {
                    luPreparedStatement.close();
                    luResultSet.close();
                    puConnectionWrapper.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }

    	return paCatalogoBean;
    }
    
    public int getDatosFlex(int idFlex, int idValor){
		int liSuperIndice = 0;

		ConnectionWrapper puConnectionWrapper = null;
		
		try {
			puConnectionWrapper = new ConnectionWrapper();
			puCallableStatement = puConnectionWrapper.prepareCall(DERCORP_CONSULTA_PKG.GET_DATOS_EN_FLEX_PR);
			puCallableStatement.setInt(1, idFlex);
			puCallableStatement.setInt(2, idValor);
			puCallableStatement.registerOutParameter(3, OracleTypes.NUMBER);
			puCallableStatement.execute();

			liSuperIndice = ((OracleCallableStatement) puCallableStatement).getInt(3);

		} catch (Exception e) {
            e.printStackTrace();
            liSuperIndice = 1;
		}finally{
			try {
				puCallableStatement.close();
				puConnectionWrapper.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return liSuperIndice;

	}

}
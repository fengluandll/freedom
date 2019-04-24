/**
* Project: Derecho Corporativo
*
* File: ClasificacionDAO.java
*
* Created on: Agosto 31, 2015 at 12:00
*
* Copyright (c) - Televisa - 2015
*/

package mx.com.televisa.derechocorporativo.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import mx.com.televisa.derechocorporativo.bean.ClasificacionBean;
import mx.com.televisa.derechocorporativo.bean.PaisBean;
import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;

/**
 * Clasificar los checkbox
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
public class ClasificacionDAO {
	 ConnectionWrapper 		 puConnectionWrapper;
	 ClasificacionBean 		 puClasificacionBean;
	 PaisBean		   		 puPaisBean;
	 List<ClasificacionBean> paClasificacionBean;
	 List<PaisBean> 		 paPaisBean;

    /**
     * Devuelve arreglo de checkbox
     * @return Arreglo de objetos
     */
    public List<ClasificacionBean> dameClasificacionChecks(){
        String lsSql = "";
        PreparedStatement luPreparedStatement = null;
        ResultSet         luResultSet         = null;

        try {
            paClasificacionBean = new ArrayList<ClasificacionBean>();
            lsSql = "SELECT ID_CATALOGO_VALOR,VAL_CAT_VAL \n"+
            "	FROM dercorp_add_campo_cat_val_tab \n"+
            "	WHERE id_catalogo = 6";

            puConnectionWrapper = new ConnectionWrapper();
            luPreparedStatement = puConnectionWrapper.prepareStatement(lsSql);
            luResultSet = luPreparedStatement.executeQuery();

            while(luResultSet.next()){
                puClasificacionBean = new ClasificacionBean();
                puClasificacionBean.setIdCatalogoValor(luResultSet.getInt(1));
                puClasificacionBean.setValCatVal(luResultSet.getString(2));
                paClasificacionBean.add(puClasificacionBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            puConnectionWrapper.close();
        }
        
        return paClasificacionBean;
    }
    
    /**
     * 
     * @param idCatalogo
     * @return los valores de los catalogos
     */
    public List<ClasificacionBean> dameCatalogoChecks(int idCatalogo){
        String lsSql = "";
        PreparedStatement luPreparedStatement = null;
        ResultSet         luResultSet         = null;

        try {
            paClasificacionBean = new ArrayList<ClasificacionBean>();
            lsSql = "SELECT ID_CATALOGO_VALOR,VAL_CAT_VAL \n"+
            "	FROM dercorp_add_campo_cat_val_tab \n"+
            "	WHERE id_catalogo = "+idCatalogo;

            puConnectionWrapper = new ConnectionWrapper();
            luPreparedStatement = puConnectionWrapper.prepareStatement(lsSql);
            luResultSet = luPreparedStatement.executeQuery();

            while(luResultSet.next()){
                puClasificacionBean = new ClasificacionBean();
                puClasificacionBean.setIdCatalogoValor(luResultSet.getInt(1));
                puClasificacionBean.setValCatVal(luResultSet.getString(2));
                paClasificacionBean.add(puClasificacionBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            puConnectionWrapper.close();
        }
        
        return paClasificacionBean;
    }


	 public static void main(String args[]){
		 for(int i = 1;i<8;i++){
			 float res = i % 3;
			 System.out.println(res);
		 }
	 }

    public List<PaisBean> damePaises(){
        String            lsSql               = "";
        PreparedStatement luPreparedStatement = null;
        ResultSet         luResultSet         = null;

        paPaisBean = new ArrayList<PaisBean>();

        try {
            lsSql = "SELECT DISTINCT \n" +
            	    "   pais.id_catalogo_valor, \n" +
            	    "   pais.val_cat_val \n" +
            	    "FROM   dercorp_add_campo_cat_val_tab pais, \n" +
            	    "       dercorp_add_campo_valor_tab reg \n" +
            	    "WHERE  pais.id_catalogo_valor = reg.val_valor \n" +
            	    "AND    reg.id_add_campo = 509 \n" + 
            	    "AND    pais.id_catalogo = 7 \n" +
            	    "ORDER BY UPPER(APP_COMMON_PKG.SIN_ACENTOS_FN(pais.val_cat_val))";
            		
            puConnectionWrapper = new ConnectionWrapper();
            luPreparedStatement = puConnectionWrapper.prepareStatement(lsSql);
            //System.out.println("Query paises: "+ lsSql);
            luResultSet = luPreparedStatement.executeQuery();

            while(luResultSet.next()){
                puPaisBean = new PaisBean();
                puPaisBean.setIdCatalogoValor(luResultSet.getInt(1));
                puPaisBean.setValCatVal(luResultSet.getString(2));
                paPaisBean.add(puPaisBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            puConnectionWrapper.close();
        }

        return paPaisBean;

    }
    
    public List<PaisBean> damePaisesCompleto(){
        String            lsSql               = "";
        PreparedStatement luPreparedStatement = null;
        ResultSet         luResultSet         = null;

        paPaisBean = new ArrayList<PaisBean>();

        try {
            lsSql = "SELECT \n" +
            	    "   pais.id_catalogo_valor, \n" +
            	    "   pais.val_cat_val \n" +
            	    "FROM   dercorp_add_campo_cat_val_tab pais\n" +
            	    "WHERE    pais.id_catalogo = 7 \n" +
            	    "ORDER BY UPPER(APP_COMMON_PKG.SIN_ACENTOS_FN(pais.val_cat_val))";
            		
            puConnectionWrapper = new ConnectionWrapper();
            luPreparedStatement = puConnectionWrapper.prepareStatement(lsSql);
            //System.out.println("Query paises: "+ lsSql);
            luResultSet = luPreparedStatement.executeQuery();

            while(luResultSet.next()){
                puPaisBean = new PaisBean();
                puPaisBean.setIdCatalogoValor(luResultSet.getInt(1));
                puPaisBean.setValCatVal(luResultSet.getString(2));
                paPaisBean.add(puPaisBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            puConnectionWrapper.close();
        }

        return paPaisBean;

    }
}

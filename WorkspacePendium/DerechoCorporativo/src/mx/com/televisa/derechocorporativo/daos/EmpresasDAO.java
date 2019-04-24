/**
* Project: Derecho Corporativo
*
* File: ???¿¿¿.java
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
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import mx.com.televisa.derechocorporativo.bean.EmpresasBean;
import mx.com.televisa.derechocorporativo.bean.SessionBean;
import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;

/**
 * 
 * Clase que interactua con la base de datos
 * todo lo relacionado a la administracion
 * de empresas
 *
 */
public class EmpresasDAO {	
	ConnectionWrapper 	puConnectionWrapper;
	EmpresasBean        puEmpresasBean;
	List<EmpresasBean>  paEmpresasBean;
	
	final static Logger log = Logger.getLogger(EmpresasDAO.class);
	
	/**
	 * Metodo constructor
	 */
    public EmpresasDAO(){
        puEmpresasBean = new EmpresasBean();
    }

	/**
	 * Metodo que obtiene una empresa
	 * @return regresa un bena de empresas
	 */
    public EmpresasBean dameEmpresa(int tiIdEmpresa){
        String            lsSql               = "";
        PreparedStatement luPreparedStatement = null;
        ResultSet 		  luResultSet         = null;

        try {
            lsSql =  " SELECT emp.id_empresa,\n"+
                     "  emp.cve_empresa,\n"+
                     "  emp.nom_empresa,\n"+
                     "  emp.atributo1,\n"+
                     "  emp.atributo2, \n"+
                     "(SELECT id_catalogo_valor \n"+
                     "   FROM DERCORP_ADD_CAMPO_CAT_VAL_TAB \n"+
                     "   WHERE id_catalogo = 7\n"+
                     "   AND val_cat_val = emp.atributo2) idPais,\n"+
                     "  emp.atributo3 \n"+
                     " FROM dercorp_empresa_tab emp\n"+
                     " WHERE id_empresa = ?";

            puConnectionWrapper = new ConnectionWrapper();
            luPreparedStatement = puConnectionWrapper.prepareStatement(lsSql);
            luPreparedStatement.setInt(1, tiIdEmpresa);
            luResultSet = luPreparedStatement.executeQuery();

            while(luResultSet.next()){
                puEmpresasBean = new EmpresasBean();
                puEmpresasBean.setIdEmpresa(luResultSet.getInt(1));
                puEmpresasBean.setCveEmpresa(luResultSet.getString(2));
                puEmpresasBean.setNomEmpresa(luResultSet.getString(3));
                puEmpresasBean.setAttr1(luResultSet.getString(4));
                puEmpresasBean.setAttr2(luResultSet.getString(5));
                puEmpresasBean.setIdPais(luResultSet.getString(6));
                puEmpresasBean.setAttr3(luResultSet.getString(7));
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

        return puEmpresasBean;

    }

	/**
	 * Metodo que obtiene todas las empresas de aceurdo
	 * a un criterio de busqueda
	 * @return regresa una lista de empresas
	 */
    public List<EmpresasBean> dameEmpresasGeneral(String tsInteligente){
        String            lsSql               = "";
        PreparedStatement luPreparedStatement = null;
        ResultSet 		  luResultSet         = null;

        paEmpresasBean = new ArrayList<>();

        try{
            lsSql =  "SELECT    ID_EMPRESA, "        +
					          "NOM_EMPRESA, "        +
					          "CVE_EMPRESA, "        +
					          "ATRIBUTO1, "          +
					          "ATRIBUTO2 "           +
				    "FROM      DERCORP_EMPRESA_TAB " +
                     "WHERE UPPER(cve_empresa)   like UPPER(?)\n"+ 
                     "	OR UPPER(nom_empresa)    like UPPER(?)\n"+
                     "	OR UPPER(atributo1)      like UPPER(?)\n"+
                     "	OR UPPER(atributo2)      like UPPER(?)\n"+	
                     "ORDER BY  NOM_EMPRESA ASC"
                     ;

            puConnectionWrapper  = new ConnectionWrapper();
            luPreparedStatement = puConnectionWrapper.prepareStatement(lsSql);
            luPreparedStatement.setString(1, "%"+tsInteligente+"%");
            luPreparedStatement.setString(2, "%"+tsInteligente+"%");
            luPreparedStatement.setString(3, "%"+tsInteligente+"%");
            luPreparedStatement.setString(4, "%"+tsInteligente+"%");
            luResultSet = luPreparedStatement.executeQuery();

            while(luResultSet.next()){
                puEmpresasBean = new EmpresasBean();
                puEmpresasBean.setIdEmpresa(luResultSet.getInt(1));
                puEmpresasBean.setNomEmpresa(luResultSet.getString(2));
                puEmpresasBean.setCveEmpresa(luResultSet.getString(3));
                puEmpresasBean.setAttr1(luResultSet.getString(4));
                puEmpresasBean.setAttr2(luResultSet.getString(5));
                paEmpresasBean.add(puEmpresasBean);
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
        
        return paEmpresasBean;

	}
	
    /**
     * Metodo que obtiene todas las empresas
     * @return regresa una lista de empresas
     */
    public List<EmpresasBean> dameEmpresas(){
        paEmpresasBean = new ArrayList<>();

        String            lstSql              =  "";
        PreparedStatement luPreparedStatement = null;
        ResultSet         luResultSet         = null;

        try {
            lstSql =    "SELECT    ID_EMPRESA, "        +
						          "NOM_EMPRESA, "        +
						          "CVE_EMPRESA, "        +
						          "ATRIBUTO1, "          +
						          "ATRIBUTO2, "           +
						          "ATRIBUTO3 "           +
					    "FROM      DERCORP_EMPRESA_TAB " +
						"ORDER BY  NOM_EMPRESA ASC"
                        ;

            puConnectionWrapper = new ConnectionWrapper();
            luPreparedStatement = puConnectionWrapper.prepareStatement(lstSql);
            luResultSet = luPreparedStatement.executeQuery();

            while(luResultSet.next()){
                puEmpresasBean = new EmpresasBean();
                puEmpresasBean.setIdEmpresa(luResultSet.getInt(1));
                puEmpresasBean.setNomEmpresa(luResultSet.getString(2));
                puEmpresasBean.setCveEmpresa(luResultSet.getString(3));
                puEmpresasBean.setAttr1(luResultSet.getString(4));
                puEmpresasBean.setAttr2(luResultSet.getString(5));
                puEmpresasBean.setAttr3(luResultSet.getString(6));
                paEmpresasBean.add(puEmpresasBean);
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

        return paEmpresasBean;
}
	
   /**
    * 
    * @param tuEmpresasBean Bean que contiene los datos de la emrpesa
    * @return false si ocurrio un error true si lo inserto bien
    */
    public boolean insertEmpresa(EmpresasBean tuEmpresasBean,SessionBean sessionBean){
	    String            lsSql               = "";
        Connection 		  luConection         = null;
        boolean    		  lbBok               = false;
        CallableStatement luCallableStatement = null;
        String            lsError             = null;

        try {
        	lsSql = "CALL DERCORP_PANEL_CONTROL_PKG.insert_empresa_pr(?,?,?,?,?,?,?)";
            puConnectionWrapper = new ConnectionWrapper();
            luConection = puConnectionWrapper.getConnection();			
            luCallableStatement = luConection.prepareCall(lsSql);
            luCallableStatement.setString(1, tuEmpresasBean.getCveEmpresa());
            luCallableStatement.setString(2, tuEmpresasBean.getNomEmpresa());
            luCallableStatement.setString(3, tuEmpresasBean.getAttr1());
            luCallableStatement.setString(4, tuEmpresasBean.getAttr2());
            luCallableStatement.setString(5, tuEmpresasBean.getIdPais());
            luCallableStatement.setString(6, sessionBean.getIdUser());
            luCallableStatement.registerOutParameter(7, java.sql.Types.VARCHAR);
            //luCallableStatement.setString(8, tuEmpresasBean.getAttr3());
            
            luCallableStatement.execute();
            lsError = luCallableStatement.getString(7);
            
            log.info("Respuesta insertar empresa: "+lsError);

            if(lsError == null){
                lbBok = true;
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
        return lbBok;
    }
	
    /**
     * 
     * @param tuEmpresasBean bena de empresas
     * @return regresa true como exitoso y false como NO exitoso
     */
    public boolean updateEmpresa(EmpresasBean tuEmpresasBean,SessionBean sessionBean){
        String            lsSql               = "";
        Connection 		  luConnection        = null;
        boolean    		  lbBok 		      = false;
        CallableStatement luCallableStatement = null;
        String            lsError             = null;

        try {
        	lsSql = "CALL DERCORP_PANEL_CONTROL_PKG.update_empresa_pr(?,?,?,?,?,?,?,?)";
            puConnectionWrapper = new ConnectionWrapper();
            luConnection = puConnectionWrapper.getConnection();			
            luCallableStatement = luConnection.prepareCall(lsSql);
            luCallableStatement.setInt(1, tuEmpresasBean.getIdEmpresa());
            luCallableStatement.setString(2, tuEmpresasBean.getCveEmpresa());
            luCallableStatement.setString(3, tuEmpresasBean.getNomEmpresa());
            luCallableStatement.setString(4, tuEmpresasBean.getAttr1());
            luCallableStatement.setString(5, tuEmpresasBean.getAttr2());
            luCallableStatement.setString(6, tuEmpresasBean.getIdPais());
            luCallableStatement.setString(7, sessionBean.getIdUser());
            luCallableStatement.registerOutParameter(8, java.sql.Types.VARCHAR);
            //luCallableStatement.setString(9, tuEmpresasBean.getAttr3());
            luCallableStatement.execute();
            lsError = luCallableStatement.getString(8);

            if(lsError == null){
                lbBok = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {				
            try {
                puConnectionWrapper.close();
                luConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return lbBok;

}

    /**
    * Metodo que borra una empresa
    * @param tuEmpresasBean
    * @return regresa true como exitoso y false como NO exitoso
    */
    public boolean deleteEmpresa(EmpresasBean tuEmpresasBean){
        String            lsSql               = "";
        Connection 		  luConnection        = null;
        boolean    		  lbBok               = false;
        CallableStatement luCallableStatement = null;
        String            lsError             = null;

        try {
        	lsSql = "CALL DERCORP_PANEL_CONTROL_PKG.delete_empresa_pr(?,?)";
            puConnectionWrapper = new ConnectionWrapper();
            luConnection = puConnectionWrapper.getConnection();
            luCallableStatement = luConnection.prepareCall(lsSql);
            luCallableStatement.setInt(1, tuEmpresasBean.getIdEmpresa());
            luCallableStatement.registerOutParameter(2, java.sql.Types.VARCHAR);
            luCallableStatement.execute();
            lsError = luCallableStatement.getString(2);

            if(lsError == null){
                lbBok = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {				
            try {
                puConnectionWrapper.close();
                luConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return lbBok;

    }
    
    
    /**
     * NAVA - Abr 21
     * */
    public static boolean isEmpresaDeletable(String pIdEmpresa){
    	
    	ConnectionWrapper conn = null;
		PreparedStatement psmt = null;
		ResultSet set = null;
		
		try{
			conn = new ConnectionWrapper();
			
			String sqlSecciones = "SELECT DERCORP_PANEL_CONTROL_PKG.IS_EMPRESA_DELETABLE_FN(" + pIdEmpresa + ") AS RESULT FROM DUAL";
			psmt = conn.prepareStatement(sqlSecciones);
			set = psmt.executeQuery();
			
			set.next();

			String result = set.getObject("RESULT").toString();
			
			if(result.equals("SI")) {
				
				return true;
			} else {
				
				return false;
			}
			
		
		} catch (Exception ex) {

			ex.printStackTrace();
			
		} finally {

			ConnectionWrapper.closeAll(set, psmt, conn);

		}
		
		return false;
	}
    
    
    
    
    
}

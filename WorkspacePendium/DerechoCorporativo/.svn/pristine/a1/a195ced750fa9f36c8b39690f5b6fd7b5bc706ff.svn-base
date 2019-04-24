/**
* Project: Derecho Corporativo
*
* File: CriterioBusquedaDAO.java
*
* Created on: Agosto 31, 2015 at 12:00
*
* Copyright (c) - Televisa - 2015
*/

package mx.com.televisa.derechocorporativo.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import mx.com.televisa.derechocorporativo.bean.CriterioBusquedaBean;
import mx.com.televisa.derechocorporativo.bean.RolBean;
import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;
import mx.com.televisa.derechocorporativo.util.FacesUtils;

/**
 * Busqueda por filtros o criterios
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
public class CriterioBusquedaDAO {
	static ConnectionWrapper 		   puConnectionWrapper;
	CriterioBusquedaBean       puCriterioBusquedaBean;
	List<CriterioBusquedaBean> paCriterioBusquedaBean;
	List<CriterioBusquedaBean> paCriterioBusquedaBeanIni;
	String					   psIdRol;

	public CriterioBusquedaDAO(){}

	/**
	 * Carga inicial de empresas
	 * @return Arreglo de objetos
	 */
    public List<CriterioBusquedaBean> cargaInicial(){
        String            lsSql               = "";
        PreparedStatement luPreparedStatement = null;
        ResultSet         luResultSet         = null;
        psIdRol = FacesUtils.getSessionBean().getIdRol();
        FacesUtils.getSessionBean().setIdRol(String.valueOf(psIdRol));
        System.out.println("IdROlMain:"+psIdRol);

        paCriterioBusquedaBeanIni = new ArrayList<CriterioBusquedaBean>();

        try {
            lsSql = "SELECT  DISTINCT id_empresa,\n"+
                    "               denom_actual,\n"+
                    "                id_clasificacion,\n"+
                    "                id_pais\n"+
                    " FROM dercorp_busqueda_view\n"+ 
                    " WHERE denom_actual IS NOT NULL\n"+
                    " AND id_clasificacion IN (462,466)\n"
                    //" AND id_pais = 624\n" Se comenta porque quieren por default Todos
                    ;
            
            if(getRevokeEmp(psIdRol) != null ){
            	lsSql+=" AND id_empresa NOT IN("+getRevokeEmp(psIdRol)+")\n";	
            }
            lsSql+=" ORDER BY DENOM_ACTUAL";
//            System.out.println("Inicial:"+lsSql);
            puConnectionWrapper = new ConnectionWrapper();
            luPreparedStatement = puConnectionWrapper.prepareStatement(lsSql);
            luResultSet = luPreparedStatement.executeQuery();

            while(luResultSet.next()){
                puCriterioBusquedaBean = new CriterioBusquedaBean();
                puCriterioBusquedaBean.setIdEmpresa(luResultSet.getInt(1));
                puCriterioBusquedaBean.setDenomActual(luResultSet.getString(2));
                puCriterioBusquedaBean.setIdClasificacion(luResultSet.getString(3));
                puCriterioBusquedaBean.setIdPais(luResultSet.getInt(4));
                paCriterioBusquedaBeanIni.add(puCriterioBusquedaBean);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
        	try {
				luResultSet.close();
				luPreparedStatement.close();
				puConnectionWrapper.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        }
        
        return paCriterioBusquedaBeanIni;
	}

    /**
     * Devuelve busqueda de empresas
     * @param tuCriterioBusquedaBean
     * @return
     */
    public List<CriterioBusquedaBean> dameBusqueda(CriterioBusquedaBean tuCriterioBusquedaBean){
        StringBuilder luStringBuilder = new StringBuilder();
        String        lsSql           = null;
        String        lsDenominacion  = tuCriterioBusquedaBean.getDenominacion();
        Statement     luStatement     = null;
        ResultSet     luResultSet     = null;

        //System.out.println("IdROlDameBusqueda:"+tuCriterioBusquedaBean.getIdRol());

        paCriterioBusquedaBean = new ArrayList<CriterioBusquedaBean>();

        if(tuCriterioBusquedaBean.getDenominacion().equals("actual")){
            lsSql = "SELECT  DISTINCT id_empresa,\n"+
                    "denom_actual,\n"+
                    "id_clasificacion,\n"+
                    "id_pais\n"+
                    "FROM dercorp_busqueda_view \n"+
                    "WHERE denom_actual IS NOT NULL\n";
        }else if(tuCriterioBusquedaBean.getDenominacion().equals("anterior")){
            lsSql = "SELECT DISTINCT id_empresa,\n"+
                    "denom_actual,\n"+
                    "id_clasificacion,\n"+
                    "id_pais,\n"+
                    "denom_anterior\n"+
                    "FROM dercorp_busqueda_view \n"+
                    "WHERE denom_anterior IS NOT NULL\n";
		}

		luStringBuilder.append(lsSql);

        if(tuCriterioBusquedaBean.getDenominacion().equals("actual")){
            if(tuCriterioBusquedaBean.getEmpresa() != null){
	            luStringBuilder.append(" AND   APP_COMMON_PKG.SIN_ACENTOS_FN(UPPER(denom_actual)) LIKE APP_COMMON_PKG.SIN_ACENTOS_FN(UPPER('%"+tuCriterioBusquedaBean.getEmpresa()+"%'))");
            }
        }else if(tuCriterioBusquedaBean.getDenominacion().equals("anterior")){
            if(tuCriterioBusquedaBean.getEmpresa() != null){
                luStringBuilder.append(" AND   APP_COMMON_PKG.SIN_ACENTOS_FN(UPPER(denom_anterior)) LIKE APP_COMMON_PKG.SIN_ACENTOS_FN(UPPER('%"+tuCriterioBusquedaBean.getEmpresa()+"%'))");
            }
        }
        if(tuCriterioBusquedaBean.getIdClasificacion()!=null){
            luStringBuilder.append(" AND   id_clasificacion IN ("+tuCriterioBusquedaBean.getIdClasificacion()+")");
        }
        if(tuCriterioBusquedaBean.getIdPais() > 0){
            luStringBuilder.append(" AND id_pais = "+tuCriterioBusquedaBean.getIdPais());
        }

        if(getRevokeEmp(tuCriterioBusquedaBean.getIdRol()) != null){
        	luStringBuilder.append(" AND id_empresa NOT IN("+getRevokeEmp(tuCriterioBusquedaBean.getIdRol())+")");
        }

        luStringBuilder.append(" ORDER BY denom_actual");

        //System.out.println("Custom:"+luStringBuilder);
        try {
            puConnectionWrapper = new ConnectionWrapper();
            luStatement =  puConnectionWrapper.createStatement();
            luResultSet = luStatement.executeQuery(luStringBuilder.toString());

            while(luResultSet.next()){
                tuCriterioBusquedaBean = new CriterioBusquedaBean();
                tuCriterioBusquedaBean.setIdEmpresa(luResultSet.getInt(1));

                if(lsDenominacion.equals("actual")){
                    tuCriterioBusquedaBean.setDenomActual(luResultSet.getString(2));
                }else if(lsDenominacion.equals("anterior")){
                    tuCriterioBusquedaBean.setDenomActual(luResultSet.getString(5));
				}

                tuCriterioBusquedaBean.setIdClasificacion(luResultSet.getString(3));
                tuCriterioBusquedaBean.setIdPais(luResultSet.getInt(4));

                if(lsDenominacion.equals("anterior")){
                    tuCriterioBusquedaBean.setDenomAnterior(luResultSet.getString(5));
                }
                
                paCriterioBusquedaBean.add(tuCriterioBusquedaBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
        	try {
				luResultSet.close();
				luStatement.close();
				puConnectionWrapper.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

        return paCriterioBusquedaBean;

    }
    
    public static String getRevokeEmp(String psIdRol){
		 String listEmp = "";
		 PreparedStatement psmt = null;
		 ResultSet rs = null;
		 
		 String lstSql = "SELECT atributo1\n"+	
						 " FROM ss_rol_tab\n"+
						 " WHERE id_rol = " + psIdRol;
		 try {
			puConnectionWrapper = new ConnectionWrapper();
			psmt = puConnectionWrapper.prepareStatement(lstSql);
			rs = psmt.executeQuery();
			while(rs.next()){
				listEmp = rs.getString(1);

			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			ConnectionWrapper.closeAll(psmt,rs,puConnectionWrapper);
		}
		 return listEmp;
		 
	 }
}

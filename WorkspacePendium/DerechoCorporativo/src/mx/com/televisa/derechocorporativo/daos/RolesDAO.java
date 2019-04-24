/**
* Project: Derecho Corporativo
*
* File: UsersDAO.java
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







import mx.com.televisa.derechocorporativo.bean.EmpresasBean;
import mx.com.televisa.derechocorporativo.bean.MenuBean;
import mx.com.televisa.derechocorporativo.bean.PestaniaBean;
import mx.com.televisa.derechocorporativo.bean.RolBean;
import mx.com.televisa.derechocorporativo.bean.UserBean;
import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;

/**
 * Administración de Roles 
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
public class RolesDAO {
	
	 ConnectionWrapper 	puConnectionWrapper;
	 RolBean    		rolBean;
	 MenuBean			menuBean;
	 PestaniaBean		pestaniaBean;
	 List<RolBean>      listrol;
	 List<MenuBean>		listMenuElements;
	 List<PestaniaBean> listPestanias;
	  
	 public List<RolBean> dameRoles(){
		 listrol = new ArrayList<>();
		 PreparedStatement psmt = null;
		 ResultSet rs = null;
		 
		 String lstSql = "SELECT id_rol,\n"+
						 "        nom_name,\n"+
						 "        des_description,\n"+
						 "        num_password_expiration_days,\n"+ 
						 "        atributo1,\n" +
						 "        atributo2,\n" +
						 "        atributo3\n" +
						 " FROM ss_rol_tab";
		 try {
			puConnectionWrapper = new ConnectionWrapper();
			psmt = puConnectionWrapper.prepareStatement(lstSql);
			rs = psmt.executeQuery();
			while(rs.next()){
				rolBean = new RolBean();
				rolBean.setIdRol(rs.getInt(1));
				rolBean.setNomName(rs.getString(2));
				rolBean.setDesDescription(rs.getString(3));
				rolBean.setNumPasswordExpirationDays(rs.getInt(4));	
				rolBean.setRevokeEnterprises(rs.getString(5));
				rolBean.setReportPre(rs.getString(6));
				rolBean.setReportPer(rs.getString(7));
				listrol.add(rolBean);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			 ConnectionWrapper.closeAll(psmt,rs,puConnectionWrapper);
		}
		 return listrol;
	 }
	 
	 public RolBean getRol(int piIdRol){
		 RolBean lurolBean = new RolBean();
		 PreparedStatement psmt = null;
		 ResultSet rs = null;
		 
		 String lstSql = "SELECT id_rol,\n"+
				 "        nom_name,\n"+
				 "        des_description,\n"+
				 "        num_password_expiration_days,\n"+ 
				 "        atributo1,\n"	+
				 "        atributo2,\n" +
				 "        atributo3\n" +
				 " FROM ss_rol_tab\n"+
				 " WHERE id_rol = " + piIdRol;;
		 try {
			puConnectionWrapper = new ConnectionWrapper();
			psmt = puConnectionWrapper.prepareStatement(lstSql);
			rs = psmt.executeQuery();
			while(rs.next()){
				lurolBean = new RolBean();
				lurolBean.setIdRol(rs.getInt(1));
				lurolBean.setNomName(rs.getString(2));
				lurolBean.setDesDescription(rs.getString(3));
				lurolBean.setNumPasswordExpirationDays(rs.getInt(4));
				lurolBean.setRevokeEnterprises(rs.getString(5));
				lurolBean.setReportPre(rs.getString(6));
				lurolBean.setReportPer(rs.getString(7));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			ConnectionWrapper.closeAll(psmt,rs,puConnectionWrapper);
		}
		 return lurolBean;
		 
	 }
	 
		/**
	    * 
	    * @param piIdRol Identificador de Rol
	    * @return Lista de Objetos del tipo MenuBean
	    */
	 public List<MenuBean> getNewMenuElements(int piIdRol){
		 listMenuElements = new ArrayList<>();
		 PreparedStatement psmt = null;
		 ResultSet rs = null;
		 
		 String lstSql = "SELECT id_menu,\n"+
						 "       id_menu_element,\n"+
						 "       is_enabled,\n"+
						 "       id_rol,\n"+
						 "       nom_menu,\n"+
						 "       id_menu_element_parent\n" +
						 " FROM dercorp_menu_vw\n"+
						 " WHERE id_rol = " + piIdRol;
		 try {
			puConnectionWrapper = new ConnectionWrapper();
			psmt = puConnectionWrapper.prepareStatement(lstSql);
			rs = psmt.executeQuery();
			while(rs.next()){
				menuBean = new MenuBean();
				menuBean.setIdMenu(rs.getInt(1));
				menuBean.setIdMenuElement(rs.getInt(2));	
				menuBean.setIsEnabled(rs.getInt(3));
				menuBean.setIdRol(rs.getInt(4));
				menuBean.setNomMenu(rs.getString(5));	
				menuBean.setIdMenuElementParent(rs.getInt(6));
				listMenuElements.add(menuBean);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			ConnectionWrapper.closeAll(psmt,rs,puConnectionWrapper);
		}
		 return listMenuElements;
	 }
	 
	 
		/**
	    * 
	    * @param piIdRol Identificador de Rol
	    * @return Lista de Objetos del tipo MenuBean
	    */
	 public List<PestaniaBean> getPestanias (int piIdRol){
		 listPestanias = new ArrayList<>();
		 PreparedStatement psmt = null;
		 ResultSet rs = null;
/*
		 String lstSql = "SELECT sec.id_seccion,\n" +
				 				"sec.nom_seccion,\n"+
				 				"NVL((select id_rol\n" +
				 					 "from   dercorp_rol_seccion_tab\n"+
				 					 "where  id_seccion = sec.id_seccion\n" +
				 					 "and    id_rol     = " + piIdRol +"),0)as id_rol\n"+
				 		 "FROM   dercorp_add_campo_seccion_tab sec,\n" +
				 		 		"dercorp_rol_seccion_tab rs\n" +
				 		 "WHERE  sec.id_seccion = rs.id_seccion(+)\n"+
				 		 "AND    rs.id_rol      =1\n"+
				 		 "ORDER BY sec.id_seccion\n";
*/
/*
 *  --Reforma Total de Estatutos --- ID_SECCION = 33
                --Poderes Especiales ----ID_SECCION = 23
                --Apoderados ----ID_SECCION = 24
                
                se quitan del query JAMS 02/06/2017
 */
         String lstSql = "SELECT SEC.ID_SECCION, "+
								"SEC.NOM_SECCION, "+
								"NVL((SELECT ID_ROL "+
									 "FROM   DERCORP_ROL_SECCION_TAB "+
									 "WHERE  ID_SECCION = SEC.ID_SECCION "+
									 "AND    ID_ROL     =  "+piIdRol+"),0)AS ID_ROL "+
						  "FROM   DERCORP_ADD_CAMPO_SECCION_TAB SEC "+
						 "WHERE ID_SECCION NOT IN (33,23,24) "+//SE AGREGA NOT IN JAMSS			 
						 "ORDER BY SEC.ID_SECCION"
                         ;

		 try {
			puConnectionWrapper = new ConnectionWrapper();
			psmt = puConnectionWrapper.prepareStatement(lstSql);
			rs = psmt.executeQuery();
			while(rs.next()){
				pestaniaBean = new PestaniaBean();
				pestaniaBean.setIdSeccion(rs.getInt(1));
				pestaniaBean.setNomSeccion(rs.getString(2));
				pestaniaBean.setIdRol(rs.getInt(3));
				listPestanias.add(pestaniaBean);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			ConnectionWrapper.closeAll(psmt,rs,puConnectionWrapper);
		}
		 return listPestanias;
	 }
	 
	/**
    * 
    * @param tuRolBean Bean que contiene los datos del rol
    * @return false si ocurrio un error true si lo inserto bien
    */
	    public boolean insertRol(RolBean tuRolBean,int idMenu){
		    String            lsSql               = "";
	        Connection 		  luConection         = null;
	        boolean    		  lbBok               = false;
	        CallableStatement luCallableStatement = null;
	        String            lsError             = null;

	        try {
	        	lsSql = "CALL DERCORP_PANEL_CONTROL_PKG.insert_rol_pr(?,?,?,?,?,?,?,?)";
	            puConnectionWrapper = new ConnectionWrapper();
	            luConection = puConnectionWrapper.getConnection();			
	            luCallableStatement = luConection.prepareCall(lsSql);
	            luCallableStatement.setString(1, tuRolBean.getNomName()+"|"+tuRolBean.getRevokeEnterprises());
	            luCallableStatement.setString(2, tuRolBean.getDesDescription());
	            luCallableStatement.setInt(3, tuRolBean.getNumPasswordExpirationDays());
	            luCallableStatement.setString(4,tuRolBean.getReportPre());
	            luCallableStatement.setString(5,tuRolBean.getReportPer());
	            luCallableStatement.setInt(6,idMenu);
	            luCallableStatement.registerOutParameter(7, java.sql.Types.VARCHAR);
	            luCallableStatement.setInt(8, tuRolBean.getUserModifico());
	            luCallableStatement.execute();
	            lsError = luCallableStatement.getString(7);
	            System.out.println(lsError);
	            if(lsError == null){
	                lbBok = true;
	            }

	        } catch (Exception e) {
	            e.printStackTrace();

	        }finally {				
	           ConnectionWrapper.closeAll(luCallableStatement,luConection);
	        }
	        return lbBok;
	    }
	    
	    /**
	    * 
	    * @param tuRolBean Bean que contiene los datos del rol
	    * @return false si ocurrio un error true si lo actualizo bien
	    */
	    public boolean updateRol(RolBean tuRolBean){
		    String            lsSql               = "";
	        Connection 		  luConection         = null;
	        boolean    		  lbBok               = false;
	        CallableStatement luCallableStatement = null;
	        String            lsError             = null;

	        try {
	        	lsSql = "CALL DERCORP_PANEL_CONTROL_PKG.update_rol_pr(?,?,?,?,?,?,?,?)";
	            puConnectionWrapper = new ConnectionWrapper();
	            luConection = puConnectionWrapper.getConnection();			
	            luCallableStatement = luConection.prepareCall(lsSql);
	            luCallableStatement.setInt(1, tuRolBean.getIdRol());
	            luCallableStatement.setString(2, tuRolBean.getNomName()+"|"+tuRolBean.getRevokeEnterprises());
	            luCallableStatement.setString(3, tuRolBean.getDesDescription());
	            luCallableStatement.setInt(4, tuRolBean.getNumPasswordExpirationDays());
	            luCallableStatement.setString(5,tuRolBean.getReportPre());
	            luCallableStatement.setString(6,tuRolBean.getReportPer());
	            luCallableStatement.registerOutParameter(7, java.sql.Types.VARCHAR);
	            luCallableStatement.setInt(8, tuRolBean.getUserModifico());
	            luCallableStatement.execute();
	            lsError = luCallableStatement.getString(7);

	            if(lsError == null){
	                lbBok = true;
	            }

	        } catch (Exception e) {
	            e.printStackTrace();

	        }finally {				
	           ConnectionWrapper.closeAll(luCallableStatement,luConection);
	        }
	        return lbBok;
	    }
 
	    
	    /**
	    * 
	    * @param tuRolBean Bean que contiene los datos del rol
	    * @return false si ocurrio un error true si lo elimino bien
	    */
	    public boolean deleteRol(int tuRolId,int idUserModifico){
		    String            lsSql               = "";
	        Connection 		  luConection         = null;
	        boolean    		  lbBok               = false;
	        CallableStatement luCallableStatement = null;
	        String            lsError             = null;
	
	        try {
	        	lsSql = "CALL DERCORP_PANEL_CONTROL_PKG.elimina_rol_pr(?,?,?)";
	            puConnectionWrapper = new ConnectionWrapper();
	            luConection = puConnectionWrapper.getConnection();			
	            luCallableStatement = luConection.prepareCall(lsSql);
	            luCallableStatement.setInt(1, tuRolId);
	            luCallableStatement.registerOutParameter(2, java.sql.Types.VARCHAR);
	            luCallableStatement.setInt(3, idUserModifico);
	            luCallableStatement.execute();
	            lsError = luCallableStatement.getString(2);
	
	            if(lsError == null){
	                lbBok = true;
	            }
	
	        } catch (Exception e) {
	            e.printStackTrace();
	
	        }finally {				
	           ConnectionWrapper.closeAll(luCallableStatement,luConection);
	        }
	        return lbBok;
	    }
	    
	    public int dameSecuenciaIdMenu(){
	    	String sql = "SELECT dercorp_menu_sq.nextval \n "+
	    				 " FROM DUAL";
	    	 Connection   luConection   = null;
	    	 ResultSet    luRs          = null;
	    	 PreparedStatement psmt = null;
	    	 int seq = 0;
	    	 
	    	 
			try {
				puConnectionWrapper = new ConnectionWrapper();
		    	luConection = puConnectionWrapper.getConnection();
				psmt = luConection.prepareStatement(sql);
				luRs = psmt.executeQuery();
				while(luRs.next()){
					seq = luRs.getInt(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				try {
					psmt.close();
					luRs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
	    	return seq; 
	    }
	 
}

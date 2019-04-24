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
import mx.com.televisa.derechocorporativo.bean.RolBean;
import mx.com.televisa.derechocorporativo.bean.UserBean;
import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;

/**
 * Administración de usuarios 
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
public class UsersDAO {

    ConnectionWrapper puConnectionWrapper;
    UserBean          puUserBean;
    List<UserBean>    paUserBean;
    RolBean           puRolBean;
    List<RolBean>     paRolBean;

    /**
     * Eliminar usuario
     * @param tuUserBean
     * @return boolean
     */
    public boolean eliminaUser(UserBean tuUserBean){
        String            lsSql               = "";
        Connection        luConnection        = null;
        boolean           lbOK                = false;
        CallableStatement luCallableStatement = null;

        try {
            lsSql = "CALL ss_change_password_pkg.DELETE_USER_PR(?,?)";
            puConnectionWrapper = new ConnectionWrapper();
            luConnection = puConnectionWrapper.getConnection();				
            luCallableStatement = luConnection.prepareCall(lsSql);
            luCallableStatement.setInt(1, tuUserBean.getIdUser());
            luCallableStatement.setInt(2, tuUserBean.getUsuarioModifico());
            luCallableStatement.execute();
            lbOK = true;

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

        return lbOK;

	 }

    /**
     * Actualizar Usuario
     * @param tuUserBean
     * @return String
     */
    public String actualizaUser(UserBean tuUserBean){
        String            lsSql               = "";
        Connection        luConnection        = null;
        String            lsMensaje           = null;
        CallableStatement luCallableStatement = null;
 
        try {
            lsSql = "CALL ss_change_password_pkg.DO_CHANGE_PR_ADMIN_PLUS_PR(?,?,?,?,?,?,?,?,?)";
            puConnectionWrapper = new ConnectionWrapper();
            luConnection = puConnectionWrapper.getConnection();
            luCallableStatement = luConnection.prepareCall(lsSql);
            luCallableStatement.setInt(1, tuUserBean.getIdUser());
            luCallableStatement.setInt(2, tuUserBean.getIdRol());
            luCallableStatement.setString(3, tuUserBean.getCvePassword());
            luCallableStatement.setString(4, tuUserBean.getNomUserLongName());
            luCallableStatement.setString(5, tuUserBean.getNomUsername());
            luCallableStatement.setString(6, tuUserBean.getStatus());
            luCallableStatement.registerOutParameter(7, java.sql.Types.VARCHAR);
            luCallableStatement.setInt(8, tuUserBean.getUsuarioModifico());
            luCallableStatement.setString(9, tuUserBean.getNumEmpleado());
            luCallableStatement.execute();
            lsMensaje = luCallableStatement.getString(7);

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

        return lsMensaje;

    }

    /**
     * Nuevo usuario
     * @param tuUserBean
     * @return String
     */
    public String nuevoUsuario(UserBean tuUserBean){
        String            lsSql               = "";
        Connection        luConnection        = null;
        String            lsMensaje           = null;
        CallableStatement luCallableStatement = null;

        try {
            lsSql = "CALL ss_change_password_pkg.CREATE_USER_ADMIN_PR(?,?,?,?,?,?,?)";
            puConnectionWrapper = new ConnectionWrapper();
            luConnection = puConnectionWrapper.getConnection();
            luCallableStatement = luConnection.prepareCall(lsSql);
            luCallableStatement.setInt(1, tuUserBean.getIdRol());
            luCallableStatement.setString(2, tuUserBean.getNomUserLongName());
            luCallableStatement.setString(3, tuUserBean.getNomUsername());
            luCallableStatement.setString(4, tuUserBean.getCvePassword());
            luCallableStatement.registerOutParameter(5, java.sql.Types.VARCHAR);
            luCallableStatement.setInt(6, tuUserBean.getUsuarioModifico());
            luCallableStatement.setString(7, tuUserBean.getNumEmpleado());
            luCallableStatement.execute();
            lsMensaje = luCallableStatement.getString(5);
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

         return lsMensaje;

    }

    /**
     * Devuelve un arreglo de roles
     * @return Arreglo de objetos
     */
    public List<RolBean> dameRoles(){
        paRolBean = new ArrayList<>();

        String            lsSql               = "";
        PreparedStatement luPreparedStatement = null;
        ResultSet         luResultSet         = null;

        try {
            lsSql = "SELECT id_rol,\n"+
                    "        nom_name,\n"+
                    "        des_description,\n"+
                    "        num_password_expiration_days\n"+         
                    " FROM ss_rol_tab";

			puConnectionWrapper = new ConnectionWrapper();
            luPreparedStatement = puConnectionWrapper.prepareStatement(lsSql);
            luResultSet = luPreparedStatement.executeQuery();

            while(luResultSet.next()){
                puRolBean = new RolBean();
                puRolBean.setIdRol(luResultSet.getInt(1));
                puRolBean.setNomName(luResultSet.getString(2));
                puRolBean.setDesDescription(luResultSet.getString(3));
                puRolBean.setNumPasswordExpirationDays(luResultSet.getInt(4));			
                paRolBean.add(puRolBean);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            puConnectionWrapper.close();
        }

        return paRolBean;

	}

    public List<UserBean> dameUsuarios(){
        paUserBean = new ArrayList<>();

        String            lsSql               = "";
        PreparedStatement luPreparedStatement = null;
        ResultSet         luResultSet         = null;

        try {
            lsSql = "SELECT id_user,\n"+
                    "  nom_user_long_name,\n"+
                    "  nom_username,\n"+
                    "  cve_password,\n"+
                    "  id_status,        \n"+ 
                    " CASE id_status WHEN 1 THEN 'ACTIVO'\n"+
                    " WHEN 2 THEN 'BLOQUEADO'\n"+
                    " WHEN 3 THEN 'EN SESSION'\n"+
                    " WHEN 4 THEN 'CAMBIO DE CONTRASEÑA'\n"+
                    " END status,\n"+
                    " atributo1 numEmpleado\n"+
                    " FROM ss_user_tab\n"+
                    " ORDER BY id_user desc";

			puConnectionWrapper = new ConnectionWrapper();

			luPreparedStatement = puConnectionWrapper.prepareStatement(lsSql);
			luResultSet = luPreparedStatement.executeQuery();

			while(luResultSet.next()){
                puUserBean = new UserBean();
                puUserBean.setIdUser(luResultSet.getInt(1));
                puUserBean.setNomUserLongName(luResultSet.getString(2));
                puUserBean.setNomUsername(luResultSet.getString(3));
                puUserBean.setCvePassword(luResultSet.getString(4));
                puUserBean.setIdStatus(luResultSet.getInt(5));
                puUserBean.setStatus(luResultSet.getString(6));
                puUserBean.setNumEmpleado(luResultSet.getString(7)!= null ? luResultSet.getString(7) : "");
                paUserBean.add(puUserBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            puConnectionWrapper.close();
        }

        return paUserBean;

	}
	
    public UserBean dameUsuario(int tiIdUser){
        paUserBean = new ArrayList<>();

        String            lsSql               = "";
        PreparedStatement luPreparedStatement = null;
        ResultSet         luResultSet         = null;

        try {
            lsSql = "SELECT sur.id_user,\n"+
                    "  sur.nom_user_long_name,\n"+
                    "  sur.nom_username,\n"+
                    "  SS_CRYPTO_PKG.DECRYPT_FN(sur.cve_password),\n"+
                    "  sur.id_status,        \n"+ 
                    " CASE sur.id_status WHEN 1 THEN 'ACTIVO'\n"+
                    " WHEN 2 THEN 'BLOQUEADO'\n"+
                    " WHEN 3 THEN 'EN SESSION'\n"+
                    " WHEN 4 THEN 'CAMBIO DE CONTRASEÑA'\n"+
                    " END status,\n"+
                    "(SELECT (SELECT sr.id_rol \n"+
                    "           FROM  ss_rol_tab sr\n"+
                    "           WHERE sr.id_rol = sur.id_rol) \n"+
                    "           FROM ss_user_rol_tab sur\n"+
                    "           WHERE id_user = ?)rol_id,\n"+
                    " sur.atributo1 numEmpleado\n"+
                    " FROM ss_user_tab sur\n"+
                    " WHERE sur.id_user = ?";

            puConnectionWrapper = new ConnectionWrapper();
            luPreparedStatement = puConnectionWrapper.prepareStatement(lsSql);
            luPreparedStatement.setInt(1, tiIdUser);
            luPreparedStatement.setInt(2, tiIdUser);
            luResultSet = luPreparedStatement.executeQuery();

            while(luResultSet.next()){
                puUserBean = new UserBean();
                puUserBean.setIdUser(luResultSet.getInt(1));
                puUserBean.setNomUserLongName(luResultSet.getString(2));
                puUserBean.setNomUsername(luResultSet.getString(3));
                puUserBean.setCvePassword(luResultSet.getString(4));
                puUserBean.setIdStatus(luResultSet.getInt(5));
                puUserBean.setStatus(luResultSet.getString(6));
                puUserBean.setIdRol(luResultSet.getInt(7));
                puUserBean.setNumEmpleado(luResultSet.getString(8)!= null ? luResultSet.getString(8) : "");
			}
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            puConnectionWrapper.close();
        }

        return puUserBean;

    }

}

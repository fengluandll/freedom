package sysadmin.app.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import sysadmin.app.model.ComboBoxBean;
import sysadmin.app.model.UsersBean;

public class UsersDAO {
	// Declaracion de variables 
	private MySqlConexion sqlConn;
    ResultSet rs;
    PreparedStatement pstmt;
    Connection conexion;
    private UsersBean dtData;
    private ComboBoxBean dtCmb;
    List<UsersBean> listUsers;
    List<ComboBoxBean> listCmb;
    
	public UsersDAO() {
		// TODO Auto-generated constructor stub
		fnLoadData();
	}
	
	public int fnValidatePasswordChgPass(String p_username, String p_password){
	//public int fnLoginValidate(String p_username, String p_password){
        String lstSqlQuery="";
        int linReturn=0;

        lstSqlQuery = " SELECT COUNT(*) existe "+
                      " FROM users "+
                      " WHERE username =  ? "+
                      " AND password = md5(?) "+
                      " AND ifnull(start_date, DATE_ADD(sysdate(), INTERVAL -1 DAY)) <= sysdate() "+
                      " AND ifnull(end_date, DATE_ADD(sysdate(), INTERVAL 1 DAY)) >= sysdate()";
        sqlConn = new MySqlConexion();

        conexion = sqlConn.getConexion();

        try {
            pstmt = conexion.prepareStatement(lstSqlQuery);
            pstmt.setString(1,p_username);
            pstmt.setString(2,p_password);
            rs = pstmt.executeQuery();
            while (rs.next()){
                linReturn = rs.getInt("existe");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return linReturn;
    }
	
	public String fnChangePasswordChgPass(String p_user_id, String p_username, String p_password_new, String p_password_old){
        String lstSqlQuery="";
        String linReturn="";

        lstSqlQuery = "SELECT change_password_fn (?, ?, ?, ?) regreso";
        sqlConn = new MySqlConexion();

        conexion = sqlConn.getConexion();

        try {
            pstmt = conexion.prepareStatement(lstSqlQuery);
            pstmt.setString(1,p_user_id);
            pstmt.setString(2,p_username);
            pstmt.setString(3,p_password_new);
            pstmt.setString(4,p_password_old);
            rs = pstmt.executeQuery();
            while (rs.next()){
                linReturn = rs.getString("regreso");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return linReturn;
    }
	
	public List<UsersBean> fnLoadData(){
		String lstSqlQuery="";
		listUsers = new ArrayList<UsersBean>();
        lstSqlQuery = " SELECT user_id, username, first_name, last_name, password, profile_id, photo, start_date, "+
		              "        end_date, segment1, segment2, segment3, segment4, segment5, segment6, segment7, "+
		              "      segment8, segment9 "+
		              " FROM users "+
		              " WHERE 1 = 1 "+
                	  " AND ifnull(start_date, DATE_ADD(sysdate(), INTERVAL -1 DAY)) <= sysdate() "+
                      " AND ifnull(end_date, DATE_ADD(sysdate(), INTERVAL 1 DAY)) >= sysdate() "+
                	  " ORDER BY user_id";
        sqlConn = new MySqlConexion();
        
        conexion = sqlConn.getConexion();

        try {
            pstmt = conexion.prepareStatement(lstSqlQuery);
            rs = pstmt.executeQuery();
            while (rs.next()){
            	dtData = new UsersBean(rs.getInt("user_id"), rs.getString("username"), rs.getString("first_name"), rs.getString("last_name"), 
            			           rs.getInt("profile_id"), rs.getString("password"), rs.getString("photo"), rs.getDate("start_date"), rs.getDate("end_date"), 
            			           rs.getString("segment1"), rs.getString("segment2"), rs.getString("segment3"), rs.getString("segment4"), rs.getString("segment5"), 
            			           rs.getString("segment6"), rs.getString("segment7"), rs.getString("segment8"), rs.getString("segment9"));
            	listUsers.add(dtData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listUsers;
	}

	public String fnDeleteUsers(String p_user_id){
        String lstSqlQuery="";
        String linReturn="";

        lstSqlQuery = "SELECT user_delete_fn (?) regreso";
        sqlConn = new MySqlConexion();

        conexion = sqlConn.getConexion();

        try {
            pstmt = conexion.prepareStatement(lstSqlQuery);
            pstmt.setString(1,p_user_id);
            rs = pstmt.executeQuery();
            while (rs.next()){
                linReturn = rs.getString("regreso");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return linReturn;
    }
	
	public String fnUpdateUsers(String p_user_id, String p_first_name, String p_last_name, String p_profile_id, String p_photo, Date p_end_date, 
			String p_segment1, String p_segment2, String p_segment3, String p_segment4, String p_segment5, String p_segment6, 
			String p_segment7, String p_segment8, String p_segment9){
        String lstSqlQuery="";
        String linReturn="";

        lstSqlQuery = "SELECT user_update_fn (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) regreso";
        sqlConn = new MySqlConexion();

        conexion = sqlConn.getConexion();

        try {
            pstmt = conexion.prepareStatement(lstSqlQuery);
            pstmt.setString(1,p_user_id);
            pstmt.setString(2,p_first_name);
            pstmt.setString(3,p_last_name);
            pstmt.setString(4,p_profile_id);
            pstmt.setString(5,p_photo);
            pstmt.setDate(6,p_end_date);
            pstmt.setString(7,p_segment1);
            pstmt.setString(8,p_segment2);
            pstmt.setString(9,p_segment3);
            pstmt.setString(10,p_segment4);
            pstmt.setString(11,p_segment5);
            pstmt.setString(12,p_segment6);
            pstmt.setString(13,p_segment7);
            pstmt.setString(14,p_segment8);
            pstmt.setString(15,p_segment9);
            rs = pstmt.executeQuery();
            while (rs.next()){
                linReturn = rs.getString("regreso");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return linReturn;
    }
	
	public String fnInsertUsers(String p_username, String p_first_name, String p_last_name, String p_password, String p_profile_id, String p_photo, 
			Date p_start_date, Date p_end_date, String p_segment1, String p_segment2, String p_segment3, String p_segment4, 
			String p_segment5, String p_segment6, String p_segment7, String p_segment8, String p_segment9){
        String lstSqlQuery="";
        String linReturn="";

        lstSqlQuery = "SELECT user_insert_fn (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) regreso";
        sqlConn = new MySqlConexion();

        conexion = sqlConn.getConexion();

        try {
            pstmt = conexion.prepareStatement(lstSqlQuery);
            pstmt.setString(1,p_username);
            pstmt.setString(2,p_first_name);
            pstmt.setString(3,p_last_name);
            pstmt.setString(4,p_password);
            pstmt.setString(5,p_profile_id);
            pstmt.setString(6,p_photo);
            pstmt.setDate(7, p_start_date);
            pstmt.setDate(8, p_end_date);
            pstmt.setString(9,p_segment1);
            pstmt.setString(10,p_segment2);
            pstmt.setString(11,p_segment3);
            pstmt.setString(12,p_segment4);
            pstmt.setString(13,p_segment5);
            pstmt.setString(14,p_segment6);
            pstmt.setString(15,p_segment7);
            pstmt.setString(16,p_segment8);
            pstmt.setString(17,p_segment9);
            rs = pstmt.executeQuery();
            while (rs.next()){
                linReturn = rs.getString("regreso");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return linReturn;
    }
	
	public int fnUserExist(String p_username){
		//public int fnLoginValidate(String p_username, String p_password){
        String lstSqlQuery="";
        int linReturn=0;

        lstSqlQuery = " SELECT COUNT(*) existe "+
                      " FROM users "+
                      " WHERE username =  ? ";
        sqlConn = new MySqlConexion();

        conexion = sqlConn.getConexion();

        try {
            pstmt = conexion.prepareStatement(lstSqlQuery);
            pstmt.setString(1,p_username);
            rs = pstmt.executeQuery();
            while (rs.next()){
                linReturn = rs.getInt("existe");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return linReturn;
	}
	
	public List<ComboBoxBean> fnLoadDataCmb(){
		String lstSqlQuery="";
		listCmb = new ArrayList<ComboBoxBean>();
        lstSqlQuery = " SELECT profile_id, name, description "+
                      " FROM profile_v ";
        sqlConn = new MySqlConexion();
        
        conexion = sqlConn.getConexion();

        try {
            pstmt = conexion.prepareStatement(lstSqlQuery);
            rs = pstmt.executeQuery();
            while (rs.next()){
            	dtCmb = new ComboBoxBean(rs.getInt("profile_id"), rs.getString("name"), rs.getString("description"));
            	listCmb.add(dtCmb);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listCmb;
	}
}

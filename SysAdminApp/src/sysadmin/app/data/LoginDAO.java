package sysadmin.app.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sysadmin.app.model.LoginBean;

public class LoginDAO {
	
	// Declaracion de variables 
	private MySqlConexion sqlConn;
    ResultSet rs;
    PreparedStatement pstmt;
    Connection conexion;
    
    public List<LoginBean> listData = new ArrayList<LoginBean>();
    public String gstUsername, gstPassword;
    
    public LoginDAO(){
    	
    }
	
	public int fnLoginValidate(String p_username, String p_password){
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
	
	public List<LoginBean> fnGetInfoUser(String p_username, String p_password){
        String lstSqlQuery="";
        LoginBean dtData;
        listData = new ArrayList<LoginBean>();

        lstSqlQuery = " SELECT user_id, username, first_name, last_name, profile_name, photo, ifnull(start_date, DATE_ADD(sysdate(), INTERVAL -1 DAY)) start_date, ifnull(end_date, DATE_ADD(sysdate(), INTERVAL 365 DAY)) end_date, concat( first_name, ' ', last_name) full_name"+
                      " FROM users_v "+
                      " WHERE username =  ? AND password = md5(?) "+
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
            	dtData = new LoginBean(rs.getInt("user_id"), rs.getString("username"), rs.getString("first_name"), 
            			           rs.getString("last_name"), rs.getString("profile_name"), rs.getString("photo"), 
            			           rs.getDate("start_date"), rs.getDate("end_date"), rs.getString("full_name"));
            	listData.add(dtData);
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

        return listData;
    }
}

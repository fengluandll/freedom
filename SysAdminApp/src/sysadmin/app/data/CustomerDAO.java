package sysadmin.app.data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sysadmin.app.model.ComboBoxBean;
import sysadmin.app.model.CustomerBean;

public class CustomerDAO {
	private MySqlConexion sqlConn;
    ResultSet rs;
    PreparedStatement pstmt;
    Connection conexion;
    private CustomerBean dtData;
    //private ComboBoxBean dtCmb;
    List<CustomerBean> listCustomer;
    List<ComboBoxBean> listCmb;

	public CustomerDAO() {
		//TODO
	}

	public String fnDeleteCustomer(String p_customer_id, String p_user_id){
        String lstSqlQuery="";
        String linReturn="";

        lstSqlQuery = "SELECT customer_delete_fn (?, ?) regreso";
        sqlConn = new MySqlConexion();

        conexion = sqlConn.getConexion();

        try {
            pstmt = conexion.prepareStatement(lstSqlQuery);
            pstmt.setString(1,p_customer_id);
            pstmt.setString(2,p_user_id);
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
	
	public String fnUpdateCustomer(int p_customer_id, String p_first_name, String p_last_name, 
			String p_business_name, String p_rfc, String p_address_line1, String p_address_line2, 
			String p_address_line3, String p_city, String p_state, String p_country, String p_postal_code, 
			String p_segment1, String p_segment2, String p_segment3, String p_segment4, String p_segment5, 
			String p_segment6, String p_segment7, String p_segment8, String p_segment9, String p_email, 
			String p_area_code_phone, String p_phone, Date p_end_date, Date p_born_date, int p_user_id){
        String lstSqlQuery="";
        String linReturn="";

        lstSqlQuery = "SELECT customer_update_fn (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) regreso";
        sqlConn = new MySqlConexion();

        conexion = sqlConn.getConexion();

        try {
            pstmt = conexion.prepareStatement(lstSqlQuery);
            pstmt.setInt(1,p_customer_id);
            pstmt.setString(2,p_first_name);
            pstmt.setString(3,p_last_name);
            pstmt.setString(4,p_business_name);
            pstmt.setString(5,p_rfc);
            pstmt.setString(6,p_address_line1);
            pstmt.setString(7,p_address_line2);
            pstmt.setString(8,p_address_line3);
            pstmt.setString(9,p_city);
            pstmt.setString(10,p_state);
            pstmt.setString(11,p_country);
            pstmt.setString(12,p_postal_code);
            pstmt.setString(13,p_segment1);
            pstmt.setString(14,p_segment2);
            pstmt.setString(15,p_segment3);
            pstmt.setString(16,p_segment4);
            pstmt.setString(17,p_segment5);
            pstmt.setString(18,p_segment6);
            pstmt.setString(19,p_segment7);
            pstmt.setString(20,p_segment8);
            pstmt.setString(21,p_segment9);
            pstmt.setString(22,p_email);
            pstmt.setString(23,p_area_code_phone);
            pstmt.setString(24,p_phone);
            pstmt.setDate(25,p_end_date);
            pstmt.setDate(26,p_born_date);
            pstmt.setInt(27,p_user_id);
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
	
	public String fnInsertCustomer(String p_first_name, String p_last_name, 
			String p_business_name, String p_rfc, String p_address_line1, String p_address_line2, 
			String p_address_line3, String p_city, String p_state, String p_country, String p_postal_code, 
			String p_segment1, String p_segment2, String p_segment3, String p_segment4, String p_segment5, 
			String p_segment6, String p_segment7, String p_segment8, String p_segment9, String p_email, 
			String p_area_code_phone, String p_phone,Date p_start_date, Date p_end_date, Date p_born_date, int p_user_id, int p_org_id){
        String lstSqlQuery="";
        String linReturn="";

        lstSqlQuery = "SELECT customer_insert_fn (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) regreso";
        sqlConn = new MySqlConexion();

        conexion = sqlConn.getConexion();

        try {
            pstmt = conexion.prepareStatement(lstSqlQuery);
            pstmt.setString(1,p_first_name);
            pstmt.setString(2,p_last_name);
            pstmt.setString(3,p_business_name);
            pstmt.setString(4,p_rfc);
            pstmt.setString(5,p_address_line1);
            pstmt.setString(6,p_address_line2);
            pstmt.setString(7,p_address_line3);
            pstmt.setString(8,p_city);
            pstmt.setString(9,p_state);
            pstmt.setString(10,p_country);
            pstmt.setString(11,p_postal_code);
            pstmt.setString(12,p_segment1);
            pstmt.setString(13,p_segment2);
            pstmt.setString(14,p_segment3);
            pstmt.setString(15,p_segment4);
            pstmt.setString(16,p_segment5);
            pstmt.setString(17,p_segment6);
            pstmt.setString(18,p_segment7);
            pstmt.setString(19,p_segment8);
            pstmt.setString(20,p_segment9);
            pstmt.setString(21,p_email);
            pstmt.setString(22,p_area_code_phone);
            pstmt.setString(23,p_phone);
            pstmt.setDate(24,p_start_date);
            pstmt.setDate(25,p_end_date);
            pstmt.setDate(26,p_born_date);
            pstmt.setInt(27,p_user_id);
            pstmt.setInt(28,p_org_id);
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
	
	public int fnCustomerExist(String p_rfc){
		//public int fnLoginValidate(String p_username, String p_password){
        String lstSqlQuery="";
        int linReturn=0;

        lstSqlQuery = " SELECT COUNT(*) existe "+
                      " FROM customers "+
                      " WHERE rfc =  ? ";
        sqlConn = new MySqlConexion();

        conexion = sqlConn.getConexion();

        try {
            pstmt = conexion.prepareStatement(lstSqlQuery);
            pstmt.setString(1,p_rfc);
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
	public List<CustomerBean> fnLoadData(String p_first_name, String p_last_name, String p_rfc, String p_business_name){
		String lstSqlQuery="";
		listCustomer = new ArrayList<CustomerBean>();
        lstSqlQuery = " SELECT customer_id, first_name, last_name, business_name, rfc, address_line1, address_line2,  "+
		              "        address_line3, city, state, country, postal_code, segment1, segment2, segment3, segment4,  "+
		              "        segment5, segment6, segment7, segment8, segment9, email, area_code_phone, phone, start_date, end_date, born_date "+
		              " FROM customers_v "+
		              " WHERE 1 = 1 ";
                	  if (!p_first_name.equals("")){
                		  lstSqlQuery = lstSqlQuery + " AND UPPER(first_name) LIKE UPPER('"+p_first_name+"') ";
                	  }
        			  if (!p_last_name.equals("")){
        				 lstSqlQuery = lstSqlQuery + " AND UPPER(last_name) LIKE UPPER('"+p_last_name+"') ";
                	  }
        			  if (!p_rfc.equals("")){
        				 lstSqlQuery = lstSqlQuery + " AND UPPER(rfc) LIKE UPPER('"+p_rfc+"') ";
                	  }
        			  if (!p_business_name.equals("")){
        				 lstSqlQuery = lstSqlQuery + " AND UPPER(business_name) LIKE UPPER('"+p_business_name+"') ";
                	  }
        			  lstSqlQuery = lstSqlQuery + " ORDER BY business_name, rfc, first_name, last_name, customer_id";
        sqlConn = new MySqlConexion();
        
        conexion = sqlConn.getConexion();

        try {
            pstmt = conexion.prepareStatement(lstSqlQuery);
            rs = pstmt.executeQuery();
            while (rs.next()){
            	dtData = new CustomerBean(rs.getInt("customer_id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("business_name"), 
            			rs.getString("rfc"), rs.getString("address_line1"), rs.getString("address_line2"), rs.getString("address_line3"), rs.getString("city"), 
            			rs.getString("state"), rs.getString("country"), rs.getString("postal_code"), rs.getString("segment1"), rs.getString("segment2"), 
            			rs.getString("segment3"), rs.getString("segment4"), rs.getString("segment5"), rs.getString("segment6"), rs.getString("segment7"), 
            			rs.getString("segment8"), rs.getString("segment9"), rs.getString("email"), rs.getString("area_code_phone"), rs.getString("phone"), 
            			rs.getDate("start_date"), rs.getDate("end_date"), rs.getDate("born_date"));
            	listCustomer.add(dtData);
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
        return listCustomer;
	}
}

package sysadmin.app.data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sysadmin.app.model.ComboBoxBean;
import sysadmin.app.model.ItemsBean;

public class ItemsDAO {
	private MySqlConexion sqlConn;
    ResultSet rs;
    PreparedStatement pstmt;
    Connection conexion;
    private ItemsBean dtData;
    //private ComboBoxBean dtCmb;
    List<ItemsBean> listItems;
    List<ComboBoxBean> listCmb;

	public ItemsDAO() {
	}

	public String fnDeleteItems(String p_item_id, String p_organization_id, String p_user_id){
        String lstSqlQuery="";
        String linReturn="";

        lstSqlQuery = "SELECT Item_delete_fn (?, ?, ?) regreso";
        sqlConn = new MySqlConexion();

        conexion = sqlConn.getConexion();

        try {
            pstmt = conexion.prepareStatement(lstSqlQuery);
            pstmt.setString(1,p_item_id);
            pstmt.setString(2,p_organization_id);
            pstmt.setString(3,p_user_id);
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
	
	public String fnUpdateItems(int p_item_id, int p_organization_id, String p_name, String p_description, 
			String p_type, String p_barcode, String p_enabled_flag, Date p_end_date, 
			String p_segment_context,
			String p_segment1, String p_segment2, String p_segment3, String p_segment4, String p_segment5, 
			String p_segment6, String p_segment7, String p_segment8, String p_segment9, int p_user_id, int p_org_id){
        String lstSqlQuery="";
        String linReturn="";

        lstSqlQuery = "SELECT Item_update_fn (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) regreso";
        sqlConn = new MySqlConexion();

        conexion = sqlConn.getConexion();

        try {
            pstmt = conexion.prepareStatement(lstSqlQuery);
            pstmt.setInt(1,p_item_id);
            pstmt.setInt(2,p_organization_id);
            pstmt.setString(3,p_name);
            pstmt.setString(4,p_description);
            pstmt.setString(5,p_type);
            pstmt.setString(6,p_barcode);
            pstmt.setString(7,p_enabled_flag);
            pstmt.setDate(8,p_end_date);
            pstmt.setString(9,p_segment_context);
            pstmt.setString(10,p_segment1);
            pstmt.setString(11,p_segment2);
            pstmt.setString(12,p_segment3);
            pstmt.setString(13,p_segment4);
            pstmt.setString(14,p_segment5);
            pstmt.setString(15,p_segment6);
            pstmt.setString(16,p_segment7);
            pstmt.setString(17,p_segment8);
            pstmt.setString(18,p_segment9);
            pstmt.setInt(19,p_user_id);
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
	
	public String fnInsertItems(int p_organization_id, String p_name, String p_description, String p_type, String p_barcode, String p_enabled_flag, Date p_start_date, Date p_end_date, String p_segment_context,
			String p_segment1, String p_segment2, String p_segment3, String p_segment4, String p_segment5, 
			String p_segment6, String p_segment7, String p_segment8, String p_segment9, int p_user_id, int p_org_id){
        String lstSqlQuery="";
        String linReturn="";

        lstSqlQuery = "SELECT Item_insert_fn (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) regreso";
        sqlConn = new MySqlConexion();

        conexion = sqlConn.getConexion();

        try {
            pstmt = conexion.prepareStatement(lstSqlQuery);
            pstmt.setInt(1,p_organization_id);
            pstmt.setString(2,p_name);
            pstmt.setString(3,p_description);
            pstmt.setString(4,p_type);
            pstmt.setString(5,p_barcode);
            pstmt.setString(6,p_enabled_flag);
            pstmt.setDate(7,p_start_date);
            pstmt.setDate(8,p_end_date);
            pstmt.setString(9,p_segment_context);
            pstmt.setString(10,p_segment1);
            pstmt.setString(11,p_segment2);
            pstmt.setString(12,p_segment3);
            pstmt.setString(13,p_segment4);
            pstmt.setString(14,p_segment5);
            pstmt.setString(15,p_segment6);
            pstmt.setString(16,p_segment7);
            pstmt.setString(17,p_segment8);
            pstmt.setString(18,p_segment9);
            pstmt.setInt(19,p_user_id);
            pstmt.setInt(20,p_org_id);
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
	
	public int fnItemExist(String p_rfc){
		//public int fnLoginValidate(String p_username, String p_password){
        String lstSqlQuery="";
        int linReturn=0;

        lstSqlQuery = " SELECT COUNT(*) existe "+
                      " FROM Items "+
                      " WHERE segment1 =  ? ";
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
	public List<ItemsBean> fnLoadData(String p_name, String p_description, String p_segment1){
		String lstSqlQuery="";
		listItems = new ArrayList<ItemsBean>();
        lstSqlQuery = " SELECT item_id, organization_id, name, description, type, barcode, enabled_flag, start_date,  "+
		              "        end_date, segment_context,  segment1, segment2, segment3, segment4,  "+
		              "        segment5, segment6, segment7, segment8, segment9 "+
		              " FROM Items_v "+
		              " WHERE 1 = 1 ";
                	  if (!p_name.equals("")){
                		  lstSqlQuery = lstSqlQuery + " AND UPPER(p_name) LIKE UPPER('"+p_name+"') ";
                	  }
        			  if (!p_description.equals("")){
        				 lstSqlQuery = lstSqlQuery + " AND UPPER(description) LIKE UPPER('"+p_description+"') ";
                	  }
        			  if (!p_segment1.equals("")){
        				 lstSqlQuery = lstSqlQuery + " AND UPPER(segment1) LIKE UPPER('"+p_segment1+"') ";
                	  }
        			  lstSqlQuery = lstSqlQuery + " ORDER BY segment1, name, description, item_id";
        sqlConn = new MySqlConexion();
        
        conexion = sqlConn.getConexion();

        try {
            pstmt = conexion.prepareStatement(lstSqlQuery);
            rs = pstmt.executeQuery();
            while (rs.next()){
            	dtData = new ItemsBean(rs.getInt("item_id"), rs.getInt("organization_id"), rs.getString("name"),
            			               rs.getString("description"), rs.getString("type"), rs.getString("barcode"), 
            			               rs.getString("enabled_flag"), rs.getDate("start_date"), rs.getDate("end_date"), 
            			               rs.getString("segment_context"), rs.getString("segment1"), rs.getString("segment2"), 
            			               rs.getString("segment3"), rs.getString("segment4"), rs.getString("segment5"), 
            			               rs.getString("segment6"), rs.getString("segment7"), 
            			rs.getString("segment8"), rs.getString("segment9"));
            	listItems.add(dtData);
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
        return listItems;
	}

}

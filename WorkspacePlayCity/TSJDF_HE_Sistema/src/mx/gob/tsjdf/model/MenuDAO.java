package mx.gob.tsjdf.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import mx.gob.tsjdf.bean.SessionMenuBean;
import mx.gob.tsjdf.db.ConnectionDB;

public class MenuDAO {
	
	Connection        con ;
	PreparedStatement psmt;
	ResultSet 		  rs;
	private SessionMenuBean dtMenu;
	List<SessionMenuBean> listMenu;
	private SessionMenuBean dtSubmenu;
	List<SessionMenuBean> listSubmenu;
	
	
	public List<SessionMenuBean> getMenus(int idRol){
		listMenu = new ArrayList<SessionMenuBean>();
		ConnectionDB connectionDB = new ConnectionDB();
		String sql = "SELECT  DISTINCT \n"+
					 "        rm.id_menu, m.nombre menu, m.url \n"+
				     "      , rm.id_rol  \n"+
					 "FROM    bdhe.dbo.MENUS      m \n"+
					 "      , bdhe.dbo.SUBMENUS   sm \n"+
					 "      , bdhe.dbo.ROLES_MENU rm \n"+
				     "WHERE   m.id_menu     = sm.id_menu \n"+
					 "    AND rm.id_menu    = sm.id_menu \n"+
					 "    AND rm.id_submenu = sm.id_submenu \n"+
					 "    AND rm.id_rol     = ? \n";
		try {
			con = connectionDB.getConexion();
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, idRol);
			rs = psmt.executeQuery();
			while(rs.next()){
				dtMenu = new SessionMenuBean(rs.getInt("id_menu"),rs.getInt("id_rol"), 
						rs.getString("menu"), rs.getString("url"));
				listMenu.add(dtMenu);
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				psmt.close();
				rs.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listMenu;
	}
	
	public List<SessionMenuBean> getSubmenus(String idRol, String idMenu){
		listSubmenu = new ArrayList<SessionMenuBean>();
		ConnectionDB connectionDB = new ConnectionDB();
		String sql = "SELECT  DISTINCT \n"+
					 "        rm.id_menu, rm.id_submenu, sm.nombre submenu, sm.url \n"+
				     "      , rm.id_rol  \n"+
					 "FROM    bdhe.dbo.MENUS      m \n"+
					 "      , bdhe.dbo.SUBMENUS   sm \n"+
					 "      , bdhe.dbo.ROLES_MENU rm \n"+
				     "WHERE   m.id_menu     = sm.id_menu \n"+
					 "    AND rm.id_menu    = sm.id_menu \n"+
					 "    AND rm.id_submenu = sm.id_submenu \n"+
					 "    AND rm.id_rol     = ? \n"+
					 "    AND rm.id_menu    = ? \n";
		try {
			con = connectionDB.getConexion();
			psmt = con.prepareStatement(sql);
			psmt.setString(1, idRol);
			psmt.setString(2, idMenu);
			rs = psmt.executeQuery();
			while(rs.next()){
				dtSubmenu = new SessionMenuBean(rs.getInt("id_menu"), rs.getInt("id_rol"), rs.getInt("id_submenu"), 
						rs.getString("submenu"), rs.getString("url"));
				listSubmenu.add(dtSubmenu);
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				psmt.close();
				rs.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listSubmenu;
	}
}

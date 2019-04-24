/*
* Project: AISA
*
* File: MenuDAO.java
*
* Created on: Abril 5, 2019 at 11:00
*
* Copyright (c) - Kaz Consulting - 2019
*/

package mx.com.televisa.playcity.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import mx.com.televisa.playcity.beans.SessionMenuBean;
import mx.com.televisa.playcity.model.db.ConnectionDB;

/*
* Clase de acceso a datos de menús y submenús   
* 
* @author Kaz Consulting / Jesus Argumedo
*
* @version 1.0.0
*
* @date Abril 5, 2019 at 11:00 am
*/

public class MenuDAO {
	
	Connection              poConnection;
	PreparedStatement       poPreparedStatement;
	ResultSet 		        poResultSet;
	private SessionMenuBean poDtMenu;
	List<SessionMenuBean>   paListMenu;
	private SessionMenuBean poDtSubmenu;
	List<SessionMenuBean>   paListSubmenu;
	
	//Obtiene la lista de los menús de la BD
	public List<SessionMenuBean> getMenus(){
		paListMenu = new ArrayList<SessionMenuBean>();
		ConnectionDB loConnectionDB = new ConnectionDB();
		String lsSql = "SELECT  DISTINCT \n"+
					 "        m.id_menu, m.nom_menu, m.nom_url \n"+
					 "FROM    PC_MENU_TAB      m \n"+
					 "      , PC_SUBMENU_TAB   sm \n"+
				     "WHERE   m.id_menu     = sm.id_menu \n";
		try {
			poConnection = loConnectionDB.getConexion();
			poPreparedStatement = poConnection.prepareStatement(lsSql);
			poResultSet = poPreparedStatement.executeQuery();
			while(poResultSet.next()){
				poDtMenu = new SessionMenuBean(poResultSet.getInt("id_menu"),
						poResultSet.getString("nom_menu"),
						poResultSet.getString("nom_url"));
				paListMenu.add(poDtMenu);
			}
		} catch (NamingException | SQLException toException) {
			toException.printStackTrace();
		}finally {
			try {
				poPreparedStatement.close();
				poResultSet.close();
				poConnection.close();
			} catch (SQLException toException) {
				toException.printStackTrace();
			}
		}
		return paListMenu;
	}
	
	//Obtiene la lista de los submenús de la BD
	public List<SessionMenuBean> getSubmenus(String tsIdMenu){
		paListSubmenu = new ArrayList<SessionMenuBean>();
		ConnectionDB loConnectionDB = new ConnectionDB();
		String sql = "SELECT  DISTINCT \n"+
					 "        m.id_menu, sm.id_submenu, sm.NOM_SUBMENU, sm.nom_url \n"+
					 "FROM    pc_menu_tab      m \n"+
					 "      , pc_submenu_tab   sm \n"+
				     "WHERE   m.id_menu     = sm.id_menu \n"+
					 "    AND m.id_menu    = ? \n";
		try {
			poConnection = loConnectionDB.getConexion();
			poPreparedStatement = poConnection.prepareStatement(sql);
			poPreparedStatement.setString(1, tsIdMenu);
			poResultSet = poPreparedStatement.executeQuery();
			while(poResultSet.next()){
				poDtSubmenu = new SessionMenuBean(poResultSet.getInt("id_menu"), poResultSet.getInt("id_submenu"), 
						poResultSet.getString("nom_submenu"), poResultSet.getString("nom_url"));
				paListSubmenu.add(poDtSubmenu);
			}
		} catch (NamingException | SQLException toException) {
			toException.printStackTrace();
		}finally {
			try {
				poPreparedStatement.close();
				poResultSet.close();
				poConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return paListSubmenu;
	}
}

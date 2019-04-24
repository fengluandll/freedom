package mx.gob.tsjdf.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import mx.gob.tsjdf.bean.AreasUsuarioBean;
import mx.gob.tsjdf.db.ConnectionDB;

@ManagedBean(name = "areasUsuarioDAO")
@ApplicationScoped
public class AreasUsuarioDAO {
	
	private static org.apache.log4j.Logger logger = Logger.getLogger(UsersDAO.class);
	
	Connection        con ;
	PreparedStatement psmt;
	ResultSet 		  rs;
	ConnectionDB connectionDB;
	
	List<AreasUsuarioBean> listAreas;
	
	public boolean nuevaAreaUsusario(int idUsuario,String idArea, int idUsuarioC){
		boolean bandera = false;
		String sql = "INSERT INTO bdhe.dbo.areas_usuario (id_usuario,\n"+
					 "                              id_area,\n"
					 + "                            created_by,"
					 + "						    created_date)\n"+
					 " VALUES(?,?,?,getdate())";
		connectionDB = new ConnectionDB();
		try {
			logger.info("");
			con = connectionDB.getConexion();
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setInt(1, idUsuario);
			psmt.setString(2, idArea);
			psmt.setInt(3, idUsuarioC);
			psmt.executeUpdate();
			bandera = true;
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
		return bandera;
	}
	
	public List<AreasUsuarioBean> obtenAreasUsuario(int idUsuario){
		List<AreasUsuarioBean> listData;
		AreasUsuarioBean dtData;
		listData = new ArrayList<AreasUsuarioBean>();
		ConnectionDB connectionDB = new ConnectionDB();
		String sql = "SELECT  au.ID_USUARIO, au.ID_AREA, wu.STD_N_WORK_UNITESP \n"+
					 "FROM    bdhe.dbo.AREAS_USUARIO au, \n"+
				     "        std_work_unit wu \n"+
				     "WHERE   wu.std_id_work_unit = au.id_area COLLATE DATABASE_DEFAULT \n"+
				     "    AND au.ID_USUARIO = ? \n";
		try {
			logger.info(" ");
			con = connectionDB.getConexion();
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, idUsuario);
			rs = psmt.executeQuery();
			while(rs.next()){
				dtData = new AreasUsuarioBean();
				dtData.setIdUsuario(rs.getInt("ID_USUARIO"));
				dtData.setIdArea(rs.getString("ID_AREA"));
				dtData.setNombreArea(rs.getString("STD_N_WORK_UNITESP"));
				listData.add(dtData);
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
		return listData;
	}
	public Integer obtenIdUsuario(String numEmpleado){
		String sql = "SELECT id_usuario\n"+
				 " FROM bdhe.dbo.USUARIOS \n"+
				 " WHERE NUM_EMPLEADO = ? \n";
	connectionDB = new ConnectionDB();
	Integer nmCompleto = null;
	try {
		con = connectionDB.getConexion();
		psmt = con.prepareStatement(sql);
		psmt.setString(1, numEmpleado);
		rs = psmt.executeQuery();
		
		while(rs.next()){
			nmCompleto = rs.getInt(1);
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
	return nmCompleto;
}
}

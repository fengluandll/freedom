package mx.gob.tsjdf.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import org.apache.log4j.Logger;

import mx.gob.tsjdf.bean.QuincenaActualBean;
import mx.gob.tsjdf.db.ConnectionDB;

public class QuincenasActualDAO {
	
	Connection        con ;
	PreparedStatement psmt;
	ResultSet 		  rs;
	ConnectionDB connectionDB;
	
	private static org.apache.log4j.Logger logger = Logger.getLogger(LoginDAO.class);
	
	List<QuincenaActualBean> listQuincenasAct; 
	
	public List<QuincenaActualBean> getDatosQuincenaActual(){
		List<QuincenaActualBean> listData;
		QuincenaActualBean dtData;
		listData = new ArrayList<QuincenaActualBean>();
		ConnectionDB connectionDB = new ConnectionDB();
		String sql = "SELECT  qa.id_quincena_actual \n"+
					 "      , qa.fecha_paga  \n"+
				     "      , (SELECT x.SCO_NM_PAYENG FROM M4SCO_HT_PAYS x WHERE YEAR(x.SCO_DT_START) >= '2015' AND x.sco_dt_accrued = CONVERT(DATETIME, qa.fecha_paga , 103)) fecha_paga_nombre  \n"+
				     "      , activo  \n"+
				     "      , case  activo when 'Y' then 'Activa' else 'Inactiva' end activo_nombre  \n"+
				     "FROM bdhe.dbo.QUINCENA_ACTUAL qa  \n";
		try {
			logger.info("Lista de Layout");
			con = connectionDB.getConexion();
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()){
				dtData = new QuincenaActualBean();
				dtData.setIdQuincenaActual(rs.getInt("id_quincena_actual"));
				dtData.setFechaPaga(rs.getString("fecha_paga"));
				dtData.setFechaPagaNombre(rs.getString("fecha_paga_nombre"));
				dtData.setActivo(rs.getString("activo"));
				dtData.setActivoNombre(rs.getString("activo_nombre"));
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

	public Integer getExisteQuincena(String fechaPaga){
		Integer intRegreso = 0;
		ConnectionDB connectionDB = new ConnectionDB();
		String sql = "SELECT  count(*) existe \n"+
				     "FROM bdhe.dbo.QUINCENA_ACTUAL qa  \n"+
				     "WHERE qa.fecha_paga = ?";
		try {
			logger.info("Lista de Layout");
			con = connectionDB.getConexion();
			psmt = con.prepareStatement(sql);
			psmt.setString(1, fechaPaga);
			rs = psmt.executeQuery();
			while(rs.next()){
				intRegreso = rs.getInt("existe");
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
		return intRegreso;
	}
	
	public boolean guardaRegistro(String fechaPaga, String flagActivo, Integer usuarioCreacion){
		boolean ok = false;
		String sql = "INSERT INTO bdhe.dbo.QUINCENA_ACTUAL (fecha_paga, activo, created_by, created_date) \n"+
				     "VALUES (?,'Y',?,getdate()) ";
		connectionDB = new ConnectionDB();
		try {
			con = connectionDB.getConexion();
			psmt = con.prepareStatement(sql);
			psmt.setString(1, fechaPaga);
			psmt.setInt(2, usuarioCreacion);
			psmt.executeUpdate();
			ok = true;
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				psmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ok;
	}
	
	public boolean cambiaStatusQuincenaExiste(String fechaPaga, Integer usuarioModificacion){
		boolean ok = false;
		String sql = "UPDATE bdhe.dbo.QUINCENA_ACTUAL \n"+
				     "SET    activo = 'Y', \n"+
				     "       last_update_by = ?, \n"+
				     "       last_update_date = getdate()  \n"+
					 "WHERE  fecha_paga  = ?";
		connectionDB = new ConnectionDB();
		try {
			con = connectionDB.getConexion();
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, usuarioModificacion);
			psmt.setString(2, fechaPaga);
			psmt.executeUpdate();
			ok = true;
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				psmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ok;
	}
}

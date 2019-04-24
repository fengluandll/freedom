package mx.gob.tsjdf.cfdi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NamingException;

import mx.gob.tsjdf.db.ConnectionDB;

public class CfdiDAO {
	
	ConnectionDB 	  connectionDB;
	Connection   	  con;
	PreparedStatement psmt;
	ResultSet 		  rs;
	
	public boolean existeEmpleadosNomina(String fechaPaga,String tipoNomina){
		String sql = "select count(*)cantidad from M4CME_AC_HR_PERIOD where sco_dt_pay = convert(datetime, ? , 103)";
		connectionDB = new ConnectionDB();
		boolean existe = false;
		try {
			if(tipoNomina.equals("tribunal")){
				con = connectionDB.getConexionCFDI();
			}else if(tipoNomina.equals("consejo")){
				con = connectionDB.getConexionConsejoCFDI();
			}
			psmt = con.prepareStatement(sql);
			psmt.setString(1, fechaPaga);
			rs = psmt.executeQuery();
			int count = 0;
			while(rs.next()){
				count = rs.getInt(1);
			}
			if(count > 0){
				existe = true;
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
		return existe;
	}
	
	public int existeAlgunosEmpleadosNomina(String fechaPaga,String empleados,String tipoNomina){
		String sql = "select count(distinct  sco_id_hr)cantidad from M4CME_AC_HR_PERIOD where sco_dt_pay = convert(datetime, '"+ fechaPaga +"', 103)\n"+
					 "AND sco_id_hr IN ("+empleados+")";
		connectionDB = new ConnectionDB();
		Statement stm = null;
		int count = 0;
		try {
			if(tipoNomina.equals("tribunal")){
				con = connectionDB.getConexionCFDI();
			}else if(tipoNomina.equals("consejo")){
				con = connectionDB.getConexionConsejoCFDI();
			}
			
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			
			while(rs.next()){
				count = rs.getInt(1);
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				stm.close();
				rs.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}

	public String dameRutaCFDI(int idTabla){
		String sql = "SELECT valor FROM bdhe.dbo.CONF_CFDI WHERE id_conf_cfdi = ?";
		connectionDB = new ConnectionDB();
		String ruta = null;
		try {
			con = connectionDB.getConexion();
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, idTabla);
			rs = psmt.executeQuery();
			while(rs.next()){
				ruta = rs.getString(1);
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
		return ruta;
	}
}

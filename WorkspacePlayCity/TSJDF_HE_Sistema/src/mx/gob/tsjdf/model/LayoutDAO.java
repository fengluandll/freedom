package mx.gob.tsjdf.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import org.apache.log4j.Logger;

import mx.gob.tsjdf.bean.LayoutBean;
import mx.gob.tsjdf.db.ConnectionDB;

public class LayoutDAO {
	
	Connection        con ;
	PreparedStatement psmt;
	ResultSet 		  rs;
	ConnectionDB connectionDB;
	
	private static org.apache.log4j.Logger logger = Logger.getLogger(LoginDAO.class);
	
	List<LayoutBean> listLayout;
	
	
	public List<LayoutBean> getDatosLayout(String fechaPaga, String tipoReporte, String fechaAplicacionPaga, String fechaComputo){
		List<LayoutBean> listData;
		LayoutBean dtData;
		listData = new ArrayList<LayoutBean>();
		ConnectionDB connectionDB = new ConnectionDB();
		String sql = "SELECT   s.id_solicitud, \n"+
					 "         s.num_empleado, \n"+
				     "         s.a_paterno,  \n"+
				     "         s.a_materno,  \n"+
				     "         s.nombre,  \n"+
				     "         CONVERT(VARCHAR, s.fecha_inicial, 103) fecha_inicial,  \n"+
				     "         CONVERT(VARCHAR, s.fecha_final, 103) fecha_final,  \n"+
				     "         sum(se.NUM_HE_LABORADAS) total_he,  \n"+
				     "         CONVERT(VARCHAR, s.fecha_paga, 103) fecha_paga,  \n"+
				     "         count(se.ID_SOLICITUD) total_dias_he  \n"+
					 "FROM    bdhe.dbo.SOLICITUDES            s \n"+
					 "      , bdhe.dbo.SOLICITUD_HE_LABORADAS se \n"+
				     "WHERE    s.ID_SOLICITUD = se.ID_SOLICITUD \n";
		if(tipoReporte.equals("Todas")){
			sql = sql + "    AND  id_estatus_solicitud IN (3,6) \n";
		}else{
			sql = sql + "    AND  id_estatus_solicitud IN (3) \n";
		}
		sql = sql +  "    AND  fecha_paga          = CONVERT(DATETIME, ?, 103) \n"+
					 "GROUP BY s.id_solicitud, s.num_empleado, s.a_paterno, s.a_materno, \n"+
					 "         s.nombre, s.fecha_inicial, s.fecha_final, s.fecha_paga";
		try {
			logger.info("Lista de Layout");
			con = connectionDB.getConexion();
			psmt = con.prepareStatement(sql);
			psmt.setString(1, fechaPaga);
			rs = psmt.executeQuery();
			while(rs.next()){
				dtData = new LayoutBean();
				dtData.setIdSolicitud(rs.getInt("id_solicitud"));
				dtData.setMensajes("");
				dtData.setIdEmpleado(rs.getString("num_empleado"));
				dtData.setIdIncidencia("006");
				dtData.setFechaIniAplicacion(fechaAplicacionPaga);
				dtData.setFechaFinAplicacion(fechaAplicacionPaga);
				dtData.setFechaInicio(fechaAplicacionPaga);
				dtData.setFechaFin(fechaAplicacionPaga);
				dtData.setFechaPaga(rs.getString("fecha_paga"));
				dtData.setNumDiasHE(rs.getString("total_dias_he"));
				dtData.setNumHorasHE(rs.getString("total_he"));
				dtData.setUnidades(rs.getString("total_he"));
				dtData.setNombreCompleto(rs.getString("a_paterno")+" "+rs.getString("a_materno")+" "+rs.getString("nombre"));
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
	
	public boolean cambiaStatusSolicitudGenerada(int idSolicitud,int estatus, String numEmpGenera){
		boolean ok = false;
		String sql = "UPDATE bdhe.dbo.solicitudes \n"+
				     "SET    id_estatus_solicitud = ?, \n"+
				     "       FECHA_GENERA_LAYOUT  = getdate(), \n"+
				     "       NUM_EMPLEADO_GENERA  = ?\n"+
					 "WHERE  id_solicitud       = ?";
		connectionDB = new ConnectionDB();
		try {
			con = connectionDB.getConexion();
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, estatus);
			psmt.setString(2, numEmpGenera);
			psmt.setInt(3, idSolicitud);
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
	
	public List<LayoutBean> generarReporte(String fechaPaga, String tipoReporte, String fechaAplicacionPaga, String fechaComputo){
		return getDatosLayout(fechaPaga, tipoReporte,fechaAplicacionPaga,fechaComputo);
	}
	
	public boolean cambiaStatusQuincenaActiva(String fecha_paga){
		boolean ok = false;
		String sql = "UPDATE bdhe.dbo.QUINCENA_ACTUAL \n"+
				     "SET    activo = 'N' \n"+
					 "WHERE  fecha_paga  = ?";
		connectionDB = new ConnectionDB();
		try {
			con = connectionDB.getConexion();
			psmt = con.prepareStatement(sql);
			psmt.setString(1, fecha_paga);
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

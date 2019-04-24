package mx.gob.tsjdf.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import org.apache.log4j.Logger;

import mx.gob.tsjdf.bean.SolicitudesBean;
import mx.gob.tsjdf.db.ConnectionDB;

public class CapturarSolicitudesDAO {

	Connection        con ;
	PreparedStatement psmt;
	ResultSet 		  rs;
	ConnectionDB      connectionDB;
	
	List<SolicitudesBean> listSolicitudes;
	SolicitudesBean solicitudesBean;
	private static org.apache.log4j.Logger logger = Logger.getLogger(CapturarSolicitudesDAO.class);
	
	public List<SolicitudesBean> dameSolicitudesPorProcesar(String fechaQuincena){
		listSolicitudes = new ArrayList<>();
		String sql = "SELECT  s.id_solicitud \n"+
					 "      , s.num_empleado \n"+
					 "      , s.a_paterno \n"+
					 "      , s.a_materno \n"+
					 "      , s.nombre \n"+
					 "      , CONVERT(                           VARCHAR(10),s.fecha_inicial,103) fecha_inicial \n"+
					 "      , CONVERT(                           VARCHAR(10),s.fecha_final,103) fecha_final \n"+
					 "      , CONVERT(                           VARCHAR(10),s.fecha_paga,103) fecha_paga \n"+
					 "      , CAST(ROUND(s.total_he,2,1) AS      DECIMAL(18,2)) total_he \n"+
					 "      , CAST(ROUND(s.total_dias_he,2,1) AS DECIMAL(18,2))total_dias_he \n"+
					 "      , es.DESCRIPCION estado, es.id_estatus_solicitud, s.id_area \n"+
					 "FROM    bdhe.dbo.solicitudes s \n"+
					 "      , bdhe.dbo.ESTATUS_SOLICITUD es \n"+ 
					 "WHERE   s.id_estatus_solicitud  = es.id_estatus_solicitud"+
					 "    AND upper(es.DESCRIPCION)  IN (upper('No terminada'), upper('Terminada'), upper('Rechazada')) \n"+
					 "    AND fecha_paga              = CONVERT(DATETIME,?, 103) \n";
		connectionDB = new ConnectionDB();
		try {
			con = connectionDB.getConexion();
			psmt = con.prepareStatement(sql);
			psmt.setString(1, fechaQuincena);
			rs = psmt.executeQuery();
			while(rs.next()){
				solicitudesBean = new SolicitudesBean();
				solicitudesBean.setIdSolicitud(rs.getInt("id_solicitud"));
				solicitudesBean.setNumEmpleado(rs.getString("num_empleado"));
				solicitudesBean.setaPaterno(rs.getString("a_paterno"));
				solicitudesBean.setaMaterno(rs.getString("a_materno"));
				solicitudesBean.setNombre(rs.getString("nombre"));
				solicitudesBean.setFechaInicial(rs.getString("fecha_inicial"));
				solicitudesBean.setFechaFinal(rs.getString("fecha_final"));
				solicitudesBean.setFechaPaga(rs.getString("fecha_paga"));
				solicitudesBean.setTotalHe(rs.getInt("total_he"));
				solicitudesBean.setTotalDiasHe(rs.getInt("total_dias_he"));
				solicitudesBean.setStatusSolicitud(rs.getString("estado"));
				solicitudesBean.setIdstatusSolicitud(rs.getInt("id_estatus_solicitud"));
				solicitudesBean.setIdArea(rs.getString("id_area"));
				listSolicitudes.add(solicitudesBean);
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
		return listSolicitudes;
	}
	
	public List<SolicitudesBean> dameSolicitudesCreadasQuincena(String fechaQuincena){
		listSolicitudes = new ArrayList<>();
		String sql = "SELECT  s.id_solicitud \n"+
					 "      , s.num_empleado \n"+
					 "      , s.a_paterno \n"+
					 "      , s.a_materno \n"+
					 "      , s.nombre \n"+
					 "      , CONVERT(                           VARCHAR(10),s.fecha_inicial,103) fecha_inicial \n"+
					 "      , CONVERT(                           VARCHAR(10),s.fecha_final,103) fecha_final \n"+
					 "      , CONVERT(                           VARCHAR(10),s.fecha_paga,103) fecha_paga \n"+
					 "      , es.DESCRIPCION estado, es.id_estatus_solicitud, s.id_area \n"+
					 "      , s.NUM_EMPLEADO_MODIFICA empleado_crea, s.FECHA_MODIFICA fecha_creacion, s.NUM_EMPLEADO_AUTORIZA, s.FECHA_AUTORIZA, s.NUM_EMPLEADO_GENERA, FECHA_GENERA_LAYOUT \n"+
					 "      , (SELECT sum(w.NUM_HE_LABORADAS) FROM bdhe.dbo.SOLICITUD_HE_LABORADAS w where w.ID_SOLICITUD = s.ID_SOLICITUD ) total_he \n"+
					 "      , (SELECT count(w.ID_SOLICITUD_HE_ELABORADAS) FROM bdhe.dbo.SOLICITUD_HE_LABORADAS w where w.ID_SOLICITUD = s.ID_SOLICITUD ) total_dias_he \n"+
					 "FROM    bdhe.dbo.solicitudes s \n"+
					 "      , bdhe.dbo.ESTATUS_SOLICITUD es \n"+ 
					 "WHERE   s.id_estatus_solicitud  = es.id_estatus_solicitud \n"+
					 "    AND fecha_paga              = CONVERT(DATETIME,?, 103) \n";
		connectionDB = new ConnectionDB();
		try {
			logger.info("");
			logger.info(sql);
			con = connectionDB.getConexion();
			psmt = con.prepareStatement(sql);
			psmt.setString(1, fechaQuincena);
			rs = psmt.executeQuery();
			while(rs.next()){
				solicitudesBean = new SolicitudesBean();
				solicitudesBean.setIdSolicitud(rs.getInt("id_solicitud"));
				solicitudesBean.setNumEmpleado(rs.getString("num_empleado"));
				solicitudesBean.setaPaterno(rs.getString("a_paterno"));
				solicitudesBean.setaMaterno(rs.getString("a_materno"));
				solicitudesBean.setNombre(rs.getString("nombre"));
				solicitudesBean.setFechaInicial(rs.getString("fecha_inicial"));
				solicitudesBean.setFechaFinal(rs.getString("fecha_final"));
				solicitudesBean.setFechaPaga(rs.getString("fecha_paga"));
				solicitudesBean.setTotalHe(rs.getInt("total_he"));
				solicitudesBean.setTotalDiasHe(rs.getInt("total_dias_he"));
				solicitudesBean.setStatusSolicitud(rs.getString("estado"));
				solicitudesBean.setIdstatusSolicitud(rs.getInt("id_estatus_solicitud"));
				solicitudesBean.setIdArea(rs.getString("id_area"));
				//
				solicitudesBean.setNumEmpeadoCrea(rs.getString("empleado_crea"));
				solicitudesBean.setFechaCrea(rs.getString("fecha_creacion"));
				solicitudesBean.setNumEmpeadoAutoriza(rs.getString("NUM_EMPLEADO_AUTORIZA"));
				solicitudesBean.setFechaAutoriza(rs.getString("FECHA_AUTORIZA"));
				solicitudesBean.setNumEmpleadoGenera(rs.getString("NUM_EMPLEADO_GENERA"));
				solicitudesBean.setFechaGenera(rs.getString("FECHA_GENERA_LAYOUT"));
				//
				solicitudesBean.setTotalHe(rs.getInt("total_he"));
				solicitudesBean.setTotalDiasHe(rs.getInt("total_dias_he"));
				//
				solicitudesBean.setNombreCompleto(rs.getString("a_paterno")+" "+rs.getString("a_materno")+" "+rs.getString("nombre"));
				listSolicitudes.add(solicitudesBean);
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
		return listSolicitudes;
	}

	public List<SolicitudesBean> dameSolicitudesPorAprobar(String fechaQuincena){
		listSolicitudes = new ArrayList<>();
		String sql = "SELECT  s.id_solicitud \n"+
					 "      , s.num_empleado \n"+
					 "      , s.a_paterno \n"+
					 "      , s.a_materno \n"+
					 "      , s.nombre \n"+
					 "      , CONVERT(                           VARCHAR(10),s.fecha_inicial,103) fecha_inicial \n"+
					 "      , CONVERT(                           VARCHAR(10),s.fecha_final,103) fecha_final \n"+
					 "      , CONVERT(                           VARCHAR(10),s.fecha_paga,103) fecha_paga \n"+
					 "      , CAST(ROUND(s.total_he,2,1) AS      DECIMAL(18,2)) total_he \n"+
					 "      , CAST(ROUND(s.total_dias_he,2,1) AS DECIMAL(18,2))total_dias_he \n"+
					 "      , es.DESCRIPCION estado, es.id_estatus_solicitud, s.id_area \n"+
					 "FROM    bdhe.dbo.solicitudes s \n"+
					 "      , bdhe.dbo.ESTATUS_SOLICITUD es \n"+ 
					 "WHERE   s.id_estatus_solicitud  = es.id_estatus_solicitud"+
					 "    AND upper(es.DESCRIPCION)  IN (upper('Terminada')) \n"+
					 "    AND fecha_paga              = CONVERT(DATETIME,?, 103) \n";
		connectionDB = new ConnectionDB();
		try {
			logger.info(sql);
			con = connectionDB.getConexion();
			psmt = con.prepareStatement(sql);
			psmt.setString(1, fechaQuincena);
			rs = psmt.executeQuery();
			while(rs.next()){
				solicitudesBean = new SolicitudesBean();
				solicitudesBean.setIdSolicitud(rs.getInt("id_solicitud"));
				solicitudesBean.setNumEmpleado(rs.getString("num_empleado"));
				solicitudesBean.setaPaterno(rs.getString("a_paterno"));
				solicitudesBean.setaMaterno(rs.getString("a_materno"));
				solicitudesBean.setNombre(rs.getString("nombre"));
				solicitudesBean.setFechaInicial(rs.getString("fecha_inicial"));
				solicitudesBean.setFechaFinal(rs.getString("fecha_final"));
				solicitudesBean.setFechaPaga(rs.getString("fecha_paga"));
				solicitudesBean.setTotalHe(rs.getInt("total_he"));
				solicitudesBean.setTotalDiasHe(rs.getInt("total_dias_he"));
				solicitudesBean.setStatusSolicitud(rs.getString("estado"));
				solicitudesBean.setIdstatusSolicitud(rs.getInt("id_estatus_solicitud"));
				solicitudesBean.setIdArea(rs.getString("id_area"));
				listSolicitudes.add(solicitudesBean);
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
		return listSolicitudes;
	}
}

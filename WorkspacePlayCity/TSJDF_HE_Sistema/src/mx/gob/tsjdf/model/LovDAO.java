package mx.gob.tsjdf.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import org.apache.log4j.Logger;

import mx.gob.tsjdf.bean.HorasExtrasBean;
import mx.gob.tsjdf.bean.LoginBean;
import mx.gob.tsjdf.bean.LovBean;
import mx.gob.tsjdf.bean.SolicitudesBean;
import mx.gob.tsjdf.db.ConnectionDB;

public class LovDAO {

	Connection        con ;
	PreparedStatement psmt;
	ResultSet 		  rs;
	private LovBean dtLov;
	List<LovBean> listLov;
	ConnectionDB connectionDB;
	SolicitudesBean solicitudesBean;
	HorasExtrasBean horasExtrasBean;
	List<HorasExtrasBean> listHoras;
	
	private static org.apache.log4j.Logger logger = Logger.getLogger(LovDAO.class);
	
	public boolean eliminaSolicitud(int idSolicitud){
		String sql = "DELETE FROM bdhe.dbo.SOLICITUDES where id_solicitud = ?";
		connectionDB = new ConnectionDB();
		boolean ok = false;
		try {
			con = connectionDB.getConexion();
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, idSolicitud);
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
	
	public boolean existeMismafecha(String fecha,int idSolicitud){
		String sql = "SELECT COUNT(*) FROM bdhe.dbo.SOLICITUD_HE_LABORADAS\n"+
					 "WHERE CONVERT(DATETIME, fecha , 103) = ? AND id_solicitud = ?";
		boolean existe = false;
		connectionDB = new ConnectionDB();
		try {
			con = connectionDB.getConexion();
			psmt = con.prepareStatement(sql);
			psmt.setString(1, fecha);
			psmt.setInt(2, idSolicitud);
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
	
	public boolean existeMismafechaModificable(String fecha,int idSolicitud,int idSolicitudHE){
		String sql = "SELECT COUNT(*) FROM bdhe.dbo.SOLICITUD_HE_LABORADAS\n"+
					 "	WHERE CONVERT(DATETIME, fecha , 103) = ?\n"+ 
					 "	AND id_solicitud = ?\n"+
					 "	AND id_solicitud_he_elaboradas <> ?";
		boolean existe = false;
		connectionDB = new ConnectionDB();
		try {
			con = connectionDB.getConexion();
			psmt = con.prepareStatement(sql);
			psmt.setString(1, fecha);
			psmt.setInt(2, idSolicitud);
			psmt.setInt(3, idSolicitudHE);
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
	
	public boolean guardaTodaSolicitud(String jornada,int idSolicitud,int estatus){
		boolean ok = false;
		String sql = "UPDATE bdhe.dbo.solicitudes \n "+
					 "	SET jornada_ordinaria    = ?,\n"+
					 "	    id_estatus_solicitud = ? \n"+
					 "	WHERE id_solicitud       = ? ";
		connectionDB = new ConnectionDB();
		try {
			con = connectionDB.getConexion();
			psmt = con.prepareStatement(sql);
			psmt.setString(1, jornada);
			psmt.setInt(2, estatus);
			psmt.setInt(3, idSolicitud);
			psmt.executeUpdate();
			ok = true;
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
		return ok;
	}
	
	public boolean aprobarSolicitud(int idSolicitud,int estatus, String numEmpleadoAutoriza, String motivoRechaza){
		boolean ok = false;
		String sql = "UPDATE bdhe.dbo.solicitudes \n"+
				 	"	SET id_estatus_solicitud  = ?, \n"+
				 	"	    NUM_EMPLEADO_AUTORIZA = ?, \n"+
				 	"	           FECHA_AUTORIZA = getdate(), \n"+
				 	"              MOTIVO_RECHAZO = ? \n"+
					 "	WHERE id_solicitud        = ?";
		connectionDB = new ConnectionDB();
		try {
			con = connectionDB.getConexion();
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, estatus);
			psmt.setString(2, numEmpleadoAutoriza);
			psmt.setString(3, motivoRechaza);
			psmt.setInt(4, idSolicitud);
			psmt.executeUpdate();
			ok = true;
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
		return ok;
	}
	
	
	
	public boolean EliminaHoraExtra(int idhe){
		String sql = "DELETE FROM bdhe.dbo.SOLICITUD_HE_LABORADAS where ID_SOLICITUD_HE_ELABORADAS = ?";
		boolean paso = false;
		connectionDB = new ConnectionDB();
		try {
			con = connectionDB.getConexion();
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, idhe);
			psmt.executeUpdate();
			paso = true;
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
		return paso;
	}
	
	public boolean editaHorasExtras(int idhe,String fecha,
								  String horaEntrada,String horaSalida,
								  String hrsLaboradas,String justificacion){
		boolean ok = false;
		String sql = "UPDATE bdhe.dbo.SOLICITUD_HE_LABORADAS \n"+
					 "	SET     FECHA            = ?,\n"+
					 "	        HORA_ENTRADA     = ?,\n"+
					 "	        HORA_SALIDA      = ?,\n"+
					 "	        NUM_HE_LABORADAS = ?,\n"+
					 "	        JUSTIFICACION    = ?,\n"+
					 "          FECHA_ACTUALIZACION = getdate()\n"+
					 "  WHERE ID_SOLICITUD_HE_ELABORADAS = ?";
		connectionDB = new ConnectionDB();
		try {
			con = connectionDB.getConexion();
			psmt = con.prepareStatement(sql);
			psmt.setString(1, fecha);
			psmt.setString(2, horaEntrada);
			psmt.setString(3, horaSalida);
			psmt.setString(4, hrsLaboradas);
			psmt.setString(5, justificacion);
			psmt.setInt(6, idhe);
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
	
	public List<HorasExtrasBean> dameHorasExtras(int idSolicitud){
		listHoras = new ArrayList<>();
		String sql = "SELECT  ID_SOLICITUD,\n"+
					 "	        CONVERT(VARCHAR(10),FECHA,103)FECHA,\n"+
					 "	        HORA_ENTRADA,\n"+
					 "	        HORA_SALIDA,\n"+
					 "	        NUM_HE_LABORADAS,\n"+
					 "	        JUSTIFICACION,\n"+
					 "          id_solicitud_he_elaboradas\n"+
					 "	FROM bdhe.dbo.SOLICITUD_HE_LABORADAS\n"+ 
					 "	WHERE id_solicitud = ?";
		//logger.info(sql);
		connectionDB = new ConnectionDB();
		try {
			con = connectionDB.getConexion();
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, idSolicitud);
			rs = psmt.executeQuery();
			while(rs.next()){
				horasExtrasBean = new HorasExtrasBean();
				horasExtrasBean.setIdSolicitud(rs.getInt(1));
				horasExtrasBean.setFecha(rs.getString(2));
				horasExtrasBean.setHoraEntrada(rs.getString(3));
				horasExtrasBean.setHoraSalida(rs.getString(4));
				horasExtrasBean.setNumHorasLaboradas(rs.getString(5));
				horasExtrasBean.setJustificacion(rs.getString(6));
				horasExtrasBean.setIdSolicitudHe(rs.getInt(7));
				listHoras.add(horasExtrasBean);
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
		return listHoras;
	}
	
	public boolean guardaHorasextras(int idSolicitud,String fecha,
								  String horaEntrada,String horaSalida,
								  String hrsLaboradas,String justificacion){
		boolean exito = false;
		String sql = "INSERT INTO bdhe.dbo.SOLICITUD_HE_LABORADAS(ID_SOLICITUD,\n"+
					 "				   FECHA,\n"+
					 "				   HORA_ENTRADA,\n"+
					 "				   HORA_SALIDA,\n"+
					 "				   NUM_HE_LABORADAS,\n"+
					 "				   JUSTIFICACION,\n"+
					 "				   FECHA_ACTUALIZACION)\n"+ 
					 " VALUES(?, ?, ?, ?, ?, ?, getdate())";
		connectionDB = new ConnectionDB();
		try {
			con = connectionDB.getConexion();
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, idSolicitud);
			psmt.setString(2, fecha);
			psmt.setString(3, horaEntrada);
			psmt.setString(4, horaSalida);
			psmt.setString(5, hrsLaboradas);
			psmt.setString(6, justificacion);
			psmt.executeUpdate();
			exito = true;
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
		return exito;
	}
	
	public int obtendIdSolicitud(String numEmpleado,String fechaPaga){
		
		String sql = "SELECT ID_SOLICITUD FROM bdhe.dbo.solicitudes WHERE num_empleado = ? AND fecha_paga = ?";
		int count = 0;
		try {
			connectionDB = new ConnectionDB();
			con = connectionDB.getConexion();
			psmt = con.prepareStatement(sql);
			psmt.setString(1, numEmpleado);
			psmt.setString(2, fechaPaga);
			rs = psmt.executeQuery();
			
			while(rs.next()){
				count = rs.getInt(1);
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
		return count;
	}
	
	public String obtenNombreDia(String fecha){
		
		String sql = "SELECT UPPER(DATENAME(DW, CONVERT(DATETIME, ?, 103))) ";
		String nombreDia = "";
		try {
			connectionDB = new ConnectionDB();
			con = connectionDB.getConexion();
			psmt = con.prepareStatement(sql);
			psmt.setString(1, fecha);
			rs = psmt.executeQuery();
			
			while(rs.next()){
				nombreDia = rs.getString(1);
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
		return nombreDia;
	}
	
	public Integer obtenDiaFeriado(String fecha){
		
		String sql = "SELECT COUNT(*) regreso FROM bdhe.dbo.DIAS_FERIADOS WHERE FECHA = CONVERT(DATETIME,?, 103)";
		Integer diaFeriado = 0;
		try {
			connectionDB = new ConnectionDB();
			con = connectionDB.getConexion();
			psmt = con.prepareStatement(sql);
			psmt.setString(1, fecha);
			rs = psmt.executeQuery();
			
			while(rs.next()){
				diaFeriado = rs.getInt(1);
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
		return diaFeriado;
	}
	
	public String obtenAreaEmpleado(String numEmpleado){
		
		String sql = "SELECT  SUBSTRING(SCO_ID_WORK_UNIT,3,1) codigo \n"+
			         "FROM    M4SCO_H_HR_WUNIT "+
			         "WHERE   SCO_DT_START <= GETDATE() "+
			         "    AND SCO_DT_END   >= GETDATE() "+
			         "    AND SCO_ID_HR     = ?";
		String regreso = "";
		try {
			connectionDB = new ConnectionDB();
			con = connectionDB.getConexion();
			psmt = con.prepareStatement(sql);
			psmt.setString(1, numEmpleado);
			rs = psmt.executeQuery();
			
			while(rs.next()){
				regreso = rs.getString(1);
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
		return regreso;
	}
	
	public Integer obtenValidaMesFechaPagaFechaHE(String fecha, String fechaPaga){
		
		String sql = "SELECT  count(*) \n "+
				 	 "FROM    M4SCO_HT_PAYS pays \n"+
					 "WHERE   pays.sco_dt_accrued = CONVERT(DATETIME, ? , 103) \n"+
					 "    AND pays.SCO_DT_START  <= CONVERT(DATETIME, ? , 103) \n"+
				     "    AND pays.SCO_DATE_END  >= CONVERT(DATETIME, ? , 103)";
		Integer valida = 0;
		try {
			connectionDB = new ConnectionDB();
			con = connectionDB.getConexion();
			psmt = con.prepareStatement(sql);
			psmt.setString(1, fechaPaga);
			psmt.setString(2, fecha);
			psmt.setString(3, fecha);
			rs = psmt.executeQuery();
			
			while(rs.next()){
				valida = rs.getInt(1);
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
		return valida;
	}
	
	public Integer obtenNumeroDeHERegistradas(Integer idSolicitud){
		
		String sql = "SELECT  sum(NUM_HE_LABORADAS) + 3 FROM    bdhe.dbo.SOLICITUD_HE_LABORADAS WHERE   ID_SOLICITUD = ?";
		Integer diaFeriado = 0;
		try {
			connectionDB = new ConnectionDB();
			con = connectionDB.getConexion();
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, idSolicitud);
			rs = psmt.executeQuery();
			
			while(rs.next()){
				diaFeriado = rs.getInt(1);
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
		return diaFeriado;
	}
	
	public Integer obtenFechaRepetida(Integer idSolicitud, String fecha){
		
		String sql = "SELECT  count(*) \n"+
		             "FROM    bdhe.dbo.SOLICITUD_HE_LABORADAS \n"+
				     "WHERE   ID_SOLICITUD = ? \n"+
		             "    AND cast(FECHA as date) = cast(CONVERT(DATETIME, ?, 103) as date)";
		Integer diaFeriado = 0;
		try {
			connectionDB = new ConnectionDB();
			con = connectionDB.getConexion();
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, idSolicitud);
			psmt.setString(2, fecha);
			rs = psmt.executeQuery();
			
			while(rs.next()){
				diaFeriado = rs.getInt(1);
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
		return diaFeriado;
	}
	
	public boolean existeEmpleadoSolicitud(String numEmpleado, String fechaPaga){
		boolean existe = false;
		String sql = "SELECT COUNT(*) FROM bdhe.dbo.solicitudes WHERE num_empleado = ? AND fecha_paga = CONVERT(DATETIME,?, 103)";
		int count = 0;
		try {
			connectionDB = new ConnectionDB();
			con = connectionDB.getConexion();
			psmt = con.prepareStatement(sql);
			psmt.setString(1, numEmpleado);
			psmt.setString(2, fechaPaga);
			rs = psmt.executeQuery();
			
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
	
	public boolean guardarSolicitud(SolicitudesBean solicitudesBean,LoginBean loginBean){
		boolean paso = false;
		String sql = "INSERT INTO bdhe.dbo.SOLICITUDES(NUM_EMPLEADO,\n"+
					 "		RFC,\n"+
					 "		A_PATERNO,\n"+
					 "		A_MATERNO,\n"+
					 "		NOMBRE,\n"+
					 "		FECHA_INICIAL,\n"+
					 "		FECHA_FINAL,\n"+
					 "		FECHA_PAGA,\n"+
					 "		ID_AREA,\n"+
					 "		ID_ESTATUS_SOLICITUD,\n"+
					 "		NUM_EMPLEADO_MODIFICA,\n"+
					 "		FECHA_MODIFICA)\n"+
					 "	VALUES(?,?,?,?,?,?,?,?,?,?,?,getdate())";
		connectionDB 			= new ConnectionDB();
		try {
			con = connectionDB.getConexion();
			psmt = con.prepareStatement(sql);
			psmt.setString(1, solicitudesBean.getNumEmpleado());
			psmt.setString(2, solicitudesBean.getRfc());
			psmt.setString(3, solicitudesBean.getaPaterno());
			psmt.setString(4, solicitudesBean.getaMaterno());
			psmt.setString(5, solicitudesBean.getNombre());
			psmt.setString(6, solicitudesBean.getFechaInicial());
			psmt.setString(7, solicitudesBean.getFechaFinal());
			psmt.setString(8, solicitudesBean.getFechaPaga());
			psmt.setString(9, solicitudesBean.getIdArea());
			psmt.setInt(10, 1);
			psmt.setString(11, loginBean.getNumEmpleado());
			psmt.executeUpdate();
			paso = true;
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
		return paso;
	}
	
	public SolicitudesBean dameSolicitud(String numEmpleado,String fecha){
		solicitudesBean = new SolicitudesBean();
		String sql =     "SELECT\n" +
						"  std_id_person NUM_EMP,\n" +
						"  p.STD_N_FAM_NAME_1 a_paterno,\n" +
						"  p.STD_N_MAIDEN_NAME a_materno,\n" +
						"  p.STD_N_FIRST_NAME nombre,\n" +
						"CONCAT(p.STD_N_FAM_NAME_1,' ',p.STD_N_FAM_NAME_1,' ',p.STD_N_MAIDEN_NAME)AS nombre_completo,\n"+
						"  p.STD_SS_NUMBER rfc,\n" +
						"  cme.CME_ID_POSITION num_plaza,\n" +
						"  sme.SAR_ID_CONVENIO nivel_salarial,\n" +
						"  cme.CME_ID_WUNIT area,\n" +
						" (SELECT jornada_ordinaria \n"+
						"	   FROM bdhe.dbo.solicitudes\n"+ 
					    "	   WHERE num_empleado = ?\n"+ 
						"	   AND CONVERT(DATETIME, fecha_paga , 103) = ?)jornada_ordinaria,\n"+
						" (SELECT id_solicitud \n"+
						"	   FROM bdhe.dbo.solicitudes\n"+ 
					    "	   WHERE num_empleado = ?\n"+ 
						"	   AND CONVERT(DATETIME, fecha_paga , 103) = ?)id_solicitud\n"+
						"FROM\n" +
						"  m4tsjdf.dbo.std_person p\n" +
						"INNER JOIN m4tsjdf.dbo.M4CME_AC_HR_PERIOD cme\n" +
						"ON\n" +
						"  cme.SCO_ID_HR    = p.std_id_person\n" +
						"AND cme.SCO_DT_PAY = CONVERT(DATETIME, ? , 103)\n" +
						"INNER JOIN m4tsjdf.dbo.M4SME_AC_HR_PERIOD sme\n" +
						"ON\n" +
						"  sme.SCO_ID_HR            = p.std_id_person\n" +
						"AND sme.SCO_DT_PAY         = CONVERT(DATETIME, ? , 103)\n" +
						"AND sme.SCO_DT_START_SLICE = cme.SCO_DT_START_SLICE\n" +
						"INNER JOIN m4tsjdf.dbo.M4SCO_HT_PAYS pays\n" +
						"ON\n" +
						"  pays.sco_dt_accrued = CONVERT(DATETIME, ? , 103)\n" +
						"WHERE 1=1\n" +
						"AND std_id_person       = ?\n";
		
		String sql2 = "SELECT CONVERT(VARCHAR(10),sco_dt_start,103) fecha_inicial,\n"+
					  "	  CONVERT(VARCHAR(10),sco_date_end,103) fecha_final,\n"+
					  "	  CONVERT(VARCHAR(10),sco_dt_accrued,103) fecha_paga,\n"+
					  "	  SCO_NM_PAYESP descripcion\n"+
					  "	FROM  m4tsjdf.dbo.M4SCO_HT_PAYS\n"+
					  "	WHERE sco_dt_accrued = CONVERT(DATETIME, ? , 103)";
		
		connectionDB 			= new ConnectionDB();
		ResultSet rs2 			= null;
		PreparedStatement psmt2 = null;
		try {
			con = connectionDB.getConexion();
			psmt = con.prepareStatement(sql);
			psmt.setString(1, numEmpleado);
			psmt.setString(2, fecha);
			psmt.setString(3, numEmpleado);
			psmt.setString(4, fecha);
			psmt.setString(5, fecha);			
			psmt.setString(6, fecha);
			psmt.setString(7, fecha);
			psmt.setString(8, numEmpleado);
			rs = psmt.executeQuery();
			while(rs.next()){
				solicitudesBean.setNumEmpleado(rs.getString(1));
				solicitudesBean.setaPaterno(rs.getString(2));
				solicitudesBean.setaMaterno(rs.getString(3));
				solicitudesBean.setNombre(rs.getString(4));
				solicitudesBean.setNombreCompleto(rs.getString(5));
				solicitudesBean.setRfc(rs.getString(6));
				solicitudesBean.setNumPlaza(rs.getString(7));
				solicitudesBean.setIdNivel(rs.getString(8));
				solicitudesBean.setIdArea(rs.getString(9));
				solicitudesBean.setJornadaOrdinaria(rs.getString(10));
				solicitudesBean.setIdSolicitud(rs.getInt(11));
				
			}
		    psmt2 = con.prepareStatement(sql2);
		    psmt2.setString(1, fecha);
		    rs2 = psmt2.executeQuery();
		    while(rs2.next()){
		    	solicitudesBean.setFechaInicial(rs2.getString(1));
		    	solicitudesBean.setFechaFinal(rs2.getString(2));
		    	solicitudesBean.setFechaPaga(rs2.getString(3));
		    	solicitudesBean.setDescripcionNomina(rs2.getString(4));
		    }
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				psmt.close();
				psmt2.close();
				rs.close();
				rs2.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return solicitudesBean;
	}
	
	public List<LovBean> getQuincenas(Integer idRol){
		listLov = new ArrayList<LovBean>();
		ConnectionDB connectionDB = new ConnectionDB();
		String sql = "SELECT  CONVERT(VARCHAR(10),sco_dt_accrued,103) fecha_pago, \n"+
					 "        SCO_NM_PAYESP nombre, \n"+
					 "        SCO_NM_PAYESP descripcion \n"+
					 "FROM    M4SCO_HT_PAYS \n"+
					 "WHERE   YEAR(SCO_DT_START) >= '2015' \n";
		if(idRol != 3){
			sql = sql + "    AND sco_dt_accrued IN (SELECT (CONVERT(DATETIME, x.fecha_paga , 103)) fecha_paga FROM bdhe.dbo.QUINCENA_ACTUAL x WHERE x.activo = 'Y')";
		}
		sql = sql + "ORDER BY CONVERT(VARCHAR(10),sco_dt_accrued,126) \n";
		//logger.info("Query de quincenas");
		//logger.info(sql);
		try {
			con = connectionDB.getConexion();
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()){
				dtLov = new LovBean(rs.getString("fecha_pago"),rs.getString("nombre"), 
						rs.getString("descripcion"));
				listLov.add(dtLov);
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
		return listLov;
	}
	public List<LovBean> getRoles(){
		listLov = new ArrayList<LovBean>();
		ConnectionDB connectionDB = new ConnectionDB();
		String sql = "SELECT  ID_ROL id, \n"+
					 "        DESCRIPCION nombre, \n"+
					 "        DESCRIPCION descripcion \n"+
					 "FROM    bdhe.dbo.ROLES_USUARIO \n";
		logger.info("");
		//logger.info(sql);
		try {
			con = connectionDB.getConexion();
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()){
				dtLov = new LovBean(rs.getString("id"),rs.getString("nombre"), 
						rs.getString("descripcion"));
				listLov.add(dtLov);
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
		return listLov;
	}
	public List<LovBean> getAreasUsuario(){
		listLov = new ArrayList<LovBean>();
		ConnectionDB connectionDB = new ConnectionDB();
		String sql = "SELECT  wu.std_id_work_unit id, \n"+
					 "        wu.std_id_work_unit nombre, \n"+
					 "        std_n_work_unitesp  descripcion \n"+
					 "FROM    std_work_unit wu \n";
		//logger.info("Query de quincenas");
		//logger.info(sql);
		try {
			con = connectionDB.getConexion();
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()){
				dtLov = new LovBean(rs.getString("id"),rs.getString("nombre"), 
						rs.getString("descripcion"));
				listLov.add(dtLov);
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
		return listLov;
	}
	public List<LovBean> getUsuarios(){
		listLov = new ArrayList<LovBean>();
		ConnectionDB connectionDB = new ConnectionDB();
		String sql = "SELECT  u.id_usuario id \n"+
			     "      , u.num_empleado nombre\n"+
				 "      , concat(p.STD_N_FAM_NAME_1,' ',p.STD_N_MAIDEN_NAME, ' ', p.STD_N_FIRST_NAME) descripcion \n"+
				 "FROM    bdhe.dbo.usuarios      u \n"+
				 "      , bdhe.dbo.roles_usuario ru \n"+
				 "      , std_person             p \n"+
				 "      , m4sco_h_hr_wunit       wu \n"+
				 "WHERE   u.id_rol       = ru.id_rol \n"+
				 "    AND u.num_empleado = p.std_id_person COLLATE DATABASE_DEFAULT \n"+
				 "    AND wu.sco_id_hr   = p.std_id_person \n"+
				 "    AND wu.sco_dt_end  = CONVERT(DATETIME,'01/01/4000', 103) \n"+
				 "ORDER BY u.id_usuario DESC \n";
		//logger.info("Query de quincenas");
		//logger.info(sql);
		try {
			con = connectionDB.getConexion();
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()){
				dtLov = new LovBean(rs.getString("id"),rs.getString("nombre"), 
						rs.getString("descripcion"));
				listLov.add(dtLov);
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
		return listLov;
	}
	public List<LovBean> getStatusSolicitud(){
		listLov = new ArrayList<LovBean>();
		ConnectionDB connectionDB = new ConnectionDB();
		String sql = "SELECT  ID_ESTATUS_SOLICITUD id \n"+
			     "      , DESCRIPCION nombre\n"+
				 "      , DESCRIPCION descripcion \n"+
				 "FROM    bdhe.dbo.ESTATUS_SOLICITUD \n"+
				 "WHERE   upper(descripcion) IN (upper('autorizada'), upper('rechazada')) \n";
		//logger.info("Query de quincenas");
		//logger.info(sql);
		try {
			con = connectionDB.getConexion();
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()){
				dtLov = new LovBean(rs.getString("id"),rs.getString("nombre"), 
						rs.getString("descripcion"));
				listLov.add(dtLov);
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
		return listLov;
	}
	
	public boolean esComisiario(String numEmpleado, String fechaPaga){
		boolean existe = false;
		String sql = "SELECT  count(*) existe \n"+
				     "FROM    M4CME_H_HR_COMISIA \n "+
				     "WHERE   DT_START <= (SELECT  SCO_DT_START FROM    M4SCO_HT_PAYS WHERE sco_dt_accrued = CONVERT(DATETIME, ? , 103)) \n "+
				     "    AND DT_END >= (SELECT  SCO_DATE_END FROM    M4SCO_HT_PAYS WHERE sco_dt_accrued = CONVERT(DATETIME, ? , 103)) \n "+
				     "    AND STD_ID_HR = ? \n ";
		int count = 0;
		try {
			connectionDB = new ConnectionDB();
			con = connectionDB.getConexion();
			psmt = con.prepareStatement(sql);
			psmt.setString(1, fechaPaga);
			psmt.setString(2, fechaPaga);
			psmt.setString(3, numEmpleado);
			rs = psmt.executeQuery();
			
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
	
	public boolean esMinisterio(String numEmpleado, String fechaPaga){
		boolean existe = false;
		String sql = "SELECT  Count(*) \n"+
				     "FROM    (SELECT STD_ID_HR FROM M4SCO_INCIDEN_PER \n "+
				     "         WHERE ID_DMD_COMPONENT IN ('CME_DIAS_ML_MAG','CME_D_ML_JUEZ_PAZ','CME_D_ML_SAJ1I','CME_D_ML_SAP','CME_D_ML_JUEZ_INST') \n "+
				     "         AND SCO_DT_ACCRUED IN (SELECT  SCO_DT_ACCRUED FROM    M4SCO_HT_PAYS WHERE sco_dt_accrued = CONVERT(DATETIME, ? , 103)) \n "+
				     "         UNION ALL \n "+
				     "         SELECT STD_ID_HR FROM M4SCO_PERIO_VALUES \n "+
				     "         WHERE ID_DMD_COMPONENT IN ('CME_DIAS_ML_MAG','CME_D_ML_JUEZ_PAZ','CME_D_ML_SAJ1I','CME_D_ML_SAP','CME_D_ML_JUEZ_INST') \n "+
				     "             AND DT_START     <= (SELECT  SCO_DT_START FROM    M4SCO_HT_PAYS WHERE sco_dt_accrued = CONVERT(DATETIME, ? , 103))  \n "+
				     "             AND DT_COR_START <= (SELECT  SCO_DT_START FROM    M4SCO_HT_PAYS WHERE sco_dt_accrued = CONVERT(DATETIME, ? , 103)) \n "+
				     "             AND DT_END       >= (SELECT  SCO_DT_START FROM    M4SCO_HT_PAYS WHERE sco_dt_accrued = CONVERT(DATETIME, ? , 103))  \n "+
				     "             AND DT_COR_END   >= (SELECT  SCO_DT_START FROM    M4SCO_HT_PAYS WHERE sco_dt_accrued = CONVERT(DATETIME, ? , 103)) \n "+
				     "        ) Ministros \n "+
				     "WHERE STD_ID_HR = ? \n ";
		int count = 0;
		try {
			connectionDB = new ConnectionDB();
			con = connectionDB.getConexion();
			psmt = con.prepareStatement(sql);
			psmt.setString(1, fechaPaga);
			psmt.setString(2, fechaPaga);
			psmt.setString(3, fechaPaga);
			psmt.setString(4, fechaPaga);
			psmt.setString(5, fechaPaga);
			psmt.setString(6, numEmpleado);
			rs = psmt.executeQuery();
			
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
	
	public boolean esPenal(String numEmpleado, String fechaPaga){
		boolean existe = false;
		String sql = "SELECT  Count(*) \n"+
				     "FROM    (SELECT *  \n "+
				     "         FROM M4SCO_PERIO_VALUES \n "+
				     "         WHERE ID_DMD_COMPONENT = 'CME_IND_PAGO_CDT' \n "+
				     "         UNION ALL  \n "+
				     "         SELECT  * \n "+
				     "         FROM    M4SCO_PERIO_VALUES \n "+
				     "         WHERE   ID_DMD_COMPONENT = 'CME_IND_PAGA_CDT') M4SCO_PERIO_VALUES \n "+
				     "WHERE   STD_ID_HR    =  ? \n "+
				     "    AND DT_START     <= (SELECT  SCO_DT_START FROM    M4SCO_HT_PAYS WHERE sco_dt_accrued = CONVERT(DATETIME, ? , 103))  \n "+
				     "    AND DT_COR_START <= (SELECT  SCO_DT_START FROM    M4SCO_HT_PAYS WHERE sco_dt_accrued = CONVERT(DATETIME, ? , 103)) \n "+
				     "    AND DT_END       >= (SELECT  SCO_DT_START FROM    M4SCO_HT_PAYS WHERE sco_dt_accrued = CONVERT(DATETIME, ? , 103))  \n "+
				     "    AND DT_COR_END   >= (SELECT  SCO_DT_START FROM    M4SCO_HT_PAYS WHERE sco_dt_accrued = CONVERT(DATETIME, ? , 103)) \n ";
		int count = 0;
		try {
			connectionDB = new ConnectionDB();
			con = connectionDB.getConexion();
			psmt = con.prepareStatement(sql);
			psmt.setString(1, numEmpleado);
			psmt.setString(2, fechaPaga);
			psmt.setString(3, fechaPaga);
			psmt.setString(4, fechaPaga);
			psmt.setString(5, fechaPaga);
			rs = psmt.executeQuery();
			
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
	
	public String validaComisarios(String numEmpleado, String fechaPaga){
		String existe = "OK";
		boolean lesComisario = esComisiario(numEmpleado, fechaPaga);
		if(lesComisario){
			existe = "El empleado ingresado actualmente es comisario, por lo cual no se le puede generar la solicitud.\n";
		}
		boolean lesPenal = esPenal(numEmpleado, fechaPaga); 
		if(lesPenal){
			existe = existe + "El empleado ingresado actualmente se encuentra en penal, por lo cual no se le puede generar la solicitud.\n";
		}
		boolean lesMinistro = esMinisterio(numEmpleado, fechaPaga);
		if(lesMinistro){
			existe = existe + "El empleado ingresado actualmente es ministro de ley, por lo cual no se le puede generar la solicitud.\n";
		}
		return existe;
	}
	
}

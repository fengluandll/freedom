package mx.gob.tsjdf.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.faces.context.FacesContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import mx.gob.tsjdf.bean.LoginBean;
import mx.gob.tsjdf.db.ConnectionDB;
import mx.gob.tsjdf.util.Funciones;
import mx.gob.tsjdf.utils.security.cryptography.CryptMD5;

public class LoginDAO {
	
	Connection        con ;
	PreparedStatement psmt;
	ResultSet 		  rs;
	private static org.apache.log4j.Logger logger = Logger.getLogger(LoginDAO.class);
	
	
	public boolean obtenUsuario(String userName,String pass) {
		ConnectionDB connectionDB = new ConnectionDB();
		boolean pasa = false;
		try {
			con = connectionDB.getConexion();
			String sql = "SELECT  count(*) \n"+
						 "FROM    bdhe.dbo.USUARIOS \n"+
						 "WHERE   NUM_EMPLEADO = ? \n"+
						 "	  AND PASSWORD = ? ";
			psmt = con.prepareStatement(sql);
			psmt.setString(1,userName);
			psmt.setString(2, CryptMD5.getMD5(pass,"MD5") );
			rs = psmt.executeQuery();
			int count = 0;
			while(rs.next()){
				count = rs.getInt(1);
			}
			if(count > 0){
				pasa = true;
				
			}
		} catch (NamingException | SQLException e) {
			logger.error(e);
			Funciones.mostrarMensaje(e.getMessage(),"Error" , "ERROR");
			Funciones.updateComponent("frmLogin:pgLogin");
			
		}finally {
			try {
				psmt.close();
				rs.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				Funciones.mostrarMensaje(e.getMessage(),"Error" , "ERROR");
			}
			
		}
		return pasa;
		
	}
	
	public LoginBean datosEmpleado(String numEmpleado){
		
		LoginBean loginBean = null;
		String sql = "SELECT\n" +
					"    u.id_usuario,\n" +
					"    u.num_empleado,\n" +
					"    p.STD_N_FAM_NAME_1 a_paterno,\n" +
					"    p.STD_N_MAIDEN_NAME a_materno,\n" +
					"    p.STD_N_FIRST_NAME nombre,\n" +
					"    CONCAT(p.STD_N_FAM_NAME_1,' ',p.STD_N_FAM_NAME_1,' ',p.STD_N_MAIDEN_NAME)AS nombre_completo,\n" +
					"    wu.sco_id_work_unit area,\n" +
					"    ru.descripcion rol,\n" +
					"    ru.id_rol id_rol\n"+
					"FROM\n" +
					"    bdhe.dbo.usuarios u,\n" +
					"    bdhe.dbo.roles_usuario ru,\n" +
					"    std_person p,\n" +
					"    m4sco_h_hr_wunit wu\n" +
					"WHERE 1=1\n" +
					"    AND u.id_rol       = ru.id_rol\n" +
					"    AND u.num_empleado = p.std_id_person COLLATE DATABASE_DEFAULT\n" +
					"    AND wu.sco_id_hr   = p.std_id_person\n" +
					"    AND wu.sco_dt_end  = CONVERT(DATETIME,'01/01/4000', 103)\n" +
					"    AND u.num_empleado = ?";
		
		ConnectionDB connectionDB = new ConnectionDB();
		try {
			con = connectionDB.getConexion();
			psmt = con.prepareStatement(sql);
			psmt.setString(1, numEmpleado);
			rs = psmt.executeQuery();
			
			while(rs.next()){
				loginBean = new LoginBean();
				loginBean.setIdUsuario(rs.getInt(1));
				loginBean.setNumEmpleado(rs.getString(2));
				loginBean.setApellidoPaterno(rs.getString(3));
				loginBean.setApellidoMaterno(rs.getString(4));
				loginBean.setNombre(rs.getString(5));
				loginBean.setNombreCompleto(rs.getString(6));
				loginBean.setArea(rs.getString(7));
				loginBean.setNomRol(rs.getString(8));
				loginBean.setIdRol(rs.getInt(9));
				
				
			}
		} catch (NamingException | SQLException e) {
			logger.error(e);
		}finally {
			try {
				psmt.close();
				rs.close();
				con.close();
			} catch (SQLException e) {
				logger.error(e);
			}
			
		}
		
		return loginBean;
	}

}
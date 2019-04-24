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

import mx.gob.tsjdf.bean.UsuariosBean;
import mx.gob.tsjdf.db.ConnectionDB;

@ManagedBean(name = "usersDAO")
@ApplicationScoped
public class UsersDAO {

	private static org.apache.log4j.Logger logger = Logger.getLogger(UsersDAO.class);
	
	Connection        con ;
	PreparedStatement psmt;
	ResultSet 		  rs;

	private UsuariosBean dtData;
	List<UsuariosBean> listData;
	ConnectionDB connectionDB;
	
	public boolean borraUsuario(int idUsuario){
		String sql = "DELETE FROM bdhe.dbo.usuarios WHERE id_usuario = ?";
		connectionDB = new ConnectionDB();
		boolean paso = false;
		try {
			con = connectionDB.getConexion();
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, idUsuario);
			psmt.executeUpdate();
			paso = true;
		} catch (SQLException | NamingException e) {
			e.printStackTrace();
		}finally {
			try {
				psmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return paso;
	}
	
	public boolean actualizaUsuario(int idRol,String estatus,int quienActualiza,int idUsuario){
		boolean paso = false;
		String sql = "UPDATE bdhe.dbo.usuarios\n"+
					 "	SET id_rol 	 	   = ?,\n"+
					 "	    estatus 	   = ?,\n"+
					 "	    last_update_by = ?,\n"+
					 "	    last_update_date = getdate()\n"+
					 "	WHERE ID_USUARIO = ?";
		connectionDB = new ConnectionDB();
		try {
			con = connectionDB.getConexion();
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, idRol);
			psmt.setString(2, estatus);
			psmt.setInt(3, quienActualiza);
			psmt.setInt(4, idUsuario);
			psmt.executeUpdate();
			paso = true;
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
		return paso;
	}
	
	public List<UsuariosBean> getUsuarios() {
        listData = new ArrayList<UsuariosBean>();
        connectionDB = new ConnectionDB();
		String sql = "SELECT  u.id_usuario \n"+
				     "      , u.id_rol \n"+
				     "      , u.num_empleado \n"+
				     "      , CASE u.ESTATUS WHEN 'A' THEN 'Activo' WHEN 'I' THEN 'Inactivo' ELSE 'Ninguno' END status \n"+
					 "      , p.STD_N_FAM_NAME_1 a_paterno \n"+
					 "      , p.STD_N_MAIDEN_NAME a_materno \n"+
					 "      , p.STD_N_FIRST_NAME nombre \n"+
					 "      , wu.sco_id_work_unit area \n"+
					 "      , ru.descripcion rol_name \n"+
					 "      , concat(p.STD_N_FAM_NAME_1,' ',p.STD_N_MAIDEN_NAME, ' ', p.STD_N_FIRST_NAME) nombre_completo \n"+
					 "      ,u.PASSWORD\n"+
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
				dtData = new UsuariosBean(rs.getInt("id_usuario"), rs.getInt("id_rol"), rs.getString("num_empleado"), 
						rs.getString("status"), rs.getString("nombre_completo"), rs.getString("rol_name"),rs.getString("PASSWORD"));
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
	
	public String dameNomUsuario(String numEmpleado){
		String sql = "SELECT CONCAT(p.STD_N_FAM_NAME_1,' ',p.STD_N_FAM_NAME_1,' ',p.STD_N_MAIDEN_NAME)AS nombre_completo\n"+
					 " FROM\n"+
					 " m4tsjdf.dbo.std_person p,\n"+
					 " m4tsjdf.dbo.m4sco_h_hr_wunit pwu\n"+
					 " WHERE\n"+
					 " pwu.sco_id_hr    = p.std_id_person\n"+
					 " AND pwu.sco_dt_end = CONVERT(DATETIME,'01/01/4000', 103)\n"+
					 " AND std_id_person  = ?";
		connectionDB = new ConnectionDB();
		String nmCompleto = null;
		try {
			con = connectionDB.getConexion();
			psmt = con.prepareStatement(sql);
			psmt.setString(1, numEmpleado);
			rs = psmt.executeQuery();
			
			while(rs.next()){
				nmCompleto = rs.getString(1);
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
	
	public boolean nuevoUsusario(String numEmpleado,int idRol,String pass,String status,int idUsuario){
		boolean bandera = false;
		String sql = "INSERT INTO bdhe.dbo.usuarios (num_empleado,\n"+
					 "                              id_rol,\n"+
					 "                              password,\n"+
					 "                              estatus,"
					 + "                            created_by,"
					 + "						    created_date)\n"+
					 " VALUES(?,?,?,?,?,getdate() )";
		connectionDB = new ConnectionDB();
		try {
			con = connectionDB.getConexion();
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, numEmpleado);
			psmt.setInt(2, idRol);
			psmt.setString(3, pass);
			psmt.setString(4, status);
			psmt.setInt(5, idUsuario);
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
	
	public boolean existeUsusario(String numEmpleado){
		boolean existe = false;
		String sql = "SELECT COUNT(*) FROM bdhe.dbo.usuarios WHERE num_empleado = ?";
		int count = 0;
		try {
			connectionDB = new ConnectionDB();
			con = connectionDB.getConexion();
			psmt = con.prepareStatement(sql);
			psmt.setString(1, numEmpleado);
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
	public String obtieneUsusarioId(String numEmpleado){
		String sql = "SELECT id_usuario FROM bdhe.dbo.usuarios WHERE num_empleado = ?";
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
}

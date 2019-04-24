package mx.kaz.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import mx.kaz.beans.UserBean;
import mx.kaz.model.ConectionWrapper;

public class UsersDAO {
	
	private org.apache.log4j.Logger logger = Logger.getLogger(UsersDAO.class);
	private ConectionWrapper conectionWrapper;
	private Connection con;
	private FacesContext facesContext;
	PreparedStatement psmt;
	ResultSet rs;
	UserBean userBean;
	List<UserBean> listUserDao;
	
	public UsersDAO() {
		userBean = new UserBean();
		conectionWrapper = new ConectionWrapper();
		listUserDao = new ArrayList<>();
	}
	
	public List<UserBean> getUser()  {
		String sql = "select personal_id, given_names, surnames, from_login_id, segment6, username, password, id_status, rol_id, rol_name,nom_status\r\n" + 
				     "from startonl_sct_app.usuarios_view order by username asc";
		try {
			con = conectionWrapper.getConexion();
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				userBean = new UserBean();
				userBean.setPersonalId(rs.getInt(1));
				userBean.setGivenNames(rs.getString(2));
				userBean.setSurnames(rs.getString(3));
				userBean.setLoginId(rs.getInt(4));
				userBean.setSegment6(rs.getString(5));
				userBean.setUserName(rs.getString(6));
				userBean.setPassword(rs.getString(7));
				userBean.setIdStatus(rs.getInt(8));
				userBean.setRolId(rs.getInt(9));
				userBean.setRolName(rs.getString(10));
				userBean.setStatus(rs.getString(11));
				listUserDao.add(userBean);
			}
		} catch (NamingException | SQLException e) {
			logger.error(e);
		}finally {
			try {
				rs.close();
				psmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				logger.error(e);
			}
			
		}
		return listUserDao;
	}
	
	public boolean updateUser(UserBean userBean,int lastUpdateBy) {
		boolean actualizo = false;
		
		String sqlUpdate = "UPDATE login SET username = ?,\r\n" + 
							"                 id_status = ?,\r\n" + 
							"                 last_update_date = sysdate(),\r\n" + 
							"                 last_update_by   = ?,\r\n";
		if(userBean.getPassword() == null || userBean.getPassword().equals("")) {
			sqlUpdate += "				 password = password\r\n";
		}else {
			sqlUpdate += "				 password = MD5(?)\r\n";
		}
							
			sqlUpdate +=				" where login_id = ?";
		String sqlUpdate2 = "update personal SET given_names = ?,\r\n" + 
							"					rol_id      = ?,\r\n" + 
							"                    last_update_date = sysdate(),\r\n" + 
							"                    last_update_by   = ?\r\n" + 
							"WHERE  personal_id =  ?          ";
		try {
			con = conectionWrapper.getConexion();
			//con.setAutoCommit(false);
			psmt = con.prepareStatement(sqlUpdate);
			psmt.setString(1, userBean.getUserName());
			psmt.setInt(2, userBean.getIdStatus());
			psmt.setInt(3, lastUpdateBy);
			if(!userBean.getPassword().equals("")) {
				psmt.setString(4, userBean.getPassword());
				psmt.setInt(5, userBean.getLoginId());
			}else {
				psmt.setInt(4, userBean.getLoginId());
			}
			
			psmt.executeUpdate();
			psmt = con.prepareStatement(sqlUpdate2);
			psmt.setString(1, userBean.getGivenNames());
			psmt.setInt(2, userBean.getRolId());
			psmt.setInt(3, lastUpdateBy);
			psmt.setInt(4, userBean.getPersonalId());
			psmt.executeUpdate();
			//con.setAutoCommit(true);
			actualizo = true;
			
		} catch (NamingException | SQLException e) {
			logger.error(e);
		}finally {
			try {
				psmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				logger.error(e);
			}
		}
		
		
		return actualizo;
	}
	
	public Map<String,Integer> dameRoles() {
		
		String sql = "select rol_id,rol_name from roles order by rol_name";
		Map<String,Integer> mapRoles = new HashMap<String,Integer>();
		try {
			con = conectionWrapper.getConexion();
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			int idRol  		= 0;
			String nomrol 	= "";
			while(rs.next()) {
				idRol =  rs.getInt(1);
				nomrol = rs.getString(2);
				mapRoles.put(nomrol, idRol);
			}
		} catch (NamingException | SQLException e) {
			logger.error(e);
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				psmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				logger.error(e);
			}
		}
		return mapRoles;
	}
	

	public String creaUsuarioDAO(UserBean userBean) {
		String paso = "FALSE";
		try {
			con = conectionWrapper.getConexion();
			CallableStatement cs = con.prepareCall("{Call new_user_pr(?,?,?,?,?)}");
			cs.setString(1, userBean.getUserName());
			cs.setString(2, userBean.getPassword());
			cs.setString(3, userBean.getGivenNames());
			cs.setInt(4, userBean.getRolId());
			cs.registerOutParameter(5, java.sql.Types.VARCHAR);
			cs.executeUpdate();
			paso = cs.getString(5);
			
		} catch (NamingException | SQLException e) {
			logger.error(e);
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				psmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				logger.error(e);
			}
		}
		return paso;
	}
	
	public String deleteUser(UserBean userBean) {
		
		String paso = "FALSE";
		try {
			con = conectionWrapper.getConexion();
			CallableStatement cs = con.prepareCall("{Call deleteUser_pr(?,?)}");
			cs.setInt(1, userBean.getLoginId());
			cs.registerOutParameter(2, java.sql.Types.VARCHAR);
			cs.executeUpdate();
			paso = cs.getString(2);
			
		} catch (NamingException | SQLException e) {
			logger.error(e);
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				psmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				logger.error(e);
			}
		}
		return paso;
	}

}

/**
 * @Jose de Jesus Argumedo Quiroz
 */
package mx.javaonline.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import mx.javaonline.beans.PreRegistroBean;
import mx.javaonline.model.ConectionWrapper;

import org.apache.log4j.Logger;

public class PreRegistroDAO {

	private org.apache.log4j.Logger logger = Logger.getLogger(PreRegistroDAO.class);
	ConectionWrapper conectionWrapper;
	Connection con;
	PreRegistroBean preRegistroBean;
	List<PreRegistroBean> listPreResgistro;
/*	
	public PreRegistroDAO() {
		conectionWrapper = new ConectionWrapper();
	}
	
	public List<PreRegistroBean> getPreResgistros(){
		String sql = "SELECT id_pre_registro,nombres,apellidos,fecha_nacimiento,correo_principal,status,creation_date FROM pre_db.pre_registro WHERE status = 'P'";
		listPreResgistro = new ArrayList<>();
		try {
			con = conectionWrapper.getConexionPre();
			PreparedStatement psmt = con.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()){
				preRegistroBean = new PreRegistroBean();
				preRegistroBean.setIdPreegistro(rs.getInt(1));
				preRegistroBean.setNombres(rs.getString(2));
				preRegistroBean.setApellidos(rs.getString(3));
				preRegistroBean.setFechaNacimiento(rs.getDate(4));
				preRegistroBean.setCorreoPrincipal(rs.getString(5));
				preRegistroBean.setStatus(rs.getString(6));
				preRegistroBean.setCreationDate(rs.getDate(7));
				listPreResgistro.add(preRegistroBean);
			}
		} catch (NamingException | SQLException e) {
			logger.error(e);
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				logger.error(e);
			}
	    }
		return listPreResgistro;
	}
	
	public boolean updateStatus(int idPreRegistro){
		boolean bandera = false;
		String sql = "UPDATE pre_db.pre_registro SET status = 'T' WHERE id_pre_registro = ?";
		try {
			con = conectionWrapper.getConexionPre();
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setInt(1, idPreRegistro);
			psmt.executeUpdate();
			bandera = true;
		} catch (NamingException | SQLException e) {
			logger.error(e);
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				logger.error(e);
			}
	    }
		return bandera;
	}
*/
}
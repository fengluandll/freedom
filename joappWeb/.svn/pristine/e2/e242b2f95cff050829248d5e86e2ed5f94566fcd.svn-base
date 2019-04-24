/**
 * @Jose de Jesus Argumedo Quiroz
 */
package mx.javaonline.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import mx.javaonline.beans.StudentPersonalBean;
import mx.javaonline.model.ConectionWrapper;

import org.apache.log4j.Logger;

public class ThemeSwitcherDAO {
	
	private org.apache.log4j.Logger logger = Logger.getLogger(ThemeSwitcherDAO.class);
	ConectionWrapper conectionWrapper;
	Connection con;
	FacesContext facesContext;
	HttpSession session;
	
	public ThemeSwitcherDAO(){
		conectionWrapper = new ConectionWrapper();
		facesContext = FacesContext.getCurrentInstance();
		session = (HttpSession) facesContext.getExternalContext().getSession(true);
	}
	
	public void guardaTema(String tema){
		try {
			StudentPersonalBean studentPersonalBean = (StudentPersonalBean)session.getAttribute("studentPersonalBean");
			String delete = "DELETE FROM theme_user WHERE st_per_id = ?";
			String sql = "INSERT INTO theme_user(st_per_id,nom_tema) VALUES(?,?)";
			con = conectionWrapper.getConexion();
			
			PreparedStatement psmt2 = con.prepareStatement(delete);
			psmt2.setInt(1, studentPersonalBean.getStudentPersonalId());
			psmt2.executeUpdate();
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setInt(1, studentPersonalBean.getStudentPersonalId());
			psmt.setString(2, tema);
			psmt.executeUpdate();
		}catch (NamingException | SQLException e) {
			logger.error(e);
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				logger.error(e);
			}
	    }
	}
	
	public String dameMiTema(){
		String nomTema = "";
		try {
			StudentPersonalBean studentPersonalBean = (StudentPersonalBean)session.getAttribute("studentPersonalBean");
			String sql = "SELECT nom_tema FROM theme_user WHERE st_per_id = ?";
			con = conectionWrapper.getConexion();
			PreparedStatement psmt = con.prepareStatement(sql);
			
			psmt.setInt(1, 0);
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()){
				nomTema = rs.getString(1);
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
		return nomTema;
	}

}

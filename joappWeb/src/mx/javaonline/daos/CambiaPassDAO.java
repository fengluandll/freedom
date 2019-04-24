package mx.javaonline.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import mx.javaonline.beans.StudentPersonalBean;
import mx.javaonline.model.ConectionWrapper;

import org.apache.log4j.Logger;

public class CambiaPassDAO {
	
	private org.apache.log4j.Logger logger = Logger.getLogger(CambiaPassDAO.class);
	ConectionWrapper conectionWrapper;
	Connection con;
	FacesContext facesContext;
	HttpSession session;

	public CambiaPassDAO() {
		facesContext = FacesContext.getCurrentInstance();
		session = (HttpSession) facesContext.getExternalContext().getSession(true);
	}
	
	public boolean cambiaPass(String pass){
		boolean bandera = false;
		StudentPersonalBean studentPersonalBean = (StudentPersonalBean)session.getAttribute("studentPersonalBean");
		conectionWrapper = new ConectionWrapper();
		String sql = "UPDATE login set password = MD5(?) WHERE login_id = ?";
		try {
			con = conectionWrapper.getConexion();
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1,pass);
			psmt.setInt(2, studentPersonalBean.getFromLoginId());
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
	
	
}

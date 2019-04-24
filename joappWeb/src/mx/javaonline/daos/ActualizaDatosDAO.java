package mx.javaonline.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import mx.javaonline.beans.StudentPersonalBean;
import mx.javaonline.model.ConectionWrapper;

import org.apache.log4j.Logger;

public class ActualizaDatosDAO {
	
	private org.apache.log4j.Logger logger = Logger.getLogger(ActualizaDatosDAO.class);
	ConectionWrapper conectionWrapper;
	Connection con;
	FacesContext facesContext;
	HttpSession session;
	

	public ActualizaDatosDAO() {
		conectionWrapper = new ConectionWrapper();
		facesContext = FacesContext.getCurrentInstance();
		session = (HttpSession) facesContext.getExternalContext().getSession(true);
	}
	
	public boolean acualizaDatos(String nombres,String apellidos,String mail,String city){
		boolean bandera = false;
		StudentPersonalBean studentPersonalBean = (StudentPersonalBean)session.getAttribute("studentPersonalBean");
		try {
			con = conectionWrapper.getConexion();
			CallableStatement cs = con.prepareCall("{Call actualizaDatos_pr(?,?,?,?,?,?)}");
			cs.setInt(1, studentPersonalBean.getStudentPersonalId());
			cs.setString(2, studentPersonalBean.getSegment1());
			cs.setString(3,nombres);
			cs.setString(4, apellidos);
			cs.setString(5, mail);
			cs.setString(6, city);
			//cs.setString(6, fecNacimiento);
			cs.execute();
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

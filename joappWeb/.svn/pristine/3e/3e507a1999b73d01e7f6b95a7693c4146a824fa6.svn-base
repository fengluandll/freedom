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

import mx.javaonline.model.ConectionWrapper;

import org.apache.log4j.Logger;

public class PracticasDAO {

	private org.apache.log4j.Logger logger = Logger.getLogger(PracticasDAO.class);
	ConectionWrapper conectionWrapper;
	Connection con;
	
	public PracticasDAO() {
		conectionWrapper = new ConectionWrapper();
	}
	
	public String existeVideoPractica(int topicId){
		String video = null;
		String sql = "SELECT practica_video FROM content_topics WHERE topic_id = ? AND practica_video IS NOT NULL";
		try {
			con = conectionWrapper.getConexion();
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setInt(1, topicId);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()){
				video = rs.getString(1);
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
		return video;
	}

}

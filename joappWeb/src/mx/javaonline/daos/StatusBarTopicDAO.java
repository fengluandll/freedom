package mx.javaonline.daos;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import mx.javaonline.beans.StudentPersonalBean;
import mx.javaonline.model.ConectionWrapper;

import org.apache.log4j.Logger;

public class StatusBarTopicDAO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private org.apache.log4j.Logger logger = Logger.getLogger(StatusBarTopicDAO.class);
	ConectionWrapper conectionWrapper;
	Connection con;

	public StatusBarTopicDAO() {
	}
	public boolean setStatusBar(String accion,
								float porcentaje,
								String topicId,
								StudentPersonalBean studentPersonalBean,
								int videoId,
								String tipCont){
		boolean bandera = false;
		
    	conectionWrapper = new ConectionWrapper();
    	
		try {
			
			con = conectionWrapper.getConexion();
			CallableStatement cstmtCall = con.prepareCall("{CALL set_value_bar_topic(?,?,?,?,?,?,?)}");
			cstmtCall.setInt(1,studentPersonalBean.getStudentPersonalId());
			cstmtCall.setInt(2,Integer.parseInt(topicId));
			cstmtCall.setString(3,studentPersonalBean.getSegment1());
			cstmtCall.setString(4,accion);
			cstmtCall.setFloat(5,porcentaje);
			cstmtCall.setInt(6,videoId);
			cstmtCall.setString(7,tipCont);
			cstmtCall.execute();
			bandera = true;
			
		} catch (SQLException | NamingException e) {
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

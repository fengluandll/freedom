/**
 * Copyright (c) Argumedo
 */
package mx.kaz.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import mx.kaz.model.ConectionWrapper;

import org.apache.log4j.Logger;

/**
 * @author Argumedo
 *
 */


public class MailDAO {
	
	private org.apache.log4j.Logger logger = Logger.getLogger(ProyectDAO.class);
	ConectionWrapper conectionWrapper;
	Connection con;
	
	public MailDAO(){
		conectionWrapper = new ConectionWrapper();
	}
	
	public String traeCorreos(){
		String sql = "SELECT nom_mail FROM config_mail";
		String correos = "";
		try {
			con = conectionWrapper.getConexion();
			PreparedStatement psmt = con.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()){
				correos = rs.getString(1);
			}
		} catch (NamingException | SQLException e) {
			logger.error(e);
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return correos;
	}
	

}

/**
 * @Jose de Jesus Argumedo Quiroz
 */
package mx.javaonline.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import mx.javaonline.model.ConectionWrapper;

import org.apache.log4j.Logger;

public class BuzonSugerenciaDAO {

	private org.apache.log4j.Logger logger = Logger.getLogger(BuzonSugerenciaDAO.class);
	ConectionWrapper conectionWrapper;
	Connection con;
	
	
	public BuzonSugerenciaDAO() {
		conectionWrapper = new ConectionWrapper();
	}
	
	public boolean enviaComentarios(int stuPerId,String tipoPer,String coment){
		boolean bandera = false;
		try {
			String sql =   "INSERT INTO buzon_sugerencias\n" +
							"(id_st_per,\n" +
							"tipo_persona,\n" +
							"cometarios,\n" +
							"leido,\n" +
							"segment1,\n" +
							"segment2,\n" +
							"segment3,\n" +
							"segment4,\n" +
							"segment5,\n" +
							"segment6,\n" +
							"segment7,\n" +
							"segment8,\n" +
							"creation_date)\n" +
							"VALUES(\n" +
							"?,\n" +
							"?,\n" +
							"?,\n" +
							"'N',\n" +
							"null,\n" +
							"null,\n" +
							"null,\n" +
							"null,\n" +
							"null,\n" +
							"null,\n" +
							"null,\n" +
							"null,\n" +
							"sysdate())";
			con = conectionWrapper.getConexion();
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setInt(1, stuPerId);
			psmt.setString(2, tipoPer);
			psmt.setString(3, coment);
			psmt.executeUpdate();
			bandera = true;
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
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

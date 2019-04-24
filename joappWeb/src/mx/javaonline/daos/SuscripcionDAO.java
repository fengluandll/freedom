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

import mx.javaonline.admin.beans.SuscripcionPSBean;
import mx.javaonline.beans.StudentPersonalBean;
import mx.javaonline.model.ConectionWrapper;

import org.apache.log4j.Logger;

public class SuscripcionDAO {

	private org.apache.log4j.Logger logger = Logger.getLogger(SuscripcionDAO.class);
	ConectionWrapper conectionWrapper;
	Connection con;
	FacesContext facesContext;
	HttpSession session;
	SuscripcionPSBean suscripcionPSBean;

	public SuscripcionDAO() {
		conectionWrapper = new ConectionWrapper();
		facesContext = FacesContext.getCurrentInstance();
		session = (HttpSession) facesContext.getExternalContext().getSession(true);
	}
	
	public SuscripcionPSBean fechaSuscripcion(){
		StudentPersonalBean studentPersonalBean = (StudentPersonalBean)session.getAttribute("studentPersonalBean");
		String sql = "SELECT DATE_FORMAT(s.creation_date,'%d-%m-%Y')AS fec_susc, tp.tipo_pago\n" +
						"FROM students s,\n" +
						"      tipo_pagos tp\n" +
						"WHERE s.forma_pago = tp.id_tipo_pagos\n" +
						"AND student_id = ?";
		String sql2 =  "SELECT DATE_FORMAT(p.creation_date,'%d-%m-%Y')AS fec_susc,tp.tipo_pago\n" +
						"FROM personals p,\n" +
						"      tipo_pagos tp\n" +
						"WHERE p.forma_pago = tp.id_tipo_pagos\n" +
						"AND personal_id = ?";
			try {
				con = conectionWrapper.getConexion();
				PreparedStatement psmt = null;
				if(studentPersonalBean.getSegment1().equals("S")){
					psmt = con.prepareStatement(sql);
					
				}else if(studentPersonalBean.getSegment1().equals("P") || studentPersonalBean.getSegment1().equals("A")){
					psmt = con.prepareStatement(sql2);
				}
				psmt.setInt(1, studentPersonalBean.getStudentPersonalId());
				ResultSet rs = psmt.executeQuery();
				while(rs.next()){
					suscripcionPSBean = new SuscripcionPSBean();
					suscripcionPSBean.setFecInscripcion(rs.getString(1));
					suscripcionPSBean.setFormaPAgo(rs.getString(2));
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
			return suscripcionPSBean;
	}

}

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

import org.apache.log4j.Logger;

import mx.javaonline.dasboard.beans.CursoUnidadBean;
import mx.javaonline.model.ConectionWrapper;

public class UnidadesDAO {
	
	private org.apache.log4j.Logger logger = Logger.getLogger(UnidadesDAO.class);
	ConectionWrapper conectionWrapper;
	Connection con;
	List<CursoUnidadBean> listCurUni;
	CursoUnidadBean cursoUnidadBean;
	
	public UnidadesDAO() {
		conectionWrapper = new ConectionWrapper();
	}
	
	public List<CursoUnidadBean> unidadesFromCurso(int courseId) {
		listCurUni = new ArrayList<>();
		try {
			con = conectionWrapper.getConexion();
		   String sql = "SELECT c.course_id, \n"+
						   "c.course_name, \n"+
						   "c.link ligaCurso, \n"+
						   "u.unit_id, \n"+
						   "u.unit_name,\n"+
						   "u.segment1 objetivo_unidad, \n"+
						   "u.segment2, \n"+
						   "u.segment3, \n"+
						   "u.link ligaUnidad, \n"+
						   "u.id_order \n"+
					"FROM  courses c, \n"+
					"     units   u \n"+
					"WHERE u.course_id = c.course_id \n"+
					"AND   c.course_id = ? \n";
		PreparedStatement psmt =  con.prepareStatement(sql);
		psmt.setInt(1,courseId);
		ResultSet rs = psmt.executeQuery();
		
		while(rs.next()){
			cursoUnidadBean = new CursoUnidadBean();
//			cursoUnidadBean.setStudentPersonalId(rs.getInt(1));
			cursoUnidadBean.setCourseId(rs.getInt(1));
			cursoUnidadBean.setCourseName(rs.getString(2));
			cursoUnidadBean.setLigaCurso(rs.getString(3));
			cursoUnidadBean.setUnitId(rs.getInt(4));
			cursoUnidadBean.setUnitName(rs.getString(5));
			cursoUnidadBean.setSegment1(rs.getString(6));
			cursoUnidadBean.setSegment2(rs.getString(7));
			cursoUnidadBean.setSegment3(rs.getString(8));
			cursoUnidadBean.setLigaUnidad(rs.getString(9));
			cursoUnidadBean.setIdOrder(rs.getInt(10));
			listCurUni.add(cursoUnidadBean);
			
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
		return listCurUni;
	}
	

}

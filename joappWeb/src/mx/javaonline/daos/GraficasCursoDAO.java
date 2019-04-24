/**
 * @Jose de Jesus Argumedo Quiroz
 */
package mx.javaonline.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import mx.javaonline.admin.beans.CursoPorcenBean;
import mx.javaonline.beans.StudentPersonalBean;
import mx.javaonline.carousel.beans.TopicsCarouselBean;
import mx.javaonline.model.ConectionWrapper;

import org.apache.log4j.Logger;

public class GraficasCursoDAO {

	private org.apache.log4j.Logger logger = Logger.getLogger(GraficasCursoDAO.class);
	ConectionWrapper conectionWrapper;
	Connection con;
	FacesContext facesContext;
	HttpSession session;
	CursoPorcenBean cursoPorcenBean;
	TopicsCarouselBean topicsCarouselBean;
	Map<String,CursoPorcenBean> mapPorcTotal;

	public GraficasCursoDAO() {
		conectionWrapper = new ConectionWrapper();
		facesContext = FacesContext.getCurrentInstance();
		session = (HttpSession) facesContext.getExternalContext().getSession(true);
		topicsCarouselBean = new TopicsCarouselBean();
		mapPorcTotal = new HashMap<>();
		
	}
	
/*	
	public Map<String,CursoPorcenBean> porcTotalCurso(){
		StudentPersonalBean studentPersonalBean = (StudentPersonalBean)session.getAttribute("studentPersonalBean");
		String sql =  "SELECT u.unit_id, c.course_name,(SELECT COUNT(*) FROM units WHERE course_id = c.course_id)\n" +
						" FROM units u,\n" +
						"     courses c\n" +
						" WHERE c.course_id = u.course_id\n" +
						" AND u.course_id IN (SELECT c.course_id\n" +
						"						FROM student_courses sc,\n" +
						"							 courses c\n" +
						"						WHERE c.course_id = sc.course_id\n" +
						"						AND sc.student_id = ? \n" +
						"						AND c.course_id != 3)";
		String sql2 =  " SELECT u.unit_id, c.course_name,(SELECT COUNT(*) FROM units WHERE course_id = c.course_id)\n" +
						" FROM units u,\n" +
						"     courses c\n" +
						" WHERE c.course_id = u.course_id\n" +
						" AND u.course_id IN (SELECT c.course_id\n" +
						"						FROM personal_courses pc,\n" +
						"							 courses c\n" +
						"						WHERE c.course_id = pc.course_id\n" +
						"						AND pc.personal_id = ? \n" +
						"						AND c.course_id != 3)";
		try {
			con = conectionWrapper.getConexion();
			PreparedStatement psmt = null;
			if(studentPersonalBean.getSegment1().equals("S")){
				psmt = con.prepareStatement(sql);
				
			}else if(studentPersonalBean.getSegment1().equals("P") || studentPersonalBean.getSegment1().equals("A")){
				psmt = con.prepareStatement(sql2);
			}
		
			psmt.setInt(1,studentPersonalBean.getStudentPersonalId());
			ResultSet rs = psmt.executeQuery();
			int barraPorUnidad = 0;
			int porTotalCurso  = 0;
			String courseName  = "";
			String lastCourseName = "";
			while(rs.next()){
				courseName = rs.getString(2);
				if(!courseName.equals(lastCourseName)){
					porTotalCurso  = 0;
					lastCourseName = courseName;
				}
				cursoPorcenBean = new CursoPorcenBean();
				barraPorUnidad = topicsCarouselBean.getUnitBarraGral(rs.getInt(1));
				porTotalCurso  += barraPorUnidad;
				cursoPorcenBean.setCourseName(courseName);
				cursoPorcenBean.setPorcentaje(porTotalCurso);
				cursoPorcenBean.setTotUnits((porTotalCurso * 100)/(rs.getInt(3) * 100));
				mapPorcTotal.put(cursoPorcenBean.getCourseName(), cursoPorcenBean);
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
		return mapPorcTotal;
	}
*/	
	public int porcTotalCurso(int courseId){
		StudentPersonalBean studentPersonalBean = (StudentPersonalBean)session.getAttribute("studentPersonalBean");
		int porTotalUnidad = 0;
		String sql =  "SELECT u.unit_id, c.course_name,(SELECT COUNT(*) FROM units WHERE course_id = c.course_id)\n" +
						" FROM units u,\n" +
						"     courses c\n" +
						" WHERE c.course_id = u.course_id\n" +
						" AND u.course_id = ?";
		String sql2 =  " SELECT u.unit_id, c.course_name,(SELECT COUNT(*) FROM units WHERE course_id = c.course_id)\n" +
						" FROM units u,\n" +
						"     courses c\n" +
						" WHERE c.course_id = u.course_id\n" +
						" AND u.course_id = ?";
		try {
			con = conectionWrapper.getConexion();
			PreparedStatement psmt = null;
			if(studentPersonalBean.getSegment1().equals("S")){
				psmt = con.prepareStatement(sql);
				
			}else if(studentPersonalBean.getSegment1().equals("P") || studentPersonalBean.getSegment1().equals("A")){
				psmt = con.prepareStatement(sql2);
			}
		
			psmt.setInt(1,courseId);
			ResultSet rs = psmt.executeQuery();
			int barraPorUnidad = 0;
			int porTotalCurso  = 0;
			String courseName  = "";
			while(rs.next()){
				cursoPorcenBean = new CursoPorcenBean();
				courseName = rs.getString(2);
				barraPorUnidad = topicsCarouselBean.getUnitBarraGral(rs.getInt(1));
				porTotalCurso  += barraPorUnidad;
				
				porTotalUnidad = (porTotalCurso * 100)/(rs.getInt(3) * 100);
				//porTotalUnidad = (porTotalCurso * rs.getInt(3))/(rs.getInt(3));
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
		return porTotalUnidad;
	}
}

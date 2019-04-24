package mx.javaonline.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import mx.javaonline.admin.beans.CutBean;
import mx.javaonline.admin.beans.TopicsBean;
import mx.javaonline.beans.CoursesBean;
import mx.javaonline.beans.UnitsBean;
import mx.javaonline.model.ConectionWrapper;

import org.apache.log4j.Logger;

public class AyudaDAO {
	private org.apache.log4j.Logger logger = Logger.getLogger(AyudaDAO.class);
	ConectionWrapper conectionWrapper;
	Connection con;
	private List<CoursesBean> listCourses;
	private List<UnitsBean> listUnits;
	private List<TopicsBean> listTopics;
	CoursesBean coursesBean;
	UnitsBean unitsBean;
	TopicsBean topicsBean;
	CutBean cutBean;

	public AyudaDAO() {
		
	}
	
	public List<CoursesBean> getCourses(int studentId,String personaStudent){
		listCourses = new ArrayList<>();
		String sql = "SELECT sc.student_id, \n"+
					 "	     sc.course_id,\n"+
					 "       c.course_name\n"+
					 "FROM student_courses sc,\n"+
					 "            courses c\n"+
					 "WHERE sc.course_id = c.course_id \n"+
					 "AND   sc.student_id = ?";
		String sql2 = "SELECT sc.personal_id, \n"+
				 "	     sc.course_id,\n"+
				 "       c.course_name\n"+
				 "FROM personal_courses sc,\n"+
				 "            courses c\n"+
				 "WHERE sc.course_id = c.course_id \n"+
				 "AND   sc.personal_id = ?";
		conectionWrapper = new ConectionWrapper();
		try {
			con = conectionWrapper.getConexion();
			PreparedStatement psmt = null;
			if(personaStudent.equals("S")){
				psmt = con.prepareStatement(sql);
			}else if(personaStudent.equals("P") || personaStudent.equals("A")){
				psmt = con.prepareStatement(sql2);
			}
			
			psmt.setInt(1, studentId);
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()){
				coursesBean = new CoursesBean();
				coursesBean.setStudentId(rs.getInt(1));
				coursesBean.setCourseId(rs.getInt(2));
				coursesBean.setCourseName(rs.getString(3));
				listCourses.add(coursesBean);
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
		return listCourses;
	}
	
	public List<UnitsBean> getUnits(int courseId){
		listUnits = new ArrayList<>();
		String sql = "SELECT unit_id,unit_name FROM units WHERE course_id = ?";
		conectionWrapper = new ConectionWrapper();
		try {
			con = conectionWrapper.getConexion();
			PreparedStatement psmt = null;
			psmt = con.prepareStatement(sql);
			
			
			psmt.setInt(1, courseId);
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()){
				unitsBean = new UnitsBean();
				unitsBean.setUnitId(rs.getInt(1));
				unitsBean.setUnitName(rs.getString(2));
				listUnits.add(unitsBean);
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
		return listUnits;
	}
	
	public List<TopicsBean> getTopics(int unidadId){
		listTopics = new ArrayList<>();
		String sql = "SELECT topic_id,topic_name FROM topics WHERE unit_id = ?";
		conectionWrapper = new ConectionWrapper();
		try {
			con = conectionWrapper.getConexion();
			PreparedStatement psmt = null;
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, unidadId);
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()){
				topicsBean = new TopicsBean();
				topicsBean.setTopicId(rs.getInt(1));
				topicsBean.setTopicName(rs.getString(2).length() > 70 ? rs.getString(2).substring(0,70) + "...": rs.getString(2));
				listTopics.add(topicsBean);
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
		return listTopics;
	}
	
	public CutBean getAllCUT(int courseId,int unitId,int topicId){
		String sql = "SELECT c.course_name, \n"+
					 "	      u.unit_name,\n"+
					 "	      t.topic_name\n"+
					 "	FROM courses c,\n"+
					 "	     units u,\n"+
					 "	     topics t\n"+
					 "	WHERE c.course_id = ?\n"+
					 "	AND   u.unit_id   = ?\n"+
					 "	AND   t.topic_id  = ?";
		conectionWrapper = new ConectionWrapper();
		try {
			con = conectionWrapper.getConexion();
			PreparedStatement psmt = null;
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, courseId);
			psmt.setInt(2, unitId);
			psmt.setInt(3, topicId);
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()){
				cutBean = new CutBean();
				cutBean.setCourseName(rs.getString(1));
				cutBean .setUnitName(rs.getString(2));
				cutBean.setTopicName(rs.getString(3));
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
		return cutBean;
	}

}

package mx.javaonline.admin.beans;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import mx.javaonline.beans.CoursesBean;
import mx.javaonline.beans.StudentPersonalBean;
import mx.javaonline.daos.PreRegistroDAO;
import mx.javaonline.model.ConectionWrapper;

import org.apache.log4j.Logger;
import org.primefaces.model.DualListModel;

public class AsignaCursosUsuario {
	
	private static org.apache.log4j.Logger logger = Logger.getLogger(AsignaCursosUsuario.class);
	ConectionWrapper conectionWrapper;
	Connection con;
	CoursesBean coursesBean;
	private DualListModel<String> courses;
	List<String> coursesSource;
	List<String> coursesTarjet;
	FacesContext facesContext = FacesContext.getCurrentInstance();
	PreRegistroDAO preRegistroDAO;

	StudentPersonalBean studentPersonalBean;
	
	public AsignaCursosUsuario(){
//		preRegistroDAO = new PreRegistroDAO();
		showCursos();
	}
	
	private void showCursos(){
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		String nomRol = (String)session.getAttribute("nomRol");
	
		String sql = "";
		if(nomRol.equals("Estudiante")){
			sql = "SELECT course_id, \n" +
				     " 	     course_name  \n" +
					 "FROM courses WHERE course_id <> 3";
		}else if(nomRol.equals("Personal") ){
			sql = "SELECT course_id, \n" +
				     " 	     course_name  \n" +
					 "FROM courses WHERE course_id <> 3";
		}else if(nomRol.equals("Administrador") ){
			sql = "SELECT course_id, \n" +
				     " 	     course_name  \n" +
					 "FROM courses";
		}
		
		
		coursesSource = new ArrayList<String>();
		coursesTarjet = new ArrayList<String>();
		conectionWrapper = new ConectionWrapper();
		try {
			con = conectionWrapper.getConexion();
			PreparedStatement psmt = con.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()){
//				coursesBean = new CoursesBean();
//				coursesBean.setCourseId(rs.getInt(1));
//				coursesBean.setCourseName(rs.getString(2));
//				coursesSource.add(coursesBean);
				String courseId = rs.getString(1);
				String courseName = rs.getString(2);
				String coursesAll = courseName + "|" + courseId;
				coursesSource.add(coursesAll);
			}
			courses = new DualListModel<String>(coursesSource,coursesTarjet);
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				logger.error(e);
			}
		}
	}
	
	
	
	public void procesaCursosAgregados(){
		conectionWrapper = new ConectionWrapper();
		StringBuilder stb = new StringBuilder();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		logger.debug("stuPErID " +session.getAttribute("stuPerId"));
		int stuPerId     = (Integer) session.getAttribute("stuPerId");
		String tipoUsuario  = (String) session.getAttribute("tipoUsuario");
		
		List<String> listC =  courses.getTarget();
		String sql  = "{CALL insert_courses_user_pr(?,?,?)}";
		
		
		try {
			con = conectionWrapper.getConexion();
			String delStu = "DELETE FROM student_courses WHERE student_id = ?";
			String delPer = "DELETE FROM personal_courses WHERE personal_id = ?";
			PreparedStatement pstm = null;
			
			if(tipoUsuario.equals("S")){
				pstm = con.prepareStatement(delStu);
				
			}else if(tipoUsuario.equals("P") || tipoUsuario.equals("A")){
				pstm = con.prepareStatement(delPer);
			}
			pstm.setInt(1, stuPerId);
			pstm.executeUpdate();
			
			
			CallableStatement cst = con.prepareCall(sql);
			
			if(listC.size() > 0){
				String cadena = "";
				int pai = 0;
				for(int i = 0; i < listC.size(); i++){
					cadena = (String)listC.get(i);
					pai = cadena.lastIndexOf("|");
					String nomCourse = cadena.substring(0,pai);
					stb.append(nomCourse + " ,");
					String courseId = cadena.substring(pai + 1, cadena.length());
					cst.setInt(1, stuPerId);
					cst.setString(2, courseId);
					cst.setString(3, tipoUsuario);
					cst.execute();
					
				}
				String allCourses = stb.toString().substring(0,stb.toString().length() - 1);
				session.removeAttribute("allCourses");
				session.setAttribute("allCourses", allCourses);
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se han agregado los cursos correctamente","Exito" ); 
			    FacesContext.getCurrentInstance().addMessage(null, message);
			}
			
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				logger.error(e);
			}
		}
		
		
			
			
		
		
		
		
		
/*		for(CoursesBean cur : listC){
			cur.getCourseId();
			cur.getCourseName();
			
		}*/
	}

	public DualListModel<String> getCourses() {
		return courses;
	}

	public void setCourses(DualListModel<String> courses) {
		this.courses = courses;
	}	
	
	
}

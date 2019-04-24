/**
 * @Jose de Jesus Argumedo Quiroz
 */
package mx.javaonline.admin.beans;

import java.io.Serializable;
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

import mx.javaonline.beans.StudentPersonalBean;
import mx.javaonline.model.ConectionWrapper;

import org.apache.log4j.Logger;
import org.primefaces.model.DualListModel;

public class AgregaQuitaCursos implements Serializable{
	

	private static final long serialVersionUID = 1L;
	private DualListModel<String> courses;
	private static org.apache.log4j.Logger logger = Logger.getLogger(AgregaQuitaCursos.class);
	FacesContext facesContext = FacesContext.getCurrentInstance();
	HttpSession session;
	StudentPersonalBean studentPersonalBean;
	private String tipoUsuario;
	private int studPersonalId;
	ConectionWrapper conectionWrapper;
	Connection con;
	List<String> coursesSource;
	List<String> coursesTarjet;


	public AgregaQuitaCursos() {
		coursesSource = new ArrayList<String>();
		coursesTarjet = new ArrayList<String>();
		showCursosJava();
	}

public void showCursosJava(){
		
		session = (HttpSession) facesContext.getExternalContext().getSession(true);
		studentPersonalBean = (StudentPersonalBean)session.getAttribute("studentPersonalBean");
		this.tipoUsuario = studentPersonalBean.getSegment1();
		this.studPersonalId = studentPersonalBean.getStudentPersonalId();
		
		logger.debug("this.tipoUsuario "+this.tipoUsuario);
		logger.debug("this.studPersonalId "+this.studPersonalId);
		
		String sqlstu = "SELECT  c.course_id,CONCAT(c.segment1,' ',course_name)  \n" +
						"FROM student_courses sc, \n" +
						"courses c \n" +
						"WHERE c.course_id = sc.course_id  \n" +
						"AND c.status = 'A' \n"+
						"AND student_id = ?";
		String sqlTarjetst = "SELECT course_id,CONCAT(segment1,' ',course_name)  \n" +
							 "FROM courses  \n" +
						 	 "WHERE course_id \n" +
						 	 "NOT IN (SELECT c.course_id  \n" +
						 	 "        FROM student_courses sc,\n" +
						 	 "             courses c\n" +
							 "        WHERE sc.student_id = ?\n" +
						     "		  AND   c.course_id = sc.course_id\n" +
						     "        AND   c.status='A') \n" +
							 "AND tipo = 'PJ' \n" +
							 "AND status = 'A'\n" +
								 "ORDER BY segment1";
		String sqlPer =  "SELECT  c.course_id,CONCAT(c.segment1,' ',c.course_name) \n " + 
						 "	FROM personal_courses sc, \n "+ 
						 "		 courses c \n " +
						 "	WHERE c.course_id = sc.course_id \n "+
						 "  AND   c.status = 'A' \n"+
						 "	AND   sc.personal_id = ?";
		String sqlTarjetper = "SELECT course_id,CONCAT(segment1,' ',course_name)  \n" +
							  "FROM courses  \n" +
							  "WHERE course_id \n" +
							  "NOT IN (SELECT c.course_id  \n" +
							  "	       FROM personal_courses sc,\n" +
							  "		   courses c\n" +
							  "			WHERE sc.personal_id = ?\n" +
							  "			AND    c.course_id = sc.course_id\n" +
							  "			AND   c.status='A') \n" +
							  "AND tipo = 'PJ' \n" +
							  "AND status = 'A'\n" +
							  "ORDER BY segment1";
		
		
		
		conectionWrapper = new ConectionWrapper();
		try {
			con = conectionWrapper.getConexion();
			PreparedStatement psmt1 = null;
			PreparedStatement psmt2 = null;
			if(this.tipoUsuario.equals("S")){
				psmt1 = con.prepareStatement(sqlstu);
				psmt1.setInt(1, this.studPersonalId);
				psmt2 = con.prepareStatement(sqlTarjetst);
				psmt2.setInt(1, this.studPersonalId);
			}else if(this.tipoUsuario.equals("P") || this.tipoUsuario.equals("A")){
				psmt1 = con.prepareStatement(sqlPer);
				logger.debug("sqlPer "+sqlPer);
				psmt1.setInt(1, this.studPersonalId);
				psmt2 = con.prepareStatement(sqlTarjetper);
				logger.debug("sqlTarjetper "+sqlTarjetper);
				psmt2.setInt(1, this.studPersonalId);
			}
			
			ResultSet rs = psmt1.executeQuery();
			
			String coursesSourc = "";
			while(rs.next()){
//				coursesBean = new CoursesBean();
//				coursesBean.setCourseId(rs.getInt(1));
//				coursesBean.setCourseName(rs.getString(2));
//				coursesSource.add(coursesBean);
				String courseId = rs.getString(1);
				String courseName = rs.getString(2);
				coursesSourc = courseName + "|" + courseId;
				coursesTarjet.add(coursesSourc);
			}
			ResultSet rs2 = psmt2.executeQuery();
			String coursesTarj = "";
			while(rs2.next()){
//				coursesBean = new CoursesBean(
				String courseId = rs2.getString(1);
				String courseName = rs2.getString(2);
				coursesTarj = courseName + "|" + courseId;
				coursesSource.add(coursesTarj);
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
	
	List<String> listC =  courses.getTarget();
	String sql  = "{CALL insert_courses_user_pr(?,?,?)}";
	
	
	try {
		con = conectionWrapper.getConexion();
		String delStu = "DELETE FROM student_courses WHERE student_id = ?";
		String delPer = "DELETE FROM personal_courses WHERE personal_id = ?";
		PreparedStatement pstm = null;
		
		if(this.tipoUsuario == null){
			studentPersonalBean = (StudentPersonalBean)session.getAttribute("studentPersonalBean");
			this.tipoUsuario = studentPersonalBean.getSegment1();
		}
		
		if(this.tipoUsuario.equals("S")){
			pstm = con.prepareStatement(delStu);
			
		}else if(this.tipoUsuario.equals("P") || this.tipoUsuario.equals("A")){
			pstm = con.prepareStatement(delPer);
		}
		pstm.setInt(1, this.studPersonalId);
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
				cst.setInt(1, this.studPersonalId);
				cst.setString(2, courseId);
				cst.setString(3, this.tipoUsuario);
				cst.execute();
			}
			String allCourses = stb.toString().substring(0,stb.toString().length() - 1);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se han agregado los cursos correctamente",
													"Para ver los cambios tienes que salir y volver a entrar a la aplicación" ); 
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
}

public DualListModel<String> getCourses() {
	return courses;
}

public void setCourses(DualListModel<String> courses) {
	this.courses = courses;
}



}

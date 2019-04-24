
package mx.javaonline.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import mx.javaonline.beans.StudentPersonalBean;
import mx.javaonline.model.ConectionWrapper;

import org.apache.log4j.Logger;


public class ReenviarCorreoDAO {

	private org.apache.log4j.Logger logger = Logger.getLogger(ReenviarCorreoDAO.class);
	ConectionWrapper conectionWrapper;
	Connection con;
	FacesContext facesContext;
	HttpSession session;
	StudentPersonalBean studentPersonalBean;
	List<StudentPersonalBean> listSP;
	
	public ReenviarCorreoDAO() {
		conectionWrapper = new ConectionWrapper();
	}
	
	public List<String> getCourses(int studenPersonalId,String personalStudent){
		List<String> listCourses = new ArrayList<>();
		String sql = "SELECT (SELECT course_name FROM courses WHERE course_id = sc.course_id) course_name\n" +
					"FROM student_courses sc\n" +
					"WHERE student_id = ?";
		String sql2 = "SELECT (SELECT course_name FROM courses WHERE course_id = sc.course_id) course_name\n" +
					"FROM personal_courses sc\n" +
					"WHERE personal_id = ?";
		try {
			con = conectionWrapper.getConexion();
			PreparedStatement psmt = null;
			if(personalStudent.equals("S")){
				psmt = con.prepareStatement(sql);
			}else if(personalStudent.equals("P") || personalStudent.equals("A")){
				psmt = con.prepareStatement(sql2);
			}
			
			psmt.setInt(1,studenPersonalId);
			ResultSet rs = psmt.executeQuery();
			String curso = "";
			while(rs.next()){
				curso = rs.getString(1);
				listCourses.add(curso);
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
		return listCourses;
	}
	
	public boolean existeMailBD(String user){
		conectionWrapper = new ConectionWrapper();
		boolean bandera = true;
		try {
			String sql = "SELECT COUNT(*) FROM login WHERE username = ?";
			con = conectionWrapper.getConexion();
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1,user);
			ResultSet rs = psmt.executeQuery();
			int count = 0;
			while(rs.next()){
				count = rs.getInt(1);
			}
			if(count == 0){
				bandera = false;
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
		return bandera;
	}
	
	public String getRol(int rolId){
		String sqlRol = "SELECT rol_name FROM roles  WHERE rol_id = ?";
		String rolName = "";
		try {
			con = conectionWrapper.getConexion();
			PreparedStatement psmt = con.prepareStatement(sqlRol);
			psmt.setInt(1, rolId);
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()){
				rolName = rs.getString(1);
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
		return rolName;
	}
	
	public boolean planchaPassword(int loginId,String pass){
		boolean bandera = false;
		String sql = "UPDATE login \n" +
				"SET password = MD5(?)\n" +
				"WHERE login_id = ?";
		try {
			con = conectionWrapper.getConexion();
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, pass);
			psmt.setInt(2, loginId);
			psmt.executeUpdate();
			bandera = true;
		} catch (NamingException | SQLException e) {
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
	
	public boolean guardaCorreo(String correo,int fromLoginId,String personalStudent,String pass){
		
		boolean bandera = false;
		String sql = "";
		if(personalStudent.equals("S")){
			sql =  "UPDATE students \n" +
					"SET mail = ?  \n" +
					"WHERE from_login_id = ?";
			
		}else if(personalStudent.equals("P") || personalStudent.equals("A")){
			sql =  "UPDATE personals \n" +
					"SET mail = ?  \n" +
					"WHERE from_login_id = ?";	
		}
		String sql2 = "UPDATE login \n" +
						"SET username = ?,password = MD5(?)\n" +
						"WHERE login_id = ?";
	
		try {
			con = conectionWrapper.getConexion();
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, correo);
			psmt.setInt(2, fromLoginId);
			psmt.executeUpdate();
			PreparedStatement psmt2 = con.prepareStatement(sql2);
			psmt2.setString(1, correo);
			psmt2.setString(2, pass);
			psmt2.setInt(3, fromLoginId);
			psmt2.executeUpdate();
			bandera = true;
		} catch (NamingException | SQLException e) {
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
	
	public List<StudentPersonalBean> buscaAlumno(String givenNames,String surnames,String fecIni,String fecFin){
		listSP = new ArrayList<>();
		
		String	sql2 = "SELECT num_cta,given_names,surnames,mail,creation_date,student_id,from_login_id,segment1,rol_id \n" +
							"FROM students\n" +
							"WHERE given_names LIKE ?";
		String	sql3 = "SELECT num_cta,given_names,surnames,mail,creation_date,student_id,from_login_id,segment1,rol_id \n" +
							"FROM students\n" +
							"WHERE surnames LIKE ?";
		String	sql4 = "SELECT num_cta,given_names,surnames,mail,creation_date,student_id,from_login_id,segment1,rol_id \n" +
							"FROM students\n" +
							"ORDER BY creation_date desc\n" +
							"LIMIT 50";
		String	sql5 = "SELECT num_cta,given_names,surnames,mail,creation_date,student_id,from_login_id,segment1,rol_id \n" +
							"FROM students\n" +
							"WHERE given_names LIKE ?\n" +
							"AND   surnames LIKE ?";
		String	sql6 = "SELECT num_cta,given_names,surnames,mail,creation_date,student_id,from_login_id,segment1,rol_id \n" +
							"FROM students\n" +
							"WHERE given_names LIKE ?\n" +
							"AND   surnames LIKE ?\n" +
							"AND DATE(creation_date) between ? AND ?";
		String	sql7 = "SELECT num_cta,given_names,surnames,mail,creation_date,student_id,from_login_id,segment1,rol_id\n" +
							"FROM students\n" +
							"WHERE surnames LIKE ? \n" +
							"AND DATE(creation_date) between ? AND ?";
		String	sql8 = "SELECT num_cta,given_names,surnames,mail,creation_date,student_id,from_login_id,segment1,rol_id\n" +
							"FROM students\n" +
							"WHERE given_names LIKE ? \n" +
							"AND DATE(creation_date between) ? AND ?";
		String	sql9 = "SELECT num_cta,given_names,surnames,mail,creation_date,student_id,from_login_id,segment1,rol_id\n" +
							"FROM students\n" +
							"WHERE DATE(creation_date) between ? AND ?";
		
		
		try {
			con = conectionWrapper.getConexion();
			PreparedStatement psmt = null;
			if(givenNames.equals("") && surnames.equals("") && fecIni.equals("") && fecFin.equals("")){
				psmt =  con.prepareStatement(sql4);
			}else if(!givenNames.equals("") && surnames.equals("") && fecIni.equals("") && fecFin.equals("")){
				psmt =  con.prepareStatement(sql2);
				psmt.setString(1, "%" + givenNames + "%");
			}else if(!givenNames.equals("") && !surnames.equals("") && fecIni.equals("") && fecFin.equals("")){
				psmt =  con.prepareStatement(sql5);
				psmt.setString(1, "%" + givenNames + "%");
				psmt.setString(2, "%" + surnames + "%");
			}else if(!givenNames.equals("") && !surnames.equals("") && !fecIni.equals("") && !fecFin.equals("")){
				psmt =  con.prepareStatement(sql6);
				psmt.setString(1, "%" + givenNames + "%");
				psmt.setString(2, "%" + surnames + "%");
				psmt.setString(3, "%" + fecIni + "%");
				psmt.setString(4, "%" + fecFin + "%");
			}else if(givenNames.equals("") && !surnames.equals("") && fecIni.equals("") && fecFin.equals("")){
				psmt =  con.prepareStatement(sql3);
				psmt.setString(1, "%" + surnames + "%");
				
			}else if(givenNames.equals("") && !surnames.equals("") && !fecIni.equals("") && !fecFin.equals("")){
				psmt =  con.prepareStatement(sql7);
				psmt.setString(1, "%" + surnames + "%");
				psmt.setString(2, fecIni);
				psmt.setString(3, fecFin);
			}else if(!givenNames.equals("") && surnames.equals("") && !fecIni.equals("") && !fecFin.equals("")){
				psmt =  con.prepareStatement(sql8);
				psmt.setString(1, "%" + givenNames + "%");
				psmt.setString(2, fecIni);
				psmt.setString(3, fecFin);
				
			}else if(givenNames.equals("") && surnames.equals("") && !fecIni.equals("") && !fecFin.equals("")){
				psmt =  con.prepareStatement(sql9);
				psmt.setString(1, fecIni);
				psmt.setString(2, fecFin);
			}
			
			
			ResultSet rs = psmt.executeQuery();
			int count = 0;
			while(rs.next()){
				count ++;
				studentPersonalBean = new StudentPersonalBean();
				studentPersonalBean.setNumCta(rs.getString(1));
				studentPersonalBean.setGivenNames(rs.getString(2));
				studentPersonalBean.setSurNames(rs.getString(3));
				studentPersonalBean.setMail(rs.getString(4));
				studentPersonalBean.setCreationDate(rs.getDate(5));
				studentPersonalBean.setStudentPersonalId(rs.getInt(6));
				studentPersonalBean.setFromLoginId(rs.getInt(7));
				studentPersonalBean.setSegment1(rs.getString(8));
				studentPersonalBean.setRolId((rs.getInt(9)));
				listSP.add(studentPersonalBean);
			}
				listSP = buscaPersonal(givenNames,surnames,fecIni,fecFin,listSP);
			
			
		} catch (NamingException | SQLException e) {
			logger.error(e);
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				logger.error(e);
			}
	    }
		return listSP;
	}
	
	public List<StudentPersonalBean> buscaPersonal(String givenNames,String surnames,String fecIni,String fecFin,List<StudentPersonalBean> listSP){
		//listSP = new ArrayList<>();	
			
		String	sql2 = "SELECT num_cta,given_names,surnames,mail,creation_date,personal_id,from_login_id,segment1,rol_id \n" +
					"FROM personals\n" +
					"WHERE given_names LIKE ?";
		String	sql3 = "SELECT num_cta,given_names,surnames,mail,creation_date,personal_id,from_login_id,segment1,rol_id \n" +
							"FROM personals\n" +
							"WHERE surnames LIKE ?";
		String	sql4 = "SELECT num_cta,given_names,surnames,mail,creation_date,personal_id,from_login_id,segment1,rol_id \n" +
							"FROM personals\n" +
							"ORDER BY creation_date desc\n" +
							"LIMIT 50";
		String	sql5 = "SELECT num_cta,given_names,surnames,mail,creation_date,personal_id,from_login_id,segment1,rol_id \n" +
							"FROM personals\n" +
							"WHERE given_names LIKE ?\n" +
							"AND   surnames LIKE ?";
		String	sql6 = "SELECT num_cta,given_names,surnames,mail,creation_date,personal_id,from_login_id,segment1,rol_id \n" +
							"FROM personals\n" +
							"WHERE given_names LIKE ?\n" +
							"AND   surnames LIKE ?\n" +
							"AND DATE(creation_date) between ? AND ?";
		String	sql7 = "SELECT num_cta,given_names,surnames,mail,creation_date,personal_id,from_login_id,segment1,rol_id\n" +
							"FROM personals\n" +
							"WHERE surnames LIKE ? \n" +
							"AND DATE(creation_date) between ? AND ?";
		String	sql8 = "SELECT num_cta,given_names,surnames,mail,creation_date,personal_id,from_login_id,segment1,rol_id\n" +
							"FROM personals\n" +
							"WHERE given_names LIKE ? \n" +
							"AND DATE(creation_date between) ? AND ?";
		String	sql9 = "SELECT num_cta,given_names,surnames,mail,creation_date,personal_id,from_login_id,segment1,rol_id\n" +
							"FROM personals\n" +
							"WHERE DATE(creation_date) between ? AND ?";
		
		try {
			con = conectionWrapper.getConexion();
			PreparedStatement psmt = null;
			if(givenNames.equals("") && surnames.equals("") && fecIni.equals("") && fecFin.equals("")){
				psmt =  con.prepareStatement(sql4);
			}else if(!givenNames.equals("") && surnames.equals("") && fecIni.equals("") && fecFin.equals("")){
				psmt =  con.prepareStatement(sql2);
				psmt.setString(1, "%" + givenNames + "%");
			}else if(!givenNames.equals("") && !surnames.equals("") && fecIni.equals("") && fecFin.equals("")){
				psmt =  con.prepareStatement(sql5);
				psmt.setString(1, "%" + givenNames + "%");
				psmt.setString(2, "%" + surnames + "%");
			}else if(!givenNames.equals("") && !surnames.equals("") && !fecIni.equals("") && !fecFin.equals("")){
				psmt =  con.prepareStatement(sql6);
				psmt.setString(1, "%" + givenNames + "%");
				psmt.setString(2, "%" + surnames + "%");
				psmt.setString(3, "%" + fecIni + "%");
				psmt.setString(4, "%" + fecFin + "%");
			}else if(givenNames.equals("") && !surnames.equals("") && fecIni.equals("") && fecFin.equals("")){
				psmt =  con.prepareStatement(sql3);
				psmt.setString(1, "%" + surnames + "%");
				
			}else if(givenNames.equals("") && !surnames.equals("") && !fecIni.equals("") && !fecFin.equals("")){
				psmt =  con.prepareStatement(sql7);
				psmt.setString(1, "%" + surnames + "%");
				psmt.setString(2, fecIni);
				psmt.setString(3, fecFin);
			}else if(!givenNames.equals("") && surnames.equals("") && !fecIni.equals("") && !fecFin.equals("")){
				psmt =  con.prepareStatement(sql8);
				psmt.setString(1, "%" + givenNames + "%");
				psmt.setString(2, fecIni);
				psmt.setString(3, fecFin);
				
			}else if(givenNames.equals("") && surnames.equals("") && !fecIni.equals("") && !fecFin.equals("")){
				psmt =  con.prepareStatement(sql9);
				psmt.setString(1, fecIni);
				psmt.setString(2, fecFin);
			}
			
			
			ResultSet rs = psmt.executeQuery();
			while(rs.next()){
				studentPersonalBean = new StudentPersonalBean();
				studentPersonalBean.setNumCta(rs.getString(1));
				studentPersonalBean.setGivenNames(rs.getString(2));
				studentPersonalBean.setSurNames(rs.getString(3));
				studentPersonalBean.setMail(rs.getString(4));
				studentPersonalBean.setCreationDate(rs.getDate(5));
				studentPersonalBean.setStudentPersonalId(rs.getInt(6));
				studentPersonalBean.setFromLoginId(rs.getInt(7));
				studentPersonalBean.setSegment1(rs.getString(8));
				studentPersonalBean.setRolId((rs.getInt(9)));
				listSP.add(studentPersonalBean);
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
		return listSP;
	}

}

package mx.javaonline.admin.beans;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import mx.javaonline.beans.StudentPersonalBean;
import mx.javaonline.model.ConectionWrapper;
import mx.javaonline.util.ManejoFechas;

import org.apache.log4j.Logger;
import org.primefaces.model.DualListModel;

public class BuscaActualizaUsuario {
	
	private int studPersonalId;
	private String numCta;
	private String givenNames;
	private String surnames;
	private String phone;
	private String mail;
	private String country;
	private String city;
	private String state;
	private int rolId;
	private java.sql.Date creationDate;
	private String endDate;
	private boolean prueba;
	private boolean activar;
	private String buskNumCta;
	private String buskMail;
	private String segment2;
	private String tipoUsuario;
	private String segment1;
	private boolean prueba2;
	private int formaPago;
	
	private static org.apache.log4j.Logger logger = Logger.getLogger(BuscaActualizaUsuario.class);
	ConectionWrapper conectionWrapper;
	Connection con;
	private DualListModel<String> courses;
	List<String> coursesSource;
	List<String> coursesTarjet;
	FacesContext facesContext = FacesContext.getCurrentInstance();
	HttpSession session;
	StudentPersonalBean studentPersonalBean;

	public BuscaActualizaUsuario() {
		session = (HttpSession) facesContext.getExternalContext().getSession(true);
		coursesSource = new ArrayList<String>();
		coursesTarjet = new ArrayList<String>();
		courses = new DualListModel<String>(coursesSource,coursesTarjet);
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
	}
	public void showCursos(){
	
		logger.debug("this.tipoUsuario "+this.tipoUsuario);
		logger.debug("this.studPersonalId "+this.studPersonalId);
		String sqlstu =  "SELECT  c.course_id,c.course_name \n " + 
						 "	FROM student_courses sc, \n "+ 
						 "		 courses c \n " +
						 "	WHERE c.course_id = sc.course_id \n "+  
						 "	AND student_id = ?";
		String sqlTarjetst = "SELECT course_id,course_name \n"+ 
							" FROM courses  \n"+
							" WHERE course_id \n"+
							" NOT IN (SELECT course_id \n"+ 
							"		FROM student_courses \n"+
							"		WHERE student_id = ?)\n"+
							" AND course_id <> 3";
		String sqlPer =  "SELECT  c.course_id,c.course_name \n " + 
						 "	FROM personal_courses sc, \n "+ 
						 "		 courses c \n " +
						 "	WHERE c.course_id = sc.course_id \n "+  
						 "	AND sc.personal_id = ?";
		String sqlTarjetper = "SELECT course_id,course_name \n"+ 
							" FROM courses  \n"+
							" WHERE course_id \n"+
							" NOT IN (SELECT course_id \n"+ 
							"		FROM personal_courses \n"+
							"		WHERE personal_id = ?)\n"+
							" AND course_id <> 3";
		
		
		
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
			String courseId = "";
			String courseName = "";
			while(rs.next()){
//				coursesBean = new CoursesBean();
//				coursesBean.setCourseId(rs.getInt(1));
//				coursesBean.setCourseName(rs.getString(2));
//				coursesSource.add(coursesBean);
				courseId = rs.getString(1);
				courseName = rs.getString(2);
				coursesSourc = courseName + "|" + courseId;
				coursesTarjet.add(coursesSourc);
			}
			ResultSet rs2 = psmt2.executeQuery();
			String coursesTarj = "";
			while(rs2.next()){
//				coursesBean = new CoursesBean(
				courseId = rs2.getString(1);
				courseName = rs2.getString(2);
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
	
	
	public void buscaUsuario(){
		logger.debug("buskMail "+buskMail);
		logger.debug("buskNumCta "+buskNumCta);
		conectionWrapper = new ConectionWrapper();
		try {
			con = conectionWrapper.getConexion();
			CallableStatement cst = con.prepareCall("{CALL busca_usuario_pr(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			cst.setString(1, buskNumCta);
			cst.setString(2, buskMail);
			cst.registerOutParameter(3, Types.INTEGER);
			cst.registerOutParameter(4, Types.VARCHAR);
			cst.registerOutParameter(5, Types.VARCHAR);
			cst.registerOutParameter(6, Types.VARCHAR);
			cst.registerOutParameter(7, Types.VARCHAR);
			cst.registerOutParameter(8, Types.VARCHAR);
			cst.registerOutParameter(9, Types.VARCHAR);
			cst.registerOutParameter(10, Types.VARCHAR);
			cst.registerOutParameter(11, Types.VARCHAR);
			cst.registerOutParameter(12, Types.INTEGER);
			cst.registerOutParameter(13, Types.DATE);
			cst.registerOutParameter(14, Types.DATE);
			cst.registerOutParameter(15, Types.VARCHAR);
			cst.registerOutParameter(16, Types.VARCHAR);
			cst.registerOutParameter(17, Types.INTEGER);
			cst.registerOutParameter(18, Types.INTEGER);
			cst.execute();
			this.studPersonalId = cst.getInt(3);
			this.numCta         = cst.getString(4);
			this.givenNames     = cst.getString(5);
			this.surnames       = cst.getString(6);
			this.phone          = cst.getString(7);
			this.mail           = cst.getString(8);
			this.country        = cst.getString(9);
			this.city           = cst.getString(10);
			this.state          = cst.getString(11);
			this.rolId          = cst.getInt(12);
			this.creationDate   = cst.getDate(13);
			this.endDate        = cst.getString(14);
			this.segment2       = cst.getString(15);
			this.tipoUsuario    = cst.getString(16);
			this.activar        = cst.getBoolean(17);
			this.formaPago      = cst.getInt(18);
			if(this.endDate != null){
				if(!this.endDate.equals("9999-12-31") ){
					this.prueba = true;
				}
			}
			
			
//			logger.debug(this.studPersonalId);
//			logger.debug(this.numCta);
//			logger.debug(this.givenNames);
//			logger.debug(this.surnames);
//			logger.debug(this.phone);
//			logger.debug(this.mail);
//			logger.debug(this.country);
//			logger.debug(this.city);
//			logger.debug(this.state);
//			logger.debug(this.rolId);
//			logger.debug(this.creationDate);
//			logger.debug(this.endDate);
//			logger.debug(this.segment2);
//			logger.debug("this.tipoUsuario "+this.tipoUsuario);
			if(this.studPersonalId == 0){
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "No se encontro resultados","Warning" ); 
			    FacesContext.getCurrentInstance().addMessage(null, message);
			}else{
				showCursos();
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
		
	}
	
	public void actualizaUsuario(){
/*		
		logger.debug(this.studPersonalId);
		logger.debug(this.numCta);
		logger.debug(this.givenNames);
		logger.debug(this.surnames);
		logger.debug(this.phone);
		logger.debug(this.mail);
		logger.debug(this.country);
		logger.debug(this.city);
		logger.debug(this.state);
		logger.debug(this.creationDate);
		logger.debug(this.prueba);
		logger.debug(this.segment2);
		logger.debug(this.tipoUsuario);
*/		
		java.sql.Date fecFin = null;
		if(prueba){
			 fecFin = ManejoFechas.sumarFechasDias(new java.sql.Date(new java.util.Date().getTime()),10); 
		}else if(prueba2){
				 fecFin = ManejoFechas.sumarFechasDias(new java.sql.Date(new java.util.Date().getTime()),31); 
		}else{
			Date fec = ManejoFechas.deStringToDate("9999-12-31");
			fecFin = new java.sql.Date(fec.getTime());
		}
		logger.debug("fecFin "+fecFin);
		conectionWrapper = new ConectionWrapper();
		
		try {
			con = conectionWrapper.getConexion();
			CallableStatement cst = con.prepareCall("{CALL update_user_pr(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			
			cst.setInt(1,this.studPersonalId);
			cst.setString(2, this.givenNames);
			cst.setString(3, this.surnames);
			cst.setInt(4, ManejoFechas.dameEdad(this.segment2));
			cst.setString(5, this.phone);
			cst.setString(6, this.country);
			cst.setString(7, this.city);
			cst.setString(8, this.state);
			cst.setDate(9, fecFin);
			cst.setString(10, this.segment2);
			cst.setString(11, this.tipoUsuario);
			cst.setBoolean(12, this.activar);
			cst.setInt(13, this.formaPago);
			cst.execute();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Los datos se actualizaron correctamente","Exito" ); 
		    FacesContext.getCurrentInstance().addMessage(null, message);
			
		} catch (NamingException | SQLException e) {
			logger.error(e);
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				logger.error(e);
			}
		}
			
	}
	public int getStudPersonalId() {
		return studPersonalId;
	}

	public void setStudPersonalId(int studPersonalId) {
		this.studPersonalId = studPersonalId;
	}

	public String getNumCta() {
		return numCta;
	}

	public void setNumCta(String numCta) {
		this.numCta = numCta;
	}

	public String getGivenNames() {
		return givenNames;
	}

	public void setGivenNames(String givenNames) {
		this.givenNames = givenNames;
	}

	public String getSurnames() {
		return surnames;
	}

	public void setSurnames(String surnames) {
		this.surnames = surnames;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getRolId() {
		return rolId;
	}

	public void setRolId(int rolId) {
		this.rolId = rolId;
	}

	public java.sql.Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(java.sql.Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public boolean isPrueba() {
		return prueba;
	}

	public void setPrueba(boolean prueba) {
		this.prueba = prueba;
	}

	public String getBuskNumCta() {
		return buskNumCta;
	}

	public void setBuskNumCta(String buskNumCta) {
		this.buskNumCta = buskNumCta;
	}

	public String getBuskMail() {
		return buskMail;
	}

	public void setBuskMail(String buskMail) {
		this.buskMail = buskMail;
	}

	public String getSegment2() {
		return segment2;
	}

	public void setSegment2(String segment2) {
		this.segment2 = segment2;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public String getSegment1() {
		return segment1;
	}

	public void setSegment1(String segment1) {
		this.segment1 = segment1;
	}
	public DualListModel<String> getCourses() {
		return courses;
	}
	public void setCourses(DualListModel<String> courses) {
		this.courses = courses;
	}

	public boolean isActivar() {
		return activar;
	}

	public void setActivar(boolean activar) {
		this.activar = activar;
	}

	public boolean isPrueba2() {
		return prueba2;
	}

	public void setPrueba2(boolean prueba2) {
		this.prueba2 = prueba2;
	}

	public int getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(int formaPago) {
		this.formaPago = formaPago;
	}
	
	
	
}
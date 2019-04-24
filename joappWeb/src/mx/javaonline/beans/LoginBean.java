package mx.javaonline.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.javaonline.model.ConectionWrapper;
import mx.javaonline.util.FuncionesGrales;
import mx.javaonline.util.mail.SendMails;

import org.apache.log4j.Logger;

public class LoginBean {
	private static org.apache.log4j.Logger logger = Logger
			.getLogger(LoginBean.class);

	private String user;
	private String password;
	private String strJson;
	ConectionWrapper conectionWrapper;
	StudentPersonalBean studentPersonalBean;
	FacesContext facesContext = FacesContext.getCurrentInstance();
	ResourceBundle bundle = ResourceBundle.getBundle("DatosGenerales");
	HttpSession session = (HttpSession) facesContext.getExternalContext()
			.getSession(true);
	private String correo;
	Connection con;
	SendMails sendMails;

	public LoginBean() {

		conectionWrapper = new ConectionWrapper();
	}

	public void olvidoContrasena() {
		String select = "SELECT COUNT(*) FROM login WHERE username = ?";

		try {
			con = conectionWrapper.getConexion();
			PreparedStatement pstmt = con.prepareStatement(select);
			pstmt.setString(1, correo);
			ResultSet rs = pstmt.executeQuery();
			int encontro = 0;
			while (rs.next()) {
				encontro = rs.getInt(1);
			}
			if (encontro > 0) {
				String pass = FuncionesGrales.generaContrasena(correo);
				String update = "UPDATE login SET password = MD5(?) WHERE username = ?";
				PreparedStatement pstmt2 = con.prepareStatement(update);
				pstmt2.setString(1, pass);
				pstmt2.setString(2, correo);
				pstmt2.executeUpdate();
//				logger.debug("pass " + pass);
				sendMails = new SendMails();
				boolean bandera = sendMails.recuperaPassword(pass, correo);
				if (bandera) {
					FacesContext
							.getCurrentInstance()
							.addMessage(
									null,
									new FacesMessage(
											FacesMessage.SEVERITY_INFO,
											"Correo enviado",
											"Revisa tu correo e intenta logearte de nuevo"));
				}

			} else {
				FacesContext
						.getCurrentInstance()
						.addMessage(
								null,
								new FacesMessage(
										FacesMessage.SEVERITY_ERROR,
										"El correo que capturaste no se encuentra en nuestro sistema",
										"E-mail inválido"));
			}

		} catch (NamingException | SQLException e) {
			logger.debug(e);
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_FATAL, e
								.getClass().getName(), e.getMessage()));
				logger.error(e);
			}
		}

	}

	public String passStudent() {
		String paNoPasa = "NoPasa";
		/*if(user.equals("") && password.equals("")) {
			System.out.println("json "+ strJson);
			paNoPasa = "success";
		}else {
			*/
		
		try {
			con = conectionWrapper.getConexion();
			String sql = "SELECT login_id FROM login \n"
					+ " WHERE USERNAME = ? \n" + "AND password = MD5(?)";

			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, user);
			psmt.setString(2, password);
			ResultSet rs = psmt.executeQuery();
			int loginId = 0;
			while (rs.next()) {
				loginId = rs.getInt(1);
			}
			if (loginId > 0) {
				boolean ai = consultaActivoInactivo(loginId, con);
				if (ai) {// Reviso si el usuario esta activo

					if (validaStudiante(loginId, con)) {

						updateLastLogin(loginId, con);
						paNoPasa = "success";
					} else if (validaPersonal(loginId, con)) {
						updateLastLogin(loginId, con);
						paNoPasa = "success";
					} else {
						FacesContext
								.getCurrentInstance()
								.addMessage(
										null,
										new FacesMessage(
												FacesMessage.SEVERITY_INFO,
												bundle.getString("usuario.noregistrado.responsabilidad"),
												bundle.getString("mensaje.ayuda")));
					}
				} else {
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_WARN, bundle
									.getString("usuario.inactivo"), bundle
									.getString("mensaje.ayuda")));
				}
			} else {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle
								.getString("usuario.contrasena.incorrecto"),
								bundle.getString("vuelve.intentar")));
			}

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, e.getClass()
							.getName(), e.getMessage()));
			logger.error(e);

		} finally {
			try {
				con.close();

			} catch (SQLException e) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_FATAL, e
								.getClass().getName(), e.getMessage()));

				logger.error(e);
			}
		}
	//}
//		logger.debug("paNoPasa " + paNoPasa);
		return paNoPasa;
	}

	public boolean consultaActivoInactivo(int loginId, Connection con) {
		boolean ai = false;
		String selectActivo = "SELECT count(login_id) \n"
				+ "	FROM login \n"
				+ "	WHERE login_id = ? \n";
				//+ " AND sysdate() <= end_date";

		try {
			PreparedStatement psmt = con.prepareStatement(selectActivo);
			psmt.setInt(1, loginId);
			ResultSet rs = psmt.executeQuery();
			int count = 0;
			while (rs.next()) {
				count = rs.getInt(1);
			}
			if (count > 0) {
				ai = true;
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, e.getClass()
							.getName(), e.getMessage()));
			logger.error(e);

		}
		/*
		 * finally{ try { con.close();
		 * 
		 * } catch (SQLException e) {
		 * FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
		 * FacesMessage.SEVERITY_FATAL, e.getClass().getName(),
		 * e.getMessage()));
		 * 
		 * logger.error(e); } }
		 */
		return ai;
	}

	public void updateLastLogin(int loginId, Connection con) {
		String updateLastLogin = "UPDATE login \n"
				+ "SET last_login= sysdate(), segment2 = 1 \n" + "WHERE  login_id = ?";

		try {
			PreparedStatement psmt = con.prepareStatement(updateLastLogin);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String utilDate = sdf.format(new java.util.Date());
			//psmt.setString(1, utilDate);
			psmt.setInt(1, loginId);
			psmt.executeUpdate();
		} catch (SQLException e) {
			logger.error(e);
		}
	}

	private boolean validaPersonal(int loginId, Connection con)
			throws SQLException {
		boolean bandera = false;
		String sQueryPersonal = "SELECT  s.personal_id,\n" + "s.num_cta,\n"
				+ "s.given_names,\n" + "s.surnames,\n" + "s.age,\n"
				+ "s.marital_status,\n" + "s.phone,\n" + "s.mail,\n"
				+ "s.country,\n" + "s.city,\n" + "s.state,\n"
				+ "s.from_login_id,\n" + "s.rol_id,\n" + "s.segment1,\n"
				+ "s.segment2,\n" + "s.segment3,\n" + "s.segment4,\n"
				+ "s.segment5,\n" + "s.segment6,\n" + "s.segment7,\n"
				+ "s.segment8,\n" + "s.creation_date,\n" + "s.end_date,\n"
				+ "r.rol_name rol_name\n" + "FROM personals s,roles r\n"
				+ "WHERE s.rol_id = r.rol_id AND from_login_id = ?";
//		logger.info("Query estudiantes " + sQueryPersonal);
		PreparedStatement psmt = con.prepareStatement(sQueryPersonal);
		psmt.setInt(1, loginId);
		ResultSet rs = psmt.executeQuery();
		while (rs.next()) {
			studentPersonalBean = new StudentPersonalBean();
			studentPersonalBean.setStudentPersonalId(rs.getInt("personal_id"));
			studentPersonalBean.setNumCta(rs.getString("num_cta"));
			studentPersonalBean.setGivenNames(rs.getString("given_names"));
			studentPersonalBean.setSurNames(rs.getString("surnames"));
			studentPersonalBean.setAge(rs.getInt("age"));
			studentPersonalBean.setMaritalStatus(rs.getString("marital_status"));
			studentPersonalBean.setPhone(rs.getString("phone"));
			studentPersonalBean.setMail(rs.getString("mail"));
			studentPersonalBean.setCountry(rs.getString("country"));
			studentPersonalBean.setCity(rs.getString("city"));
			studentPersonalBean.setState(rs.getString("state"));
			studentPersonalBean.setFromLoginId(rs.getInt("from_login_id"));
			studentPersonalBean.setRolId(rs.getInt("rol_id"));
			studentPersonalBean.setSegment1(rs.getString("segment1"));
			studentPersonalBean.setSegment2(rs.getString("segment2"));
			studentPersonalBean.setSegment3(rs.getString("segment3"));
			studentPersonalBean.setSegment4(rs.getString("segment4"));
			studentPersonalBean.setSegment5(rs.getString("segment5"));
			studentPersonalBean.setSegment6(rs.getInt("segment6"));
			studentPersonalBean.setSegment7(rs.getDate("segment7"));
			studentPersonalBean.setSegment8(rs.getBlob("segment8"));
			studentPersonalBean.setCreationDate(rs.getDate("creation_date"));
			studentPersonalBean.setEndDate(rs.getDate("end_date"));
			studentPersonalBean.setRolName(rs.getString("rol_name"));

			session.setAttribute("studentPersonalBean", studentPersonalBean);
			bandera = true;
		}

		return bandera;
	}

	public boolean validaStudiante(int loginId, Connection con)
			throws NamingException, SQLException {

		boolean bandera = false;
		String sQueryStudents = "SELECT  s.student_id,\n" + "s.num_cta,\n"
				+ "s.first_name,\n" + "s.last_name,\n" + "s.age,\n"
				+ "s.marital_status,\n" + "s.phone,\n" + "s.mail,\n"
				+ "s.country,\n" + "s.city,\n" + "s.state,\n"
				+ "s.from_login_id,\n" + "s.rol_id,\n" + "s.segment1,\n"
				+ "s.segment2,\n" + "s.segment3,\n" + "s.segment4,\n"
				+ "s.segment5,\n" + "s.segment6,\n" + "s.segment7,\n"
				+ "s.segment8,\n" + "s.creation_date,\n" + "s.end_date,\n"
				+ "r.rol_name rol_name\n" + "FROM students s,roles r\n"
				+ "WHERE s.rol_id = r.rol_id AND from_login_id = ?";
		// logger.info("Query estudiantes "+sQueryStudents);
		PreparedStatement psmt = con.prepareStatement(sQueryStudents);
		psmt.setInt(1, loginId);
		ResultSet rs = psmt.executeQuery();
		while (rs.next()) {
			studentPersonalBean = new StudentPersonalBean();
			studentPersonalBean.setStudentPersonalId(rs.getInt("student_id"));
			studentPersonalBean.setNumCta(rs.getString("num_cta"));
			studentPersonalBean.setGivenNames(rs.getString("first_name"));
			studentPersonalBean.setSurNames(rs.getString("last_name"));
			studentPersonalBean.setAge(rs.getInt("age"));
			studentPersonalBean
					.setMaritalStatus(rs.getString("marital_status"));
			studentPersonalBean.setPhone(rs.getString("phone"));
			studentPersonalBean.setMail(rs.getString("mail"));
			studentPersonalBean.setCountry(rs.getString("country"));
			studentPersonalBean.setCity(rs.getString("city"));
			studentPersonalBean.setState(rs.getString("state"));
			studentPersonalBean.setFromLoginId(rs.getInt("from_login_id"));
			studentPersonalBean.setRolId(rs.getInt("rol_id"));
			studentPersonalBean.setSegment1(rs.getString("segment1"));
			studentPersonalBean.setSegment2(rs.getString("segment2"));
			studentPersonalBean.setSegment3(rs.getString("segment3"));
			studentPersonalBean.setSegment4(rs.getString("segment4"));
			studentPersonalBean.setSegment5(rs.getString("segment5"));
			studentPersonalBean.setSegment6(rs.getInt("segment6"));
			studentPersonalBean.setSegment7(rs.getDate("segment7"));
			studentPersonalBean.setSegment8(rs.getBlob("segment8"));
			studentPersonalBean.setCreationDate(rs.getDate("creation_date"));
			studentPersonalBean.setEndDate(rs.getDate("end_date"));
			studentPersonalBean.setRolName(rs.getString("rol_name"));

			session.setAttribute("studentPersonalBean", studentPersonalBean);
			bandera = true;
		}

		return bandera;
	}

	public String salir() {

		StudentPersonalBean studentPersonalBean = (StudentPersonalBean) session
				.getAttribute("studentPersonalBean");
		String updateActive = "UPDATE login \n" + "SET segment2 = 0 , last_login = sysdate() \n"
				+ "WHERE  login_id = ?";

		try {
			con = conectionWrapper.getConexion();
			PreparedStatement psmt = con.prepareStatement(updateActive);
			psmt.setInt(1, studentPersonalBean.getFromLoginId());
			psmt.executeUpdate();
		} catch (SQLException | NamingException e) {
			logger.error(e);
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				logger.error(e);
			}
		}

		logger.debug("Session de " + studentPersonalBean.getGivenNames()
				+ " Cerrada!!");
		FacesContext.getCurrentInstance().getExternalContext()
				.invalidateSession();

		return "salir";
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getStrJson() {
		return strJson;
	}

	public void setStrJson(String strJson) {
		this.strJson = strJson;
	}
	
	

}

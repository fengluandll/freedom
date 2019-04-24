package mx.kaz.beans;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import mx.kaz.daos.ProyectDAO;
import mx.kaz.jobs.TestJob;
import mx.kaz.model.ConectionWrapper;
import mx.kaz.util.FuncionesGenerales;
import mx.kaz.util.Mensajes;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;

public class LoginBean {
	
	private static org.apache.log4j.Logger logger = Logger.getLogger(LoginBean.class);

	private String user;
	private String password;
	private String confirmPassword;
	private String beforePassword;
	ConectionWrapper conectionWrapper;
	FacesContext facesContext = FacesContext.getCurrentInstance();
	HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
	Connection con;
	PersonalBean personalBean;
	ProyectDAO proyectDAO;
	
	public LoginBean() throws SchedulerException{
		conectionWrapper = new ConectionWrapper();
		proyectDAO = new ProyectDAO();
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();
//		JobDetail job = new Job(TestJob.class);
//		JobDetail job = new Job("","",TestJob.class);
	
	}
	
	public String validaCambioPassword() {
		
		String flujo 			= "";
		String status 			= "";
		CallableStatement cs 	= null;
		if(!this.password.equals(this.confirmPassword)) {
			FuncionesGenerales.mostrarMensaje("Error de validación de contraseña", "Las contraseñas deben de coincidir", Mensajes.WARN);
		
		}else {
			String user =  (String)session.getAttribute("user");
			String beforepass =  (String)session.getAttribute("pass");
			try {
				con = conectionWrapper.getConexion();
				cs = con.prepareCall("{ CALL updatePassword(?,?,?,?) }");
				cs.setString(1, user);
				cs.setString(2, this.password);
				cs.setString(3, beforepass);
				cs.registerOutParameter(4, java.sql.Types.VARCHAR);
				cs.executeUpdate();
				status = cs.getString(4);
				if(status.equals("OK")) {
					this.user = user;
					flujo = passUser();
				}else {
					FuncionesGenerales.mostrarMensaje("Error", "La contraseña anterior no es la correcta", Mensajes.ERROR);
				}
			} catch (SQLException | NamingException e) {
				e.printStackTrace();
				logger.error(e);
			}finally {
				try {
					cs.close();
					con.close();
	
				} catch (SQLException e) {
					FuncionesGenerales.mostrarMensaje(e.getClass().getName(), e.getMessage(), Mensajes.FATAL);
					logger.error(e);
				}
			}
			
		}
		return flujo;
	}

	public String passUser(){
		
		String pasaNoPasa = "No";
//		if(personalBean == null){
		String status = "";	
			
			try {
				con = conectionWrapper.getConexion();
				CallableStatement cs = con.prepareCall("{ CALL login_user_pr(?,?,?) }");
				cs.setString(1, user);
				cs.setString(2, password);
				cs.registerOutParameter(3, java.sql.Types.VARCHAR);
				cs.executeUpdate();
				status = cs.getString(3);
				
				/*String sql = "SELECT login_id FROM login \n"
						+ " WHERE USERNAME = ? \n" + "AND password = MD5(?) AND id_status = 1";
				PreparedStatement psmt = con.prepareStatement(sql);
				psmt.setString(1, user);
				psmt.setString(2, password);
				ResultSet rs = psmt.executeQuery();
				
				while (rs.next()) {
					loginId = rs.getInt(1);
				}*/
				if(status.equals("CAMBIO")) {
					pasaNoPasa = "CAMBIO_PASS";
					session.setAttribute("personalBean",new PersonalBean());
					session.setAttribute("user", user);
					session.setAttribute("pass", password);
				}else if(status.equals("INACTIVO")) {
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_WARN, "El usuario se encuentra inactivo, consulte al Administrador del Sistema ","Acceso no permitido"));
				}else {
				
					int loginId = Integer.parseInt(status);
					if (loginId > 0) {
						boolean bandera = validaPersonal(loginId,con);
						if(bandera){
							pasaNoPasa = "principal";
						}else{
							FacesContext.getCurrentInstance().addMessage(
									null,
									new FacesMessage(FacesMessage.SEVERITY_WARN, "El usuario no tiene asignado un rol","Agregue un rol al usuario"));
						}
						
						
					} else {
						FacesContext.getCurrentInstance().addMessage(
								null,
								new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario y/o contraseña incorrecto","Vuelva a intentarlo"));
					}
				}
	
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_FATAL, e.getClass()
								.getName(), e.getMessage()));
				e.printStackTrace();
	
			} finally {
				try {
					
					con.close();
	
				} catch (SQLException e) {
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_FATAL, e
									.getClass().getName(), e.getMessage()));
	
					e.printStackTrace();
				}
			}
		
//		}else{
//			FacesContext.getCurrentInstance().addMessage(
//					null,
//					new FacesMessage(FacesMessage.SEVERITY_WARN, "Existe una sesion abierta en tu navegador","Salga de la sesión anterior y vuelva a intentarlo"));
//			RequestContext.getCurrentInstance().execute("alert('Existe una sesion abierta en tu navegador.')");
//			//FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
//		}
		return pasaNoPasa;
	}
	
	private boolean validaPersonal(int loginId, Connection con)
			throws SQLException {
		boolean bandera = false;
		String sQueryPersonal = "SELECT  s.personal_id,\n"
				+ "s.given_names,\n" + "s.surnames,\n" 				
				+ "s.from_login_id,\n" + "s.rol_id,\n" + "s.segment1,\n"
				+ "s.segment2,\n" + "s.segment3,\n" + "s.segment4,\n"
				+ "s.segment5,\n" + "s.segment6,\n" + "s.segment7,\n"
				+ "s.segment8,\n" + "s.creation_date,\n" + "s.last_update_date\n"
				+ "FROM personal s,roles r\n"
				+ "WHERE s.rol_id = r.rol_id AND from_login_id = ?";
//		logger.info("Query estudiantes " + sQueryPersonal);
		PreparedStatement psmt = con.prepareStatement(sQueryPersonal);
		psmt.setInt(1, loginId);
		ResultSet rs = psmt.executeQuery();
		String nomPto = null;
		while (rs.next()) {
			personalBean = new PersonalBean();
			personalBean.setPersonalId(rs.getInt("personal_id"));
			personalBean.setGivenNames(rs.getString("given_names"));
			personalBean.setSurNames(rs.getString("surnames"));			
			personalBean.setFromLoginId(rs.getInt("from_login_id"));
			personalBean.setRolId(rs.getInt("rol_id"));
			personalBean.setSegment1(rs.getString("segment1"));
			personalBean.setSegment2(rs.getString("segment2"));
			personalBean.setSegment3(rs.getString("segment3"));
			personalBean.setSegment4(rs.getString("segment4"));
			personalBean.setSegment5(rs.getString("segment5"));
			personalBean.setSegment6(rs.getInt("segment6"));
			personalBean.setSegment7(rs.getDate("segment7"));
			personalBean.setSegment8(rs.getBlob("segment8"));
			personalBean.setCreationDate(rs.getDate("creation_date"));
			personalBean.setEndDate(rs.getDate("last_update_date"));

			session.setAttribute("personalBean", personalBean);
			nomPto = proyectDAO.obtenNomPto(personalBean.getSegment6());
			session.setAttribute("nomPto", nomPto);
			bandera = true;
		}

		return bandera;
	}
	
	public String salir() {

		PersonalBean personalBean = (PersonalBean) session.getAttribute("personalBean");
				String updateActive = "UPDATE login \n" + "SET id_status = 1 \n"
				+ "WHERE  login_id = ?";
		PreparedStatement psmt = null;
		try {
			con = conectionWrapper.getConexion();
			psmt = con.prepareStatement(updateActive);
			psmt.setInt(1, personalBean.getFromLoginId());
			psmt.executeUpdate();
		} catch (SQLException | NamingException e) {
			logger.error(e);
		} finally {
			try {
				psmt.close();
				con.close();
			} catch (SQLException e) {
				logger.error(e);
			}
		}

		logger.debug("Session de " + personalBean.getGivenNames()
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

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getBeforePassword() {
		return beforePassword;
	}

	public void setBeforePassword(String beforePassword) {
		this.beforePassword = beforePassword;
	}
	
	
	
}

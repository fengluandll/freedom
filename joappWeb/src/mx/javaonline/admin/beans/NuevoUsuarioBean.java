package mx.javaonline.admin.beans;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.primefaces.event.SelectEvent;

import com.mysql.jdbc.jdbc2.optional.ConnectionWrapper;
import com.mysql.jdbc.jdbc2.optional.PreparedStatementWrapper;

import mx.javaonline.beans.ArbolCursosBean;
import mx.javaonline.beans.LoginBean;
import mx.javaonline.beans.PreRegistroBean;
import mx.javaonline.daos.PreRegistroDAO;
import mx.javaonline.model.ConectionWrapper;
import mx.javaonline.model.CursosBeanTableModel;
import mx.javaonline.util.FuncionesGrales;
import mx.javaonline.util.ManejoFechas;

public class NuevoUsuarioBean implements Serializable{

	private int studentPersonalId;
	private String numCta;
	private String firstNames;
	private String lastNames;
	private int age;
	private String maritalStatus;
	private String phone;
	private String mail;
	private String country;
	private String city;
	private String state;
	private int fromLoginId;
	private int rolId;
	private String segment1;
	private String segment2;
	private String segment3;
	private String segment4;
	private String segment5;
	private int segment6;
	private java.sql.Date segment7;
	private Blob segment8;
	private java.sql.Date creationDate;
	private java.sql.Date endDate;
	private int idPReRegistro;
	private Map<String,Integer> roles = new HashMap<String, Integer>();
	private Map<String,Integer> pagos = new HashMap<String, Integer>();
	private boolean prueba;
	private boolean prueba2;
	private int formPago;
	private String password;
	private String confirmPassword;
	
	ConectionWrapper conectionWrapper;
	Connection con;
	FacesContext facesContext = FacesContext.getCurrentInstance();
	List<PreRegistroBean> listPreResgistro;
	PreRegistroDAO preRegistroDAO;
	PreRegistroBean selectedPre;
	private static org.apache.log4j.Logger logger = Logger.getLogger(NuevoUsuarioBean.class);
	HttpSession session;
	ResourceBundle bundle = ResourceBundle.getBundle("DatosGenerales");
	
	public NuevoUsuarioBean(){
		session = (HttpSession) facesContext.getExternalContext().getSession(true);
		getRolesDB();
		getFormaPago();
//		preRegistroDAO = new PreRegistroDAO();
//		getPreRegistros();
		
	}
/*	
	public void getPreRegistros(){
		listPreResgistro = preRegistroDAO.getPreResgistros();
	}
*/	
	public void onRowSelect(SelectEvent event) {
		PreRegistroBean preRegistroBean = (PreRegistroBean) event.getObject();
		
		this.firstNames      = preRegistroBean.getNombres();
		this.lastNames       = preRegistroBean.getApellidos();
		SimpleDateFormat sdt = new SimpleDateFormat("dd/MM/yyyy");
		String fecNaci       = sdt.format(preRegistroBean.getFechaNacimiento());
		this.segment2        = fecNaci;
		this.mail            = preRegistroBean.getCorreoPrincipal();
		this.idPReRegistro   = preRegistroBean.getIdPreegistro();
    }
	
	private void getFormaPago(){
		conectionWrapper = new ConectionWrapper();
		try {
			String sql = "SELECT id_tipo_pagos,tipo_pago FROM tipo_pagos";
			con = conectionWrapper.getConexion();
			PreparedStatement psmt = con.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			int idTipoPago = 0;
			String tipoPago = "";
			while(rs.next()){
				idTipoPago = rs.getInt(1);
				tipoPago = rs.getString(2);
				pagos.put(tipoPago,idTipoPago);
			}
			
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				con.close();
			} catch (SQLException e) {
				logger.error(e);
			}
		}
	}
	private void getRolesDB(){
		conectionWrapper = new ConectionWrapper();
		try {
			String sql = "SELECT rol_id,rol_name FROM roles";
			con = conectionWrapper.getConexion();
			PreparedStatement psmt = con.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			int rolId = 0;
			String rolName = "";
			while(rs.next()){
				rolId = rs.getInt(1);
				rolName = rs.getString(2);
				roles.put(rolName,rolId);
			}
			
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				con.close();
			} catch (SQLException e) {
				logger.error(e);
			}
		}
	}
	
	public String guardarUsuarioPlus(){
		/*logger.debug("mail "+mail);
		
		logger.debug("givenNames "+firstNames);
		logger.debug("surNames "+lastNames);
		logger.debug("segment2 "+city);
		
		logger.debug("country "+password);
		logger.debug("estado "+confirmPassword);*/
		
		conectionWrapper = new ConectionWrapper();
		String bandera = "";
		DecimalFormat df = new DecimalFormat("0000");
		int sequencia = 0;
		String nomRol = "";
		try {
			String sql = "select MAX(seq_st),anio from sequence_control";
			String upd1 = "UPDATE sequence_control SET seq_st = ?";
			String sql2 = "select MAX(seq_pe),anio from sequence_control";
			String upd2 = "UPDATE sequence_control SET seq_pe = ?";
			String sqlRol = "SELECT rol_name FROM roles  WHERE rol_id = ?";
			con = conectionWrapper.getConexion();
			con.setAutoCommit(false);
			PreparedStatement psmt = null;
			PreparedStatement psmt2 = null;
			psmt2 = con.prepareStatement(sqlRol);
			psmt2.setInt(1, rolId);
			psmt = con.prepareStatement(sql);
			ResultSet rs  = psmt.executeQuery();
			ResultSet rs2 = psmt2.executeQuery();
			nomRol = "";
			while(rs2.next()){
				nomRol = rs2.getString(1);
			}
			
			while(rs.next()){
				sequencia = rs.getInt(1);
			}
			psmt.close();
			
			//psmt = con.prepareStatement(upd1);
			
			//psmt.setInt(1, sequencia + 1);
			//psmt.executeUpdate();
			con.setAutoCommit(true);
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				logger.error(e);
			}
		}
		
		
		
		String user = mail;
		
		SimpleDateFormat sdf = new SimpleDateFormat("YY");
		String anio = sdf.format(new Date());
		String seq = df.format(++sequencia);
		String numCta = "";
		String indenti = "";
		java.sql.Date fecFin = null;
		numCta = "ST-"+seq+"-"+anio;
		indenti = "S";
		
		if(FuncionesGrales.validateEmail(mail)){
			 
			
			if(!this.password.equals(this.confirmPassword)) {
				FuncionesGrales.mostrarMensaje("Ups Algo salio mal", "Ambas contraseñas deben de coincidir, vuelve a intentarlo", "ERROR");
				return bandera;
			}
			
			if(existeMailBD(user) == false){
				String pass = this.confirmPassword;//FuncionesGrales.generaContrasena(mail);
				if(insertNewUserIndividual(user,pass,numCta,indenti,Integer.parseInt(seq))){
					
//					preRegistroDAO.updateStatus(this.idPReRegistro);
					
					session.removeAttribute("surnames");
					session.removeAttribute("user");
					session.removeAttribute("numCta");
					session.removeAttribute("nomRol");
					session.removeAttribute("pass");
					
					session.setAttribute("surnames", firstNames + " " +lastNames);
					session.setAttribute("user", user);
					session.setAttribute("numCta", numCta);
					session.setAttribute("nomRol", nomRol);
					session.setAttribute("pass", pass);
					bandera = passStudent(user,pass);
				}
			}else{
				
				FuncionesGrales.mostrarMensaje("Ups Algo salio mal", "El correo que capturaste ya existe,por favor elije otro", "ERROR");
			}
		}else{
			/*FacesContext.getCurrentInstance().addMessage(null,
					   new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"El correo no es valido,favor de validar","Validacion"));*/
			FuncionesGrales.mostrarMensaje("Ups Algo salio mal", "El correo no es valido,favor de validar", "ERROR");
		}
		
				
				
		return bandera;
		
			}
	
	/**
	 * Metodo traido del loginBean, modificar en los dos
	 * @return regresa el flujo a donde se va a ir
	 */
	public String passStudent(String user,String password) {
		String paNoPasa = "NoPasa";
		LoginBean loginBean = new LoginBean();
		
		try {
			conectionWrapper = new ConectionWrapper();
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
				boolean ai = loginBean.consultaActivoInactivo(loginId, con);
				if (ai) {// Reviso si el usuario esta activo

					if (loginBean.validaStudiante(loginId, con)) {

						loginBean.updateLastLogin(loginId, con);
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
	
	/**
	 * @deprecated
	 * @return
	 */
	public String guardarUsuario(){
/*		logger.debug("givenNames "+givenNames);
		logger.debug("surNames "+surNames);
		logger.debug("segment2 "+segment2);
		logger.debug("country "+country);
		logger.debug("estado "+state);
		logger.debug("city "+city);
		logger.debug("mainl "+mail);
		logger.debug("phone "+phone);
		logger.debug("roles "+rolId);*/
		conectionWrapper = new ConectionWrapper();
		String bandera = "";
		DecimalFormat df = new DecimalFormat("0000");
		int sequencia = 0;
		String nomRol = "";
		try {
			String sql = "select MAX(seq_st),anio from sequence_control";
			String upd1 = "UPDATE sequence_control SET seq_st = ?";
			String sql2 = "select MAX(seq_pe),anio from sequence_control";
			String upd2 = "UPDATE sequence_control SET seq_pe = ?";
			String sqlRol = "SELECT rol_name FROM roles  WHERE rol_id = ?";
			con = conectionWrapper.getConexion();
			con.setAutoCommit(false);
			PreparedStatement psmt = null;
			PreparedStatement psmt2 = null;
			psmt2 = con.prepareStatement(sqlRol);
			psmt2.setInt(1, rolId);
			if(rolId == 1){
				psmt = con.prepareStatement(sql);
			}else if(rolId == 2 || rolId == 3){
				psmt = con.prepareStatement(sql2);
			}
			ResultSet rs  = psmt.executeQuery();
			ResultSet rs2 = psmt2.executeQuery();
			nomRol = "";
			while(rs2.next()){
				nomRol = rs2.getString(1);
			}
			
			while(rs.next()){
				sequencia = rs.getInt(1);
			}
			psmt.close();
			if(rolId == 1){
				psmt = con.prepareStatement(upd1);
			}else if(rolId == 2 || rolId == 3){
				psmt = con.prepareStatement(upd2);
			}
			psmt.setInt(1, sequencia + 1);
			psmt.executeUpdate();
			con.setAutoCommit(true);
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				logger.error(e);
			}
		}
		
		
		
		String user = mail;
		
		SimpleDateFormat sdf = new SimpleDateFormat("YY");
		String anio = sdf.format(new Date());
		String seq = df.format(sequencia);
		String numCta = "";
		String indenti = "";
		java.sql.Date fecFin = null;
		if(rolId == 1){
			numCta = "ST-"+seq+"-"+anio;
			indenti = "S";
		}else if(rolId == 3){
			numCta = "PR-"+seq+"-"+anio;
			indenti = "P";
		}else if(rolId == 2){
			numCta = "AD-"+seq+"-"+anio;
			indenti = "A";
		}
		if(prueba){
			 fecFin = ManejoFechas.sumarFechasDias(new java.sql.Date(new java.util.Date().getTime()),15); 
		}else if(prueba2){
			 fecFin = ManejoFechas.sumarFechasDias(new java.sql.Date(new java.util.Date().getTime()),31); 
		}else{
			Date fec = ManejoFechas.deStringToDate("9999-12-31");
			fecFin = new java.sql.Date(fec.getTime());
		}
/*
		logger.debug(user);
		logger.debug(numCta);
		logger.debug(indenti);
		logger.debug(nomRol);
		logger.debug(fecFin);
*/		
		if(FuncionesGrales.validateEmail(mail)){
			 
			String pass = FuncionesGrales.generaContrasena(mail);
			logger.debug("pass "+pass);
			if(existeMailBD(user) == false){
				if(insertNewUser(user,pass,fecFin,numCta,indenti)){
					
//					preRegistroDAO.updateStatus(this.idPReRegistro);
					
					session.removeAttribute("surnames");
					session.removeAttribute("user");
					session.removeAttribute("numCta");
					session.removeAttribute("nomRol");
					session.removeAttribute("pass");
					
					session.setAttribute("surnames", firstNames + " " +lastNames);
					session.setAttribute("user", user);
					session.setAttribute("numCta", numCta);
					session.setAttribute("nomRol", nomRol);
					session.setAttribute("pass", pass);
					bandera = "success";
				}
			}else{
				FacesContext.getCurrentInstance().addMessage(null,
						   new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"El correo que capturaste ya existe,por favor elije otro","Validacion"));
			}
		}else{
			FacesContext.getCurrentInstance().addMessage(null,
					   new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"El correo no es valido,favor de validar","Validacion"));
		}
		
		
		return bandera;
	}
	
	private boolean existeMailBD(String user){
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
	
	private boolean insertNewUserIndividual(String user,String pass,String numCta,String indenti,int seq){
		conectionWrapper = new ConectionWrapper();
		boolean pasa = false;
		try {
			con = conectionWrapper.getConexion();
			CallableStatement cst = con.prepareCall("{CALL insert_new_user_web_pr(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			cst.setString(1, user);
			cst.setString(2, pass);
			cst.setString(3, numCta);
			cst.setString(4, firstNames);
			cst.setString(5, lastNames);
			cst.setString(6, mail);
			cst.setString(7, city);
			cst.setInt(8, 1);
			cst.setString(9, indenti);
			cst.registerOutParameter(10, Types.INTEGER);
			cst.registerOutParameter(11, Types.VARCHAR);
			cst.setString(12, segment2);
			cst.setInt(13, seq);
			cst.execute();
			int stuPerId = cst.getInt(10);
			String tipoUsuario = cst.getString(11);
			
			session.removeAttribute("stuPerId");
			session.removeAttribute("tipoUsuario");
			session.setAttribute("stuPerId", stuPerId);
			session.setAttribute("tipoUsuario", tipoUsuario);
		    pasa = true;
			
			
		} catch (NamingException | SQLException e) {
			logger.error(e);
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				logger.error(e);
			}
		}
		return pasa;
	}
	
	private boolean insertNewUser(String user,String pass,java.sql.Date fecFin,String numCta,String indenti){
		conectionWrapper = new ConectionWrapper();
		boolean pasa = false;
		try {
			con = conectionWrapper.getConexion();
			CallableStatement cst = con.prepareCall("{CALL insert_new_user_pr(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			cst.setString(1, user);
			cst.setString(2, pass);
			cst.setDate(3, fecFin);
			cst.setString(4, numCta);
			cst.setString(5, firstNames);
			cst.setString(6, lastNames);
			cst.setInt(7, ManejoFechas.dameEdad(segment2));
			cst.setString(8, null);
			cst.setString(9, phone);
			cst.setString(10, mail);
			cst.setString(11, country);
			cst.setString(12, city);
			cst.setString(13, state);
			cst.setInt(14, rolId);
			cst.setString(15, indenti);
			cst.registerOutParameter(16, Types.INTEGER);
			cst.registerOutParameter(17, Types.VARCHAR);
			cst.setString(18, segment2);
			cst.setInt(19, formPago);
			cst.execute();
			int stuPerId = cst.getInt(16);
			String tipoUsuario = cst.getString(17);
			
			session.removeAttribute("stuPerId");
			session.removeAttribute("tipoUsuario");
			session.setAttribute("stuPerId", stuPerId);
			session.setAttribute("tipoUsuario", tipoUsuario);
		    pasa = true;
			
			
		} catch (NamingException | SQLException e) {
			logger.error(e);
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				logger.error(e);
			}
		}
		return pasa;
	}
	
	public int getStudentPersonalId() {
		return studentPersonalId;
	}
	
	public void setStudentPersonalId(int studentPersonalId) {
		this.studentPersonalId = studentPersonalId;
	}
	public String getNumCta() {
		return numCta;
	}
	public void setNumCta(String numCta) {
		this.numCta = numCta;
	}
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
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
	public int getFromLoginId() {
		return fromLoginId;
	}
	public void setFromLoginId(int fromLoginId) {
		this.fromLoginId = fromLoginId;
	}
	public int getRolId() {
		return rolId;
	}
	public void setRolId(int rolId) {
		this.rolId = rolId;
	}
	public String getSegment1() {
		return segment1;
	}
	public void setSegment1(String segment1) {
		this.segment1 = segment1;
	}
	public String getSegment2() {
		return segment2;
	}
	public void setSegment2(String segment2) {
		this.segment2 = segment2;
	}
	public String getSegment3() {
		return segment3;
	}
	public void setSegment3(String segment3) {
		this.segment3 = segment3;
	}
	public String getSegment4() {
		return segment4;
	}
	public void setSegment4(String segment4) {
		this.segment4 = segment4;
	}
	public String getSegment5() {
		return segment5;
	}
	public void setSegment5(String segment5) {
		this.segment5 = segment5;
	}
	public int getSegment6() {
		return segment6;
	}
	public void setSegment6(int segment6) {
		this.segment6 = segment6;
	}
	public java.sql.Date getSegment7() {
		return segment7;
	}
	public void setSegment7(java.sql.Date segment7) {
		this.segment7 = segment7;
	}
	public Blob getSegment8() {
		return segment8;
	}
	public void setSegment8(Blob segment8) {
		this.segment8 = segment8;
	}
	public java.sql.Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(java.sql.Date creationDate) {
		this.creationDate = creationDate;
	}
	public java.sql.Date getEndDate() {
		return endDate;
	}
	public void setEndDate(java.sql.Date endDate) {
		this.endDate = endDate;
	}

	public Map<String, Integer> getRoles() {
		return roles;
	}

	public void setRoles(Map<String, Integer> roles) {
		this.roles = roles;
	}

	public boolean isPrueba() {
		return prueba;
	}

	public void setPrueba(boolean prueba) {
		this.prueba = prueba;
	}

	public List<PreRegistroBean> getListPreResgistro() {
		return listPreResgistro;
	}

	public void setListPreResgistro(List<PreRegistroBean> listPreResgistro) {
		this.listPreResgistro = listPreResgistro;
	}

	public PreRegistroBean getSelectedPre() {
		return selectedPre;
	}

	public void setSelectedPre(PreRegistroBean selectedPre) {
		this.selectedPre = selectedPre;
	}

	public int getIdPReRegistro() {
		return idPReRegistro;
	}

	public void setIdPReRegistro(int idPReRegistro) {
		this.idPReRegistro = idPReRegistro;
	}

	public boolean isPrueba2() {
		return prueba2;
	}

	public void setPrueba2(boolean prueba2) {
		this.prueba2 = prueba2;
	}

	public int getFormPago() {
		return formPago;
	}

	public void setFormPago(int formPago) {
		this.formPago = formPago;
	}

	public Map<String, Integer> getPagos() {
		return pagos;
	}

	public void setPagos(Map<String, Integer> pagos) {
		this.pagos = pagos;
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
	public String getFirstNames() {
		return firstNames;
	}
	public void setFirstNames(String firstNames) {
		this.firstNames = firstNames;
	}
	public String getLastNames() {
		return lastNames;
	}
	public void setLastNames(String lastNames) {
		this.lastNames = lastNames;
	}

	
	
}

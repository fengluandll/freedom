
package mx.javaonline.admin.beans;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import mx.javaonline.beans.StudentPersonalBean;
import mx.javaonline.daos.ReenviarCorreoDAO;
import mx.javaonline.util.FuncionesGrales;
import mx.javaonline.util.ManejoFechas;
import mx.javaonline.util.mail.SendMails;

import org.apache.log4j.Logger;
import org.primefaces.event.SelectEvent;

public class ReenviarCorreoBienveBean {

	FacesContext facesContext = FacesContext.getCurrentInstance();
	HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
	private static org.apache.log4j.Logger logger = Logger.getLogger(ReenviarCorreoBienveBean.class);
	private String nombres;
	private String apellidos;
	private Date fecIni;
	private Date fecFin;
	private String noCta;
	private String mail;
	private int id;
	private int fromLoginId;
	private String segment1;
	private int rolId;
	private String pass;
	private String disabled;
	
	List<StudentPersonalBean> listAlumn;
	ReenviarCorreoDAO reenviarCorreoDAO;
	StudentPersonalBean selectedAlum;

	public ReenviarCorreoBienveBean() {
		reenviarCorreoDAO = new ReenviarCorreoDAO();
		disabled = "true";
	}
	
	public void buscar(){
		logger.info("nombres "+ nombres);
		logger.info("apellidos "+ apellidos);
		logger.info("fecIni "+ fecIni);
		logger.info("fecFin "+ fecFin);
		if((fecIni == null && fecFin != null) || (fecIni != null && fecFin == null)){
			FacesContext.getCurrentInstance().addMessage(null,
					   new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Ups!","Debes seleccionar ambas fechas"));
		}else if(fecIni != null && fecFin != null){
			String sFecIni = ManejoFechas.dateToString(fecIni);
			String sFecFin = ManejoFechas.dateToString(fecFin);
			
			listAlumn = reenviarCorreoDAO.buscaAlumno(nombres,apellidos,sFecIni,sFecFin);
		}else if(fecIni == null && fecFin == null){
			listAlumn = reenviarCorreoDAO.buscaAlumno(nombres,apellidos,"","");
		}
		
		
		
	}


	public void onRowSelect(SelectEvent event){
		StudentPersonalBean studentPersonalBean = (StudentPersonalBean)event.getObject();
		logger.info("id "+studentPersonalBean.getStudentPersonalId());
		this.mail  	   = studentPersonalBean.getMail();
		this.noCta 	   = studentPersonalBean.getNumCta();
		this.nombres   = studentPersonalBean.getGivenNames();
		this.apellidos = studentPersonalBean.getSurNames();
		this.id        = studentPersonalBean.getStudentPersonalId();
		this.fromLoginId = studentPersonalBean.getFromLoginId();
		this.segment1    = studentPersonalBean.getSegment1();
		this.rolId       = studentPersonalBean.getRolId();
	}
	
	public void onRowUnselect(SelectEvent event){
		StudentPersonalBean studentPersonalBean = (StudentPersonalBean)event.getObject();
		logger.info("ID "+studentPersonalBean.getStudentPersonalId());
	}
	
	public void modificaCorreo(){
		boolean pasa = reenviarCorreoDAO.existeMailBD(this.mail);
		if(!pasa){
			this.pass = FuncionesGrales.generaContrasena(this.mail);
			boolean bandera = reenviarCorreoDAO.guardaCorreo(this.mail, this.fromLoginId, this.segment1,this.pass);
			if(bandera){
				FacesContext.getCurrentInstance().addMessage(null,
						   new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Exito","El correo se guardo exitosamente"));
			}else{
				FacesContext.getCurrentInstance().addMessage(null,
						   new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Ups!","Algo salio mal en la actualización del correo"));
			}
		}else{
			FacesContext.getCurrentInstance().addMessage(null,
					   new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Ups!","El usuario ya existe en la base de datos"));
		}
	
		
	}
	
	public void enviaCorreo(){
		
		List<String> listCourses = reenviarCorreoDAO.getCourses(this.id, this.segment1);
		StringBuilder sb = new StringBuilder();
		for(String s : listCourses){
			sb.append(s);
			sb.append("\n");
		}
		String spass = "";
		if(this.pass == null){
			spass = FuncionesGrales.generaContrasena(this.mail);
			reenviarCorreoDAO.planchaPassword(fromLoginId, spass);
		}
		
		session.removeAttribute("surnames");
		session.removeAttribute("user");
		session.removeAttribute("numCta");
		session.removeAttribute("nomRol");
		session.removeAttribute("pass");
		session.removeAttribute("allCourses");
		session.setAttribute("surnames", this.nombres + " " +this.apellidos);
		session.setAttribute("user", this.mail);
		session.setAttribute("numCta", this.noCta);
		session.setAttribute("nomRol", reenviarCorreoDAO.getRol(this.rolId));
		session.setAttribute("pass", this.pass==null?spass:this.pass);
		session.setAttribute("allCourses", sb.toString());
		SendMails sendMails = new SendMails();
		sendMails.enviaMailBienvenida();
	}
	
	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Date getFecIni() {
		return fecIni;
	}

	public void setFecIni(Date fecIni) {
		this.fecIni = fecIni;
	}

	public Date getFecFin() {
		return fecFin;
	}

	public void setFecFin(Date fecFin) {
		this.fecFin = fecFin;
	}

	public List<StudentPersonalBean> getListAlumn() {
		return listAlumn;
	}

	public void setListAlumn(List<StudentPersonalBean> listAlumn) {
		this.listAlumn = listAlumn;
	}

	public StudentPersonalBean getSelectedAlum() {
		return selectedAlum;
	}

	public void setSelectedAlum(StudentPersonalBean selectedAlum) {
		this.selectedAlum = selectedAlum;
	}

	public String getNoCta() {
		return noCta;
	}

	public void setNoCta(String noCta) {
		this.noCta = noCta;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFromLoginId() {
		return fromLoginId;
	}

	public void setFromLoginId(int fromLoginId) {
		this.fromLoginId = fromLoginId;
	}

	public String getSegment1() {
		return segment1;
	}

	public void setSegment1(String segment1) {
		this.segment1 = segment1;
	}

	public int getRolId() {
		return rolId;
	}

	public void setRolId(int rolId) {
		this.rolId = rolId;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}



	
	
	

}

package mx.javaonline.admin.beans;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import mx.javaonline.beans.StudentPersonalBean;
import mx.javaonline.controlador.FileUploadController;
import mx.javaonline.daos.ActualizaDatosDAO;
import mx.javaonline.util.FuncionesGrales;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.primefaces.event.FileUploadEvent;

public class ActualizaDatosBean implements Serializable{
	

	private static final long serialVersionUID = 1L;
	private static org.apache.log4j.Logger logger = Logger.getLogger(ActualizaDatosBean.class);
	HttpSession session;
	FacesContext facesContext;
	
	private String noCuenta;
	private String nombres;
	private String apellidos;
	private String mail;
	private String fecNacimiento;
	private String dia;
	private String mes;
	private String anio;
	private String rolName;
	private String city;
	StudentPersonalBean studentPersonalBean;
	private List<String> listDays = Arrays.asList("01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16",
												  "17","18","19","20","21","22","23","24","25","26","27","28","29","30","31");
	private List<String> listMonths = Arrays.asList("Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto",
													"Septiembre","Octubre","Noviembre","Diciembre");
	private List<Integer> listYears;
	ActualizaDatosDAO actualizaDatosDAO;


	public ActualizaDatosBean() {
		facesContext = FacesContext.getCurrentInstance();
		session = (HttpSession) facesContext.getExternalContext().getSession(true);
		listYears = new ArrayList<>();
		obtenStudentPersonal();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String anio = sdf.format(new Date());
		int iAnio = Integer.parseInt(anio);
		for(int i = 1; i < 100; i++){
			iAnio -- ;
			listYears.add(iAnio);
		}
			
	}
	
	public void obtenStudentPersonal(){
		StudentPersonalBean studentPersonalBean = (StudentPersonalBean)session.getAttribute("studentPersonalBean");
		this.noCuenta  = studentPersonalBean.getNumCta();
		this.nombres   = studentPersonalBean.getGivenNames();
		this.apellidos = studentPersonalBean.getSurNames();
		this.mail      = studentPersonalBean.getMail();
		this.rolName   = studentPersonalBean.getRolName();
		this.city	   = studentPersonalBean.getCity();
		//String fecNac = studentPersonalBean.getSegment2();
		//String[] arr = StringUtils.split(fecNac, '/');
		
		
		/*for(int i = 0;i < arr.length;i++){
			this.dia  = arr[0];
			this.mes  = FuncionesGrales.numeroaMes(arr[1]);
			this.anio = arr[2];
		}*/
		
		
	}
	
	public void actualizaDatos(){
		
		
		actualizaDatosDAO = new ActualizaDatosDAO();
		//String fecNaci = dia+"/"+FuncionesGrales.mesaNumero(mes)+"/"+anio;
		boolean ok = FuncionesGrales.validateEmail(mail);
		if(ok){
			boolean bandera = actualizaDatosDAO.acualizaDatos(nombres,apellidos,mail,city);
			if(bandera){
				StudentPersonalBean studentPersonalBean = (StudentPersonalBean)session.getAttribute("studentPersonalBean");
				studentPersonalBean.setGivenNames(nombres);
				studentPersonalBean.setSurNames(apellidos);
				FacesContext.getCurrentInstance().addMessage(null,
						   new FacesMessage(FacesMessage.SEVERITY_INFO,
							"BIEN","Datos actualizados,entra de nuevo al sistema para ver tus cambios"));
			}else{
				FacesContext.getCurrentInstance().addMessage(null,
						   new FacesMessage(FacesMessage.SEVERITY_FATAL,
							"Ups","Error al actualizar tus datos"));
			}
		}else{
			FacesContext.getCurrentInstance().addMessage(null,
					   new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Ups","Ingrese un correo valido ej. samuel@gmail.com"));
		}
		
		
	}
	
	public void actualizaImagen(FileUploadEvent event){
		FileUploadController controller = new FileUploadController();
		String extension = StringUtils.substringAfter(event.getFile().getFileName(), ".");
		FileInputStream finput;
		try {
			finput = (FileInputStream) event.getFile().getInputstream();
			boolean bandera = controller.subeImagen(finput,extension);
			if(bandera){
				FacesContext.getCurrentInstance().addMessage(null,
						   new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Exito",event.getFile().getFileName() + " Se cargo correctamente."));
		
			}else{
				FacesContext.getCurrentInstance().addMessage(null,
						   new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Ups",event.getFile().getFileName() + " Error al cargar la imagen"));
			}
		} catch (IOException e) {
			logger.error(e);
		}
		
	}

	public String getNoCuenta() {
		return noCuenta;
	}

	public void setNoCuenta(String noCuenta) {
		this.noCuenta = noCuenta;
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

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getFecNacimiento() {
		return fecNacimiento;
	}

	public void setFecNacimiento(String fecNacimiento) {
		this.fecNacimiento = fecNacimiento;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

	public List<String> getListDays() {
		return listDays;
	}

	public void setListDays(List<String> listDays) {
		this.listDays = listDays;
	}

	public List<String> getListMonths() {
		return listMonths;
	}

	public void setListMonths(List<String> listMonths) {
		this.listMonths = listMonths;
	}

	public List<Integer> getListYears() {
		return listYears;
	}

	public void setListYears(List<Integer> listYears) {
		this.listYears = listYears;
	}

	public String getRolName() {
		return rolName;
	}

	public void setRolName(String rolName) {
		this.rolName = rolName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	

}

package mx.javaonline.admin.beans;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import mx.javaonline.beans.CoursesBean;
import mx.javaonline.beans.StudentPersonalBean;
import mx.javaonline.beans.UnitsBean;
import mx.javaonline.daos.AyudaDAO;
import mx.javaonline.util.mail.SendMails;

public class PreguntaTemaBean implements Serializable{
	

	private static final long serialVersionUID = 1L;
	FacesContext facesContext = FacesContext.getCurrentInstance();
	HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
	private org.apache.log4j.Logger logger = Logger.getLogger(PreguntaTemaBean.class);
	private int courseId;
	private int unitId;
	private int topicId;
	private String pregunta;
	private String readOnly;
	private List<CoursesBean> listCourses;
	private List<UnitsBean> listUnits;
	private List<TopicsBean> listTopics;
	AyudaDAO ayudaDAO;
	private UploadedFile file;
	private UploadedFile file2;
	private UploadedFile file3;
	private UploadedFile file4;
	private UploadedFile file5;

	public PreguntaTemaBean() {
		
		ayudaDAO = new AyudaDAO();
		StudentPersonalBean studentPersonalBean = (StudentPersonalBean)session.getAttribute("studentPersonalBean");
		listCourses = ayudaDAO.getCourses(studentPersonalBean.getStudentPersonalId(),studentPersonalBean.getSegment1());
		readOnly = "true";
	}
	
	public void handleChange(){  
		listUnits = ayudaDAO.getUnits(this.courseId);
	}
	
	public void handleChangeUnit(){
		logger.debug("New value: " + this.unitId);
		listTopics = ayudaDAO.getTopics(this.unitId);
	}
	
	public void handleChangeTopic(){
		logger.debug("New value: " + this.topicId);
		readOnly = "false";
	}
	
	public void enviaPregunta(){
		logger.debug("Pregunta "+this.pregunta);
		if(this.courseId == 0 || this.unitId == 0 || this.topicId == 0 || this.pregunta.equals("")){
			FacesContext.getCurrentInstance().addMessage(null,
					   new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Ups!","Debes llenar todos los campos Curso,Unidad,Tema y Pregunta"));
		}else{
			/**
			 * Se crea un arrayList para ir agregando los nombres de los archivos
			 * con el fin de iterarlo y pasarlo a un arreglo de string para mandarselos al correo
			 */
			List<String> listFilesName = new ArrayList<>();
			int count = 0;
			if(this.file.getSize() > 0){
				count++;
				listFilesName.add("1_"+this.file.getFileName());
			}
			if(this.file2.getSize() > 0){
				count++;
				listFilesName.add("2_"+this.file2.getFileName());
			}
			if(this.file3.getSize() > 0){
				count++;
				listFilesName.add("3_"+this.file3.getFileName());
			}
			if(this.file4.getSize() > 0){
				count++;
				listFilesName.add("4_"+this.file4.getFileName());
			}
			if(this.file5.getSize() > 0){
				count++;
				listFilesName.add("5_"+this.file5.getFileName());
			}
			//Valida que los formatos sean los correctos
			if(count > 0){
				String ext  = StringUtils.substringAfter(file.getFileName(), ".").toUpperCase();
				String ext2 = StringUtils.substringAfter(file2.getFileName(), ".").toUpperCase();
				String ext3 = StringUtils.substringAfter(file3.getFileName(), ".").toUpperCase();
				String ext4 = StringUtils.substringAfter(file4.getFileName(), ".").toUpperCase();
				String ext5 = StringUtils.substringAfter(file5.getFileName(), ".").toUpperCase();
				if((ext.equals("JPG")  || ext.equals("GIF") || ext.equals("PNG")  ||  ext.equals("") ) &&
				   (ext2.equals("JPG") || ext2.equals("GIF") || ext2.equals("PNG") || ext2.equals("") )&&
				   (ext3.equals("JPG") || ext3.equals("GIF") || ext3.equals("PNG") || ext3.equals("")) &&
				   (ext4.equals("JPG") || ext4.equals("GIF") || ext4.equals("PNG") || ext4.equals("")) &&
				   (ext5.equals("JPG") || ext5.equals("GIF") || ext5.equals("PNG") || ext5.equals(""))
				   ){
					
					
				try {
					File tempFile;
					String[] fileNames = new String[count];
					InputStream inStream = null;
					OutputStream salida = null;
					int len;
					byte[] buf;
					String numFile;
					/**Empieza a iterar los nombres y valida de acuerdo a un numero para que dependiendo de este
					 * cree un archivo temporal y lo agregue a un arreglo de String con su ruta absoluta
					 * y se lo mande al correo
					 */
						for(int i = 0; i < listFilesName.size(); i++){
							numFile = StringUtils.substringBefore(listFilesName.get(i),"_");
							if(numFile.equals("1")){
								inStream = file.getInputstream();
							}else if(numFile.equals("2")){
								inStream = file2.getInputstream();
							}else if(numFile.equals("3")){
								inStream = file3.getInputstream();
							}if(numFile.equals("4")){
								inStream = file4.getInputstream();
							}else if(numFile.equals("5")){
								inStream = file5.getInputstream();
							}
						   
						   tempFile = File.createTempFile(listFilesName.get(i),null);//Aqui le dan el nombre y/o con la ruta del archivo salida
						   salida = new FileOutputStream(tempFile);
						   buf =new byte[1024];
						   while((len=inStream.read(buf)) > 0){
						      salida.write(buf,0,len);
						   }
						   salida.close();
						   inStream.close();
						   tempFile.deleteOnExit();
						   fileNames[i] = tempFile.getAbsolutePath();
						}
					enviaCorreo(fileNames);
					} catch (IOException e) {
						logger.debug(e);
					}
					
					
				}else{
					FacesContext.getCurrentInstance().addMessage(null,
							   new FacesMessage(FacesMessage.SEVERITY_WARN,
								"Ups!","El formato de alguno de tus archivos no es JPG, PNG o GIF"));
				}
			}else{
				enviaCorreo(new String[0]);
			}
			
			
			
		}
	}
	
	private void enviaCorreo(String[] fileNames){
		CutBean cutBean = ayudaDAO.getAllCUT(this.courseId, this.unitId, this.topicId);
		SendMails sendMails = new SendMails();
		sendMails.enviaPregunta(cutBean, this.pregunta,fileNames);
		this.courseId = 0;
		this.unitId   = 0;
		this.topicId  = 0;
		this.pregunta = "";
		FacesContext.getCurrentInstance().addMessage(null,
				   new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Bien","Tu pregunta fue enviada,revisa tu correo periodicamente"));
	}
	
	 public void handleFileUpload(FileUploadEvent event) {
	        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
	        FacesContext.getCurrentInstance().addMessage(null, message);
	    }


	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getUnitId() {
		return unitId;
	}

	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}

	public List<CoursesBean> getListCourses() {
		return listCourses;
	}

	public void setListCourses(List<CoursesBean> listCourses) {
		this.listCourses = listCourses;
	}

	public int getTopicId() {
		return topicId;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}

	public List<UnitsBean> getListUnits() {
		return listUnits;
	}

	public void setListUnits(List<UnitsBean> listUnits) {
		this.listUnits = listUnits;
	}

	public List<TopicsBean> getListTopics() {
		return listTopics;
	}

	public void setListTopics(List<TopicsBean> listTopics) {
		this.listTopics = listTopics;
	}

	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	public String getReadOnly() {
		return readOnly;
	}

	public void setReadOnly(String readOnly) {
		this.readOnly = readOnly;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public UploadedFile getFile2() {
		return file2;
	}

	public void setFile2(UploadedFile file2) {
		this.file2 = file2;
	}

	public UploadedFile getFile3() {
		return file3;
	}

	public void setFile3(UploadedFile file3) {
		this.file3 = file3;
	}

	public UploadedFile getFile4() {
		return file4;
	}

	public void setFile4(UploadedFile file4) {
		this.file4 = file4;
	}

	public UploadedFile getFile5() {
		return file5;
	}

	public void setFile5(UploadedFile file5) {
		this.file5 = file5;
	}

	
	
	
}

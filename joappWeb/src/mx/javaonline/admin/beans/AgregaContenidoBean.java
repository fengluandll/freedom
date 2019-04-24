package mx.javaonline.admin.beans;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import mx.javaonline.daos.AgregaContenidoDAO;
import mx.javaonline.model.CursosBeanTableModel;
import mx.javaonline.model.TopicBeanTableModel;
import mx.javaonline.model.UnitsBeanTableModel;
import mx.javaonline.util.FuncionesGrales;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;

public class AgregaContenidoBean implements Serializable {


	private static final long serialVersionUID = 1L;
	AgregaContenidoDAO agregaContenidoDAO;
	List<CursosBeanTableModel> listCourses;
	List<UnitsBeanTableModel>  listUnits;
	List<TopicBeanTableModel>  listTopics;
	CursosBeanTableModel selectedCurso;
	UnitsBeanTableModel selectedUnidad;
	TopicBeanTableModel selectedTopic;
	private int activeIndex;
	private int courseId;
	private String courseName;
	private int unitId;
	private String unitName;
	private int topicId;
	private String topicName;
	private String linkVideo;
	private String linkEvaluacion;
	private java.util.Date creationDate; 
	private List<ColumnModel> columns;
	private List<ColumnModel> columnsUnits;
	private List<ColumnModel> columnsTopics;
	private int ocultaLinkEvaluacion;
	private final static List<String> VALID_COLUMN_KEYS = Arrays.asList("id_curso", "nombre_curso",
																		"fecha_creacion");
	private final static List<String> VALID_COLUMN_KEYS_UTIS = Arrays.asList("unidad_id", "nombre_unidad");
	private final static List<String> VALID_COLUMN_KEYS_TOPICS = Arrays.asList("id_topic", "nombre_topic");
	private static org.apache.log4j.Logger logger = Logger.getLogger(AgregaContenidoBean.class);
	FacesMessage msg;
	FacesContext facesContext = FacesContext.getCurrentInstance();
	HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
	
	private List<VideosBean> listVideos;
	private String nombreVideo;
	private int idVideo;
	private List<LaminasBean> listLaminas;
	private String nombreLamina;
	private int idLamina;
	private List<PracticasBean> listPracticas;
	private String nomArchivoPractia;
	private String nomPractica;
	private String linkVideoPrac;
	private int tipoContentId;
	private Map<String,Integer> mapTipoContenido = new HashMap<String, Integer>();
	private List<SolutionsBean> listSoluciones;
	private String nomArchivoSolucion;
	private String nomSolucion;
	private String linkVideosoluc;
	
	
	
	public AgregaContenidoBean() {
		agregaContenidoDAO = new AgregaContenidoDAO();
		listCourses = agregaContenidoDAO.dameCursosDb();
		createDynamicColumns();
	}
	
	  private void createDynamicColumns() {
	       
	        columns = new ArrayList<ColumnModel>();   
	         
	        for(String columnKey : VALID_COLUMN_KEYS) {
	               columns.add(new ColumnModel(columnKey.toUpperCase(), columnKey));
	        }
	    }
	  
	  private void createDynamicColumnsUnits() {
	       
	        columnsUnits = new ArrayList<ColumnModel>();   
	         
	        for(String columnKey : VALID_COLUMN_KEYS_UTIS) {
	        	columnsUnits.add(new ColumnModel(columnKey.toUpperCase(), columnKey));
	        }
	    }
	  
	  private void createDynamicColumnsTopics() {
	       
		  columnsTopics = new ArrayList<ColumnModel>();   
	         
	        for(String columnKey : VALID_COLUMN_KEYS_TOPICS) {
	        	columnsTopics.add(new ColumnModel(columnKey.toUpperCase(), columnKey));
	        }
	    }
	
	public void onRowSelect(SelectEvent event) {
		CursosBeanTableModel cursosBeanTableModel = (CursosBeanTableModel) event.getObject();
		
		this.courseId     = cursosBeanTableModel.getId_curso();
		this.courseName   = cursosBeanTableModel.getNombre_curso();
		this.creationDate = cursosBeanTableModel.getFecha_creacion();
		listUnits = agregaContenidoDAO.dameUnidadesDb(courseId);
		createDynamicColumnsUnits();
		activeIndex = 1;
//        FacesMessage msg = new FacesMessage("Car Selected", String.valueOf(((CursosBeanTableModel) event.getObject()).getId_curso()));
//        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
	public void onRowSelectUidad(SelectEvent event) {
		UnitsBeanTableModel unitsBeanTableModel = (UnitsBeanTableModel) event.getObject();
		
		this.unitId    = unitsBeanTableModel.getUnidad_id();
		this.unitName   = unitsBeanTableModel.getNombre_unidad();
		createDynamicColumnsTopics();
		listTopics = agregaContenidoDAO.dameTopicsDb(unitId);
		activeIndex = 2;
//        FacesMessage msg = new FacesMessage("Car Selected", String.valueOf(((UnitsBeanTableModel) event.getObject()).getUnidad_id()));
//        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
	public void onRowSelectTopics(SelectEvent event) {
		TopicBeanTableModel topicBeanTableModel = (TopicBeanTableModel) event.getObject();
		
		this.topicId   		  = topicBeanTableModel.getId_topic();
		this.topicName 		  = topicBeanTableModel.getNombre_topic();
//		this.linkVideo 		  = agregaContenidoDAO.obtenContenido(topicId);
		this.linkEvaluacion   = agregaContenidoDAO.obtenLigaEvalPlus(topicId);
		this.listVideos       =  agregaContenidoDAO.dameVideos(topicId);
		this.listLaminas      =  agregaContenidoDAO.dameLaminas(topicId);
		this.listPracticas    =  agregaContenidoDAO.damePracticas(topicId);
		this.listSoluciones   =  agregaContenidoDAO.dameSoluciones(topicId);
//		this.mapTipoContenido = agregaContenidoDAO.dameTipoContenido();
		
		if(topicBeanTableModel.getNombre_topic().equals("Evaluación de la unidad")){
			this.ocultaLinkEvaluacion = 1;
		}else{
			this.ocultaLinkEvaluacion = 0;
		}
//		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		session.removeAttribute("topicId2");
		session.setAttribute("topicId2", this.topicId);
		activeIndex = -1;

    }
	
	public void guardaLinkVideo(){
	
		if(topicId > 0){
			boolean bandera = agregaContenidoDAO.guardaLinkVideo(topicId, linkVideo);
			if(bandera){
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito","Link de video guardado" ); 
			    FacesContext.getCurrentInstance().addMessage(null, msg);
			    activeIndex = 3;
			}else{
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ups","Salio algo mal en guardar el link del video" ); 
			    FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		}else{
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ups","Debes seleccionar un tema o topic" ); 
		    FacesContext.getCurrentInstance().addMessage(null, msg);
		}

	}
	
	public void guardaLinkEval(){
		
		if(topicId > 0){
			boolean bandera = agregaContenidoDAO.guardaEvaluacion(topicId, linkEvaluacion);
			if(bandera){
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito","Link de evaluación guardado" ); 
			    FacesContext.getCurrentInstance().addMessage(null, msg);
			    activeIndex = 3;
			}else{
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ups","Salio algo mal en guardar el link de evaluación" ); 
			    FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		}else{
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ups","Debes seleccionar un tema o topic" ); 
		    FacesContext.getCurrentInstance().addMessage(null, msg);
		}

	}
	
	public void handleFileUpload(FileUploadEvent event) {
		
		FileInputStream finput;
		FacesMessage message;
		
		try {

				finput = (FileInputStream) event.getFile().getInputstream();
				//event.getFile().getFileName().
				String extension = StringUtils.substringAfter(event.getFile().getFileName(), ".");
				logger.info(extension);
				
				boolean bandera = agregaContenidoDAO.subeContenidoPlus(finput,this.topicId,event.getFile().getFileName());
				if(bandera){
					this.listLaminas    =  agregaContenidoDAO.dameLaminas(topicId);
					FuncionesGrales.manageDialog("dlgLamina", 1);
					FuncionesGrales.mostrarMensaje("Bien!!", "Lamina guardada.", "INFO");
					RequestContext.getCurrentInstance().update("idDialogLamina:id_Lamina");
				}else{
					FuncionesGrales.mostrarMensaje("UPSS!!", "Algo salio mal.", "ERROR");
				}
			
			
			} catch (IOException e) {
				logger.error(e);
			} 

}

	static public class ColumnModel implements Serializable {
	   	 

		    private static final long serialVersionUID = 1L;
			private String header;
	        private String property;
	 
	        public ColumnModel(String header, String property) {
	            this.header = header;
	            this.property = property;
	        }
	 
	        public String getHeader() {
	            return header;
	        }
	 
	        public String getProperty() {
	            return property;
	        }
	    }
		
/************************************************************
 * **********************************************************
        EMPIEZA NUEVA IMPLEMENTACION PARA LOS CONTENIDOS.
 ************************************************************
 ************************************************************/	
	
	
public void guardaVideo(){
	
	boolean bandera = agregaContenidoDAO.guardaLinkVideo(topicId, nombreVideo, linkVideo);
	if(bandera){
		this.listVideos     =  agregaContenidoDAO.dameVideos(topicId); 
		FuncionesGrales.mostrarMensaje("Bien!!", "Video guardado", "INFO");
		FuncionesGrales.manageDialog("dlgVideo", 1);
        
	}else{
		FuncionesGrales.mostrarMensaje("UPSS!!", "Algo salio mal.", "ERROR");
	}
}
	
public void editarVideo(VideosBean videosBean){
	this.nombreVideo = videosBean.getNomVideo();
	this.linkVideo   = videosBean.getLinkVideo();
	this.idVideo     = videosBean.getIdVideo();
}

public void editaVideo(){
	VideosBean videosBean = new VideosBean();
	videosBean.setIdVideo(this.idVideo);
	videosBean.setNomVideo(this.nombreVideo);
	videosBean.setLinkVideo(this.linkVideo);
	boolean bandera = agregaContenidoDAO.editaVideo(videosBean);
	if(bandera){
		this.listVideos     =  agregaContenidoDAO.dameVideos(topicId); 
		FuncionesGrales.mostrarMensaje("Bien!!", "Video guardado", "INFO");
		FuncionesGrales.manageDialog("dlgActuaVideo", 1);
	}else{
		FuncionesGrales.mostrarMensaje("UPSS!!", "Algo salio mal.", "ERROR");
	}
}

public void eliminaVideo(VideosBean videosBean){
	
	boolean bandera = agregaContenidoDAO.eliminaVideo(videosBean.getIdVideo());
	if(bandera){
		this.listVideos =  agregaContenidoDAO.dameVideos(topicId); 
		FuncionesGrales.mostrarMensaje("Bien!!", "Video eliminado.", "INFO");
	}else{
		FuncionesGrales.mostrarMensaje("UPSS!!", "Algo salio mal.", "ERROR");
	}
}

public void eliminaPractica(PracticasBean practicasBean){
	
	boolean bandera = agregaContenidoDAO.eliminaPractica(practicasBean.getIdPracticas());
	if(bandera){
		this.listPracticas =  agregaContenidoDAO.damePracticas(topicId); 
		FuncionesGrales.mostrarMensaje("Bien!!", "Practica eliminada.", "INFO");
	}else{
		FuncionesGrales.mostrarMensaje("UPSS!!", "Algo salio mal.", "ERROR");
	}
}

public void eliminaLamina(LaminasBean laminasBean){
	
	boolean bandera = agregaContenidoDAO.eliminaLamina(laminasBean.getId_laminas());
	if(bandera){
		this.listLaminas =  agregaContenidoDAO.dameLaminas(topicId); 
		FuncionesGrales.mostrarMensaje("Bien!!", "Lamina eliminada.", "INFO");
		
	}else{
		FuncionesGrales.mostrarMensaje("UPSS!!", "Algo salio mal.", "ERROR");
	}
}

public void handleFileUploadPractica(FileUploadEvent event) {
	
	FileInputStream finput;
	PracticasBean practicasBean = new PracticasBean();
	
	try {

			finput = (FileInputStream) event.getFile().getInputstream();
			//event.getFile().getFileName().
			String extension = StringUtils.substringAfter(event.getFile().getFileName(), ".");
			logger.info(extension);
			practicasBean.setNomArchivo(event.getFile().getFileName());
			practicasBean.setId_topic(this.topicId);
			practicasBean.setNomPractica(this.nomPractica);
			practicasBean.setIdContentType(2);//En la BD es el id del PDFs
			
			boolean bandera = agregaContenidoDAO.guardaPracticaLamina(finput, practicasBean);
			if(bandera){
				this.listPracticas    =  agregaContenidoDAO.damePracticas(topicId);
				FuncionesGrales.manageDialog("dlgPracticaPDF", 1);
				FuncionesGrales.mostrarMensaje("Bien!!", "Practica Guardada.", "INFO");
				RequestContext.getCurrentInstance().update("idDialogPracticaPDF:idPracticaPDF");
				bandera = true;
			}else{
				FuncionesGrales.mostrarMensaje("UPSS!!", "Algo salio mal.", "ERROR");
			}
		
		
		} catch (IOException e) {
			logger.error(e);
		} 

}

public void guardaPracticaVideo(){
	PracticasBean practicasBean = new PracticasBean();
	practicasBean.setId_topic(this.topicId);
	practicasBean.setNomPractica(this.nomPractica);
	practicasBean.setLink_video(this.linkVideoPrac);
	practicasBean.setIdContentType(1);//En la BD es el id del Video
	
	
	boolean bandera = agregaContenidoDAO.guardaPracticaVideo(practicasBean);
	if(bandera){
		this.listPracticas    =  agregaContenidoDAO.damePracticas(topicId);
		FuncionesGrales.manageDialog("dlgPracticaVideo", 1);
		FuncionesGrales.mostrarMensaje("Bien!!", "Practica Guardada.", "INFO");
		RequestContext.getCurrentInstance().update("frmPractica:tblPracticas");
	}else{
		FuncionesGrales.mostrarMensaje("UPSS!!", "Algo salio mal.", "ERROR");
	}
}
	
	
	public void handleFileUploadSolucion(FileUploadEvent event) {
		
		FileInputStream finput;
		SolutionsBean solutionsBean = new SolutionsBean();
		
		try {
	
				finput = (FileInputStream) event.getFile().getInputstream();
				//event.getFile().getFileName().
				String extension = StringUtils.substringAfter(event.getFile().getFileName(), ".");
				logger.info(extension);
				solutionsBean.setNomArchivo(event.getFile().getFileName());
				solutionsBean.setTopicId(this.topicId);
				solutionsBean.setSolutionsName(this.nomSolucion);
				solutionsBean.setIdContentType(2);//En la BD es el id del PDFs
				
				boolean bandera = agregaContenidoDAO.guardaSolucionLamina(finput, solutionsBean);
				if(bandera){
					this.listSoluciones    =  agregaContenidoDAO.dameSoluciones(topicId);
					FuncionesGrales.manageDialog("dlgSolucionPDF", 1);
					FuncionesGrales.mostrarMensaje("Bien!!", "Solucion Guardada.", "INFO");
					RequestContext.getCurrentInstance().update("idDialogSolucionPDF:idSolucionPDF");
					bandera = true;
				}else{
					FuncionesGrales.mostrarMensaje("UPSS!!", "Algo salio mal.", "ERROR");
				}
			
			
			} catch (IOException e) {
				logger.error(e);
			} 
	
	}

	public void guardaSolucionVideo(){
		SolutionsBean solutionsBean = new SolutionsBean();
		solutionsBean.setTopicId(this.topicId);
		solutionsBean.setSolutionsName(this.nomSolucion);
		solutionsBean.setLinkVideo(this.linkVideosoluc);
		solutionsBean.setIdContentType(1);//En la BD es el id del Video
		
		
		boolean bandera = agregaContenidoDAO.guardaSolucionVideo(solutionsBean);
		if(bandera){
			this.listSoluciones    =  agregaContenidoDAO.dameSoluciones(topicId);
			FuncionesGrales.manageDialog("dlgSolucionVideo", 1);
			FuncionesGrales.mostrarMensaje("Bien!!", "Solución Guardada.", "INFO");
			RequestContext.getCurrentInstance().update("frmSolucion:tblSoluciones");
		}else{
			FuncionesGrales.mostrarMensaje("UPSS!!", "Algo salio mal.", "ERROR");
		}
	}
	
	
	public void eliminaSolcuion(SolutionsBean solutionsBean){
		
		boolean bandera = agregaContenidoDAO.eliminaSolucion(solutionsBean.getSolutionsId());
		if(bandera){
			this.listSoluciones    =  agregaContenidoDAO.dameSoluciones(topicId);
			FuncionesGrales.mostrarMensaje("Bien!!", "Solución eliminada.", "INFO");
		}else{
			FuncionesGrales.mostrarMensaje("UPSS!!", "Algo salio mal.", "ERROR");
		}
	}

	public void guardaEvaluacionPlus(){
		
		if(topicId > 0){
			boolean bandera = agregaContenidoDAO.guardaEvaluacionPlus(topicId, linkEvaluacion);
			if(bandera){
				FuncionesGrales.mostrarMensaje("Bien!!", "Evaluación guardada", "INFO");
				RequestContext.getCurrentInstance().update("frmEval:itextEval");
			    activeIndex = 3;
			}else{
				FuncionesGrales.mostrarMensaje("UPSS!!", "Algo salio mal.", "ERROR");
			}
		}else{
			FuncionesGrales.mostrarMensaje("UPSS!!", "Debes seleccionar un tema o topic", "WARN");
		}

	}
	
	public List<ColumnModel> getColumns() {
		return columns;
	}


	public void setColumns(List<ColumnModel> columns) {
		this.columns = columns;
	}


	public int getCourseId() {
		return courseId;
	}


	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}


	public String getCourseName() {
		return courseName;
	}


	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}


	public java.util.Date getCreationDate() {
		return creationDate;
	}


	public void setCreationDate(java.util.Date creationDate) {
		this.creationDate = creationDate;
	}


	public List<CursosBeanTableModel> getListCourses() {
		return listCourses;
	}

	public void setListCourses(List<CursosBeanTableModel> listCourses) {
		this.listCourses = listCourses;
	}

	public CursosBeanTableModel getSelectedCurso() {
		return selectedCurso;
	}

	public void setSelectedCurso(CursosBeanTableModel selectedCurso) {
		this.selectedCurso = selectedCurso;
	}

	public List<UnitsBeanTableModel> getListUnits() {
		return listUnits;
	}

	public void setListUnits(List<UnitsBeanTableModel> listUnits) {
		this.listUnits = listUnits;
	}

	public UnitsBeanTableModel getSelectedUnidad() {
		return selectedUnidad;
	}

	public void setSelectedUnidad(UnitsBeanTableModel selectedUnidad) {
		this.selectedUnidad = selectedUnidad;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public List<ColumnModel> getColumnsUnits() {
		return columnsUnits;
	}

	public void setColumnsUnits(List<ColumnModel> columnsUnits) {
		this.columnsUnits = columnsUnits;
	}

	public int getActiveIndex() {
		return activeIndex;
	}

	public void setActiveIndex(int activeIndex) {
		this.activeIndex = activeIndex;
	}

	public int getTopicId() {
		return topicId;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public List<ColumnModel> getColumnsTopics() {
		return columnsTopics;
	}

	public void setColumnsTopics(List<ColumnModel> columnsTopics) {
		this.columnsTopics = columnsTopics;
	}

	public List<TopicBeanTableModel> getListTopics() {
		return listTopics;
	}

	public void setListTopics(List<TopicBeanTableModel> listTopics) {
		this.listTopics = listTopics;
	}

	public TopicBeanTableModel getSelectedTopic() {
		return selectedTopic;
	}

	public void setSelectedTopic(TopicBeanTableModel selectedTopic) {
		this.selectedTopic = selectedTopic;
	}

	public String getLinkVideo() {
		return linkVideo;
	}

	public void setLinkVideo(String linkVideo) {
		this.linkVideo = linkVideo;
	}

	public String getLinkEvaluacion() {
		return linkEvaluacion;
	}

	public void setLinkEvaluacion(String linkEvaluacion) {
		this.linkEvaluacion = linkEvaluacion;
	}

	public int getOcultaLinkEvaluacion() {
		return ocultaLinkEvaluacion;
	}

	public void setOcultaLinkEvaluacion(int ocultaLinkEvaluacion) {
		this.ocultaLinkEvaluacion = ocultaLinkEvaluacion;
	}

	public String getNombreVideo() {
		return nombreVideo;
	}

	public void setNombreVideo(String nombreVideo) {
		this.nombreVideo = nombreVideo;
	}

	public List<VideosBean> getListVideos() {
		return listVideos;
	}

	public void setListVideos(List<VideosBean> listVideos) {
		this.listVideos = listVideos;
	}

	public int getIdVideo() {
		return idVideo;
	}

	public void setIdVideo(int idVideo) {
		this.idVideo = idVideo;
	}

	public List<LaminasBean> getListLaminas() {
		return listLaminas;
	}

	public void setListLaminas(List<LaminasBean> listLaminas) {
		this.listLaminas = listLaminas;
	}

	public String getNombreLamina() {
		return nombreLamina;
	}

	public void setNombreLamina(String nombreLamina) {
		this.nombreLamina = nombreLamina;
	}

	public int getIdLamina() {
		return idLamina;
	}

	public void setIdLamina(int idLamina) {
		this.idLamina = idLamina;
	}

	public List<PracticasBean> getListPracticas() {
		return listPracticas;
	}

	public void setListPracticas(List<PracticasBean> listPracticas) {
		this.listPracticas = listPracticas;
	}

	public String getNomArchivoPractia() {
		return nomArchivoPractia;
	}

	public void setNomArchivoPractia(String nomArchivoPractia) {
		this.nomArchivoPractia = nomArchivoPractia;
	}

	public int getTipoContentId() {
		return tipoContentId;
	}

	public void setTipoContentId(int tipoContentId) {
		this.tipoContentId = tipoContentId;
	}

	public String getLinkVideoPrac() {
		return linkVideoPrac;
	}

	public void setLinkVideoPrac(String linkVideoPrac) {
		this.linkVideoPrac = linkVideoPrac;
	}

	public Map<String, Integer> getMapTipoContenido() {
		return mapTipoContenido;
	}

	public void setMapTipoContenido(Map<String, Integer> mapTipoContenido) {
		this.mapTipoContenido = mapTipoContenido;
	}

	public String getNomPractica() {
		return nomPractica;
	}

	public void setNomPractica(String nomPractica) {
		this.nomPractica = nomPractica;
	}

	public List<SolutionsBean> getListSoluciones() {
		return listSoluciones;
	}

	public void setListSoluciones(List<SolutionsBean> listSoluciones) {
		this.listSoluciones = listSoluciones;
	}

	public String getNomArchivoSolucion() {
		return nomArchivoSolucion;
	}

	public void setNomArchivoSolucion(String nomArchivoSolucion) {
		this.nomArchivoSolucion = nomArchivoSolucion;
	}

	public String getNomSolucion() {
		return nomSolucion;
	}

	public void setNomSolucion(String nomSolucion) {
		this.nomSolucion = nomSolucion;
	}

	public String getLinkVideosoluc() {
		return linkVideosoluc;
	}

	public void setLinkVideosoluc(String linkVideosoluc) {
		this.linkVideosoluc = linkVideosoluc;
	}

	
	
	
	

}

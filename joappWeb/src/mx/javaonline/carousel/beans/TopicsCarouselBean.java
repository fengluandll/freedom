package mx.javaonline.carousel.beans;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import mx.javaonline.admin.beans.LaminasBean;
import mx.javaonline.admin.beans.PracticasBean;
import mx.javaonline.admin.beans.SolutionsBean;
import mx.javaonline.admin.beans.VideosBean;
import mx.javaonline.beans.StudentPersonalBean;
import mx.javaonline.beans.TopicsBean;
import mx.javaonline.daos.AgregaContenidoDAO;
import mx.javaonline.model.ConectionWrapper;

public class TopicsCarouselBean {
	
	private static org.apache.log4j.Logger logger = Logger.getLogger(TopicsCarouselBean.class);
	ConectionWrapper conectionWrapper;
	List<TopicsBean> listTopics;
	FacesContext facesContext = FacesContext.getCurrentInstance();
	Connection con;
	TopicsBean topicsBean;
	HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
	String linkVideo;
	private String valueNotes;
	AgregaContenidoDAO agregaContenidoDAO;
	private String nomCurso;

	public TopicsCarouselBean() {
	
		conectionWrapper = new ConectionWrapper();
		agregaContenidoDAO = new AgregaContenidoDAO();
	}
	
	public String redireccionaCarousel(){
		
		return "topicCarousel";
	}
	
	
	public String verContenido() {
		Map<String,String> params = facesContext.getExternalContext().getRequestParameterMap();
	    String nomCurso = params.get("nomCurso");
	    String courseId = params.get("courseId");
	    
	    HttpSession session = (HttpSession)facesContext.getExternalContext().getSession(true);
	    session.setAttribute("nomCurso", nomCurso);
	    session.setAttribute("courseId", courseId);
	    
		return "contenidoCurso";
	}
	
	@SuppressWarnings("null")
	public List<TopicsBean> creaListTopics(String unitId,String topicId){
//		logger.debug("UNIT_ID "+unitId);
//		logger.debug("TOPIC_ID "+topicId);
		
		conectionWrapper = new ConectionWrapper();
		 PreparedStatement psmt = null;
		listTopics = new ArrayList<>();
		try {
			con = conectionWrapper.getConexion();
		   String sql = "SELECT t.topic_id, \n"+
						"	   t.topic_name, \n"+
						"	   t.link, \n"+
						"	   t.unit_id, \n"+
						"	   t.segment1, \n"+
						"	   t.segment2, \n"+
						"	   t.segment3,  \n"+
						"	   t.segment4,  \n"+
						"	   t.segment5,  \n"+
						"	   t.segment6,  \n"+
						"	   t.segment7,  \n"+
						"	   t.segment8, \n"+
						"	   ct.content_topics_id, \n"+
						"	   ct.link_video, \n"+
						"	   ct.lamina, \n"+
						"	   ct.practica, \n"+
						"	   ct.solucion, \n"+
						"	   ct.segment1 \n"+
						"FROM  topics t, \n"+
						"	  content_topics ct \n"+
						"WHERE unit_id = ? \n"+
						 " AND ct.topic_id = t.topic_id \n"+
						"ORDER BY unit_id,topic_id ";
		   String sql2 = "SELECT t.topic_id, \n"+
					"	   t.topic_name, \n"+
					"	   t.link, \n"+
					"	   t.unit_id, \n"+
					"	   t.segment1, \n"+
					"	   t.segment2, \n"+
					"	   t.segment3,  \n"+
					"	   t.segment4,  \n"+
					"	   t.segment5,  \n"+
					"	   t.segment6,  \n"+
					"	   t.segment7,  \n"+
					"	   t.segment8, \n"+
					"	   ct.content_topics_id, \n"+
					"	   ct.link_video, \n"+
					"	   ct.lamina, \n"+
					"	   ct.practica, \n"+
					"	   ct.solucion, \n"+
					"	   ct.segment1 \n"+
					"FROM  topics t, \n"+
					"	  content_topics ct \n"+
					"WHERE ct.topic_id = t.topic_id \n"+
					" AND  unit_id     = ? \n"+
					" AND  t.topic_id  = ? ";
		   
		 if(topicId != ""){
			 psmt = con.prepareStatement(sql2);
			 psmt.setString(1,unitId);
			 psmt.setString(2,topicId);
			 //logger.debug("sql2 "+sql2);
		 }else{
			 psmt = con.prepareStatement(sql);
			 psmt.setString(1,unitId);
			 //logger.debug("sql "+sql);
		 }
		
		
		
		
		ResultSet rs = psmt.executeQuery();
		
		while(rs.next()){
			topicsBean = new TopicsBean();
			topicsBean.setTopicId(rs.getInt(1));
			topicsBean.setTopicName(rs.getString(2));
			topicsBean.setLink(rs.getString(3));
			topicsBean.setUnitId(rs.getInt(4));
			topicsBean.setSegment1(rs.getString(5));
			topicsBean.setSegment2(rs.getString(6));
			topicsBean.setSegment3(rs.getString(7));
			topicsBean.setSegment4(rs.getString(8));
			topicsBean.setSegment5(rs.getString(9));
			topicsBean.setSegment6(rs.getInt(10));
			topicsBean.setSegment7(rs.getDate(11));
			topicsBean.setSegment8(rs.getBlob(12));
			topicsBean.setContentTopicId(rs.getInt(13));
			topicsBean.setLinkVideo(rs.getString(14));
			topicsBean.setLamina(rs.getBlob(15));
			topicsBean.setPractica(rs.getBlob(16));
			topicsBean.setSolucion(rs.getBlob(17));
			topicsBean.setSegment1_1(rs.getString(18));
			listTopics.add(topicsBean);
			
		}
		//session.removeAttribute("listTopics");
		//session.setAttribute("listTopics", listTopics);
			
		} catch (NamingException | SQLException e) {
			logger.error(e);
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				logger.error(e);
			}
		}
		return listTopics;
	}

	public String getStatusIcon(int topicId,String tipoMaterial,int videoId,String tipCont){
		StudentPersonalBean studentPersonalBean = (StudentPersonalBean)session.getAttribute("studentPersonalBean");
    	ConectionWrapper conectionWrapper = new ConectionWrapper();
		String rutaIcon = "";
		Connection con = null;
		int valueBar = 0;
	try {
		//logger.debug("studentPersonalBean.getSegment1() "+studentPersonalBean.getSegment1());
		con = conectionWrapper.getConexion();
		CallableStatement cstmtCall = con.prepareCall("{CALL get_status_bar_pr(?,?,?,?,?,?,?,?)}");
		cstmtCall.setInt(1,studentPersonalBean.getStudentPersonalId());
		cstmtCall.setInt(2,topicId);
		cstmtCall.setString(3,tipoMaterial);
		cstmtCall.setString(4,studentPersonalBean.getSegment1());
		cstmtCall.setInt(5,videoId);
		cstmtCall.setString(6,tipCont);
		cstmtCall.registerOutParameter(7, Types.INTEGER);
		cstmtCall.registerOutParameter(8, Types.VARCHAR);
		cstmtCall.execute();
		valueBar = cstmtCall.getInt(7);
		
	} catch (SQLException | NamingException e) {
			logger.error(e);
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				logger.error(e);
			}
		}
	          if(valueBar < 100){
	        	  //rutaIcon = "/images/Traffic-No-Entry-24.png";
	        	  //rutaIcon = "<i class='fas fa-minus-circle fa-2x'></i>";
	        	  rutaIcon = "fas fa-minus-circle fa-2x";
	        	  
	          }else{
	        	  //rutaIcon = "/images/Accept-24.png";
	        	  //rutaIcon = "<i class='fas fa-check fa-2x'></i>";
	        	  rutaIcon = "fas fa-check fa-2x";
	          }
	          
	   return rutaIcon;	          
	}	
	
	public int getTopicStatusBar(int topicId,String tipoMaterial,int videoId,String tipCont){
		StudentPersonalBean studentPersonalBean = (StudentPersonalBean)session.getAttribute("studentPersonalBean");		
		Connection con = null;
		int valueBar = 0;
	try {
//		logger.debug("studentPersonalBean.getSegment1() "+studentPersonalBean.getSegment1());
		con = conectionWrapper.getConexion();
		CallableStatement cstmtCall = con.prepareCall("{CALL get_status_bar_pr(?,?,?,?,?,?,?,?)}");
		cstmtCall.setInt(1,studentPersonalBean.getStudentPersonalId());
		cstmtCall.setInt(2,topicId);
		cstmtCall.setString(3,tipoMaterial);
		cstmtCall.setString(4,studentPersonalBean.getSegment1());
		cstmtCall.setInt(5,videoId);
		cstmtCall.setString(6,tipCont);
		cstmtCall.registerOutParameter(7, Types.INTEGER);
		cstmtCall.registerOutParameter(8, Types.VARCHAR);
		cstmtCall.execute();
		valueBar = cstmtCall.getInt(7);
//		logger.debug("valueBar "+valueBar);
		
	} catch (SQLException | NamingException e) {
			logger.error(e);
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				logger.error(e);
			}
		}
	return valueBar;	
	}
	
	/**
	 * Metodo que manda a llamar a un procedimeinto que contara los contenidos que contiene cada unidad
	 * entendiendo como contenido, video,lamina,practica y solucion por cada topic, si cada topic contiene estos 4, este metodo
	 * lo multiplicara por 100 para hacer la regla de 3, si solo tiene video y lamina sera 2 por 100.
	 * Tambien traera la suma de todos los estatus bar de cada clntenido para ahcer la siguiente regla de tres.
	 * 180(suma de las barras de estatus) * 100(porcentaje) / 400(numero de contenidos de toda la unidad)
	 * @param unitId unidad id
	 * @return regresa el valor general para la barra de porcentaje por unidad
	 */
	public int getUnitBarraGral(int unitId){
		//logger.debug("UNIT_ID para la barra gral "+unitId);
		StudentPersonalBean studentPersonalBean = (StudentPersonalBean)session.getAttribute("studentPersonalBean");
		int totalBarraUnidad = 0;
		try{
			con = conectionWrapper.getConexion();
			CallableStatement cstmtCall = con.prepareCall("{CALL get_unit_porc_total_pr(?,?,?,?,?,?,?,?,?,?)}");
			cstmtCall.setInt(1,studentPersonalBean.getStudentPersonalId());
			cstmtCall.setInt(2,unitId);
			cstmtCall.setString(3,studentPersonalBean.getSegment1());
			cstmtCall.registerOutParameter(4, Types.INTEGER);
			cstmtCall.registerOutParameter(5, Types.INTEGER);
			cstmtCall.registerOutParameter(6, Types.INTEGER);
			cstmtCall.registerOutParameter(7, Types.INTEGER);
			cstmtCall.registerOutParameter(8, Types.INTEGER);
			cstmtCall.registerOutParameter(9, Types.VARCHAR);
			cstmtCall.registerOutParameter(10, Types.VARCHAR);
			cstmtCall.execute();
			int porTotal       = cstmtCall.getInt(4);
			int contVideo      = cstmtCall.getInt(5);
			int contLamina     = cstmtCall.getInt(6);
			int contPractica   = cstmtCall.getInt(7);
			int contSolucion   = cstmtCall.getInt(8);
			int contEvaluacion = cstmtCall.getInt(9);
			String error = cstmtCall.getString(9);
			int totContenido = contVideo + contLamina + contPractica + contSolucion + contEvaluacion;
			int totContenidoCiento = totContenido * 100;
			if(totContenidoCiento == 0){
				totalBarraUnidad = 0;
			}else{
				
				totalBarraUnidad = porTotal * 100 / totContenidoCiento;
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
		return totalBarraUnidad;
	}
	/**
	 * @deprecated
	 * YA no se utiliza este metodo, removerlo
	 * @param unitId
	 */
	public void misNotasUnit(int unitId){
		StudentPersonalBean studentPersonalBean = (StudentPersonalBean)session.getAttribute("studentPersonalBean");
    	
		
		Connection con = null;
		String notes = null;
	try {
		con = conectionWrapper.getConexion();
		CallableStatement cstmtCall = con.prepareCall("{CALL create_notes(?,?,?,?,?,?)}");
		cstmtCall.setInt(1,studentPersonalBean.getStudentPersonalId());
		cstmtCall.setString(2,this.valueNotes);
		cstmtCall.setString(3,studentPersonalBean.getSegment1());
		cstmtCall.setString(4,studentPersonalBean.getSegment1());
		cstmtCall.registerOutParameter(5, Types.VARCHAR);
		cstmtCall.execute();
		String error = cstmtCall.getString(5);
		//logger.debug("notes "+notes);
		logger.error(error);
		FacesContext context = FacesContext.getCurrentInstance();  
        context.addMessage(null, new FacesMessage("Exitoso","Tus notas han sido guardadas")); 
		
	} catch (SQLException | NamingException e) {
			logger.error(e);
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				logger.error(e);
			}
		}
		
	}

	/************************************************************
	 * **********************************************************
	        EMPIEZA NUEVA IMPLEMENTACION PARA LOS CONTENIDOS.
	 ************************************************************
	 ************************************************************/	

	public List<TopicsBean> creaListTopicsPlus(String unitId,String topicId){
//		logger.debug("UNIT_ID "+unitId);
//		logger.debug("TOPIC_ID "+topicId);
		
		conectionWrapper = new ConectionWrapper();
		 PreparedStatement psmt = null;
		listTopics = new ArrayList<>();
		try {
			con = conectionWrapper.getConexion();
		   String sql =   "SELECT topic_id,"
		   		+ "				  topic_name,"
		   		+ "				  link,"
		   		+ "				  link_evaluacion,"
		   		+ "  			  unit_id,"
		   		+"                evaluacion_tab_id"
		   		+ "        FROM topics_all_view where unit_id = ? ORDER BY topic_id";
		   String sql2 =  "SELECT topic_id,"
		   		+ "				  topic_name,"
		   		+ "				  link,"
		   		+ "				  link_evaluacion,"
		   		+ "  			  unit_id,"
		   		+"                evaluacion_tab_id"
		   		+ "         FROM topics_all_view where unit_id = ? AND topic_id= ?";
		   
		 if(topicId != ""){
			 psmt = con.prepareStatement(sql2);
			 psmt.setString(1,unitId);
			 psmt.setString(2,topicId);
			 //logger.debug("sql2 "+sql2);
		 }else{
			 psmt = con.prepareStatement(sql);
			 psmt.setString(1,unitId);
			 //logger.debug("sql "+sql);
		 }
		
		
		ResultSet rs = psmt.executeQuery();
		
		while(rs.next()){
			topicsBean = new TopicsBean();
			topicsBean.setTopicId(rs.getInt(1));
			topicsBean.setTopicName(rs.getString(2));
			topicsBean.setLink(rs.getString(3));
			topicsBean.setSegment1_1(rs.getString(4));
			topicsBean.setUnitId(rs.getInt(5));
			listTopics.add(topicsBean);
			
		}
		//session.removeAttribute("listTopics");
		//session.setAttribute("listTopics", listTopics);
			
		} catch (NamingException | SQLException e) {
			logger.error(e);
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				logger.error(e);
			}
		}
		return listTopics;
	}
	
	
	public List<VideosBean> dameVideos(int topicId){
		
		List<VideosBean> listVideosBean = agregaContenidoDAO.dameVideos(topicId);
		return listVideosBean;
	}
	
	public List<LaminasBean> dameLaminas(int topicId){
		
		List<LaminasBean> listLaminasBean = agregaContenidoDAO.dameLaminas(topicId);
		return listLaminasBean;
	}	
	
	public List<PracticasBean> damePracticas(int topicId){
		
		List<PracticasBean> listPracticasBean = agregaContenidoDAO.damePracticas(topicId);
		return listPracticasBean;
	}	
	
	public List<SolutionsBean> dameSolucion(int topicId){
		
		List<SolutionsBean> listSolucionBean = agregaContenidoDAO.dameSoluciones(topicId);
		return listSolucionBean;
	}	
	
	
	
	public TopicsBean getTopicsBean() {
		return topicsBean;
	}

	public void setTopicsBean(TopicsBean topicsBean) {
		this.topicsBean = topicsBean;
	}

	public String getLinkVideo() {
		return linkVideo;
	}

	public void setLinkVideo(String linkVideo) {
		this.linkVideo = linkVideo;
	}

	public List<TopicsBean> getListTopics() {
		return listTopics;
	}

	public void setListTopics(List<TopicsBean> listTopics) {
		this.listTopics = listTopics;
	}

	/**
	 * @deprecated Este metodo ya no se usa, se paso a DialogNotesBean con logica
	 * duferente
	 * @return
	 */
	public String getValueNotes() {
		String unitId = (String) session.getAttribute("unitId");
		//logger.debug("unitId "+unitId);
		StudentPersonalBean studentPersonalBean = (StudentPersonalBean)session.getAttribute("studentPersonalBean");
//		logger.debug("studentPersonalBean.getStudentPersonalId() "+studentPersonalBean.getStudentPersonalId());
//		logger.debug("studentPersonalBean.getSegment1() "+studentPersonalBean.getSegment1());
    	conectionWrapper = new ConectionWrapper();
		
		Connection con = null;
		String notes = "";
	try {
		con = conectionWrapper.getConexion();
		CallableStatement cstmtCall = con.prepareCall("{CALL getValueNotes_pr(?,?,?,?,?)}");
		cstmtCall.setInt(1,studentPersonalBean.getStudentPersonalId());
		cstmtCall.setString(2,unitId);
		cstmtCall.setString(3,studentPersonalBean.getSegment1());
		cstmtCall.registerOutParameter(4, Types.VARCHAR);
		cstmtCall.registerOutParameter(5, Types.VARCHAR);
		cstmtCall.execute();
		notes = cstmtCall.getString(4);
		String error = cstmtCall.getString(5);
		//logger.debug("notes "+notes);
		logger.error(error);
		
	} catch (SQLException | NamingException e) {
			logger.error(e);
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				logger.error(e);
			}
		}
		return notes;
	}

	public void setValueNotes(String valueNotes) {
		this.valueNotes = valueNotes;
	}

	public String getNomCurso() {
		return nomCurso;
	}

	public void setNomCurso(String nomCurso) {
		this.nomCurso = nomCurso;
	}
	
	
	

}

package mx.javaonline.daos;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
















import mx.javaonline.admin.beans.ContentTopicsBean;
import mx.javaonline.admin.beans.CursosBean;
import mx.javaonline.admin.beans.LaminasBean;
import mx.javaonline.admin.beans.PracticasBean;
import mx.javaonline.admin.beans.SolutionsBean;
import mx.javaonline.admin.beans.VideosBean;
import mx.javaonline.model.ConectionWrapper;
import mx.javaonline.model.CursosBeanTableModel;
import mx.javaonline.model.TopicBeanTableModel;
import mx.javaonline.model.UnitsBeanTableModel;

import org.apache.log4j.Logger;

public class AgregaContenidoDAO {
	
	private org.apache.log4j.Logger logger = Logger.getLogger(AgregaContenidoDAO.class);
	ConectionWrapper conectionWrapper;
	Connection con;
	CursosBeanTableModel coursesBean;
	UnitsBeanTableModel unitsBean;
	TopicBeanTableModel topicBean;
	List<CursosBeanTableModel> listCourses;
	List<UnitsBeanTableModel>  listUnits;
	List<TopicBeanTableModel>  listTopics;
	ContentTopicsBean contentTopicsBean;
	Map<String,Integer> mapTipoContenido;

	public AgregaContenidoDAO() {
	
	}
	
	public String obtenContenido(int topicId){
		String linkVideo = null;
		String sql = "SELECT content_topics_id,\n"+
					 "	    topic_id,\n"+
					 "	    link_video,\n"+
					 "	    lamina,\n"+
					 "	    practica,\n"+
					 "	    solucion,\n"+
					 "	    segment1,\n"+
					 "	    segment2,\n"+
					 "	    segment3,\n"+
					 "	    segment4,\n"+
					 "	    segment5,\n"+
					 "	    segment6,\n"+
					 "	    segment7,\n"+
					 "	    segment8,\n"+
					 "	    creation_date\n"+
					 "	FROM content_topics\n"+
					 "	WHERE topic_id = ?";
		conectionWrapper = new ConectionWrapper();
		try {
			con = conectionWrapper.getConexion();
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setInt(1, topicId);
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()){
				linkVideo = rs.getString(3);
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
		return linkVideo;
	}
	
	public String obtenLigaEval(int topicId){
		String linkEval = null;
		String sql = "SELECT content_topics_id,\n"+
					 "	    topic_id,\n"+
					 "	    link_video,\n"+
					 "	    lamina,\n"+
					 "	    practica,\n"+
					 "	    solucion,\n"+
					 "	    segment1,\n"+
					 "	    segment2,\n"+
					 "	    segment3,\n"+
					 "	    segment4,\n"+
					 "	    segment5,\n"+
					 "	    segment6,\n"+
					 "	    segment7,\n"+
					 "	    segment8,\n"+
					 "	    creation_date\n"+
					 "	FROM content_topics\n"+
					 "	WHERE topic_id = ?";
		conectionWrapper = new ConectionWrapper();
		try {
			con = conectionWrapper.getConexion();
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setInt(1, topicId);
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()){
				linkEval = rs.getString(7);
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
		return linkEval;
	}
	
	public List<CursosBeanTableModel> dameCursosDb(){
		listCourses = new ArrayList<>();
			conectionWrapper = new ConectionWrapper();
			String sql = "SELECT course_id, \n"+
					 "	    course_name,\n"+
					 "	    creation_date\n"+
					 " FROM courses";
		try {
				con = conectionWrapper.getConexion();
				PreparedStatement psmt =  con.prepareStatement(sql);
				ResultSet rs = psmt.executeQuery();
				while(rs.next()){
					coursesBean = new CursosBeanTableModel();
					coursesBean.setId_curso(rs.getInt(1));
					coursesBean.setNombre_curso(rs.getString(2));
					coursesBean.setFecha_creacion(rs.getDate(3));
					listCourses.add(coursesBean);
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
		return listCourses;
	}
	
	public List<UnitsBeanTableModel> dameUnidadesDb(int courseId){
		listUnits = new ArrayList<>();
		String sql = "SELECT unit_id, \n"+
					 "	    unit_name \n"+
					 "	FROM units \n"+
					 "	WHERE course_id = ?";
		try {
			con = conectionWrapper.getConexion();
			PreparedStatement psmt =  con.prepareStatement(sql);
			psmt.setInt(1, courseId);
			ResultSet rs =  psmt.executeQuery();
			while(rs.next()){
				unitsBean = new UnitsBeanTableModel();
				unitsBean.setUnidad_id(rs.getInt(1));
				unitsBean.setNombre_unidad(rs.getString(2));
				listUnits.add(unitsBean);
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
		return listUnits;
	}
	
	public List<TopicBeanTableModel> dameTopicsDb(int unitId){
		listTopics = new ArrayList<>();
		String sql = "SELECT topic_id,topic_name FROM topics WHERE unit_id = ?";
		
		try {
			con = conectionWrapper.getConexion();
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setInt(1, unitId);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()){
				topicBean = new TopicBeanTableModel();
				topicBean.setId_topic(rs.getInt(1));
				topicBean.setNombre_topic(rs.getString(2));
				listTopics.add(topicBean);
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
		return listTopics;
		
	}
	
	public boolean guardaLinkVideo(int topicId,String linkVideo){
		boolean bandera = false;
		String sql    = "SELECT COUNT(*) FROM content_topics WHERE topic_id = ?";
		String update = "UPDATE content_topics SET link_video = ? WHERE topic_id = ?";
		String insert = "INSERT INTO content_topics(topic_id,link_video,creation_date) VALUES(?,?,sysdate())";
			
			try {
				con = conectionWrapper.getConexion();
				PreparedStatement psmt = con.prepareStatement(sql);
				psmt.setInt(1, topicId);
				ResultSet rs = psmt.executeQuery();
				int count = 0;
				while(rs.next()){
					count = rs.getInt(1);
				}
				PreparedStatement psmt2 = null;
				if(count > 0){
					psmt2 = con.prepareStatement(update);
					psmt2.setString(1, linkVideo);
					psmt2.setInt(2, topicId);
					psmt2.executeUpdate();
				}else{
					psmt2 = con.prepareStatement(insert);
					psmt2.setInt(1, topicId);
					psmt2.setString(2, linkVideo);
					psmt2.executeUpdate();
				}
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
	
	public boolean guardaEvaluacion(int topicId,String linkEvaluacion){
		boolean bandera = false;
		String sql    = "SELECT COUNT(*) FROM content_topics WHERE topic_id = ?";
		String update = "UPDATE content_topics SET segment1 = ? WHERE topic_id = ?";
		String insert = "INSERT INTO content_topics(topic_id,segment1,creation_date) VALUES(?,?,sysdate())";
			
			try {
				con = conectionWrapper.getConexion();
				PreparedStatement psmt = con.prepareStatement(sql);
				psmt.setInt(1, topicId);
				ResultSet rs = psmt.executeQuery();
				int count = 0;
				while(rs.next()){
					count = rs.getInt(1);
				}
				PreparedStatement psmt2 = null;
				if(count > 0){
					psmt2 = con.prepareStatement(update);
					psmt2.setString(1, linkEvaluacion);
					psmt2.setInt(2, topicId);
					psmt2.executeUpdate();
				}else{
					psmt2 = con.prepareStatement(insert);
					psmt2.setInt(1, topicId);
					psmt2.setString(2, linkEvaluacion);
					psmt2.executeUpdate();
				}
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
	
	public boolean subeContenido(FileInputStream event,int topicId,String accion){
		boolean bandera = false;
		conectionWrapper = new ConectionWrapper();
		String sql    = " SELECT COUNT(*) FROM content_topics WHERE topic_id = ?";
		String update = "";
		String insert = "";
		if(accion.equals("lamina")){
			update = " UPDATE content_topics SET lamina = ? WHERE topic_id = ?";
			insert = " INSERT INTO content_topics(lamina,topic_id,creation_date) VALUES(?,?,sysdate())";
		}else if(accion.equals("practica")){
			update = " UPDATE content_topics SET practica = ? WHERE topic_id = ?";
			insert = " INSERT INTO content_topics(practica,topic_id,creation_date) VALUES(?,?,sysdate())";
		}else if(accion.equals("solucion")){
			update = " UPDATE content_topics SET solucion = ? WHERE topic_id = ?";
			insert = " INSERT INTO content_topics(solucion,topic_id,creation_date) VALUES(?,?,sysdate())";
		}
		
		try {
			con = conectionWrapper.getConexion();
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setInt(1, topicId);
			ResultSet rs = psmt.executeQuery();
			int count = 0;
			while(rs.next()){
				count = rs.getInt(1);
			}
			PreparedStatement psmt2 = null;
			if(count > 0){
				psmt2 = con.prepareStatement(update);
				psmt2.setBinaryStream(1, event);
				psmt2.setInt(2, topicId);
				psmt2.executeUpdate();
				bandera = true;
			}else{
				psmt2 = con.prepareStatement(insert);
				psmt2.setBinaryStream(1, event);
				psmt2.setInt(2, topicId);
				psmt2.executeUpdate();
				bandera = true;
			}
			
			
		} catch (NamingException | SQLException e) {
			logger.error(e);
		}finally{
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					logger.error(e);
				}
			}
		}
		return bandera;
	}
	
	/************************************************************
	 * **********************************************************
	        EMPIEZA NUEVA IMPLEMENTACION PARA LOS CONTENIDOS.
	 ************************************************************
	 ************************************************************/
	
	public boolean subeContenidoPlus(FileInputStream event,int topicId,String nomLamina){
		boolean bandera = false;
		conectionWrapper = new ConectionWrapper();
		String sql    = "INSERT INTO laminas_tab(topic_id,\n" +
						"					    nom_lamina,\n" +
						"                        lamina,\n" +
						"                        creation_date)\n" +
						"VALUES(?,?,?,SYSDATE()) ";		
		
		try {
			con = conectionWrapper.getConexion();
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setInt(1, topicId);
			psmt.setString(2, nomLamina);
			psmt.setBinaryStream(3,event);
			psmt.executeUpdate();
			bandera = true;			
			
		} catch (NamingException | SQLException e) {
			logger.error(e);
		}finally{
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					logger.error(e);
				}
			}
		}
		return bandera;
	}
	
	public List<VideosBean> dameVideos(int topicId){
		VideosBean videosBean;
		List<VideosBean> listVideos = new ArrayList<>();
		conectionWrapper = new ConectionWrapper();
		String sql       =  "SELECT id_video,\n" +
							"    id_topic,\n" +
							"    nom_video,\n" +
							"    link_video,\n" +
							"    segment1,\n" +
							"    segment2,\n" +
							"    segment3,\n" +
							"    segment4,\n" +
							"    segment5,\n" +
							"    segment6,\n" +
							"    segment7,\n" +
							"    segment8,\n" +
							"    creation_date,\n" +
							"    last_update_date\n" +
							"FROM videos_tab\n" +
							"WHERE id_topic = ?";
		 try {
			con = conectionWrapper.getConexion();
			 PreparedStatement psmt = con.prepareStatement(sql);
			 psmt.setInt(1, topicId);
			 ResultSet rs = psmt.executeQuery();
			 while(rs.next()){
				 videosBean = new VideosBean();
				 videosBean.setIdVideo(rs.getInt(1));
				 videosBean.setId_topic(rs.getInt(2));
				 videosBean.setNomVideo(rs.getString(3));
				 videosBean.setLinkVideo(rs.getString(4));
				 videosBean.setSegment1(rs.getString(5));
				 videosBean.setSegment2(rs.getString(6));
				 videosBean.setSegment3(rs.getString(7));
				 videosBean.setSegment4(rs.getString(8));
				 videosBean.setSegment5(rs.getString(9));
				 videosBean.setSegment6(rs.getInt(10));
				 videosBean.setSegment7(rs.getDate(11));
				 videosBean.setSegment8(rs.getBlob(12));
				 videosBean.setCreation_date(rs.getDate(13));
				 videosBean.setEnd_date(rs.getDate(14));
				 listVideos.add(videosBean);
			 }
		} catch (NamingException | SQLException e) {
			logger.error(e);
		}finally{
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					logger.error(e);
				}
			}
		}
		
		 return listVideos;
	}
	
	
	public boolean guardaLinkVideo(int topicId,String nombreVideo,String linkVideo){
		boolean bandera = false;
		String sql = "INSERT INTO videos_tab(id_topic,nom_video,link_video,creation_date) VALUES(?,?,?,sysdate())";
		try {
			conectionWrapper = new ConectionWrapper();
			con = conectionWrapper.getConexion();
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setInt(1, topicId);
			psmt.setString(2, nombreVideo);
			psmt.setString(3, linkVideo);
			psmt.executeUpdate();
			bandera = true;
		} catch (NamingException | SQLException e) {
			logger.error(e);
		}finally{
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					logger.error(e);
				}
			}
		}
		return bandera;
	}
	
	public boolean editaVideo(VideosBean videosBean){
		boolean bandera = false;
		String sql = "UPDATE videos_tab SET nom_video = ?,\n" +
					 "					  link_video = ?,\n" +
					 "                      last_update_date = sysdate()\n" +
					 "WHERE id_video = ?";
		conectionWrapper = new ConectionWrapper();
		try {
			con = conectionWrapper.getConexion();
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, videosBean.getNomVideo());
			psmt.setString(2, videosBean.getLinkVideo());
			psmt.setInt(3, videosBean.getIdVideo());
			psmt.executeUpdate();
			bandera = true;
		} catch (NamingException | SQLException e) {
			logger.error(e);
		}finally{
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					logger.error(e);
				}
			}
		}
		return bandera;		
	}
	
	public boolean eliminaVideo(int idVideo){
		boolean bandera = false;
		String [] sql = {
			    "DELETE FROM videos_tab WHERE id_video = ?",
			    "DELETE FROM status_topic_student  where segment6 = ? AND segment5 = 'videos_tab'",
			    "DELETE FROM status_topic_personal where segment6 = ? AND segment5 = 'videos_tab'",
			};
		
		conectionWrapper = new ConectionWrapper();
		try {
			con = conectionWrapper.getConexion();
			con.setAutoCommit(false);
			PreparedStatement psmt = null;
			for (String query : sql) {
				psmt = con.prepareStatement(query);
				psmt.setInt(1, idVideo);
				psmt.executeUpdate();
			}
			con.setAutoCommit(true);
			
//			int[] resp = psmt.executeBatch();
//			for(Integer i : resp){
//				System.out.println(i);
//			}
			bandera = true;
		} catch (NamingException | SQLException e) {
			logger.error(e);
		}finally{
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					logger.error(e);
				}
			}
		}
		return bandera;		
	}
	
	public List<LaminasBean> dameLaminas(int topicId){
		LaminasBean laminasBean;
		List<LaminasBean> listLaminas = new ArrayList<>();
		conectionWrapper = new ConectionWrapper();
		String sql       =  "SELECT id_laminas,\n" +
							"    topic_id,\n" +
							"    nom_lamina,\n" +
							"    lamina,\n" +
							"    segment1,\n" +
							"    segment2,\n" +
							"    segment3,\n" +
							"    segment4,\n" +
							"    segment5,\n" +
							"    segment6,\n" +
							"    segment7,\n" +
							"    segment8,\n" +
							"    creation_date,\n" +
							"    last_update_date\n" +
							"FROM laminas_tab\n" +
							"WHERE topic_id = ?";
		 try {
			con = conectionWrapper.getConexion();
			 PreparedStatement psmt = con.prepareStatement(sql);
			 psmt.setInt(1, topicId);
			 ResultSet rs = psmt.executeQuery();
			 while(rs.next()){
				 laminasBean = new LaminasBean();
				 laminasBean.setId_laminas(rs.getInt(1));
				 laminasBean.setTopic_id(rs.getInt(2));
				 laminasBean.setNom_lamina(rs.getString(3));
				 laminasBean.setLamina(rs.getBlob(4));
				 laminasBean.setSegment1(rs.getString(5));
				 laminasBean.setSegment2(rs.getString(6));
				 laminasBean.setSegment3(rs.getString(7));
				 laminasBean.setSegment4(rs.getString(8));
				 laminasBean.setSegment5(rs.getString(9));
				 laminasBean.setSegment6(rs.getInt(10));
				 laminasBean.setSegment7(rs.getDate(11));
				 laminasBean.setSegment8(rs.getBlob(12));
				 laminasBean.setCreation_date(rs.getDate(13));
				 laminasBean.setLast_update_date(rs.getDate(14));
				 listLaminas.add(laminasBean);
			 }
		} catch (NamingException | SQLException e) {
			logger.error(e);
		}finally{
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					logger.error(e);
				}
			}
		}
		
		 return listLaminas;
	}

	public boolean eliminaLamina(int idLamina){
		boolean bandera = false;
		String [] sql = {
			    "DELETE FROM laminas_tab WHERE id_laminas = ?",
			    "DELETE FROM status_topic_student  where segment6 = ? AND segment5 = 'laminas_tab'",
			    "DELETE FROM status_topic_personal where segment6 = ? AND segment5 = 'laminas_tab'",
			};
		conectionWrapper = new ConectionWrapper();
		try {
			con = conectionWrapper.getConexion();
			con.setAutoCommit(false);
			PreparedStatement psmt = null;
			for (String query : sql) {
				psmt = con.prepareStatement(query);
				psmt.setInt(1, idLamina);
				psmt.executeUpdate();
			}
			con.setAutoCommit(true);
			bandera = true;
		} catch (NamingException | SQLException e) {
			logger.error(e);
		}finally{
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					logger.error(e);
				}
			}
		}
		return bandera;		
	}
	
	public List<PracticasBean> damePracticas(int topicId){
		PracticasBean practicasBean;
		List<PracticasBean> listPracticas = new ArrayList<>();
		conectionWrapper = new ConectionWrapper();
		String sql       =  "SELECT id_practicas,\n" +
							"    id_topic,\n" +
							"    nom_practica,\n" +
							"    nom_archivo,\n" +
							"    nom_video,\n" +
							"    link_video,\n" +
							"    lamina,\n" +
							"    practica_pdf,\n" +
							"    practica_video,\n" +
							"    id_content_type,\n" +
							"    segment1,\n" +
							"    segment2,\n" +
							"    segment3,\n" +
							"    segment4,\n" +
							"    segment5,\n" +
							"    segment6,\n" +
							"    segment7,\n" +
							"    segment8,\n" +
							"    creation_date,\n" +
							"    last_update_date,\n" +
							"    (SELECT tc.tipo_contenido\n" +
							"	 FROM tipo_contenido tc\n" +
							"     WHERE tc.id_tipo_contenido_tab = id_content_type) AS tipo_contenido\n" +
							"FROM practicas_tab\n" +
							"WHERE id_topic = ?";
		 try {
			con = conectionWrapper.getConexion();
			 PreparedStatement psmt = con.prepareStatement(sql);
			 psmt.setInt(1, topicId);
			 ResultSet rs = psmt.executeQuery();
			 while(rs.next()){
				 practicasBean = new PracticasBean();
				 practicasBean.setIdPracticas(rs.getInt(1));
				 practicasBean.setId_topic(rs.getInt(2));
				 practicasBean.setNomPractica(rs.getString(3));
				 practicasBean.setNomArchivo(rs.getString(4));
				 practicasBean.setNomVideo(rs.getString(5));
				 practicasBean.setLink_video(rs.getString(6));
				 practicasBean.setLamina(rs.getBlob(7));
				 practicasBean.setPracticaPdf(rs.getBlob(8));
				 practicasBean.setPracticaVideo(rs.getString(9));
				 practicasBean.setIdContentType(rs.getInt(10));		
				 practicasBean.setSegment1(rs.getString(11));
				 practicasBean.setSegment2(rs.getString(12));
				 practicasBean.setSegment3(rs.getString(13));
				 practicasBean.setSegment4(rs.getString(14));
				 practicasBean.setSegment5(rs.getString(15));
				 practicasBean.setSegment6(rs.getInt(16));
				 practicasBean.setSegment7(rs.getDate(17));
				 practicasBean.setSegment8(rs.getBlob(18));
				 practicasBean.setCreationDate(rs.getDate(19));
				 practicasBean.setLastUpdateDate(rs.getDate(20));
				 practicasBean.setTipoContendio(rs.getString(21));
				 listPracticas.add(practicasBean);
			 }
		} catch (NamingException | SQLException e) {
			logger.error(e);
		}finally{
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					logger.error(e);
				}
			}
		}
		
		 return listPracticas;
	}
	
	public boolean eliminaPractica(int idPractica){
		boolean bandera = false;
		String [] sql = {
			    "DELETE FROM practicas_tab WHERE id_practicas = ?",
			    "DELETE FROM status_topic_student  where segment6 = ? AND segment5 = 'practicas_tab'",
			    "DELETE FROM status_topic_personal where segment6 = ? AND segment5 = 'practicas_tab'",
			};
		conectionWrapper = new ConectionWrapper();
		try {
			con = conectionWrapper.getConexion();
			con.setAutoCommit(false);
			PreparedStatement psmt = null;
			for (String query : sql) {
				psmt = con.prepareStatement(query);
				psmt.setInt(1, idPractica);
				psmt.executeUpdate();
			}
			con.setAutoCommit(true);
			bandera = true;
			
		} catch (NamingException | SQLException e) {
			logger.error(e);
		}finally{
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					logger.error(e);
				}
			}
		}
		return bandera;		
	}
	
	public  Map<String,Integer> dameTipoContenido(){
		String sql = "SELECT id_tipo_contenido_tab,\n" +
					 "	     tipo_contenido\n" +
					 "FROM tipo_contenido";
		mapTipoContenido = new HashMap<String, Integer>();
		conectionWrapper = new ConectionWrapper();
		try {
			con = conectionWrapper.getConexion();
			PreparedStatement psmt = con.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			int tipoCont;
			String stipoCont;
			while(rs.next()){
				tipoCont  = rs.getInt(1);
				stipoCont = rs.getString(2);
				mapTipoContenido.put(stipoCont, tipoCont);
			}
		} catch (NamingException | SQLException e) {
			logger.error(e);
		}finally{
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					logger.error(e);
				}
			}
		}
		return mapTipoContenido;
	}
	
	public boolean guardaPracticaLamina(FileInputStream event,PracticasBean practicasBean){
		boolean bandera = false;
		String sql = "INSERT INTO practicas_tab\n" +
					"(id_topic,\n" +
					"nom_practica,\n" +
					"nom_archivo,\n" +
					"practica_pdf,\n" +
					"id_content_type,\n" +
					"creation_date)\n" +
					"VALUES\n" +
					"(?,\n" +
					"?,\n" +
					"?,\n" +
					"?,\n" +
					"?,\n" +
					"sysdate())";
		try {
			con = conectionWrapper.getConexion();
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setInt(1, practicasBean.getId_topic());
			psmt.setString(2, practicasBean.getNomPractica());
			psmt.setString(3, practicasBean.getNomArchivo());
			psmt.setBinaryStream(4,event);
			psmt.setInt(5,practicasBean.getIdContentType());
			psmt.executeUpdate();
			bandera = true;
		} catch (NamingException | SQLException e) {
			logger.error(e);
		}finally{
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					logger.error(e);
				}
			}
		}
		return bandera;
	}
	
	public boolean guardaPracticaVideo(PracticasBean practicasBean){
		boolean bandera = false;
		String sql = "INSERT INTO practicas_tab\n" +
					"(id_topic,\n" +
					"nom_practica,\n" +
					"link_video,\n" +
					"id_content_type,\n" +
					"creation_date)\n" +
					"VALUES\n" +
					"(?,\n" +
					"?,\n" +
					"?,\n" +
					"?,\n" +
					"sysdate())";
		try {
			con = conectionWrapper.getConexion();
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setInt(1, practicasBean.getId_topic());
			psmt.setString(2, practicasBean.getNomPractica());
			psmt.setString(3, practicasBean.getLink_video());
			psmt.setInt(4,practicasBean.getIdContentType());
			psmt.executeUpdate();
			bandera = true;
		} catch (NamingException | SQLException e) {
			logger.error(e);
		}finally{
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					logger.error(e);
				}
			}
		}
		return bandera;
	}
	
	
	public List<SolutionsBean> dameSoluciones(int topicId){
		SolutionsBean solutionsBean;
		List<SolutionsBean> listSoluciones = new ArrayList<>();
		conectionWrapper = new ConectionWrapper();
		String sql       =  "SELECT solutions_id,\n" +
							"    topic_id,\n" +
							"    solutions_name,\n" +
							"    id_content_type,\n" +
							"    link_video,\n" +
							"    solucion_pdf,\n" +
							"    segment1,\n" +
							"    segment2,\n" +
							"    segment3,\n" +
							"    segment4,\n" +
							"    segment5,\n" +
							"    segment6,\n" +
							"    segment7,\n" +
							"    segment8,\n" +
							"    creation_date,\n" +
							"    last_update_date,\n" +
							"    nom_archivo,\n"     +
							" (SELECT tc.tipo_contenido\n" +
										"	 FROM tipo_contenido tc\n" +
										"     WHERE tc.id_tipo_contenido_tab = id_content_type) AS tipo_contenido\n" +
							"FROM solutions_tab\n" +
							"WHERE topic_id = ?";
		 try {
			con = conectionWrapper.getConexion();
			 PreparedStatement psmt = con.prepareStatement(sql);
			 psmt.setInt(1, topicId);
			 ResultSet rs = psmt.executeQuery();
			 while(rs.next()){
				 solutionsBean = new SolutionsBean();
				 solutionsBean.setSolutionsId(rs.getInt(1));
				 solutionsBean.setTopicId(rs.getInt(2));
				 solutionsBean.setSolutionsName(rs.getString(3));
				 solutionsBean.setIdContentType(rs.getInt(4));
				 solutionsBean.setLinkVideo(rs.getString(5));
				 solutionsBean.setSolucionPdf(rs.getBlob(6));
				 solutionsBean.setSegment1(rs.getString(7));
				 solutionsBean.setSegment2(rs.getString(8));
				 solutionsBean.setSegment3(rs.getString(9));
				 solutionsBean.setSegment4(rs.getString(10));
				 solutionsBean.setSegment5(rs.getString(11));
				 solutionsBean.setSegment6(rs.getInt(12));
				 solutionsBean.setSegment7(rs.getDate(13));
				 solutionsBean.setSegment8(rs.getBlob(14));
				 solutionsBean.setCreation_date(rs.getDate(15));
				 solutionsBean.setLast_update_date(rs.getDate(16));
				 solutionsBean.setNomArchivo(rs.getString(17));
				 solutionsBean.setTipoContenido(rs.getString(18));
				 listSoluciones.add(solutionsBean);
			 }
		} catch (NamingException | SQLException e) {
			logger.error(e);
		}finally{
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					logger.error(e);
				}
			}
		}
		
		 return listSoluciones;
	}
	
	public boolean guardaSolucionLamina(FileInputStream event,SolutionsBean solutionsBean){
		boolean bandera = false;
		String sql = "INSERT INTO solutions_tab\n" +
					 "(topic_id,\n" +
					 "solutions_name,\n" +
					 "id_content_type,\n" +
					 "solucion_pdf,\n" +
					 "nom_archivo,\n" +
					 "creation_date)\n" +
					 "VALUES\n" +
					 "(?,?,?,?,?,sysdate())";
		try {
			con = conectionWrapper.getConexion();
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setInt(1, solutionsBean.getTopicId());
			psmt.setString(2, solutionsBean.getSolutionsName());
			psmt.setInt(3, solutionsBean.getIdContentType());
			psmt.setBinaryStream(4,event);
			psmt.setString(5,solutionsBean.getNomArchivo());
			psmt.executeUpdate();
			bandera = true;
		} catch (NamingException | SQLException e) {
			logger.error(e);
		}finally{
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					logger.error(e);
				}
			}
		}
		return bandera;
	}
	
	public boolean guardaSolucionVideo(SolutionsBean solutionsBean){
		boolean bandera = false;
		String sql = "INSERT INTO solutions_tab\n" +
					 "(topic_id,\n" +
					 "solutions_name,\n" +
					 "id_content_type,\n" +
					 "link_video,\n" +
					 "creation_date)\n" +
					 "VALUES\n" +
					 "(?,?,?,?,sysdate())";
		try {
			con = conectionWrapper.getConexion();
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setInt(1, solutionsBean.getTopicId());
			psmt.setString(2, solutionsBean.getSolutionsName());
			psmt.setInt(   3, solutionsBean.getIdContentType());
			psmt.setString(4, solutionsBean.getLinkVideo());
			psmt.executeUpdate();
			bandera = true;
		} catch (NamingException | SQLException e) {
			logger.error(e);
		}finally{
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					logger.error(e);
				}
			}
		}
		return bandera;
	}
	
	public boolean eliminaSolucion(int idSolucion){
		boolean bandera = false;
		String [] sql = {
			    "DELETE FROM solutions_tab WHERE solutions_id = ?",
			    "DELETE FROM status_topic_student  where segment6 = ? AND segment5 = 'solutions_tab'",
			    "DELETE FROM status_topic_personal where segment6 = ? AND segment5 = 'solutions_tab'",
			};
		conectionWrapper = new ConectionWrapper();
		try {
			con = conectionWrapper.getConexion();
			con.setAutoCommit(false);
			PreparedStatement psmt = null;
			for (String query : sql) {
				psmt = con.prepareStatement(query);
				psmt.setInt(1, idSolucion);
				psmt.executeUpdate();
			}
			con.setAutoCommit(true);
			bandera = true;
		} catch (NamingException | SQLException e) {
			logger.error(e);
		}finally{
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					logger.error(e);
				}
			}
		}
		return bandera;		
	}
	
	
	public String obtenLigaEvalPlus(int topicId){
		String linkEval = null;
		String sql = "SELECT evaluacion_tab_id,\n"+
					 "	    topic_id,\n"+
					 "	    link_evaluacion,\n"+
					 "	    segment1,\n"+
					 "	    segment2,\n"+
					 "	    segment3,\n"+
					 "	    segment4,\n"+
					 "	    segment5,\n"+
					 "	    segment6,\n"+
					 "	    segment7,\n"+
					 "	    segment8,\n"+
					 "	    creation_date,\n"+
					 "	    last_update_date\n"+
					 "	FROM evaluacion_tab\n"+
					 "	WHERE topic_id = ?";
		conectionWrapper = new ConectionWrapper();
		try {
			con = conectionWrapper.getConexion();
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setInt(1, topicId);
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()){
				linkEval = rs.getString(3);
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
		return linkEval;
	}
	
	public boolean guardaEvaluacionPlus(int topicId,String linkVideo){
		boolean bandera = false;
		String sql    = "SELECT COUNT(*) FROM evaluacion_tab WHERE topic_id = ?";
		String update = "UPDATE evaluacion_tab SET link_evaluacion = ?, last_update_date = sysdate() WHERE topic_id = ?";
		String insert = "INSERT INTO evaluacion_tab(topic_id,link_evaluacion,creation_date) VALUES(?,?,sysdate())";
			
			try {
				con = conectionWrapper.getConexion();
				PreparedStatement psmt = con.prepareStatement(sql);
				psmt.setInt(1, topicId);
				ResultSet rs = psmt.executeQuery();
				int count = 0;
				while(rs.next()){
					count = rs.getInt(1);
				}
				PreparedStatement psmt2 = null;
				if(count > 0){
					psmt2 = con.prepareStatement(update);
					psmt2.setString(1, linkVideo);
					psmt2.setInt(2, topicId);
					psmt2.executeUpdate();
				}else{
					psmt2 = con.prepareStatement(insert);
					psmt2.setInt(1, topicId);
					psmt2.setString(2, linkVideo);
					psmt2.executeUpdate();
				}
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
	
	
}

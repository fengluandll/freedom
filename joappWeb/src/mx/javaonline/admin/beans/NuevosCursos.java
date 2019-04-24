package mx.javaonline.admin.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.naming.NamingException;

import mx.javaonline.model.ConectionWrapper;

import org.apache.log4j.Logger;
import org.primefaces.event.RowEditEvent;

public class NuevosCursos {
	
	private static org.apache.log4j.Logger logger = Logger.getLogger(NuevosCursos.class);
	ConectionWrapper conectionWrapper;
	Connection con;
	CursosBean cursosBean;
	UnidadesBean unidadesBean;
	TopicsBean topicsBean;
	List<CursosBean> listCursosBean;
	List<UnidadesBean> listUnidadesBean;
	List<TopicsBean> listTopicBean;
	private SelectItem[] itemCursos;
	private SelectItem[] itemUnidades;
	private String nomCurso;
	private String liga;
	private String nomUnidad;
	private String objetivoUnidad;
	private int relacionUnidad;
	private String nomTopic;
	private String ligaTopic;
	private int relacionTopic;
	private String objetivoTema;
	private Map<String,Integer> cursos = new TreeMap<String,Integer>();
	private Map<String,Integer> unidades = new TreeMap<String,Integer>();
	List<CursosBean> listCursos;
	List<CursosBean> listUnidades;
	
	public NuevosCursos() {
		String[] arrayCursos   = listaCursos();
		String[] arrayUnidades = listaUnidades();
		listaUnidades();
		listaTopics();
		comboCursos();
		comboUnidades();		
		itemCursos   = createFilterOptions(arrayCursos);
		itemUnidades = createFilterOptions(arrayUnidades);
	}
	
    private SelectItem[] createFilterOptions(String[] data)  {
        SelectItem[] options = new SelectItem[data.length + 1];

        options[0] = new SelectItem("", "Select");
        for(int i = 0; i < data.length; i++) {
            options[i + 1] = new SelectItem(data[i], data[i]);
        }

        return options;
    }
    
	public void comboUnidades(){
		String sql = "SELECT unit_id,unit_name FROM units order by course_id";
		conectionWrapper = new ConectionWrapper();
		
		try {
			con = conectionWrapper.getConexion();
			PreparedStatement psmt = con.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			int unitId = 0;
			String unitName = "";
			while(rs.next()){
				unitId   = rs.getInt(1);
				unitName = rs.getString(2);
				unidades.put(unitName,unitId);
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
    
	public void comboCursos(){
		String sql = "SELECT course_id,course_name FROM courses order by course_name";
		conectionWrapper = new ConectionWrapper();
		
		try {
			con = conectionWrapper.getConexion();
			PreparedStatement psmt = con.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			int courseId = 0;
			String courseName = "";
			while(rs.next()){
				courseId   = rs.getInt(1);
				courseName = rs.getString(2);
				cursos.put(courseName,courseId);
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
	
	public void agregaTopic(){
		String sql = "INSERT INTO topics(topic_name,link,unit_id,segment1,creation_date) VALUES(?,?,?,?,sysdate())";
		
		conectionWrapper = new ConectionWrapper();	
		try {
			con = conectionWrapper.getConexion();
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, this.nomTopic);
			psmt.setString(2, this.ligaTopic);
			psmt.setInt(3, this.relacionTopic);
			psmt.setString(4, this.objetivoTema);
			psmt.executeUpdate();
			listaTopics();
			this.nomTopic = "";
			this.ligaTopic = "";
			this.objetivoTema = "";
			this.relacionTopic = 0;
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,
																				"Temada agregado","Exito"));
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
	public void agregaUnidad(){
		String sql = "INSERT INTO units(unit_name,course_id,segment1,creation_date) VALUES(?,?,?,sysdate())";
		
		conectionWrapper = new ConectionWrapper();
		try {
			con = conectionWrapper.getConexion();
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, this.nomUnidad);
			psmt.setInt(2, this.relacionUnidad);
			psmt.setString(3, this.objetivoUnidad);
			psmt.executeUpdate();
			listaUnidades();
			this.nomUnidad = "";
			this.objetivoUnidad = "";
			this.relacionUnidad = 0;
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,
																				"Unidad agregada","Exito"));
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
	
	public void listaTopics(){
		listTopicBean = new ArrayList<>();
		String sql = "SELECT  t.topic_id,\n" +
					"		t.topic_name,\n" +
					"		t.link,\n" +
					"		t.unit_id,\n" +
					"		t.segment1,\n" +
					"		t.segment2,\n" +
					"		t.segment3,\n" +
					"		t.segment4,\n" +
					"		t.segment5,\n" +
					"		t.segment6,\n" +
					"		t.segment7,\n" +
					"		t.segment8,\n" +
					"		t.creation_date,\n" +
					"		t.end_date,\n" +
					"	    u.unit_name\n" +
					"FROM topics t,\n" +
					"	units u	 \n" +
					"WHERE u.unit_id = t.unit_id";
		conectionWrapper = new ConectionWrapper();
		
		try {
			con = conectionWrapper.getConexion();
			PreparedStatement psmt = con.prepareStatement(sql);
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
				topicsBean.setCreationDate(rs.getDate(13));
				topicsBean.setEndDate(rs.getDate(14));
				topicsBean.setUnitName(rs.getString(15));
				listTopicBean.add(topicsBean);
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
	
	public String[] listaUnidades(){
		listUnidadesBean = new ArrayList<>();
		String sql = "SELECT u.unit_id, \n "+
					 "	    u.unit_name, \n "+
					 "	    u.link, \n "+
					 "	    u.course_id, \n "+
					 "	    u.segment1, \n "+
					 "	    u.segment2, \n "+
					 "	    u.segment3, \n "+
					 "	    u.segment4, \n "+
					 "      u.segment5, \n "+
					 "	    u.segment6, \n "+
					 "	    u.segment7, \n "+
					 "	    u.segment8, \n "+
					 "	    u.creation_date, \n "+
					 "	    u.end_date ,\n "+
					 "	    c.course_name \n "+
					 "	FROM units u,courses c WHERE u.course_id = c.course_id";
		conectionWrapper = new ConectionWrapper();
		
		try {
			con = conectionWrapper.getConexion();
			PreparedStatement psmt = con.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()){
				unidadesBean = new UnidadesBean();
				unidadesBean.setUnitId(rs.getInt(1));
				unidadesBean.setUnitName(rs.getString(2));
				unidadesBean.setLink(rs.getString(3));
				unidadesBean.setCourseId(rs.getInt(4));
				unidadesBean.setSegment1(rs.getString(5));
				unidadesBean.setSegment2(rs.getString(6));
				unidadesBean.setSegment3(rs.getString(7));
				unidadesBean.setSegment4(rs.getString(8));
				unidadesBean.setSegment5(rs.getString(9));
				unidadesBean.setSegment6(rs.getInt(10));
				unidadesBean.setSegment7(rs.getDate(11));
				unidadesBean.setSegment8(rs.getBlob(12));
				unidadesBean.setCreation_date(rs.getDate(13));
				unidadesBean.setEnd_date(rs.getDate(14));
				unidadesBean.setCourseName(rs.getString(15));
				listUnidadesBean.add(unidadesBean);
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
		
		String[] arrayUnidades = new String[listUnidadesBean.size()];
		for(int i = 0;i < listUnidadesBean.size();i++){
			arrayUnidades[i] = listUnidadesBean.get(i).getUnitName();
		}
		return arrayUnidades;
		
	}
	
	public String[] listaCursos(){
		listCursosBean = new ArrayList<>();
		String sql = "SELECT course_id,\n" +
					"    course_name,\n" +
					"    link,\n" +
					"    segment1,\n" +
					"    segment2,\n" +
					"    segment3,\n" +
					"    segment4,\n" +
					"    segment5,\n" +
					"    segment6,\n" +
					"    segment7,\n" +
					"    creation_date,\n" +
					"    end_date\n" +
					"FROM courses";
		conectionWrapper = new ConectionWrapper();
		try {
			con = conectionWrapper.getConexion();
			PreparedStatement psmt = con.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()){
				cursosBean = new CursosBean();
				cursosBean.setCourseId(rs.getInt(1));
				cursosBean.setCourseName(rs.getString(2));
				cursosBean.setLink(rs.getString(3));
				cursosBean.setSegment1(rs.getString(4));
				cursosBean.setSegment2(rs.getString(5));
				cursosBean.setSegment3(rs.getString(6));
				cursosBean.setSegment4(rs.getString(7));
				cursosBean.setSegment5(rs.getString(8));
				cursosBean.setSegment6(rs.getInt(9));
				cursosBean.setSegment7(rs.getDate(10));
				cursosBean.setCreationDate(rs.getDate(11));
				cursosBean.setEndDate(rs.getDate(12));
				listCursosBean.add(cursosBean);
				
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
		String[] arrayCursos = new String[listCursosBean.size()];
		for(int i = 0;i < listCursosBean.size();i++){
			arrayCursos[i] = listCursosBean.get(i).getCourseName();
		}
		return arrayCursos;
	}
	
	public void agregaCurso(){
		String sql = "INSERT INTO courses(course_name,link,creation_date) VALUES(?,?,sysdate())";
		
		conectionWrapper = new ConectionWrapper();
		try {
			con = conectionWrapper.getConexion();
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, this.nomCurso);
			psmt.setString(2, this.liga);
			psmt.executeUpdate();
			listaCursos();
			this.nomCurso = "";
			this.liga = "";
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,
																				"Curso agregado","Exito"));
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
	public void onEditTopic(RowEditEvent event){
		TopicsBean c = (TopicsBean) event.getObject();
//    	logger.debug("c.getCourseName() "+c.getTopicId());
//    	logger.debug("c.getCourseName() "+c.getTopicName());
//    	logger.debug("c.getCourseName() "+c.getLink());
//    	logger.debug("c.getCourseName() "+c.getSegment1());
//    	logger.debug("c.getCourseName() "+c.getSegment2());
//    	logger.debug("c.getCourseName() "+c.getSegment3());
//    	logger.debug("c.getCourseName() "+c.getSegment4());
//    	logger.debug("c.getCourseName() "+c.getSegment5());
//    	logger.debug("c.getCourseName() "+c.getSegment6());
    	String update = "UPDATE topics SET \n" +
    						" topic_name = ?,\n "+
							" link        = ?,\n "+
							" segment1    = ?,\n "+
							" segment2    = ?,\n "+
							" segment3    = ?,\n "+
							" segment4    = ?,\n "+
							" segment5    = ?,\n "+
							" segment6    = ? \n" +
						" WHERE topic_id = ? ";
    	conectionWrapper = new ConectionWrapper();
    	try {
			con = conectionWrapper.getConexion();
			PreparedStatement pstmt = con.prepareStatement(update);
			pstmt.setString(1, c.getTopicName());
			pstmt.setString(2, c.getLink());
			pstmt.setString(3, c.getSegment1());
			pstmt.setString(4, c.getSegment2());
			pstmt.setString(5, c.getSegment3());
			pstmt.setString(6, c.getSegment4());
			pstmt.setString(7, c.getSegment5());
			pstmt.setInt(8, c.getSegment6());
			pstmt.setInt(9, c.getTopicId());
			pstmt.executeUpdate();
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Tema actualizado","Exito"));
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
	
	public void onEditUnits(RowEditEvent event){
		UnidadesBean c = (UnidadesBean) event.getObject();
//    	logger.debug("c.getCourseName() "+c.getUnitId());
//    	logger.debug("c.getCourseName() "+c.getUnitName());
//    	logger.debug("c.getCourseName() "+c.getLink());
//    	logger.debug("c.getCourseName() "+c.getSegment1());
//    	logger.debug("c.getCourseName() "+c.getSegment2());
//    	logger.debug("c.getCourseName() "+c.getSegment3());
//    	logger.debug("c.getCourseName() "+c.getSegment4());
//    	logger.debug("c.getCourseName() "+c.getSegment5());
//    	logger.debug("c.getCourseName() "+c.getSegment6());
    	
    	String update = "UPDATE units SET \n" +
    						" unit_name = ?,\n "+
							" link        = ?,\n "+
							" segment1    = ?,\n "+
							" segment2    = ?,\n "+
							" segment3    = ?,\n "+
							" segment4    = ?,\n "+
							" segment5    = ?,\n "+
							" segment6    = ? \n" +
						" WHERE unit_id = ? ";
    	conectionWrapper = new ConectionWrapper();
    	try {
			con = conectionWrapper.getConexion();
			PreparedStatement pstmt = con.prepareStatement(update);
			pstmt.setString(1, c.getUnitName());
			pstmt.setString(2, c.getLink());
			pstmt.setString(3, c.getSegment1());
			pstmt.setString(4, c.getSegment2());
			pstmt.setString(5, c.getSegment3());
			pstmt.setString(6, c.getSegment4());
			pstmt.setString(7, c.getSegment5());
			pstmt.setInt(8, c.getSegment6());
			pstmt.setInt(9, c.getUnitId());
			pstmt.executeUpdate();
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Unidad actualizado","Exito"));
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
    public void onEdit(RowEditEvent event) {
    	CursosBean c = (CursosBean) event.getObject();
//    	logger.debug("c.getCourseName() "+c.getCourseId());
//    	logger.debug("c.getCourseName() "+c.getCourseName());
//    	logger.debug("c.getCourseName() "+c.getLink());
//    	logger.debug("c.getCourseName() "+c.getSegment1());
//    	logger.debug("c.getCourseName() "+c.getSegment2());
//    	logger.debug("c.getCourseName() "+c.getSegment3());
//    	logger.debug("c.getCourseName() "+c.getSegment4());
//    	logger.debug("c.getCourseName() "+c.getSegment5());
//    	logger.debug("c.getCourseName() "+c.getSegment6());
    	String update = "UPDATE courses SET \n" +
							" course_name = ?,\n "+
							" link        = ?,\n "+
							" segment1    = ?,\n "+
							" segment2    = ?,\n "+
							" segment3    = ?,\n "+
							" segment4    = ?,\n "+
							" segment5    = ?,\n "+
							" segment6    = ? \n" +
						" WHERE course_id = ? ";
    	conectionWrapper = new ConectionWrapper();
    	try {
			con = conectionWrapper.getConexion();
			PreparedStatement pstmt = con.prepareStatement(update);
			pstmt.setString(1, c.getCourseName());
			pstmt.setString(2, c.getLink());
			pstmt.setString(3, c.getSegment1());
			pstmt.setString(4, c.getSegment2());
			pstmt.setString(5, c.getSegment3());
			pstmt.setString(6, c.getSegment4());
			pstmt.setString(7, c.getSegment5());
			pstmt.setInt(8, c.getSegment6());
			pstmt.setInt(9, c.getCourseId());
			pstmt.executeUpdate();
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Curso actualizado","Exito"));
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
     
    public void onCancel(RowEditEvent event) {
        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Edicion cancelada","Cancelación"));
    }
 
       

	public List<CursosBean> getListCursosBean() {
		return listCursosBean;
	}

	public void setListCursosBean(List<CursosBean> listCursosBean) {
		this.listCursosBean = listCursosBean;
	}

	public String getNomCurso() {
		return nomCurso;
	}

	public void setNomCurso(String nomCurso) {
		this.nomCurso = nomCurso;
	}

	public String getLiga() {
		return liga;
	}

	public void setLiga(String liga) {
		this.liga = liga;
	}

	public String getNomUnidad() {
		return nomUnidad;
	}

	public void setNomUnidad(String nomUnidad) {
		this.nomUnidad = nomUnidad;
	}

	public String getObjetivoUnidad() {
		return objetivoUnidad;
	}

	public void setObjetivoUnidad(String objetivoUnidad) {
		this.objetivoUnidad = objetivoUnidad;
	}

	public int getRelacionUnidad() {
		return relacionUnidad;
	}

	public void setRelacionUnidad(int relacionUnidad) {
		this.relacionUnidad = relacionUnidad;
	}
	public Map<String, Integer> getCursos() {
		return cursos;
	}
	public void setCursos(Map<String, Integer> cursos) {
		this.cursos = cursos;
	}


	public List<UnidadesBean> getListUnidadesBean() {
		return listUnidadesBean;
	}


	public void setListUnidadesBean(List<UnidadesBean> listUnidadesBean) {
		this.listUnidadesBean = listUnidadesBean;
	}

	public SelectItem[] getItemCursos() {
		return itemCursos;
	}

	public void setItemCursos(SelectItem[] itemCursos) {
		this.itemCursos = itemCursos;
	}

	public List<CursosBean> getListCursos() {
		return listCursos;
	}

	public void setListCursos(List<CursosBean> listCursos) {
		this.listCursos = listCursos;
	}

	public String getNomTopic() {
		return nomTopic;
	}

	public void setNomTopic(String nomTopic) {
		this.nomTopic = nomTopic;
	}

	public String getLigaTopic() {
		return ligaTopic;
	}

	public void setLigaTopic(String ligaTopic) {
		this.ligaTopic = ligaTopic;
	}

	public int getRelacionTopic() {
		return relacionTopic;
	}

	public void setRelacionTopic(int relacionTopic) {
		this.relacionTopic = relacionTopic;
	}

	public String getObjetivoTema() {
		return objetivoTema;
	}

	public void setObjetivoTema(String objetivoTema) {
		this.objetivoTema = objetivoTema;
	}

	public Map<String, Integer> getUnidades() {
		return unidades;
	}

	public void setUnidades(Map<String, Integer> unidades) {
		this.unidades = unidades;
	}

	public List<CursosBean> getListUnidades() {
		return listUnidades;
	}

	public void setListUnidades(List<CursosBean> listUnidades) {
		this.listUnidades = listUnidades;
	}

	public SelectItem[] getItemUnidades() {
		return itemUnidades;
	}

	public void setItemUnidades(SelectItem[] itemUnidades) {
		this.itemUnidades = itemUnidades;
	}

	public List<TopicsBean> getListTopicBean() {
		return listTopicBean;
	}

	public void setListTopicBean(List<TopicsBean> listTopicBean) {
		this.listTopicBean = listTopicBean;
	}

	
	

}

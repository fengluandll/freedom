package mx.javaonline.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import mx.javaonline.model.ConectionWrapper;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

public class ArbolCursosBean {
	
	private static org.apache.log4j.Logger logger = Logger.getLogger(ArbolCursosBean.class);
	ResourceBundle bundle = ResourceBundle.getBundle("DatosGenerales");
	ConectionWrapper conectionWrapper;
	FacesContext facesContext = FacesContext.getCurrentInstance();
	HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
	CoursesBean coursesBean;
	UnitsBean unitsBean;
	TopicsBean topicsBean;
	private TreeNode root;
	private List<CoursesBean> listCourses;
	private List<UnitsBean> listUnits;
	private List<TopicsBean> listTopics;
	Connection con;
	private String pageLink;
	private TreeNode selectedNode;
	
	
	public void entroError(){
		logger.info("Ello");
	}


	public ArbolCursosBean() {
		listCourses = new ArrayList<>();
		conectionWrapper = new ConectionWrapper();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		StudentPersonalBean studentPersonalBean = (StudentPersonalBean)session.getAttribute("studentPersonalBean");
//Se pone diferente a null porque al caducar la session la pone en null y despues entra en este metodo arrojando una exception
	if(studentPersonalBean != null){
		
		if(studentPersonalBean.getSegment1().equals("S")){
			boolean tieneCurso = cursosEstudiantes(studentPersonalBean.getStudentPersonalId());
			if(tieneCurso == false){
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								         bundle.getString("no.tiene.cursos"),
								         bundle.getString("mensaje.ayuda")));
			}
		}else if(studentPersonalBean.getSegment1().equals("P") || studentPersonalBean.getSegment1().equals("A")){
			boolean tieneCurso = cursosPersonal(studentPersonalBean.getStudentPersonalId());
			if(tieneCurso == false){
				FacesContext.getCurrentInstance().addMessage("mensajeSinCursos",
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								 		 bundle.getString("no.tiene.cursos"),
								         bundle.getString("mensaje.ayuda")));
			}
		}
	}
		}
	
	/**
	 * 
	 * @param studenPersonalId id del estudiante
	 * Metodo que obtiene los cursos registrados de un estudiante
	 */
	private boolean cursosEstudiantes(int studenPersonalId){

		boolean tieneCurso = false;
		try {
			con = conectionWrapper.getConexion();
			String sql = "SELECT sc.studentl_courses_id, \n"+
								 "sc.student_id, \n"+
								 "c.course_id, \n"+
								 "c.course_name, \n"+
								 "c.link, \n"+
								 "c.descripcion, \n"+
								 "c.segment3, \n"+
								 "c.segment4, \n"+
								 "c.segment5, \n"+
								 "c.segment6, \n"+
								 "c.segment7, \n"+
								 "c.segment8 \n"+
						"FROM student_courses sc, \n"+
						"	   courses c	\n"+
						"WHERE sc.course_id = c.course_id\n"+
						"AND student_id = ? \n";
			
			PreparedStatement psmt =  con.prepareStatement(sql);
			psmt.setInt(1,studenPersonalId);
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()){
				coursesBean = new CoursesBean();
				coursesBean.setStudentlCoursesId(rs.getInt(1));
				coursesBean.setStudentId(rs.getInt(2));
				coursesBean.setCourseId(rs.getInt(3));
				coursesBean.setCourseName(rs.getString(4));
				coursesBean.setLink(rs.getString(5));
				coursesBean.setSegment2(rs.getString(6));
				coursesBean.setSegment3(rs.getString(7));
				coursesBean.setSegment4(rs.getString(8));
				coursesBean.setSegment5(rs.getString(9));
				coursesBean.setSegment6(rs.getString(10));
				coursesBean.setSegment7(rs.getString(11));
				coursesBean.setSegment8(rs.getString(12));
				listCourses.add(coursesBean);
			} 
			if(listCourses.size() > 0){
				tieneCurso = true;
			}
		   }catch (NamingException | SQLException e) {
				logger.error(e);
			}finally{
				try {
					con.close();
				} catch (SQLException e) {
					logger.error(e);
				}
		
		
			}
		return tieneCurso;
	}
	
	/**
	 * 
	 * @param studenPersonalId id del personal
	 * Metodo que obtiene los cursos registrados de un personal
	 */
	private boolean cursosPersonal(int studenPersonalId){
		logger.debug("studenPersonalId "+ studenPersonalId);
		boolean tieneCurso = false;
		try {
			con = conectionWrapper.getConexion();
			String sql = "SELECT pc.personal_courses_id, \n"+
								 "pc.personal_id, \n"+
								 "c.course_id, \n"+
								 "c.course_name, \n"+
								 "c.link \n"+
						"FROM personal_courses pc, \n"+
						"	   courses c	\n"+
						"WHERE pc.course_id = c.course_id\n"+
						"AND pc.personal_id = ? \n";
			
			PreparedStatement psmt =  con.prepareStatement(sql);
			psmt.setInt(1,studenPersonalId);
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()){
				coursesBean = new CoursesBean();
				coursesBean.setStudentlCoursesId(rs.getInt(1));
				coursesBean.setStudentId(rs.getInt(2));
				coursesBean.setCourseId(rs.getInt(3));
				coursesBean.setCourseName(rs.getString(4));
				coursesBean.setLink(rs.getString(5));
				listCourses.add(coursesBean);
			} 
			if(listCourses.size() > 0){
				tieneCurso = true;
			}
		   }catch (NamingException | SQLException e) {
				logger.error(e);
			}finally{
				try {
					con.close();
				} catch (SQLException e) {
					logger.error(e);
				}
		
			}
		return tieneCurso;
	}
	
	/**
	 * 
	 * @param cursoId id del curso a mostrar
	 * Metodo que obtiene los cursos registrados de un estudiante
	 */
	public TreeNode cursosEstudiantes2(int cursoId){
		
			return creaArbolCursos2(cursoId);
	}
	
	private TreeNode creaArbolCursos2(int courseId){
		listUnits = new ArrayList<>();
		root = new DefaultTreeNode(listUnits, null);
		
				
		try {
			con = conectionWrapper.getConexion();
			String sql = "SELECT unit_id, \n" +    
							"	 unit_name, \n" + 
							"	 link, \n" +       
							"	 course_id \n" +   
					     "FROM units\n" +          
					     "WHERE course_id = ?";
			String sql2 = "SELECT topic_id, \n" +   
						  "		 topic_name, \n" + 
						  "		 link, \n" +       
						  "		 unit_id  \n" +    
						  "FROM   topics	 \n" + 	
						  "WHERE unit_id = ?";
			
			PreparedStatement psmt =  con.prepareStatement(sql);
			psmt.setInt(1,courseId);
			PreparedStatement psmt2;
			ResultSet rs = psmt.executeQuery();
			ResultSet rs2;
			
			while(rs.next()){
				listTopics = new ArrayList<>();
				unitsBean = new UnitsBean();
				unitsBean.setUnitId(rs.getInt(1));
				unitsBean.setUnitName(rs.getString(2));
				unitsBean.setLink(rs.getString(3));
				unitsBean.setCourseId(rs.getInt(4));
				psmt2 =  con.prepareStatement(sql2);
				psmt2.setInt(1,unitsBean.getUnitId());
				rs2 = psmt2.executeQuery();
				while(rs2.next()){
					topicsBean = new TopicsBean();
					topicsBean.setTopicId(rs2.getInt(1));
					topicsBean.setTopicName(rs2.getString(2));
					topicsBean.setLink(rs2.getString(3));
					topicsBean.setUnitId(rs2.getInt(4));		
					listTopics.add(topicsBean);
					
				}
				unitsBean.setListTopicName(listTopics);
				listUnits.add(unitsBean);
			}
			
			
		   }catch (NamingException | SQLException e) {
				logger.error(e);
			}finally{
				try {
					con.close();
				} catch (SQLException e) {
					logger.error(e);
				}
				
				
		
			}
		//TreeNode node0 = new DefaultTreeNode(listUnits, root);
		TreeNode node0;
		for(UnitsBean un : listUnits){
			
			node0 = new DefaultTreeNode(un.getUnitName()+"|"+un.getLink(), root);
			for(TopicsBean top : un.getListTopicName()){
				//logger.debug("top.getTopicName() "+top.getTopicName());
				//new DefaultTreeNode(top.getTopicName()+"|"+top.getLink()+"|"+top.getUnitId(), node0);
//				logger.debug("liga debe ser " + top.getLink());
				new DefaultTreeNode(top.getTopicName()+"|"+"/joappWeb/TopicCarouselServlet?unitId="+top.getUnitId()
									+"&topicIdArbol="+top.getTopicId() + "&link="+ top.getLink(), node0);
			}
			
		}
	
	return root;
	
	}
	
	public void onTabChange(TabChangeEvent event){
		CoursesBean coursesBean = (CoursesBean)event.getData();
		session.removeAttribute("courseId");
		session.setAttribute("courseId",coursesBean.getCourseId());
		logger.debug("courseId*** "+coursesBean.getCourseId());
		logger.debug("Linkk*** "+coursesBean.getLink());
		this.setPageLink(coursesBean.getLink());
	}
	
	 public void onNodeSelect(NodeSelectEvent event) {
		 String nomUniTopic = event.getTreeNode().toString();
		 logger.debug("nomUniTopic "+nomUniTopic);
		 String link = StringUtils.substringAfterLast(nomUniTopic, "|");
		 logger.debug("link "+link);
		 this.setPageLink(link);
	    }
	 public void cargaLiga(String path){
		 logger.debug("Liga del metodo cargaLiga "+path);
		 this.setPageLink(path);
	 }
		
	
	
	
	public TreeNode getSelectedNode() {
		return selectedNode;
	}

	public void setSelectedNode(TreeNode selectedNode) {
		this.selectedNode = selectedNode;
	}
	
	public String getPageLink() {
		if(pageLink == null){
			//this.pageLink = "/joappWeb/home/antesIniciar.jsp";
			this.pageLink = "/joappWeb/home/dashCursos.xhtml";
			
		}
		return pageLink;
	}

	public void setPageLink(String pageLink) {
		
		this.pageLink = pageLink;
	}

	public List<CoursesBean> getListCourses() {
		return listCourses;
	}


	public void setListCourses(List<CoursesBean> listCourses) {
		this.listCourses = listCourses;
	}
	
	public TreeNode getRoot() {
		return root;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}

}

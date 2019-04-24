package mx.javaonline.dasboard.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import mx.javaonline.model.ConectionWrapper;

@ManagedBean
@ViewScoped
public class DashJavaBasicoBean {
	
	private static org.apache.log4j.Logger logger = Logger.getLogger(DashJavaBasicoBean.class);
	FacesContext facesContext = FacesContext.getCurrentInstance();
	ConectionWrapper conectionWrapper;
	Connection con;
	CursoUnidadBean cursoUnidadBean;
	List<CursoUnidadBean> listCurUni;
	

	public DashJavaBasicoBean() {
		 int courseId = 0;
		conectionWrapper = new ConectionWrapper();
		listCurUni = new ArrayList<>();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		if(session.getAttribute("courseId") != null) {
			courseId = (int)session.getAttribute("courseId");
		}
		
		try {
			con = conectionWrapper.getConexion();
		   String sql = "SELECT c.course_id, \n"+
						   "c.course_name, \n"+
						   "c.link ligaCurso, \n"+
						   "u.unit_id, \n"+
						   "u.unit_name,\n"+
						   "u.segment1 objetivo_unidad, \n"+
						   "u.segment2, \n"+
						   "u.segment3, \n"+
						   "u.link ligaUnidad \n"+
					"FROM  courses c, \n"+
					"     units   u \n"+
					"WHERE u.course_id = c.course_id \n"+
					"AND   c.course_id = ? \n";
		PreparedStatement psmt =  con.prepareStatement(sql);
		psmt.setInt(1,courseId);
		ResultSet rs = psmt.executeQuery();
		
		while(rs.next()){
			cursoUnidadBean = new CursoUnidadBean();
//			cursoUnidadBean.setStudentPersonalId(rs.getInt(1));
			cursoUnidadBean.setCourseId(rs.getInt(1));
			cursoUnidadBean.setCourseName(rs.getString(2));
			cursoUnidadBean.setLigaCurso(rs.getString(3));
			cursoUnidadBean.setUnitId(rs.getInt(4));
			cursoUnidadBean.setUnitName(rs.getString(5));
			cursoUnidadBean.setSegment1(rs.getString(6));
			cursoUnidadBean.setSegment2(rs.getString(7));
			cursoUnidadBean.setSegment3(rs.getString(8));
			cursoUnidadBean.setLigaUnidad(rs.getString(9));
			listCurUni.add(cursoUnidadBean);
			
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


	public List<CursoUnidadBean> getListCurUni() {
		return listCurUni;
	}


	public void setListCurUni(List<CursoUnidadBean> listCurUni) {
		this.listCurUni = listCurUni;
	}
	
	
	
}

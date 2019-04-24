/**
 * @Jose de Jesus Argumedo Quiroz
 */
package mx.javaonline.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import org.apache.log4j.Logger;

import mx.javaonline.beans.TopicsBean;
import mx.javaonline.dasboard.beans.CursoUnidadBean;
import mx.javaonline.model.ConectionWrapper;

public class TopicsDAO {
	
	private org.apache.log4j.Logger logger = Logger.getLogger(TopicsDAO.class);
	ConectionWrapper conectionWrapper;
	Connection con;
	List<CursoUnidadBean> listCurUni;
	CursoUnidadBean cursoUnidadBean;
	List<TopicsBean> listTopics;
	TopicsBean topicsBean;
	ResultSet rs;
	
	public TopicsDAO() {
		conectionWrapper = new ConectionWrapper();
	}

	public List<TopicsBean> dameTemas(int unitId) {
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
		   		+"                evaluacion_tab_id,"
		   		+"                segment1,"
		   		+"                duracion"
		   		+ "        FROM topics_all_view where unit_id = ? ORDER BY topic_id";
		   
			 psmt = con.prepareStatement(sql);
			 psmt.setInt(1,unitId);
		
			 rs = psmt.executeQuery();
		
			while(rs.next()){
				topicsBean = new TopicsBean();
				topicsBean.setTopicId(rs.getInt(1));
				topicsBean.setTopicName(rs.getString(2));
				topicsBean.setLink(rs.getString(3));
				topicsBean.setLinkEvaluacion(rs.getString(4));
				topicsBean.setUnitId(rs.getInt(5));
				topicsBean.setEvaluacion_tab_id(rs.getInt(6));
				topicsBean.setSegment1(rs.getString(7));		
				topicsBean.setDuracion(rs.getString(8));
				listTopics.add(topicsBean);
				
			}
			//session.removeAttribute("listTopics");
			//session.setAttribute("listTopics", listTopics);
				
			} catch (NamingException | SQLException e) {
				logger.error(e);
			}finally{
				try {
					psmt.close();
					rs.close();
					con.close();
				} catch (SQLException e) {
					logger.error(e);
				}
			}
		return listTopics;
	}
}

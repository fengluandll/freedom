package mx.javaonline.controlador;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import mx.javaonline.beans.StudentPersonalBean;
import mx.javaonline.model.ConectionWrapper;

import org.apache.log4j.Logger;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;  
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

public class FileDownloadController {
	
	private static org.apache.log4j.Logger logger = Logger.getLogger(FileDownloadController.class);
	ConectionWrapper conectionWrapper;
	Connection con;
	private StreamedContent file; 
	FacesContext facesContext = FacesContext.getCurrentInstance();
	HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
	private String linkPractic2;
	private String linkPractic3;

	public FileDownloadController() {
		//getPractica2();
		//getPractica3();
	}
	
	public void getPractica2(){
		
		String topicId = (String)session.getAttribute("topicId");
		conectionWrapper = new ConectionWrapper();
		String sql = "SELECT practica2 FROM content_topics WHERE topic_id = ?";
		try {
			con = conectionWrapper.getConexion();
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setInt(1, Integer.parseInt(topicId));
			ResultSet rs = psmt.executeQuery();
			while(rs.next()){
				linkPractic2 = rs.getString(1);
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

public void getPractica3(){
		
		String topicId = (String)session.getAttribute("topicId");
		conectionWrapper = new ConectionWrapper();
		String sql = "SELECT practica3 FROM content_topics WHERE topic_id = ?";
		try {
			con = conectionWrapper.getConexion();
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setInt(1, Integer.parseInt(topicId));
			ResultSet rs = psmt.executeQuery();
			while(rs.next()){
				linkPractic3 = rs.getString(1);
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

	public StreamedContent getArchivo(String accion){
		StudentPersonalBean studentPersonalBean = (StudentPersonalBean)session.getAttribute("studentPersonalBean");
		String topicId = (String)session.getAttribute("topicId");
		String nomArchivo = "";
		InputStream inStream = null;
		try {
			conectionWrapper = new ConectionWrapper();
			con = conectionWrapper.getConexion();
			String sql = "";
			if(accion.equals("lamina")){
				sql =   "SELECT ct.lamina, \n"+
						 "	     t.topic_name \n"+
						 " FROM  topics t, \n"+
						 "	  content_topics ct \n"+
						 " WHERE ct.topic_id = ? \n"+
						 " AND ct.topic_id = t.topic_id";
			}else if(accion.equals("practica")){
				sql =   "SELECT ct.practica, \n"+
						 "	     t.topic_name \n"+
						 " FROM  topics t, \n"+
						 "	  content_topics ct \n"+
						 " WHERE ct.topic_id = ? \n"+
						 " AND ct.topic_id = t.topic_id";
				
			}else if(accion.equals("solucion")){
				sql =   "SELECT ct.solucion, \n"+
						 "	     t.topic_name \n"+
						 " FROM  topics t, \n"+
						 "	  content_topics ct \n"+
						 " WHERE ct.topic_id = ? \n"+
						 " AND ct.topic_id = t.topic_id";
			}
			
		PreparedStatement psmt =  con.prepareStatement(sql);
//		logger.debug("select "+sql);
		psmt.setInt(1,Integer.parseInt(topicId));
		ResultSet rs = psmt.executeQuery();
		
		while(rs.next()){
			inStream = rs.getBinaryStream(1);
			nomArchivo = rs.getString(2);
		}
		CallableStatement cstmtCall = con.prepareCall("{CALL insert_update_status_topic_pr(?,?,?,?,?)}");
		cstmtCall.setInt(1,studentPersonalBean.getStudentPersonalId());
		cstmtCall.setInt(2,Integer.parseInt(topicId));
		cstmtCall.setString(3,studentPersonalBean.getSegment1());
		cstmtCall.setString(4,accion);
		cstmtCall.registerOutParameter(5, Types.VARCHAR);
		cstmtCall.execute();
		file = new DefaultStreamedContent(inStream, "pdf", nomArchivo);  
		} catch (NamingException | SQLException e) {
			logger.error(e);
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				logger.error(e);
			}
		}
	  
	  return file;
	}
	
	public StreamedContent getArchivoPlus(String topicId,String accion,String idContent,String tipCont){
		StudentPersonalBean studentPersonalBean = (StudentPersonalBean)session.getAttribute("studentPersonalBean");
//		String topicId = (String)session.getAttribute("topicId");
		String nomArchivo = "";
		InputStream inStream = null;
		try {
			conectionWrapper = new ConectionWrapper();
			con = conectionWrapper.getConexion();
			PreparedStatement psmt = null;
			String sql = "";
			if(accion.equals("lamina")){
				sql =   "SELECT lamina,nom_lamina FROM laminas_tab WHERE topic_id = ?";
				psmt =  con.prepareStatement(sql);
				psmt.setInt(1,Integer.parseInt(topicId));
			}else if(accion.equals("laminaPractica")){
				sql =   "select practica_pdf,nom_archivo from practicas_tab WHERE id_practicas = ?";
				psmt =  con.prepareStatement(sql);
				psmt.setInt(1,Integer.parseInt(idContent));
			}else if(accion.equals("laminaSolucion")){
				sql =   "select solucion_pdf,nom_archivo from solutions_tab WHERE solutions_id = ?";
				psmt =  con.prepareStatement(sql);
				psmt.setInt(1,Integer.parseInt(idContent));
			}
			
		
		
		ResultSet rs = psmt.executeQuery();
		
		while(rs.next()){
			inStream = rs.getBinaryStream(1);
			nomArchivo = rs.getString(2);
		}
		CallableStatement cstmtCall = con.prepareCall("{CALL insert_update_status_topic_pr(?,?,?,?,?,?,?)}");
		cstmtCall.setInt(1,studentPersonalBean.getStudentPersonalId());
		cstmtCall.setInt(2,Integer.parseInt(topicId));
		cstmtCall.setString(3,studentPersonalBean.getSegment1());
		cstmtCall.setString(4,accion);
		cstmtCall.setInt(5, Integer.parseInt(idContent));	
		cstmtCall.setString(6,tipCont);
		cstmtCall.registerOutParameter(7, Types.VARCHAR);
		cstmtCall.execute();
		file = new DefaultStreamedContent(inStream, "pdf", nomArchivo);  
		} catch (NamingException | SQLException e) {
			logger.error(e);
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				logger.error(e);
			}
		}
	  
	  return file;
	}
	
	
	public StreamedContent getFile() {
		return file;
	}

	public String getLinkPractic2() {
		return linkPractic2;
	}

	public void setLinkPractic2(String linkPractic2) {
		this.linkPractic2 = linkPractic2;
	}

	public String getLinkPractic3() {
		return linkPractic3;
	}

	public void setLinkPractic3(String linkPractic3) {
		this.linkPractic3 = linkPractic3;
	}
	
	

}
package mx.javaonline.controlador;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import mx.javaonline.beans.StudentPersonalBean;
import mx.javaonline.beans.TopicsBean;
import mx.javaonline.carousel.beans.TopicsCarouselBean;
import mx.javaonline.model.ConectionWrapper;

import org.apache.log4j.Logger;
import org.primefaces.event.FileUploadEvent;

public class FileUploadController {
	private static org.apache.log4j.Logger logger = Logger.getLogger(FileUploadController.class);
	ConectionWrapper conectionWrapper;
	Connection con;
	FacesContext facesContext = FacesContext.getCurrentInstance();
	HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
	
	public void handleFileUpload(FileUploadEvent event) {
		String topicId = (String)session.getAttribute("topicId");
		try {
			conectionWrapper = new ConectionWrapper();
			FileInputStream finput = (FileInputStream) event.getFile().getInputstream();
			con = conectionWrapper.getConexion();
		   String sql = "UPDATE content_topics SET lamina = ? \n"+
				   		"WHERE topic_id = ?";
		PreparedStatement psmt =  con.prepareStatement(sql);
//		logger.debug("UPDATE "+sql);
		psmt.setBinaryStream(1,finput);
		//psmt.setInt(2,Integer.parseInt(topicId));
		psmt.setInt(2,2);
		psmt.executeUpdate();
		FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " Se cargo correctamente.");  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
			
		} catch (NamingException | SQLException | IOException e) {
			logger.error(e);
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				logger.error(e);
			}
		}
		
        
    } 
	
	public boolean subeImagen(FileInputStream finput,String extension) {
	
		StudentPersonalBean studentPersonalBean = (StudentPersonalBean)session.getAttribute("studentPersonalBean");
		boolean bandera = false;
		conectionWrapper = new ConectionWrapper();
		String sql = "";
		if(studentPersonalBean.getSegment1().equals("S")){
			sql = "UPDATE students SET segment8 = ?,segment3 = ? WHERE student_id = ?";
		} else if(studentPersonalBean.getSegment1().equals("P") || studentPersonalBean.getSegment1().equals("A")){
			sql = "UPDATE personals SET segment8 = ?,segment3 = ? WHERE personal_id = ?";
		}
		
		
		try {
			con = conectionWrapper.getConexion();
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setBinaryStream(1,finput);
			psmt.setString(2, extension);
			psmt.setInt(3,studentPersonalBean.getStudentPersonalId());
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

}
package mx.javaonline.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.javaonline.beans.StudentPersonalBean;
import mx.javaonline.model.ConectionWrapper;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class DisplayPDF
 */
@WebServlet("/DisplayPDF")
public class DisplayPDF extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
	ConectionWrapper conectionWrapper;
	private static org.apache.log4j.Logger logger = Logger.getLogger(DisplayPDF.class);
       
 
    public DisplayPDF() {
        super();
    }
    
    private void processRequest(HttpServletRequest request, HttpServletResponse response){
    	HttpSession session = request.getSession();
    	int topicId = (int)session.getAttribute("topicId2");
//    	String topicId = request.getParameter("topicId");
    	InputStream inStream = null;
    	String accion = request.getParameter("accion");
    	String sql = "";
    	if(accion.equals("lamina")){
    		sql = "SELECT lamina FROM content_topics WHERE topic_id = ?";
    		
    	}if(accion.equals("practica")){
    		sql = "SELECT practica FROM content_topics WHERE topic_id = ?";
    	}if(accion.equals("solucion")){
    		sql = "SELECT solucion FROM content_topics WHERE topic_id = ?";
    	}
		try {
			conectionWrapper = new ConectionWrapper();
			con = conectionWrapper.getConexion();
		    PreparedStatement psmt =  con.prepareStatement(sql);
		
			psmt.setInt(1,topicId);
			ResultSet rs = psmt.executeQuery();
			byte[] bytearray;
			while(rs.next()){
				bytearray = new byte[1048576];
				int size  = 0;
				inStream  = rs.getBinaryStream(1);
				//response.reset();
				response.setContentType("application/pdf");
				
				if(inStream != null){
					while ((size = inStream.read(bytearray)) != -1) {
		                response.getOutputStream().
		                write(bytearray, 0, size);
					} 
				}
				  
			
		}
			
		} catch (NamingException | SQLException |IOException e) {
			logger.error(e);
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				logger.error(e);
			}
		}
	
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}
}
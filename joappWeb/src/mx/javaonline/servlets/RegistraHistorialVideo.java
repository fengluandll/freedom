package mx.javaonline.servlets;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.javaonline.beans.StudentPersonalBean;
import mx.javaonline.beans.TimerContent;
import mx.javaonline.daos.StatusBarTopicDAO;
import mx.javaonline.model.ConectionWrapper;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class RegistraHistorialVideo
 */
@WebServlet("/RegistraHistorialVideo")
public class RegistraHistorialVideo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private org.apache.log4j.Logger logger = Logger.getLogger(RegistraHistorialVideo.class);
	ConectionWrapper conectionWrapper;
	StatusBarTopicDAO barTopicDAO;
       
   
    public RegistraHistorialVideo() {
        super();
    }
    
    private void procesaRequest(HttpServletRequest request, HttpServletResponse response){
    	HttpSession session = request.getSession();
    	float porcentaje = Float.parseFloat(request.getParameter("porc"));
    	String accion    = request.getParameter("accion");
    	StudentPersonalBean studentPersonalBean = (StudentPersonalBean)session.getAttribute("studentPersonalBean");
    	String topicId = (String)session.getAttribute("topicId");
    	String videoId = (String)session.getAttribute("videoId");
    	String tipCont = (String)session.getAttribute("tipCont");
    	
    	barTopicDAO = new StatusBarTopicDAO();
    	barTopicDAO.setStatusBar(accion, porcentaje,topicId,studentPersonalBean,Integer.parseInt(videoId),tipCont);
    }
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		procesaRequest(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		procesaRequest(request,response);
	}

}

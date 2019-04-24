package mx.javaonline.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.javaonline.carousel.beans.TopicsCarouselBean;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class VisalizaVideoServlet
 */
@WebServlet("/VisalizaVideoServlet")
public class VisalizaVideoServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static org.apache.log4j.Logger logger = Logger.getLogger(VisalizaVideoServlet.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisalizaVideoServlet() {
    }
    
    protected void submitRequest(HttpServletRequest request, HttpServletResponse response){
    	String linkTopic = request.getParameter("linkTopic");
    	String topicId = request.getParameter("topicId");
    	String videoId = request.getParameter("videoId");
    	String tipCont = request.getParameter("tipCont");
    	
    	String embed = StringUtils.substringAfter(linkTopic, "_");
    	request.getSession().removeAttribute("linkTopic");
    	request.getSession().removeAttribute("topicId");
    	request.getSession().removeAttribute("videoId");
    	request.getSession().removeAttribute("tipCont");
    	request.getSession().setAttribute("linkTopic", linkTopic);
    	request.getSession().setAttribute("topicId", topicId);
    	request.getSession().setAttribute("videoId", videoId);
    	request.getSession().setAttribute("tipCont", tipCont);
    	
//    	logger.debug("(request.getServletContext().getContextPath() "+request.getServletContext().getContextPath());
    	
    	try {
//    		response.sendRedirect(request.getContextPath()+"/carousel/visualizaVideo.xhtml?linkTopic="+linkTopic);
    		response.sendRedirect(request.getContextPath()+"/carousel/openVideo.jsp?linkTopic="+linkTopic+"&"+"embed="+embed);
		} catch (IOException e) {
			logger.error(e);
		}
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		submitRequest(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		submitRequest(request,response);
	}

}
package mx.javaonline.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.javaonline.daos.PracticasDAO;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class VisualizaPracticaServlet
 */
@WebServlet("/VisualizaPracticaServlet")
public class VisualizaPracticaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static org.apache.log4j.Logger logger = Logger.getLogger(VisualizaPracticaServlet.class);
       

    public VisualizaPracticaServlet() {
     
    }
    
    protected void submitRequest(HttpServletRequest request, HttpServletResponse response){
    	String topicId = request.getParameter("topicId");
//    	logger.debug("topicId servlet "+topicId);
    	
    	request.getSession().removeAttribute("topicId");
    	request.getSession().setAttribute("topicId", topicId);
    	PracticasDAO practicasDAO = new PracticasDAO();
    	String linkTopic = practicasDAO.existeVideoPractica(Integer.parseInt(topicId));
    	String embed = StringUtils.substringAfter(linkTopic, "_");
    	
    try {
			if(linkTopic != null){
				response.sendRedirect(request.getContextPath()+"/carousel/openVideoPractica.jsp?linkTopic="+linkTopic+"&"+"embed="+embed);
			}else{
				response.sendRedirect(request.getContextPath()+"/carousel/visualizaPractica.xhtml?topicId="+topicId);
			}
    		
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
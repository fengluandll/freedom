package mx.javaonline.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class VisualizaLaminaServlet
 */
@WebServlet("/VisualizaLaminaServlet")
public class VisualizaLaminaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static org.apache.log4j.Logger logger = Logger.getLogger(VisualizaLaminaServlet.class);
    
	
    protected void submitRequest(HttpServletRequest request, HttpServletResponse response){
    	String topicId = request.getParameter("topicId");
//    	logger.debug("topicId servlet "+topicId);
    	
    	request.getSession().removeAttribute("topicId");
    	request.getSession().setAttribute("topicId", topicId);
    	
    try {
    		response.sendRedirect(request.getContextPath()+"/carousel/visualizaLaminas.xhtml?topicId="+topicId);
	} catch (IOException e) {
			logger.error(e);
		}
    }
    
    public VisualizaLaminaServlet() {
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		submitRequest(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		submitRequest(request,response);
	}

}

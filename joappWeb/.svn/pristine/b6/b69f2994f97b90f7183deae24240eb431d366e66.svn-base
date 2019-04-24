package mx.javaonline.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;


@WebServlet("/VisualizaEvaluacionServlet")
public class VisualizaEvaluacionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static org.apache.log4j.Logger logger = Logger.getLogger(VisualizaEvaluacionServlet.class);
       

    public VisualizaEvaluacionServlet() {
        super();
    }
    
    protected void submitRequest(HttpServletRequest request, HttpServletResponse response){
    	String link = request.getParameter("link");
    	String topicId2 = request.getParameter("topicId2");
    	request.getSession().removeAttribute("topicId2");
    	request.getSession().setAttribute("topicId2", topicId2);
    
    	
    try {
    		response.sendRedirect(request.getContextPath()+link);
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

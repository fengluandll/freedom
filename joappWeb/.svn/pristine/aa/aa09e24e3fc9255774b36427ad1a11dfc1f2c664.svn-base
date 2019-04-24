package mx.javaonline.servlets;

import java.io.IOException;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.javaonline.carousel.beans.TopicsCarouselBean;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class TopicCarouselServlet
 */
@WebServlet("/TopicCarouselServlet")
public class TopicCarouselServlet extends HttpServlet {
	
	private static org.apache.log4j.Logger logger = Logger.getLogger(TopicCarouselServlet.class);
	private static final long serialVersionUID = 1L;
	FacesContext facesContext = FacesContext.getCurrentInstance();   
	ResourceBundle bundle = ResourceBundle.getBundle("DatosGenerales");
	
    public TopicCarouselServlet() {
    	
    }
    
    protected void submitRequest(HttpServletRequest request, HttpServletResponse response){
    	String unitId = request.getParameter("unitId");
    	String topicIdArbol = request.getParameter("topicIdArbol");
    	String link = request.getParameter("link");
    	
//    	logger.debug("link servlet "+link);
    	
    	String topicId = (String)request.getSession().getAttribute("topicId");
    	request.getSession().removeAttribute("topicId");
    	request.getSession().removeAttribute("unitId");
    	request.getSession().removeAttribute("topicIdArbol");
    	request.getSession().setAttribute("unitId", unitId);
    	request.getSession().setAttribute("topicIdArbol", topicIdArbol);
    	//logger.debug("(request.getServletContext().getContextPath() "+request.getServletContext().getContextPath());
		try{
		if(topicId != null){
			response.sendRedirect("/joappWeb/TopicCarouselServlet?unitId="+unitId+"&topicIdArbol="+topicId);
		}else if(link == null || link.equals("")){
    		//RequestDispatcher dispatcher = request.getRequestDispatcher(bundle.getString("ip.application")+request.getContextPath()+"/carousel/topicCarousel.xhtml");
//    		response.sendRedirect("/joappWeb/carousel/topicCarousel.xhtml");
    		response.sendRedirect("/joappWeb/carousel/topicCarouselPlus.xhtml");
    	}else{
    		
        		response.sendRedirect(link);
    	 }
    	
		} catch (IOException e) {
			logger.error(e);
		}
    	

    	
    	
/*
    	try {
    		if(unitId.equals("16")){
    			response.sendRedirect(request.getContextPath()+"/administracion/nuevoUsuario.xhtml");
    		}else{
	    		response.sendRedirect(request.getContextPath()+"/carousel/topicCarousel.xhtml");
				//dispatcher.forward(request,response);
    		}
		} catch (IOException e) {
			logger.error(e);
		}*/
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		submitRequest(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		submitRequest(request,response);
	}

}

package mx.kaz.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.kaz.beans.PersonalBean;


@WebServlet("/SeleccionaApiServlet")
public class SeleccionaApiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

 
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	String puerto = request.getParameter("puerto");
    	HttpSession session = request.getSession();
    	session.setAttribute("nomPto", puerto);
    	String reporte = (String)session.getAttribute("nomRep");
    	PersonalBean personalBean = (PersonalBean) session.getAttribute("personalBean");
    	if(personalBean.getRolId() == 5){
    	
    		response.sendRedirect(request.getContextPath()+"/proyectos/registroProyectos.xhtml");
    	}else if(personalBean.getRolId() == 4 || personalBean.getRolId() == 8){
    		if(reporte.equals("lici")){
    			response.sendRedirect(request.getContextPath()+"/reportes/reporteLicitaciones.xhtml?in=0");
    		}else if(reporte.equals("obr")){
    			response.sendRedirect(request.getContextPath()+"/reportes/laassp.xhtml");
    		}
    		//session.removeAttribute("nomRep");
    	}
    	
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

}

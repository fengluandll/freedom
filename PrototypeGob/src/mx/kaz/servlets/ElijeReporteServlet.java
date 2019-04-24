package mx.kaz.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/ElijeReporteServlet")
public class ElijeReporteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	 public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException{
		 String reporte = request.getParameter("rep");
		 String action = request.getParameter("action");
		 HttpSession session = request.getSession();
		 String acticaDes = "";
		 if(action.equals("btn1")){ 
			 acticaDes = "1";
		 }else if(action.equals("btn2")){
			 acticaDes = "0";
		 }
		
		 if(reporte.equals("lic")){
			 session.setAttribute("nomRep", "lici");
		 }else if(reporte.equals("obr")){
			 session.setAttribute("nomRep", "obr");
		 }
		 
		 response.sendRedirect(request.getContextPath()+"/ley/seleccionaApi.xhtml?in="+acticaDes);
	 }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

}

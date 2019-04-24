package mx.com.televisa.derechocorporativo.apoderados.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.com.televisa.derechocorporativo.bean.PoderesBean;
import mx.com.televisa.derechocorporativo.daos.PoderesDAO;

/**
 * Servlet implementation class EditaPGServlet
 */
@WebServlet("/EditaPGServlet")
public class EditaPGServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException{
		 PoderesDAO poderesDao = new PoderesDAO();
		 List<PoderesBean> listPoderes = null;//poderesDao.dameDatosPoderes();
		 HttpSession session = request.getSession();
		 if(listPoderes.size() > 0){
			 session.setAttribute("listPoderes", listPoderes);
		 }else{
			 session.setAttribute("leyenda", "No existe información");
		 }
		 	 response.sendRedirect(request.getContextPath()+"/faces/jsp/captura/ajax/editaNewPG.jsp");
	 }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

}

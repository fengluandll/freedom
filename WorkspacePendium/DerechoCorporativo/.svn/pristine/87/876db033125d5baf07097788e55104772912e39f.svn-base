package mx.com.televisa.derechocorporativo.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.com.televisa.derechocorporativo.bean.RolBean;
import mx.com.televisa.derechocorporativo.daos.RolesDAO;

/**
 * Servlet implementation class AdminRolesServlet
 */
@WebServlet("/AdminRolesServlet")
public class AdminRolesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	 public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException{
		 RolesDAO rolesDAO = new RolesDAO();
		 List<RolBean> listRoles = rolesDAO.dameRoles();
		 HttpSession session = request.getSession();
		 if(listRoles.size() > 0){
			 session.setAttribute("listRol", listRoles);
		 }else{
			 session.setAttribute("leyenda", "No existe información");
		 }
		 	 response.sendRedirect(request.getContextPath()+"/faces/jsp/admin/adminRoles.jsp");
	 }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

}

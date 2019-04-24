package mx.com.televisa.derechocorporativo.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.com.televisa.derechocorporativo.bean.UserBean;
import mx.com.televisa.derechocorporativo.daos.UsersDAO;

/**
 * Servlet implementation class AdminUsersServlet
 */
@WebServlet("/AdminUsersServlet")
public class AdminUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	 public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException{
		 UsersDAO usersDAO = new UsersDAO();
		 List<UserBean> listUser = usersDAO.dameUsuarios();
		 HttpSession session = request.getSession();
		 if(listUser.size() > 0){
			 session.setAttribute("listUser", listUser);
		 }else{
			 session.setAttribute("leyenda", "No existe información");
		 }
		 	 response.sendRedirect(request.getContextPath()+"/faces/jsp/admin/adminUsers.jsp");
	 }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

}

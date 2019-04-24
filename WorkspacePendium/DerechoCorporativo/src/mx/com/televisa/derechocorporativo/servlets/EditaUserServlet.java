package mx.com.televisa.derechocorporativo.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.com.televisa.derechocorporativo.bean.SessionBean;
import mx.com.televisa.derechocorporativo.bean.UserBean;
import mx.com.televisa.derechocorporativo.daos.UsersDAO;

/**
 * Servlet implementation class EditaUserServlet
 */
@WebServlet("/EditaUserServlet")
public class EditaUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		UsersDAO usersDAO = new UsersDAO();
		UserBean userBean = null;
		HttpSession session = request.getSession();
		String nomcompleto = null;
		String nomUsuario  = null;
		String password    = null;
		String status      = null;
		String rol         = null;
		String idUser      = null;
		String numEmpleado      = null;
		SessionBean sessionBean = (SessionBean)session.getAttribute("sessionBean");
		int userModifico   = Integer.parseInt(sessionBean.getIdUser());
		if(action.equals("edita")){
			idUser = request.getParameter("idUser");
			userBean = usersDAO.dameUsuario(Integer.parseInt(idUser));
			
			if(userBean != null){
				session.setAttribute("userBean", userBean);
				response.sendRedirect(request.getContextPath()+"/faces/jsp/admin/editaUser.jsp");
			}
			
		}else if(action.equals("editaGuarda")){
			userBean = new UserBean();
			idUser = request.getParameter("idUser");
			nomcompleto = request.getParameter("txtNombrecompleto");
			nomUsuario  = request.getParameter("txtNomUsuario");
			password    = request.getParameter("txtPass");
			status      = request.getParameter("txtStatus");
			rol         = request.getParameter("cmbRol");
			numEmpleado = request.getParameter("txtNumEmpleado");
			userBean.setIdUser(Integer.parseInt(idUser));
			userBean.setNomUserLongName(nomcompleto);
			userBean.setNomUsername(nomUsuario);
			userBean.setCvePassword(password);
			userBean.setStatus(status);
			userBean.setIdRol(Integer.parseInt(rol));
			userBean.setUsuarioModifico(userModifico);
			userBean.setNumEmpleado(numEmpleado);
			String leyenda = usersDAO.actualizaUser(userBean);
			session.setAttribute("mensaje"		  , leyenda);
			if(leyenda.indexOf("OK") > -1){
				response.sendRedirect(request.getContextPath()+"/AdminUsersServlet");
			}else{
				
				session.setAttribute("userBean", userBean);
				//response.sendRedirect(request.getContextPath()+"/faces/jsp/admin/nuevoUsuario.jsp?mensaje="+leyenda);
				response.sendRedirect(request.getContextPath()+"/faces/jsp/admin/editaUser.jsp");
			}
			
		}else if(action.equals("nuevo")){
			userBean = new UserBean();
			nomcompleto = request.getParameter("txtNombrecompleto");
			nomUsuario  = request.getParameter("txtNomUsuario");
			password    = request.getParameter("txtPass");
			status      = request.getParameter("txtStatus");
			rol         = request.getParameter("cmbRol");
			numEmpleado = request.getParameter("txtNumEmpleado");
			userBean.setNomUserLongName(nomcompleto);
			userBean.setNomUsername(nomUsuario);
			userBean.setCvePassword(password);
			userBean.setStatus(status);
			userBean.setIdRol(Integer.parseInt(rol));		
			userBean.setUsuarioModifico(Integer.parseInt(sessionBean.getIdUser()));
			userBean.setNumEmpleado(numEmpleado);
			String leyenda = usersDAO.nuevoUsuario(userBean);
			if(leyenda.indexOf("Satisfactoriamente") > -1){
				response.sendRedirect(request.getContextPath()+"/AdminUsersServlet");
			}else{
				session.setAttribute("nomcompleto", nomcompleto);
				session.setAttribute("nomUsuario" , nomUsuario);
				session.setAttribute("status"     , status);
				session.setAttribute("rol"		  , rol);
				session.setAttribute("numempleado"		  , numEmpleado);
				session.setAttribute("mensaje"		  , leyenda);
				//response.sendRedirect(request.getContextPath()+"/faces/jsp/admin/nuevoUsuario.jsp?mensaje="+leyenda);
				response.sendRedirect(request.getContextPath()+"/faces/jsp/admin/nuevoUsuario.jsp");
			}
		}else if(action.equals("elimina")){
			userBean = new UserBean();
			idUser = request.getParameter("idUser");
			userBean.setIdUser(Integer.parseInt(idUser));
			userBean.setUsuarioModifico(userModifico);
			boolean ok = usersDAO.eliminaUser(userBean);
			if(ok){
				response.sendRedirect(request.getContextPath()+"/AdminUsersServlet");
			}else{
				response.sendRedirect(request.getContextPath()+"/faces/jsp/home/errorConexion.jsp");
			}
			
		}
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

}

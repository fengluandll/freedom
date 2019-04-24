package mx.com.televisa.derechocorporativo.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itextpdf.text.log.SysoCounter;

import mx.com.televisa.derechocorporativo.bean.RolBean;
import mx.com.televisa.derechocorporativo.bean.MenuBean;
import mx.com.televisa.derechocorporativo.bean.FlexColumnsCatagoBean;
import mx.com.televisa.derechocorporativo.bean.SessionBean;
import mx.com.televisa.derechocorporativo.daos.RolesDAO;

/**
 * Servlet implementation class EditaCatalogoServlet
 */
@WebServlet("/EditaRolServlet")
public class EditaRolServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String charset = response.getCharacterEncoding();
		System.out.println("charset "+charset);
		String action = request.getParameter("action");
		RolesDAO rolDAO = new RolesDAO();
		HttpSession session = request.getSession();
		SessionBean sessionBean = (SessionBean)session.getAttribute("sessionBean");
		String nomRol = "";
		if(action.equals("edita")){
			String  idRol		= request.getParameter("idRol");			
			RolBean rolBean     = rolDAO.getRol(Integer.parseInt(idRol));
			List<MenuBean> luMenuElements = rolDAO.getNewMenuElements(Integer.parseInt(idRol));
			session.setAttribute("rolBean", rolBean);
			session.setAttribute("luMenuElements", luMenuElements);
			response.sendRedirect(request.getContextPath()+"/faces/jsp/admin/editaRol.jsp?nomRol="+rolBean.getNomName());
			
		}else if(action.equals("elimina")){
			String idRol = request.getParameter("idRol");
			boolean ok = rolDAO.deleteRol(Integer.parseInt(idRol),Integer.parseInt(sessionBean.getIdUser()));
			if(ok){
				session.removeAttribute("msgNoElimina");
				response.sendRedirect(request.getContextPath()+"/AdminRolesServlet");
			}else{
				nomRol = rolDAO.getRol(Integer.parseInt(idRol)).getNomName();
				session.setAttribute("msgNoElimina", "El rol "+nomRol.toUpperCase()+" no se puede eliminar debido a que "+
													 "esta siendo utilizado por otro elemento.");
				response.sendRedirect(request.getContextPath()+"/AdminRolesServlet");
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

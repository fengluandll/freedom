package mx.com.televisa.derechocorporativo.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itextpdf.text.log.SysoCounter;

import mx.com.televisa.derechocorporativo.bean.CatalogoBean;
import mx.com.televisa.derechocorporativo.bean.FlexColumnsCatagoBean;
import mx.com.televisa.derechocorporativo.bean.SessionBean;
import mx.com.televisa.derechocorporativo.daos.CatalogoDAO;

/**
 * Servlet implementation class EditaCatalogoServlet
 */
@WebServlet("/EditaCatalogoServlet")
public class EditaCatalogoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String charset = response.getCharacterEncoding();
		System.out.println("charset "+charset);
		String action = request.getParameter("action");
		CatalogoDAO catalogoDAO = new CatalogoDAO();
		HttpSession session = request.getSession();
		SessionBean sessionBean = (SessionBean)session.getAttribute("sessionBean");
		String nomCatalogo = "";
		if(action.equals("edita")){
			String idCatalogo						    = request.getParameter("idcatalogo");
			nomCatalogo								    = request.getParameter("nomCata");			
			CatalogoBean catalogoBean 					= catalogoDAO.obtenCatalogoId(Integer.parseInt(idCatalogo));
			FlexColumnsCatagoBean flexColumnsCatagoBean = catalogoDAO.obtenFlexColumn(Integer.parseInt(idCatalogo));
			session.setAttribute("catalogoBean", catalogoBean);
			session.setAttribute("flexColumnsCatagoBean", flexColumnsCatagoBean);
			response.sendRedirect(request.getContextPath()+"/faces/jsp/admin/editaCatalogo.jsp?nomCatalogo="+nomCatalogo);
			
		}else if(action.equals("elimina")){
			String idCatalogo = request.getParameter("idcatalogo");
			boolean ok = catalogoDAO.eliminaCatalogo(Integer.parseInt(idCatalogo),sessionBean);
			if(ok){
				session.removeAttribute("msgNoElimina");
				response.sendRedirect(request.getContextPath()+"/CargaCatalogoServlet");
			}else{
				nomCatalogo = request.getParameter("nomCata");
				session.setAttribute("msgNoElimina", "El cátalogo "+nomCatalogo.toUpperCase()+" no se puede eliminar debido a que "+
													 "esta siendo utilizado por otro elemento.");
				response.sendRedirect(request.getContextPath()+"/CargaCatalogoServlet");
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

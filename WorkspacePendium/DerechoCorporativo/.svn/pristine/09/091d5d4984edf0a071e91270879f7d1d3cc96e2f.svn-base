package mx.com.televisa.derechocorporativo.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.com.televisa.derechocorporativo.bean.CatalogoBean;
import mx.com.televisa.derechocorporativo.bean.FlexColumnsCatagoBean;
import mx.com.televisa.derechocorporativo.bean.SessionBean;
import mx.com.televisa.derechocorporativo.daos.CatalogoDAO;

/**
 * Servlet implementation class GuardaCatalogoServlet
 */
@WebServlet("/GuardaCatalogoServlet")
public class GuardaCatalogoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CatalogoBean catalogoBean;
	CatalogoDAO catalogoDAO;
       
   
	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		catalogoBean = new CatalogoBean();
	    catalogoDAO  = new CatalogoDAO();
	    FlexColumnsCatagoBean flexColumnsCatagoBean = new FlexColumnsCatagoBean();
	    String action = request.getParameter("action");
	    HttpSession session = request.getSession();
	    SessionBean sessionBean = (SessionBean)session.getAttribute("sessionBean");
	    
		catalogoBean.setCodigo(request.getParameter("txtCodigo"));
		catalogoBean.setNombre(request.getParameter("txtNombre"));
		catalogoBean.setDescripcion(request.getParameter("txtDesc"));
		catalogoBean.setAttr1("");
		catalogoBean.setAttr2("");
		catalogoBean.setAttr3("");
		/******CAMPOS PARA MOSTRAR FLEXIBILIDAD EN COLUMNAS DEL DETALLE*****/
		flexColumnsCatagoBean.setIdentificador(request.getParameter("txtIdenti"));
		flexColumnsCatagoBean.setNombre(request.getParameter("txtNombreC"));
		flexColumnsCatagoBean.setValor(request.getParameter("txtValor"));
		flexColumnsCatagoBean.setDescripcion(request.getParameter("txtDescC"));
		flexColumnsCatagoBean.setAttr1(request.getParameter("txtAttr1"));
		flexColumnsCatagoBean.setAttr2(request.getParameter("txtAttr2"));
		flexColumnsCatagoBean.setAttr3(request.getParameter("txtAttr3"));
		
		boolean paso = false;
		if(action.equals("nuevo")){
			paso = catalogoDAO.insertCatalogo(catalogoBean,flexColumnsCatagoBean,sessionBean);
		}else if(action.equals("edita")){
			String idCatalogo = request.getParameter("idCatalogo");
			catalogoBean.setIdCatalogo(Integer.parseInt(idCatalogo));
			paso = catalogoDAO.editaCatalogo(catalogoBean,flexColumnsCatagoBean,sessionBean);
		}
		
		if(paso){
			response.sendRedirect(request.getContextPath()+"/CargaCatalogoServlet");
		}else{
			//response.sendRedirect(request.getContextPath()+"/faces/jsp/home/errorConexion.jsp");
			if(action.equals("nuevo")){
				response.sendRedirect(request.getContextPath()+"/faces/jsp/admin/nuevoCatalogo.jsp");
			}else if(action.equals("edita")){
				response.sendRedirect(request.getContextPath()+"/faces/jsp/admin/editaCatalogo.jsp");
			}
			
			session.setAttribute("catalogoBean", catalogoBean);
			session.setAttribute("flexColumnsCatagoBean", flexColumnsCatagoBean);
			session.setAttribute("msgDuplicado", "No es posible dar de alta un registro duplicado");
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

}

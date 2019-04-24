package mx.com.televisa.derechocorporativo.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.com.televisa.derechocorporativo.bean.CatalogoBean;
import mx.com.televisa.derechocorporativo.daos.CatalogoDAO;

/**
 * Servlet implementation class CargaCatalogoServlet
 */
@WebServlet("/CargaCatalogoServlet")
public class CargaCatalogoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		CatalogoDAO catalogoDAO = new CatalogoDAO();
		 List<CatalogoBean> listCatalog = catalogoDAO.obtenCatalogos();
		 HttpSession luHttpSession = request.getSession();
		 
		 //System.out.println("Entro:  PR");
		 
		 //ECM 26 Noviembre 2015
		 String lsVarCat = request.getParameter("action");
		 lsVarCat = lsVarCat == null?"":lsVarCat;
		 CatalogoDAO luCatalogoDAO = new CatalogoDAO();
		 
		 //System.out.println("VarCatPR: "+lsVarCat);
		  

		 if(lsVarCat.equals("buscaCat")){
			 //String lsInteligente = request.getParameter("cat");
			 String lsInteligente = request.getParameter("cat");
			 //System.out.println("Inteligente: "+lsInteligente);

			 List<CatalogoBean> laCatalogoBean = luCatalogoDAO.obtenerCatalogoBusqueda(lsInteligente);

			 luHttpSession.setAttribute("listCatalog", laCatalogoBean);
			 luHttpSession.setAttribute("busqInteliCat", lsInteligente);

		 }else{
			 if(listCatalog.size() > 0){
				 luHttpSession.setAttribute("listCatalog", listCatalog);
				 luHttpSession.removeAttribute("busqInteliCat");
			 }else{
				 luHttpSession.setAttribute("leyenda", "No existe información");
			 }
			 	 response.sendRedirect(request.getContextPath()+"/faces/jsp/admin/adminCatalog.jsp");
		 }
		 
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

}

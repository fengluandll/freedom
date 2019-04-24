package mx.com.televisa.derechocorporativo.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.com.televisa.derechocorporativo.bean.CatalogoBean;
import mx.com.televisa.derechocorporativo.bean.DetalleCatagoBean;
import mx.com.televisa.derechocorporativo.daos.CatalogoDAO;

/**
 * Servlet implementation class CargaDetalleCatgoServlet
 */
@WebServlet("/CargaDetalleCatgoServlet")
public class CargaDetalleCatgoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String idCatalogo = request.getParameter("idcatalogo");
		String nomCatalogo = request.getParameter("nomCata");
		CatalogoDAO catalogoDAO = new CatalogoDAO();
		HttpSession luHttpSession = request.getSession();
		
		CatalogoBean catalogoBean = null;

		List<DetalleCatagoBean> listDetalle = null;

		//ECM 07 Diciembre 2015
		String lsAction = request.getParameter("action");
		lsAction = lsAction == null?"":lsAction;
		if(idCatalogo != null){
			if(Integer.parseInt(idCatalogo) == 62){
				response.sendRedirect(request.getContextPath()+ "/jsp/captura/poderesGEC/catalogo/catalogoPoderesGenerales.jsp");
			}else if(Integer.parseInt(idCatalogo) == 63){
				response.sendRedirect(request.getContextPath()+ "/jsp/captura/poderesGEC/catalogo/catalogoPoderes.jsp");
			}
		}
		
		//System.out.println("CatDet IdCatDet: "+lsIdCatDet);
		System.out.println("CatDet Action: "+lsAction);
		System.out.println("CatDet NomCatalogo: "+nomCatalogo);

		if(lsAction.equals("buscaCatDet")){
			idCatalogo = (String) luHttpSession.getAttribute("IdCatDet");
			String lsInteligente =request.getParameter("cat");
			listDetalle = catalogoDAO.dameDetCatBus(Integer.parseInt(idCatalogo), lsInteligente);
			luHttpSession.setAttribute("busqInteliDet", lsInteligente);
			System.out.println("CatDet:  "+lsInteligente+"  ");
			catalogoBean = catalogoDAO.obtenCatalogoId(Integer.parseInt(idCatalogo));

			luHttpSession.setAttribute("detCatalogoBean", catalogoBean);
			luHttpSession.setAttribute("listDetalle", listDetalle);
			response.sendRedirect(request.getContextPath()+"/faces/jsp/admin/adminDetalleCatalog.jsp?nomCatalogo="+nomCatalogo+"&idCatalogo="+idCatalogo);

			
		}else{
			listDetalle = catalogoDAO.dameDetalleCatgo(Integer.parseInt(idCatalogo));
			catalogoBean = catalogoDAO.obtenCatalogoId(Integer.parseInt(idCatalogo));
			
			luHttpSession.setAttribute("detCatalogoBean", catalogoBean);
			luHttpSession.setAttribute("listDetalle", listDetalle);
			response.sendRedirect(request.getContextPath()+"/faces/jsp/admin/adminDetalleCatalog.jsp?nomCatalogo="+nomCatalogo+"&idCatalogo="+idCatalogo);


		}
		
	
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

}

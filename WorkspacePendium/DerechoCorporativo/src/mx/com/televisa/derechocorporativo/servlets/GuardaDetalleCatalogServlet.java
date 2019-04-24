package mx.com.televisa.derechocorporativo.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.com.televisa.derechocorporativo.bean.DetalleCatagoBean;
import mx.com.televisa.derechocorporativo.bean.SessionBean;
import mx.com.televisa.derechocorporativo.daos.CatalogoDAO;
import mx.com.televisa.derechocorporativo.model.Catalog;

/**
 * Servlet implementation class GuardaDetalleCatalogServlet
 */
@WebServlet("/GuardaDetalleCatalogServlet")
public class GuardaDetalleCatalogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		String nomCatalogo = request.getParameter("nomCata");
		DetalleCatagoBean detalleCatagoBean = new DetalleCatagoBean();
		HttpSession session = request.getSession();
		SessionBean sessionBean = (SessionBean)session.getAttribute("sessionBean");
		
		detalleCatagoBean.setIdCatalogo(Integer.parseInt(request.getParameter("idCatalogo").trim()));
		detalleCatagoBean.setIdentificador(request.getParameter("txtIdentificador").trim());
		detalleCatagoBean.setNombre(request.getParameter("txtNombre").trim().replace("'", "&#39;"));
		detalleCatagoBean.setValor(request.getParameter("txtValor").trim().replace("'", "&#39;"));
		detalleCatagoBean.setdescripcion(request.getParameter("txtDesc").trim().replace("'", "&#39;"));
		detalleCatagoBean.setAttr1(request.getParameter("txtAttr1").trim());
		detalleCatagoBean.setAttr2(request.getParameter("txtAttr2").trim());
		detalleCatagoBean.setAttr3(request.getParameter("txtAttr3").trim());
		CatalogoDAO catalogoDAO;
		if(action.equals("nuevo")){
			catalogoDAO = new CatalogoDAO();
			boolean paso = catalogoDAO.insertDetCatalogo(detalleCatagoBean,sessionBean);
			Catalog.reloadCatPersonasTotal();
			if(paso){
				response.sendRedirect(request.getContextPath()+"/CargaDetalleCatgoServlet?idcatalogo="+detalleCatagoBean.getIdCatalogo()+"&nomCata="+nomCatalogo);
			}else{
				//response.sendRedirect(request.getContextPath()+"/faces/jsp/home/errorConexion.jsp");
				lanzaError(request,response,session,detalleCatagoBean,nomCatalogo,action);
			}
		}else if(action.equals("edita")){
			catalogoDAO = new CatalogoDAO();
			detalleCatagoBean.setIdCatalogoVal(Integer.parseInt(request.getParameter("idCatalogoVal")));
			boolean paso = catalogoDAO.editaDetalleCatalogo(detalleCatagoBean,sessionBean);
			//Catalog.reloadCatPersonasTotal();
			
			if(paso){
				response.sendRedirect(request.getContextPath()+"/CargaDetalleCatgoServlet?idcatalogo="+detalleCatagoBean.getIdCatalogo()+"&nomCata="+nomCatalogo);
			}else{
				//response.sendRedirect(request.getContextPath()+"/faces/jsp/home/errorConexion.jsp");
				lanzaError(request,response,session,detalleCatagoBean,nomCatalogo,action);
			}
		}
	}
	
	private void lanzaError(HttpServletRequest request,HttpServletResponse response,
							HttpSession session,DetalleCatagoBean detalleCatagoBean,
							String nomCatalogo,
							String action) throws IOException{
		String jsp = "";
		String idCatalogo = "";
		if(action.equals("nuevo")){
			jsp = "nuevoDetalleCatalogo.jsp";
			idCatalogo = "idCatalogo";
		}else{
			jsp = "editaDetalleCatalogo.jsp";
			idCatalogo = "idcatalogo";
		}
		response.sendRedirect(request.getContextPath()+"/faces/jsp/admin/"+jsp+"?"+idCatalogo+"="+
                detalleCatagoBean.getIdCatalogo()+
                "&nomCata="+nomCatalogo+
                "&muestraMsg="+ (action.equals("nuevo")==true?"false":"true")+
                "&idConsec="+detalleCatagoBean.getIdentificador()+
                "&idCatalogoVal="+detalleCatagoBean.getIdCatalogoVal());
		session.setAttribute("detalleCatagoBean", detalleCatagoBean);
		session.setAttribute("msgDuplicado", "No es posible dar de alta un registro duplicado");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

}

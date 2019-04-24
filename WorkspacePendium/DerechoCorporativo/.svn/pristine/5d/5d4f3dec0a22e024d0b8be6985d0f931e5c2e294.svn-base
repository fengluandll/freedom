package mx.com.televisa.derechocorporativo.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.com.televisa.derechocorporativo.bean.CatalogoBean;
import mx.com.televisa.derechocorporativo.bean.DetalleCatagoBean;
import mx.com.televisa.derechocorporativo.bean.SessionBean;
import mx.com.televisa.derechocorporativo.daos.CatalogoDAO;


/**
 * Servlet implementation class EditaDetalleCatagServlet
 */
@WebServlet("/EditaDetalleCatagServlet")
public class EditaDetalleCatagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String action = request.getParameter("action");
		CatalogoDAO catalogoDAO = new CatalogoDAO();
		String idCatalogoVal = request.getParameter("idcatalogoVal");
		String idCatalogo = request.getParameter("idcatalogo");
		String nomCata = request.getParameter("nomCatalogo");
		HttpSession luSession = request.getSession();
		SessionBean sessionBean = (SessionBean)luSession.getAttribute("sessionBean");
		if(action.equals("edita")){
			DetalleCatagoBean detalleCatagoBean = catalogoDAO.obtenDetalleCatalogoId(Integer.parseInt(idCatalogoVal));
			
			luSession.setAttribute("detalleCatagoBean", detalleCatagoBean);
			response.sendRedirect(request.getContextPath()+"/faces/jsp/admin/editaDetalleCatalogo.jsp?idCatalogoVal="+idCatalogoVal+"&idcatalogo="+idCatalogo+"&nomCata="+nomCata);
			
		}else if(action.equals("elimina")){
			
			boolean ok = catalogoDAO.eliminaDetalleCatalogo(Integer.parseInt(idCatalogoVal),sessionBean,Integer.parseInt(idCatalogo));
			if(ok){
				response.sendRedirect(request.getContextPath()+"/CargaDetalleCatgoServlet?idcatalogo="+idCatalogo+"&nomCata="+nomCata);
			}else{
				//String nomDetalle = request.getParameter("nomDetalle");
				luSession.setAttribute("msgNoEliminaDetalle", /*"El elemento"+nomDetalle.toUpperCase()+*/"No se puede eliminar debido a que "+
						 "esta siendo utilizado por otro elemento.");
				response.sendRedirect(request.getContextPath()+"/CargaDetalleCatgoServlet?idcatalogo="+idCatalogo+"&nomCata="+nomCata);
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

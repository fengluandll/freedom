package mx.com.televisa.derechocorporativo.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import mx.com.televisa.derechocorporativo.bean.EmpresasBean;
import mx.com.televisa.derechocorporativo.bean.SessionBean;
import mx.com.televisa.derechocorporativo.daos.EmpresasDAO;


@WebServlet("/CargaEmpresasServlet")
public class CargaEmpresasServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	public void processRequest(HttpServletRequest  request, 
							   HttpServletResponse response) throws IOException{
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		EmpresasDAO luEmpresasDAO = new EmpresasDAO();
		String action 			  = request.getParameter("action");
		HttpSession luSession = request.getSession();
		SessionBean sessionBean = (SessionBean)luSession.getAttribute("sessionBean");
		
		if(action.equals("buscaEmp")){
			
			String lsInteligente = request.getParameter("emp");

			System.out.println("lsInteligente: "+lsInteligente);

			List<EmpresasBean> listEmpresas = luEmpresasDAO.dameEmpresasGeneral(lsInteligente);
			luSession.setAttribute("listEmpresas", listEmpresas);
			luSession.setAttribute("busqInteli", lsInteligente);
		}else if(action.equals("lista")){
			List<EmpresasBean> listEmpresas = luEmpresasDAO.dameEmpresas();
			luSession.setAttribute("listEmpresas", listEmpresas);
			luSession.removeAttribute("busqInteli");
			response.sendRedirect(request.getContextPath()+"/faces/jsp/admin/adminEmpresas.jsp");
		}else if(action.equals("nuevo")){
			EmpresasBean luEmpresasBean = new EmpresasBean();
//			String lsNomcorto 			= new String(request.getParameter("txtNomcorto").getBytes("ISO-8859-1"),"UTF-8");
//			String lsNombre   			= new String(request.getParameter("txtNombre").getBytes("ISO-8859-1"),"UTF-8");
//			String lsRfc      			= request.getParameter("txtRfc");
//			String lsPais     			= new String(request.getParameter("txtPais").getBytes("ISO-8859-1"),"UTF-8");
			String lsNomcorto 			= request.getParameter("txtNomcorto");
			String lsNombre   			= request.getParameter("txtNombre");
			String lsRfc      			= request.getParameter("txtRfc");
			String lsPais     			= request.getParameter("txtPais");
			//String lsResponsable		= request.getParameter("txtResponsable");			
			String nomPais = StringUtils.substringAfter(lsPais, "|");
			String idPais = StringUtils.substringBefore(lsPais, "|");
			luEmpresasBean.setCveEmpresa(lsNomcorto);
			luEmpresasBean.setNomEmpresa(lsNombre);
			luEmpresasBean.setAttr1(lsRfc);
			luEmpresasBean.setAttr2(nomPais);
			luEmpresasBean.setIdPais(idPais);
			//luEmpresasBean.setAttr3(lsResponsable);
			
			boolean bOk = luEmpresasDAO.insertEmpresa(luEmpresasBean,sessionBean);
			if(bOk){
				 response.sendRedirect(request.getContextPath()+"/CargaEmpresasServlet?action=lista");
			}else{
				response.sendRedirect(request.getContextPath()+"/faces/jsp/home/errorConexion.jsp");
			}
		}else if(action.equals("edita")){
			String lsIdEMpresa = request.getParameter("idEmpresa");
			EmpresasBean empresasBean = luEmpresasDAO.dameEmpresa(Integer.parseInt(lsIdEMpresa));
			luSession.setAttribute("empresasBean", empresasBean);
			response.sendRedirect(request.getContextPath()+"/faces/jsp/admin/editaEmpresa.jsp");
		}else if(action.equals("actualiza")){
			EmpresasBean luEmpresasBean = new EmpresasBean();
//			String lsIdEmpresa			= request.getParameter("idEmpresa");
//			String lsNomcorto 			= new String(request.getParameter("txtNomcorto").getBytes("ISO-8859-1"),"UTF-8");
//			String lsNombre   			= new String(request.getParameter("txtNombre").getBytes("ISO-8859-1"),"UTF-8");
//			String lsRfc      			= request.getParameter("txtRfc");
//			String lsPais     			= new String(request.getParameter("txtPais").getBytes("ISO-8859-1"),"UTF-8");
			String lsIdEmpresa			= request.getParameter("idEmpresa");
			String lsNomcorto 			= request.getParameter("txtNomcorto");
			String lsNombre   			= request.getParameter("txtNombre");
			String lsRfc      			= request.getParameter("txtRfc");
			String lsPais     			= request.getParameter("txtPais");
			//String lsResponsable		= request.getParameter("txtResponsable");
			String nomPais = StringUtils.substringAfter(lsPais, "|");
			String idPais = StringUtils.substringBefore(lsPais, "|");
			luEmpresasBean.setIdEmpresa(Integer.parseInt(lsIdEmpresa));
			luEmpresasBean.setCveEmpresa(lsNomcorto);
			luEmpresasBean.setNomEmpresa(lsNombre);
			luEmpresasBean.setAttr1(lsRfc);
			luEmpresasBean.setAttr2(nomPais);
			//luEmpresasBean.setAttr3(lsResponsable);
			
			luEmpresasBean.setIdPais(idPais);
			boolean bOk = luEmpresasDAO.updateEmpresa(luEmpresasBean,sessionBean);
			if(bOk){
				 response.sendRedirect(request.getContextPath()+"/CargaEmpresasServlet?action=lista");
			}else{
				response.sendRedirect(request.getContextPath()+"/faces/jsp/home/errorConexion.jsp");
			}
			
		}else if(action.equals("elimina")){
			String lsIdEmpresa			= request.getParameter("idEmpresa");
			EmpresasBean luEmpresasBean = new EmpresasBean();
			luEmpresasBean.setIdEmpresa(Integer.parseInt(lsIdEmpresa));
			
			
			
			boolean deletable = EmpresasDAO.isEmpresaDeletable(lsIdEmpresa);
			
			
			if(deletable) {

				boolean bOk = luEmpresasDAO.deleteEmpresa(luEmpresasBean);
				if(bOk){
					 response.sendRedirect(request.getContextPath()+"/CargaEmpresasServlet?action=lista");
				}else{
					response.sendRedirect(request.getContextPath()+"/faces/jsp/home/errorConexion.jsp");
				}
				
			} else{
			
				request.getSession().setAttribute("msgNoEliminaEmpresa", "La empresa no se puede borrar ya que tiene registros relacionados.");
				response.sendRedirect(request.getContextPath()+"/CargaEmpresasServlet?action=lista");
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

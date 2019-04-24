package mx.com.televisa.derechocorporativo.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import mx.com.televisa.derechocorporativo.bean.ApoderadosBean;
import mx.com.televisa.derechocorporativo.bean.SessionBean;
import mx.com.televisa.derechocorporativo.daos.ApoderadosDAO;
import mx.com.televisa.derechocorporativo.util.FacesUtils;

/**
 * Servlet implementation class ApoderadosServlet
 */
@WebServlet("/ApoderadosServlet")
public class ApoderadosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException{
		ApoderadosDAO apoderadosDAO = new ApoderadosDAO();
		ApoderadosBean apoderadosBean;
		 String param = request.getParameter("parametros");
		 String grupo = request.getParameter("grupo");
		 String idCatalogo = request.getParameter("idCatalogo");
		 HttpSession session = request.getSession();
		 SessionBean sessionBean = (SessionBean)session.getAttribute("sessionBean");
		 if(idCatalogo.equals("32")){
			 sessionBean.getApoderadosSessionVars().setCustomIdCatalogo("32");
			 sessionBean.getApoderadosSessionVars().setCustomIdComponent("listApoderados");
			 sessionBean.getApoderadosSessionVars().setTipoElemento("APODERADOS");
			 sessionBean.getApoderadosSessionVars().setCustomIdComponentRight("listApoderadosAsignados");
		 }
		 if(idCatalogo.equals("33")){
			 sessionBean.getApoderadosSessionVars().setCustomIdCatalogo("33");
			 sessionBean.getApoderadosSessionVars().setCustomIdComponent("listActosDeDominio");
			 sessionBean.getApoderadosSessionVars().setTipoElemento("ACTOS_DOMINIO");
			 sessionBean.getApoderadosSessionVars().setCustomIdComponentRight("listActosDeDominioAsignados");
		 }
		 if(idCatalogo.equals("34")){
			 sessionBean.getApoderadosSessionVars().setCustomIdCatalogo("34");
			 sessionBean.getApoderadosSessionVars().setCustomIdComponent("listActosAdministracion");
			 sessionBean.getApoderadosSessionVars().setTipoElemento("ACTOS_ADMINISTRACION");
			 sessionBean.getApoderadosSessionVars().setCustomIdComponentRight("listActosAdministracionAsignados");
		 }
		 if(idCatalogo.equals("35")){
			 sessionBean.getApoderadosSessionVars().setCustomIdCatalogo("35");
			 sessionBean.getApoderadosSessionVars().setCustomIdComponent("listTitulosCredito");
			 sessionBean.getApoderadosSessionVars().setTipoElemento("TITULOS_CREDITO");
			 sessionBean.getApoderadosSessionVars().setCustomIdComponentRight("listTitulosCreditoAsignados");
		 }
		 if(idCatalogo.equals("36")){
			 sessionBean.getApoderadosSessionVars().setCustomIdCatalogo("36");
			 sessionBean.getApoderadosSessionVars().setCustomIdComponent("listPleitosCobranzas");
			 sessionBean.getApoderadosSessionVars().setTipoElemento("PLEITOS_COBRANZAS");
			 sessionBean.getApoderadosSessionVars().setCustomIdComponentRight("listPleitosCobranzasAsignados");
		 }
		 if(idCatalogo.equals("37")){
			 sessionBean.getApoderadosSessionVars().setCustomIdCatalogo("37");
			 sessionBean.getApoderadosSessionVars().setCustomIdComponent("listPoderEspecial");
			 sessionBean.getApoderadosSessionVars().setTipoElemento("PODER_ESPECIAL");
			 sessionBean.getApoderadosSessionVars().setCustomIdComponentRight("listPoderEspecialAsignados");
		 }
		 String accion = request.getParameter("accion");
		 String numOrden = request.getParameter("numOrden");
		 String[] parametros = StringUtils.split(param,"|");
		 String empresa          = sessionBean.getIdCurrentEmpresa();
		 String escrituraId = (String)session.getAttribute("escrituraId");
		 String tipoPoderId = (String)session.getAttribute("tipoPoderId");
		 //for(String idCatalogoValor : parametros){
			 apoderadosBean = new ApoderadosBean();
			 apoderadosBean.setIdEmpresa(Integer.parseInt(empresa));
			 apoderadosBean.setIdCatalogo(Integer.parseInt(idCatalogo));
			 //apoderadosBean.setIdCatalogoValor(Integer.parseInt(idCatalogoValor));
			 apoderadosBean.setNumTipoPoder(Integer.parseInt(tipoPoderId));
			 apoderadosBean.setDesGrupo(grupo);
			 apoderadosBean.setDesEscritura(escrituraId);
			 apoderadosBean.setAtributo2(numOrden);
			
		 //}
		 if(accion.equals("agregar") || accion.equals("agregarTodos")){
			 apoderadosDAO.insertApoderados(apoderadosBean,parametros);
		 }else if(accion.equals("quitar") || accion.equals("quitarTodos")){
			 boolean encontroEevocado = apoderadosDAO.noBorrarRevocado(apoderadosBean, parametros);
			 if(encontroEevocado){
				 session.setAttribute("msgRevocados", "NO SE PUEDE ELIMINAR YA QUE SE EXSTEN APODERADOS REVOCADOS");
			 }else{
				 //apoderadosDAO.quitaApoderados(apoderadosBean,parametros);
				 session.setAttribute("msgRevocados", "");
			 }
			 
		 }
//		 System.out.println(escrituraId);
//		 System.out.println(tipoPoderId);
//		 System.out.println(empresa);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

}

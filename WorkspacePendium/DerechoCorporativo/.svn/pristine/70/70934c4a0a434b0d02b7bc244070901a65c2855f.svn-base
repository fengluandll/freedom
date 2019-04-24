package mx.com.televisa.derechocorporativo.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.com.televisa.derechocorporativo.bean.ApoderadosBean;
import mx.com.televisa.derechocorporativo.bean.SessionBean;
import mx.com.televisa.derechocorporativo.daos.ApoderadosDAO;
import mx.com.televisa.derechocorporativo.model.Catalog;
import mx.com.televisa.derechocorporativo.util.TextFormatter;

/**
 * Servlet implementation class CamposAdicionalesServlet
 */
@WebServlet("/CamposAdicionalesServlet")
public class CamposAdicionalesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException{

			 SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
			 String tipoPoder  	= request.getParameter("tipoPoder");
			 String grupoApoderado  	= request.getParameter("grupoApoderado");
			 String selectEscritura  	= request.getParameter("selectEscritura");
			 String idCatalogo  	= request.getParameter("idCatApode");
			 String nombre  		= request.getParameter("txtNombreApode");
			 String txtFecha    	= request.getParameter("txtFecha");
			 String txtTipoBaja 	= request.getParameter("txtTipoBaja");
			 String txtDoc      	= request.getParameter("txtDoc");
			 String txtDocumentum 	= request.getParameter("txtDocumentum");
			 SessionBean sessionBean= (SessionBean) request.getSession().getAttribute("sessionBean");
			 int idEmpresa          = Integer.parseInt(sessionBean.getIdCurrentEmpresa());
			 //Seccion para revocacion
			 String checkRev		= request.getParameter("chkRevocar");
			 //String selectEsc		= request.getParameter("selectEscritura");
			 String escRevocada		= request.getParameter("selectEsc");
			 String txtFecP			= request.getParameter("txtFecP");
			 String txtRevM			= request.getParameter("txtRevM");
			 String txtFecM			= request.getParameter("txtFecM");
			 String revMediante     = request.getParameter("rdioRev");
			 String fecEscritura 	= null;
			 if(revMediante.equals("esc")){
				 fecEscritura    = new Catalog().dameFechaEscritura(idEmpresa,Integer.parseInt(tipoPoder),escRevocada=="0"?null:escRevocada);
				 txtRevM       = null;
				 txtFecM 	   = null;
				 txtDocumentum = null;
			 }else if(revMediante.equals("otro")){
				 escRevocada = null;
			 }
			
			 
			 //String txtIdRev		= request.getParameter("txtIdRev");
			 String idCatalogoValor		= request.getParameter("idCatalogoValor");
			 
			 ApoderadosBean apoderadosBean = new ApoderadosBean();
			 apoderadosBean.setElijerevocacion(revMediante);
			 apoderadosBean.setDesEscritura(selectEscritura);
			 apoderadosBean.setDesGrupo(grupoApoderado);
			 apoderadosBean.setNumTipoPoder(Integer.parseInt(tipoPoder));
			 
			 apoderadosBean.setIdCatalogo(Integer.parseInt(idCatalogo));
			 apoderadosBean.setCheckRev(checkRev);
			 apoderadosBean.setProtoMedianteEsc(escRevocada);
			 apoderadosBean.setProtoMedianteEscFecha(fecEscritura);
			 apoderadosBean.setRevocadoMediante(txtRevM);
			 apoderadosBean.setRevocadoMedianteFecha(txtFecM);
			 //apoderadosBean.setIdRevocacion(txtIdRev.equals("")?"-1":txtIdRev);
			 
			 apoderadosBean.setIdEmpresa(idEmpresa);
			 
			 ApoderadosDAO apoderadosDAO = new ApoderadosDAO();
			 apoderadosBean.setIdCatalogoValor(Integer.parseInt(idCatalogoValor));
			 java.util.Date fec = null;
			try {
				if(txtFecha != null && !txtFecha.equals("")){
					fec = formatoDelTexto.parse(txtFecha);
					apoderadosBean.setFecha(new  java.sql.Date(fec.getTime()));
				}else{
					apoderadosBean.setFecha(null);
				}
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
			 apoderadosBean.setTipoBaja(txtTipoBaja);
			 apoderadosBean.setDocumento(txtDoc);
			 apoderadosBean.setRefDocumentum(txtDocumentum);
			 boolean ok = apoderadosDAO.actualizaApoderado(apoderadosBean);
			 if(ok){
				 String mensaje = "LOS CAMBIOS SURTIRAN EFECTO AL MOMENTO DE DAR CLIC EN GUARDAR CAMBIOS";
				 response.sendRedirect(request.getContextPath()+"/faces/jsp/captura/apoderados/capturaCamposAdici.jsp?escritura="+selectEscritura+"&grupoApoderado="+TextFormatter.replaceAccentsURL(grupoApoderado)+"&tipoPoder="+tipoPoder+"&idCatalogoValor="+idCatalogoValor+"&mensaje="+mensaje+"&idCatalogo="+idCatalogo+"&nombre="+TextFormatter.replaceAccentsURL(nombre));
			 }
			 
		 
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

}

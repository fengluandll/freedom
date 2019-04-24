package mx.com.televisa.derechocorporativo.apoderados.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.com.televisa.derechocorporativo.bean.PoderesBean;
import mx.com.televisa.derechocorporativo.bean.SessionBean;
import mx.com.televisa.derechocorporativo.daos.PoderesDAO;

/**
 * Servlet implementation class UpdatePoderes
 */
@WebServlet("/UpdatePGServlet")
public class UpdatePGServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	public void processRequest(HttpServletRequest tuRequest,HttpServletResponse tuResponse)throws IOException{
     
		 //PrintWriter out = tuResponse.getWriter();
		 String idPoder = tuRequest.getParameter("idPoder");
		 System.out.println("idPoder " + idPoder);
		 String action = tuRequest.getParameter("action");
	   	 //String idSeccion = tuRequest.getParameter("idSeccion");
	   	 HttpSession session = tuRequest.getSession();	
	   	 SessionBean sessionBean = (SessionBean)session.getAttribute("sessionBean");
	   	 //String lstIdCurrentEmpresa = sessionBean.getIdCurrentEmpresa();
	   	 String idEmpresa = tuRequest.getParameter("idEmpresa");
	   	 PoderesDAO poderesDao = new PoderesDAO();   	 
	   	 
	   	 
	   	if(action.equals("edita")){	   		
	   		idPoder   = tuRequest.getParameter("idPoder");
	   		idEmpresa = tuRequest.getParameter("idEmpresa");
	   		PoderesBean tuPoderesBean = poderesDao.dameDatosPoderes(Integer.parseInt(idPoder),Integer.parseInt(idEmpresa));
			
			if(tuPoderesBean != null){
				session.setAttribute("poderesBean", tuPoderesBean);
				tuResponse.sendRedirect(tuRequest.getContextPath()+"/faces/jsp/captura/ajax/editaNewPG.jsp");
			/*}
			
	   	}else if(action.equals("guarda")){
	   		PoderesBean tuPoderesBean = poderesDao.actualizaPoderes(tuPoderesBean);
	   		if(tuPoderesBean != null){
				session.setAttribute("poderesBean", tuPoderesBean);
				tuResponse.sendRedirect(tuRequest.getContextPath()+"/faces/jsp/captura/ajax/editaNewPG.jsp");
				}else{
					
					session.setAttribute("poderesBean", tuPoderesBean);
				//response.sendRedirect(request.getContextPath()+"/faces/jsp/admin/nuevoUsuario.jsp?mensaje="+leyenda);
				tuResponse.sendRedirect(tuRequest.getContextPath()+"/faces/jsp/captura/poderes/poderesGenerales.jsp");
				
				}
				*/
	   	     }}
			
	}
	

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

}
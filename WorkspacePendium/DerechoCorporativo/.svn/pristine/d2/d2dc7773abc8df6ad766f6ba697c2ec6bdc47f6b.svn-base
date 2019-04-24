package mx.com.televisa.derechocorporativo.apoderados.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.com.televisa.derechocorporativo.bean.SessionBean;
import mx.com.televisa.derechocorporativo.daos.PoderesDAO;
import mx.com.televisa.derechocorporativo.modules.captura.Pagina;

/**
 * Servlet implementation class DeletePGServlet
 */
@WebServlet("/DeletePGServlet")
public class DeletePGServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	 public void processRequest(HttpServletRequest tuRequest,HttpServletResponse tuResponse)throws IOException{

	     PrintWriter out = tuResponse.getWriter();
		 String idPoder = tuRequest.getParameter("idPoder");
		 System.out.println("idPoder " + idPoder);
	   	 //String idSeccion = tuRequest.getParameter("idSeccion");
	   	 HttpSession session = tuRequest.getSession();	
	   	 SessionBean sessionBean = (SessionBean)session.getAttribute("sessionBean");
	   	 String lstIdCurrentEmpresa = sessionBean.getIdCurrentEmpresa();
	   	 PoderesDAO poderesDao = new PoderesDAO();
	   	 boolean ok = poderesDao.DeletePoderes(Integer.parseInt(idPoder),Integer.parseInt(lstIdCurrentEmpresa));
	   	 if(ok){ 
	   		out.println(tuRequest.getContextPath()+"/jsp/captura/poderes/poderesGenerales.jsp?idSeccion="+22);
	   		//tuResponse.sendRedirect(tuRequest.getContextPath()+"/jsp/captura/poderes/poderesGenerales.jsp?idSeccion="+22);
	   	 }else{
	   		out.println("NOK");
	   	 }
	   	 /*String allow =  poderesDao.allowToOpenForm(Integer.parseInt(idPoder), 
	   			 									Integer.parseInt(lstIdCurrentEmpresa),
	   			 									Integer.parseInt(sessionBean.getIdUser()));
	   	 String[] allowIdOcupa = new String[2]; 
	   	 int idUsrOcu = 0;
		   	if(allow.contains(",")){		
				allowIdOcupa = allow.split(",");
			}
			else{
				allowIdOcupa[0]=allow;
			}
			
			
			
			if (allowIdOcupa[0].equals("NOK")){
				session.setAttribute("idUserOcupaReforma",allowIdOcupa[1]);
			}
			
			if(session.getAttribute("idUserOcupaReforma") != null){
				idUsrOcu = Integer.parseInt(session.getAttribute("idUserOcupaReforma").toString());
			}
			String nomUsrOcu = Pagina.getNomUsrOcupado(idUsrOcu);									
			out.println(allowIdOcupa[0]+","+nomUsrOcu);
		 
		*/
   	
   }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		processRequest(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

}

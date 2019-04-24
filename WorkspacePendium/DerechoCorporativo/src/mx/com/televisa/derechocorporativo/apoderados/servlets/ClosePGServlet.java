package mx.com.televisa.derechocorporativo.apoderados.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.com.televisa.derechocorporativo.bean.SessionBean;

/**
 * Servlet implementation class ClosePGServlet
 */
@WebServlet("/ClosePGServlet")
public class ClosePGServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public void processRequest(HttpServletRequest tuRequest,HttpServletResponse tuResponse)throws IOException{
    	String idSeccion = tuRequest.getParameter("idSeccion");
    	HttpSession session = tuRequest.getSession();	
    	SessionBean sessionBean = (SessionBean)session.getAttribute("sessionBean");
    	String lstIdCurrentEmpresa = sessionBean.getIdCurrentEmpresa();
		tuResponse.sendRedirect(tuRequest.getContextPath()+"/jsp/captura/poderes/poderesGenerales.jsp?idSeccion="+idSeccion);
		
    	
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

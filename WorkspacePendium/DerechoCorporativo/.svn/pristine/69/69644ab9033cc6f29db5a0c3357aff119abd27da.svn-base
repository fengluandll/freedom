package mx.com.televisa.derechocorporativo.apoderados.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.com.televisa.derechocorporativo.bean.SessionBean;

/**
 * Servlet implementation class NewPGServlet
 */
@WebServlet("/NewPGServlet")
public class NewPGServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	
	public void processRequest(HttpServletRequest tuRequest
            				  ,HttpServletResponse tuResponse)throws IOException{
	HttpSession session = tuRequest.getSession();	
	SessionBean sessionBean = (SessionBean)session.getAttribute("sessionBean");
	String lstIdCurrentEmpresa = sessionBean.getIdCurrentEmpresa();
	int idEMpresa = Integer.parseInt(lstIdCurrentEmpresa);
	String idSeccion = tuRequest.getParameter("pidSeccion");
	tuResponse.sendRedirect(tuRequest.getContextPath()+"/jsp/captura/ajax/newPG.jsp?pidSeccion="+idSeccion);
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewPGServlet() {
        super();
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

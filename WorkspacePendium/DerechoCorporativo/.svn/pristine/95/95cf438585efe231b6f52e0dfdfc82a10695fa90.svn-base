package mx.com.televisa.derechocorporativo.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.com.televisa.derechocorporativo.bean.SessionBean;
import mx.com.televisa.derechocorporativo.daos.SalirDAO;

/**
 * Servlet implementation class LoginExitServlet
 */
@WebServlet("/LoginExitServlet")
public class LoginExitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SalirDAO salirDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginExitServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void processRequest(HttpServletRequest tRequest, HttpServletResponse tResponse) throws IOException{
    	salirDAO = new SalirDAO();
    	HttpSession session = tRequest.getSession();
    	SessionBean sessionBean = (SessionBean)session.getAttribute("sessionBean");
    	salirDAO.salirControlSeccion(sessionBean);
    	borrarSecciones(tRequest);
    	tResponse.sendRedirect(tRequest.getContextPath()+"/faces/jsp/login/login.jsp?logout=true");
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
	

	public static void borrarSecciones(HttpServletRequest request){
		List<String> listSecciones = new ArrayList<String>();;
		for(int i=1;i<35;i++){
			listSecciones.add("sec"+i);
		}


		for(String sec : listSecciones){
			request.getSession().removeAttribute(sec);
		}

	}

}

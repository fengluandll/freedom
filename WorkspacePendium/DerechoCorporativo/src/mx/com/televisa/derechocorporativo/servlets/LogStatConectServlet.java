package mx.com.televisa.derechocorporativo.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.com.televisa.derechocorporativo.bean.LogStatConectBean;
import mx.com.televisa.derechocorporativo.bean.SessionBean;
import mx.com.televisa.derechocorporativo.daos.LogStatConectDAO;
import mx.com.televisa.derechocorporativo.util.FacesUtils;

/**
 * Servlet implementation class LogStatConectServlet
 */
@WebServlet("/LogStatConectServlet")
public class LogStatConectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogStatConectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void processRequest(HttpServletRequest tRequest, HttpServletResponse tResponse) throws IOException{
    	//int lIdUser = Integer.parseInt(FacesUtils.getSessionBean().getIdUser());
    	HttpSession session = tRequest.getSession();
    	SessionBean sessionBean = (SessionBean)session.getAttribute("sessionBean");
    	//System.out.println("Entro Servlet processRequest: "+sessionBean);
    	if(sessionBean != null){
    		//System.out.println("Entro sessionBean");
	    	LogStatConectBean luLogStatConectBean = new LogStatConectBean(Integer.parseInt(sessionBean.getIdUser()));
	    	LogStatConectDAO luLogStatConectDAO = new LogStatConectDAO ();
	    	luLogStatConectDAO.insertarLogStatConect(luLogStatConectBean);
    	}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("Entro Servlet doGet");
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("Entro Servlet doPost");
		processRequest(request, response);
	}

}

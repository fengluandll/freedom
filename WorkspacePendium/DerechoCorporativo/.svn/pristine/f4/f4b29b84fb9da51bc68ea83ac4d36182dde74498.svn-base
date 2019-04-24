package mx.com.televisa.derechocorporativo.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.com.televisa.derechocorporativo.daos.Abc_AppConfigTabDAO;

/**
 * Servlet implementation class Abc_appConfigTabServlet
 */
@WebServlet("/Abc_appConfigTabServlet")
public class Abc_appConfigTabServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	public void processRequest(HttpServletRequest tuRequest
                              ,HttpServletResponse tuResponse)
			                                                throws IOException{
		tuResponse.setContentType("text/html;charset=UTF-8");
		tuRequest.setCharacterEncoding("UTF-8");

		Abc_AppConfigTabDAO luAbcAppConfigTabDAO = new Abc_AppConfigTabDAO();

		String lstId = tuRequest.getParameter("id");
		String lstCod = tuRequest.getParameter("cod");
		String lstNom = tuRequest.getParameter("nom");
		String lstDes = tuRequest.getParameter("des");
		String lstVal = tuRequest.getParameter("val");
		String lsAct = tuRequest.getParameter("action") == null?"":tuRequest.getParameter("action");

		System.out.println("Serv:  "+lstId+" "+lstCod+" "+lstNom+" "+lstDes+" "+lstVal+" "+lsAct);
		
		String lsMsg;

		if(lsAct.equals("edita")){
			tuResponse.sendRedirect(tuRequest.getContextPath()+"/faces/jsp/admin/abc_appConfigTabNewEdit.jsp?action=edita"
	                + "&id="+lstId
	                +"&cod="+lstCod
	                +"&nom="+lstNom
	                +"&des="+lstDes
	                +"&val="+lstVal
					);
		}else if(lsAct.equals("elimina")){
			lsMsg = luAbcAppConfigTabDAO.borrarAppConfigTab(lstId);
			System.out.println("Msg: "+lsMsg);
			tuResponse.sendRedirect(tuRequest.getContextPath()+"/faces/jsp/admin/abc_appConfigTab.jsp");

		}
		else{//guardar y editar
			if(lstId == null || lstId.equals("")){
				lsMsg = luAbcAppConfigTabDAO.insertarAppConfigTab(lstCod, lstNom, lstDes, lstVal);
				System.out.println("Msg: "+lsMsg);
				tuResponse.sendRedirect(tuRequest.getContextPath()+"/faces/jsp/admin/abc_appConfigTab.jsp");

			}else{
				lsMsg = luAbcAppConfigTabDAO.actualizarAppConfigTab(lstId, lstCod, lstNom, lstDes, lstVal);
				tuResponse.sendRedirect(tuRequest.getContextPath()+"/faces/jsp/admin/abc_appConfigTab.jsp");

			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
	}

}
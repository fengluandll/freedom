package mx.com.televisa.derechocorporativo.servlets;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.com.televisa.derechocorporativo.bean.MenuBean;
import mx.com.televisa.derechocorporativo.bean.RolBean;
import mx.com.televisa.derechocorporativo.bean.SessionBean;
import mx.com.televisa.derechocorporativo.daos.RolesDAO;
import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;
import mx.com.televisa.derechocorporativo.data.packages.DERCORP_PANEL_CONTROL_PKG;

/**
 * Servlet implementation class GuardaCatalogoServlet
 */
@WebServlet("/GuardaRolServlet")
public class GuardaRolServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RolBean  rolBean;
	MenuBean menuBean;
	RolesDAO   rolDAO;
       
   
	public void processRequest(HttpServletRequest tuRequest, HttpServletResponse tuResponse) throws IOException{
		tuResponse.setContentType("text/html;charset=UTF-8");
		tuRequest.setCharacterEncoding("UTF-8");
		
		rolBean  = new RolBean();
		menuBean = new MenuBean();
	    rolDAO   = new RolesDAO();
	    
		ConnectionWrapper   luConnectionWrapper = null;
		ResultSet           luResultSet         = null;
		CallableStatement   luCallableStatement = null;
		Enumeration<String> lnParamNames        = tuRequest.getParameterNames();
		HttpSession session = tuRequest.getSession();
		SessionBean sessionBean = (SessionBean)session.getAttribute("sessionBean");

	    String action = tuRequest.getParameter("action");
	    
		rolBean.setIdRol(0);
		rolBean.setNomName(tuRequest.getParameter("txtNombre"));
		rolBean.setDesDescription(tuRequest.getParameter("txtDesc"));
		rolBean.setNumPasswordExpirationDays(Integer.valueOf(tuRequest.getParameter("txtExp")));
		rolBean.setRevokeEnterprises(tuRequest.getParameter("emp_h"));
		rolBean.setReportPre(tuRequest.getParameter("reppre_h"));
		rolBean.setReportPer(tuRequest.getParameter("repper_h"));
		rolBean.setUserModifico(Integer.parseInt(sessionBean.getIdUser()));
		//System.out.println("RevokeEnt:"+rolBean.getRevokeEnterprises());
		
		boolean paso = false;
		if(action.equals("nuevo")){
			int seq = rolDAO.dameSecuenciaIdMenu();
			paso = rolDAO.insertRol(rolBean,seq);
			
			try {
				luConnectionWrapper = new ConnectionWrapper();
				luCallableStatement = luConnectionWrapper.prepareCall(DERCORP_PANEL_CONTROL_PKG.UPDATE_NEW_MENU_PR);

				while (lnParamNames.hasMoreElements()) {					
					String lsParamName = lnParamNames.nextElement();
					String lsValue     = tuRequest.getParameter(lsParamName);

					if(lsValue != null && !lsValue.equals("0") && !lsValue.equals("")) {
						if(lsParamName.contains("chk_")){
							System.out.println(lsParamName  + ": " + lsValue);
							luCallableStatement.setString(1,lsParamName);
							luCallableStatement.setInt(2,seq);
							
							luCallableStatement.addBatch();
						}

					}
				}
				
				int[] laUpdateCounts = luCallableStatement.executeBatch();
				
				lnParamNames        = tuRequest.getParameterNames();
				luCallableStatement = luConnectionWrapper.prepareCall(DERCORP_PANEL_CONTROL_PKG.UPDATE_NEW_ROL_SECCION_PR);

				while (lnParamNames.hasMoreElements()) {					
					String lsParamName = lnParamNames.nextElement();
					String lsValue     = tuRequest.getParameter(lsParamName);

					if(lsValue != null && !lsValue.equals("0") && !lsValue.equals("")) {
						if(lsParamName.contains("chkp_")){
							System.out.println(lsParamName  + ": " + lsValue);
							luCallableStatement.setString(1,lsParamName);
							luCallableStatement.addBatch();
						}

					}
				}
				
				int[] laUpdatePCounts = luCallableStatement.executeBatch();
		

			} catch(Exception ex) {
					System.out.println(ex.getMessage());
				
			} finally {
				
				ConnectionWrapper.closeAll(luResultSet, luCallableStatement, luConnectionWrapper);
			}

			
		}else if(action.equals("edita")){
			rolBean.setIdRol(Integer.valueOf(tuRequest.getParameter("idRol")));
			paso = rolDAO.updateRol(rolBean);
			
			try {
				luConnectionWrapper = new ConnectionWrapper();
				luCallableStatement = luConnectionWrapper.prepareCall(DERCORP_PANEL_CONTROL_PKG.UPDATE_MENU_PR);

				while (lnParamNames.hasMoreElements()) {					
					String lsParamName = lnParamNames.nextElement();
					String lsValue     = tuRequest.getParameter(lsParamName);

					if(lsValue != null && !lsValue.equals("0") && !lsValue.equals("")) {
						if(lsParamName.contains("chk_")){
							System.out.println(lsParamName  + ": " + lsValue);
							luCallableStatement.setString(1,lsParamName);
							luCallableStatement.addBatch();
						}

					}
				}
				
				int[] laUpdateCounts = luCallableStatement.executeBatch();
				
				lnParamNames        = tuRequest.getParameterNames();
				luCallableStatement = luConnectionWrapper.prepareCall(DERCORP_PANEL_CONTROL_PKG.UPDATE_ROL_SECCION_PR);

				while (lnParamNames.hasMoreElements()) {					
					String lsParamName = lnParamNames.nextElement();
					String lsValue     = tuRequest.getParameter(lsParamName);

					if(lsValue != null && !lsValue.equals("0") && !lsValue.equals("")) {
						if(lsParamName.contains("chkp_")){
							System.out.println(lsParamName  + ": " + lsValue);
							luCallableStatement.setString(1,lsParamName);
							luCallableStatement.addBatch();
						}

					}
				}
				
				int[] laUpdatePCounts = luCallableStatement.executeBatch();
		

			} catch(Exception ex) {
					System.out.println(ex.getMessage());
				
			} finally {
				
				ConnectionWrapper.closeAll(luResultSet, luCallableStatement, luConnectionWrapper);
			}

		}
		
		if(paso){
			tuResponse.sendRedirect(tuRequest.getContextPath()+"/AdminRolesServlet");
		}
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

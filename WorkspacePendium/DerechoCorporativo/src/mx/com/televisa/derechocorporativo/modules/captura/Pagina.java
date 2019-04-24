/**
* Project: Derecho Corporativo
*
* File: Pagina.java
*
* Created on: Agosto 31, 2015 at 12:00
*
* Copyright (c) - Televisa - 2015
*/

package mx.com.televisa.derechocorporativo.modules.captura;

import java.awt.Window;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Iterator;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.mysql.jdbc.PreparedStatement;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
import mx.com.televisa.derechocorporativo.bean.SessionBean;
import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;
import mx.com.televisa.derechocorporativo.data.packages.DERCORP_CONSULTA_PKG;
import mx.com.televisa.derechocorporativo.util.FacesUtils;

/**
 * Crear paginas web html.
 *
 * @author KAZ - CONSULTING/
 *         Ivan Castaneda Loeza
 *         Jesus Argumedo
 *         Eduardo Nava
 *         Ernesto Corona Mendoza
 *
 * @version 1.0.0
 *
 * @date Agosto 31, 2015 at 12:00 pm
 *
 */
public class Pagina {
	
	final static Logger log = Logger.getLogger(Pagina.class);
	public static ConnectionWrapper connection;

	private String modo = "DEFAULT";
	private String printedTabName = "";
	
	public boolean useFlexTablesAsHtml = false;
	public HttpServletRequest request;
	public String idEmpresa;
	
	
	
	/**
	 * Crear librerias web html.
	 * 
	 * @return String
	 */
	
	public static String getHTML() {
		
		return getHTML("*");
	}
	
	public static String getHTML(String seccionId){
		return new Pagina().getHTMLLayout(seccionId);
	}
	public static String getHTMLV2(String seccionId,String idEmp){
		log.info("Seccion id: "+seccionId);
		log.info("IdEmp : "+idEmp);
		return new Pagina().getHTMLLayoutV2(seccionId,idEmp);
	}
	
	
	public static String getHTMLReforma(String seccionId){
		return new Pagina().getHTMLLayoutReformas(seccionId);
	}
	
	public static String getTabNewSave() {
		
		return getTabNewSave("*");
	}
	
	public static String getTabNewSave(String seccionId) {
		return new Pagina().getTabsNewSave(seccionId);
	}
	
	public static String getMenuReforma() {
		return getMenuReforma("*");
	}
	
	public static String getMenuReforma(String seccionId) {
		return new Pagina().getMenuReformas(seccionId);
	}
	
	public String getTabsNewSave(String seccionId){
		StringBuilder      luStringBuilder = new StringBuilder();
		ArrayList<Seccion> laSeccion       = null;
		String             liIdEmpresa     = FacesUtils.getSessionBean().getIdCurrentEmpresa();
		String       	   lsIdRol         = FacesUtils.getSessionBean().getIdRol();
		
		this.idEmpresa = liIdEmpresa;
		
		String psEditable = FacesUtils.getSessionBean().getEditCon();
		
		FacesUtils.getSessionBean().setPaginaModo(modo);

		try {

			connection = new ConnectionWrapper();
			
			
			
			if(psEditable != null && psEditable.equals("disabled")){
				
				laSeccion  = getSeccionesConInfo(connection,lsIdRol, seccionId, liIdEmpresa);
			} else {
	        	
	        	laSeccion = getSecciones(connection,lsIdRol, seccionId);
	        }
	        	
			
			EmpresaValues empVal = new EmpresaValues(connection, liIdEmpresa);
			ArrayList<Seccion> visibleSeccionList = empVal.getVisibleSeccionList(laSeccion);
			
			  
			  
			luStringBuilder.append("<div id=\"TabbedPanels1\" class=\"list-group\">");
			luStringBuilder.append("<table class=\"list-group  table-of-contents\">");

			
			int tabReference = 0;
			
			for (Seccion luSeccion : laSeccion) {
				
				String hideText = "";
				if(!visibleSeccionList.contains(luSeccion)) {
					
					hideText = " style='display:none'";
					luStringBuilder.append("<!-- Ocultando la Seccion de ECS -->");
				}
				
				
				String addJavascript = "";
				
				if(luSeccion.nomSeccion.contains("Reformas")) {
					
					addJavascript = "selectSubTabName(document.getElementById('reformasHiddenFirstTabName').value);";
				}
				
				
				if(psEditable != null && !psEditable.equals("")){ 
					
					if(luSeccion.nomSeccion.contains("Apoderados")) {
						
						addJavascript = "selectSubTabName(document.getElementById('apoderadosHiddenFirstTabName').value);";

					}
				}
				
				
				if(modo.equals("PRINT")) {
				
					printedTabName = luSeccion.nomSeccion;
				
				} else {
					
					
					String addSpryCode = ",{ defaultTab: " + tabReference + " }";
					
					if(luSeccion.nomSeccion.equals("Reformas y Movimientos Corporativos")){
						luStringBuilder.append( "<tr bgcolor=\"#2B385D\" style=\"cursor: pointer;\" onmouseover=\"cambiar_color_over(this)\" onmouseout=\"cambiar_color_out(this)\">"+	
								"<td class=\"list-group-item\" style=\"color:#FFFFFF;font-family: 'Open Sans', sans-serif;text-align:center;width:300px; height:30px; font-size:15px;\" tabindex=\"0\"" + hideText + 
										" onclick=\"selectTabName('" + luSeccion.nomSeccion + "','" + addSpryCode + "'); "
										+ "go('"+ FacesUtils.getContextPath() +"/faces/jsp/captura/waitReformaMenu.jsp?idEmp="+liIdEmpresa+"&idSeccion="+luSeccion.idSeccion+"'); " + addJavascript +  "\">" + 
										luSeccion.nomSeccion +
										"</td>"+ "</tr>");
					}
					else{
						luStringBuilder.append( "<tr bgcolor=\"#2B385D\" style=\"cursor: pointer;\" onmouseover=\"cambiar_color_over(this)\" onmouseout=\"cambiar_color_out(this)\">"+	
								"<td class=\"list-group-item\" style=\"color:#FFFFFF;font-family: 'Open Sans', sans-serif;text-align:center;width:300px; height:30px; font-size:15px;\" tabindex=\"0\"" + hideText + 
										" onclick=\"selectTabName('" + luSeccion.nomSeccion + "','" + addSpryCode + "'); "
										+ "go('"+ FacesUtils.getContextPath() +"/faces/jsp/captura/capWaitNewSave.jsp?idEmp="+liIdEmpresa+"&idSeccion="+luSeccion.idSeccion+"'); " + addJavascript +  "\">" + 
										luSeccion.nomSeccion +
										"</td>"+ "</tr>");
					}
					
				}
				
				tabReference++;
				
			}
			luStringBuilder.append("</table>");
			luStringBuilder.append("<div class=\"list-group\">");
			
		} catch (Exception ex) {
			// TODO - Pendiente - Gestion de Excepciones
            ex.printStackTrace();
			
		} finally {
		    ConnectionWrapper.closeAll(connection);
		}
		return luStringBuilder.toString();
	}
	
	public String getMenuReformas(String seccionId){
		StringBuilder      luStringBuilder = new StringBuilder();
		ArrayList<Seccion> laSeccion       = null;
		String             liIdEmpresa     = FacesUtils.getSessionBean().getIdCurrentEmpresa();
		String       	   lsIdRol         = FacesUtils.getSessionBean().getIdRol();
		
		this.idEmpresa = liIdEmpresa;
		
		String psEditable = FacesUtils.getSessionBean().getEditCon();
		
		FacesUtils.getSessionBean().setPaginaModo(modo);

		try {

			connection = new ConnectionWrapper();
			
			
			
			if(psEditable != null && psEditable.equals("disabled")){

				laSeccion  = getSeccionesConInfo(connection,lsIdRol, seccionId, liIdEmpresa);
	        } else {
	        	
	        	laSeccion = getSeccionReforma(connection,lsIdRol, seccionId);
	        }
	        	
			
			EmpresaValues empVal = new EmpresaValues(connection, liIdEmpresa);
			ArrayList<Seccion> visibleSeccionList = empVal.getVisibleSeccionList(laSeccion);
			
			  
			  
			luStringBuilder.append("<div id=\"TabbedPanels1\" class=\"list-group\">");
			luStringBuilder.append("<table class=\"list-group  table-of-contents\">");

			
			int tabReference = 0;
			
			for (Seccion luSeccion : laSeccion) {
				
				String hideText = "";
				String hideComite="";
				if(!visibleSeccionList.contains(luSeccion)) {
					
					hideText = " style='display:none'";
					luStringBuilder.append("<!-- Ocultando la Seccion de ECS -->");
				}
				if((!this.idEmpresa.equals("248") && !this.idEmpresa.equals("2122") && !this.idEmpresa.equals("2031")) && luSeccion.idSeccion == 31){
					hideComite = "display:none;";
					luStringBuilder.append("<!-- Ocultando la Seccion de Comités -->");
				}
				
				String addJavascript = "";
				
				if(luSeccion.nomSeccion.contains("Reformas")) {
					
					addJavascript = "selectSubTabName(document.getElementById('reformasHiddenFirstTabName').value);";
				}
				
				
				if(psEditable != null && !psEditable.equals("")){ 
					
					if(luSeccion.nomSeccion.contains("Apoderados")) {
						
						addJavascript = "selectSubTabName(document.getElementById('apoderadosHiddenFirstTabName').value);";

					}
				}
				
				
				if(modo.equals("PRINT")) {
				
					printedTabName = luSeccion.nomSeccion;
				
				} else {
					
					
					String addSpryCode = ",{ defaultTab: " + tabReference + " }";
					
					luStringBuilder.append( "<tr bgcolor=\"#2B385D\" style=\"cursor: pointer;\" onmouseover=\"cambiar_color_over(this)\" onmouseout=\"cambiar_color_out(this)\">"+	
							"<td class=\"list-group-item\" style=\"color:#FFFFFF;font-family: 'Open Sans', sans-serif;text-align:center;width:300px; height:30px; font-size:15px;"+hideComite+"\" tabindex=\"0\"" + hideText +  
													" onclick=\"selectTabName('" + luSeccion.nomSeccion + "','" + addSpryCode + "'); "
													+ "go('"+ FacesUtils.getContextPath() +"/faces/jsp/captura/waitReformas.jsp?idEmp="+liIdEmpresa+"&idSeccion="+luSeccion.idSeccion+"'); " + addJavascript +  "\">" + 
													luSeccion.nomSeccion +
													"</td>"+ "</tr>");
				}
				
				tabReference++;
				
			}
			luStringBuilder.append("</table>");
			luStringBuilder.append("<div class=\"list-group\">");
			
		} catch (Exception ex) {
			// TODO - Pendiente - Gestion de Excepciones
            ex.printStackTrace();
			
		} finally {
		    ConnectionWrapper.closeAll(connection);
		}
		return luStringBuilder.toString();
	}
	
	public String getHTMLLayoutReformas(String seccionId) {
		StringBuilder      luStringBuilder = new StringBuilder();
		ArrayList<Seccion> laSeccion       = null;
		String             liIdEmpresa     = FacesUtils.getSessionBean().getIdCurrentEmpresa();
		String       	   lsIdRol         = FacesUtils.getSessionBean().getIdRol();
		
		this.idEmpresa = liIdEmpresa;
		
		String psEditable = FacesUtils.getSessionBean().getEditCon();
		
		FacesUtils.getSessionBean().setPaginaModo(modo);

		try {

			connection = new ConnectionWrapper();
			
			
			
			if(psEditable != null && psEditable.equals("disabled")){

				laSeccion  = getSeccionesConInfo(connection,lsIdRol, seccionId, liIdEmpresa);
	        } else {
	        		laSeccion = getSeccionReforma(connection,lsIdRol, seccionId);
	        }
	        
	        
			
			
			
			
			
			
			EmpresaValues empVal = new EmpresaValues(connection, liIdEmpresa);
			ArrayList<Seccion> visibleSeccionList = empVal.getVisibleSeccionList(laSeccion);
			
			
			  if(psEditable != null && psEditable.equals("disabled")){
		        	if(!modo.equals("PRINT")) {		        	
		        		luStringBuilder.append("<div align='right'><a href='#' onclick=\"openPrintTab('*')\">Imprimir Todo</a></div><br>");
		        	}
		        }
			  
			if(psEditable != null && psEditable.equals("disabled")){  
				luStringBuilder.append("<div id=\"TabbedPanels1\" class=\"TabbedPanels\">");
				luStringBuilder.append("<ul class=\"TabbedPanelsTabGroup\">");
	
				
				int tabReference = 0;
				
				for (Seccion luSeccion : laSeccion) {
					
					String hideText = "";
					if(!visibleSeccionList.contains(luSeccion)) {
						
						hideText = " style='display:none'";
						luStringBuilder.append("<!-- Ocultando la Seccion de ECS -->");
					}
					
					
					String addJavascript = "";
					
					if(luSeccion.nomSeccion.contains("Reformas")) {
						
						addJavascript = "selectSubTabName(document.getElementById('reformasHiddenFirstTabName').value);";
					}
					
					
					if(psEditable != null && !psEditable.equals("")){ 
						
						if(luSeccion.nomSeccion.contains("Apoderados")) {
							
							addJavascript = "selectSubTabName(document.getElementById('apoderadosHiddenFirstTabName').value);";
							//System.out.println("addJavascript "+addJavascript);
						}
					}
					
					
					if(modo.equals("PRINT")) {
					
						printedTabName = luSeccion.nomSeccion;
					
					} else {
						
						
						String addSpryCode = ",{ defaultTab: " + tabReference + " }";
						
						luStringBuilder.append("    <li class=\"TabbedPanelsTab\" tabindex=\"0\"" + hideText + 
								" onclick=\"selectTabName('" + luSeccion.nomSeccion + "','" + addSpryCode + "');" + addJavascript + "\">" + 
									luSeccion.nomSeccion + 
									"</li>");	 
					}
					
					tabReference++;
					
				}
				luStringBuilder.append("</ul>");
				luStringBuilder.append("<div class=\"TabbedPanelsContentGroup\">");
			}

			for (Seccion luSeccion : laSeccion) {
				System.out.println(luSeccion.nomSeccion);
				String hideText = "";
				if(!visibleSeccionList.contains(luSeccion)) {
					
					hideText = " style='display:none'";
					luStringBuilder.append("<!-- Ocultando la Seccion de ECS -->");
				}
				
			    luStringBuilder.append("<div class=\"TabbedPanelsContent\"" + hideText + ">"
						+ luSeccion.toHTML(connection, empVal, this)
						+ "</div>");
			}
			
			luStringBuilder.append("</div>");
			luStringBuilder.append("</div>");
			//System.out.println("luStringBuilder "+luStringBuilder);
			
			
		} catch (Exception ex) {
			// TODO - Pendiente - Gestion de Excepciones
            ex.printStackTrace();
			
		} finally {
		    ConnectionWrapper.closeAll(connection);
		}
		return luStringBuilder.toString();
	}
	
	public String getHTMLLayout(String seccionId) {
		StringBuilder      luStringBuilder = new StringBuilder();
		ArrayList<Seccion> laSeccion       = null;
		String             liIdEmpresa     = FacesUtils.getSessionBean().getIdCurrentEmpresa();
		String       	   lsIdRol         = FacesUtils.getSessionBean().getIdRol();
		
		this.idEmpresa = liIdEmpresa;
		
		String psEditable = FacesUtils.getSessionBean().getEditCon();
		
		FacesUtils.getSessionBean().setPaginaModo(modo);

		try {

			connection = new ConnectionWrapper();
			
			
			
			if(psEditable != null && psEditable.equals("disabled")){

				laSeccion  = getSeccionesConInfo(connection,lsIdRol, seccionId, liIdEmpresa);
	        } else {
	        		laSeccion = getSecciones(connection,lsIdRol, seccionId);
	        }
	        
	        
			
			
			
			
			
			
			EmpresaValues empVal = new EmpresaValues(connection, liIdEmpresa);
			ArrayList<Seccion> visibleSeccionList = empVal.getVisibleSeccionList(laSeccion);
			
			
			  if(psEditable != null && psEditable.equals("disabled")){
		        	if(!modo.equals("PRINT")) {
		        		luStringBuilder.append("<div align='right'><a href='#' onclick=\"openPrintTab('*')\">Imprimir Todo</a></div><br>");
		        	}
		        }
			  
			if(psEditable != null && psEditable.equals("disabled")){  
				luStringBuilder.append("<div id=\"TabbedPanels1\" class=\"TabbedPanels\">");
				luStringBuilder.append("<ul class=\"TabbedPanelsTabGroup\">");
	
				
				int tabReference = 0;
				
				for (Seccion luSeccion : laSeccion) {
					
					String hideText = "";
					if(!visibleSeccionList.contains(luSeccion)) {
						
						hideText = " style='display:none'";
						luStringBuilder.append("<!-- Ocultando la Seccion de ECS -->");
					}
					
					
					String addJavascript = "";
					
					if(luSeccion.nomSeccion.contains("Reformas")) {
						
						addJavascript = "selectSubTabName(document.getElementById('reformasHiddenFirstTabName').value);";
					}
					
					
					if(psEditable != null && !psEditable.equals("")){ 
						
						if(luSeccion.nomSeccion.contains("Apoderados")) {
							
							addJavascript = "selectSubTabName(document.getElementById('apoderadosHiddenFirstTabName').value);";
							//System.out.println("addJavascript "+addJavascript);
						}
					}
					
					
					if(modo.equals("PRINT")) {
					
						printedTabName = luSeccion.nomSeccion;
					
					} else {
						
						
						String addSpryCode = ",{ defaultTab: " + tabReference + " }";
						
						luStringBuilder.append("    <li id='sec"+luSeccion.idSeccion+"' class=\"TabbedPanelsTab\" tabindex=\"0\"" + hideText + 
								" onclick=\"selectTabName('" + luSeccion.nomSeccion + "','" + addSpryCode + "');" + addJavascript + "\">" + 
									luSeccion.nomSeccion + 
									"</li>");
					}
					
					tabReference++;
					
				}
				luStringBuilder.append("</ul>");
				luStringBuilder.append("<div class=\"TabbedPanelsContentGroup\">");
			}else{
				System.out.println("si debe de aperecer");
				
			}
			System.out.println(laSeccion);
			for (Seccion luSeccion : laSeccion) {
				System.out.println(luSeccion.nomSeccion);
				String hideText = "";
				if(!visibleSeccionList.contains(luSeccion)) {
					
					hideText = " style='display:none'";
					luStringBuilder.append("<!-- Ocultando la Seccion de ECS -->");
				}
				
			    luStringBuilder.append("<div class=\"TabbedPanelsContent\"" + hideText + ">"
						+ luSeccion.toHTML(connection, empVal, this)
						+ "</div>");
			}
			
			luStringBuilder.append("</div>");
			luStringBuilder.append("</div>");
			//System.out.println("luStringBuilder "+luStringBuilder);
			
			
		} catch (Exception ex) {
			// TODO - Pendiente - Gestion de Excepciones
            ex.printStackTrace();
			
		} finally {
		    ConnectionWrapper.closeAll(connection);
		}
		return luStringBuilder.toString();
	}
	
	public String getHTMLLayoutPrintAll() {
		StringBuilder      luStringBuilder = new StringBuilder();
		ArrayList<Seccion> laSeccion       = null;
		String             liIdEmpresa     = FacesUtils.getSessionBean().getIdCurrentEmpresa();
		String       	   lsIdRol         = FacesUtils.getSessionBean().getIdRol();
		
		this.idEmpresa = liIdEmpresa;
		
		String psEditable = FacesUtils.getSessionBean().getEditCon();
		
		FacesUtils.getSessionBean().setPaginaModo(modo);

		try {

			connection = new ConnectionWrapper();
			
			//laSeccion = getSecciones(connection,lsIdRol, "*");
			
			if(psEditable != null && psEditable.equals("disabled")){

				laSeccion  = getSeccionesConInfo(connection,lsIdRol, "*", liIdEmpresa);
	        } else {
	        	
	        	laSeccion = getSecciones(connection,lsIdRol, "*");
	        }
			
			
			EmpresaValues empVal = new EmpresaValues(connection, liIdEmpresa);
			ArrayList<Seccion> visibleSeccionList = empVal.getVisibleSeccionList(laSeccion);
			
			Iterator<Seccion> iter = laSeccion.iterator();
			
			while(iter.hasNext()){
				Seccion luSeccion = (Seccion)iter.next();
				/*ULR 06/01/2017 se agrego esta condicion para ocultar el titulo de la seccion de objeto social 
				 * para evitar redundancia con la subseccion y columna de la flex*/ 
				if(luSeccion.idSeccion!=16){
					luStringBuilder.append("<table width='100%'><tr><th class='pageTitle'>" + luSeccion.nomSeccion + "</th></tr></table>");
				}
				
				
				luStringBuilder.append(luSeccion.toHTML(connection, empVal, this));
				
				if(iter.hasNext()){
					luStringBuilder.append("<pd4ml-page-break/>");
				}
				
			}
			  
			/*luStringBuilder.append("<div id=\"TabbedPanels1\" class=\"TabbedPanels\">");
			luStringBuilder.append("<ul class=\"TabbedPanelsTabGroup\">");
			 	*/
			//for (Seccion luSeccion : laSeccion) {
				/*
				String hideText = "";
				if(!visibleSeccionList.contains(luSeccion)) {
					
					hideText = " style='display:none'";
					luStringBuilder.append("<!-- Ocultando la Seccion de ECS -->");
				}
				
				
				String addJavascript = "";
				
				if(luSeccion.nomSeccion.contains("Reformas")) {
					
					addJavascript = "selectSubTabName(document.getElementById('reformasHiddenFirstTabName').value);";
				}
				
				
				if(psEditable != null && !psEditable.equals("")){ 
					
					if(luSeccion.nomSeccion.contains("Apoderados")) {
						
						addJavascript = "selectSubTabName(document.getElementById('apoderadosHiddenFirstTabName').value);";
					}
				}
				*/
				//luStringBuilder.append("<table width='100%'><tr><th class='pageTitle'>" + luSeccion.nomSeccion + "</th></tr></table>");
				
				
				//luStringBuilder.append(luSeccion.toHTML(connection, empVal, this));
				//luStringBuilder.append("<pd4ml-page-break/>");
				
			//}
			/*luStringBuilder.append("</ul>");
			luStringBuilder.append("<div class=\"TabbedPanelsContentGroup\">");
			
			for (Seccion luSeccion : laSeccion) {
				
				String hideText = "";
				if(!visibleSeccionList.contains(luSeccion)) {
					
					hideText = " style='display:none'";
					luStringBuilder.append("<!-- Ocultando la Seccion de ECS -->");
				}
				
				
			    luStringBuilder.append("<div class=\"TabbedPanelsContent\"" + hideText + ">"
						+ luSeccion.toHTML(connection, empVal, this)
						+ "</div>");
			    
			    
			    
			    
			    
			}
			*/
			/*luStringBuilder.append("</div>");
			luStringBuilder.append("</div>");*/
			
			
		} catch (Exception ex) {
			// TODO - Pendiente - Gestion de Excepciones
            ex.printStackTrace();
			
		} finally {
		    ConnectionWrapper.closeAll(connection);
		}
		return luStringBuilder.toString();
	}
	
	/**
	 * Obtener las secciones para la pagina web html.
	 * 
	 * @param tuConnect
	 * @return ArrayList<Seccion>
	 */
	public static ArrayList<Seccion> getSeccionesRepo(ConnectionWrapper tuConnect, String tsIdRol, String seccionId) {
	    ArrayList<Seccion> laSecciones         = new ArrayList<Seccion>();
	    ResultSet          luResultSet         = null;
	    ResultSetMetaData  luMetaData          = null;
	    CallableStatement  luCallableStatement = null;

	    try {
		    luCallableStatement = tuConnect.prepareCall(DERCORP_CONSULTA_PKG.GET_SECCIONES_FILTERED_REPO_PR);
		    luCallableStatement.setInt(1,Integer.valueOf(tsIdRol));
		    luCallableStatement.registerOutParameter(2, OracleTypes.CURSOR);
		    luCallableStatement.setString(3,seccionId);
            luCallableStatement.execute();
		    luResultSet = ((OracleCallableStatement)luCallableStatement).getCursor(2);
			luMetaData = null;

			while (luResultSet.next()) {
			    laSecciones.add(new Seccion(luResultSet, luResultSet.getMetaData()));
			    luMetaData = luResultSet.getMetaData();
			}

		} catch (Exception ex) {
			// TODO - Pendiente - Gestion de Excepciones
			ex.printStackTrace();

		} finally {
			//ConnectionWrapper.closeAll(luResultSet, luCallableStatement);
		}
		return laSecciones;
	}
	
	/**
	 * Obtener las secciones para la pagina web html.
	 * 
	 * @param tuConnect
	 * @return ArrayList<Seccion>
	 */
	public static ArrayList<Seccion> getSecciones(ConnectionWrapper tuConnect, String tsIdRol, String seccionId) {
	    ArrayList<Seccion> laSecciones         = new ArrayList<Seccion>();
	    ResultSet          luResultSet         = null;
	    ResultSetMetaData  luMetaData          = null;
	    CallableStatement  luCallableStatement = null;

	    try {
		    luCallableStatement = tuConnect.prepareCall(DERCORP_CONSULTA_PKG.GET_SECCIONES_FILTERED_PR);
		    luCallableStatement.setInt(1,Integer.valueOf(tsIdRol));
		    luCallableStatement.registerOutParameter(2, OracleTypes.CURSOR);
		    luCallableStatement.setString(3,seccionId);
            luCallableStatement.execute();
		    luResultSet = ((OracleCallableStatement)luCallableStatement).getCursor(2);
			luMetaData = null;

			while (luResultSet.next()) {
			    laSecciones.add(new Seccion(luResultSet, luResultSet.getMetaData()));
			    luMetaData = luResultSet.getMetaData();
			}

		} catch (Exception ex) {
			// TODO - Pendiente - Gestion de Excepciones
			ex.printStackTrace();

		} finally {
			//ConnectionWrapper.closeAll(luResultSet, luCallableStatement);
		}
		return laSecciones;
	}
	
	public static ArrayList<Seccion> getSeccionReforma(ConnectionWrapper tuConnect, String tsIdRol, String seccionId) {
	    ArrayList<Seccion> laSecciones         = new ArrayList<Seccion>();
	    ResultSet          luResultSet         = null;
	    ResultSetMetaData  luMetaData          = null;
	    CallableStatement  luCallableStatement = null;

	    try {
		    luCallableStatement = tuConnect.prepareCall(DERCORP_CONSULTA_PKG.GET_MENU_REFORMA_FILTERED_PR);
		    luCallableStatement.setInt(1,Integer.valueOf(tsIdRol));
		    luCallableStatement.registerOutParameter(2, OracleTypes.CURSOR);
		    luCallableStatement.setString(3,seccionId);
            luCallableStatement.execute();
		    luResultSet = ((OracleCallableStatement)luCallableStatement).getCursor(2);
			luMetaData = null;

			while (luResultSet.next()) {
			    laSecciones.add(new Seccion(luResultSet, luResultSet.getMetaData()));
			    luMetaData = luResultSet.getMetaData();
			}

		} catch (Exception ex) {
			// TODO - Pendiente - Gestion de Excepciones
			ex.printStackTrace();

		} finally {
			//ConnectionWrapper.closeAll(luResultSet, luCallableStatement);
		}
		return laSecciones;
	}
	
	public static void setEmpAndSecc(String seccionId, String empresaId){
		Connection tuConnect							= null;
		java.sql.PreparedStatement 	luPreparedStatement = null;
		String lsUpdate 								= "";
		try{
			ConnectionWrapper puConnectionWrapper = new ConnectionWrapper();
			tuConnect = puConnectionWrapper.getConnection();
			lsUpdate = "UPDATE DERCORP_CONTROL_SECCION SET 	   ID_EMPRESA = ?,\n"+
	                   "                                   	   ID_SECCION = ?\n"+
	                   "                                       WHERE\n"+
	                   "									   ID_USER = ?";
			luPreparedStatement = tuConnect.prepareStatement(lsUpdate);
			luPreparedStatement.setInt(1, Integer.parseInt(empresaId));
			luPreparedStatement.setInt(2, Integer.parseInt(seccionId));
			luPreparedStatement.setInt(3, Integer.parseInt(FacesUtils.getSessionBean().getIdUser()));
			luPreparedStatement.executeUpdate();
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			try{
				tuConnect.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
	}
	
	public static String getNomUsrOcupado(int idUser){
		Connection tuConnect 				= null;
		java.sql.PreparedStatement luPreparedStatement = null;
		ResultSet rs 						  = null;
		String lsSelect						  = "";
		String nomUsrOc						  = "";
		
		try{
			ConnectionWrapper puConnectionWrapper = new ConnectionWrapper();
			tuConnect = puConnectionWrapper.getConnection();
			
			
				lsSelect = "	SELECT NOM_USER_LONG_NAME \n"+
						   "		FROM SS_USER_TAB\n"+
						   "		WHERE\n"+
						   "		ID_USER = ?";
				luPreparedStatement =  tuConnect.prepareStatement(lsSelect);
				luPreparedStatement.setInt(1, idUser);
				rs = luPreparedStatement.executeQuery();
				while(rs.next()){
					nomUsrOc=rs.getString(1);
				}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try{
				rs.close();
				luPreparedStatement.close();
				tuConnect.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return nomUsrOc;
	}
	
	public static void unlockUserCap(){
		Connection tuConnect 				= null;
		int idUser = (int) FacesUtils.getSession().getAttribute("idUserMod");
		java.sql.PreparedStatement luPreparedStatement = null;
		java.sql.PreparedStatement luPreparedStatement2 = null;
		String lsUpdate						  = "";
		String lsDelete						  = "";
		try{
			ConnectionWrapper puConnectionWrapper = new ConnectionWrapper();
			tuConnect = puConnectionWrapper.getConnection();
			
			lsUpdate = "UPDATE DERCORP_CONTROL_SECCION SET 	   STATUS = ?\n"+
	                   "                                       WHERE"+
	                   "									   ID_USER = ?";
			luPreparedStatement = tuConnect.prepareStatement(lsUpdate);
			luPreparedStatement.setInt(1, 0);
			luPreparedStatement.setInt(2, idUser);
			luPreparedStatement.executeUpdate();
			
			lsDelete = "DELETE DERCORP_CONTROL_META_ROW " +
						"	WHERE \n"+
						" 	ID_USER = ?";
			luPreparedStatement2 = tuConnect.prepareStatement(lsDelete);
			luPreparedStatement2.setInt(1, idUser);
			luPreparedStatement2.executeQuery();
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try{
				tuConnect.close();
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public static ArrayList<Object> setPestanaActual(String seccionId,boolean salida){
		Connection tuConnect 				= null;
		java.sql.PreparedStatement luPreparedStatement = null;
		java.sql.PreparedStatement luPreparedStatement2 = null;
		java.sql.PreparedStatement luPreparedStatement3 = null;
		ResultSet rs 						  = null;
		String lsUpdate						  = "";
		String lsSelect						  = "";
		String lsDelete						  = "";
		String msgProcess					  = "";
		int idUser=-1;
		int status=-1;
		boolean ocupada=true;
		ArrayList<Object> msgAndId =new ArrayList<Object>();
		try{
			ConnectionWrapper puConnectionWrapper = new ConnectionWrapper();
			tuConnect = puConnectionWrapper.getConnection();
			
			
				lsSelect = "	SELECT * \n"+
						   "		FROM DERCORP_CONTROL_SECCION\n"+
						   "		WHERE\n"+
						   "		ID_EMPRESA = ?\n"+
						   "		AND ID_SECCION = ?\n"+
						   "		AND STATUS = ?\n";
				luPreparedStatement2 =  tuConnect.prepareStatement(lsSelect);
				luPreparedStatement2.setInt(1, Integer.parseInt(FacesUtils.getSessionBean().getIdCurrentEmpresa()));
				luPreparedStatement2.setInt(2, Integer.parseInt(seccionId));
				luPreparedStatement2.setInt(3, 1);
				rs = luPreparedStatement2.executeQuery();
				
				if(!rs.next()){
					status=0;
				}
				else{
					idUser = rs.getInt(1);
					status = rs.getInt(4);
				}
				if(!salida){
					if(status==0){
						ocupada=false;	
					}
					else if(status==1 && idUser==Integer.parseInt(FacesUtils.getSessionBean().getIdUser())){
						msgProcess = "REPETIDO";
						msgAndId.add(msgProcess);
						msgAndId.add(idUser);
						return msgAndId;
					}
					else{
						ocupada=true;
						msgProcess = "OCUPADA";
						msgAndId.add(msgProcess);
						msgAndId.add(idUser);
						return msgAndId;
					}
					if(!ocupada){
						lsUpdate = "UPDATE DERCORP_CONTROL_SECCION SET 	   ID_EMPRESA = ?,\n"+
				                   "                                   	   ID_SECCION = ?,\n"+
				                   "                                   	   STATUS = ?\n"+
				                   "                                       WHERE"+
				                   "									   ID_USER = ?";
						luPreparedStatement = tuConnect.prepareStatement(lsUpdate);
						luPreparedStatement.setInt(1, Integer.parseInt(FacesUtils.getSessionBean().getIdCurrentEmpresa()));
						luPreparedStatement.setInt(2, Integer.parseInt(seccionId));
						luPreparedStatement.setInt(3, 1);
						luPreparedStatement.setInt(4, Integer.parseInt(FacesUtils.getSessionBean().getIdUser()));
						luPreparedStatement.executeUpdate();
						msgProcess="OK";
						msgAndId.add(msgProcess);
						msgAndId.add(idUser);
						return msgAndId;
					}
				}
				else{
				lsUpdate = "UPDATE DERCORP_CONTROL_SECCION SET 	   ID_EMPRESA = ?,\n"+
		                   "                                   	   ID_SECCION = ?,\n"+
		                   "                                   	   STATUS = ?\n"+
		                   "                                       WHERE"+
		                   "									   ID_USER = ?";
				luPreparedStatement = tuConnect.prepareStatement(lsUpdate);
				luPreparedStatement.setInt(1, Integer.parseInt(FacesUtils.getSessionBean().getIdCurrentEmpresa()));
				luPreparedStatement.setInt(2, Integer.parseInt(seccionId));
				luPreparedStatement.setInt(3, 0);
				luPreparedStatement.setInt(4, Integer.parseInt(FacesUtils.getSessionBean().getIdUser()));
				luPreparedStatement.executeUpdate();
				lsDelete = "DELETE DERCORP_CONTROL_META_ROW "
						  +"WHERE "
						  +"ID_USER = ?";
				luPreparedStatement3 = tuConnect.prepareStatement(lsDelete);
				luPreparedStatement3.setInt(1,Integer.parseInt(FacesUtils.getSessionBean().getIdUser()));
				luPreparedStatement3.executeUpdate();
				msgProcess="SALIDA";
				msgAndId.add(msgProcess);
				msgAndId.add(idUser);
				return msgAndId;
			}
			
			
			
			
			
		}catch (Exception ex){
			ex.printStackTrace();
		}finally{
			try{
				tuConnect.close();
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		return msgAndId;
	}
	
	public static ArrayList<Seccion> getSeccionesConInfo(ConnectionWrapper tuConnect, String tsIdRol, String seccionId, String empresaId) {
		
	    ArrayList<Seccion> laSecciones         = new ArrayList<Seccion>();
	    ResultSet          luResultSet         = null;
	    ResultSetMetaData  luMetaData          = null;
	    CallableStatement  luCallableStatement = null;

	    try {
		    luCallableStatement = tuConnect.prepareCall(DERCORP_CONSULTA_PKG.GET_SECCIONES_CON_INFO_PR);
		    luCallableStatement.setInt(1,Integer.valueOf(tsIdRol));
		    luCallableStatement.registerOutParameter(2, OracleTypes.CURSOR);
		    luCallableStatement.setString(3,seccionId);
		    luCallableStatement.setString(4,empresaId);
            luCallableStatement.execute();
		    luResultSet = ((OracleCallableStatement)luCallableStatement).getCursor(2);
			luMetaData = null;

			while (luResultSet.next()) {
				Seccion sec = new Seccion(luResultSet, luResultSet.getMetaData());
				log.info("sec: "+sec.desSeccion);
			    laSecciones.add(new Seccion(luResultSet, luResultSet.getMetaData()));
			    luMetaData = luResultSet.getMetaData();
			}

		} catch (Exception ex) {
			// TODO - Pendiente - Gestion de Excepciones
			ex.printStackTrace();

		} finally {
			//ConnectionWrapper.closeAll(luResultSet, luCallableStatement);
		}
		return laSecciones;
	}

	/**
	 * Devolver conexion.
	 * 
	 * @return
	 */
	ConnectionWrapper getConnection() {
		return connection;
	}

	/**
	 * Asignar conexiï¿½n.
	 * @param connection
	 */
	void setConnection(ConnectionWrapper connection) {
		this.connection = connection;
	}

	public String getModo() {
		return modo;
	}

	public void setModo(String modo) {
		this.modo = modo;
	}

	public String getPrintedTabName() {
		return printedTabName;
	}

	public void setPrintedTabName(String printedTabName) {
		this.printedTabName = printedTabName;
	}

	public String getHTMLLayoutV2(String seccionId,String idEmp ) {
		StringBuilder      luStringBuilder = new StringBuilder();
		ArrayList<Seccion> laSeccion       = null;
		String             liIdEmpresa     = FacesUtils.getSessionBean().getIdCurrentEmpresa();
		String       	   lsIdRol         = FacesUtils.getSessionBean().getIdRol();
		
		this.idEmpresa = liIdEmpresa;
		
		String psEditable = FacesUtils.getSessionBean().getEditCon();
		

		try {

			connection = new ConnectionWrapper();
			
			
			
			if(psEditable != null && psEditable.equals("disabled")){

				laSeccion  = getSeccionesConInfo(connection,lsIdRol, "*", liIdEmpresa);
	        } else {
	        		laSeccion = getSecciones(connection,"*", seccionId);
	        }
	        
	       
			
			
			EmpresaValues empVal = new EmpresaValues(connection, liIdEmpresa);
			ArrayList<Seccion> visibleSeccionList = empVal.getVisibleSeccionList(laSeccion);
			
			
			  if(psEditable != null && psEditable.equals("disabled")){
		        	if(!modo.equals("PRINT")) {
		        		//JJAQ 11/09/2017 para que no imprima todo si no esta cargado primero poderes
		        		String htmlPG = (String)FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get("htmlPG");
		        		String htmlPE = (String)FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get("htmlPE");
		        		String htmlCP = (String)FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get("htmlCP");
		        		if( (htmlPG == null  ) ||
		        			(htmlPE == null ) ||
		        			(htmlCP == null ) ){
		        			
		        			luStringBuilder.append("<div align='right'><input type='hidden' id='msgPoderes' name='msgPoderes' value='msg'></div><br>");
		        			
		        		}
		        		
		        		luStringBuilder.append("<div align='right'><a href='#' onclick=\"openPrintTab('*')\">Imprimir Todo</a></div><br>");
		        		
		        	}
		        }
			  
			if(psEditable != null && psEditable.equals("disabled")){  
				luStringBuilder.append("<div id=\"TabbedPanels1\" class=\"TabbedPanels\">");
				luStringBuilder.append("<ul class=\"TabbedPanelsTabGroup\">");
	
				
				int tabReference = 0;
				int numSecciones = 1;
				
				for (Seccion luSeccion : laSeccion) {
					
					String hideText = "";
					if(!visibleSeccionList.contains(luSeccion)) {
						
						hideText = " style='display:none'";
						luStringBuilder.append("<!-- Ocultando la Seccion de ECS -->");
					}
					
					
					String addJavascript = "";
					
					if(luSeccion.nomSeccion.contains("Reformas")) {
						
						addJavascript = "selectSubTabName(document.getElementById('reformasHiddenFirstTabName').value);";
					}
					
					
					if(psEditable != null && !psEditable.equals("")){ 
						
						if(luSeccion.nomSeccion.contains("Apoderados")) {
							
							addJavascript = "selectSubTabName(document.getElementById('apoderadosHiddenFirstTabName').value);";
							//System.out.println("addJavascript "+addJavascript);
						}
					}
					
					
					if(modo.equals("PRINT")) {
					
						printedTabName = luSeccion.nomSeccion;
					
					} else {
						
						
						String addSpryCode = ",{ defaultTab: " + tabReference + " }";
						
						/*luStringBuilder.append("    <li id='sec"+luSeccion.idSeccion+"' class=\"TabbedPanelsTab\" tabindex=\"0\"" + hideText + 
								" onclick=\"selectTabName('" + luSeccion.nomSeccion + "','" + addSpryCode + "');" + addJavascript + "\">" + 
									luSeccion.nomSeccion + 
									"</li>");
									
									*/
						//se agrega irPagina JAMS
						//if(numSecciones<4){
						luStringBuilder.append("    <li id='sec"+luSeccion.idSeccion+"' class=\"TabbedPanelsTab\" tabindex=\"0\"" + hideText + 
								" onclick=\"irPagina(" + luSeccion.idSeccion + ","+this.idEmpresa+",'"+luSeccion.nomSeccion+"');" + addJavascript + "\">" + 
									luSeccion.nomSeccion + 
									"</li>");
						/*}else{
							luStringBuilder.append("<li id='sec"+luSeccion.idSeccion+"' class='TabbedPanelsTab' tabindex='0'>" + 
										luSeccion.nomSeccion + 
										"</li>");
						}*/
						
					}
					
					tabReference++;
					numSecciones ++;
					
				}
				luStringBuilder.append("</ul>");
				luStringBuilder.append("<div class=\"TabbedPanelsContentGroup\">");
			}else{
				System.out.println("si debe de aperecer");
				
			}
			System.out.println(laSeccion);
			for (Seccion luSeccion : laSeccion) {
				if(luSeccion.idSeccion == Integer.parseInt(seccionId)){
				System.out.println(luSeccion.nomSeccion);
				String hideText = "";
				if(!visibleSeccionList.contains(luSeccion)) {
					
					hideText = " style='display:none'";
					luStringBuilder.append("<!-- Ocultando la Seccion de ECS -->");
				}
				
			    luStringBuilder.append("<div id='divContent' class=\"TabbedPanelsContent\"" + hideText + ">"
						+ luSeccion.toHTML(connection, empVal, this)
						+ "</div>");
			}
		}
			
			luStringBuilder.append("</div>");
			luStringBuilder.append("</div>");
			//System.out.println("luStringBuilder "+luStringBuilder);
			
			
		} catch (Exception ex) {
			// TODO - Pendiente - Gestion de Excepciones
            ex.printStackTrace();
			
		} finally {
		    ConnectionWrapper.closeAll(connection);
		}
		
		return luStringBuilder.toString();
	}
}

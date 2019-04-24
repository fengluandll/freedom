<%@page import="mx.com.televisa.derechocorporativo.util.FacesUtils"%>
<%@page import="java.util.ArrayList"%>
<%@page import="mx.com.televisa.derechocorporativo.modules.flextabs.FlexTable"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO8859_1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body onload="">

<%


	if(request.getParameter("ID_EMPRESA") != null) {
		
		FacesUtils.getSessionBean().setIdCurrentEmpresa(request.getParameter("ID_EMPRESA"));
	} 

	FacesUtils.getSessionBean().setEditCon("disabled");

	ArrayList<FlexTable> reformas = FlexTable.getReformas();
%>
<table width="100%">
	<tr>
		<td width="100%">
				
					<% for(FlexTable tab : reformas) { 
					
					
					

						tab.atributo8 = "0"; // No mostrar busqueda 
						tab.showTitle = false;
    					tab.showEditableCombosECS = false;
    					
    					String reportContent = tab.toHTML(request);
    					
    					if(tab.TAB_STYLE == null || !tab.TAB_STYLE.contains("display:none")) {
    					

    					
    					/* 
    					
    					String protocol = request.getScheme(); 			// http
    				    String serverName = request.getServerName(); 	// hostname.com
    				    
    				    int serverPort = request.getServerPort(); 		// 8081
    				    String contextPath = request.getContextPath(); 	// mywebapp

    				    //String servletPath = request.getServletPath();   // /servlet/MyServlet
    				    //String pathInfo = request.getPathInfo();         // /a/b;c=123
    				    //String queryString = request.getQueryString();          // d=789
    				    // Reconstruct original requesting URL

    				    String fullContextPath = protocol + "://" + serverName + ":" + serverPort + contextPath;
    				    
    				    String imgPath = fullContextPath + "/img/";
    				    
    				    
    				    
    				    
    				    reportContent = reportContent.replace("button_green.png", "<img src='" + imgPath + "button_green_cuadro.png'>");
    				    reportContent = reportContent.replace("button_red.png", "<img src='" + imgPath + "button_red_cuadro.png'>");
    				    reportContent = reportContent.replace("button_yellow.png", "<img src='" + imgPath + "button_yellow_cuadro.png'>");
    				    
    				    
    				    reportContent = reportContent.replace("../../img/", imgPath);
    				     */
    				    
    				    
    					///DerechoCorporativo/img/
					
					%>
					
						
							<br><br>
							
						<fieldset>
						<legend><%=tab.nomFlex %></legend>
			
							<%= reportContent %>
					
							<%--
							<div id='flexTable_<%=tab.idflexTbl %>'></div>
							<div id='flexTableInnerForm_<%=tab.idflexTbl %>'></div>
							<script type='text/javascript'>
								
								</script>
							--%>
						</fieldset>
					
					<% 
					
					
						}
    				} 
    				
    				%>
			
		</td>
	</tr>
</table>
<br>
<br><br>
<div id="reformasInfoDiv"></div>
</body>
</html>
<%@page import="mx.com.televisa.derechocorporativo.modules.reportsFlex.ParameterHandler"%>
<%@page import="mx.com.televisa.derechocorporativo.modules.reportsFlex.ReportFlex,
				mx.com.televisa.derechocorporativo.bean.SessionBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%@include file="/css/kaz-styles.jsp"%>


<%
	String repId = request.getParameter("repId");

	int intRepId = Integer.parseInt(repId);
	
	ReportFlex rep = ReportFlex.getReport(repId);

	SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
	int liIdRol =   Integer.valueOf(sessionBean.getIdRol());
	
	boolean reportOK = true;
	
	String message = "";
	
	String reportContent = "";
	
	try {
		

		reportContent = ReportFlex.getReportTemplate(intRepId, request, liIdRol);
		
		
		
		
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
	    
	    
	   /* reportContent = reportContent.replace("semaforo_green.png", "<img src='" + imgPath + "semaforo_green.png'>");
	    reportContent = reportContent.replace("semaforo_red.png", "<img src='" + imgPath + "semaforo_red.png'>");
	    reportContent = reportContent.replace("semaforo_yellow.png", "<img src='" + imgPath + "semaforo_yellow.png'>");
	    reportContent = reportContent.replace("semaforo_null.png", "<img src='" + imgPath + "semaforo_null.png'>");
		*/
		
		
	} catch(IllegalArgumentException iaex) {
		
		reportOK = false;
		
		message = iaex.getMessage();
		
	} catch(Exception ex) {
		
		reportOK = false;
		
		message = "Ocurrio un problema al generar el reporte" + ex.toString();
	}
	
	
	
%>


</head>
<body>




		<table width="100%" align="center">
			<tr>
				<td><jsp:include
						page="/jsp/components/pages_border/open-pre-title.jsp"></jsp:include>
				
						Reporte: <%= rep.getNomReporte() %>
					
				<jsp:include
						page="/jsp/components/pages_border/open-post-title.jsp"></jsp:include>



<table width="100%">
	<tr>
		<td valign="top" width="70%">
			<fieldset>
				<legend>Filtros Seleccionados:</legend>
			
			
				<% if(reportOK) { %>
					<%= ParameterHandler.getParamsHTMLTable(false) %>
				
					<br>
				<% } %>
				
				<span class="errorMessageText"><%= message %></span>
				<br>
				
				<div align="right">
					<a href="showReport.jsp?id=<%=repId%>">Consultar con Otros Filtros</a>
				</div>
			
			</fieldset>
		</td>
		<td valign="top">
		<% if(reportOK) { %>
			<fieldset>
				<legend>Exportar a:</legend>
			
					<div align="center">
					
					<table width="80%">
						<tr>
							<td>
					<a href='showReportAsPDF.jsp?repId=<%= repId %>'>
						<img src='../../img/icons/pdf_file.png' width="40" height="40">
					</a>
							</td>					
							<td>
					<a href='../../../FlexReportToExcel?repId=<%= repId %>'>
						<img src='../../img/icons/xls.png' width="40" height="40">
					</a>
						</td>
					</tr>
					</table>
					
					</div>				
			</fieldset>
		<% } %>
		
		
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<% if(reportOK) { %>
				<%= reportContent %>
			<% } %>
		<td>
	</td>
	
	
</table>





				<jsp:include
					page="/jsp/components/pages_border/close.jsp"></jsp:include></td>
		</tr>
	</table>






</body>
</html>
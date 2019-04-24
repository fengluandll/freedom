<%@page import="mx.com.televisa.derechocorporativo.modules.reportsFlex.ReportFlex,
				mx.com.televisa.derechocorporativo.bean.SessionBean"%>
<%@page import="mx.com.televisa.derechocorporativo.modules.reports.Report"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.min.js"></script> 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.css">
<%@include file="/jsp/components/session_timeout/session_timeout.jsp"%>
<%@include file="/css/kaz-styles.jsp"%>

<style type="text/css">
.infoTab {
	text-align: center;
	font-size: 12px;
}
.headTab {
	font-size: 14px;
}
.nvoCta {
	text-align: right;
	font-weight: bold;
	font-style: italic;
	color: #06F;
}
.centerNew {
	text-align: center;
}
a:hover {
	background-color: #ACBCED;
}
a:hover.encima{
	background-color: #7D7FD2;
}
</style>

</head>
<body>



		<table width="100%" align="center">
			<tr>
				<td><jsp:include
						page="/jsp/components/pages_border/open-pre-title.jsp"></jsp:include>
				
						Consultar Reportes Configurables
					
				<jsp:include
						page="/jsp/components/pages_border/open-post-title.jsp"></jsp:include>




<!-- style="background-color: #C1D5F0" -->
<table width="95%" border="0" cellspacing="3" cellpadding="3" align="center" class="flexTable" >
  <tr>
    <th class="tableHeader" width="20%" scope="col">Reporte</th>
    <th class="tableHeader" width="35%" scope="col">Descripci&oacute;n</th>
    <th class="tableHeader" width="5%" scope="col">Consultar</th>
  </tr>
  <% 
  SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
  int liIdRol =   Integer.valueOf(sessionBean.getIdRol());
  
  ArrayList<ReportFlex> reports = ReportFlex.getReports(liIdRol);
  
  
  if(reports.size() > 0){
	  
	  int c = 0;
	  
  	for(ReportFlex rep : reports){ 
  	
  		String cssClass = "";
  		c++;
  		if(c%2==0) {
  			cssClass = "tableRow2";
  		}
  		
  		//onmouseover="cambiar_color_over(this)" onmouseout="cambiar_color_out(this)"
  	
  		//infoTab
  		
  	%>
	  <tr class="<%= cssClass %>" >	
	   
	    <td><%= rep.getNomReporte() %></td>
	    <td><%= (rep.getDesReporte() != null)? rep.getDesReporte() : "" %></td>
	    <td align="center">
	    	<a href="showReport.jsp?id=<%= rep.getIdReportFlex() %>">
	    		<img alt="Consultar" src="../../img/icons/List.png"> 
	    	</a>
	    </td>
	  </tr>
   <%
       }
  }else{   %>
	<tr class="infoTab">	
    <td colspan="10"><span>No existen reportes por mostrar </span></td>
    </tr>
<%} %>
</table>




				<jsp:include
					page="/jsp/components/pages_border/close.jsp"></jsp:include></td>
		</tr>
	</table>




</body>
</html>
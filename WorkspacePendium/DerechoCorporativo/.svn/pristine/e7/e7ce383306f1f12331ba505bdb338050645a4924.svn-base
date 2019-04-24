<%@page import="mx.com.televisa.derechocorporativo.modules.reportsFlex.ReportFlex"%>
<%@page import="mx.com.televisa.derechocorporativo.modules.reports.Report,
				mx.com.televisa.derechocorporativo.bean.SessionBean"%>
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
				
						Reportes Configurables
					
				<jsp:include
						page="/jsp/components/pages_border/open-post-title.jsp"></jsp:include>



<table width="50%" border="0" align="center" cellpadding="0">
	<tr>
		<td align="right" class="nvoCta">
				<a href='#' onclick="document.getElementById('newReportTableForm').style.display=''">
				Nuevo <img src="../../img/icons/new.png" alt="Nuevo" />
				</a>
		<td>
	</tr>
	<tr>
		<td>
			<%
			  SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
			  int liIdRol =   Integer.valueOf(sessionBean.getIdRol());
			
				String submitButton = request.getParameter("submitButton");
			
				if(submitButton != null && submitButton.equals("Guardar")) {
					
					String name = request.getParameter("nombreNuevoReporte");
					String desc = request.getParameter("descNuevoReporte");
					String rfcNuevoReporte = request.getParameter("paisNuevoReporte");
					String paisNuevoReporte = request.getParameter("rfcNuevoReporte");
					
					
					
					ReportFlex.insertNewReport(name, desc, rfcNuevoReporte, paisNuevoReporte, liIdRol);
					
					
					out.println("Se dio de alta correctamente el nuevo reporte.");
				} else {
					
					String deleteId = request.getParameter("deleteId");
					
					if(deleteId != null && !deleteId.equals("")) {
						
						ReportFlex.deleteReport(deleteId);
						
						
						out.println("Se dio de baja correctamente el reporte.");
					}
				}
				
			
				
			%>
		
			<form action="" method="post">
			<table width="100%" id='newReportTableForm' style="display: none"> 
				<tr>
					<td>Nombre:</td>
					<td>
						<input name='nombreNuevoReporte' id='nombreNuevoReporte'>
					</td>
				</tr>
				<tr>
					<td>Descripci&oacute;n:</td>
					<td>
						<input name='descNuevoReporte' id='descNuevoReporte'>
					</td>
				</tr>
				<tr>
					<td>Mostrar RFCs en la <br>Estructura de Capital Social:</td>
					<td>
						<label>
						<input type="radio" name='rfcNuevoReporte' id='rfcNuevoReporte1' value="NO" checked="checked"> No
						</label>
						<label>
						<input type="radio" name='rfcNuevoReporte' id='rfcNuevoReporte2' value="SI"> Si
						</label>
					</td>
				</tr>
				<tr>
					<td>Mostrar Pais en la <br>Estructura de Capital Social:</td>
					<td>
						<label>
						<input type="radio" name='paisNuevoReporte' id='paisNuevoReporte1' value="NO" checked="checked"> No
						</label>
						<label>
						<input type="radio" name='paisNuevoReporte' id='paisNuevoReporte2' value="SI"> Si
						</label>
					</td>
				</tr>
				<tr>
					<td></td>
					<td align="right">
						<input type="submit" name='submitButton' value='Guardar'>
					</td>
				</tr>
			</table>
			</form>
			&nbsp;
		</td>
	</tr>
</table>




<!-- style="background-color: #C1D5F0" -->
<table width="95%" border="0" cellspacing="3" cellpadding="3" align="center" class="flexTable" >
  <tr>
    <th class="tableHeader" width="20%" scope="col">Reporte</th>
    <th class="tableHeader" width="35%" scope="col">Descripci&oacute;n</th>
    <th class="tableHeader" width="5%" scope="col">Configurar</th>
    <th class="tableHeader" width="5%" scope="col">Borrar</th>
  </tr>
  <% 
    
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
	    	<a href="configReport.jsp?id=<%= rep.getIdReportFlex() %>">
	    		<img alt="Configurar" src="../../img/icons/List.png"> 
	    	</a>
	    </td>
	    <td align="center">
	    	<!-- <a href="?deleteId=<%= rep.getIdReportFlex() %>" onclick="return confirm('Seguro que desea eliminar el reporte <%= rep.getNomReporte() %>?')"> 
	    		<img alt="Configurar" src="../../img/icons/delete.png"> 
	    	</a>   -->
	    	<a  href="#"  onclick="swal({ title: 'Confirmar', text: '&iquest;Est&aacute; seguro que desea borrar esta reporte?', type: 'warning', showCancelButton: true, confirmButtonColor: '#DD6B55', confirmButtonText: 'Si', closeOnConfirm: false }, function(){ window.location = '/DerechoCorporativo/faces/jsp/reportesFlex/reportCatalogConfig.jsp?deleteId=<%= rep.getIdReportFlex() %>'; return false}) "><abbr title="Eliminar"><img alt="Borrar" src="../../img/icons/delete.png"></abbr> </a>
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
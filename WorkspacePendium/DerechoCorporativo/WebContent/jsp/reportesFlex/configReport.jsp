<%@page import="mx.com.televisa.derechocorporativo.util.FacesUtils"%>
<%@page import="mx.com.televisa.derechocorporativo.modules.reportsFlex.ReportFlex"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.min.js"></script> 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.css">
<%@include file="/css/kaz-styles.jsp"%>

<%
	
	String strId = request.getParameter("id");

	int id = Integer.parseInt(strId);

	FacesUtils.getSessionBean().setIdFlexReport(id);
	
	ReportFlex rep = ReportFlex.getReport(strId);

%>

<script type="text/javascript" src="<%= request.getContextPath() %>/js/ajax/simpleAjaxUtil.js"></script>

<%@include file="configReportJs.jsp"%>

</head>
<body>




		<table width="100%" align="center">
			<tr>
				<td><jsp:include
						page="/jsp/components/pages_border/open-pre-title.jsp"></jsp:include>
				
						Configuraci&oacute;n de Reporte: <%= rep.getNomReporte() %>
					
				<jsp:include
						page="/jsp/components/pages_border/open-post-title.jsp"></jsp:include>
				<jsp:include page="/jsp/components/backPageReports/regresaMenuReportes.jsp">
					<jsp:param name="action" value="rPersonalizados"/>
				</jsp:include>



<table width="100%">
	<tr>
		<td></td><td></td>
	</tr>
	<tr>
		<td width="60%" valign="top">
		
			<div id="configContent">
				<%= ReportFlex.getReportGUI(id, request) %>
			</div>
		
		</td>
		<td  valign="top">
		
				<div style="height:400px;overflow-y:scroll">
					<div id="detalleComponente"></div>
				</div>
		</td>
	</tr>



</table>





				<jsp:include
					page="/jsp/components/pages_border/close.jsp"></jsp:include></td>
		</tr>
	</table>





</body>
</html>
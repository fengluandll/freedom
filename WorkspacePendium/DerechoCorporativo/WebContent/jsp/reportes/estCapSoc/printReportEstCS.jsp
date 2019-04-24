<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="mx.com.televisa.derechocorporativo.modules.reports.ecs.ReporteECSeccion,
            mx.com.televisa.derechocorporativo.modules.captura.Pagina,
            org.apache.commons.lang3.StringUtils,
            mx.com.televisa.derechocorporativo.modules.reports.ecs.ReporteECSFlexTable,
            mx.com.televisa.derechocorporativo.modules.captura.EmpresaValues"%>
            
          
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@include file="/css/kaz-styles.jsp"%>
<%@include file="/jsp/components/session_timeout/session_timeout.jsp"%>

<script type="text/javascript" src="<%= request.getContextPath() %>/js/ejercicio/ejercicio.js"></script>
<title>Reporte Estructura de Capital Social</title>
</head>
<body>

<jsp:include page="/jsp/components/backPageReports/regresaMenuReportes.jsp">
<jsp:param name="action" value="rEstCS"/>
</jsp:include>
		
<%String hiddenEmpresa    	= 	(request.getParameter("hiddenEmpresa") != null) ? request.getParameter("hiddenEmpresa") : "";%>
<%String hiddenAccionista 	= 	(request.getParameter("hiddenAccionista") != null) ? request.getParameter("hiddenAccionista") : "";%>
<%String txtEmpresa    		= 	(request.getParameter("txtEmpresa") != null) ? request.getParameter("txtEmpresa") : "";
  String radioAccionista    = 	(request.getParameter("onlyAccionista") != null) ? request.getParameter("onlyAccionista") : "";
	EmpresaValues empresaValues = new EmpresaValues(); %>
			
<%String imprimir = request.getParameter("psHiFuOut");%> 
	
	

<span style="text-align: center"><h1>Reporte: Estructura de Capital Social</h1></span>
<%String outHtml 	 = ""; 
  String outTableECS = "";%>		





<%if(!hiddenEmpresa.equals("")){ %>
<strong><span style="color:gray;">Empresa(s):</span></strong><br>
<span style="color:gray;"><%= EmpresaValues.getNomEmpresas(hiddenEmpresa) %></span><br>
<%} %>

<%if(!hiddenAccionista.equals("")){ %>
<strong><span style="color:gray;">Accionista(s):</span></strong><br>
<span style="color:gray;"><%= EmpresaValues.getNomAccionistas(hiddenAccionista) %></span><br>
<%} %>


<%ReporteECSeccion 		reporteECSeccion 	= new ReporteECSeccion();
	
	String     empresaAccionistas = "";
	if(!hiddenAccionista.equals("")){
		empresaAccionistas = empresaValues.getEmpresaByAccionista(hiddenAccionista);
	}
	if(!hiddenAccionista.equals("") && !hiddenEmpresa.equals("")){
		hiddenEmpresa = empresaAccionistas + "," + hiddenEmpresa;
		
	}else if(!hiddenAccionista.equals("") && hiddenEmpresa.equals("")){
		hiddenEmpresa = empresaAccionistas;
	}
	hiddenEmpresa = empresaValues.ordenaEmpresas(hiddenEmpresa);%>
	
<table width="30%">
	<tr>
		<td>
			<a href="printEcsPDF.jsp?hiddenEmpresa=<%=hiddenEmpresa%>&hiddenAccionista=<%=hiddenAccionista%>&txtEmpresa=<%=txtEmpresa%>&radioAccionista=<%=radioAccionista%>" target="_blank"><img src="../../../img/icons/pdf_file_50.png"></a>		
		</td>
		<td>
			<a href="/DerechoCorporativo/FlexReportECSToExcel?hiddenEmpresa=<%=hiddenEmpresa%>&hiddenAccionista=<%=hiddenAccionista%>&txtEmpresa=<%=txtEmpresa%>&radioAccionista=<%=radioAccionista%>" target="_blank"><img src="../../../img/icons/xls_50.png"></a>
		</td>
	</tr>
</table>
<%	
  ReporteECSFlexTable 	reporteECSFlexTable;
  
if(!hiddenEmpresa.equals("")){  
String[] empresas = StringUtils.split(hiddenEmpresa.trim(),",");
if(empresas.length > 0){ %>
	<table  width='100%' border='0' id='tbl_principal'>
	
<%	for(String empresa : empresas){ %>
	
	<tr>
	<td align="center">
	<%	outHtml = reporteECSeccion.toHTML(empresa, new Pagina());%>
	<%= outHtml %>
	</td>
	</tr>
	<tr>
		<td>
			<% reporteECSFlexTable = new ReporteECSFlexTable(7,empresa);
				outTableECS = reporteECSFlexTable.toHTMLHorizontal(request,empresa,radioAccionista.equals("SI")&&hiddenAccionista.length()>0?hiddenAccionista:"",""); %>
			<%= outTableECS %>
		</td>
		
	</tr>
<%	}	
}else{ %>
	<tr>
	<td align="center">
	<%outHtml = reporteECSeccion.toHTML(hiddenEmpresa, new Pagina());%>
	<%= outHtml %>
	</td>
	</tr>
	<tr>
		<td>
			<% 	reporteECSFlexTable = new ReporteECSFlexTable(7,hiddenEmpresa);
				outTableECS = reporteECSFlexTable.toHTMLHorizontal(request,hiddenEmpresa,radioAccionista.equals("SI")&&hiddenAccionista.length()>0?hiddenAccionista:"",""); %>
			<%= outTableECS %>
		</td>
	</tr>
<%}%>

	</table>
<%}else{ %>
		<strong><h3>EL reporte no arrojo ning�n resultado</h3></strong>
<%} %>
</body>
</html>
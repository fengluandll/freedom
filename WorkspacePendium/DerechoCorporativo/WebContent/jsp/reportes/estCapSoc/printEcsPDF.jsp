<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="mx.com.televisa.derechocorporativo.modules.reports.ecs.ReporteECSeccion,
            mx.com.televisa.derechocorporativo.modules.captura.Pagina,
            org.apache.commons.lang3.StringUtils,
            mx.com.televisa.derechocorporativo.modules.reports.ecs.ReporteECSFlexTable,
            mx.com.televisa.derechocorporativo.modules.captura.EmpresaValues,
            java.util.Calendar,
            java.util.Date"%>
            
<%@ taglib uri="pd4ml" prefix="pd4ml"%>            
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<pd4ml:transform
	 screenWidth="1000" pageFormat="LETTER" fileName="Reporte Esctructura de Capital Social"
	 adjustScreenWidth="true"
	 pageInsets="5,5,10,5,mm"
	enableTableBreaks="true" enableImageSplit="false" debug="true">
  <pd4ml:footer areaHeight="25" pageNumberTemplate="$[page] de $[total]" initialPageNumber="1"  pageNumberAlignment="right">
  <%    
       Calendar fecha = Calendar.getInstance();
       
       String[] meses = new String[]{
         "Enero",
         "Febrero",
         "Marzo",
         "Abril",
         "Mayo",
         "Junio",
         "Julio",
         "Agosto",
         "Septiembre",
         "Octubre",
         "Noviembre",
         "Diciembre"};
       int anio = fecha.get(Calendar.YEAR);
       int mes = fecha.get(Calendar.MONTH);
       int dia = fecha.get(Calendar.DATE);
      %>
       <table style="width: 100%;">
       	<tr>
       		<td align="right" width="10%" style="text-align: right;"><font style="font-size: 12px;"><i>P�gina $[page] de $[total]</i></font>  </td>
       	</tr>  
  	  </table>
 </pd4ml:footer>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@include file="/css/kaz-styles4Print.jsp"%>
<%@include file="/jsp/components/session_timeout/session_timeout.jsp"%>

<script type="text/javascript" src="<%= request.getContextPath() %>/js/ejercicio/ejercicio.js"></script>
<title>Reporte Estructura de Capital Social</title>
</head>
<body>


<%		
Calendar fecha = Calendar.getInstance();
       
       String[] meses = new String[]{
         "Enero",
         "Febrero",
         "Marzo",
         "Abril",
         "Mayo",
         "Junio",
         "Julio",
         "Agosto",
         "Septiembre",
         "Octubre",
         "Noviembre",
         "Diciembre"};
       int anio = fecha.get(Calendar.YEAR);
       int mes = fecha.get(Calendar.MONTH);
       int dia = fecha.get(Calendar.DATE);
      %>

<table width="100%">
<tr>
<td><span style="text-align: center"><h1>Reporte: Estructura de Capital Social</h1></span><strong></td>
<td align="right"><span style="color:gray;">Fecha de emisi�n: <%=dia+"/"+meses[mes]+"/"+anio %></span></strong></td>
</tr>
</table>

<%String outHtml 	 = ""; 
  String outTableECS = "";%>		

<%String hiddenEmpresa    	= 	(request.getParameter("hiddenEmpresa") != null) ? request.getParameter("hiddenEmpresa") : "";%>
<%String hiddenAccionista 	= 	(request.getParameter("hiddenAccionista") != null) ? request.getParameter("hiddenAccionista") : "";%>
<%String txtEmpresa    		= 	(request.getParameter("txtEmpresa") != null) ? request.getParameter("txtEmpresa") : "";
  String radioAccionista    = 	(request.getParameter("radioAccionista") != null) ? request.getParameter("radioAccionista") : "";
	EmpresaValues empresaValues = new EmpresaValues();%>

       
<%if(!hiddenEmpresa.equals("") && hiddenAccionista.equals("")){ %>
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
	hiddenEmpresa = empresaValues.ordenaEmpresas(hiddenEmpresa);
  ReporteECSFlexTable 	reporteECSFlexTable;

if(!hiddenEmpresa.equals("")){    
String[] empresas = StringUtils.split(hiddenEmpresa.trim(),",");
if(empresas.length > -1){ %>
	<table  width='100%' border='0' id='tbl_principal'>
	
<%	for(String empresa : empresas){ %>
	<tr>
		<td>
		</td>
	</tr>
	<tr>
	<td align="center">
	<%	outHtml = reporteECSeccion.toHTML(empresa, new Pagina());	%>
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
</pd4ml:transform>
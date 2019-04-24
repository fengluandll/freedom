<%@page import="javax.persistence.criteria.CriteriaBuilder.Trimspec"%>
<%@page import="mx.com.televisa.derechocorporativo.util.StringUtils"%>
<%@page import="mx.com.televisa.derechocorporativo.data.Record"%>
<%@page import="java.util.ArrayList"%>
<%@page import="mx.com.televisa.derechocorporativo.model.Catalog"%>
<%@page import="mx.com.televisa.derechocorporativo.model.CatalogElement"%>
<%@page import="java.util.List"%>
<%@page import="mx.com.televisa.derechocorporativo.modules.reports.tenecascada.TenenciaCascada"%>
<%@page import="mx.com.televisa.derechocorporativo.components.JSCal"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.GregorianCalendar"%>

<%@ taglib uri="pd4ml" prefix="pd4ml"%>

<%@page contentType="text/html; charset=ISO8859_1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	
	String fileName = "Reporte_Tenencia_en_Cascada.pdf";
	
	
	//String protocol = request.getScheme(); 			// http
	//String serverName = request.getServerName(); 	// hostname.com
	
	//int serverPort = request.getServerPort(); 		// 8081
	//String contextPath = request.getContextPath(); 	// mywebapp
	
	//String servletPath = request.getServletPath();   // /servlet/MyServlet
	//String pathInfo = request.getPathInfo();         // /a/b;c=123
	//String queryString = request.getQueryString();          // d=789
	// Reconstruct original requesting URL
	
	//String fullContextPath = protocol + "://" + serverName + ":" + serverPort + contextPath;
	

%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><pd4ml:transform
	screenWidth="780" pageFormat="LETTER" fileName="<%= fileName %>"
	pageInsets="3,3,3,3,mm" adjustScreenWidth="true"
	enableTableBreaks="false" enableImageSplit="false" debug="true"
	pageOrientation="landscape">
	<pd4ml:footer areaHeight="30" pageNumberTemplate="$[page] de $[total]" initialPageNumber="1"  pageNumberAlignment="right">
		<%
						SimpleDateFormat dateFormat = new SimpleDateFormat("MMMMM/yyyy");
						 Calendar calendar = GregorianCalendar.getInstance();
						%>
						<table style="width: 100%;">
							<tr>
								<td valign="middle" width="90%" style="text-align: right;"><font style="font-size: 8px;">Fecha de emisión: <%=dateFormat.format(calendar.getTime()) %></font></td>
								<td valign="middle" width="10%" style="text-align: right;"><font style="font-size: 8px;"><i>Página $[page] de $[total]</i></font>  </td>
							</tr>
		
		</table>
	</pd4ml:footer>
	
	
	
	
	
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>












<link
	href="pd4ml_report.css"
	rel="stylesheet" type="text/css">

<style>
html {
	font-family: Verdana, Arial, Helvetica;
	color: #000000;
	font-size: 8pt;
}

td {
	FONT: 8px Verdana, sans-serif;
	COLOR: #000000;
}
</style>
<!-- print style sheet -->
<style type="text/css" media="print">
div.tableContainer {
	overflow: visible;
	page-break-inside: avoid;
}

div.container {
	writing-mode: lr-tb;
	page-break-inside: avoid;
}

table>tbody {
	overflow: visible;
}

td {
	height: 10.5px Verdana;
} /*adds control for test purposes*/
thead td {
	FONT: 10.5px Verdana, sans-serif;
	COLOR: #000000;
}

tfoot td {
	text-align: center;
	font-size: 9pt;
	border-bottom: solid 1px slategray;
}

thead {
	display: table-header-group;
}

tfoot {
	display: table-footer-group;
}

thead th,thead td {
	position: static;
}

thead tr {
	position: static;
	page-break-inside: avoid;
} /*prevent problem if print after scrolling table*/
table tfoot tr {
	position: static;
}

.noprint {
	display: none
}


</style>






<link rel="stylesheet" href="<%=request.getContextPath()%>/js/webix/webixCustom.css" type="text/css" charset="utf-8">
<script src="<%=request.getContextPath()%>/js/webix/webix.js" type="text/javascript" charset="utf-8"></script>

<%@include file="/css/kaz-styles4PrintSmall.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery-2.1.4.min.js"></script>
<%@include file="/jsp/components/calendar/include_calendar.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajax/simpleAjaxUtil.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/d3Grapho/d3.min.js"></script>



<script type="text/javascript" src="<%=request.getContextPath()%>/js/basicPrimitivesOrgChart/js/jquery/jquery-ui-1.10.2.custom.min.js"></script>
<link href="<%=request.getContextPath()%>/js/basicPrimitivesOrgChart/js/jquery/ui-lightness/jquery-ui-1.10.2.custom.css" media="screen" rel="stylesheet" type="text/css" />

<!-- jQuery UI Layout -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/basicPrimitivesOrgChart/jquerylayout/jquery.layout-latest.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/basicPrimitivesOrgChart/jquerylayout/layout-default-latest.css" />


<link href="<%=request.getContextPath()%>/js/basicPrimitivesOrgChart/css/primitives.latest.css?2100" media="screen" rel="stylesheet" type="text/css" />
<script  type="text/javascript" src="<%=request.getContextPath()%>/js/basicPrimitivesOrgChart/js/primitives.min.js?2100"></script>



<%

	request.setCharacterEncoding("UTF-8");

	String empresaId = request.getParameter("hiddenEmpresa");

	String paramConsolida = 	(request.getParameter("hiddenConsolida") != null) ? request.getParameter("hiddenConsolida") : "";
	String paramSegmento = 		(request.getParameter("hiddenSegmento") != null) ? request.getParameter("hiddenSegmento") : "";
	String paramClasificacion = (request.getParameter("hiddenClasificacion") != null) ? request.getParameter("hiddenClasificacion") : "";
	String paramPais = 			(request.getParameter("hiddenPais") != null) ? request.getParameter("hiddenPais") : "";
	String paramNoEmpOracle = 	(request.getParameter("hiddenNoEmpOracle") != null) ? request.getParameter("hiddenNoEmpOracle") : "";
	String paramGiro = 			(request.getParameter("hiddenGiro") != null) ? request.getParameter("hiddenGiro") : "";
	String paramPorcentaje = 			(request.getParameter("txtPorcentaje") != null) ? request.getParameter("txtPorcentaje") : "";
	String selectPorcentaje = 			(request.getParameter("selectPorcentaje") != null) ? request.getParameter("selectPorcentaje") : "";
	String selectPorcentajeCual = 			(request.getParameter("selectPorcentajeCual") != null) ? request.getParameter("selectPorcentajeCual") : "";
	String selectPorcentajeVisualizar = 			(request.getParameter("selectPorcentajeVisualizar") != null) ? request.getParameter("selectPorcentajeVisualizar") : "";
	String paramDivision = 	(request.getParameter("hiddenDivision") != null) ? request.getParameter("hiddenDivision") : "";
	
	String paramString = "";
	paramString += (request.getParameter("hiddenConsolida") != null) ? 		"&hiddenConsolida=" + request.getParameter("hiddenConsolida") : "";
	paramString += (request.getParameter("hiddenSegmento") != null) ? 		"&hiddenSegmento=" + request.getParameter("hiddenSegmento") : "";
	paramString += (request.getParameter("hiddenClasificacion") != null) ? 	"&hiddenClasificacion=" + request.getParameter("hiddenClasificacion") : "";
	paramString += (request.getParameter("hiddenPais") != null) ? 			"&hiddenPais=" + request.getParameter("hiddenPais") : "";
	paramString += (request.getParameter("hiddenNoEmpOracle") != null) ? 	"&hiddenNoEmpOracle=" + request.getParameter("hiddenNoEmpOracle") : "";
	paramString += (request.getParameter("hiddenGiro") != null) ? 			"&hiddenGiro=" + request.getParameter("hiddenGiro") : "";
	paramString += (request.getParameter("hiddenDivision") != null) ? 			"&hiddenDivision=" + request.getParameter("hiddenDivision") : "";
	
	paramString += (request.getParameter("txtConsolida") != null) ? 	"&txtConsolida=" + request.getParameter("txtConsolida") : "";
	paramString += (request.getParameter("txtSegmento") != null) ? 		"&txtSegmento=" + request.getParameter("txtSegmento") : "";
	paramString += (request.getParameter("txtClasificacion") != null) ? "&txtClasificacion=" + request.getParameter("txtClasificacion") : "";
	paramString += (request.getParameter("txtPais") != null) ? 			"&txtPais=" + request.getParameter("txtPais") : "";
	paramString += (request.getParameter("txtNoEmpOracle") != null) ? 	"&txtNoEmpOracle=" + request.getParameter("txtNoEmpOracle") : "";
	paramString += (request.getParameter("txtGiro") != null) ? 			"&txtGiro=" + request.getParameter("txtGiro") : "";
	paramString += (request.getParameter("txtDivision") != null) ? 			"&txtDivision=" + request.getParameter("txtDivision") : "";
	paramString += (request.getParameter("txtPorcentaje") != null) ? 	"&txtPorcentaje=" + request.getParameter("txtPorcentaje") : "";
	paramString += (request.getParameter("selectPorcentaje") != null) ? 	"&selectPorcentaje=" + request.getParameter("selectPorcentaje") : "";
	paramString += (request.getParameter("selectPorcentajeCual") != null) ? 	"&selectPorcentajeCual=" + request.getParameter("selectPorcentajeCual") : "";
	paramString += (request.getParameter("selectPorcentajeVisualizar") != null) ? 	"&selectPorcentajeVisualizar=" + request.getParameter("selectPorcentajeVisualizar") : "";
	
	String viewMode = request.getParameter("viewMode");

	if(empresaId != null && !empresaId.equals("") && !empresaId.equals("null") && viewMode != null && viewMode.equals("organigrama")) {
%>


<script type="text/javascript" src="orgChartEmpresasData.jsp?empresaId=<%=empresaId%>&paramConsolida=<%=paramConsolida%>&paramSegmento=<%=paramSegmento%>&paramClasificacion=<%=paramClasificacion%>&paramPais=<%=paramPais%>&paramNoEmpOracle=<%=paramNoEmpOracle%>&paramGiro=<%=paramGiro%>&paramPorcentaje=<%=paramPorcentaje%>&selectPorcentaje=<%=selectPorcentaje%>&selectPorcentajeCual=<%=selectPorcentajeCual%>"></script>

<%
	}
%>



<script type="text/javascript">

function waitBar() {
	
	document.getElementById('imgCapWait').style.display = '';
	document.getElementById('btnEjecutar').style.display = 'none';
}


	
	function openSelectPupUp(catalogId, targetIds, targetNames, namesProperty, currentValue) {
	
	    var left = screen.width - ((screen.width - 300) / 2);
	    var top = (screen.height - 300) / 2;  // for 25% - devide by 4  |  for 33% - devide by 3
	
		newwindow=window.open('../../components/simpleSelectPupUp/simpleselect.jsp?catalogId=' + catalogId + '&targetIds=' + targetIds + '&targetNames=' + targetNames + '&currentValue=' + currentValue + '&namesProperty=' + namesProperty,
							'name',
							'height=600,width=450,toolbar=0,menubar=0,location=0,status=0,scrollbars=1,resizable=1,left=' + left + ',top=' + top);
	
		if (window.focus) {newwindow.focus()}
	
		return false;
	}

	/*
	function loadReport() {
		
	    var ajaxRequest = simpleAjaxUtil_getXmlHttpObject();     		
	    if (ajaxRequest==null) {alert ("Browser does not support HTTP Request");return;}
		
	    var url = 'treeTableData.jsp';
	    var parameters = "";

	    ajaxRequest.open("post", url, true);
	    ajaxRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	    ajaxRequest.onreadystatechange=function(){
	    
		    if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
		
			    var response = ajaxRequest.responseText;
			    document.getElementById('treeTableDiv').innerHtml = '';
			    jQuery(response).appendTo('body');
		      } 
	    };    
	    ajaxRequest.send(parameters);
	}*/

	
</script>


</head>
<body onload="">

			<jsp:include page="/jsp/components/pages_border4PrintFullContext/open-pre-title.jsp"></jsp:include>
			
				Reporte de Tenencia en Cascada
			
			<jsp:include page="/jsp/components/pages_border4PrintFullContext/open-post-title.jsp"></jsp:include>

<form action="tenenciaCascada.jsp" method="post">




<table width="100%" >
	<tr>
		<td colspan="3"><hr></td>
	</tr>
	<tr>
		<td width="10%"></td>
		<td width="15%"></td>
		<td width="75%">
		
			<% 
		
				
			
			
				String nomEmpresa = "";
			
				if(empresaId != null && !empresaId.equals("")) {
					try {
						List<CatalogElement> catalogElements = new Catalog("DERCORP_BUSQUEDA_VIEW").getList("DISTINCT ID_EMPRESA, DENOM_ACTUAL", "WHERE ID_EMPRESA = " + empresaId);
						CatalogElement elem = catalogElements.get(0);
						nomEmpresa = elem.getName();
					}catch(Exception ex) {
						
						out.println("<!-- " + ex.toString() + " -->");
					}
				}
				
				//String fecha = request.getParameter("txtDate");
				String fecha = "";
				
			
						
				ArrayList<Record> treeTableRecords = null;
				
				String treeGraphData = null;
				String treeOrgChartData = null;
				
				out.println("<!-- " + empresaId + " -->");
				
				if(empresaId != null && !empresaId.equals("") && !empresaId.equals("null") && (viewMode == null || viewMode.equals("tabla"))) {
					/*
					treeTableData = TenenciaCascada.getTreeData(empresaId, fecha,
																		paramConsolida, 
																		paramSegmento, 
																		paramClasificacion, 
																		paramPais, 
																		paramNoEmpOracle, 
																		paramGiro,
																		paramPorcentaje
																		);*/
										/*TenenciaCascada.getTreeData1(empresaId, fecha,
												paramConsolida, 
												paramSegmento, 
												paramClasificacion, 
												paramPais, 
												paramNoEmpOracle, 
												paramGiro,
												paramPorcentaje,
												selectPorcentaje,
												selectPorcentajeCual,
												selectPorcentajeVisualizar
												);*/
					
					treeTableRecords =  TenenciaCascada.getTreeDataRows(empresaId, fecha,
																paramConsolida, 
																paramSegmento, 
																paramClasificacion, 
																paramPais, 
																paramNoEmpOracle, 
																paramGiro,
																paramPorcentaje,
																selectPorcentaje,
																selectPorcentajeCual
																);
				}
				
				
				if(empresaId != null && !empresaId.equals("") && !empresaId.equals("null") && viewMode != null && viewMode.equals("grafica")) {
					
					
					treeGraphData = "OK";
				}
				

				if(empresaId != null && !empresaId.equals("") && !empresaId.equals("null") && viewMode != null && viewMode.equals("organigrama")) {
					
					
					treeOrgChartData = "OK";
				}


				//String treeData = "";
				
				
			%>
			<table width="100%">
				<tr>
					<td>
						
					</td>
					<td>
								
					</td>
				</tr>
				
			</table>
		</td>
	</tr>
	<%-- 
	<tr>
		<td></td>
		<td>al dÃ­a:</td>
		<td>
				<input type='text' name='txtDate' id='txtDate'>
				<%=
				JSCal.getCalendar("txtDate", "")
				%>
		</td>
	</tr>
	--%>
	
	
	<tr>
		<td colspan="3" align="left">
			<table width="50%" >
				<tr>
					<td width="10%"></td>
					<td width="20%" style="font-size: 8px; vertical-align: middle;">Empresa:</td>
					<td colspan="4" style="font-size: 8px; vertical-align: middle;">
						<input type="hidden" id='hiddenEmpresa' name='hiddenEmpresa' value='<%=empresaId%>'>
						<%=nomEmpresa %>
						<%--<input id='txtEmpresa' name='txtEmpresa'> --%>
					</td>
					
				</tr>
				<% if((request.getParameter("txtSegmento")!=null && !request.getParameter("txtSegmento").trim().isEmpty()) || (request.getParameter("txtSegmento")!=null && !request.getParameter("txtSegmento").trim().isEmpty())) {%>
					<tr>
						<td width="10%"></td>
						<% if(request.getParameter("txtConsolida")!=null && !request.getParameter("txtConsolida").trim().isEmpty()) {%>
						<td  width="20%" style="font-size: 8px; vertical-align: middle;">Consolida:</td>
						<td style="font-size: 8px; vertical-align: middle;">
							<% if(request.getParameter("txtConsolida").equals("null")){out.println("vacio");}else{%>
								<%= StringUtils.latin1ToUTF8(request.getParameter("txtConsolida"))%>
							<%} %>
						</td>
						<td width="10%"></td>
						<%} %>
						<% if(request.getParameter("txtSegmento")!=null && !request.getParameter("txtSegmento").trim().isEmpty()) {%>
						<td  width="20%" style="font-size: 8px; vertical-align: middle;">Segmento:</td>
						<td style="font-size: 8px; vertical-align: middle;">	
							<% if(request.getParameter("txtSegmento").equals("null")){out.println("vacio");}else{%>
								<%= StringUtils.latin1ToUTF8(request.getParameter("txtSegmento"))%>
							<%} %>
						</td>
						<%} %>
					</tr>
				<%} %>
				<% if((request.getParameter("txtClasificacion")!=null && !request.getParameter("txtClasificacion").trim().isEmpty()) || (request.getParameter("txtPais")!=null && !request.getParameter("txtPais").trim().isEmpty())) {%>
				<tr>
				<% if(request.getParameter("txtClasificacion")!=null && !request.getParameter("txtClasificacion").trim().isEmpty()) {%>
					<td></td>
					<td style="font-size: 8px; vertical-align: middle;">Clasificaci&oacute;n:</td>
					<td style="font-size: 8px; vertical-align: middle;">
						<% if(request.getParameter("txtClasificacion").equals("null")){out.println("vacio");}else{%>
							<%= StringUtils.latin1ToUTF8(request.getParameter("txtClasificacion"))%>
						<%} %>
					</td>
					<%} %>
					<% if(request.getParameter("txtPais")!=null && !request.getParameter("txtPais").trim().isEmpty()) {%>
					<td></td>
					<td style="font-size: 8px; vertical-align: middle;">Pa&iacute;s:</td>
					<td style="font-size: 8px; vertical-align: middle;">
						<% if(request.getParameter("txtPais").equals("null")){out.println("vacio");}else{%>
							<%= StringUtils.latin1ToUTF8(request.getParameter("txtPais"))%>
						<%} %>
					</td>
					<%} %>
				</tr>
				<%} %>
				<% if((request.getParameter("txtSegmento")!=null && !request.getParameter("txtSegmento").trim().isEmpty()) || (request.getParameter("txtSegmento")!=null && !request.getParameter("txtSegmento").trim().isEmpty())) {%>
				<tr>
				<% if(request.getParameter("txtNoEmpOracle")!=null && !request.getParameter("txtNoEmpOracle").trim().isEmpty()) {%>
					<td></td>
					<td style="font-size: 8px; vertical-align: middle;">No Empresa:</td>
					<td style="font-size: 8px; vertical-align: middle;">
						<% if(request.getParameter("txtNoEmpOracle").equals("null")){out.println("vacio");}else{%>
							<%= StringUtils.latin1ToUTF8(request.getParameter("txtNoEmpOracle"))%>
						<%} %>
					</td>
					<%} %>
					<% if(request.getParameter("txtGiro")!=null && !request.getParameter("txtGiro").trim().isEmpty()) {%>
					<td></td>
					<td style="font-size: 8px; vertical-align: middle;">Giro:</td>
					<td style="font-size: 8px; vertical-align: middle;">
						<% if(request.getParameter("txtGiro").equals("null")){out.println("vacio");}else{%>
							<%= StringUtils.latin1ToUTF8(request.getParameter("txtGiro"))%>
						<%} %>
					</td>
					<%} %>
				</tr>
				<%} %>
				<% if(request.getParameter("selectPorcentajeCual")!=null && !request.getParameter("selectPorcentajeCual").trim().isEmpty() ) {%>
				<tr>
					
					<td></td>
					<td style="font-size: 8px; vertical-align: middle;" >Porcentaje:</td>
					<td style="font-size: 8px; vertical-align: middle;">	
					<% if(request.getParameter("txtGiro").equals("null")){out.println("vacio");}else{%>
						<%= StringUtils.latin1ToUTF8(request.getParameter("selectPorcentajeCual"))%>
								
						<%
					}
						
							//String selectPorcentaje = (request.getParameter("selectPorcentaje")!=null)? request.getParameter(selectPorcentaje) : "";
							if(selectPorcentaje.equals("1")) {
								
								out.println("igual");
								
							} else if(selectPorcentaje.equals("2")) { 
								
								out.println("mayor que");
								
							} else if(selectPorcentaje.equals("3")) { 
								
								out.println("menor que");
							}
							
						
						%>
						<%= (request.getParameter("txtPorcentaje")!=null && !request.getParameter("txtPorcentaje").trim().isEmpty())? request.getParameter("txtPorcentaje") : ""%> 
					</td>
					<% if(request.getParameter("txtDivision")!=null && !request.getParameter("txtDivision").trim().isEmpty()) {%>
					<td></td>
					<td style="font-size: 8px; vertical-align: middle;">División:</td>
					<td style="font-size: 8px; vertical-align: middle;">
						<% if(request.getParameter("txtDivision").equals("null")){out.println("vacio");}else{%>
							<%= StringUtils.latin1ToUTF8(request.getParameter("txtDivision"))%>
						<%} %>
					</td>
					<%} %>
				</tr>
				<%} %>
				<% if(selectPorcentajeVisualizar != null && !selectPorcentajeVisualizar.isEmpty()) {%>
				<tr>
					<td></td>
					<td style="font-size: 8px; vertical-align: middle;">Visualizar:</td>
					<td style="font-size: 8px; vertical-align: middle;">
						<% if(selectPorcentajeVisualizar.equals("Directo") ) { out.println("Directo");} %>
						<% if(selectPorcentajeVisualizar.equals("Indirecto")) { out.println("Indirecto");} %>
						<% if(selectPorcentajeVisualizar.equals("Ambos")) { out.println("Directo e Indirecto");} %>
						 
					</td>
					<td></td>
					<td></td>
					<td>
					</td>
				</tr>
				
				<%} %>
			</table>
		</td>
	</tr>

	<tr>
		<td colspan="3"><hr></td>
	</tr>
	<tr>
		<td colspan="3" align="center">
	
				<div id="infoDiv"></div>
	
				<div id="treeTableDiv" width="100%"></div>
				
				
			<% 
				if(treeTableRecords != null) {
			%>
				
				<table align="center" width="100%">
					<tr>
						<%--  <th style="background-image:url('<%=fullContextPath%>/img/tableHeaderBack.png');color: white;">  --%>
						<th style="background-image:url('tableHeaderBack.png');color: white; text-align: center;"># Empresa</th>
						<th style="background-image:url('tableHeaderBack.png');color: white; text-align: center;">
							<%=nomEmpresa%>
						</th>
						<% if(selectPorcentajeVisualizar != null && (selectPorcentajeVisualizar.equals("Directo") || selectPorcentajeVisualizar.equals("Ambos") ) ) { %>
						<th style="background-image:url('tableHeaderBack.png');color: white; width: 55px;  text-align: center;">% Directo</th>
						<% } %>
						<% if(selectPorcentajeVisualizar != null && (selectPorcentajeVisualizar.equals("Indirecto") || selectPorcentajeVisualizar.equals("Ambos") ) ) { %>
						<th style="background-image:url('tableHeaderBack.png');color: white;  width: 55px; text-align: center;">% Indirecto</th>
						<% } %>
						<th style="background-image:url('tableHeaderBack.png');color: white; text-align: center;">Consolida</th>
						<th style="background-image:url('tableHeaderBack.png');color: white; text-align: center;">Segmento</th>
						<th style="background-image:url('tableHeaderBack.png');color: white; text-align: center;">Clasificaci&oacute;n</th>
						<th style="background-image:url('tableHeaderBack.png');color: white; text-align: center;">Pa&iacute;s</th>
						
						<th style="background-image:url('tableHeaderBack.png');color: white; text-align: center;">Giro</th>
						<th style="background-image:url('tableHeaderBack.png');color: white; text-align: center;">División</th>
					</tr>
					
					<%
					int i=1;
					String color = "";
						for (Record record : treeTableRecords) {
							if(i%2==0){
								color = "#EEF3FF";
							}else{
								color = "white";
							}
								
					%>
						
						<tr data-tt-id="<%= record.get("ID_ROW") %>" data-tt-parent-id="<%= record.get("ID_PARENT") %>" style="background-color: <%=color%>;">  
						<td style="font-size: 8px; padding-left: 10px; vertical-align: middle;">
						<%if(record.get("NO_EMP_ORACLE") != null && !record.get("NO_EMP_ORACLE").isEmpty()){ %>
							<%= record.get("NO_EMP_ORACLE") %>
								<%} else { out.print("Vacío");}%>
						</td>
							
							 <% 
									 String paddingCell="50px";
				        		String nombreEmpresa="";
				        		nombreEmpresa = record.get("NOM_EMPRESA");
							 
							 //record.get("NOM_EMPRESA").replaceAll("  ","&nbsp;&nbsp;&nbsp;&nbsp;") 
							 if(record.get("NOM_EMPRESA").startsWith("                         ")){
				        	    	paddingCell = "300px";
				        	    	nombreEmpresa = record.get("NOM_EMPRESA").replaceAll("                         ", "");
				        	    
				        	    }
				        	    else if(record.get("NOM_EMPRESA").startsWith("                    ")){
				        	    	paddingCell = "250px";
				        	    	nombreEmpresa = record.get("NOM_EMPRESA").replaceAll("                    ", "");
				        	    
				        	    } else if(record.get("NOM_EMPRESA").startsWith("               ")){
				        	    	paddingCell = "200px";
				        	    	nombreEmpresa = record.get("NOM_EMPRESA").replaceAll("               ", "");
				        	    
				        	    }else if(record.get("NOM_EMPRESA").startsWith("          ")){
				        	    	paddingCell = "150px";
				        	    	nombreEmpresa = record.get("NOM_EMPRESA").replaceAll("          ", "");
				        	    
				        	    }else if(record.get("NOM_EMPRESA").startsWith("     ")){
				        	    	paddingCell = "100px";
				        	    	nombreEmpresa = record.get("NOM_EMPRESA").replaceAll("     ", "");
				        	    
				        	    }
							 %>
							 <td style="font-size: 8px; vertical-align: middle; padding-left: <%=paddingCell%>;">
							<%=nombreEmpresa %>
						</td>
						<% if(selectPorcentajeVisualizar != null && (selectPorcentajeVisualizar.equals("Directo") || selectPorcentajeVisualizar.equals("Ambos") ) ) { %>
						<td style="font-size: 8px; padding-left: 10px; vertical-align: middle;">
							
								<%if(record.get("DIRECTO") != null && !record.get("DIRECTO").isEmpty()){ %>
									<%= record.get("DIRECTO") %>
								<%} else { out.print("Vacío");}%>
						
						</td>
						<% } %>
						<% if(selectPorcentajeVisualizar != null && (selectPorcentajeVisualizar.equals("Indirecto") || selectPorcentajeVisualizar.equals("Ambos") ) ) { %>
						<td style="font-size: 8px; padding-left: 10px; vertical-align: middle;">
							<%if(record.get("INDIRECTO") != null && !record.get("INDIRECTO").isEmpty()){ %>
								<%= record.get("INDIRECTO") %>
							<%} else { out.print("Vacío");}%>
						</td>
						<% } %>
						<td style="font-size: 8px; padding-left: 10px; vertical-align: middle;">
							<%if(record.get("CONSOLIDA") != null && !record.get("CONSOLIDA").isEmpty()){ %>
								<%= record.get("CONSOLIDA") %>
							<%} else { out.print("Vacío");}%>
							
						</td>
						<td style="font-size: 8px; padding-left: 10px; vertical-align: middle;">
						<%if(record.get("SEGMENTO") != null && !record.get("SEGMENTO").isEmpty()){ %>
							<%= record.get("SEGMENTO") %>
								<%} else { out.print("Vacío");}%>
						</td>
						<td style="font-size: 8px; padding-left: 10px; vertical-align: middle;">
						<%if(record.get("CLASIFICACION") != null && !record.get("CLASIFICACION").isEmpty()){ %>
							<%= record.get("CLASIFICACION") %>
								<%} else { out.print("Vacío");}%>
						</td>
						<td style="font-size: 8px; padding-left: 10px; vertical-align: middle;">
						<%if(record.get("PAIS") != null && !record.get("PAIS").isEmpty()){ %>
							<%= record.get("PAIS") %>
								<%} else { out.print("Vacío");}%>
						</td>
						
						<td style="font-size: 8px; padding-left: 10px; vertical-align: middle;">
						<%if(record.get("GIRO") != null && !record.get("GIRO").isEmpty()){ %>
							<%= record.get("GIRO") %>
								<%} else { out.print("Vacío");}%>
						</td>
						<td style="font-size: 8px; padding-left: 10px; vertical-align: middle;">
						<%if(record.get("DIVISION") != null && !record.get("DIVISION").isEmpty()){ %>
							<%= record.get("DIVISION") %>
								<%} else { out.print("Vacío");}%>
						</td>
					</tr>
					<% 
					i++;
						}
					%>
					
				</table>
				
								
				
			<% 
				}
			%>
			
			
			<% 
				if(treeGraphData != null) {
			%>
			
			
			

<style>

.node {
  cursor: pointer;
}

.node circle {
  fill: #fff;
  stroke: steelblue;
  stroke-width: 1.5px;
}

.node text {
  font: 10px sans-serif;
}

.link {
  fill: none;
  stroke: #E97C00;
  stroke-width: 1px;  //1.5
}

.tablaBody{
font: 8px sans-serif;
}

</style>

<script>

/*
var margin = {top: 20, right: 120, bottom: 20, left: 300},
    width = 960 - margin.right - margin.left,
    height = 400 - margin.top - margin.bottom;
*/

var margin = {top: 20, right: 120, bottom: 20, left: 300},
	width = 1500 - margin.right - margin.left,
	height = 600 - margin.top - margin.bottom;



var i = 0,
    duration = 700, //750
    root;

var tree = d3.layout.tree()
    .size([height, width]);

var diagonal = d3.svg.diagonal()
    .projection(function(d) { return [d.y, d.x]; });

var svg = d3.select("body").append("svg")
    .attr("width", width + margin.right + margin.left)
    .attr("height", height + margin.top + margin.bottom)
  .append("g")
    .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

d3.json("treeEmpresas.jsp?empresaId=<%= empresaId %>&fecha=<%=fecha%>&paramConsolida=<%=paramConsolida%>&paramSegmento=<%=paramSegmento%>&paramClasificacion= <%=paramClasificacion%>&paramPais=<%=paramPais%>&paramNoEmpOracle=<%=paramNoEmpOracle%>&paramGiro=<%=paramGiro%>&paramPorcentaje=<%=paramPorcentaje%>", function(error, flare) {
  if (error) throw error;

  root = flare;
  root.x0 = height / 2;
  root.y0 = 0;

  function collapse(d) {
    if (d.children) {
      d._children = d.children;
      d._children.forEach(collapse);
      d.children = null;
    }
  }

  root.children.forEach(collapse);
  update(root);
});

d3.select(self.frameElement).style("height", "800px");

function update(source) {

  // Compute the new tree layout.
  var nodes = tree.nodes(root).reverse(),
      links = tree.links(nodes);

  // Normalize for fixed-depth.
  nodes.forEach(function(d) { d.y = d.depth * 180; });

  // Update the nodesâ€¦
  var node = svg.selectAll("g.node")
      .data(nodes, function(d) { return d.id || (d.id = ++i); });

  // Enter any new nodes at the parent's previous position.
  var nodeEnter = node.enter().append("g")
      .attr("class", "node")
      .attr("transform", function(d) { return "translate(" + source.y0 + "," + source.x0 + ")"; })
      .on("click", click);

  nodeEnter.append("circle")
      .attr("r", 1e-6)
      .style("fill", function(d) { return d._children ? "lightsteelblue" : "#fff"; });

  nodeEnter.append("text")
      .attr("x", function(d) { return d.children || d._children ? -10 : 10; })
      .attr("dy", ".35em")
      .attr("text-anchor", function(d) { return d.children || d._children ? "end" : "start"; })
      .text(function(d) { return d.name; })
      .style("fill-opacity", 1e-6);

  // Transition nodes to their new position.
  var nodeUpdate = node.transition()
      .duration(duration)
      .attr("transform", function(d) { return "translate(" + d.y + "," + d.x + ")"; });

  nodeUpdate.select("circle")
      .attr("r", 4.5)
      .style("fill", function(d) { return d._children ? "lightsteelblue" : "#fff"; });

  nodeUpdate.select("text")
      .style("fill-opacity", 1);

  // Transition exiting nodes to the parent's new position.
  var nodeExit = node.exit().transition()
      .duration(duration)
      .attr("transform", function(d) { return "translate(" + source.y + "," + source.x + ")"; })
      .remove();

  nodeExit.select("circle")
      .attr("r", 1e-6);

  nodeExit.select("text")
      .style("fill-opacity", 1e-6);

  // Update the linksâ€¦
  var link = svg.selectAll("path.link")
      .data(links, function(d) { return d.target.id; });

  // Enter any new links at the parent's previous position.
  link.enter().insert("path", "g")
      .attr("class", "link")
      .attr("d", function(d) {
        var o = {x: source.x0, y: source.y0};
        return diagonal({source: o, target: o});
      });

  // Transition links to their new position.
  link.transition()
      .duration(duration)
      .attr("d", diagonal);

  // Transition exiting nodes to the parent's new position.
  link.exit().transition()
      .duration(duration)
      .attr("d", function(d) {
        var o = {x: source.x, y: source.y};
        return diagonal({source: o, target: o});
      })
      .remove();

  // Stash the old positions for transition.
  nodes.forEach(function(d) {
    d.x0 = d.x;
    d.y0 = d.y;
  });
  
  
  
  
  //alert(nodes.lenght);
  //var max_depth = d3.max(nodes, function(x) { return x.depth;});
  //alert(max_depth);
  
  
//alert(d._children);
  /*
//compute the new height
  var levelWidth = [1];
  var childCount = function(level, n) {

    if(n.children && n.children.length > 0) {
      if(levelWidth.length <= level + 1) levelWidth.push(0);

      levelWidth[level+1] += n.children.length;
      n.children.forEach(function(d) {
        childCount(level + 1, d);
      });
    }
  };
  childCount(0, root);  
  var newHeight = d3.max(levelWidth) * 20; // 20 pixels per line  
  tree = tree.size([newHeight, w]);
  */
  
  
  
  
}

// Toggle children on click.
function click(d) {
  if (d.children) {
    d._children = d.children;
    d.children = null;
  } else {
    d.children = d._children;
    d._children = null;
  }
  
  update(d);
  
  
  
  
  //tree.size.height = tree.size.height *= 2;
  //alert(tree.size.height); //tree.height *= 2;
  
  
 // var tree = d3.layout.tree()
  //.size([height, width]);
  
}

</script>

			
			<% 
				}
			%>
			
			
			<% 
				if(treeOrgChartData != null) {
			%>
			
			
			<script type="text/javascript">
        jQuery(document).ready(function () {
            jQuery('body').layout(
			{
			    center__paneSelector: "#contentpanel"
			});
        });
    </script>

    <style type="text/css">

        .orgChartTextSize {


            font-size: 8px;
        }

    </style>
			
			
    <script type="text/javascript">
        var orgDiagram = null;
        var treeItems = {};

        jQuery(document).ready(function () {
            jQuery.ajaxSetup({
                cache: false
            });

            jQuery('#contentpanel').layout(
			{
			    center__paneSelector: "#centerpanel"
				//, south__paneSelector: "#southpanel"
				, south__resizable: false
				, south__closable: false
				, south__spacing_open: 0
				, south__size: 50
				, west__size: 0
				, west__paneSelector: "#westpanel"
				, west__resizable: true
				, center__onresize: function () {
				    if (orgDiagram != null) {
				        jQuery("#centerpanel").orgDiagram("update", primitives.common.UpdateMode.Refresh);
				    }
				}
			});

            function ContainsKeyValue(obj, key, value) {
                if (obj[key] == value)
                    return { exist: true, json: obj };

                for (all in obj) {
                    if (obj[all] != null && obj[all][key] == value)
                        return { exist: true, json: obj[all] };

                    if (typeof obj[all] == "object" && obj[all] != null) {
                        var found = ContainsKeyValue(obj[all], key, value);
                        if (found.exist == true)
                            return { exist: true, json: found.json };
                    }
                }
                return { exist: false, json: null };
            }

            /* Page Fit Mode */
            var pageFitModes = jQuery("#pageFitMode");
            for (var key in primitives.common.PageFitMode) {
                var value = primitives.common.PageFitMode[key];
                
                //pageFitModes.append(jQuery("<br/><label><input name='pageFitMode' type='radio' value='" + value + "' " + (value == primitives.common.PageFitMode.FitToPage ? "checked" : "") + " />" +  value + "-"+ key + "</label>"));
                
                
                
                //
                // CUSTOMIZADO
                //
                
                	
                	if(value == 0) {
                		pageFitModes.append(jQuery("<br/><label><input name='pageFitMode' type='radio' value='" + value + "' " + (value == primitives.common.PageFitMode.FitToPage ? "checked" : "") + " />Mostrar Todo</label>"));
                	}

                	if(value == 1) {
                		pageFitModes.append(jQuery("<br/><label><input name='pageFitMode' type='radio' value='" + value + "' " + (value == primitives.common.PageFitMode.FitToPage ? "checked" : "") + " />Ancho de Página</label>"));
                	}
                	
                	if(value == 2) {
                		pageFitModes.append(jQuery("<br/><label><input name='pageFitMode' type='radio' value='" + value + "' " + (value == primitives.common.PageFitMode.FitToPage ? "checked" : "") + " />Alto de Página</label>"));
                	}
                	
                	if(value == 3) {
                		pageFitModes.append(jQuery("<br/><label><input name='pageFitMode' type='radio' value='" + value + "' " + (value == primitives.common.PageFitMode.FitToPage ? "checked" : "") + " />Página</label>"));
                	}
                
                
                
            };

            jQuery("input:radio[name=pageFitMode]").change(function () {
                Update(jQuery("#centerpanel"), primitives.common.UpdateMode.Refresh);
            });

            /* Orientation Type */
            var orientationTypes = jQuery("#orientationType");
            for (var key in primitives.common.OrientationType) {
                var value = primitives.common.OrientationType[key];
                
                //orientationTypes.append(jQuery("<br/><label><input name='orientationType' type='radio' value='" + value + "' " + (value == primitives.common.OrientationType.Top ? "checked" : "") + " />" + value + "-" + key + "</label>"));
                
                if(value == 0 || value == 2) {
                	if(value == 0) {
	                	orientationTypes.append(jQuery("<br/><label><input name='orientationType' type='radio' value='" + value + "' " + (value == primitives.common.OrientationType.Top ? "checked" : "") + " />Horizontal</label>"));
                	}
                	
                	if(value == 2) {
	                	orientationTypes.append(jQuery("<br/><label><input name='orientationType' type='radio' value='" + value + "' " + (value == primitives.common.OrientationType.Top ? "checked" : "") + " />Vertical</label>"));
                	}
                }
                
            };

            jQuery("input:radio[name=orientationType]").change(function () {
                Update(jQuery("#centerpanel"), primitives.common.UpdateMode.Refresh);
            });

            /* Vertical Alignmnet */
            var verticalAlignments = jQuery("#verticalAlignment");
            for (var key in primitives.common.VerticalAlignmentType) {
                var value = primitives.common.VerticalAlignmentType[key];
                verticalAlignments.append(jQuery("<br/><label><input name='verticalAlignment' type='radio' value='" + value + "' " + (value == primitives.common.VerticalAlignmentType.Middle ? "checked" : "") + " />" + key + "</label>"));
            };

            jQuery("input:radio[name=verticalAlignment]").change(function () {
                Update(jQuery("#centerpanel"), primitives.common.UpdateMode.Refresh);
            });

            /* Horizontal Alignmnet */
            var horizontalAlignments = jQuery("#horizontalAlignment");
            for (var key in primitives.common.HorizontalAlignmentType) {
                var value = primitives.common.HorizontalAlignmentType[key];
                horizontalAlignments.append(jQuery("<br/><label><input name='horizontalAlignment' type='radio' value='" + value + "' " + (value == primitives.common.HorizontalAlignmentType.Center ? "checked" : "") + " />" + key + "</label>"));
            };

            jQuery("input:radio[name=horizontalAlignment]").change(function () {
                Update(jQuery("#centerpanel"), primitives.common.UpdateMode.Refresh);
            });

            /* Minimal Visibility */
            var pageFitModes = jQuery("#minimalVisibility");
            for (var key in primitives.common.Visibility) {
                var value = primitives.common.Visibility[key];
                pageFitModes.append(jQuery("<br/><label><input name='minimalVisibility' type='radio' value='" + value + "' " + (value == primitives.common.Visibility.Dot ? "checked" : "") + " />" + key + "</label>"));
            };

            jQuery("input:radio[name=minimalVisibility]").change(function () {
                Update(jQuery("#centerpanel"), primitives.common.UpdateMode.Refresh);
            });

            /* Selection Path Mode */
            var selectionPathModes = jQuery("#selectionPathMode");
            for (var key in primitives.common.SelectionPathMode) {
                var value = primitives.common.SelectionPathMode[key];
                selectionPathModes.append(jQuery("<br/><label><input name='selectionPathMode' type='radio' value='" + value + "' " + (value == primitives.common.SelectionPathMode.FullStack ? "checked" : "") + " />" + key + "</label>"));
            };

            jQuery("input:radio[name=selectionPathMode]").change(function () {
                Update(jQuery("#centerpanel"), primitives.common.UpdateMode.Refresh);
            });

            /* Leaves Placement Type */
            var leavesPlacementType = jQuery("#leavesPlacementType");
            for (var key in primitives.common.ChildrenPlacementType) {
                var value = primitives.common.ChildrenPlacementType[key];
                leavesPlacementType.append(jQuery("<br/><label><input name='leavesPlacementType' type='radio' value='" + value + "' " + (value == primitives.common.ChildrenPlacementType.Horizontal ? "checked" : "") + " />" + key + "</label>"));
            };

            jQuery("input:radio[name=leavesPlacementType]").change(function () {
                Update(jQuery("#centerpanel"), primitives.common.UpdateMode.Refresh);
            });

            /* Has Selector Check Box*/
            var hasSelectorCheckbox = jQuery("#hasSelectorCheckbox");
            for (var key in primitives.common.Enabled) {
                var value = primitives.common.Enabled[key];
                hasSelectorCheckbox.append(jQuery("<br/><label><input name='hasSelectorCheckbox' type='radio' value='" + value + "' " + (value == primitives.common.Enabled.True ? "checked" : "") + " />" + key + "</label>"));
            };

            jQuery("input:radio[name=hasSelectorCheckbox]").change(function () {
                Update(jQuery("#centerpanel"), primitives.common.UpdateMode.Refresh);
            });

            /* Has User Buttons */
            var hasButtons = jQuery("#hasButtons");
            for (var key in primitives.common.Enabled) {
                var value = primitives.common.Enabled[key];
                hasButtons.append(jQuery("<br/><label><input name='hasButtons' type='radio' value='" + value + "' " + (value == primitives.common.Enabled.Auto ? "checked" : "") + " />" + key + "</label>"));
            };

            jQuery("input:radio[name=hasButtons]").change(function () {
                Update(jQuery("#centerpanel"), primitives.common.UpdateMode.Refresh);
            });

            /* Items Group By Type */
            var arrowsDirections = jQuery("#arrowsDirection");
            for (var key in primitives.common.GroupByType) {
                var value = primitives.common.GroupByType[key];
                arrowsDirections.append(jQuery("<br/><label><input name='arrowsDirection' type='radio' value='" + value + "' " + (value == primitives.common.GroupByType.None ? "checked" : "") + " />" + key + "</label>"));
            };

            jQuery("input:radio[name=arrowsDirection]").change(function () {
                Update(jQuery("#centerpanel"), primitives.common.UpdateMode.Refresh);
            });

            /* Connector Type */
            var connectorTypes = jQuery("#connectorType");
            for (var key in primitives.common.ConnectorType) {
                var value = primitives.common.ConnectorType[key];
                connectorTypes.append(jQuery("<br/><label><input name='connectorType' type='radio' value='" + value + "' " + (value == primitives.common.ConnectorType.Squared ? "checked" : "") + " />" + key + "</label>"));
            };

            jQuery("input:radio[name=connectorType]").change(function () {
                Update(jQuery("#centerpanel"), primitives.common.UpdateMode.Refresh);
            });

            /* Connectors Elbows Type */
            var elbowTypes = jQuery("#elbowType");
            for (var key in primitives.common.ElbowType) {
                var value = primitives.common.ElbowType[key];
                elbowTypes.append(jQuery("<br/><label><input name='elbowType' type='radio' value='" + value + "' " + (value == primitives.common.ElbowType.None ? "checked" : "") + " />" + key + "</label>"));
            };

            jQuery("input:radio[name=elbowType]").change(function () {
                Update(jQuery("#centerpanel"), primitives.common.UpdateMode.Refresh);
            });

            // lineType
            var connectorShapeType = jQuery("#lineType");
            for (var key in primitives.common.LineType) {
                var value = primitives.common.LineType[key];
                connectorShapeType.append(jQuery("<br/><label><input name='lineType' type='radio' value='" + value + "' " + (value == primitives.common.LineType.Solid ? "checked" : "") + " />" + key + "</label>"));
            };

            jQuery("input:radio[name=lineType]").change(function () {
                Update(jQuery("#centerpanel"), primitives.common.UpdateMode.Refresh);
            });

            // color
            var color = jQuery("<select></select>");
            jQuery("#color").append(color);
            for (var key in primitives.common.Colors) {
                var value = primitives.common.Colors[key];
                color.append(jQuery("<option value='" + value + "' " + (value == primitives.common.Colors.Silver ? "selected" : "") + " >" + key + "</option>"));
            };

            jQuery("#color").change(function () {
                Update(jQuery("#centerpanel"), primitives.common.UpdateMode.Refresh);
            });

            // minimizedItemCornerRadius
            var minimizedItemCornerRadiuses = ["NULL", 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
            var minimizedItemCornerRadius = jQuery("<select></select>");
            jQuery("#minimizedItemCornerRadius").append(minimizedItemCornerRadius);
            for (var index = 0; index < minimizedItemCornerRadiuses.length; index += 1) {
                var value = minimizedItemCornerRadiuses[index];
                minimizedItemCornerRadius.append(jQuery("<option value='" + (value == "NULL" ? -1 : value) + "' " + (value == "NULL" ? "selected" : "") + " >" + value + "</option>"));
            };

            jQuery("#minimizedItemCornerRadius").change(function () {
                Update(jQuery("#centerpanel"), primitives.common.UpdateMode.Redraw);
            });

            // minimizedItemSize
            var minimizedItemSizes = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 14, 16, 18, 20, 30, 40];
            var minimizedItemSize = jQuery("<select></select>");
            jQuery("#minimizedItemSize").append(minimizedItemSize);
            for (var index = 0; index < minimizedItemSizes.length; index += 1) {
                var value = minimizedItemSizes[index];
                minimizedItemSize.append(jQuery("<option value='" + value + "' " + (value == 4 ? "selected" : "") + " >" + value + "</option>"));
            };

            jQuery("#minimizedItemSize").change(function () {
                Update(jQuery("#centerpanel"), primitives.common.UpdateMode.Redraw);
            });

            // highlightPadding
            var highlightPaddings = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
            var highlightPadding = jQuery("<select></select>");
            jQuery("#highlightPadding").append(highlightPadding);
            for (var index = 0; index < highlightPaddings.length; index += 1) {
                var value = highlightPaddings[index];
                highlightPadding.append(jQuery("<option value='" + value + "' " + (value == 2 ? "selected" : "") + " >" + value + "</option>"));
            };

            jQuery("#highlightPadding").change(function () {
                Update(jQuery("#centerpanel"), primitives.common.UpdateMode.Redraw);
            });

            // Intervals
            var intervalNames = ["normalLevelShift", "dotLevelShift", "lineLevelShift", "normalItemsInterval", "dotItemsInterval", "lineItemsInterval", "cousinsIntervalMultiplier"];
            var intervals = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 14, 16, 18, 20, 30, 40];
            var defaultConfig = new primitives.orgdiagram.Config();
            defaultConfig.dotItemsInterval = 2;
            for (var intervalIndex = 0; intervalIndex < intervalNames.length; intervalIndex++) {
                var intervalName = intervalNames[intervalIndex];
                var intervalSelector = jQuery("<select></select>");
                jQuery("#" + intervalName).append(intervalSelector);
                for (var index = 0; index < intervals.length; index += 1) {
                    var value = intervals[index];
                    var defaultValue = defaultConfig[intervalName];

                    intervalSelector.append(jQuery("<option value='" + value + "' " + (value == defaultValue ? "selected" : "") + " >" + value + "</option>"));
                };

                jQuery("#" + intervalName).change(function () {
                    Update(jQuery("#centerpanel"), primitives.common.UpdateMode.Refresh);
                });
            }

            // lineWidth
            var lineWidths = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
            var lineWidth = jQuery("<select></select>");
            jQuery("#lineWidth").append(lineWidth);
            for (var index = 0; index < lineWidths.length; index += 1) {
                var value = lineWidths[index];
                lineWidth.append(jQuery("<option value='" + value + "' " + (value == 1 ? "selected" : "") + " >" + value + "</option>"));
            };

            jQuery("#lineWidth").change(function () {
                Update(jQuery("#centerpanel"), primitives.common.UpdateMode.Refresh);
            });

            /* Show Labels */
            var showLabels = jQuery("#showLabels");
            for (var key in primitives.common.Enabled) {
                var value = primitives.common.Enabled[key];
                showLabels.append(jQuery("<br/><label><input name='showLabels' type='radio' value='" + value + "' " + (value == primitives.common.Enabled.Auto ? "checked" : "") + " />" + key + "</label>"));
            };

            jQuery("input:radio[name=showLabels]").change(function () {
                Update(jQuery("#centerpanel"), primitives.common.UpdateMode.Refresh);
            });

            /* Label Orientations */
            var labelOrientations = jQuery("#labelOrientation");
            for (var key in primitives.text.TextOrientationType) {
                var value = primitives.text.TextOrientationType[key];
                labelOrientations.append(jQuery("<br/><label><input name='labelOrientation' type='radio' value='" + value + "' " + (value == primitives.text.TextOrientationType.Horizontal ? "checked" : "") + " />" + key + "</label>"));
            };

            jQuery("input:radio[name=labelOrientation]").change(function () {
                Update(jQuery("#centerpanel"), primitives.common.UpdateMode.Refresh);
            });

            /* Label Placement */
            var labelPlacements = jQuery("#labelPlacement");
            for (var key in primitives.common.PlacementType) {
                var value = primitives.common.PlacementType[key];
                labelPlacements.append(jQuery("<br/><label><input name='labelPlacement' type='radio' value='" + value + "' " + (value == primitives.common.PlacementType.Top ? "checked" : "") + " />" + key + "</label>"));
            };

            jQuery("input:radio[name=labelPlacement]").change(function () {
                Update(jQuery("#centerpanel"), primitives.common.UpdateMode.Refresh);
            });

            /* Graphics Type */
            var graphicsType = jQuery("#graphicsType");
            for (var key in primitives.common.GraphicsType) {
                var value = primitives.common.GraphicsType[key];
                graphicsType.append(jQuery("<br/><label><input name='graphicsType' type='radio' value='" + value + "' " + (value == primitives.common.GraphicsType.SVG ? "checked" : "") + " />" + key + "</label>"));
            };

            jQuery("input:radio[name=graphicsType]").change(function () {
                Update(jQuery("#centerpanel"), primitives.common.UpdateMode.Recreate);
            });

            /* Setup & Run */
            Setup(jQuery("#centerpanel"));

            /* Load data */
            LoadData(jQuery("#centerpanel"));
        });

        function Setup(selector) {
            orgDiagram = selector.orgDiagram(GetOrgDiagramConfig());

            ShowGraphicsType(selector.orgDiagram("option", "actualGraphicsType"));
        }

        function LoadData(selector) {
            var index, len;
            for (index = 0, len = data.length; index < len; index += 1) {
                treeItems[data[index].id] = data[index];
            }

            /* set template for cursor item */
            data[0].templateName = "contactTemplate";

            selector.orgDiagram("option", {
                items: data,
                cursorItem: 0
            });
            selector.orgDiagram("update");
        }


        function Update(selector, updateMode) {
            selector.orgDiagram("option", GetOrgDiagramConfig());
            selector.orgDiagram("update", updateMode);

            ShowGraphicsType(selector.orgDiagram("option", "actualGraphicsType"));
        }

        function GetOrgDiagramConfig() {
            var graphicsType = parseInt(jQuery("input:radio[name=graphicsType]:checked").val(), 10);
            //var pageFitMode = parseInt(jQuery("input:radio[name=pageFitMode]:checked").val(), 10);
            var pageFitMode =0;
            //var orientationType = parseInt(jQuery("input:radio[name=orientationType]:checked").val(), 10);
            var orientationType = 2;
            var minimalVisibility = parseInt(jQuery("input:radio[name=minimalVisibility]:checked").val(), 10);
            var selectionPathMode = parseInt(jQuery("input:radio[name=selectionPathMode]:checked").val(), 10);
            var leavesPlacementType = parseInt(jQuery("input:radio[name=leavesPlacementType]:checked").val(), 10);
            //var hasSelectorCheckbox = parseInt(jQuery("input:radio[name=hasSelectorCheckbox]:checked").val(), 10);
            var hasSelectorCheckbox = 2;
            

            var hasButtons = parseInt(jQuery("input:radio[name=hasButtons]:checked").val(), 10);
            var verticalAlignment = parseInt(jQuery("input:radio[name=verticalAlignment]:checked").val(), 10);
            var horizontalAlignment = parseInt(jQuery("input:radio[name=horizontalAlignment]:checked").val(), 10);
            var connectorType = parseInt(jQuery("input:radio[name=connectorType]:checked").val(), 10);
            var elbowType = parseInt(jQuery("input:radio[name=elbowType]:checked").val(), 10);
            var showLabels = parseInt(jQuery("input:radio[name=showLabels]:checked").val(), 10);
            var labelOrientation = parseInt(jQuery("input:radio[name=labelOrientation]:checked").val(), 10);
            var labelPlacement = parseInt(jQuery("input:radio[name=labelPlacement]:checked").val(), 10);
            //var color = jQuery("#color option:selected").val();
            var color = "#6A5ACD";
            
            var lineWidth = parseInt(jQuery("#lineWidth option:selected").val(), 10);
            var lineType = parseInt(jQuery("input:radio[name=lineType]:checked").val(), 10);
            var arrowsDirection = parseInt(jQuery("input:radio[name=arrowsDirection]:checked").val(), 10);


            var minimizedItemCornerRadius = parseInt(jQuery("#minimizedItemCornerRadius option:selected").val(), 10);
            minimizedItemCornerRadius = minimizedItemCornerRadius == -1 ? null : minimizedItemCornerRadius;

            var minimizedItemSize = parseInt(jQuery("#minimizedItemSize option:selected").val(), 10);
            var highlightPadding = parseInt(jQuery("#highlightPadding option:selected").val(), 10);

            var normalLevelShift = parseInt(jQuery("#normalLevelShift option:selected").val(), 10);
            var dotLevelShift = parseInt(jQuery("#dotLevelShift option:selected").val(), 10);
            var lineLevelShift = parseInt(jQuery("#lineLevelShift option:selected").val(), 10);
            var normalItemsInterval = parseInt(jQuery("#normalItemsInterval option:selected").val(), 10);
            var dotItemsInterval = parseInt(jQuery("#dotItemsInterval option:selected").val(), 10);
            var lineItemsInterval = parseInt(jQuery("#lineItemsInterval option:selected").val(), 10);
            var cousinsIntervalMultiplier = parseInt(jQuery("#cousinsIntervalMultiplier option:selected").val(), 10);

            var buttons = [];
            //buttons.push(new primitives.orgdiagram.ButtonConfig("delete", "ui-icon-close", "Delete"));
            //buttons.push(new primitives.orgdiagram.ButtonConfig("properties", "ui-icon-gear", "Info"));
            //buttons.push(new primitives.orgdiagram.ButtonConfig("add", "ui-icon-person", "Add"));

            var templates = [
                getContactTemplate(minimizedItemCornerRadius, minimizedItemSize, highlightPadding),
                getDefaultTemplate(minimizedItemCornerRadius, minimizedItemSize, highlightPadding)
            ];

            return {
                graphicsType: graphicsType,
                pageFitMode: pageFitMode,
                orientationType: orientationType,
                verticalAlignment: verticalAlignment,
                horizontalAlignment: horizontalAlignment,
                arrowsDirection: arrowsDirection,
                connectorType: connectorType,
                elbowType: elbowType,
                minimalVisibility: minimalVisibility,
                hasSelectorCheckbox: hasSelectorCheckbox,
                selectionPathMode: selectionPathMode,
                leavesPlacementType: leavesPlacementType,
                hasButtons: hasButtons,
                buttons: buttons,
                templates: templates,
                onButtonClick: onButtonClick,
                onCursorChanging: onCursorChanging,
                onCursorChanged: onCursorChanged,
                onMouseClick: onMouseClick,
                onMouseDblClick: onMouseDblClick,
                onHighlightChanging: onHighlightChanging,
                onHighlightChanged: onHighlightChanged,
                onSelectionChanged: onSelectionChanged,
                onItemRender: onTemplateRender,
                itemTitleFirstFontColor: primitives.common.Colors.White,
                itemTitleSecondFontColor: primitives.common.Colors.White,
                showLabels: showLabels,
                labelOrientation: labelOrientation,
                labelPlacement: labelPlacement,
                labelOffset: 2,
                linesType: lineType,
                linesColor: color,
                linesWidth: lineWidth,
                defaultTemplateName: "contactTemplate",
                normalLevelShift: normalLevelShift,
                dotLevelShift: dotLevelShift,
                lineLevelShift: lineLevelShift,
                normalItemsInterval: normalItemsInterval,
                dotItemsInterval: dotItemsInterval,
                lineItemsInterval: lineItemsInterval, 
                cousinsIntervalMultiplier: cousinsIntervalMultiplier
            };
        }

    
        function getDefaultTemplate(minimizedItemCornerRadius, minimizedItemSize, highlightPadding) {
            var result = new primitives.orgdiagram.TemplateConfig();
            result.name = "defaultTemplate";

            // If we don;t change anything in template all its properties assigned to default values
            // So we change only default dot size and corner radius
            result.minimizedItemSize = new primitives.common.Size(minimizedItemSize, minimizedItemSize);
            result.minimizedItemCornerRadius = minimizedItemCornerRadius;
            result.highlightPadding = new primitives.common.Thickness(highlightPadding, highlightPadding, highlightPadding, highlightPadding);

            return result;
        }
        


        function getContactTemplate(minimizedItemCornerRadius, minimizedItemSize, highlightPadding) {
            var result = new primitives.orgdiagram.TemplateConfig();
            result.name = "contactTemplate";

            //result.itemSize = new primitives.common.Size(220, 120);
            result.itemSize = new primitives.common.Size(200, 80);
            result.minimizedItemSize = new primitives.common.Size(minimizedItemSize, minimizedItemSize);
            result.minimizedItemCornerRadius = minimizedItemCornerRadius;
            result.highlightPadding = new primitives.common.Thickness(highlightPadding, highlightPadding, highlightPadding, highlightPadding);

            
            var itemTemplate = jQuery(
			  '<div class="bp-item bp-corner-all bt-item-frame">'
                + '<table>'
                + '<tr><th colspan=3><div name="empresa" class="orgChartTextSize"></div></th></tr>'
                + '<tr><td colspan=3></td></tr>'
                + '<tr><td width="20%"></td><td width="40%" class="orgChartTextSize">Directo:</td><td><div name="directo" class="orgChartTextSize"></td></tr>'
                + '<tr><td width="20%"></td><td width="40%" class="orgChartTextSize">Indirecto:</td><td><div name="indirecto" class="orgChartTextSize"></td></tr>'
                + '</table>'

                /*
				+ '<div name="titleBackground" class="bp-item bp-corner-all bp-title-frame" style="top: 2px; left: 2px; width: 216px; height: 20px;">'
					+ '<div name="title" class="bp-item bp-title" style="top: 3px; left: 6px; width: 208px; height: 18px;">'
					+ '</div>'
				+ '</div>'
				//+ '<div class="bp-item bp-photo-frame" style="top: 26px; left: 2px; width: 50px; height: 60px;">'
				//	+ '<img name="photo" style="height: 60px; width:50px;" />'
				//+ '</div>'
				+ '<div name="phone" class="bp-item" style="top: 26px; left: 56px; width: 162px; height: 18px; font-size: 12px;"></div>'
                + '<div class="bp-item" style="top: 44px; left: 56px; width: 162px; height: 18px; font-size: 12px;"><a name="email" href="" target="_top"></a></div>'
				//+ '<div name="description" class="bp-item" style="top: 62px; left: 56px; width: 162px; height: 36px; font-size: 10px;"></div>'
			     + '</div>'
                */


			).css({
			    width: result.itemSize.width + "px",
			    height: result.itemSize.height + "px"
			}).addClass("bp-item bp-corner-all bt-item-frame");
            result.itemTemplate = itemTemplate.wrap('<div>').parent().html();

            return result;
        }

        function onTemplateRender(event, data) {
            switch (data.renderingMode) {
                case primitives.common.RenderingMode.Create:
                    data.element.find("[name=email]").click(function (e) {
                        /* Block mouse click propogation in order to avoid layout updates before server postback*/
                        primitives.common.stopPropagation(e);
                    });
                    /* Initialize widgets here */
                    break;
                case primitives.common.RenderingMode.Update:
                    /* Update widgets here */
                    break;
            }

            var itemConfig = data.context,
                itemTitleColor = itemConfig.itemTitleColor != null ? itemConfig.itemTitleColor : primitives.common.Colors.RoyalBlue;

            if (data.templateName == "contactTemplate") {
                //data.element.find("[name=photo]").attr({ "src": itemConfig.image });
                //data.element.find("[name=titleBackground]").css({ "background": itemTitleColor });
                //data.element.find("[name=email]").attr({ "href": ("mailto:" + itemConfig.email + "?Subject=Hello%20world") });

                //var fields = ["title", "description", "phone", "email"];
                var fields = ["empresa", "directo", "indirecto"];
                for (var index = 0; index < fields.length; index += 1) {
                    var field = fields[index];

                    var element = data.element.find("[name=" + field + "]");
                    if (element.text() != itemConfig[field]) {
                        element.text(itemConfig[field]);
                    }
                }
            }
        }

        function onSelectionChanged(e, data) {
            var selectedItems = jQuery("#centerpanel").orgDiagram("option", "selectedItems");
            var message = "";
            for (var index = 0; index < selectedItems.length; index += 1) {
                var itemConfig = treeItems[selectedItems[index]];
                if (message != "") {
                    message += ", ";
                }
                message += "<b>'" + itemConfig.title + "'</b>";
            }
            message += (data.parentItem != null ? " Parent item <b>'" + data.parentItem.title + "'" : "");
            //jQuery("#southpanel").empty().append("User selected following items: " + message);
        }

        function onHighlightChanging(e, data) {
            var message = (data.context != null) ? "User is hovering mouse over item <b>'" + data.context.title + "'</b>." : "";
            message += (data.parentItem != null ? " Parent item <b>'" + data.parentItem.title + "'" : "");

            //jQuery("#southpanel").empty().append(message);
        }

        function onHighlightChanged(e, data) {
            var message = (data.context != null) ? "User hovers mouse over item <b>'" + data.context.title + "'</b>." : "";
            message += (data.parentItem != null ? " Parent item <b>'" + data.parentItem.title + "'" : "");

            //jQuery("#southpanel").empty().append(message);
        }

        function onCursorChanging(e, data) {
            var message = "User is clicking on item '" + data.context.title + "'.";
            message += (data.parentItem != null ? " Parent item <b>'" + data.parentItem.title + "'" : "");

            //jQuery("#southpanel").empty().append(message);

            data.oldContext.templateName = null;
            data.context.templateName = "contactTemplate";
        }

        function onCursorChanged(e, data) {
            var message = "User clicked on item '" + data.context.title + "'.";
            message += (data.parentItem != null ? " Parent item <b>'" + data.parentItem.title + "'" : "");
            //jQuery("#southpanel").empty().append(message);
        }

        function onButtonClick(e, data) {
            var message = "User clicked <b>'" + data.name + "'</b> button for item <b>'" + data.context.title + "'</b>.";
            message += (data.parentItem != null ? " Parent item <b>'" + data.parentItem.title + "'" : "");
            //jQuery("#southpanel").empty().append(message);
        }

        function onMouseClick(e, data) {
            var message = "User clicked item <b>'" + data.context.title + "'</b>.";
            message += (data.parentItem != null ? " Parent item <b>'" + data.parentItem.title + "'" : "");
            //jQuery("#southpanel").empty().append(message);
        }

        function onMouseDblClick(e, data) {
            var message = "User double clicked item <b>'" + data.context.title + "'</b>.";
            message += (data.parentItem != null ? " Parent item <b>'" + data.parentItem.title + "'" : "");
            //jQuery("#southpanel").empty().append(message);
        }

        function ShowGraphicsType(graphicsType) {
            var result = null;

            switch (graphicsType) {
                case primitives.common.GraphicsType.SVG:
                    result = "SVG";
                    break;
                case primitives.common.GraphicsType.Canvas:
                    result = "Canvas";
                    break;
                case primitives.common.GraphicsType.VML:
                    result = "VML";
                    break;
            }
            jQuery("#actualGraphicsType").empty().append("Graphics Type: " + result);
        }

    </script>
    
    
    <div id="contentpanel" style="padding: 0px;" >
    
    
    
    
    
        <!--bpcontent-->
        <div id="westpanel" style="padding: 5px; margin: 0px; border-style: solid; font-size: 12px; border-color: grey; border-width: 1px; overflow: scroll; -webkit-overflow-scrolling: touch;">
            
            
            <div style='display:none;'>
    		<jsp:include page="/jsp/components/pages_border4PrintFullContext/open-pre-title.jsp"></jsp:include>
			
				Reporte de Tenencia en Cascada
			
			<jsp:include page="/jsp/components/pages_border4PrintFullContext/open-post-title.jsp"></jsp:include>
    
            
            <div align='right'>
            <a href='tenenciaCascada.jsp?hiddenEmpresa=<%= empresaId%><%=paramString%>'>Regresar..</a>
            </div>
            <br>
            <br>
            
            <h3>Opciones de Visualización</h3>
             
            <p id="pageFitMode"><b>Modalidad de Ajuste2</b><br></p>
            <p id="orientationType"><br><b>Orientación</b><br></p>
            <p id="verticalAlignment" style="display:none">Items Vertical Alignment</p>
            <p id="horizontalAlignment" style="display:none">Items Horizontal Alignment</p>
            <p id="leavesPlacementType" style="display:none">Leaves placement</p>
            <p id="minimalVisibility" style="display:none">Minimal nodes visibility</p>
            <p id="selectionPathMode" style="display:none">Selection Path Mode</p>
            <!--<h3>Default Template Options</h3>-->
            <p id="hasButtons" style="display:none">User buttons</p>
            <p id="hasSelectorCheckbox" style="display:none">Selection check box</p>
            <!--<h3>Minimized Item (Dot, Marker)</h3>-->
            <p id="minimizedItemCornerRadius" style="display:none">Corner Radius:&nbsp;</p>
            <p id="minimizedItemSize" style="display:none">Size:&nbsp;</p>
            <p id="highlightPadding" style="display:none">Highlight Padding:&nbsp;</p>            
            <!--<h3>Vertical Intervals Between Rows</h3>-->
            <p id="normalLevelShift" style="display:none">Normal:&nbsp;</p>
            <p id="dotLevelShift" style="display:none">Dotted:&nbsp;</p>
            <p id="lineLevelShift" style="display:none">Lined:&nbsp;</p>
            <!--<h3>Horizontal Intervals Between Items in Row</h3>-->
            <p id="normalItemsInterval" style="display:none">Normal:&nbsp;</p>
            <p id="dotItemsInterval" style="display:none">Dotted:&nbsp;</p>
            <p id="lineItemsInterval" style="display:none">Lined:&nbsp;</p>
            <p id="cousinsIntervalMultiplier" style="display:none">Cousins Multiplier:&nbsp;</p>
            <!--<h3>Connectors</h3>-->
            <p id="arrowsDirection" style="display:none">Arrows Direction</p>
            <p id="connectorType" style="display:none">Connectors</p>
            <p id="elbowType" style="display:none">Connectors Elbows Type</p>
            <p id="lineType" style="display:none">Line type</p>
            
            
            <p id="color" style="display:none">Color:&nbsp;</p>
            <p id="lineWidth" style="display:none">Line width:&nbsp;</p>
            
            
            <!--<h3>Labels</h3>-->
            <p id="showLabels" style="display:none">Show Labels</p>
            <p id="labelOrientation" style="display:none">Label Orientation</p>
            <p id="labelPlacement" style="display:none">Label Placement</p>
            <!--<h3>Rendering Mode</h3>-->
            <p id="graphicsType" style="display:none">Graphics</p>
            <p id="actualGraphicsType" style="display:none"></p>
            
            <jsp:include page="/jsp/components/pages_border4PrintFullContext/close.jsp"></jsp:include>
            </div>
        </div>
        <div id="centerpanel" style="overflow: hidden; padding: 0px; margin: 0px; border: 0px; ">
        
        </div>
        <div id="southpanel">
        </div>
        <!--/bpcontent-->
        
        
		


    </div>
			
			
			
	
			
			
			
			
					
			<% 
				}
			%>
			
		</td>
	</tr>
	
	
</table>

</form>

<jsp:include page="/jsp/components/pages_border4PrintFullContext/close.jsp"></jsp:include>

</body>
</html>
</pd4ml:transform>

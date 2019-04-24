<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="sun.security.util.Length"%>
<%@page import="mx.com.televisa.derechocorporativo.modules.reports.tenecascada.TenenciaCascada"%>
<%@page import="mx.com.televisa.derechocorporativo.modules.reportsFlex.ReportFlex,
				mx.com.televisa.derechocorporativo.bean.SessionBean"%>
<%@ taglib uri="pd4ml" prefix="pd4ml"%><%@page
	contentType="text/html; charset=ISO8859_1"%>
<%

	String repId = request.getParameter("repId");

	//int intRepId = Integer.parseInt(repId);

	//ReportFlex rep = ReportFlex.getReport(repId);
		
	//String fileName = "My Report 001.pdf".replace(" ", "_");
	
	//String fileName = rep.getNomReporte().replace(" ", "_")+".pdf";
	String fileName = "OrgChartRep.pdf";
	
	//String reportContent = "<h1>Hello PD4ML</h1>";
	
	SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
	int liIdRol =   Integer.valueOf(sessionBean.getIdRol());
	
	//String reportContent = ReportFlex.getReportTemplate(intRepId, request, liIdRol);
	//String reportContent = "Esto es un reporte para ORGCHART";
	String reportContent = "";
	
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
    
    String titleStyle = "style='color: #3A34A5;text-align: center;font-weight: bold;font-size: 16px;'";
    
    //reportContent = reportContent.replace("class='pageTitle2'", "style='color: white;text-align: center;font-weight: bold;font-size: 12px;background-image: url(\"" + imgPath + "borders/page_border/border02.png\");'");
    reportContent = reportContent.replace("class='pageTitle2'", "style='color: white;text-align: center;font-weight: bold;font-size: 12px;background-image: url(\"" + imgPath + "tableHeaderBack.png\");'");
    //reportContent = reportContent.replace("class='pageTitle2'", "style='background-image:url(\"" + imgPath + "tableHeaderBack.png\"); color: white;'");
    
    reportContent = reportContent.replace("class='tableHeader'", "style='background-image:url(\"" + imgPath + "tableHeaderBack.png\"); color: white;'");    
    reportContent = reportContent.replace("class='flexTable'", "style='border-color: #F5F5F5;border-width: thin;'");
    //reportContent = reportContent.replace("class='tableRow2'", "style='background:#DCE8F6;'");
    
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%--
<pd4ml:transform
	screenWidth="780" pageFormat="LETTER" fileName="<%= fileName %>"
	pageInsets="10,10,10,10,mm" adjustScreenWidth="true"
	enableTableBreaks="false" enableImageSplit="false" debug="true">
	<pd4ml:footer areaHeight="30">

	</pd4ml:footer>

--%>
	<html>
<head>
<title>Dynamic Report</title>

<link
	href="<%= fullContextPath %>/css/pd4ml_report.css"
	rel="stylesheet" type="text/css">

<style>
html {
	font-family: Verdana, Arial, Helvetica;
	color: #000000;
	font-size: 8pt;
}

td {
	FONT: 10.5px Verdana, sans-serif;
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
</head>

<!-- Inicio Def PDF -->

	  <head>
<%
	String empresaId = request.getParameter("empresaId");
	System.out.println("empresaId: "+empresaId);

	String paramConsolida = 	(request.getParameter("paramConsolida") != null) ? request.getParameter("paramConsolida") : "";
	String paramSegmento = 		(request.getParameter("paramSegmento") != null) ? request.getParameter("paramSegmento") : "";
	String paramClasificacion = (request.getParameter("paramClasificacion") != null) ? request.getParameter("paramClasificacion") : "";
	String paramPais = 			(request.getParameter("paramPais") != null) ? request.getParameter("paramPais") : "";
	String paramNoEmpOracle = 	(request.getParameter("paramNoEmpOracle") != null) ? request.getParameter("paramNoEmpOracle") : "";
	String paramGiro = 			(request.getParameter("paramGiro") != null) ? request.getParameter("paramGiro") : "";
	String paramPorcentaje = 			(request.getParameter("paramPorcentaje") != null) ? request.getParameter("paramPorcentaje") : "";
	String selectPorcentaje = 			(request.getParameter("selectPorcentaje") != null) ? request.getParameter("selectPorcentaje") : "";
	String selectPorcentajeCual = 			(request.getParameter("selectPorcentajeCual") != null) ? request.getParameter("selectPorcentajeCual") : "";
	String selectPorcentajeVisualizar = 			(request.getParameter("selectPorcentajeVisualizar") != null) ? request.getParameter("selectPorcentajeVisualizar") : "";
	
	
	String fecha = "";

	String data = TenenciaCascada.getTreeDataOrgChartGoogle(empresaId, fecha, 
			paramConsolida, 
			paramSegmento, 
			paramClasificacion, 
			paramPais, 
			paramNoEmpOracle, 
			paramGiro,
			paramPorcentaje,
			selectPorcentaje,
			selectPorcentajeCual,
			selectPorcentajeVisualizar);
	
	int longitud = data.length();
	String comaPuntoComa = StringUtils.substring(data,longitud - 1, longitud);
	if(comaPuntoComa.equals(",")){
		data = StringUtils.substring(data,0, longitud-1);
		System.out.println("-------------------"+data);	
		data+="]);";
	}
	
	
	//System.out.println("comaPuntoComa ****"+comaPuntoComa);
	System.out.println("TOC:\n"+data);

%>

    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load('current', {packages:["orgchart"]});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'Name');
        data.addColumn('string', 'Manager');
        data.addColumn('string', 'ToolTip');

        // For each orgchart box, provide the name, manager, and tooltip to show.
        /*
        data.addRows([
      				[{v:'248', f:'GRUPO TELEVISA, S.A.B.<div style="color:red; font-style:italic">directo: ""</div><div style="color:blue; font-style:italic">indirecto: ""</div>'},'',''],
    				[{v:'751', f:'METROPOLI VIP, S.A. DE C.V.<div style="color:red; font-style:italic">directo: "99.999705"</div><div style="color:blue; font-style:italic">indirecto: "99.999705"</div>'},'248',''],
    				[{v:'70', f:'SISTEMA RADIOPOLIS, S. A. DE  C. V.<div style="color:red; font-style:italic">directo: "50.000000"</div><div style="color:blue; font-style:italic">indirecto: "50.000000"</div>'},'248',''],
    				[{v:'38', f:'RADIO COMERCIALES, S.A. DE C.V.<div style="color:red; font-style:italic">directo: "99.973594"</div><div style="color:blue; font-style:italic">indirecto: "49.986797"</div>'},'70',''],
    				[{v:'66', f:'SERVICIOS RADIOPOLIS, S.A. DE C.V.<div style="color:red; font-style:italic">directo: ".001928"</div><div style="color:blue; font-style:italic">indirecto: "50.000001"</div>'},'38',''],
    				[{v:'68', f:'SERVICIOS XEZZ, S.A DE C.V.<div style="color:red; font-style:italic">directo: ".001938"</div><div style="color:blue; font-style:italic">indirecto: "49.500056"</div>'},'38',''],
    				[{v:'66', f:'SERVICIOS RADIOPOLIS, S.A. DE C.V.<div style="color:red; font-style:italic">directo: "99.998073"</div><div style="color:blue; font-style:italic">indirecto: "50.000001"</div>'},'70',''],
    				[{v:'42', f:'RADIO TELEVISORA DE MEXICALI, S.A. DE C.V.<div style="color:red; font-style:italic">directo: "99.999750"</div><div style="color:blue; font-style:italic">indirecto: "49.999875"</div>'},'70',''],
    				[{v:'146', f:'CADENA RADIODIFUSORA MEXICANA, S.A. DE C.V.<div style="color:red; font-style:italic">directo: "99.999858"</div><div style="color:blue; font-style:italic">indirecto: "50.000036"</div>'},'70',''],
    				[{v:'40', f:'RADIOTAPATIA, S.A. DE C.V.<div style="color:red; font-style:italic">directo: "99.000143"</div><div style="color:blue; font-style:italic">indirecto: "49.500107"</div>'},'146',''],
    				[{v:'39', f:'RADIO MELODIA, S.A. DE C.V.<div style="color:red; font-style:italic">directo: "99.000000"</div><div style="color:blue; font-style:italic">indirecto: "49.500036"</div>'},'146',''],
    				[{v:'313', f:'XEZZ, S.A. DE C.V.<div style="color:red; font-style:italic">directo: "99.000020"</div><div style="color:blue; font-style:italic">indirecto: "49.500046"</div>'},'146',''],
    				[{v:'68', f:'SERVICIOS XEZZ, S.A DE C.V.<div style="color:red; font-style:italic">directo: "99.998063"</div><div style="color:blue; font-style:italic">indirecto: "49.500056"</div>'},'313',''],
    				[{v:'746', f:'FUNDACION TELEVISA, A. C.<div style="color:red; font-style:italic">directo: "50.000000"</div><div style="color:blue; font-style:italic">indirecto: "50.000893"</div>'},'248',''],
    				[{v:'25', f:'PROMO-INDUSTRIAS METROPOLITANAS, S. A. DE  C. V.<div style="color:red; font-style:italic">directo: "99.999955"</div><div style="color:blue; font-style:italic">indirecto: "99.999955"</div>'},'248',''],
    				[{v:'146', f:'CADENA RADIODIFUSORA MEXICANA, S.A. DE C.V.<div style="color:red; font-style:italic">directo: ".000107"</div><div style="color:blue; font-style:italic">indirecto: "50.000036"</div>'},'25',''],
    				[{v:'749', f:'LOMAS CONSULTORES ADMINISTRATIVOS, S.A. DE C.V.<div style="color:red; font-style:italic">directo: "1.000000"</div><div style="color:blue; font-style:italic">indirecto: "1.000000"</div>'},'25',''],
    				[{v:'752', f:'SERVICIOS ADMINISTRATIVOS PROFESIONALES BOSQUES, S.A. DE C.V.<div style="color:red; font-style:italic">directo: "1.960976"</div><div style="color:blue; font-style:italic">indirecto: "1.960975"</div>'},'25',''],
    				[{v:'742', f:'CORPORATIVO BOSQUE DE CANELOS, S.A. DE C.V.<div style="color:red; font-style:italic">directo: "90.000000"</div><div style="color:blue; font-style:italic">indirecto: "11.764874"</div>'},'752',''],
    				[{v:'754', f:'TELEVISA TRANSMEDIA, S.A. DE C.V.<div style="color:red; font-style:italic">directo: ".002000"</div><div style="color:blue; font-style:italic">indirecto: ".002000"</div>'},'25',''],
    				[{v:'736', f:'COISA, CONSULTORES INDUSTRIALES S.A. DE C.V<div style="color:red; font-style:italic">directo: "2.000000"</div><div style="color:blue; font-style:italic">indirecto: "1.999999"</div>'},'25',''],
    				[{v:'753', f:'TELEINMOBILIARIA, S. DE R.L. DE C.V.<div style="color:red; font-style:italic">directo: ".003086"</div><div style="color:blue; font-style:italic">indirecto: ".003086"</div>'},'25',''],
    				[{v:'757', f:'TRUXILLOS PUBLICIDAD, S.A. DE C.V.<div style="color:red; font-style:italic">directo: ".100000"</div><div style="color:blue; font-style:italic">indirecto: ".100000"</div>'},'25',''],
    				[{v:'742', f:'CORPORATIVO BOSQUE DE CANELOS, S.A. DE C.V.<div style="color:red; font-style:italic">directo: "10.000000"</div><div style="color:blue; font-style:italic">indirecto: "11.764874"</div>'},'25',''],
    				[{v:'755', f:'TELEVISA, S.A. DE C.V.<div style="color:red; font-style:italic">directo: ".001785"</div><div style="color:blue; font-style:italic">indirecto: ".001785"</div>'},'25',''],
    				[{v:'746', f:'FUNDACION TELEVISA, A. C.<div style="color:red; font-style:italic">directo: "50.000000"</div><div style="color:blue; font-style:italic">indirecto: "50.000893"</div>'},'755',''],
    				[{v:'759', f:'CORPORATIVO LAZARETTO, S.A. DE C.V.<div style="color:red; font-style:italic">directo: ".099917"</div><div style="color:blue; font-style:italic">indirecto: ".099917"</div>'},'25',''],
    				[{v:'747', f:'GYALI, S.A. DE C.V.<div style="color:red; font-style:italic">directo: ".001775"</div><div style="color:blue; font-style:italic">indirecto: ".001775"</div>'},'25',''],
    				[{v:'740', f:'AQUITANIA CONSULTORES INDUSTRIALES, S.A. DE C.V.<div style="color:red; font-style:italic">directo: "2.000000"</div><div style="color:blue; font-style:italic">indirecto: "1.999999"</div>'},'25',''],
    				[{v:'756', f:'TORALI, S.A. DE C.V.<div style="color:red; font-style:italic">directo: ".004000"</div><div style="color:blue; font-style:italic">indirecto: ".004000"</div>'},'25',''],
    				[{v:'744', f:'CVQ SERIES, S.A. DE C.V.<div style="color:red; font-style:italic">directo: ".000293"</div><div style="color:blue; font-style:italic">indirecto: ".000293"</div>'},'25',''],
    				[{v:'745', f:'ENCUENTRO INDEPENDIENTE, S.A. DE C.V.<div style="color:red; font-style:italic">directo: ".016918"</div><div style="color:blue; font-style:italic">indirecto: ".016918"</div>'},'25',''],
    				[{v:'748', f:'JANA TALENTO, S.A. DE C.V.<div style="color:red; font-style:italic">directo: ".000361"</div><div style="color:blue; font-style:italic">indirecto: ".000361"</div>'},'25','']
        ]);
        */

        <%=data%>

        // Create the chart.
        var chart = new google.visualization.OrgChart(document.getElementById('chart_div'));
        // Draw the chart, setting the allowHtml option to true for the tooltips.
        chart.draw(data, {allowHtml:true});
        //window.print();
        
      }
   </script>
    </head>

<!-- Fin Def PDF -->

<body>

	<table width="100%" height="35px">
		<tr>
			<td width="0%"><img src="<%= imgPath %>LOGO.jpg"></td>
			<td width="10%" <%=titleStyle %>>Derecho Corporativo</td>
			<td width="70%" <%=titleStyle %>>Reporte: Tenencia en Cascada</td>
			<td>
			</td>
		</tr>
	</table>
	<br>

	<%= reportContent %>

	<!-- Inicio PDF -->

	<div id="chart_div"></div>

	<!-- Fin PDF -->

</body>
</html>


<%-- 
</pd4ml:transform>
 --%>
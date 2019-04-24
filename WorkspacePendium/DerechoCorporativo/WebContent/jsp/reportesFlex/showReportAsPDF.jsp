<%@page import="mx.com.televisa.derechocorporativo.modules.reportsFlex.ReportFlex,
				mx.com.televisa.derechocorporativo.bean.SessionBean"%>
<%@ taglib uri="pd4ml" prefix="pd4ml"%><%@page
	contentType="text/html; charset=ISO8859_1"%>
<%
	response.setHeader("Cache-Control","no-cache"); 
	response.setHeader("Pragma","no-cache"); 
	response.setDateHeader ("Expires", -1);
	String repId = request.getParameter("repId");

	int intRepId = Integer.parseInt(repId);

	ReportFlex rep = ReportFlex.getReport(repId);

	
	
		
	//String fileName = "My Report 001.pdf".replace(" ", "_");
	
	String fileName = rep.getNomReporte().replace(" ", "_")+".pdf";
	
	//String reportContent = "<h1>Hello PD4ML</h1>";
	
	SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
	int liIdRol =   Integer.valueOf(sessionBean.getIdRol());
	
	String reportContent = "";
	
	try {
	
		reportContent = ReportFlex.getReportTemplate(intRepId, request, liIdRol);
		
	} catch(Exception ex) {
		
		reportContent = ex.toString();
		ex.printStackTrace();
		
		
	}
	
	
	
	String protocol = request.getScheme(); 			// http
    String serverName = request.getServerName(); 	// hostname.com
    
    int serverPort = request.getServerPort(); 		// 8081
    String contextPath = request.getContextPath(); 	// mywebapp

    //String servletPath = request.getServletPath();   // /servlet/MyServlet
    //String pathInfo = request.getPathInfo();         // /a/b;c=123
    //String queryString = request.getQueryString();          // d=789
    // Reconstruct original requesting URL

    String fullContextPath = protocol + "://" + serverName + ":" + serverPort + contextPath;
    
    //String imgPath = fullContextPath + "/img/";
    String imgPath = "";

    reportContent = reportContent.replace("button_green.png", "<img src='" + imgPath + "button_green_cuadro.png'>");
    reportContent = reportContent.replace("button_red.png", "<img src='" + imgPath + "button_red_cuadro.png'>");
    reportContent = reportContent.replace("button_yellow.png", "<img src='" + imgPath + "button_yellow_cuadro.png'>");
    
    /*reportContent = reportContent.replace("semaforo_green.png", "<img src='" + imgPath + "semaforo_green.png'>");
    reportContent = reportContent.replace("semaforo_red.png", "<img src='" + imgPath + "semaforo_red.png'>");
    reportContent = reportContent.replace("semaforo_yellow.png", "<img src='" + imgPath + "semaforo_yellow.png'>");
    reportContent = reportContent.replace("semaforo_null.png", "<img src='" + imgPath + "semaforo_null.png'>");
	*/
	
    
    reportContent = reportContent.replace("../../img/", imgPath);
    
    
    String titleStyle = "style='color: #3A34A5;text-align: center;font-weight: bold;font-size: 10px;'";
    

    //reportContent = reportContent.replace("class='pageTitle2'", "style='color: white;text-align: center;font-weight: bold;font-size: 12px;background-image: url(\"" + imgPath + "borders/page_border/border02.png\");'");
    reportContent = reportContent.replace("class='pageTitle2'", "style='color: white;text-align: center;font-weight: bold;font-size: 10px;background-image: url(\"" + imgPath + "tableHeaderBack.png\");'");
    //reportContent = reportContent.replace("class='pageTitle2'", "style='background-image:url(\"" + imgPath + "tableHeaderBack.png\"); color: white;'");
    
    reportContent = reportContent.replace("class='tableHeader'", "style='background-image:url(\"" + imgPath + "tableHeaderBack.png\"); color: white;'");    
    reportContent = reportContent.replace("class='flexTable'", "style='border-color: #F5F5F5;border-width: thin;'");
    reportContent = reportContent.replace("class='tableRow2'", "style='background:#DCE8F6;'");
    reportContent = reportContent.replace("h2", "h4");
    reportContent = reportContent.replace("class='refNotaPie'","style='color:red; margin-left: 80px;'");
    reportContent = reportContent.replace("<sup class='superIndice'>","<span style='color: red; vertical-align: super;'>");
    reportContent = reportContent.replace("</sup>","</span>");
    reportContent = reportContent.replace("<pre", "<span");
    reportContent = reportContent.replace("</pre>", "</span>");
    reportContent = reportContent.replace("<th colspan='4'>", "<th colspan='3'>");
%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<pd4ml:transform
	screenWidth="780" pageFormat="LETTER" fileName="<%= fileName %>"
	pageInsets="10,10,10,10,mm" adjustScreenWidth="true"
	enableTableBreaks="false" enableImageSplit="false" debug="true">
	<pd4ml:footer areaHeight="30">

	</pd4ml:footer>


	<html>
<head>
<title>Dynamic Report</title>
<META HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE"> 
<META HTTP-EQUIV="Expires" CONTENT="-1">
<link
	<%-- href="<%= fullContextPath %>/css/pd4ml_report.css"--%>
	href="pd4ml_report.css"
	rel="stylesheet" type="text/css">

<style>
body {
	font-family: Verdana, Arial, Helvetica;
	color: #000000;
	font-size: 10px;
}

td {
	
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
<body>

	<table width="100%" height="35px">
		<tr>
			<%--<td width="20%"><img src="<%= imgPath %>LOGO.jpg"></td> --%>
			<td width="20%"><img src="<%= imgPath %>LogoRepPre.png"></td>
			<td width="10%" <%=titleStyle %>>Derecho Corporativo</td>
			<td width="70%" <%=titleStyle %>>Reporte: <%= rep.getNomReporte() %></td>
			<td>
			</td>
		</tr>
	</table>
	<br>


	<%= reportContent %>

</body>
</html>

</pd4ml:transform>
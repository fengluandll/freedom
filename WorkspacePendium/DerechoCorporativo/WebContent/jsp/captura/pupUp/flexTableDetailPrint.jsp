<%@page
	import="mx.com.televisa.derechocorporativo.modules.flextabs.FlexTable"%>
<%@page import="mx.com.televisa.derechocorporativo.util.*"%>
<%@page import="mx.com.televisa.derechocorporativo.bean.SessionBean"%>

<%@ taglib uri="pd4ml" prefix="pd4ml"%>
<%@page contentType="text/html; charset=ISO8859_1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%

String fileName = "Reporte.pdf";

String protocol = request.getScheme(); 			// http
String serverName = request.getServerName(); 	// hostname.com

int serverPort = request.getServerPort(); 		// 8081
String contextPath = request.getContextPath(); 	// mywebapp

String fullContextPath = protocol + "://" + serverName + ":" + serverPort + contextPath;

%>


<pd4ml:transform screenWidth="780" pageFormat="LETTER"
	fileName="<%= fileName %>" pageInsets="10,10,10,10,mm"
	adjustScreenWidth="true" enableTableBreaks="false"
	enableImageSplit="false" debug="true">
	<pd4ml:footer areaHeight="30">

	</pd4ml:footer>

<html>
	<head>
	<script
		src="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.min.js"></script>
	<link rel="stylesheet" type="text/css"
		href="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.css">
	<%
	
		//ECM 14 Abril 2016 - Agregar empresa como título
		SessionBean sessionBean =  (SessionBean)session.getAttribute("sessionBean");
		String nomEmpresa = sessionBean.getNomEmpresa();
		
		String flexTableId = request.getParameter("flexTableId");
		String rowId =  request.getParameter("rowId");
		
		FlexTable ft = new FlexTable(flexTableId);
	
		ft.atributo12 = "";
		
		String imgPath = "";
	
		String reportContent = ft.toHTMLForm(rowId, null, null); 
		
		reportContent = reportContent.replace("../../img/", imgPath);
		reportContent = reportContent.replace("icons/", imgPath);
	    
		reportContent = reportContent.replace("src='/DerechoCorporativo/img/fleca_abajo_32.png'", "src='" + imgPath + "fleca_abajo_32.png'");
		reportContent = reportContent.replace("src='/DerechoCorporativo/img/fleca_arriba_32.png'", "src='" + imgPath + "fleca_abajo_32.png'");
		
	    reportContent = reportContent.replace("class='tableHeaderAlfa2'", "style='background-image:url(\"" + imgPath + "tableHeaderBack-alfa.png\");color: white;font-weight: bold;text-align: center;color: white;'");
	    
	    reportContent = reportContent.replace("class='tableHeaderAlfa'", "style='background-image:url(\"" + imgPath + "tableHeaderBack-alfa.png\");color: white;font-weight: bold;text-align: center;color: #4320AC;'");
	    
	    reportContent = reportContent.replace("class='tableHeader'", "style='background-image:url(\"" + imgPath + "tableHeaderBack.png\"); color: white;'");
	    
	    reportContent = reportContent.replace("href=","hre=");
	    reportContent = reportContent.replace("&nbsp"," ");
	
	
	%>
	
	<title><%=ft.nomFlex %> - Detalle</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<%@include file="/css/kaz-styles4Print.jsp"%>
	<%@include file="/jsp/captura/part/validaciones.jsp"%>
	</head>
	<body>
		<div>
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				align="center">
				<tr>
					<td colspan="2" align="center"><br>
						<h3>
							<b><%=nomEmpresa%></b>
						</h3> <br></td>
				</tr>
			</table>
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				align="center">
				<tr style="background-image: url('tableHeaderBack.png')">
					<th width="100%" align="center"><font style="color: white;">
							<h3>
								<%=ft.nomFlex %>
								- Detalle
							</h3>
					</font></th>
					<td align="right">
					</td>
				</tr>
				<tr>
					<td colspan="2"><%= reportContent %></td>
				</tr>
			</table>
	
	
	
		</div>
	</body>
</html>
</pd4ml:transform>




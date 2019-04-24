<%@ taglib uri="pd4ml" prefix="pd4ml"%>
<%@page import="mx.com.televisa.derechocorporativo.util.FacesUtils"%>
<pd4ml:transform screenWidth="780" pageFormat="LETTER"
	fileName="ConsultaPoder.pdf" pageInsets="10,10,10,10,mm"
	adjustScreenWidth="true" enableTableBreaks="false"
	enableImageSplit="false" debug="true">
	<pd4ml:footer areaHeight="30">

	</pd4ml:footer>

	<%
		String htmlPestana = (String) application.getAttribute("htmlDetalle");
		htmlPestana = htmlPestana.replace("/DerechoCorporativo/img/", "/img/");
		htmlPestana = htmlPestana.replace("%CF", "&#8226;");
		htmlPestana = htmlPestana.replace("105%", "100%");
		htmlPestana = htmlPestana.replace(" &#8226;     ", "&#8226;");
		htmlPestana = htmlPestana.replace(" &#8226;    ", "&#8226;");
		htmlPestana = htmlPestana.replace(" &#8226;   ", "&#8226;");
		htmlPestana = htmlPestana.replace("&#8226; 	 ", "&#8226;");		
		htmlPestana = htmlPestana.replace("<br />", "<br/>");
		System.out.println(htmlPestana);
	%>
	<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
body {
	font-size: 10px;
	font-family: 'Open Sans', sans-serif;
	width: 100%;
}

.divSeccHead {
	background-color: #2B385D;
	color: #FFF;
	padding: 5px;
	text-align: left;
	cursor: pointer;
	border-bottom: #FFF 2px solid;
	width: 100%;
}

#divPGForm {
	width: 100% !important;
}

.divSeccH {
	font-align: center;
	background-color: #2B385D;
	color: #FFF;
	padding: 5px;
	cursor: cursor;
}

.SubTitleHead {
	font-weight: bold;
}

#tabsPoderes {
	margin-left: 30px;
	margin-right: 30px;
}

tr {
	font-size: 11px;
}

th {
	font-size: 13px;
	font-weight: normal;
	padding: 5px;
	border-left-color: #FFFFFF;
	border-left-style: solid;
	border-left-width: 1px;
}

.fondoA {
	background-color: #F8F8F8 !important;
}

.fondoB {
	background-color: #CCCCCC !important;
}

.fondoAInit {
	background-color: #F8F8F8 !important;
	border-left: #000 solid 5px;
}

.fondoBInit {
	background-color: #CCCCCC !important;
	border-left: #000 solid 5px;
}

.tableHeads {
	background-color: #2B385D;
	color: #FFFFFF;
	font-size: 13px;
}

.oddCeld {
	background-color: #FFFFFF;
	font-size: 11px;
	vertical-align: top;
}

.evenCeld {
	background-color: #DCE8F6;
	font-size: 11px;
	vertical-align: top;
}

.Img_Semaforo_green {
	width: 32px;
	height: 32px;
	background-image: url("/img/semaforo_green.png");
	background-repeat: no-repeat;
	cursor: pointer;
}

.Img_Semaforo_red {
	width: 32px;
	height: 32px;
	background-image: url("/img/semaforo_red.png");
	background-repeat: no-repeat;
	cursor: pointer;
}

.Img_Semaforo_gray {
	width: 32px;
	height: 32px;
	background-image: url("/img/semaforo_gray.png");
	background-repeat: no-repeat;
	cursor: pointer;
}

.Img_Icon_Documentum {
	width: 32px;
	height: 32px;
	background-image: url("/img/icons/List.png");
	background-repeat: no-repeat;
	cursor: pointer;
}

.pageTitle {
	color: white;
	text-align: center;
	font-size: 14px;
}
</style>

</head>
<body>
	<table style="width: 100%; text-align: center;">
		<tr>
			<td>
				<table style="width: 100%; text-align: center;">
					<tr>
						<td>
							<table border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td style="padding-right: 0px"><img
										src="../img/border01_azul.png"></td>
									<td width="100%" background="../img/border02_azul.png"
										style="padding: 0px; color: white; font-size: 16px; text-align: center;">
										<%=FacesUtils.getSessionBean().getNomEmpresa()%>
									</td>
									<!-- open-post-title -->

									<td style="padding-left: 0px"><img
										src="../img/border03_azul.png"></td>
								</tr>
							</table>

						</td>
					</tr>
				</table>

			</td>
		</tr>
		<tr>
			<td><%=htmlPestana%></td>
		</tr>
	</table>


</body>
	</html>
</pd4ml:transform>
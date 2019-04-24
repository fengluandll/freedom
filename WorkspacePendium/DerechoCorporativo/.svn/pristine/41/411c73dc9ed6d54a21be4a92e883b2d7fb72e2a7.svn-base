<%@page import="mx.com.televisa.derechocorporativo.util.StringUtils"%>
<%@page import="mx.com.televisa.derechocorporativo.util.FacesUtils"%>
<%@page import="mx.com.televisa.derechocorporativo.modules.captura.*"%>
<%@page
	import="mx.com.televisa.derechocorporativo.util.ObtenerEmpresaDenominacioActual"%>
<%@ taglib uri="pd4ml" prefix="pd4ml"%>
<%@page contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String fileName = "Reporte.pdf";

	String protocol = request.getScheme(); // http
	String serverName = request.getServerName(); // hostname.com

	int serverPort = request.getServerPort(); // 8081
	String contextPath = request.getContextPath(); // mywebapp

	String fullContextPath = protocol + "://" + serverName + ":"
			+ serverPort + contextPath;
%>
<%--   --%>

<pd4ml:transform screenWidth="960" pageFormat="LETTER"
	fileName="<%=fileName%>" pageInsets="10,0,14,0,mm"
	adjustScreenWidth="true" enableTableBreaks="false"
	enableImageSplit="false" debug="true">
	<pd4ml:footer areaHeight="30">

	</pd4ml:footer>





	<html>
<head>
<!--Estilos de Poderes-->
<style>
body {
	font-size: 13px;
	/*font-family: 'Open Sans', sans-serif;*/
}

.divSeccHead {
	background-color: #2B385D;
	color: #FFF;
	padding: 5px;
	text-align: left;
	cursor: pointer;
	border-bottom: #FFF 2px solid;
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
	margin-left: 0px;
	margin-right: 30px;
}

#PGForm_DlgApoderados_Grid_gapoderSelecc {
	height: 200px;
	overflow-x: auto;
}

#PGForm_DlgApoderados_Div_gapoderSelecc {
	height: 200px;
	overflow-x: auto;
}

#PGForm_DlgApoderadosOtros_Grid_ApSelecc {
	height: 200px;
	overflow-x: auto;
}

tr {
	font-size: 11px;
}

th {
	font-size: 13px;
	font-weight: normal;
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
	background-color: #28385D;
	color: #FFFFFF;
	font-size: 13px;
}

.tableHeads td {
	padding: 5px;
}

.tableHeads tr {
	font-size: 11px;
}

.tableHeads th {
	font-size: 13px;
	font-weight: normal;
	padding: 5px;
	border-left-color: #FFFFFF;
	border-left-style: solid;
	border-left-width: 2px;
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

.Img_Semaforo_red {
	width: 32px;
	height: 32px;
	background-image: url("/img/semaforo_red.png");
	background-repeat: no-repeat;
	cursor: pointer;
}

.Img_Semaforo_green {
	width: 32px;
	height: 32px;
	background-image: url("/img/semaforo_green.png");
	background-repeat: no-repeat;
	cursor: pointer;
}

.Img_Semaforo_yellow {
	width: 32px;
	height: 32px;
	background-image: url("/img/semaforo_yellow.png");
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
	background-image: url("/img/List.png");
	background-repeat: no-repeat;
	cursor: pointer;
}
</style>

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

thead th, thead td {
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




<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<%@include file="/css/kaz-styles4Print.jsp"%>

<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery/jquery-2.1.4.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/ajax/simpleAjaxUtil.js"></script>

<%
	String tabId = request.getParameter("tabId");
		System.out.println("tabId:  " + tabId);
%>

<%
	if (!tabId.equals("*")) {
%>

<script
	src="<%=request.getContextPath()%>/js/SpryAssets/SpryTabbedPanels.js"
	type="text/javascript"></script>
<link
	href="<%=request.getContextPath()%>/js/SpryAssets/SpryTabbedPanels4Print.css"
	rel="stylesheet" type="text/css" />



<%
	}
%>



<script type="text/javascript" src="part/flexTab.js"></script>

<script type="text/javascript" src="part/ajaxPage.js"></script>

<script type="text/javascript" src="part/pupUp.js"></script>

<script type="text/javascript" src="apoderados/apoderados.js"></script>

<script type="text/javascript" src="reformas/reformas.js"></script>



<%
	Pagina pagina = new Pagina();

		if (tabId.equals("*")) {

			pagina.setModo("PRINT_ALL");
		}
		//else if(tabId.equals("24")){
		//Apoderados Impresión nuevo dearrollo.
		//}
		else {

			pagina.setModo("PRINT");
		}

		pagina.useFlexTablesAsHtml = true;
		pagina.request = request;

		String reportContent = "";
		if (tabId.equals("*")) {

			reportContent = pagina.getHTMLLayoutPrintAll();
		}
		//else if(tabId.equals("24")){
		//Apoderados Impresión nuevo dearrollo.
		//apoderadosConsulta.jsp
		//	reportContent = "";
		//}
		else {

			reportContent = pagina.getHTMLLayout(tabId);
		}

		String tabName = pagina.getPrintedTabName();

		//String imgPath = fullContextPath + "/img/";
		String imgPath = "";

		reportContent = reportContent.replace("../../img/icons/",
				imgPath);
		//reportContent = reportContent.replace("\"/DerechoCorporativo/img/\"", "\"" + imgPath + "\"");

		reportContent = reportContent.replace(
				"src='/DerechoCorporativo/img/icons/List.png'", "src='"
						+ imgPath + "List.png'");

		reportContent = reportContent.replace("class='tableHeader'",
				"style='background-image:url(\"" + imgPath
						+ "tableHeaderBack.png\"); color: white;'");

		reportContent = reportContent
				.replace(
						"class='tableHeaderAlfa'",
						"style='background-image:url(\""
								+ imgPath
								+ "tableHeaderBack-alfa.png\");color: white;font-weight: bold;text-align: center;color: white;'");

		reportContent = reportContent.replace("button_green.png",
				"button_green.png");
		reportContent = reportContent.replace("button_red.png",
				"button_red.png");
		reportContent = reportContent.replace("button_yellow.png",
				"button_yellow.png");

		reportContent = reportContent
				.replace(
						"class='pageTitle'",
						"style='color: white;text-align: center;font-weight: bold;font-size: 12px;background-image: url(\""
								+ imgPath + "tableHeaderBack.png\");'");

		reportContent = reportContent.replace("</body>", "");
		reportContent = reportContent.replace("</html>", "");

		reportContent = reportContent.replace("href=", "hre=");
		reportContent = reportContent.replace("colspan='0'",
				"colspan='2'");
		//reportContent = reportContent.replace("&nbsp"," ");
		//reportContent = reportContent.replace(";"," ");

		//reportContent = StringUtils.latin1ToUTF8(reportContent);
%>


</head>
<body onLoad="" class="fontmenu" leftmargin="0" rightmargin="0"
	topmargin="0" marginwidth="0" marginheight="0">

	<%--@include file="part/dialogForm.jsp"--%>

	<form method="post" action="">

		<table width="100%" height="100%" border="0" align="center">
			<tr>
				<td>



					<table border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td><img src="border01_4Print.png"></td>
							<td width="100%"
								style="color: white; text-align: center; font-size: 14px;"
								background="border02_4Print.png">

								<table style="width: 95%">
									<tr>
										<td width=170><span id="idTitle"
											style="color: white; text-align: center; font-size: 14px;">
												<%=FacesUtils.getSessionBean().getNomEmpresa()%> <%
 	if (!tabId.equals("*")) {
 %> - <%
 	}
 %>
										</span></td>
									</tr>
								</table> <!--<jsp:include page="/jsp/components/pages_border4PrintFullContext/open-post-title.jsp"></jsp:include>-->
								<jsp:include page="OpenTitleSinHttps.jsp"></jsp:include>

								<table width="98%" border="0" cellspacing="2" cellpadding="3"
									id="sec_1_1">
									<tr>
										<td width="60%"></td>
										<td width="20%"></td>
										<td width="20%"></td>
									</tr>
									<div id="TabbedPanels1" class="TabbedPanels">
										<ul class="TabbedPanelsTabGroup"></ul>
										<div class="TabbedPanelsContentGroup">
											<div class="TabbedPanelsContent">
												<table width='100%' align='center' border='0'
													id='subSectionTableContainer'>
													<tr>
														<td><fieldset>
																<legend>Estructura de Capital Social</legend>
																<table width='100%' border='0' id='tbl_stat_0'
																	name='tbl_stat_0'>
																	<tr>
																		<td width='17%'></td>
																		<td width='8%'></td>
																		<td width='8%'></td>
																		<td width='17%'></td>
																		<td width='17%'></td>
																		<td width='8%'></td>
																		<td width='8%'></td>
																		<td width='17%'></td>
																	</tr>
																	<tr>
																		<td colspan='8'></td>
																	</tr>
																	<tr bgcolor=''>
																		<td colspan='2'>Capital Fijo o Mínimo</td>
																		<td colspan='6'><div
																				style='text-align: right; width: 15%;'>
																				$2,223,310.00
																				<div></td>
																	</tr>
																	<tr bgcolor=''>
																		<td colspan='2'>Capital Variable</td>
																		<td colspan='6'><div
																				style='text-align: right; width: 15%;'>
																				$2,222,470.00
																				<div></td>
																	</tr>
																	<tr bgcolor=''>
																		<td colspan='2'>Capital Social:</td>
																		<td colspan='6'><div
																				style='text-align: right; width: 15%;'>
																				$4,445,780.00
																				<div></td>
																	</tr>
																	<tr bgcolor=''>
																		<td colspan='2'>Semáforo:</td>
																		<td colspan='2'>VERDE<img id='im'
																			src='../../img/button_green.png' title='Verde'></td>
																		<td colspan='2'><div id='nom_C1022'>
																				<b></b></td>
																		<td colspan='2'></td>
																	</tr>
																	<tr bgcolor=''></tr>
																	<tr bgcolor=''></tr>
																	<tr bgcolor=''></tr>
																	<tr bgcolor=''></tr>
																	<tr bgcolor=''></tr>
																	<tr bgcolor=''></tr>
																	<tr bgcolor=''></tr>
																	<tr bgcolor=''></tr>
																	<tr bgcolor=''></tr>
																	<tr bgcolor=''>
																		<td colspan='2'>Estructura al</td>
																		<td colspan='2'><div id='nom2_C1033'>24/01/2017</div></td>
																	</tr>
																	<tr bgcolor=''>
																		<td colspan='8'><table width='100%'
																				align='center' class='flexTable' border='0'
																				cellspacing='3' cellpadding='3'>
																				<tr>
																					<th colspan='7'></th>
																					<td align='center'><a
																						onClick="closeForm();sleep(2000);setFlexTableId('7');loadFlexTableForm('7-0');"></a></td>
																				</tr>
																				<tr>
																					<th
																						style='background-image: url("tableHeaderBack.png"); color: white;'
																						width='5%'>Grupo</th>
																					<th
																						style='background-image: url("tableHeaderBack.png"); color: white;'
																						width='45%'>Accionistas</th>
																					<th
																						style='background-image: url("tableHeaderBack.png"); color: white;'
																						width='15%'>Acciones </br> Capital Fijo
																					</th>
																					<th
																						style='background-image: url("tableHeaderBack.png"); color: white;'
																						width='15%'>Acciones </br> Capital Variable
																					</th>
																					<th
																						style='background-image: url("tableHeaderBack.png"); color: white;'
																						width='5%'>%</th>
																					<th
																						style='background-image: url("tableHeaderBack.png"); color: white;'
																						width='15%'>Valor</th>
																				</tr>
																				<tr class="tableRow2">
																					<td rowspan='1'
																						style='background-image: url("tableHeaderBack-alfa.png"); color: white; font-weight: bold; text-align: center; color: white;'
																						align='null'>4</td>
																					<td align='null'>ANDRÉS VARELA ENTRECANALES</td>
																					<td align='right'>222,222</td>
																					<td align='right'>222,222</td>
																					<td align='right'>99.969859</td>
																					<td align='right'>$ 4,444,440.00</td>
																				</tr>
																				<tr>
																					<td rowspan='4'
																						style='background-image: url("tableHeaderBack-alfa.png"); color: white; font-weight: bold; text-align: center; color: white;'
																						align='null'>&nbsp;</td>
																					<td align='null'>ABC 123 &amp; ENTERPRICE</td>
																					<td align='right'>73</td>
																					<td align='right'>23</td>
																					<td align='right'>.021594</td>
																					<td align='right'>$ 960.00</td>
																				</tr>
																				<tr class="tableRow2">
																					<td align='null'>APOYO TELEFÓNICO CABLEMÁS,
																						S.A. DE C.V.</td>
																					<td align='right'>2</td>
																					<td align='right'>0</td>
																					<td align='right'>.000450</td>
																					<td align='right'>$ 20.00</td>
																				</tr>
																				<tr>
																					<td align='null'>APOYO TELEFÓNICO CABLEMÁS,
																						S.A. DE C.V.</td>
																					<td align='right'>0</td>
																					<td align='right'>0</td>
																					<td align='right'>.000000</td>
																					<td align='right'>$ 0.00</td>
																				</tr>
																				<tr class="tableRow2">
																					<td align='null'>APOYO TELEFÓNICO CABLEMÁS,
																						S.A. DE C.V.</td>
																					<td align='right'>34</td>
																					<td align='right'>2</td>
																					<td align='right'>.008098</td>
																					<td align='right'>$ 360.00</td>
																				</tr>
																				<tr>
																					<td colspan='6'></td>
																				</tr>
																				<tr>
																					</th>
																					<th></th>
																					<th
																						style='background-image: url("tableHeaderBack.png"); color: white;'
																						align='right'>Total:&nbsp;&nbsp;</th>
																					<th align='right'>222,331</th>
																					<th align='right'>222,247</th>
																					<th align='center'>100%</th>
																					<th align='right'>$ 4,445,780.00</th>
																				</tr>
																			</table>
																			<br>
																		<br>Valores en USD</td>
																	</tr>
																	<tr>
																		<td>&nbsp;</td>
																		<td>&nbsp;</td>
																	</tr>
																</table>
															</fieldset></td>
													</tr>
												</table>
											</div>
										</div>
									</div>
									<tr>
										<td colspan="3"></td>
									</tr>
								</table> <script type="text/javascript">
									var TabbedPanels1 = new Spry.Widget.TabbedPanels(
											"TabbedPanels1");
								</script> <jsp:include
									page="/jsp/components/pages_border4PrintFullContext/close.jsp"></jsp:include>

							</td>
						</tr>
					</table>
				</td>
			</tr>

		</table>
	</form>

</body>
	</html>

	<%-- --%>

</pd4ml:transform>


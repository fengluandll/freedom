<%@page import="mx.com.televisa.derechocorporativo.model.CatalogElementV2"%>
<%@page import="mx.com.televisa.derechocorporativo.modules.reports.apoderados.RepApoderados"%>
<%@page import="mx.com.televisa.derechocorporativo.data.Record"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.lang.reflect.Array"%>
<%@page import="mx.com.televisa.derechocorporativo.model.Catalog"%>
<%@page import="mx.com.televisa.derechocorporativo.model.CatalogElement"%>
<%@page import="java.util.List"%>
<%@page import="mx.com.televisa.derechocorporativo.modules.reports.tenecascada.TenenciaCascada"%>
<%@page import="mx.com.televisa.derechocorporativo.components.JSCal"%>    
<%@page import="mx.com.televisa.derechocorporativo.model.DetailApoderados"%>
<%@page import="mx.com.televisa.derechocorporativo.util.FacesUtils"%>

<%@ taglib uri="pd4ml" prefix="pd4ml"%>
<%@page contentType="text/html; charset=ISO8859_1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	
	String fileName = "Reporte_Apoderados.pdf";
	
	
	String protocol = request.getScheme(); 			// http
	String serverName = request.getServerName(); 	// hostname.com
	
	int serverPort = request.getServerPort(); 		// 8081
	String contextPath = request.getContextPath(); 	// mywebapp
	
	//String fullContextPath = protocol + "://" + serverName + ":" + serverPort + contextPath;


%>

<pd4ml:transform
	screenWidth="780" pageFormat="LETTER" fileName="<%= fileName %>"
	pageInsets="10,10,10,10,mm" adjustScreenWidth="true"
	enableTableBreaks="false" enableImageSplit="false" debug="true">
	<pd4ml:footer areaHeight="30">

	</pd4ml:footer>
	
<html>
<head>
<%@include file="/css/kaz-styles.jsp"%>


<title>Insert title here</title>
<%@include file="/css/kaz-styles4PrintSmall.jsp"%>
<link
	<%-- href="<%= fullContextPath %>/css/pd4ml_report.css"--%>
	href="pd4ml_report.css"
	rel="stylesheet" type="text/css">

<style>

.id_revocacion{
	color:red;
}

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
<body onload="">

		
			<jsp:include page="open-pre-title.jsp"></jsp:include>
			
				Reporte de Apoderados
			
			<jsp:include page="open-post-title.jsp"></jsp:include>
			
<form action="" method="post">

<table width="100%">
	<tr>
		<td colspan="3">

<%

	request.setCharacterEncoding("UTF-8");

	String btnEjecutar = request.getParameter("btnEjecutar"); 
	
	String hiddenEmpresa = 	(request.getParameter("hiddenEmpresa") != null) ? request.getParameter("hiddenEmpresa") : "";
	String hiddenTipoPoder = 	(request.getParameter("hiddenTipoPoder") != null) ? request.getParameter("hiddenTipoPoder") : "";
	
	String txtEscritura = (request.getParameter("txtEscritura") != null) ? request.getParameter("txtEscritura") : "";
	
	String hiddenApoderados = (request.getParameter("hiddenApoderados") != null) ? request.getParameter("hiddenApoderados") : "";
	
	String hiddenPoder = (request.getParameter("hiddenPoder") != null) ? request.getParameter("hiddenPoder") : "";
	
	
	
	ArrayList<Record> records = null;
	
	if(btnEjecutar != null && btnEjecutar.equals("Generar")) {
		
		records = RepApoderados.getEmpresas(hiddenEmpresa, hiddenApoderados, hiddenPoder);
	}			
	
	
	String showMode = "TREE";
	
	
%>


		</td>
	</tr>	
	<tr>
		<td colspan="3">
			<table width="100%">
				<tr>
					<td width="5%"></td>
					<td width="90%">Empresa(s):</td>
				<tr>
					<td></td>
					<td>
					<%--<td width="70%">  --%>
						
						<%= (request.getParameter("txtEmpresa")!=null)? new String(request.getParameter("txtEmpresa").getBytes("ISO-8859-1"),"UTF-8") : "" %>
						
										
					</td>
				</tr>
				<tr>
					<td width="5%"></td>
					<td width="90%">Tipo de Poder:</td>
					<%--<td width="70%">  --%>
				<tr>
					<td></td>
					<td>
						
						<%= (request.getParameter("txtTipoPoder")!=null)? request.getParameter("txtTipoPoder") : ""%>
										
					</td>
				</tr>
				<tr>
					<td></td>
					<td>Escritura:</td>
				<tr>
					<td></td>
					<td>
						<%= (request.getParameter("txtEscritura")!=null)? request.getParameter("txtEscritura") : ""%>
					</td>
			    </tr>
			    <tr>
					<td></td>
					<td>Poder:</td>
				</tr>
				<tr>
					<td></td>	
					<td>

						<%= (request.getParameter("txtPoder")!=null)? new String(request.getParameter("txtPoder").getBytes("ISO-8859-1"),"UTF-8") : ""%>
						
										
					</td>
				</tr>
				<tr>
					<td></td>
					<td>Nombres de Apoderados:</td>
				</tr>
				<tr>
					<td></td>
					<td>
						
						<%= (request.getParameter("txtApoderados")!=null)? new String(request.getParameter("txtApoderados").getBytes("ISO-8859-1"),"UTF-8") : ""%>
						
						
					</td>
			    </tr>
			    <tr>
					<td></td>
					<td></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td colspan="3"><hr></td>
	</tr>
	<tr>
		<td colspan="3">
			<br>
		</td>
	</tr>
	<tr>
		<td colspan="3" align="left">
	
				<%
				if(records != null) {
					
					if(showMode.equals("TABLE")){
						
						//
						// YA NO APLICA
						//
						
				%>
				
					
			<table width="100%">
				<tr>
					<%--<th style="background-image:url('<%=fullContextPath%>/img/tableHeaderBack.png');color: white;">  --%>
					<th style="background-image:url('tableHeaderBack.png');color: white;">
					Empresa
					</th>
					<%--<th style="background-image:url('<%=fullContextPath%>/img/tableHeaderBack.png');color: white;">  --%>
					<th style="background-image:url('tableHeaderBack.png');color: white;">
					Escritura
					</th>
					<%--<th style="background-image:url('<%=fullContextPath%>/img/tableHeaderBack.png');color: white;">  --%>
					<th style="background-image:url('tableHeaderBack.png');color: white;">
					Tipo de Poder
					</th>
					<%--<th style="background-image:url('<%=fullContextPath%>/img/tableHeaderBack.png');color: white;">  --%>
					<th style="background-image:url('tableHeaderBack.png');color: white;">
					Grupo
					</th>
					<%--<th style="background-image:url('<%=fullContextPath%>/img/tableHeaderBack.png');color: white;">  --%>
					<th style="background-image:url('tableHeaderBack.png');color: white;">
					Nombre Apoderado
					</th>
					<%--<th style="background-image:url('<%=fullContextPath%>/img/tableHeaderBack.png');color: white;">  --%>
					<th style="background-image:url('tableHeaderBack.png');color: white;">
					Concepto Poder
					</th>
					<%--<th style="background-image:url('<%=fullContextPath%>/img/tableHeaderBack.png');color: white;">  --%>
					<th style="background-image:url('tableHeaderBack.png');color: white;">
					Poder
					</th>
				</tr>
				
				<%
					for(Record rec : records) {
				%>
				<tr>
					<td>
						<%= rec.get("NOM_EMPRESA") %>
					</td>
					<td>
						<%= rec.get("DES_ESCRITURA") %>
					</td>
					<td>
						<%= rec.get("TIPO_PODER") %>
					</td>
					<td>
						<%= rec.get("DES_GRUPO") %>
					</td>
					<td>
						<%= rec.get("NOMBRE_APODERADO") %>
					</td>
					<td>
						<%= rec.get("CONCEPTO_PODER") %>
					</td>
					<td>
						<%= rec.get("DESCRIPCION_PODER") %>
					</td>					
				</tr>
				
				
				<%
					}
				%>
			</table>
				<%
				} if(showMode.equals("TREE")) {
				%>
				
				
				
				<%
				
					String lastEmpresa = "";
					String lastEscritura = "";
					String lastTipoPoder = "";
					String lastGrupo = "";
					String lastNombre = "";
					String lastConcepto = "";
					String lastPoder = "";
				
					for(Record rec : records) {
				%>
					
					<%
						if(!lastEmpresa.equals(rec.get("NOM_EMPRESA"))) {
							
							lastEmpresa = rec.get("NOM_EMPRESA");
						
							
							int intIdEmpresa = Integer.parseInt(rec.get("ID_EMPRESA"));
							
							FacesUtils.getSessionBean().setIdCurrentEmpresa(rec.get("ID_EMPRESA"));
							
						    List<CatalogElementV2> elemsEscrituras = new Catalog().dameEscrituras(intIdEmpresa);
							
						%>
							
							
							<table width="100%">
							<tr>
							<%--<th  style="background-image:url('<%=fullContextPath%>/img/tableHeaderBack.png');color: white;"> --%>
							<th  style="background-image:url('tableHeaderBack.png');color: white;">
							<h3>
							<%= rec.get("NOM_EMPRESA") %>
							</h3>
							</th>
							</tr>
							</table>
							
							
							
							
							<% for (CatalogElementV2 elemEscritura : elemsEscrituras) {
	
									
									//String escritura = elemEscritura.getValValor();
						 			String escritura = elemEscritura.getValC4();
						 			
						 			String idGenerales 	= "12290";
						 			String idEspeciales = "12291";
						 			
						 			int countGenerales = DetailApoderados.getCountApoderados(rec.get("ID_EMPRESA"), idGenerales, escritura);
						 			int countEspeciales = DetailApoderados.getCountApoderados(rec.get("ID_EMPRESA"), idEspeciales, escritura);
						 			
						 			boolean showPodGen = (hiddenTipoPoder.equals("") || hiddenTipoPoder.equals("12290") || hiddenTipoPoder.contains(",")) && countGenerales > 0;
						 			boolean showPodEsp = (hiddenTipoPoder.equals("") || hiddenTipoPoder.equals("12291") || hiddenTipoPoder.contains(",")) && countEspeciales > 0;
						 			
									if(elemEscritura.getValC4() != null
										&& elemEscritura.getValC4().replace(",", "").contains(txtEscritura.replace(",", ""))
										&& (showPodGen|| showPodEsp)
										){
								
									FacesUtils.getSessionBean().setEscrituraApo(elemEscritura.getValC4());
									//boolean existRowsG = true;
									//boolean existRowsE = true;
									//-------------------------EMPIEZA PARA OCULTAR PODERES GENERALES
									String myParamApoderadosG = (request.getParameter("hiddenApoderados") != null) ? request.getParameter("hiddenApoderados") : "";
									//System.out.println("Argu myParamApoderadosG "+myParamApoderadosG);
									ArrayList<String> apoderadoNamesG = new ArrayList<String>();
									String[] idsG = mx.com.televisa.derechocorporativo.util.StringUtils.split(myParamApoderadosG, ",");
									
									for(String id : idsG) {
										
										String name = Catalog.getCatalogElementDescription(id);
										if(name != null) {
											apoderadoNamesG.add(name);
										}
									}
									
									
									String myParamPoderG = (request.getParameter("hiddenPoder") != null) ? request.getParameter("hiddenPoder") : "";
									//System.out.println("Argu myParamPoderG "+myParamPoderG);
									
									ArrayList<String> poderesNamesG = new ArrayList<String>();
									
									if(myParamPoderG != null && !myParamPoderG.equals("")) {
										String[] p_ids = mx.com.televisa.derechocorporativo.util.StringUtils.split(myParamPoderG, ",");
										
										for(String p_id : p_ids) {
											//System.out.println("Argu p_id "+p_id);
											poderesNamesG.add(Catalog.getCatalogElementDescription(p_id));	
										}
									}
									
								
									boolean alterClassG = false;
									String trStyleG = "";
							
									String idEmpresaG 		= FacesUtils.getSessionBean().getIdCurrentEmpresa();
									String lstNumTipoPoderG 	= "12290";
									String lstEscrituraG		=	FacesUtils.getSessionBean().getEscrituraApo();
									String idCatalogoPoderes = (String)session.getAttribute("idCatalogoPoderes");
									if(hiddenPoder.equals("")){
										idCatalogoPoderes = "0";
									}
									
									List<mx.com.televisa.derechocorporativo.model.DetailApoderadosDTO> elemsG = null;
									if(idCatalogoPoderes.equals("0")){
										elemsG = new DetailApoderados().getApoderadosReporteDefaultRev(idEmpresaG, lstNumTipoPoderG, lstEscrituraG,idCatalogoPoderes);
									}else{
										elemsG = new DetailApoderados().getApoderadosReporteRev(idEmpresaG, lstNumTipoPoderG, lstEscrituraG,idCatalogoPoderes);	
									}
									
									
									/*String lstNumTipoPoderG 	= "12290";
									String lstEscrituraG		=	elemEscritura.getValValor();
									List<DetailApoderadosDTO> elemsG = new DetailApoderados().getApoderados(String.valueOf(intIdEmpresa), lstNumTipoPoderG, lstEscrituraG);
									*/
									boolean existRowsG = false;
									
									for (mx.com.televisa.derechocorporativo.model.DetailApoderadosDTO elem : elemsG) {
										
										boolean showRow = false;
										
										
										if(apoderadoNamesG.size() == 0) {
											
											showRow = true;
											existRowsG = true;
										}
										
										for(String apoderadoName : apoderadoNamesG){
											if(mx.com.televisa.derechocorporativo.util.TextFormatter.removeAccents(elem.getApoderados().trim()).contains(mx.com.televisa.derechocorporativo.util.TextFormatter.removeAccents(apoderadoName.trim()))) {
												
												showRow = true;
												existRowsG = true;
											}
										}
									}
		//----------------------------------------------TERMINA PARA OCULTAR PODERES GENERALES---------------------------------------------------------------------
		
		
		//-----------------------------INICIA OCULTA PODERES ESPECIALES--------------------------------------
		
		String myParamApoderadosE = (request.getParameter("hiddenApoderados") != null) ? request.getParameter("hiddenApoderados") : "";
		
		ArrayList<String> apoderadoNamesE = new ArrayList<String>();
		String[] idsE = mx.com.televisa.derechocorporativo.util.StringUtils.split(myParamApoderadosE, ",");
		
		for(String id : idsE) {
			
			//apoderadoNames.add(Catalog.getCatalogElementDescription(id));
			String name = Catalog.getCatalogElementDescription(id);
			if(name != null) {
				apoderadoNamesE.add(name);
			}

			
		}
		
		
		String myParamPoderE = (request.getParameter("hiddenPoder") != null) ? request.getParameter("hiddenPoder") : "";
		
		
		ArrayList<String> poderesNamesE = new ArrayList<String>();
		
		if(myParamPoderE != null && !myParamPoderE.equals("")) {
			String[] p_ids = mx.com.televisa.derechocorporativo.util.StringUtils.split(myParamPoderE, ",");
			
			for(String p_id : p_ids) {
				
				poderesNamesE.add(Catalog.getCatalogElementDescription(p_id));	
			}
		}
		

		boolean alterClassE = false;
		String trStyleE = "";

		String idEmpresaE = FacesUtils.getSessionBean().getIdCurrentEmpresa();
		String lstNumTipoPoderE = "12291";
		String lstEscrituraE		=	FacesUtils.getSessionBean().getEscrituraApo();
		if(hiddenPoder.equals("")){
			idCatalogoPoderes = "0";
		}
		List<mx.com.televisa.derechocorporativo.model.DetailApoderadosDTO> elemsE = null;
		if(idCatalogoPoderes.equals("0")){
			elemsE = new DetailApoderados().getApoderadosReporteDefaultRev(idEmpresaE, lstNumTipoPoderE, lstEscrituraE,idCatalogoPoderes);
		}else{
			elemsE = new DetailApoderados().getApoderadosReporteRev(idEmpresaE, lstNumTipoPoderE, lstEscrituraE,idCatalogoPoderes);	
		}	
		
		
		

		boolean existRowsE = false;
		
		
		
		for (mx.com.televisa.derechocorporativo.model.DetailApoderadosDTO elem : elemsE) {
			
			

			boolean showRowE = false;
			
			
			String grupoE = elem.getApoderados().substring(0, elem.getApoderados().indexOf("</b>")) + "</b><br><br>";
			String foundApoderadosE = "";
			
			
	
			if(apoderadoNamesE.size() == 0) {
				showRowE = true;
				existRowsE = true;
				
				grupoE = "";
				foundApoderadosE = elem.getApoderados(); 
			}
	
	
			for(String apoderadoName : apoderadoNamesE){
			
				if(mx.com.televisa.derechocorporativo.util.TextFormatter.removeAccents(elem.getApoderados().trim()).contains(mx.com.televisa.derechocorporativo.util.TextFormatter.removeAccents(apoderadoName.trim()))) {
					
					showRowE = true;
					existRowsE = true;
					
					foundApoderadosE += "<li>" + apoderadoName + "</li>";
				}
			}
		}
		//-----------------------------TERMINA OCULTA PODERES ESPECIALES--------------------------------------	
								
								%>
									
									<br>
									<b>
									 <%if(existRowsG || existRowsE){ %>
									<span style="font-size: 10px">
							    	<%-- Escritura: ECM <%= elemEscritura.getValValor() %> --%>
							    	Escritura: <%= elemEscritura.getValValor() %> -  <%= RepApoderados.getInfoEscrituras(rec.get("ID_EMPRESA"), escritura) %>
							    	
							    	</span>
									</b>
									
									
									<div align="right">
									<% if((hiddenTipoPoder.equals("") || hiddenTipoPoder.equals("12290") || hiddenTipoPoder.contains(","))  && countGenerales > 0) { %>
									<%if(existRowsG){ %>
									<table width='100%' cellspacing='3' cellpadding='3' border=0 class='flexTable'>
									<%--
									<tr>
										<td align='left' colspan=6 	class='tableRow3'><b>ESCRITURA: <%=elemEscritura.getValValor()%></b></td>
									</tr>
									 --%>
									<tr>
										<td align="left" colspan=6 class='tableRow4'><b>PODERES GENERALES</b></td>
									</tr>
									<tr>
										<%	//String idCatalogoPoderes = (String)session.getAttribute("idCatalogoPoderes");
									
									
									if(idCatalogoPoderes == null || idCatalogoPoderes.equals("") || hiddenPoder.equals("")){
										idCatalogoPoderes = "33,34,35,36,37";
									}
										String[] arrayPoderes = idCatalogoPoderes.split(",");
								    	String lstDominio 	= "";
								    	String lstAdmin 	= "";
								    	String lstCredito 	= "";
								    	String lstPleito 	= "";
								    	String lstEspecial 	= "";
								    	for(String str : arrayPoderes){
								    		if(str.equals("33")){
								    			lstDominio = str;
								    		}else if(str.equals("34")){
								    			lstAdmin = str;
								    		}else if(str.equals("35")){
								    			lstCredito = str;
								    		}else if(str.equals("36")){
								    			lstPleito = str;
								    		}else if(str.equals("37")){
								    			lstEspecial = str;
								    		}
								    	}
								    	%>
										<%--<th style="background-image:url('<%=fullContextPath%>/img/tableHeaderBack.png');color: white;"> --%>
										<th style="background-image:url('tableHeaderBack.png');color: white;">
										APODERADOS</th>
										<%if(lstDominio.equals("33")){ %>
										<%--<th style="background-image:url('<%=fullContextPath%>/img/tableHeaderBack.png');color: white;"> --%>
										<th style="background-image:url('tableHeaderBack.png');color: white;">
										ACTOS DE DOMINIO</th>
										<%} %>
										<%if(lstAdmin.equals("34")){ %>
										<%--<th style="background-image:url('<%=fullContextPath%>/img/tableHeaderBack.png');color: white;"> --%>
										<th style="background-image:url('tableHeaderBack.png');color: white;">
										ACTOS DE ADMINISTRACION</th>
										<%} %>
										<%if(lstCredito.equals("35")){ %>
										<%--<th style="background-image:url('<%=fullContextPath%>/img/tableHeaderBack.png');color: white;"> --%>
										<th style="background-image:url('tableHeaderBack.png');color: white;">
										TITULOS DE CREDITO</th>
										<%} %>
										<%if(lstPleito.equals("36")){ %>
										<%--<th style="background-image:url('<%=fullContextPath%>/img/tableHeaderBack.png');color: white;"> --%>
										<th style="background-image:url('tableHeaderBack.png');color: white;">
										PLEITOS Y COBRANZAS</th>
										<%} %>
										<%if(lstEspecial.equals("37")){ %>
										<%--<th style="background-image:url('<%=fullContextPath%>/img/tableHeaderBack.png');color: white;"> --%>
										<th style="background-image:url('tableHeaderBack.png');color: white;">
										PODER ESPECIAL</th>
										<%} %>
									</tr>
										
										<jsp:include page="DetailApoderadosRepRev.jsp">
											<jsp:param value="<%=countGenerales%>" name="countGenerales"/>
										</jsp:include>
										
									<tr>
										<td colspan="6">
											<%String idEmpresa 		= FacesUtils.getSessionBean().getIdCurrentEmpresa(); %>
											<%=new DetailApoderados().getRevocadosFinal(idEmpresa,"12290",FacesUtils.getSessionBean().getEscrituraApo()) %>	
										</td>
									</tr>
									</table>
									<%} %>
									<br><br><br>
									<%}
									  } %>
									
									<% if((hiddenTipoPoder.equals("") || hiddenTipoPoder.equals("12291") || hiddenTipoPoder.contains(",")) && countEspeciales > 0) { %>
									<%if(existRowsE){ %>
									<table width='100%' cellspacing='3' cellpadding='3' border=0 class='flexTable'>
									<tr>
										<td align='left' colspan=6 class='tableRow4'><b>PODERES ESPECIALES</b></td>
									</tr>
									<tr>
										<%	//String idCatalogoPoderes = (String)session.getAttribute("idCatalogoPoderes");
											if(idCatalogoPoderes == null || idCatalogoPoderes.equals("")  || hiddenPoder.equals("")){
												idCatalogoPoderes = "33,34,35,36,37";
											}
											//System.out.println("Argu1.1 idCatalogoPoderes "+idCatalogoPoderes);
												String[] arrayPoderes = idCatalogoPoderes.split(",");
										    	String lstDominio 	= "";
										    	String lstAdmin 	= "";
										    	String lstCredito 	= "";
										    	String lstPleito 	= "";
										    	String lstEspecial 	= "";
										    	for(String str : arrayPoderes){
										    		if(str.equals("33")){
										    			lstDominio = str;
										    		}else if(str.equals("34")){
										    			lstAdmin = str;
										    		}else if(str.equals("35")){
										    			lstCredito = str;
										    		}else if(str.equals("36")){
										    			lstPleito = str;
										    		}else if(str.equals("37")){
										    			lstEspecial = str;
										    		}
										    	}
										    	%>
										<%--<th style="background-image:url('<%=fullContextPath%>/img/tableHeaderBack.png');color: white;"> --%>
										<th style="background-image:url('tableHeaderBack.png');color: white;">
										APODERADOS</th>
								<%if(lstDominio.equals("33")){ %>
									<%--<th style="background-image:url('<%=fullContextPath%>/img/tableHeaderBack.png');color: white;"> --%>
									<th style="background-image:url('tableHeaderBack.png');color: white;">
									ACTOS DE DOMINIO</th>
								<%} %>
								<%if(lstAdmin.equals("34")){ %>
									<%--<th style="background-image:url('<%=fullContextPath%>/img/tableHeaderBack.png');color: white;"> --%>
									<th style="background-image:url('tableHeaderBack.png');color: white;">
									ACTOS DE ADMINISTRACION</th>
								<%} %>
								<%if(lstCredito.equals("35")){ %>
									<%--<th style="background-image:url('<%=fullContextPath%>/img/tableHeaderBack.png');color: white;"> --%>
									<th style="background-image:url('tableHeaderBack.png');color: white;">
									TITULOS DE CREDITO</th>
								<%} %>
								<%if(lstPleito.equals("36")){ %>
									<%--<th style="background-image:url('<%=fullContextPath%>/img/tableHeaderBack.png');color: white;"> --%>
									<th style="background-image:url('tableHeaderBack.png');color: white;">
									PLEITOS Y COBRANZAS</th>
								<%} %>
								<%if(lstEspecial.equals("37")){ %>
									<%--<th style="background-image:url('<%=fullContextPath%>/img/tableHeaderBack.png');color: white;"> --%>
									<th style="background-image:url('tableHeaderBack.png');color: white;">
									PODER ESPECIAL</th>
								<%} %>
										
										
									</tr>
										
										<!-- %@include file="DetailApoderadosEsp.jsp"%> -->
										<jsp:include page="/jsp/reportes/apoderados/apoderadosRevocados/DetailApoderadosEspRepRev.jsp">
											<jsp:param name="isRevocado" value="si"/>
										</jsp:include>
										<tr>
											<td colspan="6">
												<%String idEmpresa 		= FacesUtils.getSessionBean().getIdCurrentEmpresa(); %>
												<%=new DetailApoderados().getRevocadosFinal(idEmpresa,"12291",FacesUtils.getSessionBean().getEscrituraApo())%>	
											</td>
										</tr>	
									</table>
									<%}
										} %>
									</div>
									
									
									
									
							    <%}%>
						    <% }%>
									
							
							
						<%	
						}
					%>
				
					
					
					
					
					
				
				
				<%
				}
				 %>
				 
				 <br>
				<%
				}
				 %>
			
			
				
				<%
				} else {
					
					%>
					
				<!-- No se encontraron registros -->	
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
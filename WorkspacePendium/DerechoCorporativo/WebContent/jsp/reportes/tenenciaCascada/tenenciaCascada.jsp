<%@page import="mx.com.televisa.derechocorporativo.modules.reports.tenecascada.TenCascRecord"%>
<%@page import="java.util.ArrayList"%>
<%@page import="mx.com.televisa.derechocorporativo.util.StringUtils"%>
<%@page import="mx.com.televisa.derechocorporativo.model.Catalog"%>
<%@page import="mx.com.televisa.derechocorporativo.model.CatalogElement"%>
<%@page import="java.util.List"%>
<%@page import="mx.com.televisa.derechocorporativo.modules.reports.tenecascada.TenenciaCascada"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@page import="mx.com.televisa.derechocorporativo.components.JSCal"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>


<link rel="stylesheet" href='<c:out value="${applicationBean.contextPath}"/>/js/webix/webixCustom.css' type="text/css" charset="utf-8">
<script src='<c:out value="${applicationBean.contextPath}"/>/js/webix/webix.js' type="text/javascript" charset="utf-8"></script>

<%@include file="/css/kaz-styles.jsp"%>
<script type="text/javascript"
	src='<c:out value="${applicationBean.contextPath}"/>/js/jquery/jquery-2.1.4.min.js'></script>
<%@include file="/jsp/components/calendar/include_calendar.jsp"%>

<script type="text/javascript"
	src='<c:out value="${applicationBean.contextPath}"/>/js/ajax/simpleAjaxUtil.js'></script>

<script type="text/javascript"
	src='<c:out value="${applicationBean.contextPath}"/>/js/d3Grapho/d3.min.js'></script>

<link rel="stylesheet"
	href='<c:out value="${applicationBean.contextPath}"/>/js/jquery-treetable/jquery.treetable.css' />
<link rel="stylesheet"
	href='<c:out value="${applicationBean.contextPath}"/>/js/jquery-treetable/jquery.treetable.theme.default.css' />
<script
	src='<c:out value="${applicationBean.contextPath}"/>/js/jquery-treetable/jquery.treetable.js'
	type="text/javascript"></script>

<script type="text/javascript"
	src='<c:out value="${applicationBean.contextPath}"/>/js/basicPrimitivesOrgChart/js/jquery/jquery-ui-1.10.2.custom.min.js'></script>
<link
	href='<c:out value="${applicationBean.contextPath}"/>/js/basicPrimitivesOrgChart/js/jquery/ui-lightness/jquery-ui-1.10.2.custom.css'
	media="screen" rel="stylesheet" type="text/css" />

<!-- jQuery UI Layout -->
<script type="text/javascript"
	src='<c:out value="${applicationBean.contextPath}"/>/js/basicPrimitivesOrgChart/jquerylayout/jquery.layout-latest.min.js'></script>
<link rel="stylesheet" type="text/css"
	href='<c:out value="${applicationBean.contextPath}"/>/js/basicPrimitivesOrgChart/jquerylayout/layout-default-latest.css' />


<link
	href='<c:out value="${applicationBean.contextPath}"/>/js/basicPrimitivesOrgChart/css/primitives.latest.css?2100'
	media="screen" rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src='<c:out value="${applicationBean.contextPath}"/>/js/basicPrimitivesOrgChart/js/primitives.min.js?2100'></script>

<script
	src="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.min.js"
	type="text/javascript"></script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.css">
<%@include file="/jsp/components/session_timeout/session_timeout.jsp"%>

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
	
	String paramStringFull = paramString + ((request.getParameter("viewMode") != null) ? 	"&viewMode=" + request.getParameter("viewMode") : "");
	
	String viewMode = request.getParameter("viewMode");
	
	
	
	boolean returnFromOrgChart = (request.getParameter("returnFromOrgChart") != null && request.getParameter("returnFromOrgChart").equals("TRUE")) ? true : false; 
	

	if(empresaId != null && !empresaId.equals("") && !empresaId.equals("null") && viewMode != null && viewMode.equals("organigrama")) {
%>


     <script type="text/javascript"            src="orgChartEmpresasData.jsp?empresaId=<%=empresaId%>&paramConsolida=<%=paramConsolida%>&paramSegmento=<%=paramSegmento%>&paramClasificacion=<%=paramClasificacion%>&paramPais=<%=paramPais%>&paramNoEmpOracle=<%=paramNoEmpOracle%>&paramGiro=<%=paramGiro%>&paramPorcentaje=<%=paramPorcentaje%>&selectPorcentaje=<%=selectPorcentaje%>&selectPorcentajeCual=<%=selectPorcentajeCual%>&selectPorcentajeVisualizar=<%=selectPorcentajeVisualizar%>&paramDivision=<%=paramDivision%>"></script>
<%-- <script type="text/javascript" src="../../reportesFlex/TestOrgChart.jsp?empresaId=<%=empresaId%>&paramConsolida=<%=paramConsolida%>&paramSegmento=<%=paramSegmento%>&paramClasificacion=<%=paramClasificacion%>&paramPais=<%=paramPais%>&paramNoEmpOracle=<%=paramNoEmpOracle%>&paramGiro=<%=paramGiro%>&paramPorcentaje=<%=paramPorcentaje%>&selectPorcentaje=<%=selectPorcentaje%>&selectPorcentajeCual=<%=selectPorcentajeCual%>"></script> --%>
<%-- <jsp:include page="/jsp/components/pages_border/open-pre-title.jsp"></jsp:include> --%>
<%-- <jsp:include page="../../reportesFlex/TestOrgChart.jsp?empresaId=<%=empresaId%>&paramConsolida=<%=paramConsolida%>&paramSegmento=<%=paramSegmento%>&paramClasificacion=<%=paramClasificacion%>&paramPais=<%=paramPais%>&paramNoEmpOracle=<%=paramNoEmpOracle%>&paramGiro=<%=paramGiro%>&paramPorcentaje=<%=paramPorcentaje%>&selectPorcentaje=<%=selectPorcentaje%>&selectPorcentajeCual=<%=selectPorcentajeCual%>"></jsp:include> --%>


<%
	}
%>



<script type="text/javascript">

function waitBar() {
	
	
		document.getElementById('imgCapWait').style.display = 'block';
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

	
	$( document ).ready(function() {
		$( "#allExpand" ).on( "click", function() {
			var  valorExpand =  $("#txtExpand").text();
			if(valorExpand == 'Ocultar todo'){
				$("#txtExpand").text("Expandir todo");
				$("#myTreeTable-basic").treetable('collapseAll');
			}else{
				$("#txtExpand").text("Ocultar todo");
				$("#myTreeTable-basic").treetable('expandAll');
			}
			
	    });
		
		
		
		
		$("input[name=viewMode]").each(function (index) {  
		       if($(this).is(':checked')){
		          if($(this).val() == 'Inf. Bolsa'){
		        	  $("#selectTipoInfBolsa").css("display","block")
		        	  $('#btnEjecutar').removeAttr('onclick');
		          }
		       }
		    });
		
		$("input:radio[name=viewMode]").click(function() {
		    var value = $(this).val();
		    if(value == 'Inf. Bolsa'){
		    	$("#selectTipoInfBolsa").css("display","block")
		    	$('#btnEjecutar').removeAttr('onclick');
		    }else{
		    	$("#selectTipoInfBolsa").css("display","none")
		    	$('#btnEjecutar').attr('onclick','waitBar()');
		    }
		});
		
	
		var formulario = document.getElementById("formTenenciaCascada"),
		vacio = false;
		 
		formulario.addEventListener("submit", function(event){
		event.preventDefault();
		 
	
		
		if($("#hiddenEmpresa").val() == '' || $("#hiddenEmpresa").val() == 'null'){
			swal({ title: "Aviso",   
				   text: "La empresa es requerida",   
				   type: "warning",  
				   confirmButtonText: "Ok" });
			vacio = true;

			$("#imgCapWait").css("display","none")
			$('#btnEjecutar').css("display","")
		
		}else{
			vacio = false;
		}
		
		if (!vacio){
		this.submit();
		}
		}, false);
	
	

	
		
		$("#myTreeTable-basic .indenter").each(function(index,key) {
   		 
			$(this).css({'margin-left':'-19px'});
    		 
    	   
    	  });
		
	});
	 
	
	
	
	
</script>


</head>
<body onload="">

	<jsp:include page="/jsp/components/pages_border/open-pre-title.jsp"></jsp:include>

	Reporte de Tenencia en Cascada

	<jsp:include page="/jsp/components/pages_border/open-post-title.jsp"></jsp:include>
	<jsp:include
		page="/jsp/components/backPageReports/regresaMenuReportes.jsp">
		<jsp:param name="action" value="rPredefinidos" />
	</jsp:include>

	<form action="tenenciaCascada.jsp" method="post" id="formTenenciaCascada">




		<table width="100%">
			<tr>
				<td colspan="3"><hr></td>
			</tr>
			<tr>
				<td width="10%"></td>
				<td width="15%"><span style="color: red; font-size: 14px; font-weight: bold;">*</span>&nbsp;Empresa:</td>
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
				
			
						
				//String treeTableData = null;
				ArrayList<TenCascRecord> treeTableData = null;
				ArrayList<TenCascRecord> DataJasper    = null;
				
				String treeGraphData = null;
				String treeOrgChartData = null;
				
				out.println("<!-- " + empresaId + " -->");
				
				if(empresaId != null && !empresaId.equals("") && !empresaId.equals("null") && (viewMode == null || viewMode.equals("tabla"))) {
					
					//getTreeData
					if(!returnFromOrgChart) {
							treeTableData = TenenciaCascada.getTreeData1(empresaId, fecha,
																		paramConsolida, 
																		paramSegmento, 
																		paramClasificacion, 
																		paramPais, 
																		paramNoEmpOracle, 
																		paramGiro,
																		paramPorcentaje,
																		selectPorcentaje,
																		selectPorcentajeCual,
																		selectPorcentajeVisualizar,
																		paramDivision
																		);
					}
				}
				
				if(empresaId != null && !empresaId.equals("") && !empresaId.equals("null") && (viewMode == null || viewMode.equals("Inf. Bolsa"))) {
					
					//reporte Plano
					if(!returnFromOrgChart) {
						DataJasper = TenenciaCascada.getTreeData1(empresaId, fecha,
																		paramConsolida, 
																		paramSegmento, 
																		paramClasificacion, 
																		paramPais, 
																		paramNoEmpOracle, 
																		paramGiro,
																		paramPorcentaje,
																		selectPorcentaje,
																		selectPorcentajeCual,
																		selectPorcentajeVisualizar,
																		paramDivision
																		);
					}
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
							<td><input type="hidden" id='hiddenEmpresa'
								name='hiddenEmpresa' value='<%=empresaId%>'>
								<div id='divEmpresa'><%=nomEmpresa %></div> <%--<input id='txtEmpresa' name='txtEmpresa'> --%>
							</td>
							<td>
								<%--
						<input type='button' value='Seleccionar' onclick="openSelectPupUp('EMPRESAS','hiddenEmpresa','divEmpresa','innerHTML','')">
						 --%> <img
								src='<%= request.getContextPath() %>/img/btn_search.png'
								onclick="openSelectPupUp('EMPRESAS','hiddenEmpresa','divEmpresa','innerHTML','')"
								alt=""> <img
								src='<%= request.getContextPath() %>/img/btn_clean.png'
								onclick="document.getElementById('hiddenEmpresa').value = '';document.getElementById('divEmpresa').innerHTML = '';"
								alt="">
							</td>
						</tr>

					</table>
				</td>
			</tr>
			<%-- 
	<tr>
		<td></td>
		<td>al día:</td>
		<td>
				<input type='text' name='txtDate' id='txtDate'>
				<%=
				JSCal.getCalendar("txtDate", "")
				%>
		</td>
	</tr>
	--%>
			<tr>
				<td colspan="3"><hr></td>
			</tr>

			<tr>
				<td colspan="3">
					<table width="100%">
						<tr>
							<td width="10%"></td>
							<td>Consolida:</td>
							<td><input type="hidden" id='hiddenConsolida'
								name='hiddenConsolida'
								value='<%= (request.getParameter("hiddenConsolida")!=null)?request.getParameter("hiddenConsolida"):"" %>'>
								<%--<div id='divConsolida'><%=nomEmpresa %></div> --%> <input
								id='txtConsolida' name='txtConsolida' readonly="readonly"
								value='<%= (request.getParameter("txtConsolida")!=null)? StringUtils.latin1ToUTF8(request.getParameter("txtConsolida")) : ""%>'>

								<%-- 
						<input type='button' value='Seleccionar' onclick="openSelectPupUp('18','hiddenConsolida','txtConsolida','value','')">
						--%> <img src='<%= request.getContextPath() %>/img/btn_search.png'
								onclick="openSelectPupUp('18','hiddenConsolida','txtConsolida','value','')"
								alt=""> <img
								src='<%= request.getContextPath() %>/img/btn_clean.png'
								onclick="document.getElementById('hiddenConsolida').value = '';document.getElementById('txtConsolida').value = '';"
								alt=""></td>
							<td width="10%"></td>
							<td>Segmento:</td>
							<td><input type="hidden" id='hiddenSegmento'
								name='hiddenSegmento'
								value='<%= (request.getParameter("hiddenSegmento")!=null)?request.getParameter("hiddenSegmento"):"" %>'>

								<input id='txtSegmento' name='txtSegmento' readonly="readonly"
								value='<%= (request.getParameter("txtSegmento")!=null)? StringUtils.latin1ToUTF8(request.getParameter("txtSegmento")) : "" %>'>

								<%--			
						<input type='button' value='Seleccionar' onclick="openSelectPupUp('5','hiddenSegmento','txtSegmento','value','')">
						 --%> <img
								src='<%= request.getContextPath() %>/img/btn_search.png'
								onclick="openSelectPupUp('5','hiddenSegmento','txtSegmento','value','')"
								alt=""> <img
								src='<%= request.getContextPath() %>/img/btn_clean.png'
								onclick="document.getElementById('hiddenSegmento').value = '';document.getElementById('txtSegmento').value = '';"
								alt=""></td>
						</tr>

						<tr>
							<td></td>
							<td>Clasificación:</td>
							<td><input type="hidden" id='hiddenClasificacion'
								name='hiddenClasificacion'
								value='<%= (request.getParameter("hiddenClasificacion")!=null)?request.getParameter("hiddenClasificacion"):"" %>'>

								<input id='txtClasificacion' name='txtClasificacion'
								readonly="readonly"
								value='<%= (request.getParameter("txtClasificacion")!=null)? StringUtils.latin1ToUTF8(request.getParameter("txtClasificacion")) : ""%>'>

								<%-- 
						<input type='button' value='Seleccionar' onclick="openSelectPupUp('6','hiddenClasificacion','txtClasificacion','value','')">
						--%> <img src='<%= request.getContextPath() %>/img/btn_search.png'
								onclick="openSelectPupUp('6','hiddenClasificacion','txtClasificacion','value','')"
								alt=""> <img
								src='<%= request.getContextPath() %>/img/btn_clean.png'
								onclick="document.getElementById('hiddenClasificacion').value = '';document.getElementById('txtClasificacion').value = '';"
								alt=""></td>
							<td></td>
							<td>País:</td>
							<td><input type="hidden" id='hiddenPais' name='hiddenPais'
								value='<%= (request.getParameter("hiddenPais")!=null)?request.getParameter("hiddenPais"):"" %>'>

								<input id='txtPais' name='txtPais' readonly="readonly"
								value='<%= (request.getParameter("txtPais")!=null)? StringUtils.latin1ToUTF8(request.getParameter("txtPais")) : ""%>'>

								<%--
						<input type='button' value='Seleccionar' onclick="openSelectPupUp('7','hiddenPais','txtPais','value','')">
						 --%> <img
								src='<%= request.getContextPath() %>/img/btn_search.png'
								onclick="openSelectPupUp('7','hiddenPais','txtPais','value','')"
								alt=""> <img
								src='<%= request.getContextPath() %>/img/btn_clean.png'
								onclick="document.getElementById('hiddenPais').value = '';document.getElementById('txtPais').value = '';"
								alt=""></td>
						</tr>


						<tr>
							<td></td>
							<td>No Empresa:</td>
							<td><input type="hidden" id='hiddenNoEmpOracle'
								name='hiddenNoEmpOracle'
								value='<%= (request.getParameter("hiddenNoEmpOracle")!=null)?request.getParameter("hiddenNoEmpOracle"):"" %>'>

								<input id='txtNoEmpOracle' name='txtNoEmpOracle'
								readonly="readonly"
								value='<%= (request.getParameter("txtNoEmpOracle")!=null)? StringUtils.latin1ToUTF8(request.getParameter("txtNoEmpOracle")) : ""%>'>

								<%--
						<input type='button' value='Seleccionar' onclick="openSelectPupUp('17','hiddenNoEmpOracle','txtNoEmpOracle','value','')">
						 --%> <img
								src='<%= request.getContextPath() %>/img/btn_search.png'
								onclick="openSelectPupUp('17','hiddenNoEmpOracle','txtNoEmpOracle','value','')"
								alt=""> <img
								src='<%= request.getContextPath() %>/img/btn_clean.png'
								onclick="document.getElementById('hiddenNoEmpOracle').value = '';document.getElementById('txtNoEmpOracle').value = '';"
								alt=""></td>
							<td></td>
							<td>Giro:</td>
							<td><input type="hidden" id='hiddenGiro' name='hiddenGiro'
								value='<%= (request.getParameter("hiddenGiro")!=null)?request.getParameter("hiddenGiro"):"" %>'>

								<input id='txtGiro' name='txtGiro' readonly="readonly"
								value='<%= (request.getParameter("txtGiro")!=null)? StringUtils.latin1ToUTF8(request.getParameter("txtGiro")) : ""%>'>

								<%--		
						<input type='button' value='Seleccionar' onclick="openSelectPupUp('3','hiddenGiro','txtGiro','value','')">
						--%> <img src='<%= request.getContextPath() %>/img/btn_search.png'
								onclick="openSelectPupUp('3','hiddenGiro','txtGiro','value','')"
								alt=""> <img
								src='<%= request.getContextPath() %>/img/btn_clean.png'
								onclick="document.getElementById('hiddenGiro').value = '';document.getElementById('txtGiro').value = '';"
								alt=""></td>
						</tr>

						<tr>
							<td></td>
							<td>Porcentaje:</td>
							<td><select id="selectPorcentajeCual"
								name='selectPorcentajeCual'>
									<option value='Directo'
										<% if(selectPorcentajeCual.equals("Directo")) { out.println("selected='selected'");} %>>Directo</option>
									<option value='Indirecto'
										<% if(selectPorcentajeCual.equals("Indirecto")) { out.println("selected='selected'");} %>>Indirecto</option>
							</select> <select id="selectPorcentaje" name='selectPorcentaje'>
									<option value='1'
										<% if(selectPorcentaje.equals("1")) { out.println("selected='selected'");} %>>igual</option>
									<option value='2'
										<% if(selectPorcentaje.equals("2") || selectPorcentaje.equals("")) { out.println("selected='selected'");} %>>mayor
										que</option>
									<option value='3'
										<% if(selectPorcentaje.equals("3")) { out.println("selected='selected'");} %>>menor
										que</option>
							</select> <input id='txtPorcentaje' name='txtPorcentaje' size="10"
								value='<%= (request.getParameter("txtPorcentaje")!=null)? request.getParameter("txtPorcentaje") : "0"%>'>


							</td>
							<td></td>
							<td>División: </td>
							
							<td>
									<input type="hidden" id='hiddenDivision' name='hiddenDivision'
									value='<%= (request.getParameter("hiddenDivision")!=null)?request.getParameter("hiddenDivision"):"" %>'>
	
									<input id='txtDivision' name='txtDivision' readonly="readonly"
									value='<%= (request.getParameter("txtDivision")!=null)? StringUtils.latin1ToUTF8(request.getParameter("txtDivision")) : ""%>'>
	
									<%--		
							<input type='button' value='Seleccionar' onclick="openSelectPupUp('4','hiddenGiro','txtGiro','value','')">
							--%> <img src='<%= request.getContextPath() %>/img/btn_search.png'
									onclick="openSelectPupUp('4','hiddenDivision','txtDivision','value','')"
									alt=""> <img
									src='<%= request.getContextPath() %>/img/btn_clean.png'
									onclick="document.getElementById('hiddenDivision').value = '';document.getElementById('txtDivision').value = '';"
									alt="">
							</td>
							
						</tr>

						<tr>
							<td></td>
							<td>Visualizar:</td>
							<td><select id="selectPorcentajeVisualizar"
								name='selectPorcentajeVisualizar'>
									<option value='Directo'
										<% if(selectPorcentajeVisualizar.equals("Directo")) { out.println("selected='selected'");} %>>Directo</option>
									<option value='Indirecto'
										<% if(selectPorcentajeVisualizar.equals("Indirecto")) { out.println("selected='selected'");} %>>Indirecto</option>
									<option value='Ambos'
										<% if(selectPorcentajeVisualizar.equals("Ambos")) { out.println("selected='selected'");} %>>Ambos</option>
							</select></td>
							<td></td>
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
				<td></td>
				<td>Ver:</td>
				<td><label> <input type='radio' name='viewMode'
						id='viewMode1' value="tabla"
						<% if((viewMode == null || viewMode.equals("tabla") || viewMode.equals("null")) && !returnFromOrgChart) { %>
						checked="checked" <% } %>> Tabla
				</label> &nbsp; <!-- 
			<label>
				<input type='radio' name='viewMode' id='viewMode2' value="grafica" 
					<% if(viewMode != null && viewMode.equals("grafica")) { %>checked="checked" <% } %>> Gráfica
			</label>
			
			&nbsp;
			 --> <label> <input type='radio' name='viewMode'
						id='viewMode3' value="organigrama"
						<% if((viewMode != null && viewMode.equals("organigrama")) || returnFromOrgChart) { %>
						checked="checked" <% } %>> Organigrama
				</label> <label> <input type='radio' name='viewMode' id='viewMode4'
						value="Inf. Bolsa"
						"
					<% if((viewMode != null && viewMode.equals("Inf. Bolsa") ) || returnFromOrgChart) { %>
						checked="checked" <% } %>> Inf. Bolsa
						<select id="selectTipoInfBolsa" name="selectTipoInfBolsa" style="display: none; margin-left:200px;">
							<option value="PDF">Pdf</option>
							<option value="EXCEL" >Excel</option>
					</select>
				</label>
					
								
				</td>
		
					
						
					
				
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td align="right"><input type="submit" id='btnEjecutar'
					value='Generar' onclick="waitBar()" > <img
					src='<%= request.getContextPath() %>/img/wait-bar.gif'
					id='imgCapWait' style="display: none;" alt=""></td>
			</tr>
			<tr>
				<td colspan="3"></td>
			</tr>
			<tr>
				<td colspan="3"><hr></td>
			</tr>
			<% 
				if(DataJasper != null) {
					
					response.sendRedirect(request.getContextPath()+"/TenCascadaPlanoServlet?psHiFuOut="+request.getParameter("selectTipoInfBolsa")+"&hiddenNomEmpresa="+nomEmpresa);
				}
			%>
			<tr>
				<td colspan="3" align="center">
					<% 
				if(treeTableData != null) {
					
			%>
					<div align="left">
						 <a
							href="tenenciaCascadaPrintTree.jsp?hiddenEmpresa=<%= empresaId%><%=paramStringFull%>"
							target="_blank">Imprimir</a>
							 
							
							 
					</div> <br> <% 
				}
			%>
			
 						
					<div id="infoDiv"></div>
					
					

					<div id="treeTableDiv" width="100%"></div> <% 
				if(treeTableData != null) {
			%>

					<div align="left">

						<a href="#" name="allExpand" id="allExpand"><span
							id="txtExpand">Expandir todo</span></a>
					</div>
					<div id="contentExport">
						<table id="myTreeTable-basic">

							<thead>
								<tr>
									<th># Empresa</th>
									<th width="45%">Empresa</th>
									
									<% if(selectPorcentajeVisualizar != null && (selectPorcentajeVisualizar.equals("Directo") || selectPorcentajeVisualizar.equals("Ambos") ) ) { %>
									<th>Directo</th>
									<% } %>
									<% if(selectPorcentajeVisualizar != null && (selectPorcentajeVisualizar.equals("Indirecto") || selectPorcentajeVisualizar.equals("Ambos") ) ) { %>
									<th>Indirecto</th>
									<% } %>
									<th>Consolida</th>
									<th>Segmento</th>
									<th>Clasificación</th>
									<th>País</th>
									<th>Giro</th>
									<th>División</th>
								</tr>
							</thead>
							<tbody>
								<tr data-tt-id="1" class="registros">
									<td></td>
									<td style="padding-left: 17px;"><%= nomEmpresa %></td>
									
									<% if(selectPorcentajeVisualizar != null && (selectPorcentajeVisualizar.equals("Directo") || selectPorcentajeVisualizar.equals("Ambos") ) ) { %>
									<td></td>
									<% } %>
									<% if(selectPorcentajeVisualizar != null && (selectPorcentajeVisualizar.equals("Indirecto") || selectPorcentajeVisualizar.equals("Ambos") ) ) { %>
									<td></td>
									<% } %>
									<td></td>
									<td></td>
									<td></td>
									
									<td></td>
									<td></td>
									<td></td>
								</tr>
								<% 
				        	int numEmpresas = 1;
				        	for(TenCascRecord row : treeTableData) { 
				        		String paddingCell="50px";
				        		String nombreEmpresa="";
				        	
				        	%>
								 <tr data-tt-id="<%= row.getIdRow()%>"
									data-tt-parent-id="<%= row.getIdParent() %>" class="registros">
									<% 
				        	    nombreEmpresa = row.getNomEmpresa();
				        	   
				        	    if(row.getNomEmpresa().startsWith("                         ")){
				        	    	paddingCell = "300px";
				        	    	nombreEmpresa = row.getNomEmpresa().replaceAll("                         ", "");
				        	    
				        	    }
				        	    else if(row.getNomEmpresa().startsWith("                    ")){
				        	    	paddingCell = "250px";
				        	    	nombreEmpresa = row.getNomEmpresa().replaceAll("                    ", "");
				        	    
				        	    } else if(row.getNomEmpresa().startsWith("               ")){
				        	    	paddingCell = "200px";
				        	    	nombreEmpresa = row.getNomEmpresa().replaceAll("               ", "");
				        	    
				        	    }else if(row.getNomEmpresa().startsWith("          ")){
				        	    	paddingCell = "150px";
				        	    	nombreEmpresa = row.getNomEmpresa().replaceAll("          ", "");
				        	    
				        	    }else if(row.getNomEmpresa().startsWith("     ")){
				        	    	paddingCell = "100px";
				        	    	nombreEmpresa = row.getNomEmpresa().replaceAll("     ", "");
				        	    	numEmpresas++;
				        	    }
				        	   // nombreEmpresa = row.getNomEmpresa().replaceAll(" ", "");
				        	    %>
									<td><%= (row.getNoEmpOracle() != null) ? row.getNoEmpOracle() : "" %></td>
									<td style="padding-left: <%=paddingCell%>;"><span class="indenter" style="margin-left: -19px;"></span><%=nombreEmpresa%></td>
									
									<% if(selectPorcentajeVisualizar != null && (selectPorcentajeVisualizar.equals("Directo") || selectPorcentajeVisualizar.equals("Ambos") ) ) { %>
									<td><%= (row.getDirecto() != null) ? row.getDirecto() : "" %></td>
									<% } %>
									<% if(selectPorcentajeVisualizar != null && (selectPorcentajeVisualizar.equals("Indirecto") || selectPorcentajeVisualizar.equals("Ambos") ) ) { %>
									<td><%= (row.getIndirecto() != null) ? row.getIndirecto() : "" %></td>
									<% } %>
									<td><%= (row.getConsolida() != null) ? row.getConsolida() : "" %></td>
									<td><%= (row.getSegmento() != null) ? row.getSegmento() : "" %></td>
									<td><%= (row.getClasificacion() != null) ? row.getClasificacion() : "" %></td>
									<td><%= (row.getPais() != null) ? row.getPais() : "" %></td>
									
									<td><%= (row.getGiro() != null) ? row.getGiro() : "" %></td>
									<td><%= (row.getDivision() != null) ? row.getDivision() : "" %></td>
								</tr>
								<% } %>

							</tbody>
						</table>
					</div> <script type="text/javascript">
				     // $("#myTreeTable-basic").treetable({ expandable: true });
				      $("#myTreeTable-basic").treetable({ 
				    	  column: 1,
				    	  columnElType: "td", // i.e. 'td', 'th' or 'td,th'
				    	  expandable: true,
				    	  expanderTemplate: "<a href='#'>&nbsp;</a>",
				    	  indent: 1,
				    	  indenterTemplate: "<span class='indenter'></span>",
				    	  cellTemplate: '',
				    	  stringExpand: "Expand",
				    	  stringCollapse: "Collapse",

				    	  // Events
				    	  onInitialized: null,
				    	  onNodeCollapse: null,
				    	  onNodeExpand: null,
				    	  onNodeInitialized: null
				    	});
				    
				      
				    </script> <%-- 				
				<script type="text/javascript" charset="utf-8">
					
					
				webix.ready(function(){
						grida = webix.ui({
							container:"treeTableDiv",
							view:"treetable",
							columns:[
								//{ id:"id",	header:"", css:{"text-align":"right"},  	width:50},
								{ id:"nombre",	header:"<%=nomEmpresa%>",	width:400,
									template:"{common.treetable()} #nombre#" },
								<% if(selectPorcentajeVisualizar != null && (selectPorcentajeVisualizar.equals("Directo") || selectPorcentajeVisualizar.equals("Ambos") ) ) { %>
								{ id:"directo",	header:"% Directo",	width:130},
								<% } %>
								<% if(selectPorcentajeVisualizar != null && (selectPorcentajeVisualizar.equals("Indirecto") || selectPorcentajeVisualizar.equals("Ambos") ) ) { %>
								{ id:"indirecto",	header:"% Indirecto",	width:130},
								<% } %>
								{ id:"consolida",	header:"Consolida",	width:100},
								{ id:"segmento",	header:"Segmento",	width:100},
								{ id:"clasificacion",	header:"Clasificación",	width:100},
								{ id:"pais",	header:"País",	width:100},
								{ id:"no_emp_oracle",	header:"# Oracle",	width:100},
								{ id:"giro",	header:"Giro",	width:150}
							],
							autoheight:true,
							autowidth:true,
				
							data: [
							    
							    <%= treeTableData %>
							    
							         
							       
							    /*
								{ "nombre":"The Shawshank Redemption", "open":true, "data":[
									{ "nombre":"Part 1", "directo":"alpha"},
									{ "nombre":"Part 2", "directo":"beta", "open":true, "data":[
										//{ "nombre":"Part 1", "directo":"beta-twin"},
										//{ "nombre":"Part 1", "directo":"beta-twin"}
									]},
									{ "nombre":"Part 3", "directo":"gamma" }
								]},
								{ "nombre":"The Godfather", "data":[
									{ "nombre":"Part 1", "directo":"alpha" },
									{ "nombre":"Part 2", "directo":"beta" }
								]}
								*/
							]
						
						});	
					});
				</script>
				
				
				--%> <% 
				}
			%> <% 
				if(treeGraphData != null) {
			%>




					<style type="text/css">
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
	stroke-width: 1px;
	//1.5
}
</style> <script type="text/javascript">

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

d3.json("treeEmpresas.jsp?empresaId=<%= empresaId %>&fecha=<%=fecha%>&paramConsolida=<%=paramConsolida%>&paramSegmento=<%=paramSegmento%>&paramClasificacion= <%=paramClasificacion%>&paramPais=<%=paramPais%>&paramNoEmpOracle=<%=paramNoEmpOracle%>&paramGiro=<%=paramGiro%>&paramPorcentaje=<%=paramPorcentaje%>&paramDivision=<%=paramDivision%>", function(error, flare) {
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

  // Update the nodes…
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

  // Update the links…
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

</script> <% 
				}
			%> <% 
				if(treeOrgChartData != null) {
			%> <script type="text/javascript">
        jQuery(document).ready(function () {
            jQuery('body').layout(
			{
			    center__paneSelector: "#contentpanel"
			});
        });
    </script>

					<style type="text/css">
.orgChartTextSize {
	font-size: 11px;
}
</style> <script type="text/javascript">
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
				, west__size: 300
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
            var pageFitMode = parseInt(jQuery("input:radio[name=pageFitMode]:checked").val(), 10);
            var orientationType = parseInt(jQuery("input:radio[name=orientationType]:checked").val(), 10);
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
            
            <% if(selectPorcentajeVisualizar.equals("Ambos") ) { %>
            result.itemSize = new primitives.common.Size(200, 80);
            <% } %>
            
            <% if(selectPorcentajeVisualizar.equals("Directo") || selectPorcentajeVisualizar.equals("Indirecto")) { %>
            result.itemSize = new primitives.common.Size(200, 70);
            <% } %>
            
            result.minimizedItemSize = new primitives.common.Size(minimizedItemSize, minimizedItemSize);
            result.minimizedItemCornerRadius = minimizedItemCornerRadius;
            result.highlightPadding = new primitives.common.Thickness(highlightPadding, highlightPadding, highlightPadding, highlightPadding);

            
            var itemTemplate = jQuery(
			  '<div class="bp-item bp-corner-all bt-item-frame">'
                + '<table>'
                + '<tr><th colspan=3><div name="empresa" class="orgChartTextSize"></div></th></tr>'
                + '<tr><td colspan=3></td></tr>'
                <% if (selectPorcentajeVisualizar != null && (selectPorcentajeVisualizar.equals("Directo") || selectPorcentajeVisualizar.equals("Ambos") ) ) { %>
                + '<tr><td width="20%"></td><td width="40%" class="orgChartTextSize">Directo:</td><td><div name="directo" class="orgChartTextSize"></td></tr>'
                <% } %>
                <% if(selectPorcentajeVisualizar != null && (selectPorcentajeVisualizar.equals("Indirecto") || selectPorcentajeVisualizar.equals("Ambos") ) ) { %>
                + '<tr><td width="20%"></td><td width="40%" class="orgChartTextSize">Indirecto:</td><td><div name="indirecto" class="orgChartTextSize"></td></tr>'
                <% } %>
                
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


					 <div id="contentpanel" style="padding: 0px;background-image:url('<c:out value='${applicationBean.contextPath}'/>/img/page_background.jpg');" >


						<!--bpcontent-->
						<div id="westpanel"
							style="padding: 5px; margin: 0px; border-style: solid; font-size: 12px; border-color: grey; border-width: 1px; overflow: scroll; -webkit-overflow-scrolling: touch;">



							<jsp:include
								page="/jsp/components/pages_border/open-pre-title.jsp"></jsp:include>

							Reporte de Tenencia en Cascada

							<jsp:include
								page="/jsp/components/pages_border/open-post-title.jsp"></jsp:include>


							<div align='right'>

								<a
									href='tenenciaCascada.jsp?hiddenEmpresa=<%= empresaId%><%=paramString%>&amp;returnFromOrgChart=TRUE'>Regresar..</a>
								<br> <br>
								<%--  <a href="tenenciaCascadaPrintTree.jsp?hiddenEmpresa=<%= empresaId%><%=paramStringFull%>" target="_blank">Imprimir..</a> --%>
								<%--  <a href="../../reportesFlex/TestOrgChart.jsp?empresaId=<%=empresaId%>&paramConsolida=<%=paramConsolida%>&paramSegmento=<%=paramSegmento%>&paramClasificacion=<%=paramClasificacion%>&paramPais=<%=paramPais%>&paramNoEmpOracle=<%=paramNoEmpOracle%>&paramGiro=<%=paramGiro%>&paramPorcentaje=<%=paramPorcentaje%>&selectPorcentaje=<%=selectPorcentaje%>&selectPorcentajeCual=<%=selectPorcentajeCual%>"" target="_blank">Imprimir..</a> --%>
								<a
									href="../../reportesFlex/showReportAsPDFTestOrgChart.jsp?empresaId=<%=empresaId%>&amp;paramConsolida=<%=paramConsolida%>&amp;paramSegmento=<%=paramSegmento%>&amp;paramClasificacion=<%=paramClasificacion%>&amp;paramPais=<%=paramPais%>&amp;paramNoEmpOracle=<%=paramNoEmpOracle%>&amp;paramGiro=<%=paramGiro%>&amp;paramPorcentaje=<%=paramPorcentaje%>&amp;selectPorcentaje=<%=selectPorcentaje%>&amp;selectPorcentajeCual=<%=selectPorcentajeCual%>&amp;selectPorcentajeVisualizar=<%=selectPorcentajeVisualizar%>&amp;paramDivision=<%=paramDivision%>"
									target="_blank">Exportar..</a>
								<%--        <a href="Organigrama.jsp?empresaId=<%=empresaId%>&paramConsolida=<%=paramConsolida%>&paramSegmento=<%=paramSegmento%>&paramClasificacion=<%=paramClasificacion%>&paramPais=<%=paramPais%>&paramNoEmpOracle=<%=paramNoEmpOracle%>&paramGiro=<%=paramGiro%>&paramPorcentaje=<%=paramPorcentaje%>&selectPorcentaje=<%=selectPorcentaje%>&selectPorcentajeCual=<%=selectPorcentajeCual%>&selectPorcentajeVisualizar=<%=selectPorcentajeVisualizar%>&viewMode=organigrama&txtPorcentaje=<%=paramPorcentaje%>" target="_blank">Exportar..</a> --%>
								<%--<a href="OrgChartSimpleJQuery.jsp" target="_blank">Exportar..</a> --%>
							</div>
							<br> <br>

							<h3>Opciones de Visualización</h3>

							<p id="pageFitMode">
								<b>Modalidad de Ajuste</b><br>
							</p>
							<p id="orientationType">
								<br>
								<b>Orientación</b><br>
							</p>
							<p id="verticalAlignment" style="display: none">Items
								Vertical Alignment</p>
							<p id="horizontalAlignment" style="display: none">Items
								Horizontal Alignment</p>
							<p id="leavesPlacementType" style="display: none">Leaves
								placement</p>
							<p id="minimalVisibility" style="display: none">Minimal nodes
								visibility</p>
							<p id="selectionPathMode" style="display: none">Selection
								Path Mode</p>
							<!--<h3>Default Template Options</h3>-->
							<p id="hasButtons" style="display: none">User buttons</p>
							<p id="hasSelectorCheckbox" style="display: none">Selection
								check box</p>
							<!--<h3>Minimized Item (Dot, Marker)</h3>-->
							<p id="minimizedItemCornerRadius" style="display: none">Corner
								Radius:&nbsp;</p>
							<p id="minimizedItemSize" style="display: none">Size:&nbsp;</p>
							<p id="highlightPadding" style="display: none">Highlight
								Padding:&nbsp;</p>
							<!--<h3>Vertical Intervals Between Rows</h3>-->
							<p id="normalLevelShift" style="display: none">Normal:&nbsp;</p>
							<p id="dotLevelShift" style="display: none">Dotted:&nbsp;</p>
							<p id="lineLevelShift" style="display: none">Lined:&nbsp;</p>
							<!--<h3>Horizontal Intervals Between Items in Row</h3>-->
							<p id="normalItemsInterval" style="display: none">Normal:&nbsp;</p>
							<p id="dotItemsInterval" style="display: none">Dotted:&nbsp;</p>
							<p id="lineItemsInterval" style="display: none">Lined:&nbsp;</p>
							<p id="cousinsIntervalMultiplier" style="display: none">Cousins
								Multiplier:&nbsp;</p>
							<!--<h3>Connectors</h3>-->
							<p id="arrowsDirection" style="display: none">Arrows
								Direction</p>
							<p id="connectorType" style="display: none">Connectors</p>
							<p id="elbowType" style="display: none">Connectors Elbows
								Type</p>
							<p id="lineType" style="display: none">Line type</p>

							<p id="color" style="display: none">Color:&nbsp;</p>
							<p id="lineWidth" style="display: none">Line width:&nbsp;</p>

							<!--<h3>Labels</h3>-->
							<p id="showLabels" style="display: none">Show Labels</p>
							<p id="labelOrientation" style="display: none">Label
								Orientation</p>
							<p id="labelPlacement" style="display: none">Label Placement</p>
							<!--<h3>Rendering Mode</h3>-->
							<p id="graphicsType" style="display: none">Graphics</p>
							<p id="actualGraphicsType" style="display: none"></p>

							<jsp:include page="/jsp/components/pages_border/close.jsp"></jsp:include>

						</div>
						<div id="centerpanel"
							style="overflow: hidden; padding: 0px; margin: 0px; border: 0px;">

						</div>
						<div id="southpanel"></div>
						<!--/bpcontent-->

					</div> <% 
				}
			%>

				</td>
			</tr>


		</table>

	</form>

	<jsp:include page="/jsp/components/pages_border/close.jsp"></jsp:include>

</body>
</html>
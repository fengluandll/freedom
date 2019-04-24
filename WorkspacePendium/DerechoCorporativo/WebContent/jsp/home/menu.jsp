<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="mx.com.televisa.derechocorporativo.util.FacesUtils"%>
<%@page import="mx.com.televisa.derechocorporativo.bean.SessionBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<!--  <link rel="STYLESHEET" type="text/css" href="../../css/bootstrap.min.css"/>  -->
<script type="text/javascript" src="../../js/jquery/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="../../js/jquery/jquery.form.js"></script>
<title>Insert title here</title>


<%  
    String lstIdRol = FacesUtils.getSessionBean().getIdRol();
	String lstNomUser = FacesUtils.getSessionBean().getNomUser();
	
	
	//FacesUtils.getSessionBean().setEditCon("");
	SessionBean sessionBean = (SessionBean)session.getAttribute("sessionBean"); 
	sessionBean.setEditCon("");
	
	if (lstNomUser == null) {
		response.sendRedirect("/DerechoCorporativo/faces/jsp/home/error.jsp");	
}
%>
	


<%@include file="/css/kaz-styles.jsp"%>

<script type="text/javascript" language="javascript">
	window.onload = function(){
	 	document.getElementById("462").checked = true;
	 	document.getElementById("466").checked = true;
	 	document.frmBusca.pai.value = 0;
	}

	/*
	*Funcion que pone la marca de agua buscar y la quita cuando escribes
	*/
	
	$(document).ready(function(){
	
		$('#filtro').val("Empresa").focus(function(){
			 if($(this).val() == "Empresa"){
				  $(this).removeClass("lightText").val(""); } }).blur(
						  function(){ if($(this).val() == ""){ 
							  $(this).val("Empresa").addClass("lightText"); } }); 

/*
 * Funcion para hacer un ajax sumbmit al form de buscar
 */
		$('#frmBusca').ajaxForm(function() {
			
			//var contexto = $('#context').val();
			//var contexto = $(location).attr('href');
			//var contexto = 'http://vaio-ernesto:7001/DerechoCorporativo/';
			var contexto = $(location).attr('href');
			contexto = contexto.substring(0, contexto.indexOf('/faces'));
			//alert(contexto);
			var dataString = "";
			$.ajax({
				type: "POST",
				url: "",
				data: dataString,
				cache: false,
				success: function()
				{
					$('#contentEmp').load(contexto+"/RecargaMenuServlet");
				}
				});
				return false;
			
	});

		});		
	
	

	/*$(document).ajaxStart(function() {
		  $('#ajaxloading').show();
		});*/

		$(document).ajaxSend(function() {
			  $('#ajaxloading').show();
			});
		
		/*$(document).ajaxComplete(function() {
		  $('#ajaxloading').hide();
		});*/
	$(document).ajaxStop(function() {
		  $('#ajaxloading').hide();
		});

	function minimizeMenu() {
		parent.document.getElementById('framesetMenuAndWindow').cols = "54,*";
		
		menu_table.style.display = "none";
		menu_btn.style.display = "";
		
	}

	function maximizeMenu() {
			
		parent.document.getElementById('framesetMenuAndWindow').cols = "340,*";
		
		menu_table.style.display = "";
		menu_btn.style.display = "none";
	}

	function searchEmpresa(){
		var empresa=document.getElementById('filtro');
		empresa = empresa.value;
		window.location = '/DerechoCorporativo/faces/BuscaEmpresaServlet?emp='+empresa;

	}

</script>   


</head>
<body>
	<!-- style="background-image: url('<c:out value="${applicationBean.contextPath}"/>/img/page_background.jpg');">  -->
	<f:view>
<input id="context" type="hidden" value="${applicationBean.requesturl}"/>
<img src="<c:out value="${applicationBean.contextPath}"/>/img/max-tab_azul.png" style="display: none" id="menu_btn" onclick="maximizeMenu()"></img>


<div id="menu_table">

		<jsp:include page="/jsp/components/pages_border/open-pre-title.jsp"></jsp:include>
		
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td></td>
				<td width="100%">Captura de Manuales Corporativos</td>
				<td><img src="<c:out value="${applicationBean.contextPath}"/>/img/min.png" onclick="minimizeMenu()"></img></td>
			</tr>
<!-- 
			<tr>
				<td style="width: 121px; ">Buscar Empresa:</td>
				<td><input ="textfield"/></td>
			</tr>
-->		
		</table>

<jsp:include page="/jsp/components/pages_border/open-post-title.jsp"></jsp:include>
	
	<br>
	<div align="right">
		<a href="mainMenu.jsp">Menu Principal</a>
		<!-- <input id="btnMnuPrinci" type="submit" value="Menu Principal" class="btn btn-info btn-sm"/>  -->
	</div>
	

 	<br>
 
<form id="frmBusca" name="frmBusca" action="/DerechoCorporativo/BuscaEmpresaServlet" method="post"> 
<table width="100%" style="text-align:left;" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td colspan="3"><span class="blueBoldText">Denominaci&oacute;n</span> <input type="hidden" id="IdRol" name="IdRol" value="<%=lstIdRol%>" /></td>
		
	</tr>
	<tr>
		<td width="10%"></td>
	<!-- <td style="width: 129px; ">Busca Empresa:</td>  -->
	    <td width="45%"><span ></span><label><input type="radio" name="denominacion" value="actual" checked="checked"/> Actual </label></td>
	    <td width="45%"><span ></span><label><input type="radio" name="denominacion" value="anterior"/> Anterior </label></td>
	</tr>
	<tr>
		<td>&nbsp; </td>
	</tr>
	<tr>
		<td colspan="3"><span class="blueBoldText">Clasificaci&oacute;n</span></td>
	</tr>
	<tr>
		<td>&nbsp; </td>
		<td colspan="2">
		<%= 
			new mx.com.televisa.derechocorporativo.modules.home.MenuBean().getClasificacionChecks() 
		%>
	</td>
	</tr>
	<tr>
		<td>&nbsp; </td>
	</tr>
	<tr>
		<td><span class="blueBoldText">Pa&iacute;s:</span></td>
		<td colspan="2">
		<%= 
			new mx.com.televisa.derechocorporativo.modules.home.MenuBean().getPaises() 
		%>
		</td>
	</tr>
	<tr>
		<td>&nbsp; </td>
	</tr>
	<tr>
		<td><span class="blueBoldText">Empresa:</span></td>
		<td colspan="2"><input id="filtro" type="text" name="textBuscar" class="lightText"  value="" size="20"/></td>
	</tr>
	<tr>
		<td colspan="3" align="right"><input id="btnBuscar" type="submit" value="Buscar" class="btn btn-info btn-sm"/></td>
	</tr>		
</table>
</form>
 <div id="ajaxloading" style="position:relative; text-align:center;display:none ;">
	<img src="<c:out value="${applicationBean.contextPath}"/>/img/ajax-loader_chico.gif" />
</div>

		<!-- <ul> -->

<!-- 
			<a
				href="<c:out value="${applicationBean.contextPath}"/>/jsp/captura/manuales.jsp"
				target="contentFrame"> Captura
			</a>
-->

<br>

<!-- 
<br>
<div>
	<a href="/DerechoCorporativo/CargaCatalogoServlet" target='contentFrame'> <span>Administraci&oacute;n de cat&aacute;logos</span> </a>
</div>
<br>
-->

<br/>
<div id="contentEmp">
<%= 
	new mx.com.televisa.derechocorporativo.modules.home.MenuBean().cargaInicial()
%>
</div>
<!-- 

			<c :forEach var="empresa" items="@{menuBean.empresas}"> 
				<a
					href="<c:out value="${applicationBean.contextPath}"/>/jsp/captura/manuales.jsp?idEmp=<c:out value="@{empresa.cveEmpresa}"/>"
					target="contentFrame"> 
						<c:out value="@{empresa.empresaName}"></c:out>
				</a>
				<br></br>
				<br></br>
			</c : forEach>
-->

			
		<!-- 
		<c :forEach var="section" items="@{menuBean.sections}">
			<a
				href="<c:out value="@{applicationBean.contextPath}"/><c:out value="@{section.url}"></c:out>"
				target="contentFrame"> <c:out value="@{section.name}"></c:out>
			</a>
			<br></br>
			<br></br>
		</c : forEach>
 		-->
		<!-- </ul> -->

		<jsp:include page="/jsp/components/pages_border/close.jsp"></jsp:include>
</div>

	</f:view>
</body>
</html>
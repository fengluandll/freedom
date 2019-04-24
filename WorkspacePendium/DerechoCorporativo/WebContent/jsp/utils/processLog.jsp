<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="mx.com.televisa.derechocorporativo.components.JSCal"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Descarga de Log</title>
<%@include file="/css/kaz-styles.jsp"%>

<style type="text/css">
body {
	background-color:white;
	/*background-image:
		url("<c:out value='${applicationBean.contextPath}'/>/img/page_background.jpg");
	background-attachment:fixed;*/
	margin-top: 0;
		margin-left: 0;
		margin-right: 0;
		margin-bottom: 0;
}
</style>
<script type="text/javascript" src="<c:out value="${applicationBean.contextPath}"/>/js/jquery/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="<c:out value="${applicationBean.contextPath}"/>/js/util/prism.js"></script>
<link href="<c:out value="${applicationBean.contextPath}"/>/css/prism.css" rel="stylesheet" />
<script type="text/javascript" language="javascript">
	$(document).ready(function() {
		$( '#btnVer' ).click(function(){
			$('#logResponse').text("Obteniendo el log...");
			$('#divLoading').html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src='../../img/wait-bar.gif'></img>");
			$("#method").val("ver");
				$.ajax( {
			      data: $("#frmDercargaLog").serialize(),
			      type: "post",
			      url: '/DerechoCorporativo/ProcessLog',
			      success: function( dato ){
			    	  var json = JSON.parse(dato);
			    	  $("#logResponse").text(json["jsonLog"]);
			    	  $('#divLoading').html("")
			         
			      }
			});
		});
		$( '#btnDescargar' ).click(function(){
			$('#logResponse').text("Obteniendo el log...");
			$('#divLoading').html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src='../../img/wait-bar.gif'></img>");
			$("#method").val("descargar");
			$( "#frmDercargaLog" ).submit();
		});
	});
</script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td height="76px"
						background="<c:out value="${applicationBean.contextPath}"/>/img/header_azul_1440_76.jpg">
					</td>
				</tr>
				</table>
	<form id="frmDercargaLog" name="frmDercargaLog"  method="post" action="/DerechoCorporativo/ProcessLog" style="width:100%; ">
	<input type="hidden" id="method" name="method">
	
		<table width="100%" style="margin-top: 10px;">
			<tr>
				<td width="10%" style="text-align: right; padding-right: 10px;">Path:</td>
				<td><input type="text" size="100" name="path" id="path"
					value="/u02/app/Middleware/log_pendium/logPendium.log"></td>
			</tr>
			<tr>
				<td style="text-align: right; padding-right: 10px;">Hora inicio:</td>
				<td><input type="text" name="hInicio" id="hInicio"></td>
			</tr>
			<tr>
				<td style="text-align: right; padding-right: 10px;">Hora fin:</td>
				<td><input type="text" name="hFin" id="hFin"></td>
			</tr>
			<tr>
				<td style="text-align: right;"><input type="button" value="Ver" id="btnVer"></td>
				<td><input type="button" value="Descargar" id="btnDescargar"><span id="divLoading"></span></td>
			</tr>
		</table>
	</form>
	<pre style="max-height:29em;overflow:auto;" class="language-markup"><code class="language-markup" id="logResponse" style="width: 95%; height:200px"></code></pre>

</body>
</html>
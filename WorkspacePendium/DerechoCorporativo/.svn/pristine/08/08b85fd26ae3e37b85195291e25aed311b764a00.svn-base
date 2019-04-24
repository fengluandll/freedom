<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<%@include file="/js/util/clientInfo.jsp"%>
<script type="text/javascript">

function doCheck() {

	//alert('doCheck');

	var clientInfo = getClientInfo();
	var arrClientInfo = clientInfo.split("|");
	
	var browser  = arrClientInfo[0];
	var version = arrClientInfo[1];
	var jsVersion = arrClientInfo[2];
	var resolution = arrClientInfo[3];
	var os = arrClientInfo[4];
	var userAgent = arrClientInfo[5];
	
	
	
	document.getElementById('output').innerHTML = document.getElementById('output').innerHTML + "Navegador: ----" + browser;
	document.getElementById('output').innerHTML = document.getElementById('output').innerHTML + "<br />";
	document.getElementById('output').innerHTML = document.getElementById('output').innerHTML + "Version: ----"+ version;
	document.getElementById('output').innerHTML = document.getElementById('output').innerHTML + "<br />";
	document.getElementById('output').innerHTML = document.getElementById('output').innerHTML + "Version de Javascript: ---" + jsVersion;
	document.getElementById('output').innerHTML = document.getElementById('output').innerHTML + "<br />";
	document.getElementById('output').innerHTML = document.getElementById('output').innerHTML + "Resolucion: ---------" + resolution;
	document.getElementById('output').innerHTML = document.getElementById('output').innerHTML + "<br />";
	document.getElementById('output').innerHTML = document.getElementById('output').innerHTML + "Sistema Operativo: ----" + os;
	document.getElementById('output').innerHTML = document.getElementById('output').innerHTML + "<br />";
	document.getElementById('output').innerHTML = document.getElementById('output').innerHTML + "userAgent: ----"+ userAgent;
	document.getElementById('output').innerHTML = document.getElementById('output').innerHTML + "<br />";
}

</script>

</head>
<body>
	<f:view>

		<input type="button" value="Obtener Info del Cliente"
			onclick="doCheck()">

		<div id="output"></div>


	</f:view>
</body>
</html>

<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<html>
<head>
<script type="text/javascript"
	src="<%= request.getContextPath() %>/js/jquery/jquery-1.4.3.min.js"></script>

<style type="text/css">
.slideToggleboxOut {
	
}

.slideTogglebox { /*float:left;
		padding:8px;
		margin:16px;
		
		*/
	
}
</style>
</head>
<body>
	<h3>Busqueda Avanzada</h3>

	<button id="slideToggleButton">Mostrar Busqueda Avanzada</button>
	<br />


	<div class="slideTogglebox">
		<jsp:include page="/jsp/components/aqua_pupup_small/open.jsp"></jsp:include>

		Contenido del Bloque

		<jsp:include page="/jsp/components/aqua_pupup_small/close.jsp"></jsp:include>
	</div>

	<script type="text/javascript">
	  
	$("#slideToggleButton").click(function () {	
		$('.slideTogglebox').slideToggle();
		
		//Mostrar Busqueda Avanzada
		//Ocultar Busqueda Avanzada
		//$("#slideToggleButton").value = ($("#slideToggleButton").value=="+")?"-":"+";
	}); 
	
	$('.slideTogglebox').hide();//Ocultar al principio


	</script>


	Otros componentes
	<br>
	<br> Fecha:
	<%= new java.util.Date() %>


</body>
</html>
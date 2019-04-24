<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<script type="text/javascript">
function regresaMenu(action){
	if(action==1){
		//alert(action);
		window.location = "/DerechoCorporativo/faces/jsp/reportesFlex/reportCatalogConfig.jsp";	
	}else if(action==2){
		window.location = "/DerechoCorporativo/faces/jsp/reportes/reportCatalog.jsp";
	}else if(action==3){
		window.location = "/DerechoCorporativo/faces/jsp/reportesFlex/reportCatalog.jsp";
	}else if(action==4){
		window.location = "/DerechoCorporativo/faces/jsp/reportes/estCapSoc/repEstructuraCapitalSocial.jsp";
	}else if(action==5){
		window.location = "/DerechoCorporativo/faces/jsp/reportes/admVig/adminVigPrincipal.jsp";
	}
	
}

</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<% String numBack = request.getParameter("action"); 
   String action = "";
	if(numBack.equals("rPersonalizados")){
		action = "1";
	}else if(numBack.equals("rPredefinidos")){
		action = "2";
	}else if(numBack.equals("rPersonalizadosFlex")){
		action = "3";
	}else if(numBack.equals("rEstCS")){
		action = "4";
	}else if(numBack.equals("rAdminVig")){
		action = "5";
	}
	
%>
<body>
	<div style="text-align: center;">
	<a href="#"><img alt="Regresar" title="Regresar" src="/DerechoCorporativo/img/icons/Navigation-Left-blue-32.png"
									onclick="regresaMenu(<%=action%>);" ></a>
	</div>
</body>
</html>
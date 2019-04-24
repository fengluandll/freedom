<%@page import="mx.com.televisa.derechocorporativo.bean.ApoderadosGruposApoBean"%>
<%@page import="mx.com.televisa.derechocorporativo.util.FacesUtils"%>
<%@page import="java.util.List"%>
<%@page import="mx.com.televisa.derechocorporativo.model.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	


</head>

<body>
<%
	String idEmpresa = FacesUtils.getSessionBean().getIdCurrentEmpresa();
	String escritura = request.getParameter("apoEscritura");
	String tipoPoder = request.getParameter("apoTipoder");
	String grupo = request.getParameter("apoGrupo");
	
	String numOrden = new Catalog().getNumeroOrdenApoderados(idEmpresa,tipoPoder,escritura,grupo);
%>
	<input id="txtNumOrden" onkeypress="return soloNumeros(event)" name="txtNumOrden" value="<%=numOrden==null?"":numOrden%>" type="text" style="width: 17px;">
</body>
</html>
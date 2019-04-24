<%@page import="mx.com.televisa.derechocorporativo.bean.ApoderadosGruposApoBean"%>
<%@page import="mx.com.televisa.derechocorporativo.util.FacesUtils"%>
<%@page import="java.util.List"%>
<%@page import="mx.com.televisa.derechocorporativo.model.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	
<%
	List<ApoderadosGruposApoBean> elems = new Catalog().dameGrupoApoderados();
%>
</head>
<body>
	<select name='selectGrupoApo' id='selectGrupoApo' style='width: 180px' onChange="selectGpoApoderChanged()">
	<option value='0'>(Seleccione)</option>
	<%for(ApoderadosGruposApoBean grupoApo:elems){%>
		<option value="<%=grupoApo.getNomCatVal()%>"><%=grupoApo.getNomCatVal()%></option>
	<%}%>
	</select>
</body>
</html>
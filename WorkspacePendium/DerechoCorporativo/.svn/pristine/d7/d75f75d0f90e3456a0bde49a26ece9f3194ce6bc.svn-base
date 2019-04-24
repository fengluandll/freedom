<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="mx.com.televisa.derechocorporativo.util.FacesUtils"%>
<%@page import="java.util.List"%>
<%@page import="mx.com.televisa.derechocorporativo.model.*"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@include file="/css/kaz-styles.jsp"%>
<script src="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.min.js"></script> 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.css">
<style type="text/css">
.centerDEHasta {
	text-align: center;
}
</style>
<script type="text/javascript">

function submitForm(){
	var desde = document.getElementById("selectDesde").value;
	var hasta = document.getElementById("selectHasta").value;
	if(desde != 0 && hasta != 0){
		document.getElementById("frmCopia").submit();
	}else{
		swal("Es requerido seleccionar las escrituras")
	}
}
</script>
</head>
<body>
<% String idEmpresa = FacesUtils.getSessionBean().getIdCurrentEmpresa();

	List<CatalogElementV2> elems = new Catalog().dameTodasEscrituras(Integer.parseInt(idEmpresa)); 
	List<CatalogElementV2> elemsHasta = new Catalog().dameEscrituras(Integer.parseInt(idEmpresa));%>
<form id="frmCopia" name="frmCopia" method="post" action="/DerechoCorporativo/faces/CopiaEscrituraServlet">
<input type="hidden" id="idEmpresa" name="idEmpresa" value="<%=idEmpresa %>"/>
<table width="90%" border="0" cellpadding="1" align="center">
  <tr>
    <th colspan="4" scope="col" style="font-size: 18px;">Copiar Escritura</th>
     
  </tr>
  <tr>
  	<th colspan="4" scope="col" style="font-size: 16px;color: green;"><%=session.getAttribute("msgCopyEscritura") == null ? "" : session.getAttribute("msgCopyEscritura")%></th>
  </tr>
  <%session.removeAttribute("msgCopyEscritura"); %>
  <tr>
  	<td>&nbsp;</td>
  </tr>
  <tr>
  	<td>&nbsp;</td>
  </tr>
  <tr class="centerDEHasta">
    <td><strong>De:</strong></td>
    <td ><select style="width: 200px;" name="selectDesde" id="selectDesde" onchange="setEscritura()">
					      <option value="0">---Selecione---</option>
					    <% for (CatalogElementV2 elem : elems) {%>
					    	<%if(elem.getValC4() != null){%>
					    		<option value="<%= elem.getValC4()+'|'+elem.getIdEmpresa() %>"><%= elem.getValValor()+"    ["+elem.getEmpresa()+"]" %></option>
					    	<%}%>
					    <% }%>
					</select></td>
    <td><strong>Hasta:</strong></td>
    <td><select style="width: 200px;" name="selectHasta" id="selectHasta" onchange="setEscritura()">
					      <option value="0">---Selecione---</option>
					    <% for (CatalogElementV2 elem : elemsHasta) {%>
					    	<%if(elem.getValC4() != null){%>
					    		<option value="<%= elem.getValC4() %>"><%= elem.getValValor() %></option>
					    	<%}%>
					    <% }%>
					</select></td>
  </tr>
   <tr>
  	<td>&nbsp;</td>
  </tr>
  <tr>
    <td colspan="4" style="text-align: center;"><input type="button" value="Copiar" onclick="javascript:submitForm();"/></td>
  </tr>
</table>
</form>
</body>
</html>
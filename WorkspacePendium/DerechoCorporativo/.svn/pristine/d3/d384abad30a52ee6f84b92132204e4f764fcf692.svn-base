<%@ page language="java" contentType="text/html; UTF-8"
    pageEncoding="UTF-8" import="mx.com.televisa.derechocorporativo.bean.*,
                                 mx.com.televisa.derechocorporativo.util.TextFormatter
     "%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%@include file="/css/kaz-styles.jsp"%>
<script src="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.min.js"></script> 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.css">
<%@include file="/jsp/components/session_timeout/session_timeout.jsp"%>
<style type="text/css">
.btnRight {
	text-align: right;
}
a:hover {
	background-image:
		url("<c:out value='${applicationBean.contextPath}'/>/img/Back2.png");
}
</style>
<script type="text/javascript">
function closeForm(){
	window.location = '/DerechoCorporativo/faces/jsp/admin/adminCatalog.jsp';
}
function validaForm(){
	var codigo = document.getElementById('txtCodigo').value.trim();
	var nombre = document.getElementById('txtNombre').value.trim();
	var desc = document.getElementById('txtDesc').value.trim();
	if(codigo == ''){
		swal("Es requerido capturar el Código")
		}else if(nombre == ''){
			swal("Es requerido capturar el nombre")
		}else if(desc == ''){
			swal("Es requerido capturar la descripción")
		}else{
			document.getElementById("frmEditCata").submit();
			}
}
</script>
</head>
<body>
<% CatalogoBean catalogoBean = (CatalogoBean)session.getAttribute("catalogoBean"); 
   FlexColumnsCatagoBean flexColumnsCatagoBean = (FlexColumnsCatagoBean)session.getAttribute("flexColumnsCatagoBean"); 
  // String cata = request.getParameter("nomCatalogo");
  String cata = catalogoBean.getNombre();
   %>
   
		<table width="100%" align="center">
			<tr>
				<td><jsp:include
						page="/jsp/components/pages_border/open-pre-title.jsp"></jsp:include>
				
						Administraci&oacute;n de Cat&aacute;logo: <u><%= cata %></u>
					
				<jsp:include
						page="/jsp/components/pages_border/open-post-title.jsp"></jsp:include>





<% String msgDupli = (String)request.getSession().getAttribute("msgDuplicado"); %>
<form id="frmEditCata" name="frmEditCata" method="post" action="/DerechoCorporativo/faces/GuardaCatalogoServlet?action=edita">


<table width="80%" border="0" align="center" cellspacing="0" cellpadding="0">
	<tr>
		
		<th width="100%" align="left"><font style="color: red;"> <%= msgDupli == null ? "" : msgDupli %> </font></th>
		<th>&nbsp;</th>
	</tr>
	<tr>
		<th>&nbsp;</th>
	</tr>
  <tr>
  	<th width="100%" align="center" style="font-size: 18px;"><font style=""><%--= cata --%></font></th>
  </tr>
  <tr style="background-image: url('/DerechoCorporativo/img/Back2.png')">
    <th width="100%" align="center"><font style="color: white;"> Edita Cat&aacute;logo  </font></th>
    <td align="right"><a href="#"><img src="/DerechoCorporativo/img/close.png" onclick="closeForm()"></a></td>
  </tr>
  
  <tr>
  	<td colspan="2">
  	<input name="idCatalogo" type="hidden" value="<%= catalogoBean.getIdCatalogo() %>">
  	<table width="80%" border="0">
  	<tr>
    <td>C&oacute;digo:</td>
    <td>
      <input type="text" name="txtCodigo" id="txtCodigo" value="<%= catalogoBean.getCodigo() == null? "" : TextFormatter.ToFormatHtml( catalogoBean.getCodigo().trim() )%>" title="<%= catalogoBean.getCodigo() == null? "" : TextFormatter.ToFormatHtml( catalogoBean.getCodigo().trim() ) %>"/>
    </td>
  </tr>
  <tr>
    <td>Nombre:</td>
    <td><input type="text" name="txtNombre" id="txtNombre" value="<%= catalogoBean.getNombre() == null ? "" : TextFormatter.ToFormatHtml( catalogoBean.getNombre().trim() )%>" title="<%= catalogoBean.getNombre() == null ? "" : TextFormatter.ToFormatHtml( catalogoBean.getNombre().trim() ) %>"/></td>
  </tr>
  <tr>
    <td>Descripci&oacute;n:</td>
    <td><input type="text" name="txtDesc" id="txtDesc" value="<%= catalogoBean.getDescripcion() == null? "" : TextFormatter.ToFormatHtml( catalogoBean.getDescripcion().trim() )%>" title="<%= catalogoBean.getDescripcion() == null? "" : TextFormatter.ToFormatHtml( catalogoBean.getDescripcion().trim() ) %>"/></td>
  </tr>
<!--   
  <tr>
    <td>Atributo1:</td>
    <td><input type="text" name="txtAttr1" id="txtAttr1" value="<%= catalogoBean.getAttr1() %>"/></td>
  </tr>
  <tr>
    <td>Atributo2:</td>
    <td><input type="text" name="txtAttr2" id="txtAttr2" value="<%= catalogoBean.getAttr2() %>"/></td>
  </tr>
  <tr>
    <td>Atributo3:</td>
    <td><input type="text" name="txtAttr3" id="txtAttr3" value="<%= catalogoBean.getAttr3() %>"/></td>
  </tr>
  -->
  	</table>
  	</td>
  </tr>
</table>
<p>&nbsp;</p>
<p>&nbsp;</p>
<table width="80%" border="0" align="center" cellspacing="0" cellpadding="0">
  <tr style="background-image: url('/DerechoCorporativo/img/Back2.png')">
    <th width="100%" align="center"><font style="color: white;"> MAPEO DE COLUMNAS DE CATALOGO </font></th>
  </tr>
  <tr>
  	<td colspan="2">
  	<table width="80%" border="0">
  	<tr>
    <td>Identificador:</td>
    <td>
      <input type="text" name="txtIdenti" id="txtIdenti" value="<%= flexColumnsCatagoBean.getIdentificador() == null ? "" : TextFormatter.ToFormatHtml( flexColumnsCatagoBean.getIdentificador().trim() )%>" title="<%= flexColumnsCatagoBean.getIdentificador() == null ? "" : TextFormatter.ToFormatHtml( flexColumnsCatagoBean.getIdentificador().trim() ) %>"/>
    </td>
  </tr>
  <tr>
    <td>Nombre:</td>
    <td><input type="text" name="txtNombreC" id="txtNombreC" value="<%=flexColumnsCatagoBean.getNombre() == null ? "" : TextFormatter.ToFormatHtml( flexColumnsCatagoBean.getNombre().trim() ) %>" title="<%=flexColumnsCatagoBean.getNombre() == null ? "" : TextFormatter.ToFormatHtml( flexColumnsCatagoBean.getNombre().trim() )%>"/></td>
  </tr>
  <tr>
    <td>Valor:</td>
    <td><input type="text" name="txtValor" id="txtValor" value="<%= flexColumnsCatagoBean.getValor() == null ? "" : TextFormatter.ToFormatHtml( flexColumnsCatagoBean.getValor().trim() ) %>" title="<%= flexColumnsCatagoBean.getValor() == null ? "" : TextFormatter.ToFormatHtml( flexColumnsCatagoBean.getValor().trim() )%>"/></td>
  </tr>
  <tr>
    <td>Descripci&oacute;n:</td>
    <td><input type="text" name="txtDescC" id="txtDescC" value="<%= flexColumnsCatagoBean.getDescripcion() == null ? "" : TextFormatter.ToFormatHtml( flexColumnsCatagoBean.getDescripcion().trim() ) %>" title="<%= flexColumnsCatagoBean.getDescripcion() == null ? "" : TextFormatter.ToFormatHtml( flexColumnsCatagoBean.getDescripcion().trim() )%>"/></td>
  </tr>
  <tr>
    <td>Atributo1:</td>
    <td><input type="text" name="txtAttr1" id="txtAttr1" value="<%= flexColumnsCatagoBean.getAttr1() == null ? "" : TextFormatter.ToFormatHtml( flexColumnsCatagoBean.getAttr1().trim() )%>" title="<%= flexColumnsCatagoBean.getAttr1() == null ? "" : TextFormatter.ToFormatHtml( flexColumnsCatagoBean.getAttr1().trim() )%>"/></td>
  </tr>
  <tr>
    <td>Atributo2:</td>
    <td><input type="text" name="txtAttr2" id="txtAttr2" value="<%= flexColumnsCatagoBean.getAttr2() == null ? "" : TextFormatter.ToFormatHtml( flexColumnsCatagoBean.getAttr2().trim() ) %>" title="<%= flexColumnsCatagoBean.getAttr2() == null ? "" : TextFormatter.ToFormatHtml( flexColumnsCatagoBean.getAttr2().trim() )%>"/></td>
  </tr>
  <tr>
    <td>Atributo3:</td>
    <td><input type="text" name="txtAttr3" id="txtAttr3" value="<%= flexColumnsCatagoBean.getAttr3() == null ? "" : TextFormatter.ToFormatHtml( flexColumnsCatagoBean.getAttr3().trim() ) %>" title="<%= flexColumnsCatagoBean.getAttr3() == null ? "" : TextFormatter.ToFormatHtml( flexColumnsCatagoBean.getAttr3().trim() )%>"/></td>
  </tr>
  
  	</table>
  	</td>
  </tr>
  <tr>
    <td colspan="2" class="btnRight"><input type="button" value="Guardar" onclick="validaForm();"></td>
  </tr>
</table>
</form>



				<jsp:include
					page="/jsp/components/pages_border/close.jsp"></jsp:include></td>
		</tr>
	</table>
<%  session.removeAttribute("catalogoBean"); 
	session.removeAttribute("flexColumnsCatagoBean");
	request.getSession().removeAttribute("msgDuplicado");%>


</body>
</html>
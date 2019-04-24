<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="mx.com.televisa.derechocorporativo.bean.*" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.min.js"></script> 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.css">
<%@include file="/jsp/components/session_timeout/session_timeout.jsp"%>

<%@include file="/css/kaz-styles.jsp"%>

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
	/*var identiCampo = document.getElementById('txtIdenti').value;
	var nomCampo = document.getElementById('txtNombreC').value;
	var valorCampo = document.getElementById('txtValor').value;
	var descCampo = document.getElementById('txtDescC').value;
	var attr1 = document.getElementById('txtAttr1').value;
	var attr2 = document.getElementById('txtAttr2').value;
	var attr3 = document.getElementById('txtAttr3').value;*/
	txtIdenti
	if(codigo == ''){
			swal("Es requerido capturar el Código")
		}else if(nombre == ''){
			swal("Es requerido capturar el nombre")
		}else if(desc == ''){
			swal("Es requerido capturar la descripción")
		}/*else if(identiCampo == ''){
			swal("Es requerido capturar el identificador de columna")
		}else if(nomCampo == ''){
			swal("Es requerido capturar el nombre de columna")
		}else if(valorCampo == ''){
			swal("Es requerido capturar el valor de columna")
		}else if(descCampo == ''){
			swal("Es requerido capturar la descripción de columna")
		}else if(attr1 == ''){
			swal("Es requerido capturar atributo1 de columna")
		}else if(attr2 == ''){
			swal("Es requerido capturar atributo2 de columna")
		}else if(attr3 == ''){
			swal("Es requerido capturar atributo3 de columna")
		}
			*/

		else {
			document.getElementById("frmNvoCata").submit();
			}
}
</script>
</head>
<body>




<table width="100%" align="center">
			<tr>
				<td><jsp:include
						page="/jsp/components/pages_border/open-pre-title.jsp"></jsp:include>
				
						Administraci&oacute;n de Cat&aacute;logos
		
					
				<jsp:include
						page="/jsp/components/pages_border/open-post-title.jsp"></jsp:include>



<% String msgDupli = (String)request.getSession().getAttribute("msgDuplicado"); %>
<form id="frmNvoCata" name="frmNvoCata" method="post" action="/DerechoCorporativo/faces/GuardaCatalogoServlet?action=nuevo">
<p>&nbsp;</p>
<p>&nbsp;</p>
<table width="80%" border="0" align="center" cellspacing="0" cellpadding="0">
	<tr>
		
		<th width="100%" align="left"><font style="color: red;"> <%= msgDupli == null ? "" : msgDupli %> </font></th>
		<th>&nbsp;</th>
	</tr>
	<tr>
		<th>&nbsp;</th>
	</tr>
	<% request.getSession().removeAttribute("msgDuplicado");
	CatalogoBean catalogoBean = (CatalogoBean)request.getSession().getAttribute("catalogoBean");
	FlexColumnsCatagoBean flexColumnsCatagoBean = (FlexColumnsCatagoBean)request.getSession().getAttribute("flexColumnsCatagoBean");
	String codigo = "";
	String nombre = "";
	String desc   = "";
	String identificador = "";
	String nomMapeo = "";
	String valor = "";
	String descMapeo = "";
	String attr1 	 = "";
	String attr2 	 = "";
	String attr3 	 = "";	
	
	if(catalogoBean != null){
		codigo = catalogoBean.getCodigo();
		nombre = catalogoBean.getNombre();
		desc   = catalogoBean.getDescripcion();	
	}
	if(flexColumnsCatagoBean != null){
		identificador = flexColumnsCatagoBean.getIdentificador();
		nomMapeo      = flexColumnsCatagoBean.getNombre();
		valor  		  = flexColumnsCatagoBean.getValor();
		descMapeo	  = flexColumnsCatagoBean.getDescripcion();
		attr1		  = flexColumnsCatagoBean.getAttr1();
		attr2         = flexColumnsCatagoBean.getAttr2();
		attr3		  = flexColumnsCatagoBean.getAttr2();
	}
	%>
	
  <tr style="background-image: url('/DerechoCorporativo/img/Back2.png')">
    <th width="100%" align="center"><font style="color: white;"> Nuevo Cat&aacute;logo </font></th>
    <td align="right"><a href="#"><img src="/DerechoCorporativo/img/close.png" onclick="closeForm()"></a></td>
  </tr>
  <tr>
  	<td colspan="2">
  	<table width="80%" border="0">
  	<tr>
    <td>C&oacute;digo:</td>
    <td>
      <input type="text" name="txtCodigo" id="txtCodigo" value="<%= codigo != null ? codigo : "" %>"/>
    </td>
  </tr>
  <tr>
    <td>Nombre:</td>
    <td><input type="text" name="txtNombre" id="txtNombre" value="<%= nombre != null ? nombre : "" %>" /></td>
  </tr>
  <tr>
    <td>Descripci&oacute;n:</td>
    <td><input type="text" name="txtDesc" id="txtDesc" value="<%= desc != null ? desc : "" %>" /></td>
  </tr>
<!--   
  <tr>
    <td>Atributo1:</td>
    <td><input type="text" name="txtAttr1" id="txtAttr1" /></td>
  </tr>
  <tr>
    <td>Atributo2:</td>
    <td><input type="text" name="txtAttr2" id="txtAttr2" /></td>
  </tr>
  <tr>
    <td>Atributo3:</td>
    <td><input type="text" name="txtAttr3" id="txtAttr3" /></td>
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
      <input type="text" name="txtIdenti" id="txtIdenti" value="<%= identificador != null ? identificador : "" %>"/>
    </td>
  </tr>
  <tr>
    <td>Nombre:</td>
    <td><input type="text" name="txtNombreC" id="txtNombreC" value="<%= nomMapeo != null ? nomMapeo : "" %>"/></td>
  </tr>
  <tr>
    <td>Valor:</td>
    <td><input type="text" name="txtValor" id="txtValor" value="<%= valor != null ? valor : "" %>" /></td>
  </tr>
  <tr>
    <td>Descripci&oacute;n:</td>
    <td><input type="text" name="txtDescC" id="txtDescC" value="<%= descMapeo != null ? descMapeo : "" %>" /></td>
  </tr>
  <tr>
    <td>Atributo1:</td>
    <td><input type="text" name="txtAttr1" id="txtAttr1" value="<%= attr1 != null ? attr1 : "" %>" /></td>
  </tr>
  <tr>
    <td>Atributo2:</td>
    <td><input type="text" name="txtAttr2" id="txtAttr2" value="<%= attr2 != null ? attr2 : "" %>"/></td>
  </tr>
  <tr>
    <td>Atributo3:</td>
    <td><input type="text" name="txtAttr3" id="txtAttr3" value="<%= attr3 != null ? attr3 : "" %>" /></td>
  </tr>
  
  	</table>
  	</td>
  </tr>
  <tr>
    <td colspan="2" class="btnRight"><input type="button" value="Guardar" onclick="validaForm();"></td>
  </tr>
</table>
</form>

<%  session.removeAttribute("catalogoBean"); 
	session.removeAttribute("flexColumnsCatagoBean"); %>




				<jsp:include
					page="/jsp/components/pages_border/close.jsp"></jsp:include></td>
		</tr>
	</table>



</body>
</html>
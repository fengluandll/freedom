<%@page import="mx.com.televisa.derechocorporativo.daos.ClasificacionDAO"%>
<%@page import="java.util.List"%>
<%@page import="mx.com.televisa.derechocorporativo.bean.PaisBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	window.location = '/DerechoCorporativo/faces/jsp/admin/adminEmpresas.jsp';
}
function validaForm(){
	var nomCorto = document.getElementById('txtNomcorto').value.trim();
	var nombre = document.getElementById('txtNombre').value.trim();
	
	if(nomCorto == ''){
			swal("Es requerido capturar el nombre corto")
		}else if(nombre == ''){
			swal("Es requerido capturar el nombre")
		}else {
			document.getElementById("frmNvaEmpr").submit();
		}
}

function conMayusculas(field) {
    field.value = field.value.toUpperCase()
}
</script>
</head>
<body>
<table width="100%" align="center">
			<tr>
				<td><jsp:include
						page="/jsp/components/pages_border/open-pre-title.jsp"></jsp:include>
				
						Administraci&oacute;n de Empresas
					
				<jsp:include
						page="/jsp/components/pages_border/open-post-title.jsp"></jsp:include>



<% ClasificacionDAO clasificacionDAO = new ClasificacionDAO();
    List<PaisBean> listPaises = clasificacionDAO.damePaisesCompleto(); %>
<form id="frmNvaEmpr" name="frmNvaEmpr" method="post" action="/DerechoCorporativo/faces/CargaEmpresasServlet?action=nuevo">
<p>&nbsp;</p>
<p>&nbsp;</p>
<table width="80%" border="0" align="center" cellspacing="0" cellpadding="0">
  <tr style="background-image: url('/DerechoCorporativo/img/Back2.png')">
    <th width="100%" align="center"><font style="color: white;"> Nueva Empresa </font></th>
    <td align="right"><a href="#"><img src="/DerechoCorporativo/img/close.png" onclick="closeForm()"></a></td>
  </tr>
  <tr>
  	<td colspan="2">
  	<table width="80%" border="0">
        <tr>
            <td>Nombre:</td>
           <td><input type="text" name="txtNombre" id="txtNombre" onChange="conMayusculas(this)"/></td>
       </tr>
  	   <tr>
           <td>Nombre Corto:</td>
           <td>
           <input type="text" name="txtNomcorto" id="txtNomcorto" onChange="conMayusculas(this)" />
           </td>
      </tr>

  <tr>
    <td>RFC:</td>
    <td><input type="text" name="txtRfc" id="txtRfc" /></td>
  </tr>
  
  <tr>
    <td>Pa&iacute;s:</td>
    <td><select name="txtPais" id="txtPais" style="width: 165px;">
    		<option value="0">---Seleccione---</option>
    		<% for(PaisBean pai : listPaises){ %>
    		<option value="<%=pai.getIdCatalogoValor()+"|"+pai.getValCatVal()%>"><%=pai.getValCatVal()%></option>
    		<%} %>
       </select> </td>
  </tr>
  <!--<tr>
    <td>Responsable:</td>
    <td><input type="text" name="txtResponsable" id="txtResponsable" /></td>
  </tr>-->
   <tr>
    <td colspan="2" class="btnRight"><input type="button" value="Guardar" onclick="validaForm();"></td>
  </tr>
  	</table>
  	</td>
  </tr>
</table> 
</form>
	<jsp:include
		page="/jsp/components/pages_border/close.jsp"></jsp:include></td>
	</tr>
</table>
</body>
</html>
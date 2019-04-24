<%@ page language="java" contentType="text/html; UTF-8"
    pageEncoding="UTF-8" import="mx.com.televisa.derechocorporativo.bean.*,mx.com.televisa.derechocorporativo.daos.*,java.util.List, mx.com.televisa.derechocorporativo.modules.dynhtml.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%@include file="/css/kaz-styles.jsp"%>
<script src="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.min.js"></script> 
<script src="<%=request.getContextPath()%>/jsp/captura/part/pupUp.js"></script>
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
	window.location = '/DerechoCorporativo/AdminRolesServlet';
}
function validaForm(){
	var nombre = document.getElementById('txtNombre').value;
	var desc = document.getElementById('txtDesc').value;
	var exp = document.getElementById('txtExp').value;

	if(nombre == ''){
			swal("Es requerido capturar el Nombre del Rol")
		}else if(desc == ''){
			swal("Es requerido capturar una descripci&oacute;n del Rol")
		}else if(exp == ''){
			swal("Es requerido capturar el número de Días en los que expira el Password asignado a usuarios con ese Rol ")
		}else { 
			document.getElementById("frmEditRol").submit();
		}
}

function isNumberKey(evt)
{
   var charCode = (evt.which) ? evt.which : event.keyCode
   if (charCode > 31 && (charCode < 48 || charCode > 57))
      return false;

   return true;
}

function updateCheck(box){
	
	var vis = (box.checked) ? true : false;
	var id = box.id;
	var ids = id.split('_');
	var idParent = ids[2];
	var idChild  = ids[1];
	var bandera = false;
	
	var elems = document.querySelectorAll('input[type="checkbox"][class="check_secciones"][id$="_'+idChild+'"]');

	//alert(idParent);
	if(idParent != 0 && vis) {
		document.getElementById("chk_"+idParent+"_0").checked = true;	
	}
	
	if (idParent ==0 ){
		for (var i = 0, len = elems.length; i < len; i++){
		    elems[i].checked = vis; 
		    //alert(elems[i].id);
		}
	}
	

	
	if(idParent != 0 && !vis){
		var elems2 = document.querySelectorAll('input[type="checkbox"][class="check_secciones"][id$="_'+idParent+'"]');
		for(var j = 0, len = elems2.length; j < len; j++){
			if(elems2[j].checked){
				bandera = true;
				//alert('true_');
			}
		}
		
		if(!bandera){
			document.getElementById("chk_"+idParent+"_0").checked = false;	
		}
	}
	
}
</script>
</head>
<body>

<table width="100%" align="center">
			<tr>
				<td><jsp:include
						page="/jsp/components/pages_border/open-pre-title.jsp"></jsp:include>
				
						Administraci&oacute;n de Roles
					
				<jsp:include
						page="/jsp/components/pages_border/open-post-title.jsp"></jsp:include>




<form id="frmEditRol" name="frmEditRol" method="post" action="/DerechoCorporativo/faces/GuardaRolServlet?action=edita">
<p>&nbsp;</p>
<p>&nbsp;</p>
<% RolesDAO rolDAO = new RolesDAO();
   RolBean rolBean     = (RolBean) session.getAttribute("rolBean");
   List<MenuBean> luMenuElements = rolDAO.getNewMenuElements(rolBean.getIdRol());
   List<PestaniaBean> listPest  = rolDAO.getPestanias(rolBean.getIdRol());
 %>
<table width="80%" border="0" align="center" cellspacing="0" cellpadding="0">
  <tr style="background-image: url('/DerechoCorporativo/img/Back2.png')">
    <th width="100%" align="center"><font style="color: white;"> Edita Rol </font></th>
    <td align="right"><a href="#"><img src="/DerechoCorporativo/img/close.png" onclick="closeForm()"></a></td>
  </tr>
  <tr>
  	<td colspan="2">
  	<table width="80%" border="0">
  	<tr>
    <td><input name="idRol" type="hidden" value="<%= rolBean.getIdRol() %>">Nombre:</td>
    <td>
      <input type="text" name="txtNombre" id="txtNombre"  maxlength = "20"  value="<%=rolBean.getNomName() %>" title="<%=rolBean.getNomName() %>"/>
    </td>
  </tr>
  <tr>
    <td>Descripci&oacute;n:</td>
    <td><input type="text" name="txtDesc" id="txtDesc" maxlength = "20" value="<%=rolBean.getDesDescription()%>" title="<%=rolBean.getDesDescription()%>"/></td>
  </tr>
  <tr>
    <td>Expiraci&oacute;n Password (D&iacute;as)</td>
    <td><input type="text" name="txtExp" id="txtExp" value="<%=rolBean.getNumPasswordExpirationDays()%>" title="<%=rolBean.getNumPasswordExpirationDays()%>" onkeypress="return isNumberKey(event)"/></td>
  </tr>
  	</table>
  	</td>
  </tr>
</table>
<p>&nbsp;</p>
<p>&nbsp;</p>
<table width="80%" border="0" align="center" cellspacing="0" cellpadding="0">
  <tr style="background-image: url('/DerechoCorporativo/img/Back2.png')">
    <th width="100%" align="center"><font style="color: white;"> Otorgar permisos sobre las secciones: </font></th>
  </tr>
  <tr>
  	<td colspan="2">
  	<table width="80%" border="0">
<%    for(MenuBean menu: luMenuElements){ %>
		<tr>
		<td><input type="checkbox" class="check_secciones" id="chk_<%=menu.getIdMenuElement() %>_<%=menu.getIdMenuElementParent() %>" name="chk_<%=menu.getIdMenuElement() %>" 
				value="1 " <%=(menu.getIsEnabled()==1)?"checked":"" %> onclick="updateCheck(this);" ><%=menu.getNomMenu() %><br></td>
		</tr>	
<%} %>
  	</table>
  	</td>
  </tr>
  
    <tr><td>&nbsp&nbsp</td></tr>
  <tr style="background-image: url('/DerechoCorporativo/img/Back2.png')">
    <th width="100%" align="center"><font style="color: white;"> Otorgar permisos sobre las siguientes pestañas: </font></th>
  </tr>
  <tr>
  	<td colspan="2">
  	<table width="80%" border="0">
<%	for(PestaniaBean pestania: listPest){   	%>	
		<tr>
		<td width="100%"><input type="checkbox" class="check_pest" id="chkp_<%=pestania.getIdSeccion()%>_<%=rolBean.getIdRol()%>" 
			name="chkp_<%=pestania.getIdSeccion()%>_<%=rolBean.getIdRol()%>" value="1 " <%=(pestania.getIdRol()!=0)?"checked":"" %>	>
				&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<%=pestania.getNomSeccion() %><br></td>
		<td>

<%	} %>
  	</table>
  	</td>
  </tr>
  
   <tr><td>&nbsp&nbsp</td></tr>
  <tr style="background-image: url('/DerechoCorporativo/img/Back2.png')">
    <th width="100%" align="center"><font style="color: white;"> Revocar permisos sobre las siguientes empresas: </font></th>
  </tr>
  <tr>
  	<td colspan="2">
  	<table width="80%" border="0">

		<tr>
		<td width="100%">
		<%= MultiSelectList.getMultiSelectListEmp(rolBean.getRevokeEnterprises(), "0") %>
		</td>
		<td>

  	</table>
  	</td>
  </tr>
  
  <tr><td>&nbsp&nbsp</td></tr>
  <tr style="background-image: url('/DerechoCorporativo/img/Back2.png')">
    <th width="100%" align="center"><font style="color: white;"> Otorgar permisos sobre los siguientes Reportes Predefinidos: </font></th>
  </tr>
  <tr>
  	<td colspan="2">
  	<table width="80%" border="0">

		<tr>
		<td width="100%">
		<%= MultiSelectList.getMultiSelectListReportPre(rolBean.getReportPre(), "0") %>
		</td>
		<td>

  	</table>
  	</td>
  </tr>
  
  <tr><td>&nbsp&nbsp</td></tr>
  <tr style="background-image: url('/DerechoCorporativo/img/Back2.png')">
    <th width="100%" align="center"><font style="color: white;"> Otorgar permisos sobre los siguientes Reportes Personalizados: </font></th>
  </tr>
  <tr>
  	<td colspan="2">
  	<table width="80%" border="0">

		<tr>
		<td width="100%">
		<%= MultiSelectList.getMultiSelectListReportPer(rolBean.getReportPer(), "0") %>
		</td>
		<td>

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



</body>
</html>
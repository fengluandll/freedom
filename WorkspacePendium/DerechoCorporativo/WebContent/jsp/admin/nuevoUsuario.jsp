<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="mx.com.televisa.derechocorporativo.daos.UsersDAO,
    								 mx.com.televisa.derechocorporativo.bean.RolBean,
    								 java.util.*"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.min.js"></script> 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.css">

<%@include file="/css/kaz-styles.jsp"%>
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
	window.location = '/DerechoCorporativo/faces/jsp/admin/adminUsers.jsp';
}
function validaForm(){
	var nomComple = document.getElementById('txtNombrecompleto').value;
	var nombre = document.getElementById('txtNomUsuario').value;
	var pass = document.getElementById('txtPass').value;
	var status = document.getElementById("txtStatus").selectedIndex;
	var cmbRol = document.getElementById("cmbRol").selectedIndex;
	
	if(nomComple == ''){
		swal("Es requerido capturar el nombreCompleto")
		}else if(nombre == ''){
			swal("Es requerido capturar el nombre de usuario")
		}else if(pass == ''){
			swal("Es requerido capturar la contraseña")
		}else if(status == 0){
			swal("Es requerido el Status")
		}else if(cmbRol == 0){
			swal("Es requerido el Rol")
		}else{
			document.getElementById("frmNvoUser").submit();
			}
}
</script>
</head>
<body>




		<table width="100%" align="center">
			<tr>
				<td><jsp:include
						page="/jsp/components/pages_border/open-pre-title.jsp"></jsp:include>
				
						Administraci&oacute;n de Usuarios
					
				<jsp:include
						page="/jsp/components/pages_border/open-post-title.jsp"></jsp:include>




<% UsersDAO userDAO = new UsersDAO();
List<RolBean> listRol = userDAO.dameRoles();
%>
<form id="frmNvoUser" name="frmNvoUser" method="post" action="/DerechoCorporativo/faces/EditaUserServlet?action=nuevo">
<p>&nbsp;</p>
<p>&nbsp;</p>
<table width="80%" border="0" align="center" cellspacing="0" cellpadding="0">
  <tr style="background-image: url('/DerechoCorporativo/img/Back2.png')">
    <th width="100%" align="center"><font style="color: white;"> Nuevo Usuario </font></th>
    <td align="right"><a href="#"><img src="/DerechoCorporativo/img/close.png" onclick="closeForm()"></a></td>
  </tr>
  <tr>
  	<td colspan="2">
  	<table width="80%" border="0">
  	<tr>
  		<c:set var="msg" value='<%= session.getAttribute("mensaje") %>'/>
  		
  		<td colspan="2"><c:if  test="${msg != null}">
  							 <h4 style="color:red;"><%= session.getAttribute("mensaje") %></h4>
  		                </c:if> </td>
  	</tr>
  	<tr>
    <td>Número de empleado:</td>
    <td>
      <c:set var="numE" value='<%= session.getAttribute("numempleado") %>'/>			
      <input type="text" name="txtNumEmpleado" id="txtNumEmpleado" value="<c:if  test="${numE != null}"><%= session.getAttribute("numempleado") %></c:if>"/>
    </td>
    
  </tr>
  	<tr>
    <td>Nombre Completo:</td>
    <td>
      <c:set var="nomC" value='<%= session.getAttribute("nomcompleto") %>'/>			
      <input type="text" name="txtNombrecompleto" id="txtNombrecompleto" value="<c:if  test="${nomC != null}"><%= session.getAttribute("nomcompleto") %></c:if>"/>
    </td>
    
  </tr>
  <tr>
    <td>Nombre de Usuario:</td>
    <c:set var="nomU" value='<%= session.getAttribute("nomUsuario") %>'/>	
    <td><input type="text" name="txtNomUsuario" id="txtNomUsuario" value="<c:if  test="${nomU != null}"><%= session.getAttribute("nomUsuario") %></c:if>"/></td>
  </tr>
  <tr>
    <td>Contraseña:</td>
    <td><input type="password" name="txtPass" id="txtPass" /></td>
  </tr>
  <tr>
    <td>Status:</td>
    
    <td><select id="txtStatus" name="txtStatus">
			<option value="0">---Seleccione---</option>
    		<option value="1"  <%= session.getAttribute("status") != null && session.getAttribute("status").equals("1")?"selected": "" %> > ACTIVO</option>
    		<option value="2"  <%= session.getAttribute("status") != null && session.getAttribute("status").equals("2")?"selected": "" %> > BLOQUEADO</option>
    		<option value="3"  <%= session.getAttribute("status") != null && session.getAttribute("status").equals("3")?"selected": "" %> >EN SESSION</option>
    	</select></td>
  </tr>
  <tr>
    <td>Rol:</td>
    <td><select id="cmbRol" name="cmbRol">
			<option value="0">---Seleccione---</option>
			<% for(RolBean r : listRol){ %>
    		<option value="<%= r.getIdRol() %>" <%= session.getAttribute("rol") != null && session.getAttribute("rol").equals(String.valueOf(r.getIdRol()))?"selected": "" %> ><%= r.getNomName() %></option>
    		<% } %>
    	</select>
    </td>
  </tr>
  	</table>
  	</td>
  </tr>
  <tr>
    <td colspan="2" class="btnRight"><input type="button" value="Guardar" onclick="validaForm();"></td>
  </tr>
</table>
<% session.removeAttribute("nomcompleto");
   session.removeAttribute("nomUsuario");
   session.removeAttribute("status"); 
   session.removeAttribute("rol");
   session.removeAttribute("mensaje");%>
</form>





				<jsp:include
					page="/jsp/components/pages_border/close.jsp"></jsp:include></td>
		</tr>
	</table>
	
	
	
</body>
</html>
<%@page import="mx.com.televisa.derechocorporativo.bean.SessionBean"%>
<%@page import="mx.com.televisa.derechocorporativo.util.FacesUtils"%>
<%@page import="javax.faces.context.FacesContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="mx.com.televisa.derechocorporativo.bean.UserBean,
     								  mx.com.televisa.derechocorporativo.daos.UsersDAO,
    								  mx.com.televisa.derechocorporativo.bean.RolBean,
    								 java.util.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
<script type="text/javascript" src="../../js/jquery/jquery-3.1.1.js"></script>
<%@include file="/css/kaz-styles.jsp"%>
<script src="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.min.js"></script> 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.css">
<%@include file="/jsp/components/session_timeout/session_timeout.jsp"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@page import="mx.com.televisa.derechocorporativo.modules.captura.*"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
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
	var ncomple = document.getElementById('txtNombrecompleto').value;
	var user = document.getElementById('txtNomUsuario').value;
	var pass = document.getElementById('txtStatus').value;
	var cmbRol = document.getElementById("cmbRol").selectedIndex;
	if(ncomple == ''){
		swal("Es requerido capturar el nombre completo")
		}else if(user == ''){
			swal("Es requerido capturar el usuario")
		}else if(pass == ''){
			swal("Es requerido capturar el password")
		}else if(cmbRol == 0){
			swal("Es requerido el Rol")
		}else{
			document.getElementById("frmEditUser").submit();
			}
}

		$(document).ready(function(){
			$("input[name=txtPass]").blur(function () {	 
				$("#txtStatus").val(4).prop('selected', true);
			}); 
		 });

</script>
</head>
<f:view>
<body>

<!-- 
<%
if(FacesUtils.getSession().getAttribute("alertUnblock") != null && FacesUtils.getSession().getAttribute("alertUnblock").toString().equals("1")){%>
<script type="text/javascript">
	swal("Usuario desbloqueado correctamente", "", "success"); 	
</script>
<%
	FacesUtils.getSession().setAttribute("alertUnblock", 0);
}
%>
 -->
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
<form id="frmEditUser" name="frmEditUser" method="post" action="/DerechoCorporativo/faces/EditaUserServlet?action=editaGuarda">
<p>&nbsp;</p>
<p>&nbsp;</p>
<% 
UserBean userBean = (UserBean)session.getAttribute("userBean");

FacesUtils.getSession().setAttribute("idUserMod", userBean.getIdUser());

%>

<c:set var="id" value="${userBeanUn}" scope="request" ></c:set>
<table width="80%" border="0" align="center" cellspacing="0" cellpadding="0">
  <tr style="background-image: url('/DerechoCorporativo/img/Back2.png')">
    <th width="100%" align="center"><font style="color: white;"> Edita Usuario </font></th>
    <td align="right"><a href="#"><img src="/DerechoCorporativo/img/close.png" onclick="closeForm()"></a></td>
  </tr>
  <tr>
  		<c:set var="msg" value='<%= session.getAttribute("mensaje") %>'/>
  		
  		<td colspan="2"><c:if  test="${msg != null}">
  							 <h4 style="color:red;"><%= session.getAttribute("mensaje") %></h4>
  		                </c:if> </td>
  	</tr>
  <tr>
  	<td colspan="2">
  	<input name="idUser" type="hidden" value="<%= userBean.getIdUser() %>">
  	<table width="80%" border="0">
  	<tr>
    <td>Número de empleado:</td>
    
    <td>
      <input type="text" name="txtNumEmpleado" id="txtNumEmpleado" value="<%= userBean.getNumEmpleado() %>" title="<%= userBean.getNumEmpleado() %>"/>
    </td>
  </tr>
  	<tr>
    <td>Nombre Completo:</td>
    
    <td>
      <input type="text" name="txtNombrecompleto" id="txtNombrecompleto" value="<%= userBean.getNomUserLongName() %>" title="<%= userBean.getNomUserLongName() %>"/>
    </td>
  </tr>
  <tr>
    <td>Nombre de Usuario:</td>
    <td><input type="text" name="txtNomUsuario" id="txtNomUsuario" value="<%= userBean.getNomUsername() %>" title="<%= userBean.getNomUsername() %>"/></td>
  </tr>
  <tr>
    <td>Contraseña:</td>
    <td><input type="password" name="txtPass" id="txtPass" value="<%= userBean.getCvePassword() %>"/></td>
  </tr>
  <tr>
    <td>Status:</td>
    <td><select id="txtStatus" name="txtStatus">
    		<option value="1" <%= userBean.getStatus().equals("ACTIVO")?"selected": "" %> >ACTIVO</option>
    		<option value="2" <%= userBean.getStatus().equals("BLOQUEADO")?"selected":"" %> >BLOQUEADO</option>
    		<option value="3" <%= userBean.getStatus().equals("EN SESSION")?"selected":"" %> >EN SESSION</option>
    		<option value="4" <%= userBean.getStatus().equals("CAMBIO DE CONTRASEÑA")?"selected":"" %> >CAMBIO DE CONTRASEÑA</option>
    	</select>
    </td>
  </tr>
    <tr>
    <td>Rol:</td>
    <td><select id="cmbRol" name="cmbRol">
			<option value="0">---Seleccione---</option>
			<% for(RolBean r : listRol){ %>
    		<option value="<%= r.getIdRol() %>"  <%= userBean.getIdRol() == r.getIdRol()?"selected":"" %> ><%= r.getNomName() %></option>
    		<% } %>
    	</select>
    </td>
  </tr>
  <!-- 
  <tr>
  	<td>
  		Desbloquear captura de usuario: 
  	</td>
  	<td>
  		<h:form>
  			<h:commandButton action="#{menuBean.unblock}" value="Desbloquear"></h:commandButton>
  		</h:form>
  	</td>
  </tr> 
  -->
  	</table>
  	</td>
  </tr>
  <tr>
    <td colspan="2" class="btnRight"><input type="button" value="Guardar" onclick="validaForm();"></td>
  </tr>
</table>
<% session.removeAttribute("mensaje"); %>
</form>







				<jsp:include
					page="/jsp/components/pages_border/close.jsp"></jsp:include></td>
		</tr>
	</table>




</body></f:view>
</html>
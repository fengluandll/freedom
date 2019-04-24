<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="mx.com.televisa.derechocorporativo.bean.RolBean,java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/css/kaz-styles.jsp"%>
<script src="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.min.js"></script> 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.css">
<%@include file="/jsp/components/session_timeout/session_timeout.jsp"%>
<style type="text/css">
a:hover {
	background-color: #ACBCED;
}
.infoTab {
	text-align: left;
	font-size: 12px;
}
.infoTab2 {
	text-align: left;
	font-size: 12px;
	background:#DCE8F6;
}
</style>
<script type="text/javascript">

function cambiar_color_over(celda){ 
	   celda.style.backgroundColor="#7D7FD2" 
	} 
function cambiar_color_out(celda){ 
   celda.style.backgroundColor="#C1D5F0" 
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




<% List<RolBean> listRol = (List<RolBean>)session.getAttribute("listRol"); %>

<table width="50%" border="0" align="center" cellpadding="0">
    <tr>
  		<c:set var="msg" value='<%= session.getAttribute("msgNoElimina") %>'/>
  		
  		<td colspan="2"><c:if  test="${msg != null}">
  							 <h4 style="color:red;"><%= session.getAttribute("msgNoElimina") %></h4>
  		                </c:if> </td>
  </tr>
  <tr>
    <td width="87%" class="nvoCta">Nuevo Rol</td>
    <td class="centerNew"><a href="<c:out value="${applicationBean.contextPath}"/>/faces/jsp/admin/nuevoRol.jsp"><abbr title="Nuevo"><img src="../../img/icons/new.png" alt="Nuevo" /></abbr></a></td>
  </tr>
</table>

<table width="95%" border="0" cellspacing="0" 

cellpadding="0" align="center" class="flexTable" style="background-color:#FFFFFF">
  <tr>
    <th class="tableHeader" width="20%" scope="col">Nombre Rol</th>
    <th class="tableHeader" width="20%" scope="col">Descripci&oacute;n</th>
    <th class="tableHeader" width="15%" scope="col">Expiraci&oacute;n Password (D&iacute;as)</th>
    <th class="tableHeader" width="5%" scope="col">Editar</th>
    <th class="tableHeader" width="5%" scope="col">Eliminar</th>
  </tr>
  <%int count = 0; 
  if(listRol.size() > 0){
  	for(RolBean rol : listRol){
  		count ++;
  		if(count % 2 == 0){
  		%>
  		<tr class="infoTab">	
  		<%}else{ %>
  	  	<tr class="infoTab2">
  	  	<%} %>
   	  

	  	
	    <td height="40px"><%= rol.getNomName() %></td>
	    <td><%= rol.getDesDescription() %></td>
	    <td><%= rol.getNumPasswordExpirationDays()%></td>
	    
	    <td><a class="encima" href="<c:out value="${applicationBean.contextPath}"/>/EditaRolServlet?action=edita&idRol=<%= rol.getIdRol() %>"><abbr title="Editar"><img alt="Editar" src="../../img/icons/edit.png"></abbr> </a> </td>
	    <!-- <td><a class="encima" href="<c:out value="${applicationBean.contextPath}"/>/EditaRolServlet?action=elimina&idRol=<%= rol.getIdRol() %>"  onclick="return confirm('¿Está seguro que desea eliminar este rol?');"><abbr title="Eliminar"><img alt="Borrar" src="../../img/icons/delete.png"></abbr> </a> </td> -->
	    <td align="center"><a class="encima" href="#"  onclick="swal({ title: 'Confirmar', text: '&iquest;Est&aacute; seguro que desea borrar este rol?', type: 'warning', showCancelButton: true, confirmButtonColor: '#DD6B55', confirmButtonText: 'Si', closeOnConfirm: false }, function(){ window.location = '/DerechoCorporativo/faces/EditaRolServlet?action=elimina&idRol=<%= rol.getIdRol() %>'; return false}) "><abbr title="Eliminar"><img alt="Borrar" src="../../img/icons/delete.png"></abbr> </a> </td>
	  </tr>
   <%
       }
  }else{   %>
	<tr class="infoTab">	
    <td colspan="10"><span>No existen roles por mostrar </span></td>
    </tr>
<%} %>
</table>





				<jsp:include
					page="/jsp/components/pages_border/close.jsp"></jsp:include></td>
		</tr>
	</table>
	
	
	
</body>
</html>
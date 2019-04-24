<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,
    							 mx.com.televisa.derechocorporativo.bean.EmpresasBean "%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.min.js"></script> 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.css">
<%@include file="/jsp/components/session_timeout/session_timeout.jsp"%>
<%@include file="/css/kaz-styles.jsp"%>
<style type="text/css">
.infoTab {
	text-align: center;
	font-size: 12px;
}
.infoTab2 {
	text-align: center;
	font-size: 12px;
	background:#DCE8F6;
}
.headTab {
	font-size: 14px;
}
.nvoCta {
	text-align: right;
	font-weight: bold;
	font-style: italic;
	color: #06F;
}
.centerNew {
	text-align: center;
}
a:hover {
	background-color: #ACBCED;
}
a:hover.encima{
	background-color: #7D7FD2;
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

<% List<EmpresasBean> listEmpresas = (List<EmpresasBean>)session.getAttribute("listEmpresas");%>
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="flexTable" style="background-color: #FFFFFF">
  <tr>
    <th class="tableHeader" width="25%" scope="col">Nombre </th>
    <th class="tableHeader" width="25%" scope="col"> Nombre corto</th>
    <th class="tableHeader" width="15%" scope="col">RFC</th>
    <th class="tableHeader" width="15%" scope="col">Pa&iacute;s</th>
    <th class="tableHeader" width="5%" scope="col">Editar</th>
    <th class="tableHeader" width="5%" scope="col">Eliminar</th>
  </tr>

  <% 
  	int count = 0;
  	if(listEmpresas.size() > 0){  		
  		for(EmpresasBean empresa : listEmpresas){%>
  		<% 
  		count++;
  		if (count % 2 == 0){%>
  		
        <tr class="infoTab">
        <%}else{%> 
        <tr class="infoTab2">
        <%}%>
  	    <td><%= empresa.getNomEmpresa() %></td>
  	    <td height="40px"><%= empresa.getCveEmpresa() %></td>
  	    <td><%= empresa.getAttr1()==null?"":empresa.getAttr1() %></td>
  	    <td><%= empresa.getAttr2()==null?"":empresa.getAttr2() %></td>
  	    <td><a class="encima" href="<c:out value="${applicationBean.contextPath}"/>/CargaEmpresasServlet?action=edita&idEmpresa=<%= empresa.getIdEmpresa() %>"><abbr title="Editar"><img alt="Editar" src="../../img/icons/edit.png"></abbr> </a> </td>
  	    <!-- <td><a class="encima" href="<c:out value="${applicationBean.contextPath}"/>/CargaEmpresasServlet?action=elimina&idEmpresa=<%= empresa.getIdEmpresa() %>"  onclick="return confirm('¿Está seguro que desea eliminar este registro?');"><abbr title="Eliminar"><img alt="Borrar" src="../../img/icons/delete.png"></abbr> </a> </td> -->
  	    <td align="center"><a class="encima" href="#"  onclick="swal({ title: 'Confirmar', text: '&iquest;Est&aacute; seguro que desea borrar esta empresa?', type: 'warning', showCancelButton: true, confirmButtonColor: '#DD6B55', confirmButtonText: 'Si', closeOnConfirm: false }, function(){ window.location = '/DerechoCorporativo/faces/CargaEmpresasServlet?action=elimina&idEmpresa=<%= empresa.getIdEmpresa() %>'; return false}) "><abbr title="Eliminar"><img alt="Borrar" src="../../img/icons/delete.png"></abbr> </a> </td>
  	  </tr>
  	 
      <%
       
         }	
    //session.removeAttribute("listDetalle");
  	}else{   %>
  		<tr class="infoTab">	
  	    <td colspan="10"><span>No existe informaci&oacute;n para este cat&aacute;logo </span></td>
  	    </tr>
  	<%}%>
	
</table>

</body>
</html>
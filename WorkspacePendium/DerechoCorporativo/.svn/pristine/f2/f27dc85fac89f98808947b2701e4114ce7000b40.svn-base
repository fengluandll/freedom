<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,
    							 mx.com.televisa.derechocorporativo.bean.CatalogoBean,
    							 mx.com.televisa.derechocorporativo.util.TextFormatter
    							  "%>
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

<% List<CatalogoBean> listCatalogo = (List<CatalogoBean>)session.getAttribute("listCatalog"); 
   //System.out.println("listCatalogo.size: "+listCatalogo.size() == null?0:listCatalogo.size());
%>
	<table width="95%" border="0" cellspacing="3" cellpadding="3" align="center" class="flexTable" >
	  <tr>
	    <th class="tableHeader" width="20%" scope="col">Nombre</th>
	    <th class="tableHeader" width="35%" scope="col">Descripción</th>
	    <th class="tableHeader" width="5%" scope="col">Editar</th>
	    <th class="tableHeader" width="5%" scope="col">Eliminar</th>
	    <th class="tableHeader" width="5%" scope="col">Detalle</th>
	  </tr>
  <% 
  	if(listCatalogo.size() > 0){
  		int c = 0;
  		for(CatalogoBean cat : listCatalogo){ 
  	  		String cssClass = "";
  	  		c++;
  	  		if(c%2==0) {
  	  			cssClass = "tableRow2";
  	  		}

  		%>
			  <tr class="<%= cssClass %>" >	
			    <td><%= cat.getNombre() %></td>
			    <td><%= (cat.getDescripcion() != null)? cat.getDescripcion() : "" %></td>
			    <td align="center"><a class="encima" href="<c:out value="${applicationBean.contextPath}"/>/EditaCatalogoServlet?action=edita&idcatalogo=<%= cat.getIdCatalogo() %>&nomCata=<%= cat.getNombre() %>"><abbr title="Editar"><img alt="Editar" src="../../img/icons/edit.png"></abbr> </a> </td>
			    <td align="center"><a class="encima" href="#"  onclick="swal({ title: 'Confirmar', text: '&iquest;Est&aacute; seguro que desea borrar este catalogo y su detalle?', type: 'warning', showCancelButton: true, confirmButtonColor: '#DD6B55', confirmButtonText: 'Si', closeOnConfirm: false }, function(){ window.location = '/DerechoCorporativo/faces/EditaCatalogoServlet?action=elimina&idcatalogo=<%= cat.getIdCatalogo() %>&nomCata=<%= TextFormatter.ToFormatHtml( cat.getNombre() )%>'; return false}) "><abbr title="Eliminar"><img alt="Borrar" src="../../img/icons/delete.png"></abbr> </a> </td>
			    <td align="center"><a class="encima" href="<c:out value="${applicationBean.contextPath}"/>/CargaDetalleCatgoServlet?idcatalogo=<%= cat.getIdCatalogo() %>&nomCata=<%= cat.getNombre() %>"><abbr title="Ir a detalle"><img alt="Ir a detalle" src="../../img/icons/List.png"></abbr> </a></td>
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
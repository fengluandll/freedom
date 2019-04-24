<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,
                                 mx.com.televisa.derechocorporativo.bean.CatalogoBean,
                                 mx.com.televisa.derechocorporativo.bean.DetalleCatagoBean,
                                 mx.com.televisa.derechocorporativo.bean.FlexColumnsCatagoBean,
                                 mx.com.televisa.derechocorporativo.daos.CatalogoDAO"
    							 %>
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
	text-align: left;
	font-size: 12px;
}
.infoTab2 {
	text-align: left;
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

<% 	List<DetalleCatagoBean> listCatalogo = (List<DetalleCatagoBean>)session.getAttribute("listDetalle");
    CatalogoDAO catalogoDAO = new CatalogoDAO();
	FlexColumnsCatagoBean flexColumnsCatagoBean = new FlexColumnsCatagoBean();
	flexColumnsCatagoBean = catalogoDAO.obtenFlexColumn(Integer.parseInt((String)session.getAttribute("IdCatDet")));
	
	String nomCatalogo = "";
	if(request.getParameter("nomCatalogo") != null){
		nomCatalogo = new String(request.getParameter("nomCatalogo").getBytes("ISO-8859-1"),"UTF-8");	
	}
	
%>
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="flexTable" style="background-color: #FFFFFF">
  <tr>
    <th class="tableHeader" width="5%" scope="col"> <%= flexColumnsCatagoBean.getIdentificador() == null ? "Identificador" : flexColumnsCatagoBean.getIdentificador()%></th>
    <th class="tableHeader" width="20%" scope="col"><%= flexColumnsCatagoBean.getNombre() == null ? "Nombre" : flexColumnsCatagoBean.getNombre() %></th>
    <th class="tableHeader" width="20%" scope="col"><%= flexColumnsCatagoBean.getValor() == null ? "Valor" : flexColumnsCatagoBean.getValor() %></th>
    <th class="tableHeader" width="20%" scope="col"><%= flexColumnsCatagoBean.getDescripcion() == null ? "Descripción" : flexColumnsCatagoBean.getDescripcion()%></th>
    <th class="tableHeader" width="5%" scope="col">Editar</th>
    <th class="tableHeader" width="5%" scope="col">Eliminar</th>
  </tr>
  <% 
  	int count = 0;
  	if(listCatalogo.size() > 0){
  		for(DetalleCatagoBean cat : listCatalogo){
  		count++;
  		if(count % 2 == 0){
  		%>
  		<tr class="infoTab">	
  		<%}else{ %>
  	  	<tr class="infoTab2">
  	  	<%} %>
<!--   	  onmouseover="cambiar_color_over(this)" onmouseout="cambiar_color_out(this) -->
  	    <td height="40px"><%= cat.getIdentificador() %></td>
  	    <td><%=cat.getNombre()%></td>
  	    <td><%=cat.getValor()%></td>
  	    <td><%=cat.getDescripcion()%></td>
  	   <!--  <td><%=cat.getAttr1()%></td> -->
  	    <!-- <td><%=cat.getAttr2()%> </td> -->
  	    <!-- <td><%=cat.getAttr3()%></td> -->
  	    <td align="center"><a class="encima" href="<c:out value="${applicationBean.contextPath}"/>/EditaDetalleCatagServlet?action=edita&idcatalogoVal=<%= cat.getIdCatalogoVal() %>&idcatalogo=<%= cat.getIdCatalogo() %>&nomCatalogo=<%=nomCatalogo%>"><abbr title="Editar"><img alt="Editar" src="../../img/icons/edit.png"></abbr> </a> </td>
  	    <!-- <td><a class="encima" href="<c:out value="${applicationBean.contextPath}"/>/EditaDetalleCatagServlet?action=elimina&idcatalogoVal=<%= cat.getIdCatalogoVal() %>&idcatalogo=<%= cat.getIdCatalogo() %>&nomCatalogo=<%= request.getParameter("nomCatalogo") %>&nomDetalle=<%= cat.getNombre() %>"  onclick="return confirm('¿Está seguro que desea eliminar este registro?');"><abbr title="Eliminar"><img alt="Borrar" src="../../img/icons/delete.png"></abbr> </a> </td> -->
  	    <td align="center"><a class="encima" href="#"  onclick="swal({ title: 'Confirmar', text: '&iquest;Est&aacute; seguro que desea borrar este registro?', type: 'warning', showCancelButton: true, confirmButtonColor: '#DD6B55', confirmButtonText: 'Si', closeOnConfirm: false }, function(){ window.location = '/DerechoCorporativo/faces/EditaDetalleCatagServlet?action=elimina&idcatalogoVal=<%= cat.getIdCatalogoVal() %>&idcatalogo=<%= cat.getIdCatalogo() %>&nomCatalogo=<%= request.getParameter("nomCatalogo") %> <%-- &nomDetalle=<%= cat.getNombre() %> --%>'; return false}) "><abbr title="Eliminar"><img alt="Borrar" src="../../img/icons/delete.png"></abbr> </a> </td>
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
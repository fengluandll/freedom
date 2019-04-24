<%@page import="mx.com.televisa.derechocorporativo.bean.CatalogoBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"

    pageEncoding="UTF-8" import="java.util.*,
    								  mx.com.televisa.derechocorporativo.bean.DetalleCatagoBean,
    								  mx.com.televisa.derechocorporativo.bean.FlexColumnsCatagoBean,
    								  mx.com.televisa.derechocorporativo.daos.CatalogoDAO" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../../js/jquery/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="../../js/jquery/jquery.form.js"></script>
<script src="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.min.js"></script>
 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.css">
<%@include file="/jsp/components/session_timeout/session_timeout.jsp"%>
<%@include file="/css/kaz-styles.jsp"%>

<style type="text/css">
.infoTab {
	text-align: left;
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
	text-align: left;
}
a:hover {
	background-color: #ACBCED;
}
a:hover.encima{
	background-color: #7D7FD2;
}
</style>
<script type="text/javascript">
function openCatal(context){
	window.location = '/DerechoCorporativo/faces/BuscaEmpresaServlet?emp='+empresa;
}
function cambiar_color_over(celda){ 
	   celda.style.backgroundColor="#7D7FD2" 
	} 
function cambiar_color_out(celda){ 
   celda.style.backgroundColor="#C1D5F0" 
}
function busqueda(){
	if(window.event.keyCode == 13){
		doneTypingCatDet();
	}
}
function doneTypingCatDet () {

	var cat = $("#filtroCatDet").val();

	$.ajax({
		  method: "POST",
		  url: "/DerechoCorporativo/CargaDetalleCatgoServlet?action=buscaCatDet",
		  data: 'cat='+cat,
		  cache: false,
		  success: function()
		  {
			  $('#contentCatDet').load("/DerechoCorporativo/faces/jsp/admin/contenidoCatDet.jsp");
	      }
		})
}
</script>
</head>
<body>
<% CatalogoBean catalogoBean = (CatalogoBean)session.getAttribute("detCatalogoBean"); 
   
  String cata = catalogoBean.getNombre();
 // session.removeAttribute("catalogoBean");
   %>
<table width="100%" align="center">
	<tr>
		<td>
			<jsp:include page="/jsp/components/pages_border/open-pre-title.jsp"></jsp:include>
						Administraci&oacute;n de Cat&aacute;logo: <u><%= cata %></u>
			<jsp:include page="/jsp/components/pages_border/open-post-title.jsp"></jsp:include>
			
			<% List<DetalleCatagoBean> listCatalogo = (List<DetalleCatagoBean>)session.getAttribute("listDetalle"); 
			   CatalogoDAO catalogoDAO = new CatalogoDAO();
			   FlexColumnsCatagoBean flexColumnsCatagoBean = new FlexColumnsCatagoBean();
			   flexColumnsCatagoBean = catalogoDAO.obtenFlexColumn(Integer.parseInt(request.getParameter("idCatalogo")));
			   session.setAttribute("IdCatDet", (String)request.getParameter("idCatalogo"));
			   String nomCatalogo = new String(request.getParameter("nomCatalogo").getBytes("ISO-8859-1"),"UTF-8");
			%>

			<table width="90%" border="0" align="center" cellpadding="0">
				<tr>
			  		<c:set var="msg" value='<%= session.getAttribute("msgNoEliminaDetalle") %>'/>  		
			  		<td colspan="2"><c:if  test="${msg != null}">
			  							 <h4 style="color:red;"><%= session.getAttribute("msgNoEliminaDetalle") %></h4>
			  		                </c:if> </td>
			   </tr>
			   <% session.removeAttribute("msgNoEliminaDetalle"); %>
			  	<tr>
				    <td align="left"><a href="<c:out value="${applicationBean.contextPath}"/>/CargaCatalogoServlet"><abbr title="Regresar"><img src="../../img/icons/Navigation-Left-blue-32.png" alt="Regresar" /></abbr></a>
				    </td>
				    <td class="centerNew" style="font-size: 18px;">
			    	<!-- 
			    	<span><strong>Cat&aacute;logo: <%= new String(request.getParameter("nomCatalogo").getBytes("ISO-8859-1"),"UTF-8")%></strong></span>
			    	-->
			    	</td>
			    
				  	<%String lsInteli = (String)session.getAttribute("busqInteliDet");
				  	System.out.println("Inteli: "+lsInteli);%>
			  	<td>
			  		<input 	 style="margin-left: 50px;" 
				  			 id="filtroCatDet" 
				  			 type="text"
				  			 name="textBuscarCatDet" 
				  			 class=""  
				  			 value="" 
				  			 onkeyup="busqueda()"
				  			 <%--<%= lsInteli==null?"":lsInteli %>--%>
				  			 size="30"/>
				  			 <a href="#" id="filtro3CatDet" onclick="doneTypingCatDet();" > <img src="/DerechoCorporativo/img/search.png"></a>
			  	</td>
			    <td width="27%" class="nvoCta">Nuevo Detalle</td>
			    <td class="centerNew"><a href="<c:out value="${applicationBean.contextPath}"/>/faces/jsp/admin/nuevoDetalleCatalogo.jsp?idCatalogo=<%= request.getParameter("idCatalogo") %>&nomCatalogo=<%=nomCatalogo%>"><abbr title="Nuevo"><img src="../../img/icons/new.png" alt="Nuevo" /></abbr></a></td>
			  </tr>
			</table>

<div id="contentCatDet"> 
	<jsp:include page="/jsp/admin/contenidoCatDet.jsp"></jsp:include>
</div>
		<jsp:include page="/jsp/components/pages_border/close.jsp"></jsp:include>
	</td>
</tr>

</table>
</body>
</html>
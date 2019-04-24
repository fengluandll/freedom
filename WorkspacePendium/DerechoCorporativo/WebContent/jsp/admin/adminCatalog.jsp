<%@ page language="java" contentType="text/html; UTF-8"
    pageEncoding="UTF-8" import="java.util.*,mx.com.televisa.derechocorporativo.bean.CatalogoBean" %>
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

<title>Administraci&oacute;n de Cat&aacute;logos</title>
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
		doneTypingCat();
	}
}
function doneTypingCat () {
	//alert('Entro doneTyping');
	var cat = $("#filtroCat").val();
	cat = cat.replace('&', '%26'); //Sustituir ampersand por URL Encoded
	//alert('cat: '+cat);
	$.ajax({
		  method: "POST",
		  url: "/DerechoCorporativo/CargaCatalogoServlet?action=buscaCat",
		  data: 'cat='+cat,
		  cache: false,
		  success: function()
		  {
			  $('#contentCat').load("/DerechoCorporativo/faces/jsp/admin/contenidoCatalogo.jsp");
	      }
		})
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


		<table width="50%" border="0" align="center" cellpadding="0">
		  <tr>
		  		<c:set var="msg" value='<%= session.getAttribute("msgNoElimina") %>'/>		  		
		  		<td colspan="2"><c:if  test="${msg != null}">
		  							 <h4 style="color:red;"><%= session.getAttribute("msgNoElimina") %></h4>
		  		                </c:if>
		  		</td>
		  </tr>
		  <% session.removeAttribute("msgNoElimina"); %>
		  <tr>
		  	  <%String lsInteli = (String)session.getAttribute("busqInteliCat");
		  		System.out.println("InteliCat: "+lsInteli);%>
		  	  <td><input style="margin-left: 50px;" 
		  			 id="filtroCat" 
		  			 type="text" 
		  			 onkeyup="busqueda()"
		  			 name="textBuscarCat" 
		  			 class=""  
		  			 value="<%= lsInteli==null?"":lsInteli %>"
		  			 size="30"/>
		  	</td>
		  	<td>
		  		<a href="#" id="filtro2Cat" onclick="doneTypingCat();" > <img src="/DerechoCorporativo/img/search.png"></a>
		  	</td>
		  	<!--  -->
		    <td width="87%" class="nvoCta">Nuevo</td>
		    <td class="centerNew">
		    	<a href="<c:out value="${applicationBean.contextPath}"/>/faces/jsp/admin/nuevoCatalogo.jsp"><abbr title="Nuevo"><img src="../../img/icons/new.png" alt="Nuevo" /></abbr></a>
		    </td>
		  </tr>
		</table>
									<!--  -->
<div id="contentCat"> 
	<jsp:include page="/jsp/admin/contenidoCatalogo.jsp"></jsp:include>
</div>
			<jsp:include page="/jsp/components/pages_border/close.jsp"></jsp:include></td>
		</tr>
</table>
</body>
</html>
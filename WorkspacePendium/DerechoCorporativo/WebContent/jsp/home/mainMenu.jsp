<%@page import="mx.com.televisa.derechocorporativo.config.AppConfig"%>
<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="mx.com.televisa.derechocorporativo.util.FacesUtils"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<script type="text/javascript" src="../../js/jquery/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="../../js/jquery/jquery.form.js"></script>


<%

	//treeMenu
	//treeMenuV2
	String treeMenuFolder = AppConfig.getConfigValue("MENU_V");
%>


<link href="<%= request.getContextPath() %>/js/<%= treeMenuFolder %>/dtree.css" type="text/css" rel="StyleSheet">
<script src="<%= request.getContextPath() %>/js/<%= treeMenuFolder %>/dtree.js" type="text/javascript"></script>




<title>Insert title here</title>


<%
	String lstNomUser = FacesUtils.getSessionBean().getNomUser();
	if (lstNomUser == null) {
		response.sendRedirect("/DerechoCorporativo/faces/jsp/home/error.jsp");	
}
%>
	


<%@include file="/css/kaz-styles.jsp"%>

<script type="text/javascript" language="javascript">


	function minimizeMenu() {
		parent.document.getElementById('framesetMenuAndWindow').cols = "54,*";
		
		menu_table.style.display = "none";
		menu_btn.style.display = "";
		
	}

	function maximizeMenu() {
			
		parent.document.getElementById('framesetMenuAndWindow').cols = "340,*";
		
		menu_table.style.display = "";
		menu_btn.style.display = "none";
	}

	function searchEmpresa(){
		var empresa=document.getElementById('filtro');
		empresa = empresa.value;
		window.location = '/DerechoCorporativo/faces/BuscaEmpresaServlet?emp='+empresa;

	}

</script>   

</head>
<body>
	<!-- style="background-image: url('<c:out value="${applicationBean.contextPath}"/>/img/page_background.jpg');"  -->
	
	<f:view>
	
	
<input id="context" type="hidden" value="${applicationBean.requesturl}"/>
<img src="<c:out value="${applicationBean.contextPath}"/>/img/max-tab_azul.png" style="display: none" id="menu_btn" onclick="maximizeMenu()"></img>


<div id="menu_table">

		<jsp:include page="/jsp/components/pages_border/open-pre-title.jsp"></jsp:include>
		
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td></td>
				<td width="100%">Men&uacute;</td>
				<td><img src="<c:out value="${applicationBean.contextPath}"/>/img/min.png" onclick="minimizeMenu()"></img></td>
			</tr>

		</table>

<jsp:include page="/jsp/components/pages_border/open-post-title.jsp"></jsp:include>

 	<br/>
 	
 	<div align="right">
 	 <a href="javascript:d.openAll();">Mostrar Todo</a>
      <font color=red> | </font>
      <a href="javascript:d.closeAll();">Ocultar Todo</a> 
 	</div>
 	
 	<br></br>

<script type="text/javascript" language="javascript">

<%= 
	new mx.com.televisa.derechocorporativo.modules.home.MenuBean().getMainMenu()
%>
</script>

<jsp:include page="/jsp/components/pages_border/close.jsp"></jsp:include>
</div>

	</f:view>
</body>
</html>
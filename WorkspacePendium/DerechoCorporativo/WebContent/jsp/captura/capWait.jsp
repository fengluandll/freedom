<%@page import="mx.com.televisa.derechocorporativo.modules.captura.Pagina"%>
<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="mx.com.televisa.derechocorporativo.util.FacesUtils"%>
<%@include file="/css/kaz-styles.jsp"%>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta http-equiv="Pragma" content="no-cache" /><meta http-equiv="Expires" content="-1" /><meta http-equiv="Cache-Control" content="no-cache" />
  <!-- <meta http-equiv="refresh" content="0;URL =cap.jsp?idEmp=<%=request.getParameter("idEmp") %>"/> -->
  <%
  	String msg="";

  	boolean salida = false;
  	if(request.getParameter("salida") != null)
  	{
  		salida = Boolean.parseBoolean(request.getParameter("salida"));
  	}
  	if(salida){
  		msg=Pagina.setPestanaActual(request.getParameter("idSeccion"), salida).get(0).toString();
  		session.setAttribute("menu", "1");
  		%>
  	<script type="text/javascript">parent.frames['capHeader'].document.getElementById('spanTabName').innerHTML = "";</script> 
  		<%
  	}
  	
  	String edit="";
  	edit = FacesUtils.getSessionBean().getEditCon();
  	if(edit.equals("disabled")){
  %>
  	 
  	<meta http-equiv="refresh" content="0;URL =cap.jsp?idEmp=<%=request.getParameter("idEmp") %>&idSeccion=7&pa=2"/>
  <% 
  	}
  	else{
  %>
  		<meta http-equiv="refresh" content="0;URL =NewSaveMenu.jsp?idEmp=<%=request.getParameter("idEmp") %>"/>
  <%
  	}
  %>
  
  <style type="text/css">
  #loader { background:url(../../img/Waiting.GIF) no-repeat center center; height:66px; width:1100px; top: 300px; left: 200px;}
  #header { }

body {
	/*background-image:
		url("<c:out value='${applicationBean.contextPath}'/>/img/page_background.jpg");
	background-attachment:fixed;*/
	/*margin-top: 0;
		margin-left: 0;
		margin-right: 0;
		margin-bottom: 0;*/
}

  </style>
      <script type="text/javascript">
        function closeMe() {
            window.close();
        }
        setTimeout(closeMe, 40000);
    </script>
</html>
<body>
<div id="loader">Cargando Información de la Empresa...</div>
</body>
</html>


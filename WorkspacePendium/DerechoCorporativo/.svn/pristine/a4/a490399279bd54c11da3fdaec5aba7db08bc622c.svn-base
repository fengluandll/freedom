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
  <title>Visualizar Documento</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta http-equiv="Pragma" content="no-cache" /><meta http-equiv="Expires" content="-1" /><meta http-equiv="Cache-Control" content="no-cache" />
  <meta http-equiv="refresh" content="0;URL =/DerechoCorporativo/DownloadDocumentumFile"/>
  <style type="text/css">
  #loader { background:url(../../img/Waiting.GIF) no-repeat center center; height:66px; width:1100px; top: 300px; left: 200px;}
  #header { }
  </style>
      <script type="text/javascript">
        function closeMe() {
            window.close();
        }
        setTimeout(closeMe, 40000);
    </script>
</html>
<body>

<%	String numDocumentum = request.getParameter("doc");
	//FacesUtils.getSessionBean().setLstNumDocumentum(numDocumentum);
	session.setAttribute("numDocumentum", numDocumentum);
%>
<div id="loader">Estableciendo conexión con Documentum...</div>
</body>
</html>


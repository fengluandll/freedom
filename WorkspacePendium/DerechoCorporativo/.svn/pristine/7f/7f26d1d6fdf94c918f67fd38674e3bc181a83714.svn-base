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
<%-- 
<%@include file="part/validaciones.jsp"%>
--%>

<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta http-equiv="Pragma" content="no-cache" /><meta http-equiv="Expires" content="-1" /><meta http-equiv="Cache-Control" content="no-cache" />
  <!-- <meta http-equiv="refresh" content="0;URL =cap.jsp?idEmp=<%=request.getParameter("idEmp") %>"/> -->
  <%
    String idSeccion = request.getParameter("idSeccion");
	Pagina.setEmpAndSecc(request.getParameter("idSeccion"), request.getParameter("idEmp"));
    String msgProcess="";
    int idUser=0;
  	msgProcess=Pagina.setPestanaActual(request.getParameter("idSeccion"),false).get(0).toString();
  	idUser=Integer.parseInt(Pagina.setPestanaActual(request.getParameter("idSeccion"),false).get(1).toString());
  	if(msgProcess.equals("OK")){
  		if(idSeccion.equals("22")){ %>
		 <meta http-equiv="refresh" content="0;URL =<%=request.getContextPath()%>/faces/jsp/captura/poderesGEC/indexPoderes.jsp?idEmp=<%=request.getParameter("idEmp") %>&idSeccion=<%=request.getParameter("idSeccion")%>"/>  
		<%--<meta http-equiv="refresh" content="0;URL =<%=request.getContextPath()%>/faces/jsp/home/enDesarrollo.jsp" />--%>  			
		<%}else{%>
			<meta http-equiv="refresh" content="0;URL =NewSave.jsp?idEmp=<%=request.getParameter("idEmp") %>&idSeccion=<%=request.getParameter("idSeccion")%>"/>
	<%} %>
  		
  <%
  		session.setAttribute("msg", msgProcess);
  	}
  	else if(msgProcess.equals("OCUPADA") || msgProcess.equals("REPETIDO")){
  %>
  	<meta http-equiv="refresh" content="0;URL =NewSaveMenu.jsp?idEmp=<%=request.getParameter("idEmp") %>"/>
  <%
  		session.setAttribute("msg", msgProcess);
  		session.setAttribute("idUserOcupa",idUser);
  	}
  %>
  
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
<div id="loader">Cargando Información de la Empresa...</div>
</body>
</html>


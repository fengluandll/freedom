<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="mx.com.televisa.derechocorporativo.util.FacesUtils"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/css/kaz-styles.jsp"%>
<script src="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.min.js"></script> 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.css">
<%@include file="/jsp/components/session_timeout/session_timeout.jsp"%>
<title>Glosario</title>
</head>
<body>

<jsp:include page="/jsp/components/pages_border/open-pre-title.jsp"></jsp:include>
				Glosario	
<jsp:include page="/jsp/components/pages_border/open-post-title.jsp"></jsp:include>

<form action="/DerechoCorporativo/SubeGlosarioServlet" method="post" enctype="multipart/form-data">
<br/>
<br/>
<table width="50%" align="center">
<tr>
	<td>
		    <span style="font-size: 18px;color:#2B385D;">Subir Glosario en formato PDF</span>
	</td>
</tr>
<tr>
	<td>
		    &nbsp;
	</td>
</tr>
<tr>
	<td>
		    <%=session.getAttribute("messageFileUpload")==null?"":session.getAttribute("messageFileUpload")%>
		    <%session.removeAttribute("messageFileUpload"); %>
	</td>
</tr>
<tr>
	<td>
		    &nbsp;
	</td>
</tr>
<tr>
	<td>
		    <input type="file" name="file" />
	</td>
</tr>	
<tr>
	<td>
		    <input type="submit" value="Subir" />
	</td>	    
</tr>
</table>
</form>
<jsp:include page="/jsp/components/pages_border/close.jsp"></jsp:include>
</body>
</html>
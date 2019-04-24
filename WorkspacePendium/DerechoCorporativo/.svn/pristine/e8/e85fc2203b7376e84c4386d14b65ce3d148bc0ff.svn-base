
<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<%-- @include file="consultants-js.jsp"  --%>

</head>
<body>

	<table border="0" cellpadding="0" cellspacing="0" width="0">
		<tr valign="top">
			<td><input type="text" id="txtFilter"
				onkeyup="performSearchOnEnter(event, this.value)"></td>
			<td><input type="image"
				src="<%= request.getContextPath() %>/img/search.png"
				onclick="performSearch(document.getElementById('txtFilter').value);">
			</td>
		</tr>
	</table>
	<div id="output"></div>
	<script type="text/javascript">
		performSearch('');
	</script>
	<div id="debugInfo"></div>
</body>
</html>
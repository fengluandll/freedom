<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>...</title>

<%

	String entityCode = "21";

	session.setAttribute("entityCode", entityCode);


	String id = request.getParameter("id");

%>

<meta http-equiv="refresh" content="0;URL=adm_entity_row.jsp?id=<%= id %>">
  <style>
  body{
  	cursor:wait;
  }  
  </style>
</head>

<body>
</body>
</html>
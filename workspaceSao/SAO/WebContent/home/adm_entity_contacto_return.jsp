<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.movemini.data.OneRecordFactory"%>
<%@page import="com.movemini.data.Record"%>
<%@page import="com.movemini.data.ConnectionWrapper"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>...</title>

<%

	String entityCode = "6";
	String  ec = (String)session.getAttribute("entityCode");
	session.setAttribute("entityCode", entityCode);
	
	
	session.setAttribute("selectedTab", "Contactos");

	String cliente_id = (String) session.getAttribute("cliente_id");
	if("nuevo".equals(session.getAttribute(ec))){
 		session.removeAttribute(ec);
 	}
	Record screenConfig =  OneRecordFactory.getFirstRecord("SELECT * FROM flex_entity WHERE id = " + ec);

	//String id = request.getParameter("id");
	
	
	if(request.getParameter("idDeleteRow_") != null) {
		String xid = request.getParameter("idDeleteRow_");
	             		
		ConnectionWrapper.staticExecuteUpdate(
				"UPDATE " + screenConfig.get("table")  + " SET id_status = '0' WHERE " + screenConfig.get("column_id") + " = '" + xid + "'");	
	
	}

%>

<meta http-equiv="refresh" content="0;URL=adm_entity_cliente_row.jsp?id=<%= cliente_id %>">
  <style>
  body{
  	cursor:wait;
  }  
  </style>
</head>

<body>
</body>
</html>
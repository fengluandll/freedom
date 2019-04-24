<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="mx.com.televisa.derechocorporativo.data.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<%
String query = "";
		query += "SELECT 'Hello MySQL' as message";
		
		
		ConnectionWrapper cw = new ConnectionWrapper();
		ResultSet rs  = cw.executeQuery(query);
		
		while(rs.next()){
			

			%>
			<%=rs.getString("message")%>
			<%
			
		
		}
		
		rs.close();
		cw.close();
		
		%>


		
		
</body>
</html>
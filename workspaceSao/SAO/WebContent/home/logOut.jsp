<%
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
	response.setHeader("Pragma","no-cache"); //HTTP 1.0
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.Catch"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% session.invalidate();  %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SAO</title>
<meta http-equiv="refresh" content="0;URL=login.jsp">
</head>
<body>

<%
// 	try{
// 		Class.forName("com.mysql.jdbc.Driver");
// 		String url="jdbc:mysql://localhost:3306/prosante";
// 		String username="root";
// 		String password="120315";
// 		Connection conn=DriverManager.getConnection(url,username,password);

// 		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
// 		Date date = new Date();
// 		String currentTime = dateFormat.format(date);
// 		Statement st = conn.createStatement();
// 		String instruccion = "UPDATE user SET LASTCONN = '"+currentTime+"' WHERE ID = "+session.getAttribute("ID_USER");
// 		if(st.executeUpdate(instruccion) >= 1) {
// 			session.removeAttribute("ID_USER");
// 			session.removeAttribute("NOMBRE");
// 			session.removeAttribute("APELLIDO");
// 			session.removeAttribute("EDAD");
// 			session.removeAttribute("FECHA_NACIMIENTO");
// 			session.removeAttribute("TITULO");
// 			session.removeAttribute("ID_ROLE");
// 			session.removeAttribute("FOLIO");
//< % - -  			% >  - - % >
//<!-- 				<script type="text/javascript">window.location.replace("login.jsp")</script> -->
//)<%-- 			<% -- % >
// 	    }
//     }catch(Exception _a){
//     	System.out.println(_a);
//     }




%>
</body>
</html>

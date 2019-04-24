<%@page import="mx.com.televisa.derechocorporativo.modules.home.MenuBean"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<%
MenuBean.setStatusPestañaCero();
session.setAttribute("menu", "1");
List<String> listSecciones = new ArrayList<String>();;

ServletContext aplicacion= request.getServletContext();
aplicacion.setAttribute("htmlPG", null);		

	aplicacion.setAttribute("htmlPE", null);

	aplicacion.setAttribute("htmlCP", null);	

		for(int i=1;i<35;i++){
			listSecciones.add("sec"+i);
		}


for(String sec : listSecciones){
	request.getSession().removeAttribute(sec);
}

%>
</head>
<frameset rows="55,*" border="0" name="home">
	<frame src="capHead.jsp?idEmp=<%=request.getParameter("idEmp") %>" name="capHeader" scrolling="no" noresize>
	<frame src="capWait.jsp?idEmp=<%=request.getParameter("idEmp") %>&Edit=<%=request.getParameter("Edit") %>" name="subContentFrame" id="subContentFrame" noresize>
</frameset>
</html>
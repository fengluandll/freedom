<%@page import="java.util.StringTokenizer"%><%@page import="mx.com.televisa.derechocorporativo.modules.flextabs.FlexTable"%><%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%
	
	String idFlexTab = request.getParameter("globalFlexTableId");


	String key =  request.getParameter("key");
	
	FlexTable ft = new FlexTable(idFlexTab);
	


	
	ft.deleteFromControlMeta(key);

%>
<%@page import="mx.com.televisa.derechocorporativo.modules.flextabs.FlexTable"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	

	String idFlexTab = request.getParameter("idFlexTab");
		
	if(idFlexTab.equals("undefined")) {
			
		
		System.out.println("Se identifico un intento de imprimir una FlexTable con idFlexTab = undefined");
	
	} else {
		
		FlexTable ft = new FlexTable(idFlexTab);
	
		out.println(ft.toHTML(request));
	}
%>

 
<%@page import="mx.com.televisa.derechocorporativo.modules.reportsFlex.ParameterHandler"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	
	String id = request.getParameter("id");
	String value = request.getParameter("value");
%>
<%= ParameterHandler.addParamValue(id, value) %>
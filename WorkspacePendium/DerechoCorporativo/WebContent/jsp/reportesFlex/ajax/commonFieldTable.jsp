<%@page import="mx.com.televisa.derechocorporativo.modules.reportsFlex.Field"%>
<%@page import="mx.com.televisa.derechocorporativo.modules.reportsFlex.ReportFlex"%>
<%@page import="mx.com.televisa.derechocorporativo.util.FacesUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%

	//String fieldId = request.getParameter("fieldId");
	int seccionId = Integer.parseInt(request.getParameter("seccionId"));
	int subSeccionId = Integer.parseInt(request.getParameter("subSeccionId"));
	String filter = new String(request.getParameter("filter").getBytes("ISO-8859-1"),"UTF-8");
	boolean flexTabs = request.getParameter("flexTabs").equals("SI");
	boolean flexColumns = request.getParameter("flexColumns").equals("SI");
	
	
%>
<%= Field.getCamposTableHTML(seccionId, subSeccionId, filter, flexTabs, flexColumns) %>
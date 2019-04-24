<%@page import="mx.com.televisa.derechocorporativo.modules.reportsFlex.ReportFlex"%>
<%@page import="mx.com.televisa.derechocorporativo.util.FacesUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	
	int id = FacesUtils.getSessionBean().getIdFlexReport();

%>
<%= ReportFlex.getReportGUI(id, request) %>

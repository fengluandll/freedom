<%@page import="mx.com.televisa.derechocorporativo.modules.reportsFlex.Field"%>
<%@page import="mx.com.televisa.derechocorporativo.modules.reportsFlex.ReportFlex"%>
<%@page import="mx.com.televisa.derechocorporativo.util.FacesUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%

	String fieldId = request.getParameter("fieldId");
	String seccionId = request.getParameter("seccionId");
%>
<%= Field.getSubSeccionHTML(fieldId, seccionId) %>
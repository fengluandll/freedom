<%@page import="com.movemini.simpleajaxservlet.interprete.InterpretesAsignadosTable"%>
<%@page import="com.movemini.util.StringUtils"%>
<%@page import="com.movemini.util.TextFormatter"%>
<%@page import="com.movemini.util.NumberFormatter"%>
<%
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
	response.setHeader("Pragma","no-cache"); //HTTP 1.0
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<%@page import="com.movemini.data.DataArray"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.movemini.util.DateUtils"%><%@page import="com.movemini.data.Record"%><%@page import="com.movemini.data.OneRecordFactory"%><%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%><%@ page import="java.sql.*" %><%@ page import="java.io.*" %><%@ taglib uri="pd4ml" prefix="pd4ml"%><%
    
    
    
	String fileName = "Factura.pdf";

	String pageFormat = "LETTER";
	
	String pageOrientation = "portrait";
	
%><pd4ml:transform
	screenWidth="780" pageFormat="<%= pageFormat %>" pageOrientation="<%= pageOrientation %>" fileName="<%= fileName %>"
	pageInsets="10,10,10,10,mm" adjustScreenWidth="true"
	enableTableBreaks="false" enableImageSplit="false" debug="true">
	<pd4ml:footer areaHeight="30">

	</pd4ml:footer>    

<%@ include file="factura_html.jsp" %>
</pd4ml:transform>
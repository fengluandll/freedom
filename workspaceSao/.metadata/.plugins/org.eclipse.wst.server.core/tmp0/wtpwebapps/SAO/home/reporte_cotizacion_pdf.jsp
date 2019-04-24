<%
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
	response.setHeader("Pragma","no-cache"); //HTTP 1.0
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<%@page import="com.movemini.util.NumberFormatter"%>
<%@page import="com.movemini.data.ConnectionCounter"%>
<%@page import="com.movemini.layers.session.SessionBean"%>

<%@page import="com.movemini.data.DataArray"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.movemini.util.DateUtils"%><%@page import="com.movemini.data.Record"%><%@page import="com.movemini.data.OneRecordFactory"%><%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%><%@ page import="java.sql.*" %><%@ page import="java.io.*" %><%@ taglib uri="pd4ml" prefix="pd4ml"%><%



	String fileName = "Cotizacion.pdf";

	String pageFormat = "LETTER";

	String pageOrientation = "portrait";

	//Record user = SessionBean.getInstance(request).getUser();


	System.out.println("ConnectionCounter.count(): " + ConnectionCounter.count());


%>
<pd4ml:transform
	screenWidth="780" pageFormat="<%= pageFormat %>" pageOrientation="<%= pageOrientation %>" fileName="<%= fileName %>"
	pageInsets="10,10,10,10,mm" adjustScreenWidth="true"
	enableTableBreaks="false" enableImageSplit="false" debug="true">
	<pd4ml:footer areaHeight="30">

	</pd4ml:footer>



<%@ include file="reporte_cotizacion_html.jsp" %>


</pd4ml:transform>

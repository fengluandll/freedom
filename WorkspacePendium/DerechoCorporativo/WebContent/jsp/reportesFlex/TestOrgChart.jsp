<%@page import="mx.com.televisa.derechocorporativo.modules.reports.tenecascada.TenenciaCascada"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
 
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:h="http://java.sun.com/jsf/html">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<script src="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.min.js"></script> 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.css">
<title>Insert title here</title>
<%@include file="/css/kaz-styles.jsp"%>
</head>

   <head>
<%
	String empresaId = request.getParameter("empresaId");
	System.out.println("empresaId: "+empresaId);

	String paramConsolida = 	(request.getParameter("paramConsolida") != null) ? request.getParameter("paramConsolida") : "";
	String paramSegmento = 		(request.getParameter("paramSegmento") != null) ? request.getParameter("paramSegmento") : "";
	String paramClasificacion = (request.getParameter("paramClasificacion") != null) ? request.getParameter("paramClasificacion") : "";
	String paramPais = 			(request.getParameter("paramPais") != null) ? request.getParameter("paramPais") : "";
	String paramNoEmpOracle = 	(request.getParameter("paramNoEmpOracle") != null) ? request.getParameter("paramNoEmpOracle") : "";
	String paramGiro = 			(request.getParameter("paramGiro") != null) ? request.getParameter("paramGiro") : "";
	String paramPorcentaje = 			(request.getParameter("paramPorcentaje") != null) ? request.getParameter("paramPorcentaje") : "";
	String selectPorcentaje = 			(request.getParameter("selectPorcentaje") != null) ? request.getParameter("selectPorcentaje") : "";
	String selectPorcentajeCual = 			(request.getParameter("selectPorcentajeCual") != null) ? request.getParameter("selectPorcentajeCual") : "";

	String fecha = "";

	String data = TenenciaCascada.getTreeDataOrgChartGoogle(empresaId, fecha, 
			paramConsolida, 
			paramSegmento, 
			paramClasificacion, 
			paramPais, 
			paramNoEmpOracle, 
			paramGiro,
			paramPorcentaje,
			selectPorcentaje,
			selectPorcentajeCual,
			null);
System.out.println("TOC:\n"+data);

	

%>

    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load('current', {packages:["orgchart"]});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'Name');
        data.addColumn('string', 'Manager');
        data.addColumn('string', 'ToolTip');

        // For each orgchart box, provide the name, manager, and tooltip to show.
		/*
		data.addRows([
				[{v:'248', f:'GRUPO TELEVISA, S.A.B.<div style="color:red; font-style:italic">directo: ""</div><div style="color:blue; font-style:italic">indirecto: ""</div>'},'',''],
				[{v:'751', f:'METROPOLI VIP, S.A. DE C.V.<div style="color:red; font-style:italic">directo: "99.999705"</div><div style="color:blue; font-style:italic">indirecto: "99.999705"</div>'},'248',''],
				[{v:'70', f:'SISTEMA RADIOPOLIS, S. A. DE  C. V.<div style="color:red; font-style:italic">directo: "50.000000"</div><div style="color:blue; font-style:italic">indirecto: "50.000000"</div>'},'248',''],
				[{v:'38', f:'RADIO COMERCIALES, S.A. DE C.V.<div style="color:red; font-style:italic">directo: "99.973594"</div><div style="color:blue; font-style:italic">indirecto: "49.986797"</div>'},'70',''],
				[{v:'66', f:'SERVICIOS RADIOPOLIS, S.A. DE C.V.<div style="color:red; font-style:italic">directo: ".001928"</div><div style="color:blue; font-style:italic">indirecto: "50.000001"</div>'},'38',''],
				[{v:'68', f:'SERVICIOS XEZZ, S.A DE C.V.<div style="color:red; font-style:italic">directo: ".001938"</div><div style="color:blue; font-style:italic">indirecto: "49.500056"</div>'},'38',''],
				[{v:'66', f:'SERVICIOS RADIOPOLIS, S.A. DE C.V.<div style="color:red; font-style:italic">directo: "99.998073"</div><div style="color:blue; font-style:italic">indirecto: "50.000001"</div>'},'70',''],
				[{v:'42', f:'RADIO TELEVISORA DE MEXICALI, S.A. DE C.V.<div style="color:red; font-style:italic">directo: "99.999750"</div><div style="color:blue; font-style:italic">indirecto: "49.999875"</div>'},'70',''],
				[{v:'146', f:'CADENA RADIODIFUSORA MEXICANA, S.A. DE C.V.<div style="color:red; font-style:italic">directo: "99.999858"</div><div style="color:blue; font-style:italic">indirecto: "50.000036"</div>'},'70',''],
				[{v:'40', f:'RADIOTAPATIA, S.A. DE C.V.<div style="color:red; font-style:italic">directo: "99.000143"</div><div style="color:blue; font-style:italic">indirecto: "49.500107"</div>'},'146',''],
				[{v:'39', f:'RADIO MELODIA, S.A. DE C.V.<div style="color:red; font-style:italic">directo: "99.000000"</div><div style="color:blue; font-style:italic">indirecto: "49.500036"</div>'},'146',''],
				[{v:'313', f:'XEZZ, S.A. DE C.V.<div style="color:red; font-style:italic">directo: "99.000020"</div><div style="color:blue; font-style:italic">indirecto: "49.500046"</div>'},'146',''],
				[{v:'68', f:'SERVICIOS XEZZ, S.A DE C.V.<div style="color:red; font-style:italic">directo: "99.998063"</div><div style="color:blue; font-style:italic">indirecto: "49.500056"</div>'},'313',''],
				[{v:'746', f:'FUNDACION TELEVISA, A. C.<div style="color:red; font-style:italic">directo: "50.000000"</div><div style="color:blue; font-style:italic">indirecto: "50.000893"</div>'},'248',''],
				[{v:'25', f:'PROMO-INDUSTRIAS METROPOLITANAS, S. A. DE  C. V.<div style="color:red; font-style:italic">directo: "99.999955"</div><div style="color:blue; font-style:italic">indirecto: "99.999955"</div>'},'248',''],
				[{v:'146', f:'CADENA RADIODIFUSORA MEXICANA, S.A. DE C.V.<div style="color:red; font-style:italic">directo: ".000107"</div><div style="color:blue; font-style:italic">indirecto: "50.000036"</div>'},'25',''],
				[{v:'749', f:'LOMAS CONSULTORES ADMINISTRATIVOS, S.A. DE C.V.<div style="color:red; font-style:italic">directo: "1.000000"</div><div style="color:blue; font-style:italic">indirecto: "1.000000"</div>'},'25',''],
				[{v:'752', f:'SERVICIOS ADMINISTRATIVOS PROFESIONALES BOSQUES, S.A. DE C.V.<div style="color:red; font-style:italic">directo: "1.960976"</div><div style="color:blue; font-style:italic">indirecto: "1.960975"</div>'},'25',''],
				[{v:'742', f:'CORPORATIVO BOSQUE DE CANELOS, S.A. DE C.V.<div style="color:red; font-style:italic">directo: "90.000000"</div><div style="color:blue; font-style:italic">indirecto: "11.764874"</div>'},'752',''],
				[{v:'754', f:'TELEVISA TRANSMEDIA, S.A. DE C.V.<div style="color:red; font-style:italic">directo: ".002000"</div><div style="color:blue; font-style:italic">indirecto: ".002000"</div>'},'25',''],
				[{v:'736', f:'COISA, CONSULTORES INDUSTRIALES S.A. DE C.V<div style="color:red; font-style:italic">directo: "2.000000"</div><div style="color:blue; font-style:italic">indirecto: "1.999999"</div>'},'25',''],
				[{v:'753', f:'TELEINMOBILIARIA, S. DE R.L. DE C.V.<div style="color:red; font-style:italic">directo: ".003086"</div><div style="color:blue; font-style:italic">indirecto: ".003086"</div>'},'25',''],
				[{v:'757', f:'TRUXILLOS PUBLICIDAD, S.A. DE C.V.<div style="color:red; font-style:italic">directo: ".100000"</div><div style="color:blue; font-style:italic">indirecto: ".100000"</div>'},'25',''],
				[{v:'742', f:'CORPORATIVO BOSQUE DE CANELOS, S.A. DE C.V.<div style="color:red; font-style:italic">directo: "10.000000"</div><div style="color:blue; font-style:italic">indirecto: "11.764874"</div>'},'25',''],
				[{v:'755', f:'TELEVISA, S.A. DE C.V.<div style="color:red; font-style:italic">directo: ".001785"</div><div style="color:blue; font-style:italic">indirecto: ".001785"</div>'},'25',''],
				[{v:'746', f:'FUNDACION TELEVISA, A. C.<div style="color:red; font-style:italic">directo: "50.000000"</div><div style="color:blue; font-style:italic">indirecto: "50.000893"</div>'},'755',''],
				[{v:'759', f:'CORPORATIVO LAZARETTO, S.A. DE C.V.<div style="color:red; font-style:italic">directo: ".099917"</div><div style="color:blue; font-style:italic">indirecto: ".099917"</div>'},'25',''],
				[{v:'747', f:'GYALI, S.A. DE C.V.<div style="color:red; font-style:italic">directo: ".001775"</div><div style="color:blue; font-style:italic">indirecto: ".001775"</div>'},'25',''],
				[{v:'740', f:'AQUITANIA CONSULTORES INDUSTRIALES, S.A. DE C.V.<div style="color:red; font-style:italic">directo: "2.000000"</div><div style="color:blue; font-style:italic">indirecto: "1.999999"</div>'},'25',''],
				[{v:'756', f:'TORALI, S.A. DE C.V.<div style="color:red; font-style:italic">directo: ".004000"</div><div style="color:blue; font-style:italic">indirecto: ".004000"</div>'},'25',''],
				[{v:'744', f:'CVQ SERIES, S.A. DE C.V.<div style="color:red; font-style:italic">directo: ".000293"</div><div style="color:blue; font-style:italic">indirecto: ".000293"</div>'},'25',''],
				[{v:'745', f:'ENCUENTRO INDEPENDIENTE, S.A. DE C.V.<div style="color:red; font-style:italic">directo: ".016918"</div><div style="color:blue; font-style:italic">indirecto: ".016918"</div>'},'25',''],
				[{v:'748', f:'JANA TALENTO, S.A. DE C.V.<div style="color:red; font-style:italic">directo: ".000361"</div><div style="color:blue; font-style:italic">indirecto: ".000361"</div>'},'25','']
			]);
        */
		<%= data%>


		// Create the chart.
        var chart = new google.visualization.OrgChart(document.getElementById('chart_div'));
        // Draw the chart, setting the allowHtml option to true for the tooltips.
        chart.draw(data, {allowHtml:true});
        


        
      }
   </script>
    </head>
  <body>
    <div id="chart_div"></div>


	<a href='showReportAsPDFTestOrgChart.jsp?repId=<%= 9 %>'>
		<img src='../../img/icons/pdf_file.png' width="40" height="40">
	</a>
 
  </body>
</html>
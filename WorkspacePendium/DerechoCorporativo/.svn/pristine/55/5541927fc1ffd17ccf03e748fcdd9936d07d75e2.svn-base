<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="mx.com.televisa.derechocorporativo.modules.reports.tenecascada.TenenciaCascada"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String empresaId = request.getParameter("empresaId");


	String paramConsolida = 	(request.getParameter("paramConsolida") != null) ? request.getParameter("paramConsolida") : "";
	String paramSegmento = 		(request.getParameter("paramSegmento") != null) ? request.getParameter("paramSegmento") : "";
	String paramClasificacion = (request.getParameter("paramClasificacion") != null) ? request.getParameter("paramClasificacion") : "";
	String paramPais = 			(request.getParameter("paramPais") != null) ? request.getParameter("paramPais") : "";
	String paramNoEmpOracle = 	(request.getParameter("paramNoEmpOracle") != null) ? request.getParameter("paramNoEmpOracle") : "";
	String paramGiro = 			(request.getParameter("paramGiro") != null) ? request.getParameter("paramGiro") : "";
	String paramPorcentaje = 			(request.getParameter("paramPorcentaje") != null) ? request.getParameter("paramPorcentaje") : "";
	String selectPorcentaje = 			(request.getParameter("selectPorcentaje") != null) ? request.getParameter("selectPorcentaje") : "";
	String selectPorcentajeCual = 			(request.getParameter("selectPorcentajeCual") != null) ? request.getParameter("selectPorcentajeCual") : "";
	String selectPorcentajeVisualizar = 			(request.getParameter("selectPorcentajeVisualizar") != null) ? request.getParameter("selectPorcentajeVisualizar") : "";
	
	
	
	//String fecha = request.getParameter("fecha");
	String fecha = "";
	/*TenenciaCascada.getTreeData1(empresaId, fecha,
			paramConsolida, 
			paramSegmento, 
			paramClasificacion, 
			paramPais, 
			paramNoEmpOracle, 
			paramGiro,
			paramPorcentaje,
			selectPorcentaje,
			selectPorcentajeCual,
			selectPorcentajeVisualizar
			);*/
	
	String data = TenenciaCascada.getTreeDataOrgChart(empresaId, fecha, 
														paramConsolida, 
														paramSegmento, 
														paramClasificacion, 
														paramPais, 
														paramNoEmpOracle, 
														paramGiro,
														paramPorcentaje,
														selectPorcentaje,
														selectPorcentajeCual);
	/* 
	String data = TenenciaCascada.getTreeDataOrgChartGoogle(empresaId, fecha, 
			paramConsolida, 
			paramSegmento, 
			paramClasificacion, 
			paramPais, 
			paramNoEmpOracle, 
			paramGiro,
			paramPorcentaje,
			selectPorcentaje,
			selectPorcentajeCual);
	*/
	//System.out.println(data);

	int longitud = data.length();
	String comaPuntoComa = StringUtils.substring(data,longitud - 1, longitud);
	if(comaPuntoComa.equals(",")){
		data = StringUtils.substring(data,0, longitud-1);
		System.out.println("-------------------"+data);	
		data+="];";
	}
%>
<%= data %>

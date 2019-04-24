<%@page import="java.util.StringTokenizer"%><%@page import="mx.com.televisa.derechocorporativo.modules.flextabs.FlexTable"%><%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%	

	String params = request.getParameter("params");
	String globalFlexTableId = request.getParameter("globalFlexTableId");
	String alterFlexTableId = request.getParameter("alterFlexTableId");

	StringTokenizer tok = new StringTokenizer(params,"-");
	
	String idFlexTab = tok.nextToken();
	String key =  tok.nextToken();
	
	FlexTable ft = new FlexTable(idFlexTab);

	
	
	/* System.out.println("alert('" + idFlexTab + "');");
	System.out.println("alert('" + key + "');");
	 */
	
	
	out.println(ft.toFormScript(key, globalFlexTableId, alterFlexTableId));
%>
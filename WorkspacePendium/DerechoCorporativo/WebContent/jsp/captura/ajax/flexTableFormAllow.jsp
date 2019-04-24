<%@page import="java.util.StringTokenizer"%><%@page import="mx.com.televisa.derechocorporativo.modules.flextabs.FlexTable"%><%@page import="mx.com.televisa.derechocorporativo.modules.captura.Pagina"%><%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%
	
	String params = request.getParameter("params");

	StringTokenizer tok = new StringTokenizer(params,"-");
	
	String idFlexTab = tok.nextToken();
	String key =  tok.nextToken();
	
	FlexTable ft = new FlexTable(idFlexTab);
	
	String globalFlexTableId = request.getParameter("globalFlexTableId");
	String alterFlexTableId = request.getParameter("alterFlexTableId");
	
	int idUsrOcu=0;
	String nomUsrOcu = "";
	
	String allow = ft.allowToOpenForm(key, globalFlexTableId, alterFlexTableId);
	String[] allowIdOcupa = new String[2]; 
	
	if(allow.contains(",")){		
		allowIdOcupa = allow.split(",");
	}
	else{
		allowIdOcupa[0]=allow;
	}
	
	
	
	if (allowIdOcupa[0].equals("NOK")){
		session.setAttribute("idUserOcupaReforma",allowIdOcupa[1]);
	}
	
	if(session.getAttribute("idUserOcupaReforma") != null){
		idUsrOcu = Integer.parseInt(session.getAttribute("idUserOcupaReforma").toString());
	}
	nomUsrOcu=Pagina.getNomUsrOcupado(idUsrOcu);									
	out.println(allowIdOcupa[0]+","+nomUsrOcu);
%>
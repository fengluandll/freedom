<%@page import="java.util.ArrayList"%>
<%@page import="mx.com.televisa.derechocorporativo.daos.CambiaCatDAO"%>
<%@page import="mx.com.televisa.derechocorporativo.bean.DetalleCatagoBean"%>
<%@page import="mx.com.televisa.derechocorporativo.util.FacesUtils"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	String lsNumPais = request.getParameter("pais");
	String liIdEmpresa = FacesUtils.getSessionBean().getIdCurrentEmpresa();
	String lsIdCampo = request.getParameter("idCampo");
	String lsCodCampo = request.getParameter("codCampo");

	CambiaCatDAO luCambiaCatDAO = new CambiaCatDAO();	
	ArrayList<DetalleCatagoBean> laDetalleCatagoBean = new ArrayList<DetalleCatagoBean>();
	
	String lsCampoValor = luCambiaCatDAO.consultaCampoValor(liIdEmpresa, lsIdCampo);
	lsCampoValor = lsCampoValor==null?"0":lsCampoValor;

	laDetalleCatagoBean = luCambiaCatDAO.consultaNuevoCat(lsNumPais);
	/*
	System.out.println("NumPais:  "+lsNumPais);
	System.out.println("IdEmpresa:  "+liIdEmpresa);
	System.out.println("CampoValor:  "+lsCampoValor);
	
	System.out.println("IdCampo:  "+lsIdCampo);
	System.out.println("CodCampo:  "+lsCodCampo);
	*/
	
%>
<select name="<%=lsCodCampo%>" id="<%=lsCodCampo%>" style="width:180px">
		<option value="0">---Seleccione---</option>
	<%for(DetalleCatagoBean luDeCatEF: laDetalleCatagoBean){ 
			if(lsCampoValor.equals( String.valueOf( luDeCatEF.getIdCatalogoVal() ) ) ){
	%>
				<option value="<%=luDeCatEF.getIdCatalogoVal()%>" title="<%=luDeCatEF.getNombre()%>" SELECTED><%= luDeCatEF.getValor()%></option>
			<%}else{%>
				<option value="<%=luDeCatEF.getIdCatalogoVal()%>" title="<%=luDeCatEF.getNombre()%>"><%= luDeCatEF.getValor()%></option>
			<%}
	}%>
</select>
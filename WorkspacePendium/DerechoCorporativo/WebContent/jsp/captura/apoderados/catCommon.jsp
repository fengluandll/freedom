<%@page import="mx.com.televisa.derechocorporativo.util.FacesUtils"%>
<%@page import="java.util.List"%>
<%@page import="mx.com.televisa.derechocorporativo.model.*"%>
<%@page import="mx.com.televisa.derechocorporativo.bean.*"%>
<%@page import="mx.com.televisa.derechocorporativo.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
	{
	
	
	String idCatalogo  = request.getParameter("idCatalogo");
	String idComponent = request.getParameter("idComponent");
	String escritura   = (String)session.getAttribute("escrituraId");
	String tipoPoder   = (String)session.getAttribute("tipoPoderId");
	String grupo       = (String)session.getAttribute("grupo");
	
	String title  = "";
	
	
	if(idCatalogo == null) {
		
		idCatalogo = FacesUtils.getSessionBean().getApoderadosSessionVars().getCustomIdCatalogo(); 
	}
	

	if(idComponent == null) {
		
		idComponent = FacesUtils.getSessionBean().getApoderadosSessionVars().getCustomIdComponent(); 
	}
	
	
    String idEmpresa = FacesUtils.getSessionBean().getIdCurrentEmpresa();
    List<CatalogoValBean> elems = new Catalog().dameApoderados(Integer.parseInt(idCatalogo),
    														   Integer.parseInt(idEmpresa),
    														   Integer.parseInt(tipoPoder),
    														   grupo,
    														   escritura);
%>
<select name="<%=idComponent %>" id="<%=idComponent %>" onchange="" multiple style="width:450px" size="10">
    <!--  <option value="*">(Todas)</option>-->
    <% for (CatalogoValBean elem : elems) {
    		if (elem.getAtributo1() == null || elem.getAtributo1().equals("")||elem.getAtributo1().trim().equals("null")){
    			title = TextFormatter.ToFormatHtml(elem.getValCatVal());
    		} else {
    			title = TextFormatter.ToFormatHtml(elem.getAtributo1());
    		}
    %>
    <option value="<%=elem.getIdCatalogoValor()%>" title="<%=title%>"><%=elem.getValCatVal()%></option>
    <% }%>
</select>	
<%
	}
%>
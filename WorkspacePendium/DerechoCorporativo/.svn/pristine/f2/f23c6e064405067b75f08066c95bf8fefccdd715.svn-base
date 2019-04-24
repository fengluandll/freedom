<%@page import="mx.com.televisa.derechocorporativo.util.NumbersUtil"%>
<%@page import="java.util.List"%>
<%@page import="mx.com.televisa.derechocorporativo.model.Catalog"%>
<%@page import="mx.com.televisa.derechocorporativo.bean.SessionBean"%>
<%@page import="mx.com.televisa.derechocorporativo.model.CatalogElement"%>
<%@page import="mx.com.televisa.derechocorporativo.daos.CriterioBusquedaDAO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
  
	String strCatalogId = request.getParameter("catalogId");
	
	
	String filter = new String(request.getParameter("filter").getBytes("ISO-8859-1"),"UTF-8");
	String currentIds = request.getParameter("currentIds");
	
	//String searchCurrentIds = "," + currentIds + ",";
	SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
	
	int liIdRol =   Integer.valueOf(sessionBean.getIdRol());
  
  
	List<CatalogElement> catalogElements = null;
	
	
	if(NumbersUtil.isInteger(strCatalogId)) {
		
		int catalogId = Integer.parseInt(strCatalogId);
		catalogElements = Catalog.getCatalogElements(catalogId, filter, currentIds);	
	
	} else {
		
		if(strCatalogId.equals("EMPRESAS")) {
			
			String condiciones = "WHERE APP_COMMON_PKG.SIN_ACENTOS_NI_NN_FN(UPPER(DENOM_ACTUAL)) ";
					condiciones += "LIKE APP_COMMON_PKG.SIN_ACENTOS_NI_NN_FN(UPPER('%" + filter + "%')) "; 
					if(CriterioBusquedaDAO.getRevokeEmp(String.valueOf(liIdRol)) != null ){
						condiciones+=" AND ID_EMPRESA NOT IN("+CriterioBusquedaDAO.getRevokeEmp(String.valueOf(liIdRol))+") ";	
				    }
					condiciones += "ORDER BY DENOM_ACTUAL";
			

			catalogElements = new Catalog("DERCORP_BUSQUEDA_VIEW").getList("DISTINCT ID_EMPRESA, DENOM_ACTUAL", condiciones);
		}
		
	}
	
	
	
	
	%>
	
	<ul>
	<%
  	for(CatalogElement element : catalogElements){ 
  	  	
  		
  	%>
  	
  	<li>
  		<a href='#' onclick="confirmSelection('<%= element.getId() %>','<%= element.getName() %>')">
  			<%= element.getName() %>
  		</a>
  		</li>
  	<br>
  	
  	<%
  		
  	}
  	%>
  	</ul>
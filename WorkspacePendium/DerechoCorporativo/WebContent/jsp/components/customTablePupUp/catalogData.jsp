<%@page import="java.util.StringTokenizer"%>
<%@page import="mx.com.televisa.derechocorporativo.util.NumbersUtil"%>
<%@page import="java.util.List"%>
<%@page import="mx.com.televisa.derechocorporativo.model.Catalog"%>
<%@page import="mx.com.televisa.derechocorporativo.model.CatalogElement,
				mx.com.televisa.derechocorporativo.daos.RolesDAO,
				mx.com.televisa.derechocorporativo.bean.SessionBean,
				mx.com.televisa.derechocorporativo.bean.RolBean,
				mx.com.televisa.derechocorporativo.daos.CriterioBusquedaDAO,
				org.apache.commons.lang3.StringUtils"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
  
	String strCatalogId = request.getParameter("catalogId");
	String filter = new String(request.getParameter("filter").getBytes("ISO-8859-1"),"UTF-8");
	String currentIds = request.getParameter("currentIds");
	
	//out.println(strCatalogId);
	//out.println(currentIds);
	
	
	StringTokenizer strTok1 = new StringTokenizer(currentIds, ",");
	
	String tokResult = ""; 
	
	while(strTok1.hasMoreTokens()) {
		
		String tok = strTok1.nextToken();
		
		StringTokenizer strTok2 = new StringTokenizer(tok, ";");
		
		if(!tokResult.equals("")) {
			
			tokResult += ",";
		}
		
		tokResult += strTok2.nextToken();
	}
	
	currentIds = tokResult;
	
	//out.println("<br>");
	//out.println(currentIds);
	
	
	
	String searchCurrentIds = "," + currentIds + ",";
  
	SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
	RolesDAO rolDAO = new RolesDAO();
	RolBean rolBean     = (RolBean) session.getAttribute("rolBean");
	
	int liIdRol =   Integer.valueOf(sessionBean.getIdRol());
	
	//List<CatalogElement> catalogElements = Catalog.getCatalogElements(catalogId, filter, currentIds, liIdRol);
  

	List<CatalogElement> catalogElements = null;
	
	System.out.println("CATALOGO: "+strCatalogId);
	if(NumbersUtil.isInteger(strCatalogId)) {
		
		int catalogId = Integer.parseInt(strCatalogId);
		
		if(catalogId == 6969){
			catalogElements = new Catalog("DERCORP_CAT_PERSONAS_TOTAL_TAB").getList("PERSON_ID, NOMBRE", "WHERE APP_COMMON_PKG.SIN_ACENTOS_NI_NN_FN(UPPER(NOMBRE)) LIKE APP_COMMON_PKG.SIN_ACENTOS_NI_NN_FN(UPPER('%" + filter + "%')) OR '" + currentIds + "' LIKE '%' || PERSON_ID || '%' ORDER BY UPPER(NOMBRE)");
		}else{
			if(catalogId == 1) {

				//catalogElements = new Catalog("DERCORP_BUSQUEDA_VIEW").getList("ID_EMPRESA, DENOM_ACTUAL", "WHERE UPPER(DENOM_ACTUAL) LIKE UPPER('%" + filter + "%') OR '" + currentIds + "' LIKE '%' || ID_EMPRESA || '%' ORDER BY UPPER(DENOM_ACTUAL)");
				StringBuilder stb = new StringBuilder();
				
				if(currentIds != null && !currentIds.equals("")){
					boolean tieneComa = StringUtils.contains(currentIds, ",");
					if(tieneComa){
						String[] arrayIds = StringUtils.split(currentIds, ",");
						for(String ids : arrayIds){
							stb.append("OR "+ids +" = ID_EMPRESA ");
						}
					}else{
						stb.append("OR "+currentIds +" = ID_EMPRESA ");
					}
				}
				String condiciones = " WHERE (UPPER(DENOM_ACTUAL) LIKE UPPER('%" + filter + "%') " + stb + ") ";
				if(CriterioBusquedaDAO.getRevokeEmp(String.valueOf(liIdRol)) != null ){
					condiciones+=" AND ID_EMPRESA NOT IN("+CriterioBusquedaDAO.getRevokeEmp(String.valueOf(liIdRol))+") ";
					
			    }
				condiciones+=" ORDER BY DENOM_ACTUAL ";	//Se agrega para ordenamiento JAMS 28/09/2017
				catalogElements = new Catalog("DERCORP_BUSQUEDA_VIEW").getList("DISTINCT ID_EMPRESA, DENOM_ACTUAL", condiciones);

			} else {
				
				catalogElements = Catalog.getCatalogElements(catalogId, filter, currentIds, liIdRol);
			}
		}
	
	} else {
		
		if(strCatalogId.equals("EMPRESAS") || strCatalogId.equals("1")) {
			System.out.println("EMPRESAS");
			
			String condiciones =  " WHERE UPPER(DENOM_ACTUAL) LIKE UPPER('%" + filter + "%') ";
					condiciones+="OR '" + currentIds + "' ";
					condiciones+="LIKE '%' || ID_EMPRESA || '%' "; 
					if(CriterioBusquedaDAO.getRevokeEmp(String.valueOf(liIdRol)) != null ){
						condiciones+=" AND ID_EMPRESA NOT IN("+CriterioBusquedaDAO.getRevokeEmp(String.valueOf(liIdRol))+") ";	
				    }
					condiciones+="ORDER BY UPPER(DENOM_ACTUAL)";
			catalogElements = new Catalog("DERCORP_BUSQUEDA_VIEW").getList("DISTINCT ID_EMPRESA, DENOM_ACTUAL",condiciones);
		}
		

		if(strCatalogId.equals("PERSONAS")) {
			System.out.println("PERSONAAS");
			catalogElements = new Catalog("DERCORP_CAT_PERSONAS_TAB").getList("PERSON_ID, NOMBRE", "WHERE APP_COMMON_PKG.SIN_ACENTOS_NI_NN_FN(UPPER(NOMBRE)) LIKE APP_COMMON_PKG.SIN_ACENTOS_NI_NN_FN(UPPER('%" + filter + "%'))OR '" + currentIds + "' LIKE '%' || PERSON_ID || '%' ORDER BY UPPER(NOMBRE)");
		}
		

		if(strCatalogId.equals("PODERES")) {
			System.out.println("PODERES");
			catalogElements = Catalog.getCatalogPoderes(filter, currentIds);
		}
	}
  
	
	
	
	//out.println(searchCurrentIds);
	
	
	
	
	  
  	for(CatalogElement element : catalogElements){ 
  	
  		String  strElementId = Integer.toString(element.getId());
  		
  		if(element.getId() == 12070) {
  			strElementId = "button_green.png";
  		}

  		if(element.getId() == 12071) {
  			strElementId = "button_yellow.png";
  		}

  		if(element.getId() == 12072) {
  			strElementId = "button_red.png";
  		}
  		
  		
  		
  		
  		
  		if(searchCurrentIds.contains("," + strElementId + ",")) {
  	%>
  	
  	<label>
  	<input type="checkbox" name="selection" id="<%= strElementId %>" value="<%= element.getName() %>" checked="checked" onchange="updateSelection()"> 
  		<%= element.getName() %>
  		</label>
  	<br>
  	
  	<%
  		}
  	}
  	%>
  	
  	
  	<%

  	for(CatalogElement element : catalogElements){ 
  		
  		String  strElementId = Integer.toString(element.getId());
  		
  		if(element.getId() == 12070) {
  			strElementId = "button_green.png";
  		}

  		if(element.getId() == 12071) {
  			strElementId = "button_yellow.png";
  		}

  		if(element.getId() == 12072) {
  			strElementId = "button_red.png";
  		}
  		
  		
  		if(!searchCurrentIds.contains("," + strElementId + ",")) {
  	%>
  	
  	<label>
  	<input type="checkbox" name="selection" id="<%= strElementId %>" value="<%= element.getName() %>"  onchange="updateSelection()">
  		<%= element.getName() %>
  		</label>
  	<br>
  	
  	<%
  		}
  	}
  	%>
  	
  	
  	
  	
  	
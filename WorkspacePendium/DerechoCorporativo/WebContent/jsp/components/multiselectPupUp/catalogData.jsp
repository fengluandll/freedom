<%@page import="mx.com.televisa.derechocorporativo.modules.reports.apoderados.RepApoderados"%>
<%@page import="mx.com.televisa.derechocorporativo.util.NumbersUtil"%>
<%@page import="java.util.List"%>
<%@page import="mx.com.televisa.derechocorporativo.model.Catalog"%>
<%@page import="mx.com.televisa.derechocorporativo.model.CatalogElement,
mx.com.televisa.derechocorporativo.daos.CriterioBusquedaDAO,
				mx.com.televisa.derechocorporativo.bean.SessionBean,
				org.apache.commons.lang3.StringUtils"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
  
	String strCatalogId = request.getParameter("catalogId");
	String strPais      = request.getParameter("pais");
	String filter 		= new String(request.getParameter("filter").getBytes("ISO-8859-1"),"UTF-8");
	String currentIds 	= request.getParameter("currentIds");
	String tarId      	= request.getParameter("tarId");
	String poderesId 	= "";
	
	//out.println(currentIds);
	
	String searchCurrentIds = "," + currentIds + ",";
  
	SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
	int liIdRol =   Integer.valueOf(sessionBean.getIdRol());
	
	//List<CatalogElement> catalogElements = Catalog.getCatalogElements(catalogId, filter, currentIds, liIdRol);
  

	List<CatalogElement> catalogElements = null;
	
	
	if(NumbersUtil.isInteger(strCatalogId)) {//JJAQ 16_03_2017 Para que no entre cuando sea denominaciones y pase al else
		
		int catalogId = Integer.parseInt(strCatalogId);
		
		if(catalogId == 6969){
			//catalogElements = new Catalog("DERCORP_CAT_PERSONAS_TOTAL_TAB").getList("PERSON_ID, NOMBRE", "WHERE APP_COMMON_PKG.SIN_ACENTOS_NI_NN_FN(UPPER(NOMBRE)) LIKE APP_COMMON_PKG.SIN_ACENTOS_NI_NN_FN(UPPER('%" + filter + "%')) OR '" + currentIds + "' LIKE '%' || PERSON_ID || '%' ORDER BY UPPER(APP_COMMON_PKG.SIN_ACENTOS_FN(NOMBRE))");
			//JJAQ 14-09-2018 el like no servia y traia de mas
			if(currentIds.length() > 0){
				catalogElements = new Catalog("DERCORP_CAT_PERSONAS_TOTAL_TAB").getList("PERSON_ID, NOMBRE", "WHERE APP_COMMON_PKG.SIN_ACENTOS_NI_NN_FN(UPPER(NOMBRE)) LIKE APP_COMMON_PKG.SIN_ACENTOS_NI_NN_FN(UPPER('%" + filter + "%')) OR PERSON_ID IN (" + currentIds + ") ORDER BY UPPER(APP_COMMON_PKG.SIN_ACENTOS_FN(NOMBRE))");				
			}else{
				catalogElements = new Catalog("DERCORP_CAT_PERSONAS_TOTAL_TAB").getList("PERSON_ID, NOMBRE", "WHERE APP_COMMON_PKG.SIN_ACENTOS_NI_NN_FN(UPPER(NOMBRE)) LIKE APP_COMMON_PKG.SIN_ACENTOS_NI_NN_FN(UPPER('%" + filter + "%')) ORDER BY UPPER(APP_COMMON_PKG.SIN_ACENTOS_FN(NOMBRE))");				
			}
			
		}else if(catalogId == 1){//JJAQ Para que tome la clasificacion en la denominacion de los reportes.
			
			if(filter != null && !filter.equals(""))
				filter = filter.toUpperCase();
				
			if(tarId != null && (tarId.equals("30__VAL_C7") || tarId.equals("30__VAL_C11"))){
				catalogElements = new Catalog("dercorp_add_campo_cat_val_tab camp").getList("DISTINCT camp.ID_CATALOGO_VALOR, camp.val_cat_val", "WHERE camp.id_catalogo = 40 AND UPPER(camp.val_cat_val) LIKE UPPER('%" + filter + "%') OR '" + currentIds + "' LIKE '%' || camp.ID_CATALOGO_VALOR || '%' ORDER BY UPPER(APP_COMMON_PKG.SIN_ACENTOS_FN(UPPER(camp.val_cat_val)))");				
			}else{
				String check         = request.getParameter("chk");
				if(check.equals("") || check == null){
					check = "";
				}else{
					check = "and VEMP.id_clasificacion in ("+check+")";
				}
				catalogElements = Catalog.getCatalogElements(catalogId, filter, currentIds, liIdRol,check,strPais);
			}
		
		}else{
			//System.out.println("catalogId: "+catalogId);
			//System.out.println("filter: "+filter);
			//System.out.println("currentIds: "+currentIds);
			//System.out.println("liIdRol: "+liIdRol);
			
			if(filter != null && !filter.equals(""))
				filter = filter.toUpperCase();
				
			if(tarId != null && (tarId.equals("30__VAL_C7") || tarId.equals("30__VAL_C11"))){
				catalogElements = new Catalog("dercorp_add_campo_cat_val_tab camp").getList("DISTINCT camp.ID_CATALOGO_VALOR, camp.val_cat_val", "WHERE camp.id_catalogo = 40 AND UPPER(camp.val_cat_val) LIKE UPPER('%" + filter + "%') OR '" + currentIds + "' LIKE '%' || camp.ID_CATALOGO_VALOR || '%' ORDER BY UPPER(APP_COMMON_PKG.SIN_ACENTOS_FN(UPPER(camp.val_cat_val)))");				
			}else{
				catalogElements = Catalog.getCatalogElements(catalogId, filter, currentIds, liIdRol);
			}
			
		}
	
	} else {
		
		if(strCatalogId.equals("ACCIONISTAS")) {
		
			StringBuilder stb = new StringBuilder();
			
			String clasif     = "";	
			if(currentIds != null && !currentIds.equals("")){
				boolean tieneComa = StringUtils.contains(currentIds, ",");
				if(tieneComa){
					String[] arrayIds = StringUtils.split(currentIds, ",");
					for(String ids : arrayIds){
						stb.append("OR "+ids +" = ID_CATALOGO_VALOR ");
					}
				}else{
					stb.append("OR "+currentIds +" = ID_CATALOGO_VALOR ");
				}
			}
			
			String condiciones = "WHERE ID_CATALOGO = 40 AND (UPPER(VAL_CAT_VAL) LIKE UPPER('%" + filter + "%') " + stb + ")";
			
			catalogElements = new Catalog("DERCORP_ADD_CAMPO_CAT_VAL_TAB").getList("DISTINCT ID_CATALOGO_VALOR, VAL_CAT_VAL", condiciones);
		
	}
		
		if(strCatalogId.equals("EMPRESAS")) {
				String check       = request.getParameter("chk");
				String orCondition = "";
				StringBuilder stb = new StringBuilder();
				if(check == null){
					check = "";
				}
				String clasif     = "";	
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
				//catalogElements = new Catalog("DERCORP_BUSQUEDA_VIEW").getList("DISTINCT ID_EMPRESA, DENOM_ACTUAL", "WHERE UPPER(DENOM_ACTUAL) LIKE UPPER('%" + filter + "%') OR '" + currentIds + "' LIKE '%' || ID_EMPRESA || '%' ",check);
				String condiciones = "WHERE (UPPER(DENOM_ACTUAL) LIKE UPPER('%" + filter + "%') " + stb + ")";
				if(CriterioBusquedaDAO.getRevokeEmp(String.valueOf(liIdRol)) != null ){
					condiciones+=" AND ID_EMPRESA NOT IN("+CriterioBusquedaDAO.getRevokeEmp(String.valueOf(liIdRol))+")\n";	
			    }
				catalogElements = new Catalog("DERCORP_BUSQUEDA_VIEW").getList("DISTINCT ID_EMPRESA, DENOM_ACTUAL", condiciones,check,strPais);
			
		}
		

		if(strCatalogId.equals("PERSONAS")) {
			//JJAQ 14-09-2018 el like no servia y traia de mas
			//catalogElements = new Catalog("DERCORP_CAT_PERSONAS_TAB").getList("PERSON_ID, NOMBRE", "WHERE APP_COMMON_PKG.SIN_ACENTOS_NI_NN_FN(UPPER(NOMBRE)) LIKE APP_COMMON_PKG.SIN_ACENTOS_NI_NN_FN(UPPER('%" + filter + "%'))OR '" + currentIds + "' LIKE '%' || PERSON_ID || '%' ORDER BY UPPER(APP_COMMON_PKG.SIN_ACENTOS_FN(NOMBRE))");
			if(currentIds.length() > 0){
				catalogElements = new Catalog("DERCORP_CAT_PERSONAS_TAB").getList("PERSON_ID, NOMBRE", "WHERE APP_COMMON_PKG.SIN_ACENTOS_NI_NN_FN(UPPER(NOMBRE)) LIKE APP_COMMON_PKG.SIN_ACENTOS_NI_NN_FN(UPPER('%" + filter + "%'))OR PERSON_ID IN(" + currentIds + ") ORDER BY UPPER(APP_COMMON_PKG.SIN_ACENTOS_FN(NOMBRE))");	
			}else{
				catalogElements = new Catalog("DERCORP_CAT_PERSONAS_TAB").getList("PERSON_ID, NOMBRE", "WHERE APP_COMMON_PKG.SIN_ACENTOS_NI_NN_FN(UPPER(NOMBRE)) LIKE APP_COMMON_PKG.SIN_ACENTOS_NI_NN_FN(UPPER('%" + filter + "%')) ORDER BY UPPER(APP_COMMON_PKG.SIN_ACENTOS_FN(NOMBRE))");
			}
			

		}
		

		if(strCatalogId.equals("PODERES")) {
			
			catalogElements = Catalog.getCatalogPoderes(filter, currentIds);
			request.getSession().setAttribute("PODERES", "PODERES");
			
		}
		
		if(strCatalogId.equals("FACULTADES")) {
			catalogElements = new ArrayList<CatalogElement>();
			CatalogElement facultadAD = new CatalogElement();
			facultadAD.setId(1);
			facultadAD.setName("Actos de Dominio");
			catalogElements.add(facultadAD);
			CatalogElement facultadAA = new CatalogElement();
			facultadAA.setId(2);
			facultadAA.setName("Actos de Administración");
			catalogElements.add(facultadAA);
			CatalogElement facultadTC = new CatalogElement();
			facultadTC.setId(3);
			facultadTC.setName("Títulos de Crédito");
			catalogElements.add(facultadTC);
			CatalogElement facultadPC = new CatalogElement();
			facultadPC.setId(4);
			facultadPC.setName("Pleitos y Cobranzas");
			catalogElements.add(facultadPC);
			
			/* List<CatalogElement> catalogElementsTemp = new ArrayList<CatalogElement>();
			
			catalogElementsTemp.addAll(Catalog.getCatalogPoderes(filter, currentIds));
			
			request.getSession().setAttribute("PODERES", "PODERES");
					
			for(CatalogElement element : catalogElementsTemp){ 
				if(!element.getName().contains("PG - ")){
					element.setName(element.getName().replaceAll("CP/PE - ", ""));
					catalogElements.add(element);
				}
															
			} */
				    				    		    
		}
		
		if(strCatalogId.equals("CATALOGOS")) {
			
			catalogElements = new Catalog("dercorp_add_campo_cat_tab").getList("ID_CATALOGO, NOM_CATALOGO", "WHERE APP_COMMON_PKG.SIN_ACENTOS_NI_NN_FN(UPPER(NOM_CATALOGO)) LIKE APP_COMMON_PKG.SIN_ACENTOS_NI_NN_FN(UPPER('%" + filter + "%'))OR '" + currentIds + "' LIKE '%' || ID_CATALOGO || '%' ORDER BY UPPER(APP_COMMON_PKG.SIN_ACENTOS_FN(NOM_CATALOGO))");
		}
	}
  	
	  
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
  	
  	
  	
  	
  	
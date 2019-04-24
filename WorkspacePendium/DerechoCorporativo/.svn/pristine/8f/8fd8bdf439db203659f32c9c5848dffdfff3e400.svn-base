<%@page import="mx.com.televisa.derechocorporativo.util.FacesUtils"%>
<%@page import="java.util.List"%>
<%@page import="mx.com.televisa.derechocorporativo.model.*"%>

<%
	{
	/*
    String idEmpresa = FacesUtils.getSessionBean().getIdCurrentEmpresa();
    List<CatalogElement> elems = new Catalog("DERCORP_METATBL_TAB").getList(
    			"ID_META_ROW, VAL_C1",
    			"WHERE ID_FLEX_TBL = 17 AND ID_EMPRESA = " + idEmpresa + "");
    */
    String idEmpresa = FacesUtils.getSessionBean().getIdCurrentEmpresa();
    List<CatalogElementV2> elems = new Catalog().dameEscrituras(Integer.parseInt(idEmpresa));
    Catalog.copyDataToApoderadosWK(Integer.valueOf(idEmpresa.trim()));
    
%>
<select name="selectEscritura" id="selectEscritura" onchange="setEscritura()" >
      <option value="0">---Selecione---</option>
    <% for (CatalogElementV2 elem : elems) {%>
		<% if(elem.getValC4() != null){%>
	    	<option value="<%= elem.getValC4() %>"><%= elem.getValValor() %></option>
	    <%}%>
    <% }%>
</select>	
<%
	}
%>
<%@page import="mx.com.televisa.derechocorporativo.util.FacesUtils"%>
<%@page import="java.util.List"%>
<%@page import="mx.com.televisa.derechocorporativo.model.*"%>

<%
	{
    //String idEmpresa = FacesUtils.getSessionBean().getIdCurrentEmpresa();
    List<CatalogElement> elems = new Catalog("DERCORP_ADD_CAMPO_CAT_VAL_TAB").getList(
    			"ID_CATALOGO_VALOR, NOM_CAT_VAL",
    			"WHERE ID_CATALOGO = 41");
%>
<select name="selectTipoPoder" id="selectTipoPoder" onchange="selectTipoPoderChanged()">
      <option value="0">--Seleccione--</option>
    <% for (CatalogElement elem : elems) {%>
    <option value="<%= elem.getId() %>"><%= elem.getName() %></option>
    <% }%>
</select>	
<%
	}
%>
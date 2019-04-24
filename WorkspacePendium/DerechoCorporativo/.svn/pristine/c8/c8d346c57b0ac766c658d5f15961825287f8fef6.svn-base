<%@page import="mx.com.televisa.derechocorporativo.util.FacesUtils"%>
<%@page import="java.util.List"%>
<%@page import="mx.com.televisa.derechocorporativo.model.*"%>
<%@page import="mx.com.televisa.derechocorporativo.bean.*"%>
<%@page import="mx.com.televisa.derechocorporativo.util.*"%>

<%
	{
	
	String idEmpresa = FacesUtils.getSessionBean().getIdCurrentEmpresa();
	
	String tipoElemento = request.getParameter("tipoElemento");
	String idComponent = request.getParameter("idComponent");
	String escritura   = (String)session.getAttribute("escrituraId");
	String tipoPoder   = (String)session.getAttribute("tipoPoderId");
	String grupo       = (String)session.getAttribute("grupo");
	
	String title  = "";
	
	
	if(tipoElemento == null) {
		
		tipoElemento = FacesUtils.getSessionBean().getApoderadosSessionVars().getTipoElemento(); 
	}
	
	if(idComponent  == null) {
		
		idComponent = FacesUtils.getSessionBean().getApoderadosSessionVars().getCustomIdComponentRight(); 
	}
	
	String idCatalogo = request.getParameter("idCatalogo");
	if(idCatalogo == null) {		
		idCatalogo = FacesUtils.getSessionBean().getApoderadosSessionVars().getCustomIdCatalogo(); 
	}
     
	
    List<ApoderadosAgregaBean> elems = new Catalog().dameApoderadosAgregados(Integer.parseInt(idCatalogo),
    																		 Integer.parseInt(idEmpresa),
    																		 Integer.parseInt(tipoPoder),
    																		 grupo,
    																		 escritura);
    
    
    //String isDoubleClick = request.getParameter("dobleClick"); 
    String isDoubleClick = (String) request.getSession().getAttribute("dobleClick"); 
    
    
    
%>
<select name="<%=idComponent %>" id="<%=idComponent %>" onchange="" multiple style="width:450px" size="10">
    <!--  <option value="*">(Todas)</option>-->
    <% for (ApoderadosAgregaBean elem : elems) {
    	if (elem.getAtributo1() == null || elem.getAtributo1().equals("")||elem.getAtributo1().trim().equals("null")){
			title = TextFormatter.ToFormatHtml(elem.getNombre());
		} else {
			title = TextFormatter.ToFormatHtml(elem.getAtributo1());
		}
    
    %>
    <% if(elem.getIdRevocacion() > 0){ %>
    	<option class='id_revocacion' value="<%= elem.getIdCatalogoValor() %>" title="<%= title %>" 
    	 <% if(isDoubleClick != null && isDoubleClick.equals("true")){ %>
				ondblclick="camposAdicionales(<%=elem.getIdCatalogoValor()%>,'<%=TextFormatter.replaceAccentsURL(elem.getNombre())%>',<%=elem.getIdCatalogo()%>);"
		 <%}%> 		
		 ><%= elem.getNombre() %> R    	 		
    	       
    	         
    	</option>
   <%}else{ %>
		<option value="<%= elem.getIdCatalogoValor() %>" title="<%= title %>" 
		<% if(isDoubleClick != null && isDoubleClick.equals("true")){ %>
				ondblclick="camposAdicionales(<%=elem.getIdCatalogoValor()%>,'<%=TextFormatter.replaceAccentsURL(elem.getNombre())%>',<%=elem.getIdCatalogo()%>);"
		<%}%>	
		><%= elem.getNombre() %>
					 
		</option> 
    <% }%>
    
    <% }%>
</select>	
<%
	}
%>
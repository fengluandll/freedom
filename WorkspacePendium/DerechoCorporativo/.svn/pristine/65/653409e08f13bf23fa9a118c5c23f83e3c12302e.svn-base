<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="mx.com.televisa.derechocorporativo.modules.reports.apoderados.RepApoderados"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="mx.com.televisa.derechocorporativo.util.FacesUtils"%>
<%@page import="java.util.List"%>
<%@page import="mx.com.televisa.derechocorporativo.model.*"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body onload="">

<%
	
    String idEmpresa_ = FacesUtils.getSessionBean().getIdCurrentEmpresa();
    List<CatalogElementV2> elems_ = new Catalog().dameEscrituras(Integer.parseInt(idEmpresa_));
    
    
    String paramEscritura = request.getParameter("paramEscritura");
    
    Map<String, Integer> mapCountGeneralesByEscritura = new HashMap<String, Integer>();
    Map<String, Integer> mapCountEspecialesByEscritura = new HashMap<String, Integer>();
    
    
%>
<div id="TabbedPanels2" class="TabbedPanels">
	    	<ul class="TabbedPanelsTabGroup">
 <% 
	 	String firstTabName = "";
		
		int c = 0;
	
 		for (CatalogElementV2 elem_ : elems_) {

 			
 			String idGenerales 	= "12290";
 			String idEspeciales = "12291";
 			String escritura = elem_.getValValor();
 			
 			int countGenerales = DetailApoderados.getCountApoderados(idEmpresa_, idGenerales, escritura);
 			int countEspeciales = DetailApoderados.getCountApoderados(idEmpresa_, idEspeciales, escritura);
 			
 			mapCountGeneralesByEscritura.put(escritura, countGenerales);
 			mapCountEspecialesByEscritura.put(escritura, countEspeciales);
 			
 			
 			if(elem_.getValC4() != null
 					&& (paramEscritura == null || paramEscritura.equals("") || elem_.getValValor().replace(",", "").contains(paramEscritura.replace(",", "")))
 					&& (countGenerales > 0 || countEspeciales > 0)
 					){
		
			//< % = elem_.getValC4() % > - < % = elem_.getValValor() % >
		
				if(c==0) {
	    		
						firstTabName = "Escritura: " + elem_.getValValor();
	    		}	
			
			
		%>
	    	
	    	
	    	
	    		<li class="TabbedPanelsTab" tabindex="0"  onclick="selectSubTabName('Escritura: <%=elem_.getValValor() %>');document.getElementById('apoderadosHiddenFirstTabName').value='Escritura: <%=elem_.getValValor() %>'">Escritura: <%=elem_.getValValor() %></li>
	  <%
	  		c++;
	  		}
 		}
 		%>
 		
 		
 		
	</ul>
	<div class="TabbedPanelsContentGroup">

		<% 
		
		for (CatalogElementV2 elem_ : elems_) { 

			String escritura = elem_.getValValor();
 			
 			int countGenerales = mapCountGeneralesByEscritura.get(escritura);
 			int countEspeciales = mapCountEspecialesByEscritura.get(escritura);
			
			if(elem_.getValC4() != null
 					&& (paramEscritura == null || paramEscritura.equals("") || elem_.getValValor().replace(",", "").contains(paramEscritura.replace(",", "")))
 					&& (countGenerales > 0 || countEspeciales > 0)
					) {
		
			
				FacesUtils.getSessionBean().setEscrituraApo(elem_.getValC4());
		
				//< % = elem_.getValC4() % > - < % = elem_.getValValor() % >
		%>
		
			<div class="TabbedPanelsContent">
			
			<br><br>
		
		

			<table width='100%' cellspacing='3' cellpadding='3' border=0 class='flexTable'>
				<tr>
					<td align='left' colspan=6 	class='tableRow3'><b style="color: black;">ESCRITURA: <%=FacesUtils.getSessionBean().getEscrituraApo()%> - <%= RepApoderados.getInfoEscrituras(idEmpresa_, escritura) %></b></td>
				</tr>
				<% if(countGenerales > 0) { %>
				<tr>
					<td align='left' colspan=6 class='tableRow4'><b>PODERES GENERALES</b></td>
				</tr>
				<tr>
					<th width='25%' class='tableHeader'>APODERADOS</th>
					<th width='15%' class='tableHeader'>ACTOS DE DOMINIO</th>
					<th width='15%' class='tableHeader'>ACTOS DE ADMINISTRACION</th>
					<th width='15%' class='tableHeader'>TITULOS DE CREDITO</th>
					<th width='15%' class='tableHeader'>PLEITOS Y COBRANZAS</th>
					<th width='15%' class='tableHeader'>PODER ESPECIAL</th>
				</tr>
					
					<!-- include file="DetailApoderados.jsp"%>  -->
					<jsp:include page="/jsp/reportes/apoderados/DetailApoderados.jsp">
						<jsp:param name="isRevocado" value="no"/>
					</jsp:include>
					
				<% } %>
			</table>
			<% if(countGenerales > 0) { %>
			<br><br><br>
			<% } %>
			<% if(countEspeciales > 0) { %>
			
			<table width='100%' cellspacing='3' cellpadding='3' border=0 class='flexTable'>
				<tr>
					<td align='left' colspan=6 class='tableRow4'><b>PODERES ESPECIALES</b></td>
				</tr>
				<tr>
					<th width='25%' class='tableHeader'>APODERADOS</th>
					<th width='15%' class='tableHeader'>ACTOS DE DOMINIO</th>
					<th width='15%' class='tableHeader'>ACTOS DE ADMINISTRACION</th>
					<th width='15%' class='tableHeader'>TITULOS DE CREDITO</th>
					<th width='15%' class='tableHeader'>PLEITOS Y COBRANZAS</th>
					<th width='15%' class='tableHeader'>PODER ESPECIAL</th>
				</tr>
					
					<!-- include file="DetailApoderadosEsp.jsp"%>  -->
					<jsp:include page="/jsp/captura/apoderados/DetailApoderadosEsp.jsp">
						<jsp:param name="isRevocado" value="no"/>
					</jsp:include>
					
			</table>
			<% } %>
			
			
			</div>
<%
}
}
%>

			<input type="hidden" name='apoderadosHiddenFirstTabName' id='apoderadosHiddenFirstTabName' value='<%=firstTabName%>'>
	</div>
</div>


					<script type="text/javascript">
						//var TabbedPanels2 = new Spry.Widget.TabbedPanels("TabbedPanels2");
					</script>
	    	
	   
	

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="mx.com.televisa.derechocorporativo.util.FacesUtils"%>
<%@page import="java.util.List"%>
<%@page import="mx.com.televisa.derechocorporativo.model.*"%>  
<%@page import="mx.com.televisa.derechocorporativo.modules.reports.apoderados.RepApoderados"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body onload="">

<%
	
	if(request.getParameter("ID_EMPRESA") != null) {
		
		FacesUtils.getSessionBean().setIdCurrentEmpresa(request.getParameter("ID_EMPRESA"));
	} 


	
    String idEmpresa_ = FacesUtils.getSessionBean().getIdCurrentEmpresa();
    List<CatalogElementV2> elems_ = new Catalog().dameEscrituras(Integer.parseInt(idEmpresa_));
    
	
    
%>

		<% 
		int count = 0;
		for (CatalogElementV2 elem_ : elems_) { 

			if(elem_.getValC4() != null) {
		
				/**Argu 06/04/2016
				    ** Implementacion para que ya no muestre secciones vacias de poderes especiales y 
				    ** generales
				 **/
				count++;
				FacesUtils.getSessionBean().setEscrituraApo(elem_.getValC4());
				String lstNumTipoPoderG = "12290";
				String lstEscrituraG		=	elem_.getValC4();//FacesUtils.getSessionBean().getEscrituraApo();
			    List<DetailApoderadosDTO> elemsApodG = new DetailApoderados().getApoderadosEsp(idEmpresa_, lstNumTipoPoderG, lstEscrituraG);
			    
			    String lstNumTipoPoderE = "12291";
				//String lstEscrituraE		=	elem_.getValC4();//FacesUtils.getSessionBean().getEscrituraApo();
			    List<DetailApoderadosDTO> elemsApodE = new DetailApoderados().getApoderadosEsp(idEmpresa_, lstNumTipoPoderE, lstEscrituraG);
				//< % = elem_.getValC4() % > - < % = elem_.getValValor() % >
		%>
		
			<%if(!elemsApodG.isEmpty() || !elemsApodE.isEmpty()){ 
				if(count > 1){
			%>
			<br><br><br>
			<%} %>
			<fieldset>
				<legend>Escritura: <%=elem_.getValValor() %> - <%= RepApoderados.getInfoEscrituras(idEmpresa_, elem_.getValValor()) %></legend>
			
			
		
			<%if(!elemsApodG.isEmpty()){ %>
			<table width='100%' cellspacing='3' cellpadding='3' border=0 class='flexTable'>
				<tr>
					<td align='left' colspan=6 	class='tableRow3'><b>ESCRITURA: <%=FacesUtils.getSessionBean().getEscrituraApo()%></b></td>
				</tr>
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
					
					<!-- %@include file="DetailApoderadosPrint.jsp"%> -->
					<jsp:include page="/jsp/captura/apoderados/DetailApoderadosPrint.jsp">
						<jsp:param name="isRevocado" value="no"/>
					</jsp:include>
					
				
			</table>
			<%} %>
			<br><br><br>
			<%if(!elemsApodE.isEmpty()){ %>
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
					
					<!-- %@include file="DetailApoderadosEspPrint.jsp"%> -->
					<jsp:include page="/jsp/captura/apoderados/DetailApoderadosEspPrint.jsp">
						<jsp:param name="isRevocado" value="no"/>
					</jsp:include>
					
			</table>
			<%} %>
			
			</fieldset>
			
<%
			}
}
}
%>



</body>
</html>
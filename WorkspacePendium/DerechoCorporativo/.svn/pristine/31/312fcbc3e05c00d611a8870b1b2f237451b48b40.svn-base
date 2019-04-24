<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="mx.com.televisa.derechocorporativo.util.FacesUtils"%>
<%@page import="mx.com.televisa.derechocorporativo.model.DetailApoderados"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Apoderados</title>
<%@include file="/css/kaz-styles.jsp"%>

<style type="text/css">
.id_revocacion{
	color:red;
}
body {
	margin-top: 0;
	margin-left: 0;
	margin-right: 0;
	margin-bottom: 0;
}
</style>

</head>
<body>
	<!-- <table width='96%' cellspacing='3' cellpadding='3' border=0 class='flexTable'> -->
	<table width='112%' cellspacing='3' cellpadding='3' border=0 class='flexTable'>
		<tr>
		<%
		System.out.println("Escritura: "+FacesUtils.getSessionBean().getEscrituraApo()); 
		String lsEscritura = FacesUtils.getSessionBean().getEscrituraApo();
		lsEscritura = lsEscritura.replace("Ã³", "ó");
		FacesUtils.getSessionBean().setEscrituraApo(lsEscritura);
		//System.out.println("Escritura: "+lsEscritura);
		System.out.println("Escritura: "+FacesUtils.getSessionBean().getEscrituraApo());
		
		%>
			<td align='left' colspan=6 	class='tableRow3'><b>ESCRITURA: <%=lsEscritura%></b></td>
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
			
		
			<jsp:include page="/jsp/captura/apoderados/DetailApoderados.jsp">
				<jsp:param name="isRevocado" value="si"/>
			</jsp:include>
		<tr>
			<td colspan="6">
				<%String idEmpresa 		= FacesUtils.getSessionBean().getIdCurrentEmpresa(); %>
				<%=new DetailApoderados().getRevocados(idEmpresa,"12290",FacesUtils.getSessionBean().getEscrituraApo()) %>	
			</td>
		</tr>	
		
	</table>
	<br><br><br>
	<table width='112%' cellspacing='3' cellpadding='3' border=0 class='flexTable'>
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
			
		
			<jsp:include page="/jsp/captura/apoderados/DetailApoderadosEsp.jsp">
				<jsp:param name="isRevocado" value="si"/>
			</jsp:include>
		<tr>
			<td colspan="6">
				
				<%=new DetailApoderados().getRevocados(idEmpresa,"12291",FacesUtils.getSessionBean().getEscrituraApo()) %>	
			</td>
		</tr>
	</table>

</body>
</html>
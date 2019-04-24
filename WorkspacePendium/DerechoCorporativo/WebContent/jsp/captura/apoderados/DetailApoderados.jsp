<%@page import="mx.com.televisa.derechocorporativo.util.FacesUtils"%>
<%@page import="java.util.List"%>
<%@page import="mx.com.televisa.derechocorporativo.model.*"%>

<%
	{
		boolean alterClass = false;
		String trStyle = "";

		String idEmpresa 		= FacesUtils.getSessionBean().getIdCurrentEmpresa();
		String lstNumTipoPoder 	= "12290";
		String lstEscritura		=	FacesUtils.getSessionBean().getEscrituraApo();
		//List<DetailApoderadosDTO> elems = new DetailApoderados().getApoderados(idEmpresa, lstNumTipoPoder, lstEscritura);
		//JJAQ
		String isRevocado = request.getParameter("isRevocado");
		List<DetailApoderadosDTO> elems = null;
		if(isRevocado != null && isRevocado.equals("si")){
			elems = new DetailApoderados().getApoderadosConRevocados(idEmpresa, lstNumTipoPoder, lstEscritura);			
		}else if(isRevocado != null && isRevocado.equals("no")){
			elems = new DetailApoderados().getApoderados(idEmpresa, lstNumTipoPoder, lstEscritura);		
		}
		for (DetailApoderadosDTO elem : elems) {
			alterClass = !alterClass;
			trStyle = (alterClass)?"class=\"tableRow2\"" : "";

%>		<tr <%=trStyle%> >
			<td><%= elem.getApoderados().replace("(","<br><font color='#555555'>(").replace(")", ")</font>")%> </td>
			<td><%= elem.getActosDominio()%></td>
			<td><%= elem.getActosAdministracion()%></td>
			<td><%= elem.getTitulosCredito()%></td>
			<td><%= elem.getPleitosCobranzas()%></td>
			<td><%= elem.getPoderesEspeciales()%></td>
		</tr>	
<%
		}
	}
%>
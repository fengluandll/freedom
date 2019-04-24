<%@page import="mx.com.televisa.derechocorporativo.modules.apoderados.ApoderadosUtil"%>
<%@page import="mx.com.televisa.derechocorporativo.util.TextFormatter"%>
<%@page import="mx.com.televisa.derechocorporativo.util.StringUtils"%>
<%@page import="java.util.ArrayList"%>
<%@page import="mx.com.televisa.derechocorporativo.util.FacesUtils"%>
<%@page import="java.util.List"%>
<%@page import="mx.com.televisa.derechocorporativo.model.*"%>

<%
	{
		
		String myParamApoderados = (request.getParameter("hiddenApoderados") != null) ? request.getParameter("hiddenApoderados") : "";
		
		ArrayList<String> apoderadoNames = new ArrayList<String>();
		String[] ids = StringUtils.split(myParamApoderados, ",");
		
		for(String id : ids) {
			
			//apoderadoNames.add(Catalog.getCatalogElementDescription(id));
			String name = Catalog.getCatalogElementDescription(id);
			if(name != null) {
				apoderadoNames.add(name);
			}

			
		}
		
		
		String myParamPoder = (request.getParameter("hiddenPoder") != null) ? request.getParameter("hiddenPoder") : "";
	
		
		ArrayList<String> poderesNames = new ArrayList<String>();
		
		if(myParamPoder != null && !myParamPoder.equals("")) {
			String[] p_ids = StringUtils.split(myParamPoder, ",");
			
			for(String p_id : p_ids) {
				
				poderesNames.add(Catalog.getCatalogElementDescription(p_id));	
			}
		}
		

		boolean alterClass = false;
		String trStyle = "";

		String idEmpresa = FacesUtils.getSessionBean().getIdCurrentEmpresa();
		String lstNumTipoPoder = "12291";
		String lstEscritura		=	FacesUtils.getSessionBean().getEscrituraApo();
		//List<DetailApoderadosDTO> elems = new DetailApoderados().getApoderadosEsp(idEmpresa, lstNumTipoPoder, lstEscritura);
		//JJAQ
		String isRevocado = request.getParameter("isRevocado");
		List<DetailApoderadosDTO> elems = null;
		if(isRevocado != null && isRevocado.equals("si")){
			elems = new DetailApoderados().getApoderadosConRevocados(idEmpresa, lstNumTipoPoder, lstEscritura);			
		}else if(isRevocado != null && isRevocado.equals("no")){
			elems = new DetailApoderados().getApoderados(idEmpresa, lstNumTipoPoder, lstEscritura);		
		}
		

		boolean existRows = false;
		
		
		
		for (DetailApoderadosDTO elem : elems) {
			
			

			boolean showRow = false;
			
			
			String grupo = elem.getApoderados().substring(0, elem.getApoderados().indexOf("</b>")) + "</b><br><br>";
			System.out.println("grupo "+grupo);
			String foundApoderados = "";
			
			
	
			if(apoderadoNames.size() == 0) {
				showRow = true;
				existRows = true;
				
				grupo = "";
				foundApoderados = elem.getApoderados(); 
			}
	
	
			for(String apoderadoName : apoderadoNames){
			
				if(TextFormatter.removeAccents(elem.getApoderados().trim()).contains(TextFormatter.removeAccents(apoderadoName.trim()))) {
					
					showRow = true;
					existRows = true;
					
					foundApoderados += "<li>" + apoderadoName + "</li>";
				}
			}
			
			
			
			if(showRow){
			
			
			
			
			alterClass = !alterClass;
			trStyle = (alterClass)?"class=\"tableRow2\"" : "";
%>		<tr <%=trStyle%> >
			<td>
				<%= grupo%>
				 
				 <%=foundApoderados.replace("(","<br><font color='#555555'>(").replace(")", ")</font>") %>
			</td>
			<td><%= ApoderadosUtil.poderesMatch(elem.getActosDominio(), poderesNames) %></td>
			<td><%= ApoderadosUtil.poderesMatch(elem.getActosAdministracion(), poderesNames)%></td>
			<td><%= ApoderadosUtil.poderesMatch(elem.getTitulosCredito(), poderesNames)%></td>
			<td><%= ApoderadosUtil.poderesMatch(elem.getPleitosCobranzas(), poderesNames)%></td>
			<td><%= ApoderadosUtil.poderesMatch(elem.getPoderesEspeciales(), poderesNames)%></td>
		</tr>	
<%
		}
		}
		
	//	if(!existRows) {
%>
<!-- 
	<tr>
		<td colspan="6">
			Ningun Registro
		</td>
	</tr>
 -->	
<%		
	//	}
	}
%>
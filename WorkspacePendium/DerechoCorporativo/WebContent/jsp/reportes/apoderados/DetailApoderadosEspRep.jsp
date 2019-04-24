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
		String idCatalogoPoderes = (String)session.getAttribute("idCatalogoPoderes");
		if(myParamPoder.equals("")){
			idCatalogoPoderes = "0";
		}
		
		/*if(idCatalogoPoderes == null || idCatalogoPoderes.equals("") && myParamPoder.equals("")){
			idCatalogoPoderes = "33,34,35,36,37,0";
		}*/
		
		//List<DetailApoderadosDTO> elems = new DetailApoderados().getApoderadosEsp(idEmpresa, lstNumTipoPoder, lstEscritura);
		//JJAQ
		String isRevocado = request.getParameter("isRevocado");
		List<DetailApoderadosDTO> elems = null;
		if(isRevocado != null && isRevocado.equals("si")){
			elems = new DetailApoderados().getApoderadosConRevocados(idEmpresa, lstNumTipoPoder, lstEscritura);			
		}else if(isRevocado != null && isRevocado.equals("no")){
			if(idCatalogoPoderes.equals("0")){
				elems = new DetailApoderados().getApoderadosReporteDefault(idEmpresa, lstNumTipoPoder, lstEscritura,idCatalogoPoderes);
			}else{
				elems = new DetailApoderados().getApoderadosReporte(idEmpresa, lstNumTipoPoder, lstEscritura,idCatalogoPoderes);	
			}		
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
			<%String[] arrayPoderes = idCatalogoPoderes.split(",");
	    	String lstDominio 	= "";
	    	String lstAdmin 	= "";
	    	String lstCredito 	= "";
	    	String lstPleito 	= "";
	    	String lstEspecial 	= "";
	    	for(String str : arrayPoderes){
	    		if(str.equals("33")){
	    			lstDominio = str;
	    		}else if(str.equals("34")){
	    			lstAdmin = str;
	    		}else if(str.equals("35")){
	    			lstCredito = str;
	    		}else if(str.equals("36")){
	    			lstPleito = str;
	    		}else if(str.equals("37")){
	    			lstEspecial = str;
	    		}
	    	}
	    	%>
	    	<!-- Se quita el poderesMatch-->
				<%if(lstDominio.equals("33") || idCatalogoPoderes.equals("0")){ %>
				<td><%= elem.getActosDominio() %></td>
				<%} %>
				<%if(lstAdmin.equals("34") || idCatalogoPoderes.equals("0")){ %>
				<td><%= elem.getActosAdministracion() %></td>
				<%} %>
				<%if(lstCredito.equals("35") || idCatalogoPoderes.equals("0")){ %>
				<td><%= elem.getTitulosCredito() %></td>
				<%} %>
				<%if(lstPleito.equals("36") || idCatalogoPoderes.equals("0")){ %>
				<td><%= elem.getPleitosCobranzas() %></td>
				<%} %>
				<%if(lstEspecial.equals("37") || idCatalogoPoderes.equals("0")){ %>
				<td><%= elem.getPoderesEspeciales() %></td>
				<%} %>
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
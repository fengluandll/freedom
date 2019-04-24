<%@page import="org.apache.commons.lang3.StringUtils"%>

<%@page import="java.util.StringTokenizer"%>
<%@page import="mx.com.televisa.derechocorporativo.daos.ConstanciaActasDAO"%>
<%@page import="mx.com.televisa.derechocorporativo.util.GetMonth"%>
<%@page import="java.util.ArrayList"%>
<%@page import="mx.com.televisa.derechocorporativo.bean.ConstanciaActasBean"%>
			
	
<%  String lsidEmp 				= request.getParameter("idEmpresa");
	String nomEmpresa 			= request.getParameter("nomEmpresa");
	String lsFecha 				= request.getParameter("lsFecha");
	String lsMes 				= request.getParameter("lsMes");
	String lsAnio 				= request.getParameter("lsAnio");
	String lstAudExternos		= request.getParameter("lstAudExternos");
	String lstDirAudExt   		= request.getParameter("lstDirAudExt");
	String lsEjercicio   		= request.getParameter("lsEjercicio");
	ArrayList<ConstanciaActasBean> alConstanciaActas   	= (ArrayList<ConstanciaActasBean>)session.getAttribute("alConstanciaActas");
	 %>
			
	<div>
		<table width="70%" align="center" border="0">
		  <tr>
		    <td align="center" style="font-size: 16px;">MARIA AZUCENA DOMÍNGUEZ COBÍAN</td>
		  </tr>
		  <tr>
		    <td>&nbsp;</td>
		  </tr>
		  <tr>
		    <td>&nbsp;</td>
		  </tr>
		  <tr>
		    <td>&nbsp;</td>
		  </tr>
		  <tr>
		    <td>&nbsp;</td>
		  </tr>
		  <tr>
		    <td>&nbsp;</td>
		  </tr>
		  <tr>
		    <td>&nbsp;</td>
		  </tr>
		  <tr>
		    <td align="right" class="formatoLetras1">Ciudad de México, <%=lsFecha%> de <%=lsMes%> de <%=lsAnio%></td>
		  </tr>
		  <tr>
		    <td>&nbsp;</td>
		  </tr>
		  <tr>
		    <td>&nbsp;</td>
		  </tr>
		  <tr>
		    <td>&nbsp;</td>
		  </tr>
		  <tr>
		    <td><span class="formatoLetras1"><strong><%=lstAudExternos%></strong></span></td>
		  </tr>
		  <tr>
		    <td><pre class="formatoPre"><%=lstDirAudExt%></pre></td>
		  </tr>
		  <tr>
		    <td>&nbsp;</td>
		  </tr>
		  <tr>
		    <td>&nbsp;</td>
		  </tr>
		  <tr>
		    <td>&nbsp;</td>
		  </tr>
		  <tr>
		    <td class="formatoLetras1">Estimados Señores:</td>
		  </tr>
		  <tr>
		    <td>&nbsp;</td>
		  </tr>
		  <tr>
		    <td>&nbsp;</td>
		  </tr>
		  <tr>
		    <td class="formatoLetras1" style="text-align: justify;">Con motivo de la auditoría de los estados financieros de <%=nomEmpresa%> al 31 de diciembre de <%=lsEjercicio%> que están ustedes llevando a cabo, en mi carácter de responsable de los asuntos jurídicos corporativos de dicha sociedad,
			     he puesto a disposición de sus representantes los libros de actas de Asambleas de Socios y del Consejo de Gerentes de dicha compañia, en los que constan las actas de las sesiones efectuadas en las fechas
			    que abajo se indican y de las cuales anexo copia fotostática.
		    </td>
		    
		  </tr>
		  <tr>
		    <td>&nbsp;</td>
		  </tr>
		  <tr>
		    <td class="formatoLetras1" style="text-align: justify;">
		    	<span>Estas actas constituyen una relación de todas las asambleas de socios y de las sesiones del consejo, celebradas del
		    	1° de enero al 31 de diciembre de <%=lsEjercicio%>, inclusive.</span>
		    </td>
		    </tr> 
		  <tr>
		    <td>&nbsp;</td>
		  </tr>
		  <tr>
		    <td>&nbsp;</td>
		  </tr>
		  
		  	<%
		  	for(ConstanciaActasBean constanciaActas : alConstanciaActas){
		  	   if(!constanciaActas.getPsAsamblea().equalsIgnoreCase("N/A")){
		  	   %>
			  	<tr>
			    <td align="" style="text-align: justify;" class="formatoLetras1"><%=constanciaActas.getPsFecha()%> - <%=constanciaActas.getPsAsamblea()%></td>
			    </tr>
		    
		    <%} 
		  	   } %>
		  <tr>
		    <td>&nbsp;</td>
		  </tr>
		  <tr>
		    <td>&nbsp;</td>
		  </tr>
		  <tr>
		    <td align="center" class="formatoLetras1" style="text-align: justify;">Ratifico a ustedes desconocer cualquier hecho no consignado en las actas respectivas correspondientes a las asambleas y sesiones antes mencionadas.</td>
		  </tr>
		  <tr>
		    <td align="center">&nbsp;</td>
		  </tr>
		  <tr>
		    <td>&nbsp;</td>
		  </tr>
		  <tr>
		    <td align="left" class="formatoLetras1">Atentamente,</td>
		  </tr>
		  <tr>
		    <td align="left">&nbsp;</td>
		  </tr>
		  <tr>
		    <td>&nbsp;</td>
		  </tr>
		  <tr>
		    <td>&nbsp;</td>
		  </tr>
		  <tr>
		    <td>&nbsp;</td>
		  </tr>
		  <tr>
		    <td align="left" class="formatoLetras1">MARIA AZUCENA DOMÍNGUEZ COBÍAN</td>
		  </tr>
	</table>
</div>	


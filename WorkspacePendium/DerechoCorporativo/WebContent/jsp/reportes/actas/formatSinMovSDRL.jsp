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
	String lsRemitente			= request.getParameter("lsRemitente");
	ArrayList<ConstanciaActasBean> alConstanciaActas   	= (ArrayList<ConstanciaActasBean>)session.getAttribute("alConstanciaActas");
	%>
			
	<div>
		<table width="70%" align="center" border="0">
		  <tr>
		    <td align="center" style="font-size: 16px;"><%=lsRemitente%></td>
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
		    <td class=""><pre class="formatoPre"><%=lstDirAudExt%></pre></td>
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
		    <td class="formatoLetras1" style="text-align: justify;">Con motivo de la auditoría de los estados financieros de <%=nomEmpresa%>, al 31 de diciembre de <%=lsEjercicio%>, que están ustedes llevando a cabo, en mi carácter de responsable de los asuntos jurídicos 
			    corporativos de dicha sociedad, por este conducto les informo que no se celebraron asambleas ni sesiones de consejo por el periodo comprendido del 1° de enero al 31 de diciembre de <%=lsEjercicio%>  inclusive.
		    </td>
		    
		  </tr>
		  <tr>
		    <td>&nbsp;</td>
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
		    <td align="left" class="formatoLetras1"><%=lsRemitente%></td>
		  </tr>
	</table>
</div>	

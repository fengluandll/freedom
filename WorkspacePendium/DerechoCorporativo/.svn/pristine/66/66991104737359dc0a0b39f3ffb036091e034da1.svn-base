<%@page import="mx.com.televisa.derechocorporativo.modules.fusion.FusionCopy"%>
<%@page import="mx.com.televisa.derechocorporativo.modules.flextabs.FlexTable"%>
<%@page import="mx.com.televisa.derechocorporativo.bean.SessionBean" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.min.js"></script> 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.css">
<%
	





	String flexTableId = request.getParameter("flexTableId");
	String key =  request.getParameter("key");
	String IdEmpresa = request.getParameter("IdEmpresa");
	
	String params = "flexTableId=" + flexTableId + "&key=" + key + "&IdEmpresa" + IdEmpresa;
	
	FlexTable ft = new FlexTable(flexTableId);
	
	FusionCopy.doCopy(key,IdEmpresa);
%>
			

	<title>Copia de Fusión</title>
	<%@include file="/css/kaz-styles.jsp"%>
	<%@include file="/jsp/captura/part/validaciones.jsp"%>
	
	<script type="text/javascript">
	
		function hideShow(elementId, imgMax, imgMin) {
			
			var currDisplay = document.getElementById(elementId).style.display;
			
			if(currDisplay == 'none') {
				
				document.getElementById(elementId).style.display = '';
				
				document.getElementById(imgMax).style.display = 'none';
				document.getElementById(imgMin).style.display = '';
				
			} else {
				
				document.getElementById(elementId).style.display = 'none';
				
				document.getElementById(imgMax).style.display = '';
				document.getElementById(imgMin).style.display = 'none';
			} 
			
			
		}
	
	</script>
</head>
<body>
<div style="background-image: url('<%= request.getContextPath() %>/img/borders/page_border/border05.png');">

<%SessionBean sessionBean =  (SessionBean)session.getAttribute("sessionBean");
  String nomEmpresa = sessionBean.getNomEmpresa();
%>
<table width="80%" border="0" cellspacing="0" cellpadding="0" align="center">
						<tr>
				          <td>&nbsp;</td>
				        </tr>
				        <tr>
				          <td>&nbsp;</td>
				        </tr>
						<tr style="background-image: url('<%= request.getContextPath() %>/img/tableHeaderBack.png')">
							<th width="100%" align="center">
								<font style="color: white;">
									<h3>
									COPIAR FUSIÓN A OTRA EMPRESA
									</h3>
								</font>
							</th>
						</tr>
						<tr>
				          <td>&nbsp;</td>
				        </tr>
				        <tr>
				          <td>&nbsp;</td>
				        </tr>
					    <tr>
					      <td colspan="2" >
					      Se copió exitosamente el registro de Fusión a la empresa seleccionada.
						  </td>
				        </tr>		
				        <tr>
				          <td>&nbsp;</td>
				        </tr>
					    <tr>
					      <td align="right">
					      	<input type="button" Value='Cerrar Ventana' onClick="window.close()">
					      	 
					      </td>
				        </tr>	
				         <tr>
				          <td>&nbsp;</td>
				        </tr>
				         <tr>
				          <td>&nbsp;</td>
				        </tr>	  
				      </table>
				      
				      

</div>
</body>
</html>
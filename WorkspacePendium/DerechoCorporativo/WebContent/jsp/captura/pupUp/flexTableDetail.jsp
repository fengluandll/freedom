<%@page import="mx.com.televisa.derechocorporativo.bean.SessionBean" %>
<%@page import="mx.com.televisa.derechocorporativo.modules.flextabs.FlexTable"%>
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
	String rowId =  request.getParameter("rowId");
	
	String params = "flexTableId=" + flexTableId + "&rowId=" + rowId;
	
	FlexTable ft = new FlexTable(flexTableId);

%>
			

	<title><%=ft.nomFlex %> - Detalle</title>
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

<%SessionBean sessionBean2 =  (SessionBean)session.getAttribute("sessionBean");
  String nomEmpresa = sessionBean2.getNomEmpresa();
%>
<table width="80%" border="0" cellspacing="0" cellpadding="0" align="center">
						<tr>
							<td colspan="2" align="right">
								<br>
								<a href="flexTableDetailPrint.jsp?<%=params%>">Versión Imprimible</a>
								<br>
								<br>
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<br>
								<h3><b><%=nomEmpresa%></b></h3>
								<br>
							</td>
						</tr>
						<tr style="background-image: url('<%= request.getContextPath() %>/img/tableHeaderBack.png')">
							<th width="100%" align="center">
								<font style="color: white;">
									<h3>
									<%=ft.nomFlex %> - Detalle
									</h3>
								</font>
							</th>
							<td  align="right">
								<%--
								<img src="<%= request.getContextPath() %>/img/close.png" onclick="closeForm()"></img>
								 --%>
							</td>
						</tr>
					    <tr>
					      <td colspan="2" >
							
							
							
	<%= ft.toHTMLForm(rowId, null, null).replace("../../img/", "../../../img/") %>


</td>
				        </tr>
				        <%--
					    <tr>
					      <td width="35%">&nbsp;</td>
					      <td>&nbsp;</td>
				        </tr>
					    <tr>
					      <td>&nbsp;</td>
					      <td>&nbsp;</td>
				        </tr>
				         
					    <tr>
					      <td>&nbsp;</td>
					      <td align="right">
					      	<img src='<%= request.getContextPath() %>/img/wait-bar.gif' id='imgFlexTableWait' style="display: none">
					      	<input type="button" id='flexTableformButton' Value='Guardar' onclick='flexTableSave()'>
					      </td>
				        </tr>
				        --%>
				      </table>
				      
				      

</div>
</body>
</html>
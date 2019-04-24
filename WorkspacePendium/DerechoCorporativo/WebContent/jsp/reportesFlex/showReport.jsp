<%@page import="mx.com.televisa.derechocorporativo.util.FacesUtils"%>
<%@page import="java.util.Map"%>
<%@page import="mx.com.televisa.derechocorporativo.modules.reportsFlex.ReportFlex"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%@include file="/css/kaz-styles.jsp"%>


<%
	String id = request.getParameter("id");

	ReportFlex rep = ReportFlex.getReport(id);

	FacesUtils.getSessionBean().setCurrentFlexReportId(id);
	
%>



<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajax/simpleAjaxUtil.js"></script>

<script type="text/javascript">
	function searchCampos() {

		var ajaxRequest = simpleAjaxUtil_getXmlHttpObject();
		if (ajaxRequest == null) {alert("Browser does not support HTTP Request");return;}

		var seccionId = 0;
		var subSeccionId = 0;
		var filter = document.getElementById('fieldFilter').value;

		var url = 'ajax/commonFieldTable.jsp'; 
		var parameters = "seccionId=" + seccionId + "&subSeccionId="
				+ subSeccionId + "&filter=" + filter + "&flexTabs=NO&flexColumns=SI";

		ajaxRequest.open("post", url, true);
		ajaxRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		ajaxRequest.onreadystatechange = function() {

			if (ajaxRequest.readyState == 4 && ajaxRequest.status == 200) {

				var response = ajaxRequest.responseText;

				//alert(response);

				document.getElementById('detalleSelectCampo').innerHTML = response;

			}
		};
		ajaxRequest.send(parameters);
	}
	
	
	function selectCampo(id) {
		
		var ajaxRequest = simpleAjaxUtil_getXmlHttpObject();
		if (ajaxRequest == null) {alert("Browser does not support HTTP Request");return;}

		var url = 'ajax/showReportAddParam.jsp';		
		var parameters = "id=" + id;

		ajaxRequest.open("post", url, true);
		ajaxRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		ajaxRequest.onreadystatechange = function() {

			if (ajaxRequest.readyState == 4 && ajaxRequest.status == 200) {

				var response = ajaxRequest.responseText;

				//alert(response);

				document.getElementById('detalleSelectParams').innerHTML = response;

			}
		};
		ajaxRequest.send(parameters);
		
	}
	
	


	function quitarParametro(id) {
		
		//alert('ok');
		
		var ajaxRequest = simpleAjaxUtil_getXmlHttpObject();
		if (ajaxRequest == null) {alert("Browser does not support HTTP Request");return;}

		var url = 'ajax/showReportRemoveParam.jsp';		
		var parameters = "id=" + id;

		ajaxRequest.open("post", url, true);
		ajaxRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		ajaxRequest.onreadystatechange = function() {

			if (ajaxRequest.readyState == 4 && ajaxRequest.status == 200) {

				var response = ajaxRequest.responseText;

				//alert(response);
				//alert('ok2');

				document.getElementById('detalleSelectParams').innerHTML = response;

			}
		};
		ajaxRequest.send(parameters);
		
	}
	
	
	function updateParamValue(id, value) {
		
		var ajaxRequest = simpleAjaxUtil_getXmlHttpObject();
		if (ajaxRequest == null) {alert("Browser does not support HTTP Request");return;}

		var url = 'ajax/showReportAddParamVal.jsp';		
		var parameters = "id=" + id + 
						 "&value=" + value;

		ajaxRequest.open("post", url, true);
		ajaxRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		ajaxRequest.onreadystatechange = function() {

			if (ajaxRequest.readyState == 4 && ajaxRequest.status == 200) {

				var response = ajaxRequest.responseText;

				//alert(response);

				//document.getElementById('').innerHTML = response;

			}
		};
		ajaxRequest.send(parameters);
		
	}
	

	function showParams() {
		
		var ajaxRequest = simpleAjaxUtil_getXmlHttpObject();
		if (ajaxRequest == null) {alert("Browser does not support HTTP Request");return;}

		var url = 'ajax/showReportShowParams.jsp';		
		var parameters = "";

		ajaxRequest.open("post", url, true);
		ajaxRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		ajaxRequest.onreadystatechange = function() {

			if (ajaxRequest.readyState == 4 && ajaxRequest.status == 200) {

				var response = ajaxRequest.responseText;

				//alert(response);

				document.getElementById('detalleSelectParams').innerHTML = response;

			}
		};
		ajaxRequest.send(parameters);
		
	}
	
	function openMultiSelectPupUp(catalogId, targetIds, targetNames, namesProperty, currentValue, updateFieldCode) {
		
		/* alert(catalogId);
		alert(targetIds);
		alert(targetNames);
		alert(currentValue);
		alert(javaScriptCode); */
		
		
		/*
		 var left = (screen.width - 250) / 2;
	    var top = (screen.height - 300) / 4;  // for 25% - devide by 4  |  for 33% - devide by 3
	    */

	    var left = screen.width - ((screen.width - 300) / 2);
	    var top = (screen.height - 300) / 2;  // for 25% - devide by 4  |  for 33% - devide by 3
	    
	    
	    //alert('../components/multiselectPupUp/multiselect.jsp?catalogId=' + catalogId + '&targetIds=' + targetIds + '&targetNames=' + targetNames);
	    

		newwindow=window.open('../components/multiselectPupUp/multiselect.jsp?catalogId=' + catalogId + '&targetIds=' + targetIds + '&targetNames=' + targetNames + '&currentValue=' + currentValue + '&updateFieldCode=' + updateFieldCode + '&namesProperty=' + namesProperty,
							'name',
							'height=600,width=450,toolbar=0,menubar=0,location=0,status=0,scrollbars=1,resizable=1,left=' + left + ',top=' + top);

		if (window.focus) {newwindow.focus()}


		//newwindow.document.getElementById('var1').value = val1;	
		//newwindow.document.getElementById('var2').value = val2;


		return false;

	}

	
	function openCalendarSelectPupUp(targetField, updateFieldCode) {
		
	    var left = screen.width - ((screen.width - 250) / 2);
	    var top = (screen.height - 250) / 2;  // for 25% - devide by 4  |  for 33% - devide by 3

		newwindow=window.open('../components/calendar/calPupUp.jsp?targetField=' + targetField + '&updateFieldCode=' + updateFieldCode,
							'name',
							'height=300,width=400,toolbar=0,menubar=0,location=0,status=0,scrollbars=1,resizable=1,left=' + left + ',top=' + top);

		if (window.focus) {newwindow.focus()}

		return false;
	}
	
	
function waitBar() {
		
	document.getElementById('imgCapWait').style.display = '';
	document.getElementById('btnEjecutar').style.display = 'none';
}
	
	
	
</script>

</head>
<body>




		<table width="100%" align="center">
			<tr>
				<td><jsp:include
						page="/jsp/components/pages_border/open-pre-title.jsp"></jsp:include>
				
						Reporte: <%= rep.getNomReporte() %>
					
				<jsp:include
						page="/jsp/components/pages_border/open-post-title.jsp"></jsp:include>
				<jsp:include page="/jsp/components/backPageReports/regresaMenuReportes.jsp">
					<jsp:param name="action" value="rPersonalizadosFlex"/>
				</jsp:include>



	
	

<table width="100%">
	<tr>
		<td valign="top" width="50%">
			<fieldset>
				<legend>Filtros Seleccionados</legend>
			
			
			
				<div id='detalleSelectParams'></div>
			
				<br>
				<div align="right">
					<%
						//Map <String,String> map = FacesUtils.getSessionBean().getDynamicParams();
						//if(map.size() != 0) {
						
					%>
					<form action="showReportAsHTML.jsp" method="get">
						<input type='hidden' id='repId' name='repId' value='<%= id %>'>
						<input type="submit" id='btnEjecutar' value="Ejecutar Reporte" onclick="waitBar()">
						
						<img src='<%= request.getContextPath() %>/img/wait-bar.gif' id='imgCapWait' style="display: none;">
						
					</form>
					<%
						//}
					%>
				</div>
			
			</fieldset>
		</td>
		<td valign="top">
			
			<table>
				<tr>
					<td>
					Buscar por: <input name='fieldFilter' id='fieldFilter' onkeyup='searchCampos()'>
				
					</td>
				</tr>
				<tr>
					<td>
					
						<div id='detalleSelectCampo' style="height:200px;overflow-y:scroll"></div>
					</td>
				</tr>
			</table>
		
		
		</td>
	</tr>
</table>


<script type="text/javascript">
	
	showParams();
	searchCampos();
</script>





				<jsp:include
					page="/jsp/components/pages_border/close.jsp"></jsp:include></td>
		</tr>
	</table>






</body>
</html>
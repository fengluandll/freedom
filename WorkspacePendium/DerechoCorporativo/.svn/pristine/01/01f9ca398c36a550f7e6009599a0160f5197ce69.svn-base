<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Libros de Actas Asambleas y del Consejo</title>
<%@include file="/css/kaz-styles.jsp"%>

<script type="text/javascript">


function openMultiSelectPupUp(catalogId, targetIds, targetNames, namesProperty, currentValue, namesFormat) {

    var left = screen.width - ((screen.width - 300) / 2);
    var top = (screen.height - 300) / 2;  // for 25% - devide by 4  |  for 33% - devide by 3

	newwindow=window.open('../../components/multiselectPupUp/multiselect.jsp?catalogId=' + catalogId + '&targetIds=' + targetIds + '&targetNames=' + targetNames + '&currentValue=' + currentValue + '&namesProperty=' + namesProperty + '&namesFormat=' + namesFormat,
						'name',
						'height=600,width=450,toolbar=0,menubar=0,location=0,status=0,scrollbars=1,resizable=1,left=' + left + ',top=' + top);

	if (window.focus) {newwindow.focus()}

	return false;
}

</script>

</head>
<body>
			<jsp:include page="/jsp/components/pages_border/open-pre-title.jsp"></jsp:include>
			
				Libros de Actas Asambleas y del Consejo
			
			<jsp:include page="/jsp/components/pages_border/open-post-title.jsp"></jsp:include>



<form action="edosFin.jsp" method="post">

<table width="100%">
	<tr>
		<td colspan="3">

		</td>
	</tr>	
	<tr>
		<td colspan="3">
			<table width="100%" border='0' cellpadding="0" cellspacing="0">
				<tr>
					<td width="5%"></td>
					<td width="15%" class="tableRow2">Empresa(s):</td>
					<td width="30%" class="tableRow2" valign="top">

					<div align='right'>
						<img src='<%= request.getContextPath() %>/img/btn_search.png' 
										onclick="openMultiSelectPupUp('EMPRESAS','hiddenEmpresa','divEmpresa','innerHTML',document.getElementById('hiddenEmpresa').value,'UL')">

						<img src='<%= request.getContextPath() %>/img/btn_clean.png' 
										onclick="document.getElementById('hiddenEmpresa').value = '';document.getElementById('divEmpresa').innerHTML = '';">
						</div>

						<input type="hidden" id='hiddenEmpresa' name='hiddenEmpresa' value=''>
 						<div id="divEmpresa"></div>
 						<input type='hidden' id='txtEmpresa' name='txtEmpresa' value=''>

					</td>
					
					<td width="5%"></td>
					<td width="15%"></td>
					<td width="30%" valign="top"></td>
					
					

				<tr>
					<td colspan="6" align="right">
						<input type="submit" id='btnEjecutar' name='btnEjecutar' value='Generar' onclick="waitBar();copyParamsDivToHidden();">
						<img src='<%= request.getContextPath() %>/img/wait-bar.gif' id='imgCapWait' style="display: none;">
					</td>
				</tr>

			</table>
		</td>
	</tr>
	<tr>
		<td colspan="3"><hr></td>
	</tr>
	
</table>

</body>
</html>
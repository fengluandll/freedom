<%@page import="mx.com.televisa.derechocorporativo.model.Catalog"%>
<%@page import="mx.com.televisa.derechocorporativo.util.TextFormatter"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@page import="mx.com.televisa.derechocorporativo.components.JSCal"%>
<%@include file="/jsp/components/calendar/include_calendar.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
<%@include file="/css/kaz-styles.jsp"%>
<script src="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.css">
<%@include file="/jsp/components/session_timeout/session_timeout.jsp"%>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/ejercicio/ejercicio.js"></script>
<%
	String id = request.getParameter("id");

	Catalog.reloadCatPersonas();
%>

</head>
<body>
	<script type="text/javascript">
	
	function waitBar() {

		//document.getElementById('imgCapWait').style.display = '';
		//document.getElementById('btnEjecutar').style.display = 'none';
	}
	
	function chekAccionista(){
		var empresas = document.getElementById('hiddenEmpresa').value;
		
		if(empresas.length > 0){
			swal("Para la opción SI solo debes capturar Accionistas");
			document.getElementById('chechkNo').checked = true;
			document.getElementById('chechkSi').checked = false;
		}
	}
	
        
        

        function openMultiSelectPupUp(catalogId, targetIds, targetNames, namesProperty, currentValue, namesFormat) {

            var left = screen.width - ((screen.width - 300) / 2);
            var top = (screen.height - 300) / 2;  // for 25% - devide by 4  |  for 33% - devide by 3

        	newwindow=window.open('../../components/multiselectPupUp/multiselect.jsp?catalogId=' + catalogId + '&targetIds=' + targetIds + '&targetNames=' + targetNames + '&currentValue=' + currentValue + '&namesProperty=' + namesProperty + '&namesFormat=' + namesFormat,
        						'name',
        						'height=600,width=450,toolbar=0,menubar=0,location=0,status=0,scrollbars=1,resizable=1,left=' + left + ',top=' + top);

        	if (window.focus) {newwindow.focus()}

        	return false;
        }
        
       

        function validaForm(){
        	var bandera = false;
        	var empresas = document.getElementById('hiddenEmpresa').value;
        	var accionistas = document.getElementById('hiddenAccionista').value;
        	
        	
        	//alert('nombre: '+nombre);

        	if(accionistas == '' && empresas == ''){
        			swal("Es requerido capturar el campo Accionista(s) o Empresa(s)")
        	}else {
        			if(document.getElementById('chechkSi').checked == true && empresas.length > 0){
        				swal("Para la opción SI solo debes capturar Accionistas");
        				document.getElementById('chechkNo').checked = true;
        				document.getElementById('chechkSi').checked = false;
        			}else{
        				
        				document.getElementById('imgCapWait').style.display = '';
	        			document.getElementById("frmHiFun").submit();
	        			document.getElementById('btnEjecutar').style.display = 'none';
        			}
        		}
        }


    </script>


	<jsp:include page="/jsp/components/pages_border/open-pre-title.jsp"></jsp:include>
		Reporte: Estructura de Capital Social
	<jsp:include page="/jsp/components/pages_border/open-post-title.jsp"></jsp:include>
	<jsp:include page="/jsp/components/backPageReports/regresaMenuReportes.jsp">
	
		<jsp:param name="action" value="rPredefinidos"/>
	</jsp:include>
  <form id="frmHiFun" name="frmHiFun" method="post" action="/DerechoCorporativo/jsp/reportes/estCapSoc/printReportEstCS.jsp">
	<table width="50%" align="center" style="text-align:center;" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td>
			<fieldset>
				<legend>Filtros:</legend>
				<div align="center">
					<table width="100%">	
					<tr>
					<td colspan="2">
						<b>Empresa(s):</b>
						<div id="divEmpresa"><%= (request.getParameter("txtEmpresa")!=null)?  TextFormatter.removeAccents( (new String(request.getParameter("txtEmpresa").getBytes("ISO-8859-1"),"UTF-8")) ) : ""%></div>
					</td>
			<td class="">	
					<%String hiddenEmpresa = 	(request.getParameter("hiddenEmpresa") != null) ? request.getParameter("hiddenEmpresa") : ""; 
					  %>
							<div align='right'>
						<img src='<%= request.getContextPath() %>/img/btn_search.png' 
										onclick="openMultiSelectPupUp('EMPRESAS','hiddenEmpresa','divEmpresa','innerHTML',document.getElementById('hiddenEmpresa').value,'UL')">
												
						<img src='<%= request.getContextPath() %>/img/btn_clean.png' 
										onclick="document.getElementById('hiddenEmpresa').value = '';document.getElementById('divEmpresa').innerHTML = '';">
						</div>
						
						
						<input type="hidden" id='hiddenEmpresa' name='hiddenEmpresa' value='<%=hiddenEmpresa%>' onfocus="chekAccionista()">
						
						
 						 <input type='hidden' id='txtEmpresa' name='txtEmpresa' 
							value="<%= (request.getParameter("txtEmpresa")!=null)? new String(request.getParameter("txtEmpresa").getBytes("ISO-8859-1"),"UTF-8") : ""%>"/>
							
							
													
 													
			</td>
					</tr>
						
					   <tr>
					   		<td colspan="2">
								<b>Accionista(s):</b>
								<div id="divAccionista"><%= (request.getParameter("txtAccionista")!=null)?  TextFormatter.removeAccents( (new String(request.getParameter("txtAccionista").getBytes("ISO-8859-1"),"UTF-8")) ) : ""%></div>
							</td>
							<td>
								<%String hiddenAccionista = 	(request.getParameter("hiddenAccionista") != null) ? request.getParameter("hiddenAccionista") : "";
								String hiddenRadioVal        = 	(request.getParameter("onlyAccionista") != null) ? request.getParameter("onlyAccionista") : "";
					             %>
							<div align='right'>
								<img src='<%= request.getContextPath() %>/img/btn_search.png' 
										onclick="openMultiSelectPupUp('ACCIONISTAS','hiddenAccionista','divAccionista','innerHTML',document.getElementById('hiddenAccionista').value,'UL')">
												
						<img src='<%= request.getContextPath() %>/img/btn_clean.png' 
										onclick="document.getElementById('hiddenAccionista').value = '';document.getElementById('divAccionista').innerHTML = '';">
						</div>
						
						
						<input type="hidden" id='hiddenAccionista' name='hiddenAccionista' value='<%=hiddenAccionista%>'>
						
						
						
 						<input type='hidden' id='txtAccionista' name='txtAccionista' 
							value="<%= (request.getParameter("txtAccionista")!=null)? new String(request.getParameter("txtAccionista").getBytes("ISO-8859-1"),"UTF-8") : ""%>">
							</td>
						</tr>
				<tr>
				<td colspan="3">
				<table id="tblAcc" width="100%">
							<tr>
								<td>
									&nbsp;
								</td>
							</tr>
				<tr>
					<td>
						<strong>Mostrar en la tabla sólo el accionista seleccionado</strong>
					</td>
				</tr>
				<tr>
					<td>
						SI <input type="radio" id="chechkSi" name="onlyAccionista" value="SI" onclick="chekAccionista()">
					</td>
					<td>
						NO <input type="radio" id="chechkNo" name="onlyAccionista" value="NO" checked="checked" onclick="chekAccionista()">			
					</td>
				</tr>
				<tr>
					<td align="center" colspan="2">
						<input id="btnEjecutar" name="btnEjecutar" type="button" value="Generar" onclick="waitBar();validaForm();">
						<img src='<%=request.getContextPath()%>/img/wait-bar.gif'
						id='imgCapWait' style="display: none;" />
					</td>
				</tr>
			</table>
			</td>
			</tr>
						
			</table>
		</div>
	</fieldset>
	</td>
	</tr>

	<tr>

	<td>
	<!--
		<fieldset>
			<legend></legend>
				<div align="center">
					<table width="40%">
						<tr>
							 <td>
								<select name='psHiFuOut' style='width: 180px;'>
										 <option value="PDF">PDF</option>
										 <option value="EXCEL">Excel</option>
								</select>
							</td> 
							<td>
								<input id="btnEjecutar" name="btnEjecutar" type="button" value="Generar" onclick="waitBar();validaForm();">
								<img src='<%=request.getContextPath()%>/img/wait-bar.gif'
								id='imgCapWait' style="display: none;" />
							</td>
						</tr>
					</table>
				</div>
		</fieldset>		
		-->
	</td>
	</tr>
	<tr>
		<td colspan="3" align="right"><br /></td>
	</tr>
	<tr>
		<td colspan="3" align="left"></td>
	</tr>
</table>		
 </form>
 

<jsp:include page="/jsp/components/pages_border/close.jsp"></jsp:include>	
</body>
</html>
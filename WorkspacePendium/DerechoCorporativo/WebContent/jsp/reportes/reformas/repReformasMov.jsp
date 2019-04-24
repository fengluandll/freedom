<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="mx.com.televisa.derechocorporativo.components.JSCal"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/jsp/components/calendar/include_calendar.jsp"%>
<%@page import="mx.com.televisa.derechocorporativo.util.*"%>  

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>repReformasMov</title>
<%@include file="/css/kaz-styles.jsp"%>
<script src="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.css">
<%@include file="/jsp/components/session_timeout/session_timeout.jsp"%>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/ejercicio/ejercicio.js"></script>
<script type="text/javascript">
		function validarFechas(){
            var vFecDesde = document.getElementById('txtFecSolDesde').value;
            var aFecDesde = vFecDesde.split('/');

            var vDiaDesde = aFecDesde[0];
            var vMesDesde = (aFecDesde[1]-1);
            var vAnnoDesde = aFecDesde[2];

            var dDesde = new Date(vAnnoDesde, vMesDesde, vDiaDesde);

            var vFecHasta = document.getElementById('txtFecSolHasta').value;
            var aFecHasta = vFecHasta.split('/');

            var vDiaHasta = aFecHasta[0];
            var vMesHasta = (aFecHasta[1]-1);
            var vAnnoHasta = aFecHasta[2];

            var dHasta = new Date(vAnnoHasta, vMesHasta, vDiaHasta);

            if(dDesde > dHasta)
            	swal('La fecha Desde no puede ser mayor que la fecha Hasta')
            else
            	document.getElementById("frmRepReformas").submit();

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
	</script>

</head>
<body>
			<jsp:include page="/jsp/components/pages_border/open-pre-title.jsp"></jsp:include>
			Reporte de Reformas y Movimientos Corporativos
			<jsp:include page="/jsp/components/pages_border/open-post-title.jsp"></jsp:include>
			<jsp:include page="/jsp/components/backPageReports/regresaMenuReportes.jsp">
				<jsp:param name="action" value="rPredefinidos"/>
			</jsp:include>
<% String hiddenEmpresa = 	(request.getParameter("hiddenEmpresa") != null) ? request.getParameter("hiddenEmpresa") : "";
String paramString = 	"hiddenEmpresa=" + hiddenEmpresa;
%>
			
<form id="frmRepReformas" action="/DerechoCorporativo/RepRefMovServlet">			
<table width="70%" border='0' cellpadding="0" cellspacing="0" align="center">
			
				<tr>
					
					<td width="10%" class="tableRow2">Empresa(s):
					<div id="divEmpresa"><%= (request.getParameter("txtEmpresa")!=null)?  TextFormatter.removeAccents( (new String(request.getParameter("txtEmpresa").getBytes("ISO-8859-1"),"UTF-8")) ) : ""%></div>
					</td>
					<td class="tableRow2">	
							<div align='right'>
						<img src='<%= request.getContextPath() %>/img/btn_search.png' 
										onclick="openMultiSelectPupUp('EMPRESAS','hiddenEmpresa','divEmpresa','innerHTML',document.getElementById('hiddenEmpresa').value,'UL')">
												
						<img src='<%= request.getContextPath() %>/img/btn_clean.png' 
										onclick="document.getElementById('hiddenEmpresa').value = '';document.getElementById('divEmpresa').innerHTML = '';">
						</div>
						
						
						<input type="hidden" id='hiddenEmpresa' name='hiddenEmpresa' value='<%=hiddenEmpresa%>'>
						
 						<input type='hidden' id='txtEmpresa' name='txtEmpresa' 
							value="<%= (request.getParameter("txtEmpresa")!=null)? new String(request.getParameter("txtEmpresa").getBytes("ISO-8859-1"),"UTF-8") : ""%>">
 															
					</td>
				</tr>
				
				<tr>
					<td colspan="2">
						<fieldset>
						<legend>Rango de Fechas:</legend>
							<div align="center" >
				        		<table>
								  <tr>
										    <td class="">Fecha Solicitud Desde:</td>
										    <td>
										      		<label for="txtFecSolDesde"></label>
										      		<input type="text" name="txtFecSolDesde" id="txtFecSolDesde" onkeyup="javascript:getMascaraFecha(this);"/>
										    </td>
										    <td>
									    	<%= JSCal.getCalendar("txtFecSolDesde", "") %>
									    </td>
								  </tr>
								  <tr>
								    <td class="">Fecha Solicitud Hasta:</td>
								    <td>
								      <label for="txtFecSolHasta"></label>
								      <input type="text" name="txtFecSolHasta" id="txtFecSolHasta" onkeyup="javascript:getMascaraFecha(this);"/>
								    </td>
								    <td>
								    	<%= JSCal.getCalendar("txtFecSolHasta", "") %>
								    </td>
								  </tr>
								</table>
							</div>
					</fieldset>
					</td>
				</tr>
				<tr>
                <td colspan="2">
					<fieldset>
						<legend>Exportar a:</legend>
							<div align="center">
								<table>
									<tr>
										<td>
											<select name='sPdfExcel' style='width: 180px;'>
													 <option value="PDF">PDF</option>
													 <option value="EXCEL">Excel</option>
											</select>
										</td>
										<td>
										    <input type="button" value="Generar" onClick="validarFechas();">
											<%-- <input type="submit" value="Generar"> --%>
										</td>
									</tr>
								</table>
							</div>
			        </fieldset>
                </td>
            </tr>
				
</table>	
<input type="hidden" id='hiddenEmpresa' name='hiddenEmpresa' value='<%=hiddenEmpresa%>'>
<input type='hidden' id='txtEmpresa' name='txtEmpresa' 
							value="<%= (request.getParameter("txtEmpresa")!=null)? new String(request.getParameter("txtEmpresa").getBytes("ISO-8859-1"),"UTF-8") : ""%>">
</form>										
</body>
</html>
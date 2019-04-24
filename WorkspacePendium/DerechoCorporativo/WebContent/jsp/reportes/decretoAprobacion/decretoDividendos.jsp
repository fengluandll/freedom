<%@page import="mx.com.televisa.derechocorporativo.model.Catalog"%>
<%@page import="mx.com.televisa.derechocorporativo.util.TextFormatter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="mx.com.televisa.derechocorporativo.components.JSCal"%>
<%@include file="/jsp/components/calendar/include_calendar.jsp"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/css/kaz-styles.jsp"%>
<script src="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.min.js"></script> 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.css">
<%@include file="/jsp/components/session_timeout/session_timeout.jsp"%>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/ejercicio/ejercicio.js"></script>
</head>
<body>
<jsp:include page="/jsp/components/pages_border/open-pre-title.jsp"></jsp:include>
			
				Reporte Decreto Dividendos y Aprobaci&oacute;n del Ejercicio Social
			
			<jsp:include page="/jsp/components/pages_border/open-post-title.jsp"></jsp:include>
			<jsp:include page="/jsp/components/backPageReports/regresaMenuReportes.jsp">
				<jsp:param name="action" value="rPredefinidos"/>
			</jsp:include>

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
        
        function validaForm(){
        	//var empresas = document.getElementById('hiddenEmpresa').value;
        	
        	//if(empresas == ''){
        		//swal("Es requerido capturar al menos una Empresa")
        	//}else {
        		validarFechas();
        	//}
        }
       
        function validarFechas(){
            var vFecDesde = document.getElementById('txDe').value;
            var vFecHasta = document.getElementById('txPara').value;
            
            
            if(vFecDesde != '' && vFecHasta == ''){
            	swal('Es requerido capturar ambas fechas');
            }else if(vFecDesde == '' && vFecHasta != ''){
            	swal('Es requerido capturar ambas fechas');
            }else{
                        
	            var aFecDesde = vFecDesde.split('/');
	
	            var vDiaDesde = aFecDesde[0];
	            var vMesDesde = (aFecDesde[1]-1);
	            var vAnnoDesde = aFecDesde[2];
	
	            var dDesde = new Date(vAnnoDesde, vMesDesde, vDiaDesde);
	
	            
	            var aFecHasta = vFecHasta.split('/');
	
	            var vDiaHasta = aFecHasta[0];
	            var vMesHasta = (aFecHasta[1]-1);
	            var vAnnoHasta = aFecHasta[2];
	
	            var dHasta = new Date(vAnnoHasta, vMesHasta, vDiaHasta);
	
	            if(dDesde > dHasta){
	            	swal('La fecha Desde no puede ser mayor que la fecha Hasta');
	            }else{
	            	document.getElementById("frmddAes").submit();
	        	}
        }
	}
</script>        

<form id="frmddAes" name="frmddAes" method="post" action="/DerechoCorporativo/ReporteDividentosServlet">
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
						
						
						<input type="hidden" id='hiddenEmpresa' name='hiddenEmpresa' value='<%=hiddenEmpresa%>'>
								
					</td>
					</tr>
					
					<tr>
						    <td class="formulario">Desde:</td>
						    <td>
						      <label for="txDe"></label>
						      <input type="text" name="txDe" id="txDe" onkeyup="javascript:getMascaraFecha(this);"/>
						    </td>
						    <td>
						    	<%= JSCal.getCalendar("txDe", "") %>
						    </td>
						  </tr>

						  <tr>
						    <td class="formulario">Hasta:</td>
						    <td>
						      <label for="txPara"></label>
						      <input type="text" name="txPara" id="txPara" onkeyup="javascript:getMascaraFecha(this);"/>
						    </td>
						    <td>
						    	<%= JSCal.getCalendar("txPara", "") %>
						    </td>
						  </tr>
			</table>
		</div>
	</fieldset>
	</td>
	</tr>

	<tr>

	<td>
		<fieldset>
			<legend>Exportar a:</legend>
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
								<input type="button" value="Generar" onclick="validaForm();">
							</td>
						</tr>
					</table>
				</div>
		</fieldset>		
	</td>
	</tr>
</table>		
 </form>			
</body>
</html>
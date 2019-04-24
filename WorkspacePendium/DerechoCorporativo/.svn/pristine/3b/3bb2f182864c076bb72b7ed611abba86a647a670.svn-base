<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="mx.com.televisa.derechocorporativo.components.JSCal"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@include file="/jsp/components/calendar/include_calendar.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bitacora Modificación de Manuales</title>
<%@include file="/css/kaz-styles.jsp"%>
<script src="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.css">
<%@include file="/jsp/components/session_timeout/session_timeout.jsp"%>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/ejercicio/ejercicio.js"></script>
</head>
<body>
	<script type="text/javascript">
		function validarFechas(){
            var vFecDesde = document.getElementById('txDesde').value;
            var aFecDesde = vFecDesde.split('/');

            var vDiaDesde = aFecDesde[0];
            var vMesDesde = (aFecDesde[1]-1);
            var vAnnoDesde = aFecDesde[2];

            var dDesde = new Date(vAnnoDesde, vMesDesde, vDiaDesde);

            var vFecHasta = document.getElementById('txHasta').value;
            var aFecHasta = vFecHasta.split('/');

            var vDiaHasta = aFecHasta[0];
            var vMesHasta = (aFecHasta[1]-1);
            var vAnnoHasta = aFecHasta[2];

            var dHasta = new Date(vAnnoHasta, vMesHasta, vDiaHasta);

            if(dDesde > dHasta)
            	swal('La fecha Desde no puede ser mayor que la fecha Hasta')
            else
            	document.getElementById("fmBitaModifMan").submit();

		}
	</script>
			<jsp:include page="/jsp/components/pages_border/open-pre-title.jsp"></jsp:include>

				Bitacora Modificación de Manuales

			<jsp:include page="/jsp/components/pages_border/open-post-title.jsp"></jsp:include>
			<jsp:include page="/jsp/components/backPageReports/regresaMenuReportes.jsp">
				<jsp:param name="action" value="rPredefinidos"/>
			</jsp:include>
			
    <form id="fmBitaModifMan" name="fmBitaModifMan" method="post" action="/DerechoCorporativo/BitaModifManServlet">
        <table align="center">
        	<tr>
        		<td>
     				<fieldset>
						<legend>Rango de Fechas:</legend>
							<div align="center">
				        		<table>
								  <tr>
										    <td class="formulario">Desde:</td>
										    <td>
										      		<label for="txDesde"></label>
										      		<input type="text" name="txDesde" id="txDesde" onkeyup="javascript:getMascaraFecha(this);"/>
										    </td>
										    <td>
									    	<%= JSCal.getCalendar("txDesde", "") %>
									    </td>
								  </tr>
								  <tr>
								    <td class="formulario">Hasta:</td>
								    <td>
								      <label for="txHasta"></label>
								      <input type="text" name="txHasta" id="txHasta" onkeyup="javascript:getMascaraFecha(this);"/>
								    </td>
								    <td>
								    	<%= JSCal.getCalendar("txHasta", "") %>
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
    </form>
</body>
</html>
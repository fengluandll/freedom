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
        function changeChkHisFun() {
        	var vIsCheck = document.getElementById('chHiFuTodos').checked;
        	if(vIsCheck){
        		document.getElementById('chAdmHiFun').checked = true;
        		document.getElementById('chVigHiFun').checked = true;
        		document.getElementById('chAccHiFun').checked = true;
        		document.getElementById('chApoHiFun').checked = true;
        		document.getElementById('chConAdmHiFun').checked = true;
        	}else{
        		document.getElementById('chAdmHiFun').checked = false;
        		document.getElementById('chVigHiFun').checked = false;
        		document.getElementById('chAccHiFun').checked = false;
        		document.getElementById('chApoHiFun').checked = false;
        		document.getElementById('chConAdmHiFun').checked = false;        		
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
        
        function openMultiSelectPupUpRep(catalogId, targetIds, targetNames, currentValue, updateFieldCode, namesProperty, namesFormat) {
			/*catalogId: NumCatalogo 
			targetIds: idHidden 
			targetNames: idText
			currentValue: valores guardados para checkear
			updateFieldCode: cadena vacia, para reports
			namesProperty: value o InnerHTML
			 */

            var left = screen.width - ((screen.width - 300) / 2);
            var top = (screen.height - 300) / 2;  // for 25% - devide by 4  |  for 33% - devide by 3

        	newwindow=window.open('../../components/multiselectPupUp/multiselect.jsp?catalogId=' + catalogId + '&targetIds=' + targetIds + '&targetNames=' + targetNames + '&currentValue=' + currentValue + '&namesProperty=' + namesProperty +  '&updateFieldCode=' + updateFieldCode + '&namesFormat=' + namesFormat,
        						'name',
        						'height=300,width=400,toolbar=0,menubar=0,location=0,status=0,scrollbars=1,resizable=1,left=' + left + ',top=' + top);

        	if (window.focus) {newwindow.focus()}

        	return false;

        }

        function validaForm(){
        	var bandera = false;
        	var nombre = document.getElementById('idHiFunHidd').value;
        	var empresas = document.getElementById('hiddenEmpresa').value;
        	
        	//var checks = document.getElementById('chHiFun').value;
        	//var checks = '';
        	var elems2 = document.querySelectorAll('input[type="checkbox"][class="check_HiFu"]');

        	for(var j = 0, len = elems2.length; j < len; j++){
    			if(elems2[j].checked){
    				bandera = true;
    				//alert('true_');
    			}
    		}

        	
        	//alert('nombre: '+nombre);

        	if(nombre == '' && empresas == ''){
        			swal("Es requerido capturar en el campo Nombre o Empresa(s)")
        		}else if(!bandera){
        			swal("Es requerido seleccionar al menos checkbox")
        		}else {
        			document.getElementById("frmHiFun").submit();
        		}
        }


    </script>

	<jsp:include page="/jsp/components/pages_border/open-pre-title.jsp"></jsp:include>
		Reporte: Historial de Funcionarios
	<jsp:include page="/jsp/components/pages_border/open-post-title.jsp"></jsp:include>
	<jsp:include page="/jsp/components/backPageReports/regresaMenuReportes.jsp">
	
		<jsp:param name="action" value="rPredefinidos"/>
	</jsp:include>
  <form id="frmHiFun" name="frmHiFun" method="post" action="/DerechoCorporativo/HistoricoFuncionariosServlet">
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
						
						<%--
						<input id='txtEmpresa' 
							name='txtEmpresa' 
							readonly="readonly" size="40" 
							
							value="<%= (request.getParameter("txtEmpresa")!=null)? new String(request.getParameter("txtEmpresa").getBytes("ISO-8859-1"),"UTF-8") : ""%>">
 						--%>
 						
 						<%--<div id="divEmpresa"><%= (request.getParameter("txtEmpresa")!=null)? new String(request.getParameter("txtEmpresa").getBytes("ISO-8859-1"),"UTF-8") : ""%></div> --%>
 						
 						<input type='hidden' id='txtEmpresa' name='txtEmpresa' 
							value="<%= (request.getParameter("txtEmpresa")!=null)? new String(request.getParameter("txtEmpresa").getBytes("ISO-8859-1"),"UTF-8") : ""%>">
 						

					

						<!--
						<img src='<%= request.getContextPath() %>/img/btn_search.png' 
										onclick="openMultiSelectPupUp('EMPRESAS','hiddenEmpresa','txtEmpresa','value',document.getElementById('hiddenEmpresa').value)">
												
						<img src='<%= request.getContextPath() %>/img/btn_clean.png' 
										onclick="document.getElementById('hiddenEmpresa').value = '';document.getElementById('txtEmpresa').value = '';">
						-->										
					</td>
					</tr>
						<tr>	
							<td colspan="2">
								<b>Nombre:</b>
								<div id="idHiFunMultiList"></div>	
							</td>
							<td>
								<div align='right'>
									<img src='<%= request.getContextPath() %>/img/btn_search.png' onclick="openMultiSelectPupUpRep(
											'PERSONAS'
											,'idHiFunHidd'
											,'idHiFunMultiList'
											,document.getElementById('idHiFunHidd').value
											,''
											,'innerHTML','UL')"/>		
	                                <img src='<%= request.getContextPath() %>/img/btn_clean.png' onclick="document.getElementById('idHiFunHidd').value = '';document.getElementById('idHiFunMultiList').innerHTML = '';">
		
								</div>
		
								<input type='hidden' name='idHiFunHidd'  id='idHiFunHidd' value=''>
									
								
							</td>
						</tr>
						
						<tr>
						<td colspan="4">
							<!--  <fieldset>  -->
							
							<table>
								<tr>
									<td>
										Administración
									</td>
									<td>
										<input type='checkbox' 
										       value='8, 11, 12, 13, 14, 15, 16, 25, 26, 39, 40, 43, 44, 45, 46'
										       name='chHiFun' 
										       id='chAdmHiFun'
										       class = 'check_HiFu'
										       >
									</td>
								</tr>
								<tr>
									<td>
										Vigilancia
									</td>
									<td>
										<input type='checkbox' value='9' name='chHiFun' id='chVigHiFun' class = 'check_HiFu'>
									</td>
							   </tr>
							   <tr>
									<td>
										Estatus
									</td>
									<td>
										<select id="estatus" name="estatus">
										  <option value="Todo">Todo</option>
										  <option value="activo">Vigente</option>
										  <option value="inactivo">No Vigente</option>
										</select>
									</td>
							   </tr>
							</table>
							<hr size="1" width="100%"/>
						<!-- 	</fieldset>  -->
						</td>
						</tr>
						
						
					   <tr>
					   		<td>
								Accionistas
							</td>
							<td>
								<input type='checkbox' value='7' name='chHiFun' id='chAccHiFun' class = 'check_HiFu'>
							</td>
						</tr>
						<tr>
							<!-- JJAQ 27/12/2018 YA NO SE REQUIERE APODERADOS
							<td>
								Apoderados
							</td>
							<td>
								<input type='checkbox' value='99' name='chHiFun' id='chApoHiFun' class = 'check_HiFu'>
							</td>
							-->
						<tr>
						</tr>
							<td>
								Contactos
							</td>
							<td>
								<input type='checkbox' value='1' name='chHiFun' id='chConAdmHiFun' class = 'check_HiFu'>
							</td>
						</tr>
						<tr>
							<td>
								Todos
							</td>
							<td>
								<input type='checkbox' 
								       value='8, 11, 12, 13, 14, 15, 16, 25, 26, 9, 7, 99, 1, 39, 40, 43, 44, 45, 46' 
								       name='chHiFuTodos' 
								       id='chHiFuTodos' 
								       onChange='changeChkHisFun()' 
								       class = 'check_HiFu'>
							</td>
						</tr>

						<!-- ECM 29 Marzo 2016 Agregar rango de fechas -->
						  <tr>
						    <td class="formulario">Desde:</td>
						    <td>
						      <label for="txFecDeHFun"></label>
						      <input type="text" name="txFecDeHFun" id="txFecDeHFun" onkeyup="javascript:getMascaraFecha(this);"/>
						    </td>
						    <td>
						    	<%= JSCal.getCalendar("txFecDeHFun", "") %>
						    </td>
						  </tr>

						  <tr>
						    <td class="formulario">Hasta:</td>
						    <td>
						      <label for="txFecParaHFun"></label>
						      <input type="text" name="txFecParaHFun" id="txFecParaHFun" onkeyup="javascript:getMascaraFecha(this);"/>
						    </td>
						    <td>
						    	<%= JSCal.getCalendar("txFecParaHFun", "") %>
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
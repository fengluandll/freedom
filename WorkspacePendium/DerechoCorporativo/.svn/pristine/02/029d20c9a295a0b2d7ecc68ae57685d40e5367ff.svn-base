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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
        function cambiarCheckRepHisFun() {
        	var vIsCheck = document.getElementById('chTodos').checked;
        	if(vIsCheck){
        		document.getElementById('chAdm').checked = true;
        		document.getElementById('chVig').checked = true;
        		document.getElementById('chAcc').checked = true;
        		document.getElementById('chApo').checked = true;
        		document.getElementById('chConAdm').checked = true;
        	}else{
        		document.getElementById('chAdm').checked = false;
        		document.getElementById('chVig').checked = false;
        		document.getElementById('chAcc').checked = false;
        		document.getElementById('chApo').checked = false;
        		document.getElementById('chConAdm').checked = false;        		
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
        
        function validaFormHiCar(){
        	var bandera = false;
        	var nombre = document.getElementById('idRepHiFun').value;
        	var nomRep = document.getElementById('txtNomReporte').value;
        	var elems2 = document.querySelectorAll('input[type="checkbox"][class="check_HiCar"]');
        	var empresas = document.getElementById('hiddenEmpresa').value;
        	var a=document.getElementById("txDe").value;
        	var b=document.getElementById("txPara").value;
        	//alert(document.getElementById('chReg5').checked);
        	if(document.getElementById('chReg5').checked && !a){
        		//alert("te falta seleccionar campos :v");
        		swal('Es requerido capturar el campo "Desde:"')
        	}else if((!document.getElementById('chReg5').checked && !b) && a){
        		swal('Es requerido capturar el campo "Hasta:" o seleccionar la regla de 5 años')
        	}else{
        		
			//var vNombres = document.getElementById('hiFun_multilist').value;
        	//alert('vNombres:  '+vNombres);

        	for(var j = 0, len = elems2.length; j < len; j++){
    			if(elems2[j].checked){
    				bandera = true;
    			}
    		}

        	if(nombre == '' && empresas == ''){
        			swal("Es requerido capturar en el campo Nombre o Empresa(s)")
        		}else if(!bandera){
        			swal("Es requerido seleccionar al menos un checkbox")
        		}else if(nomRep == '') {
        			swal("Es requerido capturar el nombre del reporte")
        			
        		}else{
        			//document.getElementById("frmHiCar").submit();
        			validarFechas();
        		}
        	}
        }
		
        function disabledFechaPara(cb){
        	if(cb.checked){
        		document.getElementById("estatus").selectedIndex = 0;
        		document.getElementById("txPara").value="";
        		document.getElementById("txPara").disabled=true;
        		document.getElementById("txPara_trigger").style="display: none";
        	}else{
        		var c=document.getElementById("txPara").disabled=false;
        		document.getElementById("txPara_trigger").style="display: block";
        	}
        }
        
		function validarFechas(){
            var vFecDesde = document.getElementById('txDe').value;
            var aFecDesde = vFecDesde.split('/');

            var vDiaDesde = aFecDesde[0];
            var vMesDesde = (aFecDesde[1]-1);
            var vAnnoDesde = aFecDesde[2];

            var dDesde = new Date(vAnnoDesde, vMesDesde, vDiaDesde);

            var vFecHasta = document.getElementById('txPara').value;
            var aFecHasta = vFecHasta.split('/');

            var vDiaHasta = aFecHasta[0];
            var vMesHasta = (aFecHasta[1]-1);
            var vAnnoHasta = aFecHasta[2];

            var dHasta = new Date(vAnnoHasta, vMesHasta, vDiaHasta);

            if(dDesde > dHasta)
            	swal('La fecha Desde no puede ser mayor que la fecha Hasta')
            else
            	document.getElementById("frmHiCar").submit();

		}


    </script>

	<jsp:include page="/jsp/components/pages_border/open-pre-title.jsp"></jsp:include>
		Reporte: Anexo Cuestionario 20F
	<jsp:include page="/jsp/components/pages_border/open-post-title.jsp"></jsp:include>

	<jsp:include page="/jsp/components/backPageReports/regresaMenuReportes.jsp">
		<jsp:param name="action" value="rPredefinidos"/>
	</jsp:include>
<!-- 
HistorialDeCargoPDF.jsp

-->
  <form id="frmHiCar" name="frmHiFun" method="post" action="/DerechoCorporativo/HistoricoCargosServlet">
	<table width="40%" style="text-align:center;" align="center" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td>
			<fieldset>
				<legend>Filtros:</legend>
				<div align="center">
					<table width="100%">
					<tr>
						<td colspan="">
							<b>Nombre del Reporte:</b>
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<input style="width: 100%" type="text" id="txtNomReporte" name="txtNomReporte" value="" />
						</td>
					</tr>
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
								<div id="hiFun_multilist"></div>
							</td>
							<td>
								<div align='right'>
									<img src='<%= request.getContextPath() %>/img/btn_search.png' onclick="openMultiSelectPupUpRep(
										'PERSONAS'
										,'idRepHiFun'
										,'hiFun_multilist'
										,document.getElementById('idRepHiFun').value
										,''
										,'innerHTML','UL')"/>		
	                            <%--<img src='<%= request.getContextPath() %>/img/btn_clean.png' onclick="document.getElementById('hiddenEmpresa').value = '';document.getElementById('divEmpresa').innerHTML = '';"> --%>
	                                <img src='<%= request.getContextPath() %>/img/btn_clean.png' onclick="document.getElementById('idRepHiFun').value = '';document.getElementById('hiFun_multilist').innerHTML = '';">
		
								</div>
		
								<input type='hidden' name='idRepHiFun'  id='idRepHiFun' value=''>

							</td>
						</tr>
					</table>
					<table width="100%" align="left">
						<tr>
							<td>
								Administración
							</td>
							<td>
								<input type='checkbox' 
								       value='8, 11, 12, 13, 14, 15, 16, 25, 26, 39, 40, 43,44,45,46' 
								       name='chNomFun' 
								       id='chAdm'
								       class = 'check_HiCar'
								       >
							</td>
						</tr>
						<tr>
							<td>
								Vigilancia
							</td>
							<td>
								<input type='checkbox' value='9' name='chNomFun' id='chVig' class = 'check_HiCar'>
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
					<table width="100%" align="left">
						
					   
					   <tr>
					   		<td>
								Accionistas
							</td>
							<td>
								<input type='checkbox' value='7' name='chNomFun' id='chAcc' class = 'check_HiCar'>
							</td>
						</tr>
						<tr>
							<td>
								Apoderados
							</td>
							<td>
								<input type='checkbox' value='99' name='chNomFun' id='chApo' class = 'check_HiCar'>
							</td>
						<tr>
						</tr>
							<td>
								Contactos
							</td>
							<td>
								<input type='checkbox' value='1' name='chNomFun' id='chConAdm' class = 'check_HiCar'>
							</td>
						</tr>
						<tr>
							<td>
								Todos
							</td>
							<td>
							    <!-- value='8, 11, 12, 13, 14, 15, 16, 25, 26, 39, 40, 43, 9, 7, 99, 1' -->
								<input type='checkbox' 
								       value=''
								       name='chNomFun' 
								       id='chTodos' 
								       onChange='cambiarCheckRepHisFun()'
								       class = 'check_HiCar'
								>
							</td>
						</tr>
						</table>


						<!-- Nuevos Filtros -->
						<table width="60%" align="left">
						<tr>
							<td>
								Idioma
							</td>
							<td>
								<select name='sIdioma' style='width: 180px;'>
										 <option value="ESPAÑOL">Español</option>
										 <option value="INGLES">Ingles</option>
										 <!-- <option value="AMBOS">Ambos</option> -->
								</select>
							</td>
						</tr>
						</table>
						<table width="50%" align="left">
						  <tr>
								<td>
									Regla 5 años:
								</td>
								<td>
									<input type='checkbox' value='Reg5' name='chReg5' id='chReg5' onclick="disabledFechaPara(this);">
								</td>
						  </tr>
						</table>
						<table width="60%" align="left">
						  <tr>
						    <td class="formulario">Desde:</td>
						    <td>
						      <label for="txDe"></label>
						      <input type="text" name="txDe" id="txDe"  onkeyup="javascript:getMascaraFecha(this);"/>
						    </td>
						    <td>
						    	<%= JSCal.getCalendar("txDe", "") %>
						    </td>
						  </tr>

						  <tr>
						    <td class="formulario">Hasta:</td>
						    <td>
						      <label for="txPara"></label>
						      <input type="text" name="txPara" id="txPara"  onkeyup="javascript:getMascaraFecha(this);"/>
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
	<!--<td>
		<fieldset>
		<legend>Exportar a:</legend>
			<div align="center">
				<table width="40%">
					<tr>
						<td>
							<a href='../../../HistoricoFuncionariosServlet?psSalida=1'>
								<img src='../../../img/icons/pdf_file.png' width="40" height="40">
							</a>
						</td>					
						<td>
							<a href='../../../HistoricoFuncionariosServlet?psSalida=2'>
								<img src='../../../img/icons/xls.png' width="40" height="40">
							</a>
						</td>
					</tr>
				</table>
			</div>
			</fieldset>
		-->
	<td>
		<fieldset>
			<legend>Exportar a:</legend>
				<div align="center">
					<table width="40%">
						<tr>
							<td>
								<select name='psSalida' style='width: 180px;'>
										 <option value="PDF">PDF</option>
										 <option value="EXCEL">Excel</option>
								</select>
							</td>
							<td>
								<input type="button" value="Generar" onClick="validaFormHiCar();">
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
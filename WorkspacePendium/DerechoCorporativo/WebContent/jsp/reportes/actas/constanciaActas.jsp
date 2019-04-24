<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="mx.com.televisa.derechocorporativo.components.JSCal"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="mx.com.televisa.derechocorporativo.daos.ConstanciaActasDAO"%>
<%@page import="java.util.Map"%>
<%@include file="/jsp/components/calendar/include_calendar.jsp"%>
<%@page import="mx.com.televisa.derechocorporativo.util.*"%>  

<html>
<head>
<title>Constancia de Actas</title>
<%@include file="/css/kaz-styles4PrintSmall.jsp"%>
<%@include file="/css/kaz-styles.jsp"%>
<script src="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.css">
<%@include file="/jsp/components/session_timeout/session_timeout.jsp"%>

<script type="text/javascript">

function getMascaraFecha(pObjeto){
	var vSeparador = '/';
	var vPatron =  new Array(2,2,4);
	var vNumerico = true;

	if(pObjeto.valant != pObjeto.value){
		val = pObjeto.value
		largo = val.length
		val = val.split(vSeparador)
		val2 = ''

		for(r=0;r<val.length;r++){
			val2 += val[r]	
		}

		if(vNumerico){
			for(z=0;z<val2.length;z++){
				if(isNaN(val2.charAt(z))){
					letra = new RegExp(val2.charAt(z),"g")
					val2 = val2.replace(letra,"")
				}
			}
		}

		val = ''
		val3 = new Array()

		for(s=0; s<vPatron.length; s++){
			val3[s] = val2.substring(0,vPatron[s])
			val2 = val2.substr(vPatron[s])
		}

		for(q=0;q<val3.length; q++){
			if(q ==0){
				val = val3[q]
			}
			else{
				if(val3[q] != ""){
					val += vSeparador + val3[q]
					}
			}
		}

		pObjeto.value = val
		pObjeto.valant = val

		}
} 

function validarFecha(fecha){
	//con la expresion regular "/\D/g" se busca cualquier caracter que no sea un número y los borra con el replace
	var fechaString=fecha.value.replace(/\D/g,"");
	valFecha=/^\d*$/.test(fechaString);
	if(valFecha==true && fechaString!=""){
		var fechaArr = fecha.value.split('/');
		var dia = fechaArr[0];
		var mes = fechaArr[1];
		var aho = fechaArr[2];
		var plantilla = new Date(aho, mes - 1, dia);//mes empieza de cero Enero = 0
		 
		var longAho=aho.length;
		if(!plantilla || plantilla.getFullYear() == aho && plantilla.getMonth() == mes -1 && plantilla.getDate() == dia && longAho>3){
		 	//alert("Correcto");
		 	//Fecha correcta continuar
		 	return true;
		}else{
			swal({ title: "Aviso",   
				   text: "La fecha es incorrecta",   
				   type: "warning",  
				   confirmButtonText: "Ok" });
		 	fecha.value="";
		 	return false;
		}
		
	}else{
		swal({ title: "Aviso",   
			   text: "La fecha es incorrecta",   
			   type: "warning",  
			   confirmButtonText: "Ok" });
	 	fecha.value="";
	 	return false;
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

		function openActa(){
			var vEjercicio = document.getElementById("txtEjercicio").value;
			var vIdEmpresa = document.getElementById("hiddenEmpresa").value;
			var vFecDoc    = document.getElementById("fecDoc").value;
			var vNameEmp   = document.getElementById("txtEmpresa").value;
			var vRemitente = document.getElementById("txtRemitente").value;
			var vDireccion = document.getElementById("txtDireccion").value;
			
			if(vIdEmpresa == ''){
				swal("Es requerido seleccionar una empresa");
				return;
			}
			else if(vRemitente == '' || vDireccion == ''){
				swal("Es requerido introducir remitente y direcci�n");
				return;
			}

			window.open('/DerechoCorporativo/faces/jsp/reportes/actas/constanciaActasPrint.jsp?'+
					'ejercicio='+vEjercicio+
					'&idEmp='+vIdEmpresa+
					'&fec='+vFecDoc+
					'&nameEmp='+vNameEmp+
					'&remitente='+(escape(vRemitente))+
					'&direccion='+(escape(vDireccion))
			);			
		}
	</script>

</head>
<body>
			<jsp:include page="/jsp/components/pages_border/open-pre-title.jsp"></jsp:include>
			Reporte de Constancia de Actas
			<jsp:include page="/jsp/components/pages_border/open-post-title.jsp"></jsp:include>
			<jsp:include page="/jsp/components/backPageReports/regresaMenuReportes.jsp">
				<jsp:param name="action" value="rPredefinidos"/>
			</jsp:include>
<% 
	String hiddenEmpresa = 	(request.getParameter("hiddenEmpresa") != null) ? request.getParameter("hiddenEmpresa") : "";
	String paramString = 	"hiddenEmpresa=" + hiddenEmpresa;
	ConstanciaActasDAO constanciasActasDAO = new ConstanciaActasDAO();
	Map<String,String> datosReporte = constanciasActasDAO.getDatosParaReporte();
%>

<form id="frmConstanciaActas" action="/DerechoCorporativo/RepRefMovServlet">			
<table width="40%" border='0' cellpadding="0" cellspacing="0" align="center">
				<tr>
					<td width="" class="tableRow2">Empresa(s):
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
					<td>
						<div>
			        		<table>
							  <tr>
								    <td class="">Fecha del documento:</td>
								    <td><label for="fecDoc"></label>
								      		<input type="text" name="fecDoc" id="fecDoc" size='8'
								      		onkeyup="javascript:getMascaraFecha(this);" onblur="validarFecha(this)"/>
								      	<img src="<c:out value="${applicationBean.contextPath}"/>/img/calendar.gif"
										id="txtFec_trigger" name="txtFec_trigger" title="Mostrar Calendario"
										 />						      		
								    </td>
								    <td>
							    		<%-- <%= JSCal.getCalendar("fecDoc", "") %> --%>
							    		<script>				
										    Calendar.setup({
										    inputField : "fecDoc",
										    trigger    : "txtFec_trigger", 
										    dateFormat : "%d/%m/%Y",
										    onSelect   : function() { this.hide() }
										});
									    </script>
							    	</td>
							  </tr>
							</table>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<table>
							<tr>
								<td>
									Ejercicio:
								</td>
								<td>
									<input type='text' size='2' maxlength='4' id='txtEjercicio' name='txtEjercicio'>
								</td>
							</tr>
						</table>
					</td>
	            </tr>
	            <tr>
					<td>
						<table>
							<tr>
								<td>
									Remitente:
								</td>
								<td>
									<input type='text' maxlength='40' id='txtRemitente' name='txtRemitente' value="<%=datosReporte.get("Remitente").toString().replaceAll("\"","&quote;")%>">
								</td>
							</tr>
						</table>
					</td>
	            </tr>
	            <tr>
					<td>
						<table>
							<tr>
								<td>
									Direcci�n:
								</td>
								<td>
									<textarea rows="5" cols="50" id='txtDireccion' name='txtDireccion'><%=datosReporte.get("Direcci�n").toString()%></textarea>
								</td>
							</tr>
						</table>
					</td>
	            </tr>
	            <tr>
                	<td colspan="">
							<div align="center">
								<table>
									<tr>
										<td>
											 <input type="button" onclick="javascript:openActa();" value="Generar"/>
										</td>
									</tr>
								</table>
							</div>
	                </td>
	            </tr>

</table>	
<input type="hidden" id='hiddenEmpresa' name='hiddenEmpresa' value='<%=hiddenEmpresa%>'>
<input type='hidden' id='txtEmpresa' name='txtEmpresa' 
							value="<%= (request.getParameter("txtEmpresa")!=null)? new String(request.getParameter("txtEmpresa").getBytes("ISO-8859-1"),"UTF-8") : ""%>">
</form>										
</body>
</html>
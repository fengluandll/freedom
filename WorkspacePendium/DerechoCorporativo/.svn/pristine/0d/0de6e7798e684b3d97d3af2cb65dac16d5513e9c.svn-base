<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="mx.com.televisa.derechocorporativo.daos.ApoderadosPoderesDAO"%>
<%@page import="mx.com.televisa.derechocorporativo.components.JSCal"%>
<%@page import="mx.com.televisa.derechocorporativo.util.FacesUtils"%>
<%@page import="mx.com.televisa.derechocorporativo.bean.SessionBean"%>
<%@page import="mx.com.televisa.derechocorporativo.bean.PoderesBean"%>
<%@page import="mx.com.televisa.derechocorporativo.daos.PoderesDAO"%>
<%@page import="mx.com.televisa.derechocorporativo.modules.captura.*"%>
<%@page import="mx.com.televisa.derechocorporativo.util.*"%>
<%@page import="mx.com.televisa.derechocorporativo.util.ObtenerEmpresaDenominacioActual"%>
<%@page import="mx.com.televisa.derechocorporativo.bean.ApoderadosPoderesBean"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/jsp/components/session_timeout/session_timeout.jsp"%>
<%@include file="/jsp/captura/part/validaciones.jsp"%>
<%@include file="/css/kaz-styles.jsp"%>


<%@include file="/jsp/components/calendar/include_calendar.jsp"%>
<script type="text/javascript" src="../part/ajaxPage.js"></script>
<script type="text/javascript" src="../part/pupUp.js"></script>
<script type="text/javascript" src="../poderes/poderes.js"></script>
<script type="text/javascript" src="<c:out value="${applicationBean.contextPath}"/>/js/jquery/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="<c:out value="${applicationBean.contextPath}"/>/js/ajax/simpleAjaxUtil.js"></script>

<script src="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.css">
<script type="text/javascript">

function savePoderes(){	
	document.getElementById("frmPoderes").submit();
}


$(document).ready(function(){
	$("#txtEscCp").attr('maxlength','6');
	var vValorSemReqProto2 = document.getElementById('cbReqPro').value;
	var vValorSemReqInsc2  = document.getElementById('cbReqIns').value;
	setsSemReqProto2(vValorSemReqProto2);
	setsSemReqInsc2(vValorSemReqInsc2);
	
	setsEscPod('esc');
	
});

function getInfoLic(src, notario, ubicacion){
	//alert('ECM:  '+src.value);
	var parameters = "IdCatVal="+src.value;
	var ajaxRequest = simpleAjaxUtil_getXmlHttpObject();
	if (ajaxRequest==null) {alert ("Browser does not support HTTP Request");return;}
	var url = '/DerechoCorporativo/faces/jsp/captura/ajax/getInfoLic.jsp';
	ajaxRequest.open("post", url, true);
	ajaxRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");

	ajaxRequest.onreadystatechange=function(){
        
	    if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
		    var response = ajaxRequest.responseText;
		    var values = response.split("|");
		    document.getElementById(notario).value = String(values[0]);
		    //document.getElementById(ubicacion).selectedIndex = String(values[1]);
		    document.getElementById(ubicacion).value = String(values[1]);
	    } 
    };
    ajaxRequest.send(parameters);
}

function getInfoNotario(src, licenciado, ubicacion){
	alert('ECM:  '+src.value);
var parameters = "NotPub="+src.value;
var ajaxRequest = simpleAjaxUtil_getXmlHttpObject();
if (ajaxRequest==null) {alert ("Browser does not support HTTP Request");return;}
var url = '/jsp/captura/ajax/getInfoNotario.jsp';
ajaxRequest.open("post", url, true);
ajaxRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");

ajaxRequest.onreadystatechange=function(){
    if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
	    var response = ajaxRequest.responseText;
	    var values = response.split("|");
	    //document.getElementById(licenciado).selectedIndex = String(values[0]);
	    //document.getElementById(ubicacion).selectedIndex = String(values[1]);
	    document.getElementById(licenciado).value = String(values[0]);
	    document.getElementById(ubicacion).value = String(values[1]);
    	}
	};
	
	ajaxRequest.send(parameters);
}

function showDelegadoPor(){

	//var vDelegadorPor = document.getElementsByName("VAL_C1")[0].selectedOptions[0].text;
	var vDelegadorPor = document.getElementById(getAlterOrGlobalFlexId()+"__VAL_C1").value;
	//alert('vDelegadorPor: '+vDelegadorPor);

	//if( (vDelegadorPor.toUpperCase() ).indexOf( 'apoderado'.toUpperCase() )  > -1 ){ //Apoderado
	if(vDelegadorPor == 12342){
		document.getElementById("DIVC_"+getAlterOrGlobalFlexId()+"_VAL_C2_1").style.display = 'block';
		document.getElementById("DIVC_"+getAlterOrGlobalFlexId()+"_VAL_C2_2").style.display = 'block';
	}else{
		document.getElementById("DIVC_"+getAlterOrGlobalFlexId()+"_VAL_C2_1").style.display = 'none';
		document.getElementById("DIVC_"+getAlterOrGlobalFlexId()+"_VAL_C2_2").style.display = 'none';
	}

}
	
function getDocumentum(id){
	var doc = null;
	doc = document.getElementById(id).value;
	
	if (doc == null||doc =='null'||doc == ''){
		swal({ title: "Aviso",   
			   text: "El campo No. De Documento en DOCUMENTUM no puede estar vacío",   
			   type: "warning",  
			   confirmButtonText: "Ok" });
	}else if (doc.length != 37){
		swal({ title: "Aviso",   
			   text: "El formato del Campo No. de Documento en DOCUMENTUM es incorrecto",   
			   type: "warning",  
			   confirmButtonText: "Ok" });	
	}else{
    window.open("/DerechoCorporativo/faces/jsp/documentum/waitingPage.jsp?doc="+doc
					,null
					,"height=600,width=1100,status=yes,toolbar=no,menubar=no,location=no");
	}				



function getMascaraHora(pObjeto){
	var vSeparador = ':';
	var vPatron =  new Array(1,1);
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

function maskEsc(valCampo){
			if(valCampo != null){
				var val2 = valCampo.value;
				$(valCampo).val(commaSeparateNumber(val2));
			}
}

function commaSeparateNumber(val){

    while (/(\d+)(\d{3})/.test(val.toString())){
      val = val.toString().replace(/(\d+)(\d{3})/, '$1'+','+'$2');
    }
    return val;
}
		
function quitarPunto(element){												
			var carac = element.value;
			if(carac.indexOf('.') > -1){
				carac = carac.replace(/\./g, '');
			}
			element.value = carac;
}


</script>
</head>
<%SessionBean sessionBean = (SessionBean)session.getAttribute("sessionBean");
String lstIdCurrentEmpresa = sessionBean.getIdCurrentEmpresa();
int idEMpresa = Integer.parseInt(lstIdCurrentEmpresa);
String idSeccion = request.getParameter("pidSeccion");
%>
<body>

	<table width="95%" height="100%" border="0" align="center">
			<tr>
				<td>
<table  border="0" cellpadding="0" cellspacing="0">
					<tr>
					<td
						background="<c:out value="${applicationBean.contextPath}"/>/img/borders/page_border/border04.png">
					</td>
					<td width="100%"
						background="<c:out value="${applicationBean.contextPath}"/>/img/borders/page_border/border05.png">
					<!-- open-post-title.jsp -->


<form id="frmPoderes" name="frmPoderes" action="/DerechoCorporativo/faces/PoderesGeneralesServlet">						
<% 
PoderesBean poderesBean = (PoderesBean)session.getAttribute("poderesBean");
FacesUtils.getSession().setAttribute("idPoder", poderesBean.getIdPoder());
%>
<%
String lstId = request.getParameter("id");
%>
<form id="frmEditaPoderes" name="frmEditaPoderes" method="post" action="/DerechoCorporativo/faces/UpdatePGServlet?action=editaGuarda">
<p>&nbsp;</p>
<p>&nbsp;</p>

	

					<table width="98%" border="0" cellspacing="2" cellpadding="3"
						id="sec_1_1">
						<tr>
							<td width="60%">
							
							
							
								<div id='debugMessage'></div>
							</td>
							<td width="20%"></td>
							<td width="20%">
								<input id= "btnCancelar"  type="button" name="cerrar" value="Salir" onClick="go('<%=request.getContextPath()%>/faces/jsp/captura/capWait.jsp?idEmp=<%=idEMpresa%>&salida=true&idSeccion=<%=idSeccion%>');" >
						</tr>
						<tr>
							<td colspan="3">
									<fieldset>
									<legend class="legendThin">Poder General</legend>
				<div id="flexTableInnerForm_17">
				<div>
				<table width="80%" border="0" cellspacing="0" cellpadding="0" align="center">
								<tr style="background-image: url('/DerechoCorporativo/img/Back2.png')">
										<th width="100%" align="center">
											<font style="color: white;">
												Poderes Generales - Editar Información
											</font>
										</th>
										<td align="right">
											<a href="<c:out value="${applicationBean.contextPath}"/>/ClosePGServlet?idSeccion=<%=idSeccion%>"><img src="<%=request.getContextPath()%>/img/close.png"></a> 
										</td>
									</tr>
								    <tr>
								      <td colspan="2">
				<table width='100%' border='0' cellspacing='0' cellpadding='0'>
				
					<tr>
					<td HEIGHT=0 VALIGN=''><div id='DIVC_17_VAL_C1_1'>Delegado por:</div></td>
						<td><div id='DIVC_17_VAL_C1_2'>
								<select name='delegP' id='delegP' style='width: 300px'
									    onchange="showDelegadoPor();"><option value="<%=poderesBean.getIndDelegadoPor()==0?"":poderesBean.getIndDelegadoPor()%>">---Seleccione---</option>
									    <%=ApoderadosPoderesDAO.getSelectList("0",43)%>									    
							</div></td>
					</tr>
				
					<tr>
						<td HEIGHT=0 VALIGN=''><div id='DIVC_17_VAL_C3_2'>Fecha:</div></td>
						<td><input type='text' id="txtFec" name="txtFec"
									 size='10'
									 value = "<%=poderesBean.getFecFecha()%>" title="<%=poderesBean.getFecFecha()%>" 
									 onkeyup="javascript:getMascaraFecha(this);">									 
									  <img src="<c:out value="${applicationBean.contextPath}"/>/img/calendar.gif"
										id="txtFec_trigger" name="txtFec_trigger" title="Mostrar Calendario"/>
									
									<script type="text/javascript">				
										Calendar.setup({
										    inputField : "txtFec",
										    trigger    : "txtFec_trigger", 
										    dateFormat : "%d/%m/%Y",
										    onSelect   : function() { this.hide() }
										});
									</script>
																							
						</td>						
					</tr>
					<tr>
						<td HEIGHT=0 VALIGN=''><p><div id='DIVC_28_VAL_C4'>Hora:</div></p></td>
						<td>
								<input type='text' id="txtHor" name="txtHor" 
									onkeyup="javascript:getMascaraHora(this);"
									 size='8' value="<%=poderesBean.getFecHora()==null?"":poderesBean.getFecHora()%>">
								</td>
					</tr>
					<tr>
					<td HEIGHT=0 VALIGN=''><div id='idTipDoc'>Tipo de Documento:</div></td>
					<p><td>
						<input type='radio' name='tp'  id='tp_esc' value = "<%=poderesBean.getIndTipoDocumento()==null?"":poderesBean.getIndTipoDocumento()%>" onchange="controlEscPod(this);" checked>Escritura
					
						<input type='radio' name='tp' id='tp_cp' value = "<%=poderesBean.getIndTipoDocumento()==null?"":poderesBean.getIndTipoDocumento()%>" onchange="controlEscPod(this);">Carta Poder
					</p></td>
					</tr>
					<tr class='tableHeaderAlfa2'>
						<p><th colspan='2' align='left'><br>Instrumento <br>
						<br></th></p>
					</tr>					
					<tr>					
					<tr class='tableRow2'>
						<td HEIGHT=0 VALIGN=''><p><div id='DIVC_17_VAL_C8_1'>Escritura No./Carta Poder</div></p></td>
						<td><div id='DIVC_17_VAL_C8_2'>
								<input type='text' title='' name='txtEscCp' id='txtEscCp' onblur="maskEsc(this);"
									onkeyup="quitarPunto(this);" size='8'
									value = "<%=poderesBean.getDescEscritura()==null?"":poderesBean.getDescEscritura()%>">
							</div></td>										
					</tr>					
					<tr>
						<td HEIGHT=0 VALIGN=''><div id='DIVC_17_VAL_C9_1'>Fecha Otorgamiento:</div></td>
						<td><div id='DIVC_17_VAL_C9_2'>
								<input type='text' id="txtFec_2" name="txtFec_2" 
									size='10' value = "<%=poderesBean.getFecOtorgamientoInstr()==null?"":poderesBean.getFecOtorgamientoInstr()%>"
									onkeyup="javascript:getMascaraFec(this);">																																										 
									  <img src="<c:out value="${applicationBean.contextPath}"/>/img/calendar.gif"
										id="txtFec_trigger_2" name="txtFec_trigger_2" title="Mostrar Calendario"
										 /> 
									<script>				
										Calendar.setup({
										    inputField : "txtFec_2",
										    trigger    : "txtFec_trigger_2", 
										    dateFormat : "%d/%m/%Y",
										    onSelect   : function() { this.hide() }
										});
									</script>
						</td>
					</tr>
					<tr>
						<td HEIGHT=0 VALIGN=''><div id='DIVC_17_VAL_C15_1'>No DE
								DOCUMENTO EN DOCUMENTUM:</div></td>
						<td><div id='DIVC_17_VAL_C15_2'>
								<input type='text' id='docNumDoc' name='docNumDoc'
									size='38' value ="<%=poderesBean.getNumDocumentumInstr()==null?"":poderesBean.getNumDocumentumInstr()%>"><a
									href='javascript:getDocumentum("docNumDoc");'><abbr
									title='Obtener Documento'><img alt='Obtener Documento'
										src='<c:out value="${applicationBean.contextPath}"/>/img/icons/List.png'></abbr> </a> <input type='hidden'
									name='idMetaRow_15' id='17__VAL_C15' value='VAL_C15'></div></td>
					</tr>
					<tr class='tableRow2'>
						<td HEIGHT=0 VALIGN=''><div id='DIVC_17_VAL_C4_1'>Requiere Protocolización:</div></td>
						<td><div id='DIVC_17_VAL_C4_2'>
								<input type='checkbox' name='cbReqPro' id='cbReqPro' value ="<%=poderesBean.getIndRequiereProto()%>" title="<%=poderesBean.getIndRequiereProto()%>"
									 onclick='isChecked(this);'onchange="controlSemReqPro2(this);"><br>
									 </div>
				   </td>
					</tr> 
					<tr class='tableRow2'>
						<td HEIGHT=0 VALIGN=''><div id='DIVC_17_VAL_C5_1'>Requiere
								Inscripción RPPC:</div></td>
						<td><div id='DIVC_17_VAL_C5_2'>
								<input type='checkbox' name='cbReqIns' id='cbReqIns' value ="<%=poderesBean.getIndRequiereInscrRppc()%>" title="<%=poderesBean.getIndRequiereInscrRppc()%>"
									onclick='isChecked(this);' onchange="controlSemReqIns2(this);"><br>
							</div></td>
					</tr>
					<tr class='tableRow2'>
						<td HEIGHT=0 VALIGN=''><div id='DIVC_17_VAL_C16_1'>Semáforo: 
							</div></td>
						<td><img id='DIVC_17_VAL_C16_2' value='VAL_C16' 
							src='<c:out value="${applicationBean.contextPath}"/>/img/semaforo_green.png'></td>
					</tr>
					<tr class='tableRow2'>
						<td HEIGHT=0 VALIGN=''><div id='DIVC_17_VAL_C10_1'>Licenciado:</div></td>
						<td>
							<div style='padding-bottom: 10px' id='DIVC_17_VAL_C10_2' style="display: block;">
								<select name='comLic' id='comLic' style='width: 300px'
									    onchange="javascript:getInfoLic(this,'txtNP','comDe');">
									    <option value ="<%=poderesBean.getNumLicenciado()%>" title="<%=poderesBean.getNumLicenciado()%>">---Seleccione---</option>									    
									    <%=ApoderadosPoderesDAO.getSelectList("0",12)%>
						</div>
					</td>
					</tr>
					<tr>
						<td HEIGHT=0 VALIGN=''><div id='DIVC_17_VAL_C11_1'>Notario Público:</div></td>
						<td><div style='padding-bottom: 10px' id='DIVC_17_VAL_C11_2'>
								<input type='text' id="txtNP" name="txtNP" 
									size='10' value = "<%=poderesBean.getNomNotarioPublico()==null?"":poderesBean.getNomNotarioPublico()%>"
									onkeyup="javascript:getInfoNotario(this,'comLic','comDe');">																																										 
									  </td>
					</tr>
					<tr class='tableRow2'>
					<td HEIGHT=0 VALIGN=''><div id='DIVC_17_VAL_C12_1'>De:</div></td>
						<td><div style='padding-bottom: 10px' id='DIVC_17_VAL_C12_2'>
								<select name='comDe' id='comDe' style='width: 300px'>
									    <option value="0">---Seleccione---</option>
									    <option value="<%=poderesBean.getNumDe()%>" title="<%=poderesBean.getNumDe()%>">---Seleccione---</option>
									    <%=ApoderadosPoderesDAO.getSelectList("0",13)%>
						   </div></td> 
						</div></td> 
					</tr>
					<tr>
						<td HEIGHT=0 VALIGN=''><div id='DIVC_17_VAL_C13_1'>Suplencia/Asociado:
							</div></td>
						<td><div style='padding-bottom: 10px' id='DIVC_17_VAL_C13_2'>
								<textarea name='supAsoc' id='supAsoc' cols='20' rows='5' value="<%=poderesBean.getDesSuplenciaAsociado()%>" title="<%=poderesBean.getDesSuplenciaAsociado()%>"></textarea>
							</div></td>
					</tr>
					<tr>
					<td HEIGHT=0 VALIGN=''><div id='DIVC_17_VAL_C18_1'>Inscrita
								en el Registro Público de Comercio de:</div></td>
						<td><div id='DIVC_17_VAL_C18_2'>
								<select name='comInsRpc' id='comInsRpc' style='width: 300px'
									    onchange="showDelegadoPor();"><option value="<%=poderesBean.getNumInscritaRegistroPublico()%>" title="<%=poderesBean.getNumInscritaRegistroPublico()%>">---Seleccione---</option>
									    <%=ApoderadosPoderesDAO.getSelectList("0",13)%>
						</div></td> 
					</tr>
					<tr class='tableRow2'>
						<td HEIGHT=0 VALIGN=''><div id='DIVC_17_VAL_C19_1'>Fecha
								de Registro:</div></td>
						<td><div style='padding-bottom: 10px' id='DIVC_17_VAL_C19_2'>
						<input type='text' id="txtFec_3" name="txtFec_3" 
									 size='10' value = "<%=poderesBean.getFecRegistro()==null?"":poderesBean.getFecRegistro()%>"
									onkeyup="javascript:getMascaraFec(this);">			
									  <img src="<c:out value="${applicationBean.contextPath}"/>/img/calendar.gif"
										id="txtFec_trigger_3" name="txtFec_trigger_3" title="Mostrar Calendario"
										 /> 
									<script>
									
									
										Calendar.setup({
										    inputField : "txtFec_3",
										    trigger    : "txtFec_trigger_3",
										    dateFormat : "%d/%m/%Y",
										    onSelect   : function() { this.hide() }
										});
									</script></div></td>
					</tr>
				<tr>
				<td HEIGHT=0 VALIGN=''><div id='DIVC_17_VAL_C20_1'>Número/
								Folio Mercantil / Folio Electrónico:</div></td>
						<td><div style='padding-bottom: 10px' id='DIVC_17_VAL_C20_2'>
								<input type='text' id='txtFolMe' name='txtFolMe' 
									size='15' value ="<%=poderesBean.getNumFolioMec()==null?"":poderesBean.getNumFolioMec()%>">
							</div></td>
					</tr>
					<tr>
						<td HEIGHT=0 VALIGN=''><div id='DIVC_17_VAL_C21_1'>Otros Datos de Registro:
							</div></td>
						<td><div style='padding-bottom: 20px' id='DIVC_17_VAL_C21_2'>								
								<textarea id='otrDat' name='otrDat' cols='20' rows='5' value ="<%=poderesBean.getDesOtrosDatosRegistro()==null?"":poderesBean.getDesOtrosDatosRegistro()%>"></textarea>								
							</div></td>
					</tr>
					<tr class='tableHeaderAlfa2'>
						<th colspan='2' align='left'><br>Referencia Documentum <br>
						<br></th>
					<tr>
						<td HEIGHT=0 VALIGN=''><div id='DIVC_17_VAL_C23_1'>
								<b><big>Memo</big></b>:
							</div></td>
						<td><div id='DIVC_17_VAL_C23_2'>
								<input type='checkbox' name='cbMemo' id='cbMemo' value ="<%=poderesBean.getIndMemo()%>" title="<%=poderesBean.getIndMemo()%>"
									onclick='isChecked(this);' onchange="validarStatus(this);"><br>
							</div></td>
					</tr>
					<tr>
						<td HEIGHT=0 VALIGN=''><div id='DIVC_17_VAL_C24_1'>Solicitado
								por:</div></td>
						<td><div id='DIVC_17_VAL_C24_2'>
								<select name='comSolPor' id='comSolPor' style='width: 300px'
									   onchange="showDelegadoPor();"><option value ="<%=poderesBean.getNumSolicitadoPor()%>" title="<%=poderesBean.getNumSolicitadoPor()%>">---Seleccione---</option>									    
									    <%=ApoderadosPoderesDAO.getSelectList("0",56)%>
						</div></td>
					</tr>
					<tr class='tableRow2'>
						<td HEIGHT=0 VALIGN=''><div id='DIVC_17_VAL_C25_1'>Fecha
								de Documento:</div></td>
						<td><div id='DIVC_17_VAL_C25_2'>
						<input type='text' id="txtFec_16" name="txtFec_16" 
									 size='10' value = "<%=poderesBean.getFecDocumentoMemo()==null?"":poderesBean.getFecDocumentoMemo()%>"
									onkeyup="javascript:getMascaraFec(this);">			
									  <img src="<c:out value="${applicationBean.contextPath}"/>/img/calendar.gif"
										id="txtFec_trigger_16" name="txtFec_trigger_16" title="Mostrar Calendario"
										 /> 
									<script>
									
									
										Calendar.setup({
										    inputField : "txtFec_16",
										    trigger    : "txtFec_trigger_16",
										    dateFormat : "%d/%m/%Y",
										    onSelect   : function() { this.hide() }
										});
									</script></div></td>
					</tr>					
					<tr>
						<td HEIGHT=0 VALIGN=''><div id='DIVC_17_VAL_C15_1'>No DE
								DOCUMENTO EN DOCUMENTUM:</div></td>
						<td><div id='DIVC_17_VAL_C15_2'>
								<input type='text' id='docNumDoc' name='docNumDoc'
									size='38' value ="<%=poderesBean.getNumDocumentumInstr()==null?"":poderesBean.getNumDocumentumInstr()%>"><a
									href='javascript:getDocumentum("docNumDoc");'><abbr
									title='Obtener Documento'><img alt='Obtener Documento'
										src='<c:out value="${applicationBean.contextPath}"/>/img/icons/List.png'></abbr> </a> <input type='hidden'
									name='idMetaRow_15' id='17__VAL_C15' value='VAL_C15'></div></td>
					</tr>	
						<tr class='tableRow2'>
						<td HEIGHT=0 VALIGN=''><div id='DIVC_17_VAL_C26_1'>Fecha
								Recibido:</div></td>
						<td><div style='padding-bottom: 10px' id='DIVC_17_VAL_C26_2'>
						<input type='text' id="txtFec_4" name="txtFec_4" 
									 size='10' value = "<%=poderesBean.getFecRecibidoMemo()==null?"":poderesBean.getFecRecibidoMemo()%>"
									onkeyup="javascript:getMascaraFec(this);">			
									  <img src="<c:out value="${applicationBean.contextPath}"/>/img/calendar.gif"
										id="txtFec_trigger_4" name="txtFec_trigger_4" title="Mostrar Calendario"
										 /> 
									<script>
									
									
										Calendar.setup({
										    inputField : "txtFec_4",
										    trigger    : "txtFec_trigger_4",
										    dateFormat : "%d/%m/%Y",
										    onSelect   : function() { this.hide() }
										});
									</script></div></td>
					</tr>					
					</tr>
					<tr class='tableRow2'>
						<td HEIGHT=0 VALIGN=''><div id='DIVC_17_VAL_C27_1'>Folio 
								No:</div></td>
						<td><div id='DIVC_17_VAL_C27_2'>
								<input type='text' name='txtNumFol' id='txtNumFol'
									size='0' value ="<%=poderesBean.getNumFolio()==null?"":poderesBean.getNumFolio()%>">
							</div></td>
					</tr>
					<tr>
						<td HEIGHT=0 VALIGN=''><div id='DIVC_17_VAL_C28_1'>No DE
								DOCUMENTO EN DOCUMENTUM:</div></td>
						<td><div id='DIVC_17_VAL_C28_2'>
								<input type='text' id='docNumDocM' name='docNumDocM'
									size='38' value ="<%=poderesBean.getNumDocumentoMemo()==null?"":poderesBean.getNumDocumentoMemo()%>"><a
									href='javascript:getDocumentum("docNumDocM");'><abbr
									title='Obtener Documento'><img alt='Obtener Documento'
										src='<c:out value="${applicationBean.contextPath}"/>/img/icons/List.png'></abbr> </a> <input type='hidden'
									name='idMetaRow_18' id='17__VAL_C28' value='VAL_C28'></div></td>
					</tr>					
					<tr class='tableRow2'>
						<td HEIGHT=0 VALIGN=''><div id='DIVC_17_VAL_C43_1'>
								<b><big>Documento de Entrega</big></b>:
							</div></td>
						<td><div id='DIVC_17_VAL_C43_2'>
								<input type='checkbox' name='cbDocEnt' id='cbDocEnt' value ="<%=poderesBean.getIndDocEntrega()%>" title="<%=poderesBean.getIndDocEntrega()%>"
									onclick='isChecked(this);' onchange="validarStatus(this);"><br>
							</div></td>
					</tr>
					<tr class='tableRow2'>
						<td HEIGHT=0 VALIGN=''><div id='DIVC_17_VAL_C44_1'>Fecha
							de Documento:</div></td>
						<td><div id='DIVC_17_VAL_C44_2'>
						<input type='text' id="txtFec_5" name="txtFec_5" 
									 size='10' value = "<%=poderesBean.getFecDocumentoEntrega()==null?"":poderesBean.getFecDocumentoEntrega()%>"
									onkeyup="javascript:getMascaraFec(this);">			
									  <img src="<c:out value="${applicationBean.contextPath}"/>/img/calendar.gif"
										id="txtFec_trigger_5" name="txtFec_trigger_5" title="Mostrar Calendario"
										 /> 
									<script>
									
									
										Calendar.setup({
										    inputField : "txtFec_5",
										    trigger    : "txtFec_trigger_5",
										    dateFormat : "%d/%m/%Y",
										    onSelect   : function() { this.hide() }
										});
									</script></div></td>
					</tr>
					<tr class='tableRow2'>
						<td HEIGHT=0 VALIGN=''><div id='DIVC_17_VAL_C45_1'>Fecha
							Recibido:</div></td>
						<td><div id='DIVC_17_VAL_C45_2'>
						<input type='text' id="txtFec_6" name="txtFec_6" 
									 size='10' value = "<%=poderesBean.getFecRecibidaEntrega()==null?"":poderesBean.getFecRecibidaEntrega()%>"
									onkeyup="javascript:getMascaraFec(this);">			
									  <img src="<c:out value="${applicationBean.contextPath}"/>/img/calendar.gif"
										id="txtFec_trigger_6" name="txtFec_trigger_6" title="Mostrar Calendario"
										 /> 
									<script>
									
									
										Calendar.setup({
										    inputField : "txtFec_6",
										    trigger    : "txtFec_trigger_6",
										    dateFormat : "%d/%m/%Y",
										    onSelect   : function() { this.hide() }
										});
									</script></div></td>
					</tr>
					<tr>
						<td HEIGHT=0 VALIGN=''><div id='DIVC_17_VAL_C46_1'>No DE
								DOCUMENTO EN DOCUMENTUM:</div></td>
						<td><div id='DIVC_17_VAL_C46_2'>
								<input type='text' id='docNumDocE' name='docNumDocE'
									size='38' value ="<%=poderesBean.getNumDocumentumEntrega()==null?"":poderesBean.getNumDocumentumEntrega()%>"><a
									href='javascript:getDocumentum("docNumDocE");'><abbr
									title='Obtener Documento'><img alt='Obtener Documento'
										src='<c:out value="${applicationBean.contextPath}"/>/img/icons/List.png'></abbr> </a> <input type='hidden'
									name='idMetaRow_18' id='17__VAL_C46' value='VAL_C46'></div></td>
					</tr>
					<tr class='tableRow2'>
						<td HEIGHT=0 VALIGN=''><div id='DIVC_17_VAL_C29_1'>
								<b><big>Otros</big></b>:
							</div></td>
						<td><div id='DIVC_17_VAL_C29_2'>
								<input type='checkbox' name='cbOtr' id='cbOtr' value ="<%=poderesBean.getIndOtros()%>" title="<%=poderesBean.getIndOtros()%>"
									onclick='isChecked(this);' onchange="validarStatus(this);"><br>
							</div></td>
					</tr>
					<tr class='tableRow2'>
						<td HEIGHT=0 VALIGN=''><div id='DIVC_17_VAL_C30_1'>Fecha
								de Documento:</div></td>
						<td><div id='DIVC_17_VAL_C30_2'>
						<input type='text' id="txtFec_7" name="txtFec_7" 
									 size='10' value = "<%=poderesBean.getFecDocumentoOtros()==null?"":poderesBean.getFecDocumentoOtros()%>"
									onkeyup="javascript:getMascaraFec(this);">			
									  <img src="<c:out value="${applicationBean.contextPath}"/>/img/calendar.gif"
										id="txtFec_trigger_7" name="txtFec_trigger_7" title="Mostrar Calendario"
										 /> 
									<script>
									
									
										Calendar.setup({
										    inputField : "txtFec_7",
										    trigger    : "txtFec_trigger_7",
										    dateFormat : "%d/%m/%Y",
										    onSelect   : function() { this.hide() }
										});
									</script></div></td>
					</tr>					
					<tr class='tableRow2'>
						<td HEIGHT=0 VALIGN=''><div id='DIVC_17_VAL_C31_1'>Fecha
								Recibido:</div></td>
						<td><div id='DIVC_17_VAL_C31_2'>
						<input type='text' id="txtFec_8" name="txtFec_8" 
									 size='10' value = "<%=poderesBean.getFecRecibidoOtros()==null?"":poderesBean.getFecRecibidoOtros()%>"
									 onkeyup="javascript:getMascaraFec(this);">			
									  <img src="<c:out value="${applicationBean.contextPath}"/>/img/calendar.gif"
										id="txtFec_trigger_8" name="txtFec_trigger_8" title="Mostrar Calendario"
										 /> 
									<script>
									
									
										Calendar.setup({
										    inputField : "txtFec_8",
										    trigger    : "txtFec_trigger_8",
										    dateFormat : "%d/%m/%Y",
										    onSelect   : function() { this.hide() }
										});
									</script></div></td>
					</tr>
					<tr class='tableRow2'>
						<td HEIGHT=0 VALIGN=''><div id='DIVC_17_VAL_C32_1'>No DE
								DOCUMENTO EN DOCUMENTUM:</div></td>
						<td><div style='padding-bottom: 20px' id='DIVC_17_VAL_C32_2'>
								<input type='text' id='docNumDocO' name='docNumDocO'
									size='38' value ="<%=poderesBean.getNumDocumentumOtros()==null?"":poderesBean.getNumDocumentumOtros()%>"><a
									href='javascript:getDocumentum("docNumDocO");'><abbr
									title='Obtener Documento'><img alt='Obtener Documento'
										src='<c:out value="${applicationBean.contextPath}"/>/img/icons/List.png'></abbr> </a> <input type='hidden'
									name='idMetaRow_18' id='17__VAL_C32' value='VAL_C32'></div></td>
					</tr>				
					<tr class='tableHeaderAlfa2'>
						<th colspan='2' align='left'><br>Status <br>
						<br></th>
					<tr>
						<td HEIGHT=0 VALIGN=''><div id='DIVC_17_VAL_C76_1'>Aplica:
							</div></td>
						<td><div id='DIVC_17_VAL_C76_2'>
						<input type='checkbox' name='cbEstatus' id='cbEstatus' value ="<%=poderesBean.getIndAplicaEstatus()%>" title="<%=poderesBean.getIndAplicaEstatus()%>"
									 onclick='isChecked(this);'onchange="habilitarStatus(this);"><br>
									 <script>$(document).ready(function(){
													$("#cbEstatus").attr('maxlength','6');
													cambiarStatus(document.getElementById('cbEstatus').value);													
												});
									</script>								
							</div></td>
					</tr>
					<tr class='tableRow2'>
						<td HEIGHT=0 VALIGN=''><div id='DIVC_17_VAL_C150_1'>Semáforo
								Status:</div></td>
						<td><img id='semEst' value='VAL_C150'
							src='<c:out value="${applicationBean.contextPath}"/>/img/semaforo_green.png'></td>
					</tr>
					<tr>
					<tr class='tableRow2'>
						<td HEIGHT=0 VALIGN=''><div id='DIVC_17_VAL_C56_1'>Fecha
							Programada de Entrega:</div></td>
						<td><div id='DIVC_17_VAL_C56_2'>
						<input type='text' id="txtFec_9" name="txtFec_9" 
									 size='10' value = "<%=poderesBean.getFecProgEntregaEstatus()==null?"":poderesBean.getFecProgEntregaEstatus()%>"
									onkeyup="javascript:getMascaraFec(this);">			
									  <img src="<c:out value="${applicationBean.contextPath}"/>/img/calendar.gif"
										id="txtFec_trigger_9" name="txtFec_trigger_9" title="Mostrar Calendario"
										 /> 
									<script>
									
									
										Calendar.setup({
										    inputField : "txtFec_9",
										    trigger    : "txtFec_trigger_9",
										    dateFormat : "%d/%m/%Y",
										    onSelect   : function() { this.hide() }
										});
									</script></div></td>
					</tr>					
					<tr>
						<td HEIGHT=0 VALIGN=''><div id='DIVC_17_VAL_C57_1'>
								<b><big>Redactada</big></b>:
							</div></td>
						<td><div id='DIVC_17_VAL_C57_2'>
								<input type='checkbox' name='cbRedac' id='cbRedac' value ="<%=poderesBean.getIndRedactada()%>" title="<%=poderesBean.getIndRedactada()%>"
									onclick='isChecked(this);' onchange="validarStatus(this);"><br>
							</div></td>
					</tr>
					<tr class='tableRow2'>
						<td HEIGHT=0 VALIGN=''><div id='DIVC_17_VAL_C58_1'>Responsable:
							</div></td>
						<td><div id='DIVC_17_VAL_C58_2'>
								<select name='comResp' id='comResp' style='width: 300px'
									    onchange="showDelegadoPor();"><option value ="<%=poderesBean.getNumRespRedactada()%>" title="<%=poderesBean.getNumRespRedactada()%>">---Seleccione---</option>
									    <%=ApoderadosPoderesDAO.getSelectList("0",59)%>
						</div></td> 
					</tr>
					<tr class='tableRow2'>
						<td HEIGHT=0 VALIGN=''><div id='DIVC_17_VAL_C59_1'>Fecha
							Programada de Entrega:</div></td>
						<td><div id='DIVC_17_VAL_C59_2'>
						<input type='text' id="cumResp" name="cumResp" 
									 size='10' value = "<%=poderesBean.getFecCumplimientoRedactada()==null?"":poderesBean.getFecCumplimientoRedactada()%>"
									onkeyup="javascript:getMascaraFec(this);">			
									  <img src="<c:out value="${applicationBean.contextPath}"/>/img/calendar.gif"
										id="txtFec_trigger_10" name="txtFec_trigger_10" title="Mostrar Calendario"
										 /> 
									<script>
									
									
										Calendar.setup({
										    inputField : "cumResp",
										    trigger    : "txtFec_trigger_10",
										    dateFormat : "%d/%m/%Y",
										    onSelect   : function() { this.hide() }
										});
									</script></div></td>
					</tr>		
					<tr class='tableRow2'>
						<td HEIGHT=0 VALIGN=''><div id='DIVC_17_VAL_C60_1'>
								<b><big>Revisión Gerente</big></b>:
							</div></td>
						<td><div id='DIVC_17_VAL_C60_2'>
								<input type='checkbox' name='cbRevGte' id='cbRevGte' value ="<%=poderesBean.getIndRevisionGerente()%>" title="<%=poderesBean.getIndRevisionGerente()%>"
									onclick='isChecked(this);' onchange="validarStatus(this);"><br>
							</div></td>
					</tr>
					<tr>
						<td HEIGHT=0 VALIGN=''><div id='DIVC_17_VAL_C61_1'>Responsable:
							</div></td>
						<td><div id='DIVC_17_VAL_C61_2'>
								<select name='comRespGte' id='comRespGte' style='width: 300px'
									    onchange="showDelegadoPor();"><option value ="<%=poderesBean.getNumRespGerente()==0?"":poderesBean.getNumRespGerente()%>">---Seleccione---</option>
									    <%=ApoderadosPoderesDAO.getSelectList("0",59)%>
						</div></td> 
					</tr>
					<tr>
						<td HEIGHT=0 VALIGN=''><div id='DIVC_17_VAL_C62_1'>Cumplimiento:
							</div></td>
						<td><div id='DIVC_17_VAL_C62_2'>
								<input type='text' id="cumGte" name="cumGte" 
									 size='10' value ="<%=poderesBean.getFecCumplimientoGerente()==null?"":poderesBean.getFecCumplimientoGerente()%>"
									onkeyup="javascript:getMascaraFec(this);">
									
									  <img src="<c:out value="${applicationBean.contextPath}"/>/img/calendar.gif"
										id="txtFec_trigger_11" name="txtFec_trigger_11" title="Mostrar Calendario"
										 /> 
									<script>
									
									
										Calendar.setup({
										    inputField : "cumGte",
										    trigger    : "txtFec_trigger_11",
										    dateFormat : "%d/%m/%Y",
										    onSelect   : function() { this.hide() }
										});
									</script></td>
					</tr>				
					<tr>
						<td HEIGHT=0 VALIGN=''><div id='DIVC_17_VAL_C63_1'>
								<b><big>Correcciones</big></b>:
							</div></td>
						<td><div id='DIVC_17_VAL_C63_2'>
								<input type='checkbox' name='cbCorr' id='cbCorr' value ="<%=poderesBean.getIndCorrecciones()==null?"":poderesBean.getIndCorrecciones()%>"
									onclick='isChecked(this);' onchange="validarStatus(this);"><br>
							</div></td>
					</tr>
					<tr class='tableRow2'>
						<td HEIGHT=0 VALIGN=''><div id='DIVC_17_VAL_C64_1'>Responsable:
							</div></td>
						<td><div id='DIVC_17_VAL_C64_2'>
								<select name='cbRespCorr' id='cbRespCorr' style='width: 300px'
									   onchange="showDelegadoPor();"><option value ="<%=poderesBean.getNumRespCorrecciones()==0?"":poderesBean.getNumRespCorrecciones()%>">---Seleccione---</option>
									   <%=ApoderadosPoderesDAO.getSelectList("0",59)%>
						</div></td> 
					</tr>
					<tr class='tableRow2'>
						<td HEIGHT=0 VALIGN=''><div id='DIVC_17_VAL_C65_1'>Cumplimiento:
							</div></td>
						<td><div id='DIVC_17_VAL_C65_2'>
									<input type='text' id="cumCorr" name="cumCorr" 
									 size='10' value ="<%=poderesBean.getFecCumplimientoCorrecciones()==null?"":poderesBean.getFecCumplimientoCorrecciones()%>"
									onkeyup="javascript:getMascaraFec(this);">									
									  <img src="<c:out value="${applicationBean.contextPath}"/>/img/calendar.gif"
										id="txtFec_trigger_12" name="txtFec_trigger_12" title="Mostrar Calendario"
										 /> 
									<script>
									
									
										Calendar.setup({
										    inputField : "cumCorr",
										    trigger    : "txtFec_trigger_12",
										    dateFormat : "%d/%m/%Y",
										    onSelect   : function() { this.hide() }
										});
									</script></td>
					</tr>			
					<tr class='tableRow2'>
						<td HEIGHT=0 VALIGN=''><div id='DIVC_17_VAL_C66_1'>
								<b><big>Aut. Dirección</big></b>: 
							</div></td>
						<td><div id='DIVC_17_VAL_C66_2'>
								<input type='checkbox' name='cbAutDir' id='cbAutDir' value ="<%=poderesBean.getIndAutDireccion()==null?"":poderesBean.getIndAutDireccion()%>"
									onclick='isChecked(this);' onchange="validarStatus(this);"><br>
							</div></td>
					</tr>
					<tr>
						<td HEIGHT=0 VALIGN=''><div id='DIVC_17_VAL_C67_1'>Responsable:   
							</div></td>
						<td><div id='DIVC_17_VAL_C67_2'>
								<select name='comRespDir' id='comRespDir' style='width: 300px'
									   onchange="showDelegadoPor();"><option value = "<%=poderesBean.getNumRespAut()%>" title="<%=poderesBean.getNumRespAut()%>">---Seleccione---</option>
									    <%=ApoderadosPoderesDAO.getSelectList("0",59)%>
						</div></td> 
					</tr>
					<tr>
						<td HEIGHT=0 VALIGN=''><div id='DIVC_17_VAL_C68_1'>Cumplimiento:   
							</div></td>
						<td><div style='padding-bottom: 10px' id='DIVC_17_VAL_C68_2'>
									<input type='text' id="cumDir" name="cumDir" 
									 size='10' value = "<%=poderesBean.getIndAutDireccion()==null?"":poderesBean.getIndAutDireccion()%>"
									onkeyup="javascript:getMascaraFec(this);">									
									  <img src="<c:out value="${applicationBean.contextPath}"/>/img/calendar.gif"
										id="txtFec_trigger_13" name="txtFec_trigger_13" title="Mostrar Calendario"
										 /> 
									<script>
									
									
										Calendar.setup({
										    inputField : "cumDir",
										    trigger    : "txtFec_trigger_13",
										    dateFormat : "%d/%m/%Y",
										    onSelect   : function() { this.hide() }
										});
									</script></td>
					</tr>	
					<tr>
						<td HEIGHT=0 VALIGN=''><div id='DIVC_17_VAL_C69_1'>   
								<b><big>En firmas</big></b>:   
							</div></td>
						<td><div id='DIVC_17_VAL_C69_2'>
								<input type='checkbox' name='cbEnFirm' id='cbEnFirm' value = "<%=poderesBean.getIndFirmas()==null?"":poderesBean.getIndFirmas()%>"
									onclick='isChecked(this);' onchange="validarStatus(this);"><br>
							</div></td>
					</tr>
					<tr class='tableRow2'>
						<td HEIGHT=0 VALIGN=''><div id='DIVC_17_VAL_C70_1'>Responsable:   
							</div></td>
						<td><div id='DIVC_17_VAL_C70_2'>
								<select name='comRespFirm' id='comRespFirm' style='width: 300px'
									   onchange="showDelegadoPor();"><option value = "<%=poderesBean.getNumRespFirmas()%>" title="<%=poderesBean.getNumRespFirmas()%>">---Seleccione---</option>
									   <%=ApoderadosPoderesDAO.getSelectList("0",59)%>
						</div></td> 
					</tr>
					<tr class='tableRow2'>
						<td HEIGHT=0 VALIGN=''><div id='DIVC_17_VAL_C71_1'>Cumplimiento:
							</div></td>
						<td><div style='padding-bottom: 10px' id='DIVC_17_VAL_C71_2'>
									<input type='text' id="cumFirm" name="cumFirm"    
									 size='10' value = "<%=poderesBean.getFecCumplimientoFirmas()==null?"":poderesBean.getFecCumplimientoFirmas()%>"
									onkeyup="javascript:getMascaraFec(this);">
									 
									  <img src="<c:out value="${applicationBean.contextPath}"/>/img/calendar.gif"
										id="txtFec_trigger_14" name="txtFec_trigger_14" title="Mostrar Calendario"
										 /> 
									<script>
									
									
										Calendar.setup({
										    inputField : "cumFirm",
										    trigger    : "txtFec_trigger_14",
										    dateFormat : "%d/%m/%Y",
										    onSelect   : function() { this.hide() }
										});
									</script></td>
					</tr>				
					<tr class='tableRow2'>
						<td HEIGHT=0 VALIGN=''><div id='DIVC_17_VAL_C72_1'>
								<b><big>Entregada</big></b>:
							</div></td>
						<td><div id='DIVC_17_VAL_C72_2'>
								<input type='checkbox' name='cbEntr' id='cbEntr' value = "<%=poderesBean.getIndEntregada()==null?"":poderesBean.getIndEntregada()%>"
									onclick='isChecked(this);' onchange="validarStatus(this);"><br>
							</div></td>
					</tr>
					<tr>
						<td HEIGHT=0 VALIGN=''><div id='DIVC_17_VAL_C73_1'>Responsable:   
							</div></td>
						<td><div style= 'padding-bottom: 5px' id='DIVC_17_VAL_C73_2'>
								<select name='comEntr' id='comEntr' style='width: 300px'
									    onchange="showDelegadoPor();"><option value = "<%=poderesBean.getNumRespEntregada()%>" title="<%=poderesBean.getNumRespEntregada()%>">---Seleccione---</option>
									    <%=ApoderadosPoderesDAO.getSelectList("0",59)%>
						</div></td> 
					</tr>
					<tr>
						<td HEIGHT=0 VALIGN=''><div id='DIVC_17_VAL_C74_1'>Cumplimiento:   
							</div></td>
						<td><div style='padding-bottom: 10px' id='DIVC_17_VAL_C74_2'>
						<input type='text' id="cumEntr" name="cumEntr"
									 onkeyup="javascript:getMascaraFec(this);" 
									 size='10' value = "<%=poderesBean.getFecCumplimientoEntregada()==null?"":poderesBean.getFecCumplimientoEntregada()%>">																		
									 <img src="<c:out value="${applicationBean.contextPath}"/>/img/calendar.gif"
									 id="txtFec_trigger_15" name="txtFec_trigger_15" title="Mostrar Calendario"
										 /> 
									<script>
									
									
										Calendar.setup({
										    inputField : "cumEntr",
										    trigger    : "txtFec_trigger_15",
										    dateFormat : "%d/%m/%Y",
										    onSelect   : function() { this.hide() }
										});
									</script></td>
					</tr>
					<tr class='tableRow2'>
						<td HEIGHT=0 VALIGN=''><div id='DIVC_17_VAL_C6_1'>Enviada   
								a Notaría N°:</div></td>
						<td><div style='padding-bottom: 5px' id='DIVC_17_VAL_C6_2'>
								<input type='text' title='' name='envNot' id='envNot' size='0'
									value = "<%=poderesBean.getNumEnviadaNotaria()==null?"":poderesBean.getNumEnviadaNotaria()%>">
							</div>
						</td>
					</tr>
					<tr>
						<td HEIGHT=0 VALIGN=''><div id='DIVC_17_VAL_C7_1'>Fecha de
								envío a NP:</div></td>
						<td><div id='DIVC_17_VAL_C7_2'>
								<input type='text' id="envMp" name="envMp"
								      onkeyup="javascript:getMascaraFec(this);" 
									  size='10' value ="<%=poderesBean.getFecEnvioNotaria()==null?"":poderesBean.getFecEnvioNotaria()%>">				 
									  <img src="<c:out value="${applicationBean.contextPath}"/>/img/calendar.gif"
										id="txtFec_trigger_1" name="txtFec_trigger_1" title="Mostrar Calendario"
										 /> 
									<script>
									
									
										Calendar.setup({
										    inputField : "envMp",
										    trigger    : "txtFec_trigger_1",
										    dateFormat : "%d/%m/%Y",
										    onSelect   : function() { this.hide() }
										});
									</script></div></td>
					</tr>
					<input type='hidden' name='idMetaRow_17' id='idMetaRow_17' value='0'>
					<input type='hidden' name='fieldIds_17' id='fieldIds_17'						
				        value= 17__VAL_C149|17__VAL_C1|17__VAL_C4|17__VAL_C5|17__VAL_C16|17__VAL_C6|17__VAL_C7|17__VAL_C8|17__VAL_C9|17__VAL_C10|17__VAL_C11|17__VAL_C12|17__VAL_C13|17__VAL_C15|17__VAL_C18|17__VAL_C19|17__VAL_C20|17__VAL_C21|17__VAL_C23|17__VAL_C24|17__VAL_C25|17__VAL_C26|7__VAL_C27|17__VAL_C28|17__VAL_C43|17__VAL_C45|17__VAL_C46|17__VAL_C29|17__VAL_C30|17__VAL_C31|17__VAL_C32|17__VAL_C76|17__VAL_C150|17__VAL_C56|17__VAL_C57|17__VAL_C58|17__VAL_C59|17__VAL_C60|17__VAL_C61|17__VAL_C62|17__VAL_C63|17__VAL_C64|17__VAL_C65|17__VAL_C66|17__VAL_C67|17__VAL_C68|7__VAL_C69|17__VAL_C70|17__VAL_C71|17__VAL_C72|17__VAL_C73|17__VAL_C74|'>
				
				</table>
				</td>
				</tr>
				<tr>
					      <td>&nbsp;</td>
					      <td align="right">
					      	<img src="<c:out value="${applicationBean.contextPath}"/>/img/wait-bar.gif" id="imgFlexTableWait" style="display: none">
					      	
					      	<input type="button" id="flexTableformButton" value="Guardar" onclick="savePoderes();">
					      </td>
				        </tr>
  	</tbody></table>
  	</td>
  </tr>
</table> 
</form>
	<jsp:include
		page="/jsp/components/pages_border/close.jsp"></jsp:include></td>
	</tr>
</table>

</body>
</html>
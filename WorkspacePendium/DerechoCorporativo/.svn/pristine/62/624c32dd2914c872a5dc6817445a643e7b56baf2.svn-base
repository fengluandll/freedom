<%@page import="mx.com.televisa.derechocorporativo.bean.ApoderadosBean, mx.com.televisa.derechocorporativo.util.TextFormatter"%>
<%@page import="mx.com.televisa.derechocorporativo.components.JSCal"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="mx.com.televisa.derechocorporativo.util.FacesUtils"%>
<%@page import="java.util.List"%>
<%@page import="mx.com.televisa.derechocorporativo.model.*"%>
<%@page import="mx.com.televisa.derechocorporativo.bean.SessionBean"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*,mx.com.televisa.derechocorporativo.daos.ApoderadosDAO" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@include file="/jsp/components/calendar/include_calendar.jsp"%>
<%@include file="/css/kaz-styles.jsp"%>
<%@include file="../part/validaciones.jsp"%>
<script src="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.min.js"></script> 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.css">
<title>Campos Adicionales</title>
<script type="text/javascript">
window.onload = function(){
	
	changeRevoca();
}

function changeRevoca(){
	if(document.getElementById('chkRevocar').checked){
		document.getElementById('contentRev').style.display='block';
	}else{
		document.getElementById('contentRev').style.display='none';
	}
	if(document.getElementById('rdioOtro').checked){
		activaOtro();
	}else if(document.getElementById('rdioEsc').checked){
		activaEscritura();
	}
	
	if(document.getElementById('rdioOtro').checked == false && document.getElementById('rdioEsc').checked == false){
		document.getElementById('selectEsc').style.display = 'none';
		document.getElementById('lblEsc').style.display    = 'none';
	}
	
}

function daleSubmit(){
	
	var porNombre=document.getElementsByName("rdioRev");
	var escVal = document.getElementById('selectEsc').value;
	var isCheckEsc  = document.getElementById('rdioEsc').checked;
	var isCheckOtro = document.getElementById('rdioOtro').checked;
	
	var count = 0;
	for(var i=0;i<porNombre.length;i++){
        if(porNombre[i].checked)
            count++;
    }
	
	if(count == 0){
		swal("Atención", "Es requerido seleccionar Revocado mediante","warning");	
	}else if((escVal == 0 && isCheckEsc == false && isCheckOtro == false) || (escVal == 0 && isCheckEsc == true && isCheckOtro == false) || (escVal != 0 && isCheckEsc == false && isCheckOtro == false)){
		swal("Atención", "Es requerido seleccionar la escritura","warning");
	}else{
		document.getElementById('form1').submit();
		window.opener.document.getElementById('idMensajeGuardar').style.display='block';
	}
}

function activaOtro(){
	
	document.getElementById('selectEsc').style.display = 'none';
	//document.getElementById('selectEsc').value = '0';
	document.getElementById('txtDocumentum').style.display  	= 'block';
	document.getElementById('lblEsc').style.display  		= 'none';
	document.getElementById('lblRevM').style.display 		= 'block';
	document.getElementById('txtRevM').style.display 		= 'block';
	document.getElementById('lblFec').style.display  		= 'block';
	document.getElementById('txtFec').style.display  		= 'block';
	document.getElementById('calFec').style.display  		= 'block';
	
	document.getElementById('lblDoc').style.display  		= 'block';
	document.getElementById('linkDoc').style.display  		= 'block';
	document.getElementById('opcRevocada').value = '0';
	
}
function activaEscritura(){
	
	document.getElementById('selectEsc').style.display = 'block';
	document.getElementById('txtDocumentum').style.display  	= 'none';
	document.getElementById('lblEsc').style.display  = 'block';
	document.getElementById('lblRevM').style.display = 'none';
	//document.getElementById('txtRevM').value 		 = '';
	//document.getElementById('txtFec').value 		 = '';
	//document.getElementById('txtDocumentum').value 	 = '';
	document.getElementById('txtRevM').style.display = 'none';
	document.getElementById('lblFec').style.display  = 'none';
	document.getElementById('txtFec').style.display  = 'none';
	document.getElementById('calFec').style.display  = 'none';
	
	document.getElementById('lblDoc').style.display  		= 'none';
	document.getElementById('linkDoc').style.display  		= 'none';
}


</script>

<style type="text/css">

#btnGuardar {
	text-align: center;
}
.formulario {
	font-weight: bold;
}
.formulario {
	text-align: center;
}
.campos {
	text-align: center;
}
#campos {
	text-align: center;
}
</style>

</head>
<body>
<%  String id = request.getParameter("idCatalogoValor"); 
	String idCatalogo = request.getParameter("idCatalogo");
	String tipoPoder  = request.getParameter("tipoPoder");
	String grupoApoderado = request.getParameter("grupoApoderado");
	String selectEscritura  = request.getParameter("escritura");
	
	System.out.println("id "+id);
	System.out.println("idCatalogo "+idCatalogo);
	System.out.println("tipoPoder "+tipoPoder);
	System.out.println("grupoApoderado "+grupoApoderado);
	System.out.println("selectEscritura "+selectEscritura);
	
	SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
	String idEmpresa = sessionBean.getIdCurrentEmpresa();
	System.out.println("idEmpresa "+idEmpresa);
	ApoderadosDAO apoderadosDAO = new ApoderadosDAO();
	
	ApoderadosBean apoderadosBean = apoderadosDAO.dameApoderado(Integer.parseInt(id),Integer.parseInt(idCatalogo),Integer.parseInt(tipoPoder),grupoApoderado,selectEscritura,Integer.parseInt(idEmpresa));
	SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
	String fecha = "";
	if(apoderadosBean.getFecha() != null){
		fecha = formatoDelTexto.format(apoderadosBean.getFecha());	
	}
	String tipBaja = "";
	if(apoderadosBean.getTipoBaja() != null){
		tipBaja = apoderadosBean.getTipoBaja();
	}
	String doc = "";
	if(apoderadosBean.getDocumento() != null){
		doc = apoderadosBean.getDocumento();
	}
	String refDoc = "";
	if(apoderadosBean.getRefDocumentum() != null){
		refDoc = apoderadosBean.getRefDocumentum();
	}
	
	String nombreApo = request.getParameter("nombre");
	//nombreApo = TextFormatter.removeAccents(nombreApo);
	//nombreApo = new String(nombreApo.getBytes("ISO-8859-1"),"UTF-8");
	
    List<CatalogElementV2> elems = new Catalog().dameEscrituras(Integer.parseInt(idEmpresa),Integer.parseInt(tipoPoder));
        
%>
<form id="form1" name="form1" method="post" action="/DerechoCorporativo/CamposAdicionalesServlet">
<input id="idCatApode" name="idCatApode" type="hidden" value="<%=idCatalogo%>"/>
<input id="idCatalogoValor" name="idCatalogoValor" type="hidden" value="<%=id%>"/>
<input id="txtNombreApode" name="txtNombreApode" type="hidden" value="<%= request.getParameter("nombre") %>"/>

<input id="tipoPoder" name="tipoPoder" type="hidden" value="<%=tipoPoder%>"/>
<input id="selectEscritura" name="selectEscritura" type="hidden" value="<%=selectEscritura%>"/>
<input id="grupoApoderado" name="grupoApoderado" type="hidden" value="<%=grupoApoderado%>"/>

<table width="90%" border="0" cellpadding="1">
  <tr>
    <th colspan="2" scope="col"><%=nombreApo%> </th>
    
  </tr>
  <tr>
  		<th colspan="3" scope="col" style="color:red;"> <%= request.getParameter("mensaje") == null ? "" : request.getParameter("mensaje")%> </th>
  </tr>
  <tr>
  	<td>
  		&nbsp;
  	</td>
  	<td>
  		&nbsp;
  	</td>
  	<td>
  		&nbsp;
  	</td>
  </tr>
  <!-- 
  <tr>
    <td class="formulario">Fecha:</td>
    <td class="campos"><label for="txtFecha"></label>      
      <input type="text" name="txtFecha" id="txtFecha" value="<%=fecha%>"/>
    </td>
    <td>
    	<%= JSCal.getCalendar("txtFecha", "") %>
    </td>
  </tr>
  <tr>
    <td class="formulario">Tipo de Baja:</td>
    <td class="campos"><label for="txtTipoBaja"></label>
    <input type="text" name="txtTipoBaja" id="txtTipoBaja" value="<%=tipBaja%>"/></td>
  </tr>
  <tr>
    <td class="formulario">No de Documento:</td>
    <td class="campos"><label for="txtDoc"></label>
    <input type="text" name="txtDoc" id="txtDoc" value="<%=doc%>" /></td>
  </tr>
  -->

  <tr>
  	<td class="formulario">Revocar:</td>
  	<%String aplicaCheck = "";
  		if(apoderadosBean.getCheckRev()!=null && apoderadosBean.getCheckRev().equals("Si")){
  		aplicaCheck = "checked";
  	}%>
  	<td><input id="chkRevocar" name="chkRevocar" type="checkbox" onclick="changeRevoca()" <%=aplicaCheck%>/>  </td>
  </tr>
 
</table>

<div id="contentRev" style="margin-top: 30px;">
<fieldset>
<legend align="left">Campos para Revocar</legend>
	<table>
		<tr>
			<td>
				Revocado mediante:
			</td>
			<td>
			<%	String rdiorevMedEsc = "";
				String rdiorevMedOtro = "";
				if(apoderadosBean.getAtributo15() != null && apoderadosBean.getAtributo15().equals("esc")){
					rdiorevMedEsc = "checked";
				} else if(apoderadosBean.getAtributo15() != null && apoderadosBean.getAtributo15().equals("otro")){
					rdiorevMedOtro = "checked";
				}%>
				<input type="radio" id="rdioEsc" name="rdioRev" value="esc" <%=rdiorevMedEsc%> onclick="activaEscritura()">Escritura
			</td>
			<td>
				<input type="radio" id="rdioOtro" name="rdioRev" value="otro" <%=rdiorevMedOtro%> onclick="activaOtro()"/>Otro
			</td>
		</tr>
	</table>
	
	<table>
		<tr>
			<td> &nbsp; </td>
		</tr>
		<tr>
		   			<td class=""><span id="lblEsc" style="display: block">Escritura:</span></td>
				  	<td> <select style="display: block" name="selectEsc" id="selectEsc" onchange="" >
				      <option id="opcRevocada" value="0">---Seleccione---</option>
							    <% 
							    	for (CatalogElementV2 elem : elems) {%>
									 <%	String selected = "";
									 	if(elem.getValC4() != null){
									 	if(elem.getValValor().equals(apoderadosBean.getProtoMedianteEsc())){
									 		selected = "selected";
									 	}
									 %>
									 	<%if(!selectEscritura.trim().equals(elem.getValValor().trim())){ %>
									 		<option value="<%=elem.getValValor()%>" <%=selected%> ><%=elem.getValValor()%></option>	
									 	<% } %>
								    	
								    <%}%>
							    <% }%>
							</select>	</td>
					<!-- <td class="">De Fecha:</td>
					<td><input type="text" id="txtFecP" name="txtFecP" value="<%=apoderadosBean.getProtoMedianteEscFecha()==null?"":apoderadosBean.getProtoMedianteEscFecha()%>"/></td>
					<td><%= JSCal.getCalendar("txtFecP", "") %></td>		
					-->
	  </tr>
	  
		  <tr>
		 
		  		<td class=""><span id="lblRevM" style="display: none"> Revocado mediante:</span></td>
				<td><input id="txtRevM" style="display: none" type="text" name="txtRevM" id="txtRevM" value="<%=apoderadosBean.getRevocadoMediante()==null?"":apoderadosBean.getRevocadoMediante()%>"/> </td>
				<td class=""><span id="lblFec" style="display: none">De Fecha:</span></td>
				<td><input id="txtFec" style="display: none" type="text" id="txtFecM" name="txtFecM" value="<%=apoderadosBean.getRevocadoMedianteFecha()==null?"":apoderadosBean.getRevocadoMedianteFecha()%>"/></td>
				<td><span id="calFec" style="display: none"> <%= JSCal.getCalendar("txtFec", "") %></span></td>
			
		  </tr>
	  
	<!--   <tr>
	  		<td class="" colspan="1">Id. Revocación</td>
			<td><input type="text" name="txtIdRev" id="txtIdRev" value="<%=(apoderadosBean.getIdRevocacion()==null||apoderadosBean.getIdRevocacion().equals("-1"))?"":apoderadosBean.getIdRevocacion()%>"/> </td>
	  </tr>
	   -->
	     <tr>
		  	<td><span id="lblDoc" style="display: none">No. de Documento en Documentum:</span></td>
		    <td class="campos"><label for="txtDocumentum"></label>
		    <input style="display: none" type="text" name="txtDocumentum" id="txtDocumentum" value="<%=refDoc%>" 
		    size = 38 
		    onkeyup="javascript:getMascaraDocumentum(this);"/><!-- maxlength=38 --></td>
		    <td><a href="javascript:getDocumentum('txtDocumentum');"><abbr title='No DE DOCUMENTO EN DOCUMENTUM'><img id="linkDoc" style="display: none" alt='Obtener Documento' src="../../../img/icons/List.png"></abbr> </a> </td>
		  </tr>
	</table>
</fieldset>	
</div>	
<center>
<table>
	  <tr>
	    <td style="text-align:center"><input type="button" onclick="daleSubmit()" name="btnGuardar" id="btnGuardar" value="Aceptar" /></td>
	  </tr>
</table>
</center>
</form>
</body>
</html>
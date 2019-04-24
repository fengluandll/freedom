<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<head>
	<script src="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.min.js"></script> 
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.css">
</head>

<script type="text/javascript">




function updateReport(idReportFlex, nomReport, descReport, descRFC, descPais, saltoPag) {
	
	//alert('Hi');
	
    var ajaxRequest = simpleAjaxUtil_getXmlHttpObject();     
	
    if (ajaxRequest==null) {alert ("Browser does not support HTTP Request");return;}
	
	
	/*var newSecc = document.getElementById('newSecc').value;

	if(newSecc == "") {

		alert('Capture el nombre de la sección');
		return false;
	}*/
	
	
	
    var url = 'ajax/configAction.jsp';
	var parameters = 	"METHOD=UPDATE_REPORT" + 
						"&nomReport=" + nomReport +
						"&descReport=" + descReport +
						"&descRFC=" + descRFC +
						"&descPais=" + descPais +
						"&saltoPag=" + saltoPag
						;
	

    ajaxRequest.open("post", url, true);
    ajaxRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    ajaxRequest.onreadystatechange=function(){
    
	    if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
	
		    var response = ajaxRequest.responseText;
		    //alert(response);
		    //reloadContent();
	      } 
    };    
    ajaxRequest.send(parameters);
}


function newSection() {
	
	//alert('Hi');
	
    var ajaxRequest = simpleAjaxUtil_getXmlHttpObject();     
	
    if (ajaxRequest==null) {alert ("Browser does not support HTTP Request");return;}
	
	
	var newSecc = document.getElementById('newSecc').value;

	if(newSecc == "") {

		alert('Capture el nombre de la sección');
		return false;
	}
	
	
    var url = 'ajax/configAction.jsp';
	var parameters = 	"METHOD=NEW_SECCION&" + 
						"newSecc=" + newSecc;
	

    ajaxRequest.open("post", url, true);
    ajaxRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    ajaxRequest.onreadystatechange=function(){
    
	    if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
	
		    var response = ajaxRequest.responseText;
		    //alert(response);
		    reloadContent();
	      } 
    };    
    ajaxRequest.send(parameters);
}



function updateSection(seccId, seccName) {
	
	//alert('Hi');
	//alert(seccId);
	//alert(seccName);
	
    var ajaxRequest = simpleAjaxUtil_getXmlHttpObject();     
	
    if (ajaxRequest==null) {alert ("Browser does not support HTTP Request");return;}
	
	/*
	var newSecc = document.getElementById('newSecc').value;

	if(newSecc == "") {

		alert('Capture el nombre de la sección');
		return false;
	}
	*/
	
    var url = 'ajax/configAction.jsp';
	var parameters = 	"METHOD=UPDATE_SECCION" + 
						"&seccId=" + seccId +
						"&seccName=" + seccName;
	

    ajaxRequest.open("post", url, true);
    ajaxRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    ajaxRequest.onreadystatechange=function(){
    
	    if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
	
		    var response = ajaxRequest.responseText;
		    //alert(response);
		    //reloadContent();
	      } 
    };    
    ajaxRequest.send(parameters);
}



function deleteSection(idSecc) {
	
	//alert('Hi');
	
	//var confirmation = confirm('Seguro de que desea borrar la secci&oacute;n?');
	
	//if(!confirmation) {
		
	//	return false;
	//}
	swal({   title: "Confirmar",   
		 text: '¿Está seguro de borrar esta sección?',   
		 type: "warning",   
		 showCancelButton: true,   
		 confirmButtonColor: "#DD6B55",   
		 confirmButtonText: "Si",   
		 closeOnConfirm: true }, 
		 function(){  deleteSectionAjax(idSecc); });
}

function deleteSectionAjax(idSecc)
{	
    var ajaxRequest = simpleAjaxUtil_getXmlHttpObject();     
	
    if (ajaxRequest==null) {alert ("Browser does not support HTTP Request");return;}
	
	var url = 'ajax/configAction.jsp';
	var parameters = 	"METHOD=DELETE_SECCION&" + 
						"idSecc=" + idSecc;
	

    ajaxRequest.open("post", url, true);
    ajaxRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    ajaxRequest.onreadystatechange=function(){
    
	    if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
	
		    var response = ajaxRequest.responseText;
		    //alert(response);
		    reloadContent();
	      } 
    };    
    ajaxRequest.send(parameters);
}



function deleteRow(idRow) {
	
	//alert('Hi');
	
	
	//var confirmation = confirm('Seguro de que desea borrar la fila?');
	
	//if(!confirmation) {
		
		//return false;
	//}
	
	swal({   title: "Confirmar",   
		 text: '¿Está seguro de borrar esta fila?',   
		 type: "warning",   
		 showCancelButton: true,   
		 confirmButtonColor: "#DD6B55",   
		 confirmButtonText: "Si",   
		 closeOnConfirm: true }, 
		 function(){  deleteFilaAjax(idRow); });
}

function deleteFilaAjax(idRow){
    var ajaxRequest = simpleAjaxUtil_getXmlHttpObject();     
	
    if (ajaxRequest==null) {alert ("Browser does not support HTTP Request");return;}
	
	var url = 'ajax/configAction.jsp';
	var parameters = 	"METHOD=DELETE_ROW&" + 
						"idRow=" + idRow;
	

    ajaxRequest.open("post", url, true);
    ajaxRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    ajaxRequest.onreadystatechange=function(){
    
	    if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
	
		    var response = ajaxRequest.responseText;
		    //alert(response);
		    reloadContent();
	      } 
    };    
    ajaxRequest.send(parameters);
}



function newRow(idSecc, fieldNum) {
	
	//alert('Hi');
	
    var ajaxRequest = simpleAjaxUtil_getXmlHttpObject();     
	
    if (ajaxRequest==null) {alert ("Browser does not support HTTP Request");return;}
	
	var url = 'ajax/configAction.jsp';
	var parameters = 	"METHOD=NEW_ROW&" + 
						"idSecc=" + idSecc + "&fieldNum=" + fieldNum;
	

    ajaxRequest.open("post", url, true);
    ajaxRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    ajaxRequest.onreadystatechange=function(){
    
	    if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
	
		    var response = ajaxRequest.responseText;
		    //alert(response);
		    reloadContent();
	      } 
    };    
    ajaxRequest.send(parameters);
}

function reloadContent() {
	
	//alert('reloadContent');
	
    var ajaxRequest = simpleAjaxUtil_getXmlHttpObject();     
	if (ajaxRequest==null) {alert ("Browser does not support HTTP Request");return;}
	
	var url = 'ajax/configReportContent.jsp';
	var parameters = 	"";

    ajaxRequest.open("post", url, true);
    ajaxRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    ajaxRequest.onreadystatechange=function(){
    
	    if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
	
		    var response = ajaxRequest.responseText;
		    
		    //alert(response);
		    
		    document.getElementById('configContent').innerHTML = response;
		    
	      } 
    };    
    ajaxRequest.send(parameters);
}


function changeField(fieldId) {
	
	//alert('reloadContent');
	
    var ajaxRequest = simpleAjaxUtil_getXmlHttpObject();     
	if (ajaxRequest==null) {alert ("Browser does not support HTTP Request");return;}
	
	var url = 'ajax/configFieldContent.jsp';
	var parameters = 	"fieldId=" + fieldId;

    ajaxRequest.open("post", url, true);
    ajaxRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    ajaxRequest.onreadystatechange=function(){
    
	    if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
	
		    var response = ajaxRequest.responseText;
		    
		    //alert(response);
		    
		    document.getElementById('detalleComponente').innerHTML = response;
		    
		    selectSeccionChange();
		    
	      } 
    };    
    ajaxRequest.send(parameters);
}


function selectSeccionChange() {
	
    var ajaxRequest = simpleAjaxUtil_getXmlHttpObject();     
	if (ajaxRequest==null) {alert ("Browser does not support HTTP Request");return;}
	
	var fieldId = document.getElementById('fieldId').value;
	var seccionId = document.getElementById('selectSeccion').value;

	var url = 'ajax/configFieldContentSubSecc.jsp';
	var parameters = 	"seccionId=" + seccionId + 
						"&fieldId=" + fieldId;

    ajaxRequest.open("post", url, true);
    ajaxRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    ajaxRequest.onreadystatechange=function(){
    
	    if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
	
		    var response = ajaxRequest.responseText;
		    
		    //alert(response);
		    
		    //alert('selectSeccionChange response');
		    
		    document.getElementById('detalleSelectSubSeccion').innerHTML = response;
		    
		    searchCampos();	
	      } 
    };    
    ajaxRequest.send(parameters);	
}


function selectSubSeccionChange() {
	
	searchCampos();	
}











function searchCampos() {
	
    var ajaxRequest = simpleAjaxUtil_getXmlHttpObject();     
	if (ajaxRequest==null) {alert ("Browser does not support HTTP Request");return;}
	
	var seccionId = document.getElementById('selectSeccion').value;
	var subSeccionId = document.getElementById('selectSubSeccion').value;
	var filter = document.getElementById('filterSearch').value;
	
	
	var url = 'ajax/commonFieldTable.jsp';
	var parameters = 	"seccionId=" + seccionId + 
						"&subSeccionId=" + subSeccionId + 
						"&filter=" + filter + 
						"&flexTabs=SI&flexColumns=NO";

    ajaxRequest.open("post", url, true);
    ajaxRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    ajaxRequest.onreadystatechange=function(){
    
	    if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
	
		    var response = ajaxRequest.responseText;
		    
		    //alert(response);
		    
		    //alert('searchCampos response');
		    
		    document.getElementById('detalleSelectCampo').innerHTML = response;
		    
	      } 
    };    
    ajaxRequest.send(parameters);	
}




function selectCampo(id) {

    var ajaxRequest = simpleAjaxUtil_getXmlHttpObject();     
	
    if (ajaxRequest==null) {alert ("Browser does not support HTTP Request");return;}
	
    
    var reportFlexFieldId = document.getElementById('fieldId').value;
    var appFlexFieldIf = id;
    
	var url = 'ajax/configAction.jsp';
	var parameters = 	"METHOD=UPDATE_FIELD&" + 
						"reportFlexFieldId=" + reportFlexFieldId + 
						"&appFlexFieldIf=" + appFlexFieldIf;
	

    ajaxRequest.open("post", url, true);
    ajaxRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    ajaxRequest.onreadystatechange=function(){
    
	    if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
	
		    var response = ajaxRequest.responseText;
		    //alert(response);
		    reloadContent();
		    
		    //alert('Se aplico correctamente el cambio!');
		    //document.getElementById('detalleComponente').innerHTML = '';
		    
		    document.getElementById('detalleComponente').innerHTML = '<h2 style=color:#32CD32;>¡Se aplico correctamente el cambio!</h2>';
		    //mensajeExito();
		    
	      } 
    };    
    ajaxRequest.send(parameters);
}

function mensajeExito(){
	swal("Se aplico correctamente el cambio!");
}





function openFlexColsPupUp(flexReportFieldId, flexTableId) {

    var left = screen.width - ((screen.width - 300) / 2);
    var top = (screen.height - 300) / 2;  // for 25% - devide by 4  |  for 33% - devide by 3
    
    
    

	newwindow=window.open('configReportFlexColsPupUp.jsp?flexReportFieldId=' + flexReportFieldId + '&flexTableId=' + flexTableId,
						'name',
						'height=500,width=400,toolbar=0,menubar=0,location=0,status=0,scrollbars=1,resizable=1,left=' + left + ',top=' + top);

	if (window.focus) {newwindow.focus()}

	return false;

}



</script>
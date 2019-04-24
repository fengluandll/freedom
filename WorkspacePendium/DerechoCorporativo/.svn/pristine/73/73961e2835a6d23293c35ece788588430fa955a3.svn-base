//Se recupero la revision 709 el 20 de Junio
var globalFlexTableId;
var alterFlexTableId = '';

//Nava
/*
function saveCheckBox(checkBoxObject) {
	
    var ajaxRequest = simpleAjaxUtil_getXmlHttpObject();     
	
    if (ajaxRequest==null) {alert ("Browser does not support HTTP Request");return;}
	
    var varCode = checkBoxObject.name;
    var varId   = checkBoxObject.id;
    var varValue = (checkBoxObject.checked)? checkBoxObject.name : "";
    
    var url = 'ajax/saveOneField.jsp';
	var parameters = "CODE=" + varCode + "&VALUE=" + varValue;

    ajaxRequest.open("post", url, true);
    ajaxRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    ajaxRequest.onreadystatechange=function(){
    
	    if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){

		    var response = ajaxRequest.responseText;
		   
	      } 
    };    
    ajaxRequest.send(parameters);
}
*/

function setFlexTableId(paramId) {
	
	globalFlexTableId = paramId;
}

function setAlterFlexTableId(paramId) {
	
	alterFlexTableId = paramId;
}

function getAlterOrGlobalFlexId() {
	
	if(alterFlexTableId != '') {
		
		return alterFlexTableId;
	} else {
		
		return globalFlexTableId;
	}
}


function debugGlobalVars(moment) {
	
	alert('Moment: ' + moment);
	alert('globalFlexTableId: ' + globalFlexTableId);
	alert('alterFlexTableId: ' + alterFlexTableId);
}


function loadFlexTabIni(idFlexTab) {
	
	//alert(idFlexTab);
	
    var ajaxRequest = simpleAjaxUtil_getXmlHttpObject();     
	
    if (ajaxRequest==null) {alert ("Browser does not support HTTP Request");return;}
	
	
	//var empresa = document.getElementById('empresa').value;
	//var concepto = document.getElementById('concepto').value;
	//var criterio = document.getElementById('criterio').value;
	
	
	
	//document.getElementById('flexTable_' + idFlexTab).innerHTML = "<center>Buscando ... <img src='img/esperar.gif'></center>";
	
    var url = 'ajax/flexTable.jsp';
	var parameters = 	"idFlexTab=" + idFlexTab;
	

    ajaxRequest.open("post", url, true);
    ajaxRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    ajaxRequest.onreadystatechange=function(){
    
	    if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){

			//alert('Response');

		    var response = ajaxRequest.responseText;
		    document.getElementById('flexTable_' + idFlexTab).innerHTML = response;
		    
		    //document.getElementById('flexTable_' + idFlexTab).style.display = '';
		    //document.getElementById('flexTableInnerForm_' + idFlexTab).style.display = 'none';
		    
		    
		    //$('.formBox_'+globalFlexTableId).slideToggle();
			//$('.tableBox_'+globalFlexTableId).slideToggle('slow');
			
	      } 
		  /*else {
			  alert(ajaxRequest.readyState);
			  alert(ajaxRequest.status);
			}*/
    };    
    for(i=0;i<30;i++){
    	if($( "#subPanel"+i )){
    		var estile = $( "#subPanel"+i ).attr("style");
    		
    		if(estile!="display:none"){
    			$( "#subPanel"+i ).click();
        		break;
    		}
    		
    	}
    }
  
    
    ajaxRequest.send(parameters);
}



function loadFlexTabFilter(idFlexTab, filter) {
	
	
	//alert('loadFlexTabFilter');
	
	
	
    var ajaxRequest = simpleAjaxUtil_getXmlHttpObject();     
	
    if (ajaxRequest==null) {alert ("Browser does not support HTTP Request");return;}
	
	
	//var empresa = document.getElementById('empresa').value;
	//var concepto = document.getElementById('concepto').value;
	//var criterio = document.getElementById('criterio').value;
	
	
	
	//document.getElementById('flexTable_' + idFlexTab).innerHTML = "<center>Buscando ... <img src='img/esperar.gif'></center>";
    
    var url = 'ajax/flexTable.jsp';
	var parameters = 	"idFlexTab=" + idFlexTab + "&filter=" + filter;
	
	
    ajaxRequest.open("post", url, true);
    ajaxRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    ajaxRequest.onreadystatechange=function(){
    
	    if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
	
			//alert('Response');
	    
		    var response = ajaxRequest.responseText;
		    
		    document.getElementById('flexTable_' + idFlexTab).innerHTML = response;
		    
		    //document.getElementById('flexTable_' + idFlexTab).style.display = '';
		    //document.getElementById('flexTableInnerForm_' + idFlexTab).style.display = 'none';
		    
		    
		    //$('.formBox_'+globalFlexTableId).slideToggle();
			//$('.tableBox_'+globalFlexTableId).slideToggle('slow');
			
	      } 
		  /*else {
			  alert(ajaxRequest.readyState);
			  alert(ajaxRequest.status);
			}*/
    };    
    ajaxRequest.send(parameters);
}


function saveCheckBoxAndLoadFlexTable(checkBoxObject, idFlexTab) {
	
	//alert('saveCheckBoxAndLoadFlexTable');
	
    var ajaxRequest = simpleAjaxUtil_getXmlHttpObject();     
	
    if (ajaxRequest==null) {alert ("Browser does not support HTTP Request");return;}
	
    
    
    var varCode = checkBoxObject.name;
    var varId   = checkBoxObject.id;
    //alert(varId);
    var varValue = (checkBoxObject.checked)? checkBoxObject.name : "";
    
    /*
    var varSelect = '';
    if (varCode=='C1030'){
    	varSelect = 'DIVC24';
    }else {
    	varSelect = 'DIVC25';
    }
    
    
    if (varValue==varId){
    	//alert('Visible:'+varSelect);
    	document.getElementById(varSelect).style.display = "block";
    }else{
    	//alert('Escondido:'+varSelect);
    	document.getElementById(varSelect).style.display = "none";
    }
    */
    
    var url = 'ajax/saveOneField.jsp';
	var parameters = "CODE=" + varCode + "&VALUE=" + varValue;
	

    ajaxRequest.open("post", url, true);
    ajaxRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    ajaxRequest.onreadystatechange=function(){
    
	    if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
	
			//alert('Response');

		    var response = ajaxRequest.responseText;
		    
		    loadFlexTab(idFlexTab, '');
		    
		    
		    
		    
		    //$('.formBox_'+globalFlexTableId).slideToggle();
			//$('.tableBox_'+globalFlexTableId).slideToggle('slow');
			
	      } 
		  /*else {
			  alert(ajaxRequest.readyState);
			  alert(ajaxRequest.status);
			}*/
    };    
    ajaxRequest.send(parameters);
}


function loadFlexTab(idFlexTab, message) {
	
	//QUITAR
	//alert('loadFlexTab');
	
	//TESTING-TEMP
	enableChecks();
	
    var ajaxRequest = simpleAjaxUtil_getXmlHttpObject();     
	
    if (ajaxRequest==null) {alert ("Browser does not support HTTP Request");return;}
	
	
	//var empresa = document.getElementById('empresa').value;
	//var concepto = document.getElementById('concepto').value;
	//var criterio = document.getElementById('criterio').value;
	
	
	
	//document.getElementById('flexTable_' + idFlexTab).innerHTML = "<center>Buscando ... <img src='img/esperar.gif'></center>";
	
    var url = 'ajax/flexTable.jsp';
	var parameters = 	"idFlexTab=" + idFlexTab;
	

    ajaxRequest.open("post", url, true);
    ajaxRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    ajaxRequest.onreadystatechange=function(){
    
	    if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
	
			//alert('Response');

		    var response = ajaxRequest.responseText;
		    document.getElementById('flexTable_' + idFlexTab).innerHTML = response + message;
		    
		    document.getElementById('flexTable_' + idFlexTab).style.display = '';
		    document.getElementById('flexTableInnerForm_' + idFlexTab).style.display = 'none';
		    
		    //QUITAR
		    //alert('Response de loadFlexTab');
		    
		    
		    
		    //$('.formBox_'+globalFlexTableId).slideToggle();
			//$('.tableBox_'+globalFlexTableId).slideToggle('slow');
			
	      } 
		  /*else {
			  alert(ajaxRequest.readyState);
			  alert(ajaxRequest.status);
			}*/
    };    
    ajaxRequest.send(parameters);
}










function closeCurrentAndLoadFlexTabForm(idFlexTab, paramIds) {
	
	//QA
	//
	//alert('closeCurrentAndLoadFlexTab');
	
	
	
	document.getElementById('btnGuardar').style.display = '';
	
	
	//
	//QA
	//alert('enableChecks');
	
	//TESTING-TEMP
	enableChecks();
	
	
	//QA
	//alert('enableChecks OK');
	
    var ajaxRequest = simpleAjaxUtil_getXmlHttpObject();     
	
    if (ajaxRequest==null) {alert ("Browser does not support HTTP Request");return;}
	
	
	//var empresa = document.getElementById('empresa').value;
	//var concepto = document.getElementById('concepto').value;
	//var criterio = document.getElementById('criterio').value;
	
	
	
	//document.getElementById('flexTable_' + idFlexTab).innerHTML = "<center>Buscando ... <img src='img/esperar.gif'></center>";
	
    var url = 'ajax/flexTable.jsp';
	var parameters = 	"idFlexTab=" + globalFlexTableId;
	

    ajaxRequest.open("post", url, true);
    ajaxRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    ajaxRequest.onreadystatechange=function(){
    
	    if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
	
	    	
	    	//QA
	    	//alert('Response');
			//alert(globalFlexTableId);
		    
			
			var response = ajaxRequest.responseText;
		    
		    //alert(document.getElementById('flexTable_' + globalFlexTableId));
		    //alert(document.getElementById('flexTableInnerForm_' + globalFlexTableId));
		    
		    if(document.getElementById('flexTable_' + globalFlexTableId) != null){
		    	
		    	//QA
		    	//alert('One');
		    
		    	document.getElementById('flexTable_' + globalFlexTableId).innerHTML = response;
		    
		    	document.getElementById('flexTable_' + globalFlexTableId).style.display = '';
		    }
		    
		    
		    if(document.getElementById('flexTableInnerForm_' + globalFlexTableId) != null) {
		    	
		    	//QA
		    	//alert('Dos');
		    	
		    	document.getElementById('flexTableInnerForm_' + globalFlexTableId).style.display = 'none';
		    }

		    //QUITAR
		    //alert('Response de loadFlexTab');
		    
	    	//QA
	    	//alert('idFlexTab:' + idFlexTab);
		    
		    setFlexTableId(idFlexTab);
		    
		    //
		    //QA
		    //
		    //alert('globalFlexTableId:' + globalFlexTableId);
		    
		    //QA
		    //
		    //alert('paramIds:' + paramIds);
		    
		    loadFlexTableForm(paramIds);
		    
		    
		    //$('.formBox_'+globalFlexTableId).slideToggle();
			//$('.tableBox_'+globalFlexTableId).slideToggle('slow');
			
	      } 
		  /*else {
			  alert(ajaxRequest.readyState);
			  alert(ajaxRequest.status);
			}*/
    };    
    ajaxRequest.send(parameters);
}

function nuevo_fn(idFlexTab, paramIds){
	
	document.getElementById('btnGuardar').style.display = '';

	enableChecks();
/*
		    if(document.getElementById('flexTable_' + globalFlexTableId) != null){

		    	document.getElementById('flexTable_' + globalFlexTableId).style.display = 'none';
		    }

		    if(document.getElementById('flexTableInnerForm_' + globalFlexTableId) != null) {

		    	document.getElementById('flexTableInnerForm_' + globalFlexTableId).style.display = 'block';
		    }
*/
		    setFlexTableId(idFlexTab);
		    
		    loadFlexTableForm(paramIds);
			
	       
	
}

// 20 Octubre 2016
// Cambiar la forma de abrir la captura de flex al editar un registro.
function editar_fn(idFlexTab, paramIds){
	setFlexTableId(idFlexTab);
	loadFlexTableForm(paramIds);
}

function deleteForm(key,globalFlexTableId){
	var url = 'ajax/flexTableFormClose.jsp';
	var ajaxRequest = simpleAjaxUtil_getXmlHttpObject(); 
	
    ajaxRequest.open("post", url, true);
    ajaxRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    ajaxRequest.onreadystatechange=function(){
    
    };
    ajaxRequest.send("key="+key+"&globalFlexTableId="+globalFlexTableId);
}

function deleteEjerciciosTemp(){
	$.ajax({
		 url		:	'/DerechoCorporativo/AprobEjerSocServlet',
		 data		:	'tipoAccion=deleteAllTemp',
		 type		:	'post',
		 success	:	function(data){
		 }
	});
}

function deleteAsuntosTemp(){
	$.ajax({
		 url		:	'/DerechoCorporativo/AddAsuntoServlet',
		 data		:	'tipoAccion=deleteAllTemp',
		 type		:	'post',
		 success	:	function(data){
		 }
	});
}

function deleteAgregarOtrosTemp(){
	$.ajax({
		 url		:	'/DerechoCorporativo/AddAgregarServlet',
		 data		:	'tipoAccion=deleteAllTemp',
		 type		:	'post',
		 success	:	function(data){
		 }
	});
}

function closeForm() {
	
	//QUITAR
	//alert('closeForm');
	
	
	//alert(globalFlexTableId);
    document.getElementById('btnGuardar').style.display = '';    
	loadFlexTab(globalFlexTableId, '');
	//document.getElementById('flexTable_' + globalFlexTableId).style.display = '';
    //document.getElementById('flexTableInnerForm_' + globalFlexTableId).style.display = 'none';
    
	//$('.formBox_'+globalFlexTableId).slideToggle();
	//$('.tableBox_'+globalFlexTableId).slideToggle('slow');
	

	
}

function closeFormAutoma() {
	//alert(globalFlexTableId);
	
    document.getElementById('btnGuardar').style.display = '';    
    enableChecks();
    document.getElementById('flexTableInnerForm_' + globalFlexTableId).style.display = 'none';
    document.getElementById('flexTable_' + globalFlexTableId).style.display = 'block';
    
}

//
// TVSA
//
function disableChecks() {

	//debug("Revisando preLoadForm");
	
	if(globalSectionId == "20") {
	
		//alert(document.getElementById('C1011').style.display);
		
		document.getElementById('C1011').style.display = 'none';
		
		//alert(document.getElementById('C1011').style.display);
		
		document.getElementById('C1012').style.display = 'none';
		document.getElementById('C1013').style.display = 'none';
		document.getElementById('C1014').style.display = 'none';
		document.getElementById('C1015').style.display = 'none';
		document.getElementById('C1016').style.display = 'none';
		document.getElementById('C1017').style.display = 'none';
	}
	
	if(globalSectionId == "19") {
		try{
		document.getElementById('C1030').style.display = 'none';
		document.getElementById('C1031').style.display = 'none';
		}catch(e){
		}
	}
	
	if(globalSectionId == "20") {
		//ECM 29 Octubre 2015
		document.getElementById('C1062').style.display = 'none';
		document.getElementById('C1063').style.display = 'none';
		
		
		document.getElementById('C1164').style.display = 'none';
		document.getElementById('C1166').style.display = 'none';
		document.getElementById('C1172').style.display = 'none';
		document.getElementById('C2013').style.display = 'none';//Argu
		
	}
}

//
//TVSA
//
function enableChecks() {

	//debug("Revisando preLoadForm");
	
	if(globalSectionId == "20") {
		document.getElementById('C1011').style.display = '';
		document.getElementById('C1012').style.display = '';
		document.getElementById('C1013').style.display = '';
		document.getElementById('C1014').style.display = '';
		document.getElementById('C1015').style.display = '';
		document.getElementById('C1016').style.display = '';
		document.getElementById('C1017').style.display = '';
	}
	
	if(globalSectionId == "19") {
	try{	
		var acf = document.getElementById('C1030').value;
		var acv = document.getElementById('C1031').value;
		document.getElementById('C1030').style.display = '';
		document.getElementById('C1031').style.display = '';
	}catch(e){
			
	}
	}
	
	if(globalSectionId == "20") {
		
		//ECM 29 Octubre 2015
		document.getElementById('C1062').style.display = '';
		document.getElementById('C1063').style.display = '';
		

		document.getElementById('C1164').style.display = '';
		document.getElementById('C1166').style.display = '';
		document.getElementById('C1172').style.display = '';
		document.getElementById('C2013').style.display = '';//Argu
	}
}

function debug(message) {
	
	document.getElementById('debugMessage').innerHTML += message;
}

function loadFlexTableForm(params) {
	//QUITAR
	//alert("loadFlexTableForm");
	
	
	//TESTING-TEMP
	disableChecks();
	
	
    var ajaxRequest2 = simpleAjaxUtil_getXmlHttpObject();     
	
    if (ajaxRequest2==null) {alert ("Browser does not support HTTP Request");return;}
	
	
    var url = 'ajax/flexTableFormAllow.jsp';
	var parameters = 	"params=" + params + 
						"&globalFlexTableId=" + globalFlexTableId +
						"&alterFlexTableId=" + alterFlexTableId
						;
	

	ajaxRequest2.open("post", url, true);
	ajaxRequest2.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    ajaxRequest2.onreadystatechange=function(){
    
	    if(ajaxRequest2.readyState == 4 && ajaxRequest2.status==200){
	

		    var response = ajaxRequest2.responseText;
		    var result = new Array ();
		    result = response.split(",");
		    if(result[0].trim() == "OK") {
		    	
		    	
		    	
		    	
		    	
		    	
		    	//TESTING-TEMP
		    	disableChecks();
		    	
		    	
		    	
		    	
		    	document.getElementById('btnGuardar').style.display = 'none';
		    	
		    	//document.getElementById('flexTable_' + globalFlexTableId).innerHTML = "";
		    	document.getElementById('flexTable_' + globalFlexTableId).style.display = 'none';
		    	
		        var ajaxRequest = simpleAjaxUtil_getXmlHttpObject();     
		    	
		        if (ajaxRequest==null) {alert ("Browser does not support HTTP Request");return;}
		    	
		    	
		    	//var empresa = document.getElementById('empresa').value;
		    	//var concepto = document.getElementById('concepto').value;
		    	//var criterio = document.getElementById('criterio').value;
		    	
		    	
		    	
		    	//document.getElementById('flexTable_' + idFlexTab).innerHTML = "<center>Buscando ... <img src='img/esperar.gif'></center>";
		    	
		        var url = 'ajax/flexTableForm.jsp';
		    	var parameters = 	"params=" + params + 
		    						"&globalFlexTableId=" + globalFlexTableId +
		    						"&alterFlexTableId=" + alterFlexTableId
		    						;
		    	

		        ajaxRequest.open("post", url, true);
		        ajaxRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		        ajaxRequest.onreadystatechange=function(){
		        
		    	    if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
		    	
		    			//alert('Response');
		    	    	
		    	    	//alert('OK;' + getAlterOrGlobalFlexId());

		    		    var response = ajaxRequest.responseText;
		    		    //document.getElementById('flexTableFormDiv').innerHTML = response;
		    		    document.getElementById('flexTableInnerForm_'+globalFlexTableId).innerHTML = response;
		    		    if(globalFlexTableId == 8){
		    		    	setAdminUnico();
		    		    }
		    		    
		    		    
		    		    document.getElementById('flexTable_' + globalFlexTableId).style.display = 'none';
		    		    document.getElementById('flexTableInnerForm_' + globalFlexTableId).style.display = '';
		    		   
		    		    document.getElementById('flexTableInnerForm_' + globalFlexTableId).scrollIntoView();
		    		    //alert('lets try');
		    		    
		    		    $.getScript('ajax/flexTableFormScript.jsp?params=' + params);
		    		    
		    		    //alert('getScript OK');
		    		    
		    		    
		    		    
		    		    //alterFlexTableId = '';
		    		    
		    		    
		    		    //$('.formBox_'+globalFlexTableId).slideToggle('slow');
		    		    //$('.tableBox_'+globalFlexTableId).slideToggle('slow');
		    		    
		    	      } 
		    		  //else {
		    			//  alert(ajaxRequest.readyState);
		    			//  alert(ajaxRequest.status);
		    		//	}
		        };    
		        ajaxRequest.send(parameters);
	    	
		    	
		    	
		    } else {
		    	swal({   title: "Acceso Denegado",   text: "El registro que intentas editar se encuentra en edici\u00f3n por: "+ result[1], type: "warning",  timer: 6000,   showConfirmButton: true });
		    }
		    
		    
		    
		    
	      } 
    };    
    ajaxRequest2.send(parameters);
    	
	
	    
}

function flexTableSave() {

	//alert('Entro: flexTableSaveInfo');
	
	
	
	document.getElementById('imgFlexTableWait').style.display = '';
	document.getElementById('flexTableformButton').style.display = 'none';
	
	//29.1 cambio NAVA no funciono completamente
	window.location.hash = ('#flexTableInnerForm_' + globalFlexTableId);
	
	document.getElementById('btnGuardar').style.display = '';
	
	flexTableSaveInfo();
	
	
	
	//document.getElementById('flexTableInnerForm_'+globalFlexTableId).innerHTML = "";
	
	
}



function flexTableSaveInfo() {

    var ajaxRequest = simpleAjaxUtil_getXmlHttpObject();     
	
    if (ajaxRequest==null) {alert ("Browser does not support HTTP Request");return;}
	
	//alert(globalFlexTableId);
    
    var idMetaRow = document.getElementById('idMetaRow_'+getAlterOrGlobalFlexId()).value;
    
	//var empresa = document.getElementById('empresa').value;
	//var concepto = document.getElementById('concepto').value;
	var fieldIds = document.getElementById('fieldIds_'+getAlterOrGlobalFlexId()).value;
	//alert('fieldIds: '+fieldIds);
	var fieldNewValues = "";
	
	var ids = fieldIds.split("|");
	for (var i in ids) {
		
		if(document.getElementById(ids[i]) != null) {
			if(document.getElementById(ids[i]).type=='radio'){
				var names = document.getElementsByName(ids[i]);
				var value = "";
				var cont  = 0;
				for(var j in names){
					if(names[j].checked){
						value = names[j].value;

						fieldNewValues += "#"+value+"#|";
						cont ++;
					}
				}
				if(cont == 0){
					fieldNewValues += "##|";
				}
			}
			else{ 
				fieldNewValues += "#" + document.getElementById(ids[i]).value + "#|";

			}
		}
	}
	
	
	//document.getElementById('flexTable_' + idFlexTab).innerHTML = "<center>Buscando ... <img src='img/esperar.gif'></center>";

	fieldNewValues = encodeURIComponent(fieldNewValues);
	
    var url = 'ajax/flexTableSave.jsp';
	var parameters = 	"fieldIds=" + fieldIds + "&fieldNewValues=" + fieldNewValues + 
						"&idMetaRow=" + idMetaRow + 
						"&flexTabId=" + getAlterOrGlobalFlexId()
						;

    ajaxRequest.open("post", url, true);
    ajaxRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    ajaxRequest.onreadystatechange=function(){
    
	    if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
	
			//alert('Response');

		    var response = ajaxRequest.responseText;
		    //document.getElementById('flexTableFormDiv').innerHTML = response;
		    
		    var message = "";
		    
		    if(response == "OK") {
		    	
		    	//alert('Se guardaron los datos Correctamente');
		    	
		    	//showGrowl("OK", "Se guardaron los datos Correctamente");
		    	
		    	message = "<span class='infoMessageText' align='center'>Se guardaron los datos Correctamente</span>";
		    	
		    } else {
		    	
		    	//alert(response)
		    	
		    	message = "<span class='errorMessageText' align='center'>" + response + "</span>";
		    	
		    	//showGrowl("ERROR", response);
		    }
		    
		    
		    loadFlexTab(globalFlexTableId, message);
		    
		    
		    if(globalFlexTableId == 7) {
		    	setCamposCapitalSocial();
		    }
		    
	      } 
		  //else {
			//  alert(ajaxRequest.readyState);
			//  alert(ajaxRequest.status);
		//	}
    };    
    ajaxRequest.send(parameters);
    
    
}

//ECM 31 Agosto 2015
function setCamposCapitalSocial(){
	//alert('setCamposCapitalSocial');
	
	var ajaxRequest = simpleAjaxUtil_getXmlHttpObject();
	if (ajaxRequest==null) {alert ("Browser does not support HTTP Request");return;}
    var url = 'ajax/setCamposCapitalSocial.jsp';
    var parameters = "flexTabId=7"
	ajaxRequest.open("post", url, true);
    ajaxRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");

    ajaxRequest.onreadystatechange=function(){
	    if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
		    var response = ajaxRequest.responseText;
		    var values = response.split("|");
		    //alert(String(values[0])+' '+String(values[1])+' '+String(values[2])) ;
		    
		    var vCS = Number(String(values[0]));
		    var vCF = Number(String(values[1]));
		    var vCV = Number(String(values[2]));
		    var vEC = String(values[3]);
		    
//		    alert(vCS);
		    vCS = perform(vCS);
		    vCF = perform(vCF);
		    vCV = perform(vCV);
		    
		    //document.getElementById('C42').value = performOnBlur(vCS); 
		    //document.getElementById('C1028').value = performOnBlur(vCF); 
		    //document.getElementById('C1029').value = performOnBlur(vCV);
		    document.getElementById('C42').value = vCS; 
		    document.getElementById('C1028').value = vCF; 
		    document.getElementById('C1029').value = vCV;
		    //document.getElementById('C1033').value = vEC;		// NAVA -  Se comenta debido a: 87	CAPTURA/CONSULTA	ESTRUCTURA DE CAPITAL SOCIAL	La fecha "Estructura de capital al:" , no debe actualizarse para poder capturarla de forma manual.

	    }
    };
	ajaxRequest.send(parameters);
}

/*ECM 09 Marzo 2016 --Verificar que la escritura no exista en la tabla de apoderados.
 *  Modificar deleteFlexRow(key)
 *  Agregar   verificarEscritura(), borrarRegistroFlex() . 
 */
function deleteFlexRow(key, params) {
	
	var ajaxRequest2 = simpleAjaxUtil_getXmlHttpObject();     
    if (ajaxRequest2==null) {alert ("Browser does not support HTTP Request");return;}
	
    var url = 'ajax/flexTableFormAllow.jsp';
	var parameters = 	"params=" + params + 
						"&globalFlexTableId=" + globalFlexTableId +
						"&alterFlexTableId=" + alterFlexTableId;
	
	ajaxRequest2.open("post", url, true);
	ajaxRequest2.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    ajaxRequest2.onreadystatechange=function(){
    
	    if(ajaxRequest2.readyState == 4 && ajaxRequest2.status==200){

		    var response = ajaxRequest2.responseText;

		    var result = new Array ();
		    result = response.split(",");
		    if(result[0].trim() == "OK") {


		        if (globalFlexTableId == 17
		            || globalFlexTableId == 18
		            ){
		        	verificarEscritura(key);
		        }else{
		        	borrarRegistroFlex(key);
		        }


		    } else {
		    	swal({   title: "Acceso Denegado",   text: "El registro que intentas eliminar se encuentra en edici\u00f3n por: "+ result[1], type: "warning",  timer: 6000,   showConfirmButton: true });
		    }		    
	      } 
    };    
    ajaxRequest2.send(parameters);
    

}

function verificarEscritura(key){

    var ajaxRequest = simpleAjaxUtil_getXmlHttpObject();

    if (ajaxRequest==null) {alert ("Browser does not support HTTP Request");return;}

    var idMetaRow = key;

    var url = 'ajax/flexTableVerificarEscritura.jsp';
	var parameters = 	"idMetaRow=" + idMetaRow+
	                    "&flexTabId=" + globalFlexTableId
						;

    ajaxRequest.open("post", url, true);
    ajaxRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    ajaxRequest.onreadystatechange=function(){

	    if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){

		    var response = ajaxRequest.responseText;

		    var message = "";

		    if(response == "OK") {

		    	message = "<span class='infoMessageText' align='center'>Se elimin&oacute; el registro correctamente</span>";

		    } else {

		    	message = "<span class='errorMessageText' align='center'>" + response + "</span>";
		    }

		    loadFlexTab(globalFlexTableId, message);

	      }
    };    
    ajaxRequest.send(parameters);

}
function borrarRegistroFlex(key){
    var ajaxRequest = simpleAjaxUtil_getXmlHttpObject();
	
    if (ajaxRequest==null) {alert ("Browser does not support HTTP Request");return;}
	
    
    var idMetaRow = key;
    
	//document.getElementById('flexTable_' + idFlexTab).innerHTML = "<center>Buscando ... <img src='img/esperar.gif'></center>";
	
    var url = 'ajax/flexTableDelete.jsp';
	var parameters = 	"idMetaRow=" + idMetaRow+
	                    "&flexTabId=" + globalFlexTableId
						;
	
    ajaxRequest.open("post", url, true);
    ajaxRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    ajaxRequest.onreadystatechange=function(){
    
	    if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
	
			//alert('Response');

		    var response = ajaxRequest.responseText;
		    //document.getElementById('flexTableFormDiv').innerHTML = response;
		    
		    var message = "";
		    
		    if(response == "OK") {
		    	
		    	//alert('Se eliminó el registro correctamente');
		    	
		    	//showGrowl("OK", "Se elimino el registro correctamente");
		    	
		    	message = "<span class='infoMessageText' align='center'>Se elimin&oacute; el registro correctamente</span>";
		    	
		    } else {
		    	
		    	//alert(response)
		    	//showGrowl("ERROR", response);
		    	
		    	message = "<span class='errorMessageText' align='center'>" + response + "</span>";
		    }
		    
		    
		    loadFlexTab(globalFlexTableId, message);
		    
		    //ECM 31 AGOSTO 2015
		    setCamposCapitalSocial();
		    
		    
	      } 
		  //else {
			//  alert(ajaxRequest.readyState);
			//  alert(ajaxRequest.status);
		//	}
    };    
    ajaxRequest.send(parameters);

}

/*
function copyDateValue(fromId, toId) {

	  document.getElementById(toId).value = document.getElementById(fromId).value;
}
*/


function storeMultipleValues(listId, textId) {

	  var options = document.getElementById(listId) && document.getElementById(listId).options;
	  
	  var opt;
	
	  document.getElementById(textId).value = "";
	
	  for (var i=0, iLen=options.length; i<iLen; i++) {
	    opt = options[i];
	
	    if (opt.selected) {
	
			if(document.getElementById(textId).value != "") {
	
				document.getElementById(textId).value = document.getElementById(textId).value + ",";
			}
	    	document.getElementById(textId).value = document.getElementById(textId).value + opt.value;
	    }
	  }
}

//ECM 31 AGOSTO 2015
function perform(e){
	//e = t(e);
	e = accounting.formatMoney(e);
	e = e.replace(/^\s+|\s+$/g,"");
	return e;

}

function t(st) {
	alert('st.replace');
	return st.replace(/^\s+|\s+$/g,"");
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


function isChecked(element){
	if(element.checked){
		element.value = 'Si';
	}else{
		element.value='No'
	}
}

	//ECM 26 Enero 2016 Agregar reglas de negocio a los checks de Status en Reformas
	function validarStatus(element){
		
		//alert('validarStatus globalFlexTableId: ' + globalFlexTableId);
		
		
		var idCheck = element.id;

	    if(getAlterOrGlobalFlexId() == 20){
	    	if(idCheck == '20__VAL_C38'){
	        	if(element.checked){
	        		document.getElementById('20__VAL_C41').checked = false;
	        		document.getElementById('20__VAL_C44').checked = false;
	        		document.getElementById('20__VAL_C47').checked = false;
	        		document.getElementById('20__VAL_C50').checked = false;
	        		document.getElementById('20__VAL_C53').checked = false;
	        		
	        		document.getElementById('20__VAL_C41').value = 'No';
	        		document.getElementById('20__VAL_C44').value = 'No';
	        		document.getElementById('20__VAL_C47').value = 'No';
	        		document.getElementById('20__VAL_C50').value = 'No';
	        		document.getElementById('20__VAL_C53').value = 'No';

	        	}
	    	}else if(idCheck == '20__VAL_C41'){
	    		if(element.checked){
	        		document.getElementById('20__VAL_C38').checked = false;
	        		document.getElementById('20__VAL_C44').checked = false;
	        		document.getElementById('20__VAL_C47').checked = false;
	        		document.getElementById('20__VAL_C50').checked = false;
	        		document.getElementById('20__VAL_C53').checked = false;
	        		
	        		document.getElementById('20__VAL_C38').value = 'No';
	        		document.getElementById('20__VAL_C44').value = 'No';
	        		document.getElementById('20__VAL_C47').value = 'No';
	        		document.getElementById('20__VAL_C50').value = 'No';
	        		document.getElementById('20__VAL_C53').value = 'No';

	        		
	    		}
	    	}else if(idCheck == '20__VAL_C44'){
	    		if(element.checked){
	        		document.getElementById('20__VAL_C38').checked = false;
	        		document.getElementById('20__VAL_C41').checked = false;
	        		document.getElementById('20__VAL_C47').checked = false;
	        		document.getElementById('20__VAL_C50').checked = false;
	        		document.getElementById('20__VAL_C53').checked = false;
	        		
	        		document.getElementById('20__VAL_C38').value = 'No';
	        		document.getElementById('20__VAL_C41').value = 'No';
	        		document.getElementById('20__VAL_C47').value = 'No';
	        		document.getElementById('20__VAL_C50').value = 'No';
	        		document.getElementById('20__VAL_C53').value = 'No';

	        		
	    		}
	    	}else if(idCheck == '20__VAL_C47'){
	    		if(element.checked){
	        		document.getElementById('20__VAL_C38').checked = false;
	        		document.getElementById('20__VAL_C44').checked = false;
	        		document.getElementById('20__VAL_C41').checked = false;
	        		document.getElementById('20__VAL_C50').checked = false;
	        		document.getElementById('20__VAL_C53').checked = false;
	        		
	        		document.getElementById('20__VAL_C38').value = 'No';
	        		document.getElementById('20__VAL_C44').value = 'No';
	        		document.getElementById('20__VAL_C41').value = 'No';
	        		document.getElementById('20__VAL_C50').value = 'No';
	        		document.getElementById('20__VAL_C53').value = 'No';

	        		
	    		}
	    	}else if(idCheck == '20__VAL_C50'){
	    		if(element.checked){
	        		document.getElementById('20__VAL_C38').checked = false;
	        		document.getElementById('20__VAL_C44').checked = false;
	        		document.getElementById('20__VAL_C47').checked = false;
	        		document.getElementById('20__VAL_C41').checked = false;
	        		document.getElementById('20__VAL_C53').checked = false;
	        		
	        		document.getElementById('20__VAL_C38').value = 'No';
	        		document.getElementById('20__VAL_C44').value = 'No';
	        		document.getElementById('20__VAL_C47').value = 'No';
	        		document.getElementById('20__VAL_C41').value = 'No';
	        		document.getElementById('20__VAL_C53').value = 'No';
	        		
	    		}
	    	}else if(idCheck == '20__VAL_C53'){
	    		if(element.checked){
	        		document.getElementById('20__VAL_C38').checked = false;
	        		document.getElementById('20__VAL_C44').checked = false;
	        		document.getElementById('20__VAL_C47').checked = false;
	        		document.getElementById('20__VAL_C50').checked = false;
	        		document.getElementById('20__VAL_C41').checked = false;
	        		
	        		document.getElementById('20__VAL_C38').value = 'No';
	        		document.getElementById('20__VAL_C44').value = 'No';
	        		document.getElementById('20__VAL_C47').value = 'No';
	        		document.getElementById('20__VAL_C50').value = 'No';
	        		document.getElementById('20__VAL_C41').value = 'No';

	    		}
	    	}
	    }else if(getAlterOrGlobalFlexId() == 21){
	    	if(idCheck == '21__VAL_C29'){
	        	if(element.checked){
	        		document.getElementById('21__VAL_C32').checked = false;
	        		document.getElementById('21__VAL_C35').checked = false;
	        		document.getElementById('21__VAL_C38').checked = false;
	        		document.getElementById('21__VAL_C41').checked = false;
	        		document.getElementById('21__VAL_C44').checked = false;
	        		
	        		document.getElementById('21__VAL_C32').value = 'No';
	        		document.getElementById('21__VAL_C35').value = 'No';
	        		document.getElementById('21__VAL_C38').value = 'No';
	        		document.getElementById('21__VAL_C41').value = 'No';
	        		document.getElementById('21__VAL_C44').value = 'No';
	        		
	        	}
	    	}else if(idCheck == '21__VAL_C32'){
	    		if(element.checked){
	        		document.getElementById('21__VAL_C29').checked = false;
	        		document.getElementById('21__VAL_C35').checked = false;
	        		document.getElementById('21__VAL_C38').checked = false;
	        		document.getElementById('21__VAL_C41').checked = false;
	        		document.getElementById('21__VAL_C44').checked = false;
	        		
	        		document.getElementById('21__VAL_C29').value = 'No';
	        		document.getElementById('21__VAL_C35').value = 'No';
	        		document.getElementById('21__VAL_C38').value = 'No';
	        		document.getElementById('21__VAL_C41').value = 'No';
	        		document.getElementById('21__VAL_C44').value = 'No';

	    		}
	    	}else if(idCheck == '21__VAL_C35'){
	    		if(element.checked){
	        		document.getElementById('21__VAL_C29').checked = false;
	        		document.getElementById('21__VAL_C32').checked = false;
	        		document.getElementById('21__VAL_C38').checked = false;
	        		document.getElementById('21__VAL_C41').checked = false;
	        		document.getElementById('21__VAL_C44').checked = false;
	        		
	        		document.getElementById('21__VAL_C29').value = 'No';
	        		document.getElementById('21__VAL_C32').value = 'No';
	        		document.getElementById('21__VAL_C38').value = 'No';
	        		document.getElementById('21__VAL_C41').value = 'No';
	        		document.getElementById('21__VAL_C44').value = 'No';

	    		}
	    	}else if(idCheck == '21__VAL_C38'){
	    		if(element.checked){
	        		document.getElementById('21__VAL_C29').checked = false;
	        		document.getElementById('21__VAL_C32').checked = false;
	        		document.getElementById('21__VAL_C35').checked = false;
	        		document.getElementById('21__VAL_C41').checked = false;
	        		document.getElementById('21__VAL_C44').checked = false;
	        		
	        		document.getElementById('21__VAL_C29').value = 'No';
	        		document.getElementById('21__VAL_C32').value = 'No';
	        		document.getElementById('21__VAL_C35').value = 'No';
	        		document.getElementById('21__VAL_C41').value = 'No';
	        		document.getElementById('21__VAL_C44').value = 'No';

	    		}
	    	}else if(idCheck == '21__VAL_C41'){
	    		if(element.checked){
	        		document.getElementById('21__VAL_C29').checked = false;
	        		document.getElementById('21__VAL_C32').checked = false;
	        		document.getElementById('21__VAL_C35').checked = false;
	        		document.getElementById('21__VAL_C38').checked = false;
	        		document.getElementById('21__VAL_C44').checked = false;
	        		
	        		document.getElementById('21__VAL_C29').value = 'No';
	        		document.getElementById('21__VAL_C32').value = 'No';
	        		document.getElementById('21__VAL_C35').value = 'No';
	        		document.getElementById('21__VAL_C38').value = 'No';
	        		document.getElementById('21__VAL_C44').value = 'No';

	        		
	    		}
	    	}else if(idCheck == '21__VAL_C44'){
	    		if(element.checked){
	        		document.getElementById('21__VAL_C29').checked = false;
	        		document.getElementById('21__VAL_C32').checked = false;
	        		document.getElementById('21__VAL_C35').checked = false;
	        		document.getElementById('21__VAL_C38').checked = false;
	        		document.getElementById('21__VAL_C41').checked = false;
	        		
	        		document.getElementById('21__VAL_C29').value = 'No';
	        		document.getElementById('21__VAL_C32').value = 'No';
	        		document.getElementById('21__VAL_C35').value = 'No';
	        		document.getElementById('21__VAL_C38').value = 'No';
	        		document.getElementById('21__VAL_C41').value = 'No';

	    		}
	    	}
	    }else if(getAlterOrGlobalFlexId() == 22){
	    	if(idCheck == '22__VAL_C36'){
	        	if(element.checked){
	        		document.getElementById('22__VAL_C39').checked = false;
	        		document.getElementById('22__VAL_C42').checked = false;
	        		document.getElementById('22__VAL_C45').checked = false;
	        		document.getElementById('22__VAL_C48').checked = false;
	        		document.getElementById('22__VAL_C51').checked = false;
	        		
	        		document.getElementById('22__VAL_C39').value = 'No';
	        		document.getElementById('22__VAL_C42').value = 'No';
	        		document.getElementById('22__VAL_C45').value = 'No';
	        		document.getElementById('22__VAL_C48').value = 'No';
	        		document.getElementById('22__VAL_C51').value = 'No';

	        	}
	    	}else if(idCheck == '22__VAL_C39'){
	    		if(element.checked){
	        		document.getElementById('22__VAL_C36').checked = false;
	        		document.getElementById('22__VAL_C42').checked = false;
	        		document.getElementById('22__VAL_C45').checked = false;
	        		document.getElementById('22__VAL_C48').checked = false;
	        		document.getElementById('22__VAL_C51').checked = false;
	        		
	        		document.getElementById('22__VAL_C36').value = 'No';
	        		document.getElementById('22__VAL_C42').value = 'No';
	        		document.getElementById('22__VAL_C45').value = 'No';
	        		document.getElementById('22__VAL_C48').value = 'No';
	        		document.getElementById('22__VAL_C51').value = 'No';

	    		}
	    	}else if(idCheck == '22__VAL_C42'){
	    		if(element.checked){
	        		document.getElementById('22__VAL_C36').checked = false;
	        		document.getElementById('22__VAL_C39').checked = false;
	        		document.getElementById('22__VAL_C45').checked = false;
	        		document.getElementById('22__VAL_C48').checked = false;
	        		document.getElementById('22__VAL_C51').checked = false;
	        		
	        		document.getElementById('22__VAL_C36').value = 'No';
	        		document.getElementById('22__VAL_C39').value = 'No';
	        		document.getElementById('22__VAL_C45').value = 'No';
	        		document.getElementById('22__VAL_C48').value = 'No';
	        		document.getElementById('22__VAL_C51').value = 'No';

	    		}
	    	}else if(idCheck == '22__VAL_C45'){
	    		if(element.checked){
	        		document.getElementById('22__VAL_C36').checked = false;
	        		document.getElementById('22__VAL_C39').checked = false;
	        		document.getElementById('22__VAL_C42').checked = false;
	        		document.getElementById('22__VAL_C48').checked = false;
	        		document.getElementById('22__VAL_C51').checked = false;
	        		
	        		document.getElementById('22__VAL_C36').value = 'No';
	        		document.getElementById('22__VAL_C39').value = 'No';
	        		document.getElementById('22__VAL_C42').value = 'No';
	        		document.getElementById('22__VAL_C48').value = 'No';
	        		document.getElementById('22__VAL_C51').value = 'No';

	    		}
	    	}else if(idCheck == '22__VAL_C48'){
	    		if(element.checked){
	        		document.getElementById('22__VAL_C36').checked = false;
	        		document.getElementById('22__VAL_C39').checked = false;
	        		document.getElementById('22__VAL_C42').checked = false;
	        		document.getElementById('22__VAL_C45').checked = false;
	        		document.getElementById('22__VAL_C51').checked = false;
	        		
	        		document.getElementById('22__VAL_C36').value = 'No';
	        		document.getElementById('22__VAL_C39').value = 'No';
	        		document.getElementById('22__VAL_C42').value = 'No';
	        		document.getElementById('22__VAL_C45').value = 'No';
	        		document.getElementById('22__VAL_C51').value = 'No';

	    		}
	    	}else if(idCheck == '22__VAL_C51'){
	    		if(element.checked){
	        		document.getElementById('22__VAL_C36').checked = false;
	        		document.getElementById('22__VAL_C39').checked = false;
	        		document.getElementById('22__VAL_C42').checked = false;
	        		document.getElementById('22__VAL_C45').checked = false;
	        		document.getElementById('22__VAL_C48').checked = false;

	        		document.getElementById('22__VAL_C36').value = 'No';
	        		document.getElementById('22__VAL_C39').value = 'No';
	        		document.getElementById('22__VAL_C42').value = 'No';
	        		document.getElementById('22__VAL_C45').value = 'No';
	        		document.getElementById('22__VAL_C48').value = 'No';

	    		}
	    	}
	    }else if(getAlterOrGlobalFlexId() == 23){
	    	if(idCheck == '23__VAL_C68'){
	        	if(element.checked){
	        		document.getElementById('23__VAL_C71').checked = false;
	        		document.getElementById('23__VAL_C74').checked = false;
	        		document.getElementById('23__VAL_C77').checked = false;
	        		document.getElementById('23__VAL_C80').checked = false;
	        		document.getElementById('23__VAL_C83').checked = false;
	        		
	        		document.getElementById('23__VAL_C71').value = 'No';
	        		document.getElementById('23__VAL_C74').value = 'No';
	        		document.getElementById('23__VAL_C77').value = 'No';
	        		document.getElementById('23__VAL_C80').value = 'No';
	        		document.getElementById('23__VAL_C83').value = 'No';

	        	}
	    	}else if(idCheck == '23__VAL_C71'){
	    		if(element.checked){
	        		document.getElementById('23__VAL_C68').checked = false;
	        		document.getElementById('23__VAL_C74').checked = false;
	        		document.getElementById('23__VAL_C77').checked = false;
	        		document.getElementById('23__VAL_C80').checked = false;
	        		document.getElementById('23__VAL_C83').checked = false;
	        		
	        		document.getElementById('23__VAL_C68').value = 'No';
	        		document.getElementById('23__VAL_C74').value = 'No';
	        		document.getElementById('23__VAL_C77').value = 'No';
	        		document.getElementById('23__VAL_C80').value = 'No';
	        		document.getElementById('23__VAL_C83').value = 'No';

	    		}
	    	}else if(idCheck == '23__VAL_C74'){
	    		if(element.checked){
	        		document.getElementById('23__VAL_C68').checked = false;
	        		document.getElementById('23__VAL_C71').checked = false;
	        		document.getElementById('23__VAL_C77').checked = false;
	        		document.getElementById('23__VAL_C80').checked = false;
	        		document.getElementById('23__VAL_C83').checked = false;
	        		
	        		document.getElementById('23__VAL_C68').value = 'No';
	        		document.getElementById('23__VAL_C71').value = 'No';
	        		document.getElementById('23__VAL_C77').value = 'No';
	        		document.getElementById('23__VAL_C80').value = 'No';
	        		document.getElementById('23__VAL_C83').value = 'No';

	    		}
	    	}else if(idCheck == '23__VAL_C77'){
	    		if(element.checked){
	        		document.getElementById('23__VAL_C68').checked = false;
	        		document.getElementById('23__VAL_C71').checked = false;
	        		document.getElementById('23__VAL_C74').checked = false;
	        		document.getElementById('23__VAL_C80').checked = false;
	        		document.getElementById('23__VAL_C83').checked = false;
	        		
	        		document.getElementById('23__VAL_C68').value = 'No';
	        		document.getElementById('23__VAL_C71').value = 'No';
	        		document.getElementById('23__VAL_C74').value = 'No';
	        		document.getElementById('23__VAL_C80').value = 'No';
	        		document.getElementById('23__VAL_C83').value = 'No';

	    		}
	    	}else if(idCheck == '23__VAL_C80'){
	    		if(element.checked){
	        		document.getElementById('23__VAL_C68').checked = false;
	        		document.getElementById('23__VAL_C71').checked = false;
	        		document.getElementById('23__VAL_C74').checked = false;
	        		document.getElementById('23__VAL_C77').checked = false;
	        		document.getElementById('23__VAL_C83').checked = false;
	        		
	        		document.getElementById('23__VAL_C68').value = 'No';
	        		document.getElementById('23__VAL_C71').value = 'No';
	        		document.getElementById('23__VAL_C74').value = 'No';
	        		document.getElementById('23__VAL_C77').value = 'No';
	        		document.getElementById('23__VAL_C83').value = 'No';

	    		}
	    	}else if(idCheck == '23__VAL_C83'){
	    		if(element.checked){
	        		document.getElementById('23__VAL_C68').checked = false;
	        		document.getElementById('23__VAL_C71').checked = false;
	        		document.getElementById('23__VAL_C74').checked = false;
	        		document.getElementById('23__VAL_C77').checked = false;
	        		document.getElementById('23__VAL_C80').checked = false;

	        		document.getElementById('23__VAL_C68').value = 'No';
	        		document.getElementById('23__VAL_C71').value = 'No';
	        		document.getElementById('23__VAL_C74').value = 'No';
	        		document.getElementById('23__VAL_C77').value = 'No';
	        		document.getElementById('23__VAL_C80').value = 'No';

	    		}
	    	}
	    }else if(getAlterOrGlobalFlexId() == 17){
	    	if(idCheck == '17__VAL_C57'){
	        	if(element.checked){
	        		document.getElementById('17__VAL_C60').checked = false;
	        		document.getElementById('17__VAL_C63').checked = false;
	        		document.getElementById('17__VAL_C66').checked = false;
	        		document.getElementById('17__VAL_C69').checked = false;
	        		document.getElementById('17__VAL_C72').checked = false;

	        		document.getElementById('17__VAL_C60').value = 'No';
	        		document.getElementById('17__VAL_C63').value = 'No';
	        		document.getElementById('17__VAL_C66').value = 'No';
	        		document.getElementById('17__VAL_C69').value = 'No';
	        		document.getElementById('17__VAL_C72').value = 'No';
	        	}
	        }else if(idCheck == '17__VAL_C60'){
    		    if(element.checked){
	        		document.getElementById('17__VAL_C57').checked = false;
	        		document.getElementById('17__VAL_C63').checked = false;
	        		document.getElementById('17__VAL_C66').checked = false;
	        		document.getElementById('17__VAL_C69').checked = false;
	        		document.getElementById('17__VAL_C72').checked = false;

	        		document.getElementById('17__VAL_C57').value = 'No';
	        		document.getElementById('17__VAL_C63').value = 'No';
	        		document.getElementById('17__VAL_C66').value = 'No';
	        		document.getElementById('17__VAL_C69').value = 'No';
	        		document.getElementById('17__VAL_C72').value = 'No';

    		    }
	        }else if(idCheck == '17__VAL_C63'){
    		    if(element.checked){
	        		document.getElementById('17__VAL_C57').checked = false;
	        		document.getElementById('17__VAL_C60').checked = false;
	        		document.getElementById('17__VAL_C66').checked = false;
	        		document.getElementById('17__VAL_C69').checked = false;
	        		document.getElementById('17__VAL_C72').checked = false;

	        		document.getElementById('17__VAL_C57').value = 'No';
	        		document.getElementById('17__VAL_C60').value = 'No';
	        		document.getElementById('17__VAL_C66').value = 'No';
	        		document.getElementById('17__VAL_C69').value = 'No';
	        		document.getElementById('17__VAL_C72').value = 'No';

    		    }
	        }else if(idCheck == '17__VAL_C66'){
    		    if(element.checked){
	        		document.getElementById('17__VAL_C57').checked = false;
	        		document.getElementById('17__VAL_C60').checked = false;
	        		document.getElementById('17__VAL_C63').checked = false;
	        		document.getElementById('17__VAL_C69').checked = false;
	        		document.getElementById('17__VAL_C72').checked = false;

	        		document.getElementById('17__VAL_C57').value = 'No';
	        		document.getElementById('17__VAL_C60').value = 'No';
	        		document.getElementById('17__VAL_C63').value = 'No';
	        		document.getElementById('17__VAL_C69').value = 'No';
	        		document.getElementById('17__VAL_C72').value = 'No';

    		    }
	        }else if(idCheck == '17__VAL_C69'){
    		    if(element.checked){
	        		document.getElementById('17__VAL_C57').checked = false;
	        		document.getElementById('17__VAL_C60').checked = false;
	        		document.getElementById('17__VAL_C63').checked = false;
	        		document.getElementById('17__VAL_C66').checked = false;
	        		document.getElementById('17__VAL_C72').checked = false;

	        		document.getElementById('17__VAL_C57').value = 'No';
	        		document.getElementById('17__VAL_C60').value = 'No';
	        		document.getElementById('17__VAL_C63').value = 'No';
	        		document.getElementById('17__VAL_C66').value = 'No';
	        		document.getElementById('17__VAL_C72').value = 'No';

    		    }
	        }else if(idCheck == '17__VAL_C72'){
	        	if(element.checked){
	        		document.getElementById('17__VAL_C57').checked = false;
	        		document.getElementById('17__VAL_C60').checked = false;
	        		document.getElementById('17__VAL_C63').checked = false;
	        		document.getElementById('17__VAL_C66').checked = false;
	        		document.getElementById('17__VAL_C69').checked = false;
	
	        		document.getElementById('17__VAL_C57').value = 'No';
	        		document.getElementById('17__VAL_C60').value = 'No';
	        		document.getElementById('17__VAL_C63').value = 'No';
	        		document.getElementById('17__VAL_C66').value = 'No';
	        		document.getElementById('17__VAL_C69').value = 'No';
	        	}
	        }
	    }
///////////////////////////////////////////////////////////////////////////////
	    else if(getAlterOrGlobalFlexId() == 18){
	    	if(idCheck == '18__VAL_C57'){
	        	if(element.checked){
	        		document.getElementById('18__VAL_C60').checked = false;
	        		document.getElementById('18__VAL_C63').checked = false;
	        		document.getElementById('18__VAL_C66').checked = false;
	        		document.getElementById('18__VAL_C69').checked = false;
	        		document.getElementById('18__VAL_C72').checked = false;

	        		document.getElementById('18__VAL_C60').value = 'No';
	        		document.getElementById('18__VAL_C63').value = 'No';
	        		document.getElementById('18__VAL_C66').value = 'No';
	        		document.getElementById('18__VAL_C69').value = 'No';
	        		document.getElementById('18__VAL_C72').value = 'No';
	        	}
	        }else if(idCheck == '18__VAL_C60'){
    		    if(element.checked){
	        		document.getElementById('18__VAL_C57').checked = false;
	        		document.getElementById('18__VAL_C63').checked = false;
	        		document.getElementById('18__VAL_C66').checked = false;
	        		document.getElementById('18__VAL_C69').checked = false;
	        		document.getElementById('18__VAL_C72').checked = false;

	        		document.getElementById('18__VAL_C57').value = 'No';
	        		document.getElementById('18__VAL_C63').value = 'No';
	        		document.getElementById('18__VAL_C66').value = 'No';
	        		document.getElementById('18__VAL_C69').value = 'No';
	        		document.getElementById('18__VAL_C72').value = 'No';

    		    }
	        }else if(idCheck == '18__VAL_C63'){
    		    if(element.checked){
	        		document.getElementById('18__VAL_C57').checked = false;
	        		document.getElementById('18__VAL_C60').checked = false;
	        		document.getElementById('18__VAL_C66').checked = false;
	        		document.getElementById('18__VAL_C69').checked = false;
	        		document.getElementById('18__VAL_C72').checked = false;

	        		document.getElementById('18__VAL_C57').value = 'No';
	        		document.getElementById('18__VAL_C60').value = 'No';
	        		document.getElementById('18__VAL_C66').value = 'No';
	        		document.getElementById('18__VAL_C69').value = 'No';
	        		document.getElementById('18__VAL_C72').value = 'No';

    		    }
	        }else if(idCheck == '18__VAL_C66'){
    		    if(element.checked){
	        		document.getElementById('18__VAL_C57').checked = false;
	        		document.getElementById('18__VAL_C60').checked = false;
	        		document.getElementById('18__VAL_C63').checked = false;
	        		document.getElementById('18__VAL_C69').checked = false;
	        		document.getElementById('18__VAL_C72').checked = false;

	        		document.getElementById('18__VAL_C57').value = 'No';
	        		document.getElementById('18__VAL_C60').value = 'No';
	        		document.getElementById('18__VAL_C63').value = 'No';
	        		document.getElementById('18__VAL_C69').value = 'No';
	        		document.getElementById('18__VAL_C72').value = 'No';

    		    }
	        }else if(idCheck == '18__VAL_C69'){
    		    if(element.checked){
	        		document.getElementById('18__VAL_C57').checked = false;
	        		document.getElementById('18__VAL_C60').checked = false;
	        		document.getElementById('18__VAL_C63').checked = false;
	        		document.getElementById('18__VAL_C66').checked = false;
	        		document.getElementById('18__VAL_C72').checked = false;

	        		document.getElementById('18__VAL_C57').value = 'No';
	        		document.getElementById('18__VAL_C60').value = 'No';
	        		document.getElementById('18__VAL_C63').value = 'No';
	        		document.getElementById('18__VAL_C66').value = 'No';
	        		document.getElementById('18__VAL_C72').value = 'No';

    		    }
	        }else if(idCheck == '18__VAL_C72'){
	        	if(element.checked){
	        		document.getElementById('18__VAL_C57').checked = false;
	        		document.getElementById('18__VAL_C60').checked = false;
	        		document.getElementById('18__VAL_C63').checked = false;
	        		document.getElementById('18__VAL_C66').checked = false;
	        		document.getElementById('18__VAL_C69').checked = false;
	
	        		document.getElementById('18__VAL_C57').value = 'No';
	        		document.getElementById('18__VAL_C60').value = 'No';
	        		document.getElementById('18__VAL_C63').value = 'No';
	        		document.getElementById('18__VAL_C66').value = 'No';
	        		document.getElementById('18__VAL_C69').value = 'No';
	        	}
	        }
	    }else if(getAlterOrGlobalFlexId() == 27){
	    	if(idCheck == getAlterOrGlobalFlexId()+'__VAL_C123'){
	    		if(element.checked){
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').checked = false;
	    			
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').value = 'No';
	    		}
	    	}else if(idCheck == getAlterOrGlobalFlexId()+'__VAL_C126'){
	    		if(element.checked){
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').checked = false;
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').checked = false;
	    			
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').value = 'No';
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').value = 'No';
	    		}
	    	}else if(idCheck == getAlterOrGlobalFlexId()+'__VAL_C128'){
	    		if(element.checked){
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').checked = false;
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').checked = false;
	    			
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').value = 'No';
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').value = 'No';
	    		}
	    	}else if(idCheck == getAlterOrGlobalFlexId()+'__VAL_C131'){
	    		if(element.checked){
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').checked = false;
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').checked = false;
	    			
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').value = 'No';
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').value = 'No';
	    		}
	    	}else if(idCheck == getAlterOrGlobalFlexId()+'__VAL_C134'){
	    		if(element.checked){
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').checked = false;
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').checked = false;
	    			
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').value = 'No';
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').value = 'No';
	    		}
	    	}else if(idCheck == getAlterOrGlobalFlexId()+'__VAL_C136'){
	    		if(element.checked){
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').checked = false;
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').checked = false;
	    			
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').value = 'No';
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').value = 'No';
	    		}
	    	}
	    }else if(getAlterOrGlobalFlexId() == 28){
	    	if(idCheck == getAlterOrGlobalFlexId()+'__VAL_C123'){
	    		if(element.checked){
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').checked = false;
	    			
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').value = 'No';
	    		}
	    	}else if(idCheck == getAlterOrGlobalFlexId()+'__VAL_C126'){
	    		if(element.checked){
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').checked = false;
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').checked = false;
	    			
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').value = 'No';
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').value = 'No';
	    		}
	    	}else if(idCheck == getAlterOrGlobalFlexId()+'__VAL_C128'){
	    		if(element.checked){
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').checked = false;
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').checked = false;
	    			
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').value = 'No';
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').value = 'No';
	    		}
	    	}else if(idCheck == getAlterOrGlobalFlexId()+'__VAL_C131'){
	    		if(element.checked){
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').checked = false;
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').checked = false;
	    			
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').value = 'No';
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').value = 'No';
	    		}
	    	}else if(idCheck == getAlterOrGlobalFlexId()+'__VAL_C134'){
	    		if(element.checked){
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').checked = false;
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').checked = false;
	    			
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').value = 'No';
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').value = 'No';
	    		}
	    	}else if(idCheck == getAlterOrGlobalFlexId()+'__VAL_C136'){
	    		if(element.checked){
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').checked = false;
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').checked = false;
	    			
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').value = 'No';
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').value = 'No';
	    		}
	    	}
	    }else if(getAlterOrGlobalFlexId() == 29){
	    	if(idCheck == getAlterOrGlobalFlexId()+'__VAL_C123'){
	    		if(element.checked){
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').checked = false;
	    			
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').value = 'No';
	    		}
	    	}else if(idCheck == getAlterOrGlobalFlexId()+'__VAL_C126'){
	    		if(element.checked){
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').checked = false;
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').checked = false;
	    			
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').value = 'No';
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').value = 'No';
	    		}
	    	}else if(idCheck == getAlterOrGlobalFlexId()+'__VAL_C128'){
	    		if(element.checked){
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').checked = false;
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').checked = false;
	    			
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').value = 'No';
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').value = 'No';
	    		}
	    	}else if(idCheck == getAlterOrGlobalFlexId()+'__VAL_C131'){
	    		if(element.checked){
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').checked = false;
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').checked = false;
	    			
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').value = 'No';
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').value = 'No';
	    		}
	    	}else if(idCheck == getAlterOrGlobalFlexId()+'__VAL_C134'){
	    		if(element.checked){
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').checked = false;
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').checked = false;
	    			
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').value = 'No';
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').value = 'No';
	    		}
	    	}else if(idCheck == getAlterOrGlobalFlexId()+'__VAL_C136'){
	    		if(element.checked){
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').checked = false;
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').checked = false;
	    			
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').value = 'No';
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').value = 'No';
	    		}
	    	}
	    }else if(getAlterOrGlobalFlexId() == 30){
	    	if(idCheck == getAlterOrGlobalFlexId()+'__VAL_C123'){
	    		if(element.checked){
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').checked = false;
	    			
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').value = 'No';
	    		}
	    	}else if(idCheck == getAlterOrGlobalFlexId()+'__VAL_C126'){
	    		if(element.checked){
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').checked = false;
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').checked = false;
	    			
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').value = 'No';
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').value = 'No';
	    		}
	    	}else if(idCheck == getAlterOrGlobalFlexId()+'__VAL_C128'){
	    		if(element.checked){
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').checked = false;
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').checked = false;
	    			
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').value = 'No';
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').value = 'No';
	    		}
	    	}else if(idCheck == getAlterOrGlobalFlexId()+'__VAL_C131'){
	    		if(element.checked){
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').checked = false;
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').checked = false;
	    			
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').value = 'No';
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').value = 'No';
	    		}
	    	}else if(idCheck == getAlterOrGlobalFlexId()+'__VAL_C134'){
	    		if(element.checked){
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').checked = false;
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').checked = false;
	    			
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').value = 'No';
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').value = 'No';
	    		}
	    	}else if(idCheck == getAlterOrGlobalFlexId()+'__VAL_C136'){
	    		if(element.checked){
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').checked = false;
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').checked = false;
	    			
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').value = 'No';
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').value = 'No';
	    		}
	    	}
	    }else if(getAlterOrGlobalFlexId() == 31){
	    	if(idCheck == getAlterOrGlobalFlexId()+'__VAL_C123'){
	    		if(element.checked){
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').checked = false;
	    			
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').value = 'No';
	    		}
	    	}else if(idCheck == getAlterOrGlobalFlexId()+'__VAL_C126'){
	    		if(element.checked){
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').checked = false;
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').checked = false;
	    			
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').value = 'No';
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').value = 'No';
	    		}
	    	}else if(idCheck == getAlterOrGlobalFlexId()+'__VAL_C128'){
	    		if(element.checked){
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').checked = false;
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').checked = false;
	    			
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').value = 'No';
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').value = 'No';
	    		}
	    	}else if(idCheck == getAlterOrGlobalFlexId()+'__VAL_C131'){
	    		if(element.checked){
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').checked = false;
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').checked = false;
	    			
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').value = 'No';
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').value = 'No';
	    		}
	    	}else if(idCheck == getAlterOrGlobalFlexId()+'__VAL_C134'){
	    		if(element.checked){
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').checked = false;
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').checked = false;
	    			
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').value = 'No';
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').value = 'No';
	    		}
	    	}else if(idCheck == getAlterOrGlobalFlexId()+'__VAL_C136'){
	    		if(element.checked){
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').checked = false;
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').checked = false;
	    			
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').value = 'No';
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').value = 'No';
	    		}
	    	}
	    }else if(getAlterOrGlobalFlexId() == 32){
	    	if(idCheck == getAlterOrGlobalFlexId()+'__VAL_C123'){
	    		if(element.checked){
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').checked = false;
	    			
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').value = 'No';
	    		}
	    	}else if(idCheck == getAlterOrGlobalFlexId()+'__VAL_C126'){
	    		if(element.checked){
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').checked = false;
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').checked = false;
	    			
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').value = 'No';
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').value = 'No';
	    		}
	    	}else if(idCheck == getAlterOrGlobalFlexId()+'__VAL_C128'){
	    		if(element.checked){
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').checked = false;
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').checked = false;
	    			
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').value = 'No';
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').value = 'No';
	    		}
	    	}else if(idCheck == getAlterOrGlobalFlexId()+'__VAL_C131'){
	    		if(element.checked){
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').checked = false;
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').checked = false;
	    			
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').value = 'No';
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').value = 'No';
	    		}
	    	}else if(idCheck == getAlterOrGlobalFlexId()+'__VAL_C134'){
	    		if(element.checked){
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').checked = false;
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').checked = false;
	    			
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').value = 'No';
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').value = 'No';
	    		}
	    	}else if(idCheck == getAlterOrGlobalFlexId()+'__VAL_C136'){
	    		if(element.checked){
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').checked = false;
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').checked = false;
	    			
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').value = 'No';
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').value = 'No';
	    		}
	    	}
	    }else if(getAlterOrGlobalFlexId() == 33){
	    	if(idCheck == getAlterOrGlobalFlexId()+'__VAL_C123'){
	    		if(element.checked){
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').checked = false;
	    			
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').value = 'No';
	    		}
	    	}else if(idCheck == getAlterOrGlobalFlexId()+'__VAL_C126'){
	    		if(element.checked){
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').checked = false;
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').checked = false;
	    			
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').value = 'No';
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').value = 'No';
	    		}
	    	}else if(idCheck == getAlterOrGlobalFlexId()+'__VAL_C128'){
	    		if(element.checked){
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').checked = false;
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').checked = false;
	    			
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').value = 'No';
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').value = 'No';
	    		}
	    	}else if(idCheck == getAlterOrGlobalFlexId()+'__VAL_C131'){
	    		if(element.checked){
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').checked = false;
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').checked = false;
	    			
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').value = 'No';
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').value = 'No';
	    		}
	    	}else if(idCheck == getAlterOrGlobalFlexId()+'__VAL_C134'){
	    		if(element.checked){
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').checked = false;
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').checked = false;
	    			
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').value = 'No';
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').value = 'No';
	    		}
	    	}else if(idCheck == getAlterOrGlobalFlexId()+'__VAL_C136'){
	    		if(element.checked){
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').checked = false;
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').checked = false;
	    			
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').value = 'No';
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').value = 'No';
	    		}
	    	}
	    }else if(getAlterOrGlobalFlexId() == 34){
	    	if(idCheck == getAlterOrGlobalFlexId()+'__VAL_C123'){
	    		if(element.checked){
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').checked = false;
	    			
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').value = 'No';
	    		}
	    	}else if(idCheck == getAlterOrGlobalFlexId()+'__VAL_C126'){
	    		if(element.checked){
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').checked = false;
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').checked = false;
	    			
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').value = 'No';
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').value = 'No';
	    		}
	    	}else if(idCheck == getAlterOrGlobalFlexId()+'__VAL_C128'){
	    		if(element.checked){
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').checked = false;
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').checked = false;
	    			
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').value = 'No';
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').value = 'No';
	    		}
	    	}else if(idCheck == getAlterOrGlobalFlexId()+'__VAL_C131'){
	    		if(element.checked){
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').checked = false;
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').checked = false;
	    			
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').value = 'No';
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').value = 'No';
	    		}
	    	}else if(idCheck == getAlterOrGlobalFlexId()+'__VAL_C134'){
	    		if(element.checked){
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').checked = false;
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').checked = false;
	    			
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').value = 'No';
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').value = 'No';
	    		}
	    	}else if(idCheck == getAlterOrGlobalFlexId()+'__VAL_C136'){
	    		if(element.checked){
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').checked = false;
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').checked = false;
	    			
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').value = 'No';
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').value = 'No';
	    		}
	    	}
	    }else if(getAlterOrGlobalFlexId() == 35){
	    	if(idCheck == getAlterOrGlobalFlexId()+'__VAL_C123'){
	    		if(element.checked){
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').checked = false;
	    			
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').value = 'No';
	    		}
	    	}else if(idCheck == getAlterOrGlobalFlexId()+'__VAL_C126'){
	    		if(element.checked){
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').checked = false;
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').checked = false;
	    			
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').value = 'No';
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').value = 'No';
	    		}
	    	}else if(idCheck == getAlterOrGlobalFlexId()+'__VAL_C128'){
	    		if(element.checked){
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').checked = false;
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').checked = false;
	    			
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').value = 'No';
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').value = 'No';
	    		}
	    	}else if(idCheck == getAlterOrGlobalFlexId()+'__VAL_C131'){
	    		if(element.checked){
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').checked = false;
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').checked = false;
	    			
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').value = 'No';
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').value = 'No';
	    		}
	    	}else if(idCheck == getAlterOrGlobalFlexId()+'__VAL_C134'){
	    		if(element.checked){
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').checked = false;
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').checked = false;
	    			
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').value = 'No';
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').value = 'No';
	    		}
	    	}else if(idCheck == getAlterOrGlobalFlexId()+'__VAL_C136'){
	    		if(element.checked){
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').checked = false;
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').checked = false;
	    			
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').value = 'No';
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').value = 'No';
	    		}
	    	}
	    }else if(getAlterOrGlobalFlexId() == 41){
	    	if(idCheck == getAlterOrGlobalFlexId()+'__VAL_C123'){
	    		if(element.checked){
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').checked = false;
	    			
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').value = 'No';
	    		}
	    	}else if(idCheck == getAlterOrGlobalFlexId()+'__VAL_C126'){
	    		if(element.checked){
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').checked = false;
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').checked = false;
	    			
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').value = 'No';
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').value = 'No';
	    		}
	    	}else if(idCheck == getAlterOrGlobalFlexId()+'__VAL_C128'){
	    		if(element.checked){
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').checked = false;
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').checked = false;
	    			
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').value = 'No';
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').value = 'No';
	    		}
	    	}else if(idCheck == getAlterOrGlobalFlexId()+'__VAL_C131'){
	    		if(element.checked){
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').checked = false;
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').checked = false;
	    			
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').value = 'No';
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').value = 'No';
	    		}
	    	}else if(idCheck == getAlterOrGlobalFlexId()+'__VAL_C134'){
	    		if(element.checked){
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').checked = false;
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').checked = false;
	    			
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').value = 'No';
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').value = 'No';
	    		}
	    	}else if(idCheck == getAlterOrGlobalFlexId()+'__VAL_C136'){
	    		if(element.checked){
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').checked = false;
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').checked = false;
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').checked = false;
	    			
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C123').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C126').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C128').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C131').value = 'No';
	    			document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C134').value = 'No';
	    			//document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C136').value = 'No';
	    		}
	    	}
	    }
}

//ECM 09 Noviembre 2015
function controlSemReqPro(element){
	setsSemReqProto(element.value);

}
//ECM 09 Noviembre 2015
function controlSemReqIns(element){
	setsSemReqInsc(element.value);
}

//ECM 10 Noviembre 2015 Metodo inicial de carga datos en flex.
/*
 * 
 *          INICIO FLEX
 * 
 * */
function IniSemReq(){
	
	//alert('IniSemReq');
	
	//alert('Flex:  '+globalFlexTableId);
	
	//alert('getAlterOrGlobalFlexId: ' + getAlterOrGlobalFlexId());
	
	
	if(getAlterOrGlobalFlexId() == 17 ||
	   getAlterOrGlobalFlexId() == 18
	){
		
		showDelegadoPor();
		
		var vValorSemReqProto2 = document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C4').value;
		var vValorSemReqInsc2  = document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C5').value;

		setsSemReqProto2(vValorSemReqProto2);
		setsSemReqInsc2(vValorSemReqInsc2);

		if(getAlterOrGlobalFlexId() == 17){
			cambiarStatus(document.getElementById('17__VAL_C76').value);
			
		}else if(getAlterOrGlobalFlexId() == 18){
			cambiarStatus(document.getElementById('18__VAL_C76').value);
		}
		

	}else 	if(getAlterOrGlobalFlexId() == 20 ||
			   getAlterOrGlobalFlexId() == 21 ||
			   getAlterOrGlobalFlexId() == 22 ||
			   getAlterOrGlobalFlexId() == 27 ||
			   getAlterOrGlobalFlexId() == 28 ||
			   getAlterOrGlobalFlexId() == 29 ||
			   getAlterOrGlobalFlexId() == 30 ||
			   getAlterOrGlobalFlexId() == 31 ||
			   getAlterOrGlobalFlexId() == 32 ||
			   getAlterOrGlobalFlexId() == 33 ||
			   getAlterOrGlobalFlexId() == 34 ||
			   getAlterOrGlobalFlexId() == 35 ||
			   getAlterOrGlobalFlexId() == 41
			){
		
		
		//alert('antes de vValorSemReqProto');
		
		
		var vValorSemReqProto = document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C81').value;
		
		//alert(vValorSemReqProto);
		
		var vValorSemReqInsc  = document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C82').value;

		//alert('XY: 1');
		
		setsSemReqProto(vValorSemReqProto);
		
		//alert('XY: 2');
		
		setsSemReqInsc(vValorSemReqInsc);
		
		
		//alert('XY: 3');
		
		setMonedaGen();
		
		
		//alert('XY: 4');
		
		
		//ECM 25 Febrero 2016 Cambia status aplica o no aplica.
		if(getAlterOrGlobalFlexId() == 20){
			
			//alert('OK');
			//alert("document.getElementById('20__VAL_C59').value: " + document.getElementById('20__VAL_C59').value);
			
			cambiarStatus(document.getElementById('20__VAL_C59').value);
		}else if(getAlterOrGlobalFlexId() == 21){
			cambiarStatus(document.getElementById('21__VAL_C49').value);
		}else if(getAlterOrGlobalFlexId() == 22){
			cambiarStatus(document.getElementById('22__VAL_C55').value);
		}else if(getAlterOrGlobalFlexId() == 27){
			cambiarStatus(document.getElementById('27__VAL_C17').value);
		}else if(getAlterOrGlobalFlexId() == 28){
			cambiarStatus(document.getElementById('28__VAL_C26').value);
		}else if(getAlterOrGlobalFlexId() == 29){
			cambiarStatus(document.getElementById('29__VAL_C45').value);
		}else if(getAlterOrGlobalFlexId() == 30){
			cambiarStatus(document.getElementById('30__VAL_C28').value);
		}else if(getAlterOrGlobalFlexId() == 31){
			cambiarStatus(document.getElementById('31__VAL_C25').value);
		}else if(getAlterOrGlobalFlexId() == 32){
			cambiarStatus(document.getElementById('32__VAL_C45').value);
		}else if(getAlterOrGlobalFlexId() == 33){
			cambiarStatus(document.getElementById('33__VAL_C47').value);
		}else if(getAlterOrGlobalFlexId() == 34){
			cambiarStatus(document.getElementById('34__VAL_C68').value);

			mostrarFusionAumentoCapitalFijo(document.getElementById('34__VAL_C9').value);
			mostrarFusionDisminucionCapitalFijo(document.getElementById('34__VAL_C43').value);
			mostrarFusionAumentoCapitalVariable(document.getElementById('34__VAL_C13').value);
			mostrarFusionDisminucionCapitalVariable(document.getElementById('34__VAL_C47').value);

		}else if(getAlterOrGlobalFlexId() == 35){
			cambiarStatus(document.getElementById('35__VAL_C25').value);
		}else if(getAlterOrGlobalFlexId() == 41){
			cambiarStatus(document.getElementById('41__VAL_C25').value);
		}

	}else if(getAlterOrGlobalFlexId() == 23){
		var vValorSemReqProto3 = document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C101').value;
		var vValorSemReqInsc3  = document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C102').value;

		setsSemReqProto3(vValorSemReqProto3);
		setsSemReqInsc3(vValorSemReqInsc3);
		setMonedaGen();
		//ECM 25 Febrero 2016 Cambia status aplica o no aplica.
		cambiarStatus(document.getElementById('23__VAL_C98').value);

	}

	if(getAlterOrGlobalFlexId() == 22){
		var vValorReformaParcial = document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C10').value;
		setsReformaParcialTrans(vValorReformaParcial);

	}

	
	
}

//ECM 11 Noviembre 2015
function setsSemReqProto(pValorCheck){
	/*
	if(pValorCheck == 'No'){
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C84_1').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C84_2').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C85_1').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C85_2').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C86_1').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C86_2').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C87_1').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C87_2').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C88_1').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C88_2').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C89_1').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C89_2').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C90_1').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C90_2').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C91_1').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C91_2').style.display = 'none';
		//document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C92_1').style.display = 'none';
		//document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C92_2').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C93_1').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C93_2').style.display = 'none';

	}else{
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C84_1').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C84_2').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C85_1').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C85_2').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C86_1').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C86_2').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C87_1').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C87_2').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C88_1').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C88_2').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C89_1').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C89_2').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C90_1').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C90_2').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C91_1').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C91_2').style.display = 'block';
		//document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C92_1').style.display = 'block';
		//document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C92_2').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C93_1').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C93_2').style.display = 'block';
	}	
	*/
	
	if(pValorCheck == 'No'){
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C84_1').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C84_2').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C85_1').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C85_2').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C86_1').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C86_2').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C87_1').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C87_2').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C88_1').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C88_2').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C89_1').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C89_2').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C90_1').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C90_2').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C91_1').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C91_2').style.display = 'none';
		//document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C92_1').style.display = 'none';
		//document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C92_2').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C93_1').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C93_2').style.display = 'none';

	}else{
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C84_1').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C84_2').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C85_1').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C85_2').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C86_1').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C86_2').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C87_1').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C87_2').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C88_1').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C88_2').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C89_1').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C89_2').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C90_1').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C90_2').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C91_1').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C91_2').style.display = 'block';
		//document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C92_1').style.display = 'block';
		//document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C92_2').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C93_1').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C93_2').style.display = 'block';
	}	
}

//ECM 11 Noviembre 2015
function setsSemReqInsc(pValorCheck){
	/*
	if(pValorCheck == 'No'){
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C94_1').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C94_2').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C95_1').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C95_2').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C96_1').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C96_2').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C97_1').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C97_2').style.display = 'none';
	}else{
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C94_1').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C94_2').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C95_1').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C95_2').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C96_1').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C96_2').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C97_1').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C97_2').style.display = 'block';
	}
	
	*/
	
	if(pValorCheck == 'No'){
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C94_1').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C94_2').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C95_1').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C95_2').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C96_1').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C96_2').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C97_1').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C97_2').style.display = 'none';
	}else{
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C94_1').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C94_2').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C95_1').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C95_2').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C96_1').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C96_2').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C97_1').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C97_2').style.display = 'block';
	}
	
}

//ECM 12 Noviembre 2015
function setMonedaGen(){
	
	//var vValMoneda = document.getElementById('C21').value;
	
	// Se comento la linea anterior asumiendo que no tiene caso
	// NAVA - 12 Julio
	
	
}

function siReformaParcial(pObjeto){
	//alert(pObjecto.value);
	setsReformaParcialTrans(pObjeto.value);

}

function setsReformaParcialTrans(pSiReformaParcial){
	if(pSiReformaParcial == 'No'){
		/*
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C11_1').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C11_2').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C12_1').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C12_2').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C13_1').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C13_2').style.display = 'none';
		 */
		//ULR 11-05-2017 se comento esta linea para no generar error de javascript, ya 
		// que se ocultaron los campos que referenciaba
		/*document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C11_1').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C11_2').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C12_1').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C12_2').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C13_1').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C13_2').style.display = 'none';*/
	}else{
		/*
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C11_1').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C11_2').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C12_1').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C12_2').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C13_1').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C13_2').style.display = 'block';
		*/
		//ULR 11-05-2017 se comento esta linea para no generar error de javascript, ya 
		// que se ocultaron los campos que referenciaba	
	/*document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C11_1').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C11_2').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C12_1').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C12_2').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C13_1').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C13_2').style.display = 'block';*/

	}
}

/**
 *  ECM 13 Noviembre 2015
 */
function controlSemReqPro2(element){
	setsSemReqProto2(element.value);

}

function controlSemReqIns2(element){
	setsSemReqInsc2(element.value);
}

function setsSemReqProto2(pValorCheck){
	if(pValorCheck == 'No'){
		/*
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C6_1').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C6_2').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C7_1').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C7_2').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C8_1').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C8_2').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C9_1').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C9_2').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C10_1').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C10_2').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C11_1').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C11_2').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C12_1').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C12_2').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C13_1').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C13_2').style.display = 'none';
		//document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C14_1').style.display = 'none';
		//document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C14_2').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C15_1').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C15_2').style.display = 'none';
		*/
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C6_1').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C6_2').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C7_1').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C7_2').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C8_1').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C8_2').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C9_1').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C9_2').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C10_1').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C10_2').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C11_1').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C11_2').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C12_1').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C12_2').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C13_1').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C13_2').style.display = 'none';
		//document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C14_1').style.display = 'none';
		//document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C14_2').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C15_1').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C15_2').style.display = 'none';
	}else{
		/*
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C6_1').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C6_2').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C7_1').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C7_2').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C8_1').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C8_2').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C9_1').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C9_2').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C10_1').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C10_2').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C11_1').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C11_2').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C12_1').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C12_2').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C13_1').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C13_2').style.display = 'block';
		//document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C14_1').style.display = 'block';
		//document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C14_2').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C15_1').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C15_2').style.display = 'block';
		*/
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C6_1').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C6_2').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C7_1').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C7_2').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C8_1').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C8_2').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C9_1').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C9_2').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C10_1').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C10_2').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C11_1').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C11_2').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C12_1').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C12_2').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C13_1').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C13_2').style.display = 'block';
		//document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C14_1').style.display = 'block';
		//document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C14_2').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C15_1').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C15_2').style.display = 'block';
	}	
}

function setsSemReqInsc2(pValorCheck){
	if(pValorCheck == 'No'){
		/*
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C18_1').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C18_2').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C19_1').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C19_2').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C20_1').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C20_2').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C21_1').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C21_2').style.display = 'none';
		*/
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C18_1').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C18_2').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C19_1').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C19_2').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C20_1').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C20_2').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C21_1').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C21_2').style.display = 'none';
	}else{
		/*
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C18_1').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C18_2').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C19_1').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C19_2').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C20_1').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C20_2').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C21_1').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C21_2').style.display = 'block';
		*/
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C18_1').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C18_2').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C19_1').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C19_2').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C20_1').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C20_2').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C21_1').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C21_2').style.display = 'block';
	}
}

function controlSemReqPro3(element){
	setsSemReqProto3(element.value);
}

function controlSemReqIns3(element){
	setsSemReqInsc3(element.value);
}

function setsSemReqProto3(pValorCheck){
	if(pValorCheck == 'No'){
		/*
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C104_1').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C104_2').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C105_1').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C105_2').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C106_1').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C106_2').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C107_1').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C107_2').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C108_1').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C108_2').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C109_1').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C109_2').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C110_1').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C110_2').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C111_1').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C111_2').style.display = 'none';
		//document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C112_1').style.display = 'none';
		//document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C112_2').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C113_1').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C113_2').style.display = 'none';
		*/
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C104_1').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C104_2').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C105_1').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C105_2').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C106_1').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C106_2').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C107_1').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C107_2').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C108_1').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C108_2').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C109_1').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C109_2').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C110_1').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C110_2').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C111_1').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C111_2').style.display = 'none';
		//document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C112_1').style.display = 'none';
		//document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C112_2').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C113_1').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C113_2').style.display = 'none';
	}else{
		/*
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C104_1').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C104_2').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C105_1').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C105_2').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C106_1').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C106_2').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C107_1').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C107_2').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C108_1').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C108_2').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C109_1').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C109_2').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C110_1').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C110_2').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C111_1').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C111_2').style.display = 'block';
		//document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C112_1').style.display = 'block';
		//document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C112_2').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C113_1').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C113_2').style.display = 'block';
		*/
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C104_1').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C104_2').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C105_1').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C105_2').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C106_1').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C106_2').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C107_1').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C107_2').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C108_1').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C108_2').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C109_1').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C109_2').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C110_1').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C110_2').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C111_1').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C111_2').style.display = 'block';
		//document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C112_1').style.display = 'block';
		//document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C112_2').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C113_1').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C113_2').style.display = 'block';
	}	
}

function setsSemReqInsc3(pValorCheck){
	if(pValorCheck == 'No'){
		/*
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C114_1').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C114_2').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C115_1').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C115_2').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C116_1').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C116_2').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C117_1').style.display = 'none';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C117_2').style.display = 'none';
		*/
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C114_1').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C114_2').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C115_1').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C115_2').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C116_1').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C116_2').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C117_1').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C117_2').style.display = 'none';
	}else{
		/*
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C114_1').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C114_2').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C115_1').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C115_2').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C116_1').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C116_2').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C117_1').style.display = 'block';
		document.getElementById('DIVC_'+getAlterOrGlobalFlexId()+'_VAL_C117_2').style.display = 'block';
		*/
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C114_1').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C114_2').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C115_1').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C115_2').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C116_1').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C116_2').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C117_1').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C117_2').style.display = 'block';
	}
}
/**
 * END ECM 13 Noviembre 2015 Reformas
 */

function setAdminUnico(){
	document.getElementById("8__VAL_C2").value = 14265;
	
}


function sleep(milliseconds) {
  var start = new Date().getTime();
  for (var i = 0; i < 1e7; i++) {
    if ((new Date().getTime() - start) > milliseconds){
      break;
    }
  }
}

function limpiarCampoNumerico(campo) {
		
	campo.value = campo.value.replace(' ','').replace(',','');

	var enNumero = (1 * campo.value);
	if( enNumero != campo.value && campo.value != ".") {
		campo.select();
		alert("El campo actual debe tener valor Numerico");
		campo.value = "";
		return false;
	} else {
		return true;
	}	
}

//ECM 05 Noviembre 2015 Formato Documento
function getFormatDocumentum(pObjeto){

	//18 ENERO 2016
	var vSrc = pObjeto.value;
	vSrc = vSrc.replace(/ /g, ''); //Quitar espacios en cadena
	vSrc = vSrc.trim();	
	pObjeto.value = vSrc; 
	
	var vSeparador = '-';
	var vPatron =  new Array(3,3,3,4,4,5,2,2,3);
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

//ECM 18 Enero 2016
 function getMascDocu(pObjeto){

}


//ECM 17 Noviembre 2015
function sumCapFijoDeConRef(){
	if(globalFlexTableId == 29 || globalFlexTableId == 37 || globalFlexTableId == 38){
		try{
			var suma1 = document.getElementById('29__VAL_C7').value;
			suma1 = accounting.unformat(suma1);
			
			var suma2 = document.getElementById('29__VAL_C8').value;
			suma2 = accounting.unformat(suma2);
			
			var suma3 = suma1 + suma2;
			
			suma3 = accounting.formatMoney(suma3);
			
			document.getElementById('29__VAL_C9').value = suma3;
			
		}catch(Exception){
			
		}

	}
	if(globalFlexTableId == 33 || globalFlexTableId == 37 || globalFlexTableId == 38){	//Escision
		try{
			var suma1 = document.getElementById('33__VAL_C8').value;
			suma1 = accounting.unformat(suma1);
			
			var suma2 = document.getElementById('33__VAL_C9').value;
			suma2 = accounting.unformat(suma2);
			
			var suma3 = suma1 + suma2;
			
			suma3 = accounting.formatMoney(suma3);
			
			document.getElementById('33__VAL_C10').value = suma3;
			
		}catch(Exception){
			
		}
		
	}
	if(globalFlexTableId == 34 || globalFlexTableId == 37 || globalFlexTableId == 38){ //Fusion
		try{
			var suma1 = document.getElementById('34__VAL_C10').value;
			suma1 = accounting.unformat(suma1);
			
			var suma2 = document.getElementById('34__VAL_C11').value;
			suma2 = accounting.unformat(suma2);
			
			var suma3 = suma1 + suma2;
			
			suma3 = accounting.formatMoney(suma3);
			
			document.getElementById('34__VAL_C12').value = suma3;
			
		}catch(Exception){
			
		}
	}
	sumCapSocRef();

}
function sumCapVarDeConRef(){
	if(globalFlexTableId == 29 || globalFlexTableId == 37 || globalFlexTableId == 38){
		try{
			var suma1 = document.getElementById('29__VAL_C11').value;
			suma1 = accounting.unformat(suma1);
			
			var suma2 = document.getElementById('29__VAL_C12').value;
			suma2 = accounting.unformat(suma2);
			
			var suma3 = suma1 + suma2;
			
			suma3 = accounting.formatMoney(suma3);
			
			document.getElementById('29__VAL_C13').value = suma3;
		}catch(Exception){
			
		}

	}
	if(globalFlexTableId == 33 || globalFlexTableId == 37 || globalFlexTableId == 38){ //Escision
		try{
			var suma1 = document.getElementById('33__VAL_C12').value;
			suma1 = accounting.unformat(suma1);
			
			var suma2 = document.getElementById('33__VAL_C13').value;
			suma2 = accounting.unformat(suma2);
			
			var suma3 = suma1 + suma2;
			
			suma3 = accounting.formatMoney(suma3);
			
			document.getElementById('33__VAL_C14').value = suma3;			
		}catch(Exception){
		}
		

	}
	if(globalFlexTableId == 34 || globalFlexTableId == 37 || globalFlexTableId == 38){ //Fusion
		try{
			var suma1 = document.getElementById('34__VAL_C14').value;
			suma1 = accounting.unformat(suma1);
			
			var suma2 = document.getElementById('34__VAL_C15').value;
			suma2 = accounting.unformat(suma2);
			
			var suma3 = suma1 + suma2;
			
			suma3 = accounting.formatMoney(suma3);
			
			document.getElementById('34__VAL_C16').value = suma3;
		}catch(Exception){
			
		}
	
	}
	sumCapSocRef();
}
function sumCapSocRef(){
	if(globalFlexTableId == 29 || globalFlexTableId == 37 || globalFlexTableId == 38){
		try{
			var suma1 = document.getElementById('29__VAL_C9').value;
			suma1 = accounting.unformat(suma1);
			
			var suma2 = document.getElementById('29__VAL_C13').value;
			suma2 = accounting.unformat(suma2);
			
			var suma3 = suma1 + suma2;
			
			suma3 = accounting.formatMoney(suma3);
			
			document.getElementById('29__VAL_C14').value = suma3;
		}catch(Exception){
			
		}

	}
	if(globalFlexTableId == 33 || globalFlexTableId == 37 || globalFlexTableId == 38){ //Escision
		try{
			var suma1 = document.getElementById('33__VAL_C10').value;
			suma1 = accounting.unformat(suma1);
			
			var suma2 = document.getElementById('33__VAL_C14').value;
			suma2 = accounting.unformat(suma2);
			
			var suma3 = suma1 + suma2;
			
			suma3 = accounting.formatMoney(suma3);
			
			document.getElementById('33__VAL_C15').value = suma3;
		}catch(Exception){
			
		}
	
	}
	if(globalFlexTableId == 34 || globalFlexTableId == 37 || globalFlexTableId == 38){ //Fusion
		try{
			var suma1 = document.getElementById('34__VAL_C12').value;
			suma1 = accounting.unformat(suma1);
			
			var suma2 = document.getElementById('34__VAL_C16').value;
			suma2 = accounting.unformat(suma2);
			
			var suma3 = suma1 + suma2;
			
			suma3 = accounting.formatMoney(suma3);
			
			document.getElementById('34__VAL_C17').value = suma3;
			
			sumaCapitalesSociales();
		}catch(Exception){
			
		}

	}
}

//ECM 25 ENERO 2015 Resta Flex Disminución de Capital
	function restaCapFijoDeConRef(){
		if(globalFlexTableId == 32 || globalFlexTableId == 37 || globalFlexTableId == 38){
			try{
				var resta1 = document.getElementById('32__VAL_C7').value;
				resta1 = accounting.unformat(resta1);
				
				var resta2 = document.getElementById('32__VAL_C8').value;
				resta2 = accounting.unformat(resta2);
				
				var resta3 = resta1 - resta2;
				
				resta3 = accounting.formatMoney(resta3);
				
				document.getElementById('32__VAL_C9').value = resta3;
				
				restaCapSocRef();				
			}catch(Exception){
				
			}
		} 
		if(globalFlexTableId == 33 || globalFlexTableId == 37 || globalFlexTableId == 38){
			try{
				var resta1 = document.getElementById('33__VAL_C8').value;
				resta1 = accounting.unformat(resta1);
				
				var resta2 = document.getElementById('33__VAL_C9').value;
				resta2 = accounting.unformat(resta2);
				
				var resta3 = resta1 - resta2;
				
				resta3 = accounting.formatMoney(resta3);
				
				document.getElementById('33__VAL_C10').value = resta3;
				
				restaCapSocRef();				
			}catch(Exception){
				
			}
		} 
		if(globalFlexTableId == 34 || globalFlexTableId == 37 || globalFlexTableId == 38){
			try{
				var resta1 = document.getElementById('34__VAL_C44').value;
				resta1 = accounting.unformat(resta1);
				
				var resta2 = document.getElementById('34__VAL_C45').value;
				resta2 = accounting.unformat(resta2);
				
				var resta3 = resta1 - resta2;
				
				resta3 = accounting.formatMoney(resta3);
				
				document.getElementById('34__VAL_C46').value = resta3;
				
				restaCapSocRef();				
			}catch(Exception){
				
			}
		}

	}
	
	function restaCapVarDeConRef(){
		if(globalFlexTableId == 32 || globalFlexTableId == 37 || globalFlexTableId == 38){
			try{
				var resta1 = document.getElementById('32__VAL_C11').value;
				resta1 = accounting.unformat(resta1);
				
				var resta2 = document.getElementById('32__VAL_C12').value;
				resta2 = accounting.unformat(resta2);
				
				var resta3 = resta1 - resta2;
				
				resta3 = accounting.formatMoney(resta3);
				
				document.getElementById('32__VAL_C13').value = resta3;
				
				restaCapSocRef();				
			}catch(Exception){
				
			}
		} 
		if(globalFlexTableId == 33 || globalFlexTableId == 37 || globalFlexTableId == 38){
			try{
				var resta1 = document.getElementById('33__VAL_C12').value;
				resta1 = accounting.unformat(resta1);
				
				var resta2 = document.getElementById('33__VAL_C13').value;
				resta2 = accounting.unformat(resta2);
				
				var resta3 = resta1 - resta2;
				
				resta3 = accounting.formatMoney(resta3);
				
				document.getElementById('33__VAL_C14').value = resta3;
				
				restaCapSocRef();
				
			}catch(Exception){
				
			}
		} 
		if(globalFlexTableId == 34 || globalFlexTableId == 37 || globalFlexTableId == 38){
			try{
				var resta1 = document.getElementById('34__VAL_C48').value;
				resta1 = accounting.unformat(resta1);
				
				var resta2 = document.getElementById('34__VAL_C49').value;
				resta2 = accounting.unformat(resta2);
				
				var resta3 = resta1 - resta2;
				
				resta3 = accounting.formatMoney(resta3);
				
				document.getElementById('34__VAL_C50').value = resta3;
				
				restaCapSocRef();
				
			}catch(Exception){
				
			}
		}

	}


	function restaCapSocRef(){
		if(globalFlexTableId == 32 || globalFlexTableId == 37 || globalFlexTableId == 38){
			try{
				var resta1 = document.getElementById('32__VAL_C9').value;
				resta1 = accounting.unformat(resta1);
				
				var resta2 = document.getElementById('32__VAL_C13').value;
				resta2 = accounting.unformat(resta2);
				
				var resta3 = resta1 + resta2;
				
				resta3 = accounting.formatMoney(resta3);
				
				document.getElementById('32__VAL_C14').value = resta3;
				
			}catch(Exception){
				
			}

		} 
		if(globalFlexTableId == 33 || globalFlexTableId == 37 || globalFlexTableId == 38){
			try{
				var resta1 = document.getElementById('33__VAL_C10').value;
				resta1 = accounting.unformat(resta1);
				
				var resta2 = document.getElementById('33__VAL_C14').value;
				resta2 = accounting.unformat(resta2);
				
				var resta3 = resta1 + resta2;
				
				resta3 = accounting.formatMoney(resta3);
				
				document.getElementById('33__VAL_C15').value = resta3;
				
			}catch(Exception){
				
			}

		}
		if(globalFlexTableId == 34 || globalFlexTableId == 37 || globalFlexTableId == 38){
			try{
				var resta1 = document.getElementById('34__VAL_C46').value;
				resta1 = accounting.unformat(resta1);
				
				var resta2 = document.getElementById('34__VAL_C50').value;
				resta2 = accounting.unformat(resta2);
				
				var resta3 = resta1 + resta2;
				
				resta3 = accounting.formatMoney(resta3);
				
				document.getElementById('34__VAL_C51').value = resta3;
				
				sumaCapitalesSociales();
				
			}catch(Exception){
				
			}

		}

	}


//ICL 03122015
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

function sumaCapitalesSociales(){

    if(globalFlexTableId == 34 || globalFlexTableId == 37 || globalFlexTableId == 38){
    	try{
    		var suma1 = document.getElementById('34__VAL_C17').value;
    		suma1 = accounting.unformat(suma1);
    		
    		var suma2 = document.getElementById('34__VAL_C51').value;
    		suma2 = accounting.unformat(suma2);
    		
    		var suma3 = suma1 + suma2;
    		suma3 = accounting.formatMoney(suma3);
    		
    		document.getElementById('34__VAL_C52').value = suma3;
			
		}catch(Exception){
			
		}

    }
}

function uncheckme(element){
	  var ele = document.getElementsByName(element.name);
	   for(var i=0;i<ele.length;i++){
	      ele[i].checked = false;
	   }
	   
	   ele[ele.length -1].checked = true;

}

function habilitarStatus(element){
	cambiarStatus(element.value);
}

//ECM 24 Febrero 2016
function cambiarStatus(element){

	
	//alert("cambiarStatus 1");
	
	$("#17__VAL_C8").attr('maxlength','6'); //Poderes Generales
	
	//alert("cambiarStatus 2");
	
	$("#18__VAL_C8").attr('maxlength','6'); //Poderes Especiales
	$("#20__VAL_C86").attr('maxlength','6'); //Reforma Total de Estatutos
	$("#21__VAL_C86").attr('maxlength','6'); //Reforma Parcial de Estatutos
	$("#22__VAL_C86").attr('maxlength','6'); //Transformacion
	$("#23__VAL_C106").attr('maxlength','6'); //Aprobación General de Estatutos
	$("#27__VAL_C86").attr('maxlength','6'); //
	$("#28__VAL_C86").attr('maxlength','6'); //
	$("#29__VAL_C86").attr('maxlength','6'); //	
	$("#30__VAL_C86").attr('maxlength','6'); //Contratos
	$("#31__VAL_C86").attr('maxlength','6'); //
	$("#32__VAL_C86").attr('maxlength','6'); //
	$("#33__VAL_C86").attr('maxlength','6'); //
	$("#34__VAL_C86").attr('maxlength','6'); //
	$("#35__VAL_C86").attr('maxlength','6'); //
	$("#41__VAL_C86").attr('maxlength','6'); //

	
	//alert("cambiarStatus 3");
	//iniMaskEscFlex();
	
	$("#17__VAL_C8").numeric();
	
	//alert("cambiarStatus 4");
	
	$("#18__VAL_C8").numeric();
	$("#20__VAL_C86").numeric();
	$("#21__VAL_C86").numeric();
	$("#22__VAL_C86").numeric();
	$("#23__VAL_C106").numeric();
	$("#27__VAL_C86").numeric();
	$("#28__VAL_C86").numeric();
	$("#29__VAL_C86").numeric();	
	$("#30__VAL_C86").numeric();
	$("#31__VAL_C86").numeric();
	$("#32__VAL_C86").numeric();
	$("#33__VAL_C86").numeric();
	$("#34__VAL_C86").numeric();
	$("#35__VAL_C86").numeric();
	$("#41__VAL_C86").numeric();

	//alert("cambiarStatus 5");
	
	//var flexTabId = globalFlexTableId;
	var flexTabId = alterFlexTableId;
	/*
	if(flexTabId == 38 || flexTabId == 37) {
		
		flexTabId = alterFlexTableId;
	}*/
	
	
	//	alert("flexTabId: " + flexTabId);
	
	
	
	
	

	if(flexTabId == 17){
	    if(element == 'No'){

	    	document.getElementById('DIVC_17_VAL_C56_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		
	    	document.getElementById('DIVC_17_VAL_C57_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_17_VAL_C58_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_17_VAL_C59_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		
    		document.getElementById('DIVC_17_VAL_C60_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_17_VAL_C61_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_17_VAL_C62_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		
    		document.getElementById('DIVC_17_VAL_C63_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_17_VAL_C64_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_17_VAL_C65_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

    		document.getElementById('DIVC_17_VAL_C66_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_17_VAL_C67_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_17_VAL_C68_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

    		document.getElementById('DIVC_17_VAL_C69_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_17_VAL_C70_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_17_VAL_C71_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

    		document.getElementById('DIVC_17_VAL_C72_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_17_VAL_C73_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_17_VAL_C74_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

            ////////////////////////////////////////////////////////////////////

	    	document.getElementById('DIVC_17_VAL_C56_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		
	    	document.getElementById('DIVC_17_VAL_C57_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_17_VAL_C58_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_17_VAL_C59_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		
    		document.getElementById('DIVC_17_VAL_C60_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_17_VAL_C61_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_17_VAL_C62_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		
    		document.getElementById('DIVC_17_VAL_C63_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_17_VAL_C64_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_17_VAL_C65_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

    		document.getElementById('DIVC_17_VAL_C66_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_17_VAL_C67_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_17_VAL_C68_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

    		document.getElementById('DIVC_17_VAL_C69_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_17_VAL_C70_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_17_VAL_C71_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

    		document.getElementById('DIVC_17_VAL_C72_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_17_VAL_C73_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_17_VAL_C74_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

    		
    	}else{
	    	document.getElementById('DIVC_17_VAL_C56_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

	    	document.getElementById('DIVC_17_VAL_C57_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_17_VAL_C58_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_17_VAL_C59_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

    		document.getElementById('DIVC_17_VAL_C60_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_17_VAL_C61_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_17_VAL_C62_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

    		document.getElementById('DIVC_17_VAL_C63_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_17_VAL_C64_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_17_VAL_C65_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

    		document.getElementById('DIVC_17_VAL_C66_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_17_VAL_C67_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_17_VAL_C68_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

    		document.getElementById('DIVC_17_VAL_C69_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_17_VAL_C70_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_17_VAL_C71_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

    		document.getElementById('DIVC_17_VAL_C72_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_17_VAL_C73_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_17_VAL_C74_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		/////////////////////////////////////////////////////////////////////
	    	document.getElementById('DIVC_17_VAL_C56_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

	    	document.getElementById('DIVC_17_VAL_C57_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_17_VAL_C58_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_17_VAL_C59_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

    		document.getElementById('DIVC_17_VAL_C60_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_17_VAL_C61_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_17_VAL_C62_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

    		document.getElementById('DIVC_17_VAL_C63_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_17_VAL_C64_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_17_VAL_C65_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

    		document.getElementById('DIVC_17_VAL_C66_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_17_VAL_C67_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_17_VAL_C68_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

    		document.getElementById('DIVC_17_VAL_C69_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_17_VAL_C70_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_17_VAL_C71_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

    		document.getElementById('DIVC_17_VAL_C72_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_17_VAL_C73_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_17_VAL_C74_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

    	}
    }else if(flexTabId == 18){
		    if(element == 'No'){
		    	document.getElementById('DIVC_18_VAL_C56_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

		    	document.getElementById('DIVC_18_VAL_C57_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    		document.getElementById('DIVC_18_VAL_C58_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    		document.getElementById('DIVC_18_VAL_C59_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

	    		document.getElementById('DIVC_18_VAL_C60_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    		document.getElementById('DIVC_18_VAL_C61_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    		document.getElementById('DIVC_18_VAL_C62_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

	    		document.getElementById('DIVC_18_VAL_C63_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    		document.getElementById('DIVC_18_VAL_C64_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    		document.getElementById('DIVC_18_VAL_C65_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

	    		document.getElementById('DIVC_18_VAL_C66_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    		document.getElementById('DIVC_18_VAL_C67_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    		document.getElementById('DIVC_18_VAL_C68_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

	    		document.getElementById('DIVC_18_VAL_C69_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    		document.getElementById('DIVC_18_VAL_C70_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    		document.getElementById('DIVC_18_VAL_C71_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

	    		document.getElementById('DIVC_18_VAL_C72_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    		document.getElementById('DIVC_18_VAL_C73_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    		document.getElementById('DIVC_18_VAL_C74_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    		////////////////////////////////////////////////////////////////////
		    	document.getElementById('DIVC_18_VAL_C56_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

		    	document.getElementById('DIVC_18_VAL_C57_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    		document.getElementById('DIVC_18_VAL_C58_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    		document.getElementById('DIVC_18_VAL_C59_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

	    		document.getElementById('DIVC_18_VAL_C60_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    		document.getElementById('DIVC_18_VAL_C61_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    		document.getElementById('DIVC_18_VAL_C62_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

	    		document.getElementById('DIVC_18_VAL_C63_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    		document.getElementById('DIVC_18_VAL_C64_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    		document.getElementById('DIVC_18_VAL_C65_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

	    		document.getElementById('DIVC_18_VAL_C66_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    		document.getElementById('DIVC_18_VAL_C67_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    		document.getElementById('DIVC_18_VAL_C68_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

	    		document.getElementById('DIVC_18_VAL_C69_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    		document.getElementById('DIVC_18_VAL_C70_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    		document.getElementById('DIVC_18_VAL_C71_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

	    		document.getElementById('DIVC_18_VAL_C72_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    		document.getElementById('DIVC_18_VAL_C73_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    		document.getElementById('DIVC_18_VAL_C74_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';


	    	}else{
		    	document.getElementById('DIVC_18_VAL_C56_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	
		    	document.getElementById('DIVC_18_VAL_C57_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    		document.getElementById('DIVC_18_VAL_C58_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    		document.getElementById('DIVC_18_VAL_C59_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	
	    		document.getElementById('DIVC_18_VAL_C60_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    		document.getElementById('DIVC_18_VAL_C61_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    		document.getElementById('DIVC_18_VAL_C62_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	
	    		document.getElementById('DIVC_18_VAL_C63_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    		document.getElementById('DIVC_18_VAL_C64_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    		document.getElementById('DIVC_18_VAL_C65_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	
	    		document.getElementById('DIVC_18_VAL_C66_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    		document.getElementById('DIVC_18_VAL_C67_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    		document.getElementById('DIVC_18_VAL_C68_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	
	    		document.getElementById('DIVC_18_VAL_C69_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    		document.getElementById('DIVC_18_VAL_C70_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    		document.getElementById('DIVC_18_VAL_C71_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	
	    		document.getElementById('DIVC_18_VAL_C72_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    		document.getElementById('DIVC_18_VAL_C73_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    		document.getElementById('DIVC_18_VAL_C74_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    		/////////////////////////////////////////////////////////////////////
		    	document.getElementById('DIVC_18_VAL_C56_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

		    	document.getElementById('DIVC_18_VAL_C57_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    		document.getElementById('DIVC_18_VAL_C58_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    		document.getElementById('DIVC_18_VAL_C59_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

	    		document.getElementById('DIVC_18_VAL_C60_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    		document.getElementById('DIVC_18_VAL_C61_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    		document.getElementById('DIVC_18_VAL_C62_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

	    		document.getElementById('DIVC_18_VAL_C63_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    		document.getElementById('DIVC_18_VAL_C64_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    		document.getElementById('DIVC_18_VAL_C65_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

	    		document.getElementById('DIVC_18_VAL_C66_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    		document.getElementById('DIVC_18_VAL_C67_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    		document.getElementById('DIVC_18_VAL_C68_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

	    		document.getElementById('DIVC_18_VAL_C69_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    		document.getElementById('DIVC_18_VAL_C70_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    		document.getElementById('DIVC_18_VAL_C71_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

	    		document.getElementById('DIVC_18_VAL_C72_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    		document.getElementById('DIVC_18_VAL_C73_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    		document.getElementById('DIVC_18_VAL_C74_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

	    	}
    }else if(flexTabId == 20){

    	//alert("DEBUG-POINT: flexTabId == 20");
    	
    	//alert(document.getElementById('DIVC_20_VAL_C37_2'));
    	
    	//alert("element: " + element);
    	
    	if(element == 'No'){
    		
    		//alert("INNER HTML");
    		
    		//
    		
    		//alert('Pre-Action 1');
    		
    		//alert(document.getElementById('DIVC_20_VAL_C37_2').innerHTML);
    		//alert(document.getElementById('DIVC_20_VAL_C37_2').style.display);
    		
    		//alert('VALUE: ' + document.getElementById('20__VAL_C37_FLEX_183').value);
    		
    		
    		//alert('DIVC_20_VAL_C37_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId));
    		//alert(document.getElementById('DIVC_20_VAL_C37_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)));
    		
    		
	    	document.getElementById('DIVC_20_VAL_C37_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

	    	//alert('Post-Action 1');
	    	
	    	document.getElementById('DIVC_20_VAL_C38_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_20_VAL_C39_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_20_VAL_C40_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

	    	document.getElementById('DIVC_20_VAL_C47_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_20_VAL_C48_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_20_VAL_C49_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

	    	document.getElementById('DIVC_20_VAL_C41_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_20_VAL_C42_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_20_VAL_C43_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

	    	document.getElementById('DIVC_20_VAL_C50_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_20_VAL_C51_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_20_VAL_C52_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

	    	document.getElementById('DIVC_20_VAL_C44_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_20_VAL_C45_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_20_VAL_C46_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		
	    	document.getElementById('DIVC_20_VAL_C53_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_20_VAL_C54_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_20_VAL_C55_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		////////////////////////////////////////////////////////////////////
	    	document.getElementById('DIVC_20_VAL_C37_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

	    	document.getElementById('DIVC_20_VAL_C38_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_20_VAL_C39_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_20_VAL_C40_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

	    	document.getElementById('DIVC_20_VAL_C47_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_20_VAL_C48_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_20_VAL_C49_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

	    	document.getElementById('DIVC_20_VAL_C41_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_20_VAL_C42_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_20_VAL_C43_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

	    	document.getElementById('DIVC_20_VAL_C50_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_20_VAL_C51_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_20_VAL_C52_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

	    	document.getElementById('DIVC_20_VAL_C44_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_20_VAL_C45_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_20_VAL_C46_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		
	    	document.getElementById('DIVC_20_VAL_C53_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_20_VAL_C54_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_20_VAL_C55_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

    	}else{
	    	document.getElementById('DIVC_20_VAL_C37_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

	    	document.getElementById('DIVC_20_VAL_C38_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_20_VAL_C39_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_20_VAL_C40_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

	    	document.getElementById('DIVC_20_VAL_C47_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_20_VAL_C48_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_20_VAL_C49_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

	    	document.getElementById('DIVC_20_VAL_C41_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_20_VAL_C42_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_20_VAL_C43_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

	    	document.getElementById('DIVC_20_VAL_C50_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_20_VAL_C51_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_20_VAL_C52_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

	    	document.getElementById('DIVC_20_VAL_C44_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_20_VAL_C45_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_20_VAL_C46_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

	    	document.getElementById('DIVC_20_VAL_C53_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_20_VAL_C54_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_20_VAL_C55_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
            /////////////////////////////////////////////////////////////////////
	    	document.getElementById('DIVC_20_VAL_C37_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

	    	document.getElementById('DIVC_20_VAL_C38_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_20_VAL_C39_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_20_VAL_C40_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

	    	document.getElementById('DIVC_20_VAL_C47_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_20_VAL_C48_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_20_VAL_C49_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

	    	document.getElementById('DIVC_20_VAL_C41_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_20_VAL_C42_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_20_VAL_C43_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

	    	document.getElementById('DIVC_20_VAL_C50_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_20_VAL_C51_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_20_VAL_C52_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

	    	document.getElementById('DIVC_20_VAL_C44_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_20_VAL_C45_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_20_VAL_C46_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

	    	document.getElementById('DIVC_20_VAL_C53_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_20_VAL_C54_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_20_VAL_C55_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

    	}
    }
    else if(flexTabId == 21){
    	if(element == 'No'){
	    	document.getElementById('DIVC_21_VAL_C28_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

	    	document.getElementById('DIVC_21_VAL_C29_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_21_VAL_C30_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_21_VAL_C31_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

	    	document.getElementById('DIVC_21_VAL_C32_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_21_VAL_C33_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_21_VAL_C34_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

	    	document.getElementById('DIVC_21_VAL_C35_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_21_VAL_C36_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_21_VAL_C37_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

	    	document.getElementById('DIVC_21_VAL_C38_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_21_VAL_C39_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_21_VAL_C40_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

	    	document.getElementById('DIVC_21_VAL_C41_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_21_VAL_C42_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_21_VAL_C43_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

	    	document.getElementById('DIVC_21_VAL_C44_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_21_VAL_C45_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_21_VAL_C46_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		////////////////////////////////////////////////////////////////////
	    	document.getElementById('DIVC_21_VAL_C28_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

	    	document.getElementById('DIVC_21_VAL_C29_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_21_VAL_C30_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_21_VAL_C31_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

	    	document.getElementById('DIVC_21_VAL_C32_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_21_VAL_C33_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_21_VAL_C34_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

	    	document.getElementById('DIVC_21_VAL_C35_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_21_VAL_C36_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_21_VAL_C37_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

	    	document.getElementById('DIVC_21_VAL_C38_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_21_VAL_C39_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_21_VAL_C40_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

	    	document.getElementById('DIVC_21_VAL_C41_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_21_VAL_C42_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_21_VAL_C43_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

	    	document.getElementById('DIVC_21_VAL_C44_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_21_VAL_C45_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_21_VAL_C46_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';


    	}else{
	    	document.getElementById('DIVC_21_VAL_C28_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

	    	document.getElementById('DIVC_21_VAL_C29_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_21_VAL_C30_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_21_VAL_C31_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

	    	document.getElementById('DIVC_21_VAL_C32_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_21_VAL_C33_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_21_VAL_C34_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

	    	document.getElementById('DIVC_21_VAL_C35_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_21_VAL_C36_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_21_VAL_C37_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

	    	document.getElementById('DIVC_21_VAL_C38_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_21_VAL_C39_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_21_VAL_C40_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

	    	document.getElementById('DIVC_21_VAL_C41_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_21_VAL_C42_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_21_VAL_C43_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

	    	document.getElementById('DIVC_21_VAL_C44_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_21_VAL_C45_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_21_VAL_C46_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		/////////////////////////////////////////////////////////////////////
	    	document.getElementById('DIVC_21_VAL_C28_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

	    	document.getElementById('DIVC_21_VAL_C29_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_21_VAL_C30_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_21_VAL_C31_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

	    	document.getElementById('DIVC_21_VAL_C32_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_21_VAL_C33_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_21_VAL_C34_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

	    	document.getElementById('DIVC_21_VAL_C35_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_21_VAL_C36_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_21_VAL_C37_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

	    	document.getElementById('DIVC_21_VAL_C38_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_21_VAL_C39_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_21_VAL_C40_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

	    	document.getElementById('DIVC_21_VAL_C41_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_21_VAL_C42_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_21_VAL_C43_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

	    	document.getElementById('DIVC_21_VAL_C44_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_21_VAL_C45_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_21_VAL_C46_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

    	}
    }
    else if(flexTabId == 22){
    	if(element == 'No'){
	    	document.getElementById('DIVC_22_VAL_C35_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

	    	document.getElementById('DIVC_22_VAL_C36_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_22_VAL_C37_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_22_VAL_C38_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

	    	document.getElementById('DIVC_22_VAL_C39_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_22_VAL_C40_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_22_VAL_C41_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

	    	document.getElementById('DIVC_22_VAL_C42_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_22_VAL_C43_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_22_VAL_C44_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

    		document.getElementById('DIVC_22_VAL_C45_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_22_VAL_C46_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_22_VAL_C47_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

    		document.getElementById('DIVC_22_VAL_C48_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_22_VAL_C49_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_22_VAL_C50_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

    		document.getElementById('DIVC_22_VAL_C51_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_22_VAL_C52_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_22_VAL_C53_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		////////////////////////////////////////////////////////////////////
	    	document.getElementById('DIVC_22_VAL_C35_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

	    	document.getElementById('DIVC_22_VAL_C36_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_22_VAL_C37_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_22_VAL_C38_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

	    	document.getElementById('DIVC_22_VAL_C39_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_22_VAL_C40_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_22_VAL_C41_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

	    	document.getElementById('DIVC_22_VAL_C42_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_22_VAL_C43_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_22_VAL_C44_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

    		document.getElementById('DIVC_22_VAL_C45_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_22_VAL_C46_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_22_VAL_C47_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

    		document.getElementById('DIVC_22_VAL_C48_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_22_VAL_C49_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_22_VAL_C50_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

    		document.getElementById('DIVC_22_VAL_C51_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_22_VAL_C52_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_22_VAL_C53_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';


    	}else{
	    	document.getElementById('DIVC_22_VAL_C35_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

	    	document.getElementById('DIVC_22_VAL_C36_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_22_VAL_C37_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_22_VAL_C38_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

	    	document.getElementById('DIVC_22_VAL_C39_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_22_VAL_C40_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_22_VAL_C41_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

	    	document.getElementById('DIVC_22_VAL_C42_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_22_VAL_C43_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_22_VAL_C44_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

    		document.getElementById('DIVC_22_VAL_C45_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_22_VAL_C46_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_22_VAL_C47_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

    		document.getElementById('DIVC_22_VAL_C48_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_22_VAL_C49_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_22_VAL_C50_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

    		document.getElementById('DIVC_22_VAL_C51_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_22_VAL_C52_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_22_VAL_C53_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		/////////////////////////////////////////////////////////////////////
	    	document.getElementById('DIVC_22_VAL_C35_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

	    	document.getElementById('DIVC_22_VAL_C36_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_22_VAL_C37_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_22_VAL_C38_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

	    	document.getElementById('DIVC_22_VAL_C39_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_22_VAL_C40_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_22_VAL_C41_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

	    	document.getElementById('DIVC_22_VAL_C42_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_22_VAL_C43_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_22_VAL_C44_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

    		document.getElementById('DIVC_22_VAL_C45_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_22_VAL_C46_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_22_VAL_C47_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

    		document.getElementById('DIVC_22_VAL_C48_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_22_VAL_C49_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_22_VAL_C50_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

    		document.getElementById('DIVC_22_VAL_C51_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_22_VAL_C52_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_22_VAL_C53_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

    	}
    }else if(flexTabId == 23){
    	if(element == 'No'){
	    	document.getElementById('DIVC_23_VAL_C67_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

	    	document.getElementById('DIVC_23_VAL_C68_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_23_VAL_C69_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_23_VAL_C70_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

	    	document.getElementById('DIVC_23_VAL_C71_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_23_VAL_C72_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_23_VAL_C73_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

	    	document.getElementById('DIVC_23_VAL_C74_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_23_VAL_C75_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_23_VAL_C76_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

	    	document.getElementById('DIVC_23_VAL_C77_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_23_VAL_C78_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_23_VAL_C79_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

	    	document.getElementById('DIVC_23_VAL_C80_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_23_VAL_C81_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_23_VAL_C82_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

	    	document.getElementById('DIVC_23_VAL_C83_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_23_VAL_C84_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_23_VAL_C85_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		////////////////////////////////////////////////////////////////////
	    	document.getElementById('DIVC_23_VAL_C67_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

	    	document.getElementById('DIVC_23_VAL_C68_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_23_VAL_C69_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_23_VAL_C70_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

	    	document.getElementById('DIVC_23_VAL_C71_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_23_VAL_C72_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_23_VAL_C73_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

	    	document.getElementById('DIVC_23_VAL_C74_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_23_VAL_C75_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_23_VAL_C76_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

	    	document.getElementById('DIVC_23_VAL_C77_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_23_VAL_C78_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_23_VAL_C79_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

	    	document.getElementById('DIVC_23_VAL_C80_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_23_VAL_C81_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_23_VAL_C82_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

	    	document.getElementById('DIVC_23_VAL_C83_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_23_VAL_C84_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
    		document.getElementById('DIVC_23_VAL_C85_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

    	}else{
	    	document.getElementById('DIVC_23_VAL_C67_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

	    	document.getElementById('DIVC_23_VAL_C68_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_23_VAL_C69_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_23_VAL_C70_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

	    	document.getElementById('DIVC_23_VAL_C71_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_23_VAL_C72_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_23_VAL_C73_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

	    	document.getElementById('DIVC_23_VAL_C74_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_23_VAL_C75_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_23_VAL_C76_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

	    	document.getElementById('DIVC_23_VAL_C77_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_23_VAL_C78_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_23_VAL_C79_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

	    	document.getElementById('DIVC_23_VAL_C80_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_23_VAL_C81_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_23_VAL_C82_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

	    	document.getElementById('DIVC_23_VAL_C83_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_23_VAL_C84_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_23_VAL_C85_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		/////////////////////////////////////////////////////////////////////
	    	document.getElementById('DIVC_23_VAL_C67_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

	    	document.getElementById('DIVC_23_VAL_C68_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_23_VAL_C69_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_23_VAL_C70_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

	    	document.getElementById('DIVC_23_VAL_C71_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_23_VAL_C72_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_23_VAL_C73_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

	    	document.getElementById('DIVC_23_VAL_C74_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_23_VAL_C75_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_23_VAL_C76_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

	    	document.getElementById('DIVC_23_VAL_C77_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_23_VAL_C78_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_23_VAL_C79_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

	    	document.getElementById('DIVC_23_VAL_C80_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_23_VAL_C81_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_23_VAL_C82_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

	    	document.getElementById('DIVC_23_VAL_C83_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_23_VAL_C84_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		document.getElementById('DIVC_23_VAL_C85_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

    	}
    }else if(flexTabId == 27){
    	if(element == 'No'){
	    	document.getElementById('DIVC_27_VAL_C6_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_27_VAL_C7_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_27_VAL_C8_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	///////////////////////////////////////////////////////////////////
	    	document.getElementById('DIVC_27_VAL_C6_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_27_VAL_C7_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_27_VAL_C8_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	//NUEVOS
	    	document.getElementById('DIVC_27_VAL_C122_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_27_VAL_C123_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_27_VAL_C124_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_27_VAL_C125_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_27_VAL_C126_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_27_VAL_C127_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_27_VAL_C128_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_27_VAL_C129_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_27_VAL_C130_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_27_VAL_C131_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_27_VAL_C132_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_27_VAL_C133_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_27_VAL_C134_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_27_VAL_C135_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_27_VAL_C136_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_27_VAL_C137_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	
	    	document.getElementById('DIVC_27_VAL_C122_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_27_VAL_C123_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_27_VAL_C124_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_27_VAL_C125_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_27_VAL_C126_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_27_VAL_C127_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_27_VAL_C128_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_27_VAL_C129_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_27_VAL_C130_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_27_VAL_C131_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_27_VAL_C132_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_27_VAL_C133_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_27_VAL_C134_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_27_VAL_C135_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_27_VAL_C136_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_27_VAL_C137_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	

    	}else{
	    	document.getElementById('DIVC_27_VAL_C6_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_27_VAL_C7_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_27_VAL_C8_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	///////////////////////////////////////////////////////////////////
	    	document.getElementById('DIVC_27_VAL_C6_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_27_VAL_C7_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_27_VAL_C8_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	//NUEVOS
	    	document.getElementById('DIVC_27_VAL_C122_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_27_VAL_C123_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_27_VAL_C124_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_27_VAL_C125_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_27_VAL_C126_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_27_VAL_C127_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_27_VAL_C128_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_27_VAL_C129_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_27_VAL_C130_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_27_VAL_C131_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_27_VAL_C132_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_27_VAL_C133_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_27_VAL_C134_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_27_VAL_C135_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_27_VAL_C136_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_27_VAL_C137_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	
	    	document.getElementById('DIVC_27_VAL_C122_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_27_VAL_C123_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_27_VAL_C124_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_27_VAL_C125_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_27_VAL_C126_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_27_VAL_C127_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_27_VAL_C128_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_27_VAL_C129_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_27_VAL_C130_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_27_VAL_C131_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_27_VAL_C132_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_27_VAL_C133_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_27_VAL_C134_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_27_VAL_C135_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_27_VAL_C136_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_27_VAL_C137_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

    	}
    }else if(flexTabId == 28){
    	if(element == 'No'){
	    	document.getElementById('DIVC_28_VAL_C7_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_28_VAL_C8_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_28_VAL_C9_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_28_VAL_C10_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_28_VAL_C11_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	////////////////////////////////////////////////////////////////////
	    	document.getElementById('DIVC_28_VAL_C7_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_28_VAL_C8_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_28_VAL_C9_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_28_VAL_C10_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_28_VAL_C11_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

	    	//NUEVOS
	    	
	    	document.getElementById('DIVC_28_VAL_C122_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_28_VAL_C123_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_28_VAL_C124_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_28_VAL_C126_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_28_VAL_C127_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_28_VAL_C128_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_28_VAL_C129_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_28_VAL_C130_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_28_VAL_C131_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_28_VAL_C132_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_28_VAL_C134_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_28_VAL_C135_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_28_VAL_C136_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_28_VAL_C137_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	
	    	
	    	document.getElementById('DIVC_28_VAL_C122_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_28_VAL_C123_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_28_VAL_C124_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_28_VAL_C126_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_28_VAL_C127_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_28_VAL_C128_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_28_VAL_C129_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_28_VAL_C130_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_28_VAL_C131_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_28_VAL_C132_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_28_VAL_C134_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_28_VAL_C135_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_28_VAL_C136_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_28_VAL_C137_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	

    	}else{
	    	document.getElementById('DIVC_28_VAL_C7_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_28_VAL_C8_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_28_VAL_C9_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_28_VAL_C10_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_28_VAL_C11_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	////////////////////////////////////////////////////////////////////
	    	document.getElementById('DIVC_28_VAL_C7_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_28_VAL_C8_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_28_VAL_C9_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_28_VAL_C10_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_28_VAL_C11_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	
	    	//NUEVOS
	    	
	    	document.getElementById('DIVC_28_VAL_C122_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_28_VAL_C123_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_28_VAL_C124_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_28_VAL_C126_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_28_VAL_C127_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_28_VAL_C128_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_28_VAL_C129_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_28_VAL_C130_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_28_VAL_C131_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_28_VAL_C132_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_28_VAL_C134_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_28_VAL_C135_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_28_VAL_C136_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_28_VAL_C137_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	
	    	
	    	document.getElementById('DIVC_28_VAL_C122_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_28_VAL_C123_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_28_VAL_C124_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_28_VAL_C126_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_28_VAL_C127_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_28_VAL_C128_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_28_VAL_C129_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_28_VAL_C130_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_28_VAL_C131_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_28_VAL_C132_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_28_VAL_C134_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_28_VAL_C135_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_28_VAL_C136_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_28_VAL_C137_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		
    	}
    }else if(flexTabId == 29){
    	if(element == 'No'){
	    	document.getElementById('DIVC_29_VAL_C19_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_29_VAL_C20_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_29_VAL_C21_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_29_VAL_C22_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_29_VAL_C23_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	////////////////////////////////////////////////////////////////////
	    	document.getElementById('DIVC_29_VAL_C19_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_29_VAL_C20_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_29_VAL_C21_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_29_VAL_C22_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_29_VAL_C23_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	//NUEVOS
	    	
	    	document.getElementById('DIVC_29_VAL_C122_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_29_VAL_C123_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_29_VAL_C124_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_29_VAL_C126_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_29_VAL_C127_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_29_VAL_C128_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_29_VAL_C129_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_29_VAL_C130_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_29_VAL_C131_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_29_VAL_C132_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_29_VAL_C134_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_29_VAL_C135_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_29_VAL_C136_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_29_VAL_C137_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	
	    	document.getElementById('DIVC_29_VAL_C122_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_29_VAL_C123_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_29_VAL_C124_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_29_VAL_C126_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_29_VAL_C127_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_29_VAL_C128_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_29_VAL_C129_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_29_VAL_C130_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_29_VAL_C131_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_29_VAL_C132_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_29_VAL_C134_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_29_VAL_C135_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_29_VAL_C136_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_29_VAL_C137_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

    	}else{
	    	document.getElementById('DIVC_29_VAL_C19_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_29_VAL_C20_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_29_VAL_C21_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_29_VAL_C22_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_29_VAL_C23_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	////////////////////////////////////////////////////////////////////
	    	document.getElementById('DIVC_29_VAL_C19_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_29_VAL_C20_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_29_VAL_C21_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_29_VAL_C22_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_29_VAL_C23_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	
//NUEVOS
	    	
	    	document.getElementById('DIVC_29_VAL_C122_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_29_VAL_C123_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_29_VAL_C124_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_29_VAL_C126_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_29_VAL_C127_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_29_VAL_C128_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_29_VAL_C129_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_29_VAL_C130_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_29_VAL_C131_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_29_VAL_C132_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_29_VAL_C134_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_29_VAL_C135_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_29_VAL_C136_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_29_VAL_C137_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	
	    	document.getElementById('DIVC_29_VAL_C122_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_29_VAL_C123_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_29_VAL_C124_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_29_VAL_C126_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_29_VAL_C127_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_29_VAL_C128_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_29_VAL_C129_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_29_VAL_C130_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_29_VAL_C131_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_29_VAL_C132_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_29_VAL_C134_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_29_VAL_C135_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_29_VAL_C136_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_29_VAL_C137_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		
    	}
    }else if(flexTabId == 30){
    	if(element == 'No'){
    		
	    	document.getElementById('DIVC_30_VAL_C15_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_30_VAL_C16_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_30_VAL_C17_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_30_VAL_C18_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	////////////////////////////////////////////////////////////////////
	    	document.getElementById('DIVC_30_VAL_C15_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_30_VAL_C16_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_30_VAL_C17_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_30_VAL_C18_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	//NUEVOS JJAQ 12/04/2017
	    	document.getElementById('DIVC_30_VAL_C122_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_30_VAL_C123_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_30_VAL_C124_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_30_VAL_C126_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_30_VAL_C127_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_30_VAL_C128_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_30_VAL_C129_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_30_VAL_C130_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_30_VAL_C131_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_30_VAL_C132_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_30_VAL_C133_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_30_VAL_C134_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_30_VAL_C135_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_30_VAL_C136_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_30_VAL_C137_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	
	    	document.getElementById('DIVC_30_VAL_C122_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_30_VAL_C123_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_30_VAL_C124_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_30_VAL_C126_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_30_VAL_C127_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_30_VAL_C128_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_30_VAL_C129_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_30_VAL_C130_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_30_VAL_C131_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_30_VAL_C132_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_30_VAL_C133_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_30_VAL_C134_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_30_VAL_C135_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_30_VAL_C136_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_30_VAL_C137_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

    	}else{
	    	document.getElementById('DIVC_30_VAL_C15_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_30_VAL_C16_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_30_VAL_C17_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_30_VAL_C18_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	////////////////////////////////////////////////////////////////////
	    	document.getElementById('DIVC_30_VAL_C15_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_30_VAL_C16_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_30_VAL_C17_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_30_VAL_C18_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	//NUEVOS JJAQ 12/04/2017
	    	document.getElementById('DIVC_30_VAL_C122_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_30_VAL_C123_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_30_VAL_C124_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_30_VAL_C126_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_30_VAL_C127_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_30_VAL_C128_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_30_VAL_C129_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_30_VAL_C130_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_30_VAL_C131_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_30_VAL_C132_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_30_VAL_C133_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_30_VAL_C134_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_30_VAL_C135_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_30_VAL_C136_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_30_VAL_C137_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	
	    	document.getElementById('DIVC_30_VAL_C122_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_30_VAL_C123_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_30_VAL_C124_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_30_VAL_C126_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_30_VAL_C127_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_30_VAL_C128_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_30_VAL_C129_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_30_VAL_C130_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_30_VAL_C131_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_30_VAL_C132_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_30_VAL_C133_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_30_VAL_C134_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_30_VAL_C135_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_30_VAL_C136_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_30_VAL_C137_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    	}
    }else if(flexTabId == 31){
    	if(element == 'No'){
	    	document.getElementById('DIVC_31_VAL_C9_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_31_VAL_C10_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_31_VAL_C11_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_31_VAL_C12_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_31_VAL_C13_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	//ULR nuevos 17/04/2017
	    	document.getElementById('DIVC_31_VAL_C122_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_31_VAL_C123_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_31_VAL_C124_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_31_VAL_C126_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_31_VAL_C127_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_31_VAL_C128_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_31_VAL_C129_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_31_VAL_C130_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_31_VAL_C131_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_31_VAL_C132_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_31_VAL_C134_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_31_VAL_C135_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_31_VAL_C136_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_31_VAL_C137_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	////////////////////////////////////////////////////////////////////
	    	document.getElementById('DIVC_31_VAL_C9_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_31_VAL_C10_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_31_VAL_C11_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_31_VAL_C12_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_31_VAL_C13_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	//ULR nuevos 17/04/2017
	    	document.getElementById('DIVC_31_VAL_C122_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_31_VAL_C123_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_31_VAL_C124_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_31_VAL_C126_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_31_VAL_C127_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_31_VAL_C128_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_31_VAL_C129_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_31_VAL_C130_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_31_VAL_C131_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_31_VAL_C132_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_31_VAL_C134_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_31_VAL_C135_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_31_VAL_C136_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_31_VAL_C137_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

    	}else{
	    	document.getElementById('DIVC_31_VAL_C9_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_31_VAL_C10_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_31_VAL_C11_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_31_VAL_C12_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_31_VAL_C13_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	//ULR nuevos 17/04/2017
	    	document.getElementById('DIVC_31_VAL_C122_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_31_VAL_C123_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_31_VAL_C124_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_31_VAL_C126_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_31_VAL_C127_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_31_VAL_C128_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_31_VAL_C129_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_31_VAL_C130_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_31_VAL_C131_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_31_VAL_C132_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_31_VAL_C134_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_31_VAL_C135_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_31_VAL_C136_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_31_VAL_C137_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	////////////////////////////////////////////////////////////////////
	    	document.getElementById('DIVC_31_VAL_C9_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_31_VAL_C10_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_31_VAL_C11_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_31_VAL_C12_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_31_VAL_C13_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	//ULR nuevos 17/04/2017
	    	document.getElementById('DIVC_31_VAL_C122_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_31_VAL_C123_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_31_VAL_C124_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_31_VAL_C126_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_31_VAL_C127_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_31_VAL_C128_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_31_VAL_C129_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_31_VAL_C130_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_31_VAL_C131_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_31_VAL_C132_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_31_VAL_C134_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_31_VAL_C135_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_31_VAL_C136_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_31_VAL_C137_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    	}
    }else if(flexTabId == 32){
    	if(element == 'No'){
	    	document.getElementById('DIVC_32_VAL_C19_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_32_VAL_C20_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_32_VAL_C21_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_32_VAL_C22_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_32_VAL_C23_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	//ULR 17-04-2017 nuevos
	    	document.getElementById('DIVC_32_VAL_C122_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_32_VAL_C123_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_32_VAL_C124_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_32_VAL_C126_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_32_VAL_C127_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_32_VAL_C128_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_32_VAL_C129_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_32_VAL_C130_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_32_VAL_C131_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_32_VAL_C132_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_32_VAL_C134_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_32_VAL_C135_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_32_VAL_C136_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_32_VAL_C137_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	////////////////////////////////////////////////////////////////////
	    	document.getElementById('DIVC_32_VAL_C19_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_32_VAL_C20_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_32_VAL_C21_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_32_VAL_C22_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_32_VAL_C23_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	//ULR 17-04-2017 nuevos
	    	document.getElementById('DIVC_32_VAL_C122_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_32_VAL_C123_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_32_VAL_C124_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_32_VAL_C126_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_32_VAL_C127_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_32_VAL_C128_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_32_VAL_C129_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_32_VAL_C130_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_32_VAL_C131_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_32_VAL_C132_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_32_VAL_C134_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_32_VAL_C135_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_32_VAL_C136_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_32_VAL_C137_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

    	}else{
	    	document.getElementById('DIVC_32_VAL_C19_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_32_VAL_C20_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_32_VAL_C21_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_32_VAL_C22_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_32_VAL_C23_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	//ULR 17-04-2017 nuevos
	    	document.getElementById('DIVC_32_VAL_C122_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_32_VAL_C123_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_32_VAL_C124_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_32_VAL_C126_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_32_VAL_C127_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_32_VAL_C128_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_32_VAL_C129_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_32_VAL_C130_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_32_VAL_C131_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_32_VAL_C132_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_32_VAL_C134_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_32_VAL_C135_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_32_VAL_C136_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_32_VAL_C137_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	////////////////////////////////////////////////////////////////////
	    	document.getElementById('DIVC_32_VAL_C19_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_32_VAL_C20_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_32_VAL_C21_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_32_VAL_C22_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_32_VAL_C23_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	//ULR 17-04-2017 nuevos
	    	document.getElementById('DIVC_32_VAL_C122_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_32_VAL_C123_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_32_VAL_C124_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_32_VAL_C126_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_32_VAL_C127_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_32_VAL_C128_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_32_VAL_C129_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_32_VAL_C130_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_32_VAL_C131_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_32_VAL_C132_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_32_VAL_C134_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_32_VAL_C135_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_32_VAL_C136_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_32_VAL_C137_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

    	}
    }else if(flexTabId == 33){
    	if(element == 'No'){
	    	document.getElementById('DIVC_33_VAL_C21_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_33_VAL_C22_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_33_VAL_C23_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_33_VAL_C24_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_33_VAL_C25_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	//Nuevos ULR 19-04-2017
	    	document.getElementById('DIVC_33_VAL_C122_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_33_VAL_C123_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_33_VAL_C124_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_33_VAL_C126_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_33_VAL_C127_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_33_VAL_C128_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_33_VAL_C129_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_33_VAL_C130_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_33_VAL_C131_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_33_VAL_C132_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_33_VAL_C134_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_33_VAL_C135_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_33_VAL_C136_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_33_VAL_C137_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	////////////////////////////////////////////////////////////////////
	    	document.getElementById('DIVC_33_VAL_C21_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_33_VAL_C22_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_33_VAL_C23_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_33_VAL_C24_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_33_VAL_C25_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	//Nuevos ULR 19-04-2017
	    	document.getElementById('DIVC_33_VAL_C122_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_33_VAL_C123_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_33_VAL_C124_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_33_VAL_C126_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_33_VAL_C127_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_33_VAL_C128_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_33_VAL_C129_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_33_VAL_C130_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_33_VAL_C131_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_33_VAL_C132_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_33_VAL_C134_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_33_VAL_C135_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_33_VAL_C136_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_33_VAL_C137_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

    	}else{
	    	document.getElementById('DIVC_33_VAL_C21_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_33_VAL_C22_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_33_VAL_C23_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_33_VAL_C24_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_33_VAL_C25_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	//Nuevos ULR 19-04-2017
	    	document.getElementById('DIVC_33_VAL_C122_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_33_VAL_C123_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_33_VAL_C124_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_33_VAL_C126_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_33_VAL_C127_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_33_VAL_C128_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_33_VAL_C129_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_33_VAL_C130_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_33_VAL_C131_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_33_VAL_C132_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_33_VAL_C134_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_33_VAL_C135_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_33_VAL_C136_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_33_VAL_C137_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	////////////////////////////////////////////////////////////////////
	    	document.getElementById('DIVC_33_VAL_C21_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_33_VAL_C22_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_33_VAL_C23_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_33_VAL_C24_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_33_VAL_C25_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	//Nuevos ULR 19-04-2017
	    	document.getElementById('DIVC_33_VAL_C122_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_33_VAL_C123_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_33_VAL_C124_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_33_VAL_C126_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_33_VAL_C127_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_33_VAL_C128_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_33_VAL_C129_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_33_VAL_C130_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_33_VAL_C131_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_33_VAL_C132_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_33_VAL_C134_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_33_VAL_C135_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_33_VAL_C136_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_33_VAL_C137_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

    	}
    }else if(flexTabId == 34){
    	if(element == 'No'){
	    	document.getElementById('DIVC_34_VAL_C23_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_34_VAL_C24_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_34_VAL_C25_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_34_VAL_C26_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_34_VAL_C27_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	//Nuevos ULR 19-04-2017
	    	document.getElementById('DIVC_34_VAL_C122_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_34_VAL_C123_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_34_VAL_C124_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_34_VAL_C126_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_34_VAL_C127_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_34_VAL_C128_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_34_VAL_C129_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_34_VAL_C130_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_34_VAL_C131_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_34_VAL_C132_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_34_VAL_C134_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_34_VAL_C135_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_34_VAL_C136_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_34_VAL_C137_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	////////////////////////////////////////////////////////////////////
	    	document.getElementById('DIVC_34_VAL_C23_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_34_VAL_C24_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_34_VAL_C25_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_34_VAL_C26_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_34_VAL_C27_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	//Nuevos ULR 19-04-2017
	    	document.getElementById('DIVC_34_VAL_C122_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_34_VAL_C123_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_34_VAL_C124_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_34_VAL_C126_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_34_VAL_C127_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_34_VAL_C128_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_34_VAL_C129_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_34_VAL_C130_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_34_VAL_C131_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_34_VAL_C132_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_34_VAL_C134_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_34_VAL_C135_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_34_VAL_C136_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_34_VAL_C137_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	

    	}else{
	    	document.getElementById('DIVC_34_VAL_C23_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_34_VAL_C24_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_34_VAL_C25_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_34_VAL_C26_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_34_VAL_C27_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	//Nuevos ULR 19-04-2017
	    	document.getElementById('DIVC_34_VAL_C122_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_34_VAL_C123_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_34_VAL_C124_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_34_VAL_C126_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_34_VAL_C127_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_34_VAL_C128_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_34_VAL_C129_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_34_VAL_C130_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_34_VAL_C131_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_34_VAL_C132_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_34_VAL_C134_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_34_VAL_C135_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_34_VAL_C136_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_34_VAL_C137_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	////////////////////////////////////////////////////////////////////
	    	document.getElementById('DIVC_34_VAL_C23_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_34_VAL_C24_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_34_VAL_C25_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_34_VAL_C26_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_34_VAL_C27_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	//Nuevos ULR 19-04-2017
	    	document.getElementById('DIVC_34_VAL_C122_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_34_VAL_C123_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_34_VAL_C124_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_34_VAL_C126_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_34_VAL_C127_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_34_VAL_C128_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_34_VAL_C129_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_34_VAL_C130_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_34_VAL_C131_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_34_VAL_C132_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_34_VAL_C134_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_34_VAL_C135_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_34_VAL_C136_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_34_VAL_C137_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
    		
    	}
    }else if(flexTabId == 35){
    	if(element == 'No'){
	    	document.getElementById('DIVC_35_VAL_C8_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_35_VAL_C9_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_35_VAL_C10_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_35_VAL_C11_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_35_VAL_C12_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	//Nuevos ULR 19-04-2017
	    	document.getElementById('DIVC_35_VAL_C122_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_35_VAL_C123_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_35_VAL_C124_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_35_VAL_C126_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_35_VAL_C127_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_35_VAL_C128_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_35_VAL_C129_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_35_VAL_C130_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_35_VAL_C131_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_35_VAL_C132_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_35_VAL_C134_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_35_VAL_C135_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_35_VAL_C136_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_35_VAL_C137_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	////////////////////////////////////////////////////////////////////
	    	document.getElementById('DIVC_35_VAL_C8_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_35_VAL_C9_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_35_VAL_C10_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_35_VAL_C11_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_35_VAL_C12_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	//Nuevos ULR 19-04-2017
	    	document.getElementById('DIVC_35_VAL_C122_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_35_VAL_C123_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_35_VAL_C124_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_35_VAL_C126_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_35_VAL_C127_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_35_VAL_C128_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_35_VAL_C129_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_35_VAL_C130_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_35_VAL_C131_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_35_VAL_C132_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_35_VAL_C134_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_35_VAL_C135_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_35_VAL_C136_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_35_VAL_C137_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	

    	}else{
	    	document.getElementById('DIVC_35_VAL_C8_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_35_VAL_C9_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_35_VAL_C10_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_35_VAL_C11_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_35_VAL_C12_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	//Nuevos ULR 19-04-2017
	    	document.getElementById('DIVC_35_VAL_C122_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_35_VAL_C123_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_35_VAL_C124_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_35_VAL_C126_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_35_VAL_C127_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_35_VAL_C128_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_35_VAL_C129_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_35_VAL_C130_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_35_VAL_C131_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_35_VAL_C132_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_35_VAL_C134_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_35_VAL_C135_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_35_VAL_C136_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_35_VAL_C137_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	////////////////////////////////////////////////////////////////////
	    	document.getElementById('DIVC_35_VAL_C8_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_35_VAL_C9_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_35_VAL_C10_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_35_VAL_C11_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_35_VAL_C12_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	//Nuevos ULR 19-04-2017
	    	document.getElementById('DIVC_35_VAL_C122_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_35_VAL_C123_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_35_VAL_C124_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_35_VAL_C126_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_35_VAL_C127_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_35_VAL_C128_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_35_VAL_C129_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_35_VAL_C130_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_35_VAL_C131_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_35_VAL_C132_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_35_VAL_C134_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_35_VAL_C135_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_35_VAL_C136_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_35_VAL_C137_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	

    	}
    }else if(flexTabId == 41){
    	if(element == 'No'){
	    	document.getElementById('DIVC_41_VAL_C8_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_41_VAL_C9_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_41_VAL_C10_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_41_VAL_C11_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_41_VAL_C12_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	//Nuevos ULR 19-04-2017
	    	document.getElementById('DIVC_41_VAL_C122_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_41_VAL_C123_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_41_VAL_C124_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_41_VAL_C126_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_41_VAL_C127_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_41_VAL_C128_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_41_VAL_C129_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_41_VAL_C130_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_41_VAL_C131_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_41_VAL_C132_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_41_VAL_C134_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_41_VAL_C135_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_41_VAL_C136_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_41_VAL_C137_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	////////////////////////////////////////////////////////////////////
	    	document.getElementById('DIVC_41_VAL_C8_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_41_VAL_C9_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_41_VAL_C10_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_41_VAL_C11_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_41_VAL_C12_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	//Nuevos ULR 19-04-2017
	    	document.getElementById('DIVC_41_VAL_C122_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_41_VAL_C123_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_41_VAL_C124_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_41_VAL_C126_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_41_VAL_C127_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_41_VAL_C128_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_41_VAL_C129_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_41_VAL_C130_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_41_VAL_C131_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_41_VAL_C132_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_41_VAL_C134_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_41_VAL_C135_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_41_VAL_C136_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';
	    	document.getElementById('DIVC_41_VAL_C137_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'none';

    	}else{
	    	document.getElementById('DIVC_41_VAL_C8_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_41_VAL_C9_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_41_VAL_C10_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_41_VAL_C11_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_41_VAL_C12_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	//Nuevos ULR 19-04-2017
	    	document.getElementById('DIVC_41_VAL_C122_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_41_VAL_C123_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_41_VAL_C124_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_41_VAL_C126_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_41_VAL_C127_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_41_VAL_C128_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_41_VAL_C129_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_41_VAL_C130_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_41_VAL_C131_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_41_VAL_C132_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_41_VAL_C134_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_41_VAL_C135_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_41_VAL_C136_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_41_VAL_C137_2'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	////////////////////////////////////////////////////////////////////
	    	document.getElementById('DIVC_41_VAL_C8_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_41_VAL_C9_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_41_VAL_C10_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_41_VAL_C11_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_41_VAL_C12_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	//Nuevos ULR 19-04-2017
	    	document.getElementById('DIVC_41_VAL_C122_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_41_VAL_C123_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_41_VAL_C124_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_41_VAL_C126_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_41_VAL_C127_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_41_VAL_C128_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_41_VAL_C129_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_41_VAL_C130_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_41_VAL_C131_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_41_VAL_C132_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_41_VAL_C134_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_41_VAL_C135_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_41_VAL_C136_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';
	    	document.getElementById('DIVC_41_VAL_C137_1'.replace('DIVC_'+alterFlexTableId, 'DIVC_'+globalFlexTableId)).style.display = 'block';

    	}
    }

}

function aumentoOdisminucion(elementId1,elementId2,element){
	var dismCF = document.getElementById(elementId1);
	var dismCV = document.getElementById(elementId2);
	
	if(element.value == 'Si'){
		if(dismCF.value == 'Si' || dismCV.value == 'Si'){	
			swal('Solo se puede capturar el aumento o la disminuci\u00f3n');
			dismCV.checked = false;
			dismCF.checked = false;
			isChecked(dismCF);
			isChecked(dismCV);
			if(elementId1 == '34__VAL_C43' && elementId2 == '34__VAL_C47'){
				habilitarFusionDisminucionCapitalFijo(dismCF);
				habilitarFusionDisminucionCapitalVariable(dismCV);
			}
			if(elementId1 == '34__VAL_C9' && elementId2 == '34__VAL_C13'){
				habilitarFusionAumentoCapitalFijo(dismCF);
				habilitarFusionAumentoCapitalVariable(dismCV);
			}
			
			////
			//habilitarFusionAumentoCapitalFijo(dismCF);
			//habilitarFusionAumentoCapitalVariable(dismCV);
		}
	}
}

//ECM 25 Febrero 2016 Fusion - Mostrar Capital Fijo, Variable, Social - FLEX 34
//ECM 26 Julio 2016 Fusion - Mostrar Capital Fijo, Variable de Aumento y Disminución - FLEX 34
function habilitarFusionAumentoCapitalFijo(element){
	aumentoOdisminucion('34__VAL_C43','34__VAL_C47',element);
	
	mostrarFusionAumentoCapitalFijo(element.value);
}

function habilitarFusionAumentoCapitalVariable(element){
	aumentoOdisminucion('34__VAL_C43','34__VAL_C47',element);
	
	mostrarFusionAumentoCapitalVariable(element.value);
}

function habilitarFusionDisminucionCapitalFijo(element){
	aumentoOdisminucion('34__VAL_C9','34__VAL_C13',element);
	mostrarFusionDisminucionCapitalFijo(element.value);
}

function habilitarFusionDisminucionCapitalVariable(element){
	aumentoOdisminucion('34__VAL_C9','34__VAL_C13',element);
	mostrarFusionDisminucionCapitalVariable(element.value);
}

function mostrarFusionAumentoCapitalFijo(valor){
	
	if(valor == 'Si'){
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C10_1').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C11_1').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C12_1').style.display = 'block';
		////////////////////////////////////////////////////////////////////
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C10_2').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C11_2').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C12_2').style.display = 'block';
		
	}else{
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C10_1').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C11_1').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C12_1').style.display = 'none';
		////////////////////////////////////////////////////////////////////
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C10_2').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C11_2').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C12_2').style.display = 'none';

	}
}

function mostrarFusionAumentoCapitalVariable(valor){
	if(valor == 'Si'){
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C14_1').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C15_1').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C16_1').style.display = 'block';
		/////////////////////////////////////////////////////////////////////
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C14_2').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C15_2').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C16_2').style.display = 'block';

	}else{
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C14_1').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C15_1').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C16_1').style.display = 'none';
		/////////////////////////////////////////////////////////////////////
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C14_2').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C15_2').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C16_2').style.display = 'none';

	}
}

function mostrarFusionDisminucionCapitalFijo(valor){
	if(valor == 'Si'){
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C44_1').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C45_1').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C46_1').style.display = 'block';
		/////////////////////////////////////////////////////////////////////
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C44_2').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C45_2').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C46_2').style.display = 'block';

	}else{
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C44_1').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C45_1').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C46_1').style.display = 'none';
		/////////////////////////////////////////////////////////////////////
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C44_2').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C45_2').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C46_2').style.display = 'none';

	}
}

function mostrarFusionDisminucionCapitalVariable(valor){
	if(valor == 'Si'){
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C48_1').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C49_1').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C50_1').style.display = 'block';
		/////////////////////////////////////////////////////////////////////
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C48_2').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C49_2').style.display = 'block';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C50_2').style.display = 'block';

	}else{
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C48_1').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C49_1').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C50_1').style.display = 'none';
		/////////////////////////////////////////////////////////////////////
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C48_2').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C49_2').style.display = 'none';
		document.getElementById('DIVC_'+globalFlexTableId+'_VAL_C50_2').style.display = 'none';

	}
}

//ECM 20 Julio 2016 - REFORMAS CAPTURA AES
function checkResultado(chResultado){
		//alert(chResultado.checked);
		//alert(chResultado.value);
		//alert(chResultado.id);
		//document.getElementById('23__VAL_C9').checked = falsedocument.getElementById('23__VAL_C9').checked = false;

		if (chResultado.id == '23__VAL_C9'){
			
			
			
			if(chResultado.value = 'Si'){
				document.getElementById('23__VAL_C7').checked = false;
				document.getElementById('23__VAL_C7').value = 'No';
			}
			if(!chResultado.checked){
				document.getElementById('23__VAL_C9').checked = false;
				document.getElementById('23__VAL_C9').value = 'No';
			}
		}
			

		if(chResultado.id == '23__VAL_C7'){
			
			
			if(chResultado.value = 'Si'){
				document.getElementById('23__VAL_C9').checked = false;
				document.getElementById('23__VAL_C9').value = 'No';
			}
			
			if(!chResultado.checked){
				document.getElementById('23__VAL_C7').checked = false;
				document.getElementById('23__VAL_C7').value = 'No';
			}
		
		}
}


//ULR 20/01/2017 se agrego esta funcion para ocultar y mostrar contactos
function hideShowContactos(elementId, imgMax, imgMin) {
	
	switch(elementId){
	case 'fieldset_tbl_stat_5':
		document.getElementById(elementId).style.display = '';
		document.getElementById(imgMax).style.display = 'none';
		document.getElementById(imgMin).style.display = '';
		document.getElementById('div_tbl_stat_5').style.display = 'none';
		break;
	case 'div_tbl_stat_5':
		document.getElementById(elementId).style.display = '';
		document.getElementById(imgMax).style.display = '';
		document.getElementById(imgMin).style.display = 'none';
		document.getElementById('fieldset_tbl_stat_5').style.display = 'none';
		break;
	}
 }
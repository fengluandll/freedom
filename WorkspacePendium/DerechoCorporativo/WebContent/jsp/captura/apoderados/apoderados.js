window.onload = function(){
	
}


function soloNumeros(e){
	
	key=e.keyCode || e.wich;
	teclado =String.fromCharCode(key);
	numeros="0123456789";
	especiales = "8-37-38-46";//array
	teclado_especial=false;
	for(var i in especiales){
		if(key==especiales[i]){
			teclado_especial=true;
		}
	}
	if(numeros.indexOf(teclado)==-1 && !teclado_especial){
		return false;
	}

}
/**
 * JJAQ Funcionalidad para captar evento en el combo
 *  del tipo poder en la pestana de apoderados
 */
function selectTipoPoderChanged(){ 
	
	var escrituraId = selectEscritura.value;
    var gpoApoderId = selectGrupoApo.value;
    if(escrituraId != '0' && gpoApoderId != '0'){
    	performSearch();
    }else{
    	//swal('Es requerido seleccionar la escritura y Grupos apoderados')
    }
	
}

function openPopup(){
	var idEscr = document.getElementById('selectEscritura').value;
	if(idEscr == 0){
		swal('Selecciona una escritura.');
	}else{
	window.open('/DerechoCorporativo/faces/jsp/captura/apoderados/showPreviewApoderados.jsp'
			,null
			,'height=900,width=1900,status=yes,toolbar=no,menubar=no,location=no');
	}
}

function reloadEscrituras(){
	
	var escrituraId = selectEscritura.value;
	var tipoPoderId = selectTipoPoder.value;
    var grupo       = selectGrupoApo.value;
	if(escrituraId != '0' && grupo != '0' && tipoPoderId != '0'){
		performSearch();
		reloadPasarelaCampos();
		reloadTipoPoder();
		var ajaxRequest = simpleAjaxUtil_getXmlHttpObject();     
	    if (ajaxRequest==null) {alert ('Browser does not support HTTP Request');return;}
		
	    //var url = 'catEscrituras.jsp';
	    
	    //var chanelId = selectCanal.value;
	
	   var parameters = ''; //'chanelId='+chanelId;
	
	    ajaxRequest.open('post','apoderados/catEscrituras.jsp', true);
	    ajaxRequest.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
	    ajaxRequest.onreadystatechange=function(){
	    
		    if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
	
			    var response = ajaxRequest.responseText;
			    document.getElementById('selectEscrituraDiv').innerHTML = response;
	
		      }
	    };    
	    ajaxRequest.send(parameters);
	}else{
		document.getElementById('apoderadosInfoDiv').innerHTML = '';
	}
		
    
}

function reloadGpoApoderados(){
	var ajaxRequest = simpleAjaxUtil_getXmlHttpObject();     
    if (ajaxRequest==null) {alert ('Browser does not support HTTP Request');return;}
	
    //var url = 'catEscrituras.jsp';
    
    //var chanelId = selectCanal.value;

   var parameters = ''; //'chanelId='+chanelId;

    ajaxRequest.open('post','apoderados/catGrupApo.jsp', true);
    ajaxRequest.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
    ajaxRequest.onreadystatechange=function(){
    
	    if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){

		    var response = ajaxRequest.responseText;
		    document.getElementById('inputGrupoDiv').innerHTML = response;

	      }
    };    
    ajaxRequest.send(parameters);
}

function reloadTipoPoder(){
	var ajaxRequest = simpleAjaxUtil_getXmlHttpObject();     
    if (ajaxRequest==null) {alert ('Browser does not support HTTP Request');return;}
	
    //var url = 'catEscrituras.jsp';
    
    //var chanelId = selectCanal.value;

   var parameters = ''; //'chanelId='+chanelId;

    ajaxRequest.open('post','apoderados/catTipoPoder.jsp', true);
    ajaxRequest.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
    ajaxRequest.onreadystatechange=function(){
    
	    if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){

		    var response = ajaxRequest.responseText;
		    document.getElementById('selectTipoPoderDiv').innerHTML = response;

	      }
    };    
    ajaxRequest.send(parameters);
}

function reloadPasarelaCampos(){
	var ajaxRequest = simpleAjaxUtil_getXmlHttpObject();     
    if (ajaxRequest==null) {alert ('Browser does not support HTTP Request');return;}
	
    //var url = 'catEscrituras.jsp';
    
    //var chanelId = selectCanal.value;

   var parameters = ''; //'chanelId='+chanelId;

    ajaxRequest.open('post','apoderados/apoderadosInfo.jsp', true);
    ajaxRequest.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
    ajaxRequest.onreadystatechange=function(){
    
	    if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){

		    var response = ajaxRequest.responseText;
		    document.getElementById('apoderadosInfoDiv').innerHTML = response;

	      }
    };    
    ajaxRequest.send(parameters);
}



function reloadCatalogos() {
	
	reloadCatalogo('apoderados/catEscrituras.jsp', 'selectEscrituraDiv');
	
	reloadCatalogo('apoderados/catTipoPoder.jsp', 'selectTipoPoderDiv');
	
}


function reloadCatalogo(url, divName) {

	//debug(filter);
	//alert(url);
	//alert(divName);
    var ajaxRequest = simpleAjaxUtil_getXmlHttpObject();     
    if (ajaxRequest==null) {alert ('Browser does not support HTTP Request');return;}
	
    //var url = 'catEscrituras.jsp';
    
    //var chanelId = selectCanal.value;

   var parameters = ''; //'chanelId='+chanelId;
   if(divName == "listApoderadosAsignadosDiv"){
	   ajaxRequest.open('post', url+"?dobleClick=true", true);   
   }else{
	   ajaxRequest.open('post', url, true);
   }    
    ajaxRequest.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
    ajaxRequest.onreadystatechange=function(){
    
	    if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){

		    var response = ajaxRequest.responseText;
		    document.getElementById(divName).innerHTML = response;
                    
                    //
                    //performSearch();
	      }
    };    
    ajaxRequest.send(parameters);
}



function selectEscrituraChanged() {
	
	performSearch();	
}



function selectGpoApoderChanged() {
	
	var escrituraId = selectEscritura.value;
    var tipoPoderId = selectTipoPoder.value;
    if(escrituraId != '0' && tipoPoderId > 0){
    	
    	performSearch();
    }else{
    	swal('Es requerido seleccionar la escritura y el tipo de poder')
    }
	
}

function ajaxNumOrden(){
	
	 var ajaxRequest = simpleAjaxUtil_getXmlHttpObject();     
	    if (ajaxRequest==null) {alert ('Browser does not support HTTP Request');return;}
		
	    var url = 'apoderados/numOrdenApoderados.jsp';
	    //var url = jsp;
	    
	    var escrituraId = selectEscritura.value;
	    var tipoPoderId = selectTipoPoder.value;
	    var grupo       = selectGrupoApo.value;

	   
	   var parameters = 'apoEscritura='+escrituraId +'&apoTipoder=' + tipoPoderId +'&apoGrupo=' + grupo;

	    ajaxRequest.open('post', url, true);
	    ajaxRequest.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
	    //document.getElementById('imgEsperaApode').style.display = '';
	    ajaxRequest.onreadystatechange=function(){
	    	
		    if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
		    	
			    var response = ajaxRequest.responseText;
			    document.getElementById('inputOrdenDiv').innerHTML = response;
			    //document.getElementById('imgEsperaApode').style.display = 'none';
			    
		      }
	    };    
	    ajaxRequest.send(parameters);
}


function performSearch() {
	
	ajaxNumOrden();
	
    var ajaxRequest = simpleAjaxUtil_getXmlHttpObject();     
    if (ajaxRequest==null) {alert ('Browser does not support HTTP Request');return;}
	
    var url = 'apoderados/apoderadosInfo.jsp';
    //var url = jsp;
    
    ////var escrituraId = selectEscritura.value;
    ////var tipoPoderId = selectTipoPoder.value;
    ////var grupo       = selectGrupoApo.value;

    var escrituraId = document.getElementById('selectEscritura').value;
    var tipoPoderId = document.getElementById('selectTipoPoder').value;
    var grupo       = document.getElementById('selectGrupoApo').value;

    
   var parameters = 'escrituraId='+escrituraId +'&tipoPoderId=' + tipoPoderId +'&grupo=' + grupo;

    ajaxRequest.open('post', url, true);
    ajaxRequest.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
    document.getElementById('imgEsperaApode').style.display = '';
    ajaxRequest.onreadystatechange=function(){
    	
	    if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
	    	
		    var response = ajaxRequest.responseText;
		    document.getElementById('apoderadosInfoDiv').innerHTML = response;
		    document.getElementById('imgEsperaApode').style.display = 'none';
		    
	      }
    };    
    ajaxRequest.send(parameters);
    
}

//ECM 26 AGOSTO 2015
function setEscritura(){
	//Argumel 17 MARZO 2016
	var escrituraId = selectEscritura.value;
    var tipoPoderId = selectTipoPoder.value;
    var grupo       = selectGrupoApo.value;
    
    if(escrituraId != '0' && tipoPoderId != '0' && grupo != '0'){
    	performSearch();
    }
	
	var ajaxRequest = simpleAjaxUtil_getXmlHttpObject();
	if (ajaxRequest==null) {alert ('Browser does not support HTTP Request');return;}
    var url = 'apoderados/setEscritura.jsp';
	var escritura = selectEscritura.value;
	//var escrituraId = TextFormatter(escritura);
	//alert(escrituraId);
	//var escrituraId = escritura.replace('ó','o');
	//escritura = escritura.replace('Consejo','o');
	
	//alert(encodeURIComponent(escritura));
	var parameters = 'escrituraId='+escritura;
	ajaxRequest.open('post', url, true);
    ajaxRequest.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
	ajaxRequest.send(parameters);

}


function addRow(fromList,divRefresh,idCatalogo,accion,jsp,divDerechoRefrescar,toJsp) {
	
	var listFrom = document.getElementById(fromList);
	var escrituraId = selectEscritura.value;
    var tipoPoderId = selectTipoPoder.value;
	var grupo 	 = selectGrupoApo.value;
	var numOrden = txtNumOrden.value;
	
	 if(escrituraId != '0' && tipoPoderId > 0 && grupo != '0'){
		
		 if(numOrden != '' && numOrden != null){
			 document.getElementById('imgEsperaApode').style.display = '';
		
			//	var listTo = document.getElementById(toList);
				var dataString = '';
				//alert(accion);
				if(accion=='quitarTodos' || accion=='agregarTodos'){
					
					for (var i = 0; i < listFrom.options.length; i++) {
						
							var val = listFrom.options[i].value;
							dataString = dataString + val + '|'
					}
				}else{
					for (var i = 0; i < listFrom.options.length; i++) {
						
						if(listFrom.options[i].selected ==true){
							var val = listFrom.options[i].value;
							dataString = dataString + val + '|'
							
							
						}
					}
				}
				
				//var contexto = $('#context').val();
				
				$.ajax({
					type: 'POST',
					url: '/DerechoCorporativo/ApoderadosServlet',
					data: 'parametros='+dataString+'&grupo='+grupo+'&idCatalogo='+idCatalogo+'&accion='+accion+'&numOrden='+numOrden,
					cache: false,
					headers: 'Content-Type application/x-www-form-urlencoded',
					success: function()
					{
						
						reloadCatalogo('apoderados/'+jsp, divRefresh);
						reloadCatalogo('apoderados/'+toJsp, divDerechoRefrescar);
						document.getElementById('imgEsperaApode').style.display = 'none';
						//$('#'+divRefresh).load('apoderados/'+jsp);
					}
					});
					return false;
		 }else{
			 swal('Orden No. debe ser capturado');
		 }
	 }else{
		 swal('Es requerido seleccionar una escritura un poder y un grupo');
	 }
}

$(document).ajaxSend(function() {
	  $('#ajaxloading').show();
	});

function camposAdicionales(id,nombre,idCatalogo){
	
	//alert(nombre);
	var valTipoPoder      = document.getElementById('selectTipoPoder').value;
	var valGrupoApoderado = document.getElementById('selectGrupoApo').value;
	var escritura 		  = document.getElementById('selectEscritura').value;
	
	//alert(valTipoPoder);
	var w = '800';
	var h = '490';
	var left = (screen.width/2)-(w/2);
	var top = (screen.height/2)-(h/2);
	window.open('/DerechoCorporativo/faces/jsp/captura/apoderados/capturaCamposAdici.jsp?escritura='+escritura+'&grupoApoderado='+valGrupoApoderado+'&idCatalogo='+idCatalogo+'&idCatalogoValor='+id+'&nombre='+nombre+'&tipoPoder='+valTipoPoder
			,null
			,'status=yes,toolbar=no,menubar=no,location=no width='+w+', height='+h+', top='+top+', left='+left)
			
}

function copiaEscrituraFn(){
	
	var w = '600';
	var h = '290';
	var left = (screen.width/2)-(w/2);
	var top = (screen.height/2)-(h/2);
	window.open('/DerechoCorporativo/faces/jsp/captura/apoderados/copiarEscritura.jsp'
			,null
			,'status=yes,toolbar=no,menubar=no,location=no width='+w+', height='+h+', top='+top+', left='+left)
			
}

function TextFormatter(strInput){
	
	//var arreglo = strInput.split('');
	var longitud = strInput.length;

	var strOutput;
	for(i = 0;i < longitud;i++){
		//alert(strInput.charAt(i));
		var letra = strInput.charAt(i);
		strOutput = letra.replace('á', '%E1');
		strOutput = letra.replace('é', '%E9');
		strOutput = letra.replace('í', '%ED');
		strOutput = letra.replace('ó','%F3');
		alert('ey '+letra.replace('ó','%F3'));
		strOutput = letra.replace('ú', '%FA');
		
		strOutput = letra.replace('Á', '%C1');
		strOutput = letra.replace('É', '%C9');
		strOutput = letra.replace('Í', '%CD');
		strOutput = letra.replace('Ó','%D3');
		strOutput = letra.replace('Ú', '%DA');
		
		//Acentos Invertidos
		strOutput = letra.replace('à', '%E0');
		strOutput = letra.replace('è', '%E8');
		strOutput = letra.replace('ì', '%EC');
		strOutput = letra.replace('ò', '%F2');
		strOutput = letra.replace('ù', '%F9');
		
		strOutput = letra.replace('À', '%C0');
		strOutput = letra.replace('È', '%C8');
		strOutput = letra.replace('Ì', '%CC');
		strOutput = letra.replace('Ò', '%D2');
		strOutput = letra.replace('Ù', '%D9');
	}
	/*var strOutput = strInput.replace('á', '%E1');
	strOutput = strOutput.replace('é', '%E9');
	strOutput = strOutput.replace('í', '%ED');
	strOutput = strOutput.replace('ó','%F3');
	alert('formarter1 '+strOutput);
	strOutput = strOutput.replace('ú', '%FA');
	
	strOutput = strOutput.replace('Á', '%C1');
	strOutput = strOutput.replace('É', '%C9');
	strOutput = strOutput.replace('Í', '%CD');
	strOutput = strOutput.replace('Ó','%D3');
	strOutput = strOutput.replace('Ú', '%DA');
	
	//Acentos Invertidos
	strOutput = strOutput.replace('à', '%E0');
	strOutput = strOutput.replace('è', '%E8');
	strOutput = strOutput.replace('ì', '%EC');
	strOutput = strOutput.replace('ò', '%F2');
	strOutput = strOutput.replace('ù', '%F9');
	
	strOutput = strOutput.replace('À', '%C0');
	strOutput = strOutput.replace('È', '%C8');
	strOutput = strOutput.replace('Ì', '%CC');
	strOutput = strOutput.replace('Ò', '%D2');
	strOutput = strOutput.replace('Ù', '%D9');
	alert('formarter2 '+strOutput);*/
	return strOutput;
}






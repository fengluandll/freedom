

function getAlterOrGlobalFlexId() {
	
	if(alterFlexTableId != '') {
		
		return alterFlexTableId;
	} else {
		
		return globalFlexTableId;
	}
}

/*
	  new Calendar({          inputField: "txtFec", 
		  dateFormat: "%d/%m/%Y",          
		  trigger: "txtFec_trigger",          
		  bottomBar: true,										            
		  onSelect: function() {                 
		var date = Calendar.intToDate(this.selection.get());                 
		this.hide();                 				  
		return false;         } }); */
	  
	 /* Calendar.setup({
	        trigger    : "txtFec_trigger",
	        inputField : "txtFec"
	    });
	  */
function clear_txtFec() {	document.getElementById('txtFec').value = "";	}

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
function flexTableSave() {

	alert('Entro: flexTableSaveInfo');
	
	
	
	document.getElementById('imgFlexTableWait').style.display = '';
	document.getElementById('flexTableformButton').style.display = 'none';
	
	//document.getElementById('Guardar').style.display = '';
	
	flexTableSaveInfo();
	
	
	
	//document.getElementById('flexTableInnerForm_'+globalFlexTableId).innerHTML = "";
	
	
}


function validaForm(){
	var asunto = document.getElementById('txtAsunto').value.trim();
	var nombre = document.getElementById('txtNombre').value.trim();
	var desc = document.getElementById('txtDesc').value.trim();
	if(asunto == ''){
		swal("Es requerido capturar el Asunto")
		}else if(nombre == ''){
			swal("Es requerido capturar el nombre")
		}else if(desc == ''){
			swal("Es requerido capturar la descripción")
		}else{
			document.getElementById("flexTableformButton").submit();
			}
}

function flexTableSaveInfo() {
	
	var ajaxRequest = simpleAjaxUtil_getXmlHttpObject();     
	
    if (ajaxRequest==null) {alert ("Browser does not support HTTP Request");return;}
	
	alert(globalFlexTableId);
    
    var idMetaRow = document.getElementById('idMetaRow_17').value;
    
    alert(idMetaRow);
    
	//var empresa = document.getElementById('empresa').value;
	//var concepto = document.getElementById('concepto').value;
	var fieldIds = document.getElementById('fieldIds_17').value;
	alert('fieldIds: '+fieldIds);
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
	var parameters = 	"fieldIds=" + fieldIds + 
						"&idMetaRow=" + idMetaRow;

    ajaxRequest.open("post",true);
    ajaxRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    ajaxRequest.onreadystatechange=function(){
    
	    if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
	
			alert('Response');

		    var response = ajaxRequest.responseText;
		    //document.getElementById('flexTableFormDiv').innerHTML = response;
		    
		    var message = "";
		    
		    if(response == "OK") {
		    	
		    	alert('Se guardaron los datos Correctamente');
		    	
		    	showGrowl("OK", "Se guardaron los datos Correctamente");
		    	
		    	message = "<span class='infoMessageText' align='center'>Se guardaron los datos Correctamente</span>";
		    	
		    } else {
		    	
		    	alert(response)
		    	
		    	message = "<span class='errorMessageText' align='center'>" + response + "</span>";
		    	
		    	//showGrowl("ERROR", response);
		    }
		    
		    
		    loadFlexTab(globalFlexTableId, message);
		    
		    
		    if(globalFlexTableId == 7) {
		    	setCamposCapitalSocial();
		    }
		    
	      } 
		  else {
			  alert(ajaxRequest.readyState);
			  alert(ajaxRequest.status);
			}
    };    
    ajaxRequest.send(parameters);
    
    
}
function buscarFiltro(){
		alert('Buscar...');
	}
	
	function go(url){
		
		window.location=url;
	}
	
	
	var globalFlexTableId;
	var alterFlexTableId = '';
	
	function isChecked(element){
		if(element.checked){
			element.value = 'Si';
		}else{
			element.value='No'
		}
	}
	
	function controlEscPod(element){
		
		setsEscPod(element.value);

	}
	
	function controlSemReqPro2(element){
		//alert(element);
		setsSemReqProto2(element.value);

	}
	function controlSemReqIns2(element){
		//alert(element);
		setsSemReqInsc2(element.value);
	}
	
	function habilitarStatus(element){
		//alert(element);
		cambiarStatus(element.value);
		
	}
	
	function cambiarStatus(element){
		
		    if(element == 'No'){

		    	document.getElementById('DIVC_17_VAL_C56_1').style.display = 'none';
		    	document.getElementById('DIVC_17_VAL_C56_2').style.display = 'none';
		    	document.getElementById('DIVC_17_VAL_C57_1').style.display = 'none';
		    	document.getElementById('DIVC_17_VAL_C57_2').style.display = 'none';
		    	document.getElementById('DIVC_17_VAL_C58_1').style.display = 'none';
		    	document.getElementById('DIVC_17_VAL_C58_2').style.display = 'none';
		    	document.getElementById('DIVC_17_VAL_C59_1').style.display = 'none';
		    	document.getElementById('DIVC_17_VAL_C59_2').style.display = 'none';
		    	document.getElementById('DIVC_17_VAL_C60_1').style.display = 'none';
		    	document.getElementById('DIVC_17_VAL_C60_2').style.display = 'none';
		    	document.getElementById('DIVC_17_VAL_C61_1').style.display = 'none';
		    	document.getElementById('DIVC_17_VAL_C61_2').style.display = 'none';
		    	document.getElementById('DIVC_17_VAL_C62_1').style.display = 'none';
		    	document.getElementById('DIVC_17_VAL_C62_2').style.display = 'none';
		    	document.getElementById('DIVC_17_VAL_C63_1').style.display = 'none';
		    	document.getElementById('DIVC_17_VAL_C63_2').style.display = 'none';
		    	document.getElementById('DIVC_17_VAL_C64_1').style.display = 'none';
		    	document.getElementById('DIVC_17_VAL_C64_2').style.display = 'none';
		    	document.getElementById('DIVC_17_VAL_C65_1').style.display = 'none';
		    	document.getElementById('DIVC_17_VAL_C65_2').style.display = 'none';
		    	document.getElementById('DIVC_17_VAL_C66_1').style.display = 'none';
		    	document.getElementById('DIVC_17_VAL_C66_2').style.display = 'none';
		    	document.getElementById('DIVC_17_VAL_C67_1').style.display = 'none';
		    	document.getElementById('DIVC_17_VAL_C67_2').style.display = 'none';
		    	document.getElementById('DIVC_17_VAL_C68_1').style.display = 'none';
		    	document.getElementById('DIVC_17_VAL_C68_2').style.display = 'none';
		    	document.getElementById('DIVC_17_VAL_C69_1').style.display = 'none';
		    	document.getElementById('DIVC_17_VAL_C69_2').style.display = 'none';
		    	document.getElementById('DIVC_17_VAL_C70_1').style.display = 'none';
		    	document.getElementById('DIVC_17_VAL_C70_2').style.display = 'none';
		    	document.getElementById('DIVC_17_VAL_C71_1').style.display = 'none';
		    	document.getElementById('DIVC_17_VAL_C71_2').style.display = 'none';
		    	document.getElementById('DIVC_17_VAL_C72_1').style.display = 'none';
		    	document.getElementById('DIVC_17_VAL_C72_2').style.display = 'none';
		    	document.getElementById('DIVC_17_VAL_C73_1').style.display = 'none';
		    	document.getElementById('DIVC_17_VAL_C73_2').style.display = 'none';
		    	document.getElementById('DIVC_17_VAL_C74_1').style.display = 'none';
		    	document.getElementById('DIVC_17_VAL_C74_2').style.display = 'none';
	    	
	    	}else{
	    		
	    		document.getElementById('DIVC_17_VAL_C56_1').style.display = 'block';
		    	document.getElementById('DIVC_17_VAL_C56_2').style.display = 'block';
		    	document.getElementById('DIVC_17_VAL_C57_1').style.display = 'block';
		    	document.getElementById('DIVC_17_VAL_C57_2').style.display = 'block';
		    	document.getElementById('DIVC_17_VAL_C58_1').style.display = 'block';
		    	document.getElementById('DIVC_17_VAL_C58_2').style.display = 'block';
		    	document.getElementById('DIVC_17_VAL_C59_1').style.display = 'block';
		    	document.getElementById('DIVC_17_VAL_C59_2').style.display = 'block';
		    	document.getElementById('DIVC_17_VAL_C60_1').style.display = 'block';
		    	document.getElementById('DIVC_17_VAL_C60_2').style.display = 'block';
		    	document.getElementById('DIVC_17_VAL_C61_1').style.display = 'block';
		    	document.getElementById('DIVC_17_VAL_C61_2').style.display = 'block';
		    	document.getElementById('DIVC_17_VAL_C62_1').style.display = 'block';
		    	document.getElementById('DIVC_17_VAL_C62_2').style.display = 'block';
		    	document.getElementById('DIVC_17_VAL_C63_1').style.display = 'block';
		    	document.getElementById('DIVC_17_VAL_C63_2').style.display = 'block';
		    	document.getElementById('DIVC_17_VAL_C64_1').style.display = 'block';
		    	document.getElementById('DIVC_17_VAL_C64_2').style.display = 'block';
		    	document.getElementById('DIVC_17_VAL_C65_1').style.display = 'block';
		    	document.getElementById('DIVC_17_VAL_C65_2').style.display = 'block';
		    	document.getElementById('DIVC_17_VAL_C66_1').style.display = 'block';
		    	document.getElementById('DIVC_17_VAL_C66_2').style.display = 'block';
		    	document.getElementById('DIVC_17_VAL_C67_1').style.display = 'block';
		    	document.getElementById('DIVC_17_VAL_C67_2').style.display = 'block';
		    	document.getElementById('DIVC_17_VAL_C68_1').style.display = 'block';
		    	document.getElementById('DIVC_17_VAL_C68_2').style.display = 'block';
		    	document.getElementById('DIVC_17_VAL_C69_1').style.display = 'block';
		    	document.getElementById('DIVC_17_VAL_C69_2').style.display = 'block';
		    	document.getElementById('DIVC_17_VAL_C70_1').style.display = 'block';
		    	document.getElementById('DIVC_17_VAL_C70_2').style.display = 'block';
		    	document.getElementById('DIVC_17_VAL_C71_1').style.display = 'block';
		    	document.getElementById('DIVC_17_VAL_C71_2').style.display = 'block';
		    	document.getElementById('DIVC_17_VAL_C72_1').style.display = 'block';
		    	document.getElementById('DIVC_17_VAL_C72_2').style.display = 'block';
		    	document.getElementById('DIVC_17_VAL_C73_1').style.display = 'block';
		    	document.getElementById('DIVC_17_VAL_C73_2').style.display = 'block';
		    	document.getElementById('DIVC_17_VAL_C74_1').style.display = 'block';
		    	document.getElementById('DIVC_17_VAL_C74_2').style.display = 'block';
		    	
	    	
	    }
	}
	//ECM 10 Noviembre 2015 Metodo inicial de carga datos en flex.
	/*
	 * 
	 *          INICIO FLEX
	 * 
	 * */
	function IniSemReq(){
		
		alert('IniSemReq');
		
		//alert('Flex:  '+globalFlexTableId);
		
		//alert('getAlterOrGlobalFlexId: ' + getAlterOrGlobalFlexId());
		
		
		if(getAlterOrGlobalFlexId() == 17 ||
		   getAlterOrGlobalFlexId() == 18
		){
			
			showDelegadoPor();
			
			var vValorSemReqProto2 = document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C4').value;
			var vValorSemReqInsc2  = document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C5').value;
			var vValorEscPod  = document.getElementById(getAlterOrGlobalFlexId()+'__VAL_C4'+ '__VAL_C5').value;
			
			setsSemReqProto2(vValorSemReqProto2);
			setsSemReqInsc2(vValorSemReqInsc2);
			setsEscPod(vValorEscPod);

			if(getAlterOrGlobalFlexId() == 17){
				cambiarStatus(document.getElementById('17__VAL_C76').value);
				
			}else if(getAlterOrGlobalFlexId() == 18){
				cambiarStatus(document.getElementById('18__VAL_C76').value);
			}{
			
			setsSemReqInsc(vVacontrolEscPodrSemReqInsc);
		}
		}		
		
	}

	function setsEscPod(pValorCheck){
		
		if(pValorCheck == 'esc'){
			
			document.getElementById('DIVC_17_VAL_C4_1').style.display = 'block';
			document.getElementById('DIVC_17_VAL_C4_2').style.display = 'block';
			document.getElementById('DIVC_17_VAL_C5_1').style.display = 'block';
			document.getElementById('DIVC_17_VAL_C5_2').style.display = 'block';
			document.getElementById('DIVC_17_VAL_C9_1').style.display = 'block';
			document.getElementById('DIVC_17_VAL_C9_2').style.display = 'block';
			document.getElementById('DIVC_17_VAL_C10_1').style.display = 'none';
			document.getElementById('DIVC_17_VAL_C10_2').style.display = 'none';
			document.getElementById('DIVC_17_VAL_C11_1').style.display = 'none';
			document.getElementById('DIVC_17_VAL_C11_2').style.display = 'none'
			document.getElementById('DIVC_17_VAL_C12_1').style.display = 'none';
			document.getElementById('DIVC_17_VAL_C12_2').style.display = 'none';
			document.getElementById('DIVC_17_VAL_C13_1').style.display = 'none';
			document.getElementById('DIVC_17_VAL_C13_2').style.display = 'none';
			document.getElementById('DIVC_17_VAL_C15_1').style.display = 'block';
			document.getElementById('DIVC_17_VAL_C15_2').style.display = 'block';
			document.getElementById('DIVC_17_VAL_C16_1').style.display = 'none';
			document.getElementById('DIVC_17_VAL_C16_2').style.display = 'none';
			document.getElementById('DIVC_17_VAL_C18_1').style.display = 'none';
			document.getElementById('DIVC_17_VAL_C18_2').style.display = 'none';
			document.getElementById('DIVC_17_VAL_C19_1').style.display = 'none';
			document.getElementById('DIVC_17_VAL_C19_2').style.display = 'none';
			document.getElementById('DIVC_17_VAL_C20_1').style.display = 'none';
			document.getElementById('DIVC_17_VAL_C20_2').style.display = 'none';
			document.getElementById('DIVC_17_VAL_C21_1').style.display = 'none';
			document.getElementById('DIVC_17_VAL_C21_2').style.display = 'none';
			
		}else{
			document.getElementById('DIVC_17_VAL_C9_1').style.display = 'none';
			document.getElementById('DIVC_17_VAL_C9_2').style.display = 'none';
			document.getElementById('DIVC_17_VAL_C10_1').style.display = 'none';
			document.getElementById('DIVC_17_VAL_C10_2').style.display = 'none';
			document.getElementById('DIVC_17_VAL_C11_1').style.display = 'none';
			document.getElementById('DIVC_17_VAL_C11_2').style.display = 'none'
			document.getElementById('DIVC_17_VAL_C12_1').style.display = 'none';
			document.getElementById('DIVC_17_VAL_C12_2').style.display = 'none';
			document.getElementById('DIVC_17_VAL_C13_1').style.display = 'none';
			document.getElementById('DIVC_17_VAL_C13_2').style.display = 'none';
			document.getElementById('DIVC_17_VAL_C15_1').style.display = 'none';
			document.getElementById('DIVC_17_VAL_C15_2').style.display = 'none';
			document.getElementById('DIVC_17_VAL_C16_1').style.display = 'none';
			document.getElementById('DIVC_17_VAL_C16_2').style.display = 'none';
			document.getElementById('DIVC_17_VAL_C18_1').style.display = 'none';
			document.getElementById('DIVC_17_VAL_C18_2').style.display = 'none';
			document.getElementById('DIVC_17_VAL_C19_1').style.display = 'none';
			document.getElementById('DIVC_17_VAL_C19_2').style.display = 'none';
			document.getElementById('DIVC_17_VAL_C20_1').style.display = 'none';
			document.getElementById('DIVC_17_VAL_C20_2').style.display = 'none';
			document.getElementById('DIVC_17_VAL_C21_1').style.display = 'none';
			document.getElementById('DIVC_17_VAL_C21_2').style.display = 'none';
			document.getElementById('DIVC_17_VAL_C4_1').style.display = 'none';
			document.getElementById('DIVC_17_VAL_C4_2').style.display = 'none';
			document.getElementById('DIVC_17_VAL_C5_1').style.display = 'none';
			document.getElementById('DIVC_17_VAL_C5_2').style.display = 'none';
			
			
		}	
	}
	
	function setsSemReqProto2(pValorCheck){
		//alert(pValorCheck);
		if(pValorCheck == 'No'){
			
		  /*document.getElementById('DIVC_17_VAL_C6_1').style.display = 'none';
			document.getElementById('DIVC_17_VAL_C6_2').style.display = 'none';
			document.getElementById('DIVC_17_VAL_C7_1').style.display = 'none';
			document.getElementById('DIVC_17_VAL_C7_2').style.display = 'none';			
			document.getElementById('DIVC_17_VAL_C8_1').style.display = 'none';
			document.getElementById('DIVC_17_VAL_C8_2').style.display = 'none';
			document.getElementById('DIVC_17_VAL_C9_1').style.display = 'none';
			document.getElementById('DIVC_17_VAL_C9_2').style.display = 'none';*/
			document.getElementById('DIVC_17_VAL_C10_1').style.display = 'none';
			document.getElementById('DIVC_17_VAL_C10_2').style.display = 'none';
			document.getElementById('DIVC_17_VAL_C11_1').style.display = 'none';
			document.getElementById('DIVC_17_VAL_C11_2').style.display = 'none';
			document.getElementById('DIVC_17_VAL_C12_1').style.display = 'none';
			document.getElementById('DIVC_17_VAL_C12_2').style.display = 'none';
			document.getElementById('DIVC_17_VAL_C13_1').style.display = 'none';
			document.getElementById('DIVC_17_VAL_C13_2').style.display = 'none';
			document.getElementById('DIVC_17_VAL_C16_1').style.display = 'none';
			document.getElementById('DIVC_17_VAL_C16_2').style.display = 'none';
		}else{
			
		  /*document.getElementById('DIVC_17_VAL_C6_1').style.display = 'block';
			document.getElementById('DIVC_17_VAL_C6_2').style.display = 'block';
			document.getElementById('DIVC_17_VAL_C7_1').style.display = 'block';
			document.getElementById('DIVC_17_VAL_C7_2').style.display = 'block';
			document.getElementById('DIVC_17_VAL_C8_1').style.display = 'block';
			document.getElementById('DIVC_17_VAL_C8_2').style.display = 'block';
			document.getElementById('DIVC_17_VAL_C9_1').style.display = 'block';
			document.getElementById('DIVC_17_VAL_C9_2').style.display = 'block';
			document.getElementById('DIVC_17_VAL_C10_1').style.display = 'block';
			document.getElementById('DIVC_17_VAL_C10_2').style.display = 'block';
			document.getElementById('DIVC_17_VAL_C11_1').style.display = 'block';
			document.getElementById('DIVC_17_VAL_C11_2').style.display = 'block';
			document.getElementById('DIVC_17_VAL_C12_1').style.display = 'block';
			document.getElementById('DIVC_17_VAL_C12_2').style.display = 'block';
			document.getElementById('DIVC_17_VAL_C13_1').style.display = 'block';
			document.getElementById('DIVC_17_VAL_C13_2').style.display = 'block';
			document.getElementById('DIVC_17_VAL_C14_1').style.display = 'block';
			document.getElementById('DIVC_17_VAL_C14_2').style.display = 'block';
			document.getElementById('DIVC_17_VAL_C15_1').style.display = 'block';
			document.getElementById('DIVC_17_VAL_C15_2').style.display = 'block';*/
			document.getElementById('DIVC_17_VAL_C10_1').style.display = 'block';
			document.getElementById('DIVC_17_VAL_C10_2').style.display = 'block';
			document.getElementById('DIVC_17_VAL_C11_1').style.display = 'block';
			document.getElementById('DIVC_17_VAL_C11_2').style.display = 'block';
			document.getElementById('DIVC_17_VAL_C12_1').style.display = 'block';
			document.getElementById('DIVC_17_VAL_C12_2').style.display = 'block';
			document.getElementById('DIVC_17_VAL_C13_1').style.display = 'block';
			document.getElementById('DIVC_17_VAL_C13_2').style.display = 'block';
			document.getElementById('DIVC_17_VAL_C16_1').style.display = 'block';
			document.getElementById('DIVC_17_VAL_C16_2').style.display = 'block';
		}	
	}
	
	
	function setsSemReqInsc2(pValorCheck){
		if(pValorCheck == 'No'){
			/*
			document.getElementById('DIVC_17_VAL_C18_1').style.display = 'none';
			document.getElementById('DIVC_17_VAL_C18_2').style.display = 'none';
			document.getElementById('DIVC_17_VAL_C19_1').style.display = 'none';
			document.getElementById('DIVC_17_VAL_C19_2').style.display = 'none';
			document.getElementById('DIVC_17_VAL_C20_1').style.display = 'none';
			document.getElementById('DIVC_17_VAL_C20_2').style.display = 'none';
			document.getElementById('DIVC_17_VAL_C21_1').style.display = 'none';
			document.getElementById('DIVC_17_VAL_C21_2').style.display = 'none';
			*/
			document.getElementById('DIVC_17_VAL_C18_1').style.display = 'none';
			document.getElementById('DIVC_17_VAL_C18_2').style.display = 'none';
			document.getElementById('DIVC_17_VAL_C19_1').style.display = 'none';
			document.getElementById('DIVC_17_VAL_C19_2').style.display = 'none';
			document.getElementById('DIVC_17_VAL_C20_1').style.display = 'none';
			document.getElementById('DIVC_17_VAL_C20_2').style.display = 'none';
			document.getElementById('DIVC_17_VAL_C21_1').style.display = 'none';
			document.getElementById('DIVC_17_VAL_C21_2').style.display = 'none';
		}else{
			/*
			document.getElementById('DIVC_17_VAL_C18_1').style.display = 'block';
			document.getElementById('DIVC_17_VAL_C18_2').style.display = 'block';
			document.getElementById('DIVC_17_VAL_C19_1').style.display = 'block';
			document.getElementById('DIVC_17_VAL_C19_2').style.display = 'block';
			document.getElementById('DIVC_17_VAL_C20_1').style.display = 'block';
			document.getElementById('DIVC_17_VAL_C20_2').style.display = 'block';
			document.getElementById('DIVC_17_VAL_C21_1').style.display = 'block';
			document.getElementById('DIVC_17_VAL_C21_2').style.display = 'block';
			*/
			document.getElementById('DIVC_17_VAL_C18_1').style.display = 'block';
			document.getElementById('DIVC_17_VAL_C18_2').style.display = 'block';
			document.getElementById('DIVC_17_VAL_C19_1').style.display = 'block';
			document.getElementById('DIVC_17_VAL_C19_2').style.display = 'block';
			document.getElementById('DIVC_17_VAL_C20_1').style.display = 'block';
			document.getElementById('DIVC_17_VAL_C20_2').style.display = 'block';
			document.getElementById('DIVC_17_VAL_C21_1').style.display = 'block';
			document.getElementById('DIVC_17_VAL_C21_2').style.display = 'block';
		}
	}
	
	
	function setFlexTableId(paramId) {
	
		globalFlexTableId = paramId;
	}
	
	function setAlterFlexTableId(paramId) {
		
		alterFlexTableId = paramId;
	}
	
	function deleteForm(key,globalFlexTableId){
		var url = '../ajax/flexTableFormClose.jsp';
		var ajaxRequest = simpleAjaxUtil_getXmlHttpObject(); 
		
	    ajaxRequest.open("post", url, true);
	    ajaxRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	    ajaxRequest.onreadystatechange=function(){
	    
	    };
	    ajaxRequest.send("key="+key+"&globalFlexTableId="+globalFlexTableId);
	}
	
	function closeForm() {
		
		if(globalFlexTableId == '17'){
			loadFlexTab('17', '');
		}else if(globalFlexTableId == '18'){
			loadFlexTab('18', '');
		}

	}
	
	function closeCurrentAndLoadFlexTabForm_new(idFlexTab, paramIds) {
		
		//document.getElementById('btnGuardar').style.display = '';
		
		//enableChecks();
		
		var ajaxRequest = simpleAjaxUtil_getXmlHttpObject();     
		
	    if (ajaxRequest==null) {alert ("Browser does not support HTTP Request");return;}
		
		
		//var empresa = document.getElementById('empresa').value;
		//var concepto = document.getElementById('concepto').value;
		//var criterio = document.getElementById('criterio').value;
		
		
		//alert(globalFlexTableId);
		//document.getElementById('flexTable_' + idFlexTab).innerHTML = "<center>Buscando ... <img src='img/esperar.gif'></center>";
		
	    var url = '../ajax/newPG.jsp';
		var parameters = "idFlexTab=" + idFlexTab;//17;
		

	    ajaxRequest.open("post", url, true);
	    ajaxRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	    ajaxRequest.onreadystatechange=function(){
	    
		    if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
			    				
				var response = ajaxRequest.responseText;
				
			    if(document.getElementById('flexTable_' + idFlexTab) != null){
			    	
			    	//QA
			    	//alert('One');
			    
			    	//document.getElementById('flexTable_' + idFlexTab).innerHTML = response;
			    
			    	document.getElementById('flexTable_' + idFlexTab).style.display = '';
			    }
			    
			    
			   // if(document.getElementById('flexTableInnerForm_' + globalFlexTableId) != null) {
			    	
			    	//QA
			    	//alert('Dos');
			    	
			    	document.getElementById('flexTableInnerForm_' + globalFlexTableId).style.display = 'none';
			    }

			    //QUITAR
			    //alert('Response de loadFlexTab');
			    
		    	//QA
		    	//alert('idFlexTab:' + idFlexTab);
			    
			    setFlexTableId(idFlexTab);
			    
			    //loadFlexTableForm(paramIds);
			    
				
		      //} 
	    };    
	    ajaxRequest.send(parameters);
	}
	
	var globalSectionId = '<%=idSeccion%>';
	
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
	
/*Nuevo JJAQ*/
function loadFlexTab_new(idFlexTab, message) {
	//alert('entro');
	//enableChecks();
	
    var ajaxRequest = simpleAjaxUtil_getXmlHttpObject();     
	
    if (ajaxRequest==null) {alert ("Browser does not support HTTP Request");return;}
	
    var url = '../ajax/reloadPG.jsp';
    var url = '../ajax/flexTable.jsp';
	var parameters = 	"idFlexTab=" + idFlexTab;
	

    ajaxRequest.open("post", url, true);
    ajaxRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    ajaxRequest.onreadystatechange=function(){
    
	    if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
	
			alert('Response');

		    var response = ajaxRequest.responseText;
		    //document.getElementById('flexTable_17').innerHTML = response + message;
		    
		    document.getElementById('flexTable_17').value = response + message;
		    document.getElementById('flexTableInnerForm_' + idFlexTab).value = 'none';
		    //style.display = 'none';
		   
			
	      } 
    };    
    ajaxRequest.send(parameters);
}

function verificarEscritura_pg(key){

    var ajaxRequest = simpleAjaxUtil_getXmlHttpObject();

    if (ajaxRequest==null) {alert ("Browser does not support HTTP Request");return;}

    var idMetaRow = key;
	
    var url = '../ajax/flexTableVerificarEscritura.jsp';
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
			
		    loadFlexTab_new(globalFlexTableId, message);

	      }
    };    
    ajaxRequest.send(parameters);

}

function loadFlexTab(idFlexTab,key, message) {
	
	//QUITAR
	alert('loadFlexTab');
	
	//TESTING-TEMP
	//enableChecks();
	
    var ajaxRequest = simpleAjaxUtil_getXmlHttpObject();     
	
    if (ajaxRequest==null) {alert ("Browser does not support HTTP Request");return;}
	
	
	//var empresa = document.getElementById('empresa').value;
	//var concepto = document.getElementById('concepto').value;
	//var criterio = document.getElementById('criterio').value;
	
	
	
	//document.getElementById('flexTable_' + idFlexTab).innerHTML = "<center>Buscando ... <img src='img/esperar.gif'></center>";
    var parameters = 	"idFlexTab=" + idFlexTab + "idPoder=" + key;
    var url = 'ajax/flexTable.jsp';
	

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


function deleteFlexRow(key) {	
	var ajaxRequest2 = simpleAjaxUtil_getXmlHttpObject(); 
    
    if (ajaxRequest2==null) {alert ("Browser does not support HTTP Request");return;}
	
    var contexto = $(location).attr('href');
    contexto = contexto.substring(0, contexto.indexOf('/faces'));
    //var url = contexto + '/DeletePGServlet';
      var url = 'deletePoder.jsp';
    //var url = 'ajax/flexTableFormAllow.jsp';
	//var url = '../ajax/flexTableDelete.jsp';
	var parameters = 	"idPoder=" + key;
						
	ajaxRequest2.open("post", url, true);
	ajaxRequest2.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    ajaxRequest2.onreadystatechange=function(){
    
	    if(ajaxRequest2.readyState == 4 && ajaxRequest2.status==200){

		    var response = ajaxRequest2.responseText;
			
			
		    var message = "";
		    
		    if(response != "NOK") {
		    	
		    	document.getElementById('flexTable_17').innerHTML = response;
		    	
		    	showGrowl("OK", "Se elimino el registro correctamente");
		    	
		    	message = "<span class='infoMessageText' align='center'>Se elimin&oacute; el registro correctamente</span>";
		    	
		    } else {
		    	
		    	//alert(response)
		    	showGrowl("Error al cargar la información", response);
		    	
		    	message = "<span class='errorMessageText' align='center'>" + response + "</span>";
		    }
		    
		   
	      } 
    };    
    ajaxRequest2.send(parameters);
    

}


function borrarRegistroFlex(key){
    var ajaxRequest = simpleAjaxUtil_getXmlHttpObject();
	
    if (ajaxRequest==null) {alert ("Browser does not support HTTP Request");return;}
	
    
    var idPoder = key;
    
	//document.getElementById('flexTable_' + idFlexTab).innerHTML = "<center>Buscando ... <img src='img/esperar.gif'></center>";
	
    var url = 'ajax/flexTableDelete.jsp';
	var parameters = "idMetaRow=" + idMetaRow;
	
		
    ajaxRequest.open("post", url, true);
    ajaxRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    ajaxRequest.onreadystatechange=function(){
    
	    if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
	
			alert('Response');

		    var response = ajaxRequest.responseText;
		    //document.getElementById('flexTableFormDiv').innerHTML = response;
		    
		    var message = "";
		    
		    if(response == "OK") {
		    	
		    	alert('Se eliminó el registro correctamente');
		    	
		    	//showGrowl("OK", "Se elimino el registro correctamente");
		    	
		    	message = "<span class='infoMessageText' align='center'>Se elimin&oacute; el registro correctamente</span>";
		    	
		    } else {
		    	
		    	alert(response)
		    	//showGrowl("ERROR", response);
		    	
		    	message = "<span class='errorMessageText' align='center'>" + response + "</span>";
		    }
		    
		    
		    loadFlexTab(idFlexTab, message);
		    
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

function loadFlexTabIni(key) {
	
	alert(key);
	
    var ajaxRequest = simpleAjaxUtil_getXmlHttpObject();     
	
    if (ajaxRequest==null) {alert ("Browser does not support HTTP Request");return;}
	
	
	//var empresa = document.getElementById('empresa').value;
	//var concepto = document.getElementById('concepto').value;
	//var criterio = document.getElementById('criterio').value;
	
	
	
	//document.getElementById('flexTable_' + idFlexTab).innerHTML = "<center>Buscando ... <img src='img/esperar.gif'></center>";
	
    //var url = 'ajax/flexTable.jsp';
    //var parameters = 	"idFlexTab=" + idFlexTab;
	
	var contexto = $(location).attr('href');
    contexto = contexto.substring(0, contexto.indexOf('/faces'));
    var url = contexto + '/EditaPGServlet';
    
    var parameters = 	"idPoder=" + key;

    ajaxRequest.open("post", url, true);
    ajaxRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    ajaxRequest.onreadystatechange=function(){
    
	    if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){

			alert('Response');

		    var response = ajaxRequest.responseText;
		    //document.getElementById('flexTable_' + idFlexTab).innerHTML = response;			
			
					
				    var message = "";
				    
				    if(response != "NOK") {
				    	
				    	//document.getElementById('flexTable_17').innerHTML = response;
				    				    	
				    } else {
				    	
				    	alert(response)
				    	showGrowl("Error al cargar poderes generales", response);
			
				    }
				    
				   
			      } 
		    };    
		    ajaxRequest2.send(parameters);
		    

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

    }


function getMascaraHora(pObjeto){
	var vSeparador = ':';
	var vPatron =  new Array(2,2);
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

function getMascaraFecha(pObjeto){
	alert(1);
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

function getInfoLic(src, notario, ubicacion){
	alert('ECM:  '+src.value);
	var parameters = "IdCatVal="+src.value;
	var ajaxRequest = simpleAjaxUtil_getXmlHttpObject();
	if (ajaxRequest==null) {alert ("Browser does not support HTTP Request");return;}
	var url = 'ajax/getInfoLic.jsp';
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
var url = 'ajax/getInfoNotario.jsp';
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

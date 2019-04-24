
function openMultiSelectPupUp(catalogId, targetIds, targetNames, currentValue) {

	/*
	 var left = (screen.width - 250) / 2;
    var top = (screen.height - 300) / 4;  // for 25% - devide by 4  |  for 33% - devide by 3
    */

    var left = screen.width - ((screen.width - 300) / 2);
    var top = (screen.height - 300) / 2;  // for 25% - devide by 4  |  for 33% - devide by 3
    
    
    //alert('../components/multiselectPupUp/multiselect.jsp?catalogId=' + catalogId + '&targetIds=' + targetIds + '&targetNames=' + targetNames);
    

	newwindow=window.open('../components/multiselectPupUp/multiselect.jsp?catalogId=' + catalogId + '&targetIds=' + targetIds + '&targetNames=' + targetNames + '&currentValue=' + currentValue,
						'name',
						'height=300,width=400,toolbar=0,menubar=0,location=0,status=0,scrollbars=1,resizable=1,left=' + left + ',top=' + top);

	if (window.focus) {newwindow.focus()}


	//newwindow.document.getElementById('var1').value = val1;	
	//newwindow.document.getElementById('var2').value = val2;


	return false;

}


function openMultiSelectPupUp2(catalogId, targetIds, targetNames, namesProperty, currentValue, namesFormat) {


    var left = screen.width - ((screen.width - 300) / 2);
    var top = (screen.height - 300) / 2;  // for 25% - devide by 4  |  for 33% - devide by 3

	newwindow=window.open('../components/multiselectPupUp/multiselect.jsp?catalogId=' + catalogId + '&targetIds=' + targetIds + '&targetNames=' + targetNames + '&currentValue=' + currentValue + '&namesProperty=' + namesProperty + '&namesFormat=' + namesFormat,
						'name',
						'height=300,width=400,toolbar=0,menubar=0,location=0,status=0,scrollbars=1,resizable=1,left=' + left + ',top=' + top);

	if (window.focus) {newwindow.focus()}

	return false;

}

//ULR 09-03-2017 se añadio la funcion para abrir una ventana nueva en la cual se van a añadir nuevos objetos sociales 
function openCustomTableEjercicioPupUp(typeView,idMetaRow){
    var left = screen.width - ((screen.width - 300) / 2);
    var top = (screen.height - 300) / 2;  // for 25% - devide by 4  |  for 33% - devide by 3

	if(idMetaRow!=0){
		newwindow=window.open('../components/customTablePupUp/customTableEjercicio.jsp?typeView='+typeView+'&idMetaRow='+idMetaRow,		
				'name',
				'height=450,width=800,toolbar=0,menubar=0,location=0,status=0,scrollbars=1,resizable=1,left=' + left + ',top=' + top);
	}else{
		newwindow=window.open('../components/customTablePupUp/customTableEjercicio.jsp?typeView='+typeView,		
				'name',
				'height=450,width=800,toolbar=0,menubar=0,location=0,status=0,scrollbars=1,resizable=1,left=' + left + ',top=' + top);
	}

	if (window.focus) {newwindow.focus()}

	return false;

}

//ULR 12-04-2017 se añadio la funcion para abrir una ventana nueva en la cual se van a añadir asuntos en las reformas sesion dl consejo y comites 
function openCustomTableAsuntoPupUp(typeView,idMetaRow){
    var left = screen.width - ((screen.width - 300) / 2);
    var top = (screen.height - 300) / 2;  // for 25% - devide by 4  |  for 33% - devide by 3

	if(idMetaRow!=0){
		newwindow=window.open('../components/customTablePupUp/customTableAsunto.jsp?typeView='+typeView+'&idMetaRow='+idMetaRow,		
				'name',
				'height=450,width=800,toolbar=0,menubar=0,location=0,status=0,scrollbars=1,resizable=1,left=' + left + ',top=' + top);
	}else{
		newwindow=window.open('../components/customTablePupUp/customTableAsunto.jsp?typeView='+typeView,		
				'name',
				'height=450,width=800,toolbar=0,menubar=0,location=0,status=0,scrollbars=1,resizable=1,left=' + left + ',top=' + top);
	}

	if (window.focus) {newwindow.focus()}

	return false;

}

//JJAQ 31-01-2019 se añadio la funcion para abrir una ventana nueva en la cual se van a añadir agregar mas en otros que antes era escrituras otros 
function openCustomTableAgregarPupUp(typeView,idMetaRow){
    var left = screen.width - ((screen.width - 300) / 2);
    var top = (screen.height - 300) / 2;  // for 25% - devide by 4  |  for 33% - devide by 3

	if(idMetaRow!=0){
		newwindow=window.open('../components/customTablePupUp/customTableAgregar.jsp?typeView='+typeView+'&idMetaRow='+idMetaRow,		
				'name',
				'height=450,width=800,toolbar=0,menubar=0,location=0,status=0,scrollbars=1,resizable=1,left=' + left + ',top=' + top);
	}else{
		newwindow=window.open('../components/customTablePupUp/customTableAgregar.jsp?typeView='+typeView,		
				'name',
				'height=450,width=800,toolbar=0,menubar=0,location=0,status=0,scrollbars=1,resizable=1,left=' + left + ',top=' + top);
	}

	if (window.focus) {newwindow.focus()}

	return false;

}

function openCustomTablePupUp(catalogId, targetIds,
			//, targetNames, namesProperty, 
			currentValue, targetNames
			//, namesFormat
		) {


    var left = screen.width - ((screen.width - 300) / 2);
    var top = (screen.height - 300) / 2;  // for 25% - devide by 4  |  for 33% - devide by 3

	newwindow=window.open('../components/customTablePupUp/customTable.jsp?catalogId=' + catalogId + '&targetIds=' + targetIds + '&currentValue=' + currentValue
										+ '&targetNames=' + targetNames
						//  + '&namesProperty=' + namesProperty + '&namesFormat=' + namesFormat
						,
						'name',
						'height=450,width=600,toolbar=0,menubar=0,location=0,status=0,scrollbars=1,resizable=1,left=' + left + ',top=' + top);

	if (window.focus) {newwindow.focus()}

	return false;

}








function openSelectPupUp(catalogId, targetIds, targetNames, namesProperty, currentValue) {

    var left = screen.width - ((screen.width - 300) / 2);
    var top = (screen.height - 300) / 2;  // for 25% - devide by 4  |  for 33% - devide by 3

	newwindow=window.open('../components/simpleSelectPupUp/simpleselect.jsp?catalogId=' + catalogId + '&targetIds=' + targetIds + '&targetNames=' + targetNames + '&currentValue=' + currentValue + '&namesProperty=' + namesProperty,
						'name',
						'height=600,width=450,toolbar=0,menubar=0,location=0,status=0,scrollbars=1,resizable=1,left=' + left + ',top=' + top);

	if (window.focus) {newwindow.focus()}

	return false;
}



function openFusionCopy(flexTableId, key) {

    var left = screen.width - ((screen.width - 300) / 2);
    var top = (screen.height - 300) / 2;  // for 25% - devide by 4  |  for 33% - devide by 3

	newwindow=window.open('pupUp/fusionCopy.jsp?flexTableId=' + flexTableId + '&key=' + key,
						'name',
						'height=600,width=650,toolbar=0,menubar=0,location=0,status=0,scrollbars=1,resizable=1,left=' + left + ',top=' + top);

	if (window.focus) {newwindow.focus()}

	return false;
}

function loadFlexTab_aes() {

		$.ajax({
			url		:	'ajax/flexTable.jsp',
			data	:	"idFlexTab=" + 23,
			type	:	'post',
			success	:	function(data){
				 var response = data;
			     document.getElementById('flexTable_23').innerHTML = response + '';
			     document.getElementById('flexTable_23').style.display = '';
			     document.getElementById('flexTableInnerForm_23').style.display = 'none';
					
			}
		});
	  

	
}

function openAESCopy(flexTableId, key,idEmpresa) {

$(document).ready(function(){
	var contextPath=$('#contextPath').val();	
	swal({
		  title: "Estas seguro?",
		  text: "Copiaras este registro a esta misma empresa!",
		  type: "warning",
		  showCancelButton: true,
		  confirmButtonColor: "#2B385D",
		  confirmButtonText: "Si,Copiar!",
		  closeOnConfirm: false
		},
		function(){
			$.ajax({
				url		:	contextPath+'/CopiadoAEServlet',
				data	:	'metaRowId='+key+'&idEmpresa='+idEmpresa,
				type	:	'post',
				success	:	function(data){
					if(data=="ok"){
						loadFlexTab_aes();
						swal("Copiado!", "Registro copiado correctamente", "success");
					}else{
						alert("Ha ocurrido un error, consulte conel administrador del sistema");
					}
						
				}
			});
		  
		});

});
}





function openFlexTableDetail(flexTableId, rowId) {

    var left = screen.width - ((screen.width - 300) / 1);
    var top = (screen.height - 700) / 1;  // for 25% - devide by 4  |  for 33% - devide by 3

	newwindow=window.open('pupUp/flexTableDetail.jsp?flexTableId=' + flexTableId + '&rowId=' + rowId,
						'name',
						'height=600,width=650,toolbar=0,menubar=0,location=0,status=0,scrollbars=1,resizable=1,left=' + left + ',top=' + top);

	if (window.focus) {newwindow.focus()}

	return false;
}

function openPrintTab(tabId) {

    var left = screen.width - ((screen.width - 300) / 2);
    var top = (screen.height - 300) / 2;  // for 25% - devide by 4  |  for 33% - devide by 3
    //var contexto = $(location).attr('href');
    //var num = contexto.indexOf('p');
    //alert('contexto: '+contexto);
    //alert('num: '+num);
/*
	newwindow=window.open('printTab.jsp?tabId=' + tabId,
						'name',
						'height=800,width=800,toolbar=0,menubar=0,location=0,status=0,scrollbars=1,resizable=1,left=' + left + ',top=' + top);
*/    
    	var data = '';
	    try{
	    	if(tabId == '*'){
	    		data = document.getElementById('msgPoderes').value;
	    	}
	        
	    }catch(Exception){
	    	
	    }
        //alert(data);
    	if(data == 'msg'){
    		swal("Para imprimir Todo el manual debes cargar primero Poderes");
    	}else{
    		
	    	newwindow=window.open('printTab.jsp?tabId=' + tabId);
	    	if (window.focus) {newwindow.focus()}
    	}
            		            
    //newwindow=window.open('Hola.jsp?tabId=' + tabId);
    //newwindow=window.open('http://tvsfeapdecp.corp.televisa.com.mx:7003/DerechoCorporativo/faces/jsp/captura/printTab.jsp?tabId=' + tabId);
	//newwindow=window.open('http://tvsfedbdecd.corp.televisa.com.mx:7003/DerechoCorporativo/faces/jsp/captura/printTab.jsp?tabId=' + tabId);
    //newwindow=window.open('http://vaio-ernesto:7001/DerechoCorporativo/faces/jsp/captura/printTab.jsp?tabId=' + tabId);    
	
	return false;
}

function openEdit(id, cod, nom, des, val){
//	alert('id:'+id+' cod:'+cod+' nom:'+nom+' des:'+des+' val:'+val);
//	document.getElementById('flexTable_Abc_AppConfigTab').style.display = 'none';
    var left = screen.width - ((screen.width - 100) / 2);
    var top = (screen.height - 800) / 2;

	newwindow=window.open('abc_appConfigTabNewEdit.jsp?id='+id+'&cod='+cod+'&nom='+nom+'&des='+des+'&val='+val,
						'name',
						'height=200,width=600,toolbar=0,menubar=0,location=0,status=0,scrollbars=1,resizable=1,left=' + left + ',top=' + top);

	if (window.focus) {newwindow.focus()}

	return false;

}

function openNew(){
    var left = screen.width - ((screen.width - 300) / 2);
    var top = (screen.height - 300) / 2;

	newwindow=window.open('abc_appConfigTabNewEdit.jsp?',
						'name',
						'height=200,width=600,toolbar=0,menubar=0,location=0,status=0,scrollbars=1,resizable=1,left=' + left + ',top=' + top);

	if (window.focus) {newwindow.focus()}

	return false;
	
}

/*
function openNew(){
	
	//document.getElementById('flexTable_Abc_AppConfigTab').style.display = 'none';


	var ajaxRequest = simpleAjaxUtil_getXmlHttpObject();


    if (ajaxRequest==null) {alert ("Browser does not support HTTP Request");return;}

    var url = '../../admin/abc_appConfigTabNewEdit.jsp';
    
    ajaxRequest.open("post", url, true);
    ajaxRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");

    ajaxRequest.onreadystatechange=function(){
	    if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
		    var response = ajaxRequest.responseText;

		    document.getElementById('').style.display = 'block';

	      } 
    };    
    //ajaxRequest.send(parameters);

}*/

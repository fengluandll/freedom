/**
 *ULR 26/04/2017 este archivo contiene toda la interaccion y eventos realizados en el pupup de la custom table asunto 
 */
$(document).ready(function(){
	var typeView=$('#typeView').val();
	var contextPath=$('#contextPath').val();
	var idMetaRow=$('#idMetaRow').val();
	
	findTemp(idMetaRow);
	findTemp2(idMetaRow);
	
	//Para guardar los ejercicios si idMetaRow es dif de cero se estan añadiendo mas ejercicios a un registro
	$('#saveAsunto').click(function(){
		var id=document.getElementById('idAsunto').value;
		var asunto=document.getElementById('asunto').value;
		if((id!=null && id!="")&&(asunto!=null && asunto!="")){
			$.ajax({
				url		:	contextPath+'/AddAsuntoServlet',
				data		:	$('#formAsunto').serialize(),
				type		:	'post',
				success	:	function(data){
					if(data=='ok'){
						findTemp(idMetaRow);
						findTemp2(idMetaRow);
						$('#formAsunto')[0].reset();
					}else{
						alert("error");
					}
				}
			});
		}else{
			swal({ title: "Aviso",   
				   text: "Los campos Id y Se aprobó no pueden estar vacíos",   
				   type: "warning",  
				   confirmButtonText: "Ok" });
		}
		
	});
	
	//Invoca funcion para editar
	$(document).on('click', "a.editElement", function() {
		console.log('Click edit class element');
        var idAsunto = $(this).data("asunto");
        callEdit(idAsunto);
    });
	
	//Llenar formulario paraEditar un ejercicio
	function callEdit(idRow){
		//updateEjercicio
		//formEjercicio
		$('#formUpdateAsunto').removeClass('hidden');
		$('#formAsunto').addClass('hidden');
		$.ajax({
			url		:	contextPath+'/AddAsuntoServlet',
			data	:	'tipoAccion=findEdit&idRow='+idRow,
			type	:	'post',
			dataType: 	'json',
			success	:	function(data){
				if(data['status']=="ok"){
					
					
					$('#idAsuntoRowU').val(data['id_asunto_row']);
					$('#idAsuntoU').val(data['id_asunto']);
					$('#asuntoU').val(data['asunto']);
				}else{
					alert("error");
				}
					
			}
		});
	}
	
	//Para mostrar el formulario de captura 
	$('#btnUpdateCancelar').click(function(){
		 $('#formAsunto').removeClass('hidden');
		 $('#formUpdateAsunto').addClass('hidden');
	});
	
	//Para actualizar el ejercicio 
	$('#btnUpdateAsunto').click(function(){
		$.ajax({
			 url		:	contextPath+'/AddAsuntoServlet',
			 data		:	$('#formUpdateAsunto').serialize(),
			 type		:	'post',
			 success	:	function(data){
				 if(data=='ok'){
					 findTemp(idMetaRow);
					 findTemp2(idMetaRow);
					 $('#formUpdateAsunto')[0].reset();
					 $('#formUpdateAsunto').addClass('hidden');
					 $('#formAsunto')[0].reset();
					 $('#formAsunto').removeClass('hidden');
				 }else{
					 alert("error");
				 }
			 }
		});
	});
	
	//Obtener los asuntos para mostrarlos en el popup
	function findTemp(idMetaRow){
		$.ajax({
			url		:	contextPath+'/AddAsuntoServlet',
			data	:	'tipoAccion=findTemp&idMetaRow='+idMetaRow,
			type	:	'post',
			success	:	function(data){
				$('#tableContent').html(data);
			}
		});
	}
	
	//Obtener los asuntos para setearlos a la ventana padre
	function findTemp2(idMetaRow){
		$.ajax({
			url		:	contextPath+'/AddAsuntoServlet',
			data	:	'tipoAccion=findTemp2&idMetaRow='+idMetaRow,
			type	:	'post',
			success	:	function(data){
				$('#tableContentHidden').html(data);
			}
		});
	}
	
	//Invoca funcion para eliminar
	$(document).on('click', "a.deleteElement", function() {
		var idAsunto = $(this).data("asunto");
        callDelete(idAsunto);
    });
	
	//mostrar alerta antes de eliminar
	function callDelete(idRow){
		swal({
			  title: "Confirmar",
			  text: "¿Está seguro que desea borrar este registro?",
			  type: "warning",
			  showCancelButton: true,
			  confirmButtonColor: "#DD6B55",
			  confirmButtonText: 'Si',
			  closeOnConfirm: true
			},
			function(){
			  deleteAsunto(idRow);
			  findTemp(idMetaRow);
			  findTemp2(idMetaRow);
			  //swal("Deleted!", "Your imaginary file has been deleted.", "success");
			});
	}
	
	//eliminar un ejercicio
	function deleteAsunto(idRow){
		$.ajax({
			url		:	contextPath+'/AddAsuntoServlet',
			data	:	'tipoAccion=delete&idRow='+idRow,
			type	:	'post',
			success	:	function(data){
				if(data=="ok"){
					findTemp(idMetaRow);
					findTemp2(idMetaRow);
				}else{
					alert("Ha ocurrido un error");
				}
					
			}
		});
	}
	
	$('#btnSendAsunto').click(function(){
		var tbody=$('#tableContentHidden').html();
		if(tbody!=null && tbody!=""){
			window.opener.document.getElementById("tblAsuntos").classList.remove("hidden");
			window.opener.document.getElementById("asuntosTemp").innerHTML = tbody;	
		}else{
			window.opener.document.getElementById("tblAsuntos").classList.add("hidden");
		}
		window.close();
	});
	
});

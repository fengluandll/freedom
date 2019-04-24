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
	$('#saveAgregar').click(function(){
		var id=document.getElementById('idAgregar').value;
		var asunto=document.getElementById('agregar').value;
		if((id!=null && id!="")&&(asunto!=null && asunto!="")){
			$.ajax({
				url		:	contextPath+'/AddAgregarServlet',
				data		:	$('#formAgregar').serialize(),
				type		:	'post',
				success	:	function(data){
					if(data=='ok'){
						findTemp(idMetaRow);
						findTemp2(idMetaRow);
						$('#formAgregar')[0].reset();
					}else{
						alert("error");
					}
				}
			});
		}else{
			swal({ title: "Aviso",   
				   text: "Los campos Id y Descripción no pueden estar vacíos",   
				   type: "warning",  
				   confirmButtonText: "Ok" });
		}
		
	});
	
	//Invoca funcion para editar
	$(document).on('click', "a.editElement", function() {
		console.log('Click edit class element');
        var idAsunto = $(this).data("agregar");
        callEdit(idAsunto);
    });
	
	//Llenar formulario paraEditar un ejercicio
	function callEdit(idRow){
		//updateEjercicio
		//formEjercicio
		$('#formUpdateAgregar').removeClass('hidden');
		$('#formAgregar').addClass('hidden');
		$.ajax({
			url		:	contextPath+'/AddAgregarServlet',
			data	:	'tipoAccion=findEdit&idRow='+idRow,
			type	:	'post',
			dataType: 	'json',
			success	:	function(data){
				if(data['status']=="ok"){
					
					
					$('#idAgregarRowU').val(data['id_agregar_row']);
					$('#idAgregarU').val(data['id_agregar']);
					$('#agregarU').val(data['agregar']);
				}else{
					alert("error");
				}
					
			}
		});
	}
	
	//Para mostrar el formulario de captura 
	$('#btnUpdateCancelar').click(function(){
		 $('#formAgregar').removeClass('hidden');
		 $('#formUpdateAgregar').addClass('hidden');
	});
	
	//Para actualizar el ejercicio 
	$('#btnUpdateAgregar').click(function(){
		$.ajax({
			 url		:	contextPath+'/AddAgregarServlet',
			 data		:	$('#formUpdateAgregar').serialize(),
			 type		:	'post',
			 success	:	function(data){
				 if(data=='ok'){
					 findTemp(idMetaRow);
					 findTemp2(idMetaRow);
					 $('#formUpdateAgregar')[0].reset();
					 $('#formUpdateAgregar').addClass('hidden');
					 $('#formAgregar')[0].reset();
					 $('#formAgregar').removeClass('hidden');
				 }else{
					 alert("error");
				 }
			 }
		});
	});
	
	//Obtener los Agregars para mostrarlos en el popup
	function findTemp(idMetaRow){
		$.ajax({
			url		:	contextPath+'/AddAgregarServlet',
			data	:	'tipoAccion=findTemp&idMetaRow='+idMetaRow,
			type	:	'post',
			success	:	function(data){
				$('#tableContent').html(data);
			}
		});
	}
	
	//Obtener los Agregars para setearlos a la ventana padre
	function findTemp2(idMetaRow){
		$.ajax({
			url		:	contextPath+'/AddAgregarServlet',
			data	:	'tipoAccion=findTemp2&idMetaRow='+idMetaRow,
			type	:	'post',
			success	:	function(data){
				$('#tableContentHidden').html(data);
			}
		});
	}
	
	//Invoca funcion para eliminar
	$(document).on('click', "a.deleteElement", function() {
		var idAsunto = $(this).data("agregar");
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
			url		:	contextPath+'/AddAgregarServlet',
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
	
	$('#btnSendAgregar').click(function(){
		var tbody=$('#tableContentHidden').html();
		if(tbody!=null && tbody!=""){
			window.opener.document.getElementById("tblAgregar").classList.remove("hidden");
			window.opener.document.getElementById("agregarTemp").innerHTML = tbody;	
		}else{
			window.opener.document.getElementById("tblAgregar").classList.add("hidden");
		}
		window.close();
	});
	
});

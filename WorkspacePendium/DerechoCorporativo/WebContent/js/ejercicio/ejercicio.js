$(document).ready(function(){
	
	$("#documentoSol").keyup(function() {
		getFormatDocumentum(this);
		if ( event.which == 13 ) {
			$("#fecha_docSol").focus();
		  }
	});

	
	$('#documentoSol').mask("000-000-000-0000-0000-00000-00-00-000");
	$('#documentoU').mask("000-000-000-0000-0000-00000-00-00-000");
	$('#fecha_docSol').mask("00/00/0000");
	$('#fecha_entregaSol').mask("00/00/0000");
	$('#fecha_docU').mask("00/00/0000");
	$('#fecha_entregaU').mask("00/00/0000");
	
	$("#fecha_docSol").keyup(function() {
	//	getMascaraFecha(this);
	}).blur(function(){
		validarFecha(this);
		//getMascaraFecha(this);
	}).bind({        
        paste : function(x){        	
        	//validarFecha(this);
    		//getMascaraFecha(this);
        }
    });
	
	$("#fecha_entregaSol").keyup(function() {
		//getMascaraFecha(this);
	}).blur(function(){
		validarFecha(this);
	//	getMascaraFecha(this);
	}).bind({        
        paste : function(x){        	
        //	validarFecha(this);
    	//	getMascaraFecha(this);
        }
    });
	
	//JAMS 08/02/2018 validacion de fechas
	
	$("#documentoU").keyup(function() {
		getFormatDocumentum(this);
		if ( event.which == 13 ) {
			$("#fecha_docSol").focus();
		  }
	});
	
	$("#fecha_docU").keyup(function() {
	//	getMascaraFecha(this);
	}).blur(function(){
		validarFecha(this);
	//	getMascaraFecha(this);
	}).bind({        
        paste : function(x){        	
        //	validarFecha(this);
    	//	getMascaraFecha(this);
        }
    });
	
	$("#fecha_entregaU").keyup(function() {
		//getMascaraFecha(this);
	}).blur(function(){
		validarFecha(this);
	//	getMascaraFecha(this);
	}).bind({        
        paste : function(x){        	
      //  	validarFecha(this);
    	//	getMascaraFecha(this);
        }
    });
	
	/////////
	
	
	
	var typeView=$('#typeView').val();
	var contextPath=$('#contextPath').val();
	var idMetaRow=$('#idMetaRow').val();
		findTemp(idMetaRow);
		findTemp2(idMetaRow);
		$('input[type="checkbox"]').click(function(){
	        var idDiv = $(this).data("div");
	        $("#" + idDiv).toggle();
	    });
		//Invoca funcion para eliminar
		$(document).on('click', "a.deleteElement", function() {
			var idEjercicio = $(this).data("ejercicio");
	        callDelete(idEjercicio);
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
				  deleteEjercicio(idRow);
				  findTemp(idMetaRow);
				  findTemp2(idMetaRow);
				  //swal("Deleted!", "Your imaginary file has been deleted.", "success");
				});
		}
		
		//Editar un ejercicio
		function deleteEjercicio(idRow){
			$.ajax({
				url		:	contextPath+'/AprobEjerSocServlet',
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
		
		//Invoca funcion para editar
		$(document).on('click', "a.editElement", function() {
			console.log('Click edit class element');
	        var idEjercicio = $(this).data("ejercicio");
	        callEdit(idEjercicio);
	    });
		
		//Lllenar formulario paraEditar un ejercicio
		function callEdit(idRow){
			//updateEjercicio
			//formEjercicio
			$('#formUpdateEjercicio').removeClass('hidden');
			$('#checksContent').addClass('hidden');
			$('#formEjercicio').addClass('hidden');
			$.ajax({
				url		:	contextPath+'/AprobEjerSocServlet',
				data	:	'tipoAccion=findEdit&idRow='+idRow,
				type	:	'post',
				dataType: 	'json',
				success	:	function(data){
					if(data['status']=="ok"){
						/*switch(data['tipo_documento']){
							case "solicitud":
								$('#headerUpdate').html('<label>Solictud</label>');
								break;
							case "infCom":
								$('#headerUpdate').html('<label>Informe del Comisario</label>');
								break;
							case "def":
								$('#headerUpdate').html('<label>Dictamen de los Estados Financieros</label>');
								break;
							case "df":
								$('#headerUpdate').html('<label>Dictamen Fiscal</label>');
								break;
							default:
								console.log("Algo ha salido mal");
								break;
						}*/
						//console.log('Ajax Edit');
						$('#typeDocU').val(data['tipo_documento']);
						//$('#ejercicioU').val(data['ejercicio_social']);
						$('#idEjercicioRowU').val(data['id_ejercicio']);
						$('#documentoU').val(data['no_documentum']);
						$('#fecha_docU').val(data['fecha_documentum']);
						$('#fecha_entregaU').val(data['fecha_entrega']);
					}else{
						alert("error");
					}
						
				}
			});
		}
		
		//Para guardar los ejercicios si idMetaRow es dif de cero se estan añadiendo mas ejercicios a un registro
		$('#saveEjercicio').click(function(){
			var nomDocumento=document.getElementById('typeDocS').value;
			
			if(nomDocumento!=null && nomDocumento!=""){
				$.ajax({
					url		:	contextPath+'/AprobEjerSocServlet',
					data		:	$('#formEjercicio').serialize(),
					type		:	'post',
					success	:	function(data){
						if(data=='ok'){
							findTemp(idMetaRow);
							findTemp2(idMetaRow);
							$('#formEjercicio')[0].reset();
						}else{
							alert("error");
						}
					}
				});
			}else{
				swal({ title: "Aviso",   
					   text: "El campo Nombre de Documento no puede estar vacío",   
					   type: "warning",  
					   confirmButtonText: "Ok" });
			}
			
		});
		
		//Para mostrar el formulario de captura 
		$('#btnUpdateCancelar').click(function(){
			 $('#checksContent').removeClass('hidden');
			 $('#formEjercicio').removeClass('hidden');
			 $('#formUpdateEjercicio').addClass('hidden');
		});
		
		//Para actualizar el ejercicio 
		$('#btnUpdateEjercicio').click(function(){
			$.ajax({
				 url		:	contextPath+'/AprobEjerSocServlet',
				 data		:	$('#formUpdateEjercicio').serialize(),
				 type		:	'post',
				 success	:	function(data){
					 if(data=='ok'){
						 findTemp(idMetaRow);
						 findTemp2(idMetaRow);
						 $('#formUpdateEjercicio')[0].reset();
						 $('#formUpdateEjercicio').addClass('hidden');
						 $('#formEjercicio')[0].reset();
						 $('#checksContent').removeClass('hidden');
						 $('#formEjercicio').removeClass('hidden');
					 }else{
						 alert("error");
					 }
				 }
			});
		});
		
		$('#btnSendEjercicio').click(function(){
			var tbody=$('#tableContentHidden').html();
			if(tbody!=null && tbody!=""){
				window.opener.document.getElementById("tblEjercicios").classList.remove("hidden");
				window.opener.document.getElementById("ejerciciosTemp").innerHTML = tbody;	
			}else{
				window.opener.document.getElementById("tblEjercicios").classList.add("hidden");
			}
			window.close();
		});
		
		//Obtener los ejercicios temporales para mostrarlos en el popup
		function findTemp(idMetaRow){
			$.ajax({
				url		:	contextPath+'/AprobEjerSocServlet',
				data	:	'tipoAccion=findTemp&idMetaRow='+idMetaRow,
				type	:	'post',
				success	:	function(data){
					$('#tableContent').html(data);
				}
			});
		}
		
		//Obtener los ejercicios temporales para setearlos a la ventana padre
		function findTemp2(idMetaRow){
			$.ajax({
				url		:	contextPath+'/AprobEjerSocServlet',
				data	:	'tipoAccion=findTemp2&idMetaRow='+idMetaRow,
				type	:	'post',
				success	:	function(data){
					$('#tableContentHidden').html(data);
				}
			});
		}
		
	});
	
	//Mascara año
	function getFecYear(pObjeto){
		var vSeparador = '';
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
	
	function getDocumentumDeTabla(noDocumentum){
		if(noDocumentum!=null && noDocumentum!=""){
			window.open("/DerechoCorporativo/faces/jsp/documentum/waitingPage.jsp?doc="+noDocumentum
					,null
					,"height=600,width=1100,status=yes,toolbar=no,menubar=no,location=no");			
		}else{
			swal({ title: "Aviso",   
				   text: "No. de Documento no puede estar vacío",   
				   type: "warning",  
				   confirmButtonText: "Ok" });
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

	}
	
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
	
function eje(){
		
		$('.mascaraDocumentum').mask("000-000-000-0000-0000-00000-00-00-000");
	}
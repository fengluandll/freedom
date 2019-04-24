<%
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
	response.setHeader("Pragma","no-cache"); //HTTP 1.0
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%


	// OK


%>


<script type="text/javascript">


/**
 *
 *
 * Para agregar animacion cambiar show() por toggle( 200 );
 *
 *
 */



 var globalSelectedSala = '';
 var globalSelectedFecha = '';



var global_id_evento_interprete = '';
var global_id_evento_tecnico = ''


     window.formato = function(){

			$.fn.datepicker.defaults.language = 'es';
			$('.date').datepicker( {
	    		  format: "dd/mm/yyyy"
	    		  //,
	    		  //  viewMode: "months",
	    		  //  minViewMode: "months"
	    	    });
          $('.time').timepicker({
              minuteStep: 1,
              template: 'modal',
              appendWidgetTo: 'body',
              showMeridian: false,
              defaultTime: false
          });
		}

function deleteComentario(idComentario){
	
	 swal({
		    title: "¿Eliminar el comentario?",
		    text: "El comentario se eliminara",
		    type: "warning",
		    showCancelButton: true,
		    confirmButtonColor: '#DD6B55',
		    confirmButtonText: 'Sí, continuar',
		    cancelButtonText: "No, cancelar",
		    closeOnConfirm: true,
		    closeOnCancel: true
		 },
		 function(isConfirm){

		   if (isConfirm){
				 $.ajax({
					 url: "/SAO/CRMComentariosDelete",
					 type: "post",
					 data: { key: idComentario },
					 success: function(data){
						 reloadTablaComentarios();
						 swal((data != "OK"?"Error: No se elimino el comentario":"Comentario eliminado"));
							
					 }

				 });

		    }
		 });
	
	}

function actualizarVistaPrevia() {

	$.ajax({
		type:'POST',
		url :"reporte_cotizacion_html.jsp",
		data: {
			ID_EVENTO : $("#id_evento").val()
		},
		success: function(data) {

			$("#divVistaPrevia").empty();
			$("#divVistaPrevia").append(data);

			//$("#divVistaPrevia").hide();
			//$("#divVistaPrevia").show();

		},
		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
	});

}




 function agregarPolitica(p_id_politica) {


	 $.ajax({
			type:'POST',
			url :"/SAO/PoliticaAddServlet",
			data: {
				ID_EVENTO : $("#id_evento").val(),
				id_politica : p_id_politica
			},
			success: function(data) {

				selectPoliticasEvento();
				selectPoliticasAplicables();

				//$('#divFechas').html(data);

				/*$("#divOutput").empty();
				$("#divOutput").append(data);

				$("#divOutput").hide();
				$("#divOutput").show();*/

			},
			error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
		});

 }


 function quitarPolitica(p_id_evento_politica) {


	 $.ajax({
			type:'POST',
			url :"/SAO/PoliticaRemoveServlet",
			data: {
				id_evento_politica : p_id_evento_politica
			},
			success: function(data) {

				selectPoliticasEvento();
				selectPoliticasAplicables();

				//$('#divFechas').html(data);

				/*$("#divOutput").empty();
				$("#divOutput").append(data);

				$("#divOutput").hide();
				$("#divOutput").show();*/

			},
			error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
		});
 }


 function selectPoliticasEvento() {

 	$.ajax({
 		type:'POST',
 		url :"/SAO/PoliticasEventoSelectServlet",
 		data: {
 			ID_EVENTO : $("#id_evento").val()
 		},
 		success: function(data) {

 			$("#divPoliticasEvento").empty();
 			$("#divPoliticasEvento").append(data);

 			$("#divPoliticasEvento").hide();
 			$("#divPoliticasEvento").show();


 			actualizarVistaPrevia();
 		},
 		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
 	});

 }


 function selectPoliticasAplicables() {

	 	$.ajax({
	 		type:'POST',
	 		url :"/SAO/PoliticasAplicablesSelectServlet",
	 		data: {
	 			ID_EVENTO : $("#id_evento").val(),
	 			ID_TIPO_EVENTO : $("#id_tipo_evento").val()
	 		},
	 		success: function(data) {

	 			$("#divPoliticasAplicables").empty();
	 			$("#divPoliticasAplicables").append(data);

	 			$("#divPoliticasAplicables").hide();
	 			$("#divPoliticasAplicables").show();

	 		},
	 		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
	 	});

}








 //COND



 function agregarCondicion(p_id_condicion) {


	 $.ajax({
			type:'POST',
			url :"/SAO/CondicionAddServlet",
			data: {
				ID_EVENTO : $("#id_evento").val(),
				id_condicion : p_id_condicion
			},
			success: function(data) {

				selectCondicionesEvento();
				selectCondicionesAplicables();

				//$('#divFechas').html(data);

				/*$("#divOutput").empty();
				$("#divOutput").append(data);

				$("#divOutput").hide();
				$("#divOutput").show();*/

			},
			error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
		});

 }


 function quitarCondicion(p_id_evento_condicion) {


	 $.ajax({
			type:'POST',
			url :"/SAO/CondicionRemoveServlet",
			data: {
				id_evento_condicion : p_id_evento_condicion
			},
			success: function(data) {

				selectCondicionesEvento();
				selectCondicionesAplicables();

				//$('#divFechas').html(data);

				/*$("#divOutput").empty();
				$("#divOutput").append(data);

				$("#divOutput").hide();
				$("#divOutput").show();*/

			},
			error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
		});
 }


 function selectCondicionesEvento() {

 	$.ajax({
 		type:'POST',
 		url :"/SAO/CondicionesEventoSelectServlet",
 		data: {
 			ID_EVENTO : $("#id_evento").val()
 		},
 		success: function(data) {

 			$("#divCondicionesEvento").empty();
 			$("#divCondicionesEvento").append(data);

 			$("#divCondicionesEvento").hide();
 			$("#divCondicionesEvento").show();


 			actualizarVistaPrevia();
 		},
 		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
 	});

 }


 function selectCondicionesAplicables() {

	 	$.ajax({
	 		type:'POST',
	 		url :"/SAO/CondicionesAplicablesSelectServlet",
	 		data: {
	 			ID_EVENTO : $("#id_evento").val(),
	 			ID_TIPO_EVENTO : $("#id_tipo_evento").val()
	 		},
	 		success: function(data) {

	 			$("#divCondicionesAplicables").empty();
	 			$("#divCondicionesAplicables").append(data);

	 			$("#divCondicionesAplicables").hide();
	 			$("#divCondicionesAplicables").show();

	 		},
	 		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
	 	});

}







 /// OBSERVACIONES




 function agregarObservacion(p_id_Observacion) {


	 $.ajax({
			type:'POST',
			url :"/SAO/ObservacionAddServlet",
			data: {
				ID_EVENTO : $("#id_evento").val(),
				id_observacion : p_id_Observacion
			},
			success: function(data) {

				selectObservacionesEvento();
				selectObservacionesAplicables();

				//$('#divFechas').html(data);

				/*$("#divOutput").empty();
				$("#divOutput").append(data);

				$("#divOutput").hide();
				$("#divOutput").show();*/

			},
			error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
		});

 }


 function quitarObservacion(p_id_evento_Observacion) {


	 $.ajax({
			type:'POST',
			url :"/SAO/ObservacionRemoveServlet",
			data: {
				id_evento_observacion : p_id_evento_Observacion
			},
			success: function(data) {

				selectObservacionesEvento();
				selectObservacionesAplicables();

				//$('#divFechas').html(data);

				/*$("#divOutput").empty();
				$("#divOutput").append(data);

				$("#divOutput").hide();
				$("#divOutput").show();*/

			},
			error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
		});
 }


 function selectObservacionesEvento() {

 	$.ajax({
 		type:'POST',
 		url :"/SAO/ObservacionesEventoSelectServlet",
 		data: {
 			ID_EVENTO : $("#id_evento").val()
 		},
 		success: function(data) {

 			$("#divObservacionesEvento").empty();
 			$("#divObservacionesEvento").append(data);

 			$("#divObservacionesEvento").hide();
 			$("#divObservacionesEvento").show();

 			actualizarVistaPrevia();

 		},
 		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
 	});

 }


 function selectObservacionesAplicables() {

	 	//alert($("#id_tipo_evento").val());


	 	$.ajax({
	 		type:'POST',
	 		url :"/SAO/ObservacionesAplicablesSelectServlet",
	 		data: {
	 			ID_EVENTO : $("#id_evento").val(),
	 			ID_TIPO_EVENTO : $("#id_tipo_evento").val()
	 		},
	 		success: function(data) {

	 			$("#divObservacionesAplicables").empty();
	 			$("#divObservacionesAplicables").append(data);

	 			$("#divObservacionesAplicables").hide();
	 			$("#divObservacionesAplicables").show();

	 		},
	 		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
	 	});

}



 //VIATICOS



 function agregarViatico(p_id_Viatico) {


	 $.ajax({
			type:'POST',
			url :"/SAO/ViaticoAddServlet",
			data: {
				ID_EVENTO : $("#id_evento").val(),
				id_viatico : p_id_Viatico
			},
			success: function(data) {

				selectViaticosEvento();
				selectViaticosAplicables();

				//$('#divFechas').html(data);

				/*$("#divOutput").empty();
				$("#divOutput").append(data);

				$("#divOutput").hide();
				$("#divOutput").show();*/

			},
			error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
		});

 }


 function quitarViatico(p_id_evento_Viatico) {


	 $.ajax({
			type:'POST',
			url :"/SAO/ViaticosRemoveServlet",
			data: {
				id_evento_viatico : p_id_evento_Viatico
			},
			success: function(data) {

				selectViaticosEvento();
				selectViaticosAplicables();

				//$('#divFechas').html(data);

				/*$("#divOutput").empty();
				$("#divOutput").append(data);

				$("#divOutput").hide();
				$("#divOutput").show();*/

			},
			error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
		});
 }


 function selectViaticosEvento() {

 	$.ajax({
 		type:'POST',
 		url :"/SAO/ViaticosEventoSelectServlet",
 		data: {
 			ID_EVENTO : $("#id_evento").val()
 		},
 		success: function(data) {

 			$("#divViaticosEvento").empty();
 			$("#divViaticosEvento").append(data);

 			$("#divViaticosEvento").hide();
 			//$("#divViaticosEvento").show();
 			$("#divViaticosEvento").show();


 			actualizarVistaPrevia();
 		},
 		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
 	});

 }


 function selectViaticosAplicables() {

	 	$.ajax({
	 		type:'POST',
	 		url :"/SAO/ViaticosAplicablesSelectServlet",
	 		data: {
	 			ID_EVENTO : $("#id_evento").val(),
	 			ID_TIPO_EVENTO : $("#id_tipo_evento").val()
	 		},
	 		success: function(data) {

	 			$("#divViaticosAplicables").empty();
	 			$("#divViaticosAplicables").append(data);

	 			$("#divViaticosAplicables").hide();
	 			//$("#divViaticosAplicables").show();
	 			$("#divViaticosAplicables").show();

	 		},
	 		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
	 	});

}








 // PRODUCTOS



 function agregarProducto(p_id_Producto) {


	 //alert(p_id_Producto);

	 $.ajax({
			type:'POST',
			url :"/SAO/ProductoAddServlet",
			data: {
				ID_EVENTO : $("#id_evento").val(),
				id_dia : globalSelectedFecha,
				id_producto : p_id_Producto,
				id_tipo_evento : $("#id_tipo_evento").val()
			},
			success: function(data) {

				selectProductosEvento();
				selectProductosAplicables();
				selectPaquetesAplicables();	// OPTIMIZABLE - Identificar las que no son necesarias

				//$('#divFechas').html(data);

				/*$("#divOutput").empty();
				$("#divOutput").append(data);

				$("#divOutput").hide();
				$("#divOutput").show();*/

			},
			error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
		});

 }


 function quitarProducto(p_id_evento_Producto) {


	 $.ajax({
			type:'POST',
			url :"/SAO/ProductoRemoveServlet",
			data: {
				id_evento_producto : p_id_evento_Producto
			},
			success: function(data) {

				selectProductosEvento();
				selectProductosAplicables();

				//$('#divFechas').html(data);

				/*$("#divOutput").empty();
				$("#divOutput").append(data);

				$("#divOutput").hide();
				$("#divOutput").show();*/

			},
			error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
		});
 }


 function selectProductosEvento() {

	 //alert($("#id_evento").val());
	 //alert(globalSelectedFecha);

 	$.ajax({
 		type:'POST',
 		url :"/SAO/ProductosEventoSelectServlet",
 		data: {
 			ID_EVENTO : $("#id_evento").val(),
 			id_tipo_evento : $("#id_tipo_evento").val(),
 			id_dia : globalSelectedFecha
 		},
 		dataType: 'json',
 		success: function(data) {

 			$("#divProductosEvento").empty();


 			$("#divProductosEvento").append(data.data);

 			$("#spanSalaDiaHeader").html(data.nombre);

 			$("#divProductosEvento").hide();
 			$("#divProductosEvento").show();

 			actualizarVistaPrevia();
 		},
 		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
 	});

 }


 function selectProductosAplicables() {

	 	$.ajax({
	 		type:'POST',
	 		url :"/SAO/ProductosAplicablesSelectServlet",
	 		data: {
	 			ID_EVENTO : $("#id_evento").val(),
	 			id_dia : globalSelectedFecha,
	 			id_version : $("#id_version").val(),
	 			filter : $("#productosFilter").val(),
	 			id_tipo_evento : $("#id_tipo_evento").val(),
	 		},
	 		success: function(data) {

	 			$("#divProductosAplicables").empty();
	 			$("#divProductosAplicables").append(data);

	 			$("#divProductosAplicables").hide();
	 			$("#divProductosAplicables").show();

	 		},
	 		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
	 	});

}








 function selectPaquetesAplicables() {
	 if($("#id_tipo_evento").val() == '1'){
	 	$.ajax({
	 		type:'POST',
	 		url :"/SAO/PaquetesSelectServlet",
	 		data: {
	 			filter : $("#paquetesFilter").val()
	 		},
	 		success: function(data) {

	 			$("#divPaquetesAplicables").empty();
	 			$("#divPaquetesAplicables").append(data);

	 			$("#divPaquetesAplicables").hide();
	 			$("#divPaquetesAplicables").show();

	 		},
	 		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
	 	});
	 }
}











 function agregarPaquete(p_id_paquete) {


	 //alert(p_id_Producto);

	 $.ajax({
			type:'POST',
			url :"/SAO/EventoAddPaqueteServlet",
			data: {
				ID_EVENTO : $("#id_evento").val(),
				id_evento_sala : globalSelectedFecha,
				id_paquete : p_id_paquete
			},
			success: function(data) {

				selectProductosEvento();
				selectProductosAplicables();
				selectPaquetesAplicables();

				//$('#divFechas').html(data);

				/*$("#divOutput").empty();
				$("#divOutput").append(data);

				$("#divOutput").hide();
				$("#divOutput").show();*/

			},
			error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
		});

 }















 function updateEvento(component) {

	// alert();

 	$.ajax({
 		type:'POST',
 		url :"/SAO/EventoUpdateServlet",
 		data: {
 			//id_evento : $("#id_evento").val(),
 			//nombre : $("#NOMBRE").val(),
 			//status : $("#STATUS").val(),
 			//id_empresa : $("#ID_EMPRESA").val()

 			id_evento : $("#id_evento").val(),
			field_id : component.id,
			field_value : component.value
 		},
 		success: function(data) {

 			//$("#divSedes").empty();
 			//$("#divSedes").append(data);

 			//$("#divSedes").hide();
 			//$("#divSedes").show();

 			if(component.id == "id_cliente") {

 				reloadEventoForm();
 			}



 			//
 			// Recargar tabla de datos para que se actualice la moneda.
 			//
 			if(globalSelectedSala != '') {
 				selectSala(globalSelectedSala);
 			}

 			actualizarVistaPrevia();

 		},
 		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
 	});

 }


 function reloadEventoForm() {

 	$.ajax({
 		type:'POST',
 		url :"/SAO/EventoFormServlet",
 		data: {
 			ID_EVENTO : $("#id_evento").val()
 		},
 		success: function(data) {

 			$("#divEventoForm").empty();
 			$("#divEventoForm").append(data);

 			//$("#divEventoForm").hide();
 			//$("#divEventoForm").show();
 		},
 		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
 	});

 }







 function selectSalas() {

// 	 alert($("#id_version").val());

 	$.ajax({
 		type:'POST',
 		url :"/SAO/SalaSelectServlet",
 		data: {
 			ID_EVENTO : $("#id_evento").val(),
 			id_version : $("#id_version").val()
 		},
 		success: function(data) {

//  			alert(data);

 			$("#divSalas").empty();
 			$("#divSalas").append(data);

 			$("#divSalas").hide();
 			$("#divSalas").show();

 			actualizarVistaPrevia();
 		},
 		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
 	});

 }



 function nuevaSala() {

 	$.ajax({
 		type:'POST',
 		url :"/SAO/SalaInsertServlet",
 		data: {
 			id_evento : $("#id_evento").val(),
 			id_version : $("#id_version").val()
 		},
 		success: function(data) {

 			selectSalas();

 			/*$("#divOutput").empty();
 			$("#divOutput").append(data);

 			$("#divOutput").hide();
 			$("#divOutput").show();*/

 		},
 		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
 	});

 }




 function nuevaFecha() {

	 	$.ajax({
	 		type:'POST',
	 		url :"/SAO/SalaFechaInsertServlet",
	 		data: {
	 			id_evento_sala : globalSelectedSala
	 		},
	 		success: function(data) {

	 			selectSala(globalSelectedSala);

	 			/*$("#divOutput").empty();
	 			$("#divOutput").append(data);

	 			$("#divOutput").hide();
	 			$("#divOutput").show();*/

	 		},
	 		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
	 	});

	 }


function updateCantidad(p_id_evento_producto, object) {


	$.ajax({
		type:'POST',
		url :"/SAO/ValidateCantidadProductoEventoServlet",
		data: {
			id_evento_producto : p_id_evento_producto,
			cantidad: object.value
		},
		success: function(data) {


			if(data == "OK") {

				doUpdateCantidad(p_id_evento_producto, object);
			} else {

				swal(data);

				selectProductosEvento();
			}

		},
		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
	});
}


function doUpdateCortesia(p_id_evento_producto, object) {

	$.ajax({
		type:'POST',
		url :"/SAO/ProductoEventoCortesiaUpdateServlet",
		data: {
			id_evento_producto : p_id_evento_producto,
			cortesia: $(object).val()
		},
		success: function(data) {
			selectInterpretesAsignados();
			actualizarVistaPrevia();

		},
		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
	});
}



function doUpdateCortesiaTE(p_id_evento_producto, object) {

	$.ajax({
		type:'POST',
		url :"/SAO/ProductoTEventoCortesiaUpdateServlet",
		data: {
			id_evento_producto : p_id_evento_producto,
			cortesia: $(object).val()
		},
		success: function(data) {
			selectInterpretesAsignados();
			actualizarVistaPrevia();
		},
		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
	});
}

function updateCantidadProductoTE(p_id_evento_producto, object) {

	/*
	$.ajax({
		type:'POST',
		url :"/SAO/ValidateCantidadProductoEventoServlet",
		data: {
			id_evento_producto : p_id_evento_producto,
			cantidad: object.value
		},
		success: function(data) {


			if(data == "OK") {

				doUpdateCantidad(p_id_evento_producto, object);
			} else {

				alert(data);

				selectProductosEvento();
			}

		},

	});
	*/
}



function doUpdateCantidad(p_id_evento_producto, object) {

	$.ajax({
		type:'POST',
		url :"/SAO/ProductoEventoCantidadUpdateServlet",
		data: {
			id_evento_producto : p_id_evento_producto,
			cantidad: object.value
		},
		success: function(data) {




			//if($("#id_moneda").val() == "2") {
					// OMNILINGUA USA
			if($("#id_tipo_cotizacion").val() == "4") {

				suffix = "_usd";
			} else {

				suffix = "_mxn";
			}

			//alert(data);

			//alert(document.getElementById("div-"+"precio_cliente_descuento-" + p_id_evento_producto));


			selectInterpretesAsignados();

			//alert("div-"+"precio_cliente_descuento" + suffix + "-" + p_id_evento_producto);
			//alert("div-"+"precio_especial_descuento" + suffix + "-" + p_id_evento_producto);

			updateScreenValue("div-"+"precio_cliente_descuento" + suffix + "-" + p_id_evento_producto);
			updateScreenValue("div-"+"precio_especial_descuento" + suffix + "-" + p_id_evento_producto);

			actualizarVistaPrevia();
			/*$("#divOutput").empty();
			$("#divOutput").append(data);

			$("#divOutput").hide();
			$("#divOutput").show();*/

		},
		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
	});
}






function updatePrecioEspecial(p_id_evento_producto, object) {


	$.ajax({
		type:'POST',
		url :"/SAO/ProductoEventoPrecioUpdateServlet",
		data: {
			id_evento_producto : p_id_evento_producto,
			cantidad: object.value
		},
		success: function(data) {


			//selectInterpretesAsignados();

			//updateScreenValue("div-"+"precio_cliente_descuento-" + p_id_evento_producto);
			//updateScreenValue("div-"+"precio_especial_descuento-" + p_id_evento_producto);

			actualizarVistaPrevia();
			/*$("#divOutput").empty();
			$("#divOutput").append(data);

			$("#divOutput").hide();
			$("#divOutput").show();*/

		},
		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
	});
}




function updateDescuento(p_id_evento_producto, object) {

	$.ajax({
		type:'POST',
		url :"/SAO/ProductoEventoDescuentoUpdateServlet",
		data: {
			id_evento_producto : p_id_evento_producto,
			descuento: object.value
		},
		success: function(data) {

			if(data == "OK") {

				// Recalcular Totales en Pantalla




				updateScreenValue("div-"+"precio_cliente_descuento-" + p_id_evento_producto);
				updateScreenValue("div-"+"precio_especial_descuento-" + p_id_evento_producto);



			} else {

				//alert(data);
				selectFecha(globalSelectedFecha);
			}





			//selectInterpretesAsignados();
			/*$("#divOutput").empty();
			$("#divOutput").append(data);

			$("#divOutput").hide();
			$("#divOutput").show();*/

		},
		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
	});
}


function updateScreenValue(div) {

	//alert(("#" + div1 + "." + div2));

	//$("#" + div1 + "." + div2).html("ok");

	$.ajax({
		type:'POST',
		url :"/SAO/SelecterServlet",
		data: {
			div_field_code : div,
			dummyVoidParam : ''
		},
		success: function(data) {

			//alert(" data: " + data);

			//alert("div: " + div);

			//alert($("#" + div));


			//document.getElementById(div).innerHtml = data;

			//$("#" + div).html(data);

			$("#" + div).empty();
			$("#" + div).append(data);


			//$("#" + div).html("ok");


			/*$("#divOutput").empty();
			$("#divOutput").append(data);

			$("#divOutput").hide();
			$("#divOutput").show();*/

		},
		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
	});




}



function updateSalaNombre(p_key, object) {

	//alert('updateSede:' + KEY);
	//alert('object:' + object);


	$.ajax({
		type:'POST',
		url :"/SAO/SalaUpdateNameServlet",
		data: {
			key : p_key,
			nom: object.value
		},
		success: function(data) {

			/*$("#divOutput").empty();
			$("#divOutput").append(data);

			$("#divOutput").hide();
			$("#divOutput").show();*/

			actualizarVistaPrevia();
		},
		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
	});



}




function updateSubtotalRecord(p_key, object) {

	//alert('updateSede:' + KEY);
	//alert('object:' + object);


	$.ajax({
		type:'POST',
		url :"/SAO/SubtotalUpdateServlet",
		data: {
			key : p_key,
			nom: object.value,
			code : object.id
		},
		success: function(data) {

			/*$("#divOutput").empty();
			$("#divOutput").append(data);

			$("#divOutput").hide();
			$("#divOutput").show();*/



			selectSalas();

			actualizarVistaPrevia();
		},
		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
	});



}












/***/
function insertProductoAbierto() {

	$.ajax({
		type:'POST',
		url :"/SAO/ProductoAbiertoInsertServlet",
		data: {
			id_evento_sala : globalSelectedSala
		},
		success: function(data) {

			/*$("#divProductosAbiertosEvento").empty();
			$("#divProductosAbiertosEvento").append(data);

			$("#divProductosAbiertosEvento").hide();
			$("#divProductosAbiertosEvento").show();*/

			reloadProductosAbiertos();

		},
		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
	});
}

/***/
function reloadProductosAbiertos() {

	$.ajax({
		type:'POST',
		url :"/SAO/ProductoAbiertoSelectServlet",
		data: {
			id_dia : globalSelectedSala
		},
		success: function(data) {

			$("#divProductosAbiertosEvento").empty();
			$("#divProductosAbiertosEvento").append(data);

			$("#divProductosAbiertosEvento").hide();
			$("#divProductosAbiertosEvento").show();
		},
		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
	});
}

/***/
function updateProductoAbiertoRecord(p_key, object) {

	$.ajax({
		type:'POST',
		url :"/SAO/ProductoAbiertoUpdateServlet",
		data: {
			key : p_key,
			nom: object.value,
			code : object.id
		},
		success: function(data) {

			actualizarVistaPrevia();
		},
		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
	});
}

/***/
function quitarProductoAbierto(row_id) {

	$.ajax({
		type:'POST',
		url :"/SAO/ProductoAbiertoRemoveServlet",
		data: {
			id_producto_abierto : row_id
		},
		success: function(data) {

			reloadProductosAbiertos();

		},
		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
	});
}
/***/














function updateSalaCantidad(p_key, object) {

	//alert('updateSede:' + KEY);
	//alert('object:' + object);


	$.ajax({
		type:'POST',
		url :"/SAO/SalaUpdateCantidadServlet",
		data: {
			key : p_key,
			nom: object.value
		},
		success: function(data) {

			/*$("#divOutput").empty();
			$("#divOutput").append(data);

			$("#divOutput").hide();
			$("#divOutput").show();*/

			actualizarVistaPrevia();
		},
		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
	});



}


function selectSalaTraduccionEscrita() {


	$.ajax({
		type:'POST',
		url :"/SAO/SalaTraduccionEscritaServlet",
		data: {
			dummyParam : ''
		},
		success: function(data) {

			selectSala(data);

		},
		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
	});





}



function selectSala(p_id_sala) {

	$('#detalleCotizacion').show('slow');
	///////$("#divLinkNuevaFecha").show();

	globalSelectedSala = p_id_sala;

	//alert('select sede'  + p_id_sede);

// 	$.ajax({
// 		type:'POST',
// 		url :"/SAO/SalaFechasSelectServlet",
// 		data: {
// 			id_sala : p_id_sala
// 		},
// 		success: function(data) {

// 			//$('#divFechas').html(data);

// 			$("#divFechas").empty();
// 			$("#divFechas").append(data);

// 			$("#divFechas").hide();
// 			$("#divFechas").show();

// 		},
// 		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
// 	});


// 	$.ajax({
// 		type:'POST',
// 		url :"/SAO/SalaNombreServlet",
// 		data: {
// 			id_sala : p_id_sala
// 		},
// 		success: function(data) {

// 			$('#divNombreSala').html("Fechas de la Sala: <b><u>" + data + "</u></b>");

// 			/*$("#divOutput").empty();
// 			$("#divOutput").append(data);

// 			$("#divOutput").hide();
// 			$("#divOutput").show();*/

// 		},
// 		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
// 	});






	selectFecha(p_id_sala);
	reloadProductosAbiertos();



}


function selectFecha(p_id) {

	//alert(p_id);

	globalSelectedFecha = p_id;


	selectProductosEvento();
	selectProductosAplicables();

	if($("#id_tipo_evento").val() == "1"){
		selectPaquetesAplicables();
	}

	/**
	ya no aplica
	loadEncabezado(p_id);

	*/

}


function loadEncabezado(p_id_fecha) {

	//alert("loadEncabezado p_id_fecha: " + p_id_fecha);

	$.ajax({
		type:'POST',
		url :"/SAO/EncabezadoNameServlet",
		data: {
			id_dia : p_id_fecha
		},
		success: function(data) {

			//selectSedes();
			//alert(data);

			//$('#spanSalaDiaHeader').html(data);
			$('#spanSalaDiaHeader').html("");

			//$('#divFechas').html(data);

			/*$("#divOutput").empty();
			$("#divOutput").append(data);

			$("#divOutput").hide();
			$("#divOutput").show();*/

		},
		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
	});

}






function deleteSala(p_id_sala) {


	$("#divLinkNuevaFecha").hide();

	globalSelectedSala = '';

	//alert('select sede'  + p_id_sede);

	$.ajax({
		type:'POST',
		url :"/SAO/SalaDeleteServlet",
		data: {
			id_sala : p_id_sala
		},
		success: function(data) {

			//selectSedes();


			selectSalas();


			//selectSala(0);
			//selectFecha(0);


			$("#divFechas").empty();
			$("#divProductosEvento").empty();
			$("#divProductosAplicables").empty();


			$("#divProductosAbiertosEvento").empty();



			//$('#divFechas').html(data);

			/*$("#divOutput").empty();
			$("#divOutput").append(data);

			$("#divOutput").hide();
			$("#divOutput").show();*/

		},
		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
	});

}




// function nuevaFecha____() {

// 	$.ajax({
// 		type:'POST',
// 		url :"/SAO/SedeSelectServlet",
// 		data: {
// 			ID_EVENTO : $("#id_evento").val()
// 		},
// 		success: function(data) {

// 			$("#divSedes").empty();
// 			$("#divSedes").append(data);

// 			$("#divSedes").hide();
// 			$("#divSedes").show();

// 		},
// 		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
// 	});

// }





function updateSalaFecha(p_key, object) {

	//alert(object.value);

	$.ajax({
		type:'POST',
		url :"/SAO/SalaDiaUpdateFechaServlet",
		data: {
			key : p_key,
			fecha: object.value
		},
		success: function(data) {

			/*$("#divOutput").empty();
			$("#divOutput").append(data);

			$("#divOutput").hide();
			$("#divOutput").show();*/

		},
		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
	});

}



function updateInterpreteFecha(p_key, object) {

	//alert(object.value);

	$.ajax({
		type:'POST',
		url :"/SAO/AsignacionInterpreteUpdateFechaServlet",
		data: {
			key : p_key,
			fecha: object.value
		},
		success: function(data) {

			/*$("#divOutput").empty();
			$("#divOutput").append(data);

			$("#divOutput").hide();
			$("#divOutput").show();*/


		},
		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
	});

}




//updateInterpreteHora



function updateAsignacionInterpreteCampo(p_key, object, p_campo) {

	//alert(object.value);

	$.ajax({
		type:'POST',
		url :"/SAO/AsignacionInterpreteUpdateCampoServlet",
		data: {
			key : p_key,
			valor: object.value,
			campo: p_campo
		},
		success: function(data) {

			/*$("#divOutput").empty();
			$("#divOutput").append(data);

			$("#divOutput").hide();
			$("#divOutput").show();*/

		},
		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
	});

}



function updateSalaCampo(p_key, object, p_campo) {

	//alert(object.value);

	$.ajax({
		type:'POST',
		url :"/SAO/SalaDiaUpdateCampoServlet",
		data: {
			key : p_key,
			valor: object.value,
			campo: p_campo
		},
		success: function(data) {

			/*$("#divOutput").empty();
			$("#divOutput").append(data);

			$("#divOutput").hide();
			$("#divOutput").show();*/

			actualizarVistaPrevia();
		},
		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
	});

}




function deleteSalaFecha(p_id) {

	$.ajax({
		type:'POST',
		url :"/SAO/SalaFechaDeleteServlet",
		data: {
			id_dia : p_id
		},
		success: function(data) {

			selectSala(globalSelectedSala);

			/*$("#divOutput").empty();
			$("#divOutput").append(data);

			$("#divOutput").hide();
			$("#divOutput").show();*/

		},
		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
	});

}



function updateArea(p_key, object) {

	//alert('updateSede:' + KEY);
	//alert('object:' + object);


	$.ajax({
		type:'POST',
		url :"/Prosante/EventoAreaUpdateServlet",
		data: {
			key : p_key,
			nom: object.value
		},
		success: function(data) {

			/*$("#divOutput").empty();
			$("#divOutput").append(data);

			$("#divOutput").hide();
			$("#divOutput").show();*/

		},
		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
	});
}




function deleteArea(p_id_area) {

	//alerrt();
	//$("#divLinkNuevaFecha").hide();

	globalSelectedSala = '';

	//alert('select sede'  + p_id_sede);

	$.ajax({
		type:'POST',
		url :"/Prosante/EventoAreaDeleteServlet",
		data: {
			id_area : p_id_area
		},
		success: function(data) {

			selectAreas();


			//$('#divFechas').html(data);

			/*$("#divOutput").empty();
			$("#divOutput").append(data);

			$("#divOutput").hide();
			$("#divOutput").show();*/

		},
		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
	});

}




function selectAreas() {

	$.ajax({
		type:'POST',
		url :"/Prosante/EventoAreaSelectServlet",
		data: {
			ID_EVENTO : $("#id_evento").val()
		},
		success: function(data) {

			$("#divAreas").empty();
			$("#divAreas").append(data);

			$("#divAreas").hide();
			$("#divAreas").show();

		},
		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
	});

}




function nuevaArea() {

 	$.ajax({
 		type:'POST',
 		url :"/Prosante/EventoAreaInsertServlet",
 		data: {
 			id_evento : $("#id_evento").val()
 		},
 		success: function(data) {

 			selectAreas();

 			/*$("#divOutput").empty();
 			$("#divOutput").append(data);

 			$("#divOutput").hide();
 			$("#divOutput").show();*/

 		},
 		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
 	});

 }

function selectEventoInterprete(p_id_evento_interprete) {


	global_id_evento_interprete = p_id_evento_interprete;

	//alert(global_id_evento_interprete);

	$.ajax({
		type:'POST',
		url :"/SAO/InterpretesDisponiblesSelectServlet",
		data: {
			ID_EVENTO : $("#id_evento").val(),
			selected_especialidades : selectedEspecialidades(),
			selected_idiomas : selectedIdiomas()
		},
		success: function(data) {

			$("#divInterpretesDisponibles").html(data);

			$("#divInterpretesDisponibles").hide();
// 			$("#divInterpretesDisponibles").show();
			$("#divInterpretesDisponibles").show();

			$('#tablaInterpretesDisponibles').ready( function () {
			    $('#tablaInterpretesDisponibles').dataTable({
			    	  dom: '<"top"flp>rt<"bottom"i><"clear">',
			    	'bSort': false,"language":{
                    "sProcessing":     "Procesando...",
                    "sLengthMenu":     "Mostrar _MENU_ registros",
                    "sZeroRecords":    "No se encontraron resultados",
                    "sEmptyTable":     "Ningún dato disponible en esta tabla",
                    "sInfo":           "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
                    "sInfoEmpty":      "Mostrando registros del 0 al 0 de un total de 0 registros",
                    "sInfoFiltered":   "(filtrado de un total de _MAX_ registros)",
                    "sInfoPostFix":    "",
                    "sSearch":         "Buscar:",
                    "sUrl":            "",
                    "sInfoThousands":  ",",
                    "sLoadingRecords": "Cargando...",
                    "oPaginate": {
                        "sFirst":    "Primero",
                        "sLast":     "Último",
                        "sNext":     "Siguiente",
                        "sPrevious": "Anterior"
                    },
                    "oAria": {
                        "sSortAscending":  ": Activar para ordenar la columna de manera ascendente",
                        "sSortDescending": ": Activar para ordenar la columna de manera descendente"
                    }
                },	responsive : true,
                    'aoColumns': [
                          { sWidth: "20%", bSearchable: false, bSortable: false },
                          { sWidth: "20%", bSearchable: true, bSortable: true },
                          { sWidth: "60%", bSearchable: true, bSortable: true }
                          //match the number of columns here for table1
                    ],
                    "scrollCollapse": false,
                    "info":           true,
                    "paging":         true}
			    );
			    $("#tablaInterpretesDisponibles_filter").css("width","initial");
			    $("#tablaInterpretesDisponibles_info").css("white-space","initial");

			});
			nuevaAsignacionDeInterprete();


		},
		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
	});

}


function asignarInterprete(p_id_interprete) {


	$.ajax({
		type:'POST',
		url :"/SAO/InterpreteAsignacionValidacionServlet",
		data: {
			id_interprete : p_id_interprete,
			id_evento_interprete : global_id_evento_interprete
		},
		success: function(data) {

				if(data != "OK") {

				    swal({
                      title: data,
                      type: 'warning',
                      showCancelButton: true,html:true,
                      confirmButtonColor: '#DD6B55',
                      cancelButtonText: 'Cancelar',
                      closeOnConfirm: true
                    },
                    function(){
                    	doAsignarInterprete(p_id_interprete)
                    });

				} else {

					doAsignarInterprete(p_id_interprete);
				}



		},
		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
	});


}


function doAsignarInterprete(p_id_interprete) {

	$.ajax({
		type:'POST',
		url :"/SAO/InterpreteAsignacionServlet",
		data: {
			id_interprete : p_id_interprete,
			id_evento_interprete : global_id_evento_interprete
		},
		success: function(data) {


			selectInterpretesAsignados();
			//$("#divInterpretesDisponibles").empty();
			//$("#divInterpretesDisponibles").append(data);

			//$("#divInterpretesDisponibles").hide();
			//$("#divInterpretesDisponibles").show();
			$("#CalenderModalEdit").modal('hide');
		},
		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
	});

}



function desAsignarInterprete(ID){

	swal({
		  title: 'Se remover&aacute; la asignaci&oacute;n',
		  type: 'warning',
		  showCancelButton: true,html:true,
		  confirmButtonColor: '#DD6B55',
		  cancelButtonText: 'Cancelar',
		  closeOnConfirm: true
		},
		function(){
			$.ajax({
				type:'POST',
				url :"/SAO/InterpreteDesAsignacionServlet",
				data: {
					id_evento_interprete : ID
				},
				success: function(data) {


					selectInterpretesAsignados();

				},
				error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
				});

		});


}

function desAsignarTecnico(ID){
	swal({
		  title: 'Se remover&oacute; la asignaci&oacute;n',
		  type: 'warning',
		  showCancelButton: true,
		  confirmButtonColor: '#DD6B55',
		  cancelButtonText: 'Cancelar',
		  closeOnConfirm: true
		},
		function(){

			$.ajax({
			type:'POST',
			url :"/SAO/TecnicoDesAsignacionServlet",
			data: {
				id_evento_tecnico : ID
			},
			success: function(data) {


				selectTecnicosAsignados();

			},
			error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
			});

		});


}


function eliminarEventoTecnico(ID){
	swal({
		  title: 'Se remover&aacute; t&eacute;cnico',
		  type: 'warning',
		  showCancelButton: true,html:true,
		  confirmButtonColor: '#DD6B55',
		  cancelButtonText: 'Cancelar',
		  closeOnConfirm: true
		},
		function(){


			$.ajax({
			type:'POST',
			url :"/SAO/TecnicoRemoverServlet",
			data: {
				id_evento_tecnico : ID
			},
			success: function(data) {


				selectTecnicosAsignados();

			},
			error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
			});

		});
}

function eliminarEventoInterprete(ID){

	swal({
		  title: 'Se remover&aacute; la asignaci&oacute;n',
		  type: 'warning',html:true,
		  showCancelButton: true,
		  confirmButtonColor: '#DD6B55',
		  confirmButtonText:'Continuar',
		  cancelButtonText: 'Cancelar',
		  closeOnConfirm: true
		},
		function(){

			$.ajax({
				type:'POST',
				url :"/SAO/InterpreteRemoverServlet",
				data: {
					id_evento_interprete : ID
				},
				success: function(data) {


					selectInterpretesAsignados();

				},
				error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
				});

		});

}

function selectInterpretesAsignados() {

	$.ajax({
		type:'POST',
		url :"/SAO/InterpretesAsignadosSelectServlet",
		data: {
			ID_EVENTO : $("#id_evento").val()
		},
		success: function(data) {

			$("#divInterpretesAsignados").empty();
			$("#divInterpretesAsignados").append(data);

			$("#divInterpretesAsignados").hide();
// 			$("#divInterpretesAsignados").show();
			$("#divInterpretesAsignados").show();


			$("#divInterpretesDisponibles").empty();
			window.formato();
		},
		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
	});

}


function nuevoEspacioAsignacionInterprete(p_id_evento_sala) {



	$.ajax({
		type:'POST',
		url :"/SAO/AddEventoInterpreteServlet",
		data: {
			id_evento_sala : p_id_evento_sala
		},
		success: function(data) {

			selectInterpretesAsignados();

		},
		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
	});



}




function doFilterInterpretes() {

	//alert(selectedEspecialidades());

	////////global_id_evento_interprete = p_id_evento_interprete;

	//alert(global_id_evento_interprete);





	$.ajax({
		type:'POST',
		url :"/SAO/InterpretesDisponiblesSelectServlet",
		data: {
			ID_EVENTO : $("#id_evento").val(),
			selected_especialidades : selectedEspecialidades(),
			selected_idiomas : selectedIdiomas()
		},
		success: function(data) {

			$("#divInterpretesDisponibles").empty();
			$("#divInterpretesDisponibles").html(data);

			$('#tablaInterpretesDisponibles').ready( function () {
					$('#tablaInterpretesDisponibles').dataTable({
						  dom: '<"top"flp>rt<"bottom"i><"clear">',
						'bSort': false,"language":{
										"sProcessing":     "Procesando...",
										"sLengthMenu":     "Mostrar _MENU_ registros",
										"sZeroRecords":    "No se encontraron resultados",
										"sEmptyTable":     "NingÃºn dato disponible en esta tabla",
										"sInfo":           "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
										"sInfoEmpty":      "Mostrando registros del 0 al 0 de un total de 0 registros",
										"sInfoFiltered":   "(filtrado de un total de _MAX_ registros)",
										"sInfoPostFix":    "",
										"sSearch":         "Buscar:",
										"sUrl":            "",
										"sInfoThousands":  ",",
										"sLoadingRecords": "Cargando...",
										"oPaginate": {
												"sFirst":    "Primero",
												"sLast":     "Último",
												"sNext":     "Siguiente",
												"sPrevious": "Anterior"
										},
										"oAria": {
												"sSortAscending":  ": Activar para ordenar la columna de manera ascendente",
												"sSortDescending": ": Activar para ordenar la columna de manera descendente"
										}
								},	responsive : true,
										'aoColumns': [
													{ sWidth: "20%", bSearchable: false, bSortable: false },
													{ sWidth: "20%", bSearchable: true, bSortable: true },
													{ sWidth: "60%", bSearchable: true, bSortable: true }
													//match the number of columns here for table1
										],
										"scrollCollapse": false,
										"info":           true,
										"paging":         true}
					);
					$("#tablaInterpretesDisponibles_filter").css("width","initial");
					$("#tablaInterpretesDisponibles_info").css("white-space","initial");
				});
			$("#divInterpretesDisponibles").hide();
			//$("#divInterpretesDisponibles").show();

			$("#divInterpretesDisponibles").show();

		},
		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
	});


}


function selectedEspecialidades() {

	var codes = '|';

	var inputs = document.getElementsByTagName("input");

	for(var i = 0; i < inputs.length; i++) {

	    if(inputs[i].type == "checkbox") {

	        //inputs[i].checked = true;

	        //alert(inputs[i].id);
	        if(inputs[i].id.indexOf("especialidad") != -1) {


		        if(inputs[i].checked == true) {
		        	codes += inputs[i].id + "|";
		        }

	        }

	    }
	}

	return codes;
}



function selectedIdiomas() {

	var codes = '|';

	var inputs = document.getElementsByTagName("input");

	for(var i = 0; i < inputs.length; i++) {

	    if(inputs[i].type == "checkbox") {

	        //inputs[i].checked = true;

	        //alert(inputs[i].id);
	        if(inputs[i].id.indexOf("idioma") != -1) {


		        if(inputs[i].checked == true) {
		        	codes += inputs[i].id + "|";
		        }

	        }

	    }
	}

	return codes;
}



function nuevaAsignacionDeInterprete() {

	//alert('NEW');

	 $('#fc_edit').click();
}

function nuevoEspacioAsignacionTecnico(p_id_evento_sala) {

	$.ajax({
		type:'POST',
		url :"/SAO/AddEventoSalaTecnicoServlet",
		data: {
			id_evento_sala : p_id_evento_sala
		},
		success: function(data) {

			selectTecnicosAsignados();

		},
		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
	});



}

function updateAsignacionTecnicoCampo(p_key, object, p_campo) {

	//alert(object.value);

	$.ajax({
		type:'POST',
		url :"/SAO/AsignacionTecnicoCampoUpdateServlet",
		data: {
			key : p_key,
			valor: object.value,
			campo: p_campo
		},
		success: function(data) {

		},
		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
	});

}

function selectEventoTecnico (p_id_evento_tecnico) {

	global_id_evento_tecnico = p_id_evento_tecnico;

	$.ajax({
		type:'POST',
		url :"/SAO/TecnicosDisponiblesSelectServlet",
		success: function(data) {

			$("#divTecnicosDisponibles").empty();
			$("#divTecnicosDisponibles").append(data);

			$("#divTecnicosDisponibles").hide();
// 			$("#divInterpretesDisponibles").show();
			$("#divTecnicosDisponibles").show();


			//nuevaAsignacionDeInterprete();


		},
		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
	});

	$("#modalTecnicosDisponibles").modal("show");
}

function selectTecnicosAsignados() {

	$.ajax({
		type:'POST',
		url :"/SAO/TecnicosAsignadosSelectServlet",
		data: {
			ID_EVENTO : $("#id_evento").val()
		},
		success: function(data) {

			$("#divTecnicos").empty();
			$("#divTecnicos").append(data);

			$("#divTecnicos").hide();
// 			$("#divInterpretesAsignados").show();
			$("#divTecnicos").show();


			$("#divTecnicosDisponibles").empty();
			window.formato();
		},
		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
	});

}


function asignarTecnico(p_id_tecnico) {


	$.ajax({
		type:'POST',
		url :"/SAO/TecnicoAsignacionValidacionServlet",
		data: {
			id_tecnico : p_id_tecnico,
			id_evento_tecnico : global_id_evento_tecnico
		},
		success: function(data) {

				if(data != "OK") {

					swal({
                      title: "Mensaje:",
											text:data,
                      type: 'warning',
                      showCancelButton: true
											,html:true,
                      confirmButtonColor: '#DD6B55',
                      cancelButtonText: 'Cancelar',
                      closeOnConfirm: true
                    },
                    function(){
                    	doAsignarTecnico(p_id_tecnico)
                    });

				} else {

					doAsignarTecnico(p_id_tecnico);
				}



		},
		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
	});


}


function doAsignarTecnico(p_id_tecnico){
$.ajax({
	type:'POST',
	url :"/SAO/TecnicoAsignacionServlet",
	data: {
		id_tecnico : p_id_tecnico,
		id_evento_tecnico : global_id_evento_tecnico
	},
	success: function(data) {


		selectTecnicosAsignados();

		$("#modalTecnicosDisponibles").modal('hide');
	},
	error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
});

}


var typingTimer;



function updateProductoTEEvento(p_id_evento_producto, component) {

	// alert();

 	$.ajax({
 		type:'POST',
 		url :"/SAO/ProductoTEEventoUpdateServlet",
 		data: {
 			//id_evento : $("#id_evento").val(),
 			//nombre : $("#NOMBRE").val(),
 			//status : $("#STATUS").val(),
 			//id_empresa : $("#ID_EMPRESA").val()
 			id_evento_producto : p_id_evento_producto,
 			//id_evento : $("#id_evento").val(),
			field_id : component.id,
			field_value : component.value
 		},
 		success: function(data) {

;


			clearTimeout(typingTimer);

			typingTimer = setTimeout(
				function(){
					selectSala(globalSelectedSala);
				}, 400
			);



 			//$("#divSedes").empty();
 			//$("#divSedes").append(data);

 			//$("#divSedes").hide();
 			//$("#divSedes").toggle(200);

 		},
 		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
 	});

 }


function espera(campo,event){
	  clearTimeout(typingTimer);

		var tecleada =  (event.keyCode ? event.keyCode : event.which);

		if((tecleada >= 48 && tecleada <= 57) || tecleada == 46 || tecleada == 8) {
			if(tecleada == 46 && campo.value.indexOf(".") != -1) {
				return false;
			} else {
				return true;
			}
		}else{
			return false;
		}
}

function quitarProductoTE(p_id_evento_Producto) {


	 $.ajax({
			type:'POST',
			url :"/SAO/ProductoRemoveServlet",
			data: {
				id_evento_producto : p_id_evento_Producto,
				isTraduccionEscrita : 'SI'
			},
			success: function(data) {

				selectProductosEvento();
				selectProductosAplicables();

				//$('#divFechas').html(data);

				/*$("#divOutput").empty();
				$("#divOutput").append(data);

				$("#divOutput").hide();
				$("#divOutput").show();*/

			},
			error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
		});
}







function nuevaFechaEvento() {


	$.ajax({
 		type:'POST',
 		url :"/SAO/EventoFechaInsertServlet",
 		data: {
 			id_evento : $("#id_evento").val()
 		},
 		success: function(data) {

 			selectFechasEvento();
 			//selectSala(globalSelectedSala);

 			/*$("#divOutput").empty();
 			$("#divOutput").append(data);

 			$("#divOutput").hide();
 			$("#divOutput").show();*/

 		},
 		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
 	});

}

function updateSalaFechaEvento(p_key, object) {

	//alert(object.value);

	$.ajax({
		type:'POST',
		url :"/SAO/EventoFechaUpdateServlet",
		data: {
			key : p_key,
			fecha: object.value
		},
		success: function(data) {

			/*$("#divOutput").empty();
			$("#divOutput").append(data);

			$("#divOutput").hide();
			$("#divOutput").show();*/

		},
		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
	});

}






function deleteSalaFechaEvento(p_id) {

	$.ajax({
		type:'POST',
		url :"/SAO/EventoFechaDelete",
		data: {
			key : p_id
		},
		success: function(data) {

			selectFechasEvento();
			//selectSala(globalSelectedSala);

			/*$("#divOutput").empty();
			$("#divOutput").append(data);

			$("#divOutput").hide();
			$("#divOutput").show();*/

		},
		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
	});

}


function selectFechasEvento() {

	$.ajax({
		type:'POST',
		url :"/SAO/EventoFechasSelectServlet",
		data: {
			id_evento : $("#id_evento").val()
		},
		success: function(data) {

			$("#divFechasEvento").empty();
			$("#divFechasEvento").append(data);

			$("#divFechasEvento").hide();
			$("#divFechasEvento").show();
			window.formato();

		},
		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
	});
 }

function mostrarSeguimiento(){
	$("#modalSeguimiento").modal("show");
}


function insertNewComment() {


	if($("#id_semaforo").val() == "0"){

		swal({type:'warning',text: "Seleccione el sem&aacuteforo",html:true});
		return;
	}


	if($("#comentario").val() == ""){

		swal({type:'warning',text: "Capture su comentario",html:true});
		return;
	}




 	$.ajax({
 		type:'POST',
 		url :"/SAO/AddCRMCommentServlet",
 		data: {
 			id_semaforo : $("#id_semaforo").val(),
 			comentario: $("#comentario").val(),
 			id_cliente: $('#_idCliente').val(),
			idEventoComentario: <%=eventoRecord.get("id_evento")%>
 		},
 		success: function(data) {

 			//alert("ok");

 			swal("SAO:", "Se agrego correctamente el comentario", "success");
 			$('#modalNuevoSeguimiento').modal('hide');
 			$("#id_semaforo").val("0");
 			 $("#comentario").val("");

 			reloadTablaComentarios();
 		},
 		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
 	});

}


function reloadTablaComentarios() {

	$.ajax({
		type:'POST',
		url :"/SAO/CRMComentariosSelectServlet",
		data: {
			id: $("#_idCliente").val(),
			idEventoComentario: <%=eventoRecord.get("id_evento")%>
		},
		success: function(data) {
			$("#divTableComentariosCRM").empty();
			$("#divTableComentariosCRM").append(data);

			$("#divTableComentariosCRM").hide();
			$("#divTableComentariosCRM").show();

		},
		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
	});

}



</script>

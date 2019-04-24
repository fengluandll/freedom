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

var idEventoGlobal = 0;
var idDetalleGlobal = 0;

function PopupCenter(url, title, w, h) {
    // Fixes dual-screen position                         Most browsers      Firefox
    var dualScreenLeft = window.screenLeft != undefined ? window.screenLeft : screen.left;
    var dualScreenTop = window.screenTop != undefined ? window.screenTop : screen.top;

    var width = window.innerWidth ? window.innerWidth : document.documentElement.clientWidth ? document.documentElement.clientWidth : screen.width;
    var height = window.innerHeight ? window.innerHeight : document.documentElement.clientHeight ? document.documentElement.clientHeight : screen.height;

    var left = ((width / 2) - (w / 2)) + dualScreenLeft;
    var top = ((height / 2) - (h / 2)) + dualScreenTop;
    var newWindow = window.open(url, title, 'scrollbars=yes, width=' + w + ', height=' + h + ', top=' + top + ', left=' + left);

    // Puts focus on the newWindow
    if (window.focus) {
        newWindow.focus();
    }
}

function previewFactura(f_id,idEvento) {
	PopupCenter('/SAO/home/factura_html.jsp?ID_EVENTO='+idEvento+'&idFactura='+f_id,'Vista Previa','900','500');
}

function previewFacturaPDF(f_id,idEvento)
{
    PopupCenter('/SAO/home/factura_pdf.jsp?ID_EVENTO='+idEvento+'&idFactura='+f_id,'Vista Previa','900','500');
}

function montosFactura(f_id){

    $.ajax({
       url :"/SAO/MontoFacturaSelectServlet" ,
       type: "post",
       data : { ID_FACTURA: f_id},
       success:function (data){
            $("#divFacturaPreview").html(data);
       },


    });
    //$('#modalFacturaPreview').modal("show");

}

function deleteFactura(p_id,idEvento) {

	$.ajax({
		type:'POST',
		url :"/SAO/EventoFacturaDelete",
		data: {
			key : p_id
		},
		success: function(data) {

						   mostrarModalFacturas(idEvento)	;

		},
		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
	});

}



function deleteDetalleFactura(p_id,idFactura) {

	$.ajax({
		type:'POST',
		url :"/SAO/FacturaDetalleDelete",
		data: {
			key : p_id
		},
		success: function(data) {

						   mostrarModalDetalleFacturas(idFactura,idEventoGlobal)	;

		},
		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
	});

}


function deleteMonto(p_id,idFactura) {

	$.ajax({
		type:'POST',
		url :"/SAO/MontoFacturaDelete",
		data: {
			key : p_id
		},
		success: function(data) {

            montosFactura(idFactura);

		},
		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
	});

}




function nuevoMonto(idFactura){

     $.ajax({
			type:'POST',
			url :"/SAO/AddMontoFacturaServlet",
			data: {
				ID_FACTURA : idFactura
			},
			success: function(data) {


			   montosFactura(idFactura);
				 actualizarVistaPreviaEF(idFactura);

			},
			error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
		});
}

function nuevaFactura(idEvento){

     $.ajax({
			type:'POST',
			url :"/SAO/AddEventoFacturaServlet",
			data: {
				ID_EVENTO : idEvento
			},
			success: function(data) {


			   mostrarModalFacturas(idEvento)


			},
			error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
		});
}


function nuevoDetalleFactura(idFactura){

     $.ajax({
			type:'POST',
			url :"/SAO/AddEventoDetalleFacturaServlet",
			data: {
				ID_FACTURA : idFactura
			},
			success: function() {


			   mostrarModalDetalleFacturas(idFactura,$("#auxIdEvento").val()	);
				 	actualizarVistaPreviaEF(idFactura);

			},
			error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
		});
}

function showDatosEventoConfirmado(param_id_evento) {

	$.ajax({
		type:'POST',
		url :"/SAO/AgendaEventoFormServlet",
		data: {
			ID_EVENTO : param_id_evento
		},
		success: function(data) {
			$("#nFormAgenda2").html(data);

			$("#nFormAgenda2").show();
		},
		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
	});

}

function mostrarModalDetalleFacturas(idFactura,idEvento){
	idDetalleGlobal = idFactura;
	idEventoGlobal = idEvento;
	$("#auxIdEvento").val(idEvento);
	$("#modalMemoFacturaDetalle").modal('show');
	montosFactura(idFactura);
	showDatosEventoConfirmado(idEvento);
	actualizarVistaPreviaEF(idFactura);
	/*
	$.ajax({
		type:'POST',
		url :"/SAO/FacturaDetalleEventoSelectServlet",
		data: {
			ID_FACTURA : idFactura
		},
		success: function(data) {

			$("#divCapturaFacturaDetalle").empty();
			$("#divCapturaFacturaDetalle").append(data);

			$("#divCapturaFacturaDetalle").hide();
//	 			$("#divInterpretesAsignados").show();
			$("#divCapturaFacturaDetalle").show();
				$("#datatable-but").DataTable({
									dom: "Bfrtip",

									responsive: true
								});
				actualizarVistaPreviaEF(idFactura);


		},
		error:function(exception){console.log('Exeption:'+exception); /*logSystemswal(  'Error',  'Proceso no completado',  'error');}
	});
*/

	}





function updateFactura(p_key, object, p_campo) {

	//alert(object.value);

	$.ajax({
		type:'POST',
		url :"/SAO/FacturaUpdateServlet",
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


function updateDetalleFactura(p_key, object, p_campo,idFactura) {
	idDetalleGlobal = idFactura;
	//alert(object.value);

	$.ajax({
		type:'POST',
		url :"/SAO/FacturaDetalleUpdateServlet",
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
			actualizarVistaPreviaEF(idFactura);

		},
		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
	});

}

function updateMonto(p_key, object, p_campo) {

	//alert(object.value);

	$.ajax({
		type:'POST',
		url :"/SAO/MontoUpdateServlet",
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
			actualizarVistaPreviaEF(idDetalleGlobal);


		},
		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
	});

}



function mostrarModalMemoServicio(idEvento){

	idEventoGlobal = idEvento;
	debugger;
	  $("#modalMemoServicio").modal('show');

	  $("#divCapturaMemoEvento").empty();
	  $("#divVistaPrevia").empty();

	  $('#editorCliente').empty();
	  $('#editorOmni').empty();

	  obtenerMemoServicio(idEvento);

	  obtenerCorreoContactoCliente(idEvento, "1");
		<% // Ver si son lo correos son los de omnilingua o de los interpretes
		 %>
	  	obtenerCorreoContactoCliente(idEvento, "2");
		obtenerCorreoContactoCliente(idEvento, "3");

	  obtenerNombreEvento(idEvento);

	  actualizarVistaPrevia(idEvento);


  };
  function mostrarModalFacturas(idEvento){

	  $("#modalFacturas").modal('show');

	  $.ajax({
			type:'POST',
			url :"/SAO/FacturaEventoSelectServlet",
			data: {
				ID_EVENTO : idEvento
			},
			success: function(data) {

				$("#divFacturas").empty();
				$("#divFacturas").append(data);

				$("#divFacturas").hide();
//	 			$("#divInterpretesAsignados").show();
				$("#divFacturas").show();
				  $("#datatable-but").DataTable({
		                dom: "Bfrtip",

		                responsive: true
		              });

			},
			error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
		});


	  };

  function copiarMemoServicioEditores()
  {
	 var memoServicio = $("#divPrincipalMemo").html();

     $('#editorCliente').html(memoServicio);
     $('#editorOmni').html(memoServicio);
  }

  function obtenerMemoServicio(idEvento){
	  //MemoServicioSelectServlet

	  $.ajax({
			type:'POST',
			url :"/SAO/MemoServicioSelectServlet",
			data: {
				ID_EVENTO : idEvento
			},
			success: function(data) {

				$("#divCapturaMemoEvento").empty();
				$("#divCapturaMemoEvento").append(data);

			},
			error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
		});
  }

  function actualizarVistaPrevia(idEvento) {

		$.ajax({
			type:'POST',
			url :"reporte_memo_servicio_html.jsp",
			data: {
				ID_EVENTO : idEvento
			},
			success: function(data) {

				$("#divVistaPrevia").empty();
				$("#divVistaPrevia").append(data);

				//$("#divVistaPrevia").hide();
				//$("#divVistaPrevia").show();
				copiarMemoServicioEditores();
			},
			error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
		});

	}



	  function actualizarVistaPreviaEF(idEFactura) {

			$.ajax({
				type:'POST',
				url :"factura_html.jsp",
				data: {
					ID_EVENTO: $("#auxIdEvento").val(),
					idFactura:idEFactura
				},

					success: function(data) {

						$("#divVistaPreviaEF").empty();
					$("#divVistaPreviaEF").append(data);

					//$("#divVistaPrevia").hide();
					//$("#divVistaPrevia").show();
					copiarFacturaEditores();
				},
				error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
			});

		}


		function copiarFacturaEditores()
	  {
		 var facturaHTML = $("#divVistaPreviaEF").html();

	     $('#editorClienteEF').html(facturaHTML);
	     $('#editorOmniEF').html(facturaHTML);
	  }

  	function obtenerCorreoContactoCliente(idEvento, tipo_Correo){
  		$.ajax({
			type:'POST',
			url :"/SAO/MemoServicioSelectedCorreoServlet",
			data: {
				ID_EVENTO : idEvento,
				tipoCorreo : tipo_Correo
			},
			success: function(data) {

				if(tipo_Correo == '1'){
					$("#txtCorreoCLiente").empty();
  	 				$("#txtCorreoCLiente").val(data);
				}else if(tipo_Correo == '3'){
					$("#txtCorreoInterprete").empty();
  	 				$("#txtCorreoInterprete").val(data);
				} else	{
						$("#txtCorreoOmni").empty();
  	 				$("#txtCorreoOmni").val(data);

					}
				//$("#divVistaPrevia").hide();
				//$("#divVistaPrevia").show();
				copiarMemoServicioEditores();
			},
			error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
		});

	}
  	function actualizarCampoMemoServicio(component, id){
  		var v;
  		if(component.type != 'checkbox'){
  			 v =  component.value;
  		}else{
  			v = $(component).prop("checked")?1:0;
  		}
  		
  		$.ajax({
  	 		type:'POST',
  	 		url :"/SAO/MemoServicioUpdateCampoServlet",
  	 		data: {
  	 			key : id,
  	 			campo : component.id,
  				valor : v
  	 		},
  	 		success: function(data) {

  	 			//$("#divSedes").hide();
  	 			//$("#divSedes").toggle(200);
  	 			actualizarVistaPrevia(idEventoGlobal);

  	 		},
  	 		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
  	 	});
  	}

  	function obtenerNombreEvento(idEvento){
  		//
  		$.ajax({
  	 		type:'POST',
  	 		url :"/SAO/MemoServicioSelectedNombreEventoServlet",
  	 		data: {
  	 			ID_EVENTO: idEvento
  	 		},
  	 		success: function(data) {
  	 			var asunto = "Memo de servicio: ";
  	 			$("#txtAsuntoCLiente").empty();
	 			$("#txtAsuntoCLiente").val(asunto + data);

  	 			$("#txtAsuntoOmni").empty();
	 			$("#txtAsuntoOmni").val(asunto + data);

  	 		},
  	 		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
  	 	});
  	}


  	var funcEnviarCorreoMemo = function (){
  		$.ajax({
  	 		type:'POST',
  	 		url :"/SAO/NotificacionMemoServicioServlet",
  	 		data: {
  	 			id_evento : idEventoGlobal,
  	 			emailCliente: $("#txtCorreoCLiente").val(),
  	 			emailOmnilingua: $("#txtCorreoOmni").val(),
					emailIntepretes: $("#txtCorreoInterprete").val(),
  	 			asuntoCliente : $("#txtAsuntoCLiente").val(),
  	 			asuntoOmnilingua : $("#txtAsuntoOmni").val(),
  	 			mensajeCliente : $("#editorCliente").html(),
  	 			mensajeOmnilingua : $("#editorOmni").html()
  	 		},
  	 		success: function(data) {

  		 		if(data == "OK")
  		 		{
  		 			swal('Memo de Servicio enviado');
						$("#modalMemoServicio").modal('hide');
  		 		}
  	 		},
  	 		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
  	 	});
  	};


		var funcEnviarCorreoFactura = function (){
			$.ajax({
				type:'POST',
				url :"/SAO/NotificacionFacturaServlet",
				data: {
					id_factura : idDetalleGlobal,
					id_evento : idEventoGlobal,
					emailOmnilingua: $("#txtCorreoOmniEF").val(),
					asuntoOmnilingua : $("#txtAsuntoOmniEF").val(),
					mensajeOmnilingua : $("#editorOmniEF").html()
				},
				success: function(data) {

					if(data == "OK")
					{
						swal('Detalle de factura enviado');

					}
				},
				error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
			});
		};




</script>

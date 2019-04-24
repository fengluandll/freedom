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


function updateEventoSede(idSala, component) {

	// alert();

 	$.ajax({
 		type:'POST',
 		url :"/SAO/CotizacionUpdateEventoSala",
 		data: {
 			//id_evento : $("#id_evento").val(),
 			//nombre : $("#NOMBRE").val(),
 			//status : $("#STATUS").val(),
 			//id_empresa : $("#ID_EMPRESA").val()
 			id_sala : idSala,
 			id_evento : $("#id_evento").val(),
			field_id : component.id,
			field_value : component.value
 		},
 		success: function(data) {

 			//$("#divSedes").empty();
 			//$("#divSedes").append(data);

 			//$("#divSedes").hide();
 			//$("#divSedes").toggle(200);


 			actualizarVistaPrevia();

 		},
 		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
 	});

 }

var funcMostarModalSubgrupoCotizador = function (idEvento){

	$.ajax({
 		type:'POST',
 		url :"/SAO/SelectCotizacionSubgrupoServlet",
 		data: {
 			id_evento : idEvento
 		},
 		success: function(data) {
	 			$("#divSubgrupo").empty();
	 			$("#divSubgrupo").append(data);
 		},
 		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
 	});

	$("#modalCotizadorGrupo").modal("show");
};


var funcNuevoSubgrupo = function (idEvento){

	if($("#txtNombreSubgerupo").val().length <= 0)
	{
		return;
	}

	$.ajax({
 		type:'POST',
 		url :"/SAO/SubtotalesInsertNuevoServlet",
 		data: {
 			id_evento : idEvento,
 			nombre_subgrupo : $("#txtNombreSubgerupo").val(),
 			descripcion_subgrupo: $("#txtTextSubgerupo").val()
 		},
 		success: function(data) {
	 			if(data == "OK"){
		 			$("#modalCotizadorGrupo").modal("hide");

		 			//window.location.href = "adm_evento_sedes.jsp?ID_EVENTO=" + idEvento;

		 			selectSalas();
		 			selectFechasEvento();

	 			}
 		},
 		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
 	});
};




var funcMostarModalEnvioCotizacion = function (idEvento){

	$('#ID_EVENTO').val(idEvento);

	var nombreEvento = $("#nombre").val();

	$("#txtAsunto").val( $('<textarea />').html("Cotizaci&oacute;n de Evento " + nombreEvento).text());

	$("#txtMensaje").html("Estimado(a) " + $("#id_contacto  option:selected").text() + ": \n\nAdjunto la cotizaci&oacute;n solicitada para el evento " + nombreEvento + ". \n\nQuedamos a tus &Oacute;rdenes")

	//$("#txtMensaje").html("	Estimado(a) " + $("#id_contacto  option:selected").text() + ": Adjunto la cotizaci�n solicitada para el evento " + nombreEvento + ". Quedamos a tus �rdenes")

	//document.getElementById("txtMensaje").value = "	Estimado(a) " + $("#id_contacto  option:selected").text() + ": Adjunto la cotizaci�n solicitada para el evento " + nombreEvento + ". Quedamos a tus �rdenes";

	$("#modalEnviarCorreo").modal("show");
};

var funcMostarModalEnvioCierre = function (idEvento){
	$('#ID_EVENTOC').val(idEvento);

	var nombreEvento = $("#nombre").val();

	$("#txtAsunto_c").val( $('<textarea />').html("Notici&oacute;n de Cierre de Evento " + nombreEvento).text());

	$("#txtMensaje_c").html("Estimado(a) " + $("#id_contacto  option:selected").text() + ": \n\nNotificamos el cierre del evento " + nombreEvento + ". \n\nQuedamos a tus &Oacute;rdenes")


	$("#modalEnviarCorreoCierre").modal("show");


};


var funcMostrarPDF = function (){
	window.open('reporte_cotizacion_pdf.jsp?ID_EVENTO=' + $("#ID_EVENTO").val());
};

var funcEnviarCotizacionCliente = function (){
	let valor = $("#txtDireccionCorreo").val();
	var evalua = valor.includes(".") && valor.includes("@");
	if(valor.trim() != '' &&  evalua &&  valor.trim() != '' && valor.trim() != ''){
		$.ajax({
	 		type:'POST',
	 		url :"/SAO/NotificacionCotizacionClienteServlet",
	 		data: {
	 			id_evento : $('#ID_EVENTO').val(),
	 			email: $("#txtDireccionCorreo").val(),
	 			
	 			cc : $("#txtCC").val(),
	 			cco : $("#txtCCO").val(),
	 		
	 			asunto: $("#txtAsunto").val(),
	 			mensaje : $("#txtMensaje").val(),
	 			nombre_usuario : $("#nombreUsuario").val(),
	 			apellido_usuario : $("#apellidoUsuario").val()
	 		},
	 		success: function(data) {

		 		if(data == "OK")
		 		{
		 			swal({'text':'Cotizaci&oacute;n enviada','html':true});

		 			$("#modalEnviarCorreo").modal("hide");

		 			window.location.href = 'adm_eventos_list.jsp?idEventoChangeStatus= ' + $("#ID_EVENTO").val() + '&idStatus=4';
		 		}
	 		},
	 		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
	 	});
	}else{
		swal({title: "Error", text: "Por favor, complete la informaci&oacute;n solicitada previo a enviar la cotizaci&oacute;n",html:true,type:"error"});
	}
};

var funcEnviarCierreCliente = function(){
	$.ajax({
 		type:'POST',
 		url :"/SAO/NotificacionCierreClienteServlet",
 		data: {
 			id_evento : $('#ID_EVENTOC').val(),
 			email: $("#txtDireccionCorreo_c").val(),
 			asunto: $("#txtAsunto_c").val(),
 			mensaje : $("#txtMensaje_c").val(),
 			nombre_usuario : $("#nombreUsuario_c").val(),
 			apellido_usuario : $("#apellidoUsuario_c").val()
 		},
 		success: function(data) {

	 		if(data == "OK")
	 		{
	 			swal({'text':'Notificaci&oacute;n enviada','html':true});

	 			$("#modalEnviarCorreoCierre").modal("hide");
	 			window.location.href = 'adm_eventos_list.jsp?idEventoChangeStatus= ' + $("#ID_EVENTOC").val() + '&idStatus=8';
	 		}

 		}

 	});
};








var funcMostarModalCancelacion =  function(idEvento){
	$('#ID_EVENTO').val(idEvento);

	$("#txtPorcentajeInterpretes").val("0");
	$("#txtPorcentajeEquipo").val("0");
	$("#txtPagoInterpretes").val("0");

	$("#txtPorcentajeInterpretes").prop('disabled', true);
	$("#txtPorcentajeEquipo").prop('disabled', true);
	$("#txtPagoInterpretes").prop('disabled', true);

	$("#modalCancelacion").modal("show");
};

var funcConfirmarCancelacion = function (){

	var idEvento = $('#ID_EVENTO').val();
	var motivoCancelacion = $("#txtMotivoCancelacion").val();
	var porcentajeInterpretes = $("#txtPorcentajeInterpretes").val();
	var porcentajeEquipo = $("#txtPorcentajeEquipo").val();
	var pagoInterpretes =  $("#txtPagoInterpretes").val()

	if(motivoCancelacion.length <= 0){
		swal("Debe agregar un motivo de cancelacion.");
		return;
	}

	if(porcentajeInterpretes > 100 || porcentajeEquipo > 100 || pagoInterpretes > 100){
		swal("El porcentaje debe ser menor o igual a 100.");
		return;
	}


	var newStatus = "";

	if($('#chkAplicaPenalizacion').is(':checked')){
		newStatus = "7";
	}else{
		newStatus = "6";
	}




	$.ajax({
 		type:'POST',
 		url :"/SAO/EventoCancelacionServlet",
 		data: {
 			id_evento : idEvento,
 			motivo: $("#txtMotivoCancelacion").val(),
 			porcentaje_interpretes: $("#txtPorcentajeInterpretes").val(),
 			porcentaje_equipo : $("#txtPorcentajeEquipo").val(),
 			porcentaje_pago_interpretes : $("#txtPagoInterpretes").val()
 		},
 		success: function(data) {

	 		if(data == "OK")
	 		{
	 			swal({text:'Cotizaci&oacute;n Cancelada', html:true});

	 			$("#modalCancelacion").modal("hide");

	 			window.location.href = 'adm_eventos_list.jsp?idEventoChangeStatus= ' + $("#ID_EVENTO").val() + '&idStatus=' + newStatus;
	 		}
 		},
 		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
 	});
};

var funcAplicaPorcentajes =  function (){

	if($('#chkAplicaPenalizacion').is(':checked')){
		$("#txtPorcentajeInterpretes").prop('disabled', false);
		$("#txtPorcentajeEquipo").prop('disabled', false);
		$("#txtPagoInterpretes").prop('disabled', false);
	}else{
		$("#txtPorcentajeInterpretes").prop('disabled', true);
		$("#txtPorcentajeEquipo").prop('disabled', true);
		$("#txtPagoInterpretes").prop('disabled', true);
	}
};





</script>

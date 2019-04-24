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


var funcObternerEstatusEvento = function (){

	$.ajax({
 		type:'POST',
 		url :"/SAO/EventoSelectEstatusServlet",
 		//data: {
 		//},
 		success: function(data) {

 			$("#divComboEstatus").empty();
 			$("#divComboEstatus").append(data);

 		},
 		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
 	});
};


var funcObternerComboEventoNombre = function (){

	$.ajax({
 		type:'POST',
 		url :"/SAO/EventoSelectComboNombreServlet",
 		//data: {
 		//},
 		success: function(data) {

 			$("#divComboNombreEvento").empty();
 			$("#divComboNombreEvento").append(data);

 		},
 		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
 	});
};

//
var funcObternerComboCliente = function (){

	$.ajax({
 		type:'POST',
 		url :"/SAO/EventoSelectComboClienteServlet",
 		//data: {
 		//},
 		success: function(data) {

 			$("#divComboCliente").empty();
 			$("#divComboCliente").append(data);

 		},
 		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
 	});
};

var funcObtenerAgenda = function (){

	var idCliente =  $("#id_cliente option:selected").val();
	var idEvento =  $("#id_evento option:selected").val();
	var idEstatusEvento =  $("#id_evento_status option:selected").val();
	var fechaSeleccionada = $("#startDate").val();

	if(fechaSeleccionada.length > 0){
		fechaSeleccionada = fechaSeleccionada.split('-')[1];
	}

	$.ajax({
 		type:'POST',
 		url :"/SAO/AgendaSelectServlet",
 		data: {
 			id_cliente : idCliente,
 			id_evento : idEvento,
 			id_estatus : idEstatusEvento,
 			fecha : fechaSeleccionada
 		},
 		success: function(data) {
 	debugger;
			var argEventos = eval(data);

 			$('#calendar').fullCalendar( 'removeEvents');
 		    $('#calendar').fullCalendar('addEventSource', argEventos);

 		   if( $("#startDate").val().length > 0){
 		   	$('#calendar').fullCalendar('gotoDate', new Date($("#startDate").val().split('-')[1], $("#startDate").val().split('-')[0], 1));
 		   }

 		},
 		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
 	});
};

var funcObtenerAgendaInterpretes = function (){


	var idCliente =  $("#id_cliente option:selected").val();
	var idEvento =  $("#id_evento option:selected").val();
	var idEstatusEvento =  $("#id_evento_status option:selected").val();
	var fechaSeleccionada = $("#startDate").val();

	if(fechaSeleccionada.length > 0){
		fechaSeleccionada = fechaSeleccionada.split('-')[1];
	}

	$.ajax({
 		type:'POST',
 		url :"/SAO/AgendaSelectInterpreteServlet",
 		data: {
 			id_cliente : idCliente,
 			id_evento : idEvento,
 			id_estatus : idEstatusEvento,
 			fecha : fechaSeleccionada
 		},
 		success: function(data) {

 			//$("#calendar").empty();
 			debugger;


 			var argEventos = eval(data);

 			$('#calendar').fullCalendar( 'removeEvents');
 		    $('#calendar').fullCalendar('addEventSource', argEventos);

 		   if( $("#startDate").val().length > 0){
 		   	$('#calendar').fullCalendar('gotoDate', new Date($("#startDate").val().split('-')[1], $("#startDate").val().split('-')[0], 1));
 		   }
 			//funcInicializaAgenda(argEventos, null);
 		},
 		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
 	});
};

var funcObtenerAgendaTecnicos = function (){


	var idCliente =  $("#id_cliente option:selected").val();
	var idEvento =  $("#id_evento option:selected").val();
	var idEstatusEvento =  $("#id_evento_status option:selected").val();
	var fechaSeleccionada = $("#startDate").val();

	if(fechaSeleccionada.length > 0){
		fechaSeleccionada = fechaSeleccionada.split('-')[1];
	}

	$.ajax({
 		type:'POST',
 		url :"/SAO/AgendaSelectTecnicoServlet",
 		data: {
 			id_cliente : idCliente,
 			id_evento : idEvento,
 			id_estatus : idEstatusEvento,
 			fecha : fechaSeleccionada
 		},
 		success: function(data) {

 			//$("#calendar").empty();
 			debugger;


 			var argEventos = eval(data);

 			$('#calendar').fullCalendar( 'removeEvents');
 		    $('#calendar').fullCalendar('addEventSource', argEventos);

 		   if( $("#startDate").val().length > 0){
 		   	$('#calendar').fullCalendar('gotoDate', new Date($("#startDate").val().split('-')[1], $("#startDate").val().split('-')[0], 1));
 		   }
 			//funcInicializaAgenda(argEventos, null);
 		},
 		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
 	});
};

var funcInicializaAgenda = function(argEventos, anio){
	var calendar = $('#calendar').fullCalendar({
        header: {
          left: 'prev,next today',
          center: 'title',
          right: 'month,agendaWeek,agendaDay'
        },
        //editable: false, // set this false

        //lang: 'es',
        monthNames: ['Enero','Febrero','Marzo','Abril','Mayo','Junio','Julio','Agosto','Septiembre','Octubre','Noviembre','Diciembre'],
        monthNamesShort: ['Ene','Feb','Mar','Abr','May','Jun','Jul','Ago','Sep','Oct','Nov','Dic'],
        dayNames: ['Domingo','Lunes','Martes','Miercoles','Jueves','Viernes','Sabado'],
        dayNamesShort: ['Dom','Lun','Mar','Mi','Jue','Vie','Sab'],
        buttonText: {
      	  today: "Hoy",
     		  month:    'mes',
  		  week:     'semana',
  		  day:      'dia',
   		  list:     'lista'
        },
        height: 650,
        fixedWeekCount: false,
        selectable: true,
        selectHelper: true,
        eventClick: function(calEvent, jsEvent, view) {

          $('#fc_edit').click();

     		$('#ID_EVENTO').val(calEvent.id_evento);





				if(calEvent.id_estatus != 5){
					$('#title2').val(calEvent.evento);
					$('#fecha').val(calEvent.fecha);
					$('#cliente').val(calEvent.cliente);
				//$('#sala').val(calEvent.sala);
				//$('#interprete').val(calEvent.interprete);
					$("#btnMemoEvento").hide();

					$("#nFormAgenda1").show();
					$("#nFormAgenda2").hide();
				}else{					
					if(calEvent.id_tipo_eveto == 2){
						$("#btnMemoEvento").hide();
					}else{
						$("#btnMemoEvento").show();
					}

					$("#nFormAgenda1").hide();
					showDatosEventoConfirmado(calEvent.id_evento);
				}


     		showInterpretes(calEvent.id_evento);
     		showFacturas(calEvent.id_evento);

          categoryClass = $("#event_type").val();

          $(".antosubmit2").on("click", function() {
            calEvent.title = $("#title2").val();

            calendar.fullCalendar('updateEvent', calEvent);
            $('.antoclose2').click();
          });

          calendar.fullCalendar('unselect');
        },

        editable: false,

        events: argEventos
      });

};



function showInterpretes(param_id_evento) {

	$.ajax({
		type:'POST',
		url :"/SAO/InterpretesAsignadosServlet",
		data: {
			ID_EVENTO : param_id_evento
		},
		success: function(data) {

			$("#divInterpretesList").empty();
			$("#divInterpretesList").append(data);

			$("#divInterpretesList").hide();
// 			$("#divInterpretesAsignados").show();
			$("#divInterpretesList").show();


			//$("#divInterpretesDisponibles").empty();

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



function showFacturas(param_id_evento) {

	$.ajax({
		type:'POST',
		url :"/SAO/FacturasSelectServlet",
		data: {
			ID_EVENTO : param_id_evento
		},
		success: function(data) {
			$("#divFacturasList").empty();
			$("#divFacturasList").append(data);

			$("#divFacturasList").hide();
			$("#divFacturasList").show();
		},
		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
	});

}




</script>

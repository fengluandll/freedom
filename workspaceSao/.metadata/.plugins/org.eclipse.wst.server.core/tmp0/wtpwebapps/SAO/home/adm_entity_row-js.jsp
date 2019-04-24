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
 */

// var globalSelectedSala = '';
// var globalSelectedFecha = '';


function updateField(component) {





	if(component.tagName == 'DIV'){
	    value =  component.innerHTML;
	}else{
		value =  component.value;
	}

	 $.ajax({
			type:'POST',
			url :"/SAO/SimpleFormUpdateFieldServlet",
			data: {
				//ID_EVENTO : $("#id_evento").val(),
				field_id : component.id,
				field_value : value
			},
			success: function(data) {

				//selectPoliticasEvento();
				//selectPoliticasAplicables();

				//$('#divFechas').html(data);

				/*$("#divOutput").empty();
				$("#divOutput").append(data);

				$("#divOutput").hide();
				$("#divOutput").toggle(200);*/

			},
			error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
		});

 }




</script>

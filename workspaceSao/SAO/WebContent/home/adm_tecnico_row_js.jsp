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



 function updateTecnico(component) {

 	$.ajax({
 		type:'POST',
 		url :"/SAO/TecnicoUpdateServlet",
 		data: {
 			id_tecnico : $("#id_tecnico").val(),
 			field_id : component.id,
			field_value : component.value
 		},
 		success: function(data) {

 			//$("#divSedes").empty();
 			//$("#divSedes").append(data);

 			//$("#divSedes").hide();
 			//$("#divSedes").toggle(200);

 		},
 		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
 	});

 }

</script>

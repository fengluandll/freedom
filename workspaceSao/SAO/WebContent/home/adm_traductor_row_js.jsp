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

function updateTraductor(component){
	$.ajax({
 		type:'POST',
 		url :"/SAO/TraductorUpdateServlet",
 		data: {
 			id_traductor : $("#id_traductor").val(),
 			field_id : component.id,
			field_value : component.value
 		},
 		success: function(data) {

 		},
 		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
 	});
}

</script>

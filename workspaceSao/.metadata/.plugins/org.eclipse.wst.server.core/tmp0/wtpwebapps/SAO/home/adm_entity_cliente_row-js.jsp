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




 function eliminarRazon(id){
	 swal({   
   		    title: "¿Eliminar?",
   		    text: "La información se eliminará",
   		    type: "warning",
   		    showCancelButton: true,
   		    confirmButtonColor: '#DD6B55',
   		    confirmButtonText: 'Sí, continuar',
   		    cancelButtonText: "No",
   		    closeOnConfirm: true,
   		    closeOnCancel: true
   		 },
   		 function(isConfirm){

   		   if (isConfirm){
   				window.location= 'adm_entity_razon_social_delete.jsp?id='+id;
   		    }
   		 });  
	 
} 
 

 function eliminarContacto(id){
	 swal({   
   		    title: "¿Eliminar?",
   		    text: "La información se eliminará",
   		    type: "warning",
   		    showCancelButton: true,
   		    confirmButtonColor: '#DD6B55',
   		    confirmButtonText: 'Sí, continuar',
   		    cancelButtonText: "No",
   		    closeOnConfirm: true,
   		    closeOnCancel: true
   		 },
   		 function(isConfirm){

   		   if (isConfirm){
   				window.location= 'adm_entity_contacto_delete.jsp?id='+id;
   		    }
   		 });  
	 
} 

function insertNewComment() {

	if($("#id_semaforo").val() == "0"){

		return;
		swal({text:"Seleccione el sem&aacute;foro", html: true, type:"error",title:"Error"});
	}


	if($("#comentario").val() == ""){
		swal({text:"Capture su comentario", html: true, type:"error",title:"Error"});
		return;
	}




 	$.ajax({
 		type:'POST',
 		url :"/SAO/AddCRMCommentServlet",
 		data: {
 			id_semaforo : $("#id_semaforo").val(),
 			comentario: $("#comentario").val(),
			idEventoComentario: $("#idEventoComentario").val()
 		},
 		success: function(data) {

 			//alert("ok");

 			swal("SAO:", "Se agrego correctamente el comentario", "success");

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
			dummy_param : 'Hello World!'
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

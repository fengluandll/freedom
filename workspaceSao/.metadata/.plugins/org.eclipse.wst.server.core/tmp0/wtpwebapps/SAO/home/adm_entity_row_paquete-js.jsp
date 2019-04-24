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







 function updatePaqueteProductoCampo(p_key, object, p_campo) {

 	//alert(object.value);

 	$.ajax({
 		type:'POST',
 		url :"/SAO/ProductoPaqueteUpdateCampoServlet",
 		data: {
 			key : p_key,
 			valor: object.value,
 			campo: p_campo
 		},
 		success: function(data) {


 		},
 		error:function(exception){swal('Exeption:'+exception);}
 	});

 }



 function quitarProducto(p_id_paquete_producto) {

	 $.ajax({
			type:'POST',
			url :"/SAO/ProductoPaqueteRemoveServlet",
			data: {
				id_paquete_producto : p_id_paquete_producto
			},
			success: function(data) {

				reloadPaqueteProductos();


			},
			error:function(exception){console.log('Exeption:'+exception);/*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
		});

 }







function agregarProducto() {

	 $('#fc_edit').click();
}




function doAgregarProducto(p_id_producto) {


 	$.ajax({
 		type:'POST',
 		url :"/SAO/AddProductoPaqueteServlet",
 		data: {
 			id_paquete : $("#id").val(),
 			id_producto: p_id_producto
 		},
 		success: function(data) {


 			$('#fc_edit').click();

 			reloadPaqueteProductos();

 		},
 		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
 	});
}





function reloadPaqueteProductos() {

	$.ajax({
		type:'POST',
		url :"/SAO/ProductosPaqueteSelectServlet",
		data: {
			id : $("#id").val()
		},
		success: function(data) {

			$("#divPaqueteProductos").empty();
			$("#divPaqueteProductos").append(data);

			$("#divPaqueteProductos").hide();
			$("#divPaqueteProductos").show();

		},
		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
	});

}










</script>

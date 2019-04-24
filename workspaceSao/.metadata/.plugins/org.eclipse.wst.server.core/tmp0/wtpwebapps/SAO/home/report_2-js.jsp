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



function updateTable() {


	//alert();

 	$.ajax({
 		type:'POST',
 		url :"/SAO/Report2TableServlet",
 		data: {
 			Cliente : $("#Cliente").val(),
 			Fecha_Ini : $("#Fecha_Ini").val(),
 			Fecha_Fin : $("#Fecha_Fin").val()
 		},
 		success: function(data) {

 			$("#divReportTable").empty();
 			$("#divReportTable").append(data);

 			$("#divReportTable").hide();
 			$("#divReportTable").show();
 			//$("#divReportTable").toggle(200);
 			


 		},
 		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
 	});

 }



</script>

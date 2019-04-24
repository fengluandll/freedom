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



 function updateInterprete(component) {

 	$.ajax({
 		type:'POST',
 		url :"/SAO/InterpreteUpdateServlet",
 		data: {
 			id_interprete : $("#id_interprete").val(),
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




 function agregarEspecialidad(p_id_especialidad) {


	 $.ajax({
			type:'POST',
			url :"/SAO/EspecialidadAddServlet",
			data: {
				id_interprete : $("#id_interprete").val(),
				id_especialidad : p_id_especialidad
			},
			success: function(data) {

				selectEspecialidadesInterprete();
				selectEspecialidadesAplicables();

				//$('#divFechas').html(data);

				/*$("#divOutput").empty();
				$("#divOutput").append(data);

				$("#divOutput").hide();
				$("#divOutput").toggle(200);*/

			},
			error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
		});

 }


 /**
  * DOCUMENTACION
  *
  * @movemini AJAX__delete_cat_interprete_especialidad
  */
 function quitarEspecialidad(p_id_interprete_especialidad) {


	 $.ajax({
			type:'POST',
			url :"/SAO/EspecialidadRemoveServlet",
			data: {
				id_interprete_especialidad : p_id_interprete_especialidad
			},
			success: function(data) {

				selectEspecialidadesInterprete();
				selectEspecialidadesAplicables();

				//$('#divFechas').html(data);

				/*$("#divOutput").empty();
				$("#divOutput").append(data);

				$("#divOutput").hide();
				$("#divOutput").toggle(200);*/

			},
			error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
		});
 }


 function selectEspecialidadesInterprete() {

	 	$.ajax({
	 		type:'POST',
	 		url :"/SAO/EspecialidadesInterpreteSelectServlet",
	 		data: {
	 			id_interprete : $("#id_interprete").val()
	 		},
	 		success: function(data) {

	 			$("#divEspecialidadesInterprete").empty();
	 			$("#divEspecialidadesInterprete").append(data);

	 			$("#divEspecialidadesInterprete").hide();
	 			$("#divEspecialidadesInterprete").toggle(200);

	 		},
	 		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
	 	});

	 }


	 function selectEspecialidadesAplicables() {

		 	$.ajax({
		 		type:'POST',
		 		url :"/SAO/EspecialidadesAplicablesSelectServlet",
		 		data: {
		 			id_interprete : $("#id_interprete").val()
		 		},
		 		success: function(data) {

		 			$("#divEspecialidadesAplicables").empty();
		 			$("#divEspecialidadesAplicables").append(data);

		 			$("#divEspecialidadesAplicables").hide();
		 			$("#divEspecialidadesAplicables").toggle(200);

		 		},
		 		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
		 	});

	}













	 function agregarIdioma(p_id_Idioma) {


		 $.ajax({
				type:'POST',
				url :"/SAO/IdiomaAddServlet",
				data: {
					id_interprete : $("#id_interprete").val(),
					id_idioma : p_id_Idioma
				},
				success: function(data) {

					selectIdiomasInterprete();
					selectIdiomasAplicables();

					//$('#divFechas').html(data);

					/*$("#divOutput").empty();
					$("#divOutput").append(data);

					$("#divOutput").hide();
					$("#divOutput").toggle(200);*/

				},
				error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
			});

	 }


	 /**
	  * DOCUMENTACION
	  *
	  * @movemini AJAX__delete_cat_interprete_Idioma
	  */
	 function quitarIdioma(p_id_interprete_Idioma) {


		 $.ajax({
				type:'POST',
				url :"/SAO/IdiomaRemoveServlet",
				data: {
					id_interprete_idioma : p_id_interprete_Idioma
				},
				success: function(data) {

					selectIdiomasInterprete();
					selectIdiomasAplicables();

					//$('#divFechas').html(data);

					/*$("#divOutput").empty();
					$("#divOutput").append(data);

					$("#divOutput").hide();
					$("#divOutput").toggle(200);*/

				},
				error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
			});
	 }


	 function selectIdiomasInterprete() {

		 	$.ajax({
		 		type:'POST',
		 		url :"/SAO/IdiomasInterpreteSelectServlet",
		 		data: {
		 			id_interprete : $("#id_interprete").val()
		 		},
		 		success: function(data) {

		 			$("#divIdiomasInterprete").empty();
		 			$("#divIdiomasInterprete").append(data);

		 			$("#divIdiomasInterprete").hide();
		 			$("#divIdiomasInterprete").toggle(200);

		 		},
		 		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
		 	});

		 }


		 function selectIdiomasAplicables() {

			 	$.ajax({
			 		type:'POST',
			 		url :"/SAO/IdiomasAplicablesSelectServlet",
			 		data: {
			 			id_interprete : $("#id_interprete").val()
			 		},
			 		success: function(data) {

			 			$("#divIdiomasAplicables").empty();
			 			$("#divIdiomasAplicables").append(data);

			 			$("#divIdiomasAplicables").hide();
			 			$("#divIdiomasAplicables").toggle(200);

			 		},
			 		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
			 	});

		}

















</script>

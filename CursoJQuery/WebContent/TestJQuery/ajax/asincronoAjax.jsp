<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Usando javascript asíncrono AJAX</title>
<script type="text/javascript" src="../../js/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
	$('document').ready(function(){
		obtenerDatos();
		});

	function obtenerDatos(){
		$.ajax({
			url: "textoAJAX.txt",
			type: "GET",
			dataType: "text",
			success: terminado,
			error: error,
			complete: function(xhr,status){
					alert("Completado correctamente");
				}

			});
		}
	function terminado(resultado){
		alert("Datos recibos");
		$("#divPrueba").append(resultado);
		}
	function error(xhr,status,strErr){
		alert("Error al ejecutar, favor de revisar "+status+" "+strErr);
		}
</script>
</head>
 <body>

	    	<h2 style = "margin-left:15px">Uso de las Funcionalidades AJAX</h2>

	    	<div id = "divPrueba" style="font-size:18pt"></div>

</body>

</html>
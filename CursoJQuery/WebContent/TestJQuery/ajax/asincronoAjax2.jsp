<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Usando asistente implementación AJAX</title>
<script type="text/javascript" src="../../js/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
	$(function(){
//		$.get("textoAJAX.txt",terminado,"text");
		$("#divPrueba").load("textoAJAX.txt");
		});
	function terminado(resultado){
		alert('Datos recibidos correctamente');
		$('#divPrueba').append(resultado);
		}
</script>
</head>
<body>
	<h2 style = "margin-left:15px">Uso de las Funcionalidades AJAX</h2>

   	<div id = "divPrueba" style="font-size:18pt"></div>
</body>
</html>
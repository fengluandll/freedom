<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Usando Controladores de Evento AJAX</title>
<script type="text/javascript" src="../../js/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#divPrueba").load("textoAJAX.txt");
		});
	$(document).ajaxStart(function(){
			alert("Llamada AJAX iniciada");
		});
	$(document).ajaxStop(function(){
			alert("Llamada AJAX detenida");
		});
	$(document).ajaxSend(function(){
		alert("Llamada AJAX enviada");
		});
	$(document).ajaxComplete(function(){
		alert("Llamada AJAX completada");
		});
	$(document).ajaxError(function(evt,jqXHR,settings,err){
		alert("Error al realizar la llamada "+err);
		});
	$(document).ajaxSuccess(function(){
		alert("Llamada AJAX realizada exitosamente ");
		});
</script>
</head>
<body>
	<h2 style = "margin-left:15px">Uso de las Funcionalidades AJAX</h2>
	<div id = "divPrueba" style="font-size:18pt"></div>
	
</body>
</html>
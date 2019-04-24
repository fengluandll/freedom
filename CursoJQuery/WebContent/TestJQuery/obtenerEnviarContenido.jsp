<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Obtener y enviar contenido</title>
<script type="text/javascript" src="../js/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
	$("document").ready(function(){
//		alert('Los componentes de la lista son '+ $('#listaElementos').html());
		var nvoElemento = '<h1>Nuevo elemento</h1>';
//		$('li.b').html(nvoElemento);
//		alert('El texto del parrafo con clase b es '+ $('p.b').text());
		$('li').text('Elemento de tipo li');
		
		});
</script>
<style type="text/css">
	    .a{
	    	color:#008000;
	    }
	    .b{
	    	color:#FF0000;
	    }
	</style>
</head>


	<body>
		<ul id = "listaElementos">
	    	<li class = "a">Elemento 1</li>
	    	<li class = "b">Elemento 2</li>
	    	<li class = "c">Elemento 3</li>
	    </ul>

	    <p class = "a">Parrafo 1</p>
	   	<p class = "b">Parrafo 2</p>
	   	<p class = "c">Parrafo 3</p>
	</body>

</html>
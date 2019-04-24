<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Obteniendo informacion de la pagina</title>
<script type="text/javascript" src="../js/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
	$("document").ready(function(){
//		alert('Existen'+ $('p').size() + 'Elementos de tipo <p>' );
//		var elementos = $('li').get();
//		alert('Existen '+elementos.length + 'elementos de tipo <li>');
//		$('ul').find('li.b').css('border','2px solid blue'); //encuentra en todas las ul las listas con la clase b y ponle un borde
		var borde = 2;
		$('p').each(function(){
				$(this).css('border',borde + 'px solid blue');
				borde = borde + 1;
			});
		
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
	    	<li class = "a"><label class='a'>Elemento 1</label></li>
	   		<li class = "b">Elemento 2</li>
	    	<li class = "c">Elemento 3</li>
	    </ul>

		<p class = "a">Parrafo 1</p>
	    <p class = "b">Parrafo 2</p>
	    <p class = "c">Parrafo 3</p>
	    
	</body>

</html>
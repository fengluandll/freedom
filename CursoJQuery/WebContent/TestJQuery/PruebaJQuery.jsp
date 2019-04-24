<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Test JQuery</title>
<script type="text/javascript" src="../js/jquery-1.11.1.min.js"></script>
</head>
	
	<script type="text/javascript">
		$("document").ready(function(){
			//alert('La pagina se cargo automaticamente');
//			$('li').css('border','3px solid blue');
//			$('p').css('border','3px solid blue');
//			$('.a').css('border','3px solid blue');
//			$('li,.a').css('border','3px solid blue');
//			$('#elementoLista').css('border','3px solid blue');
//			$('li+.b').css('border','3px solid blue');
			$('ul~p').css('border','3px solid blue');
			});
	
	</script>
	
<style>
		.estilo1{
	    	width: 400px;
	    	clear:both;
	    }
	    .estilo2{
	    	position:absolute;
	    	left: 150px;
	   		width: 200px;
	   	}
</style>
<body>
		<ul>
	    	<li class = 'a' id = 'elementoLista'>Elemento 1</li>
	   		<li class = 'b'>Elemento 2</li>
	    	<li class = 'c'>Elemento 3</li>
	    </ul>

	    <p class = "a">Parrafo 1</p>
	    <p class = "b">Parrafo 2</p>
	    <p class = "c">Parrafo 3</p>
	</body>

</html>
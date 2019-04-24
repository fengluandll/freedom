<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Filtros por contenido</title>
<script type="text/javascript" src="../js/jquery-1.11.1.min.js"></script>
</head>
<script type="text/javascript">
	$("document").ready(function(){
//		$('p[class]').css('border','2px solid blue');
//		$('p[class=a]').css('border','2px solid blue');
//		$('li[class^=elem]').css('border','2px solid blue'); //Que empiecen con la palabra elem
//	    $('li[class$=3]').css('border','2px solid blue'); //Que terminen con 3
//		$('p[id=p1][class*=a]').css('border','2px solid blue'); //El parrado con su id p1 yq ue en su clase contenga la letra a
		$('p[id^=p][class*=a]').css('border','2px solid blue'); //El parrado con su id p1 yq ue en su clase contenga la letra a
		
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
	    	<li class = 'elem1' id = 'elementoLista'>Elemento 1</li>
	   		<li class = 'b'>Elemento 2</li>
	    	<li class = 'elem3'>Elemento 3</li>
	    </ul>

	    <p id="p1" class = "a">Parrafo 1</p>
	    <p id="p2" class = "b">Parrafo 2</p>
	    <p id="p3" class = "a">Parrafo 3</p>
	</body>
</html>
<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Filtros por visibilidad contenido y child</title>
<script type="text/javascript" src="../js/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
	$("document").ready(function(){
//		$('p:contains(Pa)').css('border','2px solid blue'); Los parrafos que contengan dentro de su contenido la palabra Pa
//		$('p:parent').css('border','2px solid blue'); Todo
//		$('ul:has(li[class=a])').css('border','2px solid blue'); //Toda la lista que al menos una lista contenga la clase a,seleccionara todo el ul
//  		$('ul li:nth-child(3)').css('border','2px solid blue'); // de una lsita selecciona el humero 3
  		$('p:last-child').css('border','2px solid blue'); // //El ultimo parrafo que encuentre
  		
		});
</script>
</head>

<body>
	<ul>
	    	<li class = 'a' id = 'elementoLista'>Elemento 1</li>
	   		<li class = 'b'>Elemento 2</li>
	    	<li class = 'elem3'>Elemento 3</li>
	    </ul>

		    <p id="p1" class = "a">Parrafo 1</p>
		    <p id="p2" class = "b">Parrafo 2</p>
		    <p id="p3" class = "a">Parrafo 3</p>
</body>
</html>
<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Manejo de atributos</title>
<script type="text/javascript" src="../js/jquery-1.11.1.min.js"></script>
<script type="text/javascript">

	$('document').ready(function(){
//		alert('El atributo src de img es: '+ $('img').attr('src'));
//	$('img').attr('src','../img/fondo2.jpg'); //Cambia el atribute src por otra imagen
/*	$('img').attr({src:'../img/fondo2.jpg',title:'fondo 2'});
	$('a').attr('href','../img/fondo2.jpg')
*/
	$('a').removeAttr('href');//elimina el link
		});
	
</script>
<style type="text/css">
	.estilo1{
    	width:300px;
    }
</style>

</head>


	<body>
		<a href='../img/fondo1.png'>
			<img src="../img/fondo1.png" class = 'estilo1' title='fondo1'/>
		</a>
	</body>
</html>
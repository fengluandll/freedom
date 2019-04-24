<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Concatenacion de funciones</title>
<script type="text/javascript" src="../js/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
	$("document").ready(function(){
		$('#listaElementos').find('li.a').find('.aa').css('border','2px solid blue');
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
	    	<li class = "a"><label class = 'aa'>Elemento 1</label></li>
	   		<li class = "b"><label class = 'bb'>Elemento 2</label></li>
	 		<li class = "c"><label class = 'cc'>Elemento 3</label></li>
	    </ul>

	    <p class = "a">Parrafo 1</p>
	    <p class = "b">Parrafo 2</p>
	    <p class = "c">Parrafo 3</p>
	</body>

</html>
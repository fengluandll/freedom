<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Filtros por formulario</title>
<script type="text/javascript" src="../js/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
$("document").ready(function(){
//	$('form :text').before('Es un texto').css('border','2px solid red');//Antepone una palabra a todos los texto y le pone un borde
//	$('form :password').before('Es un password').css('border','2px solid blue'); // el elemento password se le antepone una palabra
//	$('form :radio[checked = checked]').before('Seleccionado');
	$('form :submit').css('border','2px solid red');
	
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
</head>
<body>
<form>
			<tbody>

				<tr class="estilo1">
	    	 		<td  class="estilo2">
	    	 			<label>FORMULARIO DE PRUEBA</label>
	    			</td>
	   	 		</tr>
	    	 	
	    	 	</br>
	    	 	</br>
	    	 	
	    	 	<tr class="estilo1">
	    	 		<td  class="estilo2">
	    	 			<label>Nombre:</label>
	    			</td>
	    	 		<td>
	    	 			<input class="estilo2" type="text" value=""/>
	    			</td>
	   	 		</tr>
	    	 	
	    	 	</br>
	    	 	</br>
	    
	    		<tr class="estilo1">
	    	 		<td  class="estilo2">
	    	 			<label>Apellido:</label>
	    	 		</td>
	    	 		<td  class="estilo2">
	    	 			<input class="estilo2" type="text" value=""/>
	    	 		</td>
	    	 	</tr>
	    	 	
	    	 	</br>
	    	 	</br>
	    	 	
	    	 	<tr class="estilo1">
	    	 		<td  class="estilo2">
	    	 			<label>Usuario:</label>
	    	 		</td>
	    	 		<td>
	    	 			<input class="estilo2" type="text" value=""/>
	    			</td>
	   	 		</tr>
	    	 	
	    	 	</br>
	    	 	</br>
	    	 	
	    	 	<tr class="estilo1">
	    	 		<td  class="estilo2">
	    	 			<label>Contrase&ntilde;a:</label>
	    	 		</td>
	    	 		<td>
	    	 			<input class="estilo2" type="password" value=""/>
	    	 		</td>
	    	 	</tr>
	    	 
	    	 	</br>
	    		</br>
	   
	   	 		<tr class="estilo1">
	    	 		<td  class="estilo2">
	    	 			<label>Genero:</label>
	    	 		</td>
	    	 		<td>
	    				<input name = "genero" type="radio" value="" style="margin-left:90px;"/>M</>
	   	 				<input name = "genero" type="radio" value="" checked='checked'/>F</>
	   	 			</td>
	     		</tr>
	    
	     		</br>
	     		</br>
	    
	     		<tr class="estilo1">
	    	 		<td  class="estilo2">
	    				<label>Acciones de Interes:</label>
	   	 			</td>
	   	 			<td>
	    	 			<input style="margin-left:15px;" name = "genero" type="checkbox" value=""/>Comer</>
	    				<input name = "genero" type="checkbox" value=""/>Dormir</>
	   	 				<input name = "genero" type="checkbox" value=""/>Bailar</>
	   	 			</td>
	   	 		</tr>
	 
	   	 		</br>
	   	 		</br>

    	 		<tr class="estilo1">	    	 			
	    	 		<td>
	    	 			<input style="margin-left:150px" type="submit" value="Guardar"/>
	    	 		</td>
	    		</tr>

	    	</tbody>
	    </form>

</body>
</html>
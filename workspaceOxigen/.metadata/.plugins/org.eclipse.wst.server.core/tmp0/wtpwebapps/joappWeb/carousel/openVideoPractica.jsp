<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title></title>
<script type="text/javascript" src="../js/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
function atras(){
	var text = window.parent.document.getElementById('menuCursos').getElementsByTagName('a')[0].click();
	history.go(-1);	
}
</script>
<style type="text/css">
body{
	background-color: black;
}
#divlogoAtras{
	position: relative;
    top: 10px;
    right: 0px;
    left: 25px;
}
.regla{
border: 1px solid #d19405;
position: relative;
}
</style>
</head>
<body>

<div style="text-align:center;">
	<a href="javascript:atras()" >
		<img src="../images/Arrow-Left-blue-48.png" alt="atras" />
	</a>
</div>
<hr class="regla"/>

<div id="<%=request.getParameter("linkTopic") %>"  
	 class="wistia_embed" 
	 style="width:637px;
	        height:355px; 
	        margin: 0 auto;
	        position: relative;
	        top: 0px;">&nbsp;</div>

        
<script charset="ISO-8859-1" src="//fast.wistia.com/assets/external/E-v1.js"></script>
<script type="text/javascript">

wistiaEmbed = Wistia.embed('<%=request.getParameter("embed") %>', {

});
wistiaEmbed.ready( function() {
	  var all_cookies = document.cookie.split(';'), // gets the value of the cookies on the page
	    cookie_str = "resume_video=",
	    resume_cookie = does_resume_cookie_exist(all_cookies);


	  function does_resume_cookie_exist(cookie_arr) {
	    var i, curr_string, found;
	      for (i = 0; i < cookie_arr.length; i++) {
	      curr_string = cookie_arr[i];
	        if (curr_string.substring(0,5) === cookie_str.substring(0,5)) {
	          // we've found it!
	          found = curr_string;
	          break;
	        }
	      }
	    return found;
	  }

	  function set_cookie_time(t) {
//		  alert("Tiempo: "+t.toString());
		 
	    document.cookie = cookie_str + t.toString(); // this takes the time (t) and sets the cookie with that time  
	  }

	  if (resume_cookie) {
	    num = resume_cookie.split('=')[1];
	    start_time = parseInt(num);
//	    alert("Tiempo en que se quedo "+start_time);
	    wistiaEmbed.time(start_time).play(); // plays the video at the specific time defined in the cookie upon return to the page
	  } else {
	    set_cookie_time(0);  // places a cookie on the visitor
//	    wistiaEmbed.play();  // starts the video from the beginning
	  }
	  wistiaEmbed.bind("timechange", function(t) { // on timechange, reset cookie
	    set_cookie_time(t);
	  });
	  wistiaEmbed.bind("end", function() { // if person has watched the entire video, sets the video to beginning upon retun
	    set_cookie_time(0);
	    registraPorcentaje();
	  });
	  
	  wistiaEmbed.bind("pause", function() { // if person sets the video to pause
			
		  registraPorcentaje();
		  });
	});

function registraPorcentaje(){
    start_time = wistiaEmbed.time();
//    alert("Tiempo en que se quedo "+start_time);

    function terminado(resultado){
			//alert("Terminado "+resultado);
	    }
	function error(xhr,status,strErr){
		alert("Error al registrar el tiempo del video "+status+" "+strErr);
		}
//    alert("Duracion "+wistiaEmbed.duration());
    porcentaje = Math.round(start_time) * 100 / + Math.round(wistiaEmbed.duration());
    var dataString = "porc="+porcentaje+"&accion=practica";
    $.ajax({
		type:"POST",
		url: "/joappWeb/RegistraHistorialVideo",
		data: dataString,
		success: terminado,
		error: error,
		complete: function(xhr,status){
			//alert("Completado correctamente");
		}
    });
//    alert("porcen "+porcentaje);
	
}


</script>	
</body>
</html>
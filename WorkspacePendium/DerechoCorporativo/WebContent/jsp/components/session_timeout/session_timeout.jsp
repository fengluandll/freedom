<%--

Incluir este archivo en las paginas necesarias en <head> con:
	%@include file="/jsp/components/session_timeout/session_timeout.jsp" %
En lugar de usar: 
	<script type="text/javascript" src=.....
--%>
<%@ page import="mx.com.televisa.derechocorporativo.util.ControlSession"%>

<script type="text/javascript">


/**
 * Seccion : Modulo de Seguridad
 * Descripci�n :Timer para logout despu�s de timeout de sesion
 */
 function idleLogout() {
	    var t;
	    var t2;
	    window.onload = resetTimer;
	    window.onmousemove = resetTimer;
	    window.onmousedown = resetTimer; // catches touchscreen presses
	    window.onclick = resetTimer;     // catches touchpad clicks
	    window.onscroll = resetTimer;    // catches scrolling with arrow keys
	    window.onkeypress = resetTimer;
	    
	    function logout() {
	    	/*alert("Se ha detectado inactividad en su sesi�n, por razones de seguridad ser� sacado de la aplicaci�n");*/
	    	/*swal({   title: "Timeout",   
	    			 text: "Se ha detectado inactividad en su sesi�n, por razones de seguridad ser� sacado de la aplicaci�n",   
	    			 type: "info",  
	    			 confirmButtonText: "Ok" });*/

	    	swal({   title: "Timeout de sesi�n",   
   			 		 text: "Se ha detectado inactividad en su sesi�n. �desea continuar trabajando?",   
   			 		 type: "warning",   
	    			 confirmButtonColor: "#DD6B55",   
//	    			 confirmButtonText: "Ok",
	    			 confirmButtonText: "Salir",
	    			 cancelButtonText: "Continuar",
	    			 showCancelButton: true,
	    			 animation: "false",
	    			 //timer: "300000",   //5mins Tiempo que dura el mensaje qu eser� cerrada la sesi�n
	    			 timer: <%=ControlSession.tiempoInactividadMensaje()%>,
	    			 //timer: "10000",   //10seg
	    			 allowOutsideClick: "true",
	    			 closeOnConfirm: false } , 
	    			 function(){  top.location.href = '<%=request.getContextPath()%>/LoginExitServlet'; });
	    			 
	    }
	    function resetTimer() {
	        clearTimeout(t);
	       // t = setTimeout(logout,1800000);  // 30 min Tiempo de inactividad en la sesi�n
	        t = setTimeout(logout,<%=ControlSession.tiempoInactividadMensaje()%>);
	        //t = setTimeout(logout,30000);  //
	    }        
	    
	}
	idleLogout();


</script>
/**
 * Este archivo reune las funciones mas usadas dentro
 * de cualquier sistema con uso de formularios.
 * 
 * @author Eduardo Armando Nava Correa
 *
 * @indice	desplegar()
 *		aparecer()
 *		asignar()
 *		reparar()
 *		redondear()
 *		redondearAEnteros()
 *		limpiarCampo()
 *		validarNumero()
 *		soloNumero
 *		validarPorcentaje()
 *		validarTexto()
 * 		validarCodigo()
 */

	/**
	 * Hace aparecer una tabla, división (div) o span a travéz de su id,
	 * usualmente cuando este tiene el atributo style.display con valor "none".
	 * y lo hace desaparecer en caso contrario.
	 */	
	function desplegar(region) {
		
		if (region.style.display == "") {
			region.style.display = "none"
		} else {
			region.style.display = ""		
		}			
	}
	
	/**
	 * Hace aparecer una tabla, división (div) o span a travéz de su id,
	 * usualmente cuando este tiene el atributo style.visibility con valor "hidden".
	 * y lo hace desaparecer en caso contrario.
	 *
	 * La diferencia con desplegar() es el atributo usado; 
	 * una region con display-none, no ocupa ningun espacio en la pantalla.
	 *
	 */	
	function aparecer(region) {
		
		if (region.style.visibility == "visible") {
			region.style.visibility = "hidden"
		} else {
			region.style.visibility = "visible"		
		}	
		
	}
	
	function habilitar(campo) {
		
		if (campo.disabled == "true") {
			campo.disabled = "false"
		} else {
			campo.disabled = "true"		
		}	
		
	}
	
	/**
	 * Le asigna al campo (input) dado, el valor dado.
	 */	
	function asignar(campo, valor) {
		
		campo.value = valor;
	}
	
	/**
	 * Reúne las características de las funciones redondear y limpiar
	 * con argumentos preestablecidos.
	 */	
	function reparar(campo) {
		
		campo.value = redondear(campo.value, 2);
		limpiarCampo(campo, "0");
	}

	/**
	 * Reúne las características de las funciones redondear y limpiar
	 * con argumentos preestablecidos.
	 */	
	function repararAceptandoCero(campo) {
		
		campo.value = redondear(campo.value, 2);
		limpiarCampo(campo, "");
	}
	
	/**
	 * Redondea un número en la cantidad de decimales dada.
	 *
	 * @ejemplo	x = redondear(x, 2);	
	 */
	function redondear(cantidad, decimales) {
	
		var cantidad = parseFloat(cantidad);
		var decimales = parseFloat(decimales);

		decimales = (!decimales ? 2 : decimales);
		return Math.round(cantidad * Math.pow(10, decimales)) / Math.pow(10, decimales);
	} 
	
	/**
	 * Redondea un número a enteros
	 *
	 * @ejemplo	x = redondearAEnteros(x);	
	 */
	function redondearAEnteros(cantidad) {
	
		var cantidad = parseFloat(cantidad);
		var decimales = parseFloat(0);

		return Math.round(cantidad * Math.pow(10, decimales)) / Math.pow(10, decimales);
	} 

	/**
	 * Redondea un número a decenas
	 *
	 * @ejemplo	x = redondearADecenas(x);	
	 */
	function redondearADecenas(cantidad) {
	
		return 10 * (redondearAEnteros(.1 * cantidad));
	} 

	/**
	 * Redondea un número a centenas
	 *
	 * @ejemplo	x = redondearACentenas(x);	
	 */
	function redondearACentenas(cantidad) {
	
		return 100 * (redondearAEnteros(.01 * cantidad));
	} 
	
		/**
	 * Redondea un número a millares
	 *
	 * @ejemplo	x = redondearAMillares(x);	
	 */
	function redondearAMillares(cantidad) {
	
		return 1000 * (redondearAEnteros(.001 * cantidad));
	} 
	

	/**
	 * En ocasiones, después de realizar una operación con valores no validos,
	 * un campo muestra un resultado indeseable como lo es el caso de  "Infinity",
	 * Esta función limpia ese campo cuando se presenta esa situación, 
	 * o cuando contiene el valor indeseado dado.
	 */
	function limpiarCampo(campo, valorIndeseado) {
		/*
			Ejemplo: limpiarCampo(x, 0);	
		*/
		if(campo.value == "Infinity" 
			|| campo.value == "-Infinity"
			|| campo.value == "NaN"			 
			|| campo.value == "undefined"		
			|| campo.value == valorIndeseado)	{
				campo.value = "";
		}
	
	}

	/**
	 * Arroja una alerta cuando el campo validado no es numérico.
	 *
	 * @uso <input onKeyUp="validarNumero(this)">
	 *
	 * @deprecated Sustituido por soloNumero()
	 */ 
	function validarNumero(campo) {

		var enNumero = (1 * campo.value);
		if( enNumero != campo.value && campo.value != ".") {
			campo.select();
			if(typeof swal != 'undefined')
				alert("El campo actual debe tener valor Numerico")
			else
				swal("El campo actual debe tener valor Numerico")
			return false;
		} else {
			return true;
		}			
	}
	
	/**
	 * No permite que entren letras a un campo, ni que 
	 * entre más que UN punto (decimal)
	 *
	 * @uso <input onkeypress="return soloNumero(this)">
	 */		
	function soloNumero(campo) {
		
		var tecleada = event.keyCode;
		
		if((tecleada >= 48 && tecleada <= 57) || tecleada == 46) {			
			if(tecleada == 46 && campo.value.indexOf(".") != -1) {
				return false;	
			} else {
				return true;
			}			
		}else{
			return false;
		}
	}

	/**
	 * No permite que entren letras a un campo
	 *
	 * @uso <input onkeypress="return soloNumero(this)">
	 */		
	function soloNumeroEntero(campo) {
	
		var tecleada = event.keyCode;

		if(tecleada >= 48 && tecleada <= 57) {
			return true;						
		} else {
			return false;
		}
	}


	/**
	 * Arroja una alerta cuando el campo validado no es numérico o es mayor a 100.
	 *
	 * @uso <input onKeyUp="validarPorcentaje(this)">
	 */ 
	function validarPorcentaje(campo) {
		
		if(validarNumero(campo)) {
		
			if(campo.value > 100) {			
				campo.select();
				if(typeof swal != 'undefined')
					alert("Atencion, el porcentaje debe ser menor o igual a 100")
				else
					swal("Atencion, el porcentaje debe ser menor o igual a 100")
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}			
	}

	/**
	 * Arroja una alerta cuando el campo validado tiene alguno de los caracteres enlistados.
	 *
	 * @uso <input onKeyUp="validarTexto(this)">
	 */ 
	function validarTexto(campo) {
		
		if(
		   campo.value.indexOf("@") != -1 ||
		   campo.value.indexOf("?") != -1 ||
		   campo.value.indexOf("¿") != -1 ||
		   campo.value.indexOf("{") != -1 ||
		   campo.value.indexOf("}") != -1) {
		
				campo.select();
				if(typeof swal != 'undefined')
					alert("El campo actual no debe contener ninguno de los siguientes simbolos:\n\n@\t?\t¿\t{\t}");
				else
					swal("El campo actual no debe contener ninguno de los siguientes simbolos:\n\n@\t?\t¿\t{\t}")
				return false;
		} else {
			return true;
		}							   
	}
	
	/**
	 * Arroja una alerta cuando el campo validado tiene alguno de los caracteres enlistados.
	 *
	 * @uso <input onKeyUp="validarCodigo(this)">
	 */ 
	function validarCodigo(campo) {
		
		if(
		   campo.value.indexOf(" ") != -1 ||
		   campo.value.indexOf("-") != -1 ) {
		
				campo.select();
				if(typeof swal != 'undefined')
					alert("El campo actual no debe contener guiones ni espacios.");
				else
					swal("El campo actual no debe contener guiones ni espacios.")
				return false;
		} else {
			return true;
		}						   
	}
	


	
	
	
	
function sortNumber(a,b)
{
return a - b
}	


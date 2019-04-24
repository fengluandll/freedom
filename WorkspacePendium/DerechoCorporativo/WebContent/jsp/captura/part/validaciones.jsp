<%--

Incluir este archivo en las paginas necesarias en <head> con:
	%@include file="/js/capturaValidaciones/validaciones.jsp" %
En lugar de usar: 
	<script type="text/javascript" src=.....
--%>

<%--
	TODO: Revisar si no hay conflicto al cargar doble JQuery
--%>
<script type="text/javascript" src="<c:out value="${applicationBean.contextPath}"/>/js/jquery/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="<c:out value="${applicationBean.contextPath}"/>/js/jquery/jquery.maskedinput.js"></script>
<script type="text/javascript" src="<c:out value="${applicationBean.contextPath}"/>/js/jquery/jquery.numeric.js"></script>
<script type="text/javascript" src="<c:out value="${applicationBean.contextPath}"/>/js/accounting/accounting.min.js"></script>
<script src="<c:out value="${applicationBean.contextPath}"/>/jsp/components/sweet_alert/sweetalert.min.js"></script> 
 
<script type="text/javascript">


function openTab(evt, poderName) {
	
	
    // Declare all variables
    var i, tabcontent, tablinks;
    document.getElementById("defaultOpen").click();
    // Get all elements with class="tabcontent" and hide them
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }

    // Get all elements with class="tablinks" and remove the class "active"
    tablinks = document.getElementsByClassName("TabbedPanelsTab");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" TabbedPanelsTabSelected", "");
    }

    // Show the current tab, and add an "active" class to the button that opened the tab
    document.getElementById(poderName).style.display = "block";
    evt.currentTarget.className += " TabbedPanelsTabSelected";
    
    
} 


/**
 * Seccion : Información General
 * Descripción : A partir del campo duración (C14), y de la fecha Inicial (C15),
 * se calcula la fecha final (C16)
 *	
 */

function convertirAMayusculas(input){ 
	 input.value = input.toUpperCase(); 
}
 
function getFechaEscCons(){
	 //C61 select numero de años
	 //lblAtr1
	 var valueC61=$("#C61 option:selected").text();
	 if($.isNumeric(valueC61)){
		 $("#lblAtr1").css("display","inline");
	 }else{
		 $("#lblAtr1").css("display","none");
	 }
	 
 }
 
function getFechaFinal() {
 
	var fechaIni = document.getElementsByName("C15")[0].value;
	var duracion = document.getElementsByName("C14")[0].selectedOptions[0].text;
	
	if(duracion * 1 == duracion) {
		var labelAtr1 = document.getElementById("lblAtr1").style.display = 'inline';
		//alert('Es numero');
		
		var fechasDiv = fechaIni.split("/");
		var anio      = parseInt(fechasDiv[2])+parseInt(duracion);
		var fechaFin  = String(fechasDiv[0])+"/"+String(fechasDiv[1])+"/"+String(anio);
		document.getElementsByName("C16")[0].value = fechaFin;
		
	} else {
		var labelAtr1 = document.getElementById("lblAtr1").style.display = 'none';
		//alert('No es numero');
		
		document.getElementsByName("C16")[0].value = "";
		
	}
	
	
}

/**
 * Seccion : Administración y Vigilancia
 * Descripción : A partir de la selección de Checboxes se muestran y ocultan las flextables correspondientes
 */
 function showMe (it, box) {
	 var vis = (box.checked) ? "block" : "none";
	 /*if(!document.getElementById("C1011").checked||it == "flexTable_8"){
	 	document.getElementById(it).style.display = vis;
	 }*/
	 
		 	document.getElementById(it).style.display = vis;
		 
	 /*
	 if(document.getElementById("C1011").checked){

		 document.getElementById("C1012").checked = false;
		 document.getElementById("C1013").checked = false;
		 document.getElementById("C1014").checked = false;
		 document.getElementById("C1015").checked = false;
		 document.getElementById("C1016").checked = false;
		 document.getElementById("C1017").checked = false;
		 document.getElementById("C2013").checked = false;//Argu
		 
		//ECM 29 Octubre 2015
		document.getElementById('C1062').checked = false;
		document.getElementById('C1063').checked = false;
		
		//ECM 01 Abril 2016
		document.getElementById('C1164').checked = false;
		document.getElementById('C1166').checked = false;
		//document.getElementById('C1168').checked = false;
		//document.getElementById('C1170').checked = false;
		document.getElementById('C1172').checked = false;



		 document.getElementById("C1012").disabled = true;
		 document.getElementById("C1013").disabled = true;
		 document.getElementById("C1014").disabled = true;
		 document.getElementById("C1015").disabled = true;
		 document.getElementById("C1016").disabled = true;
		 document.getElementById("C1017").disabled = true;
		 
		//ECM 29 Octubre 2015
		document.getElementById('C1062').disabled = true;
		document.getElementById('C1063').disabled = true;
		
		//ECM 01 Abril 2016
		document.getElementById('C1164').disabled = true;
		document.getElementById('C1166').disabled = true;
		//document.getElementById('C1168').disabled = true;
		//document.getElementById('C1170').disabled = true;
		document.getElementById('C1172').disabled = true;
		
		//Argu 05 Sep 2016
		document.getElementById('C2013').disabled = true;

	 }
	 */
	 /**
	 if(it == "flexTable_8" && box.checked){
		 document.getElementById("C1012").checked = false;
		 document.getElementById("C1013").checked = false;
		 document.getElementById("C1014").checked = false;
		 document.getElementById("C1015").checked = false;
		 document.getElementById("C1016").checked = false;
		 document.getElementById("C1017").checked = false;
		//ARGU 05 Sep 2016
		 document.getElementById("C2013").checked = false;
		 
		//ECM 29 Octubre 2015
		document.getElementById('C1062').checked = false;
		document.getElementById('C1063').checked = false;
		
		//ECM 01 Abril 2016
		document.getElementById('C1164').checked = false;
		document.getElementById('C1166').checked = false;
		//document.getElementById('C1168').checked = false;
		//document.getElementById('C1170').checked = false;
		document.getElementById('C1172').checked = false;

		 
		 document.getElementById("C1012").disabled = true;
		 document.getElementById("C1013").disabled = true;
		 document.getElementById("C1014").disabled = true;
		 document.getElementById("C1015").disabled = true;
		 document.getElementById("C1016").disabled = true;
		 document.getElementById("C1017").disabled = true;
		 
		//ECM 29 Octubre 2015
		document.getElementById('C1062').disabled = true;
		document.getElementById('C1063').disabled = true;
		
		//ECM 01 Abril 2016
		document.getElementById('C1164').disabled = true;
		document.getElementById('C1166').disabled = true;
		//document.getElementById('C1168').disabled = true;
		//document.getElementById('C1170').disabled = true;
		document.getElementById('C1172').disabled = true;
		//ARGU 05 Sep 2016
		document.getElementById('C2013').disabled = true; 
		 
		 document.getElementById("flexTable_11").style.display ="none";
		 document.getElementById("flexTable_12").style.display ="none";
		 document.getElementById("flexTable_13").style.display ="none";
		 document.getElementById("flexTable_14").style.display ="none";
		 document.getElementById("flexTable_15").style.display ="none";
		 document.getElementById("flexTable_16").style.display ="none";

		 //ECM 29 Octubre 2015 
		 document.getElementById("flexTable_25").style.display ="none";
		 document.getElementById("flexTable_26").style.display ="none";
		 
		//ECM 01 Abril 2016
		 document.getElementById("flexTable_39").style.display ="none";
		 document.getElementById("flexTable_40").style.display ="none";
		 //document.getElementById("flexTable_41").style.display ="none";
		 //document.getElementById("flexTable_42").style.display ="none";
		 document.getElementById("flexTable_43").style.display ="none";
		 
		//ARGU 05 Sep 2016
		 document.getElementById("flexTable_9").style.display ="none";

	  } else {

		  document.getElementById("C1012").disabled = false;
		  document.getElementById("C1013").disabled = false;
		  document.getElementById("C1014").disabled = false;
		  document.getElementById("C1015").disabled = false;
		  document.getElementById("C1016").disabled = false;
		  document.getElementById("C1017").disabled = false;

		//ECM 29 Octubre 2015
		document.getElementById('C1062').disabled = false;
		document.getElementById('C1063').disabled = false;
		
		//ECM 01 Abril 2016
		document.getElementById('C1164').disabled = false;
		document.getElementById('C1166').disabled = false;
		//document.getElementById('C1168').disabled = false;
		//document.getElementById('C1170').disabled = false;
		document.getElementById('C1172').disabled = false;
		
		//ARGU 05 Sep 2016
		document.getElementById('C2013').disabled = false;

	  }
	 **/
	}


 /**
  * Seccion : Administración y Vigilancia
  * Descripción : A partir de la selección de Checboxes se muestran y ocultan las flextables correspondientes
  */
  function test () {
 	 alert("yeah onload");
 	}

/**
*	ECM 15 Julio 2015
*	Formatear y desformatear campos
*
*/
function performOnFocus(element){
	element.value = accounting.unformat(element.value);
	element.select();
	element.style.textAlign = "right";


}
function performOnBlur(element){
	element.value = trim(element.value);
	//element.value = accounting.formatMoney(element.value, { symbol: "$",  format: "%s %v" });
	element.value = accounting.formatMoney(element.value);
	element.style.textAlign = "right";

}

function trim(stringToTrim) {
	return stringToTrim.replace(/^\s+|\s+$/g,"");
}

//ECM 24 AGOSTO 2015
function cambiaSemaforo(src){
	
	//alert('ECM:  '+src);
	if (src == "button_yellow.png"){
		document.getElementById("im").src="../../img/"+src;
		//alert('Eligio Amarillo');
		document.getElementById("C1035").style.display = 'block';
		document.getElementById("C1036").style.display = 'block';
		document.getElementById("C1037").style.display = 'block';
		document.getElementById("C1038").style.display = 'block';
		document.getElementById("C1039").style.display = 'block';
		document.getElementById("C1040").style.display = 'block';
		document.getElementById("C1041").style.display = 'block';
		document.getElementById("C1042").style.display = 'block';
		document.getElementById("C1043").style.display = 'block';
		document.getElementById("C1044").style.display = 'block';
		document.getElementById("C1045").style.display = 'block';
		document.getElementById("C1046").style.display = 'block';
		document.getElementById("C1047").style.display = 'block';
		//document.getElementById("C1048").style.display = 'block';

		document.getElementById("nom_C1022").style.display = 'block';
		document.getElementById("ele_C1022").style.display = 'block';
		
		//ECM 14 Abril 2016 - Cambiar el tooltip
		document.getElementById("im").title = 'Amarillo';

		
	}else if(src == "button_red.png"){
		document.getElementById("im").src="../../img/"+src;
		//alert('Eligio Rojo');
		document.getElementById("C1035").style.display = 'block';
		document.getElementById("C1036").style.display = 'block';
		document.getElementById("C1037").style.display = 'block';
		document.getElementById("C1038").style.display = 'block';
		document.getElementById("C1039").style.display = 'block';
		document.getElementById("C1040").style.display = 'block';
		document.getElementById("C1041").style.display = 'block';
		document.getElementById("C1042").style.display = 'block';
		document.getElementById("C1043").style.display = 'block';
		document.getElementById("C1044").style.display = 'block';
		document.getElementById("C1045").style.display = 'block';
		document.getElementById("C1046").style.display = 'block';
		document.getElementById("C1047").style.display = 'block';
		//document.getElementById("C1048").style.display = 'block';
		
		document.getElementById("nom_C1022").style.display = 'block';
		document.getElementById("ele_C1022").style.display = 'block';
		
		document.getElementById("im").title = 'Rojo';

	}else if(src == "button_green.png"){
		document.getElementById("im").src="../../img/"+src;
		//alert('Eligio Verde');
		document.getElementById("C1035").style.display = 'none';
		document.getElementById("C1036").style.display = 'none';
		document.getElementById("C1037").style.display = 'none';
		document.getElementById("C1038").style.display = 'none';
		document.getElementById("C1039").style.display = 'none';
		document.getElementById("C1040").style.display = 'none';
		document.getElementById("C1041").style.display = 'none';
		document.getElementById("C1042").style.display = 'none';
		document.getElementById("C1043").style.display = 'none';
		document.getElementById("C1044").style.display = 'none';
		document.getElementById("C1045").style.display = 'none';
		document.getElementById("C1046").style.display = 'none';
		document.getElementById("C1047").style.display = 'none';
		//document.getElementById("C1048").style.display = 'none';
		
		//
		document.getElementById("nom_C1022").style.display = 'none';
		document.getElementById("ele_C1022").style.display = 'none';
		
		document.getElementById("im").title = 'Verde';

	}else if(src == "0"){
		document.getElementById("im").src='';

		document.getElementById("C1035").style.display = 'none';
		document.getElementById("C1036").style.display = 'none';
		document.getElementById("C1037").style.display = 'none';
		document.getElementById("C1038").style.display = 'none';
		document.getElementById("C1039").style.display = 'none';
		document.getElementById("C1040").style.display = 'none';
		document.getElementById("C1041").style.display = 'none';
		document.getElementById("C1042").style.display = 'none';
		document.getElementById("C1043").style.display = 'none';
		document.getElementById("C1044").style.display = 'none';
		document.getElementById("C1045").style.display = 'none';
		document.getElementById("C1046").style.display = 'none';
		document.getElementById("C1047").style.display = 'none';
		//document.getElementById("C1048").style.display = 'none';

		document.getElementById("nom_C1022").style.display = 'none';
		document.getElementById("ele_C1022").style.display = 'none';

		document.getElementById("im").title = '';
	}
}

//ECM 27 Julio 2015
function cambiaEmpresa(){
	var empresa = null;
	empresa = document.getElementById('C1').value;
	//empresa = document.getElementsByName("C1").value;
	//alert('ECM Empresa:  '+empresa);
	window.onload;
}

	$(document).ready(function(){
		
	//});
//window.onload = function(){
	//alert(document.getElementById("C47").value);
	//if($( "#sec7" ).val()!= undefined){
	     //   	alert("click"+$( "#sec7" ).val());
	   //   	$( "#sec7" ).click();
	        	
	  //      }
	
	//remueve la clase select
	$(".TabbedPanelsTab").removeClass("TabbedPanelsTabSelected");
	var idSe =  '<%=request.getParameter("idSeccion")%>';
	
	//agrega al actual
	$("#sec"+idSe).addClass("TabbedPanelsTabSelected");
	
	switch(globalSectionId){
		//Resumen General
		case '7':
			//Validar tipo de asociacion al inicio
			diasbledAccionesPartSocial();
			break;
		//Estructura de capital
		case '19':
			break;
		//Escritura constitutiva
		case '21':
			//validar duracion
			getFechaEscCons();
			selectValorNominalChangeEscCons();
			break;
		default:
			break;
	}
	
	if(globalSectionId == "7") {
		var posicion=document.getElementById('C1').options.selectedIndex; //posicion
		//alert(document.getElementById('C1').options[posicion].text); //valor
		var valorEmp = document.getElementById('C1').options[posicion].text;
		parent.frames['capHeader'].document.getElementById('idTitle').innerHTML = valorEmp;
		
		//alert(document.getElementById("C2016").value+" - "+globalSectionId);
		//document.getElementById("C2016").readOnly = true;
		
	}
	
	/*document.getElementById('C1').disabled = true;
	document.getElementById('C2').disabled = true;
	document.getElementById('C10').disabled = true;
	document.getElementById('C30').disabled = true;*/
	
	/*var empresa;
	empresa = document.getElementById('C1').value;
	//empresa = document.getElementsByName("C1").value;
	alert('ECM Empresa:  '+empresa);
	*/
	//ECM 05 AGOSTO 2015
	if(globalSectionId == "19") {
		var semaforo = document.getElementById("C47").value;
		if(semaforo == "button_green.png"){
			//alert('Eligio Verde');
			document.getElementById("C1035").style.display = 'none';
			document.getElementById("C1036").style.display = 'none';
			document.getElementById("C1037").style.display = 'none';
			document.getElementById("C1038").style.display = 'none';
			document.getElementById("C1039").style.display = 'none';
			document.getElementById("C1040").style.display = 'none';
			document.getElementById("C1041").style.display = 'none';
			document.getElementById("C1042").style.display = 'none';
			document.getElementById("C1043").style.display = 'none';
			document.getElementById("C1044").style.display = 'none';
			document.getElementById("C1045").style.display = 'none';
			document.getElementById("C1046").style.display = 'none';
			document.getElementById("C1047").style.display = 'none';
			//document.getElementById("C1048").style.display = 'none';
			
			//
			document.getElementById("nom_C1022").style.display = 'none';
			document.getElementById("ele_C1022").style.display = 'none';
			
			document.getElementById("im").title = 'Verde';
		}else if(semaforo == "0"){
			document.getElementById("C1035").style.display = 'none';
			document.getElementById("C1036").style.display = 'none';
			document.getElementById("C1037").style.display = 'none';
			document.getElementById("C1038").style.display = 'none';
			document.getElementById("C1039").style.display = 'none';
			document.getElementById("C1040").style.display = 'none';
			document.getElementById("C1041").style.display = 'none';
			document.getElementById("C1042").style.display = 'none';
			document.getElementById("C1043").style.display = 'none';
			document.getElementById("C1044").style.display = 'none';
			document.getElementById("C1045").style.display = 'none';
			document.getElementById("C1046").style.display = 'none';
			document.getElementById("C1047").style.display = 'none';
			//document.getElementById("C1048").style.display = 'none';
			
			document.getElementById("nom_C1022").style.display = 'none';
			document.getElementById("ele_C1022").style.display = 'none';
			
		}
	}
	
	if(globalSectionId == "7") {
		//Habilitar o deshabilitar textbox 
		var radios = document.getElementsByName("C22");
		if(radios[0].checked) {
		  document.getElementById("C23").disabled = true;  
	    }else  {
		  document.getElementById("C23").disabled = false;
		}
		var radios2 = document.getElementsByName("C11");
		if(radios2[0].checked) {
		  document.getElementById("C27").disabled = true;  
	    }else 
		  document.getElementById("C27").disabled = false;

	
		/*
		//ECM MONEDA
		var vValMoneda = document.getElementById('C21').value;
		document.getElementById('C1051').value = vValMoneda;
		document.getElementById('C65').value = vValMoneda;
		*/
		//JJAQ Carga para los combos pais y estado para la pestana Resumen General
		cambiaCat('C10','C12_13',511,'C12');
		//cambiaCat('C60','C59_13',558,'C59');


	}
	
	if(globalSectionId == "21") {
		//ECM 18 Marzo 2016  - Status Escritura Constitutiva.
		//cambiaCheckStatus(document.getElementById("C1024"));
		//aplicaStatusEscCons(document.getElementById("C1024"));
	}
	
	//ARGU 06/09/2016
	if(globalSectionId == "20") {
		if(document.getElementById("C2013").checked == false){
			document.getElementById("flexTable_9").style.display ="none";	
		}
		
	}

	//ECM 26 FEBRERO 2016 CAMBIA STATUS SEMAFOR GENERAL
});

/**
 * Seccion : Escritura Constitutiva
 * Descripción : Actualización Dinámica de los campos Notario, Licenciado y Entidad
 */
 function updateNotario (id_element) {
	var lic = null;
	var not = null;
	var de  = null;
	 
	 
    if(id_element== 'C54'){
		lic = document.getElementById('C54').value;
		not = null;
    }
    if(id_element== 'C55'){
	    lic = null;
	    not = document.getElementById('C55').value;
    }
	 
	var ajaxRequest = simpleAjaxUtil_getXmlHttpObject();     
		
    if (ajaxRequest==null) {alert ("Browser does not support HTTP Request");return;}

    var url = 'ajax/notarioValidation.jsp';
	var parameters = 	"lic=" + lic + "&not=" + not
						;
	
    //alert(url+"|"+parameters);
    ajaxRequest.open("post", url, true);
    ajaxRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    ajaxRequest.onreadystatechange=function(){
    
	    if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
		    var response = ajaxRequest.responseText;
		    
		    //alert(response);
		    
		    var values = response.split("|");
		    document.getElementById('C54').value = String(values[0]);
		    document.getElementById('C55').value = String(values[1]);
		    document.getElementById('C56').value = String(values[2]);
	      } 
    };    
    ajaxRequest.send(parameters);

	}

//ECM 30 Julio 2015
function closePerspective(){

	//var href = document.getElementById("btnCerrar").innerHTML = window.location.href;
	//var pathname = document.getElementById("btnCerrar").innerHTML = window.location.pathname;
	//var assign = window.location.assign("/DerechoCorporativo/jsp/home/menu.jsp");
	//alert(href+pathname);
	//alert(assign);

	//Si
	//window.location="/DerechoCorporativo/jsp/home/main.jsp";
	window.location="/DerechoCorporativo/faces/jsp/home/content.jsp";

}

//ECM 04 AGOSTO 2015

function sumaCapitales(){
	var suma1 = document.getElementById('C1028').value;
	suma1 = accounting.unformat(suma1);
	
	var suma2 = document.getElementById('C1029').value;
	suma2 = accounting.unformat(suma2);

	var suma3 = suma1 + suma2;
	
	suma3 = accounting.formatMoney(suma3);
	document.getElementById('C42').value = suma3;
	
	
}

//ECM 12 AGOSTO 2015
function getContactos(src){
	
	var parameters = "Nom="+src.value;
	var ajaxRequest = simpleAjaxUtil_getXmlHttpObject();
	if (ajaxRequest==null) {alert ("Browser does not support HTTP Request");return;}
	var url = 'ajax/getContacto.jsp';
	ajaxRequest.open("post", url, true);
	ajaxRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");

	ajaxRequest.onreadystatechange=function(){
        
	    if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
		    var response = ajaxRequest.responseText;
		    var values = response.split("|");

		    //document.getElementById('1__VAL_C2').selectedIndex = String(values[1]);
		    //COMBO '1__VAL_C2'
		    document.getElementById('1__VAL_C2').value = String(values[1]);
		    document.getElementById('1__VAL_C3').value = String(values[2]);
		    //document.getElementById('1__VAL_C4').value = String(values[3]);
		    document.getElementById('1__VAL_C5').value = String(values[4]);
		    //document.getElementById('C55').value = String(values[1]);
		    //document.getElementById('C56').value = String(values[2]);
	      } 
    };
    ajaxRequest.send(parameters);

}

//ECM 14 AGOSTO 2015
function getInfoLic(src, notario, ubicacion){
	var parameters = "IdCatVal="+src.value;
	var ajaxRequest = simpleAjaxUtil_getXmlHttpObject();
	if (ajaxRequest==null) {alert ("Browser does not support HTTP Request");return;}
	var url = 'ajax/getInfoLic.jsp';
	ajaxRequest.open("post", url, true);
	ajaxRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");

	ajaxRequest.onreadystatechange=function(){
        
	    if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
		    var response = ajaxRequest.responseText;
		    var values = response.split("|");
		    document.getElementById(notario).value = String(values[0]);
		    //document.getElementById(ubicacion).selectedIndex = String(values[1]);
		    document.getElementById(ubicacion).value = String(values[1]);
	    } 
    };
    ajaxRequest.send(parameters);
}

//ECM 14 AGOSTO 2015
function getInfoNotario(src, licenciado, ubicacion){
//		alert('ECM:  '+src.value);
	var parameters = "NotPub="+src.value;
	var ajaxRequest = simpleAjaxUtil_getXmlHttpObject();
	if (ajaxRequest==null) {alert ("Browser does not support HTTP Request");return;}
	var url = 'ajax/getInfoNotario.jsp';
	ajaxRequest.open("post", url, true);
	ajaxRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");

	ajaxRequest.onreadystatechange=function(){
	    if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
		    var response = ajaxRequest.responseText;
		    var values = response.split("|");
		    //document.getElementById(licenciado).selectedIndex = String(values[0]);
		    //document.getElementById(ubicacion).selectedIndex = String(values[1]);
		    document.getElementById(licenciado).value = String(values[0]);
		    document.getElementById(ubicacion).value = String(values[1]);
	    }
    };
    ajaxRequest.send(parameters);
}

function cambiaComboCapitalFijo(src){
		document.getElementById('C24').value = src.value; 
}

function cambiaComboCapitalVariable(src){
	document.getElementById('C25').value = src.value; 
}

//ECM 19 Agosto 2015
function showReview(){
	//alert('showReview:  ');
	//window.location="/DerechoCorporativo/faces/jsp/home/content.jsp";
	//window.location="/DerechoCorporativo/faces/jsp/captura/apoderados/showPreviewApoderados.jsp";
	/*
	window.open = ("/DerechoCorporativo/faces/jsp/captura/apoderados/showPreviewApoderados.jsp"
					,null
					,"height=200
					,width=400
					,status=yes
					,toolbar=no
					,menubar=no
					,location=no"
					);
	*/
}

function añadeValor(){
	//alert(document.getElementById('idGrupo').value);
	var x = document.getElementById("mySelect");
	//alert(x);
	var option = document.createElement("option");
	option.text = document.getElementById('idGrupo').value;
	x.add(option);

}

function getDocumentum(id){
	var doc = null;
	doc = document.getElementById(id).value;
	
	if (doc == null||doc =='null'||doc == ''){
		swal({ title: "Aviso",   
			   text: "El campo No. De Documento en DOCUMENTUM no puede estar vacío",   
			   type: "warning",  
			   confirmButtonText: "Ok" });
	}else if (doc.length != 37){
		swal({ title: "Aviso",   
			   text: "El formato del Campo No. de Documento en DOCUMENTUM es incorrecto",   
			   type: "warning",  
			   confirmButtonText: "Ok" });	
	}else{
    window.open("/DerechoCorporativo/faces/jsp/documentum/waitingPage.jsp?doc="+doc
					,null
					,"height=600,width=1100,status=yes,toolbar=no,menubar=no,location=no");
	}				

}

function getDocumentumDeTabla(noDocumentum){
	if(noDocumentum!=null && noDocumentum!=""){
		window.open("/DerechoCorporativo/faces/jsp/documentum/waitingPage.jsp?doc="+noDocumentum
				,null
				,"height=600,width=1100,status=yes,toolbar=no,menubar=no,location=no");			
	}else{
		swal({ title: "Aviso",   
			   text: "No. de Documento no puede estar vacío",   
			   type: "warning",  
			   confirmButtonText: "Ok" });
	}
}

/*$(document).ajaxSend(function() {
	  $('#ajaxloading').show();
	});*/
function disableTextboxFromRadio(radio,textbox){
  if(radio.value == 11100) {
	document.getElementById(textbox).disabled = false;
	document.getElementById(textbox).value = '';
  }else if (radio.value == 11101){
	document.getElementById(textbox).disabled = true;
	document.getElementById(textbox).value = 'N/A';
  }
}

function isNumberKey(evt) {
        var charCode = (evt.which) ? evt.which : event.keyCode;
        if (charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57)) {
            return false;
        } else {
            // If the number field already has . then don't allow to enter . again.
            if (evt.target.value.search(/\./) > -1 && charCode == 46) {
                return false;
            }
            return true;
        }
}


//ECM 05 Noviembre 2015 Formato Documento
function getMascaraDocumentum(pObjeto){
	
	//18 ENERO 2016
	var vSrc = pObjeto.value;
	vSrc = vSrc.replace(/ /g, ''); //Quitar espacios en cadena
	vSrc = vSrc.trim();	
	pObjeto.value = vSrc; 

	
	var vSeparador = '-';
	var vPatron =  new Array(3,3,3,4,4,5,2,2,3);
	var vNumerico = true;

	if(pObjeto.valant != pObjeto.value){
		val = pObjeto.value
		largo = val.length
		val = val.split(vSeparador)
		val2 = ''

		for(r=0;r<val.length;r++){
			val2 += val[r]	
		}

		if(vNumerico){
			for(z=0;z<val2.length;z++){
				if(isNaN(val2.charAt(z))){
					letra = new RegExp(val2.charAt(z),"g")
					val2 = val2.replace(letra,"")
				}
			}
		}

		val = ''
		val3 = new Array()

		for(s=0; s<vPatron.length; s++){
			val3[s] = val2.substring(0,vPatron[s])
			val2 = val2.substr(vPatron[s])
		}

		for(q=0;q<val3.length; q++){
			if(q ==0){
				val = val3[q]
			}
			else{
				if(val3[q] != ""){
					val += vSeparador + val3[q]
					}
			}
		}

		pObjeto.value = val
		pObjeto.valant = val

		}
}

//ICL 03122015
function getMascaraFecha(pObjeto){
	var vSeparador = '/';
	var vPatron =  new Array(2,2,4);
	var vNumerico = true;

	if(pObjeto.valant != pObjeto.value){
		val = pObjeto.value
		largo = val.length
		val = val.split(vSeparador)
		val2 = ''

		for(r=0;r<val.length;r++){
			val2 += val[r]	
		}

		if(vNumerico){
			for(z=0;z<val2.length;z++){
				if(isNaN(val2.charAt(z))){
					letra = new RegExp(val2.charAt(z),"g")
					val2 = val2.replace(letra,"")
				}
			}
		}

		val = ''
		val3 = new Array()

		for(s=0; s<vPatron.length; s++){
			val3[s] = val2.substring(0,vPatron[s])
			val2 = val2.substr(vPatron[s])
		}

		for(q=0;q<val3.length; q++){
			if(q ==0){
				val = val3[q]
			}
			else{
				if(val3[q] != ""){
					val += vSeparador + val3[q]
					}
			}
		}
		pObjeto.value = val
		pObjeto.valant = val

		}
}


function validarFecha(fecha){
	//con la expresion regular "/\D/g" se busca cualquier caracter que no sea un número y los borra con el replace
	var fechaString=fecha.value.replace(/\D/g,"");
	valFecha=/^\d*$/.test(fechaString);
	if(valFecha==true && fechaString!=""){
		var fechaArr = fecha.value.split('/');
		var dia = fechaArr[0];
		var mes = fechaArr[1];
		var aho = fechaArr[2];
		var plantilla = new Date(aho, mes - 1, dia);//mes empieza de cero Enero = 0
		// alert("entra");
		 if(fechaArr.length == 3){
		var longAho=aho.length;
		if(!plantilla || (plantilla.getFullYear() == aho && plantilla.getMonth() == mes -1 && plantilla.getDate() == dia && longAho>3)){
		 	//alert("Correcto");
		 	//Fecha correcta continuar
		 	return true;
		}else{
			swal({ title: "Aviso",   
				   text: "La fecha es incorrecta",   
				   type: "warning",  
				   confirmButtonText: "Ok" });
		 	fecha.value="";
		 	return false;
		}
		
	}else{
		swal({ title: "Aviso",   
			   text: "La fecha es incorrecta",   
			   type: "warning",  
			   confirmButtonText: "Ok" });
	 	fecha.value="";
	 	return false;
	}
	}else{
		swal({ title: "Aviso",   
			   text: "La fecha es incorrecta",   
			   type: "warning",  
			   confirmButtonText: "Ok" });
	 	fecha.value="";
	 	return false;
	}
}

var currentTabName;

function selectTabName(tabName, addSpryCode) {
	
	
	
	currentTabName = tabName;
	parent.frames['capHeader'].document.getElementById('spanTabName').innerHTML = tabName;
	
	
	if(currentTabName == 'Apoderados'){
		//alert('Apoderados');
		//var idEscr = document.getElementById("selectEscritura").value;
		//alert(idEscr);
		///////////selectEscritura.value = '0';
	    ///////////selectTipoPoder.value = '0';
        ///////////selectGrupoApo.value  = '0'; 
        //document.getElementById("selectEscritura").value = '0';
        //document.getElementById("selectTipoPoder").value = '0';
        //document.getElementById("SelectGrupoApo").value = '0';
      
        
        
        var ajaxRequest = simpleAjaxUtil_getXmlHttpObject();     
	    if (ajaxRequest==null) {alert ("Browser does not support HTTP Request");return;}
	
	    var parameters = "";
	
	    ajaxRequest.open("post",'../captura/apoderados/catEscrituras.jsp', true);
	    ajaxRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	    ajaxRequest.onreadystatechange=function(){
	    
		    if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
	
			    var response = ajaxRequest.responseText;
			    /*document.getElementById('selectEscrituraDiv').innerHTML = response;
	
			  	document.getElementById("selectEscritura").value = '0';
		        document.getElementById("selectTipoPoder").value = '0';
		        document.getElementById("SelectGrupoApo").value = '0';
		      */
			    
			      }
		    };    
		    ajaxRequest.send(parameters);
		
		//reloadEscrituras();
	}
	
	
	saveSpryCodeInSession(addSpryCode);
	saveTabNameInSession(tabName);
	
	
	
}

function selectSubTabName(subtabName) {
	
	parent.frames['capHeader'].document.getElementById('spanTabName').innerHTML =  subtabName;
	saveSubTabNameInSession(subtabName);
}
   
  
  
function saveSpryCodeInSession(addSpryCode) {

    var ajaxRequest = simpleAjaxUtil_getXmlHttpObject();     
	
    if (ajaxRequest==null) {alert ("Browser does not support HTTP Request");return;}
	
    var url = "saveSpryCodeInSession.jsp";
    
    var parameters = 	"addSpryCode=" + addSpryCode;
	
    ajaxRequest.open("post", url, true);
    ajaxRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    ajaxRequest.onreadystatechange=function(){
    
	    if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
	
		    var response = ajaxRequest.responseText;
		    //document.getElementById(divId).innerHTML = response;

		  } 
    };    
    ajaxRequest.send(parameters);
}



function saveTabNameInSession(tabName) {

    var ajaxRequest = simpleAjaxUtil_getXmlHttpObject();     
	
    if (ajaxRequest==null) {alert ("Browser does not support HTTP Request");return;}
	
    var url = "saveTabNameInSession.jsp";
    
    var parameters = 	"tabName=" + tabName;
	
    ajaxRequest.open("post", url, true);
    ajaxRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    ajaxRequest.onreadystatechange=function(){
    
	    if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
	
		    var response = ajaxRequest.responseText;
		    //document.getElementById(divId).innerHTML = response;

		  } 
    };    
    ajaxRequest.send(parameters);
}

function saveSubTabNameInSession(subTabName) {

    var ajaxRequest = simpleAjaxUtil_getXmlHttpObject();     
	
    if (ajaxRequest==null) {alert ("Browser does not support HTTP Request");return;}
	
    var url = "saveSubTabNameInSession.jsp";
    
    var parameters = 	"subTabName=" + subTabName;
	
    ajaxRequest.open("post", url, true);
    ajaxRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    ajaxRequest.onreadystatechange=function(){
    
	    if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
	
		    var response = ajaxRequest.responseText;
		    //document.getElementById(divId).innerHTML = response;

		  } 
    };    
    ajaxRequest.send(parameters);
}

   
function getMascaraHora(pObjeto){
	var vSeparador = ':';
	var vPatron =  new Array(2,2);
	var vNumerico = true;

	if(pObjeto.valant != pObjeto.value){
		val = pObjeto.value
		largo = val.length
		val = val.split(vSeparador)
		val2 = ''

		for(r=0;r<val.length;r++){
			val2 += val[r]	
		}

		if(vNumerico){
			for(z=0;z<val2.length;z++){
				if(isNaN(val2.charAt(z))){
					letra = new RegExp(val2.charAt(z),"g")
					val2 = val2.replace(letra,"")
				}
			}
		}

		val = ''
		val3 = new Array()

		for(s=0; s<vPatron.length; s++){
			val3[s] = val2.substring(0,vPatron[s])
			val2 = val2.substr(vPatron[s])
		}

		for(q=0;q<val3.length; q++){
			if(q ==0){
				val = val3[q]
			}
			else{
				if(val3[q] != ""){
					val += vSeparador + val3[q]
					}
			}
		}

		pObjeto.value = val
		pObjeto.valant = val

		}
}

//ECM 25 Enero 2016 Agregar mascara año.
function getFecYear(pObjeto){
	var vSeparador = '';
	var vPatron =  new Array(2,2);
	var vNumerico = true;

	if(pObjeto.valant != pObjeto.value){
		val = pObjeto.value
		largo = val.length
		val = val.split(vSeparador)
		val2 = ''

		for(r=0;r<val.length;r++){
			val2 += val[r]	
		}

		if(vNumerico){
			for(z=0;z<val2.length;z++){
				if(isNaN(val2.charAt(z))){
					letra = new RegExp(val2.charAt(z),"g")
					val2 = val2.replace(letra,"")
				}
			}
		}

		val = ''
		val3 = new Array()

		for(s=0; s<vPatron.length; s++){
			val3[s] = val2.substring(0,vPatron[s])
			val2 = val2.substr(vPatron[s])
		}

		for(q=0;q<val3.length; q++){
			if(q ==0){
				val = val3[q]
			}
			else{
				if(val3[q] != ""){
					val += vSeparador + val3[q]
					}
			}
		}

		pObjeto.value = val
		pObjeto.valant = val

		}
}


/*ECM 12 Enero 2016
 * JJAQ 01/04/2016 Modificado para la reutilización del cambio pais y estado
 * cmbPais: id del combo pais en html
 * divEstado: Id del div donde se encuentra el combo del Estado
 * id_add_campo_estado: Id en la base de datos 'DERCORP_ADD_CAMPO_TAB' del combo estado
 * codCampo_estado: Id en la base de datos 'DERCORP_ADD_CAMPO_TAB' del combo estado
 */
function cambiaCat(cmbPais,divEstado,id_add_campo_estado,codCampo_estado){
	
	//alert(cmbPais);
	var pais = document.getElementById(cmbPais).value;
	//alert(pais);
	//alert(divEstado);
	//alert(id_add_campo_estado);
	//alert(codCampo_estado);
	var ajaxRequest = simpleAjaxUtil_getXmlHttpObject();
    if (ajaxRequest==null) {alert ("Browser does not support HTTP Request");return;}

    var url = 'ajax/cambiaCat.jsp';
	var parameters = "pais="+pais+"&idCampo="+id_add_campo_estado+"&codCampo="+codCampo_estado;

    ajaxRequest.open("post", url, true);
    ajaxRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    ajaxRequest.onreadystatechange=function(){

	    if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){	
		    var response = ajaxRequest.responseText;
		    document.getElementById(divEstado).innerHTML = response;
	      }
    };
    ajaxRequest.send(parameters);
    
    
    //cambiaCatEscCons();

}

	

//ECM 14 Enero 2016
function cambiaCatEscCons(){
	
	var pais = document.getElementById('C10').value;

	var ajaxRequest = simpleAjaxUtil_getXmlHttpObject();
    if (ajaxRequest==null) {alert ("Browser does not support HTTP Request");return;}

    var url = 'ajax/cambiaCat.jsp';

	var parameters = "pais="+pais+"&idCampo=555&codCampo=C56";

    ajaxRequest.open("post", url, true);
    ajaxRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    ajaxRequest.onreadystatechange=function(){

	    if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
		    var response2 = ajaxRequest.responseText;
		    document.getElementById('C56_13').innerHTML = response2;
	      }
    };
    ajaxRequest.send(parameters);
	
}

function uncheckme(element){
	  var ele = document.getElementsByName(element.name);
	   for(var i=0;i<ele.length;i++){
	      ele[i].checked = false;
	   }
	   
	   ele[ele.length -1].checked = true;

}


function cambiaCheckStatus(element){
	//alert('Check:  '+element.value+'  '+element.checked);
	if(element.value == 'C1135'){
		if(element.checked){
			
			document.getElementById("C1140").checked = false;
			document.getElementById("C1145").checked = false;
			document.getElementById("C1150").checked = false;
			document.getElementById("C1155").checked = false;
			document.getElementById("C1160").checked = false;
			////////////////////////////////////////////////

		/*	document.getElementById("C1135").value = 'Si';
			document.getElementById("C1140").value = 'No';
			document.getElementById("C1145").value = 'No';
			document.getElementById("C1150").value = 'No';
			document.getElementById("C1155").value = 'No';
			document.getElementById("C1160").value = 'No';*/

		}
	}else 	if(element.value == 'C1140'){
		        if(element.checked){
		            document.getElementById("C1135").checked = false;
		            document.getElementById("C1145").checked = false;
		            document.getElementById("C1150").checked = false;
		            document.getElementById("C1155").checked = false;
		            document.getElementById("C1160").checked = false;
                }
	}else 	if(element.value == 'C1145'){
		        if(element.checked){
		            document.getElementById("C1135").checked = false;
		            document.getElementById("C1140").checked = false;
		            document.getElementById("C1150").checked = false;
		            document.getElementById("C1155").checked = false;
		            document.getElementById("C1160").checked = false;
		        }
    }else 	if(element.value == 'C1150'){
		        if(element.checked){
		            document.getElementById("C1135").checked = false;
		            document.getElementById("C1140").checked = false;
		            document.getElementById("C1145").checked = false;
		            document.getElementById("C1155").checked = false;
		            document.getElementById("C1160").checked = false;
		        }
    }else 	if(element.value == 'C1155'){
		        if(element.checked){
		            document.getElementById("C1135").checked = false;
		            document.getElementById("C1140").checked = false;
		            document.getElementById("C1145").checked = false;
		            document.getElementById("C1150").checked = false;
		            document.getElementById("C1160").checked = false;
		        }
    }else 	if(element.value == 'C1160'){
		        if(element.checked){
		            document.getElementById("C1135").checked = false;
		            document.getElementById("C1140").checked = false;
		            document.getElementById("C1145").checked = false;
		            document.getElementById("C1150").checked = false;
		            document.getElementById("C1155").checked = false;
		        }
	}
}

//ECM 13 Abril 2016 Aplica Status en Escritura Constitutiva
function aplicaStatusEscCons(element){
	if(element.value == 'C1024'){
        if(element.checked){
        	
        	//$('#tbl_stat_5 tr').removeClass("tableRow2");  OK
        	//$('#tbl_stat_5 tr').addClass("tableRow2");  OK
        	$('#tbl_stat_5 tr').filter(':nth-child(6)').addClass("tableRow2");
        	$('#tbl_stat_5 tr').filter(':nth-child(8)').addClass("tableRow2");
        	$('#tbl_stat_5 tr').filter(':nth-child(10)').addClass("tableRow2");
        	$('#tbl_stat_5 tr').filter(':nth-child(12)').addClass("tableRow2");
        	$('#tbl_stat_5 tr').filter(':nth-child(14)').addClass("tableRow2");
        	$('#tbl_stat_5 tr').filter(':nth-child(16)').addClass("tableRow2");
        	$('#tbl_stat_5 tr').filter(':nth-child(18)').addClass("tableRow2");
        	$('#tbl_stat_5 tr').filter(':nth-child(20)').addClass("tableRow2");
        	$('#tbl_stat_5 tr').filter(':nth-child(22)').addClass("tableRow2");

        	//var showMe = $("#Stat_3").val();
        	//alert('showMe: '+showMe);

        	//var showMe = $("#C1137").val();
        	//alert('showMe: '+showMe);

        	document.getElementById("nom_C1133").style.display = 'block';
        	document.getElementById("nom_C1135").style.display = 'block';
        	document.getElementById("nom_C1137").style.display = 'block';
        	document.getElementById("nom_C1138").style.display = 'block';
        	document.getElementById("nom_C1140").style.display = 'block';
        	document.getElementById("nom_C1142").style.display = 'block';
        	document.getElementById("nom_C1143").style.display = 'block';
        	document.getElementById("nom_C1145").style.display = 'block';
        	document.getElementById("nom_C1147").style.display = 'block';
        	document.getElementById("nom_C1148").style.display = 'block';
        	document.getElementById("nom_C1150").style.display = 'block';
        	document.getElementById("nom_C1152").style.display = 'block';
        	document.getElementById("nom_C1153").style.display = 'block';
        	document.getElementById("nom_C1155").style.display = 'block';
        	document.getElementById("nom_C1157").style.display = 'block';
        	document.getElementById("nom_C1158").style.display = 'block';
        	document.getElementById("nom_C1160").style.display = 'block';
        	document.getElementById("nom_C1162").style.display = 'block';
        	document.getElementById("nom_C1163").style.display = 'block';

        	//
        	document.getElementById("nom2_C1133").style.display = 'block';
        	document.getElementById("nom2_C1135").style.display = 'block';
        	document.getElementById("C1137_59").style.display = 'block';
        	document.getElementById("nom2_C1138").style.display = 'block';
        	document.getElementById("nom2_C1140").style.display = 'block';
        	document.getElementById("C1142_59").style.display = 'block';
        	document.getElementById("nom2_C1143").style.display = 'block';
        	document.getElementById("nom2_C1145").style.display = 'block';
        	document.getElementById("C1147_59").style.display = 'block';
        	document.getElementById("nom2_C1148").style.display = 'block';
        	document.getElementById("nom2_C1150").style.display = 'block';
        	document.getElementById("C1152_59").style.display = 'block';
        	document.getElementById("nom2_C1153").style.display = 'block';
        	document.getElementById("nom2_C1155").style.display = 'block';
        	document.getElementById("C1157_59").style.display = 'block';
        	document.getElementById("nom2_C1158").style.display = 'block';
        	document.getElementById("nom2_C1160").style.display = 'block';
        	document.getElementById("C1162_59").style.display = 'block';
        	document.getElementById("nom2_C1163").style.display = 'block';

        }else{

        	//$('#tbl_stat_5 tr').addClass("tableRow2"); OK
        	//$('#tbl_stat_5 tr.tableRow2').removeClass("tableRow2"); ok
        	$('#tbl_stat_5 tr.tableRow2').filter(':nth-child(6)').removeClass("tableRow2");
        	$('#tbl_stat_5 tr.tableRow2').filter(':nth-child(8)').removeClass("tableRow2");
        	$('#tbl_stat_5 tr.tableRow2').filter(':nth-child(10)').removeClass("tableRow2");
        	$('#tbl_stat_5 tr.tableRow2').filter(':nth-child(12)').removeClass("tableRow2");
        	$('#tbl_stat_5 tr.tableRow2').filter(':nth-child(14)').removeClass("tableRow2");
        	$('#tbl_stat_5 tr.tableRow2').filter(':nth-child(16)').removeClass("tableRow2");
        	$('#tbl_stat_5 tr.tableRow2').filter(':nth-child(18)').removeClass("tableRow2");
        	$('#tbl_stat_5 tr.tableRow2').filter(':nth-child(20)').removeClass("tableRow2");
        	$('#tbl_stat_5 tr.tableRow2').filter(':nth-child(22)').removeClass("tableRow2");

        	document.getElementById("nom_C1133").style.display = 'none';
        	document.getElementById("nom_C1135").style.display = 'none';
        	document.getElementById("nom_C1137").style.display = 'none';
        	document.getElementById("nom_C1138").style.display = 'none';
        	document.getElementById("nom_C1140").style.display = 'none';
        	document.getElementById("nom_C1142").style.display = 'none';
        	document.getElementById("nom_C1143").style.display = 'none';
        	document.getElementById("nom_C1145").style.display = 'none';
        	document.getElementById("nom_C1147").style.display = 'none';
        	document.getElementById("nom_C1148").style.display = 'none';
        	document.getElementById("nom_C1150").style.display = 'none';
        	document.getElementById("nom_C1152").style.display = 'none';
        	document.getElementById("nom_C1153").style.display = 'none';
        	document.getElementById("nom_C1155").style.display = 'none';
        	document.getElementById("nom_C1157").style.display = 'none';
        	document.getElementById("nom_C1158").style.display = 'none';
        	document.getElementById("nom_C1160").style.display = 'none';
        	document.getElementById("nom_C1162").style.display = 'none';
        	document.getElementById("nom_C1163").style.display = 'none';

        	//
        	document.getElementById("nom2_C1133").style.display = 'none';
        	document.getElementById("nom2_C1135").style.display = 'none';
        	document.getElementById("C1137_59").style.display = 'none';
        	document.getElementById("nom2_C1138").style.display = 'none';
        	document.getElementById("nom2_C1140").style.display = 'none';
        	document.getElementById("C1142_59").style.display = 'none';
        	document.getElementById("nom2_C1143").style.display = 'none';
        	document.getElementById("nom2_C1145").style.display = 'none';
        	document.getElementById("C1147_59").style.display = 'none';
        	document.getElementById("nom2_C1148").style.display = 'none';
        	document.getElementById("nom2_C1150").style.display = 'none';
        	document.getElementById("C1152_59").style.display = 'none';
        	document.getElementById("nom2_C1153").style.display = 'none';
        	document.getElementById("nom2_C1155").style.display = 'none';
        	document.getElementById("C1157_59").style.display = 'none';
        	document.getElementById("nom2_C1158").style.display = 'none';
        	document.getElementById("nom2_C1160").style.display = 'none';
        	document.getElementById("C1162_59").style.display = 'none';
        	document.getElementById("nom2_C1163").style.display = 'none';

        }
	}

}

//ECM 03 Marzo 2016
function checkDec(el){

	var vRespLim = document.getElementById("C18").value;

	 if(vRespLim == 724){//Sociedad de Responsabilidad Límitada de capital variable.
		 var ex = /^[0-9]+\.?[0-9]*$/;
		 if(ex.test(el.value)==false){
		 	el.value = el.value.substring(0,el.value.length - 1);
		 }	 
	 }else{
		 if (/[^0-9]/.test(el.value[el.value.length-1]))
			 el.value=el.value.slice(0,el.value.length-1)

	 }

}

jQuery(function($){
	
	$("#C52").attr('maxlength','6');
	$("#C52").numeric();

});

function quitarPunto(element){
	var carac = element.value;
	if(carac.indexOf('.') > -1){
		carac = carac.replace(/\./g, '');
	}
	element.value = carac;
}

function maskEsc(valCampo){
	if(valCampo != null){
		var val2 = valCampo.value;
		$(valCampo).val(commaSeparateNumber(val2));
	}

}

function commaSeparateNumber(val){

    while (/(\d+)(\d{3})/.test(val.toString())){
      val = val.toString().replace(/(\d+)(\d{3})/, '$1'+','+'$2');
    }
    return val;
}



function loadDefaultStatusValorNominalMonedas() {
	
	if(globalSectionId == "7") {	
		selectValorNominalChange();
	}
	
}

//ULR 25 para desabilitar campos Acciones partes sociales
function diasbledAccionesPartSocial(){
	var selectTipoSoc = $("#C18 option:selected").text().indexOf("Asociación Civil");
	if(selectTipoSoc>=0){
		document.getElementById('C3404').disabled=true;
		document.getElementById('C3303').disabled=true;
		document.getElementById('C3505').disabled=true;
		document.getElementById('C3202').disabled=true;
		document.getElementById('C3101').disabled=true;
		document.getElementById('C20').disabled=true;
		//document.getElementById('C1076').disabled=true;
		//document.getElementById('C1077').disabled=true;
		document.getElementById('C21').disabled=true;
		document.getElementById('C22').disabled=true;
		document.getElementById('C23').disabled=true;
		document.getElementById('C24').disabled=true;
		document.getElementById('C25').disabled=true;
		document.getElementById('C26').disabled=true;
		document.getElementById('C27').disabled=true;
		document.getElementById('C1066').disabled=true;
	}else{
		document.getElementById('C3404').disabled=false;
		document.getElementById('C3303').disabled=false;
		document.getElementById('C3505').disabled=false;
		document.getElementById('C3202').disabled=false;
		document.getElementById('C3101').disabled=false;
		document.getElementById('C20').disabled=false;
		//document.getElementById('C1076').disabled=false;
		//document.getElementById('C1077').disabled=false;
		document.getElementById('C21').disabled=false;
		document.getElementById('C22').disabled=false;
		document.getElementById('C23').disabled=false;
		document.getElementById('C24').disabled=false;
		document.getElementById('C25').disabled=false;
		document.getElementById('C26').disabled=false;
		document.getElementById('C27').disabled=false;
		document.getElementById('C1066').disabled=false;
	}
}
//01-02-2017 ULR para validar el valor nominal de escritura constitutiva (se modifico con la nueva peticion de Azucena)
function selectValorNominalChangeEscCons(){
	var selectValorNominal = $('#C64 option:selected');
	
	var seleccione = 0;
	var sin_expresion_nominal = 11111;
	var valor_desigual = 14540;
	
	//Tiene Valor Nominal
	if(selectValorNominal.val() != seleccione 
			&& selectValorNominal.text().indexOf('N/A')!=0
			//&& selectValorNominal.val()!=sin_expresion_nominal
			) {
		//muestra la moneda del valor nominal
		$('#C65').css("display","inline");
		//ocultar el valor teorico nominal
		//$('#C1088').css("display","none");
		//$('#C1089').css("display","none");
		//resetea el valor teorico nominal
		//$('#C1088').val(seleccione);
		//$('#C1089').val(seleccione);
	}else{
		//muestra solo el campo de valor nominal
		$('#C64').css("display","inline");
		//oculta la moneda del valor nominal
		$('#C65').css("display","none");
		//ocultar el valor teorico nominal
		//$('#C1088').css("display","none");
		//$('#C1089').css("display","none");
		//resetea el valor teorico nominal
		//$('#C1088').val(seleccione);
		//$('#C1089').val(seleccione);
	}
}

//03-02-2017 ULR para validar el valor teorico nominal de escritura constitutiva 
function selectValorTeoricoNominalChangeEscCons(){
	var selectValorTeoricoNominal = $('#C1088 option:selected');
	
	var seleccione = 0;
	var sin_expresion_nominal = 11111;
	var valor_desigual = 14540;
	
	//Tiene Valor Teorico Nominal
	 if(selectValorTeoricoNominal.val() != seleccione 
			&& selectValorTeoricoNominal.text().indexOf('N/A')!=0) {
		//muestra la moneda del valor teorico nominal
		$('#C1089').css("display","inline");
	} else {
		//muestra el valor teorico nominal
		$('#C1088').css("display","inline");
		$('#C1089').css("display","none");
		//resetea la moneda del valor teorico nominal
		$('#C1089').val(seleccione);
	}
}

function selectValorNominalChange() {
	
	var selectValorNominal = document.getElementById('C20');
	var selectValorNominalMoneda = document.getElementById('C21');
	//var selectValorTeoricoNominal = document.getElementById('C1076');
	//var selectValorTeoricoNominalMoneda = document.getElementById('C1077');
	
	
	var seleccione = 0;
	var sin_expresion_nominal = 11111;
	var valor_desigual = 14540;
	var n_a = 17491;
	
	/* 
	
	//Tiene Valor Nominal
	if(selectValorNominal.value != seleccione 
			&& selectValorNominal.value != sin_expresion_nominal
			&& selectValorNominal.value != valor_desigual) {
		
		selectValorNominalMoneda.disabled = false;
		
		selectValorTeoricoNominal.disabled = true;
		selectValorTeoricoNominalMoneda.disabled = true;
		
		selectValorTeoricoNominal.value = seleccione;
		selectValorTeoricoNominalMoneda.value = seleccione;
	} else {

		selectValorNominalMoneda.disabled = true;
		selectValorTeoricoNominal.disabled = false;
		//selectValorTeoricoNominalMoneda.disabled = false;
		
		selectValorNominalMoneda.value = seleccione; 
	}
	 */
	 
	 

	
	//Tiene Valor Nominal
	if(selectValorNominal.value != seleccione 
			//&& selectValorNominal.value != valor_desigual
			//&& selectValorNominal.value != sin_expresion_nominal
			&& selectValorNominal.options[selectValorNominal.selectedIndex].text != 'N/A') {
		
		selectValorNominalMoneda.style.display = '';
		
		//selectValorTeoricoNominal.style.display = 'none';
		//selectValorTeoricoNominalMoneda.style.display = 'none';
		
		//selectValorTeoricoNominal.value = seleccione;
		//selectValorTeoricoNominalMoneda.value = seleccione;
	} else{
		//oculta la moneda del valor nominal
		selectValorNominalMoneda.style.display = 'none';
		//ocultar el valor teorico nominal
		//selectValorTeoricoNominal.style.display = 'none';
		//selectValorTeoricoNominalMoneda.style.display = 'none';
		//resetea el valor teorico nominal
		//selectValorTeoricoNominal.value = seleccione;
		//selectValorTeoricoNominalMoneda.value = seleccione;
	}
	 
	
	
}





function selectValorTeoricoNominalChange() {
	
	var selectValorNominal = document.getElementById('C20');
	var selectValorNominalMoneda = document.getElementById('C21');
	var selectValorTeoricoNominal = document.getElementById('C1076');
	var selectValorTeoricoNominalMoneda = document.getElementById('C1077');
	
	
	var seleccione = 0;
	var sin_expresion_nominal = 11111;
	var valor_desigual = 14540;
	var n_a = 17491;
	
	if(selectValorTeoricoNominal.value != seleccione 
			/*&& selectValorTeoricoNominal.value != sin_expresion_nominal
			&& selectValorTeoricoNominal.value != valor_desigual*/
			&& selectValorTeoricoNominal.options[selectValorTeoricoNominal.selectedIndex].text != 'N/A') {
		
		selectValorTeoricoNominalMoneda.style.display = '';
		
	} else {

		selectValorTeoricoNominalMoneda.style.display = 'none';
		selectValorTeoricoNominal.style.display = '';
		selectValorTeoricoNominalMoneda.value = seleccione;
	}

	
	
	
}

	// ECM 20 Septiembre 2016 Resumen General - Información General
	// Agregar metodo de funcionalidad al campo Domicilio Comercial
	function getTelefono(Obj){
		var idDomCom = Obj.value;
		var ajaxRequest = simpleAjaxUtil_getXmlHttpObject();     

	    if (ajaxRequest==null) {alert ("Browser does not support HTTP Request");return;}

	    var url = 'ajax/getTelefono.jsp';
		var parameters = 'idDomCom='+idDomCom;

	    ajaxRequest.open("post", url, true);
	    ajaxRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	    ajaxRequest.onreadystatechange=function(){

		    if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
			    var response = ajaxRequest.responseText;

			    document.getElementById('C2016').value = response;
		      } 
	    };    
	    ajaxRequest.send(parameters);

		}
function quitarApostrofe(element){
	
		var carac = element.value;
	
		if(carac.indexOf("'") > -1){
			
			//carac = carac.replace(/\'/g,"\\'");
			carac = carac.replace(/\'/g, '');
		}
		element.value = carac;
	}	
	


</script>
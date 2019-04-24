

/**
 * 
 */
function simpleAjaxUtil_getXmlHttpObject() {
	
	var xmlHttp=null;
	
	try {
 		// Firefox, Opera 8.0+, Safari
		xmlHttp=new XMLHttpRequest();
	} catch (e) {
		//Internet Explorer
		try {
			xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
		}
	}
	
	return xmlHttp;
	
	
	/** Implementaci—n Alternativa */
	
	/*
		var peticion;
	    if (window.ActiveXObject)
	    	peticion=new ActiveXObject("Microsoft.XMLHTTP");
	    else if((window.XMLHttpRequest) || (typeof XMLHttpRequest)!=undefined)
		  	peticion=new XMLHttpRequest();
	  	else
			alert("Su navegador no tiene soporte para AJAX");
	 */
}

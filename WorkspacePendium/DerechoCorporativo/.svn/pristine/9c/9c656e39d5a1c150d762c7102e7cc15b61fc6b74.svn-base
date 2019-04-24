

var globalSearchEscritApodConsulta = '';


function setGlobalSearchEscritApodConsulta(thisValue) {
	
	globalSearchEscritApodConsulta = thisValue;
}




function loadAjaxPage(url, divId) {
	

    var ajaxRequest = simpleAjaxUtil_getXmlHttpObject();     
	
    if (ajaxRequest==null) {alert ("Browser does not support HTTP Request");return;}
	
	//var parameters = 	"";
    var parameters = 	"paramEscritura=" + globalSearchEscritApodConsulta;
	
    
    

    ajaxRequest.open("post", url, true);
    ajaxRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    ajaxRequest.onreadystatechange=function(){
    
	    if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
	
		
		    var response = ajaxRequest.responseText;
		    document.getElementById(divId).innerHTML = response;		    
		    /*Se carga la funcion que esta en la etiqueta <CHECK_IN> para cargar el modulo de consultas*/ 
		    var checkin = "";
		    if(response.indexOf('<CHECK_IN style="display:none">')>=0){
		    	checkin = response.substring( response.indexOf('<CHECK_IN style="display:none">')+31, response.indexOf('</CHECK_IN>'));		       
		        try{
			    	var w = $(window);
			    	window[checkin]();		   		    			        
		        }
		        catch(e){
		        	var msg = e;
		        	alert(msg);
		        }
		      }
		    		    
		    if(url == "apoderados/apoderados.jsp"
		    	|| url == "apoderados/apoderadosConsulta.jsp"
		    	) {
		    	
		    	var TabbedPanels2 = new Spry.Widget.TabbedPanels("TabbedPanels2");
		    	
		    	//selectSubTabName(document.getElementById('apoderadosHiddenFirstTabName').value);
		    	
		    }
		    
		    
		    
		    if(url == "reformas/reformas.jsp"
		    	|| url == "reformas/reformasConsulta.jsp"
		    	//|| url == "reformas/reformasPrint.jsp"
		    	) {
		    	
		    	if(url == "reformas/reformas.jsp"
			    	|| url == "reformas/reformasConsulta.jsp"
			    	) {
		    	
		    		var TabbedPanels3 = new Spry.Widget.TabbedPanels("TabbedPanels3");
		    	}
		    	
		    	
		    	loadFlexTabIni('20');
				loadFlexTabIni('21');
				loadFlexTabIni('22');
				loadFlexTabIni('23');
				loadFlexTabIni('27');
				loadFlexTabIni('28');
				loadFlexTabIni('29');
				loadFlexTabIni('30');
				loadFlexTabIni('31');
				loadFlexTabIni('32');
				loadFlexTabIni('33');
				loadFlexTabIni('34');
				loadFlexTabIni('35');
				loadFlexTabIni('38');
				loadFlexTabIni('41');
				
				//alert(document.getElementById('hiddenFirstTabName').value);
				//selectSubTabName(document.getElementById('reformasHiddenFirstTabName').value);
		    }
		  } 
    };    
    ajaxRequest.send(parameters);
}



<script type="text/javascript">




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
	
	parent.frames['capHeader'].document.getElementById('spanTabName').innerHTML = currentTabName + "<br>" + subtabName;
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


function getTabNameInSession(tabName) {
	
    var ajaxRequest = simpleAjaxUtil_getXmlHttpObject();     
	
    if (ajaxRequest==null) {alert ("Browser does not support HTTP Request");return;}
	
    var url = "getTabNameInSession.jsp";
    
    var parameters = 	"";
	
    ajaxRequest.open("post", url, true);
    ajaxRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    ajaxRequest.onreadystatechange=function(){
    
	    if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
	
		    var response = ajaxRequest.responseText;
		    //document.getElementById(divId).innerHTML = response;
		    currentTabName = response;
		  } 
    };    
    ajaxRequest.send(parameters);
}



getTabNameInSession();


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














</script>
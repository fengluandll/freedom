

<html>
<head>

<script type="text/javascript"
	src="<%=request.getContextPath() %>/js/ajax/simpleAjaxUtil.js"></script>
<script type="text/javascript">


function updateOutputAjax() {
	

    var ajaxRequest = simpleAjaxUtil_getXmlHttpObject();     
    if (ajaxRequest==null) {alert ("Browser does not support HTTP Request");return;}
	
    var url = '<%=request.getContextPath() %>/jsp/test/ajax/time.jsp';
	//var parameters = "coverageId="+coverageId+"&personId="+personId+"&transactionId="+transactionId.value+"&entityId="+entityId+"&bandera="+bandera;
	var parameters = "";
    
    ajaxRequest.open("post", url, true);
    ajaxRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    ajaxRequest.onreadystatechange=function(){
    
	    if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){

		    var response = ajaxRequest.responseText;
		    //var responseArray = response.split("_");
		    //var responseElement_1 = responseArray[0];
	    	
		    output.innerHTML = response;
	      }
    };    
    ajaxRequest.send(parameters);
}

</script>


</head>
<body>

	<input type="button" onclick="updateOutputAjax()"
		value="Launch Ajax Test">

	<div id="output"></div>


</body>
</html>
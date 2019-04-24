
<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript"
	src="<%=request.getContextPath() %>/js/ajax/simpleAjaxUtil.js"></script>
<script type="text/javascript">


function performSearch(filter) {
	

    var ajaxRequest = simpleAjaxUtil_getXmlHttpObject();     
    if (ajaxRequest==null) {alert ("Browser does not support HTTP Request");return;}
	
    var url = '<%=request.getContextPath() %>/jsp/test/ajax/iSearchData.jsp';
	var parameters = "filter="+filter;

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


	<input type="text" onkeyup="performSearch(this.value)">

	<div id="output"></div>


</body>
</html>

<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>


<script type="text/javascript" src="<c:out value="${applicationBean.contextPath}"/>/js/jquery/jquery-2.1.4.min.js"></script>



<script type="text/javascript" src="<c:out value="${applicationBean.contextPath}"/>/js/ajax/simpleAjaxUtil.js"></script>


<script src="<c:out value="${applicationBean.contextPath}"/>/js/multiSelect/jquery.multiple.select.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/multiSelect/multiple-select.css">




<script type="text/javascript">

	function storeMultipleValues(a,b) {


		alert('storeMultipleValues');
		
		
	}

	function loadAll() {
		
		loadDivFuncTest();
		loadDivUnitTest();
		loadScriptFuncTest();
		loadScriptUnitTest();
	}
	
	function loadDivFuncTest() {
		
	    var ajaxRequest = simpleAjaxUtil_getXmlHttpObject();     
		
	    if (ajaxRequest==null) {alert ("Browser does not support HTTP Request");return;}
		
	    //var url = 'sampleTestForm.jsp';
	    //
	   	var url = 'ajax/flexTableForm.jsp';
		var parameters = 	"params=24-0";
		

	    ajaxRequest.open("post", url, true);
	    ajaxRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	    ajaxRequest.onreadystatechange=function(){
	    
		    if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){

			    var response = ajaxRequest.responseText;

			    document.getElementById('funcTest').innerHTML = response;
			    
			    document.getElementById('funcTest').style.display = '';
			    
			    txtFormFuncTest.value = response;
			    
			    
			    $.getScript('ajax/flexTableFormScript.jsp?params=24-0'); 
			    // params
		      } 
	    };    
	    ajaxRequest.send(parameters);
	}

	
	
	
	function loadDivUnitTest() {
		
	    var ajaxRequest = simpleAjaxUtil_getXmlHttpObject();     
		
	    if (ajaxRequest==null) {alert ("Browser does not support HTTP Request");return;}
		
	    var url = 'sampleTestForm.jsp';
	    //
	   	//var url = 'ajax/flexTableForm.jsp';
		var parameters = 	"params=24-0";
		

	    ajaxRequest.open("post", url, true);
	    ajaxRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	    ajaxRequest.onreadystatechange=function(){
	    
		    if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){

			    var response = ajaxRequest.responseText;

			    document.getElementById('unitTest').innerHTML = response;
			    
			    
			    txtFormUnitTest.value = response;
			    
			    $.getScript('sampleTestScript.jsp');
			    //$.getScript('ajax/flexTableFormScript.jsp?params=' + params);
		      } 
	    };    
	    ajaxRequest.send(parameters);
	}

	
	



	function loadScriptFuncTest() {
		
	    var ajaxRequest = simpleAjaxUtil_getXmlHttpObject();     
		
	    if (ajaxRequest==null) {alert ("Browser does not support HTTP Request");return;}
		
	    //var url = 'sampleTestForm.jsp';
	    //
	   	var url = 'ajax/flexTableFormScript.jsp';
		var parameters = 	"params=24-0";
		

	    ajaxRequest.open("post", url, true);
	    ajaxRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	    ajaxRequest.onreadystatechange=function(){
	    
		    if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){

			    var response = ajaxRequest.responseText;

			    //document.getElementById('funcTest').innerHTML = response;
			    
			    txtScriptFuncTest.value = response;
			    
			    //$.getScript('sampleTestScript.jsp');
			    ///$.getScript('ajax/flexTableFormScript.jsp?params=24-0'); // params
		      } 
	    };    
	    ajaxRequest.send(parameters);
	}

	
	
	
	function loadScriptUnitTest() {
		
	    var ajaxRequest = simpleAjaxUtil_getXmlHttpObject();     
		
	    if (ajaxRequest==null) {alert ("Browser does not support HTTP Request");return;}
		
	    var url = 'sampleTestScript.jsp';
	    //
	   	//var url = 'ajax/flexTableForm.jsp';
		var parameters = 	"params=24-0";
		

	    ajaxRequest.open("post", url, true);
	    ajaxRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	    ajaxRequest.onreadystatechange=function(){
	    
		    if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){

			    var response = ajaxRequest.responseText;

			    //document.getElementById('unitTest').innerHTML = response;
			    
			    
			    txtScriptUnitTest.value = response;
			    
			    //$.getScript('sampleTestScript.jsp');
			    //$.getScript('ajax/flexTableFormScript.jsp?params=' + params);
		      } 
	    };    
	    ajaxRequest.send(parameters);
	}

	
	
</script>



</head>
<body>

<input type="button" onclick="loadAll()">

<table width="100%">
	<tr>
		<td width="50%">Unit Test</td>
		<td>Functional Test</td>
	</tr>
	<tr>
		<td><div id="unitTest"></div></td>
		<td><div id="funcTest"></div></td>
	</tr>
	<tr>
		<td>Form - Unit Test</td>
		<td>Form - Functional Test</td>
	</tr>
	<tr>
		<td><textarea id="txtFormUnitTest" rows="10" cols="50"></textarea></td>
		<td><textarea id="txtFormFuncTest" rows="10" cols="50"></textarea></td>
	</tr>
	
	<tr>
		<td>Script - Unit Test</td>
		<td>Script - Functional Test</td>
	</tr>
	<tr>
		<td><textarea id="txtScriptUnitTest" rows="10" cols="50"></textarea></td>
		<td><textarea id="txtScriptFuncTest" rows="10" cols="50"></textarea></td>
	</tr>
	
	
</table>





</body>
</html>
<%@page import="mx.com.televisa.derechocorporativo.modules.reportsFlex.Field"%>
<%@page import="mx.com.televisa.derechocorporativo.modules.flextabs.FlexColumn"%>
<%@page import="java.util.List"%>
<%@page import="mx.com.televisa.derechocorporativo.model.Catalog"%>
<%@page import="mx.com.televisa.derechocorporativo.model.CatalogElement,
				mx.com.televisa.derechocorporativo.bean.SessionBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Seleccionar</title>

<%@include file="/css/kaz-styles.jsp"%>

<script type="text/javascript" src="<%= request.getContextPath() %>/js/ajax/simpleAjaxUtil.js"></script>

<%



	String flexReportFieldId = request.getParameter("flexReportFieldId");
	String flexTableId = request.getParameter("flexTableId");

	//String currentValue = request.getParameter("currentValue");
	
	Field field = Field.getFieldById(flexReportFieldId);
	
	String currentIds = field.getAtributo1();

	/*
	String catalogId = request.getParameter("catalogId");
	String targetIds = "'" + request.getParameter("targetIds") + "'";
	String targetNames = "'" + request.getParameter("targetNames") + "'";
	String namesProperty = request.getParameter("namesProperty");
	String currentValue = request.getParameter("currentValue");
	
	String updateFieldCode = request.getParameter("updateFieldCode");
	
	
	if(namesProperty == null || namesProperty.equals("")) {
		
		namesProperty = "value";
	}
	*/
	
%>

<script type="text/javascript">

	/*
	function replaceAll(str, find, replace) {
	
		return str.replace(new RegExp(find, 'g'), replace);
	}*/

	
	function confirmSelection() {
		
		var ids = document.getElementById('csvIds').value;
		

		
		var ajaxRequest = simpleAjaxUtil_getXmlHttpObject();     
		
	    if (ajaxRequest==null) {alert ("Browser does not support HTTP Request");return;}
		
	    var url = "ajax/configAction.jsp";
	    
		var parameters = 	"METHOD=COLUMNS" + 
							'&flexReportFieldId=<%=flexReportFieldId%>' + 
							'&ids=' + ids;
		

	    ajaxRequest.open("post", url, true);
	    ajaxRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	    ajaxRequest.onreadystatechange=function(){
	    
		    if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
		
			
			    var response = ajaxRequest.responseText;
			    //document.getElementById('output').innerHTML = response;
			    
			    window.close();
			  } 
	    };    
	    ajaxRequest.send(parameters);
		
	}
	
	
	/*
		window.opener.document.getElementById(< % =targetIds%>).value = document.getElementById('csvIds').value;
		
		< % 
			if(namesProperty.toUpperCase().equals("VALUE")) { 
		 % >
		
		window.opener.document.getElementById(< % =targetNames%>).value = document.getElementById('csvNames').value;
		
		< %
			}
		% >
		
		< % 
		if(namesProperty.toUpperCase().equals("INNERHTML")) { 
		% >
		
		window.opener.document.getElementById(< % =targetNames%>).innerHTML = replaceAll(document.getElementById('csvNames').value, ",", "<br>");
		
		< %
			}
		% >
	
		
		
		
		< %
			if(updateFieldCode != null && !updateFieldCode.equals("")) {
		% >
		
		window.opener.updateParamValue('< % = updateFieldCode % >', document.getElementById('csvIds').value);
		
		< %
			}
		 % >
		
		window.close();
	}
	*/
	
	
	
	function updateSelection() {
		
		var chk_arr =  document.getElementsByName("selection");
		var chklength = chk_arr.length;             
		
		var csvIds = '';
		//var csvNames = '';
		
		for(k=0;k< chklength;k++)
		{
		    if(chk_arr[k].checked) {
		    	
		    	if(csvIds != '') {
		    		
		    		csvIds += ','
		    	}
		    	
		    	csvIds += chk_arr[k].id;
		    	
		    	
				/*if(csvNames != '') {
		    		
					csvNames += ', '
		    	}
				csvNames += chk_arr[k].value;*/
		    }
		} 
		
		
		document.getElementById('csvIds').value = csvIds;
		//document.getElementById('csvNames').value = csvNames;
		
		
		
	}
	
	
	
		/*
		var chk_arr =  document.getElementsByName("selection");
		var chklength = chk_arr.length;             

		
		
		var csvIds = '';
		var csvNames = '';
		
		for(k=0;k< chklength;k++)
		{
		    if(chk_arr[k].checked) {
		    	
		    	if(csvIds != '') {
		    		
		    		csvIds += ','
		    	}
		    	
		    	csvIds += chk_arr[k].id;
		    	
		    	
				if(csvNames != '') {
		    		
					csvNames += ', '
		    	}
				csvNames += chk_arr[k].value;
		    }
		} 
		
		
		document.getElementById('csvIds').value = csvIds;
		document.getElementById('csvNames').value = csvNames;
	}
	*/

	/*
	function searchElements() {

	    var ajaxRequest = simpleAjaxUtil_getXmlHttpObject();     
		
	    if (ajaxRequest==null) {alert ("Browser does not support HTTP Request");return;}
		
	    var url = "catalogData.jsp"
	    
	    var filter = document.getElementById('filter').value;
	    var catId = < % = catalogId % >;
	    var currentIds = document.getElementById('csvIds').value;
	    
		var parameters = 	"filter=" + filter + 
							'&catalogId=' + catId + 
							'&currentIds=' + currentIds;
		

	    ajaxRequest.open("post", url, true);
	    ajaxRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	    ajaxRequest.onreadystatechange=function(){
	    
		    if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
		
			
			    var response = ajaxRequest.responseText;
			    document.getElementById('output').innerHTML = response;
			  } 
	    };    
	    ajaxRequest.send(parameters);
	}
	*/

</script>


</head>
<body>
<div style="background-image: url('<%= request.getContextPath() %>/img/borders/page_border/border05.png');">

<input type="hidden" id="csvIds" value="<%=currentIds%>">
<%--

<input type="hidden" id="csvNames">
 --%>

<table width="100%">
	<tr>
		<td align="right">
			<input type="button" value="Aceptar Selección" onclick="confirmSelection()">
		</td>
	</tr>	
	<tr>
		<td>
		<!-- 
		Buscar: 
			<input type="text" id="filter" onkeyup="searchElements()">
			 -->
		</td>
	</tr>	
	<tr>
		<td>&nbsp;
		</td>
	</tr>	
	<tr>
		<td>
		<div id="output">
		</div>
		
		<% 
  
		/*
	int catalogId = Integer.parseInt(request.getParameter("catalogId"));
	String filter = new String(request.getParameter("filter").getBytes("ISO-8859-1"),"UTF-8");
	String currentIds = request.getParameter("currentIds");
	
	String searchCurrentIds = "," + currentIds + ",";
  
	SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
	int liIdRol =   Integer.valueOf(sessionBean.getIdRol());
	
	List<CatalogElement> catalogElements = Catalog.getCatalogElements(catalogId, filter, currentIds, liIdRol);
  */
  
  
  	boolean modoConfiguracioDeReportes = true;
  	ArrayList<FlexColumn> columns = FlexColumn.getFlexColumns(flexTableId, false, modoConfiguracioDeReportes); 

  	//String currentIds = "";
	  
  	for(FlexColumn column : columns){ 
  	
  		String check = "";
  		if(currentIds != null && currentIds.contains(Integer.toString(column.ID_FLEX_COLUM ))) {
  			
  			 check = "checked=\"checked\"";
  		}
  	%>
  	
  	<% if(column.NOM_FLEX_COLUM != null && !column.NOM_FLEX_COLUM.trim().equals("")){%>
  	<label>
  	<input type="checkbox" name="selection" id="<%= column.ID_FLEX_COLUM %>" value="<%= column.ID_FLEX_COLUM %>" <%= check %> onchange="updateSelection()">
  		<%-- <%= column.DES_FLEX_COLUM --%>
  		<%= column.NOM_FLEX_COLUM  %>
  		</label>
  	<br>
  	
  	<%}
  	}
  	%>
  	
  	
  	
  	
  	
  	
  	
		</td>
	</tr>	
</table>

</div>
</body>
</html>
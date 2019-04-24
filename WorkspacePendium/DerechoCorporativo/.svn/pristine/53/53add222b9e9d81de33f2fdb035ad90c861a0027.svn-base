<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Seleccionar Fecha</title>

<%@include file="include_calendar.jsp" %>


<%
	String targetField = "'" + request.getParameter("targetField") + "'";
	String updateFieldCode = request.getParameter("updateFieldCode");

%>

<script type="text/javascript">


function confirmSelection() {
	
	window.opener.document.getElementById(<%=targetField%>).value = document.getElementById('calinfo').value;

	<%
		if(updateFieldCode != null && !updateFieldCode.equals("")) {
	%>
	
	window.opener.updateParamValue('<%= updateFieldCode %>', document.getElementById('calinfo').value);
	
	<%
		}
	%>
	
	
	window.close();
}




</script>

</head>
<body>


<table width="100%">
	<tr>
		<td align="right">
			<input type="button" value="Aceptar Selección" onclick="confirmSelection()">
		</td>
	</tr>	
	<tr>
		<td>
			<div id="calcontainer"></div> <br /><br />
	        <input type="hidden" name="calinfo" id="calinfo" size="25">
	        <Script Language="JavaScript">
	        <!--
	        Calendar.setup ({
	            cont: 'calcontainer',
	            showTime: true,
	            onSelect: function() {
	                var date = this.selection.get();
	                date     = Calendar.intToDate(date);
	                date     = Calendar.printDate(date,"%d/%m/%Y");
	                    
	                document.getElementById('calinfo').value = date;
	            }
	            })
	        //-->
	        </Script>

		</td>
	</tr>	
</table>


		

</body>
</html>
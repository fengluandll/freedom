<%@page import="java.util.ArrayList"%>
<%@page import="mx.com.televisa.derechocorporativo.modules.flextabs.FlexTable"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body onload="">

<%
	ArrayList<FlexTable> reformas = FlexTable.getReformas();
%>
<table width="100%">
	<tr>
		<td width="5%"></td>
		<td width="95%">

			<div id="TabbedPanels3" class="TabbedPanels">
	    		<ul class="TabbedPanelsTabGroup">
	    			<%
	    				String firstTabName = "";
	    			
	    				int c = 0; 
	    				for(FlexTable tab : reformas) { 
	    					

	    					if(c==0) {
	    						
	    						firstTabName = tab.nomFlex;
	    					}
	    			
	    			%>
	    				<li class="TabbedPanelsTab" tabindex="<%=c%>" onclick="selectSubTabName('<%=tab.nomFlex %>');document.getElementById('reformasHiddenFirstTabName').value='<%=tab.nomFlex %>'"><%=tab.nomFlex %></li>
	    			<% 
	    					c++;
	    				} 
	    			%>
    			</ul>
				<div class="TabbedPanelsContentGroup">
					<% for(FlexTable tab : reformas) { %>
					<div class="TabbedPanelsContent">
						
							<br><br>
					
							<div id='flexTable_<%=tab.idflexTbl %>'></div>
							<div id='flexTableInnerForm_<%=tab.idflexTbl %>'></div>
							<script type='text/javascript'>
								
								</script>
					</div>
					<% } %>
					<input type="hidden" name='reformasHiddenFirstTabName' id='reformasHiddenFirstTabName' value='<%=firstTabName%>'>
				</div>
			</div>
			<br>			
		</td>
	</tr>
</table>
<br>
<br><br>
<div id="reformasInfoDiv"></div>

</body>
</html>
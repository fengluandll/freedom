<%@page import="mx.com.televisa.derechocorporativo.daos.MngDataPoderes"%>
<%@page import="mx.com.televisa.derechocorporativo.bean.GenericDataBean"%>
<%@page import="mx.com.televisa.derechocorporativo.bean.SessionBean"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="com.google.gson.GsonBuilder"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<%@page import="mx.com.televisa.derechocorporativo.modules.flextabs.FlexTable"%>

<%@include file="/css/kaz-styles.jsp"%>

<%@ page language="java" contentType="text/html;  charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%	
	List<Object> metaSession = new ArrayList<>();
	Gson gson = new Gson();
	
	SessionBean sessionBean = (SessionBean)session.getAttribute("sessionBean");	 
	
	metaSession.add(sessionBean);	
	metaSession.add(request.getContextPath());
    
	String jsonMS = gson.toJson(metaSession);
	
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">		
</head>

<body>

<table width="100%">
	<tr>
		<td>

			<div id="TabbedPanelsPoderes" class="TabbedPanels">
	    		<ul class="TabbedPanelsTabGroup">
	    			<li id="defaultOpen" class="TabbedPanelsTab TabbedPanelsTabSelected" tabindex="1" onclick="javascript:openTab(event,'PG');selectSubTabName('Poderes Generales');">Poderes generales</li>
	    			<li class="TabbedPanelsTab" tabindex="2" onclick="javascript:openTab(event,'PE');selectSubTabName('Poderes Especiales');">Poderes especiales</li>
	    			<li class="TabbedPanelsTab" tabindex="3" onclick="javascript:openTab(event,'CP');selectSubTabName('Cartas Poder');">Cartas poder</li>
	    			<li class="TabbedPanelsTab" tabindex="4" onclick="javascript:openTab(event,'ER');selectSubTabName('Revocaciones');">Revocaciones</li>
    			</ul>
				<div class="TabbedPanelsContentGroup">
					
					<div id="PG" class="tabcontent">						
						<br>
						<div id='id_PG'>					
							<iframe id="iFrame_PG" src="/DerechoCorporativo/faces/jsp/consulta/poderesGEC/consultaPoderesGenerales/consultaPG.jsp?TipoEsc=PG" style="width:100%; height:2600px; border:0px"></iframe>
						</div>
					</div>
					
					<div id="PE" class="tabcontent" style="display:none">						
						<br>
						<div id='id_PE'>							
							<iframe id="iFrame_PE" src="/DerechoCorporativo/faces/jsp/consulta/poderesGEC/consultaPoderesGenerales/consultaPG.jsp?TipoEsc=PE" style="width:100%; height:2600px; border:0px"></iframe>
						</div>
					</div>							
					
					<div id="CP" class="tabcontent" style="display:none">						
						<br>
						<div id='id_CP'>					
							<iframe id="iFrame_CP" src="/DerechoCorporativo/faces/jsp/consulta/poderesGEC/consultaPoderesGenerales/consultaPG.jsp?TipoEsc=CP" style="width:100%; height:2600px; border:0px"></iframe>
						</div>						
					</div>
					
					<div id="ER" class="tabcontent" style="display:none">						
						<br>
						<div id='id_ER'>					
							<iframe id="iFrame_ER" src="/DerechoCorporativo/faces/jsp/consulta/poderesGEC/consultaPoderesGenerales/consultaPG.jsp?TipoEsc=ER" style="width:100%; height:2600px; border:0px"></iframe>
						</div>						
					</div>
																		
				</div>
			</div>
			<br>			
		</td>
	</tr>
</table>
<br>
<br><br>

<div  id="MetaSession" style="display:none">
	<%=jsonMS %>
</div>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%
String htmlPG = (String)application.getAttribute("htmlPG")==null?"":(String)application.getAttribute("htmlPG");		
String htmlPE = (String)application.getAttribute("htmlPE")==null?"":(String)application.getAttribute("htmlPE");		
String htmlCP = (String)application.getAttribute("htmlCP")==null?"":(String)application.getAttribute("htmlCP");
String htmlER = (String)application.getAttribute("htmlER")==null?"":(String)application.getAttribute("htmlER");

htmlPG = htmlPG.replace("/DerechoCorporativo/img/","/img/");
htmlPG = "<fieldset><legend>Poderes Generales</legend>" + htmlPG + "</fieldset>"; 
htmlPG = htmlPG.replace("bullet","&#8226;");
htmlPG = htmlPG.replace("plusssign","+");
htmlPG = htmlPG.replace("percent","%");

htmlPE = htmlPE.replace("/DerechoCorporativo/img/","/img/");
htmlPE = "<br><br><fieldset><legend>Poderes Especiales</legend>" + htmlPE + "</fieldset>";
htmlPE = htmlPE.replace("bullet","&#8226;");
htmlPE = htmlPE.replace("plusssign","+");
htmlPE = htmlPE.replace("percent","%");

htmlCP = htmlCP.replace("/DerechoCorporativo/img/","/img/");
htmlCP = "<br><fieldset><legend>Cartas Poder</legend>" + htmlCP + "</fieldset>";
htmlCP = htmlCP.replace("bullet","&#8226;");
htmlCP = htmlCP.replace("plusssign","+");
htmlCP = htmlCP.replace("percent","%");

htmlER = htmlER.replace("/DerechoCorporativo/img/","/img/");
htmlER = "<br><fieldset><legend>Revocaciones</legend>" + htmlER + "</fieldset>";
htmlER = htmlER.replace("bullet","&#8226;");
htmlER = htmlER.replace("plusssign","+");
htmlER = htmlER.replace("percent","%");
%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> 	  
</head>
<body>
	
	<%=htmlPG %>
	<%=htmlPE %>
	<%=htmlCP %>
	<%=htmlER %>

</body>
</html>
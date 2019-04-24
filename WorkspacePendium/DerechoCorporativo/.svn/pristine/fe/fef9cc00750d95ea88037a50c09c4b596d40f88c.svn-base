<%@ taglib uri="pd4ml" prefix="pd4ml"%>
<pd4ml:transform screenWidth="990" pageFormat="LETTER"
	fileName="DetalleDePoder.pdf" pageInsets="10,10,10,10,mm"
	adjustScreenWidth="true" enableTableBreaks="true"
	enableImageSplit="false" debug="true">
	<pd4ml:footer areaHeight="30">

	</pd4ml:footer>

<%
String htmlDetalle = (String)application.getAttribute("htmlDetalle");
htmlDetalle = htmlDetalle.replace("/DerechoCorporativo/img/","/img/");
htmlDetalle = htmlDetalle.replace("%CF","&#8226;");
htmlDetalle = htmlDetalle.replace("<div id=\"divSeccCContent\" class=\"divSeccContent\" style=\"display:none\">","<div id=\"divSeccCContent\" class=\"divSeccContent\">");
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 	<style>
	
		body{
			font-size: 13px;
			font-family: 'Open Sans', sans-serif;
					width:100% !important;	
		}				
						
		.divSeccHead{
			background-color:#2B385D;
			color:#FFF;
			padding:5px;
			text-align:left;
			cursor:pointer;
			border-bottom: #FFF 2px solid;
			width:100%;
		}
		
		#divPGForm{
		 width:100% !important;
		}
		
		.divSeccH{
			font-align:center;
			background-color:#2B385D;
			color:#FFF;
			padding:5px;	
			cursor:cursor;
		}
		
		.SubTitleHead{
			font-weight:bold;
		}
		
		#tabsPoderes {
		    margin-left: 30px;
		    margin-right: 30px;
		}
		
		#PGForm_DlgApoderados_Grid_gapoderSelecc{
			height:200px;
			overflow-x: auto;
		}
		
		#PGForm_DlgApoderados_Div_gapoderSelecc{
			height:200px;
			overflow-x: auto;
			font-size: 11px;
		}
		
		#PGForm_DlgApoderadosOtros_Grid_ApSelecc{
			height:200px;
			overflow-x: auto;
		}
		
		table { 
		   
		    width:100% !important;
		}
		
		td { 
    		padding: 5px;
		}
		
		tr{
			font-size: 11px;
		}

		th{
			font-size: 13px;
			font-weight: normal;
			padding: 5px;
			border-left-color: #FFFFFF;
    		border-left-style: solid;
			border-left-width: 1px;
			
		}
				
		.fondoA{
			background-color:#F8F8F8 !important;
		}
		
		.fondoB{
			background-color:#CCCCCC !important;
		}
		
		.fondoAInit{
			background-color:#F8F8F8 !important;
			border-left: #000 solid 5px;
		}
		
		.fondoBInit{
			background-color:#CCCCCC !important;
			border-left: #000 solid 5px;
		}
		
		.tableHeads{
			background-color: #2B385D;
			color:#FFFFFF;	
			font-size: 13px;	
			
		}
		
		.oddCeld{
			background-color: #FFFFFF;					
			font-size: 11px;			
			vertical-align: top;
		}
				
		.evenCeld{
			background-color: #DCE8F6;
			font-size: 11px;
			vertical-align: top;
		}
					
		.Img_Semaforo_green {  
		  width: 32px;
		  height:32px;
		  background-image: url("/img/semaforo_green.png");
		  background-repeat: no-repeat; 
		  cursor:pointer;
		}
		
		.Img_Semaforo_yellow {  
		  width: 32px;
		  height:32px;
		  background-image: url("/img/semaforo_yellow.png");
		  background-repeat: no-repeat; 
		  cursor:pointer;
		}
		
		.Img_Semaforo_gray{
		  width: 32px;
		  height:32px;
		  background-image: url("/img/semaforo_gray.png");
		  background-repeat: no-repeat; 
		  cursor:pointer;
		}
		
		.Img_Icon_Documentum{  
	  	width: 32px;
	  	height:32px;
	  	background-image: url("/img/icons/List.png");
	  	background-repeat: no-repeat; 
	  	cursor:pointer;	  
		}		
					
	</style>
	
</head>
<body>
	
	<%=htmlDetalle %>
	
</body>
</html>
</pd4ml:transform>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="mx.com.televisa.derechocorporativo.daos.MngDataPoderes"%>
<%@page import="mx.com.televisa.derechocorporativo.bean.GenericDataBean"%>
<%@page import="mx.com.televisa.derechocorporativo.bean.SessionBean"%>
<%@page import="mx.com.televisa.derechocorporativo.util.FacesUtils"%>

<%@page import="com.google.gson.Gson"%>
<%@page import="com.google.gson.GsonBuilder"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="mx.com.televisa.derechocorporativo.daos.Abc_AppConfigTabDAO"%>
<%
		
	String nonCache = false ? "" : "?lastversion=" + new java.util.Random().nextInt(); 
	List<Object> initArgs = new ArrayList();	
	List<Object> metaSession = new ArrayList();
	Gson gson = new Gson();	
	String idSeccion = request.getParameter("idSeccion");	
	String TipoEsc = request.getParameter("TipoEsc");	
	if(TipoEsc == null)
		TipoEsc = "PG";
	SessionBean sessionBean = (SessionBean)session.getAttribute("sessionBean");	 
	
	metaSession.add(sessionBean);	
	metaSession.add(request.getContextPath());    
	metaSession.add(TipoEsc);
	metaSession.add(sessionBean.getIdCurrentEmpresa());
	
	String jsonMS=gson.toJson(metaSession);
	GenericDataBean listEsc = null;
	switch(TipoEsc){
		case "PE":
			//******** Carga registros de Poderes Especiales
			listEsc = MngDataPoderes.query_PODERES_ESPECIALES_PR(Integer.parseInt(sessionBean.getIdCurrentEmpresa()), "" );
			
			break;
		case "CP":
			//******** Carga registros de Cartas Poder
			listEsc = MngDataPoderes.query_PODERES_CARTA_PODER_PR(Integer.parseInt(sessionBean.getIdCurrentEmpresa()), "" );
			
			break;
		case "ER":
			//******** Carga registros de Revocaciones 
			listEsc = MngDataPoderes.query_REVOCACIONES_PR(Integer.parseInt(sessionBean.getIdCurrentEmpresa()),"");
			
			break;
		default: 
			//******** Carga registros de Escritura Poder Tipo Poder General "PG"
			listEsc = MngDataPoderes.query_PODERES_GENERALES_PR(Integer.parseInt(sessionBean.getIdCurrentEmpresa()),"");
			
			break;
	}
	
	initArgs.add(listEsc);
	
	String tipoEscritura = "";
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta content="text/html" http-equiv="content-type charset=ISO-8859-1"></meta>
    <title></title>
    <link href="/DerechoCorporativo/css/webCore.css<%=nonCache %>" rel="stylesheet" type="text/css" />
    <link href="/DerechoCorporativo/css/jquery-ui-css/pendium/jquery-ui.css" rel="stylesheet" type="text/css" />
    <script src="/DerechoCorporativo/js/jquery/jquery-2.1.4.min.js" type="text/javascript"></script>
    
    <script src="/DerechoCorporativo/css/jquery-ui-css/pendium/jquery-ui.js" type="text/javascript"></script>
    
        
    <link href="/DerechoCorporativo/css/jquery-ui-css/blue-moon/GridSort.css" rel="stylesheet" type="text/css" />
    <script src="/DerechoCorporativo/js/jquery/globalize.js" type="text/javascript"></script>
    <script src="/DerechoCorporativo/js/jquery/plugins/jGridContentMVC-1.0.2.js<%=nonCache %>" type="text/javascript"></script>
    <script src="/DerechoCorporativo/js/jquery/plugins/jquery.tablesorter.js<%=nonCache %>" type="text/javascript"></script>
    <script src="/DerechoCorporativo/js/jquery/plugins/jquery.json-2.2.min.js<%=nonCache %>" type="text/javascript"></script>
    <script src="/DerechoCorporativo/js/jquery/plugins/urlEncode.js<%=nonCache %>" type="text/javascript"></script>
    
    <script src="/DerechoCorporativo/js/jquery/WebKernel/Util.js<%=nonCache %>" type="text/javascript"></script>
    <script src="/DerechoCorporativo/js/jquery/WebKernel/webKernel.js<%=nonCache %>" type="text/javascript"></script>
    <script src="/DerechoCorporativo/js/jquery/WebKernel/RequestModel.js<%=nonCache %>" type="text/javascript"></script>
    
    <script src="/DerechoCorporativo/js/jsCal/jquery.mask.js<%=nonCache %>" type="text/javascript"></script>
    
    <link href="/DerechoCorporativo/jsp/consulta/poderesGEC/consultaPoderesGenerales/consultaPG.css<%=nonCache %>" rel="stylesheet" type="text/css" />
    <script src="/DerechoCorporativo/jsp/consulta/poderesGEC/consultaPoderesGenerales/RequestModel.js<%=nonCache %>" type="text/javascript"></script>
    <script src="/DerechoCorporativo/jsp/consulta/poderesGEC/consultaPoderesGenerales/consultaPG.js<%=nonCache %>" type="text/javascript"></script>
        
    
    <script src="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.min.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.css">	
	   
	<script type="text/javascript">
	 var printPestanaPoder = function(){		 		 
		 var action = 'cargarHtmlDetallePoderes'; 
		 //Se oculta el div con los campos de busqueda e imprimir
		 $("#filaBuscarImprimir").hide();
		 //Obtener html de los iFrames de poderes
		 var htmlPestana = $('body').html();
		//Se muestra el div con los campos de busqueda e imprimir
		 $("#filaBuscarImprimir").show();
		//Se concatena el titulo con el tipo de poder
		htmlPestana = "<h3 style='color:#4E609F'>" + $("#tipoEscritura").val() + "</h3><br/>" + htmlPestana;		 
		//Enviar html de Poderes via ajax al Servlet para guardar html en sesion
			$.ajax({
		        dataType: 'json',
		        success: function(){
		        	
		        },
		        complete: function(){
		        	newwindow=window.open('consultaPGPrint.jsp');
			    	if (window.focus) {newwindow.focus()}		        		        	
		        },
		        error: function(){
		        	
		        },
		        url: '/DerechoCorporativo/Abc_PoderesGenerales',
		        data: {action:action, htmlDetalle:$.URLEncode(htmlPestana)},
		        type: 'POST'
		    });    
   	     }
	 
	</script>	   
	   
</head>
<body id="body" style="display:none">

    <div id="tabsPoderes">    
	
	<div id="tabsPoderes-1">	
		<div id="divPGMain">		
        <table width="100%">        
            <tr id="filaBuscarImprimir">                       
                <td id="queryRow" style="width:300px">  	                  
	            	<input type="text" id="txtPGQuery"></input>
	                <button type="button" openFormType="PG" id="btnPGQuery" title="Buscar">Buscar</button>
	                <div id="btnReset" title="Click para limpiar búsqueda" style="float:right">
						<a href="#txtPGQuery">
							<img src="/DerechoCorporativo/img/btn_clean.png"></img>
						</a>
					</div>    					             
                </td>                
                <td>
                	<div style="margin-left:400px">
                  		<a href="#" style="text-decoration: none;color:#2B385D;font-size: 13px;" onclick="printPestanaPoder()">Imprimir esta subsecci&oacute;n</a>
                 	</div> 
                </td>                               
            </tr>
            <tr>
                <td colspan="2">
                    <div id="gridPGmain">
                    	<%=gson.toJson(initArgs.get(0)) %>
                    </div>
                </td>
            </tr>
        </table>  
       </div>   
       
    </div>
	   
</div>
<%
if(TipoEsc.equals("PG"))
	tipoEscritura = "Poderes Generales";
if(TipoEsc.equals("PE"))
	tipoEscritura = "Poderes Especiales"; 
if(TipoEsc.equals("CP")) 
	tipoEscritura = "Cartas Poder";
if(TipoEsc.equals("ER")) 
	tipoEscritura = "Revocaciones"; 
%>

<input id="tipoEscritura" type="hidden" value="<%=tipoEscritura %>"/>

<div id="MetaSession" style="display:none">	
	<%=jsonMS %>
</div>

<div id="AppEngaged" title="Por favor espere...">
	<table style="width: 100%; height: 100%;">            
    	<tr><td align="center"><div id="AppEngaged_Icon"></div></td></tr>
        <tr><td align="center"><div id="AppEngaged_Msg"></div></td></tr>                    
    </table>
</div>
        
</body>
</html>
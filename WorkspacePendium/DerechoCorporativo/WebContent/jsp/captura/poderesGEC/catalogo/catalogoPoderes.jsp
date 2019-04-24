<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="mx.com.televisa.derechocorporativo.daos.MngDataPoderes"%>
<%@page import="mx.com.televisa.derechocorporativo.bean.GenericDataBean"%>
<%@page import="mx.com.televisa.derechocorporativo.bean.SessionBean"%>

<%@page import="com.google.gson.Gson"%>
<%@page import="com.google.gson.GsonBuilder"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="mx.com.televisa.derechocorporativo.bean.CatalogoBean"%>
<%
String nonCache = false ? "" : "?lastversion=" + new java.util.Random().nextInt(); 
List<Object> initArgs = new ArrayList();	
List<Object> metaSession = new ArrayList();
Gson gson = new Gson();

String idSeccion = request.getParameter("idSeccion");	
SessionBean sessionBean = (SessionBean)session.getAttribute("sessionBean");	 

metaSession.add(sessionBean);	
metaSession.add(request.getContextPath());

String jsonMS=gson.toJson(metaSession);
	
//******** Carga del catalogo de Poderes Especiales
initArgs.add(MngDataPoderes.queryCatalogosDePoderes("PE"));	

 CatalogoBean catalogoBean = (CatalogoBean)session.getAttribute("detCatalogoBean"); 

String cata = catalogoBean.getNombre();
// session.removeAttribute("catalogoBean");


%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta content="text/html" http-equiv="Content-Type" charset="ISO-8859-1"></meta>
    <title></title>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@include file="../../../../css/kaz-styles.jsp"%>
    <link href="../../../../css/webCore.css<%=nonCache %>" rel="stylesheet" type="text/css" />
    <link href="../../../../css/jquery-ui-css/pendium/jquery-ui.css" rel="stylesheet" type="text/css" />
    <script src="../../../../js/jquery/jquery-2.1.4.min.js" type="text/javascript"></script>
    
    <script src="../../../../css/jquery-ui-css/pendium/jquery-ui.js" type="text/javascript"></script>
    
        
    <link href="../../../../css/jquery-ui-css/blue-moon/GridSort.css" rel="stylesheet" type="text/css" />
    <script src="../../../../js/jquery/globalize.js" type="text/javascript"></script>
    <script src="../../../../js/jquery/plugins/jGridContentMVC-1.0.2.js<%=nonCache %>" type="text/javascript"></script>
    <script src="../../../../js/jquery/plugins/jquery.tablesorter.js<%=nonCache %>" type="text/javascript"></script>
    <script src="../../../../js/jquery/plugins/jquery.json-2.2.min.js<%=nonCache %>" type="text/javascript"></script>
    <script src="../../../../js/jquery/plugins/urlEncode.js<%=nonCache %>" type="text/javascript"></script>
    
    <script src="../../../../js/jquery/WebKernel/Util.js<%=nonCache %>" type="text/javascript"></script>
    <script src="../../../../js/jquery/WebKernel/webKernel.js<%=nonCache %>" type="text/javascript"></script>
    <script src="../../../../js/jquery/WebKernel/RequestModel.js<%=nonCache %>" type="text/javascript"></script>
        
    <script src="RequestModelCatPoderes.js<%=nonCache %>" type="text/javascript"></script>
    <script src="catalogoPoderes.js<%=nonCache %>" type="text/javascript"></script>
    
    <script src="../../../../js/CKEditor/ckeditor.js" type="text/javascript"></script> 
    
    <script src="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.min.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.css">
	<%@include file="../../../components/session_timeout/session_timeout.jsp"%>

	<style type="text/css">
		.tableHeads{
			background-color: #2B4963;
			color:#FFFFFF;
		}
		.oddCeld{
				background-color: #FFFFFF;								
		}
		.evenCeld{
				background-color: #DCE8F6;
		}
	</style>
    
</head>
<body>
	<jsp:include page="/jsp/components/pages_border/open-pre-title.jsp"></jsp:include>
		Administraci&oacute;n de Cat&aacute;logo: <u><%= cata %></u>
	<jsp:include page="/jsp/components/pages_border/open-post-title.jsp"></jsp:include>

	<div id="divCatalogoMain">
		<table width="100%" border="0" align="center">
			<tr>							
				<td align="left">
					<a href="<c:out value="${applicationBean.contextPath}"/>/CargaCatalogoServlet"><abbr title="Regresar"><img src="../../img/Navigation-Left-blue-32.png" alt="Regresar" /></abbr></a>
				</td>
				<td align="center">
					<input type="text" id="txtCatalogoQuery" style="margin-left: 50px;" size="30" controller="text"/>
					<img id="btnCatalogoQuery" src="../../img/search.png" style="cursor:pointer"/>
				</td>
				<td class="nvoCta">Nuevo</td>
				<td align="left">
					<div openFormMode="new" openFormType="Catalogo" divHide="#divCatalogoMain" id="btnCatalogoAdd" class="Img_Icon_new"></div>
				</td>
			</tr>
			<tr>
				<td width="100%" colspan="4">
					<div id="gridCatalogoMain">
						<%=gson.toJson(initArgs.get(0)) %>
					</div>
				</td>
			</tr>
		</table>
	</div>
		      
	<div id="CatalogoForm_DialogoCaptura" title="Captura de catalogo de poderes" style="display:none">
	
		<div id="CatalogoForm_DialogoCaptura_FormaDeEjercerPoder" style="display:none">		
		  <div id="CatalogoForm_DialogoCaptura_tabs">
		  	<ul>
				<li><a href="#CatalogoForm_DialogoCaptura_FormaDeEjercerPoderTab-1">Poder y descripcion</a></li>
				<li><a href="#CatalogoForm_DialogoCaptura_FormaDeEjercerPoderTab-2">Formas de Ejercer el Poder</a></li>
			</ul>
		  <div id="CatalogoForm_DialogoCaptura_FormaDeEjercerPoderTab-1">
		  	<table style="margin:20px;width:100%;">
				<tr>
					<td><label>Poder:</label></td>
					<td><input type="text" size="50" id="CatalogoForm_DialogoCaptura_txt_poder" dataField="des_podertipo" controller="text" class="CatalogoForm_DialogoCaptura"></input></td>
				</tr>
				<tr>
					<td><label>Descripci&oacute;n:</label></td>
					<td><textarea rows="5" cols="50" id="CatalogoForm_DialogoCaptura_txt_descripcion"></textarea></td>
					<!-- <td><div id="CatalogoForm_DialogoCaptura_txt_descripcion" style="background-color:#FFFFFF;border:solid 1px #ABADB3;width:400px"></div></td> -->
				</tr>
				<tr>
					<td style="width:20%">Caracter&iacute;sticas/<br>Limitaciones:</td>
					<td rowspan="4"><textarea rows="6" cols="50" id="CatalogoForm_DialogoCaptura_txt_caract"></textarea></td>
				</tr>
			</table>
		  </div>
		  
		  <div id="CatalogoForm_DialogoCaptura_FormaDeEjercerPoderTab-2">		  	
		    <table width="100%" id="CatalogoForm_DialogoCaptura_chk_aA_Table">
		    	<tr>
		    		<td style="width:20%" valign="top">
		    			<div>
		    				<input type="radio" name="CatalogoForm_DialogoCaptura_Delegable" id="CatalogoForm_DialogoCaptura_Delegable" value="Delegable" checked="checked" />
		    				<label for="CatalogoForm_DialogoCaptura_Deleg1">Delegable</label>
		    			</div>
		    			<div>
		    				<input type="radio" name="CatalogoForm_DialogoCaptura_Delegable" id="CatalogoForm_DialogoCaptura_NoDelegable" value="No Delegable" />
		    				<label for="CatalogoForm_DialogoCaptura_Deleg1">No Delegable</label>
		    			</div>
		    			<div>
		    				<input type="radio" name="CatalogoForm_DialogoCaptura_Delegable" id="CatalogoForm_DialogoCaptura_Ninguno" value="Ninguno" />
		    				<label for="CatalogoForm_DialogoCaptura_Deleg1">Ninguno</label>
		    			</div>
		    		</td>
		    		<td style="width:30%" valign="top">
		    			<div>
		    				<input type="radio" name="CatalogoForm_DialogoCaptura_Individual" id="CatalogoForm_DialogoCaptura_Individual" value="Individual" checked="checked"/>
		    				<label for="CatalogoForm_DialogoCaptura_DelegA2">Individual</label>
		    			</div>
		    			<div>
		    				<input type="radio" name="CatalogoForm_DialogoCaptura_Individual" id="CatalogoForm_DialogoCaptura_Conjuntamente" value="Conjuntamente" />
		    				<label for="CatalogoForm_DialogoCaptura_DelegB2">Conjuntamente</label>
		    			</div>
		    			<div>
		    				<input type="radio" name="CatalogoForm_DialogoCaptura_Individual" id="CatalogoForm_DialogoCaptura_Conjunta_Separadamente" value="Conjunta o separadamente" />
		    				<label for="CatalogoForm_DialogoCaptura_DelegC2">Conjunta o separadamente</label>
		    			</div>
		    			<div>
		    				<input type="radio" name="CatalogoForm_DialogoCaptura_Individual" id="CatalogoForm_DialogoCaptura_Otro" value="Ninguno" />
		    				<label for="CatalogoForm_DialogoCaptura_DelegD2">Ninguno</label>
		    			</div>  
		    		</td>
		    	</tr>
		    	<tr>
		    		<td style="width:25%" valign="top" colspan="2">
		    			<div><label for="CatalogoForm_DialogoCaptura_Forma_Ejercerlo">Formas de Ejercerlo:</label></div>
		    			<textarea rows="5" cols="40" id="CatalogoForm_DialogoCaptura_Forma_Ejercerlo" class="CatalogoForm_DialogoCaptura"></textarea>    			     		
		    		</td>
		    		
		    	</tr>
		    </table>
		  </div>  
		  </div>
		</div>
		 
	</div>  
			    		
	<div id="MetaSession" style="display:none">	
		<%=jsonMS %>
	</div>
	<input type="hidden" id="MetaSessionHdn" style="display:none" value="<%=jsonMS %>"/>

<jsp:include page="/jsp/components/pages_border/close.jsp"></jsp:include>

</body>
</html>
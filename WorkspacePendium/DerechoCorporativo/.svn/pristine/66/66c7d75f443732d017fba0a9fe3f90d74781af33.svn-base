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
	
//******** Carga del catalogo de Poderes Generales
initArgs.add(MngDataPoderes.queryCatalogosDePoderes("PG"));	

 CatalogoBean catalogoBean = (CatalogoBean)session.getAttribute("detCatalogoBean"); 

String cata = catalogoBean.getNombre();
// session.removeAttribute("catalogoBean");


%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta content="text/html" http-equiv="content-type" charset="ISO-8859-1"></meta>
    <title></title>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@include file="/css/kaz-styles.jsp"%>
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
    <script src="catalogoPoderesGenerales.js<%=nonCache %>" type="text/javascript"></script> 
    
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
		
	<div id="divCatalogoPGMain">
		<table width="100%">
			<tr>							
				<td align="left">
					<a href="<c:out value="${applicationBean.contextPath}"/>/CargaCatalogoServlet"><abbr title="Regresar"><img src="../../img/Navigation-Left-blue-32.png" alt="Regresar" /></abbr></a>
				</td>
				<td align="center">
					<input type="text" id="txtCatalogoPGQuery" style="margin-left: 50px;" size="30" controller="text"/>					
					<img id="btnCatalogoPGQuery" src="../../img/search.png" style="cursor:pointer"/>
				</td>
				<td class="nvoCta">Nuevo</td>
				<td>
					<div openFormMode="new" openFormType="Catalogo" divHide="#divCatalogoPGMain" id="btnCatalogoPGAdd" class="Img_Icon_new"></div>
				</td>
			</tr>
			<tr>
				<td width="100%" colspan="4">
					<div id="gridCatalogoPGmain">
						<%=gson.toJson(initArgs.get(0)) %>
					</div>
				</td>
			</tr>
		</table>
	</div>
		      	
		<div id="CatalogoPGForm_DialogoCaptura" title="Captura de catalogo de poderes" style="display:none">
				
		<fieldset>
    		<legend style="font-weight:bold"></legend>
    		<table style="margin:20px;width:100%;">
				<tr>
					<td><label>Poder:</label></td>
					<td><input type="text" size="50" id="CatalogoPGForm_DialogoCaptura_txt_poder" controller="text" class="CatalogoPGForm_DialogoCaptura"></input></td>
				</tr>
				<tr>
					<td><label>Descripci&oacute;n:</label></td>
					<td><textarea rows="5" cols="50" id="CatalogoPGForm_DialogoCaptura_txt_descripcion"></textarea></td>
				</tr>				
			</table>
		</fieldset>
		<br/>
		<div id="CatalogoPGForm_DialogoCaptura_tabs" class="DetallePoder DetallePoderGeneral">
	  <ul>
	    <li><a href="#CatalogoPGForm_DialogoCaptura_tab_1">Actos de Administraci&oacute;n</a></li>
	    <li><a href="#CatalogoPGForm_DialogoCaptura_tab_2">Actos de Dominio</a></li>
	    <li><a href="#CatalogoPGForm_DialogoCaptura_tab_3">T&iacute;tulos de Cr&eacute;dito</a></li>
	    <li><a href="#CatalogoPGForm_DialogoCaptura_tab_4">Pleitos y Cobranzas</a></li>    	    
	  </ul>
	  
	  <div id="CatalogoPGForm_DialogoCaptura_tab_1">
	  	<input type="checkbox" id="CatalogoPGForm_DialogoCapturaOtros_chk_Tiene_aa" toggleDiv="#CatalogoPGForm_DialogoCapturaOtros_chk_aA_Table" defaultShow="false" controller="check" class="CatalogoPGForm_DialogoCaptura"/>
	  	<label for="CatalogoPGForm_DialogoCapturaOtros_chk_Tiene_aa">Actos de Administraci&oacute;n</label>
	    <table width="100%" id="CatalogoPGForm_DialogoCapturaOtros_chk_aA_Table">
	    	<tr>
	    		<td style="width:33%" valign="top">
	    			<div>
	    				<input type="radio" name="CatalogoPGForm_DialogoCaptura_Delegable_aa" id="CatalogoPGForm_DialogoCapturaOtros_DelegA1" value="Delegable" checked="checked" />
	    				<label for="CatalogoPGForm_DialogoCapturaOtros_DelegA1">Delegable</label>
	    			</div>
	    			<div>
	    				<input type="radio" name="CatalogoPGForm_DialogoCaptura_Delegable_aa" id="CatalogoPGForm_DialogoCapturaOtros_DelegB1" value="No Delegable" />
	    				<label for="CatalogoPGForm_DialogoCapturaOtros_DelegB1">No Delegable</label>
	    			</div>
	    			<div>
	    				<input type="radio" name="CatalogoPGForm_DialogoCaptura_Delegable_aa" id="CatalogoPGForm_DialogoCapturaOtros_DelegC1" value="Ninguno" />
	    				<label for="CatalogoPGForm_DialogoCapturaOtros_Deleg1">Ninguno</label>
	    			</div>
	    		</td>
	    		<td colspan="2" valign="top">
	    			<div>
	    				<input type="radio" name="CatalogoPGForm_DialogoCaptura_Individual_aa" id="CatalogoPGForm_DialogoCapturaOtros_DelegA2" value="Individual" checked="checked"/>
	    				<label for="CatalogoPGForm_DialogoCapturaOtros_DelegA2">Individual</label>
	    			</div>
	    			<div>
	    				<input type="radio" name="CatalogoPGForm_DialogoCaptura_Individual_aa" id="CatalogoPGForm_DialogoCapturaOtros_DelegB2" value="Conjuntamente" />
	    				<label for="CatalogoPGForm_DialogoCapturaOtros_DelegB2">Conjuntamente</label>
	    			</div>
	    			<div>
	    				<input type="radio" name="CatalogoPGForm_DialogoCaptura_Individual_aa" id="CatalogoPGForm_DialogoCapturaOtros_DelegC2" value="Conjuntamente o Separadamente" />
	    				<label for="CatalogoPGForm_DialogoCapturaOtros_DelegB2">Conjuntamente o Separadamente</label>
	    			</div>
	    			<div>
	    				<input type="radio" name="CatalogoPGForm_DialogoCaptura_Individual_aa" id="CatalogoPGForm_DialogoCapturaOtros_DelegD2" value="Ninguno" />
	    				<label for="CatalogoPGForm_DialogoCapturaOtros_DelegD2">Ninguno</label>
	    			</div>  
	    		</td>
	    		
	    	</tr>
	    	<tr>
	    		<td  valign="top">
	    			<div><label for="CatalogoPGForm_DialogoCapturaOtros_txt_aa_caracteristicas">Caracter&iacute;sticas/Limitaciones</label></div>
	    			<textarea style="width:98%;" rows="5" cols="15" id="CatalogoPGForm_DialogoCapturaOtros_txt_aa_caracteristicas" class="CatalogoPGForm_DialogoCaptura"></textarea>
	    			     			
	    		</td>
	    		<td  valign="top" style="width:33%" >
	    			<div><label for="CatalogoPGForm_DialogoCapturaOtros_txt_aa_formaejercerlo">Forma de Ejercerlo</label></div>
	    			<textarea style="width:98%;" rows="5" cols="15" id="CatalogoPGForm_DialogoCapturaOtros_txt_aa_formaejercerlo" class="CatalogoPGForm_DialogoCaptura"></textarea>
	    			     			
	    		</td>
	    			    		    		    		    	
	    	</tr>
	    </table>
	  </div>  
	  
	  <div id="CatalogoPGForm_DialogoCaptura_tab_2">
	  <input type="checkbox" id="CatalogoPGForm_DialogoCapturaOtros_chk_Tiene_ad" toggleDiv="#CatalogoPGForm_DialogoCapturaOtros_chk_aD_Table" defaultShow="false" controller="check" class="CatalogoPGForm_DialogoCaptura"/>
	  <label for="CatalogoPGForm_DialogoCapturaOtros_chk_Tiene_ad">Actos de Dominio</label>
	    <table width="100%" id="CatalogoPGForm_DialogoCapturaOtros_chk_aD_Table">
	    	<tr>
	    		<td style="width:33%" valign="top">
	    			<div>
	    				<input type="radio" name="CatalogoPGForm_DialogoCaptura_Delegable_ad" id="CatalogoPGForm_DialogoCapturaOtros_DelegA2" value="Delegable" checked="checked" />
	    				<label for="CatalogoPGForm_DialogoCapturaOtros_Deleg1">Delegable</label>
	    			</div>
	    			<div>
	    				<input type="radio" name="CatalogoPGForm_DialogoCaptura_Delegable_ad" id="CatalogoPGForm_DialogoCapturaOtros_DelegB2" value="No Delegable" />
	    				<label for="CatalogoPGForm_DialogoCapturaOtros_Deleg1">No Delegable</label>
	    			</div>
	    			<div>
	    				<input type="radio" name="CatalogoPGForm_DialogoCaptura_Delegable_ad" id="CatalogoPGForm_DialogoCapturaOtros_DelegC2" value="Ninguno" />
	    				<label for="CatalogoPGForm_DialogoCapturaOtros_Deleg1">Ninguno</label>
	    			</div>
	    		</td>
	    		<td colspan="2"  valign="top">
	    			<div>
	    				<input type="radio" name="CatalogoPGForm_DialogoCaptura_Individual_ad" id="CatalogoPGForm_DialogoCapturaOtros_DelegA2B" value="Individual" checked="checked"/>
	    				<label for="CatalogoPGForm_DialogoCapturaOtros_DelegA2">Individual</label>
	    			</div>
	    			<div>
	    				<input type="radio" name="CatalogoPGForm_DialogoCaptura_Individual_ad" id="CatalogoPGForm_DialogoCapturaOtros_DelegB2B" value="Conjuntamente" />
	    				<label for="CatalogoPGForm_DialogoCapturaOtros_DelegB2">Conjuntamente</label>
	    			</div> 
	    			<div>
	    				<input type="radio" name="CatalogoPGForm_DialogoCaptura_Individual_ad" id="CatalogoPGForm_DialogoCapturaOtros_DelegC2B" value="Conjuntamente o Separadamente" />
	    				<label for="CatalogoPGForm_DialogoCapturaOtros_DelegB2">Conjuntamente o Separadamente</label>
	    			</div> 			
	    			<div>
	    				<input type="radio" name="CatalogoPGForm_DialogoCaptura_Individual_ad" id="CatalogoPGForm_DialogoCapturaOtros_DelegD2B" value="Ninguno" />
	    				<label for="CatalogoPGForm_DialogoCapturaOtros_DelegD2">Ninguno</label>
	    			</div>
	    			
	    		</td>
	    		
	    		</tr>
	    		<tr>
	    		
	    		<td  valign="top">
	    			<div><label for="CatalogoPGForm_DialogoCapturaOtros_txt_caract">Caracter&iacute;sticas/Limitaciones</label></div>
	    			<textarea style="width:98%;" rows="5" cols="15" id="CatalogoPGForm_DialogoCapturaOtros_txt_ad_caracteristicas" class="CatalogoPGForm_DialogoCaptura"></textarea>
	    			     			
	    		</td>
	    		<td valign="top" style="width:33%">
	    			<div><label for="CatalogoPGForm_DialogoCapturaOtros_txt_caract">Forma de Ejercerlo</label></div>
	    			<textarea style="width:98%;" rows="5" cols="15" id="CatalogoPGForm_DialogoCapturaOtros_txt_ad_formaejercerlo" class="CatalogoPGForm_DialogoCaptura"></textarea>
	    		</td>	    		
	    	</tr>
	    </table>
	  </div>  
	  
	  <div id="CatalogoPGForm_DialogoCaptura_tab_3">
	  <input type="checkbox" id="CatalogoPGForm_DialogoCapturaOtros_chk_Tiene_tc" toggleDiv="#CatalogoPGForm_DialogoCapturaOtros_chk_tC_Table" defaultShow="false" controller="check" class="CatalogoPGForm_DialogoCaptura"/>
	  <label for="CatalogoPGForm_DialogoCapturaOtros_chk_Tiene_tc">T&iacute;tulos de Cr&eacute;dito</label>
	    <table width="100%" id="CatalogoPGForm_DialogoCapturaOtros_chk_tC_Table">
	    	<tr>
	    		<td style="width:33%" valign="top">
	    			<div>
	    				<input type="radio" name="CatalogoPGForm_DialogoCaptura_Delegable_tc" id="CatalogoPGForm_DialogoCapturaOtros_DelegA3" value="Delegable" checked="checked" />
	    				<label for="CatalogoPGForm_DialogoCapturaOtros_Deleg1">Delegable</label>
	    			</div>
	    			<div>
	    				<input type="radio" name="CatalogoPGForm_DialogoCaptura_Delegable_tc" id="CatalogoPGForm_DialogoCapturaOtros_DelegB3" value="No Delegable" />
	    				<label for="CatalogoPGForm_DialogoCapturaOtros_Deleg1">No Delegable</label>
	    			</div>
	    			<div>
	    				<input type="radio" name="CatalogoPGForm_DialogoCaptura_Delegable_tc" id="CatalogoPGForm_DialogoCapturaOtros_DelegC3" value="Ninguno" />
	    				<label for="CatalogoPGForm_DialogoCapturaOtros_Deleg1">Ninguno</label>
	    			</div>
	    		</td>
	    		<td  colspan="2"  valign="top"> 
	    			<div>
	    				<input type="radio" name="CatalogoPGForm_DialogoCaptura_Individual_tc" id="CatalogoPGForm_DialogoCapturaOtros_DelegA2C" value="Individual" checked="checked"/>
	    				<label for="CatalogoPGForm_DialogoCapturaOtros_DelegA2">Individual</label>
	    			</div>
	    			<div>
	    				<input type="radio" name="CatalogoPGForm_DialogoCaptura_Individual_tc" id="CatalogoPGForm_DialogoCapturaOtros_DelegB2C" value="Conjuntamente" />
	    				<label for="CatalogoPGForm_DialogoCapturaOtros_DelegB2">Conjuntamente</label>
	    			</div>    
	    				
	    			<div>
	    				<input type="radio" name="CatalogoPGForm_DialogoCaptura_Individual_tc" id="CatalogoPGForm_DialogoCapturaOtros_DelegC2C" value="Conjuntamente o Separadamente" />
	    				<label for="CatalogoPGForm_DialogoCapturaOtros_DelegB2">Conjuntamente o Separadamente</label>
	    			</div>		
	    			<div>
	    				<input type="radio" name="CatalogoPGForm_DialogoCaptura_Individual_tc" id="CatalogoPGForm_DialogoCapturaOtros_DelegD2C" value="Ninguno" />
	    				<label for="CatalogoPGForm_DialogoCapturaOtros_DelegD2">Ninguno</label>
	    			</div>    			
	    		</td>
	    		
	    		
	    		</tr>
	    		<tr>
	    		<td  valign="top">
	    			<div><label for="CatalogoPGForm_DialogoCapturaOtros_txt_caract">Caracter&iacute;sticas/Limitaciones</label></div>
	    			<textarea style="width:98%;" rows="5" cols="15" id="CatalogoPGForm_DialogoCapturaOtros_txt_tc_caracteristicas" class="CatalogoPGForm_DialogoCaptura"></textarea>
	    			     			
	    		</td>
	    		<td valign="top" style="width:33%">
	    			<div><label for="CatalogoPGForm_DialogoCapturaOtros_txt_caract">Forma de Ejercerlo</label></div>
	    			<textarea style="width:98%;" rows="5" cols="15" id="CatalogoPGForm_DialogoCapturaOtros_txt_tc_formaejercerlo" class="CatalogoPGForm_DialogoCaptura"></textarea>
	    		</td>	    		
	    		
	    	</tr>
	    </table>
	  </div>  
	  
	  <div id="CatalogoPGForm_DialogoCaptura_tab_4">
	  <input type="checkbox" id="CatalogoPGForm_DialogoCapturaOtros_chk_Tiene_pc" toggleDiv="#CatalogoPGForm_DialogoCapturaOtros_chk_pC_Table" defaultShow="false" controller="check" class="CatalogoPGForm_DialogoCaptura"/>
	  <label for="CatalogoPGForm_DialogoCapturaOtros_chk_Tiene_pc">Pleitos y Cobranzas</label>
	    <table width="100%" id="CatalogoPGForm_DialogoCapturaOtros_chk_pC_Table">
	    	<tr>
	    		<td style="width:33%" valign="top">
	    			<div>
	    				<input type="radio" name="CatalogoPGForm_DialogoCaptura_Delegable_pc" id="CatalogoPGForm_DialogoCapturaOtros_DelegA4" value="Delegable" checked="checked" />
	    				<label for="CatalogoPGForm_DialogoCapturaOtros_Deleg1">Delegable</label>
	    			</div>
	    			<div>
	    				<input type="radio" name="CatalogoPGForm_DialogoCaptura_Delegable_pc" id="CatalogoPGForm_DialogoCapturaOtros_DelegB4" value="No Delegable" />
	    				<label for="CatalogoPGForm_DialogoCapturaOtros_Deleg1">No Delegable</label>
	    			</div>
	    			<div>
	    				<input type="radio" name="CatalogoPGForm_DialogoCaptura_Delegable_pc" id="CatalogoPGForm_DialogoCapturaOtros_DelegC4" value="Ninguno" />
	    				<label for="CatalogoPGForm_DialogoCapturaOtros_Deleg1">Ninguno</label>
	    			</div>
	    		</td>
	    		<td  colspan="2"  valign="top">
	    			<div>
	    				<input type="radio" name="CatalogoPGForm_DialogoCaptura_Individual_pc" id="CatalogoPGForm_DialogoCapturaOtros_DelegA2D" value="Individual" checked="checked"/>
	    				<label for="CatalogoPGForm_DialogoCapturaOtros_DelegA2">Individual</label>
	    			</div>
	    			<div>
	    				<input type="radio" name="CatalogoPGForm_DialogoCaptura_Individual_pc" id="CatalogoPGForm_DialogoCapturaOtros_DelegB2D" value="Conjuntamente" />
	    				<label for="CatalogoPGForm_DialogoCapturaOtros_DelegB2">Conjuntamente</label>
	    			</div>    	
	    				
	    			<div>
	    				<input type="radio" name="CatalogoPGForm_DialogoCaptura_Individual_pc" id="CatalogoPGForm_DialogoCapturaOtros_DelegC2D" value="Conjuntamente o Separadamente" />
	    				<label for="CatalogoPGForm_DialogoCapturaOtros_DelegB2">Conjuntamente o Separadamente</label>
	    			</div> 	
	    			<div>
	    				<input type="radio" name="CatalogoPGForm_DialogoCaptura_Individual_pc" id="CatalogoPGForm_DialogoCapturaOtros_DelegD2D" value="Ninguno" />
	    				<label for="CatalogoPGForm_DialogoCapturaOtros_DelegD2">Ninguno</label>
	    			</div>
	    			
	    		</td>
	    		
	    	</tr>
	    	<tr>
	    		
	    		<td  valign="top">
	    			<div><label for="CatalogoPGForm_DialogoCapturaOtros_txt_caract">Caracter&iacute;sticas/Limitaciones</label></div>
	    			<textarea style="width:98%;" rows="5" cols="15" id="CatalogoPGForm_DialogoCapturaOtros_txt_pc_caracteristicas" class="CatalogoPGForm_DialogoCaptura"></textarea>
	    			     			
	    		</td>
	    		<td  valign="top" style="width:33%">
	    			<div><label for="CatalogoPGForm_DialogoCapturaOtros_txt_caractD">Forma de Ejercerlo</label></div>
	    			<textarea style="width:98%;" rows="5" cols="15" id="CatalogoPGForm_DialogoCapturaOtros_txt_pc_formaejercerlo" class="CatalogoPGForm_DialogoCaptura"></textarea>
	    		</td>	    		
	    	</tr>
	    </table>
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
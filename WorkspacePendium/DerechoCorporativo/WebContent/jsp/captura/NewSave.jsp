<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="mx.com.televisa.derechocorporativo.util.FacesUtils"%>
<%@page import="mx.com.televisa.derechocorporativo.modules.captura.*"%>
<%@page import="mx.com.televisa.derechocorporativo.util.ObtenerEmpresaDenominacioActual"%>

<html>
<head>

<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<%@include file="/css/kaz-styles.jsp"%>
<%-- 
<%@include file="part/validaciones.jsp"%>
--%>
<script type="text/javascript" src="<c:out value="${applicationBean.contextPath}"/>/js/jquery/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="<c:out value="${applicationBean.contextPath}"/>/js/jsCal/jquery.mask.js"></script>
<script type="text/javascript" src="<c:out value="${applicationBean.contextPath}"/>/js/ejercicio/formato.js"></script>

<%-- 
<script type="text/javascript" src="<c:out value="${applicationBean.contextPath}"/>/js/jquery/jquery-1.4.3.min.js"></script>
--%>

<script type="text/javascript" src="<c:out value="${applicationBean.contextPath}"/>/js/ajax/simpleAjaxUtil.js"></script>

<script src="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.min.js"></script> 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.css">
<script type="text/JavaScript"> 
/*jQuery(window).bind('beforeunload', function(){
    return 'Existen cambios pendientes por guardar';
});*/

$(document).ready(function() {
	
	if(globalSectionId != 16 && globalSectionId != 22 &&
	   globalSectionId != 23 && globalSectionId != 26 &&
	   globalSectionId != 27 && globalSectionId != 28){
		formmodified=0;
	    $('form *').change(function(){
	        formmodified=1;
	    });
	    window.onbeforeunload = confirmExit;
	    function confirmExit() {
	        if (formmodified == 1) {
	            return "No se han guardado cambios";
	        }
	    }
	    $("input[name='submit']").click(function() {
	        formmodified = 0;
	    });
	}
    
});
</script>

<%-- 
<script type="text/javascript" src="<c:out value="${applicationBean.contextPath}"/>/js/jquery/jquery-1.11.4.js"></script>
--%>

<%--
<script src="<c:out value="${applicationBean.contextPath}"/>/js/multiSelect/jquery.multiple.select.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/multiSelect/multiple-select.css">
--%>

<%@include file="/jsp/components/calendar/include_calendar.jsp"%>
<%@include file="/jsp/components/jquery-ui/include_purr.jsp"%>
<%@include file="/jsp/components/session_timeout/session_timeout.jsp"%>
<%-- @include file="/jsp/components/jquery-ui/include_dialog.jsp" 
--%>

<%--
<script src="<%=request.getContextPath()%>/js/SpryAssets/SpryAccordion.js" type="text/javascript"></script>
<link href="<%=request.getContextPath()%>/js/SpryAssets/SpryAccordion.css" rel="stylesheet" type="text/css" />
 --%>
 
<script src="<%=request.getContextPath()%>/js/SpryAssets/SpryTabbedPanels.js" type="text/javascript"></script>
<link href="<%=request.getContextPath()%>/js/SpryAssets/SpryTabbedPanels.css" rel="stylesheet" type="text/css" />



<script type="text/javascript" src="<c:out value="${applicationBean.contextPath}"/>/js/accounting/accounting.min.js"></script>


<script type="text/javascript" src="part/flexTab.js"></script>

<script type="text/javascript" src="part/ajaxPage.js"></script>

<script type="text/javascript" src="part/pupUp.js"></script>

<script type="text/javascript" src="apoderados/apoderados.js"></script>

<script type="text/javascript" src="reformas/reformas.js"></script>

<%@include file="part/validaciones.jsp"%>

<%--@include file="part/dialogStyle.jsp"--%>


<%
	//ECM 25 Agosto 2015
	//String lstEditCon = request.getParameter("Edit")==null?"":request.getParameter("Edit");
	//FacesUtils.getSessionBean().setEditCon(lstEditCon);
	
	
	String lstEditCon = FacesUtils.getSessionBean().getEditCon();
	
	//out.println("*" + lstEditCon + "*");
	
	
	
	
	//ECM 20 Julio 2015
	String lstIdCurrentEmpresa = request.getParameter("idEmp");
	String idSeccion = request.getParameter("idSeccion");
	
	if(lstEditCon.equals("")){
		switch (idSeccion){
			case "25":
				lstEditCon = "Flex";
				break;
			case "16":
				lstEditCon = "Flex";
				break;
			case "22":
				lstEditCon = "Flex";
				break;
			case "23":
				lstEditCon = "Flex";
				break;
			case "26":
				lstEditCon = "Flex";
				break;
			case "27":
				lstEditCon = "Flex";
				break;
			case "28":
				lstEditCon = "Flex";
				break;
		}
	}
	//System.out.println("idSeccion: "+idSeccion);
	FacesUtils.getSessionBean().setIdCurrentEmpresa(request.getParameter("idEmp"));
	
	String lstEmpresaDenominacionActual = ObtenerEmpresaDenominacioActual.getEmpresaDenominacionActual(lstIdCurrentEmpresa);
	//FacesUtils.getSessionBean().setNomEmpresa(request.getParameter("NomEmp"));
	FacesUtils.getSessionBean().setNomEmpresa(lstEmpresaDenominacionActual);
	
	String lstNomUser = FacesUtils.getSessionBean().getNomUser();
	if (lstNomUser == null) {
		response.sendRedirect("/DerechoCorporativo/faces/jsp/home/error.jsp");
		
	}
%>

<script type="text/javascript">

	function waitBar() {
		
		document.getElementById('imgCapWait').style.display = '';
		document.getElementById('btnGuardar').style.display = 'none';
	}

	function go(url){
		window.location=url;
	}
	
	var globalSectionId = '<%=idSeccion%>';

</script>


</head>
<body onLoad="" class="fontmenu" leftmargin="0" rightmargin="0" topmargin="0" marginwidth="0" marginheight="0" >

	<%--@include file="part/dialogForm.jsp"--%>

	<form method="post" action="">

		<table width="95%" height="100%" border="0" align="center">
			<tr>
				<td>
				
					<%-- jsp:include page="/jsp/components/pages_border/open-pre-title.jsp"></jsp:include>--%>
					
					<!--
						<table style="width:95%">
						<tr>
							<td width=170><span id="idTitle" style="font-size: 9pt"><%=FacesUtils.getSessionBean().getNomEmpresa()%></span></td>
							
		
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						
							<%
							if(lstEditCon.equals("")){
							%>
								<td width=95><input id= "btnGuardar" type="submit" name="submit" value="Guardar Cambios" style="padding: 3px; border: 0px solid white; margin: 0 0 3px 0; background: rgb(192,192,192); width: 50%; font-family: sans-serif; font-size: 14px; color:black;"></td>
								<td width=95><input id= "btnCancelar"  type="button" name="cerrar" value="Cancelar" onClick="closePerspective()" style="padding: 3px; border: 0px solid white; margin: 0 0 3px 0; background: rgb(192,192,192); width: 50%; font-family: sans-serif; font-size: 14px; color:black;"></td>
							<%
							}
							%>



						</tr>
						</table>
						-->


					<%-- jsp:include page="/jsp/components/pages_border/open-post-title.jsp"></jsp:include--%>
					
					<!-- open-post-title.jsp -->
					<table  border="0" cellpadding="0" cellspacing="0">
					<tr>
					<td
						background="<c:out value="${applicationBean.contextPath}"/>/img/borders/page_border/border04_azul.png">
					</td>
					<td width="100%"
						background="<c:out value="${applicationBean.contextPath}"/>/img/borders/page_border/border05.png">
					<!-- open-post-title.jsp -->	
						
					

					<table width="98%" border="0" cellspacing="2" cellpadding="3"
						id="sec_1_1">
						<tr>
							<td width="60%">
								<%=RequestHandler.handleRequest(request)%>
								<div id='debugMessage'></div>
							</td>
							<td width="20%">
							
								<!-- style="padding: 3px; border: 0px solid white; margin: 0 0 3px 0; background: rgb(192,192,192); width: 50%; font-family: sans-serif; font-size: 14px; color:black;"  -->
							
								<%
							if(lstEditCon.equals("")){
							%>
								<input id= "btnGuardar" type="submit" name="submit" value="Guardar Cambios" onclick="waitBar()">
								
								<img src='<%= request.getContextPath() %>/img/wait-bar.gif' id='imgCapWait' style="display: none;">
								
									
								<%
								} else {
								%>
								
								<div style="display: none">
								<input id= "btnGuardar" type="submit" name="submit" value="Guardar Cambios" onclick="waitBar()">
								
								<img src='<%= request.getContextPath() %>/img/wait-bar.gif' id='imgCapWait' style="display: none;">
								</div>
									
								<%
								}
								%>
								
							</td>
							<td width="20%">
							
								<!-- style="padding: 3px; border: 0px solid white; margin: 0 0 3px 0; background: rgb(192,192,192); width: 50%; font-family: sans-serif; font-size: 14px; color:black;" -->
							

								<input id= "btnCancelar"  type="button" name="cerrar" value="Salir" onClick="go('<%=FacesUtils.getContextPath()%>/faces/jsp/captura/capWait.jsp?idEmp=<%=lstIdCurrentEmpresa%>&salida=true&idSeccion=<%=idSeccion %>');" >

							</td>
						</tr>
						<!-- 
			             <tr>
			               <td align="right">&nbsp;</td>
			                <td colspan="2" align="right"><input type="submit" name="submit" value="Guardar Cambios"></td>
			              </tr>
			             -->
						<tr>
							<td colspan="3">
								<%=Pagina.getHTML(idSeccion)%>
							</td>
						</tr>
					</table> 
					
					
					<%-- 
					<script type="text/javascript">
						var Accordion1 = new Spry.Widget.Accordion(
								"Accordion1");
					</script>
					--%>
					
					<%
						String addSpryCode = (String) session.getAttribute("addSpryCode");
						
						if(addSpryCode == null) {
							
							addSpryCode = "";
						}
						session.setAttribute("menu", "0");
					%>
					
					
					<script type="text/javascript">
						
						<%--
						var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1"<%=addSpryCode%>);
						--%>
					
						
						loadDefaultStatusValorNominalMonedas();
					</script>
					
					
					<jsp:include page="/jsp/components/pages_border/close.jsp"></jsp:include>


				</td>
			</tr>
		</table>
	</form>
	
</body>
</html>
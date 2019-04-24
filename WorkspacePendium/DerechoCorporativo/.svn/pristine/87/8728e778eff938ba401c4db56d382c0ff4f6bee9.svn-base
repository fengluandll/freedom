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
<link rel="Stylesheet" href="css/bootstrap.min.css" type="text/css"><link>
--%>

<script type="text/javascript" src="<c:out value="${applicationBean.contextPath}"/>/js/jquery/jquery-2.1.4.min.js"></script>


<script type="text/javascript" src="<c:out value="${applicationBean.contextPath}"/>/js/ajax/simpleAjaxUtil.js"></script>
<script src="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.min.js"></script> 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.css">
<script type="text/JavaScript"> 
/*jQuery(window).bind('beforeunload', function(){
    return 'Existen cambios pendientes por guardar';
});*/
/*$(document).ready(function() {
	
    formmodified=0;
    $('form *').change(function(){
        formmodified=1;
    });
    window.onbeforeunload = confirmExit;
    function confirmExit() {
        if (formmodified == 1) {
            return "No se han guardado cambios Argu";
        }
    }
    $("input[name='submit']").click(function() {
        formmodified = 0;
    });
});
*/
function cambiar_color_over(celda){ 
	   celda.style.backgroundColor="#212A46" 
	} 
function cambiar_color_out(celda){ 
celda.style.backgroundColor="#2B385D" 
}
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



<%@include file="part/validacionesMenu.jsp"%>


<%--@include file="part/dialogStyle.jsp"--%>


<%
	//ECM 25 Agosto 2015
	//String lstEditCon = request.getParameter("Edit")==null?"":request.getParameter("Edit");
	//FacesUtils.getSessionBean().setEditCon(lstEditCon);
	
	
	String lstEditCon = FacesUtils.getSessionBean().getEditCon();
	
	//out.println("*" + lstEditCon + "*");
	
	
	
	
	//ECM 20 Julio 2015
	String lstIdCurrentEmpresa = request.getParameter("idEmp");
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


	//var globalSectionId = '0';
	
</script>


</head>
<body onLoad="" class="fontmenu" leftmargin="0" rightmargin="0" topmargin="0" marginwidth="0" marginheight="0" >

	<form method="post" action="">

		<table width="95%" height="100%" border="0" align="center">
			<tr>
				<td>
				
					
					<table  border="0" cellpadding="0" cellspacing="0">
					<tr>
					<td
						background="<c:out value="${applicationBean.contextPath}"/>/img/borders/page_border/border04_azul.png">
					</td>
					<td width="100%"
						background="<c:out value="${applicationBean.contextPath}"/>/img/borders/page_border/border05.png">
					<!-- open-post-title.jsp -->	
						
					<%
						String msg = "";
						int idUsrOcu=0;
						String nomUsrOcu = "";
						if (session.getAttribute("msg")!=null){
							msg= session.getAttribute("msg").toString();
							session.removeAttribute("msg");
							if(msg.equals("OCUPADA")){
								if(session.getAttribute("idUserOcupa")!=null){
									idUsrOcu = Integer.parseInt(session.getAttribute("idUserOcupa").toString());
								}
								nomUsrOcu=Pagina.getNomUsrOcupado(idUsrOcu);
								System.out.println("nomUsr: "+nomUsrOcu);
					%>
								<h4 align="center" style="color:#FF0000">La sección que intentas editar se encuentra en edición por <%=nomUsrOcu%>.</h4>
					<% 	
							}
							else if(msg.equals("REPETIDO")){
					%>
								<h4 align="center" style="color:#FF0000">Únicamente puedes editar una sección a la vez.</h4>
					<%
							}
						}
					%>

					<table width="98%" border="0" cellspacing="2" cellpadding="3"
						id="sec_1_1">
						<tr>
							<td width="60%">
								<%=RequestHandler.handleRequest(request)%>
								<div id='debugMessage'></div>
							</td>
							<td width="20%">
								
								<!--  						
								<%
								if(lstEditCon.equals("")){
								%>
								<input id= "btnGuardar" type="submit" name="submit" value="Guardar Cambios" onclick="waitBar()">
								
								<img src='<%= request.getContextPath() %>/img/wait-bar.gif' id='imgCapWait' style="display: none;">
								
									
								<%
								}
								%> -->
								
							</td>
							<td width="20%">
														
								
								<input id= "btnCancelar"  type="button" name="cerrar" value="Salir" onClick="go('<%=request.getContextPath()%>/faces/jsp/captura/NewSaveMenu.jsp?idEmp=<%=request.getParameter("idEmp")%>')">
								
								
							</td>
						</tr>
						<tr>
							<td colspan="3">
								<%--=Pagina.getHTML()--%>
								<center>
								<%=Pagina.getMenuReforma()%>
								</center>
							</td>
						</tr>
					</table> 
					
					<%
						String addSpryCode = (String) session.getAttribute("addSpryCode");
						
						if(addSpryCode == null) {
							
							addSpryCode = "";
						}
						
					%>
					
					<script type="text/javascript">
						var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1"<%=addSpryCode%>);
						///loadDefaultStatusValorNominalMonedas();
					</script>
					
					
					<jsp:include page="/jsp/components/pages_border/close.jsp"></jsp:include>


				</td>
			</tr>
		</table>
	</form>
	
</body>
</html>
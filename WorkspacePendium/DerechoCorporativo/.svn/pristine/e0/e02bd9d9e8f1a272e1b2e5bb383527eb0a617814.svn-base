<%@page import="mx.com.televisa.derechocorporativo.util.StringUtils"%>
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


<script type="text/javascript" src="<c:out value="${applicationBean.contextPath}"/>/js/jquery/jquery-2.1.4.min.js"></script>

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

/*
$(document).ready(function() {
    formmodified=0;
    $('form *').change(function(){
        formmodified=1;
    });
    window.onbeforeunload = confirmExit;
    function confirmExit() {
        if (formmodified == 1) {
            return "Existen cambios que no han sido guardados.¿En verdad desea salir sin guardar?";
        }
    }
    $("input[name='submit']").click(function() {
        formmodified = 0;
    });
});
*/

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
<!-- Se quito session timeOut -->


<%-- @include file="/jsp/components/jquery-ui/include_dialog.jsp" 
--%>

<%--
<script src="<%=request.getContextPath()%>/js/SpryAssets/SpryAccordion.js" type="text/javascript"></script>
<link href="<%=request.getContextPath()%>/js/SpryAssets/SpryAccordion.css" rel="stylesheet" type="text/css" />
 --%>
 




<script type="text/javascript" src="<c:out value="${applicationBean.contextPath}"/>/js/accounting/accounting.min.js"></script>


<script type="text/javascript" src="part/flexTab.js"></script>

<script type="text/javascript" src="part/ajaxPage.js"></script>

<script type="text/javascript" src="part/pupUp.js"></script>

<script type="text/javascript" src="apoderados/apoderados.js"></script>

<script type="text/javascript" src="reformas/reformas.js"></script>

<%--@include file="part/validaciones.jsp"--%>

<%--@include file="part/dialogStyle.jsp"--%>


<%
	
	//ECM 25 Agosto 2015
	//String lstEditCon = request.getParameter("Edit")==null?"":request.getParameter("Edit");
	//FacesUtils.getSessionBean().setEditCon(lstEditCon);
	
	
	String lstEditCon = FacesUtils.getSessionBean().getEditCon();
	
	
	
	
	String ce = FacesUtils.getSessionBean().getIdCurrentEmpresa();
	
	if(!request.getParameter("idEmp").equals(ce)) {
		
		session.setAttribute("addSpryCode","");
	}
	
	
	
	
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

</head>
<body onLoad="" class="fontmenu" leftmargin="0" rightmargin="0" topmargin="0" marginwidth="0" marginheight="0" >

	<%--@include file="part/dialogForm.jsp"--%>

	<form method="post" action="">

		<table width="95%" height="100%" border="0" align="center">
			<tr>
				<td>
				
					<jsp:include page="/jsp/components/pages_border/open-pre-title.jsp"></jsp:include>
						<table style="width:95%">
						<tr>
							<td width="55%" align='left'>
								<span id="idTitle" style="font-size: 10pt">
									<%=FacesUtils.getSessionBean().getNomEmpresa()%>
								</span>
							</td>
							<!--
		
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							-->
							<%
							//if(lstEditCon.equals("")){
							%>
								<!-- <td width=95><input id= "btnGuardar" type="submit" name="submit" value="Guardar Cambios" style="padding: 3px; border: 0px solid white; margin: 0 0 3px 0; background: rgb(192,192,192); width: 50%; font-family: sans-serif; font-size: 14px; color:black;"></td>
								<td width=95><input id= "btnCancelar"  type="button" name="cerrar" value="Cancelar" onClick="closePerspective()" style="padding: 3px; border: 0px solid white; margin: 0 0 3px 0; background: rgb(192,192,192); width: 50%; font-family: sans-serif; font-size: 14px; color:black;"></td>
								 -->
							<%
							//}
							%>



							<%
									String tabLabel = "Resumen General";
									//String tabScript = "";
							
									String tabName = (String) session.getAttribute("tabName");
									//String subTabName = (String) session.getAttribute("subTabName");
							
									
									if(tabName != null) {
										
										tabName = StringUtils.latin1ToUTF8(tabName);
										
										tabLabel = tabName;
										//tabScript = "currentTabName = '" + tabName + "';";
									}
									
									/*if(subTabName != null) {
										
										tabLabel = tabLabel + "<br>" + subTabName;
									}*/
									String menu = session.getAttribute("menu").toString();
							%>
							

							<td width="45%" align="right">
								<i>
								<span id="spanTabName2" style="font-size: 9pt"></span><br>
								<span id="spanTabName" style="font-size: 9pt">&nbsp;</span>
								<%
									if(!menu.equals(null)){
										if(!menu.equals("1")){
								%>
									<%=tabLabel %>
								<%
										}
									}
								%>
								</span>
								</i>
							</td>
							

							<%--
							<script type="text/javascript" language="javascript">
								<%=tabScript %>
							</script>
 							--%>
						</tr>
						</table>


					<!-- open-post-title -->
					</td>
					<td><img
						src="<c:out value="${applicationBean.contextPath}"/>/img/borders/page_border/border03_azul.png">
					</td>
					</tr>
					</table>
					<!-- open-post-title -->

				</td>
			</tr>
		</table>
	</form>
	
</body>
</html>
<%
	response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", 0); //prevents caching at the proxy server
%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="mx.com.televisa.derechocorporativo.util.FacesUtils"%>
<%@page import="mx.com.televisa.derechocorporativo.daos.Abc_AppConfigTabDAO"%>
<html>
<head>
<script type="text/javascript" src="<c:out value="${applicationBean.contextPath}"/>/js/ajax/simpleAjaxUtil.js"></script>
<script type="text/javascript" src="../../../js/jquery/jquery-2.1.4.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Insert title here</title>
<%@include file="/css/kaz-styles.jsp"%>

</head>
<body
	style="background-image: url('<c:out value="${applicationBean.contextPath}"/>/img/header_azul_1440_76.jpg');">
	<f:view>

		<table width="100%"  border="0" cellspacing="0" cellpadding="0">
		<!-- 
			<tr>
				<td height="10">&nbsp;</td>
			</tr>
		 -->	
			<tr>
				<td></td>
				<td align="right" class="coloredText " width="300">

					<table>
						<tr>
                            <%Abc_AppConfigTabDAO lsAbc_AppConfigTabDAO = new Abc_AppConfigTabDAO(); %>
							<td align="center" colspan="2"><%=lsAbc_AppConfigTabDAO.getAmbiente().toUpperCase()%></td>
						</tr>
					
						<tr>
							<td align="center" colspan="2" style="">Bienvenido: <%=FacesUtils.getSessionBean().getNomUser()%></td>
						</tr>
						<tr>
							<td width="50%"></td>
						<td align="center"><a id="linkSalir"
								href="<c:out value="${applicationBean.contextPath}"/>/LoginExitServlet"
								target="_top">Salir del Sistema</a></td>
						</tr>
						<tr>
							<td colspan="">&nbsp;</td>
						</tr>
						
					</table>
				</td>
			</tr>
		</table>

	</f:view>
<script language="JavaScript" type="text/javascript">
	window.onload = function(){
		    setInterval(function(){
	            var ajaxRequest = simpleAjaxUtil_getXmlHttpObject();
	            if (ajaxRequest==null) {alert ("Browser does not support HTTP Request");return;}

	            var contexto = $(location).attr('href');
	            contexto = contexto.substring(0, contexto.indexOf('/faces'));
	    	    var url = contexto + '/LogStatConectServlet';
	    	    var parameters = "";
                //alert(url);
	    	    ajaxRequest.open("post", url, true);
	    	    ajaxRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	    	    ajaxRequest.onreadystatechange=function(){

	    		    if(ajaxRequest.readyState == 4 && ajaxRequest.status==200){
	    		    	//alert('Insert con Éxito');
	    		    } 
	    	    };ajaxRequest.send(parameters);

		    }, 60000);
	}
</script>
</body>
</html>
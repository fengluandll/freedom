<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:h="http://java.sun.com/jsf/html">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<script src="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.min.js"></script> 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.css">
<title>Insert title here</title>
<%@include file="/css/kaz-styles.jsp"%>
<%@include file="/jsp/components/session_timeout/session_timeout.jsp"%>
<style type="text/css">
body {
	/*background-image:
		url("<c:out value='${applicationBean.contextPath}'/>/img/page_background.jpg");
	background-attachment:fixed;*/
	/*margin-top: 0;
		margin-left: 0;
		margin-right: 0;
		margin-bottom: 0;*/
}
</style>
</head>
<body>
	<f:view>

		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>

					<table width="100%" align="center">
						<tr>
							<td align="center">
							
							<%-- 
							<jsp:include
									page="/jsp/components/pages_border/open-pre-title.jsp"></jsp:include>
								Bienvenido <jsp:include
									page="/jsp/components/pages_border/open-post-title.jsp"></jsp:include>
 						--%>

								<%--<img src="../../img/Splash.jpg"></img> --%>
								<%--<img src="../../img/TVSA 534_2.png"></img> --%>

	
		
							<%--
								<jsp:include
									page="/jsp/components/pages_border/close.jsp"></jsp:include></td>
									
									  --%>
								
						</tr>
					</table>


				</td>
			</tr>

			<tr>
				<td>&nbsp;</td>
			</tr>

		</table>

	</f:view>
</body>
</html>
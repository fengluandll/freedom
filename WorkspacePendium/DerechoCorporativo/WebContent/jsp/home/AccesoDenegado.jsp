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
<title>Insert title here</title>
<%@include file="/css/kaz-styles.jsp"%>

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
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>

					<table width="60%" align="center">
						<tr>
							<td><jsp:include
									page="/jsp/components/pages_border/open-pre-title.jsp"></jsp:include>
							
									Acceso Denegado
								
							<jsp:include
									page="/jsp/components/pages_border/open-post-title.jsp"></jsp:include>
									
									<br />
									<span style="color:red;font-size: 14px;">No fué posible procesar lo solicitud, Solo el usuario de Operaciones (USEROPER) puede entrar a esta configuraci&oacute;n</span>
								
								<jsp:include
									page="/jsp/components/pages_border/close.jsp"></jsp:include></td>
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
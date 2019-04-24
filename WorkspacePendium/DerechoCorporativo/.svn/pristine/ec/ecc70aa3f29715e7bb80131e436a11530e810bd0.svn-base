<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="mx.com.televisa.derechocorporativo.util.FacesUtils"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pendium</title>

<%@include file="/css/kaz-styles.jsp"%>

<style type="text/css">
body {
	margin-top: 0;
	margin-left: 0;
	margin-right: 0;
	margin-bottom: 0;
}
</style>

</head>
<body>
	<f:view>
		<h:form>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td height="76px"
						background="<c:out value="${applicationBean.contextPath}"/>/img/header_azul_1440_76.jpg">
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>

						
						<table width="60%" align="left">
							<tr>
								<td>
								
								<%-- 
								<jsp:include
										page="/jsp/components/pages_border/open-pre-title.jsp"></jsp:include>
									Bienvenido a KazApp <jsp:include
										page="/jsp/components/pages_border/open-post-title.jsp"></jsp:include>

									KazApp es la aplicación informática desarrollada internamente
									por Kaz para la administración de sus propios procesos y
									recursos, entre ellos, el control de recursos humanos,
									clientes, ventas y facturación. <jsp:include
										page="/jsp/components/pages_border/close.jsp"></jsp:include>
										--%>
										
										
										
										</td>
							</tr>
						</table>
						 

					</td>
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


						<table width="40%" align="center">
							<tr>
								<td>
								
								
								<jsp:include
										page="/jsp/components/pages_border/open-pre-title.jsp"></jsp:include>
									Defina una nueva Constraseña de Acceso
									
								<jsp:include
										page="/jsp/components/pages_border/open-post-title.jsp"></jsp:include>

									<!-- 
			<jsp:include page="/jsp/components/aqua_pupup_small/open.jsp"></jsp:include>
			 -->
									<table width="80%" align="center">
										<tr>
											<th colspan="2"></th>
										</tr>
										<tr>
											<td>Nombre de Usuario:</td>
											<td><%=FacesUtils.getSessionBean().getNomUser()%>
											</td>
										</tr>
										<tr>
											<td>Contraseña Anterior:</td>
											<td><h:inputSecret value="#{changePasswordBean.txtOdlPassword}"></h:inputSecret></td>
										</tr>
										<tr>
											<td>Contraseña Nueva Constraseña:</td>
											<td><h:inputSecret value="#{changePasswordBean.txtPassword1}"></h:inputSecret></td>
										</tr>
										<tr>
											<td>Confirmación de Contraseña:</td>
											<td><h:inputSecret value="#{changePasswordBean.txtPassword2}"></h:inputSecret></td>
										</tr>
										<tr>
											<td colspan="2" class= "loginMessages"><h:outputText
													value="#{changePasswordBean.lblMessage}"></h:outputText></td>
										</tr>
										<tr>
											<td></td>
											<td><h:commandButton value="Guardar"
													action="#{changePasswordBean.doChange}"></h:commandButton></td>
										</tr>
									</table> <!-- 
			<jsp:include page="/jsp/components/aqua_pupup_small/close.jsp"></jsp:include>
			--> <jsp:include page="/jsp/components/pages_border/close.jsp"></jsp:include>

								</td>
							</tr>
						</table>

					</td>
				</tr>

			</table>




		</h:form>
	</f:view>
</body>
</html>
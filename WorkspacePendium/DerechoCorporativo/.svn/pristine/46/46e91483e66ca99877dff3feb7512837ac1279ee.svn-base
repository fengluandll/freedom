<%@page import="mx.com.televisa.derechocorporativo.daos.Abc_AppConfigTabDAO"%>
<%@page import="mx.com.televisa.derechocorporativo.bean.Abc_AppConfigTabBean"%>
<%@page import="mx.com.televisa.derechocorporativo.bean.SessionBean"%>
<%@page import="java.util.ArrayList"%>
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
<script type="text/javascript" src="../captura/part/pupUp.js"></script>
 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.css">
<title>Altas, bajas, cambios AppConfigTab</title>
<%@include file="../../css/kaz-styles.jsp"%>


<%		SessionBean sessionBean = (SessionBean)session.getAttribute("sessionBean");
		Abc_AppConfigTabDAO laAppConfigTabDAO = new Abc_AppConfigTabDAO();
		boolean paso = laAppConfigTabDAO.isUserOper(Integer.parseInt(sessionBean.getIdUser()));
		if(!paso){
			response.sendRedirect(request.getContextPath()+"/faces/jsp/home/AccesoDenegado.jsp");
		}
		ArrayList<Abc_AppConfigTabBean> alAppConfigTabBean = laAppConfigTabDAO.ConsultaAppConfigTab();
%>
<html>
	<body>
	<!-- <form action="abc_appConfigTab.jsp" method="post"> -->
		<div id='flexTable_Abc_AppConfigTab'>
			<table width='90%' align='center' class='flexTable' border='0' cellspacing='3' cellpadding='3'>
				<tr>
					<th colspan="4">Altas, bajas, cambios AppConfigTab
					</th>
					<td align='center'>
						<!-- <a onClick="openNew()"> <img src='../../img/icons/new.png'> </a> -->
						<td class="centerNew">
							<a href="<c:out value="${applicationBean.contextPath}"/>/faces/jsp/admin/abc_appConfigTabNewEdit.jsp">
								<abbr title="Nuevo"><img src="../../img/icons/new.png" alt="Nuevo" /></abbr>
							</a>
						</td>
					</td>
				</tr>
				<tr>
					<th class='tableHeader'>Código
					</th>
					<th class='tableHeader'>Nombre
					</th>
					<th class='tableHeader'>Descripción
					</th>
					<th class='tableHeader'>Valor
					</th>
					<th class='tableHeader'>Editar
					</th>
					<th class='tableHeader'>Borrar
					</th>
				</tr>
	
				<%
				boolean alterClass = false;
				String trStyle = "";
				for(Abc_AppConfigTabBean laAppConfigTabBean : alAppConfigTabBean){
					alterClass = !alterClass;
					trStyle = (alterClass)?"class=\"tableRow2\"" : "";
				%>
				<tr  <%=trStyle%> >
					<td><%=laAppConfigTabBean.getCodConfig() %></td>
					<td><%=laAppConfigTabBean.getNomConfig() %></td>
					<%if(laAppConfigTabBean.getCodConfig().equals("PWD_DOC")){ %>
						<td><%=laAppConfigTabBean.getDesConfig()%></td>
						<td>********</td>
					<%}else{ %>
						<td><%=laAppConfigTabBean.getDesConfig()%></td>
						<td><%=laAppConfigTabBean.getValConfig() %></td>
					<%} %>
					<td align='center'>
						<a class="encima" href="<c:out value="${applicationBean.contextPath}"/>/Abc_appConfigTabServlet?action=edita&id=<%=  laAppConfigTabBean.getIdConfig()%>
																																	&cod=<%= laAppConfigTabBean.getCodConfig().replace("&","'%26'")%>
																																	&nom=<%= laAppConfigTabBean.getNomConfig().replace("&","'%26'")%>
																																	&des=<%= laAppConfigTabBean.getDesConfig().replace("&","'%26'")%>
																																	&val=<%= laAppConfigTabBean.getValConfig().replace("&","'%26'")%>">
							<abbr title="Editar"><img alt="Editar" src="../../img/icons/edit.png"></abbr>
						</a>
					</td>

					<td align="center"><a class="encima" href="#"  onclick="swal({ title: 'Confirmar', text: '&iquest;Est&aacute; seguro que desea borrar este registro?', type: 'warning', showCancelButton: true, confirmButtonColor: '#DD6B55', confirmButtonText: 'Si', closeOnConfirm: false }, function(){ window.location = '/DerechoCorporativo/faces/Abc_appConfigTabServlet?action=elimina&id=<%= laAppConfigTabBean.getIdConfig() %>'; return false}) "><abbr title="Eliminar"><img alt="Borrar" src="../../img/icons/delete.png"></abbr> </a> </td>
				</tr>
				<%}%>
			</table>
		</div>

</body>
</html>
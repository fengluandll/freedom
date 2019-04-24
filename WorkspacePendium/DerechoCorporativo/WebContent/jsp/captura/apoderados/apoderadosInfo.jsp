<%@page import="mx.com.televisa.derechocorporativo.util.FacesUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>

<script type="text/javascript">
window.onload = function(){
	alert('entro 2');
	var idEscr = document.getElementById("selectEscritura").value;
	alert(idEscr);
}
</script>



</head>
<body>


 

<% String estructuraId = request.getParameter("escrituraId");
   String tipoPoderId  = request.getParameter("tipoPoderId");
   String sgrupo       = new String(request.getParameter("grupo").getBytes("iso-8859-1"), "UTF-8");
   //System.out.println("estructuraId "+estructuraId);
   //System.out.println("sgrupo "+sgrupo);
   //System.out.println("sgrupo "+sgrupo);
	
   session.setAttribute("escrituraId", estructuraId);
   session.setAttribute("tipoPoderId", tipoPoderId);
   session.setAttribute("grupo"      , sgrupo);
   %>


 




<fieldset>
	<legend class="legendThin">Apoderados</legend>
<input id="context" type="hidden" value="${applicationBean.requesturl}"/>
	<table width="100%">
		
		<tr>
			<td width="20%"></td>
			<td>
				<%
					FacesUtils.getSessionBean().getApoderadosSessionVars().setCustomIdCatalogo("32");
					FacesUtils.getSessionBean().getApoderadosSessionVars().setCustomIdComponent("listApoderados");
				%>
				
				<div id="listApoderadosDiv">
				
					<%@include file="catCommon.jsp" %>
				</div>         
			</td>
			<td>
				<table>
					<tr>
						<td>
							<abbr title="Agregar"><img style="cursor: pointer;" alt="Agregar" src="<c:out value="${applicationBean.contextPath}"/>/jsp/captura/img/Play.png" onclick="addRow('listApoderados','listApoderadosDiv','32','agregar','catCommon.jsp','listApoderadosAsignadosDiv','infoCommon.jsp')"></abbr>	
						</td>
						<td>
							 <div id="ajaxloadingApo" style="position:relative; text-align:center;display:none ;">
								<img src="<c:out value="${applicationBean.contextPath}"/>/img/ajax-loader_chico.gif" />
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<abbr title="AgregarTodos"><img style="cursor: pointer;" alt="AgregarTodos" src="<c:out value="${applicationBean.contextPath}"/>/jsp/captura/img/Fast-forward.png" onclick="addRow('listApoderados','listApoderadosDiv','32','agregarTodos','catCommon.jsp','listApoderadosAsignadosDiv','infoCommon.jsp')"></abbr>	
						</td>
					</tr>
					<tr>
						<td>
							<abbr title="Quitar"><img style="cursor: pointer;" alt="Quitar" src="<c:out value="${applicationBean.contextPath}"/>/jsp/captura/img/Playback.png" onclick="addRow('listApoderadosAsignados','listApoderadosAsignadosDiv','32','quitar','infoCommon.jsp','listApoderadosDiv','catCommon.jsp')"></abbr>
						</td>
					</tr>
					<tr>
						<td>
							<abbr title="Quitar Todos"><img style="cursor: pointer;" alt="Quitar Todos" src="<c:out value="${applicationBean.contextPath}"/>/jsp/captura/img/Rewind.png" onclick="addRow('listApoderadosAsignados','listApoderadosAsignadosDiv','32','quitarTodos','infoCommon.jsp','listApoderadosDiv','catCommon.jsp')"></abbr>
						</td>
					</tr>
				</table>
					
			</td>
			<td>					
				<%
					FacesUtils.getSessionBean().getApoderadosSessionVars().setTipoElemento("APODERADOS");
					FacesUtils.getSessionBean().getApoderadosSessionVars().setCustomIdComponentRight("listApoderadosAsignados");
				%>
				<span class="legendThin">Doble click para la captura de campos adicionales</span>
				</br>
				<span style="color:red"><%=session.getAttribute("msgRevocados")==null?"":session.getAttribute("msgRevocados")%></span>
				<div id="listApoderadosAsignadosDiv">
					<!--  %@include file="infoCommon.jsp?dobleClick=true" %> -->
					
					<%-- 
					<jsp:include page="infoCommon.jsp"><!-- Este parametro no funciono, se realizo en el js para pasarle el parametro dobleClick -->
				 		<jsp:param value="true" name="dobleClick"/>
					</jsp:include>
					--%>
					
					<% 
						request.getSession().setAttribute("dobleClick", "true");
					%>
					<jsp:include page="infoCommon.jsp"/>
					
					
				</div>         
			</td>
		</tr>
	</table>

</fieldset>

<br><br>
<%session.removeAttribute("msgRevocados");%>

<fieldset>
	<legend class="legendThin">Actos de Dominio</legend>

	<table width="100%">
		<tr>
			<td width="20%"></td>
			<td>
				<%
					FacesUtils.getSessionBean().getApoderadosSessionVars().setCustomIdCatalogo("33");
					FacesUtils.getSessionBean().getApoderadosSessionVars().setCustomIdComponent("listActosDeDominio");
				%>
				
				<div id="listActosDeDominioDiv">
					<%@include file="catCommon.jsp" %>
				</div>         
			</td>
			<td>
					<table>
					<tr>
						<td>
							<abbr title="Agregar"><img style="cursor: pointer;" alt="Agregar" src="<c:out value="${applicationBean.contextPath}"/>/jsp/captura/img/Play.png" onclick="addRow('listActosDeDominio','listActosDeDominioDiv','33','agregar','catCommon.jsp','listActosDeDominioAsignadosDiv','infoCommon.jsp')"></abbr>	
						</td>
					</tr>
					<tr>
						<td>
							<abbr title="Agregar Todos"><img style="cursor: pointer;" alt="Agregar Todos" src="<c:out value="${applicationBean.contextPath}"/>/jsp/captura/img/Fast-forward.png" onclick="addRow('listActosDeDominio','listActosDeDominioDiv','33','agregarTodos','catCommon.jsp','listActosDeDominioAsignadosDiv','infoCommon.jsp')"></abbr>	
						</td>
					</tr>
					<tr>
						<td>
							<abbr title="Quitar"><img style="cursor: pointer;" alt="Quitar" src="<c:out value="${applicationBean.contextPath}"/>/jsp/captura/img/Playback.png" onclick="addRow('listActosDeDominioAsignados','listActosDeDominioAsignadosDiv','33','quitar','infoCommon.jsp','listActosDeDominioDiv','catCommon.jsp')"></abbr>
						</td>
					</tr>
					<tr>
						<td>
							<abbr title="Quitar Todos"><img style="cursor: pointer;" alt="Quitar Todos" src="<c:out value="${applicationBean.contextPath}"/>/jsp/captura/img/Rewind.png" onclick="addRow('listActosDeDominioAsignados','listActosDeDominioAsignadosDiv','33','quitarTodos','infoCommon.jsp','listActosDeDominioDiv','catCommon.jsp')"></abbr>
						</td>
					</tr>
				</table>
					
			</td>
			<td>					
				<%
					FacesUtils.getSessionBean().getApoderadosSessionVars().setTipoElemento("ACTOS_DOMINIO");
					FacesUtils.getSessionBean().getApoderadosSessionVars().setCustomIdComponentRight("listActosDeDominioAsignados");
				%>
				
				<div id="listActosDeDominioAsignadosDiv">
					<!--  %@include file="infoCommon.jsp?dobleClick=true" %> -->

					<%--
					<jsp:include page="infoCommon.jsp">
				 		<jsp:param value="false" name="dobleClick"/>
					</jsp:include>
 					--%>
 
 
					<% 
					
						request.getSession().setAttribute("dobleClick", "true");
					%>
					<jsp:include page="infoCommon.jsp"/>
				</div>         
			</td>
		</tr>
	</table>

</fieldset>

<br><br>


<fieldset>
	<legend class="legendThin">Actos de Administración</legend>

	<table width="100%">
		<tr>
			<td width="20%"></td>
			<td>
				<%
					FacesUtils.getSessionBean().getApoderadosSessionVars().setCustomIdCatalogo("34");
					FacesUtils.getSessionBean().getApoderadosSessionVars().setCustomIdComponent("listActosAdministracion");
				%>
				
				<div id="listActosAdministracionDiv">
					<%@include file="catCommon.jsp" %>
				</div>         
			</td>
			<td>
				<table>
					<tr>
						<td>
							<abbr title="Agregar"><img style="cursor: pointer;" alt="Agregar" src="<c:out value="${applicationBean.contextPath}"/>/jsp/captura/img/Play.png" onclick="addRow('listActosAdministracion','listActosAdministracionDiv','34','agregar','catCommon.jsp','listActosAdministracionAsignadosDiv','infoCommon.jsp')"></abbr>	
						</td>
					</tr>
					<tr>
						<td>
							<abbr title="Agregar Todos"><img style="cursor: pointer;" alt="Agregar Todos" src="<c:out value="${applicationBean.contextPath}"/>/jsp/captura/img/Fast-forward.png" onclick="addRow('listActosAdministracion','listActosAdministracionDiv','34','agregarTodos','catCommon.jsp','listActosAdministracionAsignadosDiv','infoCommon.jsp')"></abbr>	
						</td>
					</tr>
					<tr>
						<td>
							<abbr title="Quitar"><img style="cursor: pointer;" alt="Quitar" src="<c:out value="${applicationBean.contextPath}"/>/jsp/captura/img/Playback.png" onclick="addRow('listActosAdministracionAsignados','listActosAdministracionAsignadosDiv','34','quitar','infoCommon.jsp','listActosAdministracionDiv','catCommon.jsp')"></abbr>
						</td>
					</tr>
					<tr>
						<td>
							<abbr title="Quitar Todos"><img style="cursor: pointer;" alt="Quitar Todos" src="<c:out value="${applicationBean.contextPath}"/>/jsp/captura/img/Rewind.png" onclick="addRow('listActosAdministracionAsignados','listActosAdministracionAsignadosDiv','34','quitarTodos','infoCommon.jsp','listActosAdministracionDiv','catCommon.jsp')"></abbr>
						</td>
					</tr>
				</table>
					
			</td>
			<td>					
				<%
					FacesUtils.getSessionBean().getApoderadosSessionVars().setTipoElemento("ACTOS_ADMINISTRACION");
					FacesUtils.getSessionBean().getApoderadosSessionVars().setCustomIdComponentRight("listActosAdministracionAsignados");
				%>
				
				<div id="listActosAdministracionAsignadosDiv">
					<!--  %@include file="infoCommon.jsp?dobleClick=true" %> -->
					
					<%-- 
					<jsp:include page="infoCommon.jsp">
				 		<jsp:param value="false" name="dobleClick"/>
					</jsp:include>
					--%>
					
					<% 
						request.getSession().setAttribute("dobleClick", "true");
					%>
					<jsp:include page="infoCommon.jsp"/>
				 	
				</div>         
			</td>
		</tr>
	</table>

</fieldset>

<br><br>

<fieldset>
	<legend class="legendThin">Títulos de Crédito</legend>

	<table width="100%">
		<tr>
			<td width="20%"></td>
			<td>
				<%
					FacesUtils.getSessionBean().getApoderadosSessionVars().setCustomIdCatalogo("35");
					FacesUtils.getSessionBean().getApoderadosSessionVars().setCustomIdComponent("listTitulosCredito");
				%>
				
				<div id="listTitulosCreditoDiv">
					<%@include file="catCommon.jsp" %>
				</div>         
			</td>
			<td>
					<table>
					<tr>
						<td>
							<abbr title="Agregar"><img style="cursor: pointer;" alt="Agregar" src="<c:out value="${applicationBean.contextPath}"/>/jsp/captura/img/Play.png" onclick="addRow('listTitulosCredito','listTitulosCreditoDiv','35','agregar','catCommon.jsp','listTitulosCreditoAsignadosDiv','infoCommon.jsp')"></abbr>	
						</td>
					</tr>
					<tr>
						<td>
							<abbr title="Agregar Todos"><img style="cursor: pointer;" alt="Agregar Todos" src="<c:out value="${applicationBean.contextPath}"/>/jsp/captura/img/Fast-forward.png" onclick="addRow('listTitulosCredito','listTitulosCreditoDiv','35','agregarTodos','catCommon.jsp','listTitulosCreditoAsignadosDiv','infoCommon.jsp')"></abbr>	
						</td>
					</tr>
					<tr>
						<td>
							<abbr title="Quitar"><img style="cursor: pointer;" alt="Quitar" src="<c:out value="${applicationBean.contextPath}"/>/jsp/captura/img/Playback.png" onclick="addRow('listTitulosCreditoAsignados','listTitulosCreditoAsignadosDiv','35','quitar','infoCommon.jsp','listTitulosCreditoDiv','catCommon.jsp')"></abbr>
						</td>
					</tr>
					<tr>
						<td>
							<abbr title="Quitar Todos"><img style="cursor: pointer;" alt="Quitar Todos" src="<c:out value="${applicationBean.contextPath}"/>/jsp/captura/img/Rewind.png" onclick="addRow('listTitulosCreditoAsignados','listTitulosCreditoAsignadosDiv','35','quitarTodos','infoCommon.jsp','listTitulosCreditoDiv','catCommon.jsp')"></abbr>
						</td>
					</tr>
				</table>
					
			</td>
			<td>					
				<%
					FacesUtils.getSessionBean().getApoderadosSessionVars().setTipoElemento("TITULOS_CREDITO");
					FacesUtils.getSessionBean().getApoderadosSessionVars().setCustomIdComponentRight("listTitulosCreditoAsignados");
				%>
				
				<div id="listTitulosCreditoAsignadosDiv">
					<!--  %@include file="infoCommon.jsp?dobleClick=true" %> -->
					
					<%--
					<jsp:include page="infoCommon.jsp">
				 		<jsp:param value="false" name="dobleClick"/>
					</jsp:include>
					 --%>
					 
					 
					<% 
						request.getSession().setAttribute("dobleClick", "true");
					%>
					
					<jsp:include page="infoCommon.jsp"/>
					
				</div>         
			</td>
		</tr>
	</table>

</fieldset>


<br><br>

<fieldset>
	<legend class="legendThin">Pleitos y Cobranzas</legend>

	<table width="100%">
		<tr>
			<td width="20%"></td>
			<td>
				<%
					FacesUtils.getSessionBean().getApoderadosSessionVars().setCustomIdCatalogo("36");
					FacesUtils.getSessionBean().getApoderadosSessionVars().setCustomIdComponent("listPleitosCobranzas");
				%>
				
				<div id="listPleitosCobranzasDiv">
					<%@include file="catCommon.jsp" %>
				</div>         
			</td>
			<td>
				<table>
					<tr>
						<td>
							<abbr title="Agregar"><img style="cursor: pointer;" alt="Agregar" src="<c:out value="${applicationBean.contextPath}"/>/jsp/captura/img/Play.png" onclick="addRow('listPleitosCobranzas','listPleitosCobranzasDiv','36','agregar','catCommon.jsp','listPleitosCobranzasAsignadosDiv','infoCommon.jsp')"></abbr>	
						</td>
					</tr>
					<tr>
						<td>
							<abbr title="Agregar Todos"><img style="cursor: pointer;" alt="Agregar Todos" src="<c:out value="${applicationBean.contextPath}"/>/jsp/captura/img/Fast-forward.png" onclick="addRow('listPleitosCobranzas','listPleitosCobranzasDiv','36','agregarTodos','catCommon.jsp','listPleitosCobranzasAsignadosDiv','infoCommon.jsp')"></abbr>	
						</td>
					</tr>
					<tr>
						<td>
							<abbr title="Quitar"><img style="cursor: pointer;" alt="Quitar" src="<c:out value="${applicationBean.contextPath}"/>/jsp/captura/img/Playback.png" onclick="addRow('listPleitosCobranzasAsignados','listPleitosCobranzasAsignadosDiv','36','quitar','infoCommon.jsp','listPleitosCobranzasDiv','catCommon.jsp')"></abbr>
						</td>
					</tr>
					<tr>
						<td>
							<abbr title="Quitar Todos"><img style="cursor: pointer;" alt="Quitar Todos" src="<c:out value="${applicationBean.contextPath}"/>/jsp/captura/img/Rewind.png" onclick="addRow('listPleitosCobranzasAsignados','listPleitosCobranzasAsignadosDiv','36','quitarTodos','infoCommon.jsp','listPleitosCobranzasDiv','catCommon.jsp')"></abbr>
						</td>
					</tr>
				</table>
					
			</td>
			<td>					
				<%
					FacesUtils.getSessionBean().getApoderadosSessionVars().setTipoElemento("PLEITOS_COBRANZAS");
					FacesUtils.getSessionBean().getApoderadosSessionVars().setCustomIdComponentRight("listPleitosCobranzasAsignados");
				%>
				
				<div id="listPleitosCobranzasAsignadosDiv">
					<!--  %@include file="infoCommon.jsp?dobleClick=true" %> -->
					
					<%--
					<jsp:include page="infoCommon.jsp">
				 		<jsp:param value="false" name="dobleClick"/>
					</jsp:include>
					 --%>
					 
					 
					 
					<% 
						request.getSession().setAttribute("dobleClick", "true");
					%>
					
					
					<jsp:include page="infoCommon.jsp"/>
				 		
				 		
				</div>         
			</td>
		</tr>
	</table>

</fieldset>

<br><br>

<fieldset>
	<legend class="legendThin">Poder Especial</legend>

	<table width="100%">
		<tr>
			<td width="20%"></td>
			<td>
				<%
					FacesUtils.getSessionBean().getApoderadosSessionVars().setCustomIdCatalogo("37");
					FacesUtils.getSessionBean().getApoderadosSessionVars().setCustomIdComponent("listPoderEspecial");
				%>
				
				<div id="listPoderEspecialDiv">
					<%@include file="catCommon.jsp" %>
				</div>         
			</td>
			<td>
					<table>
					<tr>
						<td>
							<abbr title="Agregar"><img style="cursor: pointer;" alt="Agregar" src="<c:out value="${applicationBean.contextPath}"/>/jsp/captura/img/Play.png" onclick="addRow('listPoderEspecial','listPoderEspecialDiv','37','agregar','catCommon.jsp','listPoderEspecialAsignadosDiv','infoCommon.jsp')"></abbr>	
						</td>
					</tr>
					<tr>
						<td>
							<abbr title="Agregar Todos"><img style="cursor: pointer;" alt="Agregar Todos" src="<c:out value="${applicationBean.contextPath}"/>/jsp/captura/img/Fast-forward.png" onclick="addRow('listPoderEspecial','listPoderEspecialDiv','37','agregarTodos','catCommon.jsp','listPoderEspecialAsignadosDiv','infoCommon.jsp')"></abbr>	
						</td>
					</tr>
					<tr>
						<td>
							<abbr title="Quitar"><img style="cursor: pointer;" alt="Quitar" src="<c:out value="${applicationBean.contextPath}"/>/jsp/captura/img/Playback.png" onclick="addRow('listPoderEspecialAsignados','listPoderEspecialAsignadosDiv','37','quitar','infoCommon.jsp','listPoderEspecialDiv','catCommon.jsp')"></abbr>
						</td>
					</tr>
					<tr>
						<td>
							<abbr title="Quitar Todos"><img style="cursor: pointer;" alt="Quitar Todos" src="<c:out value="${applicationBean.contextPath}"/>/jsp/captura/img/Rewind.png" onclick="addRow('listPoderEspecialAsignados','listPoderEspecialAsignadosDiv','37','quitarTodos','infoCommon.jsp','listPoderEspecialDiv','catCommon.jsp')"></abbr>
						</td>
					</tr>
				</table>
					
			</td>
			<td>					
				<%
					FacesUtils.getSessionBean().getApoderadosSessionVars().setTipoElemento("PODER_ESPECIAL");
					FacesUtils.getSessionBean().getApoderadosSessionVars().setCustomIdComponentRight("listPoderEspecialAsignados");
				%>
				
				<div id="listPoderEspecialAsignadosDiv">
					<!--  %@include file="infoCommon.jsp?dobleClick=true" %> -->
					
					<%-- 
					<jsp:include page="infoCommon.jsp">
				 		<jsp:param value="false" name="dobleClick"/>
					</jsp:include>
					--%>
					
					
					<% 
						request.getSession().setAttribute("dobleClick", "true");
					%>
					
					<jsp:include page="infoCommon.jsp"/>
				 	
				 	
				</div>         
			</td>
		</tr>
	</table>

</fieldset>






</body>
</html>
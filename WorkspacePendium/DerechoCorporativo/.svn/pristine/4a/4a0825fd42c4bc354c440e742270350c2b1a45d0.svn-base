<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="mx.com.televisa.derechocorporativo.components.JSCal"%>
<%String typeView=request.getParameter("typeView");
  int idMetaRow=(request.getParameter("idMetaRow")!=null)?Integer.parseInt(request.getParameter("idMetaRow")):0;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Añadir</title>
<%@include file="/css/kaz-styles.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.css">
<%@include file="/jsp/components/calendar/include_calendar.jsp"%>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/ajax/simpleAjaxUtil.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery/jquery-2.1.4.min.js"></script>
<!-- Validaciones y peticiones ajax para asuntos -->
<script type="text/javascript" src="<%= request.getContextPath() %>/js/asunto/agregar.js"></script>
<script src="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.min.js"></script> 
<style>
body,td,th,p,div{
	/*font-family:Verdana, Arial, Helvetica, sans-serif;*/
	font-size: 13px;
	font-family: 'Open Sans', sans-serif;
	font-weight: lighter;
}
</style>
</head>
<body>
<!-- Variables de trabajo -->
<input type="hidden" id="typeView" value="<%=typeView%>">
<input type="hidden" id="contextPath" value="<%=request.getContextPath()%>">
<center>
	<h1>Agregar Otros</h1>
</center>
<table width="100%">
	<tr>
		<td style="text-align: right;">
			<input type="button" id="btnSendAgregar" value="Aceptar">
		</td>
	</tr>
</table>

<div id="contentForms" class="light-color">
		<form id="formUpdateAgregar" class="hidden" onsubmit="return false">
			<input type="hidden" name="tipoAccion" value="update">
			<!-- update registros -->
			<div  class="divContent2">
			<table width="100%" style="margin-top:20px; margin-bottom: 20px;">
				<tr>
					<td colspan="2" class="headerTitleSeccion">
						Editar Agregar Otro
					</td>
				</tr>
				<tr>
					<td width="35%" style="text-align: right; padding-right: 5px;"><label><b>Id</b></label></td>
					<td><input type="text" id="idAgregarU" name="idAgregarU" size="40"></td>
				</tr>
				<tr>
					<td style="text-align: right; padding-right: 5px;"><label><b>Descripción</label></td>
					<td><textarea id="agregarU" name="agregarU" cols='50'></textarea>
					<input id="idAgregarRowU" name="idAgregarRowU" type="hidden">
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: right;">
						<button id="btnUpdateCancelar">Cancelar</button>
						<button id="btnUpdateAgregar">Actualizar</button>
					</td>
				</tr>
			</table>
			<!-- 
				<div class="divContent" id="updatedEjercicio"><div class="headerTitleSeccion">Editar Asunto</div>
						<div class="divGroup">
							<div>
								<label><b>Id</b></label>
							</div>
							<div>
								<input type="text" id="idAsuntoU" name="idAsuntoU" >
							</div>
						</div>
						<div class="divGroup">
							<div>
								<label><b>Se aprobó</b></label>
							</div>
							<div>
								<input type="text" id="asuntoU" name="asuntoU" >
							</div>
						</div>
						<input id="idAsuntoRowU" name="idAsuntoRowU" type="hidden">
				</div>	
				<div class="floatRight">
					<div class="buttons">
						<button id="btnUpdateCancelar">Cancelar</button>
					</div>
					<div class="buttons">
						<button id="btnUpdateAsunto">Actualizar</button>
					</div>
				</div>
				-->
			</div>
		</form>
		<form id="formAgregar" onsubmit="return false"><!-- inicia form para agregar un nuevo ejercicio -->
			<input type="hidden" name="tipoAccion" value="insert">
			<input id="idMetaRow" name="idMetaRow" value="<%=idMetaRow%>" type="hidden">
			
			<table width="100%" style="margin-top:20px; margin-bottom: 20px;">
				<tr>
					<td colspan="2" class="headerTitleSeccion">
						Nuevo
					</td>
				</tr>
				<tr>
					<td width="35%" style="text-align: right; padding-right: 5px;"><label><b>Id</b></label></td>
					<td><input type="text" id="idAgregar" name="idAgregar" size="40"></td>
				</tr>
				<tr>
					<td style="text-align: right; padding-right: 5px;"><label><b>Descripción</label></td>
					<td><textarea id="agregar" name="agregar" cols='50' ></textarea></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: right;">
						<button id="saveAgregar">Agregar</button>
					</td>
				</tr>
				
			</table>
			<!--  
			<div  class="divContent2">
				<div class="divContent">
						<div class="headerTitleSeccion">Nuevo Asunto</div>
						<div class="divGroup">
							<div>
								<label><b>Id</b></label>
							</div>
							<div>
								<input type="text" id="idAsunto" name="idAsunto" >
							</div>
						</div>
						<div class="divGroup">
							<div>
								<label><b>Se aprobó</b></label>
							</div>
							<div>
								<input type="text" id="asunto" name="asunto" >
							</div>
						</div>
						<div class="floatRight">
						<div>
								<button id="saveAsunto">Agregar</button>
							</div>
						</div>
				</div>	
			</div>-->
		</form>
	</div>

<table width="100%" id="tableAgregar">
	<tr>
		<td>
			<div id="myTable">
				<table width="100%">
					<thead>
						<tr>
							<th class="tableHeaderAlfa2">Id</th>
							<th class="tableHeaderAlfa2">Descripción</th>
							<%if(typeView.equals("consulta")){ %>
								<th class="tableHeaderAlfa2">Editar</th>
								<th class="tableHeaderAlfa2">Borrar</th>
							<% } %>
						</tr>
					</thead>
					<tbody id="tableContent">
					</tbody>
					<tbody id="tableContentHidden" class="hidden">
					</tbody>
				</table>
			</div>
		</td>
	</tr>	
</table>
<div>
	
	
</div>
</body>
</html>
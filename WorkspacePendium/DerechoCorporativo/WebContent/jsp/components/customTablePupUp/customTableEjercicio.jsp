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
<!-- Validaciones y peticiones ajax para ajercicio social -->
<script type="text/javascript" src="<%= request.getContextPath() %>/js/ejercicio/ejercicio.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jsCal/jquery.mask.js"></script>
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
	<h1>Añadir mas Documentos</h1>
</center>
<div class="floatRight">
	<div>
		<input type="button" id="btnSendEjercicio" value="Aceptar">
	</div>
</div>

<table width="100%" id="tableEjercicios">
	<tr>
		<td>
			<div id="myTable">
				<table width="100%">
					<thead>
						<tr>
							<th class="tableHeaderAlfa2">Nombre Documento</th>
							<th class="tableHeaderAlfa2">Fecha documento</th>
							<th class="tableHeaderAlfa2">Fecha recibido</th>
							<th class="tableHeaderAlfa2">Doc. Digitalizado</th>
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
	
	<div id="contentForms" class="light-color">
		<form id="formUpdateEjercicio" class="hidden" onsubmit="return false">
			<input type="hidden" name="tipoAccion" value="update">
			<!-- update registros -->
			<div  class="divContent2">
				<div class="divContent" id="updatedEjercicio"><div class="headerTitleSeccion">Editar Documento</div>
						<div class="divGroup">
							<!-- Fecha -->
							<div>
								<label><b>Nombre del documento</b></label>
							</div>
							<div>
								<input type="text" id="typeDocU" name="typeDocU" >
							</div>
						</div>
						<input id="idEjercicioRowU" name="idEjercicioRowU" type="hidden">
						<div class="divGroup">
							<!-- Liga -->
							<div class="divLabelDocumentum">
								<label for="documentoU"><b>Documento Digitalizado</b></label>
							</div>
							<div class="divInputDocumentum">
								<input type="text" id="documentoU" name="documentoU" >
							</div>
							<div class="divHrefIcon">
								<a href="javascript:getDocumentum('documentoU')"><img src="<%= request.getContextPath()%>/img/List_16.png"></a>
							</div>
						</div>
						<div class="divGroup2">
							<!-- Fecha -->
							<div class="divLabelDocumentum">
								<label><b>Fecha del documento</b></label>
							</div>
							<div>
								<input type="text" id="fecha_docU" name="fecha_docU">
							</div>
							<div class="divHrefIcon">
								<%= JSCal.getCalendar("fecha_docU", "") %>
							<!-- 
								<img src="/DerechoCorporativo/img/calendar.gif" id="fecha_docU_trigger" title="Mostrar Calendario"> 
								<script type="text/javascript">    	  
									new Calendar({  inputField: "fecha_docU", 
													dateFormat: "%d/%m/%Y", 
													trigger: "fecha_docU_trigger", 
													bottomBar: true,										            
													onSelect: function() {                 
														var date = Calendar.intToDate(this.selection.get());                 
														this.hide();                 				  
														return false;         
													} 
												}); 
												function clear_fecha_entregaSol(){	
													document.getElementById('fecha_docU').value = "";	
												}
								</script> -->
							</div>
						</div>
						<div class="divGroup2">
							<!-- Fecha -->
							<div class="divLabelDocumentum">
								<label><b>Fecha de recibido</b></label>	
							</div>
							<div>
								<input type="text" id="fecha_entregaU" name="fecha_entregaU" >
							</div>
							<div class="divHrefIcon">
							<%= JSCal.getCalendar("fecha_entregaU", "") %>
							<!-- 
								<img src="/DerechoCorporativo/img/calendar.gif" id="fecha_entregaU_trigger" title="Mostrar Calendario"> 
								<script type="text/javascript">    	  
									new Calendar({  inputField: "fecha_entregaU", 
													dateFormat: "%d/%m/%Y", 
													trigger: "fecha_entregaU_trigger", 
													bottomBar: true,										            
													onSelect: function() {                 
														var date = Calendar.intToDate(this.selection.get());                 
														this.hide();                 				  
														return false;         
													} 
												}); 
												function clear_fecha_entregaSol(){	
													document.getElementById('fecha_entregaU').value = "";	
												}
								</script> -->
							</div>
						</div>
				</div>	
				<div class="floatRight">
					<div class="buttons">
						<button id="btnUpdateCancelar">Cancelar</button>
					</div>
					<div class="buttons">
						<button id="btnUpdateEjercicio">Actualizar</button>
					</div>
				</div>
			</div>
		</form>
		<form id="formEjercicio" onsubmit="return false"><!-- inicia form para agregar un nuevo ejercicio -->
			<input type="hidden" name="tipoAccion" value="insert">
			<input id="idMetaRow" name="idMetaRow" value="<%=idMetaRow%>" type="hidden">
			<div  class="divContent2">
				<div class="divContent">
						<div class="headerTitleSeccion">Nuevo Documento</div>
						<div class="divGroup">
							<!-- Fecha -->
							<div>
								<label><b>Nombre del documento</b></label>
							</div>
							<div>
								<input type="text" id="typeDocS" name="typeDocS" >
							</div>
						</div>
						<div class="divGroup">
							<!-- Liga -->
							<div class="divLabelDocumentum">
								<label for="documentoSol"><b>Documento Digitalizado</b></label>
							</div>
							<div class="divInputDocumentum">
								<input type="text" id="documentoSol" name="documentoSol">
							</div>
							<div class="divHrefIcon">
								<a href="javascript:getDocumentum('documentoSol')"><img src="<%= request.getContextPath()%>/img/List_16.png"></a>
							</div>
						</div>
						<div class="divGroup2">
							<!-- Fecha -->
							<div class="divLabelDocumentum">
								<label><b>Fecha del documento</b></label>
							</div>
							<div>
								<input type="text" id="fecha_docSol" name="fecha_docSol">
							</div>
							<div class="divHrefIcon">
								<%= JSCal.getCalendar("fecha_docSol", "") %>
							</div>
						</div>
						<div class="divGroup2">
							<!-- Fecha -->
							<div class="divLabelDocumentum">
								<label><b>Fecha de recibido</b></label>	
							</div>
							<div>
								<input type="text" id="fecha_entregaSol" name="fecha_entregaSol">
							</div>
							<div class="divHrefIcon">
								<%= JSCal.getCalendar("fecha_entregaSol", "") %>
							</div>
						</div>
						<div class="floatRight">
						<div>
								<button id="saveEjercicio">Guardar Documento</button>
							</div>
						</div>
				</div>	
			</div>
		</form>
	</div>
</div>
</body>
</html>
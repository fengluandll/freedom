<%
	response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", 0); //prevents caching at the proxy server
%>
<%@page import="com.movemini.simpleajaxservlet.evento.ArchivoAnexoTabla"%>
<%@page import="com.movemini.simpleajaxservlet.crm.CRMComentariosTable"%>
<%@page import="com.movemini.simpleajaxservlet.crm.CRMComentariosForm"%>
<%@page
	import="com.movemini.simpleajaxservlet.interprete.InterpretesAsignadosTable"%>
<%@page
	import="com.movemini.simpleajaxservlet.viaticos.ViaticosAplicablesTable"%>
<%@page
	import="com.movemini.simpleajaxservlet.viaticos.ViaticosEventoTable"%>
<%@page import="com.movemini.simpleflexgrid.components.SelectList"%>
<%@page import="com.movemini.data.ProcedureCall"%>
<%@page import="com.movemini.simpleajaxservlet.evento_sala.SalasTable"%>
<%@page
	import="com.movemini.simpleajaxservlet.observaciones.ObservacionesAplicablesTable"%>
<%@page
	import="com.movemini.simpleajaxservlet.observaciones.ObservacionesEventoTable"%>
<%@page
	import="com.movemini.simpleajaxservlet.productos.ProductosAplicablesTable"%>
<%@page
	import="com.movemini.simpleajaxservlet.productos.ProductosEventoTable"%>
<%@page
	import="com.movemini.simpleajaxservlet.condiciones.CondicionesAplicablesTable"%>
<%@page
	import="com.movemini.simpleajaxservlet.condiciones.CondicionesEventoTable"%>
<%@page
	import="com.movemini.simpleajaxservlet.politicas.PoliticasAplicablesTable"%>
<%@page
	import="com.movemini.simpleajaxservlet.politicas.PoliticasEventoTable"%>
<%@page import="com.movemini.simpleajaxservlet.evento.EventoForm"%>
<%@page import="com.movemini.tecnico.ManejadorTecnico"%>

<%@page
	import="com.movemini.simpleajaxservlet.evento_area.EventoAreaTable"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="org.apache.tomcat.util.codec.binary.Base64"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.movemini.pdfreport.ConvertidorPDF"%>
<%@ page import="com.movemini.notificacion.EnviarCorreo"%>
<%@ page import="com.movemini.notificacion.MailFormat"%>
<%@ page import="com.movemini.data.OneValueFactory"%>



<%
	try {

		Object id_evento_status = session.getAttribute("id_evento_status");
        System.out.println("Estado"+id_evento_status);

		String id_evento_version = request.getParameter("ID_EVENTO");
		String id_evento = OneValueFactory.get("select id_evento from evento_version where id_evento_version = '"+ id_evento_version + "'");

		//String idTipoEvento = request.getParameter("ID_TIPO_EVENTO");

		SessionBean sessionBean = SessionBean.getInstance(request);
		sessionBean.setIdEvento(id_evento);
		sessionBean.setIdEventoVersion(id_evento_version);
		
		
        Record myUser = sessionBean.getUser();

		
		
		ProcedureCall.call("evento_version_create_if_not_db", id_evento);

		//String folio = OneValueFactory.get("SELECT clave_cotizacion FROM evento WHERE id_evento = " + id_evento);

		Record eventoRecord = OneRecordFactory
				.getFirstRecord("SELECT * FROM evento WHERE id_evento = " + id_evento);

		String folio = eventoRecord.get("clave_cotizacion");

		String idTipoEvento = eventoRecord.get("id_tipo_eveto");

		//     Connection conn= ConnectionWrapper.getDirectJDBCConnection();

		//     Boolean avanzar = false;

		//     if(request.getParameter("EventoEdit") != null){
		// 	      String upDateUser = "UPDATE evento SET NOMBRE=?, LUGAR=?, STATUS=?, ID_EMPRESA=?, FECHA_INICIO=?, FECHA_FIN=? WHERE ID = ?";
		// 	      PreparedStatement preparedStmtUser = conn.prepareStatement(upDateUser);
		// 	      preparedStmtUser.setString (1, request.getParameter("nombre_evento").toString());
		// 	      preparedStmtUser.setString (2, request.getParameter("lugar").toString());
		// 	      preparedStmtUser.setString (3, request.getParameter("status").toString());
		// 	      preparedStmtUser.setString (4, request.getParameter("ID_EMPRESA").toString());
		// 	      preparedStmtUser.setString (5, request.getParameter("fecha_inicio").toString());
		// 	      preparedStmtUser.setString (6, request.getParameter("fecha_fin").toString());
		// 	      preparedStmtUser.setInt    (7, Integer.parseInt(request.getParameter("ID_EVENTO_C").toString()));
		// 	      // execute the java preparedstatement
		// 	      if(preparedStmtUser.executeUpdate() >= 1){
		//     		response.sendRedirect("adm_eventos_list.jsp");
		// 	      }
		//     	  else {
		//     		  % > <script> alert("Ocurriï¿½ un error por favor contacta al Administrador");</script> < %  - - % >
		//     		  response.sendRedirect("adm_eventos_list.jsp");
		// 	      }

		//     }else if(session.getAttribute("ID_USER") != null & request.getParameter("ID_EVENTO") != null & request.getParameter("ID_EMPRESA") != null) {
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Meta, title, CSS, favicons, etc. -->
<meta charset="ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>SAO - Cotizaci&oacute;n</title>

<!-- Bootstrap -->
<link href="../home/vendors/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Font Awesome -->
<link href="../home/vendors/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">
<!-- NProgress -->
<link href="../home/vendors/nprogress/nprogress.css" rel="stylesheet">
<!-- iCheck -->
<link href="../home/vendors/iCheck/skins/flat/green.css"
	rel="stylesheet">
<!-- Datatables -->
<link
	href="../home/vendors/datatables.net-bs/css/dataTables.bootstrap.min.css"
	rel="stylesheet">
<link
	href="../home/vendors/datatables.net-buttons-bs/css/buttons.bootstrap.min.css"
	rel="stylesheet">
<link
	href="../home/vendors/datatables.net-fixedheader-bs/css/fixedHeader.bootstrap.min.css"
	rel="stylesheet">
<link
	href="../home/vendors/datatables.net-responsive-bs/css/responsive.bootstrap.min.css"
	rel="stylesheet">
<link
	href="../home/vendors/datatables.net-scroller-bs/css/scroller.bootstrap.min.css"
	rel="stylesheet">



<!-- Switchery -->
<link href="../home/vendors/switchery/dist/switchery.min.css"
	rel="stylesheet">

<link href="../home/css/datepicker.css" rel="stylesheet">
<link rel="stylesheet" href="../home/css/timepicker.css" />


<!-- FullCalendar -->
<link href="vendors/fullcalendar/dist/fullcalendar.css" rel="stylesheet">
<link href="vendors/fullcalendar/dist/fullcalendar.print.css"
	rel="stylesheet" media="print">

<!-- Custom Theme Style -->
<link href="../home/build/css/custom.css" rel="stylesheet">

<!-- <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.16/themes/base/jquery-ui.css" rel="stylesheet"> -->

<link href="../home/vendors/sweet_alert/sweetalert.css" rel="stylesheet">

<script type="text/javascript" src="js/util.js"></script>





<!--     <script src="http://cdn.jsdelivr.net/timepicker.js/latest/timepicker.min.js"></script> -->
<!-- <link href="http://cdn.jsdelivr.net/timepicker.js/latest/timepicker.min.css" rel="stylesheet"/> -->



   
   
   
   



<script src="//cdn.ckeditor.com/4.7.2/standard/ckeditor.js"></script>





</head>
<body class="nav-md">


	<input type="hidden" id="id_evento" value="<%=id_evento_version%>">
	<input type="hidden" id="id_tipo_evento" value="<%=idTipoEvento%>">


	<div class="container body">
		<div class="main_container">




			<%@include file="commonSideBarMenuEvento.jsp"%>




			<div class="right_col" role="main" style="overflow: auto;">
				<div class="">
<!-- 					<div class="page-title"></div> -->

					<div class="clearfix"></div>

					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="x_panel">
							<div class="x_title">
								<p>
									<strong>Datos del Evento </strong>
									<button class="btn btn-success pull-right" type="button" onclick="mostrarSeguimiento()">Ver seguimiento</button>
									<button class="btn btn-default pull-right" type="button" onclick="window.location.replace(document.referrer);" >Regresar</button>
								</p>
								<ul class="nav navbar-right panel_toolbox">
								</ul>
								<!-- 								<div class="clearfix" style="text-align: right"> -->
								<%-- 									<a onclick="funcMostarModalSubgrupoCotizador(<%=id_evento_version%>)"> --%>
								<!-- 										Crear Subgrupo -->
								<!-- 										<i class="fa fa-plus fa-2x"></i> -->
								<!-- 									</a> -->
								<!-- 								</div> -->
							</div>
							<div class="x_content">
								<div class="row" style="text-align: right">
									<label>Folio: </label> <strong><%=folio%> </strong>
								</div>


								<%--
                      <button type="submit" name="EventoEdit" class="btn btn-primary" style="float: right;">Guardar Datos y Regresar</button> &zwnj;
                      <a href="adm_eventos_list.jsp" class="btn btn-warning" style="float: right;">Cancelar</a>
                      --%>



<%

boolean isTecnicos = false;

if(myUser.get("id_rol").equals(HardCodeConstants.ID_ROL_TECNICOS)) {
	
	isTecnicos = true;
}



%>

								<!-- Tabs -->

								<div class="" role="tabpanel" data-example-id="togglable-tabs">
									<ul id="myTab" class="nav nav-tabs bar_tabs" role="tablist">
										
										<%
											if(isTecnicos) {
										%>
										


										<li role="presentation" class="active"><a href="#tab_content8"
											role="tab" id="home-tab" data-toggle="tab"
											aria-expanded="false">Detalle</a></li>
											
										
										<li role="presentation" class=""><a href="#tab_content9"
											role="tab" id="profile-tab2" data-toggle="tab" onclick='selectTecnicosAsignados()'
											aria-expanded="false">Asignar T&eacute;cnicos</a></li>
										
											
											
										
										<%
											} else {
										%>
										<li role="presentation" class="active"><a
											href="#tab_content0" id="home-tab" role="tab"
											data-toggle="tab" aria-expanded="true">Encabezado</a></li>
											
										<li role="presentation" class=""><a href="#tab_content1"
											role="tab" id="profile-tab" data-toggle="tab"
											aria-expanded="true">Cotizaci&oacute;n</a></li>

												<%
											if (idTipoEvento.equals("1")) {
										%>
										<li role="presentation" class=""><a href="#tab_content2"
											role="tab" id="profile-tab" data-toggle="tab"
											aria-expanded="false">Vi&aacute;ticos</a></li>
										<%
											}
										%>


										<li role="presentation" class=""><a href="#tab_content4"
											role="tab" id="profile-tab" data-toggle="tab"
											aria-expanded="false">Observaciones</a></li>



										<li role="presentation" class=""><a href="#tab_content3"
											role="tab" id="profile-tab2" data-toggle="tab"
											aria-expanded="false">Condiciones de Pago</a></li>


										<%
											//if (idTipoEvento.equals("1")) {
										%>
										<li role="presentation" class=""><a href="#tab_content5"
											role="tab" id="profile-tab2" data-toggle="tab"
											aria-expanded="false">Cancelaci&oacute;n</a></li>
										<%
											//}
										%>



										<%
											if (idTipoEvento.equals("1")) {
										%>
										<li role="presentation" class=""><a href="#tab_content6"
											role="tab" id="profile-tab2" data-toggle="tab" onclick='selectInterpretesAsignados()'
											aria-expanded="false">Int&eacute;rpretes</a></li>
										<%
											} else {
										%>
										<li role="presentation" class=""><a href="#tab_content6"
											role="tab" id="profile-tab2" data-toggle="tab"
											aria-expanded="false">Traductores</a></li>
										<%
											}
										%>


										<%
											if (idTipoEvento.equals("1") && "5".equals(id_evento_status)) {
										%>
										<li role="presentation" class=""><a href="#tab_content9"
											role="tab" id="profile-tab2" data-toggle="tab" onclick='selectTecnicosAsignados()'
											aria-expanded="false">T&eacute;cnicos</a></li>
										<%
											}
										%>

										<li role="presentation" class=""><a href="#tab_content8"
											role="tab" id="profile-tab2" data-toggle="tab"
											aria-expanded="false">Vista Previa</a></li>


										<li role="presentation" class=""><a href="#tab_contentX1"
											role="tab" id="profile-tab2" data-toggle="tab"
											aria-expanded="false">Anexos</a></li>


										<%
											} 
										%>

									</ul>
									<div id="myTabContent" class="tab-content">
									
									
										<div role="tabpanel" class="tab-pane fade <% if(!isTecnicos) { %>active in<% } %>"
											id="tab_content0" aria-labelledby="home-tab">




											<div class="x_panel">

												<div class="x_title">
													<p>
														<strong>Encabezado </strong>
													</p>
													<ul class="nav navbar-right panel_toolbox">
													</ul>
													<div class="clearfix"></div>
												</div>

												<div class="x_content">


													<div class="col-md-8 col-sm-8 col-xs-8">
														<div id="divEventoForm">
															<%
																EventoForm bean2 = new EventoForm(request);
															%>

															<%=bean2.getFormHtml()%>
														</div>
													</div>


													<div class="col-md-12 col-sm-12 col-xs-12">
														<div class="x_panel">

															<div class="x_title">
																<div id="divNombreSala">Fechas del Evento
																	(Agenda):</div>
																<ul class="nav navbar-right panel_toolbox">
																</ul>
																<div class="clearfix"></div>
															</div>

															<div class="x_content">

																<div align="right">
																	<a href="#" onclick="nuevaFechaEvento()" style="cursor: pointer;"> <i
																		class="fa fa-plus-circle fa-2x"></i> Nueva
																	</a>
																</div>


																<div id="divFechasEvento"></div>


															</div>

														</div>
													</div>



												</div>
											</div>

											<div style="display: none">
												<%=SelectList.getListByQuery("id_version",
						"SELECT id_evento_version, num_version FROM evento_version WHERE id_evento = " + id_evento
								+ " ORDER BY num_version DESC",
						null, "", "")%>
											</div>


										</div>

										<div role="tabpanel" class="tab-pane fade" id="tab_content1"
											aria-labelledby="home-tab">

											<div class="x_panel">


												<%
													/*




															<div class="x_title">
															      <h2>

															<!-- 						                  Text areas<small>Sessions</small> -->
															      </h2>
															      <ul class="nav navbar-right panel_toolbox">
															        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
															        </li>
															<!-- 						                    <li class="dropdown"> -->
															<!-- 						                      <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a> -->
															<!-- 						                      <ul class="dropdown-menu" role="menu"> -->
															<!-- 						                        <li><a href="#">Settings 1</a> -->
															<!-- 						                        </li> -->
															<!-- 						                        <li><a href="#">Settings 2</a> -->
															<!-- 						                        </li> -->
															<!-- 						                      </ul> -->
															<!-- 						                    </li> -->
															<!-- 						                    <li><a class="close-link"><i class="fa fa-close"></i></a> -->
															<!-- 						                    </li> -->
															      </ul>
															      <div class="clearfix"></div>
															    </div>


															    */
												%>

												<div class="x_content">




													<%
														//String encabezadosVisible = (idTipoEvento.equals("1"))? "": "style='display:none'";
															String encabezadosVisible = "";

															String encabezadosTitle = (idTipoEvento.equals("1")) ? "Encabezados" : "Conceptos";

															boolean showNewSubgrupo = (idTipoEvento.equals("1")) ? true : false;
													%>

													<div class="col-md-12 col-sm-12 col-xs-12"
														<%=encabezadosVisible%>>
														<div class="x_panel">

															<div class="x_title">
																<p>
																	<strong><%=encabezadosTitle%> </strong>
																</p>
																<ul class="nav navbar-right panel_toolbox">
																</ul>
																<div class="clearfix"></div>

															</div>

															<div class="x_content">
																<div align="right">
																	<a href="#" onclick="nuevaSala()" style="cursor: pointer;"> <i
																		class="fa fa-plus-circle fa-2x"></i> Nuevo




																	</a>
																</div>


																<div id="divSalas" style="overflow: auto;">
																	<%-- 												                  	<% --%>
																	<!-- // 												                  	 -->
																	<!-- SalasTable bean = new SalasTable(request); -->

																	<%-- 												                  	%> --%>

																	<%-- 												                  <%= bean.getHtml() %> --%>
																</div>


																<%
																	if (showNewSubgrupo) {
																%>
																<div class="clearfix" style="text-align: right">
																	<a
																		onclick="funcMostarModalSubgrupoCotizador(<%=id_evento_version%>)" style="cursor: pointer;">
																		SubTotales <i class="fa fa-plus fa-2x"></i>
																	</a>
																</div>
																<%
																	}
																%>


															</div>

														</div>
													</div>




													<%
														//String fechasVisible = (idTipoEvento.equals("1"))? "": "style='display:none'";
															String fechasVisible = (idTipoEvento.equals("1")) ? "style='display:none'" : "style='display:none'";
													%>
													<div class="col-md-4 col-sm-4 col-xs-12"
														<%=fechasVisible%>>
														<div class="x_panel">

															<div class="x_title">
																<div id="divNombreSala">Fechas de la Sala:</div>
																<ul class="nav navbar-right panel_toolbox">
																</ul>
																<div class="clearfix"></div>
															</div>

															<div class="x_content">

																<div align="right" id="divLinkNuevaFecha"
																	style="display: none">
																	<a href="#" onclick="nuevaFecha()" style="cursor: pointer;"> <i
																		class="fa fa-plus-circle fa-2x"></i> Nueva
																	</a>
																</div>


																<div id="divFechas"></div>


															</div>

														</div>
													</div>


												</div>

											</div>




											<div class="x_panel" id="detalleCotizacion">
												<div class="col-md-7 col-sm-7 col-xs-12">
													<div class="x_panel">

														<div class="x_title">
															<p>
																<%
																	if (idTipoEvento.equals("1")) {
																%>
																<strong>Productos <span
																		id="spanSalaDiaHeader" style="color:inherit"></span>: </strong>
																<%
																	} else {
																%>
																<strong>Servicios <span
																		id="spanSalaDiaHeader" style="color:inherit"></span>: </strong>
																<%
																	}
																%>
															</p>
															<ul class="nav navbar-right panel_toolbox">
															</ul>
															<div class="clearfix"></div>
														</div>
														<div class="x_content">
															<div id="divProductosEvento" style="overflow: auto;">
																<%-- 											                  	<% --%>
																<!-- // 											                  	 -->
																<!-- ProductosEventoTable productosEventoTable = new ProductosEventoTable(request); -->

																<%-- 											                  	%> --%>

																<%-- 											                  <%= productosEventoTable.getHtml() %> --%>
															</div>

															<br><br>


															<a onClick="insertProductoAbierto()"> <i
																		class="fa fa-plus-circle fa-2x" style="cursor: pointer;"></i> Nuevo</a>

															<div id="divProductosAbiertosEvento" style="overflow: auto; ">

															</div>

														</div>
													</div>
												</div>

												<div class="col-md-5 col-sm-5 col-xs-12">
													<div class="x_panel">




















														<div class="" role="tabpanel"
															data-example-id="togglable-tabs">
															<ul id="myTab2" class="nav nav-tabs bar_tabs"
																role="tablist">
																<li role="presentation" class="active">
																	<%
																		if (idTipoEvento.equals("1")) {
																	%> <a
																	href="#tab_contentA" id="home-tab" role="tab"
																	data-toggle="tab" aria-expanded="true"> Productos </a>
																	<%
																		} else {
																	%> <a href="#tab_contentA" id="home-tab"
																	role="tab" data-toggle="tab" aria-expanded="true">
																		Servicios </a> <%
 	}
 %>

																</li>
																<%
																	if (idTipoEvento.equals("1")) {
																%>
																<li role="presentation" class=""><a
																	href="#tab_contentB" id="profile-tab" role="tab"
																	data-toggle="tab" aria-expanded="true"> Paquetes </a></li>
																<%
																	}
																%>
															</ul>




															<div id="myTab2Content" class="tab-content">




																<div role="tabpanel" class="tab-pane fade active in"
																	id="tab_contentA" aria-labelledby="home-tab">

																	Buscar: <input id="productosFilter"
																		onkeyup="selectProductosAplicables()" size="50px">

																	<div id="divProductosAplicables"></div>

																</div>


																<div role="tabpanel" class="tab-pane fade"
																	id="tab_contentB" aria-labelledby="home-tab">


																	Buscar: <input id="paquetesFilter"
																		onkeyup="selectPaquetesAplicables()" size="50px">


																	<div id="divPaquetesAplicables"></div>


																</div>



															</div>




														</div>
														<!--                     </div> -->






















														<!-- 														<div class="x_title"> -->
														<!-- 															<p> -->
														<!-- 																<strong>Productos Aplicables: </strong> -->
														<!-- 															</p> -->
														<!-- 															<ul class="nav navbar-right panel_toolbox"> -->
														<!-- 															</ul> -->
														<!-- 															<div class="clearfix"></div> -->
														<!-- 														</div> -->
														<!-- 														<div class="x_content"> -->
														<!-- 															<div id="divProductosAplicables"> -->
														<!-- 															</div> -->
														<!-- 														</div> -->









													</div>
												</div>

											</div>












										</div>
										<div role="tabpanel" class="tab-pane fade" id="tab_content4"
											aria-labelledby="profile-tab">



											<div class="col-md-6 col-sm-6 col-xs-12">
												<div class="x_panel">

													<div class="x_title">
														<p>
															<strong>Observaciones:</strong>
														</p>
														<ul class="nav navbar-right panel_toolbox">
														</ul>
														<div class="clearfix"></div>
													</div>
													<div class="x_content">
														<div id="divObservacionesEvento">
															<%
																ObservacionesEventoTable observacionesEventoTable = new ObservacionesEventoTable(request);
															%>

															<%=observacionesEventoTable.getHtml()%>
														</div>
													</div>
												</div>
											</div>

											<div class="col-md-6 col-sm-6 col-xs-12">
												<div class="x_panel">

													<div class="x_title">
														<p>
															<strong>Observaciones Aplicables: </strong>
														</p>
														<ul class="nav navbar-right panel_toolbox">
														</ul>
														<div class="clearfix"></div>
													</div>
													<div class="x_content">
														<div id="divObservacionesAplicables">
															<%
																ObservacionesAplicablesTable observacionesAplicablesTable = new ObservacionesAplicablesTable(request);
															%>

															<%=observacionesAplicablesTable.getHtml()%>
														</div>
													</div>
												</div>
											</div>



										</div>



										<%
											if (idTipoEvento.equals("1")) {
										%>
										<div role="tabpanel" class="tab-pane fade" id="tab_content2"
											aria-labelledby="profile-tab">





											<div class="col-md-6 col-sm-6 col-xs-12">
												<div class="x_panel">

													<div class="x_title">
														<p>
															<strong>Vi&aacute;ticos:</strong>
														</p>
														<ul class="nav navbar-right panel_toolbox">
														</ul>
														<div class="clearfix"></div>
													</div>
													<div class="x_content">
														<div id="divViaticosEvento">
															<%
																ViaticosEventoTable viaticosEventoTable = new ViaticosEventoTable(request);
															%>

															<%=viaticosEventoTable.getHtml()%>
														</div>
													</div>
												</div>
											</div>

											<div class="col-md-6 col-sm-6 col-xs-12">
												<div class="x_panel">

													<div class="x_title">
														<p>
															<strong>Vi&aacute;ticos Aplicables: </strong>
														</p>
														<ul class="nav navbar-right panel_toolbox">
														</ul>
														<div class="clearfix"></div>
													</div>
													<div class="x_content">
														<div id="divViaticosAplicables">
															<%
																ViaticosAplicablesTable viaticosAplicablesTable = new ViaticosAplicablesTable(request);
															%>

															<%=viaticosAplicablesTable.getHtml()%>
														</div>
													</div>
												</div>
											</div>







										</div>
										<%
											}
										%>




										<div role="tabpanel" class="tab-pane fade" id="tab_content3"
											aria-labelledby="profile-tab">

											<div class="col-md-6 col-sm-6 col-xs-12">
												<div class="x_panel">

													<div class="x_title">
														<p>
															<strong>Condiciones de Pago:</strong>
														</p>
														<ul class="nav navbar-right panel_toolbox">
														</ul>
														<div class="clearfix"></div>
													</div>
													<div class="x_content">
														<div id="divCondicionesEvento">
															<%
																CondicionesEventoTable condicionesEventoTable = new CondicionesEventoTable(request);
															%>

															<%=condicionesEventoTable.getHtml()%>
														</div>
													</div>
												</div>
											</div>

											<div class="col-md-6 col-sm-6 col-xs-12">
												<div class="x_panel">

													<div class="x_title">
														<p>
															<strong>Condiciones Aplicables: </strong>
														</p>
														<ul class="nav navbar-right panel_toolbox">
														</ul>
														<div class="clearfix"></div>
													</div>
													<div class="x_content">
														<div id="divCondicionesAplicables">
															<%
																CondicionesAplicablesTable condicionesAppTable = new CondicionesAplicablesTable(request);
															%>

															<%=condicionesAppTable.getHtml()%>
														</div>
													</div>
												</div>
											</div>
										</div>


										<%
											//if (idTipoEvento.equals("1")) {
										%>
										<div role="tabpanel" class="tab-pane fade" id="tab_content5"
											aria-labelledby="profile-tab">

											<!--                             <table class="table table-bordered"> -->
											<!--                             	<tr> -->
											<!--                             		<th> -->
											<!--                             		Liga para ingresar al pre-registro:  -->
											<!--                             		</th> -->
											<!--                             		<td> -->
											<!--                             		Password: -->
											<!--                             		</td> -->
											<!--                             	</tr> -->
											<!--                             	<tr> -->
											<%--                             		<th>http://<%= request.getServerName() %>/Prosante/registro/login.jsp?e=<%= (13798 * Integer.parseInt(id_evento)) %></th> --%>
											<%--                             		<td><%= (16489 * Integer.parseInt(id_evento)) %></td> --%>
											<!--                             	</tr> -->
											<!--                             </table> -->



											<div class="col-md-6 col-sm-6 col-xs-12">
												<div class="x_panel">

													<div class="x_title">
														<p>
															<strong>Pol&iacute;ticas de Cancelaci&oacute;n </strong>
														</p>
														<ul class="nav navbar-right panel_toolbox">
														</ul>
														<div class="clearfix"></div>
													</div>

													<div class="x_content">
														<div align="right">
															<!-- 										                  		<a href="#" onclick="nuevaSede()"> -->

															<!-- 										                  			<i class="fa fa-plus-circle fa-2x"></i> -->

															<!-- 										                  			Nueva -->




															<!-- 										                  		</a> -->
														</div>


														<div id="divPoliticasEvento">
															<%
																PoliticasEventoTable politicasEventoTable = new PoliticasEventoTable(request);
															%>

															<%=politicasEventoTable.getHtml()%>
														</div>
													</div>

												</div>
											</div>


											<div class="col-md-6 col-sm-6 col-xs-12">
												<div class="x_panel">

													<div class="x_title">
														<p>
															<strong>Pol&iacute;ticas Aplicables: <!-- 										                    <div id="divNombreSede"></div>  -->
															</strong>
														</p>
														<ul class="nav navbar-right panel_toolbox">
														</ul>
														<div class="clearfix"></div>
													</div>

													<div class="x_content">

														<!-- 										                    	<div align="right" id="divLinkNuevaFecha" style="display: none"> -->
														<!-- 											                  		<a href="#" onclick="nuevaFecha()"> -->
														<!-- 											                  		<i class="fa fa-plus-circle fa-2x"></i> -->
														<!-- 											                  			Nueva -->
														<!-- 											                  		</a> -->
														<!-- 										                  		</div> -->


														<div id="divPoliticasAplicables">
															<%
																PoliticasAplicablesTable politicasAppTable = new PoliticasAplicablesTable(request);
															%>

															<%=politicasAppTable.getHtml()%>
														</div>


													</div>

												</div>
											</div>







											<br> <br> <br>




										</div>
										<%
											//}
										%>









										<div role="tabpanel" class="tab-pane fade" id="tab_content6"
											aria-labelledby="profile-tab">


											<div class="col-md-12 col-sm-12 col-xs-12">
												<div class="x_panel">

													<div class="x_title">
														<p>
															<strong><% if(idTipoEvento.equals("1")) { %>Asignaci&oacute;n de Int&eacute;rpretes<% }else{ %>Traductores <% } %></strong>
														</p>
														<ul class="nav navbar-right panel_toolbox">
														</ul>
														<div class="clearfix"></div>
													</div>

													<div class="x_content">
														<div id="divInterpretesAsignados" style="overflow: auto">
															<%
																InterpretesAsignadosTable x4 = new InterpretesAsignadosTable(request);
															%>

															<%=x4.getHtml()%>
														</div>
													</div>

												</div>
											</div>









										</div>



										<%
											if (idTipoEvento.equals("1")) {
										%>
										<div role="tabpanel" class="tab-pane fade" id="tab_content9"
											aria-labelledby="profile-tab">


											<div class="col-md-12 col-sm-12 col-xs-12">
												<div class="x_panel">

													<div class="x_title">
														<p>
															<strong>Asignaci&oacute;n de T&eacute;cnicos</strong>
														</p>
														<ul class="nav navbar-right panel_toolbox">
														</ul>
														<div class="clearfix"></div>
													</div>

													<div class="x_content">
														<div id="divTecnicos" style="overflow: auto">
															<%
																ManejadorTecnico mnjTecnico = new ManejadorTecnico();
															%>

															<%=mnjTecnico.crarTablaTecnicosAsignados(id_evento_version)%>
														</div>
													</div>

												</div>
											</div>
										</div>
										<%
											}
										%>



										<div role="tabpanel" class="tab-pane fade <% if(isTecnicos) { %>active in<% } %>" id="tab_content8"
											aria-labelledby="profile-tab">


											<div class="col-md-12 col-sm-12 col-xs-12">
												<div class="x_panel">

													<div class="x_title">
														<p>
															<strong>Vista Previa</strong>
														</p>
														<ul class="nav navbar-right panel_toolbox">
														</ul>
														<div class="clearfix"></div>
													</div>

													<div class="x_content">
														<div id="divVistaPrevia"></div>
													</div>

												</div>
											</div>









										</div>



									   <div role="tabpanel" class="tab-pane fade" id="tab_contentX1" aria-labelledby="home-tab">
										    <br><br>
											<div class="page-wrap">

												<h3 class="text-center">Arrastre aqu&iacute; los adjuntos</h3>

										<!-- 		<div class="profile"> -->

										<!-- 			<div class="profile-avatar-wrap"> -->
										<!-- 				< -->
										<!-- 			</div> -->

										<!-- <!-- 			<h4>Foto de Perfil</h4> -->
										<!-- <!-- 			<div class="location">Palo Alto, CA</div> -->


										<!-- <!-- 			<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Consequatur voluptatem accusantium voluptas doloremque porro temporibus aut rerum possimus cum minus.</p> -->



										<!-- 		</div> -->

												<br><br><br>


												<div id="divArchivoAnexoTabla">
												<%= new ArchivoAnexoTabla(request).getHtml() %>
												</div>
												<!--
												TambiÃ©n puedes seleccionar un archivo.
												<br><br>
												<input type="file" id="uploader">
										 		-->
											</div>

		                         	 </div>


									</div>
								</div>

								<!-- Tabs End -->



								<!-- end form for validations -->
							</div>
						</div>
					</div>
				</div>






			</div>

		</div>


	</div>
	<!--           <div class="pull-right"> -->
	<%--             Perfil: < % =Role % >  &Uacute;ltima Conexi&oacute;n: < % =dtaUser[9]  % > --%>
	<!--           </div> -->
	<!--           <div class="clearfix"></div> -->

	<!--
      </div>
     </div>
    </div>-->










    <div class="modal fade" id="modalSeguimiento">
	  <div class="modal-dialog" role="document"  style="width:95% !important;">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title">Retroalimentaci&oacuten del Cliente</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body" style="overflow-y: auto;max-height: calc(100vh - 180px);">
	        	<div id="divTableComentariosCRM">
	        		<% CRMComentariosTable cmt  =  new CRMComentariosTable(eventoRecord.get("id_cliente"));
						//	cmt.setIdEvento(eventoRecord.get("id_evento"));
							%>
		  			 <%= cmt.getHtml() %>

		  		</div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" onclick="$('#modalNuevoSeguimiento').modal('show')" class="btn btn-primary">Agregar</button>
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
	      </div>
	    </div>
	  </div>
	</div>


<div class="modal fade" id="modalNuevoSeguimiento">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header" style="padding-bottom:0 !important;">
	        <h5 class="modal-title">Agregar Comentario</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body" style="padding:0px !important;">
						<%
						CRMComentariosForm cmtf = new CRMComentariosForm(eventoRecord.get("id_cliente"));
						cmtf.setIdEvento(eventoRecord.get("id_evento"));
						%>
	      	 	<%= cmtf.getFormHtml() %>
	      	 	<input type="hidden" value="<%= eventoRecord.get("id_cliente")%>" id="_idCliente"/>
	      </div>
	      <div class="modal-footer">
	        <button type="button" onclick="insertNewComment()" class="btn btn-primary">Agregar</button>
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
	      </div>
	    </div>
	  </div>
	</div>

	<div id="modalCotizadorGrupo" class="modal fade" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<input type="hidden" id="ID_EVENTO" name="ID_EVENTO">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">X</button>
					<h4 class="modal-title" id="myModalLabel2">Subtotales</h4>
				</div>
				<div class="modal-body">
					<form id="antoform2" class="form-horizontal calender" role="form">
						<div class="row">
							<div class="form-group">


								<table width="100%">
									<tr>
										<td colspan="2"><b>Nuevo Subtotal</b></td>
									</tr>
									<tr>
										<td>Nombre:</td>
										<td><input type="text" class="form-control"
											id="txtNombreSubgerupo" name="txtDireccionCorreo"></td>
									</tr>
									<tr>
										<td>Texto:</td>
										<td><input type="text" class="form-control"
											id="txtTextSubgerupo" name="txtDireccionCorreo"></td>
									</tr>
									<tr>
										<td colspan="2" align="right">

											<button type="button" class="btn btn-primary antosubmit2"
												onclick="funcNuevoSubgrupo(<%=id_evento_version%>)">Guardar</button>
										</td>
									</tr>
								</table>









							</div>

							<div class="form-group">
								<div id="divSubgrupo"></div>
							</div>
							</div>
						</div>

						<div class="modal-footer">
							<button type="button" class="btn btn-default antoclose2"
								data-dismiss="modal">Cerrar</button>

						</div>
				</div>
			</div>
		</div>



		<div id="modalEnviarCorreo" class="modal fade" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<input type="hidden" id="ID_EVENTO" name="ID_EVENTO">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">X</button>
						<h4 class="modal-title" id="myModalLabel2">Envio de
							Cotizaci&oacute;n al Cliente</h4>
					</div>
					<div class="modal-body">
						<form id="antoform2" class="form-horizontal calender" role="form">
							<div class="row">
								<div class="form-group">
									<label class="col-sm-3 control-label">Enviar a:</label>
									<div class="col-sm-9">
										<%
											String correo = "";
												try {
													correo = OneValueFactory
															.get("select IFNULL(correo,'') from crm_contacto c inner join evento e on c.id_contacto = e.id_contacto where id_evento ="
																	+ id_evento);
												} catch (Exception e) {
													System.out.println("No se encontro el correo del contacto,");
												}
												Record usuario = SessionBean.getInstance(request).getUser();

												//String mensajeDefaultEnvioCotizacion = "Buenas Tardes,<br><br>Adjunto la cotizaci&oacute;n solicitada para su evento.<br><br>Quedo en espera de sus comentarios.";
										%>
										<input type="hidden" id="nombreUsuario" name="nombreUsuario"
											value="<%=usuario.get("nombre")%>"> <input
											type="hidden" id="apellidoUsuario" name="apellidoUsuario"
											value="<%=usuario.get("apellido")%>"> <input
											type="text" class="form-control" id="txtDireccionCorreo"
											name="txtDireccionCorreo" value="<%=correo%>">
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-sm-3 control-label">CC:</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="txtCC"
											name="txtCC">
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-sm-3 control-label">CCO:</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="txtCCO"
											name="txtCCO">
									</div>
								</div>
								
								
								

								<div class="form-group">
									<label class="col-sm-3 control-label">Asunto:</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="txtAsunto"
											name="txtAsunto">
									</div>
								</div>
								<br>
								<div class="form-group">
									<label class="col-sm-3 control-label">Mensaje:</label>
									<div class="col-sm-9">
										<textarea style="width: 100%; height: 160px" id="txtMensaje"></textarea>
									</div>
								</div>
							</div>

						</form>
						<form id="formXYZZ">
							<!--<input type="hidden" id="ID_EVENTO" name="ID_EVENTO">

          	 <button type="button" class="btn btn-default" onclick="window.open('reporte_cotizacion_pdf.jsp)'">Ver PDF de Cotizaci&oacute;n</button>
          	<input type="button"  class="btn btn-default"  onclick="funcMostrarPDF();" value="Ver PDF de Cotizaci&oacute;n" />
          	</form>
          	-->
					</div>

					<div class="modal-footer">

						<button type="button" class="btn btn-default antoclose2"
							data-dismiss="modal">Cancelar</button>
						<button type="button" class="btn btn-primary antosubmit2"
							onclick="funcEnviarCotizacionCliente()">Enviar</button>
					</div>

				</div>
			</div>
		</div>

		<div id="modalEnviarCorreoCierre" class="modal fade" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<input type="hidden" id="ID_EVENTOC" name="ID_EVENTOC">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">X</button>
						<h4 class="modal-title" >Envio de
							Notifiaci&oacute;n de Cierre al Cliente</h4>
					</div>
					<div class="modal-body">
						<form id="antoform2" class="form-horizontal calender" role="form">
							<div class="row">
								<div class="form-group">
									<label class="col-sm-3 control-label">Enviar a:</label>
									<div class="col-sm-9">
										<%
											String correo_c = "";
												try {
													correo_c = OneValueFactory
															.get("select correo from crm_contacto c inner join evento e on c.id_contacto = e.id_contacto where id_evento ="
																	+ id_evento);
												} catch (Exception e) {
													System.out.println("No se encontro el correo del contacto,");
												}


												//String mensajeDefaultEnvioCotizacion = "Buenas Tardes,<br><br>Adjunto la cotizaci&oacute;n solicitada para su evento.<br><br>Quedo en espera de sus comentarios.";
										%>
										<input type="hidden" id="nombreUsuario_c" name="nombreUsuario_c"
											value="<%=usuario.get("nombre")%>"> <input
											type="hidden" id="apellidoUsuario_c" name="apellidoUsuario_c"
											value="<%=usuario.get("apellido")%>"> <input
											type="text" class="form-control" id="txtDireccionCorreo_c"
											name="txtDireccionCorreo_c" value="<%=correo_c%>">
									</div>
								</div>

								<div class="form-group">
									<label class="col-sm-3 control-label">Asunto:</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="txtAsunto_c"
											name="txtAsunto_c">
									</div>
								</div>
								<br>
								<div class="form-group">
									<label class="col-sm-3 control-label">Mensaje:</label>
									<div class="col-sm-9">
										<textarea style="width: 100%; height: 160px" id="txtMensaje_c"></textarea>
									</div>
								</div>
							</div>

						</form>
						<form id="formXYZZ">
							<!--<input type="hidden" id="ID_EVENTO" name="ID_EVENTO">

          	 <button type="button" class="btn btn-default" onclick="window.open('reporte_cotizacion_pdf.jsp)'">Ver PDF de Cotizaci&oacute;n</button>
          	<input type="button"  class="btn btn-default"  onclick="funcMostrarPDF();" value="Ver PDF de Cotizaci&oacute;n" />
          	</form>
          	-->
					</div>

					<div class="modal-footer">

						<button type="button" class="btn btn-default antoclose2"
							data-dismiss="modal">Cancelar</button>
						<button type="button" class="btn btn-primary antosubmit2"
							onclick="funcEnviarCierreCliente()">Enviar</button>
					</div>

				</div>
			</div>
		</div>


		<div id="modalTecnicosDisponibles" class="modal fade" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<input type="hidden" id="ID_EVENTO" name="ID_EVENTO">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">X</button>
						<h4 class="modal-title" id="myModalLabel2">Envio</h4>
					</div>
					<div class="modal-body">
						<form id="antoform22" class="form-horizontal calender" role="form">
							<div class="row">


								<div class="form-group">
									<label class="col-sm-3 control-label">T&eacute;cnico:</label>
									<div class="col-sm-9">
										<div id="divTecnicosDisponibles"></div>
									</div>
								</div>
							</div>

						</form>
					</div>
				</div>
			</div>
		</div>







		<div id="fc_edit" data-toggle="modal" data-target="#CalenderModalEdit"></div>






		<div id="CalenderModalEdit" class="modal fade" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width:  90%">
				<div class="modal-content">

					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">x</button>
						<h4 class="modal-title" id="myModalLabel2">Asignaci&oacute;n
							de Int&eacute;rprete</h4>
					</div>
					<div class="modal-body">




						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="x_panel">

								<div class="x_title">
									<p>
										<strong>Int&eacute;rpretes Disponibles: <!-- 										                    <div id="divNombreSede"></div>  -->
										</strong>
									</p>
									<ul class="nav navbar-right panel_toolbox">
									</ul>
									<div class="clearfix"></div>
								</div>

								<div class="x_content">

									<!-- 										                    	<div align="right" id="divLinkNuevaFecha" style="display: none"> -->
									<!-- 											                  		<a href="#" onclick="nuevaFecha()"> -->
									<!-- 											                  		<i class="fa fa-plus-circle fa-2x"></i> -->
									<!-- 											                  			Nueva -->
									<!-- 											                  		</a> -->
									<!-- 										                  		</div> -->



									<div>
										<table width="100%">
											<tr>

											</tr>
										</table>
									</div>


									<div class="col-md-3 col-sm-3 col-xs-12">
										<table>
											<tr>
												<th>Especialidades</th>
											</tr>
											<%
												ArrayList<Record> especialidades = DataArray
															.getArrayList("SELECT * FROM cat_especialidad ORDER BY nombre");

													for (Record especialidad : especialidades) {
											%>
											<tr>
												<td><input type="checkbox"
													id='especialidad_<%=especialidad.getString("id_especialidad")%>'
													onclick="doFilterInterpretes()"><%=especialidad.get("nombre")%>
												</th>
											</tr>
											<%
												}
											%>
										</table>
									</div>

									<div class="col-md-3 col-sm-3 col-xs-12">
										<table>
											<tr>
												<th>Idiomas</th>
											</tr>
											<%
												ArrayList<Record> idiomas = DataArray
															.getArrayList("SELECT * FROM cat_idioma_traduccion ORDER BY idioma");

													for (Record idioma : idiomas) {
											%>
											<tr>
												<td><input type="checkbox"
													id='idioma_<%=idioma.getString("id_idioma")%>'
													onclick="doFilterInterpretes()"><%=idioma.get("idioma")%>
												</th>
											</tr>
											<%
												}
											%>
										</table>
									</div>


									<div class="col-md-6 col-sm-6 col-xs-12">
										<div id="divInterpretesDisponibles" >
											<%
												//PoliticasAplicablesTable politicasAppTable = new PoliticasAplicablesTable(request);
											%>

											<%-- -= politicasAppTable.getHtml() --%>
										</div>
									</div>

								</div>

							</div>
						</div>





						<!--             <div id="testmodal2" style="padding: 5px 20px;"> -->
						<!--               <form id="antoform2" class="form-horizontal calender" role="form"> -->

						<!--                 <div class="form-group"> -->
						<!--                   <label class="col-sm-3 control-label">Nombre del Evento</label> -->
						<!--                   <div class="col-sm-9"> -->
						<!--                     <input type="text" class="form-control" id="txtNombreEvento" name="txtNombreEvento"> -->
						<!--                   </div> -->
						<!--                 </div> -->
						<!--                 <div class="form-group"> -->
						<!--                   <label class="col-sm-3 control-label">Cliente</label> -->
						<!--                   <div class="col-sm-9"> -->

						<%-- --- = SelectList.getListByQuery("listCliente", "select id_cliente, nombre from crm_cliente WHERE id_status = 1 order by nombre", "(Seleccionar)", "", "") --%>
						<!--                   </div> -->
						<!--                 </div> -->
						<!--                 <div class="form-group"> -->
						<!--                   <label class="col-sm-3 control-label">Moneda:</label> -->
						<!--                   <div class="col-sm-9"> -->
						<%--   = SelectList.getListByQuery("listMoneda", "select id_moneda, nombre from cat_moneda order by nombre", "(Seleccionar)", "", "") --%>
						<!--                   </div> -->
						<!--                 </div> -->

						<!--               </form> -->
						<!--             </div> -->
					</div>
					<div class="modal-footer">
						<!--             <button type="button" class="btn btn-default antoclose2" data-dismiss="modal">Cancelar</button> -->
						<!--              <button type="button" class="btn btn-primary antosubmit2" onclick="confirmarCrearEvento()">Crear Evento</button>  -->
					</div>
				</div>
			</div>
		</div>
















		<div id="modalCancelacion" class="modal fade" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<input type="hidden" id="ID_EVENTO" name="ID_EVENTO">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">X</button>
						<h4 class="modal-title" id="myModalLabel2">Cancelaci&oacute;n</h4>
					</div>
					<div class="modal-body">

						<div id="testmodal2" style="padding: 5px 20px;">
							<form id="antoform2" class="form-horizontal calender" role="form">

								<div class="form-group">
									<label class="col-sm-3 control-label">Motivo
										Cancelaci&oacute;n:</label>
									<div class="col-sm-9">
										<textarea rows="2" id="txtMotivoCancelacion"
											class="form-control"></textarea>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-sm-3 control-label">Aplica
										Penalizaci&oacute;n:</label>
									<div class="col-sm-9">
										<input type="checkbox" id="chkAplicaPenalizacion"
											onchange="funcAplicaPorcentajes()">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">Int&eacute;rpretes:</label>
									<div class="col-sm-9">
									<div class="input-group">
										<input
											onkeypress="return event.charCode >= 48 && event.charCode <= 57"
											type="number" min="0" max="100" class="form-control"
											id="txtPorcentajeInterpretes" name="txtPorcentajeInterpretes">
											  <div class="input-group-addon"> % </div>
											</div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">Equipo:</label>
									
									<div class="col-sm-9">
										<div class="input-group">
										<input
											onkeypress="return event.charCode >= 48 && event.charCode <= 57"
											type="number" min="0" max="100" class="form-control"
											id="txtPorcentajeEquipo" name="txtPorcentajeEquipo">
											<div class="input-group-addon"> % </div>
											</div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">Pago
										Int&eacute;rpretes:</label>
									<div class="col-sm-9">
										<div class="input-group">
										<input
											onkeypress="return event.charCode >= 48 && event.charCode <= 57"
											type="number" min="0" max="100" class="form-control"
											id="txtPagoInterpretes" name="txtPagoInterpretes">
											<div class="input-group-addon"> % </div>
											</div>
									</div>
								</div>
								<div class="form-group">
								<span class="col-sm-3"></span>
									<span style="color:red;" class="col-sm-9"><b>NOTA: En caso de  penalizaci&oacute;n  se deber&aacute; asignar un % sobre  el costo total a los int&eacute;rpretes y equipo.</b></span>
								</div>
							</form>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default antoclose2"
							data-dismiss="modal">Cancelar</button>
						<button type="button" class="btn btn-primary antosubmit2"
							onclick="funcConfirmarCancelacion()">Guardar</button>
					</div>
				</div>
			</div>
		</div>














		<!-- jQuery -->
		<script src="../home/vendors/jquery/dist/jquery.min.js"></script>










		<script type="text/javascript">




	// Para habilitar el CKEDITOR solo descomentar las siguientes 2 lineas.

    //CKEDITOR.replace( 'txtMensaje' );
    //CKEDITOR.instances['txtMensaje'].on('change', function() { CKEDITOR.instances['txtMensaje'].updateElement() });

//     timer = setInterval(updateDiv,100);
//     function updateDiv(){
//         var editorText = CKEDITOR.instances.cuerpo.getData();
//         $('#trackingDiv').html(editorText);
//     }
</script>









		<%@include file="commonSideBarMenuEvento_js.jsp"%>
		<script src="../home/vendors/sweet_alert/sweetalert.min.js"></script>
		<script type="text/javascript"
			src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>


<!-- 		<script type="text/javascript" -->
<!-- 			src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/jquery-ui.min.js"></script> -->

		<!-- Bootstrap -->
		<script src="../home/vendors/bootstrap/dist/js/bootstrap.min.js"></script>
		<!-- FastClick -->
		<script src="../home/vendors/fastclick/lib/fastclick.js"></script>
		<!-- NProgress -->
		<script src="../home/vendors/nprogress/nprogress.js"></script>
		<!-- iCheck -->
		<script src="../home/vendors/iCheck/icheck.min.js"></script>
		<!-- Datatables -->
		<script
			src="../home/vendors/datatables.net/js/jquery.dataTables.min.js"></script>
		<script
			src="../home/vendors/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
		<script
			src="../home/vendors/datatables.net-buttons/js/dataTables.buttons.min.js"></script>
		<script
			src="../home/vendors/datatables.net-buttons-bs/js/buttons.bootstrap.min.js"></script>
		<script
			src="../home/vendors/datatables.net-buttons/js/buttons.flash.min.js"></script>
		<script
			src="../home/vendors/datatables.net-buttons/js/buttons.html5.min.js"></script>
		<script
			src="../home/vendors/datatables.net-buttons/js/buttons.print.min.js"></script>
		<script
			src="../home/vendors/datatables.net-fixedheader/js/dataTables.fixedHeader.min.js"></script>
		<script
			src="../home/vendors/datatables.net-keytable/js/dataTables.keyTable.min.js"></script>
		<script
			src="../home/vendors/datatables.net-responsive/js/dataTables.responsive.min.js"></script>
		<script
			src="../home/vendors/datatables.net-responsive-bs/js/responsive.bootstrap.js"></script>
		<script
			src="../home/vendors/datatables.net-scroller/js/dataTables.scroller.min.js"></script>
		<script src="../home/vendors/jszip/dist/jszip.min.js"></script>
		<script src="../home/vendors/pdfmake/build/pdfmake.min.js"></script>
		<script src="../home/vendors/pdfmake/build/vfs_fonts.js"></script>

		<!-- Switchery -->
		<script src="../home/vendors/switchery/dist/switchery.min.js"></script>

		<script src="../home/js/bootstrap-datepicker.js"></script>
		<script src="../home/js/bootstrap-timepicker.js"></script>
		<script src="vendors/moment/min/moment.min.js"></script>
		<script src="vendors/fullcalendar/dist/fullcalendar.js"></script>
		<!-- Custom Theme Scripts -->
		<script src="../home/build/js/custom.min.js"></script>




        <%@include file="adm_evento_sedes-js.jsp"%>


		<script type="text/javascript">
		//alert("ok");

		selectSalas();


		<%if (!idTipoEvento.equals("1")) {%>

		selectSalaTraduccionEscrita();

		<%}%>


		selectFechasEvento();


	</script>



		<script src="vendors/DragAvatar/resample.js"></script>
	<script src="vendors/DragAvatar/avatarAnyFileType.js"></script>




<script type="text/javascript">


function reloadAnexos() {



	 $.ajax({
			type:'POST',
			url :"/SAO/ArchivoAnexosSelectServlet",
			data: {
				ID_EVENTO : <%= id_evento_version %>
			},
			success: function(data) {

				//selectPoliticasEvento();
				//selectPoliticasAplicables();

				//$('#divFechas').html(data);

				$("#divArchivoAnexoTabla").empty();
				$("#divArchivoAnexoTabla").append(data);

				$("#divArchivoAnexoTabla").hide();
				$("#divArchivoAnexoTabla").show();

			},
			error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
		});
}



function deleteAnexo(param_id_evento_anexo) {

	var c = confirm("Seguro?");

	if(c == false) {

		return;
	}


	 $.ajax({
			type:'POST',
			url :"/SAO/ArchivoAnexoDeleteServlet",
			data: {
				id_evento_anexo : param_id_evento_anexo
			},
			success: function(data) {


				reloadAnexos();
			},
			error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
		});
}



//jQuery.event.props.push('dataTransfer');




	$("body").on('drop', function(event) {




	   // Or else the browser will open the file
	  event.preventDefault();

	   // Do something with the file(s)
	   var files = event.dataTransfer.files;


	   //alert(files);

	   var resampledFile = files[0];


	   var xhr = new XMLHttpRequest();
	   var fd = new FormData();

	   fd.append('file', resampledFile);


	   xhr.onreadystatechange = function() {

		   //alert("ok");

		   d = new Date();


		   reloadAnexos();


	// 	   if (this.readyState == 4 && this.status == 200) {
	//            document.getElementById("demo").innerHTML =
	//            this.responseText;
	//       }
	   };



	   xhr.open('POST', "/<%= HardCodeConstants.CONTEXT_PATH %>/ArchivoAnexoUploadServlet?id_evento=<%=id_evento_version%>", true);
	   xhr.send(fd);

	});





</script>







		<!-- Datatables -->
		<script>
		(function($){
			$.fn.datepicker.dates['es'] = {
				days: ["Domingo", "Lunes", "Martes", "Míercoles", "Jueves", "Viernes", "Sábado"],
				daysShort: ["Dom", "Lun", "Mar", "Mí", "Jue", "Vie", "Sá"],
				daysMin: ["Do", "Lu", "Ma", "Mi", "Ju", "Vi", "Sa"],
				months: ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"],
				monthsShort: ["Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"],
				today: "Hoy",
				monthsTitle: "Meses",
				clear: "Borrar",
				weekStart: 1,
				format: "dd/mm/yyyy"
			};
		}(jQuery));


		$(document).ready(function() {
			$('#detalleCotizacion').hide();
			var handleDataTableButtons = function() {
				if ($("#datatable-buttons").length) {
					$("#datatable-buttons").DataTable({
						dom : "Bfrtip",
						buttons : [ {
							extend : "copy",
							className : "btn-sm"
						}, {
							extend : "csv",
							className : "btn-sm"
						}, {
							extend : "excel",
							className : "btn-sm"
						}, {
							extend : "pdfHtml5",
							className : "btn-sm"
						}, {
							extend : "print",
							className : "btn-sm"
						}, ],
						responsive : true
					});
				}
			};

			TableManageButtons = function() {
				"use strict";
				return {
					init : function() {
						handleDataTableButtons();
					}
				};
			}();

			$('#datatable').dataTable();

			$('#datatable-keytable').DataTable({
				keys : true
			});

			$('#datatable-responsive').DataTable();

			$('#datatable-scroller').DataTable({
				ajax : "js/datatables/json/scroller-demo.json",
				deferRender : true,
				scrollY : 380,
				scrollCollapse : true,
				scroller : true
			});

			$('#datatable-fixed-header').DataTable({
				fixedHeader : true
			});

			var $datatable = $('#datatable-checkbox');

			$datatable.dataTable({
				'order' : [ [ 1, 'asc' ] ],
				'columnDefs' : [ {
					orderable : false,
					targets : [ 0 ]
				} ]
			});
			$datatable.on('draw.dt', function() {
				$('input').iCheck({
					checkboxClass : 'icheckbox_flat-green'
				});
			});

			TableManageButtons.init();


			window.formato();




		});
	</script>
		<!-- /Datatables -->

		<script type="text/javascript">
		//   function readURL(input) {
		//       if (input.files && input.files[0]) {
		//           var reader = new FileReader();

		//           reader.onload = function (e) {
		//               $('#blah')
		//                   .attr('src', e.target.result)
		//                   .width(170)
		//                   .height(155);
		//               $('#perfil_img')
		//               .val(e.target.result);
		//           };
		//           reader.readAsDataURL(input.files[0]);
		//       }
		//   }
	</script>
</body>
</html>
<%
	//     } else {
		//     	response.sendRedirect("adm_eventos_list.jsp");
		//     	System.out.println("Khe?");
		//     }
	} catch (Exception _e) {
		System.out.println("Error" + _e);
		_e.printStackTrace();

		%>
		<script>window.href='adm_eventos_list.jsp'</script>
		<%

	}
%>

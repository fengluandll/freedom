<%
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
	response.setHeader("Pragma","no-cache"); //HTTP 1.0
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>

<%@page import="com.movemini.layers.session.SessionBean"%>
<%@page import="com.movemini.simpleflexgrid.components.SelectList"%>
<%@page import="com.movemini.data.OneRecordFactory"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="org.apache.tomcat.util.codec.binary.Base64"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<%
try
{
	boolean access = true;


 	String id_evento_status = (String) session.getAttribute("v");
 	String usuarioConsultaCode = (String) session.getAttribute("u");




 	session.setAttribute("id_evento_status", id_evento_status);

 	Record eventoStatusRecord = null;
 	
 	if("ALL".equals(id_evento_status)) {
 	
 		eventoStatusRecord = OneRecordFactory.getFirstRecord("SELECT * FROM cat_evento_status");
 	
 	} else {
 		
 		eventoStatusRecord = OneRecordFactory.getFirstRecord("SELECT * FROM cat_evento_status WHERE id_evento_status = " + id_evento_status);
 	}
 	


    if(access
//     		session.getAttribute("ID_USER") != null
    		) {




%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>SAO - Eventos</title>

    <!-- Bootstrap -->
    <link href="../home/vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="../home/vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="../home/vendors/nprogress/nprogress.css" rel="stylesheet">
    <!-- iCheck -->
    <link href="../home/vendors/iCheck/skins/flat/green.css" rel="stylesheet">
    <link href="../home/vendors/datatables.net-bs/css/dataTables.bootstrap.min.css" rel="stylesheet">
		<!-- Datatables -->
    <link href="../home/vendors/datatables.net-buttons-bs/css/buttons.bootstrap.min.css" rel="stylesheet">
    <link href="../home/vendors/datatables.net-fixedheader-bs/css/fixedHeader.bootstrap.min.css" rel="stylesheet">
    <link href="../home/vendors/datatables.net-responsive-bs/css/responsive.bootstrap.min.css" rel="stylesheet">
    <link href="../home/vendors/datatables.net-scroller-bs/css/scroller.bootstrap.min.css" rel="stylesheet">
||||<link href="../home/vendors/summernote/summernote.css" rel="stylesheet">

    <!-- Custom Theme Style -->
    <link href="../home/build/css/custom.css" rel="stylesheet">


    <link href="../home/vendors/sweet_alert/sweetalert.css" rel="stylesheet">
    <link href="../home/vendors/DateJS/prettify.css" rel="stylesheet" />
    <link href="../home/vendors/bootstrap-wysiwyg/css/style.css" rel="stylesheet">

     <%

                    	String message = "";

                    	if(request.getParameter("idEventoChangeStatus") != null) {


                    		String xid = request.getParameter("idEventoChangeStatus");
                    		String xidStat = request.getParameter("idStatus");


                    		ConnectionWrapper.staticExecuteUpdate("UPDATE evento SET id_evento_status = '" + xidStat + "' WHERE id_evento = (select id_evento from evento_version where id_evento_version = "+xid+")");


                    		message = "Se cambi&oacute; el estatus del evento";
                    	}


                    %>



  </head>
  <body class="nav-md">

    <div class="container body">
      <div class="main_container">



      	<%@include file="commonSideBarMenu.jsp" %>


         <div class="right_col" role="main">
          <div class="">
            <div class="page-title">
            </div>


            <div class="clearfix"></div>



			<div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel">
                  <div class="x_title">
                    <p><strong><%= ((usuarioConsultaCode.equals("m")) ? "Mis Cotizaciones" : "Todas las Cotizaciones") %> / <%= eventoStatusRecord.get("nombre").toUpperCase() %></strong> </p>
                    <ul class="nav navbar-right panel_toolbox">
                    </ul>
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                    <p class="text-muted font-13 m-b-30">




                    </p>
										<%   	if(id_evento_status.equals("1") ) {%>
	                    <div align="right">
	                  	<p class="text-muted font-13 m-b-30">
	                      <a href="#" onclick="nuevoEvento()" type="button" class="btn btn-info">Nuevo Evento</a>
	                    </p>
	                    <br>
	                    </div>
										<% } %>




                    <table id="datatable-buttons" class="table table-striped table-bordered">
                      <thead>
                        <tr>
                          <th>Folio</th>
                          <th>Folio Anterior</th>
                          <th>Tipo Evento</th>
                          <th>Nombre del Evento</th>

                          <th>Empresa</th>


                          <!-- <th>Lugar</th>
                          <th>Fecha Inicio</th>
                          <th>Fecha Fin</th> -->
                          <th>Nombre Contacto</th>



                          <% if(((String)session.getAttribute("u")).equals("a")) { %>
                          <th>Usuario</th>
                          <% } %>


<!--                           <th>Estado</th>  -->
                          <th>Cotizaci&oacute;n</th>
                        </tr>
                      </thead>

                      <tbody>
                        <%


                        Record myUser = SessionBean.getInstance(request).getUser();


                        String id_usr = myUser.get("id_usuario");


                        ArrayList<Record> eventos = null;

                        boolean showBtnMemo = false;


                        if(usuarioConsultaCode.equals("m")) {


                        	eventos = DataArray.getArrayList("evento_select_pr", id_usr, id_evento_status);

													if(id_evento_status.equals("5") || id_evento_status.equals("8")) {

														showBtnMemo = true;
													}

                        } else {


                        	eventos = DataArray.getArrayList("evento_select_all_pr", id_evento_status);


                        	if(id_evento_status.equals("5") || id_evento_status.equals("8")) {

                        		showBtnMemo = true;
                        	}

                        }




                        
                        if(myUser.get("id_rol").equals(HardCodeConstants.ID_ROL_TECNICOS)) {
                        	
                        	showBtnMemo = false;
                        }
                        
                        



//                         Statement stUser = conn.createStatement();
//                    		ResultSet rsUser = stUser.executeQuery("SELECT ev.*, em.NOMBRE, em.NOMBRE_CONTACTO FROM evento ev INNER JOIN empresa em on em.ID = ev.ID_EMPRESA");





                   		for(Record evento : eventos){
//                   		while(rsUser.next()){
            		      %>
            		      <tr>
            		      	<td><%=evento.getString("clave_cotizacion") %></td>
            		      	<td><%=evento.getString("folio_anterior") %></td>
            		      	<td><%=evento.getString("tipo_evento") %></td>
            		      	  <td><%=evento.getString("nombre_evento") %></td>

	            		      <td><%=evento.getString("nombre_cliente") %></td>


	            		      <td><%=evento.getString("nombre_contacto") %></td>



	            		      <% if(((String)session.getAttribute("u")).equals("a")  || ((String)session.getAttribute("u")).equals("m") ) { %>
														<% if(((String)session.getAttribute("u")).equals("a") ){ %>
																<td><%=evento.getString("nombre_usuario") %></td>
															<%}%>

	                          <% } %>


            		          <td>
            		          	<a 	href="adm_evento_sedes.jsp?ID_EVENTO=<%=evento.getString("id_ultima_version")%>"
            		          		class="btn btn-info">
            		          		<i class="fa fa-pencil"> Ver Cotizaci&oacute;n</i></a>


            		          	<% if(showBtnMemo  ) {
            		          		if( !evento.getString("tipo_evento").contains("escrita")){
            		          		%>

            			          <%-- <a 	href="reporte_memo_servicio_pdf.jsp?ID_EVENTO=<%=evento.getString("id_ultima_version")%>" target="_blank" class="btn btn-info"> --%>
            			          		<a 	onclick="mostrarModalMemoServicio(<%=evento.getString("id_ultima_version")%>)" target="_blank" class="btn btn-info">
            			          		<i class="fa fa-pencil"> Memo de Servicio</i></a>

            		          	<% 		}
            		          		%>
            		          		<a 	onclick="mostrarModalFacturas(<%=evento.getString("id_ultima_version")%>)" target="_blank" class="btn btn-info">
        			          		<i class="fa fa-money"> Facturas</i></a>
            		          		<%
            		          		} %>


            		          </td>
            		      </tr>
            		      <%
                  		}
                        %>
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>


     </div>
    </div>


               <footer>
          <div class="pull-right">
<%--             Perfil: <%=Role %>  &Uacute;ltima Conexi&oacute;n: <%=dtaUser[9] %> --%>
          </div>
          <div class="clearfix"></div>
        </footer>
        <!-- /footer content -->
      </div>
    </div>




    <div id="modalMemoServicio" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
      <div class="modal-dialog modal-lg" style="width: 1150px;">
        <div class="modal-content">

          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
            <h4 class="modal-title" id="myModalLabel2">Memo de Servicio</h4>
          </div>
          <div class="modal-body">
			<div class="" role="tabpanel" data-example-id="togglable-tabs">
				<ul id="myTab" class="nav nav-tabs bar_tabs" role="tablist">
					<li role="presentation" class="active"><a
						href="#tab_content0" id="home-tab" role="tab"
						data-toggle="tab" aria-expanded="true">Captura</a></li>
					<li role="presentation"><a
						href="#tab_content1" id="home-tab" role="tab"
						data-toggle="tab" aria-expanded="true">Vista Previa</a></li>
					<li role="presentation"><a
						href="#tab_content2" id="home-tab" role="tab"
						data-toggle="tab" aria-expanded="true">Enviar</a></li>
				</ul>
				<div id="myTabContent" class="tab-content">
					<div role="tabpanel" class="tab-pane fade active in" id="tab_content0" aria-labelledby="home-tab">
						<div class="x_panel">

							<div class="x_title">
								<p>
									<strong>Captura</strong>
								</p>
								<ul class="nav navbar-right panel_toolbox">
								</ul>
								<div class="clearfix"></div>
							</div>

							<div class="x_content">


								<div class="col-md-6 col-sm-6 col-xs-12">
									<div id="divCapturaMemoEvento">

									</div>
								</div>


							</div>
						</div>
					</div>
					<div role="tabpanel" class="tab-pane fade" id="tab_content1" aria-labelledby="home-tab">
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


								<div class="col">
									<div id="divVistaPrevia">

									</div>
								</div>


							</div>
						</div>
					</div>
					<div role="tabpanel" class="tab-pane fade" id="tab_content2" aria-labelledby="home-tab">
						<div class="x_panel">

							<div class="x_title">
								<p>
									<strong>Enviar</strong>
								</p>
								<ul class="nav navbar-right panel_toolbox">
								</ul>
								<div class="clearfix"></div>
							</div>

							<div class="x_content">

								<div class="row">
								<div class="col-md-6 col-sm-6 col-xs-12">
									<p>
										<strong>Envio al Cliente</strong>
									</p>
									<div id="divEnvioMemoEventoCLiente">
										<div class="form-group" style="float: left; width: 100%;">
						                  <label class="col-sm-3 control-label">Enviar a:</label>
						                  <div class="col-sm-9">
						                    <input type="text" class="form-control" id="txtCorreoCLiente" name="txtCorreoCLiente">
						                  </div>
						                </div>

						                <div class="form-group" style="float: left; width: 100%;">
						                  <label class="col-sm-3 control-label">Asunto:</label>
						                  <div class="col-sm-9">
						                    <input type="text" class="form-control" id="txtAsuntoCLiente" name="txtAsuntoCLiente">
						                  </div>
						                </div>
						                <div class="form-group" style="float: left; width: 100%;">
						                  <label class="col-sm-3 control-label">Mensaje:</label>
						                  <div class="col-sm-9">
												<div class="btn-toolbar" data-role="editor-toolbar" data-target="#editorCliente">
													<div class="btn-group">
														<a class="btn btn-default dropdown-toggle" data-toggle="dropdown" title="Font Size"><i class="fa fa-text-height"></i>&nbsp;<b class="caret"></b></a>
														<ul class="dropdown-menu">
															<li><a data-edit="fontSize 5" class="fs-Five">Huge</a></li>
															<li><a data-edit="fontSize 3" class="fs-Three">Normal</a></li>
															<li><a data-edit="fontSize 1" class="fs-One">Small</a></li>
														</ul>
													</div>
													<div class="btn-group">
														<a class="btn btn-default" data-edit="bold" title="Bold (Ctrl/Cmd+B)"><i class="fa fa-bold"></i></a>
														<a class="btn btn-default" data-edit="italic" title="Italic (Ctrl/Cmd+I)"><i class="fa fa-italic"></i></a>
														<a class="btn btn-default" data-edit="strikethrough" title="Strikethrough"><i class="fa fa-strikethrough"></i></a>
														<a class="btn btn-default" data-edit="underline" title="Underline (Ctrl/Cmd+U)"><i class="fa fa-underline"></i></a>
													</div>
													<div class="btn-group">
														<a class="btn btn-default" data-edit="insertunorderedlist" title="Bullet list"><i class="fa fa-list-ul"></i></a>
														<a class="btn btn-default" data-edit="insertorderedlist" title="Number list"><i class="fa fa-list-ol"></i></a>
														<a class="btn btn-default" data-edit="outdent" title="Reduce indent (Shift+Tab)"><i class="fa fa-outdent"></i></a>
														<a class="btn btn-default" data-edit="indent" title="Indent (Tab)"><i class="fa fa-indent"></i></a>
													</div>
													<div class="btn-group">
														<a class="btn btn-default" data-edit="justifyleft" title="Align Left (Ctrl/Cmd+L)"><i class="fa fa-align-left"></i></a>
														<a class="btn btn-default" data-edit="justifycenter" title="Center (Ctrl/Cmd+E)"><i class="fa fa-align-center"></i></a>
														<a class="btn btn-default" data-edit="justifyright" title="Align Right (Ctrl/Cmd+R)"><i class="fa fa-align-right"></i></a>
														<a class="btn btn-default" data-edit="justifyfull" title="Justify (Ctrl/Cmd+J)"><i class="fa fa-align-justify"></i></a>
													</div>
													<div class="btn-group">
														<a class="btn btn-default dropdown-toggle" data-toggle="dropdown" title="Hyperlink"><i class="fa fa-link"></i></a>
														<div class="dropdown-menu input-append">
															<input placeholder="URL" type="text" data-edit="createLink" />
															<button class="btn" type="button">Add</button>
														</div>
													</div>
													<div class="btn-group">
														<a class="btn btn-default" data-edit="unlink" title="Remove Hyperlink"><i class="fa fa-unlink"></i></a>
														<span class="btn btn-default" title="Insert picture (or just drag & drop)" id="pictureBtn"> <i class="fa fa-picture-o"></i>
															<input class="imgUpload" type="file" data-role="magic-overlay" data-target="#pictureBtn" data-edit="insertImage" />
														</span>
													</div>
													<div class="btn-group">
														<a class="btn btn-default" data-edit="undo" title="Undo (Ctrl/Cmd+Z)"><i class="fa fa-undo"></i></a>
														<a class="btn btn-default" data-edit="redo" title="Redo (Ctrl/Cmd+Y)"><i class="fa fa-repeat"></i></a>
														<a class="btn btn-default" data-edit="html" title="Clear Formatting"><i class='glyphicon glyphicon-pencil'></i></a>
													</div>
												</div>
												<div id="editorCliente" style="overflow: auto; border: 1px solid rgb(180, 180, 180); height: 400px;" class="lead" data-placeholder="Editar memo de servicio."></div>
												<div id="editorPreview"></div>
						                  </div>
						                </div>
									</div>
								</div>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<p>
										<strong>Envio Omnilingua</strong>
									</p>
									<div id="divEnvioMemoEventoOmni">
										<div class="form-group" style="float: left; width: 100%;">
						                  <label class="col-sm-3 control-label">Enviar a:</label>
						                  <div class="col-sm-9">
						                    <input type="text" class="form-control" id="txtCorreoOmni" name="txtCorreoOmni">
						                  </div>
						                </div>	<div class="form-group" style="float: left; width: 100%;">
																				<label class="col-sm-3 control-label">Correo interpretes:</label>
																				<div class="col-sm-9">
																					<input type="text" class="form-control" id="txtCorreoInterprete" name="txtCorreoInterprete">
																				</div>
																			</div>
						                <div class="form-group" style="float: left; width: 100%;">
						                  <label class="col-sm-3 control-label">Asunto:</label>
						                  <div class="col-sm-9">
						                    <input type="text" class="form-control" id="txtAsuntoOmni" name="txtAsuntoOmni">
						                  </div>
						                </div>
						                <div class="form-group" style="float: left; width: 100%;">
						                  <label class="col-sm-3 control-label">Mensaje:</label>
						                  <div class="col-sm-9">
												<div class="btn-toolbar" data-role="editor-toolbar" data-target="#editorOmni">
													<div class="btn-group">
														<a class="btn btn-default dropdown-toggle" data-toggle="dropdown" title="Font Size"><i class="fa fa-text-height"></i>&nbsp;<b class="caret"></b></a>
														<ul class="dropdown-menu">
															<li><a data-edit="fontSize 5" class="fs-Five">Huge</a></li>
															<li><a data-edit="fontSize 3" class="fs-Three">Normal</a></li>
															<li><a data-edit="fontSize 1" class="fs-One">Small</a></li>
														</ul>
													</div>
													<div class="btn-group">
														<a class="btn btn-default" data-edit="bold" title="Bold (Ctrl/Cmd+B)"><i class="fa fa-bold"></i></a>
														<a class="btn btn-default" data-edit="italic" title="Italic (Ctrl/Cmd+I)"><i class="fa fa-italic"></i></a>
														<a class="btn btn-default" data-edit="strikethrough" title="Strikethrough"><i class="fa fa-strikethrough"></i></a>
														<a class="btn btn-default" data-edit="underline" title="Underline (Ctrl/Cmd+U)"><i class="fa fa-underline"></i></a>
													</div>
													<div class="btn-group">
														<a class="btn btn-default" data-edit="insertunorderedlist" title="Bullet list"><i class="fa fa-list-ul"></i></a>
														<a class="btn btn-default" data-edit="insertorderedlist" title="Number list"><i class="fa fa-list-ol"></i></a>
														<a class="btn btn-default" data-edit="outdent" title="Reduce indent (Shift+Tab)"><i class="fa fa-outdent"></i></a>
														<a class="btn btn-default" data-edit="indent" title="Indent (Tab)"><i class="fa fa-indent"></i></a>
													</div>
													<div class="btn-group">
														<a class="btn btn-default" data-edit="justifyleft" title="Align Left (Ctrl/Cmd+L)"><i class="fa fa-align-left"></i></a>
														<a class="btn btn-default" data-edit="justifycenter" title="Center (Ctrl/Cmd+E)"><i class="fa fa-align-center"></i></a>
														<a class="btn btn-default" data-edit="justifyright" title="Align Right (Ctrl/Cmd+R)"><i class="fa fa-align-right"></i></a>
														<a class="btn btn-default" data-edit="justifyfull" title="Justify (Ctrl/Cmd+J)"><i class="fa fa-align-justify"></i></a>
													</div>
													<div class="btn-group">
														<a class="btn btn-default dropdown-toggle" data-toggle="dropdown" title="Hyperlink"><i class="fa fa-link"></i></a>
														<div class="dropdown-menu input-append">
															<input placeholder="URL" type="text" data-edit="createLink" />
															<button class="btn" type="button">Add</button>
														</div>
													</div>
													<div class="btn-group">
														<a class="btn btn-default" data-edit="unlink" title="Remove Hyperlink"><i class="fa fa-unlink"></i></a>
														<span class="btn btn-default" title="Insert picture (or just drag & drop)" id="pictureBtn"> <i class="fa fa-picture-o"></i>
															<input class="imgUpload" type="file" data-role="magic-overlay" data-target="#pictureBtn" data-edit="insertImage" />
														</span>
													</div>
													<div class="btn-group">
														<a class="btn btn-default" data-edit="undo" title="Undo (Ctrl/Cmd+Z)"><i class="fa fa-undo"></i></a>
														<a class="btn btn-default" data-edit="redo" title="Redo (Ctrl/Cmd+Y)"><i class="fa fa-repeat"></i></a>
														<a class="btn btn-default" data-edit="html" title="Clear Formatting"><i class='glyphicon glyphicon-pencil'></i></a>
													</div>
												</div>
												<div id="editorOmni" style="overflow: auto; border: 1px solid rgb(180, 180, 180); height: 400px;" class="lead" data-placeholder="Editar memo de servicio."></div>
												<div id="editorPreview"></div>
											</div>
								          <div class="modal-footer">
                                             <button type="button" class="btn btn-default antoclose2" data-dismiss="modal">Cancelar</button>
                                             <button type="button" class="btn btn-primary antosubmit2" onclick="funcEnviarCorreoMemo()">Enviar</button>
                                          </div>

						                </div>
									</div>
								</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
          </div>
        </div>
      </div>
    </div>




    <div id="modalFacturas" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
      <div class="modal-dialog modal-lg" style="width: 1150px;">
        <div class="modal-content">

          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
            <h4 class="modal-title">Facturas</h4>
          </div>
          <div class="modal-body">

				      	<div id="divFacturas"></div>
          </div>
          <div class="modal-footer">
             <button type="button" class="btn btn-default antoclose2" data-dismiss="modal">Cerrar</button>
          </div>
        </div>
      </div>
    </div>
		<div id="modalFacturaPreview" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-lg" style="width: 1150px;">
				<div class="modal-content">

					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
						<h4 class="modal-title">Montos Factura</h4>
					</div>
					<div class="modal-body">

								<!-- <div id="divFacturaPreview"></div> -->
					</div>
					<div class="modal-footer">
						 <button type="button" class="btn btn-default antoclose2" data-dismiss="modal">Cerrar</button>
					</div>
				</div>
			</div>
		</div>
    <div id="CalenderModalEdit" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">

          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
            <h4 class="modal-title" id="myModalLabel2">Detalle del Evento</h4>
          </div>
          <div class="modal-body">

            <div id="testmodal2" style="padding: 5px 20px;">
              <form id="antoform2" class="form-horizontal calender" role="form">

                <div class="form-group">
                  <label class="col-sm-3 control-label">Nombre del Evento</label>
                  <div class="col-sm-9">
                    <input type="text" class="form-control" id="txtNombreEvento" name="txtNombreEvento">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-3 control-label">Cliente</label>
                  <div class="col-sm-9">

                    <%= SelectList.getListByQuery("listCliente", "select id_cliente, nombre from crm_cliente WHERE id_status = 1 order by nombre", "(Seleccionar)", "", "") %>
                  </div>
                </div>

                 <div class="form-group">
                  <label class="col-sm-3 control-label">Tipo Cotizaci&oacute;n:</label>
                  <div class="col-sm-9">

                    <%= SelectList.getListByQuery("listTipoEvento", "select id_tipo_eveto, nombre from cat_tipo_evento WHERE id_status = 1 order by nombre", "(Seleccionar)", "", "") %>
                  </div>
                </div>


                <div class="form-group">
                  <label class="col-sm-3 control-label"></label>
                  <div class="col-sm-9">

                    <%= SelectList.getListByQuery("listTipoCotizacion", "select id_tipo_cotizacion, nombre from cat_tipo_cotizacion", "(Seleccionar)", "", "") %>
                  </div>
                </div>



<!--                 <div class="form-group"> -->
<!--                   <label class="col-sm-3 control-label">Moneda:</label> -->
<!--                   <div class="col-sm-9"> -->
<%--                     <%= SelectList.getListByQuery("listMoneda", "select id_moneda, nombre from cat_moneda order by nombre", "(Seleccionar)", "", "") %> --%>
<!--                   </div> -->
<!--                 </div> -->

              </form>
            </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default antoclose2" data-dismiss="modal">Cancelar</button>
             <button type="button" class="btn btn-primary antosubmit2" onclick="confirmarCrearEvento()">Crear Evento</button>
          </div>
        </div>
      </div>
    </div>


				<div id="modalMemoFacturaDetalle" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				  <div class="modal-dialog modal-lg" style="width: 1150px;">
				    <div class="modal-content">

				      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
				        <h4 class="modal-title" id="myModalLabel2">Env&iacute;o a facturaci&oacute;n</h4>
				      </div>
				      <div class="modal-body">
				  <div class="" role="tabpanel" data-example-id="togglable-tabs">
				    <ul id="myTab" class="nav nav-tabs bar_tabs" role="tablist">
				      <li role="presentation" class="active"><a
				        href="#tab_content0EF" role="tab"
				        data-toggle="tab" aria-expanded="true">Captura</a></li>
				      <li role="presentation"><a
				        href="#tab_content1EF" role="tab"
				        data-toggle="tab" aria-expanded="true">Vista Previa</a></li>
				      <li role="presentation"><a
				        href="#tab_content2EF" role="tab"
				        data-toggle="tab" aria-expanded="true">Enviar</a></li>
				    </ul>
				    <div id="myTabContent" class="tab-content">
				      <div role="tabpanel" class="tab-pane fade active in" id="tab_content0EF" aria-labelledby="home-tab">
				        <div class="x_panel">

				          <div class="x_title">
				            <p>
				              <strong>Captura</strong>
				            </p>
				            <ul class="nav navbar-right panel_toolbox">
				            </ul>
				            <div class="clearfix"></div>
				          </div>

				          <div class="x_content">
										<div id="nFormAgenda2">
										</div>
										<input type="hidden"  id="auxIdEvento">

				            <div class="col-md-6 col-sm-6 col-xs-12">
											<h5>Por Factura</h5>
											<div id="divFacturaPreview"></div>
											<br>
											<hr>
											<br>

							
				              <div id="divCapturaFacturaDetalle">

				              </div>
				            </div>


				          </div>
				        </div>
				      </div>
				      <div role="tabpanel" class="tab-pane fade" id="tab_content1EF" aria-labelledby="home-tab">
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


				            <div class="col">
				              <div id="divVistaPreviaEF">

				              </div>
				            </div>


				          </div>
				        </div>
				      </div>
				      <div role="tabpanel" class="tab-pane fade" id="tab_content2EF" aria-labelledby="home-tab">
				        <div class="x_panel">

				          <div class="x_title">
				            <p>
				              <strong>Enviar</strong>
				            </p>
				            <ul class="nav navbar-right panel_toolbox">
				            </ul>
				            <div class="clearfix"></div>
				          </div>

				          <div class="x_content">

				            <div class="row">

				            <div class="col-md-12 col-sm-12 col-xs-12">
				              <p>
				                <strong>Env&iacute;o a Facturaci&oacute;n</strong>
				              </p>
				              <div id="divEnvioMemoEventoOmniEF">
				                <div class="form-group" style="float: left; width: 100%;">
				                          <label class="col-sm-3 control-label">Enviar a:</label>
				                          <div class="col-sm-9">
				                            <input type="text" class="form-control" id="txtCorreoOmniEF" name="txtCorreoOmniEF">
				                          </div>
				                        </div>
				                        <div class="form-group" style="float: left; width: 100%;">
				                          <label class="col-sm-3 control-label">Asunto:</label>
				                          <div class="col-sm-9">
				                            <input type="text" class="form-control" id="txtAsuntoOmniEF" name="txtAsuntoOmniEF">
				                          </div>
				                        </div>
				                        <div class="form-group" style="float: left; width: 100%;">
				                          <label class="col-sm-3 control-label">Mensaje:</label>
				                          <div class="col-sm-9">
				                    <div class="btn-toolbar" data-role="editor-toolbar" data-target="#editorOmniEF">
				                      <div class="btn-group">
				                        <a class="btn btn-default dropdown-toggle" data-toggle="dropdown" title="Font Size"><i class="fa fa-text-height"></i>&nbsp;<b class="caret"></b></a>
				                        <ul class="dropdown-menu">
				                          <li><a data-edit="fontSize 5" class="fs-Five">Huge</a></li>
				                          <li><a data-edit="fontSize 3" class="fs-Three">Normal</a></li>
				                          <li><a data-edit="fontSize 1" class="fs-One">Small</a></li>
				                        </ul>
				                      </div>
				                      <div class="btn-group">
				                        <a class="btn btn-default" data-edit="bold" title="Bold (Ctrl/Cmd+B)"><i class="fa fa-bold"></i></a>
				                        <a class="btn btn-default" data-edit="italic" title="Italic (Ctrl/Cmd+I)"><i class="fa fa-italic"></i></a>
				                        <a class="btn btn-default" data-edit="strikethrough" title="Strikethrough"><i class="fa fa-strikethrough"></i></a>
				                        <a class="btn btn-default" data-edit="underline" title="Underline (Ctrl/Cmd+U)"><i class="fa fa-underline"></i></a>
				                      </div>
				                      <div class="btn-group">
				                        <a class="btn btn-default" data-edit="insertunorderedlist" title="Bullet list"><i class="fa fa-list-ul"></i></a>
				                        <a class="btn btn-default" data-edit="insertorderedlist" title="Number list"><i class="fa fa-list-ol"></i></a>
				                        <a class="btn btn-default" data-edit="outdent" title="Reduce indent (Shift+Tab)"><i class="fa fa-outdent"></i></a>
				                        <a class="btn btn-default" data-edit="indent" title="Indent (Tab)"><i class="fa fa-indent"></i></a>
				                      </div>
				                      <div class="btn-group">
				                        <a class="btn btn-default" data-edit="justifyleft" title="Align Left (Ctrl/Cmd+L)"><i class="fa fa-align-left"></i></a>
				                        <a class="btn btn-default" data-edit="justifycenter" title="Center (Ctrl/Cmd+E)"><i class="fa fa-align-center"></i></a>
				                        <a class="btn btn-default" data-edit="justifyright" title="Align Right (Ctrl/Cmd+R)"><i class="fa fa-align-right"></i></a>
				                        <a class="btn btn-default" data-edit="justifyfull" title="Justify (Ctrl/Cmd+J)"><i class="fa fa-align-justify"></i></a>
				                      </div>
				                      <div class="btn-group">
				                        <a class="btn btn-default dropdown-toggle" data-toggle="dropdown" title="Hyperlink"><i class="fa fa-link"></i></a>
				                        <div class="dropdown-menu input-append">
				                          <input placeholder="URL" type="text" data-edit="createLink" />
				                          <button class="btn" type="button">Add</button>
				                        </div>
				                      </div>
				                      <div class="btn-group">
				                        <a class="btn btn-default" data-edit="unlink" title="Remove Hyperlink"><i class="fa fa-unlink"></i></a>
				                        <span class="btn btn-default" title="Insert picture (or just drag & drop)" id="pictureBtn"> <i class="fa fa-picture-o"></i>
				                          <input class="imgUpload" type="file" data-role="magic-overlay" data-target="#pictureBtn" data-edit="insertImage" />
				                        </span>
				                      </div>
				                      <div class="btn-group">
				                        <a class="btn btn-default" data-edit="undo" title="Undo (Ctrl/Cmd+Z)"><i class="fa fa-undo"></i></a>
				                        <a class="btn btn-default" data-edit="redo" title="Redo (Ctrl/Cmd+Y)"><i class="fa fa-repeat"></i></a>
				                        <a class="btn btn-default" data-edit="html" title="Clear Formatting"><i class='glyphicon glyphicon-pencil'></i></a>
				                      </div>
				                    </div>
				                    <div id="editorOmniEF" style="overflow: auto; border: 1px solid rgb(180, 180, 180); height: 400px;" class="lead" data-placeholder="Editar detalles de factura."></div>
				                    <div id="editorPreviewEF"></div>
				                  </div>
				                      <div class="modal-footer">
				                                         <button type="button" class="btn btn-default antoclose2" data-dismiss="modal">Cancelar</button>
				                                         <button type="button" class="btn btn-primary antosubmit2" onclick="funcEnviarCorreoFactura()">Enviar</button>
				                                      </div>

				                        </div>
				              </div>
				            </div>
				            </div>
				          </div>
				        </div>
				      </div>
				    </div>
				  </div>
				      </div>
				    </div>
				  </div>
				</div>



    <div id="fc_edit" data-toggle="modal" data-target="#CalenderModalEdit"></div>









    <!-- jQuery -->
    <script src="../home/vendors/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap -->
    <script src="../home/vendors/bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- FastClick -->
    <script src="../home/vendors/fastclick/lib/fastclick.js"></script>
    <!-- NProgress -->
    <script src="../home/vendors/nprogress/nprogress.js"></script>
    <!-- iCheck -->
    <script src="../home/vendors/iCheck/icheck.min.js"></script>
    <!-- Datatables -->
    <script src="../home/vendors/datatables.net/js/jquery.dataTables.custom.js"></script>
    <script src="../home/vendors/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
    <script src="../home/vendors/datatables.net-buttons/js/dataTables.buttons.min.js"></script>
    <script src="../home/vendors/datatables.net-buttons-bs/js/buttons.bootstrap.min.js"></script>
    <script src="../home/vendors/datatables.net-buttons/js/buttons.flash.min.js"></script>
    <script src="../home/vendors/datatables.net-buttons/js/buttons.html5.min.js"></script>
    <script src="../home/vendors/datatables.net-buttons/js/buttons.print.min.js"></script>
    <script src="../home/vendors/datatables.net-fixedheader/js/dataTables.fixedHeader.min.js"></script>
    <script src="../home/vendors/datatables.net-keytable/js/dataTables.keyTable.min.js"></script>
    <script src="../home/vendors/datatables.net-responsive/js/dataTables.responsive.min.js"></script>
    <script src="../home/vendors/datatables.net-responsive-bs/js/responsive.bootstrap.js"></script>
    <script src="../home/vendors/datatables.net-scroller/js/dataTables.scroller.min.js"></script>
    <script src="../home/vendors/jszip/dist/jszip.min.js"></script>
    <script src="../home/vendors/pdfmake/build/pdfmake.min.js"></script>
    <script src="../home/vendors/pdfmake/build/vfs_fonts.js"></script>

    <!-- Custom Theme Scripts -->
    <script src="../home/build/js/custom.min.js"></script>

<%@include file="adm_eventos_list_js.jsp" %>
	<script src="../home/vendors/sweet_alert/sweetalert.min.js"></script>
	<script src="../home/vendors/jquery.hotkeys/jquery.hotkeys.js"></script>
	<script src="../home/vendors/DateJS/prettify.js"></script>
	<script src="../home/vendors/bootstrap-wysiwyg/js/bootstrap-wysiwyg.min.js"></script>
<script src="../home/vendors/summernote/summernote.min.js"></script>
	<script type="text/javascript">

		<% if(message != null && !message.equals("")) { %>
		swal({title: "Mensaje:",text: "<%= message %>", type:"success",html:true});
		<% }%>

	</script>




    <!-- Datatables -->
    <script>

    $(document).ready(function() {
        var handleDataTableButtons = function() {
          if ($("#datatable-buttons").length) {
            $("#datatable-buttons").DataTable({
              dom: '<"top"Bflp>rt<"bottom"i>p<"clear">',  //Bfrtip
              buttons: [
            	  {
                      extend: "copy",
                      className: "btn-sm",
                      text : "Copiar"
                    },
                    {
                      extend: "csv",
                      className: "btn-sm",
                      text : "Descargar CSV"
                    },
                    {
                      extend: "excel",
                      className: "btn-sm",
                      text : "Descargar Excel"
                    },
                    {
                      extend: "pdfHtml5",
                      className: "btn-sm"
                    }
                    /*,
                    {
                      extend: "print",
                      className: "btn-sm"
                    },*/
              ],
              responsive: true
            });

          }
        };

        TableManageButtons = function() {
          "use strict";
          return {
            init: function() {
              handleDataTableButtons();
            }
          };
        }();

        $('#datatable').dataTable();

        $('#datatable-keytable').DataTable({
          keys: true
        });

        $('#datatable-responsive').DataTable();

        $('#datatable-scroller').DataTable({
          ajax: "js/datatables/json/scroller-demo.json",
          deferRender: true,
          scrollY: 380,
          scrollCollapse: true,
          scroller: true
        });

        $('#datatable-fixed-header').DataTable({
          fixedHeader: true
        });

        var $datatable = $('#datatable-checkbox');

        $datatable.dataTable({
          'order': [[ 1, 'asc' ]],
          'columnDefs': [
            { orderable: false, targets: [0] }
          ]
        });
        $datatable.on('draw.dt', function() {
          $('input').iCheck({
            checkboxClass: 'icheckbox_flat-green'
          });
        });

        TableManageButtons.init();

        $('#editorCliente').wysiwyg();
        $('#editorOmni').wysiwyg();
				$('#editorClienteEF').wysiwyg();
				$('#editorOmniEF').wysiwyg();

      });

      function nuevoEvento() {
    	  $("#txtNombreEvento").val('');
		  $("#listCliente").val('');
		  $("#listCliente option:selected").removeAttr("selected");
		  $("#listTipoEvento option:selected").removeAttr("selected");
		  $("#listTipoCotizacion option:selected").removeAttr("selected");
	    
    		 $('#fc_edit').click();
      };


      function confirmarCrearEvento() {

    	  	var nom = $("#txtNombreEvento").val();
			var cte = $("#listCliente").val();
			//var mon = $("#listMoneda").val();

			var tpo = $("#listTipoEvento").val();

			var tpo2 = $("#listTipoCotizacion").val();




      		if(nom.trim() == "") {

      			swal({title:"Se requiere el nombre",html:true});
      			return;
      		}


      		if(cte == "0") {

      			swal({title:"Se requiere el cliente",html:true});
      			return;
      		}


			if(tpo == "0" || tpo2 == "0") {

      			swal({title:"Se requiere el tipo de cotizaci&oacute;n",html:true});
      			return;
      		}




//       		if(mon == "0") {

//       			alert("Se requiere la moneda");
//       			return;
//       		}

    		 $.ajax({
    				type:'POST',
    				url :"/SAO/EventoInsertServlet",
    				data: {
    					nombre : $("#txtNombreEvento").val(),
    					id_cliente : $("#listCliente").val(),
    					id_tipo_evento : $("#listTipoEvento").val(),
    					id_tipo_cotizacion : $("#listTipoCotizacion").val()

//     					,
//     					id_moneda : $("#listMoneda").val()
    				},
    				success: function(data) {
    					window.location.href = "adm_evento_sedes.jsp?ID_EVENTO=" + data + "&ID_TIPO_EVENTO=" + tpo;

    				},
    				error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
    			});
      }

    </script>
    <!-- /Datatables -->
  </body>
</html>
<%
    } else {
    	response.sendRedirect("../home/login.jsp");
    }
}catch(Exception _e){
	System.out.println("Error"+_e);
	_e.printStackTrace();
}
%>

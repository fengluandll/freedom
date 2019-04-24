<%@page import="com.movemini.simpleajaxservlet.crm.CRMComentariosTable"%>
<%@page import="com.movemini.simpleajaxservlet.crm.CRMComentariosForm"%>
<%@page import="java.util.StringTokenizer"%>
<%
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
	response.setHeader("Pragma","no-cache"); //HTTP 1.0
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<%@page import="com.movemini.simpleforms.SimpleFormManager"%>
<%@page import="com.movemini.data.OneRecordFactory"%>
<%@page import="com.movemini.simpleajaxservlet.usuario.UsuarioForm"%>
<%@page import="com.movemini.simpleajaxservlet.viaticos.ViaticosAplicablesTable"%>
<%@page import="com.movemini.simpleajaxservlet.viaticos.ViaticosEventoTable"%>
<%@page import="com.movemini.simpleflexgrid.components.SelectList"%>
<%@page import="com.movemini.data.ProcedureCall"%>
<%@page import="com.movemini.simpleajaxservlet.evento_sala.SalasTable"%>
<%@page import="com.movemini.simpleajaxservlet.observaciones.ObservacionesAplicablesTable"%>
<%@page import="com.movemini.simpleajaxservlet.observaciones.ObservacionesEventoTable"%>
<%@page import="com.movemini.simpleajaxservlet.productos.ProductosAplicablesTable"%>
<%@page import="com.movemini.simpleajaxservlet.productos.ProductosEventoTable"%>
<%@page import="com.movemini.simpleajaxservlet.condiciones.CondicionesAplicablesTable"%>
<%@page import="com.movemini.simpleajaxservlet.condiciones.CondicionesEventoTable"%>
<%@page import="com.movemini.simpleajaxservlet.politicas.PoliticasAplicablesTable"%>
<%@page import="com.movemini.simpleajaxservlet.politicas.PoliticasEventoTable"%>
<%@page import="com.movemini.simpleajaxservlet.evento.EventoForm"%>
<%@page import="com.movemini.simpleajaxservlet.evento_area.EventoAreaTable"%>
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
	String aux = "-1";
 	String id = request.getParameter("id");


 	session.setAttribute("cliente_id", id);

 	//String entityCode = (String) session.getAttribute("entityCode");


 	String entityCode = "6"; // CLIENTES
	
 	if("0".equals(id)){
 		session.setAttribute(entityCode, "nuevo");
 	}
 	
 	if("nuevo".equals(session.getAttribute(entityCode))){
 		aux = "0";	
 	}

 	Record screenConfig =  OneRecordFactory.getFirstRecord("SELECT * FROM flex_entity WHERE id = " + entityCode);


 	Record screenConfigContactos =  OneRecordFactory.getFirstRecord("SELECT * FROM flex_entity WHERE id = 7");


 	Record screenConfigRazonesSociales =  OneRecordFactory.getFirstRecord("SELECT * FROM flex_entity WHERE id = 21");



 	/*
 	Record currentRow =  null;

 	if(!id.equals("0")) {

 		currentRow =  OneRecordFactory.getFirstRecord("SELECT * FROM " + screenConfig.get("table") + " WHERE " + screenConfig.get("column_id") + " = " + id);
 	}
 	*/



 	SimpleFormManager simpleFormManager = new SimpleFormManager(request, session);

 	if("0".equals(id)){
		response.sendRedirect("adm_entity_cliente_row.jsp?id="+simpleFormManager.getId());
	}else{
		id = simpleFormManager.getId();	
	}
 	session.setAttribute("crmcliente", id);


%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

<title><%= screenConfig.getString("title") %></title>





	<script type="text/javascript" src="js/util.js"></script>


    <!-- Bootstrap -->
    <link href="../home/vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="../home/vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="../home/vendors/nprogress/nprogress.css" rel="stylesheet">
    <!-- iCheck -->
    <link href="../home/vendors/iCheck/skins/flat/green.css" rel="stylesheet">
    <!-- Datatables -->
    <link href="../home/vendors/datatables.net-bs/css/dataTables.bootstrap.min.css" rel="stylesheet">
    <link href="../home/vendors/datatables.net-buttons-bs/css/buttons.bootstrap.min.css" rel="stylesheet">
    <link href="../home/vendors/datatables.net-fixedheader-bs/css/fixedHeader.bootstrap.min.css" rel="stylesheet">
    <link href="../home/vendors/datatables.net-responsive-bs/css/responsive.bootstrap.min.css" rel="stylesheet">
    <link href="../home/vendors/datatables.net-scroller-bs/css/scroller.bootstrap.min.css" rel="stylesheet">



	<!-- Switchery -->
    <link href="../home/vendors/switchery/dist/switchery.min.css" rel="stylesheet">



    <!-- Custom Theme Style -->
    <link href="../home/build/css/custom.css" rel="stylesheet">


    <%@include file="adm_entity_row-js.jsp" %>

    <%@include file="adm_entity_cliente_row-js.jsp" %>



    <link href="../home/vendors/sweet_alert/sweetalert.css" rel="stylesheet">




  </head>
  <body class="nav-md">



  	<input type="hidden" id="id" value="<%=id%>">


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
                    <div class="col-md-6 col-sm-6 col-xs-6">
                    <p><strong><%= screenConfig.get("title2") %> </strong></p>
                    </div>

                    <div class="col-md-6 col-sm-6 col-xs-6">
                    <ul class="nav navbar-right panel_toolbox">






                    	       <div align="right">
  								  <a href="adm_entity_list.jsp?ec=<%= entityCode %>" type="button" class="btn btn-info">
  								  	Regresar <%  if("0".equals(aux)) {%>(Guardar) <%} %>
  								  </a>
  								    <%  if("0".equals(aux)) {%>
  								  <button id="cancelar" type="button" class="btn btn-danger">
  								  	Cancelar
  								  </button>
  								  <%} %>
  								</div>
                    </ul>
                    </div>
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">




                     <%--
                      <button type="submit" name="EventoEdit" class="btn btn-primary" style="float: right;">Guardar Datos y Regresar</button> &zwnj;
                      <a href="adm_eventos_list.jsp" class="btn btn-warning" style="float: right;">Cancelar</a>
                      --%>

                    <!-- Tabs -->

                    <div class="" role="tabpanel" data-example-id="togglable-tabs">
                        <ul id="myTab" class="nav nav-tabs bar_tabs" role="tablist">
								<!--                           <li role="presentation" class="active"><a href="#tab_content0" id="home-tab" role="tab" data-toggle="tab" aria-expanded="true">General</a> -->
								<!--                           </li> -->
								<!--                           <li role="presentation" class=""><a href="#tab_content1" role="tab" id="profile-tab" data-toggle="tab" aria-expanded="true">Cotizaciï¿½n</a> -->
								<!--                           </li> -->


                          <%


                          String selectedTab = (String) session.getAttribute("selectedTab");
                          //"Contactos"
                          String contactosTabClass = "";
                          String razonesSocialesTabClass = "";

                          	for(int i = 1; i <= simpleFormManager.getMaxTabId(); i++) {

                          		String active = (i==1) ? "active" : "";
                          		String tabId = (i==1) ? "home-tab" : "profile-tab";




                          		if("Contactos".equals(selectedTab)) {

                          			active = "";

                          			contactosTabClass = "active";
                          		}


                          		if("RazonesSociales".equals(selectedTab)) {

                          			active = "";

                          			razonesSocialesTabClass = "active";
                          		}






                          %>

                          <li role="presentation" class="<%=active%>">
                          		<a href="#tab_content<%= i %>" id="<%=tabId %>" role="tab" data-toggle="tab" aria-expanded="true">
                          			<%= simpleFormManager.getTabHeaders().get(i-1) %>
                          		</a>
                          </li>


                          <%
                          	}
                          	%>





                          <li role="presentation" class="<%=razonesSocialesTabClass%>">
                          		<a href="#tab_contentRazonesSociales" id="profile-tab" role="tab" data-toggle="tab" aria-expanded="true">
                          			Razones Sociales
                          		</a>
                          </li>



                          <li role="presentation" class="<%=contactosTabClass%>">
                          		<a href="#tab_contentContactos" id="profile-tab" role="tab" data-toggle="tab" aria-expanded="true">
                          			Contactos
                          		</a>
                          </li>



                          <li role="presentation" class="profile-tab">
                          		<a href="#tab_contentComentarios" id="profile-tab" role="tab" data-toggle="tab" aria-expanded="true">
                          			Historial de Seguimiento
                          		</a>
                          </li>




                        </ul>




                        <div id="myTabContent" class="tab-content">

                        	<%
                          	for(int i = 1; i <= simpleFormManager.getMaxTabId(); i++) {

                          		String active = (i==1) ? " active in" : "";


                          		if("Contactos".equals(selectedTab)) {

                          			active = "";

                          			contactosTabClass = " active in";
                          		}
                          		if("RazonesSociales".equals(selectedTab)) {

                          			active = "";

                          			razonesSocialesTabClass = "active in";
                          		}


                          %>


                        	<div role="tabpanel" class="tab-pane fade <%= active %>" id="tab_content<%= i %>" aria-labelledby="home-tab">




			                        	<div class="x_panel">

														<div class="x_title">
										                    <p><strong><%= simpleFormManager.getTabHeaders().get(i-1) %></strong></p>
										                    <ul class="nav navbar-right panel_toolbox">
										                    </ul>
										                    <div class="clearfix"></div>
										                  </div>

								                <div class="x_content">
													 <div class="col-md-10 col-sm-10 col-xs-12">

														<%= simpleFormManager.getFormHtml(i) %>
													  </div>

							  					</div>
					  					</div>


                        	</div>

                          <%
                          	}
                          %>



<!--  -->



































                       <div role="tabpanel" class="tab-pane fade <%=razonesSocialesTabClass %>" id="tab_contentRazonesSociales" aria-labelledby="home-tab">




			                        	<div class="x_panel">

														<div class="x_title">
										                    <p><strong>Razones Sociales del Cliente</strong></p>
										                    <ul class="nav navbar-right panel_toolbox">
										                    </ul>
										                    <div class="clearfix"></div>
										                  </div>

								                <div class="x_content">
													 <div class="col-md-12 col-sm-12 col-xs-12">













































								<div align="right">
									<p class="text-muted font-13 m-b-30">
										<a href="adm_entity_razon_social_new.jsp?id=0" type="button" class="btn btn-info"><%= screenConfigRazonesSociales.get("new_btn_text") %></a>
									</p>
									<br>
								</div>

								<%
									String view = screenConfigRazonesSociales.get("list_table_view") + " WHERE id_cliente = " + id;

									String table = screenConfigRazonesSociales.get("table");
									String column_headers = screenConfigRazonesSociales.get("column_headers");
									String column_codes = screenConfigRazonesSociales.get("column_codes");


									StringTokenizer column_headersTok = new StringTokenizer(column_headers, "|");



								%>

								<div class="table-responsive">
								<table  class=" datatable-buttons table table-striped table-bordered" width="100%">
									<thead>
										<tr>
											<%
												while(column_headersTok.hasMoreTokens()) {
											%>
											<th><%= column_headersTok.nextToken() %></th>
											<%
												}
											%>
											<th>Administrar</th>
										</tr>
									</thead>

									<tbody>
										<%

											ArrayList<Record> rowRecord = DataArray.getArrayList("SELECT * FROM " + view + " AND id_status = 1");

											for (Record record : rowRecord) {

												StringTokenizer column_codesTok = new StringTokenizer(column_codes, "|");

										%>
										<tr>
											<%
												while(column_codesTok.hasMoreTokens()) {
											%>
												<td><%= record.get(column_codesTok.nextToken()) %></td>
											<%
												}
											%>


											<td align="center">

												<a href="adm_entity_razon_social_edit.jsp?id=<%= record.getId() %>"  class="btn btn-info">
														<i class="fa fa-pencil">
														</i>
														<%= screenConfigRazonesSociales.get("edit_btn_text") %>
												</a>
												<button onclick="eliminarRazon(<%= record.getId() %>)"  class="btn btn-danger">
														<i class="fa fa-pencil">
														</i>
														<%= screenConfigRazonesSociales.get("delete_btn_text") %>
												</button>
												


<%-- 												<a href="adm_entity_list.jsp?ec=<%= entityCode %>&idDeleteRow=<%= record.getId() %>"  --%>
<!-- 													onclick="return confirm('Estï¿½ seguro?')" -->
<!-- 												  class="btn btn-danger"> -->
<!-- 														<i class="fa fa-close"> -->
<!-- 														</i>  -->
<%-- 														<%= screenConfigContactos.get("delete_btn_text") %> --%>
<!-- 												</a> -->


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


















































<!--  -->

                          	<div role="tabpanel" class="tab-pane fade<%=contactosTabClass %>" id="tab_contentContactos" aria-labelledby="home-tab">




			                        	<div class="x_panel">

														<div class="x_title">
										                    <p><strong>Contactos del Cliente</strong></p>
										                    <ul class="nav navbar-right panel_toolbox">
										                    </ul>
										                    <div class="clearfix"></div>
										                  </div>

								                <div class="x_content">
													 <div class="col-md-12 col-sm-12 col-xs-12">













































								<div align="right">
									<p class="text-muted font-13 m-b-30">
										<a href="adm_entity_contacto_new.jsp?id=0" type="button" class="btn btn-info"><%= screenConfigContactos.get("new_btn_text") %></a>
									</p>
									<br>
								</div>

								<%
									String view2 = screenConfigContactos.get("list_table_view") + " WHERE id_cliente = " + id;

									//String table = screenConfigContactos.get("table");
									String column_headers2 = screenConfigContactos.get("column_headers");
									String column_codes2 = screenConfigContactos.get("column_codes");


									StringTokenizer column_headersTok2 = new StringTokenizer(column_headers2, "|");



								%>

								<div class="table-responsive">
								<table class="datatable-buttons table table-striped table-bordered" width="100%">
									<thead>
										<tr>
											<%
												while(column_headersTok2.hasMoreTokens()) {
											%>
											<th><%= column_headersTok2.nextToken() %></th>
											<%
												}
											%>
											<th>Administrar</th>
										</tr>
									</thead>

									<tbody>
										<%

											ArrayList<Record> rowRecord2 = DataArray.getArrayList("SELECT * FROM " + view2 + " AND id_status = 1");

											for (Record record : rowRecord2) {

												StringTokenizer column_codesTok2 = new StringTokenizer(column_codes2, "|");

										%>
										<tr>
											<%
												while(column_codesTok2.hasMoreTokens()) {
											%>
												<td><%= record.get(column_codesTok2.nextToken()) %></td>
											<%
												}
											%>


											<td align="center">

												<a href="adm_entity_contacto_edit.jsp?id=<%= record.getId() %>"  class="btn btn-info">
														<i class="fa fa-pencil">
														</i>
														<%= screenConfigContactos.get("edit_btn_text") %>
												</a>
												<button onclick="eliminarContacto(<%= record.getId() %>)"  class="btn btn-danger">
														<i class="fa fa-pencil">
														</i>
														<%= screenConfigRazonesSociales.get("delete_btn_text") %>
												</button>


<%-- 												<a href="adm_entity_list.jsp?ec=<%= entityCode %>&idDeleteRow=<%= record.getId() %>"  --%>
<!-- 													onclick="return confirm('Estï¿½ seguro?')" -->
<!-- 												  class="btn btn-danger"> -->
<!-- 														<i class="fa fa-close"> -->
<!-- 														</i>  -->
<%-- 														<%= screenConfigContactos.get("delete_btn_text") %> --%>
<!-- 												</a> -->


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






                          <div role="tabpanel" class="tab-pane fade" id="tab_contentComentarios" aria-labelledby="home-tab">

			                        	<div class="x_panel">

														<div class="x_title">
										                    <p><strong>Historial de Seguimiento</strong></p>
										                    <ul class="nav navbar-right panel_toolbox">
										                    </ul>
										                    <div class="clearfix"></div>
										                  </div>

								                <div class="x_content">
													 <div class="col-md-12 col-sm-12 col-xs-12">


													 	<%= (new CRMComentariosForm(id)).getFormHtml() %>


													 <div align="right">
													 <a onclick="insertNewComment()"  class="btn btn-info">
														<i class="fa fa-pencil">
														</i>
														Agregar Comentario
													</a>
													 </div>

													 </div>


													  <div class="col-md-12 col-sm-12 col-xs-12">


													  		<div id="divTableComentariosCRM">
													  			<%= (new CRMComentariosTable(id)).getHtml() %>
													  		</div>



													  </div>




												</div>



										</div>


							</div>











<!--                           <div role="tabpanel" class="tab-pane fade" id="tab_content1" aria-labelledby="home-tab"> </div> -->











                        </div>
                    </div>

                    <!-- Tabs End -->





                    <%


                    session.setAttribute("selectedTab","");
                    %>





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







  	<!-- jQuery -->
    <script src="../home/vendors/jquery/dist/jquery.min.js"></script>





    <script type="text/javascript">


		//selectSalas();



	</script>













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

	<!-- Switchery -->
    <script src="../home/vendors/switchery/dist/switchery.min.js"></script>

    <!-- Custom Theme Scripts -->
    <script src="../home/build/js/custom.min.js"></script>


    <script src="../home/vendors/sweet_alert/sweetalert.min.js"></script>




    <!-- Datatables -->
    <script>
    $("#cancelar").on("click",function(){
		  	 swal({   
		   		    title: "¿Desea cancelar?",
		   		    text: "Los datos capturados se perderán",
		   		    type: "warning",
		   		    showCancelButton: true,
		   		    confirmButtonColor: '#DD6B55',
		   		    confirmButtonText: 'Sí, continuar',
		   		    cancelButtonText: "No",
		   		    closeOnConfirm: true,
		   		    closeOnCancel: true
		   		 },
		   		 function(isConfirm){

		   		   if (isConfirm){
		   				window.location= 'adm_entity_list.jsp?ec=<%=entityCode + "&idDeleteRow_="+ id %>';
		   		    }
		   		 });  
	  });
			function deleteComentario(idComentario){

				 swal({
					    title: "¿Eliminar el comentario?",
					    text: "El comentario se eliminara",
					    type: "warning",
					    showCancelButton: true,
					    confirmButtonColor: '#DD6B55',
					    confirmButtonText: 'Sí, continuar',
					    cancelButtonText: "No, cancelar",
					    closeOnConfirm: true,
					    closeOnCancel: true
					 },
					 function(isConfirm){

					   if (isConfirm){
							 $.ajax({
								 url: "/SAO/CRMComentariosDelete",
								 type: "post",
								 data: { key: idComentario },
								 success: function(data){
									 if(data != "OK"){
										 swal("Error: No se elimino el comentario");
									 }else{
										 swal("Comentario eliminado");
										 reloadTablaComentarios();
									 }
								 }

							 });

					    }
					 });
			}
      $(document).ready(function() {
        var handleDataTableButtons = function() {
          if ($(".datatable-buttons").length) {
            $(".datatable-buttons").DataTable({
            	dom: '<"top"Bflp>rt<"bottom"i>p<"clear">', //"Bfrtip",
              buttons: [
                {
                  extend: "copy",
                  className: "btn-sm"
                },
                {
                  extend: "csv",
                  className: "btn-sm"
                },
                {
                  extend: "excel",
                  className: "btn-sm"
                },
                {
                  extend: "pdfHtml5",
                  className: "btn-sm"
                },
                {
                  extend: "print",
                  className: "btn-sm"
                },
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
      });
    </script>
    <!-- /Datatables -->


  </body>
</html>
<%

}catch(Exception _e){
	System.out.println("Error"+_e);
	_e.printStackTrace();
	//response.sendRedirect("adm_eventos_list.jsp");
}
%>

<%
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
	response.setHeader("Pragma","no-cache"); //HTTP 1.0
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<%@page import="com.movemini.layers.controller.agenda.Agenda"%>
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

 	Record eventoStatusRecord = OneRecordFactory.getFirstRecord("SELECT * FROM cat_evento_status WHERE id_evento_status = " + id_evento_status);


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

    <link rel="stylesheet" type="text/css" media="screen" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/themes/base/jquery-ui.css">
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
    <link href="../home/css/datepicker.css" rel="stylesheet">


<!-- FullCalendar -->
    <link href="vendors/fullcalendar/dist/fullcalendar.css" rel="stylesheet">
    <link href="vendors/fullcalendar/dist/fullcalendar.print.css" rel="stylesheet" media="print">



    <!-- Custom Theme Style -->
    <link href="../home/build/css/custom.css" rel="stylesheet">

    <style type="text/css">

    .ui-datepicker-calendar {
    display: none;
    }
		.fc-time{ display: none; }
    </style>
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
                    <p><strong>AGENDA</strong> </p>
                    <ul class="nav navbar-right panel_toolbox">
                    </ul>
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                    <p class="text-muted font-13 m-b-30">
                    </p>

                    <div align="right">
                  	<div class="row">
                  		<div class="col-md-3">
	                  		<div class="row">
	                  			<div class="col-md-2">
	                  				<label>Cliente:</label>
	                  			</div>
	                  			<div class="col-md-9">
	                  				<div id="divComboCliente"></div>
	                  			</div>
	                  		</div>
	                  	</div>
	                  	<div class="col-md-3">
	                  		<div class="row">
	                  			<div class="col-md-2">
	                  				<label>Evento:</label>
	                  			</div>
	                  			<div class="col-md-9">
	                  				<div id="divComboNombreEvento"></div>
	                  			</div>
	                  		</div>
	                  	</div>
	                  	<div class="col-md-3">
							<div class="row">
								<div class="col-md-2">
	                  				<label>Estatus:</label>
	                  			</div>
	                  			<div class="col-md-9">
	                  				<div id="divComboEstatus" class="col"></div>
	                  			</div>
	                  		</div>
	                  	</div>
	                  	<div class="col-sm-2" style="display: none">
	                  		<div class="row">
	                  			<div class="col-md-3">
	                  				<label for="startDate">Fecha:</label>
	                  			</div>
	                  			<div class="col-md-9">
	    				 			<input name="startDate" id="startDate" class="date-picker" />
	    				 		</div>
	    					</div>
	                  	</div>

	                  	<div class="col-sm-1">
	                  		<input type="button" value="Buscar" onclick="funcObtenerAgenda();">
	                  	</div>
                    </div>
                    </div>


<div id='calendar'></div>





    <!-- calendar modal -->
    <div id="CalenderModalNew" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">

          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">�</button>
            <h4 class="modal-title" id="myModalLabel">New Calendar Entry</h4>
          </div>
          <div class="modal-body">
            <div id="testmodal" style="padding: 5px 20px;">
              <form id="antoform" class="form-horizontal calender" role="form">
                <div class="form-group">
                  <label class="col-sm-3 control-label">Title</label>
                  <div class="col-sm-9">
                    <input type="text" class="form-control" id="title" name="title">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-3 control-label">Description</label>
                  <div class="col-sm-9">
                    <textarea class="form-control" style="height:55px;" id="descr" name="descr"></textarea>
                  </div>
                </div>
              </form>
            </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default antoclose" data-dismiss="modal">Close</button>
            <button type="button" class="btn btn-primary antosubmit">Save changes</button>
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
								<div id="nFormAgenda1">
                <div class="form-group">
                  <label class="col-sm-3 control-label">Evento</label>
                  <div class="col-sm-9">
                    <input type="text" class="form-control" id="title2" name="title2" disabled="disabled">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-3 control-label">Cliente</label>
                  <div class="col-sm-9">
                    <input type="text" class="form-control" id="cliente" name="cliente" disabled="disabled">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-3 control-label">Fecha</label>
                  <div class="col-sm-9">
                    <input type="text" class="form-control" id="fecha" name="fecha" disabled="disabled">
                  </div>
                </div>

<!--                 <div class="form-group"> -->
<!--                   <label class="col-sm-3 control-label">Sala</label> -->
<!--                   <div class="col-sm-9"> -->
<!--                     <input type="text" class="form-control" id="sala" name="sala" disabled="disabled"> -->
<!--                   </div> -->
<!--                 </div> -->
<!--                 <div class="form-group"> -->
<!--                   <label class="col-sm-3 control-label">Interprete</label> -->
<!--                   <div class="col-sm-9"> -->
<!--                     <input type="text" class="form-control" id="interprete" name="interprete" disabled="disabled"> -->
<!--                   </div> -->
<!--                 </div> -->
					</div>
								<div id="nFormAgenda2">
								</div>

                <div class="form-group">

                  <label class="col-md-12 control-label">
                  	<center><div id="divInterpretesList"></div></center>
                  	<h5 style="text-align:left"><b>Facturas:</b> </h5>
                  	<center><div id="divFacturasList"></div></center>
                  </label>
                </div>



<!--                 <div class="form-group"> -->
<!--                   <label class="col-sm-3 control-label">Title</label> -->
<!--                   <div class="col-sm-9"> -->
<!--                     <input type="text" class="form-control" id="title2" name="title2"> -->
<!--                   </div> -->
<!--                 </div> -->
<!--                 <div class="form-group"> -->
<!--                   <label class="col-sm-3 control-label">Description</label> -->
<!--                   <div class="col-sm-9"> -->
<!--                     <textarea class="form-control" style="height:55px;" id="descr2" name="descr"></textarea> -->
<!--                   </div> -->
<!--                 </div> -->

              </form>
            </div>
          </div>
          <div class="modal-footer">


          	<form action="adm_evento_sedes.jsp" id="formXYZ">

          	<input type="hidden" id="ID_EVENTO" name="ID_EVENTO">

          	<button type="button" class="btn btn-default" onclick="formXYZ.submit()">Administrar Evento</button>
          	<button type="button" class="btn btn-default" onclick="formXYZ.action='reporte_cotizacion_pdf.jsp';formXYZ.submit()">Ver PDF de Cotizaci&oacute;n</button>
          	<button type="button" class="btn btn-default" id="btnMemoEvento" onclick="formXYZ.action='reporte_memo_servicio_pdf.jsp';formXYZ.submit()">Ver Memo de Servicio</button>
          	</form>





            <button type="button" class="btn btn-default antoclose2" data-dismiss="modal">Cerrar</button>
<!--             <button type="button" class="btn btn-primary antosubmit2">Save changes</button> -->


          </div>
        </div>
      </div>
    </div>




    <div id="fc_create" data-toggle="modal" data-target="#CalenderModalNew"></div>
    <div id="fc_edit" data-toggle="modal" data-target="#CalenderModalEdit"></div>






               <script type="text/javascript">





               </script>
























                  </div>
                </div>
              </div>


     </div>
    </div>


               <footer>
          <div class="pull-right">

          </div>
          <div class="clearfix"></div>
        </footer>
        <!-- /footer content -->
      </div>
    </div>


    <%@include file="agenda_js.jsp" %>

    <!-- jQuery -->
    <script src="../home/vendors/jquery/dist/jquery.min.js"></script>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/jquery-ui.min.js"></script>

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
	<script src="../home/js/bootstrap-datepicker.js"></script>
<!-- FullCalendar -->
    <script src="vendors/moment/min/moment.min.js"></script>
    <script src="vendors/fullcalendar/dist/fullcalendar.js"></script>


    <!-- Custom Theme Scripts -->
    <script src="../home/build/js/custom.min.js"></script>

    <!-- Datatables -->
    <script>
      $(document).ready(function() {
        var handleDataTableButtons = function() {
          if ($("#datatable-buttons").length) {
            $("#datatable-buttons").DataTable({
              dom: "Bfrtip",
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
      });














      $(window).load(function() {

    	  debugger;

		funcObternerEstatusEvento();
		funcObternerComboEventoNombre();
		funcObternerComboCliente();

    	  $('#startDate').datepicker( {
    		  format: "mm-yyyy",
    		    viewMode: "months",
    		    minViewMode: "months"
    	    });


        var date = new Date(),
            d = date.getDate(),
            m = date.getMonth(),
            y = date.getFullYear(),
            started,
            categoryClass;

        var argEventos = [ <%= Agenda.getEvents() %> ];

        funcInicializaAgenda(argEventos, null);
      });
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

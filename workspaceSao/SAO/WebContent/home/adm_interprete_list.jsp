<%
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
	response.setHeader("Pragma","no-cache"); //HTTP 1.0
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
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


 	//String id_evento_status = (String) session.getAttribute("v");
 	//String usuarioConsultaCode = (String) session.getAttribute("u");




 	//session.setAttribute("id_evento_status", id_evento_status);




    if(access
//     		session.getAttribute("ID_USER") != null
    		) {


    	String message = "";
    	if("nuevo".equals(session.getAttribute("interprete"))){
	 		session.removeAttribute("interprete");
	 	}
    	if( "1".equals(request.getParameter("m"))){
			message = "Se dió de baja el registro correctamente.";
    	}
    	if(request.getParameter("idDeleteRow") != null || request.getParameter("idDeleteRow_") != null) {
    		String xid ;
    		if(request.getParameter("idDeleteRow_") == null){
    			xid = request.getParameter("idDeleteRow");
    		}else{
    			xid = request.getParameter("idDeleteRow_");
    		}
    		//String xidStat = request.getParameter("idStatus");
    		                    		
    			ConnectionWrapper.staticExecuteUpdate(
    				"UPDATE cat_interprete SET id_status = '0' WHERE id_interprete = '" + xid + "'");
    		response.sendRedirect("adm_interprete_list.jsp?" + (request.getParameter("idDeleteRow_") == null?"m=1":""));
    	
    	}
    


     	ArrayList<Record> interpretes = DataArray.getArrayList("SELECT * FROM cat_interprete WHERE id_status = 1");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>SAO - Int&eacute;rpretes</title>

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
	<link href="../home/vendors/sweet_alert/sweetalert.css" rel="stylesheet">
    <!-- Custom Theme Style -->
    <link href="../home/build/css/custom.css" rel="stylesheet">
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
                    <p><strong>Int&eacute;rpretes</strong> </p>
                    <ul class="nav navbar-right panel_toolbox">
                    </ul>
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                    <p class="text-muted font-13 m-b-30">
                    </p>

                    <div align="right">
                  	<p class="text-muted font-13 m-b-30">
                      <a href="adm_interprete_row.jsp?id_interprete=0" type="button" class="btn btn-info">Nuevo Int&eacute;rprete</a>
                    </p>
                    <br>
                    </div>




                    <table id="datatable-buttons" class="table table-striped table-bordered">
                      <thead>
                        <tr>
                          <th>Nombre</th>
                          <th>Detalle</th>
                        </tr>
                      </thead>

                      <tbody>
                        <%


                   		for(Record interprete : interpretes){
//                   		while(rsUser.next()){
            		      %>
            		      <tr>
            		      	  <td><%= interprete.getString("nombre") %> <%= interprete.getString("apellido") %></td>
            		          <td align="center">
            		          	<a href="adm_interprete_row.jsp?id_interprete=<%=interprete.getString("id_interprete")%>" class="btn btn-info"><i class="fa fa-pencil"> Ver Detalle</i></a>

            		          	<button			onclick="eliminarInterprete(<%=interprete.getString("id_interprete")%>)"		  class="btn btn-danger">
														<i class="fa fa-close">
														</i>
														Eliminar
												</button>



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
          </div>
          <div class="clearfix"></div>
        </footer>
        <!-- /footer content -->
      </div>
    </div>

















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
<script src="../home/vendors/sweet_alert/sweetalert.min.js"></script>   
    <!-- Datatables -->
    <script>
	    function eliminarInterprete(idInterprete){
	    	swal({   
		   		    title: "¿Eliminar Interprete?",
		   		    text: "La información se eliminará",
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
		   			window.location = 'adm_interprete_list.jsp?idDeleteRow='+idInterprete;
		   		    }
		   		 });  
	    	
	    }
      $(document).ready(function() {
        var handleDataTableButtons = function() {
          if ($("#datatable-buttons").length) {
            $("#datatable-buttons").DataTable({
              dom: '<"top"Bflp>rt<"bottom"i>p<"clear">', //"Bfrtip",
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




      function nuevoEvento() {

    		 $('#fc_edit').click();
    	 }


      function confirmarCrearEvento() {




    		 $.ajax({
    				type:'POST',
    				url :"/SAO/EventoInsertServlet",
    				data: {
    					nombre : $("#txtNombreEvento").val(),
    					id_cliente : $("#listCliente").val()
    				},
    				success: function(data) {


    					//alert(data);


    					window.location.href = "adm_evento_sedes.jsp?id=" + data;




    					//selectPoliticasEvento();
    					//selectPoliticasAplicables();

    					//$('#divFechas').html(data);

    					/*$("#divOutput").empty();
    					$("#divOutput").append(data);

    					$("#divOutput").hide();
    					$("#divOutput").toggle(200);*/




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

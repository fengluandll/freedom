<%@page import="com.movemini.catalogos.ManejadorCatalogos"%>
<%@page import="com.movemini.simpleajaxservlet.interprete.IdiomasAplicablesTable"%>
<%@page import="com.movemini.simpleajaxservlet.interprete.IdiomasInterpreteTable"%>
<%
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
	response.setHeader("Pragma","no-cache"); //HTTP 1.0
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<%@page import="com.movemini.simpleajaxservlet.interprete.EspecialidadesAplicablesTable"%>
<%@page import="com.movemini.simpleajaxservlet.interprete.EspecialidadesInterpreteTable"%>
<%@page import="com.movemini.simpleajaxservlet.interprete.InterpreteForm"%>
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

<%@ page import="com.movemini.producto.ProductoTEForm" %> 
<%
try
{
	String aux = "-1";
	String id = request.getParameter("id_producto");
	if("0".equals(id)){
 		session.setAttribute("productote", "nuevo");
 	}
 	
 	if("nuevo".equals(session.getAttribute("productote"))){
 		aux = "0";	
 	}
 	
	ProductoTEForm productoForm = new ProductoTEForm(id);
 	String idProducto =  "";
 	 	
 	
 	if("0".equals(id)){
		response.sendRedirect("adm_producto_te_row.jsp?id_producto="+ productoForm.getIdProductoTE());
	}else{
		idProducto = productoForm.getIdProductoTE();	
	}
 	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>SAO - Producto Traduccion Escrita</title>

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
    
   	<link href="../home/vendors/sweet_alert/sweetalert.css" rel="stylesheet">
    
    
    
    
    
    
  </head>
  <body class="nav-md">
  
  
  	<input type="hidden" id="id_producto" value="<%= idProducto %>">
  	
  
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
                    <p><strong>Datos del Producto </strong></p>
                    <ul class="nav navbar-right panel_toolbox">                                           
                    </ul>
                    <div class="clearfix"></div>
                    
                    <div align="right">
  								  <a href="adm_entity_producto_te.jsp" type="button" class="btn btn-info">
  								  	Regresar  <%  if("0".equals(aux)) {%>(Guardar) <%} %>
  								  </a>
  								    <%  if("0".equals(aux)) {%>
  								  <button id="cancelar" type="button" class="btn btn-danger">
  								  	Cancelar
  								  </button>
  								  <%} %>
  								</div>
  								
                  </div>
                  <div class="x_content">
                 
                    <!-- Tabs -->
                    			
                    <div class="" role="tabpanel" data-example-id="togglable-tabs">
                        <ul id="myTab" class="nav nav-tabs bar_tabs" role="tablist">
                          <li role="presentation" class="active"><a href="#tab_content0" id="home-tab" role="tab" data-toggle="tab" aria-expanded="true">Datos</a>
                          </li>
                        </ul>
                        <div id="myTabContent" class="tab-content">
                        	<div role="tabpanel" class="tab-pane fade active in" id="tab_content0" aria-labelledby="home-tab">
                        		<div class="x_panel">
									<div class="x_title">
					                    <p><strong>Datos </strong></p>
					                    <ul class="nav navbar-right panel_toolbox">                                           
					                    </ul>
					                    <div class="clearfix"></div>
					                 </div>
									<div class="x_content">
							            <div class="col-md-10 col-sm-10 col-xs-12">
								  			<%= productoForm.getFormHtml(idProducto) %>
								 		</div>
						  			</div>
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
    <script src="../home/vendors/datatables.net/js/jquery.dataTables.min.js"></script>
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
	<script src="../home/vendors/sweet_alert/sweetalert.min.js"></script>    

    <!-- Custom Theme Scripts -->
    <script src="../home/build/js/custom.min.js"></script>

	<%@include file="adm_producto_te_row_js.jsp" %>
    <!-- Datatables -->
    <script>
      $(document).ready(function() {
    	  $("#cancelar").on("click",function(){
    		  	 swal({   
    		   		    title: "¿Cancelar guardado?",
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
    		   				window.location= 'adm_entity_producto_te.jsp<%= "?idDeleteRow_="+ id %>';
    		   		    }
    		   		 });  
     	  });
        var handleDataTableButtons = function() {
          if ($("#datatable-buttons").length) {
            $("#datatable-buttons").DataTable({
              dom: "Bfrtip",
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
//     } else {
//     	response.sendRedirect("adm_eventos_list.jsp");
//     	System.out.println("Khe?");	
//     }
}catch(Exception _e){
	System.out.println("Error"+_e);
	_e.printStackTrace();
	//response.sendRedirect("adm_eventos_list.jsp");
	
}
%>
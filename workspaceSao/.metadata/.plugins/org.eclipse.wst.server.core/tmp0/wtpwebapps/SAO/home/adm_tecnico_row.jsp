<%@page import="com.movemini.tecnico.TecnicoTable"%>
<%
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
	response.setHeader("Pragma","no-cache"); //HTTP 1.0
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="org.apache.tomcat.util.codec.binary.Base64"%>
<%@ page import="java.sql.*" %> 
<%@ page import="java.io.*" %> 
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@page import="com.movemini.tecnico.ManejadorTecnico"%>
<%
try
{
	
 	String id_tecnico = request.getParameter("id_tecnico").length() > 0 || request.getParameter("id_tecnico") != null ? request.getParameter("id_tecnico") : "0";
	String aux = "-1" ;
 	
 	if("0".equals(id_tecnico)){
 		session.setAttribute("tecnico", "nuevo");
 	}
 	
 	if("nuevo".equals(session.getAttribute("tecnico"))){
 		aux = "0";	
 	}
 	
 	
 	ManejadorTecnico mnjTecnico = new ManejadorTecnico(); 
 	mnjTecnico.crearTecnico(Integer.parseInt(id_tecnico));
 	
	if("0".equals(id_tecnico)){
		response.sendRedirect("adm_tecnico_row.jsp?id_tecnico="+String.valueOf(mnjTecnico.getIdTecnico()));
	}else{
		id_tecnico = String.valueOf(mnjTecnico.getIdTecnico());	
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

    <title>SAO - T&eacute;cnico</title>

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
    
    	<link href="../home/vendors/sweet_alert/sweetalert.css" rel="stylesheet">
    
    
    <!-- Custom Theme Style -->
    <link href="../home/build/css/custom.css" rel="stylesheet">
    
    
    
    
    
    
    
  </head>
  <body class="nav-md">
  
  
  	<input type="hidden" id="id_tecnico" value="<%= id_tecnico %>">
  	
  
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
                    <p><strong>Datos del T&eacute;cnico </strong></p>
                    <ul class="nav navbar-right panel_toolbox">                                           
                    </ul>
                    <div class="clearfix"></div>
                    
                    <div align="right">
  								  <a href="adm_tecnico_list.jsp" type="button" class="btn btn-info">
  								  	Regresar  <%  if("0".equals(aux)) {%>(Guardar) <%} %>
  								  </a> <%  if("0".equals(aux)) {%>
  								  <button id="cancelar" type="button" class="btn btn-danger">
  								  	Cancelar
  								  </button>
  								  <%} %>
  								</div>
  								
                  </div>
                  <div class="x_content">
                 
                    
                    
                    
                     <%-- 
                      <button type="submit" name="EventoEdit" class="btn btn-primary" style="float: right;">Guardar Datos y Regresar</button> &zwnj;
                      <a href="adm_eventos_list.jsp" class="btn btn-warning" style="float: right;">Cancelar</a> 
                      --%>
                    
                    <!-- Tabs -->
                    			
                    <div class="" role="tabpanel" data-example-id="togglable-tabs">
                        <ul id="myTab" class="nav nav-tabs bar_tabs" role="tablist">
                          <li role="presentation" class="active"><a href="#tab_content0" id="home-tab" role="tab" data-toggle="tab" aria-expanded="true">Datos</a>
                          </li>
                          <li role="presentation" class=""><a href="#tab_contentX1" id="profile-tab" role="tab" data-toggle="tab" aria-expanded="true">Cuenta Nomina</a>
                          </li>
                          
                          <li role="presentation" class=""><a href="#tab_contentX2" id="profile-tab" role="tab" data-toggle="tab" aria-expanded="true">Cuenta Viaticos</a>
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
						                
						                
								 <div class="col-md-6 col-sm-6 col-xs-12">
								  
															                  
									<%= mnjTecnico.crearTablaTecnico(Integer.parseInt(id_tecnico)) %>
								  </div>
					  
					  
					  </div>
					  </div>
                        	</div>
                        	
                        	<div role="tabpanel" class="tab-pane fade" id="tab_contentX1" aria-labelledby="profile-tab">
                        		<div class="x_panel">
										<div class="x_title">
										                    <p><strong>Cuenta Nomina </strong></p>
										                    <ul class="nav navbar-right panel_toolbox">                                           
										                    </ul>
										                    <div class="clearfix"></div>
										                  </div>
						                <div class="x_content">
										 <div class="col-md-6 col-sm-6 col-xs-12">
										 <%= mnjTecnico.crearTablaNominaTecnico(Integer.parseInt(id_tecnico)) %>
<%-- 											<%= %> --%>
										  </div>
							  			</div>
					  			</div>
                        	</div>
                        	
                        	<div role="tabpanel" class="tab-pane fade" id="tab_contentX2" aria-labelledby="profile-tab">
                        		<div class="x_panel">
										<div class="x_title">
										                    <p><strong>Cuenta Viaticos </strong></p>
										                    <ul class="nav navbar-right panel_toolbox">                                           
										                    </ul>
										                    <div class="clearfix"></div>
										                  </div>
						                <div class="x_content">
										 <div class="col-md-6 col-sm-6 col-xs-12">
										 <%= mnjTecnico.crearTablaViaticosTecnico(Integer.parseInt(id_tecnico)) %>
<%-- 											<%= %> --%>
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
    
    
    
    
    
    <script type="text/javascript">
	
		//alert("ok");
		
		
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

    <!-- Custom Theme Scripts -->
    <script src="../home/build/js/custom.min.js"></script>
	<script src="../home/vendors/sweet_alert/sweetalert.min.js"></script>    

	<%@include file="adm_tecnico_row_js.jsp" %>
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
    		   				window.location= 'adm_tecnico_list.jsp<%= "?idDeleteRow_="+ id_tecnico %>';
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
}catch(Exception _e){
	System.out.println("Error"+_e);
	_e.printStackTrace();
}
%>
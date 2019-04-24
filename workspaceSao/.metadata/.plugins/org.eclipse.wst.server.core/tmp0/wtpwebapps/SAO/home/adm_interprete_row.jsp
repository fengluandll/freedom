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
<%
try
{
	
	
	//id_evento_status = session.setAttribute("id_evento_status");
	
 	String id_interprete = request.getParameter("id_interprete");
	
 	
 	//ProcedureCall.call("evento_version_create_if_not_db", id_evento);
 	String aux= "-1";
 	if("0".equals(id_interprete)){
 		session.setAttribute("interprete", "nuevo");
 	}
 	
 	if("nuevo".equals(session.getAttribute("interprete"))){
 		aux = "0";	
 	}


 	InterpreteForm bean2 = new InterpreteForm(request);
				   
 	
 	if("0".equals(id_interprete)){
		response.sendRedirect("adm_interprete_row.jsp?id_interprete="+bean2.getId_interprete());
	}else{
		id_interprete =bean2.getId_interprete();
	}
 	
 	

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
//     		  % > <script> alert("Ocurrió un error por favor contacta al Administrador");</script> < %  - - % >
//     		  response.sendRedirect("adm_eventos_list.jsp");   		  	    
// 	      }
	      
//     }else if(session.getAttribute("ID_USER") != null & request.getParameter("ID_EVENTO") != null & request.getParameter("ID_EMPRESA") != null) {    	    	
    	
	    
    	
    	
 	    
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>SAO - Int&eacute;rprete</title>

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


	<!-- Switchery -->
    <link href="../home/vendors/switchery/dist/switchery.min.css" rel="stylesheet">
    
    
    
    <!-- Custom Theme Style -->
    <link href="../home/build/css/custom.css" rel="stylesheet">
    
    
    <%@include file="adm_interprete_row-js.jsp" %>
    
    
    
    
  </head>
  <body class="nav-md">
  
  
  	<input type="hidden" id="id_interprete" value="<%= id_interprete %>">
  	
  
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
                    <p><strong>Datos del Int&eacute;rprete </strong></p>
                    <ul class="nav navbar-right panel_toolbox">                                           
                    </ul>
                    <div class="clearfix"></div>
                    
                    <div align="right">
  								  <a href="adm_interprete_list.jsp" type="button" class="btn btn-info">
  								  	Regresar  <%  if("0".equals(aux)) {%>(Guardar) <%} %>
  								  </a>	  <%  if("0".equals(aux)) {%>
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
                          
                          <li role="presentation" class=""><a href="#tab_contentX1" id="profile-tab" role="tab" data-toggle="tab" aria-expanded="true">Cuenta Fiscal</a>
                          </li>
                          
                          <li role="presentation" class=""><a href="#tab_contentX2" id="profile-tab" role="tab" data-toggle="tab" aria-expanded="true">Cuenta No Fiscal</a>
                          </li>
                          
                          
                          <li role="presentation" class=""><a href="#tab_contentX3" id="profile-tab" role="tab" data-toggle="tab" aria-expanded="true">Cuenta Personal</a>
                          </li>
                          
                          
                          <li role="presentation" class=""><a href="#tab_content1" role="tab" id="profile-tab" data-toggle="tab" aria-expanded="true">Especialidades</a>
                          </li>
                          <li role="presentation" class=""><a href="#tab_content2" role="tab" id="profile-tab" data-toggle="tab" aria-expanded="true">Idiomas</a>
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
								  
															                  
									<%= bean2.getFormHtml() %>
								  </div>
					  
					  
					  </div></div>
					  
					  
					  
                        	</div>
                          
                          
                          
                          
                          
                          <div role="tabpanel" class="tab-pane fade" id="tab_contentX1" aria-labelledby="profile-tab">
                        		<div class="x_panel">
										<div class="x_title">
										                    <p><strong>Cuenta Fiscal </strong></p>
										                    <ul class="nav navbar-right panel_toolbox">                                           
										                    </ul>
										                    <div class="clearfix"></div>
										                  </div>
						                <div class="x_content">
										 <div class="col-md-6 col-sm-6 col-xs-12">
											<%= bean2.getFormHtmlCuentaFiscal() %>
										  </div>
							  			</div>
					  			</div>
                        	</div>
                          
                          
                          
                          
                          
                          
                          
                          
                          <div role="tabpanel" class="tab-pane fade" id="tab_contentX2" aria-labelledby="profile-tab">
                        		<div class="x_panel">
										<div class="x_title">
										                    <p><strong>Cuenta No Fiscal </strong></p>
										                    <ul class="nav navbar-right panel_toolbox">                                           
										                    </ul>
										                    <div class="clearfix"></div>
										                  </div>
						                <div class="x_content">
										 <div class="col-md-6 col-sm-6 col-xs-12">
											<%= bean2.getFormHtmlCuentaNoFiscal() %>
										  </div>
							  			</div>
					  			</div>
                        	</div>
                          
                          
                          
                          
                          
                          
                          
                          <div role="tabpanel" class="tab-pane fade" id="tab_contentX3" aria-labelledby="profile-tab">
                        		<div class="x_panel">
										<div class="x_title">
										                    <p><strong>Cuenta Personal</strong></p>
										                    <ul class="nav navbar-right panel_toolbox">                                           
										                    </ul>
										                    <div class="clearfix"></div>
										                  </div>
						                <div class="x_content">
										 <div class="col-md-6 col-sm-6 col-xs-12">
											<%= bean2.getFormHtmlCuentaPersonal() %>
										  </div>
							  			</div>
					  			</div>
                        	</div>
                          
                          
                          
                          
                         <div role="tabpanel" class="tab-pane fade" id="tab_content1" aria-labelledby="profile-tab">



											<div class="col-md-6 col-sm-6 col-xs-12">
							                	<div class="x_panel">
							                	
							                			<div class="x_title">
										                    <p><strong>Especialidades:</strong></p>
										                    <ul class="nav navbar-right panel_toolbox">                                           
										                    </ul>
										                    <div class="clearfix"></div>
										                  </div>
										                  <div class="x_content">										                  
											                 <div id="divEspecialidadesInterprete">
											                  	<%
											                  	EspecialidadesInterpreteTable especialidadesInterpreteTable= new EspecialidadesInterpreteTable(request);
											                  	
											                  	%>
											                  
											                  <%= especialidadesInterpreteTable.getHtml() %>
											                  </div>
										                  </div>
							                	</div>
							                </div>
							                
							                <div class="col-md-6 col-sm-6 col-xs-12">
							                	<div class="x_panel">
							                	
							                			<div class="x_title">
										                    <p><strong>Especialidades Aplicables: </strong></p>
										                    <ul class="nav navbar-right panel_toolbox">                                           
										                    </ul>
										                    <div class="clearfix"></div>
										                  </div>
										                  <div class="x_content">										                  		
										                  	<div id="divEspecialidadesAplicables">
										                  		<%
										                  			EspecialidadesAplicablesTable especialidadesAplicablesTable = new EspecialidadesAplicablesTable(request);
												                  	
												                  	%>
												                  
												                  <%= especialidadesAplicablesTable.getHtml() %>
										                  	</div>
										                  </div>
							                	</div>
							                </div>



                          </div>
                          
                          
                           
                          
                          
                          <div role="tabpanel" class="tab-pane fade" id="tab_content2" aria-labelledby="profile-tab">



											<div class="col-md-6 col-sm-6 col-xs-12">
							                	<div class="x_panel">
							                	
							                			<div class="x_title">
										                    <p><strong>Idiomas:</strong></p>
										                    <ul class="nav navbar-right panel_toolbox">                                           
										                    </ul>
										                    <div class="clearfix"></div>
										                  </div>
										                  <div class="x_content">										                  
											                 <div id="divIdiomasInterprete">
											                  	<%
											                  	IdiomasInterpreteTable idiomasInterpreteTable= new IdiomasInterpreteTable(request);
											                  	
											                  	%>
											                  
											                  <%= idiomasInterpreteTable.getHtml() %>
											                  </div>
										                  </div>
							                	</div>
							                </div>
							                
							                <div class="col-md-6 col-sm-6 col-xs-12">
							                	<div class="x_panel">
							                	
							                			<div class="x_title">
										                    <p><strong>Idiomas Aplicables: </strong></p>
										                    <ul class="nav navbar-right panel_toolbox">                                           
										                    </ul>
										                    <div class="clearfix"></div>
										                  </div>
										                  <div class="x_content">										                  		
										                  	<div id="divIdiomasAplicables">
										                  		<%
										                  			IdiomasAplicablesTable idiomasAplicablesTable = new IdiomasAplicablesTable(request);
												                  	
												                  	%>
												                  
												                  <%= idiomasAplicablesTable.getHtml() %>
										                  	</div>
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
    		   				window.location= 'adm_interprete_list.jsp<%= "?idDeleteRow_="+ id_interprete %>';
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
	response.sendRedirect("adm_eventos_list.jsp");
	
}
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="org.apache.tomcat.util.codec.binary.Base64"%>
<%@ page import="java.sql.*" %> 
<%@ page import="java.io.*" %> 
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %> 
<%@ page import="java.util.Date" %> 
<%@ page import="java.text.DateFormat" %> 
<%@ page import="java.text.SimpleDateFormat" %> 

<%
try
{
	Class.forName("com.mysql.jdbc.Driver");
    String url="jdbc:mysql://localhost:3306/prosante";
    String username="root";
    String password="120315";
    Connection conn=DriverManager.getConnection(url,username,password);
    System.out.println("uno");
    if(request.getParameter("save_back") != null){
	   //Boton Guardar y regresar a lista de eventos 
       
       Statement stSave = conn.createStatement();
	   String queryInsert = "INSERT INTO empresa (NOMBRE, DIRECCION, NOMBRE_CONTACTO, TELEFONO_CONTACTO, CORREO_CONTACTO, IMG_PERFIL) VALUES ('"+
			   request.getParameter("nombre_empresa").toString()+"','"+
			   request.getParameter("direccion").toString()+"','"+
			   request.getParameter("nombre_contacto").toString()+"','"+
			   request.getParameter("numero_contacto").toString()+"','"+
			   request.getParameter("correo_contacto").toString()+"','"+
			   request.getParameter("perfil").toString()+"')";
	   if(stSave.executeUpdate(queryInsert) >= 1){
		   response.sendRedirect("adm_empresas_list.jsp");
	   }
	   	   
    }else if(request.getParameter("save_evento") != null){
    	//Boton Guardar y asignar evento nuevo a la nueva empresa
    	Statement stSave = conn.createStatement();
 	   String queryInsert = "INSERT INTO empresa (NOMBRE, DIRECCION, NOMBRE_CONTACTO, TELEFONO_CONTACTO, CORREO_CONTACTO, IMG_PERFIL) VALUES ('"+
 			   request.getParameter("nombre_empresa").toString()+"','"+
 			   request.getParameter("direccion").toString()+"','"+
 			   request.getParameter("nombre_contacto").toString()+"','"+
 			   request.getParameter("numero_contacto").toString()+"','"+
 			   request.getParameter("correo_contacto").toString()+"','"+
 			   request.getParameter("perfil").toString()+"')";
 	   if(stSave.executeUpdate(queryInsert) >= 1){
 		   Statement stSearch = conn.createStatement();
 		   ResultSet rsSearch = stSearch.executeQuery("SELECT * FROM empresa WHERE NOMBRE = '"+ request.getParameter("nombre_empresa").toString()
 				   +"' AND NOMBRE_CONTACTO = '"+request.getParameter("nombre_contacto").toString()
 				   +"' AND CORREO_CONTACTO = '"+request.getParameter("correo_contacto").toString()+"'");
 		   while(rsSearch.next()){
 		   		response.sendRedirect("nuevo_evento.jsp?ID_EMPRESA="+rsSearch.getInt(1)+"");
 		   }
 	   }
    }else if(session.getAttribute("ID_USER") != null) {    	
	    List<String> lstMenuPadres = new ArrayList<String>();
	    //List<String> lstMenuHijos = new ArrayList<String>();
	    String Role = "";
	 	    	   
    	Statement stScreen = conn.createStatement();
    	ResultSet rsScreen = stScreen.executeQuery("SELECT * FROM screen_role WHERE ID_ROLE = '"+session.getAttribute("ID_ROLE")+"'");
    	while(rsScreen.next()){
    		Statement stName = conn.createStatement();
	    	ResultSet rsName = stName.executeQuery("SELECT * FROM menu WHERE ID_SCREEN = '"+rsScreen.getInt(1)+"' and ID_PADRE = 0 AND ID_ROLE ="+session.getAttribute("ID_ROLE")+"  ORDER BY ID_ORDER, ID");
	    	while(rsName.next()){
	    		lstMenuPadres.add(rsName.getString("NAME"));	    	
	    	}
	    }
    	
    	Statement stR = conn.createStatement();
    	ResultSet rsR = stR.executeQuery("SELECT * FROM role WHERE ID = "+session.getAttribute("ID_ROLE")+"");
    	while(rsR.next()){
    		Role = rsR.getString(2);
    	}
    	
    	String[] dtaUser = new String[10];
    	String imagen = "";
    	String imagenUser = "";
    	Statement stUs = conn.createStatement();
    	ResultSet rsUs = stUs.executeQuery("SELECT * FROM user WHERE ID = "+session.getAttribute("ID_USER")+"");
    	while(rsUs.next()){
    		dtaUser[0] = rsUs.getString(1);
    		dtaUser[1] = rsUs.getString(2);
    		dtaUser[2] = rsUs.getString(3);
    		dtaUser[3] = rsUs.getString(4);
    		dtaUser[4] = rsUs.getString(5);
    		dtaUser[5] = rsUs.getString(6);
    		dtaUser[6] = rsUs.getString(7);
    		dtaUser[7] = rsUs.getString(8);
    		if(rsUs.getString(9)!= null){    		
    			imagen = rsUs.getString(9);
    		}
    		else{
    			Statement stDef = conn.createStatement();
    			ResultSet rsDef = stDef.executeQuery("SELECT * FROM configuracion WHERE id_conf = 3");
    			while(rsDef.next()){
    				imagen = rsDef.getString(3);
    			}
    		}
    		dtaUser[9] = rsUs.getString(10);
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

    <title>Prosant&eacute; - Cliente Nuevo</title>

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

    <!-- Custom Theme Style -->
    <link href="../home/build/css/custom.css" rel="stylesheet">
  </head>
  <body class="nav-md">
    <div class="container body">
      <div class="main_container">
        <div class="col-md-3 left_col">
          <div class="left_col scroll-view">
            <div class="navbar nav_title" style="border: 0;">
              <a href="../home/index.jsp" class="site_title"> <span>Prosant&eacute;</span></a>
            </div>
            <div class="clearfix"></div>			
            <!-- menu profile quick info -->
            <div class="profile">
              <div class="profile_pic">              
               <img src="<%=imagen %>" alt="..." class="img-circle profile_img">
              </div>
              <div class="profile_info">
                <span><%=Role %></span>
                <h2><%=dtaUser[1] %><%=" "%><%=dtaUser[2] %></h2>
              </div>
            </div>
            <!-- /menu profile quick info -->

            <br />
			
			
            <!-- sidebar menu -->
            <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
              <div class="menu_section">
                <h3>General</h3>
                <ul class="nav side-menu">
                <%
                for (String item: lstMenuPadres) {
                	
                	Statement st1 = conn.createStatement();                    
                    ResultSet rs1 = st1.executeQuery("SELECT * FROM menu m WHERE m.NAME = '"+item+"' AND ID_ROLE = "+session.getAttribute("ID_ROLE")+"");
                    while(rs1.next()){
                	%>                	
                	<li><a><i class="<%=rs1.getString("CLASS") %>"></i><%=item %><span class="fa fa-chevron-down"></span></a>
                		<ul class="nav child_menu">
                	<%
	                    Statement st = conn.createStatement();                    
	                    ResultSet rs = st.executeQuery("SELECT * FROM prosante.menu WHERE ID_PADRE = (SELECT ID FROM menu WHERE NAME = '"+item+"' AND ID_ROLE = "+session.getAttribute("ID_ROLE")+") AND ID_ROLE = "+session.getAttribute("ID_ROLE")+"  ORDER BY ID_ORDER, ID");
	                    while(rs.next()){
	                    	%><li><a href="<%=rs.getString("URL") %>"><%=rs.getString("NAME") %></a></li><%
	                    }
                    }
                    %>
                    	</ul>
                   	</li>
                    <%
                }
                %>                                 
                </ul>
              </div>              
            </div>
            <!-- /sidebar menu -->

            <!-- /menu footer buttons -->
            <div class="sidebar-footer hidden-small">
            <!--
              <a data-toggle="tooltip" data-placement="top" title="Settings">
                <span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
              </a>
              <a data-toggle="tooltip" data-placement="top" title="FullScreen">
                <span class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span>
              </a>
              <a data-toggle="tooltip" data-placement="top" title="Lock">
                <span class="glyphicon glyphicon-eye-close" aria-hidden="true"></span>
              </a>
               -->
              <a data-toggle="tooltip" data-placement="top" title="Logout" href="../home/logOut.jsp">
                <span class="glyphicon glyphicon-off" aria-hidden="true"></span>
              </a>
            </div>
            <!-- /menu footer buttons -->
          </div>
        </div>

        <!-- top navigation -->
        <div class="top_nav">
          <div class="nav_menu">
            <nav>
              <div class="nav toggle">
                <a id="menu_toggle"><i class="fa fa-bars"></i></a>
              </div>

              <ul class="nav navbar-nav navbar-right">
                <li class="">
                  <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                    <img src="<%=imagen %>" alt=""><%=dtaUser[1] %><%=" "%><%=dtaUser[2] %>
                    <span class=" fa fa-angle-down"></span>
                  </a>
                  <ul class="dropdown-menu dropdown-usermenu pull-right">                 
                    <li><a href="../home/logOut.jsp"><i class="fa fa-sign-out pull-right"></i> Salir</a></li>
                  </ul>
                </li>
              </ul>
            </nav>
          </div>
        </div>
        <!-- /top navigation -->
        
         <div class="right_col" role="main">
          <div class="">
            <div class="page-title">                        
            </div>

            <div class="clearfix"></div>

			<div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel">
                  <div class="x_title">
                    <p><strong>Sistema de Captura de Prosant&eacute; <strong style="color: #58ACFA;"> Alta de Nueva Empresa</strong> </p>
                    <ul class="nav navbar-right panel_toolbox">                                           
                    </ul>
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                  <div style="width: 800px;">
                    <!-- start form for validation -->
                    <form id="demo-form" class="form-horizontal form-label-left" data-parsley-validate action="nueva_empresa.jsp" method="post">                                
					 
                      <label for="text" style="display: inline-block;">Nombre de la Empresa:</label> 
                      <input type="text" name="nombre_empresa" class="form-control"  required value="" style="width: 500px">  
                      
                      <label for="text" style="display: inline-block;">Direcci&oacute;n :</label>                      
                      <textarea id="message" required="required" class="form-control" name="direccion" data-parsley-trigger="keyup" data-parsley-minlength="8" data-parsley-maxlength="200" data-parsley-minlength-message="La direccion es muy corta"
                            data-parsley-validation-threshold="10" style="width: 500px" required></textarea>
                                                  
					  <hr>  
					                        
                      <label for="text" style="display: inline-block;">Nombre del Contacto :</label>
                      <input type="text" name="nombre_contacto" class="form-control" required value="" style="width: 500px">
                      
                      <label for="text" style="display: inline-block;">N&uacute;mero del Contacto :</label>
                      <input type="text" name="numero_contacto" class="form-control" required value="" style="width: 500px">
                      
                      <label for="text" style="display: inline-block;">Correo Electr&oacute;nico del Contacto : <small>Se utilizar&aacute; como medio de difusi&oacute;n para el Pre Registro </small> </label>
                      <input type="text" name="correo_contacto" class="form-control" required value="" style="width: 500px">                                      
                      <br>
                      <label for="text" style="display: inline-block;">Logo de la Empresa </label>
					  <div style="width: 500px; padding: 0px; padding-top: 20px;" >
						  <div class="col-md-55" style="width: 200px;">
	                        <div class="thumbnail">
	                          <div class="image view view-first">
	                          <%
	                            Statement stImg = conn.createStatement();
		          				ResultSet rsImg = stImg.executeQuery("SELECT * FROM configuracion WHERE id_conf = 3");
		          				while(rsImg.next()){
		          					imagenUser =  rsImg.getString(3);
		          				}
	                          %>
	                            <img style="width: 100%;" src="<%=imagenUser%>" alt="image" id="blah" />
	                            <input type="hidden" value="<%=imagenUser%>" name="perfil" id="perfil_img">
	                            <div class="mask">
	                              <p>Editar Perfil</p>
	                              <div class="tools tools-bottom">	                                                           
	                              </div>
	                            </div>
	                          </div>
	                          <div class="caption">
	                            <p>Foto de Perfil</p>
	                          </div>
	                        </div>
	                      </div>  
	                      <a><input type="file" name="file" id="file" onchange="readURL(this);"/></a>                   
                      </div>
                      <br><br>
                      
                      <br><br><br><br><br><br><br><br><br>
                      <div style="width: 600px;">                      	
                        
                        
                        
                        <a href="adm_empresas_list.jsp" class="btn btn-warning" style="float: left;">Cancelar</a> 
                        
                        <button type="submit" name="save_back" class="btn btn-primary" value="Guardar Datos y Regresar" style="float: right;">Guardar Datos y Regresar</button> &zwnj;
                        <button type="submit" name="save_evento" class="btn btn-info" value="Guardar y Registrar Evento" style="float: right;">Guardar y Registrar Evento</button> &zwnj;
                        
                      </div>                      
                    </form>
                    <!-- end form for validations -->
				   </div>
                  </div>
                  </div>
                </div>
              </div>						
          <div class="pull-right">
            Perfil: <%=Role %>  &Uacute;ltima Conexi&oacute;n: <%=dtaUser[9] %>
          </div>
          <div class="clearfix"></div>
        </footer>
        <!-- /footer content -->
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

    <!-- Custom Theme Scripts -->
    <script src="../build/js/custom.min.js"></script>

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
  function readURL(input) {
      if (input.files && input.files[0]) {
          var reader = new FileReader();

          reader.onload = function (e) {
              $('#blah')
                  .attr('src', e.target.result)
                  .width(170)
                  .height(155);
              $('#perfil_img')
              .val(e.target.result);
          };		
          reader.readAsDataURL(input.files[0]);
      }
  }
  </script>
  </body>
</html>
<%
    } else {
    	response.sendRedirect("adm_user_list.jsp");
    	System.out.println("Khe?");	
    }
}catch(Exception _e){
	System.out.println("Error"+_e);
	_e.printStackTrace();
}
%>
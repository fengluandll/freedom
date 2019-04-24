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
	Class.forName("com.mysql.jdbc.Driver");
    String url="jdbc:mysql://localhost:3306/prosante";
    String username="root";
    String password="120315";
    Connection conn=DriverManager.getConnection(url,username,password);
    
    if(session.getAttribute("ID_USER") != null) {
	    List<String> lstMenuPadres = new ArrayList<String>();
	    //List<String> lstMenuHijos = new ArrayList<String>();
	    String Role = "";
	 	    	   
    	Statement stScreen = conn.createStatement();
    	ResultSet rsScreen = stScreen.executeQuery("SELECT * FROM screen_role WHERE ID_ROLE = '"+session.getAttribute("ID_ROLE")+"'");
    	while(rsScreen.next()){
    		Statement stName = conn.createStatement();
	    	ResultSet rsName = stName.executeQuery("SELECT * FROM menu WHERE ID_SCREEN = '"+rsScreen.getInt(1)+"' and ID_PADRE = 0 AND ID_ROLE ="+session.getAttribute("ID_ROLE")+" ORDER BY ID_ORDER, ID");
	    	while(rsName.next()){
	    		lstMenuPadres.add(rsName.getString("NAME"));	    	
	    	}
	    	//Statement stHijos = conn.createStatement();
	    	//ResultSet rsHijos = stHijos.executeQuery("SELECT * FROM menu WHERE ID_SCREEN = '"+rsScreen.getInt(1)+"' and ID_PADRE > 0 AND ID_ROLE ="+session.getAttribute("ID_ROLE")+"");
	    	//while(rsHijos.next()){
	    		//lstMenuHijos.add(rsHijos.getString("NAME"));
	    	//}
	    }
    	
    	Statement stR = conn.createStatement();
    	ResultSet rsR = stR.executeQuery("SELECT * FROM role WHERE ID = "+session.getAttribute("ID_ROLE")+"");
    	while(rsR.next()){
    		Role = rsR.getString(2);
    	}
    	
    	String[] dtaUser = new String[10];
    	String imagen = "";
    	
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

    <title>Prosant&eacute; - Usuarios</title>

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
                    ResultSet rs1 = st1.executeQuery("SELECT * FROM menu m WHERE m.NAME = '"+item+"' AND ID_ROLE = "+session.getAttribute("ID_ROLE")+" ORDER BY ID_ORDER, ID");
                    while(rs1.next()){
                	%>                	
                	<li><a><i class="<%=rs1.getString("CLASS") %>"></i><%=item %><span class="fa fa-chevron-down"></span></a>
                		<ul class="nav child_menu">
                	<%
	                    Statement st = conn.createStatement();                    
	                    ResultSet rs = st.executeQuery("SELECT * FROM prosante.menu WHERE ID_PADRE = (SELECT ID FROM menu WHERE NAME = '"+item+"' AND ID_ROLE = "+session.getAttribute("ID_ROLE")+") AND ID_ROLE = "+session.getAttribute("ID_ROLE")+" ORDER BY ID_ORDER, ID");
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
                    <!--  <li><a href="javascript:;"> Profile</a></li>
                    <li>
                      <a href="javascript:;">
                        <span class="badge bg-red pull-right">50%</span>
                        <span>Settings</span>
                      </a>
                    </li>
                    <li><a href="javascript:;">Help</a></li>-->
                    <li><a href="../home/logOut.jsp"><i class="fa fa-sign-out pull-right"></i>Salir </a></li>
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
                    <p><strong>Usuarios / Listado de Usuarios</strong> </p>
                    <ul class="nav navbar-right panel_toolbox">                                           
                    </ul>
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                    <p class="text-muted font-13 m-b-30">
                      
                    </p>
                    
                    
                      <div align="right">
                  	<p class="text-muted font-13 m-b-30">
                      <a href="adm_user_new.jsp" type="button" class="btn btn-info">Nuevo Usuario</a>
                    </p>
                    <br>
                    </div>
                    
                    
                    
                    <table id="datatable-buttons" class="table table-striped table-bordered">
                      <thead>
                        <tr>                          
                          <th>Nombre</th>
                          <th>Usuario</th>
                          <th>Fecha de Nacimiento</th>
                          <th>Tipo de Usuario</th> 
                          <th>Administrar</th>                      
                        </tr>
                      </thead>

                      <tbody>                                                
                        <%
                        Statement stUser = conn.createStatement();
                   		ResultSet rsUser = stUser.executeQuery("SELECT u.*, us.ID_ROLE FROM user u INNER JOIN user_role us on us.ID_USER = u.ID");
                  		while(rsUser.next()){
            		      %>
            		      <tr>
	            		      <td><%=rsUser.getString(2)+" "+rsUser.getString(3) %></td>
	            		      <td><%=rsUser.getString(4) %></td>
	            		      <td><%=rsUser.getString(7) %></td>
	            		      <%
	            		      	Statement stsub = conn.createStatement();
	            		        ResultSet rsSub = stsub.executeQuery("SELECT * FROM role WHERE ID ="+rsUser.getString(12)+"");
	            		        while(rsSub.next()){
	            		        %>
	            		        <td><%=rsSub.getString(2) %></td>
	            		        <td><a href="adm_user_edit.jsp?ID_USER=<%=rsUser.getString(1)%>&ID_ROLE=<%=rsUser.getString(12)%>" class="btn btn-info"><i class="fa fa-pencil"></i> Editar Usuario </a> </td>
	            		        <%
	            		        }
	            		      %>	            		     	            		      
            		      </tr>
            		      <%
                  		}
                        %>                                                                         
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>  
               <footer>
          <div class="pull-right">
            Perfil: <%=Role %>  &Uacute;ltima Conexi&oacute;n: <%=dtaUser[9] %>
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
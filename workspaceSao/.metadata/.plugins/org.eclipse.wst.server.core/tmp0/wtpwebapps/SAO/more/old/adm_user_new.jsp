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
    
    if(request.getParameter("UserEdit") != null){
    	boolean avanzar = false;    	    	
    	System.out.println(request.getParameter("gender").toString());
		System.out.println(request.getParameter("nombre").toString());
		System.out.println(request.getParameter("apellidos").toString());
		System.out.println(request.getParameter("fecha").toString());
		System.out.println(request.getParameter("username").toString());
		System.out.println(request.getParameter("userpass").toString());
		System.out.println(request.getParameter("kindUser").toString());
		
    	if(request.getParameter("gender")           != null 
    			& request.getParameter("nombre")    != null
    			& request.getParameter("apellidos") != null
 				& request.getParameter("fecha")     != null
    			& request.getParameter("username")  != null
    			& request.getParameter("userpass")  != null
    			& request.getParameter("kindUser")  != null){
    		
    		System.out.println(request.getParameter("gender").toString());
    		System.out.println(request.getParameter("nombre").toString());
    		System.out.println(request.getParameter("apellidos").toString());
    		System.out.println(request.getParameter("fecha").toString());
    		System.out.println(request.getParameter("username").toString());
    		System.out.println(request.getParameter("userpass").toString());
    		System.out.println(request.getParameter("kindUser").toString());
    		
    		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    		Date date = new Date();
    		String currentTime = dateFormat.format(date);
    		
   		  	Statement stU = conn.createStatement();
   		    String instruccion = "INSERT INTO user (NAME, LAST_NAME, USERNAME, PASSWORD, BIRTHDAY, ID_JOB_TITLE, LASTCONN, SEXO) VALUES ('"+
	   				  request.getParameter("nombre").toString()+"','"+
	   				  request.getParameter("apellidos").toString()+"','"+
	   				  request.getParameter("username").toString()+"','"+
	   				  request.getParameter("userpass").toString()+"','"+
	   				  request.getParameter("fecha").toString()+"','1','"+
				      currentTime+"','"+
					  request.getParameter("gender").toString()+"')";
   		    System.out.println(instruccion);
    		if(stU.executeUpdate(instruccion) >= 1)
	        {
    			Statement getUser = conn.createStatement();
    			ResultSet getUserData = getUser.executeQuery("SELECT ID FROM user WHERE USERNAME = '"+request.getParameter("username").toString()+"'");
    			while(getUserData.next()){
	    			Statement stRole = conn.createStatement();
	    			String queryRole = "INSERT INTO user_role (ID_ROLE, ID_USER) VALUES ('"+request.getParameter("kindUser").toString()+"','"+getUserData.getInt(1)+"')";
	    			if(stRole.executeUpdate(queryRole) >= 1)
	    	        {
	    				avanzar = true;
	    	        }
    			}
	        }
    		else
    		{
    			out.println("-.-");
    		}
    		    
   	        String upDateUser = "UPDATE user SET IMG_PROFILE=? WHERE USERNAME = '"+request.getParameter("username").toString()+"'";
   	        PreparedStatement preparedStmtUser = conn.prepareStatement(upDateUser);
   	        preparedStmtUser.setString (1, request.getParameter("perfil").toString());
   	        System.out.println(upDateUser);
   	        // execute the java preparedstatement
   	        if(preparedStmtUser.executeUpdate() >= 1){
   	    	   avanzar = true;
   	        }
   	        else {
   	    	  avanzar = false;
   	        }    	          	
    	}else{
    		%> <script>alert("Ocurrio un error; \nNo pueden haber valores nulos");</script> <%
    		response.sendRedirect("adm_user_list.jsp");
    	}    	    	
    	
    	if(avanzar){
    		response.sendRedirect("adm_user_list.jsp");
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

    <title>SAO - Usuarios Nuevo</title>

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
                    ResultSet rs1 = st1.executeQuery("SELECT * FROM menu m WHERE m.NAME = '"+item+"' AND ID_ROLE = "+session.getAttribute("ID_ROLE")+"  ORDER BY ID_ORDER, ID");
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
                    <p><strong>Sistema de Captura de Prosant&eacute; <strong>Nuevo Usuario</strong> </p>
                    <ul class="nav navbar-right panel_toolbox">                                           
                    </ul>
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                  <div style="width: 600px;">
                    <!-- start form for validation -->
                    <form id="demo-form" class="form-horizontal form-label-left" data-parsley-validate action="adm_user_new.jsp" method="post">                                
					  <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback" style="padding-left: 0px;">
					    <label >Nombre (s) :</label>
                        <input type="text" name="nombre" style="padding: 14px;" class="form-control has-feedback-left" placeholder="Nombre (s)" required value="">                       
                      </div>

                      <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                        <label for="text" style="display: inline-block;">Apellidos :</label>
                        <input type="text" name="apellidos" class="form-control" placeholder="Apellidos" required value="">                        
                      </div>
                      
                      <label for="text" style="display: inline-block;">Fecha de Nacimiento :</label>
                      <input type="text" name="fecha" class="form-control" placeholder="ej. 1993-08-27" required value="" style="width: 290px">                        
                
                      <br>
                      <label>Genero :</label> <br>                     					  					  
                    		<p>
                    		Masculino: 
                    		<input type="radio" class="flat" name="gender" id="genderM" value="M"  required/> Femenino: 
                    		<input type="radio" class="flat" name="gender" id="genderF" value="F"  required/>
                    		</p>
					  <hr>
					  <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback" style="padding-left: 0px;">
					    <label for="fullname">Usuario de Acceso :</label>
                        <input type="text" name="username" style="padding: 14px;" class="form-control has-feedback-left" id="inputSuccess2" placeholder="Usuario de Acceso" required value="">                       
                      </div>

                      <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                        <label for="text" style="display: inline-block;">Contraseña de Acceso :</label>
                        <input type="text" name="userpass" class="form-control" id="inputSuccess3" placeholder="Contraseña de Acceso" required value="">                        
                      </div>
                      <p>Tipo de Usuario :</p>
                      <div class="item form-group" style="padding-left: 0px;">                                                
                        <div class="col-md-6 col-sm-6 col-xs-12" style="padding-left: 0px;">
                          <select name="kindUser" style="width: 290px;" class="form-control" style="padding-left: 0px;" required>
                          	<%
                          	request.getParameter("ID_ROLE");
                          	Statement stRole = conn.createStatement();
                          	ResultSet rsRole = stRole.executeQuery("SELECT DISTINCT * FROM role");
                          	while(rsRole.next()){                          		
                       			%>
                       			<option value="<%=rsRole.getString(1)%>" selected><%=rsRole.getString(2)%></option>
                       			<%                          		
                          	}
                          	%>
                          </select>   <br>                       
                        </div> <br>
                      </div>     
					  <div style="width: 500px; padding: 0px; padding-top: 50px;" >
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
                      
                      <a href="adm_user_list.jsp" class="btn btn-warning" style="float: left;">Cancelar</a>
                      <button type="submit" name="UserEdit" class="btn btn-primary" style="float: right;">Guardar Datos y Regresar</button> &zwnj;
                      
                       
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
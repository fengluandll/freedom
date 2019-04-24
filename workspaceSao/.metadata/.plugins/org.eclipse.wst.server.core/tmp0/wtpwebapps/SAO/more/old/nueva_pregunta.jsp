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

<!-- Librerias Mail.-->
<%@page import="javax.mail.Authenticator"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Properties"%>
<%@page import="javax.mail.Message"%>
<%@page import="javax.mail.MessagingException"%>
<%@page import="javax.mail.Session"%>
<%@page import="javax.mail.Transport"%>
<%@ page import="javax.mail.BodyPart"%>
<%@page import="javax.mail.internet.InternetAddress"%>
<%@page import="javax.mail.internet.MimeMessage"%>
<%@page import="javax.mail.PasswordAuthentication"%>
<%@page import="javax.mail.internet.InternetAddress"%>
<%@page import="javax.mail.internet.MimeBodyPart"%>
<%@page import="javax.mail.internet.MimeMessage"%>
<%@page import="javax.mail.internet.MimeMultipart"%>
<%@page import="javax.activation.DataHandler"%>
<%@page import="javax.mail.Multipart"%>
<%@page import="javax.activation.DataSource"%>
<%@page import="javax.activation.FileDataSource"%>

<%
try
{
	Class.forName("com.mysql.jdbc.Driver");
    String url="jdbc:mysql://localhost:3306/prosante";
    String username="root";
    String password="120315";
    Connection conn=DriverManager.getConnection(url,username,password);
    
    if(request.getParameter("guardar_back") != null){
    	
    	//nombre_evento  lugar  dte_inicio dte_fin id_empresa
    	//System.out.println(request.getParameter("id_empresa"));
    	Statement stInsert = conn.createStatement();
    	    	    	
    	/*String fechaInicio[] = request.getParameter("dte_inicio").split("/");
    	String fechaFin[] = request.getParameter("dte_fin").split("/");
    	out.println(fechaInicio[2]+"-"+fechaInicio[1]+"-"+fechaInicio[0]);
    	out.println(fechaFin[2]+"-"+fechaFin[1]+"-"+fechaFin[0]);*/
    	
    	String queryInsert = "INSERT INTO cat_preguntaspre (NOMBRE, BLOQUE) VALUES ('"+
    	request.getParameter("NOMBRE")+"', '"+
 	    request.getParameter("BLOQUE")+"')";
    	
    	int a = stInsert.executeUpdate(queryInsert);
    	
    	
    	System.out.println("------->" + a);
    	
    	
    	
    	/*

    	}else if(request.getParameter("guardar_back") != null){
    	System.out.println();
    	Statement stInsert = conn.createStatement();
    	String queryInsert = "INSERT INTO evento (NOMBRE, LUGAR, STATUS, ID_EMPRESA" + 
    			//FECHA_INICIO, FECHA_FIN
    			") VALUES ('"+
    	request.getParameter("nombre_evento")+"', '"+
 	    request.getParameter("lugar")+"','1','"+
 	    request.getParameter("id_empresa") + "'" + //,
 	    /*'"+
 	    request.getParameter("dte_inicio")+"', '"+
 	    request.getParameter("dte_fin")+"' *  
 	    /
 	    ")";
    	if(stInsert.executeUpdate(queryInsert) >= 1){
    		Statement search = conn.createStatement();
    		ResultSet rsSearch = search.executeQuery("SELECT ID FROM evento WHERE NOMBRE = '"+request.getParameter("nombre_evento")+"' AND ID_EMPRESA = "+request.getParameter("id_empresa")); 
    		while(rsSearch.next()){
    			PreparedStatement stUpDate = conn.prepareStatement("UPDATE empresa SET ID_EVENTO=? WHERE ID =?");
    			stUpDate.setInt(1, rsSearch.getInt(1));
    			stUpDate.setInt(2, Integer.parseInt(request.getParameter("id_empresa")));
    			if(stUpDate.executeUpdate() >= 1){
    				
    				response.sendRedirect("adm_eventos_list.jsp");		
    			}
    		}    		
    	}
    	
    	*/
    	
    	
		response.sendRedirect("adm_preguntas_list.jsp");
    	
    }
    	
    	//else 
    	
    	
    if(session.getAttribute("ID_USER") != null) {
    	
    	String imagen = "";
    	String imagenUser = "";
    	Boolean IDEMPRESA = false;
    	String[] dtaCompany = new String[10];
    	if(request.getParameter("ID_EMPRESA") != null){    	    
        	Statement stSearch = conn.createStatement();
        	ResultSet rsSearch = stSearch.executeQuery("SELECT * FROM empresa WHERE ID = "+Integer.parseInt(request.getParameter("ID_EMPRESA").toString())+"");
        	while(rsSearch.next()){
        		dtaCompany[0] = rsSearch.getString(1);
        		dtaCompany[1] = rsSearch.getString(2);
        		dtaCompany[2] = rsSearch.getString(3);
        		dtaCompany[3] = rsSearch.getString(4);
        		dtaCompany[4] = rsSearch.getString(5);
        		dtaCompany[5] = rsSearch.getString(6);
        		dtaCompany[6] = rsSearch.getString(7);
        		if(rsSearch.getString(8) != null){    		
        			imagen = rsSearch.getString(8);
        		}
        		else{
        			Statement stDef = conn.createStatement();
        			ResultSet rsDef = stDef.executeQuery("SELECT * FROM configuracion WHERE id_conf = 3");
        			while(rsDef.next()){
        				imagen = rsDef.getString(3);
        			}
        		}
        		IDEMPRESA = true;
        	}
        }    	
    	
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

    <title>Prosant&eacute; - Evento Nuevo</title>
	
	<link rel="stylesheet" href="css/datepicker.css">
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
                    <p><strong>Sistema de Captura de Prosant&eacute; Nuevo Evento </p>
                    <ul class="nav navbar-right panel_toolbox">                                           
                    </ul>
                    <div class="clearfix"></div>
                  </div>
                  <form id="demo-form" class="form-horizontal form-label-left" data-parsley-validate action="" method="post">
                  <div class="x_content">
                  <div style="width: 430px;">
                    <!-- start form for validation -->
                      
                      
                       <label for="text" style="display: inline-block;">Pregunta:</label> 
                      <input type="text" name="NOMBRE" class="form-control"  required  style="width: 400px">
                      <!-- 
                      placeholder="ej. Prosant&eacute; "  
                       -->
                      <p>Bloque :</p>
                      <div class="item form-group" style="padding-left: 0px;">                                                
                        <div class="col-md-6 col-sm-6 col-xs-12" style="padding-left: 0px;">
                          <select name="BLOQUE" style="width: 400px;" class="form-control" style="padding-left: 0px;" required>
                          	<%
        	                Statement stEmpresa = conn.createStatement();
        	                ResultSet rsGetEmpresa = stEmpresa.executeQuery("SELECT * FROM cat_bloque;");
        	                while(rsGetEmpresa.next()){
        	                	%>
                            	<option value="<%=rsGetEmpresa.getInt(1)%>" ><%=rsGetEmpresa.getString(2)%></option>
                            	<%
        	                }
                          	%>
                          </select>                     
                        </div>
                      </div>  
                      
                      
                      
                      
                      
                      
				   </div>


					  <script type="text/javascript">
					  	var x =0;
					  	function mostrar()
			            {						  		
			                document.getElementById('oculto'+x).style.display = 'block';
			                x++;
			            }			
			          </script>
					                                                                                          
                      <br><br>
                      <div style="width: 430px;">
                      
                      <button type="submit" name="guardar_back" class="btn btn-primary" style="float: right;">Guardar y Regresar</button>                       
                      <a href="adm_preguntas_list.jsp" class="btn btn-warning" style="float: left;">Cancelar</a>                    
                      </div>
                    <!-- end form for validations -->
                  </div>
                  </form>
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
  
  <script src="js/jquery-1.9.1.min.js"></script>
        <script src="js/bootstrap-datepicker.js"></script>
        <script type="text/javascript">
            // When the document is ready
            $(document).ready(function () {
                
                $('#date1').datepicker({
                    format: "dd/mm/yyyy"
                });  
            
            });
        </script>
        
        <script type="text/javascript">
            // When the document is ready
            $(document).ready(function () {
                
                $('#date2').datepicker({
                    format: "dd/mm/yyyy"
                });  
            
            });
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
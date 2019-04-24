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

<%
try
{
// 	Class.forName("com.mysql.jdbc.Driver");
//     String url="jdbc:mysql://localhost:3306/prosante";
//     String username="root";
//     String password="120315";
//     Connection conn=DriverManager.getConnection(url,username,password);
    
//     if(session.getAttribute("ID_USER") != null) {
// 	    List<String> lstMenuPadres = new ArrayList<String>();
// 	    String Role = "";
//     	Statement stScreen = conn.createStatement();
//     	ResultSet rsScreen = stScreen.executeQuery("SELECT * FROM screen_role WHERE ID_ROLE = '"+session.getAttribute("ID_ROLE")+"'");
//     	while(rsScreen.next()){
//     		Statement stName = conn.createStatement();
// 	    	ResultSet rsName = stName.executeQuery("SELECT * FROM menu WHERE ID_SCREEN = '"+rsScreen.getInt(1)+"' and ID_PADRE = 0 AND ID_ROLE ="+session.getAttribute("ID_ROLE")+" ORDER BY ID_ORDER, ID");
// 	    	while(rsName.next()){
// 	    		lstMenuPadres.add(rsName.getString("NAME"));	    	
// 	    	}
// 	    }
	    		       	
//     	Statement stR = conn.createStatement();
//     	ResultSet rsR = stR.executeQuery("SELECT * FROM role WHERE ID = "+session.getAttribute("ID_ROLE")+"");
//     	while(rsR.next()){
//     		Role = rsR.getString(2);
//     	}
    	
//     	String[] dtaUser = new String[10];
//     	String imagen = "";    	    	
    	
//     	Statement stUs = conn.createStatement();
//     	ResultSet rsUs = stUs.executeQuery("SELECT * FROM user WHERE ID = "+session.getAttribute("ID_USER")+"");
//     	while(rsUs.next()){
//     		dtaUser[0] = rsUs.getString(1);
//     		dtaUser[1] = rsUs.getString(2);
//     		dtaUser[2] = rsUs.getString(3);
//     		dtaUser[3] = rsUs.getString(4);
//     		dtaUser[4] = rsUs.getString(5);
//     		dtaUser[5] = rsUs.getString(6);
//     		dtaUser[6] = rsUs.getString(7);
//     		dtaUser[7] = rsUs.getString(8);
//     		if(rsUs.getString(9) != null){    	
//     			imagen = rsUs.getString(9);
//     		}
//     		else{
//     			Statement stDef = conn.createStatement();
//     			ResultSet rsDef = stDef.executeQuery("SELECT * FROM configuracion WHERE id_conf = 3");
//     			while(rsDef.next()){	    			
// 					imagen = rsDef.getString(3);
//     			}
//     		}
//     		dtaUser[9] = rsUs.getString(10);
//     	}    	      	    	
		    	
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>SAO </title>

    <!-- Bootstrap -->
    <link href="vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="vendors/nprogress/nprogress.css" rel="stylesheet">
    <!-- iCheck -->
    <%-- <link href="vendors/iCheck/skins/flat/green.css" rel="stylesheet">--%>
    <!-- bootstrap-progressbar -->
    <link href="vendors/bootstrap-progressbar/css/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet">
    <!-- JQVMap -->
    <link href="vendors/jqvmap/dist/jqvmap.min.css" rel="stylesheet"/>

    <!-- Custom Theme Style -->
    <link href="build/css/custom.css" rel="stylesheet">
    
    
    
  </head>
  <body class="nav-md">
    <div class="container body">
      <div class="main_container">
      
      
      	<%@include file="commonSideBarMenu.jsp" %>
      	
      	

        <!-- page content -->
        <div class="right_col" role="main">         
          <div class="row">
            <div class="col-md-12 col-sm-12 col-xs-12">
              <div class="dashboard_graph">

                <div class="row x_title">
                  <div class="col-md-6">
                  <%= "" %>
                    <p><strong>Sistema Automatizado Omnilingua</strong> </p>
                  </div>                  
                </div>
				<!-- 
				<img alt="" src="../img/inicio_i.png">-->
				<center>
				<img src="../img/SLogo-Omnilingua.png" alt="..." >
				</center>                
                <div class="clearfix"></div>
              </div>
            </div>
          </div>
        </div>
        <!-- /page content -->

        <!-- footer content -->
        <footer>
          <div class="pull-right">
           <!-- Perfil: < % =Role  % >  &Uacute;ltima Conexi&oacute;n: < % = d t a User[9] % > -->
          </div>
          <div class="clearfix"></div>
        </footer>
        <!-- /footer content -->
      </div>
    </div>

    <!-- jQuery -->
    <script src="vendors/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap -->
    <script src="vendors/bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- FastClick -->
    <script src="vendors/fastclick/lib/fastclick.js"></script>
    <!-- NProgress -->
    <script src="vendors/nprogress/nprogress.js"></script>
    <!-- Chart.js -->
<!--     <script src="vendors/Chart.js/dist/Chart.min.js"></script> -->
    <!-- gauge.js -->
    <%-- <script src="vendors/gauge.js/dist/gauge.min.js"></script>--%>
    <!-- bootstrap-progressbar -->
    <script src="vendors/bootstrap-progressbar/bootstrap-progressbar.min.js"></script>
    <!-- iCheck -->
    <%--<script src="vendors/iCheck/icheck.min.js"></script> --%>
    <!-- Skycons -->
    <script src="vendors/skycons/skycons.js"></script>
    <!-- Flot -->
    <%-- 
    <script src="vendors/Flot/jquery.flot.js"></script>
    <script src="vendors/Flot/jquery.flot.pie.js"></script>
    <script src="vendors/Flot/jquery.flot.time.js"></script>
    <script src="vendors/Flot/jquery.flot.stack.js"></script>
    <script src="vendors/Flot/jquery.flot.resize.js"></script>
    
    <!-- Flot plugins -->
    <script src="vendors/flot.orderbars/js/jquery.flot.orderBars.js"></script>
    <script src="vendors/flot-spline/js/jquery.flot.spline.min.js"></script>
    <script src="vendors/flot.curvedlines/curvedLines.js"></script>
    --%>
    <!-- DateJS -->
    <%--
    	<script src="vendors/DateJS/build/date.js"></script>
     --%>
    <!-- JQVMap -->
    <script src="vendors/jqvmap/dist/jquery.vmap.js"></script>
    <script src="vendors/jqvmap/dist/maps/jquery.vmap.world.js"></script>
    <script src="vendors/jqvmap/examples/js/jquery.vmap.sampledata.js"></script>
    <!-- bootstrap-daterangepicker -->
    <%--
    <script src="js/moment/moment.min.js"></script>
    <script src="js/datepicker/daterangepicker.js"></script>
 	--%>
    <!-- Custom Theme Scripts -->
    <script src="build/js/custom.min.js"></script>

    <!-- Flot -->
    <script>
      $(document).ready(function() {
        var data1 = [
          [gd(2012, 1, 1), 17],
          [gd(2012, 1, 2), 74],
          [gd(2012, 1, 3), 6],
          [gd(2012, 1, 4), 39],
          [gd(2012, 1, 5), 20],
          [gd(2012, 1, 6), 85],
          [gd(2012, 1, 7), 7]
        ];

        var data2 = [
          [gd(2012, 1, 1), 82],
          [gd(2012, 1, 2), 23],
          [gd(2012, 1, 3), 66],
          [gd(2012, 1, 4), 9],
          [gd(2012, 1, 5), 119],
          [gd(2012, 1, 6), 6],
          [gd(2012, 1, 7), 9]
        ];
        $("#canvas_dahs").length && $.plot($("#canvas_dahs"), [
          data1, data2
        ], {
          series: {
            lines: {
              show: false,
              fill: true
            },
            splines: {
              show: true,
              tension: 0.4,
              lineWidth: 1,
              fill: 0.4
            },
            points: {
              radius: 0,
              show: true
            },
            shadowSize: 2
          },
          grid: {
            verticalLines: true,
            hoverable: true,
            clickable: true,
            tickColor: "#d5d5d5",
            borderWidth: 1,
            color: '#fff'
          },
          colors: ["rgba(38, 185, 154, 0.38)", "rgba(3, 88, 106, 0.38)"],
          xaxis: {
            tickColor: "rgba(51, 51, 51, 0.06)",
            mode: "time",
            tickSize: [1, "day"],
            //tickLength: 10,
            axisLabel: "Date",
            axisLabelUseCanvas: true,
            axisLabelFontSizePixels: 12,
            axisLabelFontFamily: 'Verdana, Arial',
            axisLabelPadding: 10
          },
          yaxis: {
            ticks: 8,
            tickColor: "rgba(51, 51, 51, 0.06)",
          },
          tooltip: false
        });

        function gd(year, month, day) {
          return new Date(year, month - 1, day).getTime();
        }
      });
    </script>
    <!-- /Flot -->

    <!-- JQVMap -->
    <script>
      $(document).ready(function(){
        $('#world-map-gdp').vectorMap({
            map: 'world_en',
            backgroundColor: null,
            color: '#ffffff',
            hoverOpacity: 0.7,
            selectedColor: '#666666',
            enableZoom: true,
            showTooltip: true,
            values: sample_data,
            scaleColors: ['#E6F2F0', '#149B7E'],
            normalizeFunction: 'polynomial'
        });
      });
    </script>
    <!-- /JQVMap -->

    <!-- Skycons -->
    <script>
      $(document).ready(function() {
        var icons = new Skycons({
            "color": "#73879C"
          }),
          list = [
            "clear-day", "clear-night", "partly-cloudy-day",
            "partly-cloudy-night", "cloudy", "rain", "sleet", "snow", "wind",
            "fog"
          ],
          i;

        for (i = list.length; i--;)
          icons.set(list[i], list[i]);

        icons.play();
      });
    </script>
    <!-- /Skycons -->

    
    
    

<%--

    <!-- gauge.js -->
    <script>
      var opts = {
          lines: 12,
          angle: 0,
          lineWidth: 0.4,
          pointer: {
              length: 0.75,
              strokeWidth: 0.042,
              color: '#1D212A'
          },
          limitMax: 'false',
          colorStart: '#1ABC9C',
          colorStop: '#1ABC9C',
          strokeColor: '#F0F3F3',
          generateGradient: true
      };
      var target = document.getElementById('foo'),
          gauge = new Gauge(target).setOptions(opts);

      gauge.maxValue = 6000;
      gauge.animationSpeed = 32;
      gauge.set(3200);
      gauge.setTextField(document.getElementById("gauge-text"));
    </script>
    <!-- /gauge.js -->
    
    
     --%>
    
    
  </body>
</html>
<%
//     } else {
//     	response.sendRedirect("login.jsp");
//     }
}catch(Exception _e){
	System.out.println("Error: "+_e);	
	_e.printStackTrace();
	%>
	<script>window.location='/SAO'</script>    
	<%
}
%>

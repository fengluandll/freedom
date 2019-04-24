<%@page import="com.movemini.config.HardCodeConstants"%>
<%@page import="com.movemini.layers.session.SessionBean"%>
<%
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
	response.setHeader("Pragma","no-cache"); //HTTP 1.0
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<%@page import="com.movemini.layers.view.menu.RecordCounter"%>
<%@page import="com.movemini.data.DataArray"%>
<%@page import="com.movemini.data.Record"%>
<%@page import="com.movemini.data.ConnectionWrapper"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%



// 	Connection connx = ConnectionWrapper.getDirectJDBCConnection();



Record user = SessionBean.getInstance(request).getUser();



// 	List<String> lstMenuPadres = new ArrayList<String>();
// 	String Role = "";

// 	Statement stScreen = connx.createStatement();
// 	ResultSet rsScreen = stScreen.executeQuery("SELECT * FROM screen_role WHERE ID_ROLE = '"+session.getAttribute("ID_ROLE")+"'");
// 	while(rsScreen.next()){
// 		Statement stName = connx.createStatement();
// 		ResultSet rsName = stName.executeQuery("SELECT * FROM menu WHERE ID_SCREEN = '"+rsScreen.getInt(1)+"' and ID_PADRE = 0 AND ID_ROLE ="+session.getAttribute("ID_ROLE")+" ORDER BY ID_ORDER, ID");
// 		while(rsName.next()){
// 			lstMenuPadres.add(rsName.getString("NAME"));
// 		}
// 	}



	ArrayList<Record> menuParents = DataArray.getArrayList("SELECT * FROM ss_menu WHERE id_menu_padre = 0 AND accesos LIKE '%" + user.get("id_rol") + "%' ORDER BY id_orden");




// 	Statement stR = connx.createStatement();
// 	ResultSet rsR = stR.executeQuery("SELECT * FROM role WHERE ID = "+session.getAttribute("ID_ROLE")+"");
// 	while(rsR.next()){
// 		Role = rsR.getString(2);
// 	}




// 	String[] dtaUser = new String[10];
// 	String imagen = "";
// 	String imagenUser = "";
// 	Statement stUs = connx.createStatement();
// 	//ResultSet rsUs = stUs.executeQuery("SELECT * FROM user WHERE ID = "+session.getAttribute("ID_USER")+"");
// 	while(rsUs.next()){
// // 		dtaUser[0] = rsUs.getString(1);
// // 		dtaUser[1] = rsUs.getString(2);
// // 		dtaUser[2] = rsUs.getString(3);
// // 		dtaUser[3] = rsUs.getString(4);
// // 		dtaUser[4] = rsUs.getString(5);
// // 		dtaUser[5] = rsUs.getString(6);
// // 		dtaUser[6] = rsUs.getString(7);
// // 		dtaUser[7] = rsUs.getString(8);
// 		if(rsUs.getString(9)!= null){
// 			imagen = rsUs.getString(9);
// 		}
// 		else{
// 			Statement stDef = connx.createStatement();
// 			ResultSet rsDef = stDef.executeQuery("SELECT * FROM configuracion WHERE id_conf = 3");
// 			while(rsDef.next()){
// 				imagen = rsDef.getString(3);
// 			}
// 		}
// 		dtaUser[9] = rsUs.getString(10);
// 	}




// 	Statement stEditEvento = connx.createStatement();
// 	ResultSet rsEditEvento = stEditEvento.executeQuery("SELECT * FROM evento WHERE ID = "+request.getParameter("ID_EVENTO")+"");
// 	String[] lstEvento = new String[12];
// 	while(rsEditEvento.next()){
// 		lstEvento[0] =  rsEditEvento.getString(1);
// 		lstEvento[1] =  rsEditEvento.getString(2);
// 		lstEvento[2] =  rsEditEvento.getString(3);
// 		lstEvento[3] =  rsEditEvento.getString(4);
// 		lstEvento[4] =  rsEditEvento.getString(5);
// 		lstEvento[5] =  rsEditEvento.getString(6);
// 		lstEvento[6] =  rsEditEvento.getString(7);
// 	}









%>


      <div class="col-md-3 left_col">
          <div class="left_col scroll-view">



		 		<div class="navbar nav_title" style="border: 0;">
		              <a href="main.jsp" class="site_title"> <span>SAO</span></a>
		            </div>
		            <div class="clearfix"></div>

		           <!--
		            -->


		            <!-- menu profile quick info -->
		            <div class="profile">
		              <div class="profile_pic">

<%-- 		               <img src="<%= user.get("imagen") %>" alt="..." class="img-circle profile_img"> --%>

		              <img id='profileImg1' src="/<%= HardCodeConstants.CONTEXT_PATH %>/ProfilePhotoServlet" width="40%" height="40%" class="img-circle profile_img">



		              </div>
		              <div class="profile_info">
		                <!--<span><%= user.get("rol_name") %></span>-->
		                <h2><%= user.get("nombre") %><%=" "%><%= user.get("apellido") %></h2>
		              </div>
		            </div>
		            <!-- /menu profile quick info -->

		            <br />




					<!-- sidebar menu -->
		            <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
		              <div class="menu_section">
		                <!-- <h3>Menu</h3>-->
		                <br>
		                <br>
		                <br>


		                <ul class="nav side-menu">
		                <%



		                for (Record recordParent: menuParents) {

		                	//Statement st1 = connx.createStatement();
		                    //ResultSet rs1 = st1.executeQuery("SELECT * FROM menu m WHERE m.NAME = '"+item+"' AND ID_ROLE = "+session.getAttribute("ID_ROLE")+" ORDER BY ID_ORDER, ID");

		                    %>
		                	<li><a>
		                			<i class="<%=recordParent.getString("class") %>"></i>
		                					<%=recordParent.get("nombre") %>
		                					<span class="fa fa-chevron-down"></span>
		                		</a>
		                		<ul class="nav child_menu">
		                		<%




		                	ArrayList<Record> menuChilds = DataArray.getArrayList("SELECT * FROM ss_menu WHERE id_menu_padre = " + recordParent.get("id_menu") + " AND accesos LIKE '%" + user.get("id_rol") + "%' ORDER BY id_orden");

		                	for (Record recordChild: menuChilds) {
		                    	%>

		                			<li class="<%=recordChild.getString("class") %>"><a href="<%=recordChild.getString("url")%>">
		                			<%= recordChild.getString("nombre") %>
		                			<%= RecordCounter.getCount(user.get("id_usuario"), recordChild.get("url")) %>
		                			</a></li>


		                			<%
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

									<!--	<a href="javascript:void(0);" data-toggle="tooltip" onclick="alert()">
											<i class="glyphicon glyphicon-bell"></i>
											<span class="badge bg-green">6</span>

									</a> -->

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

<%--                     <img src="<%= user.get("imagen") %>" alt=""> --%>

                    <img id='profileImg2' src="/<%= HardCodeConstants.CONTEXT_PATH %>/ProfilePhotoServlet" width="40%" height="40%">

                    <%= user.get("nombre") %><%=" "%><%= user.get("apellido") %>


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




<%


// ConnectionWrapper.closeAll(rsScreen, stScreen, rsR, stR, connx);


%>

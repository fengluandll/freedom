/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.82
 * Generated at: 2018-06-05 23:46:35 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.home;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.StringTokenizer;
import com.movemini.data.OneRecordFactory;
import com.movemini.data.Record;
import com.movemini.data.DataArray;
import org.apache.tomcat.util.codec.binary.Base64;
import java.sql.*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import com.movemini.config.HardCodeConstants;
import com.movemini.layers.session.SessionBean;
import com.movemini.layers.view.menu.RecordCounter;
import com.movemini.data.DataArray;
import com.movemini.data.Record;
import com.movemini.data.ConnectionWrapper;
import java.sql.DriverManager;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.Statement;

public final class adm_005fentity_005flist_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(1);
    _jspx_dependants.put("/home/commonSideBarMenu.jsp", Long.valueOf(1527701485317L));
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=ISO-8859-1");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;


	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
	response.setHeader("Pragma","no-cache"); //HTTP 1.0
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");

	try {
		
		String entityCode = request.getParameter("ec");
		
		session.setAttribute("entityCode", entityCode);
		
		if("nuevo".equals(session.getAttribute(entityCode))){
	 		session.removeAttribute(entityCode);
	 	}
		
		Record screenConfig =  OneRecordFactory.getFirstRecord("SELECT * FROM flex_entity WHERE id = " + entityCode);
		
		
		
		
		boolean access = true;

		if (access) {


                    	String message = "";
                    
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
                    				"UPDATE " + screenConfig.get("table")  + " SET id_status = '0' WHERE " + screenConfig.get("column_id") + " = '" + xid + "'");
                    		response.sendRedirect("adm_entity_list.jsp?ec="+entityCode+ (request.getParameter("idDeleteRow_") == null?"&m=1":""));
                    	
                    	}
                    
                    
                    	
                    	

				String detailPage = "adm_entity_row.jsp";
			
				if(entityCode.equals("6")) {
					
					detailPage = "adm_entity_cliente_row.jsp";
				}
			
				if(entityCode.equals("11")) {
					
					detailPage = "adm_entity_row_paquete.jsp";
				}
		
													
													
													

      out.write("\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">\n");
      out.write("<!-- Meta, title, CSS, favicons, etc. -->\n");
      out.write("<meta charset=\"utf-8\">\n");
      out.write("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n");
      out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("\n");
      out.write("<title>");
      out.print( screenConfig.getString("title") );
      out.write("</title>\n");
      out.write("\n");
      out.write("<!-- Bootstrap -->\n");
      out.write("<link href=\"../home/vendors/bootstrap/dist/css/bootstrap.min.css\"\n");
      out.write("\trel=\"stylesheet\">\n");
      out.write("<!-- Font Awesome -->\n");
      out.write("<link href=\"../home/vendors/font-awesome/css/font-awesome.min.css\"\n");
      out.write("\trel=\"stylesheet\">\n");
      out.write("<!-- NProgress -->\n");
      out.write("<link href=\"../home/vendors/nprogress/nprogress.css\" rel=\"stylesheet\">\n");
      out.write("<!-- iCheck -->\n");
      out.write("\n");
      out.write("<!-- Datatables -->\n");
      out.write("<link\n");
      out.write("\thref=\"../home/vendors/datatables.net-bs/css/dataTables.bootstrap.min.css\"\n");
      out.write("\trel=\"stylesheet\">\n");
      out.write("<link\n");
      out.write("\thref=\"../home/vendors/datatables.net-buttons-bs/css/buttons.bootstrap.min.css\"\n");
      out.write("\trel=\"stylesheet\">\n");
      out.write("<link\n");
      out.write("\thref=\"../home/vendors/datatables.net-fixedheader-bs/css/fixedHeader.bootstrap.min.css\"\n");
      out.write("\trel=\"stylesheet\">\n");
      out.write("<link\n");
      out.write("\thref=\"../home/vendors/datatables.net-responsive-bs/css/responsive.bootstrap.min.css\"\n");
      out.write("\trel=\"stylesheet\">\n");
      out.write("<link\n");
      out.write("\thref=\"../home/vendors/datatables.net-scroller-bs/css/scroller.bootstrap.min.css\"\n");
      out.write("\trel=\"stylesheet\">\n");
      out.write("\n");
      out.write("<!-- Custom Theme Style -->\n");
      out.write("<link href=\"../home/build/css/custom.css\" rel=\"stylesheet\">\n");
      out.write("\n");
      out.write("<link href=\"../home/vendors/sweet_alert/sweetalert.css\" rel=\"stylesheet\">\n");
      out.write("    \n");
      out.write("    \n");
      out.write("    \n");
      out.write("    \n");
      out.write("</head>\n");
      out.write("<body class=\"nav-md\">\n");
      out.write("\t<div class=\"container body\">\n");
      out.write("\t\t<div class=\"main_container\">\n");
      out.write("\n");
      out.write("\n");
      out.write("\t\t\t");
      out.write('\n');
      out.write('\n');

	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
	response.setHeader("Pragma","no-cache"); //HTTP 1.0
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");




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










      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("      <div class=\"col-md-3 left_col\">\n");
      out.write("          <div class=\"left_col scroll-view\">\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\t\t \t\t<div class=\"navbar nav_title\" style=\"border: 0;\">\n");
      out.write("\t\t              <a href=\"main.jsp\" class=\"site_title\"> <span>SAO</span></a>\n");
      out.write("\t\t            </div>\n");
      out.write("\t\t            <div class=\"clearfix\"></div>\n");
      out.write("\n");
      out.write("\t\t           <!--\n");
      out.write("\t\t            -->\n");
      out.write("\n");
      out.write("\n");
      out.write("\t\t            <!-- menu profile quick info -->\n");
      out.write("\t\t            <div class=\"profile\">\n");
      out.write("\t\t              <div class=\"profile_pic\">\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\t\t              <img id='profileImg1' src=\"/");
      out.print( HardCodeConstants.CONTEXT_PATH );
      out.write("/ProfilePhotoServlet\" width=\"40%\" height=\"40%\" class=\"img-circle profile_img\">\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\t\t              </div>\n");
      out.write("\t\t              <div class=\"profile_info\">\n");
      out.write("\t\t                <!--<span>");
      out.print( user.get("rol_name") );
      out.write("</span>-->\n");
      out.write("\t\t                <h2>");
      out.print( user.get("nombre") );
      out.print(" ");
      out.print( user.get("apellido") );
      out.write("</h2>\n");
      out.write("\t\t              </div>\n");
      out.write("\t\t            </div>\n");
      out.write("\t\t            <!-- /menu profile quick info -->\n");
      out.write("\n");
      out.write("\t\t            <br />\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\t\t\t\t\t<!-- sidebar menu -->\n");
      out.write("\t\t            <div id=\"sidebar-menu\" class=\"main_menu_side hidden-print main_menu\">\n");
      out.write("\t\t              <div class=\"menu_section\">\n");
      out.write("\t\t                <!-- <h3>Menu</h3>-->\n");
      out.write("\t\t                <br>\n");
      out.write("\t\t                <br>\n");
      out.write("\t\t                <br>\n");
      out.write("\n");
      out.write("\n");
      out.write("\t\t                <ul class=\"nav side-menu\">\n");
      out.write("\t\t                ");




		                for (Record recordParent: menuParents) {

		                	//Statement st1 = connx.createStatement();
		                    //ResultSet rs1 = st1.executeQuery("SELECT * FROM menu m WHERE m.NAME = '"+item+"' AND ID_ROLE = "+session.getAttribute("ID_ROLE")+" ORDER BY ID_ORDER, ID");

		                    
      out.write("\n");
      out.write("\t\t                \t<li><a>\n");
      out.write("\t\t                \t\t\t<i class=\"");
      out.print(recordParent.getString("class") );
      out.write("\"></i>\n");
      out.write("\t\t                \t\t\t\t\t");
      out.print(recordParent.get("nombre") );
      out.write("\n");
      out.write("\t\t                \t\t\t\t\t<span class=\"fa fa-chevron-down\"></span>\n");
      out.write("\t\t                \t\t</a>\n");
      out.write("\t\t                \t\t<ul class=\"nav child_menu\">\n");
      out.write("\t\t                \t\t");





		                	ArrayList<Record> menuChilds = DataArray.getArrayList("SELECT * FROM ss_menu WHERE id_menu_padre = " + recordParent.get("id_menu") + " AND accesos LIKE '%" + user.get("id_rol") + "%' ORDER BY id_orden");

		                	for (Record recordChild: menuChilds) {
		                    	
      out.write("\n");
      out.write("\n");
      out.write("\t\t                \t\t\t<li class=\"");
      out.print(recordChild.getString("class") );
      out.write("\"><a href=\"");
      out.print(recordChild.getString("url"));
      out.write("\">\n");
      out.write("\t\t                \t\t\t");
      out.print( recordChild.getString("nombre") );
      out.write("\n");
      out.write("\t\t                \t\t\t");
      out.print( RecordCounter.getCount(user.get("id_usuario"), recordChild.get("url")) );
      out.write("\n");
      out.write("\t\t                \t\t\t</a></li>\n");
      out.write("\n");
      out.write("\n");
      out.write("\t\t                \t\t\t");

		                    }

		                    
      out.write("\n");
      out.write("\t\t                    \t</ul>\n");
      out.write("\t\t                   \t</li>\n");
      out.write("\t\t                    ");

		                }
		                
      out.write("\n");
      out.write("\t\t                </ul>\n");
      out.write("\t\t              </div>\n");
      out.write("\t\t            </div>\n");
      out.write("\t\t            <!-- /sidebar menu -->\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\t\t            <!-- /menu footer buttons -->\n");
      out.write("\t\t            <div class=\"sidebar-footer hidden-small\">\n");
      out.write("\t\t            <!--\n");
      out.write("\t\t              <a data-toggle=\"tooltip\" data-placement=\"top\" title=\"Settings\">\n");
      out.write("\t\t                <span class=\"glyphicon glyphicon-cog\" aria-hidden=\"true\"></span>\n");
      out.write("\t\t              </a>\n");
      out.write("\t\t              <a data-toggle=\"tooltip\" data-placement=\"top\" title=\"FullScreen\">\n");
      out.write("\t\t                <span class=\"glyphicon glyphicon-fullscreen\" aria-hidden=\"true\"></span>\n");
      out.write("\t\t              </a>\n");
      out.write("\t\t              <a data-toggle=\"tooltip\" data-placement=\"top\" title=\"Lock\">\n");
      out.write("\t\t                <span class=\"glyphicon glyphicon-eye-close\" aria-hidden=\"true\"></span>\n");
      out.write("\t\t              </a>\n");
      out.write("\t\t               -->\n");
      out.write("\t\t              <a data-toggle=\"tooltip\" data-placement=\"top\" title=\"Logout\" href=\"../home/logOut.jsp\">\n");
      out.write("\t\t                <span class=\"glyphicon glyphicon-off\" aria-hidden=\"true\"></span>\n");
      out.write("\t\t              </a>\n");
      out.write("\n");
      out.write("\t\t\t\t\t\t\t\t\t<!--\t<a href=\"javascript:void(0);\" data-toggle=\"tooltip\" onclick=\"alert()\">\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<i class=\"glyphicon glyphicon-bell\"></i>\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<span class=\"badge bg-green\">6</span>\n");
      out.write("\n");
      out.write("\t\t\t\t\t\t\t\t\t</a> -->\n");
      out.write("\n");
      out.write("\t\t            </div>\n");
      out.write("\t\t            <!-- /menu footer buttons -->\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("          </div>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("        <!-- top navigation -->\n");
      out.write("        <div class=\"top_nav\">\n");
      out.write("          <div class=\"nav_menu\">\n");
      out.write("            <nav>\n");
      out.write("              <div class=\"nav toggle\">\n");
      out.write("                <a id=\"menu_toggle\"><i class=\"fa fa-bars\"></i></a>\n");
      out.write("              </div>\n");
      out.write("\n");
      out.write("              <ul class=\"nav navbar-nav navbar-right\">\n");
      out.write("                <li class=\"\">\n");
      out.write("                  <a href=\"javascript:;\" class=\"user-profile dropdown-toggle\" data-toggle=\"dropdown\" aria-expanded=\"false\">\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("                    <img id='profileImg2' src=\"/");
      out.print( HardCodeConstants.CONTEXT_PATH );
      out.write("/ProfilePhotoServlet\" width=\"40%\" height=\"40%\">\n");
      out.write("\n");
      out.write("                    ");
      out.print( user.get("nombre") );
      out.print(" ");
      out.print( user.get("apellido") );
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("                    <span class=\" fa fa-angle-down\"></span>\n");
      out.write("                  </a>\n");
      out.write("                  <ul class=\"dropdown-menu dropdown-usermenu pull-right\">\n");
      out.write("                    <li><a href=\"../home/logOut.jsp\"><i class=\"fa fa-sign-out pull-right\"></i>Salir </a></li>\n");
      out.write("\n");
      out.write("                  </ul>\n");
      out.write("                </li>\n");
      out.write("\n");
      out.write("              </ul>\n");
      out.write("            </nav>\n");
      out.write("          </div>\n");
      out.write("        </div>\n");
      out.write("        <!-- /top navigation -->\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");



// ConnectionWrapper.closeAll(rsScreen, stScreen, rsR, stR, connx);



      out.write('\n');
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\t\t\t<div class=\"right_col\" role=\"main\">\n");
      out.write("\t\t\t\t<div class=\"\">\n");
      out.write("\t\t\t\t\t<div class=\"page-title\"></div>\n");
      out.write("\n");
      out.write("\t\t\t\t\t<div class=\"clearfix\"></div>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\t\t\t\t\t<div class=\"col-md-12 col-sm-12 col-xs-12\">\n");
      out.write("\t\t\t\t\t\t<div class=\"x_panel\">\n");
      out.write("\t\t\t\t\t\t\t<div class=\"x_title\">\n");
      out.write("\t\t\t\t\t\t\t\t<p>\n");
      out.write("\t\t\t\t\t\t\t\t\t<strong>");
      out.print( screenConfig.getString("title2") );
      out.write("</strong>\n");
      out.write("\t\t\t\t\t\t\t\t</p>\n");
      out.write("\t\t\t\t\t\t\t\t<ul class=\"nav navbar-right panel_toolbox\">\n");
      out.write("\t\t\t\t\t\t\t\t</ul>\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"clearfix\"></div>\n");
      out.write("\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t<div class=\"x_content\">\n");
      out.write("\t\t\t\t\t\t\t\t<p class=\"text-muted font-13 m-b-30\"></p>\n");
      out.write("\n");
      out.write("\n");
      out.write("\t\t\t\t\t\t\t\t<div align=\"right\">\n");
      out.write("\t\t\t\t\t\t\t\t\t<p class=\"text-muted font-13 m-b-30\">\n");
      out.write("\t\t\t\t\t\t\t\t\t\t");
 if(!screenConfig.get("new_btn_text").equals("")) { 
      out.write("\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<a href=\"");
      out.print( detailPage );
      out.write("?id=0\" type=\"button\" class=\"btn btn-info\">");
      out.print( screenConfig.get("new_btn_text") );
      out.write("</a>\n");
      out.write("\t\t\t\t\t\t\t\t\t\t");
 } 
      out.write("\n");
      out.write("\t\t\t\t\t\t\t\t\t</p>\n");
      out.write("\t\t\t\t\t\t\t\t\t<br>\n");
      out.write("\t\t\t\t\t\t\t\t</div>\n");
      out.write("\n");
      out.write("\t\t\t\t\t\t\t\t");

									String view = screenConfig.get("list_table_view");
									String viewCompSQL = screenConfig.get("list_table_view_complement");
								
									if(viewCompSQL == null) {
										
										viewCompSQL = "";
									}
								
								
									String table = screenConfig.get("table");
									String column_headers = screenConfig.get("column_headers");
									String column_codes = screenConfig.get("column_codes");
								
								
									StringTokenizer column_headersTok = new StringTokenizer(column_headers, "|");
									
								
								
								
      out.write("\n");
      out.write("\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"table-responsive\">  \n");
      out.write("\t\t\t\t\t\t\t\t<table id=\"datatable-buttons\" class=\"table table-striped table-bordered\" width=\"100%\">\n");
      out.write("\t\t\t\t\t\t\t\t\t<thead>\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<tr>\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t");

												while(column_headersTok.hasMoreTokens()) {
											
      out.write("\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<th>");
      out.print( column_headersTok.nextToken() );
      out.write("</th>\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t");

												}
											
      out.write("\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<th>Acciones</th>\n");
      out.write("\t\t\t\t\t\t\t\t\t\t</tr>\n");
      out.write("\t\t\t\t\t\t\t\t\t</thead>\n");
      out.write("\n");
      out.write("\t\t\t\t\t\t\t\t\t<tbody>\n");
      out.write("\t\t\t\t\t\t\t\t\t\t");


											ArrayList<Record> rowRecord = DataArray.getArrayList("SELECT * FROM " + view + " WHERE id_status = 1 " + viewCompSQL);

											for (Record record : rowRecord) {
												
												StringTokenizer column_codesTok = new StringTokenizer(column_codes, "|");
														
										
      out.write("\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<tr>\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t");

												while(column_codesTok.hasMoreTokens()) {
											
      out.write("\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<td>");
      out.print( record.get(column_codesTok.nextToken()) );
      out.write("</td>\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t");

												}
											
      out.write("\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<td align=\"center\"> \n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t");
 if(!screenConfig.get("navigate_btn_text").equals("")) { 
      out.write("\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"");
      out.print( screenConfig.get("navigate_url").replace("KEY", record.getId()) );
      out.write("\" \n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t  class=\"btn btn-primary\">\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<i class=\"fa fa-search\">\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</i> \n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
      out.print( screenConfig.get("navigate_btn_text") );
      out.write("\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t</a>\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t");
 } 
      out.write("\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t");
 if(!screenConfig.get("edit_btn_text").equals("")) { 
      out.write("\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"");
      out.print( detailPage );
      out.write("?id=");
      out.print( record.getId() );
      out.write("\"  class=\"btn btn-info\" alt=\"Editar\">\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<i class=\"fa fa-pencil\">\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</i> \n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
      out.print( screenConfig.get("edit_btn_text") );
      out.write("\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t</a>\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t");
 } 
      out.write("\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t");
 if(!screenConfig.get("delete_btn_text").equals("")) { 
      out.write("\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"javascript:void(0)\" \n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tonclick=\"eliminarEntidad(");
      out.print(entityCode );
      out.write(',');
      out.print(record.getId() );
      out.write(")\"\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t  class=\"btn btn-danger\">\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<i class=\"fa fa-close\">\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</i> \n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
      out.print( screenConfig.get("delete_btn_text") );
      out.write("\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t</a>\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t");
 } 
      out.write("\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t</td>\n");
      out.write("\t\t\t\t\t\t\t\t\t\t</tr>\n");
      out.write("\n");
      out.write("\t\t\t\t\t\t\t\t\t\t");

											}
										
      out.write("\n");
      out.write("\t\t\t\t\t\t\t\t\t</tbody>\n");
      out.write("\t\t\t\t\t\t\t\t</table>\n");
      out.write("\t\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t</div>\n");
      out.write("\n");
      out.write("\t\t\t<footer>\n");
      out.write("\t\t\t<div class=\"pull-right\">\n");
      out.write("\t\t\t\t\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t\t<div class=\"clearfix\"></div>\n");
      out.write("\t\t\t</footer>\n");
      out.write("\t\t\t<!-- /footer content -->\n");
      out.write("\t\t</div>\n");
      out.write("\t</div>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\t<!-- jQuery -->\n");
      out.write("\t<script src=\"../home/vendors/jquery/dist/jquery.min.js\"></script>\n");
      out.write("\t<!-- Bootstrap -->\n");
      out.write("\t<script src=\"../home/vendors/bootstrap/dist/js/bootstrap.min.js\"></script>\n");
      out.write("\t<!-- FastClick -->\n");
      out.write("\t<script src=\"../home/vendors/fastclick/lib/fastclick.js\"></script>\n");
      out.write("\t<!-- NProgress -->\n");
      out.write("\t<script src=\"../home/vendors/nprogress/nprogress.js\"></script>\n");
      out.write("\t<!-- iCheck -->\n");
      out.write("\t");
      out.write("\n");
      out.write("\t<!-- Datatables -->\n");
      out.write("\t<script\n");
      out.write("\t\tsrc=\"../home/vendors/datatables.net/js/jquery.dataTables.custom.js\"></script>\n");
      out.write("\t<script\n");
      out.write("\t\tsrc=\"../home/vendors/datatables.net-bs/js/dataTables.bootstrap.min.js\"></script>\n");
      out.write("\t<script\n");
      out.write("\t\tsrc=\"../home/vendors/datatables.net-buttons/js/dataTables.buttons.min.js\"></script>\n");
      out.write("\t<script\n");
      out.write("\t\tsrc=\"../home/vendors/datatables.net-buttons-bs/js/buttons.bootstrap.min.js\"></script>\n");
      out.write("\t<script\n");
      out.write("\t\tsrc=\"../home/vendors/datatables.net-buttons/js/buttons.flash.min.js\"></script>\n");
      out.write("\t<script\n");
      out.write("\t\tsrc=\"../home/vendors/datatables.net-buttons/js/buttons.html5.min.js\"></script>\n");
      out.write("\t<script\n");
      out.write("\t\tsrc=\"../home/vendors/datatables.net-buttons/js/buttons.print.min.js\"></script>\n");
      out.write("\t<script\n");
      out.write("\t\tsrc=\"../home/vendors/datatables.net-fixedheader/js/dataTables.fixedHeader.min.js\"></script>\n");
      out.write("\t<script\n");
      out.write("\t\tsrc=\"../home/vendors/datatables.net-keytable/js/dataTables.keyTable.min.js\"></script>\n");
      out.write("\t<script\n");
      out.write("\t\tsrc=\"../home/vendors/datatables.net-responsive/js/dataTables.responsive.min.js\"></script>\n");
      out.write("\t<script\n");
      out.write("\t\tsrc=\"../home/vendors/datatables.net-responsive-bs/js/responsive.bootstrap.js\"></script>\n");
      out.write("\t<script\n");
      out.write("\t\tsrc=\"../home/vendors/datatables.net-scroller/js/dataTables.scroller.min.js\"></script>\n");
      out.write("\t<script src=\"../home/vendors/jszip/dist/jszip.min.js\"></script>\n");
      out.write("\t<script src=\"../home/vendors/pdfmake/build/pdfmake.min.js\"></script>\n");
      out.write("\t<script src=\"../home/vendors/pdfmake/build/vfs_fonts.js\"></script>\n");
      out.write("\n");
      out.write("\t<!-- Custom Theme Scripts -->\n");
      out.write("\t<script src=\"build/js/custom.min.js\"></script>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\t<script src=\"../home/vendors/sweet_alert/sweetalert.min.js\"></script>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\t<script type=\"text/javascript\">\n");
      out.write("\t\n");
      out.write("\t\t");
 if(message != null && !message.equals("")) { 
      out.write("\n");
      out.write("\t\tswal(\"Mensaje:\", \"");
      out.print( message );
      out.write("\", \"success\");\n");
      out.write("\t\t");
 }
      out.write("\n");
      out.write("\t\t\n");
      out.write("\t</script>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\t<!-- Datatables -->\n");
      out.write("\t<script>\n");
      out.write("\t\tfunction eliminarEntidad(code, id){\n");
      out.write("\t\t\t\n");
      out.write("\t\t\t\n");
      out.write("\t\t\t swal({\n");
      out.write("\t\t\t\t    title: \"Está seguro?\",\n");
      out.write("\t\t\t\t    type: \"warning\",\n");
      out.write("\t\t\t\t    showCancelButton: true,\n");
      out.write("\t\t\t\t    confirmButtonColor: '#DD6B55',\n");
      out.write("\t\t\t\t    confirmButtonText: 'Sí, continuar',\n");
      out.write("\t\t\t\t    cancelButtonText: \"No, cancelar\",\n");
      out.write("\t\t\t\t    closeOnConfirm: true,\n");
      out.write("\t\t\t\t    closeOnCancel: true\n");
      out.write("\t\t\t\t },\n");
      out.write("\t\t\t\t function(isConfirm){\n");
      out.write("\t\t\t\t\tif(isConfirm)\n");
      out.write("\t\t\t\t\t window.location= \"adm_entity_list.jsp?ec=\"+code+\"&idDeleteRow=\" + id;\n");
      out.write("\t\t\t\t });\n");
      out.write("\t\t\t\n");
      out.write("\t\t\t\n");
      out.write("\t\t}\n");
      out.write("\t\t$(document).ready(function() {\n");
      out.write("\t\t\t\n");
      out.write("\t\t\t\n");
      out.write("\t\t\tvar handleDataTableButtons = function() {\n");
      out.write("\t\t\t\tif ($(\"#datatable-buttons\").length) {\n");
      out.write("\t\t\t\t\t$(\"#datatable-buttons\").DataTable({\n");
      out.write("\t\t\t\t\t\tdom : '<\"top\"Bflp>rt<\"bottom\"i>p<\"clear\">',  //\"Bfrtip\",\n");
      out.write("\t\t\t\t\t\tbuttons : [ {\n");
      out.write("\t\t\t\t\t\t\textend : \"copy\",\n");
      out.write("\t\t\t\t\t\t\tclassName : \"btn-sm\",\n");
      out.write("\t\t\t\t\t\t\ttext : \"Copiar\"\n");
      out.write("\t\t\t\t\t\t}, {\n");
      out.write("\t\t\t\t\t\t\textend : \"csv\",\n");
      out.write("\t\t\t\t\t\t\tclassName : \"btn-sm\",\n");
      out.write("\t\t\t\t\t\t\ttext : \"Descargar CSV\"\n");
      out.write("\t\t\t\t\t\t}, {\n");
      out.write("\t\t\t\t\t\t\textend : \"excel\",\n");
      out.write("\t\t\t\t\t\t\tclassName : \"btn-sm\",\n");
      out.write("\t\t\t\t\t\t\ttext : \"Descargar Excel\"\n");
      out.write("\t\t\t\t\t\t}, {\n");
      out.write("\t\t\t\t\t\t\textend : \"pdfHtml5\",\n");
      out.write("\t\t\t\t\t\t\tclassName : \"btn-sm\"\n");
      out.write("\t\t\t\t\t\t}\n");
      out.write("\t\t\t\t\t\t/*,\n");
      out.write("\t\t\t\t\t\t{\n");
      out.write("\t\t\t\t\t\t  extend: \"print\",\n");
      out.write("\t\t\t\t\t\t  className: \"btn-sm\"\n");
      out.write("\t\t\t\t\t\t},*/\n");
      out.write("\t\t\t\t\t\t],\n");
      out.write("\t\t\t\t\t\tresponsive : true\n");
      out.write("\t\t\t\t\t});\n");
      out.write("\t\t\t\t}\n");
      out.write("\t\t\t};\n");
      out.write("\n");
      out.write("\t\t\tTableManageButtons = function() {\n");
      out.write("\t\t\t\t\"use strict\";\n");
      out.write("\t\t\t\treturn {\n");
      out.write("\t\t\t\t\tinit : function() {\n");
      out.write("\t\t\t\t\t\thandleDataTableButtons();\n");
      out.write("\t\t\t\t\t}\n");
      out.write("\t\t\t\t};\n");
      out.write("\t\t\t}();\n");
      out.write("\n");
      out.write("\t\t\t$('#datatable').dataTable();\n");
      out.write("\n");
      out.write("\t\t\t$('#datatable-keytable').DataTable({\n");
      out.write("\t\t\t\tkeys : true\n");
      out.write("\t\t\t});\n");
      out.write("\n");
      out.write("\t\t\t$('#datatable-responsive').DataTable();\n");
      out.write("\n");
      out.write("\t\t\t$('#datatable-scroller').DataTable({\n");
      out.write("\t\t\t\tajax : \"js/datatables/json/scroller-demo.json\",\n");
      out.write("\t\t\t\tdeferRender : true,\n");
      out.write("\t\t\t\tscrollY : 380,\n");
      out.write("\t\t\t\tscrollCollapse : true,\n");
      out.write("\t\t\t\tscroller : true\n");
      out.write("\t\t\t});\n");
      out.write("\n");
      out.write("\t\t\t$('#datatable-fixed-header').DataTable({\n");
      out.write("\t\t\t\tfixedHeader : true\n");
      out.write("\t\t\t});\n");
      out.write("\n");
      out.write("\t\t\tvar $datatable = $('#datatable-checkbox');\n");
      out.write("\n");
      out.write("\t\t\t\n");
      out.write("\t\t\t// < % // if(viewCompSQL == null) { % >\n");
      out.write("\t\t\t$datatable.dataTable({\n");
      out.write("\t\t\t\t'order' : [ [ 1, 'asc' ] ], \n");
      out.write("\t\t\t\t'columnDefs' : [ {\n");
      out.write("\t\t\t\t\torderable : false,\n");
      out.write("\t\t\t\t\ttargets : [ 0 ]\n");
      out.write("\t\t\t\t} ]\n");
      out.write("\t\t\t\t// < % // } % >\n");
      out.write("\t\t\t});\n");
      out.write("\t\t\t$datatable.on('draw.dt', function() {\n");
      out.write("\t\t\t\t$('input').iCheck({\n");
      out.write("\t\t\t\t\tcheckboxClass : 'icheckbox_flat-green'\n");
      out.write("\t\t\t\t});\n");
      out.write("\t\t\t});\n");
      out.write("\n");
      out.write("\t\t\tTableManageButtons.init();\n");
      out.write("\t\t});\n");
      out.write("\t</script>\n");
      out.write("\t<!-- /Datatables -->\n");
      out.write("</body>\n");
      out.write("</html>\n");

	} else {
			response.sendRedirect("../home/login.jsp");
		}
	} catch (Exception _e) {
		System.out.println("Error" + _e);
		_e.printStackTrace();
	}

    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
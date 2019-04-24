<%@page import="com.movemini.util.StringUtils"%>
<%@page import="com.movemini.flexreports.ReportGenericForm"%>
<%
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
	response.setHeader("Pragma","no-cache"); //HTTP 1.0
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<%@page import="com.movemini.layers.session.SessionBean"%>
<%@page import="com.movemini.simpleflexgrid.components.SelectList"%>
<%@page import="com.movemini.data.OneRecordFactory"%>
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
	boolean access = true;

	String id_report = request.getParameter("id_report");

	Record report = OneRecordFactory.getFirstRecord("select * from flex_report where id_report = " + id_report);

	ArrayList<Record> params = DataArray.getArrayList("select * from flex_report_param where id_report = " + id_report);

	ReportGenericForm formBean = new ReportGenericForm(request);



    if(access
//     		session.getAttribute("ID_USER") != null
    		) {




%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>SAO - Reporte: <%= report.get("name") %></title>

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


    <link href="../home/vendors/sweet_alert/sweetalert.css" rel="stylesheet">
    <link href="../home/vendors/DateJS/prettify.css" rel="stylesheet" />
    <link href="../home/vendors/bootstrap-wysiwyg/css/style.css" rel="stylesheet">

     <%

                    	String message = "";

//                     	if(request.getParameter("idEventoChangeStatus") != null) {


//                     		String xid = request.getParameter("idEventoChangeStatus");
//                     		String xidStat = request.getParameter("idStatus");


//                     		ConnectionWrapper.staticExecuteUpdate("UPDATE evento SET id_evento_status = '" + xidStat + "' WHERE id_evento = '" + xid + "'");


//                     		message = "Se cambio el estatus del evento";
//                     	}


                    %>

    <script type="text/javascript">


    function consultarInfo() {

    	//alert("1");

    	 <%
    	 	
	    	 StringBuilder sb = new StringBuilder();
			
			
    	 sb.append("id_report" + " : " + id_report + ",");

	    	 for(Record param : params) {

				String code = param.get("code");

				sb.append(code + " : $(\"#" + code + "\").val(),");
			}

			String strParams = StringUtils.removeLastChar(sb.toString());

		%>



    	//alert();

     	$.ajax({
     		type:'POST',
     		url :"/<%= HardCodeConstants.CONTEXT_PATH %>/ReportGenericTableServlet",
     		data: {
     			<%= strParams %>
     		},
     		success: function(data) {

     			//alert(data);

     			$("#divReportTable").empty();
     			$("#divReportTable").append(data);

     			$("#divReportTable").hide();
     			$("#divReportTable").show();
     			//$("#divReportTable").toggle(200);



     		},
     		error:function(exception){console.log('Exeption:'+exception); /*logSystem*/ swal(  'Error',  'Proceso no completado',  'error');}
     	});

     }

	function generaReporteAgendaExcel(){

		document.getElementById("formReport").submit();
	
	}



      function exportar() {

  		var paramsGetString = "id_report=<%=id_report%>&";

      	 <%
  	    	 //StringBuilder sb2 = new StringBuilder();

  	    	 for(Record param : params) {

  				String code = param.get("code");

  				//sb2.append(code + " : $(\"#" + code + "\").val(),");

  				%>
  					paramsGetString += "<%= code %>=" + $("#<%= code %>").val() + "&";
  				<%

  			}
	%>

	//alert("/<%= HardCodeConstants.CONTEXT_PATH %>/ReportGenericCSVServlet?" + paramsGetString);

  		window.location = "/<%= HardCodeConstants.CONTEXT_PATH %>/ReportGenericCSVServlet?" + paramsGetString;



       }

    </script>

  </head>
  <body class="nav-md">

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
                    <p><strong>Reporte: <%= report.get("name") %></strong> </p>
                    <ul class="nav navbar-right panel_toolbox">
                    </ul>
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                    <p class="text-muted font-13 m-b-30">

                   <%= report.get("description") %>


                    </p>


                    <div class="x_panel">
							<form id="formReport" name="formReport" action="<%=request.getContextPath()%>/ReportAgendaExcel" method="get">

								<div class="x_content">


									<div class="col-md-8 col-sm-8 col-xs-8">


										<%= formBean.getFormHtml()%>




									</div>
									<div class="col-md-6 col-sm-6 col-xs-12" >
									</div>
									<div class="col-md-6 col-sm-6 col-xs-12" >
												
										<div align="center" style="vertical-align: middle;">
										<%
											//Argumel 30/05/2018
											if(id_report.equals("11")){
										%>
										<input type="button" class="btn btn-info" value="Consultar" onclick="javascript:generaReporteAgendaExcel()"/>
												
										<%
											}else{
										%>
											<a type="button" class="btn btn-info" onclick="consultarInfo()">
												Consultar
											</a>
										<%
											}
										%>	

											<a type="button" class="btn btn-primary" onclick="exportar()">
												Exportar
											</a>
										</div>
									</div>




								</div>
								</form>
							</div>





                    <div align="left">

                    <div id="divReportTable" style="overflow:auto;">
                      </div>


                  	<p class="text-muted font-13 m-b-30">




<%--                       <input type="hidden" name="id_report" value="<%= id_report %>"> --%>
<%--                       <% --%>
<!-- //                       for(Record param : params) { -->
<%--                       %> --%>
<%--                       	<input name="<%= param.get("code") %>"> --%>
<%--                       <% --%>
<!-- //                       } -->
<%--                       %> --%>
<!--                       <input type="submit"> -->

                    </p>
                    <br>
                    </div>





                  </div>
                </div>
              </div>


     </div>
    </div>


               <footer>
          <div class="pull-right">
<%--             Perfil: <%=Role %>  &Uacute;ltima Conexi&oacute;n: <%=dtaUser[9] %> --%>
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
    <script src="../home/build/js/custom.min.js"></script>

<%@include file="adm_eventos_list_js.jsp" %>
	<script src="../home/vendors/sweet_alert/sweetalert.min.js"></script>
	<script src="../home/vendors/jquery.hotkeys/jquery.hotkeys.js"></script>
	<script src="../home/vendors/DateJS/prettify.js"></script>
	<script src="../home/vendors/bootstrap-wysiwyg/src/bootstrap-wysiwyg.js"></script>

	<script type="text/javascript">

		<% if(message != null && !message.equals("")) { %>
		swal("Mensaje:", "<%= message %>", "success");
		<% }%>

	</script>




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

        $('#editorCliente').wysiwyg();
        $('#editorOmni').wysiwyg();

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

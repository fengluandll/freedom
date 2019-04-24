

<%
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
	response.setHeader("Pragma","no-cache"); //HTTP 1.0
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>

<%@page import="com.movemini.simpleajaxservlet.interprete.InterpretesAsignadosTable"%>
<%@page import="com.movemini.util.StringUtils"%>
<%@page import="com.movemini.util.TextFormatter"%>
<%@page import="com.movemini.util.NumberFormatter"%>
<%@page import="com.movemini.data.DataArray"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.movemini.util.DateUtils"%>
<%@page import="com.movemini.data.Record"%>
<%@page import="com.movemini.data.OneValueFactory"%>

<%@page import="com.movemini.data.OneRecordFactory"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%><%@ page import="java.sql.*" %><%@ page import="java.io.*" %>

<%
try{

if(
		true
		//ID_EMPRESA > 0 && ID_EMPLEADO > 0
		){


	String id_evento_factura = request.getParameter("ID_DETALLE");



	ArrayList<Record> campos = DataArray.getArrayList("select * from factura_detalle where id_evento_factura = " + id_evento_factura);





%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

		<title>Detalles Facturacion</title>



        <style type="text/css">
        	#menu{
			position: fixed;
			top:0px;
			width:600px;
			}

			body,td,th,p,center {
				font-family:Verdana, Arial, Helvetica, sans-serif;
				font-size: 14px;
			}



        </style>
</head>
<body>


<table id="divPrincipalFactura" width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>

    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="50%">

		</td>
        <td align="right">

        <img src="/SAO/img/Logo-Omnilingua-Smaller.png">


<!--         t. 5202 5515<br /> -->
<!--           omnilingua@gmail.com -->

         </td>
      </tr>
    </table></td>
  </tr>
  <tr>
  <td>
  			<hr>
  </td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="100%"><p>
						<H1>Detalles Facturaci&oacute;n</H1>

						<br />
        </p>
          <table width="100%" border="0" cellspacing="3" cellpadding="3">

						<% for (Record campo : campos){%>
							<tr>
	              <td align="right"><h5><b><%= campo.get("campo")%>:</b></h5></td>
	              <td>&nbsp;</td>
	              <td><%= campo.get("valor") %></td>

								</tr>
						<%}%>




          </table>

          <DIV align="center">
          <H3>

          	&iexcl;Gracias por su apoyo!
          </H3>
          </DIV>

          </td>
        <td align="right" valign="top">


          </td>
      </tr>
    </table></td>
  </tr>

  <tr>
    <td>&nbsp;</td>
  </tr>

    <tr>
    <td>&nbsp;</td>
  </tr>

</table>

</body>
<%
//session.removeAttribute("ID_EMPLEADO");
//session.removeAttribute("ID_EMPRESA");
} else {
	out.println("NO Existe una Sesion ");
	response.sendRedirect("registro.jsp");
}

}catch(Exception _e){
	out.println("NO Existe una Sesion ");
	response.sendRedirect("registro.jsp");
	_e.printStackTrace();
}
%>
</html>

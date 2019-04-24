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
<%@page import="com.movemini.util.NumberFormatter"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%><%@ page import="java.sql.*" %><%@ page import="java.io.*" %>

<%
try{

if(
		true
		//ID_EMPRESA > 0 && ID_EMPLEADO > 0
		){


	String id_evento_version = request.getParameter("ID_EVENTO");

	String id_evento = OneValueFactory.get("select id_evento from evento_version where id_evento_version = "+ id_evento_version);

	String id_factura = request.getParameter("idFactura");


	Record evento = OneRecordFactory.getPr("evento_select_by_id_pr",id_evento_version);
	System.out.println("id" + id_evento_version);
	System.out.println("Evento" + evento);
    Record factura = OneRecordFactory.getFirstRecord("select * from evento_factura where id_factura = " +id_factura);
	Record crm_rz = OneRecordFactory.getFirstRecord("select * from crm_razon_social where id_razon_social=" + factura.get("id_razon"));
	System.out.println("Razon" +crm_rz);
    System.out.println("Factura"+factura);
    ArrayList<Record> montos = DataArray.getArrayList("select * from factura_monto where id_factura = " + id_factura);
		ArrayList<Record> campos = DataArray.getArrayList("select * from factura_detalle where id_evento_factura = " + id_factura);

    double sum = 0;

	if(evento != null){


%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Factura</title>
  </head>
  <body>
    <table id="divPrincipalFactura" width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td>
          <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td align="right">
                <img src="/SAO/img/Logo-Omnilingua-Smaller.png">
                <!--         t. 5202 5515/85<br /> -->
                <!--           omnilingua@gmail.com -->
              </td>
            </tr>
          </table>
        </td>
      </tr>
      <tr>
        <td colspan="2" align="left">
          <h3>I. Informaci&oacute;n General</h3>
          <table style="width:100%">
            <tr>
              <td width="10%" align="right">No.:</td>
              <td width="50%" style="border-bottom: 1px solid black">&nbsp;<%=evento.get("clave_cotizacion")%></td>
            </tr>
            <tr>
              <td width="10%" align="right">Compa&ntilde;&iacute;a:</td>
              <td width="50%" style="border-bottom: 1px solid black">&nbsp;<%=evento.get("nombre_cliente")%></td>
            </tr>
            <tr>
              <td width="10%" align="right">Evento:</td>
              <td width="50%" style="border-bottom: 1px solid black">&nbsp;<%=evento.get("nombre_evento")%></td>
            </tr>
            <tr>
              <td width="10%" align="right">Contacto:</td>
              <td width="50%" style="border-bottom: 1px solid black">&nbsp;<%=evento.get("nombre_contacto")%></td>
            </tr>
            <tr>
              <td width="10%" align="right">Tel&eacute;fonos:</td>
              <td width="50%" style="border-bottom: 1px solid black">&nbsp;<%=evento.get("telefono")%></td>
            </tr>
            <tr>
              <td width="10%" align="right">E-mail:</td>
              <td width="50%" style="border-bottom: 1px solid black">&nbsp;<%=evento.get("correo")%></td>
            </tr>
            <tr>
              <td width="10%" align="right">Fecha:</td>
              <td width="50%" style="border-bottom: 1px solid black">&nbsp;<%=evento.get("CONCAT_FECHA")%></td>
            </tr>
            <tr>
              <td width="10%" align="right">Lugar:</td>
              <td width="50%" style="border-bottom: 1px solid black">&nbsp;<%=evento.get("lugar")%></td>
            </tr>
            <tr>
              <td width="10%" align="right">Sede:</td>
              <td width="50%" style="border-bottom: 1px solid black">&nbsp;<%=evento.get("sede")%></td>
            </tr>
          </table>
        </td>
      </tr>
      <tr>
      <td>
        <h3>II. Por Facturar</h3>
        <table style="width:100%">
        <% for (Record monto: montos){
						if(monto.get("cantidad") != null)
						try{
	            	sum += Double.parseDouble(monto.get("cantidad"));
							}catch(Exception e ){
								sum +=0;
							}
            %>
          <tr>
            <td><%=monto.get("descripcion")%></td>
            <td>$ <%= NumberFormatter.conComas(monto.get("cantidad"))%></td>
            <td></td>
          </tr>
          <% } %>
        </table>
        </td>
      </tr>
      <tr>
        <table style="width:100%">
          <tr>
            <td width="70%"></td>
            <td width="30%">
              <table>
                <tr style="font-weight:bold">
                  <td align="right" width="40%">Subtotal:</td>
                  <td width="60%" style="border-bottom:1px solid black">&nbsp;$ <%= NumberFormatter.conComas(sum)%></td>
                </tr>
                <tr>
                  <td align="right" width="40%">16% IVA:</td>
                  <td width="60%" style="border-bottom: 1px solid black">&nbsp;$ <%=NumberFormatter.conComas(sum*0.16)%></td>
                </tr>
                <tr style="font-weight:bold">
                  <td align="right" width="40%">Total:</td>
                  <td width="60%" style="border-bottom:1px solid black">&nbsp;$ <%=NumberFormatter.conComas(sum*1.16)%></td>
                </tr>
              </table>
            </td>
          </tr>
        </table>
      </tr>
      <tr>
        <td>
          <h3>III. Datos de facturaci&oacute;n</h3>
          <table style="width:100%">

            <tr>
                <td>
                    &nbsp;
                </td>
            </tr>
						<% if(crm_rz != null){%>
						<tr>

						<td align="right"><b>RFC :</b></td>
						<td>&nbsp;</td>
						<td><%= crm_rz.get("rfc") %></td>
						</tr>
						<tr>
						<td align="right"><b>Raz&oacute;n Social:</b></td>
						<td>&nbsp;</td>
						<td><%= crm_rz.get("razon_social") %></td>
						</tr>
						<tr>
						<td align="right"><b>Direcci&oacute;n:</b></td>
						<td>&nbsp;</td>
						<td><%= crm_rz.get("domicilio") %></td>
						</tr>
						<%}%>
						<%  if(false) for (int i = 0; i< campos.size(); i++){
							if((i + 1) == 1){
								out.println("<tr>");
							}
							Record campo = campos.get(i);
							%>
	              <td align="right"><b><%= campo.get("campo")%>:</b></td>
	              <td>&nbsp;</td>
	              <td><%= campo.get("valor") %></td>

						<%
						if( (i + 1 ) % 2 == 0){
							 if (i == (campos.size() - 1)){
									out.println("</tr>");
								}else{
									out.println("</tr>");
									out.println("<tr>");
								}
						}
					}%>




            <tr>
                <td colspan="6">
                    <h3>Observaciones</h3>

                    <table style="width:100%">
                        <tr>
                           <td style="border-bottom: 1px solid black">&nbsp;<%=factura.get("observaciones")%></td>
                        </tr>
                    </table>
                </td>
            </tr>

          </table>
        </td>
      </tr>

        <tr>
            <td>&nbsp;</td>
        </tr>
        <tr>
            <td>&nbsp;</td>
        </tr>
    </table>
  </body>
</html>

<%
}
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

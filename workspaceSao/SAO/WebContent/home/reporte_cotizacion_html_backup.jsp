<%@page import="com.movemini.reports.ReporteCotizacionRules"%>
<%
	response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", 0); //prevents caching at the proxy server
%>
<%@page import="com.movemini.config.HardCodeConstants"%>
<%@page import="com.movemini.data.ConnectionWrapper"%>

<%@page import="com.movemini.util.NumbersUtil"%>
<%@page import="com.movemini.reports.CotizacionReportUtil"%>
<%@page import="com.movemini.util.Convert"%>
<%@page import="com.movemini.data.OneValueFactory"%>

<%@page import="com.movemini.util.NumberFormatter"%>
<%@page import="com.movemini.data.ConnectionCounter"%>
<%@page import="com.movemini.layers.session.SessionBean"%>
<%@page import="com.movemini.data.DataArray"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.movemini.util.DateUtils"%>
<%@page import="com.movemini.data.Record"%>
<%@page import="com.movemini.data.OneRecordFactory"%>
<%@page import="com.movemini.data.OneValueFactory"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.io.*"%>
<%
	double subtotalTraduccionEscrita = 0;
	Record user = SessionBean.getInstance(request).getUser();
	String nombreUsuario = "";
	String apellidosUsuario ="";
	
	
	
	//int_id_evento_version
	
	
  String id_evento = request.getParameter("ID_EVENTO");
	String id_pdf = request.getParameter("ID_PDF");

	if(user != null){
		apellidosUsuario = user.getString("apellido");
		nombreUsuario = user.getString("nombre");
	}
	if(id_pdf != null){
		Record nuevo = OneRecordFactory.getPr("select_cotizacion_pdf_pr", id_pdf);
		if(nuevo != null && nuevo.get("valid").equals("1")){

			 id_evento =nuevo.get("id_evento_version");

			 nombreUsuario = nuevo.get("nombre_usuario");
			 apellidosUsuario =  nuevo.get("apellido_usuario");
		}else{
				id_evento = "-1";
				nombreUsuario  ="";
				apellidosUsuario = "";
		}
	}


	try {

		// 	System.out.println("FIN_PDF");

		// Class.forName("com.mysql.jdbc.Driver");
		// String url=com.movemini.config.HardCodeConstants.JDBC_URL;
		// String username=com.movemini.config.HardCodeConstants.JDBC_USER;
		// String password=com.movemini.config.HardCodeConstants.JDBC_PASSWORD;
		// Connection conn=DriverManager.getConnection(url,username,password);

		// System.out.println("FIN_PDF - Debug Point 1");

		// int ID_EMPLEADO = Integer.parseInt(session.getAttribute("ID_EMPLEADO").toString());
		// int ID_EMPRESA = Integer.parseInt(session.getAttribute("ID_EMPRESA").toString());

		// System.out.println("ID_EMPLEADO:" + ID_EMPLEADO);
		// System.out.println("ID_EMPRESA:" + ID_EMPRESA);

		// System.out.println("FIN_PDF - Debug Point 2");


		Record evento = OneRecordFactory.getPr("evento_select_by_id_pr", id_evento);

		if (evento != null

		) {

			//System.out.println("FIN_PDF - Debug Point 3");



			ArrayList<Record> politicas = DataArray.getArrayList("evento_politica_select_pr", id_evento);

			ArrayList<Record> condiciones = DataArray.getArrayList("evento_condicion_select_pr", id_evento);

			ArrayList<Record> observaciones = DataArray.getArrayList("evento_observacion_select_pr", id_evento);

			ArrayList<Record> viaticos = DataArray.getArrayList("evento_viatico_select_pr", id_evento);

			//String id_moneda = evento.getString("id_tipo_cotizacion");
			String status = OneValueFactory.get("SELECT id_evento_status FROM evento WHERE id_evento =(select id_evento from evento_version where id_evento_version = " + id_evento +" limit 1)");

			String id_idioma_cotizacion = evento.getString("id_idioma_cotizacion");

			String id_tipo_evento = evento.getString("id_tipo_eveto");

			String id_tipo_cotizacion = evento.getString("id_tipo_cotizacion");

			System.out.println(evento.getString(""));
			double dbl_tipo_cambio = 0;
			String tipo_cambio ="";
			String fecha_cambio ="";
			
			if("".equals(evento.getString("precio_moneda"))){
				tipo_cambio = OneValueFactory.get("SELECT tipo_cambio FROM cat_moneda WHERE id_moneda = 2");
				ConnectionWrapper.staticExecuteUpdate("update evento set precio_moneda=" + tipo_cambio + ",fecha_moneda = now() where id_evento =(select id_evento from evento_version where id_evento_version = " + id_evento +" limit 1)");				
						
			}else{
				tipo_cambio = evento.getString("precio_moneda");
				if(!"".equals( evento.getString("fecha_moneda") )){
					fecha_cambio = " ("  + evento.getString("fecha_moneda") + ")";
				}
			}
			dbl_tipo_cambio = Convert.toDouble(tipo_cambio);	
			///////////String sufix = "_mxn";
			boolean english = false;

			//if(id_moneda.equals("2")) {
			if (id_idioma_cotizacion != null && id_idioma_cotizacion.equals("2") ) {
				///////////sufix = "_usd";
				english = true;
			} else {
				///////////sufix = "_mxn";
			}

			double subtotalGeneralCliente = 0;
			double subtotalGeneralEspecial = 0;

			double ivaCliente = 0;
			double ivaEspecial = 0;

			double totalCliente = 0;
			double totalEspecial = 0;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<head>

<title>Cotizaci&oacute;n</title>

<style type="text/css">
#menu {
	position: fixed;
	top: 0px;
	width: 600px;
}

body, td, th, p, center {
	/*font-family: Verdana, Arial, Helvetica, sans-serif;*/
	font-family: Arial;
	font-size: 14px;
}

.smallerText {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 11px;
}
#txtCotizacion td {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 11px;
}

</style>
</head>
<body style="font-family: Verdana, Arial, Helvetica, sans-serif; ">
	<table width="100%" border="0" cellspacing="0" cellpadding="0" style="font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 10px;">
		<tr>
			<td><table width="100%" border="0" cellspacing="0"
					cellpadding="0">
					<tr>
						<td width="50%"><img
							src="/SAO/img/Logo-Omnilingua-Smaller.png"></td>
						<td align="right">t. 5202 5515 <br> 5202 5585<br /> omnilingua@omnilingua.com.mx
						</td>
					</tr>
				</table></td>
		</tr>
		<tr>
			<td><table width="100%" border="0" cellspacing="0"
					cellpadding="0">
					<tr>
						<td width="70%">
							<p>
								<b>I.<% if (english) { %>General Information<% } else { %>Informaci&oacute;n General<% } %>:<br /></b>
							</p>
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="50%">
										<% if (english) { %>Customer<% } else { %>Cliente<% } %>:
									</td>
									<th><%=evento.getString("nombre_cliente") %></th>
								</tr>
								<% if (id_tipo_evento.equals("1")) { %>
									<tr>
										<td>
											<% if (english) {	%>Event<%	} else { %>Evento<% }	%>:
										</td>
										<td><%=evento.getString("nombre_evento")%></td>
									</tr>
								<% } %>
								<tr>
									<td>
										<% if (english) {	%>Contact<%	} else { %>Contacto<%	}	%>:
									</td>
									<td><%=evento.getString("nombre_contacto")%></td>
								</tr>
								<tr>
									<td>
										<% if (english) {	%>Phone<%	} else { %>Tels<%	}	%>:
									</td>
									<td><%=evento.getString("telefono")%></td>
								</tr>
								<tr>
									<td>Email:</td>
									<td><%=evento.getString("correo")%></td>
								</tr>

								<%	if (id_tipo_evento.equals("1")) {		%>
									<tr>
										<td>
											<% if (english) { %>Date<% } else { %>Fecha<% } %>:
										</td>
										<td><%=evento.getString("CONCAT_FECHA")%></td>
									</tr>
								<%	}	%>
								<%	if (id_tipo_evento.equals("1")) {	%>
										<tr>
											<td>
												<% if (english) {	%>Event Location<% } else {	%>Lugar<%	}	%>:
											</td>
											<td><%=evento.getString("lugar")%></td>
										</tr>
										<tr>
											<td>
												<% if (english) {	%>Event Location<% } else {	%>Sede<% } %>:
											</td>
											<td><%=evento.getString("sede")%></td>
										</tr>
										<tr>
											<td>
												<% if (english) { %>Schedule<% } else { %>Horario<% } %>:
											</td>
											<td><%=evento.getString("CONCAT_HORARIO")%></td>
										</tr>
								
								<% } %>
									
<%-- 											<% if (english || id_tipo_cotizacion.equals("4") || id_tipo_cotizacion.equals(HardCodeConstants.ID_A_FUTURO)) { %> --%>
<!-- 												<tr> -->
<!-- 													<td> -->
<%-- 															<%= (english?"Exchange Rate":"Tipo de cambio") %> --%>
<!-- 													</td> -->
<%-- 													<td>$ <%=dbl_tipo_cambio%> MXN  <%=fecha_cambio %></td> --%>
<!-- 												</tr> -->
<%-- 											<% }%> --%>
								
							</table></td>
						<td align="right" valign="top">
							<% if (english) { %>Date<% } else { %>Fecha<% } %>: <% if (english) { %><%=DateUtils.getTodayHumanReadableEng(evento.getString("fecha_creacion"))%>
							<% } else { %><%=DateUtils.getTodayHumanReadable(evento.getString("fecha_creacion"))%>
							<% } %> <br />
							<% 	if (english) {  %>Quotation<% } else { %>Cotizaci&oacute;n<% } %>:
							<b><%=evento.getString("clave_cotizacion")%></b><br>
							<% if (status.equals("8")){ %>
									<b><span style="color:red">CIERRE DE EVENTO</span></b>
							 <% } %>
						</td>
					</tr>
				</table></td>
		</tr>

		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td><b>II. <% if (english) { %>Quotation<% } else { %>Cotizaci&oacute;n<% } %></b></td>
		</tr>
		<tr >
			<td><%
					if (id_tipo_evento.equals("1")) {
								ArrayList<Record> evento_cotizacion_subtotales = DataArray.getArrayList("select id,texto from (	select id,texto from	evento_cotizacion_subtotales ecs inner join evento_sala es on es.id_subtotal = ecs.id where es.id_evento_version ="+id_evento+"   order by id_evento_sala 									) as t				UNION SELECT 0 as id, '' as texto");

								for (Record subtotalRow : evento_cotizacion_subtotales) {
									//ArrayList<Record> headers = DataArray.getArrayList("evento_sala_dia_headers_pr", id_evento);
									String subtotalTexto =  subtotalRow.get("texto");
									ArrayList<Record> headers = DataArray.getArrayList("evento_sala_dia_headers_by_subtotal_pr",id_evento, subtotalRow.get("id"));
									for (Record header : headers) {
										double subtotalGroupEncabezadoPrecioCliente = 0;
										double subtotalGroupEncabezadoPrecioEspecial = 0;
										ArrayList<Record> lineas = DataArray.getArrayList("evento_producto_select_by_dia_pr",	header.getString("id_evento_sala"));
				%><table width="100%" border="0" cellspacing="0" cellpadding="0">
												<tr style="">
													<td>&nbsp;</td>
												</tr>
												<tr style="background-color: #366092; color: white">
													<td style="font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 14px;"><%=header.getString("ENCABEZADO")%></td>
												</tr>
												<tr style="">
													<td>&nbsp;</td>
												</tr>
												<tr style="">
													<td>&nbsp;</td>
												</tr>
												<tr>
													<td>
														<table width="100%" border="0" cellspacing="0" cellpadding="0">
															<tr>
																<td width="10%" style="padding-right: 2cm;"><b><% if (english) { %>Quantity<% } else { %>Cantidad<% } %></b>
																</td>
																<td width="40%" ><b>
																	<% if (english) { %>Description<% } else { %>Descripci&oacute;n<% } %></b>
																</td>

																<% if (ReporteCotizacionRules.mostrarPrecioCliente(evento.getString("id_mostrar_precios"))) { %>
																		<td align="right"><b>
																			<% if (english) { %>Customer Price<% } else { %>Precio Cliente<% } %></b>
																		</td>
																<% } %>
																
																<% if (ReporteCotizacionRules.mostrarPrecioEspecial(evento.getString("id_mostrar_precios"))) { %>
																		<td align="right"><b>
																			<% if (english) { %>Special Price<% } else { %>Precio Especial<% } %></b>
																		</td>
																<% } %>
																		<td></td>
																
															</tr>
															<%
																double subtotalEncabezadoPrecioCliente = 0;
																double subtotalEncabezadoPrecioEspecial = 0;

																for (Record linea : lineas) {

																		double dblCliente = CotizacionReportUtil.getDoubleValue("precio_cliente_descuento",	linea, id_tipo_cotizacion, dbl_tipo_cambio,english);
																		double dblEspecial = CotizacionReportUtil.getDoubleValue("precio_especial_descuento", linea, id_tipo_cotizacion, dbl_tipo_cambio,english);

																		if(!"2".equals( linea.get("cortesia") )  && !"1".equals( linea.get("cortesia") )  ){
																			subtotalEncabezadoPrecioCliente += dblCliente;
																		}
																		if(!"3".equals( linea.get("cortesia") )  && !"1".equals( linea.get("cortesia") ) ){
																			subtotalEncabezadoPrecioEspecial += dblEspecial;
																		}

															%>
																			<tr style="height: 30px">
																				<td><%=linea.getString("cantidad")%></td>
																				<td>
																					<% if (english) { %><%=linea.getString("nombre_ingles")%>
																					<% } else { %><%=linea.getString("nombre_producto")%>
																					<% } %>
																				</td>

																				<% if ("1".equals(evento.getString("id_mostrar_precios"))) { %>

																						<%if ("2".equals( linea.get("cortesia") )  || "1".equals( linea.get("cortesia") )  ) {%>
																							<td align="right" >CORTES&Iacute;A</td>
																						<%} else{%>
																								<td align="right" ><%=CotizacionReportUtil.getValue2("precio_cliente_descuento", linea,id_tipo_cotizacion, dbl_tipo_cambio , english)%></td>
																						<%} if("3".equals( linea.get("cortesia") )  || "1".equals( linea.get("cortesia") )){%>
																								<td align="right" >CORTES&Iacute;A</td>
																						<%} else{%>
																								<td align="right" ><%=CotizacionReportUtil.getValue2("precio_especial_descuento", linea,	id_tipo_cotizacion, dbl_tipo_cambio, english)%></td>
																							<%}%>
																							
																				<% } else if ("2".equals(evento.getString("id_mostrar_precios"))) { %>
																				
																					<%if ("2".equals( linea.get("cortesia") )  || "1".equals( linea.get("cortesia") )  ) {%>
																					
																						<td align="right" >CORTES&Iacute;A</td>
																						
																					<%} else{%>
																					
																							<td align="right" ><%=CotizacionReportUtil.getValue2("precio_cliente_descuento", linea,id_tipo_cotizacion, dbl_tipo_cambio , english)%></td>
																							
																					<%}%>
																				<% } else if ("3".equals(evento.getString("id_mostrar_precios"))) { %>
																					<% if("3".equals( linea.get("cortesia") )  || "1".equals( linea.get("cortesia") )){%>
																							<td align="right" >CORTES&Iacute;A</td>
																					<%} else{%>
																							<td align="right" ><%=CotizacionReportUtil.getValue2("precio_especial_descuento", linea,	id_tipo_cotizacion, dbl_tipo_cambio, english)%></td>
																						<%}%>
																				<% }  %>


																				<td align="right"></td>
																			</tr>
															<%	}
																ArrayList<Record> productosAbierto = DataArray.getArrayList("select * from evento_producto_abierto where id_evento_sala = " + header.getString("id_evento_sala"));

																for(Record productoAbierto : productosAbierto ){
																	double dblClienteA = CotizacionReportUtil.getDoubleValue(productoAbierto.get("precio_cliente"));
																	double dblEspecialA = CotizacionReportUtil.getDoubleValue(productoAbierto.get("precio_especial"));
																	if(!"2".equals( productoAbierto.get("cortesia") )  && !"1".equals( productoAbierto.get("cortesia") )  ){
																		subtotalEncabezadoPrecioCliente += dblClienteA;
																	}
																	if(!"3".equals( productoAbierto.get("cortesia") )  && !"1".equals( productoAbierto.get("cortesia") ) ){
																		subtotalEncabezadoPrecioEspecial += dblEspecialA;
																	}
															%>
															<tr style="height: 30px">
																<td><%= productoAbierto.get("cantidad")%></td>
																<td><%= productoAbierto.get("descripcion")%></td>
																<%	if ("1".equals(evento.getString("id_mostrar_precios"))) { %>
																	<%if ("2".equals( productoAbierto.get("cortesia") )  || "1".equals( productoAbierto.get("cortesia") )  ) {%>
																		<td align="right" >CORTES&Iacute;A</td>
																	<%} else{%>
																			<td align="right" ><%=CotizacionReportUtil.formatoConMoneda2(productoAbierto.get("precio_cliente"),id_tipo_cotizacion, english)%></td>
																	<%} if("3".equals( productoAbierto.get("cortesia") )  || "1".equals( productoAbierto.get("cortesia") )){%>
																			<td align="right" >CORTES&Iacute;A</td>
																	<%} else{%>
																		<td align="right" ><%=CotizacionReportUtil.formatoConMoneda2(productoAbierto.get("precio_especial"),id_tipo_cotizacion, english)%></td>
																		<%}%>
																<% } else if ("2".equals(evento.getString("id_mostrar_precios"))) { %>
																	<%if ("2".equals( productoAbierto.get("cortesia") )  || "1".equals( productoAbierto.get("cortesia") )  ) {%>
																		<td align="right" >CORTES&Iacute;A</td>
																	<%} else{%>
																		<td align="right" ><%=CotizacionReportUtil.formatoConMoneda2(productoAbierto.get("precio_cliente"), id_tipo_cotizacion, english)%></td>
																	<%}%>


																<% } else if ("3".equals(evento.getString("id_mostrar_precios"))) { %>
																	<% if("3".equals( productoAbierto.get("cortesia") )  || "1".equals( productoAbierto.get("cortesia") )){%>
																			<td align="right" >CORTES&Iacute;A</td>
																	<%} else{%>
																		<td align="right" ><%=CotizacionReportUtil.formatoConMoneda2(productoAbierto.get("precio_especial"),id_tipo_cotizacion, english)%></td>
																		<%}%>

																<% } %>
																<td align="right"></td>
															</tr>
															<% } %>
															<tr>
																<td>&nbsp;</td>
																<td></td>
																<td align="right">&nbsp;</td>
															</tr>
															<% if (header.get("texto_subtotal_1") != null && !header.get("texto_subtotal_1").equals("")) { %>
																	<tr>
																		<td>&nbsp;</td>
																		<td  style="padding-right: 2cm;">
																			<%--             <% if(english){ %>One Room Service<% } else { %>Servicio por UNA sala <% } %> --%>
																			<b><%=header.get("texto_subtotal_1")%></b>
																		</td>
																		<% if ("1".equals(evento.getString("id_mostrar_precios"))) { %>
																				<td align="right">&nbsp;<div style="display: inline-block; font-weight: bold;border-top: 1pt solid black;width: auto"><%=CotizacionReportUtil.formatoConMoneda2(subtotalEncabezadoPrecioCliente, id_tipo_cotizacion, english)%></div></td>
																				<td align="right">&nbsp;<div style="display: inline-block; font-weight: bold;border-top: 1pt solid black;width: auto"><%=CotizacionReportUtil.formatoConMoneda2(subtotalEncabezadoPrecioEspecial, id_tipo_cotizacion, english)%></div></td>
																		<% } else if ("2".equals(evento.getString("id_mostrar_precios"))) { %>
																				<td align="right">&nbsp;<div style="display: inline-block; font-weight: bold;border-top: 1pt solid black;width: auto"><%=CotizacionReportUtil.formatoConMoneda2(subtotalEncabezadoPrecioCliente,id_tipo_cotizacion, english)%></div></td>

																			<% } else if ("3".equals(evento.getString("id_mostrar_precios"))) { %>
																			<td align="right">&nbsp;<div style="display: inline-block; font-weight: bold;border-top: 1pt solid black;width: auto"><%=CotizacionReportUtil.formatoConMoneda2(subtotalEncabezadoPrecioEspecial, id_tipo_cotizacion, english)%></div></td>
																		<% } %>
																	</tr>
															<%
														}
																String multiplicar_dias = header.get("multiplicar_dias");
																String multiplicar_salas = header.get("multiplicar_salas");

																					if (multiplicar_dias.equals("1") || multiplicar_salas.equals("1")) {

																						String cantidad_dias = header.get("cantidad_dias");
																						String cantidad_salas = header.get("cantidad_salas");

																						double factor = ((multiplicar_dias.equals("1")) ? Convert.toDouble(cantidad_dias)	: 1)
																							* ((multiplicar_salas.equals("1")) ? Convert.toDouble(cantidad_salas) : 1);

																						
																						if(factor == 0) {
																							
																							factor = 1;
																						}
																						
																						
																						subtotalGroupEncabezadoPrecioCliente += subtotalEncabezadoPrecioCliente * factor;
																						subtotalGroupEncabezadoPrecioEspecial += subtotalEncabezadoPrecioEspecial * factor;


																						subtotalGeneralCliente += subtotalEncabezadoPrecioCliente * factor;
																						subtotalGeneralEspecial += subtotalEncabezadoPrecioEspecial * factor;
															%>
																				<% if (header.get("texto_subtotal_2") != null && !header.get("texto_subtotal_2").equals("")) { %>
																							<tr>
																								<td>&nbsp;</td>
																								<td>
																									<%--             <% if(english){ %> --%> <%--             <%= header.getString("cantidad_dias")  %> days Service --%>
																									<%--             <% } else { %> --%> <%--             Servicio por <%= header.getString("cantidad_dias")  %> d&iacute;as.  --%>
																									<%--             <% } %> --%> <b><%=header.get("texto_subtotal_2")%></b>

																								</td>
																								<% if ("1".equals(evento.getString("id_mostrar_precios"))) { %>
																										<td align="right" >&nbsp;<div style="display: inline-block; font-weight: bold;border-top: 1pt solid black;width: auto"><%=CotizacionReportUtil.formatoConMoneda2( subtotalEncabezadoPrecioCliente * factor, id_tipo_cotizacion, english)%></div> </td>
																										<td align="right" >&nbsp;<div style="display: inline-block; font-weight: bold;border-top: 1pt solid black;width: auto"><%=CotizacionReportUtil.formatoConMoneda2( subtotalEncabezadoPrecioEspecial * factor, id_tipo_cotizacion, english)%></div></td>
																								<% } else if ("2".equals(evento.getString("id_mostrar_precios"))) { %>
																										<td align="right" >&nbsp;<div style="display: inline-block; font-weight: bold;border-top: 1pt solid black;width: auto"><%=CotizacionReportUtil.formatoConMoneda2( subtotalEncabezadoPrecioCliente * factor, id_tipo_cotizacion, english)%></div></td>
																								<% } else if ("3".equals(evento.getString("id_mostrar_precios"))) { %>
																									<td align="right" >&nbsp;<div style="display: inline-block; font-weight: bold;border-top: 1pt solid black;width: auto"><%=CotizacionReportUtil.formatoConMoneda2( subtotalEncabezadoPrecioEspecial * factor, id_tipo_cotizacion, english)%></div></td>
																								<% } %>
																							</tr>

																				<% } %>
															<%
																} else {

																						subtotalGroupEncabezadoPrecioCliente += subtotalEncabezadoPrecioCliente;
																						subtotalGroupEncabezadoPrecioEspecial += subtotalEncabezadoPrecioEspecial;

																						subtotalGeneralCliente += subtotalEncabezadoPrecioCliente;
																						subtotalGeneralEspecial += subtotalEncabezadoPrecioEspecial;
																					}
															%>

														</table>

													</td>
												</tr>
												<tr>
													<td>
														<table width="100%" border="0" cellspacing="0" cellpadding="0">
															<tr>
																<td width="10%">&nbsp;</td>
																<td width="40%">
																	<!--             Servicio por UN d&iacute;a -->
																</td>
																<td align="right">&nbsp;</td>
															</tr>
														</table>
													</td>
												</tr>
											</table> <%
							%> <%

							 				String id = subtotalRow.get("id");
							 				if (!id.equals("0") && subtotalGroupEncabezadoPrecioCliente != 0 && subtotalGroupEncabezadoPrecioEspecial != 0) {

								%>
													<table width="100%" border="0" cellspacing="0" cellpadding="0">
														<tr>
															<td style="padding-right: 2cm;" width="10%"> </td>

															<td width="40%" ><b><%=subtotalTexto%></b></td>

															<% if ("1".equals(evento.getString("id_mostrar_precios"))) { %>
																	<td align="right">&nbsp;<div style="display: inline-block; font-weight: bold;border-top: 1pt solid black;width: auto"><%=CotizacionReportUtil.formatoConMoneda2(subtotalGroupEncabezadoPrecioCliente, id_tipo_cotizacion, english)%></div></td>
																	<td align="right">&nbsp;<div style="display: inline-block; font-weight: bold;border-top: 1pt solid black;width: auto"><%=CotizacionReportUtil.formatoConMoneda2(subtotalGroupEncabezadoPrecioEspecial, id_tipo_cotizacion, english)%></div></td>
															<% } else if ("2".equals(evento.getString("id_mostrar_precios"))) { %>
																	<td align="right">&nbsp;<div style="display: inline-block; font-weight: bold;border-top: 1pt solid black;width: auto"><%=CotizacionReportUtil.formatoConMoneda2(subtotalGroupEncabezadoPrecioCliente, id_tipo_cotizacion, english)%></div></td>
															<% } else if ("3".equals(evento.getString("id_mostrar_precios"))) { %>
																	<td align="right">&nbsp;<div style="display: inline-block; font-weight: bold;border-top: 1pt solid black;width: auto"><%=CotizacionReportUtil.formatoConMoneda2(subtotalGroupEncabezadoPrecioEspecial, id_tipo_cotizacion, english)%></div></td>
															<% }else {} %>
														</tr>
													</table>
											<% }else{} %>
											<%
										}
					}

 			} else {

 				//
 				// TRADUCCION ESCRITA
 				//
 %>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr style="">
						<td>&nbsp;</td>
					</tr>
					<tr style="background-color: #366092; color: white">
						<td><%=evento.getString("nombre_evento")%></td>
					</tr>
					<tr style="">
						<td>&nbsp;</td>
					</tr>
					<tr style="">
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td>	
						
						
						
						
									<table width="100%" border="0" cellspacing="0" cellpadding="0">

								<% ArrayList<Record> headers = DataArray.getArrayList("evento_sala_dia_headers_pr", id_evento);

												for (Record header : headers) {

													ArrayList<Record> lineasTE = DataArray.getArrayList("evento_producto_te_select_by_dia_pr",
															header.getString("id_evento_sala"));
								%> 
											<tr>
												
												<td valign="top" width="100%" align="right">
													<table width="100%" >
														<tr>
															<th width="30%" colspan="2"><%=header.getString("ENCABEZADO").replace("\n", "<br>")%></th>
															<!-- 						<th class="smallerText">Idioma</th> -->
															<!-- 						<th class="smallerText">Servicio</th> -->
															<!-- 						<th class="smallerText">Cobro</th> -->
															<th width="11.66%" style="text-align: center"><% if (english) { %>Quantity<% } else { %>Cantidad<% } %></th>
															<th width="11.66%"><center><% if (english) { %>Delivery<% } else { %>Entrega<% } %>
															
															</center></th>
															
															
															
															
<%-- 																<%if("1".equals(evento.getString("id_mostrar_precios")) || "2".equals(evento.getString("id_mostrar_precios"))){ %> --%>
																		
<%-- 																	<th width="11.66%" style="text-align: center"><% if (english) { %>Unitary price<% } else { %>Costo Unitario<% } %></th> --%>
																						
<%-- 																<% } else{%> --%>
<!-- 																	<th width="11.66%" align="center">&nbsp;</th> -->
<%-- 																<%} %> --%>
																
																
																
<%-- 																<%if("1".equals(evento.getString("id_mostrar_precios")) || "3".equals(evento.getString("id_mostrar_precios"))){ %> --%>
<!-- 																			<th width="11.66%" align="center"><center><b> -->
<%-- 																			<% if (english) { %>Special Unitary price<% } else { %>Costo Unitario Especial<% } %> --%>
<!-- 																			</b></center> -->
<!-- 																		</th>				 -->
<%-- 																<% } else{%> --%>
<!-- 																	<th width="11.66%" align="center">&nbsp;</th> -->
<%-- 																<%} %> --%>
																
																
																
																
																
																
<%-- 																<% if ("1".equals(evento.getString("id_mostrar_precios")) || "2".equals(evento.getString("id_mostrar_precios"))) { %> --%>
																																																		
<!-- 																		<th width="11.66%" align="center"><center><b> -->
<%-- 																		<% if (english) { %>Customer Price<% } else { %>Costo Cliente<% } %></b></center>	 --%>
<!-- 																		</th> -->
																		
<%-- 																<% } else{%> --%>
<!-- 																	<th width="11.66%" align="center">&nbsp;</th> -->
<%-- 																<%} %>  --%>
																
																
																
																
<%-- 																<% if("1".equals(evento.getString("id_mostrar_precios")) || "3".equals(evento.getString("id_mostrar_precios"))){ %> --%>

<!-- 																		<th width="11.66%" align="center"><center><b> -->
<%-- 																			<% if (english) { %>Special Price<% } else { %>Costo Especial<% } %></b></center> --%>
<!-- 																		</th>		 -->
																				
<%-- 																<% } else{%> --%>
<!-- 																	<th width="11.66%" align="center">&nbsp;</th> -->
<%-- 																<%} %> --%>
																
																
																
																
																
																	<%if("1".equals(evento.getString("id_mostrar_precios"))){ %>
																
																		<th width="11.66%" style="text-align: center"><% if (english) { %>Unitary price<% } else { %>Costo Unitario<% } %></th>
																		<th width="11.66%" align="center"><center><b><% if (english) { %>Special Unitary price<% } else { %>Costo Unitario Especial<% } %></b></center></th>	
																		<th width="11.66%" align="center"><center><b><% if (english) { %>Customer Price<% } else { %>Costo Cliente<% } %></b></center></th>
																		<th width="11.66%" align="center"><center><b><% if (english) { %>Special Price<% } else { %>Costo Especial<% } %></b></center></th>
																
																<% } %>
																
																
																
																<%if("2".equals(evento.getString("id_mostrar_precios"))){ %>
																
																		<td width="11.66%" class="" align="center">&nbsp;</td>
																		<td width="11.66%" class="" align="center">&nbsp;</td>
																		<th width="11.66%" style="text-align: center"><% if (english) { %>Unitary price<% } else { %>Costo Unitario<% } %></th>
																		<th width="11.66%" align="center"><center><b><% if (english) { %>Customer Price<% } else { %>Costo Cliente<% } %></b></center></th>
																<% } %>
																
																
																	
																<%if("3".equals(evento.getString("id_mostrar_precios"))){ %>
																
																		<td width="11.66%" class="" align="center">&nbsp;</td>
																		<td width="11.66%" class="" align="center">&nbsp;</td>
																		<th width="11.66%" align="center"><center><b><% if (english) { %>Special Unitary price<% } else { %>Costo Unitario Especial<% } %></b></center></th>
																		<th width="11.66%" align="center"><center><b><% if (english) { %>Special Price<% } else { %>Costo Especial<% } %></b></center></th>
																<% } %>
																
																
																
																
																
																
																
																
														</tr>

														<%

															for (Record linea : lineasTE) {
																double dblEspecial = 0;
																double dblCliente = 0;
																				try {
																					String campo = "";																						
																					
																					if("1".equals(linea.get("id_cobro"))){
																						campo += "palabra";
																					}else{
																						campo += "cuartilla";
																					}
																					
																					if("1".equals(linea.get("id_entrega"))){
																						campo += "_normal";
																					}else{
																						campo += "_urgente";
																					}																				
																					
																					
																					dblEspecial = CotizacionReportUtil.getDoubleValue3(campo+"_especial", linea, id_tipo_cotizacion, dbl_tipo_cambio,english);
																					dblCliente = CotizacionReportUtil.getDoubleValue3(campo, linea, id_tipo_cotizacion, dbl_tipo_cambio,english);
																				
																					if( !"1".equals(linea.get("cortesia") ) ){
																						
																						double dblTraduccion = CotizacionReportUtil.getDoubleValue(linea.get("monto"));
																						
																						
																						
																						if(!"2".equals( linea.get("cortesia") )  && !"1".equals( linea.get("cortesia") )  ){
																							subtotalGeneralCliente += CotizacionReportUtil.getDoubleValue(linea.get("monto"));
																						}
																						if(!"3".equals( linea.get("cortesia") )  && !"1".equals( linea.get("cortesia") ) ){
																							subtotalGeneralEspecial += CotizacionReportUtil.getDoubleValue(linea.get("monto_especial"));
																						}
																						
																						//subtotalGeneralCliente +=  CotizacionReportUtil.getDoubleValue(linea.get("monto"));
																						
																					}
																				} catch (Exception ex) {
																					System.out.println("Monto no Numérico en Traducciósn Escrita");
																				}
														%>
																	<tr>
																		<%-- 						<td class="smallerText" width="20%"><%= linea.get("idioma") %></td> --%>
																		<%-- 						<td class="smallerText" width="20%"><%= linea.get("servicio") %></td> --%>
																		<%-- 						<td class="smallerText" width="20%"><%= linea.get("cobro") %></td> --%>
																		
																		<td width="10%"><b> <%=( "na".equals(linea.get("id_producto"))?linea.get("servicio"):"Traducción" ) %></b></td>
																		<td width="20%"><b> <%=linea.get("idioma" ) %></b></td>
																		<td class="" align="center">
																				<%=linea.get("cantidad") + " " + linea.get("cobro")+ ((!linea.get("cantidad").equals("1") ? "" : ""))%>
																		</td>
																		<td class=""  align="center"><%=linea.get("entrega")%></td>
														
														
														
														
																	
																
																	<%if("1".equals(evento.getString("id_mostrar_precios"))){ %>
																
																		<td  width="11.66%"  class="" align="center"><% if(ReporteCotizacionRules.isCortesiaCliente(linea.get("cortesia"))){ %>CORTES&Iacute;A <% } else { %><%=CotizacionReportUtil.formatoConMoneda2(dblCliente, id_tipo_cotizacion,english)%><% } %></td>
																		<td  width="11.66%"  class="" align="center"><% if(ReporteCotizacionRules.isCortesiaEspecial(linea.get("cortesia"))){ %>CORTES&Iacute;A <% } else { %><%=CotizacionReportUtil.formatoConMoneda2(dblEspecial, id_tipo_cotizacion,english)%><% } %></td>	
																		<td width="11.66%"  class="" align="center"><% if(ReporteCotizacionRules.isCortesiaCliente(linea.get("cortesia"))){ %>CORTES&Iacute;A <% } else { %><%=CotizacionReportUtil.formatoConMoneda2(linea.get("monto"), id_tipo_cotizacion,english)%><% } %></td>
																		<td width="11.66%" class="" align="center"><% if(ReporteCotizacionRules.isCortesiaEspecial(linea.get("cortesia"))){ %>CORTES&Iacute;A <% } else { %><%=CotizacionReportUtil.formatoConMoneda2(linea.get("monto_especial"), id_tipo_cotizacion,english)%><% } %></td>
																
																<% } %>
																
																
																
																<%if("2".equals(evento.getString("id_mostrar_precios"))){ %>
																
																		<td width="11.66%" class="" align="center">&nbsp;</td>
																		<td width="11.66%" class="" align="center">&nbsp;</td>
																		<td  width="11.66%"  class="" align="center"><% if(ReporteCotizacionRules.isCortesiaCliente(linea.get("cortesia"))){ %>CORTES&Iacute;A <% } else { %><%=CotizacionReportUtil.formatoConMoneda2(dblCliente, id_tipo_cotizacion,english)%><% } %></td>
																		<td width="11.66%"  class="" align="center"><% if(ReporteCotizacionRules.isCortesiaCliente(linea.get("cortesia"))){ %>CORTES&Iacute;A <% } else { %><%=CotizacionReportUtil.formatoConMoneda2(linea.get("monto"), id_tipo_cotizacion,english)%><% } %></td>
																<% } %>
																
																
																
																	
																<%if("3".equals(evento.getString("id_mostrar_precios"))){ %>
																
																		<td width="11.66%" class="" align="center">&nbsp;</td>
																		<td width="11.66%" class="" align="center">&nbsp;</td>
																		<td  width="11.66%"  class="" align="center"><% if(ReporteCotizacionRules.isCortesiaEspecial(linea.get("cortesia"))){ %>CORTES&Iacute;A <% } else { %><%=CotizacionReportUtil.formatoConMoneda2(dblEspecial, id_tipo_cotizacion,english)%><% } %></td>
																		<td width="11.66%" class="" align="center"><% if(ReporteCotizacionRules.isCortesiaEspecial(linea.get("cortesia"))){ %>CORTES&Iacute;A <% } else { %><%=CotizacionReportUtil.formatoConMoneda2(linea.get("monto_especial"), id_tipo_cotizacion,english)%><% } %></td>
																<% } %>
																
																
																
																<!-- DEAD CODE 1 -->
																				
																				
																				
																
																				
																				
																
																
																
																
																
																
																		
																	</tr>
														<% } %>
													</table>
												</td>
<!-- 												<tr> -->
<!-- 													<td>&nbsp;</td> -->
<!-- 												</tr> -->
											</tr>
											<%
												ArrayList<Record> lineasTE2 = DataArray.getArrayList("evento_producto_abierto_te_select_by_dia",
															header.getString("id_evento_sala"));
												// PRODUCTOS ABRIERTOS DE COTIZACION ESCRITA
											 %>
											<tr>
												
												<td valign="top" width="100%" align="right">
													<table width="100%">
														

														<%

															for (Record linea : lineasTE2) {
																				try {
																					if( !"1".equals(linea.get("cortesia") )) {
																						
																						 if(!"3".equals(evento.get("id_mostrar_precios"))){
																								
																								subtotalGeneralCliente += CotizacionReportUtil.getDoubleValue(linea.get("monto"));																							 
																						 }else{
																							 subtotalGeneralEspecial += CotizacionReportUtil.getDoubleValue(linea.get("monto"));	 
																						 }
																						

																						
																						
																						//subtotalGeneralCliente +=  CotizacionReportUtil.getDoubleValue(linea.get("monto"));
																						
																					}else{
																						
																					}
																				} catch (Exception ex) {
																					System.out.println("Monto no Numérico en Traducciósn Escrita");
																				}
														%>
																	<tr>
																		<%-- 						<td class="smallerText" width="20%"><%= linea.get("idioma") %></td> --%>
																		<%-- 						<td class="smallerText" width="20%"><%= linea.get("servicio") %></td> --%>
																		<%-- 						<td class="smallerText" width="20%"><%= linea.get("cobro") %></td> --%>
																		
																		<td width="30%"><b> <%=( "na".equals(linea.get("id_producto"))?linea.get("servicio"):"Traducción" ) %></b></td>
																		<td width="11.66%" class="" align="center">
																				<%=linea.get("cantidad") + " " + linea.get("cobro")+ ((!linea.get("cantidad").equals("1") ? "" : ""))%>
																		</td>
																		
																		
																		
																		
																		
																		<td width="11.66%" class=""  align="center"><%=linea.get("entrega")%></td>
																		
																		
																		
																		
<%-- 																		<% if ( "3".equals(evento.getString("id_mostrar_precios"))) { %> --%>
																																																		
<!-- 																				<th width="11.66%" align="center">&nbsp;	 -->
<!-- 																				</th> -->
<%-- 																		<% } %>															 --%>
																																																				
<%-- 																		<td width="11.66%" class="" align="center"><%=CotizacionReportUtil.formatoConMoneda2(linea.get("costo_unitario"), id_tipo_cotizacion,english)%></td> --%>
<!-- 																		<td width="11.66%" class="" align="center">&nbsp;</td> -->
																			
																	
<%-- 																		<td width="11.66%" class="" align="center"><%=CotizacionReportUtil.formatoConMoneda2(linea.get("monto"), id_tipo_cotizacion,english)%></td> --%>
																		
																		
																		
																		
																		
																		
																		
																		
																		
																		
																		
																		
																<%if("1".equals(evento.getString("id_mostrar_precios"))){ %>
																
																		<td width="11.66%" class="" align="center">&nbsp;</td>
																		<td width="11.66%" class="" align="center">&nbsp;</td>
																		<td width="11.66%" class="" align="center"><%=CotizacionReportUtil.formatoConMoneda2(linea.get("costo_unitario"), id_tipo_cotizacion,english)%></td>
																		<td width="11.66%" class="" align="center"><%=CotizacionReportUtil.formatoConMoneda2(linea.get("monto"), id_tipo_cotizacion,english)%></td>
																
																<% } %>
																
																
																
																<%if("2".equals(evento.getString("id_mostrar_precios")) || "3".equals(evento.getString("id_mostrar_precios"))){ %>
																
																		<td width="11.66%" class="" align="center">&nbsp;</td>
																		<td width="11.66%" class="" align="center">&nbsp;</td>
																		<td width="11.66%" class="" align="center"><%=CotizacionReportUtil.formatoConMoneda2(linea.get("costo_unitario"), id_tipo_cotizacion,english)%></td>
																		<td width="11.66%" class="" align="center"><%=CotizacionReportUtil.formatoConMoneda2(linea.get("monto"), id_tipo_cotizacion,english)%></td>
																<% } %>
																
																
																
<%-- 																<%if("3".equals(evento.getString("id_mostrar_precios"))){ %> --%>
																
<%-- 																	<td width="11.66%" class="" align="center"><%=CotizacionReportUtil.formatoConMoneda2(linea.get("costo_unitario"), id_tipo_cotizacion,english)%></td> --%>
<%-- 																		<td width="11.66%" class="" align="center"><%=CotizacionReportUtil.formatoConMoneda2(linea.get("monto"), id_tipo_cotizacion,english)%></td> --%>
<%-- 																<% } %> --%>
																		
																		
																		
															
															
															
															
															
<%-- 																<%if("1".equals(evento.getString("id_mostrar_precios")) || "2".equals(evento.getString("id_mostrar_precios"))){ %> --%>
																		
<%-- 																	<th width="11.66%" style="text-align: center"><% if (english) { %>Unitary price<% } else { %>Costo Unitario<% } %></th> --%>
																						
<%-- 																<% } else{%> --%>
<!-- 																	<th width="11.66%" align="center">&nbsp;</th> -->
<%-- 																<%} %> --%>
																
																
																
<%-- 																<%if("1".equals(evento.getString("id_mostrar_precios")) || "3".equals(evento.getString("id_mostrar_precios"))){ %> --%>
<!-- 																			<th width="11.66%" align="center"><center><b> -->
<%-- 																			<% if (english) { %>Special Unitary price<% } else { %>Costo Unitario Especial<% } %> --%>
<!-- 																			</b></center> -->
<!-- 																		</th>				 -->
<%-- 																<% } else{%> --%>
<!-- 																	<th width="11.66%" align="center">&nbsp;</th> -->
<%-- 																<%} %> --%>
																
																
																
																
																
																
<%-- 																<% if ("1".equals(evento.getString("id_mostrar_precios")) || "2".equals(evento.getString("id_mostrar_precios"))) { %> --%>
																																																		
<!-- 																		<th width="11.66%" align="center"><center><b> -->
<%-- 																		<% if (english) { %>Customer Price<% } else { %>Costo Cliente<% } %></b></center>	 --%>
<!-- 																		</th> -->
																		
<%-- 																<% } else{%> --%>
<!-- 																	<th width="11.66%" align="center">&nbsp;</th> -->
<%-- 																<%} %>  --%>
																
																
																
																
<%-- 																<% if("1".equals(evento.getString("id_mostrar_precios")) || "3".equals(evento.getString("id_mostrar_precios"))){ %> --%>

<!-- 																		<th width="11.66%" align="center"><center><b> -->
<%-- 																			<% if (english) { %>Special Price<% } else { %>Costo Especial<% } %></b></center> --%>
<!-- 																		</th>		 -->
																				
<%-- 																<% } else{%> --%>
<!-- 																	<th width="11.66%" align="center">&nbsp;</th> -->
<%-- 																<%} %> --%>
																
																		
																		
																		
																		
																		
																		
																		
																		
																		
																		
																		
																		
																		
																		
																	</tr>
																	
																	
																	<tr>
																			<% if ("1".equals(evento.getString("id_mostrar_precios")) || "2".equals(evento.getString("id_mostrar_precios"))) { %>
																																																				
																				<th width="11.66%" align="center">&nbsp;	
																				</th>
																			<% } %>	
																			<% if ( "1".equals(evento.getString("id_mostrar_precios")) || "3".equals(evento.getString("id_mostrar_precios"))) { %>
																																																				
																				<th width="11.66%" align="center">&nbsp;	
																				</th>
																			<% } %>		
																	
																	</tr>
														<% } %>
													</table>
												</td>
											</tr>
								<% } %>
							</table>
						</td>
					</tr>
					<tr>
						<td><table width="100%" border="0" cellspacing="0"
								cellpadding="0">
								<tr>
									<td width="20%">&nbsp;</td>
									<td width="50%">
										<!--             Servicio por UN dÃ­a -->
									</td>
									<td align="right">&nbsp;</td>
								</tr>
							</table></td>
					</tr>
				</table>
 <% } %>
			</td>
		</tr>
		<tr>
			<td>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="20%">&nbsp;</td>
						<td width="50%">
							<!--         Servicio por CINCO dÃ­as de evento -->
						</td>
						<td align="right">&nbsp;</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<%
					if (id_tipo_evento.equals("1")) {

								ivaCliente = subtotalGeneralCliente * 0.16;
								ivaEspecial = subtotalGeneralEspecial * 0.16;

								totalCliente = subtotalGeneralCliente + ivaCliente;
								totalEspecial = subtotalGeneralEspecial + ivaEspecial;
				%>

						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="40%">&nbsp;</td>
								<td width="20%" align="right"
									>Subtotal: <%--         <%= evento.getString("id_mostrar_precios") %> --%>


								</td><b>
								

								<% if ("1".equals(evento.getString("id_mostrar_precios"))) { %>
										<td align="right" width="20%"><%=CotizacionReportUtil.formatoConMoneda2(subtotalGeneralCliente, id_tipo_cotizacion, english)%></td>
										<td align="right" width="20%"><%=CotizacionReportUtil.formatoConMoneda2(subtotalGeneralEspecial, id_tipo_cotizacion, english)%></td>
								<% } else if ("2".equals(evento.getString("id_mostrar_precios"))) { %>
										<td align="right" width="40%"><%=CotizacionReportUtil.formatoConMoneda2(subtotalGeneralCliente, id_tipo_cotizacion, english)%></td>
								<% } else if ("3".equals(evento.getString("id_mostrar_precios"))) { %>
										<td align="right" width="40%"><%=CotizacionReportUtil.formatoConMoneda2(subtotalGeneralEspecial, id_tipo_cotizacion, english)%></td>
								<% } %>
									</b>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td align="right">
									<% if (english) { %>IVA (TAX)<% } else { %>IVA<% } %>:
								</td>

								<% if ("1".equals(evento.getString("id_mostrar_precios"))) { %>
										<td align="right"><%=CotizacionReportUtil.formatoConMoneda2(ivaCliente, id_tipo_cotizacion, english)%></td>
										<td align="right"><%=CotizacionReportUtil.formatoConMoneda2(ivaEspecial, id_tipo_cotizacion, english)%></td>
								<% } else if ("2".equals(evento.getString("id_mostrar_precios"))) { %>
										<td align="right"><%=CotizacionReportUtil.formatoConMoneda2(ivaCliente, id_tipo_cotizacion, english)%></td>
								<% } else if ("3".equals(evento.getString("id_mostrar_precios"))) { %>
										<td align="right"><%=CotizacionReportUtil.formatoConMoneda2(ivaEspecial, id_tipo_cotizacion, english)%></td>
								<% } %>

							</tr>
							<tr>
								<td>&nbsp;</td>
								<td align="right"
									>Total:</td>

								<% if ("1".equals(evento.getString("id_mostrar_precios"))) { %>
										<td align="right" style="border-top: 1pt solid black; background-color: #dfdfdf;">
											<b><%=CotizacionReportUtil.formatoConMoneda2(totalCliente, id_tipo_cotizacion, english)%></b></td>
										<td align="right"	style="border-top: 1pt solid black; background-color: #dfdfdf;">
											<b><%=CotizacionReportUtil.formatoConMoneda2(totalEspecial, id_tipo_cotizacion, english)%></b></td>
								<% } else if ("2".equals(evento.getString("id_mostrar_precios"))) { %>
										<td align="right" style="border-top: 1pt solid black; background-color: #dfdfdf;">
										<b><%=CotizacionReportUtil.formatoConMoneda2(totalCliente, id_tipo_cotizacion, english)%></b></td>
								<% } else if ("3".equals(evento.getString("id_mostrar_precios"))) { %>
										<td align="right" style="border-top: 1pt solid black; background-color: #dfdfdf;">
										<b><%=CotizacionReportUtil.formatoConMoneda2(totalEspecial, id_tipo_cotizacion, english)%></b></td>
								<% } %>

							</tr>
						</table>
<%
 	} else {

 				//
 				// TRADUCCION ESCRITA
 				//
 %>

				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="60%">&nbsp;</td>
						<td width="20%" align="right">Subtotal:</td>
								<% if ("1".equals(evento.getString("id_mostrar_precios"))) { %>
										<td align="right" width="10%"><%=CotizacionReportUtil.formatoConMoneda2(subtotalGeneralCliente, id_tipo_cotizacion, english)%></td>
										<td align="right" width="10%"><%=CotizacionReportUtil.formatoConMoneda2(subtotalGeneralEspecial, id_tipo_cotizacion, english)%></td>										
										
								<% } else if ("2".equals(evento.getString("id_mostrar_precios"))) { %>
										<td align="right" width="20%"><%=CotizacionReportUtil.formatoConMoneda2(subtotalGeneralCliente, id_tipo_cotizacion, english)%></td>
								<% } else if ("3".equals(evento.getString("id_mostrar_precios"))) { %>									
									<td align="right" width="20%"><%=CotizacionReportUtil.formatoConMoneda2(subtotalGeneralEspecial, id_tipo_cotizacion, english)%></td>
								<% } %>
						
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td align="right">
							<% if (english) { %>IVA (TAX)<% } else { %>IVA<% } %>:
						</td>
								<% if ("1".equals(evento.getString("id_mostrar_precios"))) { %>
										<td align="right" width="10%"><%=CotizacionReportUtil.formatoConMoneda2(subtotalGeneralCliente*0.16, id_tipo_cotizacion, english)%></td>
										<td align="right" width="10%"><%=CotizacionReportUtil.formatoConMoneda2(subtotalGeneralEspecial*0.16, id_tipo_cotizacion, english)%></td>
								<% } else if ("2".equals(evento.getString("id_mostrar_precios"))) { %>
									<td align="right" width="100%"><%=CotizacionReportUtil.formatoConMoneda2(subtotalGeneralCliente*0.16, id_tipo_cotizacion, english)%></td> 	
								<% } else if ("3".equals(evento.getString("id_mostrar_precios"))) { %>
									<td align="right" width="100%"><%=CotizacionReportUtil.formatoConMoneda2(subtotalGeneralEspecial*0.16, id_tipo_cotizacion, english)%></td>
									
								<% } %>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td align="right">Total:</td>

							<% if ("1".equals(evento.getString("id_mostrar_precios"))) { %>
										<td align="right" style=" background-color: #dfdfdf;" width="10%"><%=CotizacionReportUtil.formatoConMoneda2(subtotalGeneralCliente*1.16, id_tipo_cotizacion, english)%></td>										
										<td align="right" style=" background-color: #dfdfdf;" width="10%"><%=CotizacionReportUtil.formatoConMoneda2(subtotalGeneralEspecial*1.16, id_tipo_cotizacion, english)%></td>
								<% } else if ("3".equals(evento.getString("id_mostrar_precios"))) { %>
										<td align="right"  style=" background-color: #dfdfdf;" width="20%"><%=CotizacionReportUtil.formatoConMoneda2(subtotalGeneralEspecial*1.16, id_tipo_cotizacion, english)%></td>
								<% } else if ("2".equals(evento.getString("id_mostrar_precios"))) { %>										
										<td align="right" style=" background-color: #dfdfdf;" width="20%"><%=CotizacionReportUtil.formatoConMoneda2(subtotalGeneralCliente*1.16, id_tipo_cotizacion, english)%></td>
								<% } %>
					</tr>
				</table>
<% } %>


			</td>
		</tr>



		<% int addSectionCount = 3; %>

		<% if (id_tipo_evento.equals("1")) { %>

				<% if (viaticos.size() != 0) { %>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td><b><%=NumbersUtil.roman(addSectionCount++)%>. <% if (english) { %>Travel Expenses<% } else { %>Vi&aacute;ticos que el cliente debe cubrir<% } %></b></td>
				</tr>
				<tr>
					<td>
						<ul>
							<% for (Record viatico : viaticos) { %>
							<li>
								<% if (english) { %><%=viatico.getString("nombre_ingles")%>
								<% } else { %><%=viatico.getString("nombre")%>
								<% } %>
							</li>
							<% } %>
						</ul>
					</td>
				</tr>
				<% } %>
		<% } %>







		<% if (observaciones.size() != 0) { %>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td><b><%=NumbersUtil.roman(addSectionCount++)%>. <% if (english) { %>Observations<% } else { %>Observaciones<% } %></b></td>
				</tr>
				<tr>
					<td>
						<ul>
							<% for (Record observacion : observaciones) { %>
									<li>
										<% if (english) { %>
												<%=observacion.getString("nombre_ingles")%>
										<% } else { %>
												<%=observacion.getString("nombre")%>
										<% } %>
									</li>

							<%
								}
							%>
						</ul>
					</td>
				</tr>
		<% } %>
		<% if (id_tipo_evento.equals("2")) { %>
				<tr>
					<% if (english) { %>
							<td><b>III. Delivery Date</b></td>
					<% } else { %>
							<td><b>III. Fecha de Entrega:</b></td>
					<% } %>
				</tr>
				<tr>
					<td><ul>
							<li><%=evento.getString("CONCAT_FECHA")%></li>
						</ul></td>
				</tr>
		<% } %>



		<% if (condiciones.size() != 0) { %>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td><b><%=NumbersUtil.roman(addSectionCount++)%>. <% if (english) { %>Payment conditions<% } else { %>Condiciones de Pago<% } %></b></td>
				</tr>
				<tr>
					<td>
						<ul>
							<% for (Record condicion : condiciones) { %>
									<li>
										<% if (english) { %>
												<%=condicion.getString("nombre_ingles")%>
										<% } else { %>
												<%=condicion.getString("nombre")%>
										<% } %>
									</li>
							<% } %>
						</ul>
					</td>
				</tr>
		<% } %>
		<%// if (id_tipo_evento.equals("1")) { %>
					<% if (politicas.size() != 0) { %>
								<tr>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td><b><%=NumbersUtil.roman(addSectionCount++)%>. <% if (english) { %>Cancellation policy<% } else { %>Pol&iacute;tica de Cancelaci&oacute;n<% } %></b></td>
								</tr>
								<tr>
									<td>
										<ul>
											<% for (Record politica : politicas) { %>
													<li>
														<% if (english) { %>
															<%=politica.getString("nombre_ingles")%>

														<% } else { %>
															<%=politica.getString("nombre")%>
															<% } %>
													</li>
													<% } %>
										</ul>
									</td>
								</tr>
					<% } %>
		<%// } %>

		<tr>
			<td>&nbsp;</td>
		</tr>
		
		
		
		
		<% if (english || id_tipo_cotizacion.equals("4") || id_tipo_cotizacion.equals(HardCodeConstants.ID_A_FUTURO)) { %>
		
		<tr>
			<td>
				<table align="left" width="40%">
								
						<tr>
							<td>
									<%= (english?"Exchange Rate":"Tipo de cambio") %>
							</td>
							<td>$ <%=dbl_tipo_cambio%> MXN  <%=fecha_cambio %></td>
						</tr>
					
				
				</table>
				
				<br><br>
			</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<% }%>
											
											
											
											
											

		<tr>
			<td>

				<div title="Page 5">
					<div>
						<div>
							<div>
								<p>
									<% if (english) { %><% } else { %>Estamos a sus &oacute;rdenes para cualquier comentario sobre el particular. <br><br><% } %>
									<b>
										<% if (english) { %>SINCERELY<% } else { %>ATENTAMENTE<% } %>
									</b>
								</p>
								<p>
									<% if (english) { %>BA:<% } else { %>PA:<% } %>
									<%=nombreUsuario%>
									<%=apellidosUsuario%>
								</p>
								<p><b>Lic. Gabriela Durazo V.</b></p>
								<p><b>CEO</b></p>

							</div>
						</div>
					</div>
				</div>

			</td>
		</tr>
	</table>

</body>
<%
	//session.removeAttribute("ID_EMPLEADO");
			//session.removeAttribute("ID_EMPRESA");

		} else {
			out.println("NO se se encontr&oacute; la cotizaci&oacute;n");
			//response.sendRedirect("registro.jsp");
		}

	} catch (Exception _e) {
		out.println("NO se se encontr&oacute; la cotizaci&oacute;n");

		//response.sendRedirect("registro.jsp");
		_e.printStackTrace();
	}
%>
</html>

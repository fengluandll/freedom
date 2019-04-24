

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


	String id_evento_version = request.getParameter("ID_EVENTO");

	String id_evento = OneValueFactory.get("select id_evento from evento_version where id_evento_version = "+ id_evento_version);

	


	String localForaneo = OneValueFactory.get("select ifnull(id_tipo_x,0) from evento inner join evento_version  using (id_evento) where id_evento_version =" + id_evento_version);

	Record evento = OneRecordFactory.getPr("evento_select_by_id_pr", id_evento_version);

	Record memo = null;
	
	
	if("1".equals(localForaneo)){
		memo = OneRecordFactory.getPr("evento_memo_servicio_pr", id_evento_version);
	} else if ("2".equals(localForaneo)){
		
		memo = OneRecordFactory.getPr("evento_memo_servicio_foraneo_pr", id_evento_version);
	}
/*
	System.out.println(id_evento_version+ "--dd");
	System.out.println(localForaneo+ "--df");
	*/
	//ArrayList<Record> headers = DataArray.getArrayList("evento_sala_dia_headers_pr", id_evento);


	//ArrayList<Record> politicas = DataArray.getArrayList("evento_politicas_applic_select_pr", id_evento);

	//ArrayList<Record> condiciones = DataArray.getArrayList("evento_condicion_select_pr", id_evento);

	//ArrayList<Record> observaciones = DataArray.getArrayList("evento_observacion_select_pr", id_evento);





%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

		<title>Memo</title>



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
			
			#memoTabla tbody tr td {
				 padding-bottom:  10px;
			}
			.horarioInterpretacion table{
				border: 1px solid #ddd;
				border-spacing: 0;
				border-collapse: collapse;
			}
			.horarioInterpretacion table tr{
				box-sizing: border-box;
			}	

			.horarioInterpretacion table tr td{
				border: 1px solid #ddd;
				padding: 8px;
				line-height: 1.42857143;
				vertical-align: top;
			}				
			



        </style>
</head>
<body>
	<%if(!"0".equals(localForaneo)){ %>

<table id="divPrincipalMemo" width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>

    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>

          <td width="10%">		</td>       
        <td align="left" style="padding-left: 20px;">

        <img src="/SAO/img/Logo-Omnilingua-Smaller.png">


<!--         t. 5202 5515<br /> -->
<!--           omnilingua@gmail.com -->

         </td>
         
          <td align="right"  width="35%">
			<H1>Memo</H1>
			<p>Cotizaci&oacute;n: <%= evento.get("clave_cotizacion") %></p>
		</td>
		<td width="15%">		</td>
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
			

		<br />
        </p>
        
        <%if("1".equals(localForaneo)) {%>
        
          <table width="100%" border="0"  id="memoTabla">
            <tr>
              <td width="35%"></td>
              <td width="10%"></td>
              <th align="right">&nbsp;</th>
            </tr>
            <tr>
              <td align="right" ><b>Nombre del Evento</b></td>
              <td></td>
              <td ><%= memo.get("nombre_evento") %></td>
            </tr>
            <tr>
              <td align="right"  ><b>Cliente</b></td>
              <td></td>
              <td ><%= memo.get("cliente") %></td>
            </tr >
             <tr>
              <td align="right" ><b>Contacto</b></td>
              <td></td>
              <td><%= memo.get("contacto") %></td>
            </tr>
            <tr>
              <td align="right" ><b>Lugar/Sede/Sal&oacute;n</b></td>
              <td></td>
              <td><%= memo.get("lugar") + " / " + memo.get("sede") + " / " + memo.get("salon")%></td>
            </tr>
            <tr>
              <td align="right" ><b>Fecha(s)</b></td>
              <td></td>
              <td><%= memo.get("fechas") %></td>
            </tr>
            <tr>
              <td align="right" ><b>Horario General del <br>Evento</b></td>
              <td></td>
              <td><%= memo.get("horario_general") %></td>
            </tr>

            <tr>
              <td align="right" ><b>Equipo de <br>Interpretaci&oacute;n</b></td>
              <td></td>
              <td><%= memo.get("equipo_interpretacion") %></td>
            </tr>

            <tr>
              <td align="right" ><b>Horario de <br>Instalaci&oacute;n/D&iacute;a</b></td>
              <td></td>
              <td><%= memo.get("horario_instalacion") %></td>
            </tr>

            <tr>
              <td align="right" ><b>Nombre de t&eacute;cnico(s)</b></td>
              <td></td>
              <td><%= memo.get("nombre_tecnicos") %></td>
            </tr>

            <tr>
              <td align="right" ><b>Idioma(s)</b></td>
              <td></td>
              <td><%= memo.get("idiomas") %></td>
            </tr>


            <tr>
              <td align="right" ><b>Tipo de interpretaci&oacute;n</b></td>
              <td></td>
              <td><%= memo.get("tipo_interpretacion") %></td>
            </tr>


            <tr>
              <td align="right" ><b>Int&eacute;rprete(s)</b></td>
							<td ></td>
              <td><%= memo.get("nombre_interpretes").replace(",","\n") %></td>
            </tr>
						<tr>
              <td align="right"> </td>
							<td > </td>
              <td> </td>
            </tr>
            
            
            
            <tr>
              <td align="right"  ><b>Horario de<br> interpretaci&oacute;n</b> </td>
              <td></td>  
              <td >
							<div style="width:554px" class="horarioInterpretacion">
						<%
	                 	InterpretesAsignadosTable x4 = new InterpretesAsignadosTable(request);

						x4.setModo("MEMO");

	                 	%>

	                 <%= x4.getHtml() %>
									 </div>
			  </td>
			  </tr>
			  
			  
			  
			  
			  
			  
			  <tr>
              <td align="right"  ><b>Cita de<br> int&eacute;rprete(s)</b> </td>
              <td></td>  
              <td>
							<div style="width:554px" class="horarioInterpretacion">
						<%
	                 	InterpretesAsignadosTable x5 = new InterpretesAsignadosTable(request);

						x4.setModo("CITAS");

	                 	%>

	                 <%= x4.getHtml() %>
									 </div>
			  </td>
			  </tr>
			  
			  
			  
			  
			  
				<tr>
				<td align="right"  ><b>Observaciones</b> </td>
					<td align="center" colspan="2"><br><br>
						<%= OneValueFactory.get("select ifnull(observaciones,'') from evento_memo_servicio where id_evento_version ="+ id_evento_version) %>

						<%--
						<ul>

							ArrayList<Record> observaciones = DataArray.getArrayList("select * from crm_cliente_comentarios where id_evento="+id_evento_version);
							for(Record observacion: observaciones){

							<li><%=observacion.get("comentario")%></li>
						 }

						 </ul>  --%>

					</td>
				</tr>
            </tr>
            <%-- <tr>
              <td align="right">Cita de int&eacute;rpretes</td>
              <td></td>
              <td><%= "" %></td>
            </tr>
						<tr>
						<td align="right">Observaciones</td>
						<td></td>
						<td><%= interprete %></td>
						</tr> --%>




          </table>
          
          
          <%} else{%>
          	
          <table width="100%" border="0"  id="memoTabla">
            <tr>
              <td width="35%"></td>
              <td width="10%"></td>
              <th align="right">&nbsp;</th>
            </tr>
            <tr>
              <td align="right" ><b>Nombre del Evento</b></td>
              <td></td>
              <td ><%= memo.get("nombre_evento") %></td>
            </tr>
            <tr>
              <td align="right"  ><b>Cliente</b></td>
              <td></td>
              <td ><%= memo.get("cliente") %></td>
            </tr >
             <tr>
              <td align="right" ><b>Contacto</b></td>
              <td></td>
              <td><%= memo.get("contacto") %></td>
            </tr>
            <tr>
              <td align="right" ><b>Lugar/Sede/Sal&oacute;n</b></td>
              <td></td>
              <td><%= memo.get("lugar") + " / " + memo.get("sede") + " / " + memo.get("salon")%></td>
            </tr>
            <tr>
              <td align="right" ><b>Fecha(s)</b></td>
              <td></td>
              <td><%= memo.get("fechas") %></td>
            </tr>
            <tr>
              <td align="right" ><b>Horario General del <br>Evento</b></td>
              <td></td>
              <td><%= memo.get("horario_general") %></td>
            </tr>

            <tr>
              <td align="right" ><b>Equipo de <br>Interpretaci&oacute;n</b></td>
              <td></td>
              <td><%= memo.get("equipo_interpretacion") %></td>
            </tr>

            <tr>
              <td align="right" ><b>Horario de <br>Instalaci&oacute;n/D&iacute;a</b></td>
              <td></td>
              <td><%= memo.get("horario_instalacion") %></td>
            </tr>

            <tr>
              <td align="right" ><b>Nombre de t&eacute;cnico(s)</b></td>
              <%ArrayList<Record> listado = DataArray.getArrayList("select_datos_tecnicos_memo_pr", id_evento_version); %>
              <td></td>
              <td><table class="table table-bordered">
              	<thead>
              		<tr>
              			<td><b>Nombre</b></td>
              			<td><b>Traslado</b></td>
              			<td><b>Hotel de Hospedaje</b></td>
              		</tr>              		
              	</thead>
              	<tbody>
              		<% for(Record elemento: listado) {%>
              		<tr>
              			<td><%=elemento.get("nombre") %></td>
              			<td><%=elemento.get("traslado") %></td>
              			<td><%=elemento.get("hotel") %></td>              		
              		</tr>
              		<% }%>
              	</tbody>
              </table> </td>
            </tr>

            <tr>
              <td align="right" ><b>Idioma(s)</b></td>
              <td></td>
              <td><%= memo.get("idiomas") %></td>
            </tr>

            <tr>
              <td align="right" ><b>Alimentos</b></td>
              <td></td>
              <td><%= memo.get("comida") %> </td>
            </tr>
 
            <tr>
              <td align="right" ><b>Tipo de interpretaci&oacute;n</b></td>
              <td></td>
              <td><%= memo.get("tipo_interpretacion") %></td>
            </tr>


            <tr>
              <td align="right" ><b>Int&eacute;rprete(s)</b></td>
              <%
              	ArrayList<Record> listado2 = DataArray.getArrayList("select_datos_interpretes_memo_pr", id_evento_version);
              %>
							<td ></td>
              <td><table class="table table-bordered">
              	<thead>
              		<tr>
              			<td><b>Nombre</b></td>
              			<td><b>Traslado</b></td>
              			<td><b>Hotel de Hospedaje</b></td>
              		</tr>              		          		
              	</thead>
              	<tbody>
              		<% for(Record elemento: listado2) {%>
              			<tr>
	              			<td><%=elemento.get("nombre") %></td>
	              			<td><%=elemento.get("traslado") %></td>
	              			<td><%=elemento.get("hotel") %></td>
            			</tr>
              		<% }%>
              	</tbody>
              </table> </td>
            </tr>
						<tr>
              <td align="right"> </td>
							<td > </td>
              <td> </td>
            </tr>
            
            
            
            
            
<!--             <tr> -->
<!--               <td align="right"  ><b>Horario de<br> interpretaci&oacute;n</b> </td> -->
<!--               <td></td>   -->
<!--               <td > -->
<!-- 							<div style="width:554px" class="horarioInterpretacion"> -->
<%-- 						<% --%>

<!-- 
// 	                 	InterpretesAsignadosTable x4 = new InterpretesAsignadosTable(request);

// 						x4.setModo("MEMO");
 -->
<%-- 	                 	%> --%>

<%-- 	                 <%= x4.getHtml() %> --%>
<!-- 									 </div> -->
<!-- 			  </td> -->
<!-- 			  </tr> -->
			  
			  
			  
<!-- 			   <tr> -->
<!--               <td align="right"  ><b>Cita de<br> int&eacute;rprete(s)</b> </td> -->
<!--               <td></td>   -->
<!--               <td > -->
<!-- 							<div style="width:554px" class="horarioInterpretacion"> -->
<%-- 						<% --%>

<!-- 
// 	                 	InterpretesAsignadosTable x5 = new InterpretesAsignadosTable(request);

// 						x4.setModo("CITAS");
 -->
<%-- 	                 	%> --%>

<%-- 	                 <%= x4.getHtml() %> --%>
<!-- 									 </div> -->
<!-- 			  </td> -->
<!-- 			  </tr> -->
			  
			  
			  
			   <tr>
              <td align="center" colspan="3"  ><b>Cita de int&eacute;rprete(s) / Horario de interpretaci&oacute;n</b> </td>
              </tr>
              <td>
             
              <td align="center" colspan="3">
							<div style="width:700px" class="horarioInterpretacion">
						<%
	                 	InterpretesAsignadosTable x7 = new InterpretesAsignadosTable(request);

						x7.setModo("CITAS");

	                 	%>

	                 <%= x7.getHtml() %> 
									 </div>
			  </td>
			  </tr>
			  
			  
			  
			  
			  
			  
				<tr>
				<td align="right"  ><b>Observaciones</b> </td>
					<td align="center" colspan="2"><br><br>
						<%= OneValueFactory.get("select ifnull(observaciones,'') from evento_memo_servicio where id_evento_version ="+ id_evento_version) %>

						<%--
						<ul>

							ArrayList<Record> observaciones = DataArray.getArrayList("select * from crm_cliente_comentarios where id_evento="+id_evento_version);
							for(Record observacion: observaciones){

							<li><%=observacion.get("comentario")%></li>
						 }

						 </ul>  --%>

					</td>
				</tr>
            </tr>
            <tr>
            <td></td>
            <td></td>
            <td align="left"> <div style= ' color:red;'><b>NOTA IMPORTANTE</b>: Es importante facturar todos los gastos que se realicen como se menciona abajo para poder reembolsar todo y sin ningún problema de los gastos realizados.
            
            </div>
            <ul>
            <li><b>SERVICIOS ESPECIALIZADOS EN IDIOMAS OMLG, S.A. DE C. V.</b></li>
			<li><b>Domicilio Fiscal:</b> Corina 150 Col. Del Carmen C. P. 04100 Delegación, Coyoacan</li> 
			<li><b>RFC:</b> SEI120618 1U5</li>
            </ul> 
            </td>
            </tr>
            <%-- <tr>
              <td align="right">Cita de int&eacute;rpretes</td>
              <td></td>
              <td><%= "" %></td>
            </tr>
						<tr>
						<td align="right">Observaciones</td>
						<td></td>
						<td><%= interprete %></td>
						</tr> --%>




          </table>
          	
          
          <% } %>
          
          

          <DIV align="center">
          <H3>
          	<b>&iexcl;Gracias por su apoyo!</b>
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
	<%}else{ %>
	<h1>Seleccionar si es local o for&aacute;neo</h1>
	<%} %>
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

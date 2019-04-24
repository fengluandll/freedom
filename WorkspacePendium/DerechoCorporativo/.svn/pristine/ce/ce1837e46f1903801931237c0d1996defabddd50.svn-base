<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>apoderados.jsp</title>


<script type="text/javascript">

</script>
<style type="text/css">
.id_revocacion{
	color:red;
}
</style>
</head>
<body onload="">

<table width="100%">
	<tr>
		<td width="5%"></td>
		<td width="15%">Escritura:</td>
		<td width="10%">
			<div id="selectEscrituraDiv">
				<%@include file="catEscrituras.jsp" %>
			</div>
		</td>
		
		<td width="5%"></td>
		<td width="15%">Tipo Poder:</td>
		<td width="10%">
			<div id="selectTipoPoderDiv">
				<%@include file="catTipoPoder.jsp" %>
			</div>         
		</td>
		
		
		<td width="5%"></td>
		<td width="15%">Grupos Apoderados:</td>
		<td width="10%">
			<div id="inputGrupoDiv">
			<!--	<input id="idGrupo" name="idGrupo" type="text" onblur="selectTipoPoderChanged()"> -->
				<%@include file="catGrupApo.jsp" %>
			</div>
			
		</td>
		
		<td width="5%"></td>
		<td width="15%">Orden No:</td>
		<td width="10%">
			<div id="inputOrdenDiv">
					<%@include file="numOrdenApoderados.jsp" %>
			</div>
			
		</td>
 		
	</tr>
</table>

	<!-- ECM 19 AGOSTO 2015 
			Agregar PopUp
	-->
<table align="center">
	<tr>
		
	</tr>
</table>	
<table align="right">
<!--  
	<tr>
		<td align="right">
			<select id="mySelect" name="mySelect" style="padding: 3px; border: 0px solid white; margin: 0 0 3px 0; background: rgb(255,255,255); width: 10%; font-family: sans-serif; font-size: 14px; color:black;">
				<option>Neto</option>
				<option>Argu</option>
				<option>Ivan</option>
			</select>
		</td>		
	</tr>
-->
	<tr>
		<td style="text-align: center;">
			<img src="../../img/wait-bar.gif" id='imgEsperaApode' style="display: none">
		</td>
		<td width="35%"><input id= "btnPreview"  type="button" name="" value="Copiar Escritura" 
			onClick='copiaEscrituraFn();'
			style="padding: 3px;
			border: 0px solid white;
			margin: 0 0 3px 0;
			background: rgb(192,192,192);
			width: 100%;
			font-family: sans-serif;
			font-size: 14px;
			color:black;">
		</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td  width="35%"><input id= "btnPreview"  type="button" name="" value="Preview" 
			onClick='openPopup();'
			style="padding: 3px;
			border: 0px solid white;
			margin: 0 0 3px 0;
			background: rgb(192,192,192);
			width: 100%;
			font-family: sans-serif;
			font-size: 14px;
			color:black;">
		</td>
	</tr>
	
	 <tr>
  		<th colspan="5" scope="col" style="color:red;"> <%= request.getParameter("mensaje") == null ? "" : request.getParameter("mensaje")%> </th>
 	</tr>
	
</table>
<div id="idMensajeGuardar" style="display: none;">
<table align="center">
	<tr>
		<td style="color:red;">ATENCIÓN: LOS CAMBIOS SURTIRAN EFECTO AL MOMENTO DE DAR CLIC EN GUARDAR CAMBIOS</td>
	</tr>
</table>
</div>

 
         

<br>


 



<br><br>


<div id="apoderadosInfoDiv"></div>

</body>
</html>
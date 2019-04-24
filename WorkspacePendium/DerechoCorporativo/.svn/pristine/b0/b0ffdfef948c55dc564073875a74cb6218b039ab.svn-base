<%@page import="mx.com.televisa.derechocorporativo.bean.CatalogoBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="mx.com.televisa.derechocorporativo.bean.DetalleCatagoBean,
    								  mx.com.televisa.derechocorporativo.bean.FlexColumnsCatagoBean,
    								  mx.com.televisa.derechocorporativo.daos.CatalogoDAO,
    								  java.sql.Connection,
									  java.sql.PreparedStatement,
									  java.sql.ResultSet,
									  mx.com.televisa.derechocorporativo.data.ConnectionWrapper,
									  mx.com.televisa.derechocorporativo.modules.dynhtml.SelectList,
									  mx.com.televisa.derechocorporativo.util.TextFormatter
									  "%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/css/kaz-styles.jsp"%>
<script src="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.min.js"></script> 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.css">
<%@include file="/jsp/components/session_timeout/session_timeout.jsp"%>

<style type="text/css">
.btnRight {
	text-align: right;
}
a:hover {
	background-image:
		url("<c:out value='${applicationBean.contextPath}'/>/img/Back2.png");
}
</style>
<script type="text/javascript" src="../../js/jquery/jquery.js"></script>
<script type="text/javascript">

var convertirAMayusculas = function(input){
	input.value = input.value.toUpperCase();
	//$(input).val($(input).val().toUpperCase());  
}

window.onload = function(){
 	document.getElementById('txtIdenti').value = '';
	document.getElementById('txtNombreC').value = '';
	var valorCampo  = document.getElementById('txtValor').value;
	var descCampo   = document.getElementById('txtDescC').value;
	var attr1 	    = document.getElementById('txtAttr1').value;
	var attr2 	    = document.getElementById('txtAttr2').value;
	var attr3 	    = document.getElementById('txtAttr3').value;
}
function closeForm(param,param2){
	
	window.location = '/DerechoCorporativo/CargaDetalleCatgoServlet?nomCata='+param+'&idcatalogo='+param2;
}
function validaForm(){
	var identi = document.getElementById('txtIdentificador').value.trim();
	var nombre = document.getElementById('txtNombre').value.trim();
	var valor = document.getElementById('txtValor').value.trim();
	var desc = document.getElementById('txtDesc').value.trim();
	var pais = document.getElementById('txtAttr2').value.trim();
	
	if(identi == ''){
		swal("Es requerido capturar el Identificador")
		}else if(nombre == ''){
			swal("Es requerido capturar el nombre")
		}else if(valor == ''){
			swal("Es requerido capturar el valor")
		}else if(desc == ''){
			swal("Es requerido capturar la descripción")
		//}else if(pais == '0'){
			//swal("Es requerido Capturar el País")
		}else {
			document.getElementById("frmEditaDetalle").submit();
			}
}
</script>
</head>
<body>
<% CatalogoBean catalogoBean = (CatalogoBean)session.getAttribute("detCatalogoBean"); 
   
  String cata = catalogoBean.getNombre();
   %>



		<table width="100%" align="center">
			<tr>
				<td><jsp:include
						page="/jsp/components/pages_border/open-pre-title.jsp"></jsp:include>
				
						Administraci&oacute;n de Cat&aacute;logo: <u><%= cata  %></u>
					
				<jsp:include
						page="/jsp/components/pages_border/open-post-title.jsp"></jsp:include>




<form id="frmEditaDetalle" name="frmEditaDetalle" method="post" action="/DerechoCorporativo/faces/GuardaDetalleCatalogServlet?action=edita&nomCata=<%=new String(request.getParameter("nomCata").getBytes("ISO-8859-1"),"UTF-8")%>">
<p>&nbsp;</p>

<% DetalleCatagoBean detalleCatagoBean = (DetalleCatagoBean)session.getAttribute("detalleCatagoBean"); 
    CatalogoDAO catalogoDAO = new CatalogoDAO();
   FlexColumnsCatagoBean flexColumnsCatagoBean = catalogoDAO.obtenFlexColumn(Integer.parseInt(request.getParameter("idcatalogo")));
   String nomCatalogo = new String(request.getParameter("nomCata").getBytes("ISO-8859-1"),"UTF-8");
   ConnectionWrapper conn = null;
   PreparedStatement luPreparedStatement = null;
   ResultSet         luResultSet         = null;
   
   try{
	   conn = new ConnectionWrapper();
	   String msgDupli = (String)session.getAttribute("msgDuplicado");

%>
<table width="80%" border="0" align="center" cellspacing="0" cellpadding="0">
<tr>
		
		<th width="100%" align="left"><font style="color: red;"> <%= msgDupli == null ? "" : msgDupli %> </font></th>
		
	</tr>
	<tr>
		<th>&nbsp;</th>
	</tr>
  <tr style="background-image: url('/DerechoCorporativo/img/Back2.png')">
    <th width="100%" align="center"><font style="color: white;"> Edita detalle Cat&aacute;logo </font></th>
    <td align="right"><a href="#"><img src="/DerechoCorporativo/img/close.png" onclick="closeForm('<%=nomCatalogo%>','<%= request.getParameter("idcatalogo") %>')"></a></td>
  </tr>
  
  <tr>
  	
  	<td colspan="2">
  	<input name="idCatalogo" type="hidden" value="<%= request.getParameter("idcatalogo") %>">
  	<input name="idCatalogoVal" type="hidden" value="<%= request.getParameter("idCatalogoVal") %>">
  	<table width="80%" border="0">
  	<tr>
    <td><%=flexColumnsCatagoBean.getIdentificador() == null ? "Identificador" : flexColumnsCatagoBean.getIdentificador()%>:</td>
    <td>
      <input type="text" name="txtIdentificador" id="txtIdentificador" value="<%=detalleCatagoBean.getIdentificador().trim()%> " title="<%=detalleCatagoBean.getIdentificador()%> " <%= detalleCatagoBean.getStatusFlex()%>/>
    </td>
  </tr>
  <tr>
    <td><%=flexColumnsCatagoBean.getNombre() == null ? "Nombre" : flexColumnsCatagoBean.getNombre() %>:</td>
    <td><textarea name="txtNombre" id="txtNombre" cols='50' rows='1' title="<%=detalleCatagoBean.getNombre() == null ? "" : TextFormatter.ToFormatHtml( detalleCatagoBean.getNombre().trim() )%> " <%=detalleCatagoBean.getStatusFlex()%> <%= request.getParameter("idcatalogo").equals("1")?"onkeyup='convertirAMayusculas(this);'":""%>><%=detalleCatagoBean.getNombre() == null ? "" : TextFormatter.ToFormatHtml(detalleCatagoBean.getNombre().trim())%></textarea></td>
  </tr>
  <tr>
    <td><%=flexColumnsCatagoBean.getValor() == null ? "Valor" : flexColumnsCatagoBean.getValor() %>:</td>
    <td><textarea name="txtValor" id="txtValor" cols='50' rows='1' title="<%=detalleCatagoBean.getValor() == null ? "" : TextFormatter.ToFormatHtml( detalleCatagoBean.getValor().trim() )%> " <%=detalleCatagoBean.getStatusFlex()%> <%= request.getParameter("idcatalogo").equals("1")?"onkeyup='convertirAMayusculas(this);'":""%>><%=detalleCatagoBean.getValor() == null? "" : TextFormatter.ToFormatHtml(detalleCatagoBean.getValor().trim())%></textarea></td>
  </tr>
  <tr>
    <td><%=flexColumnsCatagoBean.getDescripcion() == null ? "Descripción" :  flexColumnsCatagoBean.getDescripcion()%>:</td>
    <td>
    <%
    	if(detalleCatagoBean.getIdCatinCat()!= 0 && detalleCatagoBean.getCampCatinCat().equals("txtDesc")) {
    %>
    	<%=SelectList.getSelectListCatin(conn, "txtDesc", "200", detalleCatagoBean.getStatusFlex(), detalleCatagoBean.getDescripcion(), detalleCatagoBean.getIdCatinCat())%>  	
    <%  }else{  %>
    	<textarea name="txtDesc" id="txtDesc" cols='50' rows='1' title="<%=detalleCatagoBean.getDescripcion() == null? "" : TextFormatter.ToFormatHtml(detalleCatagoBean.getDescripcion().trim())%> " <%=detalleCatagoBean.getStatusFlex()%> <%= request.getParameter("idcatalogo").equals("1")?"onkeyup='convertirAMayusculas(this);'":""%>><%=detalleCatagoBean.getDescripcion() == null? "" : TextFormatter.ToFormatHtml(detalleCatagoBean.getDescripcion().trim())%></textarea>
    <%  } %>
    </td>
  </tr>
  <tr>
    <td><%= flexColumnsCatagoBean.getAttr1() == null ? "Atributo1" : flexColumnsCatagoBean.getAttr1() %>:</td>
    <td>
    <%
    	if(detalleCatagoBean.getIdCatinCat()!= 0 && detalleCatagoBean.getCampCatinCat().equals("txtAttr1")) {
    %>
    	<%= SelectList.getSelectListCatin(conn, "txtAttr1", "200", detalleCatagoBean.getStatusFlex(), detalleCatagoBean.getAttr1(), detalleCatagoBean.getIdCatinCat())%>  	
    <%  }else{  %>
    	<textarea name="txtAttr1" id="txtAttr1" cols='50' rows='4' title="<%= detalleCatagoBean.getAttr1() == null? "" : detalleCatagoBean.getAttr1().trim() %> " <%= detalleCatagoBean.getStatusFlex() %> <%= request.getParameter("idcatalogo").equals("1")?"onkeyup='convertirAMayusculas(this);'":""%>><%= detalleCatagoBean.getAttr1() == null? "" : detalleCatagoBean.getAttr1().trim() %></textarea>
    <%  } %>
    </td>
  </tr>
  <tr>
    <td><%= flexColumnsCatagoBean.getAttr2()  == null ? "Atributo2" : flexColumnsCatagoBean.getAttr2() %>:</td>
    <td>
    <%
    	if(detalleCatagoBean.getIdCatinCat()!= 0 && detalleCatagoBean.getCampCatinCat().equals("txtAttr2")) {
    		if(request.getParameter("idcatalogo").equals("1")){
    %>
    	<textarea name="txtAttr2" id="txtAttr2" readonly cols='50' rows='1' title="<%= detalleCatagoBean.getAttr2() == null? "" : detalleCatagoBean.getAttr2().trim() %> " <%= detalleCatagoBean.getStatusFlex() %> <%= request.getParameter("idcatalogo").equals("1")?"onkeyup='convertirAMayusculas(this);'":""%>><%= detalleCatagoBean.getAttr2() == null? "" : detalleCatagoBean.getAttr2().trim() %></textarea>
    <%  }else{ %>	
    	<%= SelectList.getSelectListCatin(conn, "txtAttr2", "300", detalleCatagoBean.getStatusFlex(), detalleCatagoBean.getAttr2(), detalleCatagoBean.getIdCatinCat())%>
    	<%  }%>  	
    <%  }else{  %>
    	<textarea name="txtAttr2" id="txtAttr2" cols='50' rows='1' title="<%= detalleCatagoBean.getAttr2() == null? "" : detalleCatagoBean.getAttr2().trim() %> " <%= detalleCatagoBean.getStatusFlex() %> <%= request.getParameter("idcatalogo").equals("1")?"onkeyup='convertirAMayusculas(this);'":""%>><%= detalleCatagoBean.getAttr2() == null? "" : detalleCatagoBean.getAttr2().trim() %></textarea> 
    <%  }%>
    </td>
  </tr>
  <tr>
    <td><%= flexColumnsCatagoBean.getAttr3() == null ? "Atributo3" : flexColumnsCatagoBean.getAttr3()%>:</td>
    <td>
    <%
    	if(detalleCatagoBean.getIdCatinCat()!= 0 && detalleCatagoBean.getCampCatinCat().equals("txtAttr3")) {
    %>
    	<%= SelectList.getSelectListCatin(conn, "txtAttr3", "300", detalleCatagoBean.getStatusFlex(), detalleCatagoBean.getAttr3(), detalleCatagoBean.getIdCatinCat())%>  	
    <%  }else{
    	if(request.getParameter("idcatalogo").equals("1")){//if(nomCatalogo.contains("Denominación Social")){
    %>
    	<%= SelectList.getSelectListCatinId(conn, "txtAttr3", "300", detalleCatagoBean.getStatusFlex(), detalleCatagoBean.getAttr3(), 59)%>
    	<%		
    		}else{
    	%>
    	<textarea name="txtAttr3" id="txtAttr3" cols='50' rows='1' title="<%= detalleCatagoBean.getAttr3() == null? "" : detalleCatagoBean.getAttr3().trim() %> " <%= request.getParameter("idcatalogo").equals("1")?"onkeyup='convertirAMayusculas(this);'":""%>><%= detalleCatagoBean.getAttr3() == null? "" : detalleCatagoBean.getAttr3().trim() %></textarea>
    <%  }
    		} %>
    </td>
  </tr>
  	</table>
  	</td>
  </tr>
  <tr>
    <td colspan="2" class="btnRight"><input type="button" value="Guardar" onclick="validaForm();"></td>
  </tr>
</table>
</form>
				<jsp:include
					page="/jsp/components/pages_border/close.jsp"></jsp:include></td>
		</tr>
	</table>


<%  } catch (Exception e) {
    e.printStackTrace();

	}finally {
		session.removeAttribute("msgDuplicado");
		conn.close();

} %>
</body>
</html>
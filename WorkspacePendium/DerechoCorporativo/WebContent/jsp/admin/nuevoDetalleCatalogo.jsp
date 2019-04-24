<%@page import="mx.com.televisa.derechocorporativo.bean.CatalogoBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="mx.com.televisa.derechocorporativo.daos.CatalogoDAO,
    								  mx.com.televisa.derechocorporativo.bean.FlexColumnsCatagoBean,
    								  mx.com.televisa.derechocorporativo.bean.DetalleCatagoBean,
    								  mx.com.televisa.derechocorporativo.daos.CatalogoDAO,
    								  java.sql.Connection,
									  java.sql.PreparedStatement,
									  java.sql.ResultSet,
									  mx.com.televisa.derechocorporativo.data.ConnectionWrapper,
									  mx.com.televisa.derechocorporativo.modules.dynhtml.SelectList"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../../js/jquery/jquery-3.1.1.js"></script>
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
<script type="text/javascript">

var convertirAMayusculas = function(input){
	input.value = input.value.toUpperCase();
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
		}else{
			document.getElementById("frmNvoDetalle").submit();
		}
}

$(document).ready(function(){
		
		$("#txtAttr3 option:selected").each(function () {
        	$(this).removeAttr('selected'); 
        });
		$("#txtAttr3").val(0).prop('selected', true);

 });
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





<%
	CatalogoDAO catalogoDAO = new CatalogoDAO();
	String aumenta = request.getParameter("muestraMsg");
	String sId = request.getParameter("idCatalogo");
	String sIdConsec = request.getParameter("idConsec");
	int idCata = 0;
	if(sId != null){
		idCata = Integer.parseInt(sId);
	}
	int id = catalogoDAO.dameIdentifiConsec(Integer.parseInt(sId));
	if(aumenta != null && aumenta.equals("true")){
		id--;
	}
	if(sIdConsec != null){
		id = Integer.parseInt(sIdConsec);
	}
    FlexColumnsCatagoBean flexColumnsCatagoBean = catalogoDAO.obtenFlexColumn(idCata);
    DetalleCatagoBean detalleCatagoBean = null;
    		
    try {
    detalleCatagoBean =catalogoDAO.dameDetalleCatgo(idCata).get(0);
    }catch(Exception e) {
    	detalleCatagoBean = new DetalleCatagoBean();
    	detalleCatagoBean.setIdCatinCat(0);
    	detalleCatagoBean.setCampCatinCat("");
    }
    
    ConnectionWrapper conn = null;
    PreparedStatement luPreparedStatement = null;
    ResultSet         luResultSet         = null;
    
    try{
 	   conn = new ConnectionWrapper();
 	  DetalleCatagoBean detalle = (DetalleCatagoBean)session.getAttribute("detalleCatagoBean");
 	 
 	  if(detalle != null){
 		 String consec = detalle.getIdentificador();
 	 	  /*String identi = detalle.get;
 	 	  String nombre = 
 	 	  String valor = ;
 	 	  String desc  = ;*/
 	  }
 	  String msgDupli = (String)session.getAttribute("msgDuplicado");
 	 DetalleCatagoBean detalleCatagoBean2 = (DetalleCatagoBean)session.getAttribute("detalleCatagoBean");
 	 String nombre = "";
 	 String valor  = "";
 	 String desc   = "";
 	 String attr1  = "";
 	 String attr2  = "";
 	 String attr3  = "";
 	 if(detalleCatagoBean2 != null && aumenta != null){
 		nombre = detalleCatagoBean2.getNombre().trim();
 		valor  = detalleCatagoBean2.getValor().trim();
 		desc   = detalleCatagoBean2.getDescripcion().trim();
 		attr1  = detalleCatagoBean2.getAttr1().trim();
 		attr2  = detalleCatagoBean2.getAttr2().trim();
 		attr3  = detalleCatagoBean2.getAttr3().trim();
 	 }
%>
<form id="frmNvoDetalle" name="frmNvoDetalle" method="post" action="/DerechoCorporativo/faces/GuardaDetalleCatalogServlet?action=nuevo&nomCata=<%= request.getParameter("nomCatalogo") %>&idCatalogo=<%=sId%>">
<p>&nbsp;</p>
<table width="80%" border="0" align="center" cellspacing="0" cellpadding="0">
	<tr>
		
		<th width="100%" align="left"><font style="color: red;"> <%= msgDupli == null ? "" : msgDupli %> </font></th>
		
	</tr>
	<tr>
		<th>&nbsp;</th>
	</tr>
	
  <tr style="background-image: url('/DerechoCorporativo/img/Back2.png')">
    <th width="100%" align="center"><font style="color: white;"> Nuevo detalle Cat&aacute;logo </font></th>
    <td align="right"><a href="#"><img src="/DerechoCorporativo/img/close.png" onclick="closeForm('<%= request.getParameter("nomCatalogo") %>','<%= idCata %>')"></a></td>
  </tr>
  
  <tr>
  	
  	<td colspan="2">
  	<input name="idCatalogo" type="hidden" value="<%= idCata %>">
  	<table width="80%" border="0">
  	<tr>
    <td><%= flexColumnsCatagoBean.getIdentificador() == null ? "Identificador" : flexColumnsCatagoBean.getIdentificador()%>:</td>
    <td>
      <input type="text" name="txtIdentificador" id="txtIdentificador" value="<%= id %>" readonly="readonly"/>
    </td>
  </tr>
  <tr>
    <td><%= flexColumnsCatagoBean.getNombre() == null ? "Nombre" : flexColumnsCatagoBean.getNombre() %>:</td>
    <td><textarea name="txtNombre" id="txtNombre" cols='50' rows='1' <%= request.getParameter("idCatalogo").equals("1")?"onkeyup='convertirAMayusculas(this);'":""%>><%=nombre%></textarea></td>
  </tr>
  <tr>
    <td><%= flexColumnsCatagoBean.getValor() == null ? "Valor" : flexColumnsCatagoBean.getValor() %>:</td>
    <td><textarea name="txtValor" id="txtValor" cols='50' rows='1' <%= request.getParameter("idCatalogo").equals("1")?"onkeyup='convertirAMayusculas(this);'":""%>><%=valor%></textarea></td>
  </tr>
  <tr>
    <td><%= flexColumnsCatagoBean.getDescripcion() == null ? "Descripción" : flexColumnsCatagoBean.getDescripcion() %>:</td>
    <td>
    <%
    	if(detalleCatagoBean.getIdCatinCat()!= 0 && detalleCatagoBean.getCampCatinCat().equals("txtDesc")) {
    %>
    	<%=SelectList.getSelectListCatin(conn, "txtDesc", "200", "", "", detalleCatagoBean.getIdCatinCat())%>  	
    <%  }else{  %>
    		<textarea name="txtDesc" id="txtDesc" cols='50' rows='1' <%= request.getParameter("idCatalogo").equals("1")?"onkeyup='convertirAMayusculas(this);'":""%>><%=desc%></textarea>
    <%  } %>
    </td>
  </tr>
  <tr>
    <td><%= flexColumnsCatagoBean.getAttr1() == null ? "Atributo1" : flexColumnsCatagoBean.getAttr1() %>:</td>
    <td>
     <%
    	if(detalleCatagoBean.getIdCatinCat()!= 0 && detalleCatagoBean.getCampCatinCat().equals("txtAttr1")) {
    %>
    	<%= SelectList.getSelectListCatin(conn, "txtAttr1", "200", "", "", detalleCatagoBean.getIdCatinCat())%>  	
    <%  }else{  %>
    		<textarea name="txtAttr1" id="txtAttr1" cols='50' rows='4' <%= request.getParameter("idCatalogo").equals("1")?"onkeyup='convertirAMayusculas(this);'":""%>><%=attr1%> </textarea>
    <%  } %>
    </td>
  </tr>
  <tr>
    <td><%= flexColumnsCatagoBean.getAttr2()  == null ? "Atributo2" : flexColumnsCatagoBean.getAttr2() %>:</td>
    <td>
    <%
    	if(detalleCatagoBean.getIdCatinCat()!= 0 && detalleCatagoBean.getCampCatinCat().equals("txtAttr2")) {
    %>
    	<%= SelectList.getSelectListCatin(conn, "txtAttr2", "200", "", "", detalleCatagoBean.getIdCatinCat())%>  	
    <%  }else{  %>
    		<textarea name="txtAttr2" id="txtAttr2" cols='50' rows='1' <%= request.getParameter("idCatalogo").equals("1")?"onkeyup='convertirAMayusculas(this);'":""%>><%=attr2%></textarea>
    <%  } %>
    </td>
  </tr>
  <tr>
    <td><%= flexColumnsCatagoBean.getAttr3() == null ? "Atributo3" : flexColumnsCatagoBean.getAttr3()%>:</td>
    <td>
    <%
    	if(detalleCatagoBean.getIdCatinCat()!= 0 && detalleCatagoBean.getCampCatinCat().equals("txtAttr3")) {
    %>
    	<%= SelectList.getSelectListCatin(conn, "txtAttr3", "200", "", "", detalleCatagoBean.getIdCatinCat())%>  	
    <%  }else{  
    		if(request.getParameter("idCatalogo").equals("1")){
    	%>
    	<%= SelectList.getSelectListCatinId(conn, "txtAttr3", "300", detalleCatagoBean.getStatusFlex(), detalleCatagoBean.getAttr3(), 59)%>
    	<%		
    		}else{
    	%>
    		<textarea name="txtAttr3" id="txtAttr3"  cols='50' rows='1' <%= request.getParameter("idCatalogo").equals("1")?"onkeyup='convertirAMayusculas(this);'":""%>><%=attr3%></textarea>
    <%  } 
    		}%>
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
		session.removeAttribute("detalleCatagoBean");
		conn.close();

} %>
</body>
</html>
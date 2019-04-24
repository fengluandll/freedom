<%@page import="java.util.StringTokenizer"%>
<%@page import="mx.com.televisa.derechocorporativo.modules.flextabs.FlexTable"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/css/kaz-styles.jsp"%>

<script src="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.min.js"></script> 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.css">


<%
/*	String params = request.getParameter("params");

	StringTokenizer tok = new StringTokenizer(params,"-");

	String idFlexTab = tok.nextToken();
	String key =  tok.nextToken();

	FlexTable ft = new FlexTable(idFlexTab);
*/
	String lstId = request.getParameter("id");
	String lstCod = request.getParameter("cod");
	String lstNom = request.getParameter("nom");
	String lstDes = request.getParameter("des");
	String lstVal = request.getParameter("val");
	
	//System.out.println("JSP:  "+lstId+" "+lstCod+" "+lstNom+" "+lstDes+" "+lstVal);
	
%>
<head>
<style type="text/css">
.tamText{
	width: 300px;
	height: 20px;
}

</style>
	<script type="text/javascript">
		function closeAbc_appConfig(){
			window.location = '/DerechoCorporativo/faces/jsp/admin/abc_appConfigTab.jsp';
		}
		
		function validaAbc_appConfig(){
			
			var vCod = document.getElementById('cod').value;
			var vNom = document.getElementById('nom').value;
			var vDes = document.getElementById('des').value;
			var vVal = document.getElementById('val').value;
			
			vCod = vCod.replace('&', '%26'); //Sustituir ampersand por URL Encoded
			vNom = vNom.replace('&', '%26'); //Sustituir ampersand por URL Encoded
			vDes = vDes.replace('&', '%26'); //Sustituir ampersand por URL Encoded
			vVal = vVal.replace('&', '%26'); //Sustituir ampersand por URL Encoded
			
			//alert('Entro validar: '+vCod+' '+vNom+' '+vDes+' '+vVal);
			
			if(vCod == ''){
				swal("Es requerido capturar el Código")
			}else if(vNom == ''){
				swal("Es requerido capturar el nombre")
			}else if(vDes == ''){
				swal("Es requerido capturar la descripción")
			}else if(vVal == ''){
				swal("Es requerido capturar el valor")
			}else{
				document.getElementById("form_Abc_AppConfigTabNewEdit").submit();
			}
		}
	</script>	
</head>

<div id='flexTable_Abc_AppConfigTabNewEdit'>
<form id="form_Abc_AppConfigTabNewEdit" name="form_Abc_AppConfigTabNewEdit" method="post" action="/DerechoCorporativo/faces/Abc_appConfigTabServlet">
<table width="80%" border="0" cellspacing="0" cellpadding="0" align="center">
						<tr style="background-image: url('../../img/Back2.png')">
							<th width="100%" align="center">
								<font style="color: white;">
									<%--ft.nomFlex --%> Editar Información
								</font>
							</th>
							<td  align="right">
								<a href="#"><img src="/DerechoCorporativo/img/close.png" onclick="closeAbc_appConfig()"></a>
							</td>
						</tr>
				     	
				      </table>
<table width="45%" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
				     		<td style="width: 150px;">Código</td>
					      	<td>															
								<input class="tamText" type="text" value='<%=lstCod==null?"":lstCod%>' id="cod" name="cod">
							</td>
				        </tr>
				        <tr>
				        	<td>
				        		&nbsp;
				        	</td>
				        </tr>
				     	<tr>
				     		<td>Nombre</td>
					      	<td>	
								<input class="tamText" type="text" value='<%=lstNom==null?"":lstNom%>' id="nom" name="nom">
							</td>
				        </tr>
				        <tr>
				        	<td>
				        		&nbsp;
				        	</td>
				        </tr>
				     	<tr>
				     		<td>Descripción</td><td>
					      	<input class="tamText" class="tamText" type="text" value='<%=lstDes==null?"":lstDes%>' id="des" name="des">	
							</td>
				        </tr>
				        <tr>
				        	<td>
				        		&nbsp;
				        	</td>
				        </tr>
				     	<tr>
							<td>Valor</td>
					      	<td>
					      		<%if(lstCod != null && lstCod.equals("PWD_DOC")){ %>	
								<%--	<input type="password" value='<%=lstVal==null?"":lstVal%>' id="val" name="val"> --%>
								<input class="tamText" type="password" value="" id="val" name="val">
								<%}else{ %>
									<input class="tamText" type="text" value='<%=lstVal==null?"":lstVal%>' id="val" name="val">
								<%} %>
							</td>
				        </tr>
                        <tr>
                          <td>
                            <input type="hidden" value='<%= lstId==null?"":lstId%>' id="id" name="id">
                          </td>
                        </tr>
					    <tr>
					      <td>&nbsp;</td>
					      <td colspan="2" class="btnRight">
					      		<img src='../../img/wait-bar.gif' id='imgFlexTableWait' style="display: none">
					      	<!-- <input type="button" id='flexTableformButton' Value='Guardar' onclick='flexTableSave()'> -->
						      	 <input type="button" value="Guardar" onclick="validaAbc_appConfig();">
						      	<!-- <input type="submit" value="Guardar" > -->
					      </td>
				        </tr>
</table>				      
</form>

</div>
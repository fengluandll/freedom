<%@page import="mx.com.televisa.derechocorporativo.model.Catalog"%>
<%@page import="mx.com.televisa.derechocorporativo.util.TextFormatter"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@page import="mx.com.televisa.derechocorporativo.components.JSCal"%>
<%@include file="/jsp/components/calendar/include_calendar.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
<%@include file="/css/kaz-styles.jsp"%>
<script src="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.css">
<%@include file="/jsp/components/session_timeout/session_timeout.jsp"%>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/ejercicio/ejercicio.js"></script>
<%
	String id = request.getParameter("id");

	Catalog.reloadCatPersonas();
%>

</head>
<body>
	<script type="text/javascript">
	
	function waitBar() {

		//document.getElementById('imgCapWait').style.display = '';
		//document.getElementById('btnEjecutar').style.display = 'none';
	}
	

        
        

        function openMultiSelectPupUp(catalogId, targetIds, targetNames, namesProperty, currentValue, namesFormat) {

            var left = screen.width - ((screen.width - 300) / 2);
            var top = (screen.height - 300) / 2;  // for 25% - devide by 4  |  for 33% - devide by 3

        	newwindow=window.open('../../components/multiselectPupUp/multiselect.jsp?catalogId=' + catalogId + '&targetIds=' + targetIds + '&targetNames=' + targetNames + '&currentValue=' + currentValue + '&namesProperty=' + namesProperty + '&namesFormat=' + namesFormat,
        						'name',
        						'height=600,width=450,toolbar=0,menubar=0,location=0,status=0,scrollbars=1,resizable=1,left=' + left + ',top=' + top);

        	if (window.focus) {newwindow.focus()}

        	return false;
        }
        
        function openMultiSelectPupUpRep(catalogId, targetIds, targetNames, currentValue, updateFieldCode, namesProperty, namesFormat) {
		
            var left = screen.width - ((screen.width - 300) / 2);
            var top = (screen.height - 300) / 2;  // for 25% - devide by 4  |  for 33% - devide by 3

        	newwindow=window.open('../../components/multiselectPupUp/multiselect.jsp?catalogId=' + catalogId + '&targetIds=' + targetIds + '&targetNames=' + targetNames + '&currentValue=' + currentValue + '&namesProperty=' + namesProperty +  '&updateFieldCode=' + updateFieldCode + '&namesFormat=' + namesFormat,
        						'name',
        						'height=300,width=400,toolbar=0,menubar=0,location=0,status=0,scrollbars=1,resizable=1,left=' + left + ',top=' + top);

        	if (window.focus) {newwindow.focus()}

        	return false;

        }

       

        function validaForm(){
        	
        	var bandera = false;
        	var nombre = document.getElementById('idHiFunHidd').value;
        	var empresas = document.getElementById('hiddenEmpresa').value;
        	
        	//var checks = document.getElementById('chHiFun').value;
        	//var checks = '';
        	var elems2 = document.querySelectorAll('input[type="checkbox"][class="check_HiFu"]');

        	for(var j = 0, len = elems2.length; j < len; j++){
    			if(elems2[j].checked){
    				bandera = true;
    			}
    		}

        	
        	//alert('nombre: '+nombre);

        	if(nombre == '' && empresas == ''){
        			swal("Es requerido capturar en el campo Nombre o Empresa(s)")
        	}else if(nombre != '' && empresas != ''){
        		swal("Sólo se permite seleccionar un filtro a la vez. Empresa(s) o Nombre(s)")
              }else if(!bandera){
        			swal("Es requerido seleccionar al menos checkbox")
        		}else {
        			document.getElementById('imgCapWait').style.display = '';
        			document.getElementById("frmHiFun").submit();
        			document.getElementById('btnEjecutar').style.display = 'none';
        		}
        				
			
        			
        		
        }


    </script>


	<jsp:include page="/jsp/components/pages_border/open-pre-title.jsp"></jsp:include>
		Reporte: Administración y Vigilancia
	<jsp:include page="/jsp/components/pages_border/open-post-title.jsp"></jsp:include>
	<jsp:include page="/jsp/components/backPageReports/regresaMenuReportes.jsp">
	
		<jsp:param name="action" value="rPredefinidos"/>
	</jsp:include>
  <form id="frmHiFun" name="frmHiFun" method="post" action="/DerechoCorporativo/jsp/reportes/admVig/printAdminVig.jsp">
	<table width="50%" align="center" style="text-align:center;" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td>
			<fieldset>
				<legend>Filtros:</legend>
				<div align="center">
					<table width="100%">	
					<tr>
					<td colspan="2">
						<b>Empresa(s):</b>
						<div id="divEmpresa"><%= (request.getParameter("txtEmpresa")!=null)?  TextFormatter.removeAccents( (new String(request.getParameter("txtEmpresa").getBytes("ISO-8859-1"),"UTF-8")) ) : ""%></div>
					</td>
			<td class="">	
					<%String hiddenEmpresa = 	(request.getParameter("hiddenEmpresa") != null) ? request.getParameter("hiddenEmpresa") : ""; 
					  %>
							<div align='right'>
						<img src='<%= request.getContextPath() %>/img/btn_search.png' 
										onclick="openMultiSelectPupUp('EMPRESAS','hiddenEmpresa','divEmpresa','innerHTML',document.getElementById('hiddenEmpresa').value,'UL')">
												
						<img src='<%= request.getContextPath() %>/img/btn_clean.png' 
										onclick="document.getElementById('hiddenEmpresa').value = '';document.getElementById('divEmpresa').innerHTML = '';">
						</div>
						
						
						<input type="hidden" id='hiddenEmpresa' name='hiddenEmpresa' value='<%=hiddenEmpresa%>' onfocus="chekAccionista()">
						
						
 						 <input type='hidden' id='txtEmpresa' name='txtEmpresa' 
							value="<%= (request.getParameter("txtEmpresa")!=null)? new String(request.getParameter("txtEmpresa").getBytes("ISO-8859-1"),"UTF-8") : ""%>"/>
													
				</td>
				</tr>
						
				<tr>	
							<td colspan="2">
								<b>Nombre(s):</b>
								<div id="idHiFunMultiList"></div>	
							</td>
							<td>
								<div align='right'>
									<img src='<%= request.getContextPath() %>/img/btn_search.png' onclick="openMultiSelectPupUpRep(
											'10'
											,'idHiFunHidd'
											,'idHiFunMultiList'
											,document.getElementById('idHiFunHidd').value
											,''
											,'innerHTML','UL')"/>		
	                                <img src='<%= request.getContextPath() %>/img/btn_clean.png' onclick="document.getElementById('idHiFunHidd').value = '';document.getElementById('idHiFunMultiList').innerHTML = '';">
		
								</div>
		
								<input type='hidden' name='idHiFunHidd'  id='idHiFunHidd' value=''>
									
								
							</td>
						</tr>
						
						<tr>
						<td colspan="4">
							<!--  <fieldset>  -->
							
							<table width="100%">
								<tr>
									<td>
										Administración
									</td>
									<td>
										<input type='checkbox' 
										       value='0'
										       name='chHiFun' 
										       id='chAdmHiFun'
										       class = 'check_HiFu'
										       checked="checked"
										       >
									</td>
								</tr>
								<tr>
									<td>
										Vigilancia
									</td>
									<td>
										<input type='checkbox' value='1' name='chHiFun' id='chVigHiFun' class = 'check_HiFu' checked="checked">
									</td>
							 	</tr>
					  
				<tr>
				<td colspan="2">
				<table id="tblAcc" width="100%">
							<tr>
								<td>
									&nbsp;
								</td>
							</tr>
				<tr>
					<td align="center" colspan="2">
						<input id="btnEjecutar" name="btnEjecutar" type="button" value="Generar" onclick="waitBar();validaForm();">
						<img src='<%=request.getContextPath()%>/img/wait-bar.gif'
						id='imgCapWait' style="display: none;" />
					</td>
				</tr>
			</table>
			</td>
			</tr>
						
			</table>
		</div>
	</fieldset>
	</td>
	</tr>

</table>		
 </form>
 


</body>
</html>
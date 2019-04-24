<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="mx.com.televisa.derechocorporativo.util.TextFormatter"%>
<%@page import="mx.com.televisa.derechocorporativo.util.FacesUtils"%>
<%@page import="mx.com.televisa.derechocorporativo.model.DetailApoderados"%>
<%@page import="mx.com.televisa.derechocorporativo.model.DetailApoderadosDTO"%>
<%@page import="mx.com.televisa.derechocorporativo.daos.MngDataPoderes"%>
<%@page import="mx.com.televisa.derechocorporativo.bean.GenericDataBean"%>
<%@page import="mx.com.televisa.derechocorporativo.bean.GenericBean"%>
<%@page import="java.util.Date"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>apoderados captura</title>

<%@include file="/css/kaz-styles.jsp"%>
<script src="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.min.js"></script> 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.css">
<%@include file="/jsp/components/session_timeout/session_timeout.jsp"%>
<script src="/DerechoCorporativo/js/jquery/jquery-2.1.4.min.js" type="text/javascript"></script>
<script src="/DerechoCorporativo/css/jquery-ui-css/pendium/jquery-ui.js" type="text/javascript"></script>

<script type="text/javascript">

function soloNumeros(object){
	var texto  = object.value;
	if(texto!=null)
		for(c=0;c<texto.length;c++){
			if(isNaN(texto.charAt(c)))
				object.value = object.value.replace(texto.charAt(c),"");
		}
}

function maskEsc(valCampo){
	if(valCampo != null){
		var val2 = valCampo.value.trim();
		$(valCampo).val(commaSeparateNumber(val2));
	}

}

function commaSeparateNumber(val){

    while (/(\d+)(\d{3})/.test(val.toString())){
      val = val.toString().replace(/(\d+)(\d{3})/, '$1'+','+'$2');
    }
    return val;
}

function waitBar() {
	
	document.getElementById('imgCapWait').style.display = '';
	document.getElementById('btnEjecutar').style.display = 'none';
}

function copyParamsDivToHidden() {
	
	document.getElementById('txtEmpresa').value = document.getElementById('divEmpresa').innerHTML;  	
	document.getElementById('txtTipoPoder').value = document.getElementById('divTipoPoder').innerHTML;
	document.getElementById('txtPoder').value = document.getElementById('divPoder').innerHTML;
	document.getElementById('txtApoderados').value = document.getElementById('divApoderados').innerHTML;
	
}

	
function openSelectPupUp(catalogId, targetIds, targetNames, namesProperty, currentValue) {

    var left = screen.width - ((screen.width - 300) / 2);
    var top = (screen.height - 300) / 2;  // for 25% - devide by 4  |  for 33% - devide by 3

	newwindow=window.open('../../components/simpleSelectPupUp/simpleselect.jsp?catalogId=' + catalogId + '&targetIds=' + targetIds + '&targetNames=' + targetNames + '&currentValue=' + currentValue + '&namesProperty=' + namesProperty,
						'name',
						'height=600,width=450,toolbar=0,menubar=0,location=0,status=0,scrollbars=1,resizable=1,left=' + left + ',top=' + top);

	if (window.focus) {newwindow.focus()}

	return false;
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


function openMultiSelectPupUpAncho(catalogId, targetIds, targetNames, namesProperty, currentValue, namesFormat) {

    var left = screen.width - ((screen.width - 700) / 2);
    var top = (screen.height - 300) / 2;  // for 25% - devide by 4  |  for 33% - devide by 3

	newwindow=window.open('../../components/multiselectPupUp/multiselect.jsp?catalogId=' + catalogId + '&targetIds=' + targetIds + '&targetNames=' + targetNames + '&currentValue=' + currentValue + '&namesProperty=' + namesProperty + '&namesFormat=' + namesFormat + '&actionApod=apodActiva',
						'name',
						'height=600,width=700,toolbar=0,menubar=0,location=0,status=0,scrollbars=1,resizable=1,left=' + left + ',top=' + top);

	if (window.focus) {newwindow.focus()}

	return false;
}

$(document).ready(function(){
	$("#txtEscritura").keyup(function(){  		
		soloNumeros(this);
	});
	
	$("#txtEscritura").blur(function(){                		
		maskEsc(this);
	});    
});

</script>

</head>
<body>

			<jsp:include page="/jsp/components/pages_border/open-pre-title.jsp"></jsp:include>
			
				Poderes por Apoderados
			
			<jsp:include page="/jsp/components/pages_border/open-post-title.jsp"></jsp:include>
			<jsp:include page="/jsp/components/backPageReports/regresaMenuReportes.jsp">
				<jsp:param name="action" value="rPredefinidos"/>
			</jsp:include>
<form action="" method="post">


<table width="60%" align="center">
	<tr>
		<td colspan="3">

<%

	request.setCharacterEncoding("UTF-8");

	String btnEjecutar = request.getParameter("btnEjecutar"); 
	String psHiFuOut = request.getParameter("psHiFuOut");

	String hiddenEmpresa = 	(request.getParameter("hiddenEmpresa") != null) ? request.getParameter("hiddenEmpresa") : "";
	String hiddenTipoPoder = 	(request.getParameter("hiddenTipoPoder") != null) ? request.getParameter("hiddenTipoPoder") : "";	
	String hiddenApoderados = (request.getParameter("hiddenApoderados") != null) ? request.getParameter("hiddenApoderados") : "";
	String hiddenPoder = (request.getParameter("hiddenPoder") != null) ? request.getParameter("hiddenPoder") : "";	
	
	String txtEmpresa = 	(request.getParameter("txtEmpresa") != null) ? request.getParameter("txtEmpresa") : "";
	String txtTipoPoder = 	(request.getParameter("txtTipoPoder") != null) ? request.getParameter("txtTipoPoder") : "";
	String txtApoderados = (request.getParameter("txtApoderados") != null) ? request.getParameter("txtApoderados") : "";
	String txtPoder = (request.getParameter("txtPoder") != null) ? request.getParameter("txtPoder") : "";
	String txtEscritura = (request.getParameter("txtEscritura") != null) ? request.getParameter("txtEscritura") : "";	

	String paramString = 	"hiddenEmpresa=" + hiddenEmpresa +
							"&hiddenTipoPoder=" + hiddenTipoPoder +
							"&txtEscritura=" + txtEscritura +
							"&hiddenApoderados=" + hiddenApoderados +
							"&hiddenPoder=" + hiddenPoder +
							"&txtEmpresa=" + txtEmpresa.replace("&","%26") +	
							"&txtTipoPoder=" + txtTipoPoder +
							"&txtApoderados=" + txtApoderados +
							"&txtPoder=" + txtPoder +
							"&btnEjecutar=Generar"
							;
	
	boolean generar = btnEjecutar != null && btnEjecutar.equals("Generar");
%>
		</td>
	</tr>	
	<tr>
		<td colspan="3">
			<fieldset>
			<legend style="text-align:center">Filtros</legend>
				<table width="100%" border='0' cellpadding="3" cellspacing="3" align="center">
				
					<tr>
						
						<td width="30%"><b>Empresa(s):</b>
						<div id="divEmpresa"><%= (request.getParameter("txtEmpresa")!=null)?  txtEmpresa : ""%></div>
						</td>
						
						<td>	
							<div align='right'>
								<img src='<%= request.getContextPath() %>/img/btn_search.png' 
												onclick="openMultiSelectPupUp('EMPRESAS','hiddenEmpresa','divEmpresa','innerHTML',document.getElementById('hiddenEmpresa').value,'None')">																						
							</div>
														
							<input type="hidden" id='hiddenEmpresa' name='hiddenEmpresa' value='<%=hiddenEmpresa%>'>
													
	 						<input type='hidden' id='txtEmpresa' name='txtEmpresa' 
								value="<%= (request.getParameter("txtEmpresa")!=null)? txtEmpresa : ""%>">
	 																	
						</td>
						<td><img src='<%= request.getContextPath() %>/img/btn_clean.png' 
												onclick="document.getElementById('hiddenEmpresa').value = '';document.getElementById('divEmpresa').innerHTML = '';"></td>
					</tr>
					<tr>
						
						<td width="90%" ><b>Tipo de Poder:</b>
							<div id="divTipoPoder"><%= (request.getParameter("txtTipoPoder")!=null)? TextFormatter.removeAccents( (new String(request.getParameter("txtTipoPoder").getBytes("ISO-8859-1"),"UTF-8")) ) : ""%></div>
						</td>
						<td>	
							<div align='right'>
							<img src='<%= request.getContextPath() %>/img/btn_search.png' 
											onclick="openMultiSelectPupUp('41','hiddenTipoPoder','divTipoPoder','innerHTML',document.getElementById('hiddenTipoPoder').value,'UL')">									
							</div>
							
							<input type="hidden" id='hiddenTipoPoder' name='hiddenTipoPoder' value='<%=hiddenTipoPoder%>'>
													
							<input type='hidden' id='txtTipoPoder' name='txtTipoPoder' 
							value="<%= (request.getParameter("txtTipoPoder")!=null)? new String(request.getParameter("txtTipoPoder").getBytes("ISO-8859-1"),"UTF-8") : ""%>">														
											
						</td>
						<td><img src='<%= request.getContextPath() %>/img/btn_clean.png' 
											onclick="document.getElementById('hiddenTipoPoder').value = '';document.getElementById('divTipoPoder').innerHTML = '';"></td>
					</tr>
				    <tr>
				    	<td><b>Nombres de Apoderados:</b>
				    		<div id="divApoderados"><%= (request.getParameter("txtApoderados")!=null)? txtApoderados: ""%></div>
				    	</td>
				    	<td>
							
							<div align='right'>
													
							
							<img src='<%= request.getContextPath() %>/img/btn_search.png' 
											onclick="openMultiSelectPupUp('32','hiddenApoderados','divApoderados','innerHTML',document.getElementById('hiddenApoderados').value,'None')">									
							
							</div>
														
							<input type="hidden" id='hiddenApoderados' name='hiddenApoderados' value='<%=hiddenApoderados%>'>
													
								<input type='hidden' id='txtApoderados' name='txtApoderados' 
								value="<%= (request.getParameter("txtApoderados")!=null)? txtApoderados : ""%>">
													
						</td>
						<td><img src='<%= request.getContextPath() %>/img/btn_clean.png' 
											onclick="document.getElementById('hiddenApoderados').value = '';document.getElementById('divApoderados').innerHTML = '';"></td>
					</tr>
					<tr>
						<td><b>Poder:</b>
							<div id="divPoder"><%= (request.getParameter("txtPoder")!=null)? txtPoder: ""%></div>
						</td>
						<td>
							<div align='right'>
							<img src='<%= request.getContextPath() %>/img/btn_search.png' 
											onclick="openMultiSelectPupUpAncho('PODERES','hiddenPoder','divPoder','innerHTML',document.getElementById('hiddenPoder').value,'None')">									
							</div>
							
							<input type="hidden" id='hiddenPoder' name='hiddenPoder' value="<%=hiddenPoder%>"/>
													
							<input type='hidden' id='txtPoder' name='txtPoder' 
							value="<%= (request.getParameter("txtPoder")!=null)? txtPoder : ""%>">
														
						</td>
						<td><img src='<%= request.getContextPath() %>/img/btn_clean.png' 
											onclick="document.getElementById('hiddenPoder').value = '';document.getElementById('divPoder').innerHTML = '';"></td>
					</tr>
					<tr>
						
						<td><b>Escritura:</b></td>
						<td>&nbsp;</td>					
					</tr>
					<tr>
						
						<td>
								<input id='txtEscritura' name='txtEscritura' size="40" maxlength="6" value="<%= (request.getParameter("txtEscritura")!=null)? request.getParameter("txtEscritura") : ""%>">
						</td>
						<td>&nbsp;</td>
						<td></td>
						<td></td>
						<td></td>
					</tr>					
				</table>
			</fieldset>
		</td>
	</tr>
	<tr>
		<td colspan="3">
			<fieldset>			
				<legend style="text-align:center">Exportar a:</legend>	
				<div align="center">
					<select name="psHiFuOut" style="width: 180px;">
						<option value="PDF">PDF</option>
						<% 
						if(psHiFuOut != null){
							if(psHiFuOut.equals("EXCEL")){ 
						%>
								<option value="EXCEL" selected>Excel</option>
						<%  }
							else{
						%>
								<option value="EXCEL">Excel</option>
						<%
							}
						}
						else{%>
							<option value="EXCEL">Excel</option>
						<%}%>	
					</select>								
					<input type="submit" id='btnEjecutar' name='btnEjecutar' value='Generar' onclick="waitBar();copyParamsDivToHidden();">
					<img src='<%= request.getContextPath() %>/img/wait-bar.gif' id='imgCapWait' style="display: none;">
				</div>					
										
			</fieldset>
		</td>
	</tr>
	
</table>

</form>
	
<div align="right">
	<br>		
	<%
		if(generar && psHiFuOut !=null && psHiFuOut.equals("PDF")) {			
	%>
				<a href="PoderesPorApoderadoPrint.jsp?<%=paramString%>" target="_blank">Exportar a PDF</a>						
	<%
		}
		else if(generar && psHiFuOut !=null && psHiFuOut.equals("EXCEL")) {
	%>
			<a href="/DerechoCorporativo/ExportExcelPoderesServlet?<%=paramString + "&action=generarExcelPoderesPorApoderado"%>">Exportar a Excel</a>
	<%  } %>		
</div>

<%
String htmlout = "";
if(generar) {									
	String tipoPoder = txtTipoPoder.replace("<ul><li>", "'");
	tipoPoder = tipoPoder.replace("</li><li>","','");
	tipoPoder = tipoPoder.replace("</li></ul>", "'");
	tipoPoder = tipoPoder.equals("<ul></ul>") ? null : tipoPoder;
	tipoPoder = tipoPoder.replace("Carta Poder","CP").replace("Poder Especial","PE").replace("Poder General","PG");
	String escritura = txtEscritura.equals("") ? "" : "'%"+txtEscritura+"%'";					
	GenericDataBean gdb = MngDataPoderes.query_PODERES_POR_APODERADOS(hiddenEmpresa, tipoPoder, hiddenApoderados, hiddenPoder, escritura);					
	if(gdb.beans.size() > 0){					
		String ApoderadoCurrent = "";
		String EmpresaCurrent = "";
		String TipoPoderCurrent = "";
		String rowPrintPG = "<tr CLASS><td>ESCPD</td><td>FECPD</td><td>PODPD</td><td>AD9999</td><td>AA9999</td><td>TC9999</td><td>PC9999</td><td>EP9999</td><td>VIGCIA</td></tr>";					
		String rowPrintPE = "<tr CLASS><td>ESCPD</td><td>FECPD</td><td>PODPD</td><td>DESPD</td><td>VIGCIA</td></tr>";
		String rowPrintCP = "<tr CLASS><td>FECPD</td><td>PODPD</td><td>DESPD</td><td>VIGCIA</td></tr>";
		String rowPrint = "";									
		for(int i=0; i < gdb.beans.size(); i++){
			GenericBean bean = gdb.get(i);
			
			
			String Apoderado = bean.getProperty("DESC_NOM_EMPL").toString();												
			String Empresa = bean.getProperty("NOM_EMPRESA").toString();
			String TipoPoder = bean.getProperty("IND_TIPO_ESCRITURA").toString();						 					
			
			if(!ApoderadoCurrent.equals(Apoderado)){							 							 
				htmlout += "<br/><br/><div><h4 align='center'>" + Apoderado + "</h4>";																			
				ApoderadoCurrent = Apoderado;	
				EmpresaCurrent = "";							
			}
			
			if(!EmpresaCurrent.equals(Empresa)){							 							 
				htmlout += "<h4>" + Empresa + "</h4>";																			
				EmpresaCurrent = Empresa;	
				TipoPoderCurrent = "";							
				
			}
			if(!TipoPoderCurrent.equals(TipoPoder)){								 								 
				htmlout += "<h4>"+(TipoPoder.equals("PG") ? "Poderes Generales" : TipoPoder.equals("PE") ? "Poderes Especiales" : TipoPoder.equals("CP") ? "Cartas Poder" : "Escrituras de Revocación") + "</h4>";
				TipoPoderCurrent = TipoPoder;
			}
				
				switch(TipoPoder){
				case "PG": 
					htmlout += "<table cellspacing='0' cellpadding='3' width='100%'><tr><th class='tableHeader' width='3%'>Escritura No.</th><th class='tableHeader' width='3%'>Fecha</th><th class='tableHeader' width='10%'>Poder</th><th class='tableHeader' width='16%'>Actos de Dominio</th><th class='tableHeader' width='16%'>Actos de Administración</th><th class='tableHeader' width='16%'>Títulos de Crédito</th><th class='tableHeader' width='16%'>Pleitos y Cobranzas</th><th class='tableHeader' width='16%'>Especial</th><th class='tableHeader' width='4%'>Vigencia</th><tr>";
					rowPrint = rowPrintPG;
					break;
				case "CP": 
					htmlout += "<table cellspacing='0' cellpadding='3' width='100%'><tr><th class='tableHeader' width='10%'>Fecha</th><th class='tableHeader' width='10%'>Poder</th><th class='tableHeader' width='70%'>Descripción</th><th class='tableHeader' width='10%'>Vigencia</th><tr>";
					rowPrint = rowPrintCP;
					break;
				case "PE": 
					htmlout += "<table cellspacing='0' cellpadding='3' width='100%'><tr><th class='tableHeader' width='10%'>Escritura No.</th><th class='tableHeader' width='10%'>Fecha</th><th class='tableHeader' width='10%'>Poder</th><th class='tableHeader' width='60%'>Descripción</th><th class='tableHeader' width='10%'>Vigencia</th><tr>";
					rowPrint = rowPrintPE;
					break;
				}
								
			String esc = bean.getProperty("DES_ESCRITURA") == null ? "" : bean.getProperty("DES_ESCRITURA") == null ? "" : bean.getProperty("DES_ESCRITURA").toString();
			String fec = bean.getProperty("FEC_OTORGAMIENTO_INSTR") == null ? "" : bean.getProperty("FEC_OTORGAMIENTO_INSTR").toString();
			String fecCP = bean.getProperty("FEC_FECHA") == null ? "" : bean.getProperty("FEC_FECHA").toString();
			String pod = bean.getProperty("DES_PODERTIPO") == null ? "" : bean.getProperty("DES_PODERTIPO").toString();
			String ad = bean.getProperty("DESC_ACTOSDOMINIO") == null ? "" : bean.getProperty("DESC_ACTOSDOMINIO").toString();
			String aa = bean.getProperty("DESC_ACTOSADMON") == null ? "" : bean.getProperty("DESC_ACTOSADMON").toString();
			String tc = bean.getProperty("DESC_TITULOSCREDITO") == null ? "" : bean.getProperty("DESC_TITULOSCREDITO").toString();
			String pc = bean.getProperty("DESC_PLEITOSCOBRANZA") == null ? "" : bean.getProperty("DESC_PLEITOSCOBRANZA").toString();

			Date datevig = bean.getStrToDateProperty("FEC_VIGENCIAFIN","SHORT");				
			String vig = bean.getProperty("FEC_VIGENCIAFIN") == null ? "" : bean.getProperty("FEC_VIGENCIAFIN").toString();
			Date Now = new Date();
			if(!vig.equals("")){
				long sd = datevig.getTime();
				long sn = Now.getTime();
				if( sd <= sn )
					vig = "<label style=\"color:red\">" + vig + "</label>";
			}
		
			String iRevoca = bean.getProperty("IND_APREVOCA") == null ? null : bean.getProperty("IND_APREVOCA").toString();
			String descRev = bean.getProperty("DESC_REVOCA") == null ? "" : bean.getProperty("DESC_REVOCA").toString();
			
			String desc = bean.getProperty("DESC_DESCRIPCION") == null ? "" : bean.getProperty("DESC_DESCRIPCION").toString();
			String descEsp = bean.getProperty("DES_PODER") == null ? "" : bean.getProperty("DES_PODER").toString();
			descEsp = descEsp.replace("null", "");
			String descEspecial = bean.getProperty("DESC_PODER_ESPECIAL") == null ? "" : bean.getProperty("DESC_PODER_ESPECIAL").toString();
			descEspecial = descEspecial.replace("&#9679;","&#8226;" ).replace("null", "");
			
			String row = "";					
			switch(TipoPoder){
			case "PG": 
				row = rowPrint.replace("ESCPD", esc).replace("FECPD", fec).replace("PODPD", pod).replace("AD9999", ad).replace("AA9999", aa).replace("TC9999", tc).replace("PC9999", pc).replace("EP9999", descEspecial).replace("VIGCIA", vig);	
				break;
			case "CP": 
				row = rowPrint.replace("FECPD", fecCP).replace("PODPD", pod).replace("DESPD", descEsp).replace("VIGCIA", vig);	
				break;
			case "PE": 
				row = rowPrint.replace("ESCPD", esc).replace("FECPD", fec).replace("PODPD", pod).replace("DESPD", descEsp).replace("VIGCIA", vig);	
				break;
			}
			
			row = row.replace("CLASS", i%2 == 0 ? "" : "class='tableRow2'" );
			
			htmlout+=row + "<br/>";
			htmlout+="</table></div>";	
			htmlout += iRevoca == null ? "" : "<br/><div>" + descRev + "</div><br/>";
		}
	htmlout = htmlout.equals("") ? htmlout : htmlout;
	}else
		htmlout="No se encontraron registros";
}	
%>												

<div id="summary">
	<%=htmlout %>
</div>		
	
<jsp:include page="/jsp/components/pages_border/close.jsp"></jsp:include>

</body>
</html>
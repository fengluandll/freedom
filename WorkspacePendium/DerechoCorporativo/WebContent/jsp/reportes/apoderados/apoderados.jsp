<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="mx.com.televisa.derechocorporativo.util.TextFormatter"%>
<%@page import="mx.com.televisa.derechocorporativo.util.FacesUtils"%>
<%@page import="mx.com.televisa.derechocorporativo.daos.MngDataPoderes"%>
<%@page import="mx.com.televisa.derechocorporativo.bean.GenericDataBean"%>
<%@page import="mx.com.televisa.derechocorporativo.bean.GenericBean"%>
<%@page import="java.util.Date"%>

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
			 		
	String htmlout = "";	
	if(btnEjecutar != null && btnEjecutar.equals("Generar")) {						
		
		String tipoPoder = txtTipoPoder.replace("<ul><li>", "'");
		tipoPoder = tipoPoder.replace("</li><li>","','");
		tipoPoder = tipoPoder.replace("</li></ul>", "'");
		tipoPoder = tipoPoder.equals("<ul></ul>") ? null : tipoPoder;
		tipoPoder = tipoPoder.replace("Carta Poder","CP").replace("Poder Especial","PE").replace("Poder General","PG");
				
		String apoderados = txtApoderados.replace("</li><li>","%' OR PENDIUM_OTORGAPODER_EP_TAB.DESC_APODERADOS LIKE '%");
		apoderados = apoderados.replace("<ul><li>","PENDIUM_OTORGAPODER_EP_TAB.DESC_APODERADOS LIKE '%");
		apoderados = apoderados.replace("</li></ul>","%'");
		apoderados = apoderados.equals("<ul></ul>") ? null : apoderados;		
		
		String escritura = txtEscritura.equals("") ? "" : "'%"+txtEscritura+"%'";
		
		GenericDataBean gdb = MngDataPoderes.query_PODERES_POR_EMPRESA(hiddenEmpresa, tipoPoder, apoderados, hiddenPoder, escritura);
		
		String EmpresaCurrent = "";
		String TipoPoderCurrent = "";
		String rowPrintPG = "<tr style='vertical-align:top' CLASS><td>ESCPD</td><td>FECPD</td><td>APOPD</td><td>PODPD</td><td>AD9999</td><td>AA9999</td><td>TC9999</td><td>PC9999</td><td>EP9999</td><td>VIGCIA</td></tr>";
		String rowPrintPE = "<tr style='vertical-align:top' CLASS><td>ESCPD</td><td>FECPD</td><td>PODPD</td><td>APOPD</td><td>DESPD</td><td>VIGCIA</td></tr>";
		String rowPrintCP = "<tr style='vertical-align:top' CLASS><td>FECPD</td><td>PODPD</td><td>APOPD</td><td>DESPD</td><td>VIGCIA</td></tr>";
		String rowPrint = "";
		String apendiceRevocados = "";
		
		if(gdb.beans.size() == 0) 
			htmlout = "No se encontraron registros";
						
		for(int i=0; i < gdb.beans.size(); i++){
			GenericBean beanApoderados = gdb.get(i);
			String Empresa = beanApoderados.getProperty("NOM_EMPRESA").toString();
			String TipoPoder = beanApoderados.getProperty("IND_TIPO_ESCRITURA").toString();
								
			if(!EmpresaCurrent.equals(Empresa)){																						
				htmlout += "<br><br><div><h4 align='center'>" + Empresa + "</h4>";																			
				EmpresaCurrent = Empresa;	
				TipoPoderCurrent = "";											
			}
			
			apendiceRevocados = "";
			if(!TipoPoderCurrent.equals(TipoPoder)){
				htmlout += "<h4>"+(TipoPoder.equals("PG") ? "Poderes Generales" : TipoPoder.equals("PE") ? "Poderes Especiales" : TipoPoder.equals("CP") ? "Cartas Poder" : "Escrituras de Revocación") + "</h4>";
				TipoPoderCurrent = TipoPoder;
			}								
						
			String esc = beanApoderados.getProperty("DES_ESCRITURA") == null ? "" : beanApoderados.getProperty("DES_ESCRITURA") == null ? "" : beanApoderados.getProperty("DES_ESCRITURA").toString();
			int idOtorgaPoder = Integer.valueOf(beanApoderados.getProperty("ID_OPODER_EP_PK").toString());
			String fec = beanApoderados.getProperty("FEC_OTORGAMIENTO_INSTR") == null ? "" : beanApoderados.getProperty("FEC_OTORGAMIENTO_INSTR").toString();
			String fecCP = beanApoderados.getProperty("FEC_FECHA") == null ? "" : beanApoderados.getProperty("FEC_FECHA").toString();
			String pod = beanApoderados.getProperty("DES_PODERTIPO") == null ? "" : beanApoderados.getProperty("DES_PODERTIPO").toString();
			String apo = beanApoderados.getProperty("DESC_APODERADOS") == null ? "" : beanApoderados.getProperty("DESC_APODERADOS").toString();
			String ad = beanApoderados.getProperty("DESC_ACTOSDOMINIO") == null ? "" : beanApoderados.getProperty("DESC_ACTOSDOMINIO").toString();
			String aa = beanApoderados.getProperty("DESC_ACTOSADMON") == null ? "" : beanApoderados.getProperty("DESC_ACTOSADMON").toString();
			String tc = beanApoderados.getProperty("DESC_TITULOSCREDITO") == null ? "" : beanApoderados.getProperty("DESC_TITULOSCREDITO").toString();
			String pc = beanApoderados.getProperty("DESC_PLEITOSCOBRANZA") == null ? "" : beanApoderados.getProperty("DESC_PLEITOSCOBRANZA").toString();
			
			Date datevig = beanApoderados.getStrToDateProperty("FEC_VIGENCIAFIN","SHORT");				
			String vig = beanApoderados.getProperty("FEC_VIGENCIAFIN") == null ? "" : beanApoderados.getProperty("FEC_VIGENCIAFIN").toString();
			Date Now = new Date();
			if(!vig.equals("")){
				long sd = datevig.getTime();
				long sn = Now.getTime();
				if( sd <= sn )
					vig = "<label style=\"color:red\">" + vig + "</label>";
			}
			
			String desc = beanApoderados.getProperty("DESC_DESCRIPCION") == null ? "" : beanApoderados.getProperty("DESC_DESCRIPCION").toString();
			String descEsp = beanApoderados.getProperty("DES_PODER") == null ? "" : beanApoderados.getProperty("DES_PODER").toString();
			descEsp = descEsp.replace("null", "");
			apo = apo.replace("null","");
			
			GenericDataBean gdbRevocaciones = MngDataPoderes.query_REVOCACIONES(idOtorgaPoder);
			for(int j=0; j < gdbRevocaciones.beans.size(); j++){
				GenericBean beanRevocacion = gdbRevocaciones.get(j);	
				String apendiceRevocadoCurrent = "";								
				  apendiceRevocadoCurrent = beanRevocacion.getProperty("DESC_REVOCA") == null ? "" : beanRevocacion.getProperty("DESC_REVOCA").toString(); 
				  if(!apendiceRevocados.contains(apendiceRevocadoCurrent)){						
					  apendiceRevocados += apendiceRevocadoCurrent.trim().length()>0 ? "<i style='font-size:8pt'>" + apendiceRevocadoCurrent + "</i><br/>" : "";	
				  }									  
			}
			String descEspecial = beanApoderados.getProperty("DESC_PODER_ESPECIAL") == null ? "" : beanApoderados.getProperty("DESC_PODER_ESPECIAL").toString();
			descEspecial = descEspecial.replace("&#9679;","&#8226;" ).replace("null", "");
			
			String row = "";					
			
			if(i==0 || !beanApoderados.getProperty("ID_OPODER_EP_PK").toString().equals(gdb.get(i-1).getProperty("ID_OPODER_EP_PK").toString())){
				switch(TipoPoder){
				case "PG": 
					htmlout += "<table cellspacing='0' cellpadding='3' width='1650px' style='table-layout:fixed;'><tr><th class='tableHeader' width='5%'>Escritura No.</th><th class='tableHeader' width='5%'>Fecha</th><th class='tableHeader' width='15%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Apoderados&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th><th class='tableHeader' width='5%'>Poder</th><th class='tableHeader' width='12%'>Actos de Dominio</th><th class='tableHeader' width='12%'>Actos de Administración</th><th class='tableHeader' width='12%'>Títulos de Crédito</th><th class='tableHeader' width='12%'>Pleitos y Cobranzas</th><th class='tableHeader' width='10%'>Especial</th><th class='tableHeader' width='5%'>Vigencia</th></tr>";
					rowPrint = rowPrintPG;
					row = rowPrint.replace("ESCPD", esc).replace("FECPD", fec).replace("APOPD", apo).replace("PODPD", pod).replace("AD9999", ad).replace("AA9999", aa).replace("TC9999", tc).replace("PC9999", pc).replace("EP9999", descEspecial).replace("VIGCIA", vig);	
					break;
				case "CP": 
					htmlout += "<table cellspacing='0' cellpadding='3' width='100%'><tr><th class='tableHeader' width='10%'>Fecha</th><th class='tableHeader' width='10%'>Tipo Poder</th><th class='tableHeader' width='35%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Apoderados&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th><th class='tableHeader' width='35%'>Descripción</th><th class='tableHeader' width='10%'>Vigencia</th></tr>";
					rowPrint = rowPrintCP;
					row = rowPrint.replace("FECPD", fecCP).replace("PODPD", pod).replace("APOPD", apo).replace("DESPD", descEsp).replace("VIGCIA", vig);	
					break;
				case "PE": 
					htmlout += "<table cellspacing='0' cellpadding='3' width='100%'><tr><th class='tableHeader' width='10%'>Escritura No.</th><th class='tableHeader' width='10%'>Fecha</th><th class='tableHeader' width='10%'>Tipo Poder</th><th class='tableHeader' width='30%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Apoderados&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th><th class='tableHeader' width='30%'>Descripción</th><th class='tableHeader' width='10%'>Vigencia</th></tr>";
					rowPrint = rowPrintPE;
					row = rowPrint.replace("ESCPD", esc).replace("FECPD", fec).replace("PODPD", pod).replace("APOPD", apo).replace("DESPD", descEsp).replace("VIGCIA", vig);
					break;
				}
				
				row = row.replace("CLASS", i%2 == 0 ? "" : "class='tableRow2'" );
				
				htmlout+=row + "<br/>";
				htmlout+="</table></div>"; 
				htmlout += (apendiceRevocados.trim().length()>0 ? "<br/><div>" + apendiceRevocados + "</div><br/>" : "");
			}
		}										
	}
		
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Reporte de Apoderados</title>

<%@include file="/css/kaz-styles.jsp"%>
<script src="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.min.js"></script> 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.css">
<%@include file="/jsp/components/session_timeout/session_timeout.jsp"%>
<link href="/DerechoCorporativo/css/webCore.css" rel="stylesheet" type="text/css" />
    <link href="/DerechoCorporativo/css/jquery-ui-css/pendium/jquery-ui.css" rel="stylesheet" type="text/css" />
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
	
	var empresa = document.getElementById('divEmpresa').innerHTML;
		
	document.getElementById('txtEmpresa').value = empresa;
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
			
				Poderes por Empresa
			
			<jsp:include page="/jsp/components/pages_border/open-post-title.jsp"></jsp:include>
			<jsp:include page="/jsp/components/backPageReports/regresaMenuReportes.jsp">
				<jsp:param name="action" value="rPredefinidos"/>
			</jsp:include>
<form action="" method="post">


<table width="60%" align="center">		
	<tr>
		<td colspan="3">
		<fieldset>
			<legend style="text-align:center">Filtros</legend>
				<table width="100%" border='0' cellpadding="3" cellspacing="3">
				
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
								<input id='txtEscritura' name='txtEscritura' size="40" maxlength="6" value="<%= (request.getParameter("txtEscritura")!=null)? request.getParameter("txtEscritura") : ""%>"/>
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
</table>

<table width="60%" align="center">					
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
		if(btnEjecutar != null && btnEjecutar.equals("Generar") && psHiFuOut !=null && psHiFuOut.equals("PDF")) {			
	%>
			<a style="float:right" href="apoderadosPrint.jsp?<%=paramString%>" target="_blank">Exportar a PDF</a>					
	<%
		}
		else if(btnEjecutar != null && btnEjecutar.equals("Generar") && psHiFuOut !=null && psHiFuOut.equals("EXCEL")) {
	%>
			<a href="/DerechoCorporativo/ExportExcelPoderesServlet?<%=paramString + "&action=generarExcelPoderesPorEmpresa"%>">Exportar a Excel</a>
	<%  } %>					
</div>

<div id="summary">

	<%=htmlout %>

</div>	

<jsp:include page="/jsp/components/pages_border/close.jsp"></jsp:include>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="mx.com.televisa.derechocorporativo.util.TextFormatter"%>
<%@page import="mx.com.televisa.derechocorporativo.util.FacesUtils"%>
<%@page import="mx.com.televisa.derechocorporativo.daos.MngDataPoderes"%>
<%@page import="mx.com.televisa.derechocorporativo.bean.GenericDataBean"%>
<%@page import="mx.com.televisa.derechocorporativo.bean.GenericBean"%>
<%@page import="java.util.Date"%>
<%!
	String htmlout = "";	
	String empresaCurrent = "";
	String tipoPoderCurrent = "";	
	String facultadCurrent = "";
	String rowPrint = "";
	String apendiceRevocados = "";	
	String rowPrintPG = "";
	String rowPrintPE = "";
	String rowPrintCP = "";
	String filterAD = null;
	String filterAA = null;
	String filterTC = null;
	String filterPC = null;
%>

<%
	request.setCharacterEncoding("UTF-8");
	
	htmlout = "";	
	empresaCurrent = "";
	tipoPoderCurrent = "";	
	facultadCurrent = "";
	rowPrint = "";
	apendiceRevocados = "";	
	rowPrintPG = "";
	rowPrintPE = "";
	rowPrintCP = "";
	filterAD = null;
	filterAA = null;
	filterTC = null;
	filterPC = null; 
 
	String btnEjecutar = request.getParameter("btnEjecutar"); 
	String psHiFuOut = request.getParameter("psHiFuOut");

	String hiddenEmpresa = 	(request.getParameter("hiddenEmpresa") != null) ? request.getParameter("hiddenEmpresa") : "";
	String hiddenFacultades = (request.getParameter("hiddenFacultades") != null) ? request.getParameter("hiddenFacultades") : "";
	String hiddenTipoPoder = 	(request.getParameter("hiddenTipoPoder") != null) ? request.getParameter("hiddenTipoPoder") : "";	
	String hiddenApoderados = (request.getParameter("hiddenApoderados") != null) ? request.getParameter("hiddenApoderados") : "";
	String hiddenPoder = (request.getParameter("hiddenPoder") != null) ? request.getParameter("hiddenPoder") : "";	
		
	String txtEmpresa = 	(request.getParameter("txtEmpresa") != null) ? request.getParameter("txtEmpresa") : "";
	String txtFacultades = (request.getParameter("txtFacultades") != null) ? request.getParameter("txtFacultades") : "";
	String txtTipoPoder = 	(request.getParameter("txtTipoPoder") != null) ? request.getParameter("txtTipoPoder") : "";
	String txtApoderados = (request.getParameter("txtApoderados") != null) ? request.getParameter("txtApoderados") : "";
	String txtPoder = (request.getParameter("txtPoder") != null) ? request.getParameter("txtPoder") : "";	
	String txtEscritura = (request.getParameter("txtEscritura") != null) ? request.getParameter("txtEscritura") : "";	

	String paramString = 	"hiddenEmpresa=" + hiddenEmpresa +
							"&hiddenFacultades=" + hiddenFacultades +
							"&hiddenTipoPoder=" + hiddenTipoPoder +
							"&txtEscritura=" + txtEscritura +
							"&hiddenApoderados=" + hiddenApoderados +
							"&hiddenPoder=" + hiddenPoder +
							"&txtEmpresa=" + txtEmpresa.replace("&","%26") +
							"&txtFacultades=" + txtFacultades +
							"&txtTipoPoder=" + txtTipoPoder +
							"&txtApoderados=" + txtApoderados +
							"&txtPoder=" + txtPoder +
							"&btnEjecutar=Generar"
							;
			 				
	if(btnEjecutar != null && btnEjecutar.equals("Generar")) {						
		
		String tipoPoderFilter = txtTipoPoder.replace("<ul><li>", "'");
		tipoPoderFilter = tipoPoderFilter.replace("</li><li>","','");
		tipoPoderFilter = tipoPoderFilter.replace("</li></ul>", "'");
		tipoPoderFilter = tipoPoderFilter.equals("<ul></ul>") ? null : tipoPoderFilter;
		tipoPoderFilter = tipoPoderFilter.replace("Carta Poder","CP").replace("Poder Especial","PE").replace("Poder General","PG");
		
		String apoderados = txtApoderados.replace("</li><li>","%' OR PENDIUM_OTORGAPODER_EP_TAB.DESC_APODERADOS LIKE '%");
		apoderados = apoderados.replace("<ul><li>","PENDIUM_OTORGAPODER_EP_TAB.DESC_APODERADOS LIKE '%");
		apoderados = apoderados.replace("</li></ul>","%'");
		apoderados = apoderados.equals("<ul></ul>") ? null : apoderados;
		
		String escritura = txtEscritura.equals("") ? "" : "'%"+txtEscritura+"%'";
		
		txtFacultades = txtFacultades.replace("<ul><li>", "'");
		txtFacultades = txtFacultades.replace("</li><li>","','");
		txtFacultades = txtFacultades.replace("</li></ul>", "'");
		txtFacultades = txtFacultades.equals("<ul></ul>") ? "" : txtFacultades;
		
		if(txtFacultades.contains("Actos de Dominio")){
			filterAD = "AD9999";			
		}
		 
		if(txtFacultades.contains("Actos de Administración")){
			filterAA = "AA9999";			
		}
		
		if(txtFacultades.contains("Títulos de Crédito")){
			filterTC = "TC9999";			
		}
		
		if(txtFacultades.contains("Pleitos y Cobranzas")){
			filterPC = "PC9999";			
		}			
							
		GenericDataBean gdbPoderesGenerales = MngDataPoderes.query_PODERES_POR_FACULTAD(hiddenEmpresa, tipoPoderFilter, apoderados, hiddenPoder, escritura,filterAD, filterAA, filterTC, filterPC);				
					
		if(filterAD != null || filterAA != null || filterTC != null || filterPC != null){
			rowPrintPG = "<tr style='vertical-align:top' CLASS><td>ESCPD</td><td>FECPD</td><td>APOPD</td><td>PODPD</td>";
			if(filterAD != null)
				rowPrintPG += "<td>AD9999</td>";
			if(filterAA != null)
				rowPrintPG += "<td>AA9999</td>";
			if(filterTC != null)
				rowPrintPG += "<td>TC9999</td>";
			if(filterPC != null)
				rowPrintPG += "<td>PC9999</td>";	
			rowPrintPG += "<td>VIGCIA</td></tr>";
		}
		else{
			rowPrintPG = "<tr style='vertical-align:top' CLASS><td>ESCPD</td><td>FECPD</td><td>APOPD</td><td>PODPD</td><td>AD9999</td><td>AA9999</td><td>TC9999</td><td>PC9999</td><td>VIGCIA</td></tr>";
		}
		 rowPrintPE = "<tr style='vertical-align:top' CLASS><td>ESCPD</td><td>FECPD</td><td>PODPD</td><td>APOPD</td><td>DESPD</td><td>VIGCIA</td></tr>";
		 rowPrintCP = "<tr style='vertical-align:top' CLASS><td>FECPD</td><td>PODPD</td><td>APOPD</td><td>DESPD</td><td>VIGCIA</td></tr>";
									
		 if(gdbPoderesGenerales.beans.size() > 0){												 					
			
			for(int i=0; i < gdbPoderesGenerales.beans.size(); i++){				
				GenericBean beanPG = gdbPoderesGenerales.get(i);
				String idEmpresa = beanPG.getProperty("ID_EMPRESA").toString();
				String idEmpresaAnterior = i>0 ? gdbPoderesGenerales.get(i-1).getProperty("ID_EMPRESA").toString() : idEmpresa;				
				
				if(gdbPoderesGenerales.beans.size() == 1){
					generateHMTL(beanPG, 0);
				}
				else{
					/* if(idEmpresa.equals(idEmpresaAnterior) && i < gdbPoderesGenerales.beans.size()-1){
						generateHMTL(beanPG, i);					
						//Para que muestre el ultimo registro de Poderes Generales de la empresa actual
						if(i == gdbPoderesGenerales.beans.size()-2){
							generateHMTL(gdbPoderesGenerales.get(i+1), i+1);						
						}										
					} */
					//else{
						generateHMTL(beanPG, i);
					//}
				}
			}
		}
		else{			
				htmlout = "No se encontraron registros";			
		}	
																		
	}
		  	
%>

<%!
private void generateHMTL(GenericBean bean, int i){
	String empresa = bean.getProperty("NOM_EMPRESA").toString();
	String tipoPoder = bean.getProperty("IND_TIPO_ESCRITURA").toString();	 						
	
	if(!empresaCurrent.equals(empresa)){	
		htmlout += "<div><h4 align='center'>" + empresa + "</h4>";																			
		empresaCurrent = empresa;
		tipoPoderCurrent = "";				
	}
		 
	apendiceRevocados = "";
	if(!tipoPoderCurrent.equals(tipoPoder)){			
		htmlout += "<h4>"+(tipoPoder.equals("PG") ? "Poderes Generales" : tipoPoder.equals("PE") ? "Poderes Especiales" : tipoPoder.equals("CP") ? "Cartas Poder" : "Escrituras de Revocación") + "</h4>";
		tipoPoderCurrent = tipoPoder;
	}
					
		String esc = bean.getProperty("DES_ESCRITURA") == null ? "" : bean.getProperty("DES_ESCRITURA").toString();
		int idOtorgaPoder = Integer.valueOf(bean.getProperty("ID_OPODER_EP_PK").toString());
		String fec = bean.getProperty("FEC_OTORGAMIENTO_INSTR") == null ? "" : bean.getProperty("FEC_OTORGAMIENTO_INSTR").toString();
		String fecCP = bean.getProperty("FEC_FECHA") == null ? "" : bean.getProperty("FEC_FECHA").toString();
		String pod = bean.getProperty("DES_PODERTIPO") == null ? "" : bean.getProperty("DES_PODERTIPO").toString();
		String apo = bean.getProperty("DESC_APODERADOS") == null ? "" : bean.getProperty("DESC_APODERADOS").toString();
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
		String desc = bean.getProperty("DESC_DESCRIPCION") == null ? "" : bean.getProperty("DESC_DESCRIPCION").toString();
		String descEsp = bean.getProperty("DES_PODER") == null ? "" : bean.getProperty("DES_PODER").toString();
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
		
		if(tipoPoderCurrent.equals(tipoPoder) && empresaCurrent.equals(empresa)){
			String row = "";												
					
			switch(tipoPoder){
			case "PG": 
				String columnasPG = "";
				
				if(filterAD != null || filterAA != null || filterTC != null || filterPC != null){
					columnasPG = "<table cellspacing='0' cellpadding='3' width='1650px' style='table-layout:fixed;'><tr><th class='tableHeader' width='5%'>Escritura No.</th><th class='tableHeader' width='5%'>Fecha</th><th class='tableHeader' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Apoderados&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th><th class='tableHeader' width='5%'>Poder</th>";
					if(filterAD != null)
						columnasPG += "<th class='tableHeader' width='12%'>Actos de Dominio</th>";
					if(filterAA != null)
						columnasPG += "<th class='tableHeader' width='12%'>Actos de Administración</th>";
					if(filterTC != null)
						columnasPG += "<th class='tableHeader' width='12%'>Títulos de Crédito</th>";
					if(filterPC != null)
						columnasPG += "<th class='tableHeader' width='12%'>Pleitos y Cobranzas</th>";	
					columnasPG += "<th class='tableHeader' width='5%'>Vigencia</th></tr>";
					htmlout += columnasPG; 								
				}
				else{
					htmlout += "<table cellspacing='0' cellpadding='3' width='1650px' style='table-layout:fixed;'><tr><th class='tableHeader' width='5%'>Escritura No.</th><th class='tableHeader' width='5%'>Fecha</th><th class='tableHeader' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Apoderados&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th><th class='tableHeader' width='5%'>Poder</th>"
					+ "<th class='tableHeader' width='12%'>Actos de Dominio</th><th class='tableHeader' width='12%'>Actos de Administración</th><th class='tableHeader' width='12%'>Títulos de Crédito</th><th class='tableHeader' width='12%'>Pleitos y Cobranzas</th><th class='tableHeader' width='5%'>Vigencia</th></tr>";
				}
				
				rowPrint = rowPrintPG;
		
				row = rowPrint.replace("ESCPD", esc).replace("FECPD", fec).replace("APOPD", apo).replace("PODPD", pod).replace("AD9999", ad).replace("AA9999", aa).replace("TC9999", tc).replace("PC9999", pc).replace("VIGCIA", vig);	
				break;
			case "CP": 
				htmlout += "<table cellspacing='0' cellpadding='3' width='100%'><tr><th class='tableHeader' style='width:8%'>Fecha</th><th class='tableHeader' style='width:8%'>Poder</th><th class='tableHeader' style='width:35%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Apoderados&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th><th class='tableHeader' style='width:40%'>Descripción</th><th class='tableHeader' style='width:9%'>Vigencia</th></tr>";
				rowPrint = rowPrintCP;
				row = rowPrint.replace("FECPD", fecCP).replace("PODPD", pod).replace("APOPD", apo).replace("DESPD", descEsp).replace("VIGCIA", vig);	
				break;
			case "PE": 
				htmlout += "<table cellspacing='0' cellpadding='3' width='100%'><tr><th class='tableHeader' style='width:6%'>Escritura No.</th><th class='tableHeader' style='width:6%'>Fecha</th><th class='tableHeader' style='width:6%'>Poder</th><th class='tableHeader' style='width:35%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Apoderados&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th><th class='tableHeader' style='width:40%'>Descripción</th><th class='tableHeader' style='width:7%'>Vigencia</th></tr>";
				rowPrint = rowPrintPE;
				row = rowPrint.replace("ESCPD", esc).replace("FECPD", fec).replace("PODPD", pod).replace("APOPD", apo).replace("DESPD", descEsp).replace("VIGCIA", vig);	
				break;
			}
			
			row = row.replace("CLASS", i%2 == 0 ? "" : "class='tableRow2'" );
			
			htmlout+=row;
			htmlout+="</table></div><br/>"; 
			htmlout += (apendiceRevocados.trim().length()>0 ? "<div>" + apendiceRevocados + "</div><br/>" : "");
			
		}
}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Reporte de Poderes por Facultades</title>

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
		
	document.getElementById('txtEmpresa').value = document.getElementById('divEmpresa').innerHTML;
	document.getElementById('txtTipoPoder').value = document.getElementById('divTipoPoder').innerHTML;
	document.getElementById('txtPoder').value = document.getElementById('divPoder').innerHTML;
	document.getElementById('txtApoderados').value = document.getElementById('divApoderados').innerHTML;
	document.getElementById('txtFacultades').value = document.getElementById('divFacultades').innerHTML;
	
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
		Poderes por Facultades			
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
								<img src='<%= request.getContextPath() %>/img/btn_search.png' onclick="openMultiSelectPupUp('EMPRESAS','hiddenEmpresa','divEmpresa','innerHTML',document.getElementById('hiddenEmpresa').value,'None')">																						
							</div>
														
							<input type="hidden" id='hiddenEmpresa' name='hiddenEmpresa' value='<%=hiddenEmpresa%>'>
													
	 						<input type='hidden' id='txtEmpresa' name='txtEmpresa' 
								value="<%= (request.getParameter("txtEmpresa")!=null)? txtEmpresa : ""%>">
	 																	
						</td>
						<td><img src='<%= request.getContextPath() %>/img/btn_clean.png' onclick="document.getElementById('hiddenEmpresa').value = '';document.getElementById('divEmpresa').innerHTML = '';"></td>
					</tr>
					<tr>						
						<td width="30%"><b>Facultades:</b>
						<div id="divFacultades"><%= (request.getParameter("txtFacultades")!=null)?  txtFacultades : ""%></div>
						</td>
						
						<td>	
							<div align='right'>
								<img src='<%= request.getContextPath() %>/img/btn_search.png' onclick="openMultiSelectPupUp('FACULTADES','hiddenFacultades','divFacultades','innerHTML',document.getElementById('hiddenFacultades').value,'None')">																						
							</div>
														
							<input type="hidden" id='hiddenFacultades' name='hiddenFacultades' value='<%=hiddenFacultades%>'>
													
	 						<input type='hidden' id='txtFacultades' name='txtFacultades' 
								value="<%= (request.getParameter("txtFacultades")!=null)? txtFacultades : ""%>">
	 																	
						</td>
						<td><img src='<%= request.getContextPath() %>/img/btn_clean.png' 
												onclick="document.getElementById('hiddenFacultades').value = '';document.getElementById('divFacultades').innerHTML = '';"></td>
					</tr>
					<tr>
						
						<td width="90%" ><b>Tipo de Poder:</b>
							<div id="divTipoPoder"><%= (request.getParameter("txtTipoPoder")!=null)? TextFormatter.removeAccents( (new String(request.getParameter("txtTipoPoder").getBytes("ISO-8859-1"),"UTF-8")) ) : ""%></div>
						</td>
						<td>	
							<div align='right'>
								<img src='<%= request.getContextPath() %>/img/btn_search.png' onclick="openMultiSelectPupUp('41','hiddenTipoPoder','divTipoPoder','innerHTML',document.getElementById('hiddenTipoPoder').value,'UL')">									
							</div>
							
							<input type="hidden" id='hiddenTipoPoder' name='hiddenTipoPoder' value='<%=hiddenTipoPoder%>'>
													
							<input type='hidden' id='txtTipoPoder' name='txtTipoPoder' value="<%= (request.getParameter("txtTipoPoder")!=null)? new String(request.getParameter("txtTipoPoder").getBytes("ISO-8859-1"),"UTF-8") : ""%>">														
											
						</td>
						<td><img src='<%= request.getContextPath() %>/img/btn_clean.png' onclick="document.getElementById('hiddenTipoPoder').value = '';document.getElementById('divTipoPoder').innerHTML = '';"></td>
					</tr>
				    <tr>
				    	<td><b>Nombres de Apoderados:</b>
				    		<div id="divApoderados"><%= (request.getParameter("txtApoderados")!=null)? txtApoderados: ""%></div>
				    	</td>
				    	<td>
							
							<div align='right'>																				
								<img src='<%= request.getContextPath() %>/img/btn_search.png' onclick="openMultiSelectPupUp('32','hiddenApoderados','divApoderados','innerHTML',document.getElementById('hiddenApoderados').value,'None')">															
							</div>
														
							<input type="hidden" id='hiddenApoderados' name='hiddenApoderados' value='<%=hiddenApoderados%>'>
													
							<input type='hidden' id='txtApoderados' name='txtApoderados' value="<%= (request.getParameter("txtApoderados")!=null)? txtApoderados : ""%>">
													
						</td>
						<td><img src='<%= request.getContextPath() %>/img/btn_clean.png' onclick="document.getElementById('hiddenApoderados').value = '';document.getElementById('divApoderados').innerHTML = '';"></td>
					</tr>
					<tr>
						<td><b>Poder:</b>
							<div id="divPoder"><%= (request.getParameter("txtPoder")!=null)? txtPoder: ""%></div>
						</td>
						<td>
							<div align='right'>
							<img src='<%= request.getContextPath() %>/img/btn_search.png' onclick="openMultiSelectPupUpAncho('PODERES','hiddenPoder','divPoder','innerHTML',document.getElementById('hiddenPoder').value,'None')">									
							</div>
							
							<input type="hidden" id='hiddenPoder' name='hiddenPoder' value="<%=hiddenPoder%>"/>
													
							<input type='hidden' id='txtPoder' name='txtPoder' value="<%= (request.getParameter("txtPoder")!=null)? txtPoder : ""%>">
														
						</td>
						<td><img src='<%= request.getContextPath() %>/img/btn_clean.png' onclick="document.getElementById('hiddenPoder').value = '';document.getElementById('divPoder').innerHTML = '';"></td>
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
			<a style="float:right" href="PoderesPorFacultadesPrint.jsp?<%=paramString%>" target="_blank">Exportar a PDF</a>					
	<%
		}
		else if(btnEjecutar != null && btnEjecutar.equals("Generar") && psHiFuOut !=null && psHiFuOut.equals("EXCEL")) {
	%>
			<a href="/DerechoCorporativo/ExportExcelPoderesServlet?<%=paramString + "&action=generarExcelPoderesPorFacultades"%>">Exportar a Excel</a>
	<%  } %>					
</div>

<div id="summary">

	<%=htmlout %>

</div>	

<jsp:include page="/jsp/components/pages_border/close.jsp"></jsp:include>

</body>
</html>
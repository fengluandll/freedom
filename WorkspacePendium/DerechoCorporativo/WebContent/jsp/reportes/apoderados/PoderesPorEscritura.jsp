<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="mx.com.televisa.derechocorporativo.util.TextFormatter"%>
<%@page import="mx.com.televisa.derechocorporativo.util.FacesUtils"%>
<%@page import="mx.com.televisa.derechocorporativo.daos.MngDataPoderes"%>
<%@page import="mx.com.televisa.derechocorporativo.bean.GenericDataBean"%>
<%@page import="mx.com.televisa.derechocorporativo.bean.GenericBean"%>
<%@page import="java.util.Date"%>

<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%@include file="/css/kaz-styles.jsp"%>
<script src="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.css">
<%@include file="/jsp/components/session_timeout/session_timeout.jsp"%>
<link href="/DerechoCorporativo/css/jquery-ui-css/pendium/jquery-ui.css" rel="stylesheet" type="text/css" />
<script src="/DerechoCorporativo/js/jquery/jquery-2.1.4.min.js" type="text/javascript"></script>
<script src="/DerechoCorporativo/css/jquery-ui-css/pendium/jquery-ui.js" type="text/javascript"></script>

<script type="text/javascript">
	function soloNumeros(object) {
		var texto = object.value;
		if (texto != null)
			for (c = 0; c < texto.length; c++) {
				if (isNaN(texto.charAt(c)))
					object.value = object.value.replace(texto.charAt(c), "");
			}
	}

	function maskEsc(valCampo) {
		if (valCampo != null) {
			var val2 = valCampo.value.trim();
			$(valCampo).val(commaSeparateNumber(val2));
		}

	}

	function commaSeparateNumber(val) {

		while (/(\d+)(\d{3})/.test(val.toString())) {
			val = val.toString().replace(/(\d+)(\d{3})/, '$1' + ',' + '$2');
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

	function openSelectPupUp(catalogId, targetIds, targetNames, namesProperty,
			currentValue) {

		var left = screen.width - ((screen.width - 300) / 2);
		var top = (screen.height - 300) / 2; // for 25% - devide by 4  |  for 33% - devide by 3

		newwindow = window
				.open(
						'../../components/simpleSelectPupUp/simpleselect.jsp?catalogId='
								+ catalogId + '&targetIds=' + targetIds
								+ '&targetNames=' + targetNames
								+ '&currentValue=' + currentValue
								+ '&namesProperty=' + namesProperty,
						'name',
						'height=600,width=450,toolbar=0,menubar=0,location=0,status=0,scrollbars=1,resizable=1,left='
								+ left + ',top=' + top);

		if (window.focus) {
			newwindow.focus()
		}

		return false;
	}

	function openMultiSelectPupUp(catalogId, targetIds, targetNames,
			namesProperty, currentValue, namesFormat) {

		var left = screen.width - ((screen.width - 300) / 2);
		var top = (screen.height - 300) / 2; // for 25% - devide by 4  |  for 33% - devide by 3

		newwindow = window
				.open(
						'../../components/multiselectPupUp/multiselect.jsp?catalogId='
								+ catalogId + '&targetIds=' + targetIds
								+ '&targetNames=' + targetNames
								+ '&currentValue=' + currentValue
								+ '&namesProperty=' + namesProperty
								+ '&namesFormat=' + namesFormat,
						'name',
						'height=600,width=450,toolbar=0,menubar=0,location=0,status=0,scrollbars=1,resizable=1,left='
								+ left + ',top=' + top);

		if (window.focus) {
			newwindow.focus()
		}

		return false;
	}

	function openMultiSelectPupUpAncho(catalogId, targetIds, targetNames,
			namesProperty, currentValue, namesFormat) {

		var left = screen.width - ((screen.width - 700) / 2);
		var top = (screen.height - 300) / 2; // for 25% - devide by 4  |  for 33% - devide by 3

		newwindow = window
				.open(
						'../../components/multiselectPupUp/multiselect.jsp?catalogId='
								+ catalogId + '&targetIds=' + targetIds
								+ '&targetNames=' + targetNames
								+ '&currentValue=' + currentValue
								+ '&namesProperty=' + namesProperty
								+ '&namesFormat=' + namesFormat
								+ '&actionApod=apodActiva',
						'name',
						'height=600,width=700,toolbar=0,menubar=0,location=0,status=0,scrollbars=1,resizable=1,left='
								+ left + ',top=' + top);

		if (window.focus) {
			newwindow.focus()
		}

		return false;
	}

	$(document).ready(function() {
		$("#txtEscritura").keyup(function() {
			soloNumeros(this);
		});

		$("#txtEscritura").blur(function() {
			maskEsc(this);
		});
	});
</script>

</head>
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

	String paramString = "hiddenEmpresa=" + hiddenEmpresa +
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
			
	String Empresa="";
	String DelegadoPor="";
	String Fecha="";
	String Hora="";
	String Escritura="";
	String EscrituraDigitalizada="";
	String FechaOtorgamiento = "";
	String descTipoPoder = "";
	String Licenciado = "";String Notario = "";String NotarioDe = "";String SupleAso = "";String IRPCDe = "";String FecRegistro = "";String FolioMercantil = "";String OtrosDatosReg = "";String Solicitud = "";String Solicitadopor = "";String FechaSolicitud = "";String FechaDeRecibido = "";String Folio = "";String DocumentoDeEntrega = "";String FechaDeDocumento = "";String FechaDeRecibidoDoc="";
	String gridDocs = "";
	
	String ReqRPPC="";
	String ReqProto="";
	
	String htmlout = "";		
%>

<body>
 <jsp:include page="/jsp/components/pages_border/open-pre-title.jsp"></jsp:include>

	Poderes por Escritura

	<jsp:include page="/jsp/components/pages_border/open-post-title.jsp"></jsp:include>
	<jsp:include
		page="/jsp/components/backPageReports/regresaMenuReportes.jsp">
		<jsp:param name="action" value="rPredefinidos" />
	</jsp:include>
	<form action="" method="post">
		<table width="60%" align="center">
			<tr>
				<td colspan="3">
					<fieldset>
						<legend style="text-align: center">Filtros</legend>
						<table width="100%" border='0' cellpadding="3" cellspacing="3" align="center">
							<tr>
								<td width="30%"><b>Empresa(s):</b>
									<div id="divEmpresa"><%=(request.getParameter("txtEmpresa") != null) ? txtEmpresa: ""%></div>
								</td>

								<td>
									<div align='right'>
										<img src='<%=request.getContextPath()%>/img/btn_search.png'
											onclick="openMultiSelectPupUp('EMPRESAS','hiddenEmpresa','divEmpresa','innerHTML',document.getElementById('hiddenEmpresa').value,'None')" />
									</div>
									<input type="hidden" id='hiddenEmpresa' name='hiddenEmpresa' value='<%=hiddenEmpresa%>' /> <input type='hidden' id='txtEmpresa' name='txtEmpresa'
									value="<%=(request.getParameter("txtEmpresa") != null) ? txtEmpresa : ""%>" />
								</td>
								<td><img
									src='<%=request.getContextPath()%>/img/btn_clean.png'
									onclick="document.getElementById('hiddenEmpresa').value = '';document.getElementById('divEmpresa').innerHTML = '';"></td>
							</tr>
							<tr>
								<td width="90%"><b>Tipo de Poder:</b>
									<div id="divTipoPoder"><%=(request.getParameter("txtTipoPoder") != null) ? TextFormatter.removeAccents((new String(request.getParameter("txtTipoPoder").getBytes("ISO-8859-1"), "UTF-8"))): ""%></div>
								</td>
								<td>
									<div align='right'>
										<img src='<%=request.getContextPath()%>/img/btn_search.png' onclick="openMultiSelectPupUp('41','hiddenTipoPoder','divTipoPoder','innerHTML',document.getElementById('hiddenTipoPoder').value,'UL')"/>
									</div> 
									<input type="hidden" id='hiddenTipoPoder' name='hiddenTipoPoder' value='<%=hiddenTipoPoder%>' />
									<input type='hidden' id='txtTipoPoder' name='txtTipoPoder' value="<%=(request.getParameter("txtTipoPoder") != null) ? new String(request.getParameter("txtTipoPoder").getBytes("ISO-8859-1"),"UTF-8").trim(): ""%>" />
								</td>
								<td><img src='<%=request.getContextPath()%>/img/btn_clean.png' onclick="document.getElementById('hiddenTipoPoder').value = '';document.getElementById('divTipoPoder').innerHTML = '';" /></td>
							</tr>
							<tr>
								<td><b>Nombres de Apoderados:</b>
									<div id="divApoderados"><%=(request.getParameter("txtApoderados") != null) ? txtApoderados : ""%></div>
								</td>
								<td>
									<div align='right'>
										<img src='<%=request.getContextPath()%>/img/btn_search.png'
											onclick="openMultiSelectPupUp('32','hiddenApoderados','divApoderados','innerHTML',document.getElementById('hiddenApoderados').value,'None')" />
									</div> <input type="hidden" id='hiddenApoderados'
									name='hiddenApoderados' value='<%=hiddenApoderados%>' /> <input
									type='hidden' id='txtApoderados' name='txtApoderados'
									value="<%=(request.getParameter("txtApoderados") != null) ? txtApoderados : ""%>" />
								</td>
								<td><img
									src='<%=request.getContextPath()%>/img/btn_clean.png'
									onclick="document.getElementById('hiddenApoderados').value = '';document.getElementById('divApoderados').innerHTML = '';" /></td>
							</tr>
							<tr>
								<td><b>Poder:</b>
									<div id="divPoder"><%=(request.getParameter("txtPoder") != null) ? txtPoder : ""%></div>
								</td>
								<td>
									<div align='right'>
										<img src='<%=request.getContextPath()%>/img/btn_search.png'
											onclick="openMultiSelectPupUpAncho('PODERES','hiddenPoder','divPoder','innerHTML',document.getElementById('hiddenPoder').value,'None')" />
									</div>
									<input type="hidden" id='hiddenPoder' name='hiddenPoder' value="<%=hiddenPoder%>" /> <input type='hidden' id='txtPoder' name='txtPoder'
									value="<%=(request.getParameter("txtPoder") != null) ? txtPoder : ""%>" />
								</td>
								<td><img
									src='<%=request.getContextPath()%>/img/btn_clean.png'
									onclick="document.getElementById('hiddenPoder').value = '';document.getElementById('divPoder').innerHTML = '';" /></td>
							</tr>
							<tr>
								<td><b>Escritura:</b></td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td>
									<input id='txtEscritura' name='txtEscritura' size="40" maxlength="6" value="<%=(request.getParameter("txtEscritura") != null) ? request.getParameter("txtEscritura") : ""%>" />
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
						<legend style="text-align: center">Exportar a:</legend>
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
							</select> <input type="submit" id='btnEjecutar' name='btnEjecutar'
								value='Generar' onclick="waitBar();copyParamsDivToHidden();" />
							<img src='<%=request.getContextPath()%>/img/wait-bar.gif'
								id='imgCapWait' style="display: none;" />
						</div>
					</fieldset>
				</td>
			</tr>
			<tr>
				<td colspan="3" align="right"><br /></td>
			</tr>
			<tr>
				<td colspan="3" align="left"></td>
			</tr>
		</table>
	</form>
<%
boolean generar = btnEjecutar != null && btnEjecutar.equals("Generar");
if(generar) {																
				
	String tipoPoder = txtTipoPoder.replace("\n","").replace("\t","").replace("\r","").replace("<ul><li>", "'");
	tipoPoder = tipoPoder.replace("</li><li>","','");
	tipoPoder = tipoPoder.replace("</li></ul>", "'");
	tipoPoder = tipoPoder.equals("<ul></ul>") ? null : tipoPoder;
	tipoPoder = tipoPoder.replace("Carta Poder","CP").replace("Poder Especial","PE").replace("Poder General","PG");
	
	String apoderados = txtApoderados.replace("</li><li>","%' OR PENDIUM_OTORGAPODER_EP_TAB.DESC_APODERADOS LIKE '%");
	apoderados = apoderados.replace("<ul><li>","PENDIUM_OTORGAPODER_EP_TAB.DESC_APODERADOS LIKE '%");
	apoderados = apoderados.replace("</li></ul>","%'");
	apoderados = apoderados.equals("<ul></ul>") ? null : apoderados;
	
	String escritura = txtEscritura.equals("") ? "" : "'"+txtEscritura+"'";
%>

	<div align="right">
		<br>
		<%
			if(btnEjecutar != null && btnEjecutar.equals("Generar") && psHiFuOut !=null && psHiFuOut.equals("PDF")) {			
		%>
				<a style="float:right" href="PoderesPorEscrituraPrint.jsp?<%=paramString%>" target="_blank">Exportar a PDF</a>									
		<%
			}
			else if(btnEjecutar != null && btnEjecutar.equals("Generar") && psHiFuOut !=null && psHiFuOut.equals("EXCEL")) {
		%>
				<a href="/DerechoCorporativo/ExportExcelPoderesServlet?<%=paramString + "&action=generarExcelPoderesPorEscritura"%>">Exportar a Excel</a>
		<%  } %>					
	</div>
	<div id="summary">	
<% 			
			if(!escritura.equals("")){
				GenericDataBean gdb = MngDataPoderes.query_PODERES_POR_ESCRITURA(hiddenEmpresa, tipoPoder, apoderados, hiddenPoder, escritura);
				GenericBean beanApoderados = null;
				if(gdb.beans.size() == 0) {
					 htmlout = "No se encontraron registros";
					 generar = false;
				}
				else{
					int escrituraAnterior = 0;
					int empresaAnterior = 0;
					String tipoEscrituraAnterior = "";
					for(int c=0;c<gdb.beans.size();c++){
						beanApoderados = gdb.get(c);
						
						int escrituraActual = beanApoderados.getProperty("ID_EP_PK") == null ? 0 : Integer.valueOf(beanApoderados.getProperty("ID_EP_PK").toString());
						String tipoEscrituraActual = beanApoderados.getProperty("IND_TIPO_ESCRITURA").toString();
						int empresaActual = beanApoderados.getProperty("ID_EMPRESA") == null ? 0 : Integer.valueOf(beanApoderados.getProperty("ID_EMPRESA").toString());
						descTipoPoder = (tipoEscrituraActual.equals("PG") ? "Poder General" : tipoEscrituraActual.equals("PE") ? "Poder Especial" : tipoEscrituraActual.equals("CP") ? "Carta Poder" : "Escritura de Revocación");
						String descTipoEscritura = Integer.valueOf(beanApoderados.getProperty("NUM_TIPO_ESC_DIFERENTES").toString())>1? "Poderes" : descTipoPoder;
						htmlout = "";
						if(c==0 || !tipoEscrituraActual.equals(tipoEscrituraAnterior) || empresaAnterior != empresaActual){
							String descEspecial = beanApoderados.getProperty("DESC_PODER_ESPECIAL") == null ? "" : beanApoderados.getProperty("DESC_PODER_ESPECIAL").toString();
							if(descEspecial.length()==0)								
								htmlout += "<h4 style='width: 100%' align='left'>" + descTipoPoder + "</h4>";
							else
								htmlout += "<br/>";
							tipoEscrituraAnterior = tipoEscrituraActual; 
						}						 
								
						if(escrituraActual != escrituraAnterior && empresaActual != empresaAnterior){								
							Escritura = beanApoderados.getProperty("DES_ESCRITURA") == null ? "" : beanApoderados.getProperty("DES_ESCRITURA") == null ? "" : beanApoderados.getProperty("DES_ESCRITURA").toString();
							FechaOtorgamiento = beanApoderados.getProperty("FEC_OTORGAMIENTO_INSTR") == null ? "" : beanApoderados.getProperty("FEC_OTORGAMIENTO_INSTR").toString();
							DelegadoPor = beanApoderados.getProperty("DELEGADOPOR") == null ? "" : beanApoderados.getProperty("DELEGADOPOR").toString();
							Fecha = beanApoderados.getProperty("FEC_FECHA") == null ? "" : beanApoderados.getProperty("FEC_FECHA").toString();
							Hora = beanApoderados.getProperty("FEC_HORA") == null ? "" : beanApoderados.getProperty("FEC_HORA").toString();
							EscrituraDigitalizada = beanApoderados.getProperty("NUM_DOCUMENTUM_INSTR") == null ? "" : beanApoderados.getProperty("NUM_DOCUMENTUM_INSTR").toString();
							Empresa = beanApoderados.getProperty("NOM_EMPRESA") == null ? "" : beanApoderados.getProperty("NOM_EMPRESA").toString();						
							
							ReqProto = beanApoderados.getProperty("IND_REQUIERE_PROTO") == null ? "" : beanApoderados.getProperty("IND_REQUIERE_PROTO").toString();
							ReqRPPC = beanApoderados.getProperty("IND_REQUIERE_INSCR_RPPC") == null ? "" : beanApoderados.getProperty("IND_REQUIERE_INSCR_RPPC").toString();					
							
							Licenciado = beanApoderados.getProperty("NOM_LICENCIADO") == null ? "" : beanApoderados.getProperty("NOM_LICENCIADO").toString(); 
							Notario = beanApoderados.getProperty("NUM_NOTARIO") == null ? "" : beanApoderados.getProperty("NUM_NOTARIO").toString(); 
							NotarioDe = beanApoderados.getProperty("DE_NOTARIO") == null ? "" : beanApoderados.getProperty("DE_NOTARIO").toString(); 
							SupleAso = beanApoderados.getProperty("DES_SUPLENCIA_ASOCIADO") == null ? "" : beanApoderados.getProperty("DES_SUPLENCIA_ASOCIADO").toString(); 
							IRPCDe = beanApoderados.getProperty("DES_INSC_REGPUB") == null ? "" : beanApoderados.getProperty("DES_INSC_REGPUB").toString(); 
							FecRegistro = beanApoderados.getProperty("FEC_REGISTRO") == null ? "" : beanApoderados.getProperty("FEC_REGISTRO").toString(); 
							FolioMercantil = beanApoderados.getProperty("NUM_FOLIO_MERC") == null ? "" : beanApoderados.getProperty("NUM_FOLIO_MERC").toString(); 
							OtrosDatosReg = beanApoderados.getProperty("DES_OTROS_DATOS_REGISTRO") == null ? "" : beanApoderados.getProperty("DES_OTROS_DATOS_REGISTRO").toString(); 
							Solicitud = beanApoderados.getProperty("ID_SOL_DOC") == null ? "" : beanApoderados.getProperty("ID_SOL_DOC").toString(); 
							Solicitadopor = beanApoderados.getProperty("DES_SOL_RESP") == null ? "" : beanApoderados.getProperty("DES_SOL_RESP").toString(); 
							FechaSolicitud = beanApoderados.getProperty("FEC_SOL") == null ? "" : beanApoderados.getProperty("FEC_SOL").toString(); 
							FechaDeRecibido = beanApoderados.getProperty("FEC_SOL_REC") == null ? "" : beanApoderados.getProperty("FEC_SOL_REC").toString(); 
							Folio = beanApoderados.getProperty("DES_SOL_FOLIO") == null ? "" : beanApoderados.getProperty("DES_SOL_FOLIO").toString(); 
							DocumentoDeEntrega = beanApoderados.getProperty("ID_ENT_DOC") == null ? "" : beanApoderados.getProperty("ID_ENT_DOC").toString(); 
							FechaDeDocumento = beanApoderados.getProperty("FEC_ENT_DOC") == null ? "" : beanApoderados.getProperty("FEC_ENT_DOC").toString(); 
							FechaDeRecibidoDoc= beanApoderados.getProperty("FEC_ENT_REC") == null ? "" : beanApoderados.getProperty("FEC_ENT_REC").toString();
							int ID_EP_PK= beanApoderados.getProperty("ID_EP_PK") == null ? 0 : Integer.parseInt(beanApoderados.getProperty("ID_EP_PK").toString());																																				
													
							GenericDataBean lstDoc = MngDataPoderes.queryDOCUMENTUMS(ID_EP_PK);
							for(int d=0;d<lstDoc.beans.size();d++){
								GenericBean doc = lstDoc.get(d);
								if(d==0)
									gridDocs = "<table id=\"TblPGForm_Grid_Documents\" class=\"tablaCaramel\" style=\"width:100%\" cellspacing=\"1\" cellpadding=\"0\" border=\"0\"><tr><th style=\"width:20%;font-weight: bold;\" align=\"center\">Titulo</th><th style=\"width:20%;font-weight: bold;\" align=\"center\">Fecha del documento</th><th style=\"width:20%;font-weight: bold;\" align=\"center\">Fecha de recibido</th><th style=\"width:40%;font-weight: bold;\" align=\"center\">Documento</th></tr>";							
								
								gridDocs+="<tr "+ ( d==0?"":d%2==0?"":"style=\"background-color: #DCE8F6;\"") +"><td>COLUMN1</td><td align=\"center\">COLUMN2</td><td align=\"center\">COLUMN3</td><td align=\"center\">COLUMN4</td></tr>";
								
								String c1 = doc.getProperty("DESC_TITLE") == null ? "" : doc.getProperty("DESC_TITLE").toString();
								String c2 = doc.getProperty("FEC_REC") == null ? "" : doc.getProperty("FEC_REC").toString();
								String c3 = doc.getProperty("FEC_ENT") == null ? "" : doc.getProperty("FEC_ENT").toString();
								String c4 = doc.getProperty("ID_DOCUMENTCVE") == null ? "" : doc.getProperty("ID_DOCUMENTCVE").toString();				
								
								gridDocs = gridDocs.replace("COLUMN1", c1);
								gridDocs = gridDocs.replace("COLUMN2", c2);
								gridDocs = gridDocs.replace("COLUMN3", c3);
								gridDocs = gridDocs.replace("COLUMN4", c4);
								
								if((d+1) == lstDoc.beans.size())
									gridDocs+="</table>";
							}
							%>						
							<h3 style="width: 100%" align="center">
								<b><%=Empresa%></b>
							</h3>
							<div style="height: 30px; padding-top: 5px; background-color: #2B385D; color: #FFF; padding: 5px;">
								<label id="divPGForm_title"><%=descTipoEscritura%></label>
							</div>
							<div id="divSeccH1Content" class="divSeccHContent showSecc showSeccER showSeccPG showSeccPE">
								<table class="tablaCaramel" width="100%">
									<tr>
										<td style="width: 30%">Delegado por</td>
										<td><%=DelegadoPor%></td>
									</tr>
									<tr  style="background-color: #DCE8F6;font-size: 11px;">
										<td>Fecha</td>
										<td><%=Fecha%></td>
									</tr>
									<tr>
										<td>Hora</td>
										<td><%=Hora%></td>
									</tr>
								</table>
							</div>
							<!-- Si requiere Protocolización o   Si requiere RPPC-->
							<% if(ReqProto.equals("1") || ReqRPPC.equals("1")){ %>
							<div style="height: 30px; padding-top: 5px; background-color: #2B385D; color: #FFF; padding: 5px;">
								Instrumento</div>
							<%} %>
							<table id="divPGForm_RP" width="100%" class="tablaCaramel">
								<!-- Si requiere Protocolización -->
								<% if(ReqProto.equals("1")){ %>
								<tr>
									<td style="width: 30%">Escritura No.</td>
									<td><%=Escritura%></td>
								</tr>
								<tr style="background-color: #DCE8F6;font-size: 11px;">
									<td>Escritura Digitalizada:</td>
									<td><%=EscrituraDigitalizada%></td>
								</tr>
								<tr>
									<td>Fecha Otorgamiento:</td>
									<td><%=FechaOtorgamiento%></td>
								</tr>
								<tr style="background-color: #DCE8F6;font-size: 11px;">
									<td>Licenciado:</td>
									<td><%=Licenciado%></td>
								</tr>
								<tr>
									<td>Notario Público:</td>
									<td><%=Notario%></td>
								</tr>
								<tr style="background-color: #DCE8F6;font-size: 11px;">
									<td>De:</td>
									<td><%=NotarioDe%></td>
								</tr>
								<tr>
									<td>Suplencia / Asociado:</td>
									<td><%=SupleAso%></td>
								</tr>
								<%} %>
								<!-- Si requiere RPPC -->
								<% if( ReqRPPC.equals("1")){ %>
								<tr style="background-color: #DCE8F6;font-size: 11px;">
									<td>Inscrita en el Registro Público de Comercio de:</td>
									<td><%=IRPCDe%></td>
								</tr>
								<tr>
									<td>Fecha de Registro:</td>
									<td><%=FecRegistro%></td>
								</tr>
								<tr style="background-color: #DCE8F6;font-size: 11px;">
									<td>Folio Mercantil / Folio Mercantil Electrónico</td>
									<td><%=FolioMercantil%></td>
								</tr>
								<tr>
									<td>Otros Datos de Registro</td>
									<td><%=OtrosDatosReg%></td>
								</tr>
								<% } %>
							</table>
							<div style="height: 30px; padding-top: 5px; background-color: #2B385D; color: #FFF; padding: 5px;">
								Referencia Documentum
							</div>
							<%if(Solicitud.equals("") && Solicitadopor.equals("")
									&& FechaSolicitud.equals("") && FechaDeRecibido.equals("")
									&& Folio.equals("") && DocumentoDeEntrega.equals("")
									&& FechaDeDocumento.equals("") && FechaDeRecibidoDoc.equals("")){
								
							}else{ %>
							<table width="100%" class="tablaCaramel">
								<tr>
									<td style="width: 30%">Solicitud:</td>
									<td><%=Solicitud%></td>
								</tr>
								<tr style="background-color: #DCE8F6;font-size: 11px;">
									<td>Solicitado por:</td>
									<td><%=Solicitadopor%></td>
								</tr>
								<tr>
									<td>Fecha de Solicitud:</td>
									<td><%=FechaSolicitud%></td>
								</tr>
								<tr style="background-color: #DCE8F6;font-size: 11px;">
									<td>Fecha de Recibido:</td>
									<td><%=FechaDeRecibido%></td>
								</tr>
								<tr>
									<td>Folio:</td>
									<td><%=Folio%></td>
								</tr>
								<tr>
									<td>Documento de Entrega:</td>
									<td><%=DocumentoDeEntrega%></td>
								</tr>
								<tr style="background-color: #DCE8F6;font-size: 11px;">
									<td>Fecha de Documento:</td>
									<td><%=FechaDeDocumento%></td>
								</tr>
								<tr>
									<td>Fecha de Recibido:</td>
									<td><%=FechaDeRecibidoDoc%></td>
								</tr>
								<tr>
									<td colspan="2"><%=gridDocs %></td>
								</tr>	
							</table>																							
							<% 	
							}
							escrituraAnterior = escrituraActual;
							empresaAnterior = empresaActual;							
						}	
						
						String rowPrintPG = "<tr style='vertical-align:top' CLASS><td>APOTD</td><td>PODTD</td><td>AD9999</td><td>AA9999</td><td>TC9999</td><td>PC9999</td><td>EP9999</td><td>VIGCIA</td></tr>";
						String rowPrintPE = "<tr style='vertical-align:top' CLASS><td>APOTD</td><td>TPO</td><td>PODTD</td><td>VIGCIA</td></tr>";
						String rowPrintCP = "<tr style='vertical-align:top' CLASS><td>APOTD</td><td>TPO</td><td>PODTD</td><td>VIGCIA</td></tr>";
						String rowPrint = "";
						String apendiceRevocados = "";																																																																																		
						
						String esc = beanApoderados.getProperty("DES_ESCRITURA") == null ? "" : beanApoderados.getProperty("DES_ESCRITURA") == null ? "" : beanApoderados.getProperty("DES_ESCRITURA").toString();
						int idOtorgaPoder = Integer.valueOf(beanApoderados.getProperty("ID_OPODER_EP_PK").toString());
						String fec = beanApoderados.getProperty("FEC_OTORGAMIENTO_INSTR") == null ? "" : beanApoderados.getProperty("FEC_OTORGAMIENTO_INSTR").toString();
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
						apo = apo.replace("null", "");
						desc = desc.replace("null", "");
						descEsp = descEsp.replace("null", "");
						String descEspecial = beanApoderados.getProperty("DESC_PODER_ESPECIAL") == null ? "" : beanApoderados.getProperty("DESC_PODER_ESPECIAL").toString();
						descEspecial = descEspecial.replace("&#9679;","&#8226;" ).replace("null", "");
						
						GenericDataBean gdbRevocaciones = MngDataPoderes.query_REVOCACIONES(idOtorgaPoder);
						for(int j=0; j < gdbRevocaciones.beans.size(); j++){
							GenericBean beanRevocacion = gdbRevocaciones.get(j);	
							String apendiceRevocadoCurrent = "";								
							  apendiceRevocadoCurrent = beanRevocacion.getProperty("DESC_REVOCA") == null ? "" : beanRevocacion.getProperty("DESC_REVOCA").toString(); 
							  if(!apendiceRevocados.contains(apendiceRevocadoCurrent)){
								  if(!apendiceRevocados.contains(apendiceRevocadoCurrent)){						
									  apendiceRevocados += apendiceRevocadoCurrent.trim().length()>0 ? "<i style='font-size:8pt'>" + apendiceRevocadoCurrent + "</i><br/>" : "";	
								  }										  
							  }								  
						}
						
						String row = "";	
											
						if((c==0 || !beanApoderados.getProperty("ID_OPODER_EP_PK").toString().equals(gdb.get(c-1).getProperty("ID_OPODER_EP_PK").toString())) && beanApoderados.getProperty("NOM_EMPRESA").toString().equals(Empresa)){
						
							switch(tipoEscrituraActual){
							case "PG":
								htmlout += "<table cellspacing='0' cellpadding='3' width='100%'><tr><th class='tableHeader' style='width:25%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Apoderados&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th><th class='tableHeader' style='width:7%'>Poder</th><th class='tableHeader' style='width:13%'>Actos de Dominio</th><th class='tableHeader' style='width:13%'>Actos de Administración</th><th class='tableHeader' style='width:13%'>Títulos de Crédito</th><th class='tableHeader' style='width:13%'>Pleitos y Cobranzas</th><th class='tableHeader' style='width:12%'>Especial</th><th class='tableHeader' style='width:4%'>Vigencia</th></tr>";
								rowPrint = rowPrintPG;
								row = rowPrint.replace("APOTD", apo).replace("PODTD", pod).replace("AD9999", ad).replace("AA9999", aa).replace("TC9999", tc).replace("PC9999", pc).replace("EP9999", descEspecial).replace("VIGCIA", vig);	
								break;
							case "CP": 
								htmlout += "<table cellspacing='0' cellpadding='3' width='100%'><tr><th class='tableHeader' style='width:35%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Apoderados&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th><th class='tableHeader' style='width:10%'>Poder</th><th class='tableHeader' style='width:45%'>Descripción</th><th class='tableHeader' style='width:10%'>Vigencia</th></tr>";
								rowPrint = rowPrintCP;
								row = rowPrint.replace("APOTD", apo).replace("TPO", pod).replace("PODTD", descEsp).replace("VIGCIA", vig);	
								break;
							case "PE": 
								htmlout += "<table cellspacing='0' cellpadding='3' width='100%'><tr><th class='tableHeader' style='width:35%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Apoderados&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th><th class='tableHeader' style='width:10%'>Poder</th><th class='tableHeader' style='width:45%'>Descripción</th><th class='tableHeader' style='width:10%'>Vigencia</th></tr>";
								rowPrint = rowPrintPE;
								row = rowPrint.replace("APOTD", apo).replace("TPO", pod).replace("PODTD", descEsp).replace("VIGCIA", vig);	
								break;
							}
							
							row = row.replace("CLASS", c%2 == 0 ? "" : "class='tableRow2'" );
						
							htmlout+=row + "<tr><td></td></tr>";
							htmlout+="</table>"; 
							htmlout += (apendiceRevocados.trim().length()>0 ? "<br/><div>" + apendiceRevocados + "</div><br/>" : "");								
						}																										
																						
						out.write(htmlout);
																										
					}					
				}
%>
				</div>
<%
			}
			else{
				GenericDataBean gdb = MngDataPoderes.query_ESCRITURAS(hiddenEmpresa, tipoPoder, apoderados, hiddenPoder, escritura);
				GenericBean beanApoderados = null;
				if(gdb.beans.size() == 0) {
					 htmlout = "No se encontraron registros";
					 generar = false;
				}
				else{								
					for(int i=0; i < gdb.beans.size(); i++){
						htmlout += "<table width='100%' cellspacing='0' cellpadding='3'><tr><th class='tableHeader' style='width:10%'>Escritura</th><th class='tableHeader' style='width:30%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Apoderados&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th><th class='tableHeader' style='width:15%'>Empresa</th><th class='tableHeader' style='width:10%'>Tipo</th><th class='tableHeader' style='width:15%'>Poder</th><th class='tableHeader' style='width:20%'>Descripción</th></tr>";		
						String rowPrintGEN = "<tr style='vertical-align:top' CLASS><td align='center'>ESCTD</td><td>APOTD</td><td  align='center'>EMPTD</td><td align='center'>TIPOTD</td><td  align='center'>TPTD</td><td>PODTD</td></tr>";
						String apendiceRevocados = "";
												
						beanApoderados = gdb.get(i);					
						String esc = beanApoderados.getProperty("DES_ESCRITURA") == null ? "" : beanApoderados.getProperty("DES_ESCRITURA") == null ? "" : beanApoderados.getProperty("DES_ESCRITURA").toString();
						int idOtorgaPoder = Integer.valueOf(beanApoderados.getProperty("ID_OPODER_EP_PK").toString());
						String pod = beanApoderados.getProperty("DES_PODERTIPO") == null ? "" : beanApoderados.getProperty("DES_PODERTIPO").toString();
						String apo = beanApoderados.getProperty("DESC_APODERADOS") == null ? "" : beanApoderados.getProperty("DESC_APODERADOS").toString();
						String desc = beanApoderados.getProperty("DESC_DESCRIPCION") == null ? "" : beanApoderados.getProperty("DESC_DESCRIPCION").toString();
						Empresa = beanApoderados.getProperty("NOM_EMPRESA") == null ? "" : beanApoderados.getProperty("NOM_EMPRESA").toString();	
						String descEsp = beanApoderados.getProperty("DES_PODER") == null ? "" : beanApoderados.getProperty("DES_PODER").toString();
						String TipoPoder = beanApoderados.getProperty("IND_TIPO_ESCRITURA").toString();						
						descTipoPoder = (TipoPoder.equals("PG") ? "Poder General" : TipoPoder.equals("PE") ? "Poder Especial" : TipoPoder.equals("CP") ? "Carta Poder" : "Escritura de Revocación");		
						
						GenericDataBean gdbRevocaciones = MngDataPoderes.query_REVOCACIONES(idOtorgaPoder);
						for(int j=0; j < gdbRevocaciones.beans.size(); j++){
							GenericBean beanRevocacion = gdbRevocaciones.get(j);	
							String apendiceRevocadoCurrent = "";								
							  apendiceRevocadoCurrent = beanRevocacion.getProperty("DESC_REVOCA") == null ? "" : beanRevocacion.getProperty("DESC_REVOCA").toString(); 
							  if(!apendiceRevocados.contains(apendiceRevocadoCurrent)){									
								  apendiceRevocados += apendiceRevocadoCurrent.trim().length()>0 ? "<i style='font-size:8pt'>" + apendiceRevocadoCurrent + "</i><br/>" : "";								 
							  }									  
						}
						
						apo = apo.replace("null", "");
						desc = desc.replace("null", "");
						descEsp = descEsp.replace("null", "");
						String row = rowPrintGEN.replace("APOTD", apo).replace("PODTD", descEsp ).replace("TIPOTD", descTipoPoder).replace("ESCTD", esc).replace("EMPTD", Empresa).replace("TPTD", pod);									
						
						row = row.replace("CLASS", i%2 == 0 ? "" : "class='tableRow2'" );
						
						htmlout += row;
						htmlout += "</table><br/>";
						htmlout += apendiceRevocados.trim().length()>0 ? "<div>" + apendiceRevocados + "</div><br/>" : "";
						
					}
%>										
					<div id="summary">
							<%=htmlout%>
					</div>
<%
				}		
			}
		}
%>				

	<jsp:include page="/jsp/components/pages_border/close.jsp"></jsp:include>

</body>
</html>
<%@page import="mx.com.televisa.derechocorporativo.util.FacesUtils"%>
<%@page import="mx.com.televisa.derechocorporativo.daos.MngDataPoderes"%>
<%@page import="mx.com.televisa.derechocorporativo.bean.GenericDataBean"%>
<%@page import="mx.com.televisa.derechocorporativo.bean.GenericBean"%>
<%@page import="java.util.Calendar"%>
<%@ taglib uri="pd4ml" prefix="pd4ml"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%	
	String fileName = "ReporteApoderadosPorEscritura.pdf";		
%>

<pd4ml:transform
	screenWidth="980" pageFormat="LETTER" fileName="<%= fileName %>"
	pageInsets="1,1,1,1,mm" adjustScreenWidth="true"
	enableTableBreaks="true" enableImageSplit="false" debug="true">
	<pd4ml:footer areaHeight="30" pageNumberTemplate="$[page] de $[total]" initialPageNumber="1"  pageNumberAlignment="right">
  <%    
       Calendar fecha = Calendar.getInstance();
       
       String[] meses = new String[]{
         "Enero",
         "Febrero",
         "Marzo",
         "Abril",
         "Mayo",
         "Junio",
         "Julio",
         "Agosto",
         "Septiembre",
         "Octubre",
         "Noviembre",
         "Diciembre"};
       int anio = fecha.get(Calendar.YEAR);
       int mes = fecha.get(Calendar.MONTH);
       int dia = fecha.get(Calendar.DATE);
      %>
      <table style="width: 100%;">  	  	
       	<tr> 
       		<td valign="middle" width="80%" style="text-align: right;"><font style="font-size: 12px;">Fecha de emisión: <%=dia+"/"+meses[mes]+"/"+anio %></font></td>       	
       	</tr>
       	<tr>
       			<td valign="middle" width="10%" style="text-align: right;"><font style="font-size: 12px;"><i>Página $[page] de $[total]</i></font></td>
       	</tr>
  	  </table>
 </pd4ml:footer>
	
<html>
<head>
<%@include file="/css/kaz-styles4Print.jsp"%>
<style type="text/css">
	.tableRow2{
		background:#DCE8F6;
	}	
</style>
</head>
<%
	request.setCharacterEncoding("UTF-8");

	String btnEjecutar = request.getParameter("btnEjecutar"); 
	
	String hiddenEmpresa = 	(request.getParameter("hiddenEmpresa") != null) ? request.getParameter("hiddenEmpresa") : "";
	String hiddenTipoPoder = 	(request.getParameter("hiddenTipoPoder") != null) ? request.getParameter("hiddenTipoPoder") : "";	
	String hiddenApoderados = (request.getParameter("hiddenApoderados") != null) ? request.getParameter("hiddenApoderados") : "";
	String hiddenPoder = (request.getParameter("hiddenPoder") != null) ? request.getParameter("hiddenPoder") : "";	
	String txtEmpresa = 	(request.getParameter("txtEmpresa") != null) ? request.getParameter("txtEmpresa") : "";
	String txtTipoPoder = 	(request.getParameter("txtTipoPoder") != null) ? request.getParameter("txtTipoPoder") : "";
	String txtApoderados = (request.getParameter("txtApoderados") != null) ? request.getParameter("txtApoderados") : "";
	String txtPoder = (request.getParameter("txtPoder") != null) ? request.getParameter("txtPoder") : "";
	String txtEscritura = (request.getParameter("txtEscritura") != null) ? request.getParameter("txtEscritura") : "";	

	String txtEmpresa2 = "<ul>";
	String txtEmpresa3 = "<ul>";
	
	if(!txtEmpresa.equals("")){			
		txtEmpresa = txtEmpresa.replace("</ul>","");
		String[] empresasList = txtEmpresa.split("</li>");
		txtEmpresa = "";
		int c = 1;
		for(String empresa : empresasList){
			switch(c){
			case 1: txtEmpresa += empresa + "</li>"; c++; break;
			case 2: txtEmpresa2 += empresa + "</li>"; c++; break;
			case 3: txtEmpresa3 += empresa + "</li>"; c=1; break;
			}			
		}	
		txtEmpresa += "</ul>";
		txtEmpresa2 = txtEmpresa2.equals("<ul>") ? null : txtEmpresa2+"</ul>";
		txtEmpresa3 = txtEmpresa3.equals("<ul>") ? null : txtEmpresa3+"</ul>";
		
	}		
	
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
							if(c>0){								
							%>			
							<pd4ml:page_break/>
							<%}%>							
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
						pod = pod.replace("&#9679;","&#8226;" );
						String apo = beanApoderados.getProperty("DESC_APODERADOS") == null ? "" : beanApoderados.getProperty("DESC_APODERADOS").toString();
						apo = apo.replace("<br /> &#9679;","</li><li>");
						apo = apo.replace("&#9679;","<ul><li>");										
						apo = apo.replace(" 	 ","");	
						apo = apo.replace("<br />","</li></ul>");											
						apo = apo.trim();
						String ad = beanApoderados.getProperty("DESC_ACTOSDOMINIO") == null ? "" : beanApoderados.getProperty("DESC_ACTOSDOMINIO").toString();
						ad = ad.replace("&#9679;","&#8226;" );
						String aa = beanApoderados.getProperty("DESC_ACTOSADMON") == null ? "" : beanApoderados.getProperty("DESC_ACTOSADMON").toString();
						aa = aa.replace("&#9679;","&#8226;" );
						String tc = beanApoderados.getProperty("DESC_TITULOSCREDITO") == null ? "" : beanApoderados.getProperty("DESC_TITULOSCREDITO").toString();
						tc = tc.replace("&#9679;","&#8226;" );
						String pc = beanApoderados.getProperty("DESC_PLEITOSCOBRANZA") == null ? "" : beanApoderados.getProperty("DESC_PLEITOSCOBRANZA").toString();
						pc = pc.replace("&#9679;","&#8226;" );
						
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
						desc = desc.replace("&#9679;","&#8226;" );
						String descEsp = beanApoderados.getProperty("DES_PODER") == null ? "" : beanApoderados.getProperty("DES_PODER").toString();
						apo = apo.replace("null", "");
						desc = desc.replace("null", "");
						descEsp = descEsp.replace("&#9679;","&#8226;");										
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
						apo = apo.replace("<br /> &#9679;","</li><li>");
						apo = apo.replace("&#9679;","<ul><li>");										
						apo = apo.replace(" 	 ","");	
						apo = apo.replace("<br />","</li></ul>");											
						apo = apo.trim();	
						String desc = beanApoderados.getProperty("DESC_DESCRIPCION") == null ? "" : beanApoderados.getProperty("DESC_DESCRIPCION").toString();
						desc = desc.replace("&#9679;","&#8226;" );
						Empresa = beanApoderados.getProperty("NOM_EMPRESA") == null ? "" : beanApoderados.getProperty("NOM_EMPRESA").toString();	
						String descEsp = beanApoderados.getProperty("DES_PODER") == null ? "" : beanApoderados.getProperty("DES_PODER").toString();
						descEsp = descEsp.replace("&#9679;","&#8226;" );
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
						
						apo = apo.replace("&#9679;","&#8226;" );
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

</body>
</html>
</pd4ml:transform>
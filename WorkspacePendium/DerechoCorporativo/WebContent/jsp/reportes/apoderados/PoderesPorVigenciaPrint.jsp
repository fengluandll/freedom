<%@page import="mx.com.televisa.derechocorporativo.daos.MngDataPoderes"%>
<%@page import="mx.com.televisa.derechocorporativo.bean.GenericDataBean"%>
<%@page import="mx.com.televisa.derechocorporativo.bean.GenericBean"%>
<%@page import="mx.com.televisa.derechocorporativo.modules.reports.apoderados.RepApoderados"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@ taglib uri="pd4ml" prefix="pd4ml"%>
<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<pd4ml:transform debug="true"
	screenWidth="980" pageFormat="LETTER" fileName="ReporteApoderadosPorVigencia.pdf"
	pageInsets="1,1,1,1,mm" adjustScreenWidth="true"
	enableTableBreaks="true" enableImageSplit="false">
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
        	<td valign="middle" width="90%" style="text-align: right;"><font style="font-size: 12px;">Fecha de emisión: <%=dia+"/"+meses[mes]+"/"+anio %></font></td>        
       	</tr>  
       	<tr>
       		<td valign="middle" width="10%" style="text-align: right;"><font style="font-size: 12px;"><i>Página $[page] de $[total]</i></font>  </td>
       	</tr>  
  	  </table>
 </pd4ml:footer>

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
	String txtFechaDesde = 	(request.getParameter("txtFechaDesde") != null) ? request.getParameter("txtFechaDesde") : "";
	String txtFechaHasta = 	(request.getParameter("txtFechaHasta") != null) ? request.getParameter("txtFechaHasta") : "";
	String txtApoderados = (request.getParameter("txtApoderados") != null) ? request.getParameter("txtApoderados") : "";
	String txtPoder = (request.getParameter("txtPoder") != null) ? request.getParameter("txtPoder") : "";
	String txtEscritura = (request.getParameter("txtEscritura") != null) ? request.getParameter("txtEscritura") : "";	

	String paramString = 	"hiddenEmpresa=" + hiddenEmpresa +
							"&hiddenTipoPoder=" + hiddenTipoPoder +
							"&txtFechaDesde=" + txtFechaDesde +
							"&txtFechaHasta=" + txtFechaHasta +
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
	String htmloutCP = "";	
	String htmloutPG = "";	
	String htmloutPE = "";	
	//apendice
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
		
		GenericDataBean gdb = MngDataPoderes.query_PODERES_POR_VIGENCIA(hiddenEmpresa, txtFechaDesde, txtFechaHasta, tipoPoder, apoderados, hiddenPoder, escritura);
		
		String empresaCurrent = "";
		String tipoEscritura = "";				
		String rowPrintPG = "<tr style='vertical-align:top' CLASS><td>ESCPD</td><td>VIGCIA</td><td>PODPD</td><td>APOPD</td><td>AD9999</td><td>AA9999</td><td>TC9999</td><td>PC9999</td><td>ESP</td></tr>";
		String rowPrintPE = "<tr style='vertical-align:top' CLASS><td>ESCPD</td><td>VIGCIA</td><td>PODPD</td><td>APOPD</td><td>PODDESPD</td></tr>";
		String rowPrintCP = "<tr style='vertical-align:top' CLASS><td>VIGCIA</td><td>PODPD</td><td>APOPD</td><td>PODDESPD</td></tr>";
		String rowPrintPGTMP = "";
		String rowPrintPETMP = "";
		String rowPrintCPTMP = "";
		String rowPrint = "";
		String apendiceRevocados = "";		
		
		if(gdb.beans.size() == 0) 
			htmlout = "No se encontraron registros";
						
		for(int i=0; i < gdb.beans.size(); i++){						

			GenericBean beanApoderados = gdb.get(i);			
			
			String empresa = beanApoderados.getProperty("NOM_EMPRESA").toString();
			String tipoEscrituraCurrent = beanApoderados.getProperty("IND_TIPO_ESCRITURA").toString();			
			//sacamos datos
			String esc = beanApoderados.getProperty("DES_ESCRITURA") == null ? "" : beanApoderados.getProperty("DES_ESCRITURA") == null ? "" : beanApoderados.getProperty("DES_ESCRITURA").toString();									
			String pod = beanApoderados.getProperty("DES_PODERTIPO") == null ? "" : beanApoderados.getProperty("DES_PODERTIPO").toString();
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
			String esp = beanApoderados.getProperty("DESC_PODER_ESPECIAL") == null ? "" : beanApoderados.getProperty("DESC_PODER_ESPECIAL").toString();
			esp = esp.replace("&#9679;","&#8226;" ).replace("null", "");			
			int idOtorgaPoder = Integer.valueOf(beanApoderados.getProperty("ID_OPODER_EP_PK").toString());
						
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
			descEsp = descEsp.replace("&#9679;","&#8226;" );
			descEsp = descEsp.replace("null","" );
			apo = apo.replace("null","");

			apendiceRevocados ="";						
		
			if(!empresaCurrent.equals(empresa)){																			
				htmlout += "<br><br><div><h3 align='center'><b>" + empresa + "</b></h3>";	
				empresaCurrent = empresa;
				tipoEscritura = "";
			}
												
			if(!tipoEscrituraCurrent.equals(tipoEscritura)){
				htmlout += "<h4>"+(tipoEscrituraCurrent.equals("PG") ? "Poderes Generales" : tipoEscrituraCurrent.equals("PE") ? "Poderes Especiales" : tipoEscrituraCurrent.equals("CP") ? "Cartas Poder" : "Escrituras de Revocación") + "</h4>";
				tipoEscritura = tipoEscrituraCurrent;
			}
			
			if(i==0 || !beanApoderados.getProperty("ID_OPODER_EP_PK").toString().equals(gdb.get(i-1).getProperty("ID_OPODER_EP_PK").toString())){
				apendiceRevocados +=  RepApoderados.getApendice(idOtorgaPoder,esc);		
										
				if(tipoEscritura.equals("CP")){				
					htmlout += "<table cellspacing='0' cellpadding='3' width='100%'>";
					htmloutCP += "<tr><th class='tableHeader' width='10%'>Fecha vencimiento</th><th class='tableHeader' width='10%'>Poder</th><th class='tableHeader'  width='35%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Apoderados&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th><th class='tableHeader'  width='45%'>Descripción</th></tr>";
					
					rowPrintCPTMP = rowPrintCP.replace("VIGCIA", vig).replace("PODPD", pod).replace("APOPD", apo).replace("PODDESPD", descEsp);
					htmloutCP += rowPrintCPTMP.replace("CLASS", i%2 == 0 ? "" : "class='tableRow2'" );
					
					htmloutCP += "</table></br>";	
					htmloutCP +=(apendiceRevocados.trim().length()>0 ? "<div>" + apendiceRevocados + "</div><br/>" : "");					
				}
				
				if(tipoEscritura.equals("PG")){					
					htmlout += "<table cellspacing='0' cellpadding='3' width='1650px' style='table-layout:fixed;'>";
					htmloutPG += "<tr><th class='tableHeader' width='5%'>Escritura No.</th><th class='tableHeader' width='5%'>Fecha vencimiento</th><th class='tableHeader' width='7%'>Poder</th><th class='tableHeader' width='17%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Apoderados&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th><th class='tableHeader' width='14%'>Actos de Dominio</th><th class='tableHeader' width='14%'>Actos de Administración</th><th class='tableHeader' width='14%'>Títulos de Crédito</th><th class='tableHeader' width='14%'>Pleitos y Cobranzas</th><th class='tableHeader' width='10%'>Especial</th></tr>";
					
					rowPrintPGTMP = rowPrintPG.replace("ESCPD", esc).replace("VIGCIA", vig).replace("PODPD", pod).replace("APOPD", apo).replace("AD9999", ad).replace("AA9999", aa).replace("TC9999", tc).replace("PC9999", pc).replace("ESP", esp);
					htmloutPG += rowPrintPGTMP.replace("CLASS", i%2 == 0 ? "" : "class='tableRow2'" );
					
					htmloutPG += "</table></br>";	
					htmloutPG +=(apendiceRevocados.trim().length()>0 ? "<div>" + apendiceRevocados + "</div><br/>" : "");					
				}
				if(tipoEscritura.equals("PE")){					
					htmlout += "<table cellspacing='0' cellpadding='3' width='100%'>";
					htmloutPE += "<tr><th class='tableHeader' width='10%'>Escritura No.</th><th class='tableHeader' width='10%'>Fecha vencimiento</th><th class='tableHeader'  width='10%'>Poder</th><th class='tableHeader'  width='35%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Apoderados&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th><th class='tableHeader'  width='35%'>Descripción</th></tr>";					
					
					rowPrintPETMP = rowPrintPE.replace("ESCPD", esc).replace("VIGCIA", vig).replace("PODPD", pod).replace("APOPD", apo).replace("PODDESPD", descEsp);
					htmloutPE += rowPrintPETMP.replace("CLASS", i%2 == 0 ? "" : "class='tableRow2'" );

					htmloutPE += "</table></br>";	
					htmloutPE +=(apendiceRevocados.trim().length()>0 ? "<div>" + apendiceRevocados + "</div><br/>" : "");
				}
								
				htmlout += htmloutCP + htmloutPG + htmloutPE;
				htmloutCP = htmloutPG = htmloutPE = "";																										
				}
			}
		}	
%>
<html>
	<head>	
		<%@include file="/css/kaz-styles4Print.jsp"%>
		<style type="text/css">
			.tableRow2{
				background:#DCE8F6;
			}
		</style>	 
	</head>
	<body>
		<jsp:include page="/jsp/components/pages_border/open-pre-title.jsp"></jsp:include>		
			Poderes por Vigencia			
		<jsp:include page="/jsp/components/pages_border/open-post-title.jsp"></jsp:include>
		
		<div id="summary">
			<%=htmlout %>
		</div>	
		
		<jsp:include page="/jsp/components/pages_border/close.jsp"></jsp:include>	
	</body>
</html>
</pd4ml:transform>
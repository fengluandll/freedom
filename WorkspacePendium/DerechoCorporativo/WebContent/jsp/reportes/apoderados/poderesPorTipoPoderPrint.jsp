<%@page import="mx.com.televisa.derechocorporativo.daos.MngDataPoderes"%>
<%@page import="mx.com.televisa.derechocorporativo.bean.GenericDataBean"%>
<%@page import="mx.com.televisa.derechocorporativo.bean.GenericBean"%>
<%@page import="mx.com.televisa.derechocorporativo.modules.reports.apoderados.RepApoderados"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@ taglib uri="pd4ml" prefix="pd4ml"%>
<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%	
	String fileName = "ReporteApoderadosPorTipoDePoder.pdf";
	String txtTipoPoder = 	(request.getParameter("txtTipoPoder") != null) ? request.getParameter("txtTipoPoder") : "";	
	String txtPoder = (request.getParameter("txtPoder") != null) ? request.getParameter("txtPoder") : "";
	String screenWidth = "";	
	if((txtTipoPoder.contains("Poder General")|| txtTipoPoder.length() == 0) && 
			(txtPoder.contains("PG") || txtPoder.length() == 0)){
		screenWidth = "720";
	}
	else{
		screenWidth = "980";
	}		
%>

<pd4ml:transform
	screenWidth="<%=screenWidth %>" pageFormat="LETTER" fileName="<%= fileName %>"
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
        	<td valign="middle" width="90%" style="text-align: right;"><font style="font-size: 12px;">Fecha de emisi�n: <%=dia+"/"+meses[mes]+"/"+anio %></font></td>        
       	</tr>  
       	<tr>
       		<td valign="middle" width="10%" style="text-align: right;"><font style="font-size: 12px;"><i>P�gina $[page] de $[total]</i></font>  </td>
       	</tr>  
  	  </table>
 </pd4ml:footer>
	
<html>
<head>
<title>Reporte Poderes por Tipo de Poder</title>
<%@include file="/css/kaz-styles4Print.jsp"%>
<style type="text/css">
	.tableRow2{
		background:#DCE8F6;
	}
</style>

</head>
<body onload="">

		
			<jsp:include page="open-pre-title.jsp"></jsp:include>
			
				<h2>Reporte Poderes por Tipo de Poder</h2>
			
			<jsp:include page="open-post-title.jsp"></jsp:include>
			
<form action="" method="post">

<table width="100%">
	<tr>
		<td colspan="3">

<%
	request.setCharacterEncoding("UTF-8");

	String btnEjecutar = request.getParameter("btnEjecutar"); 
	
	String hiddenEmpresa = 	(request.getParameter("hiddenEmpresa") != null) ? request.getParameter("hiddenEmpresa") : "";
	String hiddenTipoPoder = 	(request.getParameter("hiddenTipoPoder") != null) ? request.getParameter("hiddenTipoPoder") : "";	
	String hiddenApoderados = (request.getParameter("hiddenApoderados") != null) ? request.getParameter("hiddenApoderados") : "";	
	String hiddenPoder = (request.getParameter("hiddenPoder") != null) ? request.getParameter("hiddenPoder") : "";	
	txtTipoPoder = 	(request.getParameter("txtTipoPoder") != null) ? request.getParameter("txtTipoPoder") : "";
	String txtEmpresa = request.getParameter("txtEmpresa")!=null ? request.getParameter("txtEmpresa") : "";
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
	String txtApoderados = (request.getParameter("txtApoderados") != null) ? request.getParameter("txtApoderados") : "";
	txtPoder = (request.getParameter("txtPoder") != null) ? request.getParameter("txtPoder") : "";
	String txtEscritura = (request.getParameter("txtEscritura") != null) ? request.getParameter("txtEscritura") : "";	
		
	
	String txtApoderados2 = "<ul>";
	String txtApoderados3 = "<ul>";
	
	if(!txtApoderados.equals("")){			
		txtApoderados = txtApoderados.replace("</ul>","");
		String[] ApoderadossList = txtApoderados.split("</li>");
		txtApoderados = "";
		int c = 1;
		for(String Apoderados : ApoderadossList){
			switch(c){
			case 1: txtApoderados += Apoderados + "</li>"; c++; break;
			case 2: txtApoderados2 += Apoderados + "</li>"; c++; break;
			case 3: txtApoderados3 += Apoderados + "</li>"; c=1; break;
			}
			
		}	
		txtApoderados += "</ul>";
		txtApoderados2 = txtApoderados2.equals("<ul>") ? null : txtApoderados2+"</ul>";
		txtApoderados3 = txtApoderados3.equals("<ul>") ? null : txtApoderados3+"</ul>";
		
	}		
	
	
	String txtPoder2 = "<ul>";
	String txtPoder3 = "<ul>";
	
	if(!txtPoder.equals("")){			
		txtPoder = txtPoder.replace("</ul>","");
		String[] PodersList = txtPoder.split("</li>");
		txtPoder = "";
		int c = 1;
		for(String Poder : PodersList){
			switch(c){
			case 1: txtPoder += Poder + "</li>"; c++; break;
			case 2: txtPoder2 += Poder + "</li>"; c++; break;
			case 3: txtPoder3 += Poder + "</li>"; c=1; break;
			}			
		}	
		txtPoder += "</ul>";
		txtPoder2 = txtPoder2.equals("<ul>") ? null : txtPoder2+"</ul>";
		txtPoder3 = txtPoder3.equals("<ul>") ? null : txtPoder3+"</ul>";
		
	}		
	
	String tblFilters = "<table width=\"100%\" cellpadding=\"3\" cellspacing=\"3\"><tr><td width=\"33%\"></td><td width=\"33%\"></td><td width=\"33%\"></td></tr>";
		tblFilters += "<tr><td>Tipo de Poder:</td><td></td><td></td></tr>";
		tblFilters += "<tr><td valign=\"top\">Q1</td><td valign=\"top\"></td><td valign=\"top\"></td></tr>";
		tblFilters += "<tr><td>Poder:</td><td></td><td></td></tr>";
		tblFilters += "<tr><td valign=\"top\">Q2</td><td valign=\"top\">Qb2</td><td valign=\"top\">Qc2</td></tr>";
		tblFilters += "<tr><td>Empresa(s):</td><td></td><td></td></tr>";
		tblFilters += "<tr><td valign=\"top\">Q3</td><td valign=\"top\">Qb3</td><td valign=\"top\">Qc3</td></tr>";
		tblFilters += "<tr><td>Nombres de Apoderados:</td><td></td><td></td></tr>";
		tblFilters += "<tr><td valign=\"top\">Q4</td><td valign=\"top\">Qb4</td><td valign=\"top\">Qc4</td></tr>";
		tblFilters += "<tr><td>Escritura:</td><td></td><td></td></tr>";
		tblFilters += "<tr><td valign=\"top\">Q5</td><td valign=\"top\"></td><td valign=\"top\"></td></tr>";
		tblFilters += "</table>";
		
		tblFilters = txtTipoPoder.length()>0? tblFilters.replace("Q1",request.getParameter("txtTipoPoder")) : tblFilters.replace("Q1","").replace("Tipo de Poder:","");
		
		tblFilters = txtPoder.length()>0 ? tblFilters.replace("Q2",txtPoder) : tblFilters.replace("Q2","").replace("Poder:","");
		tblFilters = txtPoder2 == null ? tblFilters.replace("Qb2","") :  tblFilters.replace("Qb2",txtPoder2);
		tblFilters = txtPoder3 == null ? tblFilters.replace("Qc2","") :  tblFilters.replace("Qc2",txtPoder3);
		
		tblFilters = txtEmpresa.length() > 0 ? tblFilters.replace("Q3",txtEmpresa) : tblFilters.replace("Q3","").replace("Empresa(s):","");
		tblFilters = txtEmpresa2 == null ? tblFilters.replace("Qb3","") :  tblFilters.replace("Qb3",txtEmpresa2);
		tblFilters = txtEmpresa3 == null ? tblFilters.replace("Qc3","") :  tblFilters.replace("Qc3",txtEmpresa3);
		
		tblFilters = txtApoderados.length()>0 ? tblFilters.replace("Q4",txtApoderados) : tblFilters.replace("Q4","").replace("Nombres de Apoderados:","");
		tblFilters = txtApoderados2 == null ? tblFilters.replace("Qb4","") :  tblFilters.replace("Qb4",txtApoderados2);
		tblFilters = txtApoderados3 == null ? tblFilters.replace("Qc4","") :  tblFilters.replace("Qc4",txtApoderados3);
		
		tblFilters = txtEscritura.length()>0? tblFilters.replace("Q5",request.getParameter("txtEscritura")) : tblFilters.replace("Q5","").replace("Escritura:","");
		
%>
		</td>
	</tr>	
	<tr>
		<td colspan="3">
<fieldset>
				<%=tblFilters %>
				</fieldset>			
		</td>
	</tr>	
	<tr>
		<td colspan="3">
			<br>
		</td>
	</tr>
	<tr>
		<td colspan="3" align="left">
	
				<%							
				String htmlout = "";
				String htmloutCP = "";	
				String htmloutPG = "";	
				String htmloutPE = "";					
				
				String tipoEscrituraCurrentConca = "";
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
						GenericDataBean gdb = MngDataPoderes.query_PODERES_POR_TIPO_DE_PODER(hiddenEmpresa, tipoPoder, apoderados, hiddenPoder, escritura);					
						
						String empresaCurrent = "";
						String tipoEscrituraCurrent = "";
						String desPoderTipoCurrent = "";
						String rowPrintPG = "<tr style='vertical-align:top' CLASS><td>ESCPD</td><td>FECPD</td><td>APOPD</td><td>PODTD</td><td>AA9999</td><td>AA9999</td><td>TC9999</td><td>PC9999</td><td>ESP</td><td>VIGCIA</td></tr>";
						String rowPrintPE = "<tr style='vertical-align:top' CLASS><td>ESCPD</td><td>FECPD</td><td>APOPD</td><td>PODTD</td><td>VIGCIA</td></tr>";
						String rowPrintCP = "<tr style='vertical-align:top' CLASS><td>FECPD</td><td>APOPD</td><td>PODTD</td><td>VIGCIA</td></tr>";
						String rowPrintPGTMP = "";
						String rowPrintPETMP = "";
						String rowPrintCPTMP = "";
						String rowPrint = "";
						String apendiceRevocados = "";
						String tipoEscrituraConca="";
						
						if(gdb.beans.size() == 0) 
							htmlout = "No se encontraron registros";
										
						for(int i=0; i < gdb.beans.size(); i++){
							
						
						//for(GenericBean beanApoderados  : gdb.beans){
							GenericBean beanApoderados = gdb.get(i);
							
							String empresa = beanApoderados.getProperty("NOM_EMPRESA").toString();
							String tipoEscritura = beanApoderados.getProperty("IND_TIPO_ESCRITURA").toString();
							String desPoderTipo = beanApoderados.getProperty("DES_PODERTIPO").toString();
							//sacamos datos
							String esc = beanApoderados.getProperty("DES_ESCRITURA") == null ? "" : beanApoderados.getProperty("DES_ESCRITURA") == null ? "" : beanApoderados.getProperty("DES_ESCRITURA").toString();
							tipoEscrituraConca =  tipoEscritura+desPoderTipo;
							String fec = beanApoderados.getProperty("FEC_OTORGAMIENTO_INSTR") == null ? "" : beanApoderados.getProperty("FEC_OTORGAMIENTO_INSTR").toString();
							String fecCP = beanApoderados.getProperty("FEC_FECHA") == null ? "" : beanApoderados.getProperty("FEC_FECHA").toString();
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
							
							int idOtorgaPoder = Integer.valueOf(beanApoderados.getProperty("ID_OPODER_EP_PK").toString());							
							
							//termina
							if(!empresaCurrent.equals(empresa)){
																										
								htmlout += "<br><br><div><h3 align='center'><b>" + empresa + "</b></h3>";	
								empresaCurrent = empresa;							
								
							}
							
							if(!desPoderTipoCurrent.equals(desPoderTipo)){								
								htmlout += "<h3>"+desPoderTipo+"</h3>";																
							}
							
							if(!tipoEscrituraCurrentConca.equals(tipoEscrituraConca)){
								htmlout += "<h4>"+(tipoEscritura.equals("PG") ? "Poderes Generales" : tipoEscritura.equals("PE") ? "Poderes Especiales" : tipoEscritura.equals("CP") ? "Cartas Poder" : "Escrituras de Revocaci�n") + "</h4>";	
							}
							
							if(i==0 || !beanApoderados.getProperty("ID_OPODER_EP_PK").toString().equals(gdb.get(i-1).getProperty("ID_OPODER_EP_PK").toString())){
															
								apendiceRevocados +=  RepApoderados.getApendice(idOtorgaPoder,esc);
								
									htmlout += "<table cellspacing='0' cellpadding='3' width='100%'>";
									
									if(tipoEscritura.equals("CP")){									
										htmloutCP += "<tr><th class='tableHeader' width='10%'>Fecha</th><th class='tableHeader'  width='35%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Apoderados&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th><th class='tableHeader'  width='45%'>Descripci�n</th><th class='tableHeader'  width='10%'>Vigencia</th></tr>";
										rowPrintCPTMP = rowPrintCP.replace("FECPD", fecCP).replace("APOPD", apo).replace("PODTD", descEsp).replace("VIGCIA", vig);
										htmloutCP += rowPrintCPTMP.replace("CLASS", i%2 == 0 ? "" : "class='tableRow2'" );									
										htmloutCP += "</table><br/>";	
										htmloutCP +=(apendiceRevocados.trim().length()>0 ? "<div>" + apendiceRevocados + "</div><br/>" : "");
									}
									
									if(tipoEscritura.equals("PG")){									
										htmloutPG += "<tr><th class='tableHeader' width='5%'>Escritura No.</th><th class='tableHeader' width='5%'>Fecha</th><th class='tableHeader' width='15%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Apoderados&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th><th class='tableHeader' width='5%'>Poder</th><th class='tableHeader' width='12%'>Actos de Dominio</th><th class='tableHeader' width='12%'>Actos de Administraci�n</th><th class='tableHeader' width='12%'>T�tulos de Cr�dito</th><th class='tableHeader' width='12%'>Pleitos y Cobranzas</th><th class='tableHeader' width='10%'>Especial</th><th class='tableHeader' width='5%'>Vigencia</th></tr>";
										rowPrintPGTMP = rowPrintPG.replace("ESCPD", esc).replace("FECPD", fec).replace("APOPD", apo).replace("PODTD", pod).replace("AA9999", ad).replace("AA9999", aa).replace("TC9999", tc).replace("PC9999", pc).replace("ESP", esp).replace("VIGCIA", vig);
										htmloutPG += rowPrintPGTMP.replace("CLASS", i%2 == 0 ? "" : "class='tableRow2'" );
										htmloutPG += "</table><br/>";	
										htmloutPG +=(apendiceRevocados.trim().length()>0 ? "<div>" + apendiceRevocados + "</div><br/>" : "");
									}
									if(tipoEscritura.equals("PE")){
										htmloutPE += "<tr><th class='tableHeader' width='10%'>Escritura No.</th><th class='tableHeader' width='10%'>Fecha</th><th class='tableHeader'  width='35%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Apoderados&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th><th class='tableHeader'  width='35%'>Descripci�n</th><th class='tableHeader'  width='10%'>Vigencia</th></tr>";																	
										rowPrintPETMP = rowPrintPE.replace("ESCPD", esc).replace("FECPD", fec).replace("APOPD", apo).replace("PODTD", descEsp).replace("VIGCIA", vig);
										htmloutPE += rowPrintPETMP.replace("CLASS", i%2 == 0 ? "" : "class='tableRow2'" );
										htmloutPE += "</table><br/>";	
										htmloutPE +=(apendiceRevocados.trim().length()>0 ? "<div>" + apendiceRevocados + "</div><br/>" : "");									
									}								
									
									htmlout += htmloutCP + htmloutPG + htmloutPE;
									htmloutCP = htmloutPG = htmloutPE = "";
																
									tipoEscrituraCurrent = tipoEscritura;
									desPoderTipoCurrent = desPoderTipo;	
										
									tipoEscrituraCurrentConca = tipoEscrituraConca;
								}
						}						
					}
				
				%>
				<%=htmlout %>
							
		</td>
	</tr>
	
	
</table>

</form>



<jsp:include page="/jsp/components/pages_border4PrintFullContext/close.jsp"></jsp:include>



</body>
</html>
</pd4ml:transform>
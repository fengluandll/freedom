<%@page import="mx.com.televisa.derechocorporativo.daos.MngDataPoderes"%>
<%@page import="mx.com.televisa.derechocorporativo.bean.GenericDataBean"%>
<%@page import="mx.com.televisa.derechocorporativo.bean.GenericBean"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@ taglib uri="pd4ml" prefix="pd4ml"%>
<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%!
	String fileName = "ReporteApoderadosPorFacultades.pdf";	
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
		
	String txtEmpresa = request.getParameter("txtEmpresa")!=null ? request.getParameter("txtEmpresa") : "";	
	String txtFacultades = (request.getParameter("txtFacultades") != null) ? request.getParameter("txtFacultades") : "";
	String txtTipoPoder = 	(request.getParameter("txtTipoPoder") != null) ? request.getParameter("txtTipoPoder") : "";
	String txtApoderados = (request.getParameter("txtApoderados") != null) ? request.getParameter("txtApoderados") : "";
	String txtPoder = (request.getParameter("txtPoder") != null) ? request.getParameter("txtPoder") : "";	
	String txtEscritura = (request.getParameter("txtEscritura") != null) ? request.getParameter("txtEscritura") : "";
	
	if(btnEjecutar != null && btnEjecutar.equals("Generar")) {						
		
		String tipoPoderFilter = txtTipoPoder.replace("<ul><li>", "'");
		tipoPoderFilter = tipoPoderFilter.replace("</li><li>","','");
		tipoPoderFilter = tipoPoderFilter.replace("</li></ul>", "'");
		tipoPoderFilter = tipoPoderFilter.equals("<ul></ul>") ? null : tipoPoderFilter;
		tipoPoderFilter = tipoPoderFilter.replace("Carta Poder","CP").replace("Poder Especial","PE").replace("Poder General","PG");
		
		String escritura = txtEscritura.equals("") ? "" : "'%"+txtEscritura+"%'";

		String apoderados = txtApoderados.replace("</li><li>","%' OR PENDIUM_OTORGAPODER_EP_TAB.DESC_APODERADOS LIKE '%");
		apoderados = apoderados.replace("<ul><li>","PENDIUM_OTORGAPODER_EP_TAB.DESC_APODERADOS LIKE '%");
		apoderados = apoderados.replace("</li></ul>","%'");
		apoderados = apoderados.equals("<ul></ul>") ? null : apoderados;
		
		txtFacultades = txtFacultades.replace("<ul><li>", "'");
		txtFacultades = txtFacultades.replace("</li><li>","','");
		txtFacultades = txtFacultades.replace("</li></ul>", "'");
		txtFacultades = txtFacultades.equals("<ul></ul>") ? "" : txtFacultades;
		
		if(txtFacultades.contains("Actos de Dominio")){
			filterAD = "AD9999";			
		}
		 
		if(txtFacultades.contains("Actos de Administraci�n")){
			filterAA = "AA9999";			
		}
		
		if(txtFacultades.contains("T�tulos de Cr�dito")){
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
	String facultadEspecial = bean.getProperty("DES_PODERTIPO") != null ? bean.getProperty("DES_PODERTIPO").toString() : ""; 
					
	boolean closeTable = false;
	
	if(!empresaCurrent.equals(empresa)){																									
		htmlout += "<br/><br/><div><h4 align='center'>" + empresa + "</h4>";																			
		empresaCurrent = empresa;
		tipoPoderCurrent = "";				
	}
	
	if(!facultadCurrent.equals(facultadEspecial) && (tipoPoder.equals("PE") || tipoPoder.equals("CP"))){																				
		htmlout += "<h4>" + facultadEspecial + "</h4>";																												
		facultadCurrent = facultadEspecial;		
		tipoPoderCurrent = "";
	} 
	
	apendiceRevocados = "";
	if(!tipoPoderCurrent.equals(tipoPoder)){			
		htmlout += "<h4>"+(tipoPoder.equals("PG") ? "Poderes Generales" : tipoPoder.equals("PE") ? "Poderes Especiales" : tipoPoder.equals("CP") ? "Cartas Poder" : "Escrituras de Revocaci�n") + "</h4>";
		tipoPoderCurrent = tipoPoder;
	}
		
		String esc = bean.getProperty("DES_ESCRITURA") == null ? "" : bean.getProperty("DES_ESCRITURA").toString();
		int idOtorgaPoder = Integer.valueOf(bean.getProperty("ID_OPODER_EP_PK").toString());
		String fec = bean.getProperty("FEC_OTORGAMIENTO_INSTR") == null ? "" : bean.getProperty("FEC_OTORGAMIENTO_INSTR").toString();
		String fecCP = bean.getProperty("FEC_FECHA") == null ? "" : bean.getProperty("FEC_FECHA").toString();
		String pod = bean.getProperty("DES_PODERTIPO") == null ? "" : bean.getProperty("DES_PODERTIPO").toString();
		String apo = bean.getProperty("DESC_APODERADOS") == null ? "" : bean.getProperty("DESC_APODERADOS").toString();
		apo = apo.replace("<br /> &#9679;","</li><li>");
		apo = apo.replace("&#9679;","<ul><li>");										
		apo = apo.replace(" 	 ","");	
		apo = apo.replace("<br />","</li></ul>");											
		apo = apo.trim();
		String ad = bean.getProperty("DESC_ACTOSDOMINIO") == null ? "" : bean.getProperty("DESC_ACTOSDOMINIO").toString();
		ad = ad.replace("&#9679;","&#8226;" );
		String aa = bean.getProperty("DESC_ACTOSADMON") == null ? "" : bean.getProperty("DESC_ACTOSADMON").toString();
		aa = aa.replace("&#9679;","&#8226;" );
		String tc = bean.getProperty("DESC_TITULOSCREDITO") == null ? "" : bean.getProperty("DESC_TITULOSCREDITO").toString();
		tc = tc.replace("&#9679;","&#8226;" );
		String pc = bean.getProperty("DESC_PLEITOSCOBRANZA") == null ? "" : bean.getProperty("DESC_PLEITOSCOBRANZA").toString();
		pc = pc.replace("&#9679;","&#8226;" );

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
		descEsp = descEsp.replace("&#9679;","&#8226;" );
		descEsp = descEsp.replace("null","");
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
					columnasPG = "<table cellspacing='0' cellpadding='3' width='100%'><tr><th class='tableHeader' width='5%'>Escritura No.</th><th class='tableHeader' width='5%'>Fecha</th><th class='tableHeader' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Apoderados&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th><th class='tableHeader' width='5%'>Poder</th>";
					if(filterAD != null)
						columnasPG += "<th class='tableHeader' width='12%'>Actos de Dominio</th>";
					if(filterAA != null)
						columnasPG += "<th class='tableHeader' width='12%'>Actos de Administraci�n</th>";
					if(filterTC != null)
						columnasPG += "<th class='tableHeader' width='12%'>T�tulos de Cr�dito</th>";
					if(filterPC != null)
						columnasPG += "<th class='tableHeader' width='12%'>Pleitos y Cobranzas</th>";	
					columnasPG += "<th class='tableHeader' width='5%'>Vigencia</th></tr>";
					htmlout += columnasPG; 								
				}
				else{
					htmlout += "<table cellspacing='0' cellpadding='3' width='100%'><tr><th class='tableHeader' width='5%'>Escritura No.</th><th class='tableHeader' width='5%'>Fecha</th><th class='tableHeader' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Apoderados&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th><th class='tableHeader' width='5%'>Poder</th>"
					+ "<th class='tableHeader' width='12%'>Actos de Dominio</th><th class='tableHeader' width='12%'>Actos de Administraci�n</th><th class='tableHeader' width='12%'>T�tulos de Cr�dito</th><th class='tableHeader' width='12%'>Pleitos y Cobranzas</th><th class='tableHeader' width='12%'>Vigencia</th></tr>";
				}
				
				rowPrint = rowPrintPG;
		
				row = rowPrint.replace("ESCPD", esc).replace("FECPD", fec).replace("APOPD", apo).replace("PODPD", pod).replace("AD9999", ad).replace("AA9999", aa).replace("TC9999", tc).replace("PC9999", pc).replace("VIGCIA", vig);	
				break;
			case "CP": 
				htmlout += "<table cellspacing='0' cellpadding='3' width='100%'><tr><th class='tableHeader' style='width:8%'>Fecha</th><th class='tableHeader' style='width:8%'>Poder</th><th class='tableHeader' style='width:35%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Apoderados&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th><th class='tableHeader' style='width:40%'>Descripci�n</th><th class='tableHeader' style='width:9%'>Vigencia</th></tr>";
				rowPrint = rowPrintCP;
				row = rowPrint.replace("FECPD", fecCP).replace("PODPD", pod).replace("APOPD", apo).replace("DESPD", descEsp).replace("VIGCIA", vig);	
				break;
			case "PE": 
				htmlout += "<table cellspacing='0' cellpadding='3' width='100%'><tr><th class='tableHeader' style='width:6%'>Escritura No.</th><th class='tableHeader' style='width:6%'>Fecha</th><th class='tableHeader' style='width:6%'>Poder</th><th class='tableHeader' style='width:35%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Apoderados&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th><th class='tableHeader' style='width:40%'>Descripci�n</th><th class='tableHeader' style='width:7%'>Vigencia</th></tr>";
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
%><pd4ml:transform
	screenWidth="780" pageFormat="LETTER" fileName="<%= fileName %>"
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
	<title>Reporte Poderes por Facultades</title>
	<%@include file="/css/kaz-styles4Print.jsp"%>
	<style type="text/css">
		.tableRow2{
			background:#DCE8F6;
		}
	</style>
</head>
<body>		
	<jsp:include page="open-pre-title.jsp"></jsp:include>
			
		<h2>Reporte Poderes por Facultades</h2>
			
	<jsp:include page="open-post-title.jsp"></jsp:include>
			
<form>
<%

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
	tblFilters += "<tr><td>Facultades:</td><td></td><td></td></tr>";
	tblFilters += "<tr><td valign=\"top\">Q6</td><td valign=\"top\"></td><td valign=\"top\"></td></tr>";
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
	tblFilters = txtFacultades.length()>0? tblFilters.replace("Q6",request.getParameter("txtFacultades")) : tblFilters.replace("Q6","").replace("Facultades:","");

	
	
	
	
	
	
	
	
	
		
		
%>
<table width="100%">	
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
				
			<%=htmlout %>
								
		</td>
	</tr>
		
</table>

</form>

<jsp:include page="/jsp/components/pages_border4PrintFullContext/close.jsp"></jsp:include>

</body>
</html>
</pd4ml:transform>
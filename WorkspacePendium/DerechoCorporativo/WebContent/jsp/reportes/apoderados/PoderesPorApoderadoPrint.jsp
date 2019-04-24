<%@page import="mx.com.televisa.derechocorporativo.util.FacesUtils"%>
<%@page import="mx.com.televisa.derechocorporativo.daos.MngDataPoderes"%>
<%@page import="mx.com.televisa.derechocorporativo.bean.GenericDataBean"%>
<%@page import="mx.com.televisa.derechocorporativo.bean.GenericBean"%>
<%@page import="java.util.Calendar"%>
<%@ taglib uri="pd4ml" prefix="pd4ml"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%	
	String fileName = "ReporteApoderadosPorApoderado.pdf";			
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
        	<td valign="middle" width="90%" style="text-align: right;"><font style="font-size: 12px;">Fecha de emisi�n: <%=dia+"/"+meses[mes]+"/"+anio %></font></td>        
       	</tr>  
       	<tr>
       		<td valign="middle" width="10%" style="text-align: right;"><font style="font-size: 12px;"><i>P�gina $[page] de $[total]</i></font>  </td>
       	</tr>
  	  </table>
 </pd4ml:footer>
	
<html>
<head>
<title>Reporte Poderes por Apoderado</title>
<%@include file="/css/kaz-styles4Print.jsp"%>
<style type="text/css">
	.tableRow2{
		background:#DCE8F6;
	}
	h2{
		font-size:15pt;
	}
</style>
</head>
<body>
	<jsp:include page="open-pre-title.jsp"></jsp:include>			
			<h2>Reporte Poderes por Apoderado</h2>
	<jsp:include page="open-post-title.jsp"></jsp:include>
	
<div style="width:100%" >
	<table width="100%">
		<tr>
			<td colspan="3">
	
	<%	
		request.setCharacterEncoding("UTF-8");
	
		String btnEjecutar = request.getParameter("btnEjecutar"); 
		
		String hiddenEmpresa = (request.getParameter("hiddenEmpresa") != null) ? request.getParameter("hiddenEmpresa") : "";
		String hiddenTipoPoder = (request.getParameter("hiddenTipoPoder") != null) ? request.getParameter("hiddenTipoPoder") : "";
		String hiddenApoderados = (request.getParameter("hiddenApoderados") != null) ? request.getParameter("hiddenApoderados") : "";
		String hiddenPoder = (request.getParameter("hiddenPoder") != null) ? request.getParameter("hiddenPoder") : "";				
		String txtTipoPoder = (request.getParameter("txtTipoPoder") != null) ? request.getParameter("txtTipoPoder") : "";
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
		String txtPoder = (request.getParameter("txtPoder") != null) ? request.getParameter("txtPoder") : "";
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
					if(btnEjecutar != null && btnEjecutar.equals("Generar")) {
						String tipoPoder = txtTipoPoder.replace("<ul><li>", "'");
						tipoPoder = tipoPoder.replace("</li><li>","','");
						tipoPoder = tipoPoder.replace("</li></ul>", "'");
						tipoPoder = tipoPoder.equals("<ul></ul>") ? null : tipoPoder;
						tipoPoder = tipoPoder.replace("Carta Poder","CP").replace("Poder Especial","PE").replace("Poder General","PG");
						String escritura = txtEscritura.equals("") ? "" : "'%"+txtEscritura+"%'";
						GenericDataBean gdb = MngDataPoderes.query_PODERES_POR_APODERADOS(hiddenEmpresa, tipoPoder, hiddenApoderados, hiddenPoder, escritura);
													
						String ApoderadoCurrent = "";
						String EmpresaCurrent = "";
						String TipoPoderCurrent = "";
						String rowPrintPG = "<tr style='vertical-align:top' CLASS><td>ESCPD</td><td>FECPD</td><td>PODPD</td><td>AD9999</td><td>AA9999</td><td>TC9999</td><td>PC9999</td><td>EP9999</td><td>VIGCIA</td></tr>";
						String rowPrintPE = "<tr CLASS><td>ESCPD</td><td>FECPD</td><td>PODPD</td><td>DESPD</td><td>VIGCIA</td></tr>";
						String rowPrintCP = "<tr CLASS><td>FECPD</td><td>PODPD</td><td>DESPD</td><td>VIGCIA</td></tr>";						
						String rowPrint = "";
						if(gdb.beans.size() == 0) 
							 htmlout = "No se encontraron registros";
						
						for(int i=0; i < gdb.beans.size(); i++){
							GenericBean bean = gdb.get(i);
							
							String Apoderado = bean.getProperty("DESC_NOM_EMPL").toString();												
							String Empresa = bean.getProperty("NOM_EMPRESA").toString();
							String TipoPoder = bean.getProperty("IND_TIPO_ESCRITURA").toString();
							
							boolean closeTable = false;
							
							if(!ApoderadoCurrent.equals(Apoderado)){							 								 
								htmlout += "<br/><br/><div><h2 align='center' style='font-size:15pt'>" + Apoderado + "</h2>";																			
								ApoderadoCurrent = Apoderado;	
								EmpresaCurrent = "";							
							}
							
							if(!EmpresaCurrent.equals(Empresa)){							 								 
								htmlout += "<h3 style='font-size:13pt'>" + Empresa + "</h3>";																			
								EmpresaCurrent = Empresa;	
								TipoPoderCurrent = "";															
							}
							
							if(!TipoPoderCurrent.equals(TipoPoder)){								 								 
								htmlout += "<h4 style='font-size:11pt'>"+(TipoPoder.equals("PG") ? "Poderes Generales" : TipoPoder.equals("PE") ? "Poderes Especiales" : TipoPoder.equals("CP") ? "Cartas Poder" : "Escrituras de Revocaci�n") + "</h4>";
								TipoPoderCurrent = TipoPoder;
							}
									
								switch(TipoPoder){
								case "PG": 
									htmlout += "<table cellspacing='0' cellpadding='3' width='100%'><tr><th class='tableHeader' width='3%'>Escritura No.</th><th class='tableHeader' width='3%'>Fecha</th><th class='tableHeader' width='10%'>Poder</th><th class='tableHeader' width='16%'>Actos de Dominio</th><th class='tableHeader' width='16%'>Actos de Administraci�n</th><th class='tableHeader' width='16%'>T�tulos de Cr�dito</th><th class='tableHeader' width='16%'>Pleitos y Cobranzas</th><th class='tableHeader' width='16%'>Especial</th><th class='tableHeader' width='4%'>Vigencia</th><tr>";
									rowPrint = rowPrintPG;
									break;
								case "CP": 
									htmlout += "<table cellspacing='0' cellpadding='3' width='100%'><tr><th class='tableHeader' width='10%'>Fecha</th><th class='tableHeader' width='10%'>Poder</th><th class='tableHeader' width='70%'>Descripci�n</th><th class='tableHeader' width='10%'>Vigencia</th><tr>";
									rowPrint = rowPrintCP;
									break;
								case "PE": 
									htmlout += "<table cellspacing='0' cellpadding='3' width='100%'><tr><th class='tableHeader' width='10%'>Escritura No.</th><th class='tableHeader' width='10%'>Fecha</th><th class='tableHeader' width='10%'>Poder</th><th class='tableHeader' width='60%'>Descripci�n</th><th class='tableHeader' width='10%'>Vigencia</th><tr>";
									rowPrint = rowPrintPE;
									break;
								}									
																
								String esc = bean.getProperty("DES_ESCRITURA") == null ? "" : bean.getProperty("DES_ESCRITURA") == null ? "" : bean.getProperty("DES_ESCRITURA").toString();
								String fec = bean.getProperty("FEC_OTORGAMIENTO_INSTR") == null ? "" : bean.getProperty("FEC_OTORGAMIENTO_INSTR").toString();
								String fecCP = bean.getProperty("FEC_FECHA") == null ? "" : bean.getProperty("FEC_FECHA").toString();
								String pod = bean.getProperty("DES_PODERTIPO") == null ? "" : bean.getProperty("DES_PODERTIPO").toString();																		
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
								
								String iRevoca = bean.getProperty("IND_APREVOCA") == null ? null : bean.getProperty("IND_APREVOCA").toString();
								String descRev = bean.getProperty("DESC_REVOCA") == null ? "" : bean.getProperty("DESC_REVOCA").toString();
								
								String desc = bean.getProperty("DESC_DESCRIPCION") == null ? "" : bean.getProperty("DESC_DESCRIPCION").toString();
								desc = desc.replace("&#9679;","&#8226;" );
								String descEsp = bean.getProperty("DES_PODER") == null ? "" : bean.getProperty("DES_PODER").toString();
								descEsp = descEsp.replace("&#9679;","&#8226;" );									
								desc = desc.replace("null", "");
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
								
								htmlout += row;
								htmlout += "</table></div><br/>";
								htmlout += iRevoca == null ? "" : "<br/><div>" + descRev + "</div><br/>";
							}
						htmlout = htmlout.equals("") ? htmlout : htmlout;							
					}
					%>
					<%=htmlout %>
				
			</td>
		</tr>
		
		
	</table>

</div>		
<jsp:include page="/jsp/components/pages_border4PrintFullContext/close.jsp"></jsp:include>			
			

</body>
</html>
</pd4ml:transform>
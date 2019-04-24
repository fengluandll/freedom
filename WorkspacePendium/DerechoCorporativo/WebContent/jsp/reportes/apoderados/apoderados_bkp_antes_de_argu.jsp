<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="mx.com.televisa.derechocorporativo.model.CatalogElementV2"%>
<%@page import="mx.com.televisa.derechocorporativo.modules.reports.apoderados.RepApoderados"%>
<%@page import="mx.com.televisa.derechocorporativo.data.Record"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.lang.reflect.Array"%>
<%@page import="mx.com.televisa.derechocorporativo.model.Catalog"%>
<%@page import="mx.com.televisa.derechocorporativo.model.CatalogElement"%>
<%@page import="java.util.List"%>
<%@page import="mx.com.televisa.derechocorporativo.modules.reports.tenecascada.TenenciaCascada"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="mx.com.televisa.derechocorporativo.components.JSCal"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="mx.com.televisa.derechocorporativo.util.TextFormatter"%>
<%@page import="mx.com.televisa.derechocorporativo.util.FacesUtils"%>
<%@page import="mx.com.televisa.derechocorporativo.model.DetailApoderados"%>
<%@page import="mx.com.televisa.derechocorporativo.util.StringUtils"%>
<%@page import="mx.com.televisa.derechocorporativo.model.DetailApoderadosDTO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<%@include file="/css/kaz-styles.jsp"%>

<script type="text/javascript">

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


</script>


</head>
<body onload="">

			<jsp:include page="/jsp/components/pages_border/open-pre-title.jsp"></jsp:include>
			
				Reporte de Apoderados
			
			<jsp:include page="/jsp/components/pages_border/open-post-title.jsp"></jsp:include>
			<jsp:include page="/jsp/components/backPageReports/regresaMenuReportes.jsp">
				<jsp:param name="action" value="rPredefinidos"/>
			</jsp:include>
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
	//System.out.println("Argu hiddenPoder "+hiddenPoder);
	if(hiddenPoder != null && !hiddenPoder.equals("")){
		//hiddenPoder = RepApoderados.getIdValorCatalogo(hiddenPoder);	
		//System.out.println("Argu hiddenPoder renovado "+hiddenPoder);
	}
	
	String txtEmpresa = 	(request.getParameter("txtEmpresa") != null) ? TextFormatter.removeAccents(request.getParameter("txtEmpresa")) : "";
	String txtTipoPoder = 	(request.getParameter("txtTipoPoder") != null) ? request.getParameter("txtTipoPoder") : "";
	String txtApoderados = (request.getParameter("txtApoderados") != null) ? request.getParameter("txtApoderados") : "";
	String txtPoder = (request.getParameter("txtPoder") != null) ? request.getParameter("txtPoder") : "";
	String txtEscritura = (request.getParameter("txtEscritura") != null) ? request.getParameter("txtEscritura") : "";	

	String paramString = 	"hiddenEmpresa=" + hiddenEmpresa +
							"&hiddenTipoPoder=" + hiddenTipoPoder +
							"&txtEscritura=" + txtEscritura +
							"&hiddenApoderados=" + hiddenApoderados +
							"&hiddenPoder=" + hiddenPoder +
							"&txtEmpresa=" + txtEmpresa +
							"&txtTipoPoder=" + txtTipoPoder +
							"&txtApoderados=" + txtApoderados +
							"&txtPoder=" + txtPoder +
							"&btnEjecutar=Generar"
							;

	ArrayList<Record> records = null;
	
	if(btnEjecutar != null && btnEjecutar.equals("Generar")) {
		
		
		////////
		//records = RepApoderados.getInfo(hiddenEmpresa, hiddenTipoPoder, txtEscritura);
		
		records = RepApoderados.getEmpresas(hiddenEmpresa, hiddenApoderados, hiddenPoder);
		
		
		//String paramString = "";
		//paramString += (request.getParameter("hiddenConsolida") != null) ? 		"&hiddenConsolida=" + request.getParameter("hiddenConsolida") : "";
	}			
	
	
	String showMode = "TREE";
	
	
	  
    
	
%>


		</td>
	</tr>	
	<tr>
		<td colspan="3">
			<table width="80%" border='0' cellpadding="0" cellspacing="0" align="center">
			
				<tr>
					
					<td width="30%" class="tableRow2">Empresa(s):
					<div id="divEmpresa"><%= (request.getParameter("txtEmpresa")!=null)?  TextFormatter.removeAccents( (new String(request.getParameter("txtEmpresa").getBytes("ISO-8859-1"),"UTF-8")) ) : ""%></div>
					</td>
					
					<td class="tableRow2">	
							<div align='right'>
						<img src='<%= request.getContextPath() %>/img/btn_search.png' 
										onclick="openMultiSelectPupUp('EMPRESAS','hiddenEmpresa','divEmpresa','innerHTML',document.getElementById('hiddenEmpresa').value,'UL')">
												
						<img src='<%= request.getContextPath() %>/img/btn_clean.png' 
										onclick="document.getElementById('hiddenEmpresa').value = '';document.getElementById('divEmpresa').innerHTML = '';">
						</div>
						
						
						<input type="hidden" id='hiddenEmpresa' name='hiddenEmpresa' value='<%=hiddenEmpresa%>'>
						
						<%--
						<input id='txtEmpresa' 
							name='txtEmpresa' 
							readonly="readonly" size="40" 
							
							value="<%= (request.getParameter("txtEmpresa")!=null)? new String(request.getParameter("txtEmpresa").getBytes("ISO-8859-1"),"UTF-8") : ""%>">
 						--%>
 						
 						<%--<div id="divEmpresa"><%= (request.getParameter("txtEmpresa")!=null)? new String(request.getParameter("txtEmpresa").getBytes("ISO-8859-1"),"UTF-8") : ""%></div> --%>
 						
 						<input type='hidden' id='txtEmpresa' name='txtEmpresa' 
							value="<%= (request.getParameter("txtEmpresa")!=null)? new String(request.getParameter("txtEmpresa").getBytes("ISO-8859-1"),"UTF-8") : ""%>">
 						

					

						<!--
						<img src='<%= request.getContextPath() %>/img/btn_search.png' 
										onclick="openMultiSelectPupUp('EMPRESAS','hiddenEmpresa','txtEmpresa','value',document.getElementById('hiddenEmpresa').value)">
												
						<img src='<%= request.getContextPath() %>/img/btn_clean.png' 
										onclick="document.getElementById('hiddenEmpresa').value = '';document.getElementById('txtEmpresa').value = '';">
						-->										
					</td>
				</tr>
				<tr>
					
					<td width="90%" >Tipo de Poder:
						<div id="divTipoPoder"><%= (request.getParameter("txtTipoPoder")!=null)? TextFormatter.removeAccents( (new String(request.getParameter("txtTipoPoder").getBytes("ISO-8859-1"),"UTF-8")) ) : ""%></div>
					</td>
					<td>	
						<div align='right'>
						<img src='<%= request.getContextPath() %>/img/btn_search.png' 
										onclick="openMultiSelectPupUp('41','hiddenTipoPoder','divTipoPoder','innerHTML',document.getElementById('hiddenTipoPoder').value,'UL')">		
						<img src='<%= request.getContextPath() %>/img/btn_clean.png' 
										onclick="document.getElementById('hiddenTipoPoder').value = '';document.getElementById('divTipoPoder').innerHTML = '';">
						</div>
						
						<input type="hidden" id='hiddenTipoPoder' name='hiddenTipoPoder' value='<%=hiddenTipoPoder%>'>
						
						<%--  
							<input id='txtTipoPoder' 
							name='txtTipoPoder' readonly="readonly" size="30" 
							value="<%= (request.getParameter("txtTipoPoder")!=null)? request.getParameter("txtTipoPoder") : ""%>">
						
						--%>
						
						
						
						
						<input type='hidden' id='txtTipoPoder' name='txtTipoPoder' 
						value="<%= (request.getParameter("txtTipoPoder")!=null)? new String(request.getParameter("txtTipoPoder").getBytes("ISO-8859-1"),"UTF-8") : ""%>">
	
						
						
						 <!-- 
						<img src='<%= request.getContextPath() %>/img/btn_search.png' 
										onclick="openMultiSelectPupUp('41','hiddenTipoPoder','txtTipoPoder','value',document.getElementById('hiddenTipoPoder').value)">		
						<img src='<%= request.getContextPath() %>/img/btn_clean.png' 
										onclick="document.getElementById('hiddenTipoPoder').value = '';document.getElementById('txtTipoPoder').value = '';">
						 -->
										
					</td>
				</tr>
			    <tr>
			    	<td class="tableRow2">Nombres de Apoderados:
			    		<div id="divApoderados"><%= (request.getParameter("txtApoderados")!=null)? TextFormatter.removeAccents( (new String(request.getParameter("txtApoderados").getBytes("ISO-8859-1"),"UTF-8")) ): ""%></div>
			    	</td>
			    	<td class="tableRow2">
						
						<div align='right'>
												
						
						<img src='<%= request.getContextPath() %>/img/btn_search.png' 
										onclick="openMultiSelectPupUp('32','hiddenApoderados','divApoderados','innerHTML',document.getElementById('hiddenApoderados').value,'UL')">		
						<img src='<%= request.getContextPath() %>/img/btn_clean.png' 
										onclick="document.getElementById('hiddenApoderados').value = '';document.getElementById('divApoderados').innerHTML = '';">
						
						</div>
						
						
						<input type="hidden" id='hiddenApoderados' name='hiddenApoderados' value='<%=hiddenApoderados%>'>
						
						<%--
						<input id='txtApoderados' name='txtApoderados' readonly="readonly" size="40" value="<%= (request.getParameter("txtApoderados")!=null)? new String(request.getParameter("txtApoderados").getBytes("ISO-8859-1"),"UTF-8") : ""%>">
						 --%>
						 
						 
							
							<input type='hidden' id='txtApoderados' name='txtApoderados' 
							value="<%= (request.getParameter("txtApoderados")!=null)? new String(request.getParameter("txtApoderados").getBytes("ISO-8859-1"),"UTF-8") : ""%>">
						
						 
						 <!-- 
						<img src='<%= request.getContextPath() %>/img/btn_search.png' 
										onclick="openMultiSelectPupUp('32','hiddenApoderados','txtApoderados','value',document.getElementById('hiddenApoderados').value)">		
						<img src='<%= request.getContextPath() %>/img/btn_clean.png' 
										onclick="document.getElementById('hiddenApoderados').value = '';document.getElementById('txtApoderados').value = '';">
						 -->



					</td>
				</tr>
				<tr>
					<td>Poder:
						<div id="divPoder"><%= (request.getParameter("txtPoder")!=null)? TextFormatter.removeAccents( (new String(request.getParameter("txtPoder").getBytes("ISO-8859-1"),"UTF-8")) ): ""%></div>
					</td>
					<td>
						<div align='right'>
						<img src='<%= request.getContextPath() %>/img/btn_search.png' 
										onclick="openMultiSelectPupUpAncho('PODERES','hiddenPoder','divPoder','innerHTML',document.getElementById('hiddenPoder').value,'UL')">		
						<img src='<%= request.getContextPath() %>/img/btn_clean.png' 
										onclick="document.getElementById('hiddenPoder').value = '';document.getElementById('divPoder').innerHTML = '';">
						</div>
						
						<input type="hidden" id='hiddenPoder' name='hiddenPoder' value="<%=hiddenPoder%>"/>
						
						<%--
						<input id='txtPoder' 
						name='txtPoder' readonly="readonly" size="30" 
						value="<%= (request.getParameter("txtPoder")!=null)? new String(request.getParameter("txtPoder").getBytes("ISO-8859-1"),"UTF-8") : ""%>">
						 --%>
						
												
						
						<input type='hidden' id='txtPoder' name='txtPoder' 
						value="<%= (request.getParameter("txtPoder")!=null)? new String(request.getParameter("txtPoder").getBytes("ISO-8859-1"),"UTF-8") : ""%>">
						
						
						<!--  
						<img src='<%= request.getContextPath() %>/img/btn_search.png' 
										onclick="openMultiSelectPupUpAncho('PODERES','hiddenPoder','txtPoder','value',document.getElementById('hiddenPoder').value)">		
						<img src='<%= request.getContextPath() %>/img/btn_clean.png' 
										onclick="document.getElementById('hiddenPoder').value = '';document.getElementById('txtPoder').value = '';">
							 -->			
					</td>
				</tr>
				<tr>
					
					<td class="tableRow2">Escritura:</td>
					<td class="tableRow2">&nbsp;</td>
					<%--<td class="tableRow2" align="right">  --%>
				</tr>
				<tr>
					
					<td class="tableRow2">
							<input id='txtEscritura' name='txtEscritura' size="40" value="<%= (request.getParameter("txtEscritura")!=null)? request.getParameter("txtEscritura") : ""%>">
					</td>
					<td class="tableRow2">&nbsp;</td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td colspan="6" align="right">
					
						<input type="submit" id='btnEjecutar' name='btnEjecutar' value='Generar' onclick="waitBar();copyParamsDivToHidden();">
			
						<img src='<%= request.getContextPath() %>/img/wait-bar.gif' id='imgCapWait' style="display: none;">
					</td>
				</tr>
				
			</table>
		</td>
	</tr>
	<tr>
		<td colspan="3"><hr></td>
	</tr>
	<tr>
		<td colspan="3" align="right">
			<br>
			<%
				if(btnEjecutar != null && btnEjecutar.equals("Generar") && records != null && records.size() > 0) {
				
			%>
			<a href="apoderadosPrint.jsp?<%=paramString%>" target="_blank">Imprimir</a>
			
			<%
				}
			%>
		</td>
	</tr>
	<tr>
		<td colspan="3" align="left">
	
				<%
				if(records != null) {
					
					if(showMode.equals("TABLE")){
						
						//
						// YA NO APLICA
						//
						
				%>
				
					
			<table width="100%">
				<tr>
					<th class="tableHeader">
					Empresa
					</th>
					<th class="tableHeader">
					Escritura
					</th>
					<th class="tableHeader">
					Tipo de Poder
					</th>
					<th class="tableHeader">
					Grupo
					</th>
					<th class="tableHeader">
					Nombre Apoderado
					</th>
					<th class="tableHeader">
					Concepto Poder
					</th>
					<th class="tableHeader">
					Poder
					</th>
				</tr>
				
				<%
					for(Record rec : records) {
				%>
				<tr>
					<td>
						<%= rec.get("NOM_EMPRESA") %>
					</td>
					<td>
						<%= rec.get("DES_ESCRITURA") %>
					</td>
					<td>
						<%= rec.get("TIPO_PODER") %>
					</td>
					<td>
						<%= rec.get("DES_GRUPO") %>
					</td>
					<td>
						<%= rec.get("NOMBRE_APODERADO") %>
					</td>
					<td>
						<%= rec.get("CONCEPTO_PODER") %>
					</td>
					<td>
						<%= rec.get("DESCRIPCION_PODER") %>
					</td>					
				</tr>
				
				
				<%
					}
				%>
			</table>
				<%
				} if(showMode.equals("TREE")) {
				%>
				
				
				
				<%
				
					String lastEmpresa = "";
					String lastEscritura = "";
					String lastTipoPoder = "";
					String lastGrupo = "";
					String lastNombre = "";
					String lastConcepto = "";
					String lastPoder = "";
				
					for(Record rec : records) {
				%>
					
					<%
						if(!lastEmpresa.equals(rec.get("NOM_EMPRESA"))) {
							
							lastEmpresa = rec.get("NOM_EMPRESA");
						
							
							int intIdEmpresa = Integer.parseInt(rec.get("ID_EMPRESA"));
							
							FacesUtils.getSessionBean().setIdCurrentEmpresa(rec.get("ID_EMPRESA"));
							
						    List<CatalogElementV2> elemsEscrituras = new Catalog().dameEscrituras(intIdEmpresa);
							
						%>
							
							
							<table width="100%">
							<tr>
							<th  class='tableHeader'>
							<h3>
							<%= rec.get("NOM_EMPRESA") %>
							</h3>
							</th>
							</tr>
							</table>
							
							
							
							
							<% for (CatalogElementV2 elemEscritura : elemsEscrituras) {
								
								//String escritura = elemEscritura.getValValor();
					 			String escritura = elemEscritura.getValC4();
					 			
					 			String idGenerales 	= "12290";
					 			String idEspeciales = "12291";
					 			
					 			int countGenerales = DetailApoderados.getCountApoderados(rec.get("ID_EMPRESA"), idGenerales, escritura);
					 			int countEspeciales = DetailApoderados.getCountApoderados(rec.get("ID_EMPRESA"), idEspeciales, escritura);
					 			
					 			boolean showPodGen = (hiddenTipoPoder.equals("") || hiddenTipoPoder.equals("12290") || hiddenTipoPoder.contains(",")) && countGenerales > 0;
					 			boolean showPodEsp = (hiddenTipoPoder.equals("") || hiddenTipoPoder.equals("12291") || hiddenTipoPoder.contains(",")) && countEspeciales > 0;
								
								 if(elemEscritura.getValC4() != null
										&& elemEscritura.getValC4().replace(",", "").contains(txtEscritura.replace(",", ""))
										&& (showPodGen || showPodEsp)
										){
								
									FacesUtils.getSessionBean().setEscrituraApo(elemEscritura.getValC4());
	//-------------------------EMPIEZA PARA OCULTAR PODERES GENERALES
									String myParamApoderadosG = (request.getParameter("hiddenApoderados") != null) ? request.getParameter("hiddenApoderados") : "";
				
									ArrayList<String> apoderadoNamesG = new ArrayList<String>();
									String[] idsG = StringUtils.split(myParamApoderadosG, ",");
									
									for(String id : idsG) {
										
										String name = Catalog.getCatalogElementDescription(id);
										if(name != null) {
											apoderadoNamesG.add(name);
										}
									}
									
									
									String myParamPoderG = (request.getParameter("hiddenPoder") != null) ? request.getParameter("hiddenPoder") : "";
								
									
									ArrayList<String> poderesNamesG = new ArrayList<String>();
									
									if(myParamPoderG != null && !myParamPoderG.equals("")) {
										String[] p_ids = StringUtils.split(myParamPoderG, ",");
										
										for(String p_id : p_ids) {
											
											poderesNamesG.add(Catalog.getCatalogElementDescription(p_id));	
										}
									}
									
								
									boolean alterClassG = false;
									String trStyleG = "";
							
									String idEmpresaG 		= FacesUtils.getSessionBean().getIdCurrentEmpresa();
									String lstNumTipoPoderG 	= "12290";
									String lstEscrituraG		=	FacesUtils.getSessionBean().getEscrituraApo();
									List<DetailApoderadosDTO> elemsG = new DetailApoderados().getApoderados(idEmpresaG, lstNumTipoPoderG, lstEscrituraG);
									
									/*String lstNumTipoPoderG 	= "12290";
									String lstEscrituraG		=	elemEscritura.getValValor();
									List<DetailApoderadosDTO> elemsG = new DetailApoderados().getApoderados(String.valueOf(intIdEmpresa), lstNumTipoPoderG, lstEscrituraG);
									*/
									boolean existRowsG = false;
									
									for (DetailApoderadosDTO elem : elemsG) {
										
										boolean showRow = false;
										
										
										if(apoderadoNamesG.size() == 0) {
											
											showRow = true;
											existRowsG = true;
										}
										
										for(String apoderadoName : apoderadoNamesG){
										
											if(TextFormatter.removeAccents(elem.getApoderados().trim()).contains(TextFormatter.removeAccents(apoderadoName.trim()))) {
												
												showRow = true;
												existRowsG = true;
											}
										}
									}
		//----------------------------------------------TERMINA PARA OCULTAR PODERES GENERALES---------------------------------------------------------------------
		
		
		//-----------------------------INICIA OCULTA PODERES ESPECIALES--------------------------------------
		
		String myParamApoderadosE = (request.getParameter("hiddenApoderados") != null) ? request.getParameter("hiddenApoderados") : "";
		
		ArrayList<String> apoderadoNamesE = new ArrayList<String>();
		String[] idsE = StringUtils.split(myParamApoderadosE, ",");
		
		for(String id : idsE) {
			
			//apoderadoNames.add(Catalog.getCatalogElementDescription(id));
			String name = Catalog.getCatalogElementDescription(id);
			if(name != null) {
				apoderadoNamesE.add(name);
			}

			
		}
		
		
		String myParamPoderE = (request.getParameter("hiddenPoder") != null) ? request.getParameter("hiddenPoder") : "";
	
		
		ArrayList<String> poderesNamesE = new ArrayList<String>();
		
		if(myParamPoderE != null && !myParamPoderE.equals("")) {
			String[] p_ids = StringUtils.split(myParamPoderE, ",");
			
			for(String p_id : p_ids) {
				
				poderesNamesE.add(Catalog.getCatalogElementDescription(p_id));	
			}
		}
		

		boolean alterClassE = false;
		String trStyleE = "";

		String idEmpresaE = FacesUtils.getSessionBean().getIdCurrentEmpresa();
		String lstNumTipoPoderE = "12291";
		String lstEscrituraE		=	FacesUtils.getSessionBean().getEscrituraApo();
		List<DetailApoderadosDTO> elemsE = new DetailApoderados().getApoderadosEsp(idEmpresaE, lstNumTipoPoderE, lstEscrituraE);
		
		

		boolean existRowsE = false;
		
		
		
		for (DetailApoderadosDTO elem : elemsE) {
			
			

			boolean showRowE = false;
			
			
			String grupoE = elem.getApoderados().substring(0, elem.getApoderados().indexOf("</b>")) + "</b><br><br>";
			String foundApoderadosE = "";
			
			
	
			if(apoderadoNamesE.size() == 0) {
				showRowE = true;
				existRowsE = true;
				
				grupoE = "";
				foundApoderadosE = elem.getApoderados(); 
			}
	
	
			for(String apoderadoName : apoderadoNamesE){
			
				if(TextFormatter.removeAccents(elem.getApoderados().trim()).contains(TextFormatter.removeAccents(apoderadoName.trim()))) {
					
					showRowE = true;
					existRowsE = true;
					
					foundApoderadosE += "<li>" + apoderadoName + "</li>";
				}
			}
		}
		//-----------------------------TERMINA OCULTA PODERES ESPECIALES--------------------------------------	
								%>
									
									<br>
									<b>
									<span style="color:#D2E0F6">. . . . . . . .</span> 
									<%if(existRowsG || existRowsE){ %>
									<span style="font-size: 15px">
							    	Escritura: <%= elemEscritura.getValValor() %> -  <%= RepApoderados.getInfoEscrituras(rec.get("ID_EMPRESA"), escritura) %> 
							    	
							    	</span>
									</b>
									
									
									<div align="right">
									<% 
											
							
										
										
										
								
										if((hiddenTipoPoder.equals("") || hiddenTipoPoder.equals("12290") || hiddenTipoPoder.contains(",")) && countGenerales > 0) {
										
									%>
									<%if(existRowsG){ %>
									<div id="<%=countGenerales%>">
									<table width='90%' cellspacing='3' cellpadding='3' border=0 class='flexTable'>
									<%--
									<tr>
										<td align='left' colspan=6 	class='tableRow3'><b>ESCRITURA: <%=elemEscritura.getValValor()%></b></td>
									</tr>
									 --%>
									<tr>
										<td align="left" colspan=6 class='tableRow4'><b>PODERES GENERALES</b></td>
									</tr>
									<tr>
										<th width='25%' class='tableHeader'>APODERADOS</th>
										<th width='15%' class='tableHeader'>ACTOS DE DOMINIO</th>
										<th width='15%' class='tableHeader'>ACTOS DE ADMINISTRACION</th>
										<th width='15%' class='tableHeader'>TITULOS DE CREDITO</th>
										<th width='15%' class='tableHeader'>PLEITOS Y COBRANZAS</th>
										<th width='15%' class='tableHeader'>PODER ESPECIAL</th>
									</tr>
										<jsp:include page="DetailApoderados.jsp">
											<jsp:param value="<%=countGenerales%>" name="countGenerales"/>
										</jsp:include>
										
										
									
									</table>
									</div>
									<%
										}
										} %>
									<br><br><br>
									<% } %>
									
								<% if((hiddenTipoPoder.equals("") || hiddenTipoPoder.equals("12291") || hiddenTipoPoder.contains(",")) && countEspeciales > 0) { 
									if(true){
								%>
									<%if(existRowsE){ %>
									<table width='90%' cellspacing='3' cellpadding='3' border=0 class='flexTable'>
									<tr>
										<td align='left' colspan=6 class='tableRow4'><b>PODERES ESPECIALES</b></td>
									</tr>
									<tr>
										<th width='25%' class='tableHeader'>APODERADOS</th>
										<th width='15%' class='tableHeader'>ACTOS DE DOMINIO</th>
										<th width='15%' class='tableHeader'>ACTOS DE ADMINISTRACION</th>
										<th width='15%' class='tableHeader'>TITULOS DE CREDITO</th>
										<th width='15%' class='tableHeader'>PLEITOS Y COBRANZAS</th>
										<th width='15%' class='tableHeader'>PODER ESPECIAL</th>
									</tr>
										
										<!-- %@include file="DetailApoderadosEsp.jsp"%> -->
										<jsp:include page="/jsp/reportes/apoderados/DetailApoderadosEsp.jsp">
											<jsp:param name="isRevocado" value="no"/>
										</jsp:include>
										
									</table>
									<%}
										} 
									    } %>
									</div>
									
									
									
									
							    <%}%>
						    <% }%>
									
							
							
						<%	
						}
					%>
				
					
					
					
					
					
				
				
				<%
				}
				 %>
				 
				 <br>
				<%
				}
				 %>
			
			<%
			
			if(records.size() == 0) {
				%>
				
			No se encontraron registros	
				<%
			}
			%>
			
				
				<%
				} else {
					
					
					}
				%>
				
				
		</td>
	</tr>
	
	
</table>

</form>

<jsp:include page="/jsp/components/pages_border/close.jsp"></jsp:include>

</body>
</html>
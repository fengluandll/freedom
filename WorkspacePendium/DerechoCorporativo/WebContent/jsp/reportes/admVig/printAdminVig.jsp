<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="mx.com.televisa.derechocorporativo.modules.reports.ecs.ReporteECSeccion,
            mx.com.televisa.derechocorporativo.modules.captura.Pagina,
            org.apache.commons.lang3.StringUtils,
            mx.com.televisa.derechocorporativo.modules.reports.ecs.ReporteECSFlexTable,
            mx.com.televisa.derechocorporativo.modules.captura.EmpresaValues,
            mx.com.televisa.derechocorporativo.bean.AdminVigBean,
            java.util.List,
            mx.com.televisa.derechocorporativo.daos.ConsultaDAO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@include file="/css/kaz-styles.jsp"%>
<%@include file="/jsp/components/session_timeout/session_timeout.jsp"%>

<script type="text/javascript" src="<%= request.getContextPath() %>/js/ejercicio/ejercicio.js"></script>
<title>Reporte Administración y Vigilancia</title>
</head>
<body>
<jsp:include page="/jsp/components/backPageReports/regresaMenuReportes.jsp">
<jsp:param name="action" value="rAdminVig"/>
</jsp:include>

<%String hiddenEmpresa  = 	(request.getParameter("hiddenEmpresa") != null) ? request.getParameter("hiddenEmpresa") : "";%>
<%String idPersona 		= 	(request.getParameter("idHiFunHidd") != null) ? request.getParameter("idHiFunHidd") : "";%>
<%String lsChecks[] 	=    request.getParameterValues("chHiFun");
  String totAdminVig	= "";
	
	int liCheck = 0;
	String lsCheck = "";
	if(lsChecks.length == 2){
		totAdminVig = "8,11,12,13,14,15,16,26,39,40,43,44,45,46,25,9";
	}else{
		for(String lsChk:lsChecks){
			
                if(lsChk.equals("0")){
                	totAdminVig = "8,11,12,13,14,15,16,25,26,39,40,43,44,45,46";
                }else if(lsChk.equals("1")){
                	totAdminVig = "9";
                }
			}
	}

	EmpresaValues empresaValues = new EmpresaValues(); 
	ConsultaDAO luConsultaDAO   = new ConsultaDAO();%>

<span style="text-align: center"><h1>Reporte: Administraci&oacute;n y Vigilancia</h1></span>
<%String outHtml 	 = "";
  String outTable    = "";%>		

<%if(!hiddenEmpresa.equals("")){ %>
<strong><span style="color:gray;">Empresa(s):</span></strong><br>
<span style="color:gray;"><%= EmpresaValues.getNomEmpresas(hiddenEmpresa) %></span><br>
<%} %>
<%if(idPersona.length() > 0){ %>
<strong><span style="color:gray;">Nombre(s):</span></strong><br>
<span style="color:gray;"><%= EmpresaValues.getNombresFuncionarios(idPersona) %></span><br>
<%} %>



<%	
  ReporteECSFlexTable 	reporteECSFlexTable;
  AdminVigBean 			adminVigBean = new AdminVigBean();
  String[] 				arrayidFlex;

	if(idPersona.length() > 0){
		adminVigBean = empresaValues.getEmpresasByNombreFuncionario(idPersona, totAdminVig);
		hiddenEmpresa = hiddenEmpresa + adminVigBean.getIdEmpresa();
		if(!hiddenEmpresa.equals("")){
			hiddenEmpresa = empresaValues.ordenaEmpresas(hiddenEmpresa);	
		}
		
	}  
	
	String[] empresas    = StringUtils.split(hiddenEmpresa.trim(),",");
	String[] idPersonas  = StringUtils.split(idPersona.trim(),",");
	if(idPersona.length() > 0){
		arrayidFlex = StringUtils.split(adminVigBean.getIdFlex(),",");	
	}else{
		arrayidFlex = StringUtils.split(totAdminVig,",");	
	}
	
	String nomEmpresa = "";
	int count 		= 0;
	int countFlex 	= 0;
%>

<table width="30%">
	<tr>
		<td>
			<a href="printAdminVigPDF.jsp?hiddenEmpresa=<%=hiddenEmpresa%>&idPersona=<%=idPersona%>&totAdminVig=<%=totAdminVig%>" target="_blank"><img src="../../../img/icons/pdf_file_50.png"></a>		
		</td>
		<td>
			<a href="/DerechoCorporativo/FlexReportVigToExcel?hiddenEmpresa=<%=hiddenEmpresa%>&idPersona=<%=idPersona%>&totAdminVig=<%=totAdminVig%>" target="_blank"><img src="../../../img/icons/xls_50.png"></a>
		</td>
	</tr>
</table>	

<% if(!hiddenEmpresa.equals("") && idPersona.equals("")){

%>

<table width="100%" border="0" id="tbl_stat_0" name="tbl_stat_0">
<tbody>
<% 
	
	if(empresas.length > 0){
	
		       %>

	
<%	for(String empresa : empresas){ 
	count ++;
	countFlex = 0;
	nomEmpresa = EmpresaValues.getNomEmpresa(empresa);
		if(count > 1){
	%>
		
		<tr>
			<td>
				<div style="height: 200px;"></div>
			</td>
		</tr>
		
	<%} %>
		<tr>
			<td align="center">
				<strong><span style='font-size:22px;'><%=nomEmpresa%></span></strong>		
			</td>
		</tr>
       
			<% 
				for(String idFlex : arrayidFlex){
					int liCountRegistros = luConsultaDAO.consultar_Adm_Vig(empresa, Integer.parseInt(idFlex));
					if(liCountRegistros == 0){
						countFlex++;
					}
		if(liCountRegistros > 0){
				%>
				<tr bgcolor="">
				<td colspan="8">
				<div id="flexTable_<%=idFlex%>">
			<%	reporteECSFlexTable = new ReporteECSFlexTable(Integer.parseInt(idFlex.trim()),empresa);
				outTable = reporteECSFlexTable.toHTMLHorizontal(request,empresa,"",idPersona); %>
				<%=outTable%>
			<%-- <% if(outTable.equals("")){ 
			        countFlex++;%>
				
			<%}else{ %>
				<%=outTable%>
			<%}  
				if(countFlex == 16){%>
				<h4>NO EXISTE INFORMACIÓN PARA ESTA EMPRESA</h4>	
			<%}%> --%>
				</div>
				</td>
				</tr>
				
				
			<%}%>
			<%if(countFlex == 16){%>
					<tr>
						<td align="center">
							<h4>NO EXISTE INFORMACIÓN PARA ESTA EMPRESA</h4>		
						</td>
					</tr>
						
				<%}%>
		     <%}%>

	<%}%>

<%}else{ %>
	
		
			<h1>NADA</h1>
		
<%}%>
</tbody>
</table>

<%}else if(!idPersona.equals("") && !hiddenEmpresa.equals("")){ %>
		
	<table width="100%" border="0" id="tbl_stat_0" name="tbl_stat_0">
<tbody>
<% 
	if(empresas.length > 0){%>

	
<%	for(String empresa : empresas){ 
		StringBuilder idFlex = new StringBuilder();
		nomEmpresa = EmpresaValues.getNomEmpresa(empresa);
		count ++;
		countFlex = 0;
		if(count > 1){
			%>
				<tr>
					<td>
						<div style="height: 200px;"></div>
					</td>
				</tr>
				
			<%} %>
			
		<tr>
			<td align="center">
				<strong><span style='font-size:22px;'><%=nomEmpresa%></span></strong>		
			</td>
		</tr>
       
			<% 
			    List<AdminVigBean> listAdmin = empresaValues.getListAdminVigBean();
			
				for(AdminVigBean admin : listAdmin){
					if(empresa.equals(admin.getIdEmpresa())){
						idFlex.append(admin.getIdFlex());
						idFlex.append(",");						
					}
				}
				idFlex.delete(idFlex.length()-1, idFlex.length());
				arrayidFlex = StringUtils.split(idFlex.toString(),",");	
				for(String idFlexx : arrayidFlex){
				 int liCountRegistros = luConsultaDAO.consultar_Adm_Vig(empresa, Integer.parseInt(idFlexx));
				
				 if(liCountRegistros == 0){
						countFlex++;
					}
				 
				 if(liCountRegistros > 0){%>
				<tr bgcolor="">
				<td colspan="8">
				<div id="flexTable_<%=idFlexx%>">
					<%	reporteECSFlexTable = new ReporteECSFlexTable(Integer.parseInt(idFlexx.trim()),empresa);
						outTable = reporteECSFlexTable.toHTMLHorizontal(request,empresa,"",idPersona); %>
						<%=outTable%>
			<%-- <% if(outTable.equals("")){ 
				countFlex++;%>
				
			<%}else{ %>
				<%=outTable%>
			<%} 
				if(countFlex == 16){%>
				<h4>NO EXISTE INFORMACIÓN PARA ESTA EMPRESA</h4>	
			<%}%> --%>
				</div>
				</td>
				</tr>
				
				
			<%}
				  if(countFlex == 16){%>
					<tr>
						<td align="center">
							<h4>NO EXISTE INFORMACIÓN PARA ESTA EMPRESA</h4>		
						</td>
					</tr>
				<%}
		   
					}    %>

	<%}%>

	<%}else{ %>
	
		
			<h1>NADA</h1>
		
	<%}%>
	</tbody>
	</table>
		
<%}else{%>
<strong><h3>EL reporte no arrojo ningún resultado</h3></strong>
<%} %>
</body>
</html>
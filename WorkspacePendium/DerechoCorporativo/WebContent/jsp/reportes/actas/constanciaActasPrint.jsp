<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ taglib uri="pd4ml" prefix="pd4ml"%>
<%@page contentType="text/html; charset=ISO8859_1"%>
<%@page import="java.util.StringTokenizer"%>
<%@page import="mx.com.televisa.derechocorporativo.daos.ConstanciaActasDAO"%>
<%@page import="mx.com.televisa.derechocorporativo.util.GetMonth"%>
<%@page import="java.util.ArrayList"%>
<%@page import="mx.com.televisa.derechocorporativo.bean.ConstanciaActasBean"%>

<%
	String fileName = "Reporte_Constancia_Actas.pdf";
	String protocol = request.getScheme(); 			// http
	String serverName = request.getServerName(); 	// hostname.com
	int serverPort = request.getServerPort(); 		// 8081
	String contextPath = request.getContextPath(); 	// mywebapp
	//String fullContextPath = protocol + "://" + serverName + ":" + serverPort + contextPath;
	//String lsEjercicio = request.getAttribute("txtEjercicio").toString();

	String lsEjercicio	  = request.getParameter("ejercicio");
	String lstEmp	      = request.getParameter("idEmp");
	boolean isMultipleEmp = StringUtils.contains(lstEmp, ",");
	String[] arrayEmp;
	if(isMultipleEmp){
		arrayEmp = new String[lstEmp.split(",").length];
		arrayEmp = lstEmp.split(",");
	}else{
		arrayEmp    = new String[1];
		arrayEmp[0] = lstEmp;
	}
	
	//String lsidEmp	 	= request.getParameter("idEmp");
	String fecDoc 		= request.getParameter("fec");
	
	String[] lsFecha    = {"","",""};
	if(fecDoc != null && !fecDoc.equals("")){
		lsFecha 	= request.getParameter("fec").split("/");	
	}
	
	String lsNameEmp 	= request.getParameter("nameEmp");

	//System.out.println("Ejercicio: "+lsEjercicio+" idEmp:"+lsidEmp+" NameEmp:"+lsNameEmp);

	ConstanciaActasDAO luConstanciaActasDAO = new ConstanciaActasDAO();
	
	String direccion = request.getParameter("direccion");
	
%>
<pd4ml:transform
	screenWidth="780" pageFormat="LETTER" fileName="<%= fileName %>"
	pageInsets="10,10,10,10,mm" adjustScreenWidth="true"
	enableTableBreaks="false" enableImageSplit="false" debug="true">
	
	<pd4ml:footer areaHeight="20">
		<center><span style="font-size: 10px;"><%=direccion%></span></center>
	</pd4ml:footer>
<html>
<head>
<title>Constancia de Actas</title>
<%@include file="/css/kaz-styles4PrintSmall.jsp"%>
<link
	href="pd4ml_report.css"
	rel="stylesheet" type="text/css">
<style type="text/css">
.formatoLetras1{
	font-size: 14px;
}

.formatoPre{
	font-family: 'Open Sans', sans-serif;
	font-size: 14px;	
}

</style>

</head>

<body>
<%  int count 	 = 0;
	int totalEmp = arrayEmp.length;
	for(String lsidEmp : arrayEmp){
	count++;
	System.out.println(luConstanciaActasDAO.getNameEmp(lsidEmp));
	ArrayList<ConstanciaActasBean> alConstanciaActas = luConstanciaActasDAO.getAsambleas(lsidEmp,lsEjercicio);
	String lsTipoSociedad = luConstanciaActasDAO.tipoSociedad(Integer.parseInt(lsidEmp));
	session.setAttribute("alConstanciaActas", alConstanciaActas);
	String remitente = request.getParameter("remitente");	
	luConstanciaActasDAO.saveDatosParaReporte(remitente, direccion);
%>

	
	<%if(alConstanciaActas.size() == 0){%>
		<jsp:include page="formatSinMovSDRL.jsp">
			<jsp:param value="<%=lsidEmp%>" name="idEmpresa"/>
			<jsp:param value="<%=luConstanciaActasDAO.getNameEmp(lsidEmp)%>" name="nomEmpresa"/>
			<jsp:param value="<%=lsFecha[0]%>" name="lsFecha"/>
			<jsp:param value="<%=GetMonth.numeroaMes(lsFecha[1])%>" name="lsMes"/>
			<jsp:param value="<%=lsFecha[2]%>" name="lsAnio"/>
			<jsp:param value="<%=luConstanciaActasDAO.getAuditoresExternos(lsidEmp)%>" name="lstAudExternos"/>
			<jsp:param value="<%=luConstanciaActasDAO.getDireccAudExt(lsidEmp)%>" name="lstDirAudExt"/>
			<jsp:param value="<%=lsEjercicio%>" name="lsEjercicio"/>
			<jsp:param value="<%=alConstanciaActas%>" name="alConstanciaActas"/>			
			<jsp:param value="<%=remitente%>" name="lsRemitente"/>
		</jsp:include>	
		<%if(count < totalEmp){ %>
		<pd4ml:page.break/>	
		<%} %>
	<%}else{ %>
		<jsp:include page="formatNormal.jsp">
			<jsp:param value="<%=lsidEmp%>" name="idEmpresa"/>
			<jsp:param value="<%=luConstanciaActasDAO.getNameEmp(lsidEmp)%>" name="nomEmpresa"/>
			<jsp:param value="<%=lsFecha[0]%>" name="lsFecha"/>
			<jsp:param value="<%=GetMonth.numeroaMes(lsFecha[1])%>" name="lsMes"/>
			<jsp:param value="<%=lsFecha[2]%>" name="lsAnio"/>
			<jsp:param value="<%=luConstanciaActasDAO.getAuditoresExternos(lsidEmp)%>" name="lstAudExternos"/>
			<jsp:param value="<%=luConstanciaActasDAO.getDireccAudExt(lsidEmp)%>" name="lstDirAudExt"/>
			<jsp:param value="<%=lsEjercicio%>" name="lsEjercicio"/>
			<jsp:param value="<%=alConstanciaActas%>" name="alConstanciaActas"/>
			<jsp:param value="<%=remitente%>" name="lsRemitente"/>
		</jsp:include>	
		<%if(count < totalEmp){ %>
		<pd4ml:page.break/>	
		<%} %>
	<%} %>

<% }%>							
</body>

</html>

</pd4ml:transform>

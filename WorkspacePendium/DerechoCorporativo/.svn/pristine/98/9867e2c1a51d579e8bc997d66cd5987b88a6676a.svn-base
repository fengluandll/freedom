<%@page import="mx.com.televisa.derechocorporativo.daos.ApoderadosPoderesDAO"%>
<%@page import="mx.com.televisa.derechocorporativo.data.ConnectionWrapper"%>
<%@page import="mx.com.televisa.derechocorporativo.bean.ApoderadosPoderesBean"%>
<%@page import="mx.com.televisa.derechocorporativo.bean.PoderesBean"%>
<%@page import="mx.com.televisa.derechocorporativo.util.FacesUtils"%>
<%@page import="mx.com.televisa.derechocorporativo.bean.SessionBean"%>
<%@page import="java.util.List"%>
<%@page import="mx.com.televisa.derechocorporativo.modules.captura.*"%>
<%@page import="mx.com.televisa.derechocorporativo.util.ObtenerEmpresaDenominacioActual"%>
<%@page import="mx.com.televisa.derechocorporativo.bean.PoderesBean"%>
<%@page import="mx.com.televisa.derechocorporativo.components.JSCal"%>

<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript" src="../part/flexTab.js"></script>
<script type="text/javascript" src="../poderes/poderes.js"></script>

<%@include file="/jsp/components/session_timeout/session_timeout.jsp"%>
<%@include file="/css/kaz-styles.jsp"%>

<%--  <%@include file="/jsp/components/jquery-ui/include_purr.jsp"%> --%>


<script type="text/javascript" src="../part/ajaxPage.js"></script>
<script type="text/javascript" src="../part/pupUp.js"></script>
<script type="text/javascript" src="<c:out value="${applicationBean.contextPath}"/>/js/jquery/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="<c:out value="${applicationBean.contextPath}"/>/js/ajax/simpleAjaxUtil.js"></script>
<script type="text/javascript" src="<c:out value="${applicationBean.contextPath}"/>/jsp/captura/poderes/poderes.js"></script>
<script src="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.css">




<%
	//String lstIdCurrentEmpresa = request.getParameter("idEmp");
	String idSeccion = request.getParameter("idSeccion");
	System.out.println("idSeccion "+idSeccion);
	String newImage = "<img src='" + request.getContextPath() + "/img/icons/new.png'>";
	 
	 SessionBean sessionBean = (SessionBean)session.getAttribute("sessionBean");
	 PoderesBean poderesBean =  new PoderesBean();
	String lstIdCurrentEmpresa = sessionBean.getIdCurrentEmpresa();
	int porcRSPodGen = poderesBean.getIdPoder(); 
	System.out.println("idEmpresa "+lstIdCurrentEmpresa);
	int idFlexTbl = 17;
    //int idSeccion = 22;
	int countTr   = 1;
	int idEMpresa = Integer.parseInt(lstIdCurrentEmpresa);
	ApoderadosPoderesDAO apoderadosPoderesDAO = new ApoderadosPoderesDAO();
	List<PoderesBean> listPoderes = apoderadosPoderesDAO.getPoderes(idEMpresa);
	
	//sessionBean.setIdCurrentEmpresa(request.getParameter("idEmp"));
	
	String lstEmpresaDenominacionActual = ObtenerEmpresaDenominacioActual.getEmpresaDenominacionActual(lstIdCurrentEmpresa);
	sessionBean.setNomEmpresa(lstEmpresaDenominacionActual);
	
	String lstNomUser = sessionBean.getNomUser();
	if (lstNomUser == null) {
		response.sendRedirect("/DerechoCorporativo/faces/jsp/home/error.jsp");
		
	}
%>			
									<table width='90%' align='center' class='flexTable' border='0' cellspacing='3' cellpadding='3'>
										<tr>
											<td colspan='11'>
												<input type='text' id='id_1' onkeydown='buscarFiltro()' value='' title='Buscar' > 
											 	<input type='button' value='Buscar' onclick='buscarFiltro()'>
										 	</td>
										 </tr>
								
										<tr>
											<th colspan='10'>Poderes Generales</th>
<%-- 								            <td align='center'><a onClick="setAlterFlexTableId('<%=idSeccion%>'); closeCurrentAndLoadFlexTabForm_new('<%=idSeccion%>','<%=idSeccion%>-0');"><%=newImage%></a></td>   --%>
								            <td align='center'><a href="<c:out value="${applicationBean.contextPath}"/>/NewPGServlet?pidSeccion=<%=idSeccion%>"><abbr title="Nuevo"><%=newImage%></abbr></a></td>
										</tr>
										<tr>
								            <th class='tableHeader' width='8%'>Escritura No</th>
								            <th class='tableHeader' width='8%'>Fecha Otorgamiento</th>
							
								            <th class='tableHeader' width='8%'>Apoderados</th>
								            <th class='tableHeader' width='20%'>Asunto</th>
								            <th class='tableHeader' width='8%'>Caracteristicas</th>
								            <th class='tableHeader' width='1%'>Requiere Inscripción RPPC</th>
								            <th class='tableHeader' width='8%'>Fecha de Registro</th>
								            <th class='tableHeader' width='2%'>Semáforo</th>
								            <th class='tableHeader' width='8%'>Vigencia</th>
								            <th class='tableHeader' width='4%'>Editar</th>
								            <th class='tableHeader' width='4%'>Borrar</th>
								        </tr>
								        <%for(PoderesBean apoPodGen : listPoderes){
								        	countTr++;
								        	%>
								        <%if(countTr % 2 == 0){ %>	
										<tr class="tableRow2">
										<%}else{ %>
										<tr>
										<%} %>
								        <td align="center"><%=apoPodGen.getDescEscritura()==null?"":apoPodGen.getDescEscritura()%></td>
								        <td><%=apoPodGen.getFecOtorgamientoInstr()==null?"":apoPodGen.getFecOtorgamientoInstr()%></td>
								        <td></td>
										<td><%=apoPodGen.getIndPoderAsunto()==null?"":apoPodGen.getIndPoderAsunto()%></td>
								        <td></td>
								        <td align="center"><%=apoPodGen.getIndRequiereInscrRppc()==null?"":apoPodGen.getIndRequiereInscrRppc()%></td>
								        <td><%=apoPodGen.getFecRegistro()==null?"":apoPodGen.getFecRegistro()%></td>
 								        <td align='center' valign='top'><img src="../../../img/<%=apoPodGen.getNomSemaforo()%>"></td>
								        <td></td>
<%--     								    <td align='center' valign='top'><a onclick="setAlterFlexTableId('<%=idFlexTbl%>');closeCurrentAndLoadFlexTabForm('<%=idFlexTbl%>','<%=idFlexTbl%>');"> <img src="<c:out value="${applicationBean.contextPath}"/>/img/icons/delete"></a></td> --%>
										<td align='center' valign='top'><a onclick="setAlterFlexTableId('<%=idFlexTbl%>');closeCurrentAndLoadFlexTabForm('<%=apoPodGen.getIdPoder()%>');"> <img src="<c:out value="${applicationBean.contextPath}"/>/img/icons/edit.png"></a></td>
										
								        <td align='center' valign='top'><a onclick="swal({title: 'Confirmar',text: '&iquest;Est&aacute; seguro que desea borrar este registro?',type: 'warning', showCancelButton: true, confirmButtonColor: '#DD6B55', confirmButtonText: 'Si', closeOnConfirm: true }, 
								   		function(){ 
										deleteFlexRow('<%=apoPodGen.getIdPoder()%>');});"> 
										<img src="<c:out value="${applicationBean.contextPath}"/>/img/icons/delete.png"/></a></td> 										
										</tr>
										<%}%>
									</table>
									<!-- <span class="infoMessageText" align="center">Se guardaron los datos Correctamente</span>  -->
			
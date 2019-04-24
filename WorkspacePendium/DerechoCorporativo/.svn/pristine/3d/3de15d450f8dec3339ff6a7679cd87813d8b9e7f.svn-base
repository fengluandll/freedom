<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="mx.com.televisa.derechocorporativo.daos.ApoderadosPoderesDAO"%>
<%@page import="java.util.List"%>
<%@page import="mx.com.televisa.derechocorporativo.bean.ApoderadosPoderesBean"%>
<%@page import="mx.com.televisa.derechocorporativo.bean.PoderesBean"%>
<%@page import="mx.com.televisa.derechocorporativo.bean.SessionBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<body>
<%	String newImage = "<img src='" + request.getContextPath() + "/img/icons/new.png'>";
	ApoderadosPoderesDAO apoderadosPoderesDAO = new ApoderadosPoderesDAO();
	SessionBean sessionBean = (SessionBean)session.getAttribute("sessionBean");
	String idEmpresa = sessionBean.getIdCurrentEmpresa();
	String porcRSPodGen = sessionBean.getIdCurrentEmpresa();
	int intIdEmpresa = Integer.parseInt(idEmpresa);
	int idFlexTbl = 17;
	List<PoderesBean> listPoderes = apoderadosPoderesDAO.getPoderes(intIdEmpresa);
	int countTr   = 1;
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
								            <td align='center'><a onClick="setAlterFlexTableId('<%=idFlexTbl%>'); closeCurrentAndLoadFlexTabForm_new('<%=idFlexTbl%>','<%=idFlexTbl%>-0');"><%=newImage%></a></td>
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
<%-- 								        <td><%=apoPodGen.getEscritura()==null?"":apoPodGen.getEscritura()%></td> --%>
<%-- 								        <td><%=apoPodGen.getFecOtorgamiento()==null?"":apoPodGen.getFecOtorgamiento()%></td> --%>
								        <!-- <td></td> -->
<%-- 								        <td><%=apoPodGen.getAsunto()==null?"":apoPodGen.getAsunto()%></td> --%>
								       <!--  <td></td> -->
<%-- 								        <td><%=apoPodGen.getReqInsRppc()==null?"":apoPodGen.getReqInsRppc()%></td> --%>
								        <td><%=apoPodGen.getFecRegistro()==null?"":apoPodGen.getFecRegistro()%></td>
<%-- 								        <td align='center' valign='top'><img src="../../../img/<%=apoPodGen.getSemaforo()%>"></td> --%>
										<td align='center' valign='top'><img src="/DerechoCorporativo/img/semaforo_green.png<%=apoPodGen.getNomSemaforo()%>"></td>
								        <td></td>
								        
 								        <td align='center' valign='top'><a onclick="setAlterFlexTableId('<%=idFlexTbl%>');closeCurrentAndLoadFlexTabForm('<%=idFlexTbl%>','<%=idFlexTbl%>');"> <img src="<c:out value="${applicationBean.contextPath}"/>/img/icons/delete"></a></td>
<%--  								        <td align='center' valign='top'><a onclick="setAlterFlexTableId('<%=idFlexTbl%>');closeCurrentAndLoadFlexTabForm('<%=idFlexTbl%>','<%=idFlexTbl%>-<%=apoPodGen.getIdMetaRow()%>');"> <img src="<c:out value="${applicationBean.contextPath}"/>/img/icons/edit.png"></a></td> --%>
 								        <td align='center' valign='top'><a onclick="swal({ title: 'Confirmar',text: '&iquest;Est&aacute; seguro que desea borrar este registro?',type: 'warning', showCancelButton: true, confirmButtonColor: '#DD6B55', confirmButtonText: 'Si', closeOnConfirm: true }, function(){ setFlexTableId('<%=idFlexTbl%>');deleteFlexRow_new('<%=idFlexTbl%>');});"> <img src="<c:out value="${applicationBean.contextPath}"/>/img/icons/delete.png"/></a></td>
										</tr>
										<%}%>
										
									</table>
									<!-- <span class="infoMessageText" align="center">Se guardaron los datos Correctamente</span>  -->
		
			
							</td>
						</tr>
					</table> 

</body>
</html>
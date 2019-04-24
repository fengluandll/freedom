<%@page import="mx.com.televisa.derechocorporativo.bean.SessionBean"%>
<%@page import="mx.com.televisa.derechocorporativo.daos.PoderesDAO"%>
 	 

<%
String idPoder = request.getParameter("idPoder");
System.out.println("idPoder " + idPoder);
	 //String idSeccion = tuRequest.getParameter("idSeccion");
	 
	 SessionBean sessionBean = (SessionBean)session.getAttribute("sessionBean");
	 String lstIdCurrentEmpresa = sessionBean.getIdCurrentEmpresa();
	 PoderesDAO poderesDao = new PoderesDAO();
	 boolean ok = poderesDao.DeletePoderes(Integer.parseInt(idPoder),Integer.parseInt(lstIdCurrentEmpresa));
	 boolean message = false;
	 if(ok = true){ 
%>		 
	<jsp:include page="/jsp/captura/poderes/loadPoderes.jsp"></jsp:include>	
	    	
	    	<span class='infoMessageText' align='center'>Se elimin&oacute; el registro correctamente</span>
<%	
	 }else{

%>		 
		 <span class='errorMessageText' align='center'>" + response + "</span>
<%
	 }

	    
%>
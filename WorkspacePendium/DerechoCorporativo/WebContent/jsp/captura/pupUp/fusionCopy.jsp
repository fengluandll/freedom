<%@page import="mx.com.televisa.derechocorporativo.modules.flextabs.FlexTable"%>
<%@page import="mx.com.televisa.derechocorporativo.bean.SessionBean" %>
<%@page import="mx.com.televisa.derechocorporativo.util.NumbersUtil"%>
<%@ page import="java.util.*" %> 
<%@page import="java.util.List"%>
<%@page import="mx.com.televisa.derechocorporativo.model.Catalog"%>
<%@page import="mx.com.televisa.derechocorporativo.model.CatalogElement"%>
<%@page import="mx.com.televisa.derechocorporativo.data.ConnectionWrapper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.*"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.min.js"></script> 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.css">

<%!
private String runQuery() throws SQLException{
	ConnectionWrapper conn = null;
	Statement stmt = null;
	ResultSet rset = null;
	
	try {
		
		conn = new ConnectionWrapper();
	
		stmt = conn.createStatement();
		
		rset = stmt.executeQuery("SELECT ID_EMPRESA, NVL(DERCORP_CAPTURA_PKG.GET_DENOM_ACTUAL_FN(ID_EMPRESA),DERCORP_EMPRESA_TAB.NOM_EMPRESA) AS NOM_EMPRESA FROM DERCORP_EMPRESA_TAB ORDER BY NOM_EMPRESA ASC");
		
		rset.next();
			
	return htmlSelect(rset);
	} catch(Exception ex) {
		
		ex.printStackTrace();
		return "";
	} finally {
		
		ConnectionWrapper.closeAll(rset, stmt, conn);
	}
}
	%>
<%!	
private String htmlSelect(ResultSet rset) throws SQLException{	
	StringBuffer sb = new StringBuffer();
	    if(rset.next()){     
	         do {
	               sb.append("<OPTION value='" + rset.getString(1) + "'>"+rset.getString(2)+ "</OPTION>");
	         }while (rset.next());
	     }
	    return sb.toString();
}
%>
	
<%
	
	String flexTableId = request.getParameter("flexTableId");
	String key =  request.getParameter("key");
	
	String params = "flexTableId=" + flexTableId + "&key=" + key;
	
	FlexTable ft = new FlexTable(flexTableId);

%>
			
	<title>Copia de Fusión</title>
	<%@include file="/css/kaz-styles.jsp" %>
	<%@include file="/jsp/captura/part/validaciones.jsp"%>
	
	<script type="text/javascript">
	
		function openEmpresasCopy2(){
			
			
			/*
		    var left = screen.width - ((screen.width - 300) / 2);
		    var top = (screen.height - 300) / 2;  // for 25% - devide by 4  |  for 33% - devide by 3

			newwindow=window.open('pupUp/EmpresasCopy2.jsp?flexTableId=' + flexTableId + '&key=' + key + '&idEmpresa=' + idEmpresa,
								'name',
								'height=600,width=650,toolbar=0,menubar=0,location=0,status=0,scrollbars=1,resizable=1,left=' + left + ',top=' + top);

			if (window.focus) {newwindow.focus()}

			return false;
			*/
			
			
			
			
			
			form1.submit();
		}
	
	</script>
</head>
<body>

<form id="form1" method="post" action="fusionCopy2.jsp">


<input type="hidden" id="key" name="key" value="<%=key%>">

<div style="background-image: url('<%= request.getContextPath() %>/img/borders/page_border/border05.png');">

<%SessionBean sessionBean =  (SessionBean)session.getAttribute("sessionBean");
  String nomEmpresa = sessionBean.getNomEmpresa();
%>

<table width="80%" border="0" cellspacing="0" cellpadding="0" align="center">
						<tr>
				          <td>&nbsp;</td>
				        </tr>
				        <tr>
				          <td>&nbsp;</td>
				        </tr>
						<tr style="background-image: url('<%= request.getContextPath() %>/img/tableHeaderBack.png')">
							<th width="100%" align="center">
								<font style="color: white;">
									<h3>
									COPIAR FUSIÓN A OTRA EMPRESA
									</h3>
								</font>
							</th>
						</tr>
				        <tr>
				          <td>&nbsp;</td>
				        </tr>
				        <tr>
				         <td align="center">
				         	
				         		<select NAME="IdEmpresa" id="IdEmpresa" class="select" AUTOFOCUS REQUIRED>
				         		<%=runQuery() %>
				         		</select>
				         	
              		     </td>
				        </tr>
				        <tr>
				          <td>&nbsp;</td>
				        </tr>
					    <tr>
					      <td align="right">
					      	<input type="button" id='flexTableformButton' Value='Aceptar' onClick="openEmpresasCopy2()">
					      	 
					      </td>
				        </tr>
				         <tr>
				          <td>&nbsp;</td>
				        </tr>
				         <tr>
				          <td>&nbsp;</td>
				        </tr>
				      </table>
				      

</div>

</form>


</body>
</html>
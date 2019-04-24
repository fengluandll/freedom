<%@page import="java.sql.*"%>
<%@page import="mx.com.televisa.derechocorporativo.data.ConnectionWrapper"%>
<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	//Enumeration<String> params = request.getParameterNames();

	/*
	while(params.hasMoreElements()) {
	
		String param = params.nextElement();
		String paramValue = request.getParameter(param);
	
		out.println(param + " - " + paramValue + "<br>");
	}
	*/

	StringBuilder sb = new StringBuilder();


	String personIds = request.getParameter("idRepHiFun");
	String flexTabIds = request.getParameter("chNomFun");
	
		
	
	
	
	String sql = "SELECT  DISTINCT PER.PERSON_ID, TRIM(VW.NOMBRE) AS NOMBRE_PERSONA " +
	        "FROM    DERCORP_REP_HIST_FUNC_VW VW " +
	        "INNER JOIN DERCORP_CAT_PERSONAS_TAB PER ON " +
	        "TRIM(VW.NOMBRE) = TRIM(PER.NOMBRE) " +
	        "WHERE   1=1 " +
	        //"--AND     VW.ID_FLEX_TBL  IN (1,2,3,4,5,7) " +
	        "AND     PER.PERSON_ID IN (" + personIds + ") " + // --($P!{pValNombre})
	        "ORDER BY 2";
	
	
	out.println(sql);

	
	
	ConnectionWrapper conn = null;
	Statement stmt = null;
	ResultSet set = null;
	
	Statement stmt2 = null;
	ResultSet set2 = null;
	
	
	try {
		conn = new ConnectionWrapper();
		
		stmt = conn.createStatement();
		
		set = stmt.executeQuery(sql);
		
		sb.append("<table width='100%' border=1>");
		while(set.next()){
			
			String id = set.getString("PERSON_ID");
			String nom = set.getString("NOMBRE");
			
			sb.append("<tr><td>XX " + nom + "</td></tr>");
			
			
			
			sb.append("<tr><td>" + "<table width='100%'><tr><td>No.</td><td>Compñia</td><td>Cargo</td><td>Fecha de Inicio</td><td>Fecha de Terminacion</td></tr>");
			
			try {
				String sql2 = "SELECT  ROWNUM "+
							"	      , VW.NOM_EMPRESA "+
							"	      , VW.CARGO "+
							"	      , VW.FECHA_DESIGNACION "+
							"	      , VW.FECHA_BAJA "+
							"	FROM    DERCORP_REP_HIST_FUNC_VW VW "+
							"	  INNER JOIN DERCORP_CAT_PERSONAS_TAB PER ON PER.NOMBRE = RTRIM(VW.NOMBRE) "+
							"	WHERE   1=1 "+
							"	AND     VW.ID_FLEX_TBL  IN (" + flexTabIds + ") "+
							"	AND     PERSON_ID = " + id + "";
				
				stmt2 = conn.createStatement();
				
				set2 = stmt2.executeQuery(sql2);
				
				while(set2.next()){
					
					String num = set2.getString("ROWNUM");
					String emp = set2.getString("NOM_EMPRESA");
					String cargo = set2.getString("CARGO");
					String fechaIni = set2.getString("FECHA_DESIGNACION");
					String fechaFin = set2.getString("FECHA_BAJA");
					
					
					sb.append("<tr><td>" + num + "</td><td>" + emp + "</td><td>" + cargo + "</td><td>" + fechaIni + "</td><td>" + fechaFin + "</td></tr>");
				}
			
			
			} finally {
				
				ConnectionWrapper.closeAll(set2, stmt2);
			}
			
			
			sb.append("</table>" + "</td></tr>");	
		}
		
		sb.append("</table>");
		
		
	} catch(Exception ex){
		
		ex.printStackTrace();
	} finally {
		
		ConnectionWrapper.closeAll(set, stmt, conn);
	}
	
	
	
	


%>


<%= sb.toString() %>

</body>
</html>
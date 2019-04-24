package mx.com.televisa.derechocorporativo.modules.reportsFlex;

import java.sql.CallableStatement;
import java.sql.ResultSet;

import javax.servlet.ServletRequest;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;
import mx.com.televisa.derechocorporativo.data.packages.DERCORP_REPORTFLEX_PKG;
import mx.com.televisa.derechocorporativo.util.FacesUtils;

public class ActionHandler {

	public static String doAction(ServletRequest request) {
		
		try {
			
			int idFlexRep = FacesUtils.getSessionBean().getIdFlexReport();
			
			
			String method = request.getParameter("METHOD");


			if(method.equals("UPDATE_REPORT")) {
				
				updateReport(idFlexRep, request);
			}
			

			
			if(method.equals("NEW_SECCION")) {
				
				saveNewSecc(idFlexRep, request);
			}
			
			
			if(method.equals("UPDATE_SECCION")) {
				
				updateSecc(idFlexRep, request);
			}

			if(method.equals("DELETE_SECCION")) {
				
				deleteSecc(idFlexRep, request);
			}
			

			if(method.equals("DELETE_ROW")) {
				
				deleteRow(request);
			}
			
		
			if(method.equals("NEW_ROW")) {
				
				newRow(idFlexRep, request);
			}

			

			if(method.equals("UPDATE_FIELD")) {
				
				updateField(idFlexRep, request);
			}
			
			

			if(method.equals("COLUMNS")) {
				
				updateFlexColumns(request);
			}
			
			
			
			
			return "OK";
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return "NOK";
		}
	}
	
	
	

	private static void updateReport(int idFlexRep, ServletRequest request) throws Exception {
		
		//String newSecc = new String(request.getParameter("newSecc").getBytes("ISO-8859-1"),"UTF-8");//request.getParameter("newSecc");
		
		String nomReport = new String(request.getParameter("nomReport").getBytes("ISO-8859-1"),"UTF-8");//request.getParameter("newSecc");
		String descReport = new String(request.getParameter("descReport").getBytes("ISO-8859-1"),"UTF-8");//request.getParameter("newSecc");
		String descRFC = request.getParameter("descRFC");
		String descPais = request.getParameter("descPais");
		String saltoPag = request.getParameter("saltoPag");
		
		ConnectionWrapper connect = null;
		CallableStatement stmt = null;
		//ResultSet set = null;

		try {

			connect = new ConnectionWrapper();
			stmt = connect.prepareCall(DERCORP_REPORTFLEX_PKG.UPDATE_REPORT_PR);

			
			stmt.setInt(1, idFlexRep);
			stmt.setString(2, nomReport);
			stmt.setString(3, descReport);
			stmt.setString(4, descRFC);
			stmt.setString(5, descPais);
			stmt.setString(6, saltoPag);
			stmt.execute();

		} finally {

			ConnectionWrapper.closeAll(stmt, connect);
		}
		
	}
	
	
	private static void saveNewSecc(int idFlexRep, ServletRequest request) throws Exception {
		
		String newSecc = new String(request.getParameter("newSecc").getBytes("ISO-8859-1"),"UTF-8");//request.getParameter("newSecc");
		
		ConnectionWrapper connect = null;
		CallableStatement stmt = null;
		//ResultSet set = null;

		try {

			connect = new ConnectionWrapper();
			stmt = connect.prepareCall(DERCORP_REPORTFLEX_PKG.INSERT_SECCION_PR);

			
			stmt.setInt(1, idFlexRep);
			stmt.setString(2, newSecc);

			stmt.execute();

		} finally {

			ConnectionWrapper.closeAll(stmt, connect);
		}
		
	}
	
	
	
	
	private static void updateSecc(int idFlexRep, ServletRequest request) throws Exception {
		
		//String newSecc = new String(request.getParameter("newSecc").getBytes("ISO-8859-1"),"UTF-8");//request.getParameter("newSecc");
		
		String seccId = request.getParameter("seccId");
		String seccName = new String(request.getParameter("seccName").getBytes("ISO-8859-1"),"UTF-8");//request.getParameter("newSecc");
		
		ConnectionWrapper connect = null;
		CallableStatement stmt = null;
		//ResultSet set = null;

		try {

			connect = new ConnectionWrapper();
			stmt = connect.prepareCall(DERCORP_REPORTFLEX_PKG.UPDATE_SECCION_PR);

			
			stmt.setInt(1, Integer.parseInt(seccId));
			stmt.setString(2, seccName);

			stmt.execute();

		} finally {

			ConnectionWrapper.closeAll(stmt, connect);
		}
		
	}
	
	

	private static void deleteSecc(int idFlexRep, ServletRequest request) throws Exception {
		
		int idSecc = Integer.parseInt(request.getParameter("idSecc"));
		
		ConnectionWrapper connect = null;
		CallableStatement stmt = null;
		//ResultSet set = null;

		try {

			connect = new ConnectionWrapper();
			stmt = connect.prepareCall(DERCORP_REPORTFLEX_PKG.DELETE_SECCION_PR);

			
			stmt.setInt(1, idSecc);
			
			stmt.execute();

		} finally {

			ConnectionWrapper.closeAll(stmt, connect);
		}
		
	}
	
	
	
	
	private static void deleteRow(ServletRequest request) throws Exception {
		
		int idRow = Integer.parseInt(request.getParameter("idRow"));
		
		ConnectionWrapper connect = null;
		CallableStatement stmt = null;
		//ResultSet set = null;

		try {

			connect = new ConnectionWrapper();
			stmt = connect.prepareCall(DERCORP_REPORTFLEX_PKG.DELETE_ROW_PR);

			
			stmt.setInt(1, idRow);
			
			stmt.execute();

		} finally {

			ConnectionWrapper.closeAll(stmt, connect);
		}
		
	}
	

	private static void newRow(int idFlexRep, ServletRequest request) throws Exception {
		
		int idSecc = Integer.parseInt(request.getParameter("idSecc"));
		int fieldNum = Integer.parseInt(request.getParameter("fieldNum"));
		
		ConnectionWrapper connect = null;
		CallableStatement stmt = null;
		//ResultSet set = null;

		try {

			connect = new ConnectionWrapper();
			stmt = connect.prepareCall(DERCORP_REPORTFLEX_PKG.INSERT_ROW_PR);

			
			stmt.setInt(1, idSecc);
			stmt.setInt(2, fieldNum);
			
			stmt.execute();

		} finally {

			ConnectionWrapper.closeAll(stmt, connect);
		}
		
	}
	


	private static void updateField(int idFlexRep, ServletRequest request) throws Exception {
		
		int reportFlexFieldId = Integer.parseInt(request.getParameter("reportFlexFieldId"));
		int appFlexFieldIf = Integer.parseInt(request.getParameter("appFlexFieldIf"));
		
		ConnectionWrapper connect = null;
		CallableStatement stmt = null;
		//ResultSet set = null;

		try {

			connect = new ConnectionWrapper();
			stmt = connect.prepareCall(DERCORP_REPORTFLEX_PKG.UPDATE_FIELD_PR);

			
			stmt.setInt(1, reportFlexFieldId);
			stmt.setInt(2, appFlexFieldIf);
			
			stmt.execute();

		} finally {

			ConnectionWrapper.closeAll(stmt, connect);
		}
		
	}
	
	
	

	private static void updateFlexColumns(ServletRequest request) throws Exception {
		
		int flexReportFieldId = Integer.parseInt(request.getParameter("flexReportFieldId"));
		String ids = request.getParameter("ids");
		
		ConnectionWrapper connect = null;
		CallableStatement stmt = null;
		//ResultSet set = null;

		try {

			connect = new ConnectionWrapper();
			stmt = connect.prepareCall(DERCORP_REPORTFLEX_PKG.UPDATE_FIELD_COLUMNS_PR);

			
			stmt.setInt(1, flexReportFieldId);
			stmt.setString(2, ids);
			
			stmt.execute();

		} finally {

			ConnectionWrapper.closeAll(stmt, connect);
		}
		
	}
	
	
	
}

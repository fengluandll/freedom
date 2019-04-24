package mx.com.televisa.derechocorporativo.modules.reportsFlex;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;

import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;
import mx.com.televisa.derechocorporativo.data.packages.DERCORP_REPORTFLEX_PARAMS_PKG;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

public class DBParamHandler {

	public static LinkedHashMap<String, String> getParams(String flexReportId) {
		
		
		LinkedHashMap<String, String> params = new LinkedHashMap<String, String>();
		
		ConnectionWrapper connect = null;
		CallableStatement stmt = null;
		ResultSet set = null;

		try {

			connect = new ConnectionWrapper();
			
			stmt = connect.prepareCall(DERCORP_REPORTFLEX_PARAMS_PKG.SELECT_PARAMS_PR);

			stmt.setString(1, flexReportId);
			
			stmt.registerOutParameter(2, OracleTypes.CURSOR);

			stmt.execute();

			set = ((OracleCallableStatement) stmt).getCursor(2);

			while (set.next()) {
				
				params.put(set.getString(1), set.getString(2));
			}

			return params;
			
		} catch (Exception ex) {

			// TODO - Pendiente - Gestion de Excepciones
			ex.printStackTrace();

		} finally {
			
			ConnectionWrapper.closeAll(set, stmt, connect);
		}
		return null;
	}
	
	

	public static void insertParam(String flexReportId, String idParam) {
		
		ConnectionWrapper connect = null;
		CallableStatement stmt = null;
		ResultSet set = null;

		try {

			connect = new ConnectionWrapper();
			
			stmt = connect.prepareCall(DERCORP_REPORTFLEX_PARAMS_PKG.INSERT_PARAM_PR);

			stmt.setString(1, flexReportId);
			stmt.setString(2, idParam);

			stmt.executeUpdate();

		} catch (Exception ex) {

			// TODO - Pendiente - Gestion de Excepciones
			ex.printStackTrace();

		} finally {
			
			ConnectionWrapper.closeAll(set, stmt, connect);
		}
	}
	

	public static void deleteParam(String flexReportId, String idParam) {
		
		ConnectionWrapper connect = null;
		CallableStatement stmt = null;
		ResultSet set = null;

		try {

			connect = new ConnectionWrapper();
			
			stmt = connect.prepareCall(DERCORP_REPORTFLEX_PARAMS_PKG.DELETE_PARAM_PR);

			stmt.setString(1, flexReportId);
			stmt.setString(2, idParam);

			stmt.executeUpdate();

		} catch (Exception ex) {

			// TODO - Pendiente - Gestion de Excepciones
			ex.printStackTrace();

		} finally {
			
			ConnectionWrapper.closeAll(set, stmt, connect);
		}
	}
	
	
	public static void updateParam(String flexReportId, String idParam, String value) {
		
		ConnectionWrapper connect = null;
		CallableStatement stmt = null;
		ResultSet set = null;

		try {

			connect = new ConnectionWrapper();
			
			stmt = connect.prepareCall(DERCORP_REPORTFLEX_PARAMS_PKG.UPDATE_PARAM_PR);

			stmt.setString(1, flexReportId);
			stmt.setString(2, idParam);
			stmt.setString(3, value);

			stmt.executeUpdate();

		} catch (Exception ex) {

			// TODO - Pendiente - Gestion de Excepciones
			ex.printStackTrace();

		} finally {
			
			ConnectionWrapper.closeAll(set, stmt, connect);
		}
	}
}

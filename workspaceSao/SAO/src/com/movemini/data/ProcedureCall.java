package com.movemini.data;

import java.sql.CallableStatement;
import java.sql.ResultSet;

public class ProcedureCall {


	public static void call(String procedureName, Object... params) {

		ConnectionWrapper conn = null;
		CallableStatement stmt = null;
		
		ResultSet set = null;
		
		try {
			
			conn = new ConnectionWrapper();
			
			stmt = conn.prepareCallProcedure(procedureName, params);   

			int result = stmt.executeUpdate();
			
			//
						
		}catch(Exception ex){
			
			//LOG EXCEPTION
			ex.printStackTrace();
			
			//return null;
			
		} finally {
			
			ConnectionWrapper.closeAll(set, stmt, conn);
		}
		
	}
}

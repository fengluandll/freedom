package com.movemini.data;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OneRecordFactory {

	public static Exception lastException;
	
	public static Record getFirstRecord(String sql) {

		ConnectionWrapper conn = null;
		PreparedStatement stmt = null;
		
		ResultSet set = null;
		
		try {
			
			conn = new ConnectionWrapper();
			
			stmt = conn.prepareStatement(sql); 

			set = stmt.executeQuery();
			
			set.next();
			
			return new Record(set);
						
		}catch(Exception ex){
			
			//LOG EXCEPTION
			ex.printStackTrace();
			
			lastException = ex;
			
			return null;
			
		} finally {
			
			ConnectionWrapper.closeAll(set, stmt, conn);
		}
		
	}
	
	
	public static Record getPr(String procedureName, Object... params) {

		ConnectionWrapper conn = null;
		CallableStatement stmt = null;
		
		ResultSet set = null;
		
		try {
			
			conn = new ConnectionWrapper();
			
			stmt = conn.prepareCallProcedure(procedureName, params);   

			set = stmt.executeQuery();
			
			set.next();
			
			return new Record(set);
						
		}catch(Exception ex){
			
			//LOG EXCEPTION
			ex.printStackTrace();
			
			return null;
			
		} finally {
			
			ConnectionWrapper.closeAll(set, stmt, conn);
		}
		
	}
	
}

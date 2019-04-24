package com.movemini.data;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OneValueFactory {

	
	public static int getInt(String procedureName, Object... params) {
		
		return Integer.parseInt(get(procedureName, params));
	}
	/**
	 * 
	 */
	public static String get(String procedureName, Object... params) {

		ConnectionWrapper conn = null;
		CallableStatement stmt = null;
		
		ResultSet set = null;
		
		try {
			
			conn = new ConnectionWrapper();
			
			stmt = conn.prepareCallProcedure(procedureName, params);   

			set = stmt.executeQuery();
			
			set.next();
				
			String value = set.getString(1);
			
			return value;
			
		}catch(Exception ex){
			
			//LOG EXCEPTION
			ex.printStackTrace();
			
			return null;
			
		} finally {
			
			ConnectionWrapper.closeAll(set, stmt, conn);
		}
		
	}
	
	
	public static String get(String sql) {

		ConnectionWrapper conn = null;
		PreparedStatement stmt = null;
		
		ResultSet set = null;
		
		try {
			
			conn = new ConnectionWrapper();
			
			stmt = conn.prepareStatement(sql);   

			set = stmt.executeQuery();
			
			set.next();
				
			String value = set.getString(1);
			
			return value;
			
		}catch(Exception ex){
			
			System.out.println(sql);
			System.out.println(ex);
			//LOG EXCEPTION
			//ex.printStackTrace();
			
			return null;
			
		} finally {
			
			ConnectionWrapper.closeAll(set, stmt, conn);
		}
		
	}
}

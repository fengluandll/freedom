package com.movemini.data;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class DataMap {
	
	Map<String, String> map;
	
	public DataMap(String procedure, String ... params) {
		
		this(procedure, 1, 2, params);
	}
	
	public DataMap(String procedure, int posId, int posName, String ... params) {
				
		map = new HashMap<String, String>();
		
		ConnectionWrapper conn = null;
		CallableStatement stmt = null;
		
		ResultSet set = null;
		
		try {
			
			conn = new ConnectionWrapper();
			
			stmt = conn.prepareCallProcedure(procedure, params);

			set = stmt.executeQuery();
			
			while(set.next()) {
				
				String key = set.getString(posId);
				String value = set.getString(posName);
				
				map.put(key, value);
			}
			
		}catch(Exception ex){
			
			//LOG EXCEPTION
			ex.printStackTrace();
			
		} finally {
			
			ConnectionWrapper.closeAll(set, stmt, conn);
			
		}
	}
	
	public String get(String key) {
		
		try {
			
			String value = map.get(key);
			
			if(value != null) {
				return map.get(key);
			} else {
				
				return "";
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			return "";
		}
	}
	
}

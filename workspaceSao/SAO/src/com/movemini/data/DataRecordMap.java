package com.movemini.data;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class DataRecordMap {
	
	Map<String, Record> map;
	
	
	/*
	public DataRecordMap(String procedure, String ... params) {
		
		this(procedure, 1, 2, params);
	}*/
	
	public DataRecordMap(String procedure, String keyField,  String ... params) {
				
		map = new HashMap<String, Record>();
		
		ConnectionWrapper conn = null;
		CallableStatement stmt = null;
		
		ResultSet set = null;
		
		try {
			
			conn = new ConnectionWrapper();
			
			stmt = conn.prepareCallProcedure(procedure, params);

			set = stmt.executeQuery();
			
			while(set.next()) {
				
				String key = set.getString(keyField);
				//String value = set.getString(posName);
				
				map.put(key, new Record(set));
			}
			
		}catch(Exception ex){
			
			//LOG EXCEPTION
			ex.printStackTrace();
			
		} finally {
			
			ConnectionWrapper.closeAll(set, stmt, conn);
			
		}
	}
	
	public Record get(String key) {
		
		try {
			
			Record value = map.get(key);
			
			if(value != null) {
				return map.get(key);
			} else {
				
				return null;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
			return null;
		}
	}
	
}
